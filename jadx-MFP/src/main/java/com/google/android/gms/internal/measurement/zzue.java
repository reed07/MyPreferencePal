package com.google.android.gms.internal.measurement;

final class zzue {
    private static final zzuc<?> zzbvh = new zzud();
    private static final zzuc<?> zzbvi = zzvt();

    private static zzuc<?> zzvt() {
        try {
            return (zzuc) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static zzuc<?> zzvu() {
        return zzbvh;
    }

    static zzuc<?> zzvv() {
        zzuc<?> zzuc = zzbvi;
        if (zzuc != null) {
            return zzuc;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
