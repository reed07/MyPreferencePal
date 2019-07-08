package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class zzyi {
    protected volatile int zzcff = -1;

    public abstract zzyi zza(zzxz zzxz) throws IOException;

    public void zza(zzya zzya) throws IOException {
    }

    /* access modifiers changed from: protected */
    public int zzf() {
        return 0;
    }

    public final int zzzh() {
        if (this.zzcff < 0) {
            zzvx();
        }
        return this.zzcff;
    }

    public final int zzvx() {
        int zzf = zzf();
        this.zzcff = zzf;
        return zzf;
    }

    public static final void zza(zzyi zzyi, byte[] bArr, int i, int i2) {
        try {
            zzya zzk = zzya.zzk(bArr, 0, i2);
            zzyi.zza(zzk);
            zzk.zzza();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public static final <T extends zzyi> T zza(T t, byte[] bArr) throws zzyh {
        return zzb(t, bArr, 0, bArr.length);
    }

    private static final <T extends zzyi> T zzb(T t, byte[] bArr, int i, int i2) throws zzyh {
        try {
            zzxz zzj = zzxz.zzj(bArr, 0, i2);
            t.zza(zzj);
            zzj.zzap(0);
            return t;
        } catch (zzyh e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
        }
    }

    public String toString() {
        return zzyj.zzc(this);
    }

    /* renamed from: zzzb */
    public zzyi clone() throws CloneNotSupportedException {
        return (zzyi) super.clone();
    }
}
