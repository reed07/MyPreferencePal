package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

final class zzeu implements Runnable {
    private final /* synthetic */ ComponentName val$name;
    private final /* synthetic */ zzes zzasu;

    zzeu(zzes zzes, ComponentName componentName) {
        this.zzasu = zzes;
        this.val$name = componentName;
    }

    public final void run() {
        this.zzasu.zzasl.onServiceDisconnected(this.val$name);
    }
}
