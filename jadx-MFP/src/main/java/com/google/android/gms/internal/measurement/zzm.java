package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzm extends zzyc<zzm> {
    private static volatile zzm[] zzpi;
    public int[] zzpj;
    public int[] zzpk;
    public int[] zzpl;
    public int[] zzpm;
    public int[] zzpn;
    public int[] zzpo;
    public int[] zzpp;
    public int[] zzpq;
    public int[] zzpr;
    public int[] zzps;

    public static zzm[] zzi() {
        if (zzpi == null) {
            synchronized (zzyg.zzcfe) {
                if (zzpi == null) {
                    zzpi = new zzm[0];
                }
            }
        }
        return zzpi;
    }

    public zzm() {
        this.zzpj = zzyl.zzcaq;
        this.zzpk = zzyl.zzcaq;
        this.zzpl = zzyl.zzcaq;
        this.zzpm = zzyl.zzcaq;
        this.zzpn = zzyl.zzcaq;
        this.zzpo = zzyl.zzcaq;
        this.zzpp = zzyl.zzcaq;
        this.zzpq = zzyl.zzcaq;
        this.zzpr = zzyl.zzcaq;
        this.zzps = zzyl.zzcaq;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzm)) {
            return false;
        }
        zzm zzm = (zzm) obj;
        if (!zzyg.equals(this.zzpj, zzm.zzpj) || !zzyg.equals(this.zzpk, zzm.zzpk) || !zzyg.equals(this.zzpl, zzm.zzpl) || !zzyg.equals(this.zzpm, zzm.zzpm) || !zzyg.equals(this.zzpn, zzm.zzpn) || !zzyg.equals(this.zzpo, zzm.zzpo) || !zzyg.equals(this.zzpp, zzm.zzpp) || !zzyg.equals(this.zzpq, zzm.zzpq) || !zzyg.equals(this.zzpr, zzm.zzpr) || !zzyg.equals(this.zzps, zzm.zzps)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzm.zzcev == null || zzm.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzm.zzcev);
    }

    public final int hashCode() {
        return ((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzyg.hashCode(this.zzpj)) * 31) + zzyg.hashCode(this.zzpk)) * 31) + zzyg.hashCode(this.zzpl)) * 31) + zzyg.hashCode(this.zzpm)) * 31) + zzyg.hashCode(this.zzpn)) * 31) + zzyg.hashCode(this.zzpo)) * 31) + zzyg.hashCode(this.zzpp)) * 31) + zzyg.hashCode(this.zzpq)) * 31) + zzyg.hashCode(this.zzpr)) * 31) + zzyg.hashCode(this.zzps)) * 31) + ((this.zzcev == null || this.zzcev.isEmpty()) ? 0 : this.zzcev.hashCode());
    }

    public final void zza(zzya zzya) throws IOException {
        int[] iArr = this.zzpj;
        int i = 0;
        if (iArr != null && iArr.length > 0) {
            int i2 = 0;
            while (true) {
                int[] iArr2 = this.zzpj;
                if (i2 >= iArr2.length) {
                    break;
                }
                zzya.zzd(1, iArr2[i2]);
                i2++;
            }
        }
        int[] iArr3 = this.zzpk;
        if (iArr3 != null && iArr3.length > 0) {
            int i3 = 0;
            while (true) {
                int[] iArr4 = this.zzpk;
                if (i3 >= iArr4.length) {
                    break;
                }
                zzya.zzd(2, iArr4[i3]);
                i3++;
            }
        }
        int[] iArr5 = this.zzpl;
        if (iArr5 != null && iArr5.length > 0) {
            int i4 = 0;
            while (true) {
                int[] iArr6 = this.zzpl;
                if (i4 >= iArr6.length) {
                    break;
                }
                zzya.zzd(3, iArr6[i4]);
                i4++;
            }
        }
        int[] iArr7 = this.zzpm;
        if (iArr7 != null && iArr7.length > 0) {
            int i5 = 0;
            while (true) {
                int[] iArr8 = this.zzpm;
                if (i5 >= iArr8.length) {
                    break;
                }
                zzya.zzd(4, iArr8[i5]);
                i5++;
            }
        }
        int[] iArr9 = this.zzpn;
        if (iArr9 != null && iArr9.length > 0) {
            int i6 = 0;
            while (true) {
                int[] iArr10 = this.zzpn;
                if (i6 >= iArr10.length) {
                    break;
                }
                zzya.zzd(5, iArr10[i6]);
                i6++;
            }
        }
        int[] iArr11 = this.zzpo;
        if (iArr11 != null && iArr11.length > 0) {
            int i7 = 0;
            while (true) {
                int[] iArr12 = this.zzpo;
                if (i7 >= iArr12.length) {
                    break;
                }
                zzya.zzd(6, iArr12[i7]);
                i7++;
            }
        }
        int[] iArr13 = this.zzpp;
        if (iArr13 != null && iArr13.length > 0) {
            int i8 = 0;
            while (true) {
                int[] iArr14 = this.zzpp;
                if (i8 >= iArr14.length) {
                    break;
                }
                zzya.zzd(7, iArr14[i8]);
                i8++;
            }
        }
        int[] iArr15 = this.zzpq;
        if (iArr15 != null && iArr15.length > 0) {
            int i9 = 0;
            while (true) {
                int[] iArr16 = this.zzpq;
                if (i9 >= iArr16.length) {
                    break;
                }
                zzya.zzd(8, iArr16[i9]);
                i9++;
            }
        }
        int[] iArr17 = this.zzpr;
        if (iArr17 != null && iArr17.length > 0) {
            int i10 = 0;
            while (true) {
                int[] iArr18 = this.zzpr;
                if (i10 >= iArr18.length) {
                    break;
                }
                zzya.zzd(9, iArr18[i10]);
                i10++;
            }
        }
        int[] iArr19 = this.zzps;
        if (iArr19 != null && iArr19.length > 0) {
            while (true) {
                int[] iArr20 = this.zzps;
                if (i >= iArr20.length) {
                    break;
                }
                zzya.zzd(10, iArr20[i]);
                i++;
            }
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] iArr5;
        int[] iArr6;
        int[] iArr7;
        int[] iArr8;
        int[] iArr9;
        int zzf = super.zzf();
        int[] iArr10 = this.zzpj;
        int i = 0;
        if (iArr10 != null && iArr10.length > 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                iArr9 = this.zzpj;
                if (i2 >= iArr9.length) {
                    break;
                }
                i3 += zzya.zzbe(iArr9[i2]);
                i2++;
            }
            zzf = zzf + i3 + (iArr9.length * 1);
        }
        int[] iArr11 = this.zzpk;
        if (iArr11 != null && iArr11.length > 0) {
            int i4 = 0;
            int i5 = 0;
            while (true) {
                iArr8 = this.zzpk;
                if (i4 >= iArr8.length) {
                    break;
                }
                i5 += zzya.zzbe(iArr8[i4]);
                i4++;
            }
            zzf = zzf + i5 + (iArr8.length * 1);
        }
        int[] iArr12 = this.zzpl;
        if (iArr12 != null && iArr12.length > 0) {
            int i6 = 0;
            int i7 = 0;
            while (true) {
                iArr7 = this.zzpl;
                if (i6 >= iArr7.length) {
                    break;
                }
                i7 += zzya.zzbe(iArr7[i6]);
                i6++;
            }
            zzf = zzf + i7 + (iArr7.length * 1);
        }
        int[] iArr13 = this.zzpm;
        if (iArr13 != null && iArr13.length > 0) {
            int i8 = 0;
            int i9 = 0;
            while (true) {
                iArr6 = this.zzpm;
                if (i8 >= iArr6.length) {
                    break;
                }
                i9 += zzya.zzbe(iArr6[i8]);
                i8++;
            }
            zzf = zzf + i9 + (iArr6.length * 1);
        }
        int[] iArr14 = this.zzpn;
        if (iArr14 != null && iArr14.length > 0) {
            int i10 = 0;
            int i11 = 0;
            while (true) {
                iArr5 = this.zzpn;
                if (i10 >= iArr5.length) {
                    break;
                }
                i11 += zzya.zzbe(iArr5[i10]);
                i10++;
            }
            zzf = zzf + i11 + (iArr5.length * 1);
        }
        int[] iArr15 = this.zzpo;
        if (iArr15 != null && iArr15.length > 0) {
            int i12 = 0;
            int i13 = 0;
            while (true) {
                iArr4 = this.zzpo;
                if (i12 >= iArr4.length) {
                    break;
                }
                i13 += zzya.zzbe(iArr4[i12]);
                i12++;
            }
            zzf = zzf + i13 + (iArr4.length * 1);
        }
        int[] iArr16 = this.zzpp;
        if (iArr16 != null && iArr16.length > 0) {
            int i14 = 0;
            int i15 = 0;
            while (true) {
                iArr3 = this.zzpp;
                if (i14 >= iArr3.length) {
                    break;
                }
                i15 += zzya.zzbe(iArr3[i14]);
                i14++;
            }
            zzf = zzf + i15 + (iArr3.length * 1);
        }
        int[] iArr17 = this.zzpq;
        if (iArr17 != null && iArr17.length > 0) {
            int i16 = 0;
            int i17 = 0;
            while (true) {
                iArr2 = this.zzpq;
                if (i16 >= iArr2.length) {
                    break;
                }
                i17 += zzya.zzbe(iArr2[i16]);
                i16++;
            }
            zzf = zzf + i17 + (iArr2.length * 1);
        }
        int[] iArr18 = this.zzpr;
        if (iArr18 != null && iArr18.length > 0) {
            int i18 = 0;
            int i19 = 0;
            while (true) {
                iArr = this.zzpr;
                if (i18 >= iArr.length) {
                    break;
                }
                i19 += zzya.zzbe(iArr[i18]);
                i18++;
            }
            zzf = zzf + i19 + (iArr.length * 1);
        }
        int[] iArr19 = this.zzps;
        if (iArr19 == null || iArr19.length <= 0) {
            return zzf;
        }
        int i20 = 0;
        while (true) {
            int[] iArr20 = this.zzps;
            if (i >= iArr20.length) {
                return zzf + i20 + (iArr20.length * 1);
            }
            i20 += zzya.zzbe(iArr20[i]);
            i++;
        }
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            switch (zzuj) {
                case 0:
                    return this;
                case 8:
                    int zzb = zzyl.zzb(zzxz, 8);
                    int[] iArr = this.zzpj;
                    int length = iArr == null ? 0 : iArr.length;
                    int[] iArr2 = new int[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzpj, 0, iArr2, 0, length);
                    }
                    while (length < iArr2.length - 1) {
                        iArr2[length] = zzxz.zzvb();
                        zzxz.zzuj();
                        length++;
                    }
                    iArr2[length] = zzxz.zzvb();
                    this.zzpj = iArr2;
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
                    int[] iArr3 = this.zzpj;
                    int length2 = iArr3 == null ? 0 : iArr3.length;
                    int[] iArr4 = new int[(i + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzpj, 0, iArr4, 0, length2);
                    }
                    while (length2 < iArr4.length) {
                        iArr4[length2] = zzxz.zzvb();
                        length2++;
                    }
                    this.zzpj = iArr4;
                    zzxz.zzat(zzas);
                    break;
                case 16:
                    int zzb2 = zzyl.zzb(zzxz, 16);
                    int[] iArr5 = this.zzpk;
                    int length3 = iArr5 == null ? 0 : iArr5.length;
                    int[] iArr6 = new int[(zzb2 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzpk, 0, iArr6, 0, length3);
                    }
                    while (length3 < iArr6.length - 1) {
                        iArr6[length3] = zzxz.zzvb();
                        zzxz.zzuj();
                        length3++;
                    }
                    iArr6[length3] = zzxz.zzvb();
                    this.zzpk = iArr6;
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
                    int[] iArr7 = this.zzpk;
                    int length4 = iArr7 == null ? 0 : iArr7.length;
                    int[] iArr8 = new int[(i2 + length4)];
                    if (length4 != 0) {
                        System.arraycopy(this.zzpk, 0, iArr8, 0, length4);
                    }
                    while (length4 < iArr8.length) {
                        iArr8[length4] = zzxz.zzvb();
                        length4++;
                    }
                    this.zzpk = iArr8;
                    zzxz.zzat(zzas2);
                    break;
                case 24:
                    int zzb3 = zzyl.zzb(zzxz, 24);
                    int[] iArr9 = this.zzpl;
                    int length5 = iArr9 == null ? 0 : iArr9.length;
                    int[] iArr10 = new int[(zzb3 + length5)];
                    if (length5 != 0) {
                        System.arraycopy(this.zzpl, 0, iArr10, 0, length5);
                    }
                    while (length5 < iArr10.length - 1) {
                        iArr10[length5] = zzxz.zzvb();
                        zzxz.zzuj();
                        length5++;
                    }
                    iArr10[length5] = zzxz.zzvb();
                    this.zzpl = iArr10;
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
                    int[] iArr11 = this.zzpl;
                    int length6 = iArr11 == null ? 0 : iArr11.length;
                    int[] iArr12 = new int[(i3 + length6)];
                    if (length6 != 0) {
                        System.arraycopy(this.zzpl, 0, iArr12, 0, length6);
                    }
                    while (length6 < iArr12.length) {
                        iArr12[length6] = zzxz.zzvb();
                        length6++;
                    }
                    this.zzpl = iArr12;
                    zzxz.zzat(zzas3);
                    break;
                case 32:
                    int zzb4 = zzyl.zzb(zzxz, 32);
                    int[] iArr13 = this.zzpm;
                    int length7 = iArr13 == null ? 0 : iArr13.length;
                    int[] iArr14 = new int[(zzb4 + length7)];
                    if (length7 != 0) {
                        System.arraycopy(this.zzpm, 0, iArr14, 0, length7);
                    }
                    while (length7 < iArr14.length - 1) {
                        iArr14[length7] = zzxz.zzvb();
                        zzxz.zzuj();
                        length7++;
                    }
                    iArr14[length7] = zzxz.zzvb();
                    this.zzpm = iArr14;
                    break;
                case 34:
                    int zzas4 = zzxz.zzas(zzxz.zzvb());
                    int position4 = zzxz.getPosition();
                    int i4 = 0;
                    while (zzxz.zzyy() > 0) {
                        zzxz.zzvb();
                        i4++;
                    }
                    zzxz.zzcb(position4);
                    int[] iArr15 = this.zzpm;
                    int length8 = iArr15 == null ? 0 : iArr15.length;
                    int[] iArr16 = new int[(i4 + length8)];
                    if (length8 != 0) {
                        System.arraycopy(this.zzpm, 0, iArr16, 0, length8);
                    }
                    while (length8 < iArr16.length) {
                        iArr16[length8] = zzxz.zzvb();
                        length8++;
                    }
                    this.zzpm = iArr16;
                    zzxz.zzat(zzas4);
                    break;
                case 40:
                    int zzb5 = zzyl.zzb(zzxz, 40);
                    int[] iArr17 = this.zzpn;
                    int length9 = iArr17 == null ? 0 : iArr17.length;
                    int[] iArr18 = new int[(zzb5 + length9)];
                    if (length9 != 0) {
                        System.arraycopy(this.zzpn, 0, iArr18, 0, length9);
                    }
                    while (length9 < iArr18.length - 1) {
                        iArr18[length9] = zzxz.zzvb();
                        zzxz.zzuj();
                        length9++;
                    }
                    iArr18[length9] = zzxz.zzvb();
                    this.zzpn = iArr18;
                    break;
                case 42:
                    int zzas5 = zzxz.zzas(zzxz.zzvb());
                    int position5 = zzxz.getPosition();
                    int i5 = 0;
                    while (zzxz.zzyy() > 0) {
                        zzxz.zzvb();
                        i5++;
                    }
                    zzxz.zzcb(position5);
                    int[] iArr19 = this.zzpn;
                    int length10 = iArr19 == null ? 0 : iArr19.length;
                    int[] iArr20 = new int[(i5 + length10)];
                    if (length10 != 0) {
                        System.arraycopy(this.zzpn, 0, iArr20, 0, length10);
                    }
                    while (length10 < iArr20.length) {
                        iArr20[length10] = zzxz.zzvb();
                        length10++;
                    }
                    this.zzpn = iArr20;
                    zzxz.zzat(zzas5);
                    break;
                case 48:
                    int zzb6 = zzyl.zzb(zzxz, 48);
                    int[] iArr21 = this.zzpo;
                    int length11 = iArr21 == null ? 0 : iArr21.length;
                    int[] iArr22 = new int[(zzb6 + length11)];
                    if (length11 != 0) {
                        System.arraycopy(this.zzpo, 0, iArr22, 0, length11);
                    }
                    while (length11 < iArr22.length - 1) {
                        iArr22[length11] = zzxz.zzvb();
                        zzxz.zzuj();
                        length11++;
                    }
                    iArr22[length11] = zzxz.zzvb();
                    this.zzpo = iArr22;
                    break;
                case 50:
                    int zzas6 = zzxz.zzas(zzxz.zzvb());
                    int position6 = zzxz.getPosition();
                    int i6 = 0;
                    while (zzxz.zzyy() > 0) {
                        zzxz.zzvb();
                        i6++;
                    }
                    zzxz.zzcb(position6);
                    int[] iArr23 = this.zzpo;
                    int length12 = iArr23 == null ? 0 : iArr23.length;
                    int[] iArr24 = new int[(i6 + length12)];
                    if (length12 != 0) {
                        System.arraycopy(this.zzpo, 0, iArr24, 0, length12);
                    }
                    while (length12 < iArr24.length) {
                        iArr24[length12] = zzxz.zzvb();
                        length12++;
                    }
                    this.zzpo = iArr24;
                    zzxz.zzat(zzas6);
                    break;
                case 56:
                    int zzb7 = zzyl.zzb(zzxz, 56);
                    int[] iArr25 = this.zzpp;
                    int length13 = iArr25 == null ? 0 : iArr25.length;
                    int[] iArr26 = new int[(zzb7 + length13)];
                    if (length13 != 0) {
                        System.arraycopy(this.zzpp, 0, iArr26, 0, length13);
                    }
                    while (length13 < iArr26.length - 1) {
                        iArr26[length13] = zzxz.zzvb();
                        zzxz.zzuj();
                        length13++;
                    }
                    iArr26[length13] = zzxz.zzvb();
                    this.zzpp = iArr26;
                    break;
                case 58:
                    int zzas7 = zzxz.zzas(zzxz.zzvb());
                    int position7 = zzxz.getPosition();
                    int i7 = 0;
                    while (zzxz.zzyy() > 0) {
                        zzxz.zzvb();
                        i7++;
                    }
                    zzxz.zzcb(position7);
                    int[] iArr27 = this.zzpp;
                    int length14 = iArr27 == null ? 0 : iArr27.length;
                    int[] iArr28 = new int[(i7 + length14)];
                    if (length14 != 0) {
                        System.arraycopy(this.zzpp, 0, iArr28, 0, length14);
                    }
                    while (length14 < iArr28.length) {
                        iArr28[length14] = zzxz.zzvb();
                        length14++;
                    }
                    this.zzpp = iArr28;
                    zzxz.zzat(zzas7);
                    break;
                case 64:
                    int zzb8 = zzyl.zzb(zzxz, 64);
                    int[] iArr29 = this.zzpq;
                    int length15 = iArr29 == null ? 0 : iArr29.length;
                    int[] iArr30 = new int[(zzb8 + length15)];
                    if (length15 != 0) {
                        System.arraycopy(this.zzpq, 0, iArr30, 0, length15);
                    }
                    while (length15 < iArr30.length - 1) {
                        iArr30[length15] = zzxz.zzvb();
                        zzxz.zzuj();
                        length15++;
                    }
                    iArr30[length15] = zzxz.zzvb();
                    this.zzpq = iArr30;
                    break;
                case 66:
                    int zzas8 = zzxz.zzas(zzxz.zzvb());
                    int position8 = zzxz.getPosition();
                    int i8 = 0;
                    while (zzxz.zzyy() > 0) {
                        zzxz.zzvb();
                        i8++;
                    }
                    zzxz.zzcb(position8);
                    int[] iArr31 = this.zzpq;
                    int length16 = iArr31 == null ? 0 : iArr31.length;
                    int[] iArr32 = new int[(i8 + length16)];
                    if (length16 != 0) {
                        System.arraycopy(this.zzpq, 0, iArr32, 0, length16);
                    }
                    while (length16 < iArr32.length) {
                        iArr32[length16] = zzxz.zzvb();
                        length16++;
                    }
                    this.zzpq = iArr32;
                    zzxz.zzat(zzas8);
                    break;
                case 72:
                    int zzb9 = zzyl.zzb(zzxz, 72);
                    int[] iArr33 = this.zzpr;
                    int length17 = iArr33 == null ? 0 : iArr33.length;
                    int[] iArr34 = new int[(zzb9 + length17)];
                    if (length17 != 0) {
                        System.arraycopy(this.zzpr, 0, iArr34, 0, length17);
                    }
                    while (length17 < iArr34.length - 1) {
                        iArr34[length17] = zzxz.zzvb();
                        zzxz.zzuj();
                        length17++;
                    }
                    iArr34[length17] = zzxz.zzvb();
                    this.zzpr = iArr34;
                    break;
                case 74:
                    int zzas9 = zzxz.zzas(zzxz.zzvb());
                    int position9 = zzxz.getPosition();
                    int i9 = 0;
                    while (zzxz.zzyy() > 0) {
                        zzxz.zzvb();
                        i9++;
                    }
                    zzxz.zzcb(position9);
                    int[] iArr35 = this.zzpr;
                    int length18 = iArr35 == null ? 0 : iArr35.length;
                    int[] iArr36 = new int[(i9 + length18)];
                    if (length18 != 0) {
                        System.arraycopy(this.zzpr, 0, iArr36, 0, length18);
                    }
                    while (length18 < iArr36.length) {
                        iArr36[length18] = zzxz.zzvb();
                        length18++;
                    }
                    this.zzpr = iArr36;
                    zzxz.zzat(zzas9);
                    break;
                case 80:
                    int zzb10 = zzyl.zzb(zzxz, 80);
                    int[] iArr37 = this.zzps;
                    int length19 = iArr37 == null ? 0 : iArr37.length;
                    int[] iArr38 = new int[(zzb10 + length19)];
                    if (length19 != 0) {
                        System.arraycopy(this.zzps, 0, iArr38, 0, length19);
                    }
                    while (length19 < iArr38.length - 1) {
                        iArr38[length19] = zzxz.zzvb();
                        zzxz.zzuj();
                        length19++;
                    }
                    iArr38[length19] = zzxz.zzvb();
                    this.zzps = iArr38;
                    break;
                case 82:
                    int zzas10 = zzxz.zzas(zzxz.zzvb());
                    int position10 = zzxz.getPosition();
                    int i10 = 0;
                    while (zzxz.zzyy() > 0) {
                        zzxz.zzvb();
                        i10++;
                    }
                    zzxz.zzcb(position10);
                    int[] iArr39 = this.zzps;
                    int length20 = iArr39 == null ? 0 : iArr39.length;
                    int[] iArr40 = new int[(i10 + length20)];
                    if (length20 != 0) {
                        System.arraycopy(this.zzps, 0, iArr40, 0, length20);
                    }
                    while (length20 < iArr40.length) {
                        iArr40[length20] = zzxz.zzvb();
                        length20++;
                    }
                    this.zzps = iArr40;
                    zzxz.zzat(zzas10);
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
