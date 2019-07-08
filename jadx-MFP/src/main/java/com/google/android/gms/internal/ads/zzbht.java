package com.google.android.gms.internal.ads;

@zzark
public final class zzbht {
    public final int heightPixels;
    private final int type;
    public final int widthPixels;

    public static zzbht zzb(zzwf zzwf) {
        if (zzwf.zzckl) {
            return new zzbht(3, 0, 0);
        }
        if (zzwf.zzcko) {
            return new zzbht(2, 0, 0);
        }
        if (zzwf.zzckn) {
            return zzaey();
        }
        return zzr(zzwf.widthPixels, zzwf.heightPixels);
    }

    public static zzbht zzaey() {
        return new zzbht(0, 0, 0);
    }

    public static zzbht zzr(int i, int i2) {
        return new zzbht(1, i, i2);
    }

    public static zzbht zzaez() {
        return new zzbht(4, 0, 0);
    }

    public static zzbht zzafa() {
        return new zzbht(5, 0, 0);
    }

    private zzbht(int i, int i2, int i3) {
        this.type = i;
        this.widthPixels = i2;
        this.heightPixels = i3;
    }

    public final boolean isFluid() {
        return this.type == 2;
    }

    public final boolean zzafb() {
        return this.type == 3;
    }

    public final boolean zzafc() {
        return this.type == 0;
    }

    public final boolean zzafd() {
        return this.type == 4;
    }

    public final boolean zzafe() {
        return this.type == 5;
    }
}
