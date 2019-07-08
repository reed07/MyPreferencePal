package com.google.android.gms.internal.icing;

final class zzez {
    private static final zzex zzmw = zzcx();
    private static final zzex zzmx = new zzey();

    static zzex zzcv() {
        return zzmw;
    }

    static zzex zzcw() {
        return zzmx;
    }

    private static zzex zzcx() {
        try {
            return (zzex) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
