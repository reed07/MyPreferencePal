package com.amplitude.api;

import android.os.Handler;
import android.os.HandlerThread;

public class WorkerThread extends HandlerThread {
    private Handler handler;

    public WorkerThread(String str) {
        super(str);
    }

    /* access modifiers changed from: 0000 */
    public void post(Runnable runnable) {
        waitForInitialization();
        this.handler.post(runnable);
    }

    /* access modifiers changed from: 0000 */
    public void postDelayed(Runnable runnable, long j) {
        waitForInitialization();
        this.handler.postDelayed(runnable, j);
    }

    private synchronized void waitForInitialization() {
        if (this.handler == null) {
            this.handler = new Handler(getLooper());
        }
    }
}
