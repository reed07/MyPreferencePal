package io.grpc;

import com.google.common.base.Preconditions;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ExperimentalApi
@ThreadSafe
public final class SynchronizationContext implements Executor {
    @GuardedBy("lock")
    private Thread drainingThread;
    private final Object lock = new Object();
    @GuardedBy("lock")
    private final Queue<Runnable> queue = new ArrayDeque();
    private final UncaughtExceptionHandler uncaughtExceptionHandler;

    public static final class ScheduledHandle {
    }

    public SynchronizationContext(UncaughtExceptionHandler uncaughtExceptionHandler2) {
        this.uncaughtExceptionHandler = (UncaughtExceptionHandler) Preconditions.checkNotNull(uncaughtExceptionHandler2, "uncaughtExceptionHandler");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r2.run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0027, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0028, code lost:
        r4.uncaughtExceptionHandler.uncaughtException(java.lang.Thread.currentThread(), r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void drain() {
        /*
            r4 = this;
            r0 = 0
        L_0x0001:
            java.lang.Object r1 = r4.lock
            monitor-enter(r1)
            if (r0 != 0) goto L_0x0013
            java.lang.Thread r0 = r4.drainingThread     // Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x000c
            monitor-exit(r1)     // Catch:{ all -> 0x0032 }
            return
        L_0x000c:
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0032 }
            r4.drainingThread = r0     // Catch:{ all -> 0x0032 }
            r0 = 1
        L_0x0013:
            java.util.Queue<java.lang.Runnable> r2 = r4.queue     // Catch:{ all -> 0x0032 }
            java.lang.Object r2 = r2.poll()     // Catch:{ all -> 0x0032 }
            java.lang.Runnable r2 = (java.lang.Runnable) r2     // Catch:{ all -> 0x0032 }
            if (r2 != 0) goto L_0x0022
            r0 = 0
            r4.drainingThread = r0     // Catch:{ all -> 0x0032 }
            monitor-exit(r1)     // Catch:{ all -> 0x0032 }
            return
        L_0x0022:
            monitor-exit(r1)     // Catch:{ all -> 0x0032 }
            r2.run()     // Catch:{ Throwable -> 0x0027 }
            goto L_0x0001
        L_0x0027:
            r1 = move-exception
            java.lang.Thread$UncaughtExceptionHandler r2 = r4.uncaughtExceptionHandler
            java.lang.Thread r3 = java.lang.Thread.currentThread()
            r2.uncaughtException(r3, r1)
            goto L_0x0001
        L_0x0032:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0032 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.SynchronizationContext.drain():void");
    }

    public final void executeLater(Runnable runnable) {
        synchronized (this.lock) {
            this.queue.add(Preconditions.checkNotNull(runnable, "runnable is null"));
        }
    }

    public final void execute(Runnable runnable) {
        executeLater(runnable);
        drain();
    }

    public void throwIfNotInThisSynchronizationContext() {
        synchronized (this.lock) {
            Preconditions.checkState(Thread.currentThread() == this.drainingThread, "Not called from the SynchronizationContext");
        }
    }
}
