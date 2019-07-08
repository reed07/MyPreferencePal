package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.grpc.Codec.Identity;
import io.grpc.Decompressor;
import io.grpc.Status;
import io.grpc.internal.StreamListener.MessageProducer;
import java.io.Closeable;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class MessageDeframer implements Deframer, Closeable {
    private boolean closeWhenComplete = false;
    private boolean compressedFlag;
    private int currentMessageSeqNo = -1;
    private Decompressor decompressor;
    private GzipInflatingBuffer fullStreamDecompressor;
    private boolean inDelivery = false;
    private int inboundBodyWireSize;
    private byte[] inflatedBuffer;
    private int inflatedIndex;
    private Listener listener;
    private int maxInboundMessageSize;
    private CompositeReadableBuffer nextFrame;
    private long pendingDeliveries;
    private int requiredLength = 5;
    private State state = State.HEADER;
    private final StatsTraceContext statsTraceCtx;
    private volatile boolean stopDelivery = false;
    private final TransportTracer transportTracer;
    private CompositeReadableBuffer unprocessed = new CompositeReadableBuffer();

    public interface Listener {
        void bytesRead(int i);

        void deframeFailed(Throwable th);

        void deframerClosed(boolean z);

        void messagesAvailable(MessageProducer messageProducer);
    }

    private static class SingleMessageProducer implements MessageProducer {
        private InputStream message;

        private SingleMessageProducer(InputStream inputStream) {
            this.message = inputStream;
        }

        @Nullable
        public InputStream next() {
            InputStream inputStream = this.message;
            this.message = null;
            return inputStream;
        }
    }

    @VisibleForTesting
    static final class SizeEnforcingInputStream extends FilterInputStream {
        private long count;
        private long mark = -1;
        private long maxCount;
        private final int maxMessageSize;
        private final StatsTraceContext statsTraceCtx;

        SizeEnforcingInputStream(InputStream inputStream, int i, StatsTraceContext statsTraceContext) {
            super(inputStream);
            this.maxMessageSize = i;
            this.statsTraceCtx = statsTraceContext;
        }

        public int read() throws IOException {
            int read = this.in.read();
            if (read != -1) {
                this.count++;
            }
            verifySize();
            reportCount();
            return read;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = this.in.read(bArr, i, i2);
            if (read != -1) {
                this.count += (long) read;
            }
            verifySize();
            reportCount();
            return read;
        }

        public long skip(long j) throws IOException {
            long skip = this.in.skip(j);
            this.count += skip;
            verifySize();
            reportCount();
            return skip;
        }

        public synchronized void mark(int i) {
            this.in.mark(i);
            this.mark = this.count;
        }

        public synchronized void reset() throws IOException {
            if (!this.in.markSupported()) {
                throw new IOException("Mark not supported");
            } else if (this.mark != -1) {
                this.in.reset();
                this.count = this.mark;
            } else {
                throw new IOException("Mark not set");
            }
        }

        private void reportCount() {
            long j = this.count;
            long j2 = this.maxCount;
            if (j > j2) {
                this.statsTraceCtx.inboundUncompressedSize(j - j2);
                this.maxCount = this.count;
            }
        }

        private void verifySize() {
            if (this.count > ((long) this.maxMessageSize)) {
                throw Status.RESOURCE_EXHAUSTED.withDescription(String.format("Compressed gRPC message exceeds maximum size %d: %d bytes read", new Object[]{Integer.valueOf(this.maxMessageSize), Long.valueOf(this.count)})).asRuntimeException();
            }
        }
    }

    private enum State {
        HEADER,
        BODY
    }

    public MessageDeframer(Listener listener2, Decompressor decompressor2, int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer2) {
        this.listener = (Listener) Preconditions.checkNotNull(listener2, "sink");
        this.decompressor = (Decompressor) Preconditions.checkNotNull(decompressor2, "decompressor");
        this.maxInboundMessageSize = i;
        this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer2, "transportTracer");
    }

    /* access modifiers changed from: 0000 */
    public void setListener(Listener listener2) {
        this.listener = listener2;
    }

    public void setMaxInboundMessageSize(int i) {
        this.maxInboundMessageSize = i;
    }

    public void setDecompressor(Decompressor decompressor2) {
        Preconditions.checkState(this.fullStreamDecompressor == null, "Already set full stream decompressor");
        this.decompressor = (Decompressor) Preconditions.checkNotNull(decompressor2, "Can't pass an empty decompressor");
    }

    public void setFullStreamDecompressor(GzipInflatingBuffer gzipInflatingBuffer) {
        boolean z = true;
        Preconditions.checkState(this.decompressor == Identity.NONE, "per-message decompressor already set");
        if (this.fullStreamDecompressor != null) {
            z = false;
        }
        Preconditions.checkState(z, "full stream decompressor already set");
        this.fullStreamDecompressor = (GzipInflatingBuffer) Preconditions.checkNotNull(gzipInflatingBuffer, "Can't pass a null full stream decompressor");
        this.unprocessed = null;
    }

    public void request(int i) {
        Preconditions.checkArgument(i > 0, "numMessages must be > 0");
        if (!isClosed()) {
            this.pendingDeliveries += (long) i;
            deliver();
        }
    }

    /* JADX INFO: finally extract failed */
    public void deframe(ReadableBuffer readableBuffer) {
        Preconditions.checkNotNull(readableBuffer, "data");
        boolean z = true;
        try {
            if (!isClosedOrScheduledToClose()) {
                if (this.fullStreamDecompressor != null) {
                    this.fullStreamDecompressor.addGzippedBytes(readableBuffer);
                } else {
                    this.unprocessed.addBuffer(readableBuffer);
                }
                z = false;
                deliver();
            }
            if (z) {
                readableBuffer.close();
            }
        } catch (Throwable th) {
            if (1 != 0) {
                readableBuffer.close();
            }
            throw th;
        }
    }

    public void closeWhenComplete() {
        if (!isClosed()) {
            if (isStalled()) {
                close();
            } else {
                this.closeWhenComplete = true;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void stopDelivery() {
        this.stopDelivery = true;
    }

    /* JADX INFO: finally extract failed */
    public void close() {
        boolean z;
        if (!isClosed()) {
            CompositeReadableBuffer compositeReadableBuffer = this.nextFrame;
            boolean z2 = compositeReadableBuffer != null && compositeReadableBuffer.readableBytes() > 0;
            try {
                if (this.fullStreamDecompressor != null) {
                    if (!z2) {
                        if (!this.fullStreamDecompressor.hasPartialData()) {
                            z = false;
                            this.fullStreamDecompressor.close();
                        }
                    }
                    z = true;
                    this.fullStreamDecompressor.close();
                }
                if (this.unprocessed != null) {
                    this.unprocessed.close();
                }
                if (this.nextFrame != null) {
                    this.nextFrame.close();
                }
                this.fullStreamDecompressor = null;
                this.unprocessed = null;
                this.nextFrame = null;
                this.listener.deframerClosed(z2);
            } catch (Throwable th) {
                this.fullStreamDecompressor = null;
                this.unprocessed = null;
                this.nextFrame = null;
                throw th;
            }
        }
    }

    public boolean isClosed() {
        return this.unprocessed == null && this.fullStreamDecompressor == null;
    }

    private boolean isClosedOrScheduledToClose() {
        return isClosed() || this.closeWhenComplete;
    }

    private boolean isStalled() {
        GzipInflatingBuffer gzipInflatingBuffer = this.fullStreamDecompressor;
        if (gzipInflatingBuffer != null) {
            return gzipInflatingBuffer.isStalled();
        }
        return this.unprocessed.readableBytes() == 0;
    }

    private void deliver() {
        if (!this.inDelivery) {
            this.inDelivery = true;
            while (!this.stopDelivery && this.pendingDeliveries > 0 && readRequiredBytes()) {
                try {
                    switch (this.state) {
                        case HEADER:
                            processHeader();
                            break;
                        case BODY:
                            processBody();
                            this.pendingDeliveries--;
                            break;
                        default:
                            StringBuilder sb = new StringBuilder();
                            sb.append("Invalid state: ");
                            sb.append(this.state);
                            throw new AssertionError(sb.toString());
                    }
                } finally {
                    this.inDelivery = false;
                }
            }
            if (this.stopDelivery) {
                close();
                return;
            }
            if (this.closeWhenComplete && isStalled()) {
                close();
            }
            this.inDelivery = false;
        }
    }

    private boolean readRequiredBytes() {
        int i;
        int i2;
        Throwable th;
        try {
            if (this.nextFrame == null) {
                this.nextFrame = new CompositeReadableBuffer();
            }
            i2 = 0;
            i = 0;
            while (true) {
                try {
                    int readableBytes = this.requiredLength - this.nextFrame.readableBytes();
                    if (readableBytes <= 0) {
                        if (i2 > 0) {
                            this.listener.bytesRead(i2);
                            if (this.state == State.BODY) {
                                if (this.fullStreamDecompressor != null) {
                                    this.statsTraceCtx.inboundWireSize((long) i);
                                    this.inboundBodyWireSize += i;
                                } else {
                                    this.statsTraceCtx.inboundWireSize((long) i2);
                                    this.inboundBodyWireSize += i2;
                                }
                            }
                        }
                        return true;
                    } else if (this.fullStreamDecompressor != null) {
                        if (this.inflatedBuffer == null || this.inflatedIndex == this.inflatedBuffer.length) {
                            this.inflatedBuffer = new byte[Math.min(readableBytes, 2097152)];
                            this.inflatedIndex = 0;
                        }
                        int inflateBytes = this.fullStreamDecompressor.inflateBytes(this.inflatedBuffer, this.inflatedIndex, Math.min(readableBytes, this.inflatedBuffer.length - this.inflatedIndex));
                        i2 += this.fullStreamDecompressor.getAndResetBytesConsumed();
                        i += this.fullStreamDecompressor.getAndResetDeflatedBytesConsumed();
                        if (inflateBytes == 0) {
                            if (i2 > 0) {
                                this.listener.bytesRead(i2);
                                if (this.state == State.BODY) {
                                    if (this.fullStreamDecompressor != null) {
                                        this.statsTraceCtx.inboundWireSize((long) i);
                                        this.inboundBodyWireSize += i;
                                    } else {
                                        this.statsTraceCtx.inboundWireSize((long) i2);
                                        this.inboundBodyWireSize += i2;
                                    }
                                }
                            }
                            return false;
                        }
                        this.nextFrame.addBuffer(ReadableBuffers.wrap(this.inflatedBuffer, this.inflatedIndex, inflateBytes));
                        this.inflatedIndex += inflateBytes;
                    } else if (this.unprocessed.readableBytes() == 0) {
                        if (i2 > 0) {
                            this.listener.bytesRead(i2);
                            if (this.state == State.BODY) {
                                if (this.fullStreamDecompressor != null) {
                                    this.statsTraceCtx.inboundWireSize((long) i);
                                    this.inboundBodyWireSize += i;
                                } else {
                                    this.statsTraceCtx.inboundWireSize((long) i2);
                                    this.inboundBodyWireSize += i2;
                                }
                            }
                        }
                        return false;
                    } else {
                        int min = Math.min(readableBytes, this.unprocessed.readableBytes());
                        i2 += min;
                        this.nextFrame.addBuffer(this.unprocessed.readBytes(min));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (DataFormatException e2) {
                    throw new RuntimeException(e2);
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            i2 = 0;
            i = 0;
            if (i2 > 0) {
                this.listener.bytesRead(i2);
                if (this.state == State.BODY) {
                    if (this.fullStreamDecompressor != null) {
                        this.statsTraceCtx.inboundWireSize((long) i);
                        this.inboundBodyWireSize += i;
                    } else {
                        this.statsTraceCtx.inboundWireSize((long) i2);
                        this.inboundBodyWireSize += i2;
                    }
                }
            }
            throw th;
        }
    }

    private void processHeader() {
        int readUnsignedByte = this.nextFrame.readUnsignedByte();
        if ((readUnsignedByte & 254) == 0) {
            this.compressedFlag = (readUnsignedByte & 1) != 0;
            this.requiredLength = this.nextFrame.readInt();
            int i = this.requiredLength;
            if (i < 0 || i > this.maxInboundMessageSize) {
                throw Status.RESOURCE_EXHAUSTED.withDescription(String.format("gRPC message exceeds maximum size %d: %d", new Object[]{Integer.valueOf(this.maxInboundMessageSize), Integer.valueOf(this.requiredLength)})).asRuntimeException();
            }
            this.currentMessageSeqNo++;
            this.statsTraceCtx.inboundMessage(this.currentMessageSeqNo);
            this.transportTracer.reportMessageReceived();
            this.state = State.BODY;
            return;
        }
        throw Status.INTERNAL.withDescription("gRPC frame header malformed: reserved bits not zero").asRuntimeException();
    }

    private void processBody() {
        this.statsTraceCtx.inboundMessageRead(this.currentMessageSeqNo, (long) this.inboundBodyWireSize, -1);
        this.inboundBodyWireSize = 0;
        InputStream compressedBody = this.compressedFlag ? getCompressedBody() : getUncompressedBody();
        this.nextFrame = null;
        this.listener.messagesAvailable(new SingleMessageProducer(compressedBody));
        this.state = State.HEADER;
        this.requiredLength = 5;
    }

    private InputStream getUncompressedBody() {
        this.statsTraceCtx.inboundUncompressedSize((long) this.nextFrame.readableBytes());
        return ReadableBuffers.openStream(this.nextFrame, true);
    }

    private InputStream getCompressedBody() {
        if (this.decompressor != Identity.NONE) {
            try {
                return new SizeEnforcingInputStream(this.decompressor.decompress(ReadableBuffers.openStream(this.nextFrame, true)), this.maxInboundMessageSize, this.statsTraceCtx);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw Status.INTERNAL.withDescription("Can't decode compressed gRPC message as compression not configured").asRuntimeException();
        }
    }
}
