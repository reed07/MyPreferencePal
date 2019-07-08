package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzj extends zzyc<zzj> {
    public zzp[] zzoo;
    public zzp[] zzop;
    public zzi[] zzoq;

    public zzj() {
        this.zzoo = zzp.zzk();
        this.zzop = zzp.zzk();
        this.zzoq = zzi.zzg();
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzj)) {
            return false;
        }
        zzj zzj = (zzj) obj;
        if (!zzyg.equals((Object[]) this.zzoo, (Object[]) zzj.zzoo) || !zzyg.equals((Object[]) this.zzop, (Object[]) zzj.zzop) || !zzyg.equals((Object[]) this.zzoq, (Object[]) zzj.zzoq)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzj.zzcev == null || zzj.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzj.zzcev);
    }

    public final int hashCode() {
        return ((((((((getClass().getName().hashCode() + 527) * 31) + zzyg.hashCode((Object[]) this.zzoo)) * 31) + zzyg.hashCode((Object[]) this.zzop)) * 31) + zzyg.hashCode((Object[]) this.zzoq)) * 31) + ((this.zzcev == null || this.zzcev.isEmpty()) ? 0 : this.zzcev.hashCode());
    }

    public final void zza(zzya zzya) throws IOException {
        zzp[] zzpArr = this.zzoo;
        int i = 0;
        if (zzpArr != null && zzpArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzp[] zzpArr2 = this.zzoo;
                if (i2 >= zzpArr2.length) {
                    break;
                }
                zzp zzp = zzpArr2[i2];
                if (zzp != null) {
                    zzya.zza(1, (zzyi) zzp);
                }
                i2++;
            }
        }
        zzp[] zzpArr3 = this.zzop;
        if (zzpArr3 != null && zzpArr3.length > 0) {
            int i3 = 0;
            while (true) {
                zzp[] zzpArr4 = this.zzop;
                if (i3 >= zzpArr4.length) {
                    break;
                }
                zzp zzp2 = zzpArr4[i3];
                if (zzp2 != null) {
                    zzya.zza(2, (zzyi) zzp2);
                }
                i3++;
            }
        }
        zzi[] zziArr = this.zzoq;
        if (zziArr != null && zziArr.length > 0) {
            while (true) {
                zzi[] zziArr2 = this.zzoq;
                if (i >= zziArr2.length) {
                    break;
                }
                zzi zzi = zziArr2[i];
                if (zzi != null) {
                    zzya.zza(3, (zzyi) zzi);
                }
                i++;
            }
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        zzp[] zzpArr = this.zzoo;
        int i = 0;
        if (zzpArr != null && zzpArr.length > 0) {
            int i2 = zzf;
            int i3 = 0;
            while (true) {
                zzp[] zzpArr2 = this.zzoo;
                if (i3 >= zzpArr2.length) {
                    break;
                }
                zzp zzp = zzpArr2[i3];
                if (zzp != null) {
                    i2 += zzya.zzb(1, (zzyi) zzp);
                }
                i3++;
            }
            zzf = i2;
        }
        zzp[] zzpArr3 = this.zzop;
        if (zzpArr3 != null && zzpArr3.length > 0) {
            int i4 = zzf;
            int i5 = 0;
            while (true) {
                zzp[] zzpArr4 = this.zzop;
                if (i5 >= zzpArr4.length) {
                    break;
                }
                zzp zzp2 = zzpArr4[i5];
                if (zzp2 != null) {
                    i4 += zzya.zzb(2, (zzyi) zzp2);
                }
                i5++;
            }
            zzf = i4;
        }
        zzi[] zziArr = this.zzoq;
        if (zziArr != null && zziArr.length > 0) {
            while (true) {
                zzi[] zziArr2 = this.zzoq;
                if (i >= zziArr2.length) {
                    break;
                }
                zzi zzi = zziArr2[i];
                if (zzi != null) {
                    zzf += zzya.zzb(3, (zzyi) zzi);
                }
                i++;
            }
        }
        return zzf;
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 10) {
                int zzb = zzyl.zzb(zzxz, 10);
                zzp[] zzpArr = this.zzoo;
                int length = zzpArr == null ? 0 : zzpArr.length;
                zzp[] zzpArr2 = new zzp[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzoo, 0, zzpArr2, 0, length);
                }
                while (length < zzpArr2.length - 1) {
                    zzpArr2[length] = new zzp();
                    zzxz.zza((zzyi) zzpArr2[length]);
                    zzxz.zzuj();
                    length++;
                }
                zzpArr2[length] = new zzp();
                zzxz.zza((zzyi) zzpArr2[length]);
                this.zzoo = zzpArr2;
            } else if (zzuj == 18) {
                int zzb2 = zzyl.zzb(zzxz, 18);
                zzp[] zzpArr3 = this.zzop;
                int length2 = zzpArr3 == null ? 0 : zzpArr3.length;
                zzp[] zzpArr4 = new zzp[(zzb2 + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zzop, 0, zzpArr4, 0, length2);
                }
                while (length2 < zzpArr4.length - 1) {
                    zzpArr4[length2] = new zzp();
                    zzxz.zza((zzyi) zzpArr4[length2]);
                    zzxz.zzuj();
                    length2++;
                }
                zzpArr4[length2] = new zzp();
                zzxz.zza((zzyi) zzpArr4[length2]);
                this.zzop = zzpArr4;
            } else if (zzuj == 26) {
                int zzb3 = zzyl.zzb(zzxz, 26);
                zzi[] zziArr = this.zzoq;
                int length3 = zziArr == null ? 0 : zziArr.length;
                zzi[] zziArr2 = new zzi[(zzb3 + length3)];
                if (length3 != 0) {
                    System.arraycopy(this.zzoq, 0, zziArr2, 0, length3);
                }
                while (length3 < zziArr2.length - 1) {
                    zziArr2[length3] = new zzi();
                    zzxz.zza((zzyi) zziArr2[length3]);
                    zzxz.zzuj();
                    length3++;
                }
                zziArr2[length3] = new zzi();
                zzxz.zza((zzyi) zziArr2[length3]);
                this.zzoq = zziArr2;
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
