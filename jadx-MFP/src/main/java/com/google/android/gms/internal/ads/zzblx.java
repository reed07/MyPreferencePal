package com.google.android.gms.internal.ads;

public enum zzblx implements zzbrg {
    UNKNOWN_FORMAT(0),
    UNCOMPRESSED(1),
    COMPRESSED(2),
    DO_NOT_USE_CRUNCHY_UNCOMPRESSED(3),
    UNRECOGNIZED(-1);
    
    private static final zzbrh<zzblx> zzcbx = null;
    private final int value;

    public final int zzom() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    public static zzblx zzdp(int i) {
        switch (i) {
            case 0:
                return UNKNOWN_FORMAT;
            case 1:
                return UNCOMPRESSED;
            case 2:
                return COMPRESSED;
            case 3:
                return DO_NOT_USE_CRUNCHY_UNCOMPRESSED;
            default:
                return null;
        }
    }

    private zzblx(int i) {
        this.value = i;
    }

    static {
        zzcbx = new zzbly();
    }
}
