package com.google.android.gms.internal.ads;

import android.database.ContentObserver;
import android.os.Handler;

final class zzrj extends ContentObserver {
    private final /* synthetic */ zzrg zzbvl;

    public zzrj(zzrg zzrg, Handler handler) {
        this.zzbvl = zzrg;
        super(handler);
    }

    public final void onChange(boolean z) {
        super.onChange(z);
        this.zzbvl.zzmw();
    }
}
