package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzg {

    public static final class zza extends zzyc<zza> {
        public static final zzyd<zzp, zza> zzpt = zzyd.zza(11, zza.class, 810);
        private static final zza[] zzpu = new zza[0];
        public int[] zzpv;
        public int[] zzpw;
        public int[] zzpx;
        private int zzpy;
        public int[] zzpz;
        public int zzqa;
        private int zzqb;

        public zza() {
            this.zzpv = zzyl.zzcaq;
            this.zzpw = zzyl.zzcaq;
            this.zzpx = zzyl.zzcaq;
            this.zzpy = 0;
            this.zzpz = zzyl.zzcaq;
            this.zzqa = 0;
            this.zzqb = 0;
            this.zzcev = null;
            this.zzcff = -1;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (!zzyg.equals(this.zzpv, zza.zzpv) || !zzyg.equals(this.zzpw, zza.zzpw) || !zzyg.equals(this.zzpx, zza.zzpx) || this.zzpy != zza.zzpy || !zzyg.equals(this.zzpz, zza.zzpz) || this.zzqa != zza.zzqa || this.zzqb != zza.zzqb) {
                return false;
            }
            if (this.zzcev == null || this.zzcev.isEmpty()) {
                return zza.zzcev == null || zza.zzcev.isEmpty();
            }
            return this.zzcev.equals(zza.zzcev);
        }

        public final int hashCode() {
            return ((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzyg.hashCode(this.zzpv)) * 31) + zzyg.hashCode(this.zzpw)) * 31) + zzyg.hashCode(this.zzpx)) * 31) + this.zzpy) * 31) + zzyg.hashCode(this.zzpz)) * 31) + this.zzqa) * 31) + this.zzqb) * 31) + ((this.zzcev == null || this.zzcev.isEmpty()) ? 0 : this.zzcev.hashCode());
        }

        public final void zza(zzya zzya) throws IOException {
            int[] iArr = this.zzpv;
            int i = 0;
            if (iArr != null && iArr.length > 0) {
                int i2 = 0;
                while (true) {
                    int[] iArr2 = this.zzpv;
                    if (i2 >= iArr2.length) {
                        break;
                    }
                    zzya.zzd(1, iArr2[i2]);
                    i2++;
                }
            }
            int[] iArr3 = this.zzpw;
            if (iArr3 != null && iArr3.length > 0) {
                int i3 = 0;
                while (true) {
                    int[] iArr4 = this.zzpw;
                    if (i3 >= iArr4.length) {
                        break;
                    }
                    zzya.zzd(2, iArr4[i3]);
                    i3++;
                }
            }
            int[] iArr5 = this.zzpx;
            if (iArr5 != null && iArr5.length > 0) {
                int i4 = 0;
                while (true) {
                    int[] iArr6 = this.zzpx;
                    if (i4 >= iArr6.length) {
                        break;
                    }
                    zzya.zzd(3, iArr6[i4]);
                    i4++;
                }
            }
            int i5 = this.zzpy;
            if (i5 != 0) {
                zzya.zzd(4, i5);
            }
            int[] iArr7 = this.zzpz;
            if (iArr7 != null && iArr7.length > 0) {
                while (true) {
                    int[] iArr8 = this.zzpz;
                    if (i >= iArr8.length) {
                        break;
                    }
                    zzya.zzd(5, iArr8[i]);
                    i++;
                }
            }
            int i6 = this.zzqa;
            if (i6 != 0) {
                zzya.zzd(6, i6);
            }
            int i7 = this.zzqb;
            if (i7 != 0) {
                zzya.zzd(7, i7);
            }
            super.zza(zzya);
        }

        /* access modifiers changed from: protected */
        public final int zzf() {
            int[] iArr;
            int[] iArr2;
            int[] iArr3;
            int[] iArr4;
            int zzf = super.zzf();
            int[] iArr5 = this.zzpv;
            int i = 0;
            if (iArr5 != null && iArr5.length > 0) {
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    iArr4 = this.zzpv;
                    if (i2 >= iArr4.length) {
                        break;
                    }
                    i3 += zzya.zzbe(iArr4[i2]);
                    i2++;
                }
                zzf = zzf + i3 + (iArr4.length * 1);
            }
            int[] iArr6 = this.zzpw;
            if (iArr6 != null && iArr6.length > 0) {
                int i4 = 0;
                int i5 = 0;
                while (true) {
                    iArr3 = this.zzpw;
                    if (i4 >= iArr3.length) {
                        break;
                    }
                    i5 += zzya.zzbe(iArr3[i4]);
                    i4++;
                }
                zzf = zzf + i5 + (iArr3.length * 1);
            }
            int[] iArr7 = this.zzpx;
            if (iArr7 != null && iArr7.length > 0) {
                int i6 = 0;
                int i7 = 0;
                while (true) {
                    iArr2 = this.zzpx;
                    if (i6 >= iArr2.length) {
                        break;
                    }
                    i7 += zzya.zzbe(iArr2[i6]);
                    i6++;
                }
                zzf = zzf + i7 + (iArr2.length * 1);
            }
            int i8 = this.zzpy;
            if (i8 != 0) {
                zzf += zzya.zzh(4, i8);
            }
            int[] iArr8 = this.zzpz;
            if (iArr8 != null && iArr8.length > 0) {
                int i9 = 0;
                while (true) {
                    iArr = this.zzpz;
                    if (i >= iArr.length) {
                        break;
                    }
                    i9 += zzya.zzbe(iArr[i]);
                    i++;
                }
                zzf = zzf + i9 + (iArr.length * 1);
            }
            int i10 = this.zzqa;
            if (i10 != 0) {
                zzf += zzya.zzh(6, i10);
            }
            int i11 = this.zzqb;
            return i11 != 0 ? zzf + zzya.zzh(7, i11) : zzf;
        }

        public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
            while (true) {
                int zzuj = zzxz.zzuj();
                switch (zzuj) {
                    case 0:
                        return this;
                    case 8:
                        int zzb = zzyl.zzb(zzxz, 8);
                        int[] iArr = this.zzpv;
                        int length = iArr == null ? 0 : iArr.length;
                        int[] iArr2 = new int[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzpv, 0, iArr2, 0, length);
                        }
                        while (length < iArr2.length - 1) {
                            iArr2[length] = zzxz.zzvb();
                            zzxz.zzuj();
                            length++;
                        }
                        iArr2[length] = zzxz.zzvb();
                        this.zzpv = iArr2;
                        break;
                    case 10:
                        int zzas = zzxz.zzas(zzxz.zzvb());
                        int position = zzxz.getPosition();
                        int i = 0;
                        while (zzxz.zzyy() > 0) {
                            zzxz.zzvb();
                            i++;
                        }
                        zzxz.zzcb(position);
                        int[] iArr3 = this.zzpv;
                        int length2 = iArr3 == null ? 0 : iArr3.length;
                        int[] iArr4 = new int[(i + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzpv, 0, iArr4, 0, length2);
                        }
                        while (length2 < iArr4.length) {
                            iArr4[length2] = zzxz.zzvb();
                            length2++;
                        }
                        this.zzpv = iArr4;
                        zzxz.zzat(zzas);
                        break;
                    case 16:
                        int zzb2 = zzyl.zzb(zzxz, 16);
                        int[] iArr5 = this.zzpw;
                        int length3 = iArr5 == null ? 0 : iArr5.length;
                        int[] iArr6 = new int[(zzb2 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzpw, 0, iArr6, 0, length3);
                        }
                        while (length3 < iArr6.length - 1) {
                            iArr6[length3] = zzxz.zzvb();
                            zzxz.zzuj();
                            length3++;
                        }
                        iArr6[length3] = zzxz.zzvb();
                        this.zzpw = iArr6;
                        break;
                    case 18:
                        int zzas2 = zzxz.zzas(zzxz.zzvb());
                        int position2 = zzxz.getPosition();
                        int i2 = 0;
                        while (zzxz.zzyy() > 0) {
                            zzxz.zzvb();
                            i2++;
                        }
                        zzxz.zzcb(position2);
                        int[] iArr7 = this.zzpw;
                        int length4 = iArr7 == null ? 0 : iArr7.length;
                        int[] iArr8 = new int[(i2 + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.zzpw, 0, iArr8, 0, length4);
                        }
                        while (length4 < iArr8.length) {
                            iArr8[length4] = zzxz.zzvb();
                            length4++;
                        }
                        this.zzpw = iArr8;
                        zzxz.zzat(zzas2);
                        break;
                    case 24:
                        int zzb3 = zzyl.zzb(zzxz, 24);
                        int[] iArr9 = this.zzpx;
                        int length5 = iArr9 == null ? 0 : iArr9.length;
                        int[] iArr10 = new int[(zzb3 + length5)];
                        if (length5 != 0) {
                            System.arraycopy(this.zzpx, 0, iArr10, 0, length5);
                        }
                        while (length5 < iArr10.length - 1) {
                            iArr10[length5] = zzxz.zzvb();
                            zzxz.zzuj();
                            length5++;
                        }
                        iArr10[length5] = zzxz.zzvb();
                        this.zzpx = iArr10;
                        break;
                    case 26:
                        int zzas3 = zzxz.zzas(zzxz.zzvb());
                        int position3 = zzxz.getPosition();
                        int i3 = 0;
                        while (zzxz.zzyy() > 0) {
                            zzxz.zzvb();
                            i3++;
                        }
                        zzxz.zzcb(position3);
                        int[] iArr11 = this.zzpx;
                        int length6 = iArr11 == null ? 0 : iArr11.length;
                        int[] iArr12 = new int[(i3 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.zzpx, 0, iArr12, 0, length6);
                        }
                        while (length6 < iArr12.length) {
                            iArr12[length6] = zzxz.zzvb();
                            length6++;
                        }
                        this.zzpx = iArr12;
                        zzxz.zzat(zzas3);
                        break;
                    case 32:
                        this.zzpy = zzxz.zzvb();
                        break;
                    case 40:
                        int zzb4 = zzyl.zzb(zzxz, 40);
                        int[] iArr13 = this.zzpz;
                        int length7 = iArr13 == null ? 0 : iArr13.length;
                        int[] iArr14 = new int[(zzb4 + length7)];
                        if (length7 != 0) {
                            System.arraycopy(this.zzpz, 0, iArr14, 0, length7);
                        }
                        while (length7 < iArr14.length - 1) {
                            iArr14[length7] = zzxz.zzvb();
                            zzxz.zzuj();
                            length7++;
                        }
                        iArr14[length7] = zzxz.zzvb();
                        this.zzpz = iArr14;
                        break;
                    case 42:
                        int zzas4 = zzxz.zzas(zzxz.zzvb());
                        int position4 = zzxz.getPosition();
                        int i4 = 0;
                        while (zzxz.zzyy() > 0) {
                            zzxz.zzvb();
                            i4++;
                        }
                        zzxz.zzcb(position4);
                        int[] iArr15 = this.zzpz;
                        int length8 = iArr15 == null ? 0 : iArr15.length;
                        int[] iArr16 = new int[(i4 + length8)];
                        if (length8 != 0) {
                            System.arraycopy(this.zzpz, 0, iArr16, 0, length8);
                        }
                        while (length8 < iArr16.length) {
                            iArr16[length8] = zzxz.zzvb();
                            length8++;
                        }
                        this.zzpz = iArr16;
                        zzxz.zzat(zzas4);
                        break;
                    case 48:
                        this.zzqa = zzxz.zzvb();
                        break;
                    case 56:
                        this.zzqb = zzxz.zzvb();
                        break;
                    default:
                        if (super.zza(zzxz, zzuj)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }
    }
}
