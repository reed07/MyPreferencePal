package com.google.android.gms.internal.ads;

final class zzbqt {
    private static final zzbqr<?> zzfmv = new zzbqs();
    private static final zzbqr<?> zzfmw = zzamf();

    private static zzbqr<?> zzamf() {
        try {
            return (zzbqr) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static zzbqr<?> zzamg() {
        return zzfmv;
    }

    static zzbqr<?> zzamh() {
        zzbqr<?> zzbqr = zzfmw;
        if (zzbqr != null) {
            return zzbqr;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
