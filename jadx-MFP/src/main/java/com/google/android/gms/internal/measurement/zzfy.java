package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfy extends zzyc<zzfy> {
    private static volatile zzfy[] zzayt;
    public Integer zzawz;
    public long[] zzayu;

    public static zzfy[] zznc() {
        if (zzayt == null) {
            synchronized (zzyg.zzcfe) {
                if (zzayt == null) {
                    zzayt = new zzfy[0];
                }
            }
        }
        return zzayt;
    }

    public zzfy() {
        this.zzawz = null;
        this.zzayu = zzyl.zzcfk;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfy)) {
            return false;
        }
        zzfy zzfy = (zzfy) obj;
        Integer num = this.zzawz;
        if (num == null) {
            if (zzfy.zzawz != null) {
                return false;
            }
        } else if (!num.equals(zzfy.zzawz)) {
            return false;
        }
        if (!zzyg.equals(this.zzayu, zzfy.zzayu)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzfy.zzcev == null || zzfy.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzfy.zzcev);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzawz;
        int i = 0;
        int hashCode2 = (((hashCode + (num == null ? 0 : num.hashCode())) * 31) + zzyg.hashCode(this.zzayu)) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i = this.zzcev.hashCode();
        }
        return hashCode2 + i;
    }

    public final void zza(zzya zzya) throws IOException {
        Integer num = this.zzawz;
        if (num != null) {
            zzya.zzd(1, num.intValue());
        }
        long[] jArr = this.zzayu;
        if (jArr != null && jArr.length > 0) {
            int i = 0;
            while (true) {
                long[] jArr2 = this.zzayu;
                if (i >= jArr2.length) {
                    break;
                }
                zzya.zzi(2, jArr2[i]);
                i++;
            }
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        Integer num = this.zzawz;
        if (num != null) {
            zzf += zzya.zzh(1, num.intValue());
        }
        long[] jArr = this.zzayu;
        if (jArr == null || jArr.length <= 0) {
            return zzf;
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            long[] jArr2 = this.zzayu;
            if (i >= jArr2.length) {
                return zzf + i2 + (jArr2.length * 1);
            }
            i2 += zzya.zzbg(jArr2[i]);
            i++;
        }
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 8) {
                this.zzawz = Integer.valueOf(zzxz.zzvb());
            } else if (zzuj == 16) {
                int zzb = zzyl.zzb(zzxz, 16);
                long[] jArr = this.zzayu;
                int length = jArr == null ? 0 : jArr.length;
                long[] jArr2 = new long[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzayu, 0, jArr2, 0, length);
                }
                while (length < jArr2.length - 1) {
                    jArr2[length] = zzxz.zzvc();
                    zzxz.zzuj();
                    length++;
                }
                jArr2[length] = zzxz.zzvc();
                this.zzayu = jArr2;
            } else if (zzuj == 18) {
                int zzas = zzxz.zzas(zzxz.zzvb());
                int position = zzxz.getPosition();
                int i = 0;
                while (zzxz.zzyy() > 0) {
                    zzxz.zzvc();
                    i++;
                }
                zzxz.zzcb(position);
                long[] jArr3 = this.zzayu;
                int length2 = jArr3 == null ? 0 : jArr3.length;
                long[] jArr4 = new long[(i + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zzayu, 0, jArr4, 0, length2);
                }
                while (length2 < jArr4.length) {
                    jArr4[length2] = zzxz.zzvc();
                    length2++;
                }
                this.zzayu = jArr4;
                zzxz.zzat(zzas);
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
