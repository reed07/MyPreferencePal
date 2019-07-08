package io.grpc.okhttp;

import com.brightcove.player.C;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.grpc.internal.SerializingExecutor;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Header;
import io.grpc.okhttp.internal.framed.Settings;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;

class AsyncFrameWriter implements FrameWriter {
    private static final Set<String> QUIET_ERRORS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"Socket closed"})));
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(OkHttpClientTransport.class.getName());
    private final SerializingExecutor executor;
    /* access modifiers changed from: private */
    public final AtomicLong flushCounter = new AtomicLong();
    /* access modifiers changed from: private */
    public FrameWriter frameWriter;
    /* access modifiers changed from: private */
    public Socket socket;
    /* access modifiers changed from: private */
    public final TransportExceptionHandler transportExceptionHandler;

    interface TransportExceptionHandler {
        void onException(Throwable th);
    }

    private abstract class WriteRunnable implements Runnable {
        public abstract void doRun() throws IOException;

        private WriteRunnable() {
        }

        public final void run() {
            try {
                if (AsyncFrameWriter.this.frameWriter != null) {
                    doRun();
                    return;
                }
                throw new IOException("Unable to perform write due to unavailable frameWriter.");
            } catch (RuntimeException e) {
                AsyncFrameWriter.this.transportExceptionHandler.onException(e);
            } catch (Exception e2) {
                AsyncFrameWriter.this.transportExceptionHandler.onException(e2);
            }
        }
    }

    public AsyncFrameWriter(TransportExceptionHandler transportExceptionHandler2, SerializingExecutor serializingExecutor) {
        this.transportExceptionHandler = transportExceptionHandler2;
        this.executor = serializingExecutor;
    }

    /* access modifiers changed from: 0000 */
    public void becomeConnected(FrameWriter frameWriter2, Socket socket2) {
        Preconditions.checkState(this.frameWriter == null, "AsyncFrameWriter's setFrameWriter() should only be called once.");
        this.frameWriter = (FrameWriter) Preconditions.checkNotNull(frameWriter2, "frameWriter");
        this.socket = (Socket) Preconditions.checkNotNull(socket2, "socket");
    }

    public void connectionPreface() {
        this.executor.execute(new WriteRunnable() {
            public void doRun() throws IOException {
                AsyncFrameWriter.this.frameWriter.connectionPreface();
            }
        });
    }

    public void ackSettings(final Settings settings) {
        this.executor.execute(new WriteRunnable() {
            public void doRun() throws IOException {
                AsyncFrameWriter.this.frameWriter.ackSettings(settings);
            }
        });
    }

    public void flush() {
        final long incrementAndGet = this.flushCounter.incrementAndGet();
        this.executor.execute(new WriteRunnable() {
            public void doRun() throws IOException {
                if (AsyncFrameWriter.this.flushCounter.get() == incrementAndGet) {
                    AsyncFrameWriter.this.frameWriter.flush();
                }
            }
        });
    }

    public void synStream(boolean z, boolean z2, int i, int i2, List<Header> list) {
        SerializingExecutor serializingExecutor = this.executor;
        final boolean z3 = z;
        final boolean z4 = z2;
        final int i3 = i;
        final int i4 = i2;
        final List<Header> list2 = list;
        AnonymousClass5 r1 = new WriteRunnable() {
            public void doRun() throws IOException {
                AsyncFrameWriter.this.frameWriter.synStream(z3, z4, i3, i4, list2);
            }
        };
        serializingExecutor.execute(r1);
    }

    public void rstStream(final int i, final ErrorCode errorCode) {
        this.executor.execute(new WriteRunnable() {
            public void doRun() throws IOException {
                AsyncFrameWriter.this.frameWriter.rstStream(i, errorCode);
            }
        });
    }

    public void data(boolean z, int i, Buffer buffer, int i2) {
        SerializingExecutor serializingExecutor = this.executor;
        final boolean z2 = z;
        final int i3 = i;
        final Buffer buffer2 = buffer;
        final int i4 = i2;
        AnonymousClass9 r1 = new WriteRunnable() {
            public void doRun() throws IOException {
                AsyncFrameWriter.this.frameWriter.data(z2, i3, buffer2, i4);
            }
        };
        serializingExecutor.execute(r1);
    }

    public void settings(final Settings settings) {
        this.executor.execute(new WriteRunnable() {
            public void doRun() throws IOException {
                AsyncFrameWriter.this.frameWriter.settings(settings);
            }
        });
    }

    public void ping(final boolean z, final int i, final int i2) {
        this.executor.execute(new WriteRunnable() {
            public void doRun() throws IOException {
                AsyncFrameWriter.this.frameWriter.ping(z, i, i2);
            }
        });
    }

    public void goAway(final int i, final ErrorCode errorCode, final byte[] bArr) {
        this.executor.execute(new WriteRunnable() {
            public void doRun() throws IOException {
                AsyncFrameWriter.this.frameWriter.goAway(i, errorCode, bArr);
                AsyncFrameWriter.this.frameWriter.flush();
            }
        });
    }

    public void windowUpdate(final int i, final long j) {
        this.executor.execute(new WriteRunnable() {
            public void doRun() throws IOException {
                AsyncFrameWriter.this.frameWriter.windowUpdate(i, j);
            }
        });
    }

    public void close() {
        this.executor.execute(new Runnable() {
            public void run() {
                if (AsyncFrameWriter.this.frameWriter != null) {
                    try {
                        AsyncFrameWriter.this.frameWriter.close();
                        AsyncFrameWriter.this.socket.close();
                    } catch (IOException e) {
                        AsyncFrameWriter.log.log(AsyncFrameWriter.getLogLevel(e), "Failed closing connection", e);
                    }
                }
            }
        });
    }

    @VisibleForTesting
    static Level getLogLevel(Throwable th) {
        if (!(th instanceof IOException) || th.getMessage() == null || !QUIET_ERRORS.contains(th.getMessage())) {
            return Level.INFO;
        }
        return Level.FINE;
    }

    public int maxDataLength() {
        FrameWriter frameWriter2 = this.frameWriter;
        if (frameWriter2 == null) {
            return C.DASH_ROLE_CAPTION_FLAG;
        }
        return frameWriter2.maxDataLength();
    }
}
