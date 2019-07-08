package com.google.android.gms.internal.measurement;

final class zztb {
    private static final Class<?> zzbto = zzfz("libcore.io.Memory");
    private static final boolean zzbtp = (zzfz("org.robolectric.Robolectric") != null);

    static boolean zzub() {
        return zzbto != null && !zzbtp;
    }

    static Class<?> zzuc() {
        return zzbto;
    }

    private static <T> Class<T> zzfz(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
