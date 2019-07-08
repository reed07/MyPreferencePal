package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.Codec.Identity;
import io.grpc.Compressor;
import io.grpc.Decompressor;
import io.grpc.internal.MessageDeframer.Listener;
import io.grpc.internal.StreamListener.MessageProducer;
import java.io.InputStream;
import javax.annotation.concurrent.GuardedBy;

public abstract class AbstractStream implements Stream {

    public static abstract class TransportState implements TransportExecutor, Listener {
        @GuardedBy("onReadyLock")
        private boolean allocated;
        @GuardedBy("onReadyLock")
        private boolean deallocated;
        private Deframer deframer;
        @GuardedBy("onReadyLock")
        private int numSentBytesQueued;
        private final Object onReadyLock = new Object();
        private final StatsTraceContext statsTraceCtx;
        private final TransportTracer transportTracer;

        /* access modifiers changed from: protected */
        public abstract StreamListener listener();

        protected TransportState(int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer2) {
            this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
            this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer2, "transportTracer");
            MessageDeframer messageDeframer = new MessageDeframer(this, Identity.NONE, i, statsTraceContext, transportTracer2);
            this.deframer = messageDeframer;
        }

        /* access modifiers changed from: protected */
        public void setFullStreamDecompressor(GzipInflatingBuffer gzipInflatingBuffer) {
            this.deframer.setFullStreamDecompressor(gzipInflatingBuffer);
            this.deframer = new ApplicationThreadDeframer(this, this, (MessageDeframer) this.deframer);
        }

        /* access modifiers changed from: 0000 */
        public final void setMaxInboundMessageSize(int i) {
            this.deframer.setMaxInboundMessageSize(i);
        }

        public void messagesAvailable(MessageProducer messageProducer) {
            listener().messagesAvailable(messageProducer);
        }

        /* access modifiers changed from: protected */
        public final void closeDeframer(boolean z) {
            if (z) {
                this.deframer.close();
            } else {
                this.deframer.closeWhenComplete();
            }
        }

        /* access modifiers changed from: protected */
        public final void deframe(ReadableBuffer readableBuffer) {
            try {
                this.deframer.deframe(readableBuffer);
            } catch (Throwable th) {
                deframeFailed(th);
            }
        }

        public final void requestMessagesFromDeframer(int i) {
            try {
                this.deframer.request(i);
            } catch (Throwable th) {
                deframeFailed(th);
            }
        }

        /* access modifiers changed from: protected */
        public final void setDecompressor(Decompressor decompressor) {
            this.deframer.setDecompressor(decompressor);
        }

        private boolean isReady() {
            boolean z;
            synchronized (this.onReadyLock) {
                z = this.allocated && this.numSentBytesQueued < 32768 && !this.deallocated;
            }
            return z;
        }

        /* access modifiers changed from: protected */
        public void onStreamAllocated() {
            boolean z = false;
            Preconditions.checkState(listener() != null);
            synchronized (this.onReadyLock) {
                if (!this.allocated) {
                    z = true;
                }
                Preconditions.checkState(z, "Already allocated");
                this.allocated = true;
            }
            notifyIfReady();
        }

        /* access modifiers changed from: protected */
        public final void onStreamDeallocated() {
            synchronized (this.onReadyLock) {
                this.deallocated = true;
            }
        }

        /* access modifiers changed from: private */
        public void onSendingBytes(int i) {
            synchronized (this.onReadyLock) {
                this.numSentBytesQueued += i;
            }
        }

        public final void onSentBytes(int i) {
            boolean z;
            synchronized (this.onReadyLock) {
                Preconditions.checkState(this.allocated, "onStreamAllocated was not called, but it seems the stream is active");
                z = true;
                boolean z2 = this.numSentBytesQueued < 32768;
                this.numSentBytesQueued -= i;
                boolean z3 = this.numSentBytesQueued < 32768;
                if (z2 || !z3) {
                    z = false;
                }
            }
            if (z) {
                notifyIfReady();
            }
        }

        /* access modifiers changed from: protected */
        public TransportTracer getTransportTracer() {
            return this.transportTracer;
        }

        private void notifyIfReady() {
            boolean isReady;
            synchronized (this.onReadyLock) {
                isReady = isReady();
            }
            if (isReady) {
                listener().onReady();
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract Framer framer();

    /* access modifiers changed from: protected */
    public abstract TransportState transportState();

    public final void writeMessage(InputStream inputStream) {
        Preconditions.checkNotNull(inputStream, "message");
        try {
            if (!framer().isClosed()) {
                framer().writePayload(inputStream);
            }
        } finally {
            GrpcUtil.closeQuietly(inputStream);
        }
    }

    public final void flush() {
        if (!framer().isClosed()) {
            framer().flush();
        }
    }

    /* access modifiers changed from: protected */
    public final void endOfMessages() {
        framer().close();
    }

    public final void setCompressor(Compressor compressor) {
        framer().setCompressor((Compressor) Preconditions.checkNotNull(compressor, "compressor"));
    }

    /* access modifiers changed from: protected */
    public final void onSendingBytes(int i) {
        transportState().onSendingBytes(i);
    }
}
