package com.google.android.gms.internal.icing;

final class zzcb {
    private static final Class<?> zzft = zzs("libcore.io.Memory");
    private static final boolean zzfu = (zzs("org.robolectric.Robolectric") != null);

    static boolean zzal() {
        return zzft != null && !zzfu;
    }

    static Class<?> zzam() {
        return zzft;
    }

    private static <T> Class<T> zzs(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
