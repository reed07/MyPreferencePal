package com.google.android.gms.internal.icing;

public enum zzgl {
    DOUBLE(zzgq.DOUBLE, 1),
    FLOAT(zzgq.FLOAT, 5),
    INT64(zzgq.LONG, 0),
    UINT64(zzgq.LONG, 0),
    INT32(zzgq.INT, 0),
    FIXED64(zzgq.LONG, 1),
    FIXED32(zzgq.INT, 5),
    BOOL(zzgq.BOOLEAN, 0),
    STRING(zzgq.STRING, 2),
    GROUP(zzgq.MESSAGE, 3),
    MESSAGE(zzgq.MESSAGE, 2),
    BYTES(zzgq.BYTE_STRING, 2),
    UINT32(zzgq.INT, 0),
    ENUM(zzgq.ENUM, 0),
    SFIXED32(zzgq.INT, 5),
    SFIXED64(zzgq.LONG, 1),
    SINT32(zzgq.INT, 0),
    SINT64(zzgq.LONG, 0);
    
    private final zzgq zzpo;
    private final int zzpp;

    private zzgl(zzgq zzgq, int i) {
        this.zzpo = zzgq;
        this.zzpp = i;
    }

    public final zzgq zzdx() {
        return this.zzpo;
    }

    public final int zzdy() {
        return this.zzpp;
    }
}
