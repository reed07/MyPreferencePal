package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfx extends zzyc<zzfx> {
    public long[] zzayp;
    public long[] zzayq;
    public zzfs[] zzayr;
    public zzfy[] zzays;

    public zzfx() {
        this.zzayp = zzyl.zzcfk;
        this.zzayq = zzyl.zzcfk;
        this.zzayr = zzfs.zzmy();
        this.zzays = zzfy.zznc();
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfx)) {
            return false;
        }
        zzfx zzfx = (zzfx) obj;
        if (!zzyg.equals(this.zzayp, zzfx.zzayp) || !zzyg.equals(this.zzayq, zzfx.zzayq) || !zzyg.equals((Object[]) this.zzayr, (Object[]) zzfx.zzayr) || !zzyg.equals((Object[]) this.zzays, (Object[]) zzfx.zzays)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzfx.zzcev == null || zzfx.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzfx.zzcev);
    }

    public final int hashCode() {
        return ((((((((((getClass().getName().hashCode() + 527) * 31) + zzyg.hashCode(this.zzayp)) * 31) + zzyg.hashCode(this.zzayq)) * 31) + zzyg.hashCode((Object[]) this.zzayr)) * 31) + zzyg.hashCode((Object[]) this.zzays)) * 31) + ((this.zzcev == null || this.zzcev.isEmpty()) ? 0 : this.zzcev.hashCode());
    }

    public final void zza(zzya zzya) throws IOException {
        long[] jArr = this.zzayp;
        int i = 0;
        if (jArr != null && jArr.length > 0) {
            int i2 = 0;
            while (true) {
                long[] jArr2 = this.zzayp;
                if (i2 >= jArr2.length) {
                    break;
                }
                zzya.zza(1, jArr2[i2]);
                i2++;
            }
        }
        long[] jArr3 = this.zzayq;
        if (jArr3 != null && jArr3.length > 0) {
            int i3 = 0;
            while (true) {
                long[] jArr4 = this.zzayq;
                if (i3 >= jArr4.length) {
                    break;
                }
                zzya.zza(2, jArr4[i3]);
                i3++;
            }
        }
        zzfs[] zzfsArr = this.zzayr;
        if (zzfsArr != null && zzfsArr.length > 0) {
            int i4 = 0;
            while (true) {
                zzfs[] zzfsArr2 = this.zzayr;
                if (i4 >= zzfsArr2.length) {
                    break;
                }
                zzfs zzfs = zzfsArr2[i4];
                if (zzfs != null) {
                    zzya.zza(3, (zzyi) zzfs);
                }
                i4++;
            }
        }
        zzfy[] zzfyArr = this.zzays;
        if (zzfyArr != null && zzfyArr.length > 0) {
            while (true) {
                zzfy[] zzfyArr2 = this.zzays;
                if (i >= zzfyArr2.length) {
                    break;
                }
                zzfy zzfy = zzfyArr2[i];
                if (zzfy != null) {
                    zzya.zza(4, (zzyi) zzfy);
                }
                i++;
            }
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        long[] jArr;
        long[] jArr2;
        int zzf = super.zzf();
        long[] jArr3 = this.zzayp;
        int i = 0;
        if (jArr3 != null && jArr3.length > 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                jArr2 = this.zzayp;
                if (i2 >= jArr2.length) {
                    break;
                }
                i3 += zzya.zzbg(jArr2[i2]);
                i2++;
            }
            zzf = zzf + i3 + (jArr2.length * 1);
        }
        long[] jArr4 = this.zzayq;
        if (jArr4 != null && jArr4.length > 0) {
            int i4 = 0;
            int i5 = 0;
            while (true) {
                jArr = this.zzayq;
                if (i4 >= jArr.length) {
                    break;
                }
                i5 += zzya.zzbg(jArr[i4]);
                i4++;
            }
            zzf = zzf + i5 + (jArr.length * 1);
        }
        zzfs[] zzfsArr = this.zzayr;
        if (zzfsArr != null && zzfsArr.length > 0) {
            int i6 = zzf;
            int i7 = 0;
            while (true) {
                zzfs[] zzfsArr2 = this.zzayr;
                if (i7 >= zzfsArr2.length) {
                    break;
                }
                zzfs zzfs = zzfsArr2[i7];
                if (zzfs != null) {
                    i6 += zzya.zzb(3, (zzyi) zzfs);
                }
                i7++;
            }
            zzf = i6;
        }
        zzfy[] zzfyArr = this.zzays;
        if (zzfyArr != null && zzfyArr.length > 0) {
            while (true) {
                zzfy[] zzfyArr2 = this.zzays;
                if (i >= zzfyArr2.length) {
                    break;
                }
                zzfy zzfy = zzfyArr2[i];
                if (zzfy != null) {
                    zzf += zzya.zzb(4, (zzyi) zzfy);
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
            if (zzuj == 8) {
                int zzb = zzyl.zzb(zzxz, 8);
                long[] jArr = this.zzayp;
                int length = jArr == null ? 0 : jArr.length;
                long[] jArr2 = new long[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzayp, 0, jArr2, 0, length);
                }
                while (length < jArr2.length - 1) {
                    jArr2[length] = zzxz.zzvc();
                    zzxz.zzuj();
                    length++;
                }
                jArr2[length] = zzxz.zzvc();
                this.zzayp = jArr2;
            } else if (zzuj == 10) {
                int zzas = zzxz.zzas(zzxz.zzvb());
                int position = zzxz.getPosition();
                int i = 0;
                while (zzxz.zzyy() > 0) {
                    zzxz.zzvc();
                    i++;
                }
                zzxz.zzcb(position);
                long[] jArr3 = this.zzayp;
                int length2 = jArr3 == null ? 0 : jArr3.length;
                long[] jArr4 = new long[(i + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zzayp, 0, jArr4, 0, length2);
                }
                while (length2 < jArr4.length) {
                    jArr4[length2] = zzxz.zzvc();
                    length2++;
                }
                this.zzayp = jArr4;
                zzxz.zzat(zzas);
            } else if (zzuj == 16) {
                int zzb2 = zzyl.zzb(zzxz, 16);
                long[] jArr5 = this.zzayq;
                int length3 = jArr5 == null ? 0 : jArr5.length;
                long[] jArr6 = new long[(zzb2 + length3)];
                if (length3 != 0) {
                    System.arraycopy(this.zzayq, 0, jArr6, 0, length3);
                }
                while (length3 < jArr6.length - 1) {
                    jArr6[length3] = zzxz.zzvc();
                    zzxz.zzuj();
                    length3++;
                }
                jArr6[length3] = zzxz.zzvc();
                this.zzayq = jArr6;
            } else if (zzuj == 18) {
                int zzas2 = zzxz.zzas(zzxz.zzvb());
                int position2 = zzxz.getPosition();
                int i2 = 0;
                while (zzxz.zzyy() > 0) {
                    zzxz.zzvc();
                    i2++;
                }
                zzxz.zzcb(position2);
                long[] jArr7 = this.zzayq;
                int length4 = jArr7 == null ? 0 : jArr7.length;
                long[] jArr8 = new long[(i2 + length4)];
                if (length4 != 0) {
                    System.arraycopy(this.zzayq, 0, jArr8, 0, length4);
                }
                while (length4 < jArr8.length) {
                    jArr8[length4] = zzxz.zzvc();
                    length4++;
                }
                this.zzayq = jArr8;
                zzxz.zzat(zzas2);
            } else if (zzuj == 26) {
                int zzb3 = zzyl.zzb(zzxz, 26);
                zzfs[] zzfsArr = this.zzayr;
                int length5 = zzfsArr == null ? 0 : zzfsArr.length;
                zzfs[] zzfsArr2 = new zzfs[(zzb3 + length5)];
                if (length5 != 0) {
                    System.arraycopy(this.zzayr, 0, zzfsArr2, 0, length5);
                }
                while (length5 < zzfsArr2.length - 1) {
                    zzfsArr2[length5] = new zzfs();
                    zzxz.zza((zzyi) zzfsArr2[length5]);
                    zzxz.zzuj();
                    length5++;
                }
                zzfsArr2[length5] = new zzfs();
                zzxz.zza((zzyi) zzfsArr2[length5]);
                this.zzayr = zzfsArr2;
            } else if (zzuj == 34) {
                int zzb4 = zzyl.zzb(zzxz, 34);
                zzfy[] zzfyArr = this.zzays;
                int length6 = zzfyArr == null ? 0 : zzfyArr.length;
                zzfy[] zzfyArr2 = new zzfy[(zzb4 + length6)];
                if (length6 != 0) {
                    System.arraycopy(this.zzays, 0, zzfyArr2, 0, length6);
                }
                while (length6 < zzfyArr2.length - 1) {
                    zzfyArr2[length6] = new zzfy();
                    zzxz.zza((zzyi) zzfyArr2[length6]);
                    zzxz.zzuj();
                    length6++;
                }
                zzfyArr2[length6] = new zzfy();
                zzxz.zza((zzyi) zzfyArr2[length6]);
                this.zzays = zzfyArr2;
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
