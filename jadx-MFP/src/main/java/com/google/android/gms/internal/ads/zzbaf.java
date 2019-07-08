package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;

@zzark
public final class zzbaf {
    private Handler mHandler = null;
    private final Object mLock = new Object();
    private HandlerThread zzenj = null;
    private int zzenk = 0;

    public final Looper zzaak() {
        Looper looper;
        synchronized (this.mLock) {
            if (this.zzenk != 0) {
                Preconditions.checkNotNull(this.zzenj, "Invalid state: mHandlerThread should already been initialized.");
            } else if (this.zzenj == null) {
                zzaxz.v("Starting the looper thread.");
                this.zzenj = new HandlerThread("LooperProvider");
                this.zzenj.start();
                this.mHandler = new Handler(this.zzenj.getLooper());
                zzaxz.v("Looper thread started.");
            } else {
                zzaxz.v("Resuming the looper thread");
                this.mLock.notifyAll();
            }
            this.zzenk++;
            looper = this.zzenj.getLooper();
        }
        return looper;
    }

    public final Handler getHandler() {
        return this.mHandler;
    }
}
