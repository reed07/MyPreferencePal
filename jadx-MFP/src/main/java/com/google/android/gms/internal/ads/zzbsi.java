package com.google.android.gms.internal.ads;

final class zzbsi {
    private static final zzbsg zzfse = zzany();
    private static final zzbsg zzfsf = new zzbsh();

    static zzbsg zzanw() {
        return zzfse;
    }

    static zzbsg zzanx() {
        return zzfsf;
    }

    private static zzbsg zzany() {
        try {
            return (zzbsg) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
