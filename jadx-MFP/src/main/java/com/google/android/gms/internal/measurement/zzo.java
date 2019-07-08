package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzo extends zzyc<zzo> {
    public zzn[] zzqf;
    public zzl zzqg;
    public String zzqh;

    public zzo() {
        this.zzqf = zzn.zzj();
        this.zzqg = null;
        this.zzqh = "";
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzo)) {
            return false;
        }
        zzo zzo = (zzo) obj;
        if (!zzyg.equals((Object[]) this.zzqf, (Object[]) zzo.zzqf)) {
            return false;
        }
        zzl zzl = this.zzqg;
        if (zzl == null) {
            if (zzo.zzqg != null) {
                return false;
            }
        } else if (!zzl.equals(zzo.zzqg)) {
            return false;
        }
        String str = this.zzqh;
        if (str == null) {
            if (zzo.zzqh != null) {
                return false;
            }
        } else if (!str.equals(zzo.zzqh)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzo.zzcev == null || zzo.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzo.zzcev);
    }

    public final int hashCode() {
        int i;
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + zzyg.hashCode((Object[]) this.zzqf);
        zzl zzl = this.zzqg;
        int i2 = hashCode * 31;
        int i3 = 0;
        if (zzl == null) {
            i = 0;
        } else {
            i = zzl.hashCode();
        }
        int i4 = (i2 + i) * 31;
        String str = this.zzqh;
        int hashCode2 = (i4 + (str == null ? 0 : str.hashCode())) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i3 = this.zzcev.hashCode();
        }
        return hashCode2 + i3;
    }

    public final void zza(zzya zzya) throws IOException {
        zzn[] zznArr = this.zzqf;
        if (zznArr != null && zznArr.length > 0) {
            int i = 0;
            while (true) {
                zzn[] zznArr2 = this.zzqf;
                if (i >= zznArr2.length) {
                    break;
                }
                zzn zzn = zznArr2[i];
                if (zzn != null) {
                    zzya.zza(1, (zzyi) zzn);
                }
                i++;
            }
        }
        zzl zzl = this.zzqg;
        if (zzl != null) {
            zzya.zza(2, (zzyi) zzl);
        }
        String str = this.zzqh;
        if (str != null && !str.equals("")) {
            zzya.zzb(3, this.zzqh);
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        zzn[] zznArr = this.zzqf;
        if (zznArr != null && zznArr.length > 0) {
            int i = 0;
            while (true) {
                zzn[] zznArr2 = this.zzqf;
                if (i >= zznArr2.length) {
                    break;
                }
                zzn zzn = zznArr2[i];
                if (zzn != null) {
                    zzf += zzya.zzb(1, (zzyi) zzn);
                }
                i++;
            }
        }
        zzl zzl = this.zzqg;
        if (zzl != null) {
            zzf += zzya.zzb(2, (zzyi) zzl);
        }
        String str = this.zzqh;
        return (str == null || str.equals("")) ? zzf : zzf + zzya.zzc(3, this.zzqh);
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 10) {
                int zzb = zzyl.zzb(zzxz, 10);
                zzn[] zznArr = this.zzqf;
                int length = zznArr == null ? 0 : zznArr.length;
                zzn[] zznArr2 = new zzn[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzqf, 0, zznArr2, 0, length);
                }
                while (length < zznArr2.length - 1) {
                    zznArr2[length] = new zzn();
                    zzxz.zza((zzyi) zznArr2[length]);
                    zzxz.zzuj();
                    length++;
                }
                zznArr2[length] = new zzn();
                zzxz.zza((zzyi) zznArr2[length]);
                this.zzqf = zznArr2;
            } else if (zzuj == 18) {
                if (this.zzqg == null) {
                    this.zzqg = new zzl();
                }
                zzxz.zza((zzyi) this.zzqg);
            } else if (zzuj == 26) {
                this.zzqh = zzxz.readString();
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
