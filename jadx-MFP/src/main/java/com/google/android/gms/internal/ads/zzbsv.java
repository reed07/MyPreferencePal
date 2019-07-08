package com.google.android.gms.internal.ads;

final class zzbsv {
    private static final zzbst zzfsz = zzaof();
    private static final zzbst zzfta = new zzbsu();

    static zzbst zzaod() {
        return zzfsz;
    }

    static zzbst zzaoe() {
        return zzfta;
    }

    private static zzbst zzaof() {
        try {
            return (zzbst) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
