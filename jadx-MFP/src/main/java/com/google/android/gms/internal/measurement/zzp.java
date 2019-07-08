package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzp extends zzyc<zzp> {
    private static volatile zzp[] zzqi;
    public String string;
    public int type;
    public zzp[] zzqj;
    public zzp[] zzqk;
    public zzp[] zzql;
    public String zzqm;
    public String zzqn;
    public long zzqo;
    public boolean zzqp;
    public zzp[] zzqq;
    public int[] zzqr;
    public boolean zzqs;

    private static int zzc(int i) {
        if (i > 0 && i <= 17) {
            return i;
        }
        StringBuilder sb = new StringBuilder(40);
        sb.append(i);
        sb.append(" is not a valid enum Escaping");
        throw new IllegalArgumentException(sb.toString());
    }

    public static zzp[] zzk() {
        if (zzqi == null) {
            synchronized (zzyg.zzcfe) {
                if (zzqi == null) {
                    zzqi = new zzp[0];
                }
            }
        }
        return zzqi;
    }

    public zzp() {
        this.type = 1;
        this.string = "";
        this.zzqj = zzk();
        this.zzqk = zzk();
        this.zzql = zzk();
        this.zzqm = "";
        this.zzqn = "";
        this.zzqo = 0;
        this.zzqp = false;
        this.zzqq = zzk();
        this.zzqr = zzyl.zzcaq;
        this.zzqs = false;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzp)) {
            return false;
        }
        zzp zzp = (zzp) obj;
        if (this.type != zzp.type) {
            return false;
        }
        String str = this.string;
        if (str == null) {
            if (zzp.string != null) {
                return false;
            }
        } else if (!str.equals(zzp.string)) {
            return false;
        }
        if (!zzyg.equals((Object[]) this.zzqj, (Object[]) zzp.zzqj) || !zzyg.equals((Object[]) this.zzqk, (Object[]) zzp.zzqk) || !zzyg.equals((Object[]) this.zzql, (Object[]) zzp.zzql)) {
            return false;
        }
        String str2 = this.zzqm;
        if (str2 == null) {
            if (zzp.zzqm != null) {
                return false;
            }
        } else if (!str2.equals(zzp.zzqm)) {
            return false;
        }
        String str3 = this.zzqn;
        if (str3 == null) {
            if (zzp.zzqn != null) {
                return false;
            }
        } else if (!str3.equals(zzp.zzqn)) {
            return false;
        }
        if (this.zzqo != zzp.zzqo || this.zzqp != zzp.zzqp || !zzyg.equals((Object[]) this.zzqq, (Object[]) zzp.zzqq) || !zzyg.equals(this.zzqr, zzp.zzqr) || this.zzqs != zzp.zzqs) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzp.zzcev == null || zzp.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzp.zzcev);
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.type) * 31;
        String str = this.string;
        int i = 0;
        int hashCode2 = (((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + zzyg.hashCode((Object[]) this.zzqj)) * 31) + zzyg.hashCode((Object[]) this.zzqk)) * 31) + zzyg.hashCode((Object[]) this.zzql)) * 31;
        String str2 = this.zzqm;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.zzqn;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        long j = this.zzqo;
        int i2 = (hashCode4 + ((int) (j ^ (j >>> 32)))) * 31;
        int i3 = 1231;
        int hashCode5 = (((((i2 + (this.zzqp ? 1231 : 1237)) * 31) + zzyg.hashCode((Object[]) this.zzqq)) * 31) + zzyg.hashCode(this.zzqr)) * 31;
        if (!this.zzqs) {
            i3 = 1237;
        }
        int i4 = (hashCode5 + i3) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i = this.zzcev.hashCode();
        }
        return i4 + i;
    }

    public final void zza(zzya zzya) throws IOException {
        zzya.zzd(1, this.type);
        String str = this.string;
        if (str != null && !str.equals("")) {
            zzya.zzb(2, this.string);
        }
        zzp[] zzpArr = this.zzqj;
        int i = 0;
        if (zzpArr != null && zzpArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzp[] zzpArr2 = this.zzqj;
                if (i2 >= zzpArr2.length) {
                    break;
                }
                zzp zzp = zzpArr2[i2];
                if (zzp != null) {
                    zzya.zza(3, (zzyi) zzp);
                }
                i2++;
            }
        }
        zzp[] zzpArr3 = this.zzqk;
        if (zzpArr3 != null && zzpArr3.length > 0) {
            int i3 = 0;
            while (true) {
                zzp[] zzpArr4 = this.zzqk;
                if (i3 >= zzpArr4.length) {
                    break;
                }
                zzp zzp2 = zzpArr4[i3];
                if (zzp2 != null) {
                    zzya.zza(4, (zzyi) zzp2);
                }
                i3++;
            }
        }
        zzp[] zzpArr5 = this.zzql;
        if (zzpArr5 != null && zzpArr5.length > 0) {
            int i4 = 0;
            while (true) {
                zzp[] zzpArr6 = this.zzql;
                if (i4 >= zzpArr6.length) {
                    break;
                }
                zzp zzp3 = zzpArr6[i4];
                if (zzp3 != null) {
                    zzya.zza(5, (zzyi) zzp3);
                }
                i4++;
            }
        }
        String str2 = this.zzqm;
        if (str2 != null && !str2.equals("")) {
            zzya.zzb(6, this.zzqm);
        }
        String str3 = this.zzqn;
        if (str3 != null && !str3.equals("")) {
            zzya.zzb(7, this.zzqn);
        }
        long j = this.zzqo;
        if (j != 0) {
            zzya.zzi(8, j);
        }
        boolean z = this.zzqs;
        if (z) {
            zzya.zzb(9, z);
        }
        int[] iArr = this.zzqr;
        if (iArr != null && iArr.length > 0) {
            int i5 = 0;
            while (true) {
                int[] iArr2 = this.zzqr;
                if (i5 >= iArr2.length) {
                    break;
                }
                zzya.zzd(10, iArr2[i5]);
                i5++;
            }
        }
        zzp[] zzpArr7 = this.zzqq;
        if (zzpArr7 != null && zzpArr7.length > 0) {
            while (true) {
                zzp[] zzpArr8 = this.zzqq;
                if (i >= zzpArr8.length) {
                    break;
                }
                zzp zzp4 = zzpArr8[i];
                if (zzp4 != null) {
                    zzya.zza(11, (zzyi) zzp4);
                }
                i++;
            }
        }
        boolean z2 = this.zzqp;
        if (z2) {
            zzya.zzb(12, z2);
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int[] iArr;
        int zzf = super.zzf() + zzya.zzh(1, this.type);
        String str = this.string;
        if (str != null && !str.equals("")) {
            zzf += zzya.zzc(2, this.string);
        }
        zzp[] zzpArr = this.zzqj;
        int i = 0;
        if (zzpArr != null && zzpArr.length > 0) {
            int i2 = zzf;
            int i3 = 0;
            while (true) {
                zzp[] zzpArr2 = this.zzqj;
                if (i3 >= zzpArr2.length) {
                    break;
                }
                zzp zzp = zzpArr2[i3];
                if (zzp != null) {
                    i2 += zzya.zzb(3, (zzyi) zzp);
                }
                i3++;
            }
            zzf = i2;
        }
        zzp[] zzpArr3 = this.zzqk;
        if (zzpArr3 != null && zzpArr3.length > 0) {
            int i4 = zzf;
            int i5 = 0;
            while (true) {
                zzp[] zzpArr4 = this.zzqk;
                if (i5 >= zzpArr4.length) {
                    break;
                }
                zzp zzp2 = zzpArr4[i5];
                if (zzp2 != null) {
                    i4 += zzya.zzb(4, (zzyi) zzp2);
                }
                i5++;
            }
            zzf = i4;
        }
        zzp[] zzpArr5 = this.zzql;
        if (zzpArr5 != null && zzpArr5.length > 0) {
            int i6 = zzf;
            int i7 = 0;
            while (true) {
                zzp[] zzpArr6 = this.zzql;
                if (i7 >= zzpArr6.length) {
                    break;
                }
                zzp zzp3 = zzpArr6[i7];
                if (zzp3 != null) {
                    i6 += zzya.zzb(5, (zzyi) zzp3);
                }
                i7++;
            }
            zzf = i6;
        }
        String str2 = this.zzqm;
        if (str2 != null && !str2.equals("")) {
            zzf += zzya.zzc(6, this.zzqm);
        }
        String str3 = this.zzqn;
        if (str3 != null && !str3.equals("")) {
            zzf += zzya.zzc(7, this.zzqn);
        }
        long j = this.zzqo;
        if (j != 0) {
            zzf += zzya.zzd(8, j);
        }
        if (this.zzqs) {
            zzf += zzya.zzbd(9) + 1;
        }
        int[] iArr2 = this.zzqr;
        if (iArr2 != null && iArr2.length > 0) {
            int i8 = 0;
            int i9 = 0;
            while (true) {
                iArr = this.zzqr;
                if (i8 >= iArr.length) {
                    break;
                }
                i9 += zzya.zzbe(iArr[i8]);
                i8++;
            }
            zzf = zzf + i9 + (iArr.length * 1);
        }
        zzp[] zzpArr7 = this.zzqq;
        if (zzpArr7 != null && zzpArr7.length > 0) {
            while (true) {
                zzp[] zzpArr8 = this.zzqq;
                if (i >= zzpArr8.length) {
                    break;
                }
                zzp zzp4 = zzpArr8[i];
                if (zzp4 != null) {
                    zzf += zzya.zzb(11, (zzyi) zzp4);
                }
                i++;
            }
        }
        return this.zzqp ? zzf + zzya.zzbd(12) + 1 : zzf;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzp zza(zzxz zzxz) throws IOException {
        int zzvb;
        while (true) {
            int zzuj = zzxz.zzuj();
            switch (zzuj) {
                case 0:
                    return this;
                case 8:
                    try {
                        zzvb = zzxz.zzvb();
                        if (zzvb > 0 && zzvb <= 8) {
                            this.type = zzvb;
                            break;
                        } else {
                            StringBuilder sb = new StringBuilder(36);
                            sb.append(zzvb);
                            sb.append(" is not a valid enum Type");
                            break;
                        }
                    } catch (IllegalArgumentException unused) {
                        zzxz.zzcb(zzxz.getPosition());
                        zza(zzxz, zzuj);
                        break;
                    }
                case 18:
                    this.string = zzxz.readString();
                    break;
                case 26:
                    int zzb = zzyl.zzb(zzxz, 26);
                    zzp[] zzpArr = this.zzqj;
                    int length = zzpArr == null ? 0 : zzpArr.length;
                    zzp[] zzpArr2 = new zzp[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzqj, 0, zzpArr2, 0, length);
                    }
                    while (length < zzpArr2.length - 1) {
                        zzpArr2[length] = new zzp();
                        zzxz.zza((zzyi) zzpArr2[length]);
                        zzxz.zzuj();
                        length++;
                    }
                    zzpArr2[length] = new zzp();
                    zzxz.zza((zzyi) zzpArr2[length]);
                    this.zzqj = zzpArr2;
                    break;
                case 34:
                    int zzb2 = zzyl.zzb(zzxz, 34);
                    zzp[] zzpArr3 = this.zzqk;
                    int length2 = zzpArr3 == null ? 0 : zzpArr3.length;
                    zzp[] zzpArr4 = new zzp[(zzb2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzqk, 0, zzpArr4, 0, length2);
                    }
                    while (length2 < zzpArr4.length - 1) {
                        zzpArr4[length2] = new zzp();
                        zzxz.zza((zzyi) zzpArr4[length2]);
                        zzxz.zzuj();
                        length2++;
                    }
                    zzpArr4[length2] = new zzp();
                    zzxz.zza((zzyi) zzpArr4[length2]);
                    this.zzqk = zzpArr4;
                    break;
                case 42:
                    int zzb3 = zzyl.zzb(zzxz, 42);
                    zzp[] zzpArr5 = this.zzql;
                    int length3 = zzpArr5 == null ? 0 : zzpArr5.length;
                    zzp[] zzpArr6 = new zzp[(zzb3 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzql, 0, zzpArr6, 0, length3);
                    }
                    while (length3 < zzpArr6.length - 1) {
                        zzpArr6[length3] = new zzp();
                        zzxz.zza((zzyi) zzpArr6[length3]);
                        zzxz.zzuj();
                        length3++;
                    }
                    zzpArr6[length3] = new zzp();
                    zzxz.zza((zzyi) zzpArr6[length3]);
                    this.zzql = zzpArr6;
                    break;
                case 50:
                    this.zzqm = zzxz.readString();
                    break;
                case 58:
                    this.zzqn = zzxz.readString();
                    break;
                case 64:
                    this.zzqo = zzxz.zzvc();
                    break;
                case 72:
                    this.zzqs = zzxz.zzup();
                    break;
                case 80:
                    int zzb4 = zzyl.zzb(zzxz, 80);
                    int[] iArr = new int[zzb4];
                    int i = 0;
                    for (int i2 = 0; i2 < zzb4; i2++) {
                        if (i2 != 0) {
                            zzxz.zzuj();
                        }
                        int position = zzxz.getPosition();
                        try {
                            iArr[i] = zzc(zzxz.zzvb());
                            i++;
                        } catch (IllegalArgumentException unused2) {
                            zzxz.zzcb(position);
                            zza(zzxz, zzuj);
                        }
                    }
                    if (i == 0) {
                        break;
                    } else {
                        int[] iArr2 = this.zzqr;
                        int length4 = iArr2 == null ? 0 : iArr2.length;
                        if (length4 != 0 || i != iArr.length) {
                            int[] iArr3 = new int[(length4 + i)];
                            if (length4 != 0) {
                                System.arraycopy(this.zzqr, 0, iArr3, 0, length4);
                            }
                            System.arraycopy(iArr, 0, iArr3, length4, i);
                            this.zzqr = iArr3;
                            break;
                        } else {
                            this.zzqr = iArr;
                            break;
                        }
                    }
                case 82:
                    int zzas = zzxz.zzas(zzxz.zzvb());
                    int position2 = zzxz.getPosition();
                    int i3 = 0;
                    while (zzxz.zzyy() > 0) {
                        try {
                            zzc(zzxz.zzvb());
                            i3++;
                        } catch (IllegalArgumentException unused3) {
                        }
                    }
                    if (i3 != 0) {
                        zzxz.zzcb(position2);
                        int[] iArr4 = this.zzqr;
                        int length5 = iArr4 == null ? 0 : iArr4.length;
                        int[] iArr5 = new int[(i3 + length5)];
                        if (length5 != 0) {
                            System.arraycopy(this.zzqr, 0, iArr5, 0, length5);
                        }
                        while (zzxz.zzyy() > 0) {
                            int position3 = zzxz.getPosition();
                            try {
                                iArr5[length5] = zzc(zzxz.zzvb());
                                length5++;
                            } catch (IllegalArgumentException unused4) {
                                zzxz.zzcb(position3);
                                zza(zzxz, 80);
                            }
                        }
                        this.zzqr = iArr5;
                    }
                    zzxz.zzat(zzas);
                    break;
                case 90:
                    int zzb5 = zzyl.zzb(zzxz, 90);
                    zzp[] zzpArr7 = this.zzqq;
                    int length6 = zzpArr7 == null ? 0 : zzpArr7.length;
                    zzp[] zzpArr8 = new zzp[(zzb5 + length6)];
                    if (length6 != 0) {
                        System.arraycopy(this.zzqq, 0, zzpArr8, 0, length6);
                    }
                    while (length6 < zzpArr8.length - 1) {
                        zzpArr8[length6] = new zzp();
                        zzxz.zza((zzyi) zzpArr8[length6]);
                        zzxz.zzuj();
                        length6++;
                    }
                    zzpArr8[length6] = new zzp();
                    zzxz.zza((zzyi) zzpArr8[length6]);
                    this.zzqq = zzpArr8;
                    break;
                case 96:
                    this.zzqp = zzxz.zzup();
                    break;
                default:
                    if (super.zza(zzxz, zzuj)) {
                        break;
                    } else {
                        return this;
                    }
            }
        }
        StringBuilder sb2 = new StringBuilder(36);
        sb2.append(zzvb);
        sb2.append(" is not a valid enum Type");
        throw new IllegalArgumentException(sb2.toString());
    }
}
