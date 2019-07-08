package com.google.android.gms.internal.icing;

import android.database.ContentObserver;
import android.os.Handler;

final class zzbd extends ContentObserver {
    private final /* synthetic */ zzbb zzda;

    zzbd(zzbb zzbb, Handler handler) {
        this.zzda = zzbb;
        super(null);
    }

    public final void onChange(boolean z) {
        this.zzda.zzs();
    }
}
