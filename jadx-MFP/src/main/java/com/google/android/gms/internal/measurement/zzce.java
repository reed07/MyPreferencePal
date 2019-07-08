package com.google.android.gms.internal.measurement;

import android.os.Build.VERSION;

public final class zzce {
    public static int version() {
        try {
            return Integer.parseInt(VERSION.SDK);
        } catch (NumberFormatException unused) {
            zzco.zzf("Invalid version number", VERSION.SDK);
            return 0;
        }
    }
}
