package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class zzrv extends ContentObserver {
    zzrv(Handler handler) {
        super(null);
    }

    public final void onChange(boolean z) {
        zzru.zzbqs.set(true);
    }
}
