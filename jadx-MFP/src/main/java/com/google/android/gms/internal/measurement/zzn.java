package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzn extends zzyc<zzn> {
    private static volatile zzn[] zzqc;
    public String name;
    private zzp zzqd;
    public zzj zzqe;

    public static zzn[] zzj() {
        if (zzqc == null) {
            synchronized (zzyg.zzcfe) {
                if (zzqc == null) {
                    zzqc = new zzn[0];
                }
            }
        }
        return zzqc;
    }

    public zzn() {
        this.name = "";
        this.zzqd = null;
        this.zzqe = null;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzn)) {
            return false;
        }
        zzn zzn = (zzn) obj;
        String str = this.name;
        if (str == null) {
            if (zzn.name != null) {
                return false;
            }
        } else if (!str.equals(zzn.name)) {
            return false;
        }
        zzp zzp = this.zzqd;
        if (zzp == null) {
            if (zzn.zzqd != null) {
                return false;
            }
        } else if (!zzp.equals(zzn.zzqd)) {
            return false;
        }
        zzj zzj = this.zzqe;
        if (zzj == null) {
            if (zzn.zzqe != null) {
                return false;
            }
        } else if (!zzj.equals(zzn.zzqe)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzn.zzcev == null || zzn.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzn.zzcev);
    }

    public final int hashCode() {
        int i;
        int i2;
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.name;
        int i3 = 0;
        int hashCode2 = hashCode + (str == null ? 0 : str.hashCode());
        zzp zzp = this.zzqd;
        int i4 = hashCode2 * 31;
        if (zzp == null) {
            i = 0;
        } else {
            i = zzp.hashCode();
        }
        int i5 = i4 + i;
        zzj zzj = this.zzqe;
        int i6 = i5 * 31;
        if (zzj == null) {
            i2 = 0;
        } else {
            i2 = zzj.hashCode();
        }
        int i7 = (i6 + i2) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i3 = this.zzcev.hashCode();
        }
        return i7 + i3;
    }

    public final void zza(zzya zzya) throws IOException {
        String str = this.name;
        if (str != null && !str.equals("")) {
            zzya.zzb(1, this.name);
        }
        zzp zzp = this.zzqd;
        if (zzp != null) {
            zzya.zza(2, (zzyi) zzp);
        }
        zzj zzj = this.zzqe;
        if (zzj != null) {
            zzya.zza(3, (zzyi) zzj);
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        String str = this.name;
        if (str != null && !str.equals("")) {
            zzf += zzya.zzc(1, this.name);
        }
        zzp zzp = this.zzqd;
        if (zzp != null) {
            zzf += zzya.zzb(2, (zzyi) zzp);
        }
        zzj zzj = this.zzqe;
        return zzj != null ? zzf + zzya.zzb(3, (zzyi) zzj) : zzf;
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 10) {
                this.name = zzxz.readString();
            } else if (zzuj == 18) {
                if (this.zzqd == null) {
                    this.zzqd = new zzp();
                }
                zzxz.zza((zzyi) this.zzqd);
            } else if (zzuj == 26) {
                if (this.zzqe == null) {
                    this.zzqe = new zzj();
                }
                zzxz.zza((zzyi) this.zzqe);
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
