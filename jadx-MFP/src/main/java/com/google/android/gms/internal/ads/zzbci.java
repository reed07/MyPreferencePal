package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

final class zzbci implements Executor {
    zzbci() {
    }

    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
