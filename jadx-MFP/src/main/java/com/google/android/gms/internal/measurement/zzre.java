package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzre extends zzyc<zzre> {
    public long zzbqe;
    public zzo zzbqf;
    public zzl zzqg;

    public zzre() {
        this.zzbqe = 0;
        this.zzqg = null;
        this.zzbqf = null;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzre)) {
            return false;
        }
        zzre zzre = (zzre) obj;
        if (this.zzbqe != zzre.zzbqe) {
            return false;
        }
        zzl zzl = this.zzqg;
        if (zzl == null) {
            if (zzre.zzqg != null) {
                return false;
            }
        } else if (!zzl.equals(zzre.zzqg)) {
            return false;
        }
        zzo zzo = this.zzbqf;
        if (zzo == null) {
            if (zzre.zzbqf != null) {
                return false;
            }
        } else if (!zzo.equals(zzre.zzbqf)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzre.zzcev == null || zzre.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzre.zzcev);
    }

    public final int hashCode() {
        int i;
        int i2;
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        long j = this.zzbqe;
        int i3 = hashCode + ((int) (j ^ (j >>> 32)));
        zzl zzl = this.zzqg;
        int i4 = i3 * 31;
        int i5 = 0;
        if (zzl == null) {
            i = 0;
        } else {
            i = zzl.hashCode();
        }
        int i6 = i4 + i;
        zzo zzo = this.zzbqf;
        int i7 = i6 * 31;
        if (zzo == null) {
            i2 = 0;
        } else {
            i2 = zzo.hashCode();
        }
        int i8 = (i7 + i2) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i5 = this.zzcev.hashCode();
        }
        return i8 + i5;
    }

    public final void zza(zzya zzya) throws IOException {
        zzya.zzi(1, this.zzbqe);
        zzl zzl = this.zzqg;
        if (zzl != null) {
            zzya.zza(2, (zzyi) zzl);
        }
        zzo zzo = this.zzbqf;
        if (zzo != null) {
            zzya.zza(3, (zzyi) zzo);
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf() + zzya.zzd(1, this.zzbqe);
        zzl zzl = this.zzqg;
        if (zzl != null) {
            zzf += zzya.zzb(2, (zzyi) zzl);
        }
        zzo zzo = this.zzbqf;
        return zzo != null ? zzf + zzya.zzb(3, (zzyi) zzo) : zzf;
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 8) {
                this.zzbqe = zzxz.zzvc();
            } else if (zzuj == 18) {
                if (this.zzqg == null) {
                    this.zzqg = new zzl();
                }
                zzxz.zza((zzyi) this.zzqg);
            } else if (zzuj == 26) {
                if (this.zzbqf == null) {
                    this.zzbqf = new zzo();
                }
                zzxz.zza((zzyi) this.zzbqf);
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
