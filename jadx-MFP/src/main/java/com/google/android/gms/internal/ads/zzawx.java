package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

final /* synthetic */ class zzawx implements Callable {
    private final Context zzcmy;
    private final zzaww zzehe;

    zzawx(zzaww zzaww, Context context) {
        this.zzehe = zzaww;
        this.zzcmy = context;
    }

    public final Object call() {
        return this.zzehe.zzac(this.zzcmy);
    }
}
