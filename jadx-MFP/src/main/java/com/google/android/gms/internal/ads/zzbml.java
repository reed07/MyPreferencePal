package com.google.android.gms.internal.ads;

public enum zzbml implements zzbrg {
    UNKNOWN_CURVE(0),
    NIST_P256(2),
    NIST_P384(3),
    NIST_P521(4),
    UNRECOGNIZED(-1);
    
    private static final zzbrh<zzbml> zzcbx = null;
    private final int value;

    public final int zzom() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    public static zzbml zzds(int i) {
        if (i == 0) {
            return UNKNOWN_CURVE;
        }
        switch (i) {
            case 2:
                return NIST_P256;
            case 3:
                return NIST_P384;
            case 4:
                return NIST_P521;
            default:
                return null;
        }
    }

    private zzbml(int i) {
        this.value = i;
    }

    static {
        zzcbx = new zzbmm();
    }
}
