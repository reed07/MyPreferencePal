package com.google.android.gms.internal.ads;

public final class zzh implements zzab {
    private int zzr;
    private int zzs;
    private final int zzt;
    private final float zzu;

    public zzh() {
        this(2500, 1, 1.0f);
    }

    private zzh(int i, int i2, float f) {
        this.zzr = 2500;
        this.zzt = 1;
        this.zzu = 1.0f;
    }

    public final int zzc() {
        return this.zzr;
    }

    public final int zzd() {
        return this.zzs;
    }

    public final void zza(zzae zzae) throws zzae {
        boolean z = true;
        this.zzs++;
        int i = this.zzr;
        this.zzr = i + ((int) (((float) i) * this.zzu));
        if (this.zzs > this.zzt) {
            z = false;
        }
        if (!z) {
            throw zzae;
        }
    }
}
