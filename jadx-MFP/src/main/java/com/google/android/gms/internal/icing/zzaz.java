package com.google.android.gms.internal.icing;

import android.database.ContentObserver;
import android.os.Handler;

final class zzaz extends ContentObserver {
    zzaz(Handler handler) {
        super(null);
    }

    public final void onChange(boolean z) {
        zzay.zzci.set(true);
    }
}
