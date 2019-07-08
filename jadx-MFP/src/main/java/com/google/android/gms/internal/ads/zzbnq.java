package com.google.android.gms.internal.ads;

public enum zzbnq implements zzbrg {
    UNKNOWN_PREFIX(0),
    TINK(1),
    LEGACY(2),
    RAW(3),
    CRUNCHY(4),
    UNRECOGNIZED(-1);
    
    private static final zzbrh<zzbnq> zzcbx = null;
    private final int value;

    public final int zzom() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    public static zzbnq zzef(int i) {
        switch (i) {
            case 0:
                return UNKNOWN_PREFIX;
            case 1:
                return TINK;
            case 2:
                return LEGACY;
            case 3:
                return RAW;
            case 4:
                return CRUNCHY;
            default:
                return null;
        }
    }

    private zzbnq(int i) {
        this.value = i;
    }

    static {
        zzcbx = new zzbnr();
    }
}
