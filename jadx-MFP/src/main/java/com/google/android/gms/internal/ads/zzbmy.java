package com.google.android.gms.internal.ads;

public enum zzbmy implements zzbrg {
    UNKNOWN_STATUS(0),
    ENABLED(1),
    DISABLED(2),
    DESTROYED(3),
    UNRECOGNIZED(-1);
    
    private static final zzbrh<zzbmy> zzcbx = null;
    private final int value;

    public final int zzom() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    public static zzbmy zzdw(int i) {
        switch (i) {
            case 0:
                return UNKNOWN_STATUS;
            case 1:
                return ENABLED;
            case 2:
                return DISABLED;
            case 3:
                return DESTROYED;
            default:
                return null;
        }
    }

    private zzbmy(int i) {
        this.value = i;
    }

    static {
        zzcbx = new zzbmz();
    }
}
