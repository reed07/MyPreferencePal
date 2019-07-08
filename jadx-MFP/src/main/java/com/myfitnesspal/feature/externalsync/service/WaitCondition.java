package com.myfitnesspal.feature.externalsync.service;

public class WaitCondition {
    private static final int WAIT_TIMEOUT = 5000;
    private final Object condition = new Object();
    private volatile boolean finished = false;

    public void reset() {
        synchronized (this.condition) {
            this.finished = false;
            this.condition.notifyAll();
        }
    }

    public void complete() {
        synchronized (this.condition) {
            this.finished = true;
            this.condition.notifyAll();
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0003 */
    /* JADX WARNING: Removed duplicated region for block: B:2:0x0003 A[LOOP:0: B:2:0x0003->B:15:0x0003, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean await() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.condition
            monitor-enter(r0)
        L_0x0003:
            boolean r1 = r4.finished     // Catch:{ all -> 0x0013 }
            if (r1 != 0) goto L_0x000f
            java.lang.Object r1 = r4.condition     // Catch:{ InterruptedException -> 0x0003 }
            r2 = 5000(0x1388, double:2.4703E-320)
            r1.wait(r2)     // Catch:{ InterruptedException -> 0x0003 }
            goto L_0x0003
        L_0x000f:
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            boolean r0 = r4.finished
            return r0
        L_0x0013:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.externalsync.service.WaitCondition.await():boolean");
    }
}
