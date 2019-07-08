package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzh extends zzyc<zzh> {
    private static volatile zzh[] zzod;
    private int name;
    public int[] zzoe;
    private int zzof;
    private boolean zzog;
    private boolean zzoh;

    public static zzh[] zze() {
        if (zzod == null) {
            synchronized (zzyg.zzcfe) {
                if (zzod == null) {
                    zzod = new zzh[0];
                }
            }
        }
        return zzod;
    }

    public zzh() {
        this.zzoe = zzyl.zzcaq;
        this.zzof = 0;
        this.name = 0;
        this.zzog = false;
        this.zzoh = false;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzh)) {
            return false;
        }
        zzh zzh = (zzh) obj;
        if (!zzyg.equals(this.zzoe, zzh.zzoe) || this.zzof != zzh.zzof || this.name != zzh.name || this.zzog != zzh.zzog || this.zzoh != zzh.zzoh) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzh.zzcev == null || zzh.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzh.zzcev);
    }

    public final int hashCode() {
        int i = 1231;
        int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + zzyg.hashCode(this.zzoe)) * 31) + this.zzof) * 31) + this.name) * 31) + (this.zzog ? 1231 : 1237)) * 31;
        if (!this.zzoh) {
            i = 1237;
        }
        return ((hashCode + i) * 31) + ((this.zzcev == null || this.zzcev.isEmpty()) ? 0 : this.zzcev.hashCode());
    }

    public final void zza(zzya zzya) throws IOException {
        boolean z = this.zzoh;
        if (z) {
            zzya.zzb(1, z);
        }
        zzya.zzd(2, this.zzof);
        int[] iArr = this.zzoe;
        if (iArr != null && iArr.length > 0) {
            int i = 0;
            while (true) {
                int[] iArr2 = this.zzoe;
                if (i >= iArr2.length) {
                    break;
                }
                zzya.zzd(3, iArr2[i]);
                i++;
            }
        }
        int i2 = this.name;
        if (i2 != 0) {
            zzya.zzd(4, i2);
        }
        boolean z2 = this.zzog;
        if (z2) {
            zzya.zzb(6, z2);
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int[] iArr;
        int zzf = super.zzf();
        if (this.zzoh) {
            zzf += zzya.zzbd(1) + 1;
        }
        int zzh = zzf + zzya.zzh(2, this.zzof);
        int[] iArr2 = this.zzoe;
        if (iArr2 != null && iArr2.length > 0) {
            int i = 0;
            int i2 = 0;
            while (true) {
                iArr = this.zzoe;
                if (i >= iArr.length) {
                    break;
                }
                i2 += zzya.zzbe(iArr[i]);
                i++;
            }
            zzh = zzh + i2 + (iArr.length * 1);
        }
        int i3 = this.name;
        if (i3 != 0) {
            zzh += zzya.zzh(4, i3);
        }
        return this.zzog ? zzh + zzya.zzbd(6) + 1 : zzh;
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 8) {
                this.zzoh = zzxz.zzup();
            } else if (zzuj == 16) {
                this.zzof = zzxz.zzvb();
            } else if (zzuj == 24) {
                int zzb = zzyl.zzb(zzxz, 24);
                int[] iArr = this.zzoe;
                int length = iArr == null ? 0 : iArr.length;
                int[] iArr2 = new int[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzoe, 0, iArr2, 0, length);
                }
                while (length < iArr2.length - 1) {
                    iArr2[length] = zzxz.zzvb();
                    zzxz.zzuj();
                    length++;
                }
                iArr2[length] = zzxz.zzvb();
                this.zzoe = iArr2;
            } else if (zzuj == 26) {
                int zzas = zzxz.zzas(zzxz.zzvb());
                int position = zzxz.getPosition();
                int i = 0;
                while (zzxz.zzyy() > 0) {
                    zzxz.zzvb();
                    i++;
                }
                zzxz.zzcb(position);
                int[] iArr3 = this.zzoe;
                int length2 = iArr3 == null ? 0 : iArr3.length;
                int[] iArr4 = new int[(i + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zzoe, 0, iArr4, 0, length2);
                }
                while (length2 < iArr4.length) {
                    iArr4[length2] = zzxz.zzvb();
                    length2++;
                }
                this.zzoe = iArr4;
                zzxz.zzat(zzas);
            } else if (zzuj == 32) {
                this.name = zzxz.zzvb();
            } else if (zzuj == 48) {
                this.zzog = zzxz.zzup();
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
