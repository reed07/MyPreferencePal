package com.google.android.gms.internal.ads;

final class zzbpp {
    private static final Class<?> zzflb = zzfv("libcore.io.Memory");
    private static final boolean zzflc = (zzfv("org.robolectric.Robolectric") != null);

    static boolean zzakl() {
        return zzflb != null && !zzflc;
    }

    static Class<?> zzakm() {
        return zzflb;
    }

    private static <T> Class<T> zzfv(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
