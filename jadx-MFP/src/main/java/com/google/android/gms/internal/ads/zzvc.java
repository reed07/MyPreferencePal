package com.google.android.gms.internal.ads;

public enum zzvc implements zzbrg {
    ENUM_FALSE(0),
    ENUM_TRUE(1),
    ENUM_UNKNOWN(1000);
    
    private static final zzbrh<zzvc> zzcbx = null;
    private final int value;

    public final int zzom() {
        return this.value;
    }

    public static zzvc zzcd(int i) {
        if (i == 1000) {
            return ENUM_UNKNOWN;
        }
        switch (i) {
            case 0:
                return ENUM_FALSE;
            case 1:
                return ENUM_TRUE;
            default:
                return null;
        }
    }

    public static zzbri zzop() {
        return zzve.zzccw;
    }

    private zzvc(int i) {
        this.value = i;
    }

    static {
        zzcbx = new zzvd();
    }
}
