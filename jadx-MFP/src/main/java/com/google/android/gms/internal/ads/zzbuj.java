package com.google.android.gms.internal.ads;

public enum zzbuj {
    DOUBLE(zzbuo.DOUBLE, 1),
    FLOAT(zzbuo.FLOAT, 5),
    INT64(zzbuo.LONG, 0),
    UINT64(zzbuo.LONG, 0),
    INT32(zzbuo.INT, 0),
    FIXED64(zzbuo.LONG, 1),
    FIXED32(zzbuo.INT, 5),
    BOOL(zzbuo.BOOLEAN, 0),
    STRING(zzbuo.STRING, 2),
    GROUP(zzbuo.MESSAGE, 3),
    MESSAGE(zzbuo.MESSAGE, 2),
    BYTES(zzbuo.BYTE_STRING, 2),
    UINT32(zzbuo.INT, 0),
    ENUM(zzbuo.ENUM, 0),
    SFIXED32(zzbuo.INT, 5),
    SFIXED64(zzbuo.LONG, 1),
    SINT32(zzbuo.INT, 0),
    SINT64(zzbuo.LONG, 0);
    
    private final zzbuo zzfvr;
    private final int zzfvs;

    private zzbuj(zzbuo zzbuo, int i) {
        this.zzfvr = zzbuo;
        this.zzfvs = i;
    }

    public final zzbuo zzapj() {
        return this.zzfvr;
    }

    public final int zzapk() {
        return this.zzfvs;
    }
}
