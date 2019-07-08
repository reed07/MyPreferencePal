package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.GoogleApiAvailabilityLight;

public final class zzav {
    public static final String VERSION = String.valueOf(GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000).replaceAll("(\\d+)(\\d)(\\d\\d)", "$1.$2.$3");
    public static final String zzwa;

    static {
        String str = "ma";
        String valueOf = String.valueOf(VERSION);
        zzwa = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }
}
