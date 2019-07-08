package com.google.android.gms.internal.ads;

public abstract class zzgc {
    public static final zzgc zzaal = new zzgd();

    public abstract zzge zza(int i, zzge zzge, boolean z);

    public abstract zzgf zza(int i, zzgf zzgf, boolean z, long j);

    public abstract int zzc(Object obj);

    public abstract int zzck();

    public abstract int zzcl();

    public final boolean isEmpty() {
        return zzck() == 0;
    }

    private final zzgf zza(int i, zzgf zzgf) {
        return zza(i, zzgf, false, 0);
    }

    public final int zza(int i, zzge zzge, zzgf zzgf, int i2) {
        zza(i, zzge, false);
        int i3 = 1;
        if (zza(0, zzgf).zzaav != i) {
            return i + 1;
        }
        switch (i2) {
            case 0:
                if (zzck() - 1 == 0) {
                    i3 = -1;
                    break;
                }
                break;
            case 1:
                i3 = 0;
                break;
            case 2:
                if (zzck() - 1 == 0) {
                    i3 = 0;
                    break;
                }
                break;
            default:
                throw new IllegalStateException();
        }
        if (i3 == -1) {
            return -1;
        }
        zza(i3, zzgf);
        return 0;
    }
}
