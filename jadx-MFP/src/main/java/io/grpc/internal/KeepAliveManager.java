package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.Status;
import io.grpc.internal.ClientTransport.PingCallback;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

public class KeepAliveManager {
    private static final long MIN_KEEPALIVE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(10);
    private static final long MIN_KEEPALIVE_TIME_NANOS = TimeUnit.SECONDS.toNanos(10);
    private static final SystemTicker SYSTEM_TICKER = new SystemTicker();
    private final boolean keepAliveDuringTransportIdle;
    /* access modifiers changed from: private */
    public final KeepAlivePinger keepAlivePinger;
    private final long keepAliveTimeInNanos;
    /* access modifiers changed from: private */
    public final long keepAliveTimeoutInNanos;
    /* access modifiers changed from: private */
    @GuardedBy("this")
    public long nextKeepaliveTime;
    /* access modifiers changed from: private */
    @GuardedBy("this")
    public ScheduledFuture<?> pingFuture;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService scheduler;
    /* access modifiers changed from: private */
    public final Runnable sendPing;
    /* access modifiers changed from: private */
    public final Runnable shutdown;
    /* access modifiers changed from: private */
    @GuardedBy("this")
    public ScheduledFuture<?> shutdownFuture;
    /* access modifiers changed from: private */
    @GuardedBy("this")
    public State state;
    /* access modifiers changed from: private */
    public final Ticker ticker;

    public static final class ClientKeepAlivePinger implements KeepAlivePinger {
        /* access modifiers changed from: private */
        public final ConnectionClientTransport transport;

        public ClientKeepAlivePinger(ConnectionClientTransport connectionClientTransport) {
            this.transport = connectionClientTransport;
        }

        public void ping() {
            this.transport.ping(new PingCallback() {
                public void onSuccess(long j) {
                }

                public void onFailure(Throwable th) {
                    ClientKeepAlivePinger.this.transport.shutdownNow(Status.UNAVAILABLE.withDescription("Keepalive failed. The connection is likely gone"));
                }
            }, MoreExecutors.directExecutor());
        }

        public void onPingTimeout() {
            this.transport.shutdownNow(Status.UNAVAILABLE.withDescription("Keepalive failed. The connection is likely gone"));
        }
    }

    public interface KeepAlivePinger {
        void onPingTimeout();

        void ping();
    }

    private enum State {
        IDLE,
        PING_SCHEDULED,
        PING_DELAYED,
        PING_SENT,
        IDLE_AND_PING_SENT,
        DISCONNECTED
    }

    private static class SystemTicker extends Ticker {
        private SystemTicker() {
        }

        public long read() {
            return System.nanoTime();
        }
    }

    static abstract class Ticker {
        public abstract long read();

        Ticker() {
        }
    }

    public KeepAliveManager(KeepAlivePinger keepAlivePinger2, ScheduledExecutorService scheduledExecutorService, long j, long j2, boolean z) {
        this(keepAlivePinger2, scheduledExecutorService, SYSTEM_TICKER, j, j2, z);
    }

