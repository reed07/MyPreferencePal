package com.google.android.gms.internal.measurement;

import android.content.ComponentName;

final class zzbe implements Runnable {
    private final /* synthetic */ ComponentName val$name;
    private final /* synthetic */ zzbc zzxe;

    zzbe(zzbc zzbc, ComponentName componentName) {
        this.zzxe = zzbc;
        this.val$name = componentName;
    }

    public final void run() {
        this.zzxe.zzxa.onServiceDisconnected(this.val$name);
    }
}
