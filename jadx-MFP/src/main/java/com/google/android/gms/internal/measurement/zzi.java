package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzi extends zzyc<zzi> {
    private static volatile zzi[] zzoi;
    public String zzoj;
    public long zzok;
    public long zzol;
    public boolean zzom;
    public long zzon;

    public static zzi[] zzg() {
        if (zzoi == null) {
            synchronized (zzyg.zzcfe) {
                if (zzoi == null) {
                    zzoi = new zzi[0];
                }
            }
        }
        return zzoi;
    }

    public zzi() {
        this.zzoj = "";
        this.zzok = 0;
        this.zzol = 2147483647L;
        this.zzom = false;
        this.zzon = 0;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzi)) {
            return false;
        }
        zzi zzi = (zzi) obj;
        String str = this.zzoj;
        if (str == null) {
            if (zzi.zzoj != null) {
                return false;
            }
        } else if (!str.equals(zzi.zzoj)) {
            return false;
        }
        if (this.zzok != zzi.zzok || this.zzol != zzi.zzol || this.zzom != zzi.zzom || this.zzon != zzi.zzon) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzi.zzcev == null || zzi.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzi.zzcev);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.zzoj;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        long j = this.zzok;
        int i2 = (hashCode2 + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.zzol;
        int i3 = (((i2 + ((int) (j2 ^ (j2 >>> 32)))) * 31) + (this.zzom ? 1231 : 1237)) * 31;
        long j3 = this.zzon;
        int i4 = (i3 + ((int) (j3 ^ (j3 >>> 32)))) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i = this.zzcev.hashCode();
        }
        return i4 + i;
    }

    public final void zza(zzya zzya) throws IOException {
        String str = this.zzoj;
        if (str != null && !str.equals("")) {
            zzya.zzb(1, this.zzoj);
        }
        long j = this.zzok;
        if (j != 0) {
            zzya.zzi(2, j);
        }
        long j2 = this.zzol;
        if (j2 != 2147483647L) {
            zzya.zzi(3, j2);
        }
        boolean z = this.zzom;
        if (z) {
            zzya.zzb(4, z);
        }
        long j3 = this.zzon;
        if (j3 != 0) {
            zzya.zzi(5, j3);
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        String str = this.zzoj;
        if (str != null && !str.equals("")) {
            zzf += zzya.zzc(1, this.zzoj);
        }
        long j = this.zzok;
        if (j != 0) {
            zzf += zzya.zzd(2, j);
        }
        long j2 = this.zzol;
        if (j2 != 2147483647L) {
            zzf += zzya.zzd(3, j2);
        }
        if (this.zzom) {
            zzf += zzya.zzbd(4) + 1;
        }
        long j3 = this.zzon;
        return j3 != 0 ? zzf + zzya.zzd(5, j3) : zzf;
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 10) {
                this.zzoj = zzxz.readString();
            } else if (zzuj == 16) {
                this.zzok = zzxz.zzvc();
            } else if (zzuj == 24) {
                this.zzol = zzxz.zzvc();
            } else if (zzuj == 32) {
                this.zzom = zzxz.zzup();
            } else if (zzuj == 40) {
                this.zzon = zzxz.zzvc();
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