    @VisibleForTesting
    KeepAliveManager(KeepAlivePinger keepAlivePinger2, ScheduledExecutorService scheduledExecutorService, Ticker ticker2, long j, long j2, boolean z) {
        this.state = State.IDLE;
        this.shutdown = new LogExceptionRunnable(new Runnable() {
            public void run() {
                boolean z;
                synchronized (KeepAliveManager.this) {
                    if (KeepAliveManager.this.state != State.DISCONNECTED) {
                        KeepAliveManager.this.state = State.DISCONNECTED;
                        z = true;
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    KeepAliveManager.this.keepAlivePinger.onPingTimeout();
                }
            }
        });
        this.sendPing = new LogExceptionRunnable(new Runnable() {
            public void run() {
                boolean z;
                synchronized (KeepAliveManager.this) {
                    KeepAliveManager.this.pingFuture = null;
                    if (KeepAliveManager.this.state == State.PING_SCHEDULED) {
                        z = true;
                        KeepAliveManager.this.state = State.PING_SENT;
                        KeepAliveManager.this.shutdownFuture = KeepAliveManager.this.scheduler.schedule(KeepAliveManager.this.shutdown, KeepAliveManager.this.keepAliveTimeoutInNanos, TimeUnit.NANOSECONDS);
                    } else {
                        if (KeepAliveManager.this.state == State.PING_DELAYED) {
                            KeepAliveManager.this.pingFuture = KeepAliveManager.this.scheduler.schedule(KeepAliveManager.this.sendPing, KeepAliveManager.this.nextKeepaliveTime - KeepAliveManager.this.ticker.read(), TimeUnit.NANOSECONDS);
                            KeepAliveManager.this.state = State.PING_SCHEDULED;
                        }
                        z = false;
                    }
                }
                if (z) {
                    KeepAliveManager.this.keepAlivePinger.ping();
                }
            }
        });
        this.keepAlivePinger = (KeepAlivePinger) Preconditions.checkNotNull(keepAlivePinger2, "keepAlivePinger");
        this.scheduler = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduler");
        this.ticker = (Ticker) Preconditions.checkNotNull(ticker2, "ticker");
        this.keepAliveTimeInNanos = j;
        this.keepAliveTimeoutInNanos = j2;
        this.keepAliveDuringTransportIdle = z;
        this.nextKeepaliveTime = ticker2.read() + j;
    }

    public synchronized void onTransportStarted() {
        if (this.keepAliveDuringTransportIdle) {
            onTransportActive();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0056, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onDataReceived() {
        /*
            r5 = this;
            monitor-enter(r5)
            io.grpc.internal.KeepAliveManager$Ticker r0 = r5.ticker     // Catch:{ all -> 0x0057 }
            long r0 = r0.read()     // Catch:{ all -> 0x0057 }
            long r2 = r5.keepAliveTimeInNanos     // Catch:{ all -> 0x0057 }
            long r0 = r0 + r2
            r5.nextKeepaliveTime = r0     // Catch:{ all -> 0x0057 }
            io.grpc.internal.KeepAliveManager$State r0 = r5.state     // Catch:{ all -> 0x0057 }
            io.grpc.internal.KeepAliveManager$State r1 = io.grpc.internal.KeepAliveManager.State.PING_SCHEDULED     // Catch:{ all -> 0x0057 }
            if (r0 != r1) goto L_0x0017
            io.grpc.internal.KeepAliveManager$State r0 = io.grpc.internal.KeepAliveManager.State.PING_DELAYED     // Catch:{ all -> 0x0057 }
            r5.state = r0     // Catch:{ all -> 0x0057 }
            goto L_0x0055
        L_0x0017:
            io.grpc.internal.KeepAliveManager$State r0 = r5.state     // Catch:{ all -> 0x0057 }
            io.grpc.internal.KeepAliveManager$State r1 = io.grpc.internal.KeepAliveManager.State.PING_SENT     // Catch:{ all -> 0x0057 }
            if (r0 == r1) goto L_0x0023
            io.grpc.internal.KeepAliveManager$State r0 = r5.state     // Catch:{ all -> 0x0057 }
            io.grpc.internal.KeepAliveManager$State r1 = io.grpc.internal.KeepAliveManager.State.IDLE_AND_PING_SENT     // Catch:{ all -> 0x0057 }
            if (r0 != r1) goto L_0x0055
        L_0x0023:
            java.util.concurrent.ScheduledFuture<?> r0 = r5.shutdownFuture     // Catch:{ all -> 0x0057 }
            r1 = 0
            if (r0 == 0) goto L_0x002d
            java.util.concurrent.ScheduledFuture<?> r0 = r5.shutdownFuture     // Catch:{ all -> 0x0057 }
            r0.cancel(r1)     // Catch:{ all -> 0x0057 }
        L_0x002d:
            io.grpc.internal.KeepAliveManager$State r0 = r5.state     // Catch:{ all -> 0x0057 }
            io.grpc.internal.KeepAliveManager$State r2 = io.grpc.internal.KeepAliveManager.State.IDLE_AND_PING_SENT     // Catch:{ all -> 0x0057 }
            if (r0 != r2) goto L_0x0039
            io.grpc.internal.KeepAliveManager$State r0 = io.grpc.internal.KeepAliveManager.State.IDLE     // Catch:{ all -> 0x0057 }
            r5.state = r0     // Catch:{ all -> 0x0057 }
            monitor-exit(r5)
            return
        L_0x0039:
            io.grpc.internal.KeepAliveManager$State r0 = io.grpc.internal.KeepAliveManager.State.PING_SCHEDULED     // Catch:{ all -> 0x0057 }
            r5.state = r0     // Catch:{ all -> 0x0057 }
            java.util.concurrent.ScheduledFuture<?> r0 = r5.pingFuture     // Catch:{ all -> 0x0057 }
            if (r0 != 0) goto L_0x0042
            r1 = 1
        L_0x0042:
            java.lang.String r0 = "There should be no outstanding pingFuture"
            com.google.common.base.Preconditions.checkState(r1, r0)     // Catch:{ all -> 0x0057 }
            java.util.concurrent.ScheduledExecutorService r0 = r5.scheduler     // Catch:{ all -> 0x0057 }
            java.lang.Runnable r1 = r5.sendPing     // Catch:{ all -> 0x0057 }
            long r2 = r5.keepAliveTimeInNanos     // Catch:{ all -> 0x0057 }
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ all -> 0x0057 }
            java.util.concurrent.ScheduledFuture r0 = r0.schedule(r1, r2, r4)     // Catch:{ all -> 0x0057 }
            r5.pingFuture = r0     // Catch:{ all -> 0x0057 }
        L_0x0055:
            monitor-exit(r5)
            return
        L_0x0057:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.KeepAliveManager.onDataReceived():void");
    }

    public synchronized void onTransportActive() {
        if (this.state == State.IDLE) {
            this.state = State.PING_SCHEDULED;
            if (this.pingFuture == null) {
                this.pingFuture = this.scheduler.schedule(this.sendPing, this.nextKeepaliveTime - this.ticker.read(), TimeUnit.NANOSECONDS);
            }
        } else if (this.state == State.IDLE_AND_PING_SENT) {
            this.state = State.PING_SENT;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0022, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onTransportIdle() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.keepAliveDuringTransportIdle     // Catch:{ all -> 0x0023 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            io.grpc.internal.KeepAliveManager$State r0 = r2.state     // Catch:{ all -> 0x0023 }
            io.grpc.internal.KeepAliveManager$State r1 = io.grpc.internal.KeepAliveManager.State.PING_SCHEDULED     // Catch:{ all -> 0x0023 }
            if (r0 == r1) goto L_0x0013
            io.grpc.internal.KeepAliveManager$State r0 = r2.state     // Catch:{ all -> 0x0023 }
            io.grpc.internal.KeepAliveManager$State r1 = io.grpc.internal.KeepAliveManager.State.PING_DELAYED     // Catch:{ all -> 0x0023 }
            if (r0 != r1) goto L_0x0017
        L_0x0013:
            io.grpc.internal.KeepAliveManager$State r0 = io.grpc.internal.KeepAliveManager.State.IDLE     // Catch:{ all -> 0x0023 }
            r2.state = r0     // Catch:{ all -> 0x0023 }
        L_0x0017:
            io.grpc.internal.KeepAliveManager$State r0 = r2.state     // Catch:{ all -> 0x0023 }
            io.grpc.internal.KeepAliveManager$State r1 = io.grpc.internal.KeepAliveManager.State.PING_SENT     // Catch:{ all -> 0x0023 }
            if (r0 != r1) goto L_0x0021
            io.grpc.internal.KeepAliveManager$State r0 = io.grpc.internal.KeepAliveManager.State.IDLE_AND_PING_SENT     // Catch:{ all -> 0x0023 }
            r2.state = r0     // Catch:{ all -> 0x0023 }
        L_0x0021:
            monitor-exit(r2)
            return
        L_0x0023:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.KeepAliveManager.onTransportIdle():void");
    }

    public synchronized void onTransportTermination() {
        if (this.state != State.DISCONNECTED) {
            this.state = State.DISCONNECTED;
            if (this.shutdownFuture != null) {
                this.shutdownFuture.cancel(false);
            }
            if (this.pingFuture != null) {
                this.pingFuture.cancel(false);
                this.pingFuture = null;
            }
        }
    }
}
