package com.google.android.gms.internal.icing;

final class zzen {
    private static final zzel zzmb = zzcq();
    private static final zzel zzmc = new zzem();

    static zzel zzco() {
        return zzmb;
    }

    static zzel zzcp() {
        return zzmc;
    }

    private static zzel zzcq() {
        try {
            return (zzel) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
