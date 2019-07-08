package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Looper;

public final class zzn {
    private final boolean zzagr = false;

    zzn(Context context) {
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
