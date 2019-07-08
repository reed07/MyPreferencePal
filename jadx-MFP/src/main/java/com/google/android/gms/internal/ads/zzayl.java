package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzayl implements Runnable {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzayh zzell;

    zzayl(zzayh zzayh, Context context) {
        this.zzell = zzayh;
        this.val$context = context;
    }

    public final void run() {
        synchronized (this.zzell.mLock) {
            this.zzell.zzeiz = zzayh.zzak(this.val$context);
            this.zzell.mLock.notifyAll();
        }
    }
}
