package com.google.android.gms.internal.icing;

final class zzdb {
    private static final zzcz<?> zzgw = new zzda();
    private static final zzcz<?> zzgx = zzbe();

    private static zzcz<?> zzbe() {
        try {
            return (zzcz) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static zzcz<?> zzbf() {
        return zzgw;
    }

    static zzcz<?> zzbg() {
        zzcz<?> zzcz = zzgx;
        if (zzcz != null) {
            return zzcz;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
