package com.google.android.gms.internal.measurement;

final class zzvs {
    private static final zzvq zzcao = zzxl();
    private static final zzvq zzcap = new zzvr();

    static zzvq zzxj() {
        return zzcao;
    }

    static zzvq zzxk() {
        return zzcap;
    }

    private static zzvq zzxl() {
        try {
            return (zzvq) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
