package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbvt extends zzbut<zzbvt> {
    private static volatile zzbvt[] zzgbt;
    public String url;
    public Integer zzgbu;
    public zzbvq zzgbv;
    private zzbvr zzgbw;
    private Integer zzgbx;
    private int[] zzgby;
    private String zzgbz;
    public Integer zzgca;
    public String[] zzgcb;

    public static zzbvt[] zzaqe() {
        if (zzgbt == null) {
            synchronized (zzbux.zzfws) {
                if (zzgbt == null) {
                    zzgbt = new zzbvt[0];
                }
            }
        }
        return zzgbt;
    }

    public zzbvt() {
        this.zzgbu = null;
        this.url = null;
        this.zzgbv = null;
        this.zzgbw = null;
        this.zzgbx = null;
        this.zzgby = zzbvc.zzfsg;
        this.zzgbz = null;
        this.zzgca = null;
        this.zzgcb = zzbvc.zzfxc;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        zzbur.zzv(1, this.zzgbu.intValue());
        String str = this.url;
        if (str != null) {
            zzbur.zzf(2, str);
        }
        zzbvq zzbvq = this.zzgbv;
        if (zzbvq != null) {
            zzbur.zza(3, (zzbuz) zzbvq);
        }
        zzbvr zzbvr = this.zzgbw;
        if (zzbvr != null) {
            zzbur.zza(4, (zzbuz) zzbvr);
        }
        Integer num = this.zzgbx;
        if (num != null) {
            zzbur.zzv(5, num.intValue());
        }
        int[] iArr = this.zzgby;
        int i = 0;
        if (iArr != null && iArr.length > 0) {
            int i2 = 0;
            while (true) {
                int[] iArr2 = this.zzgby;
                if (i2 >= iArr2.length) {
                    break;
                }
                zzbur.zzv(6, iArr2[i2]);
                i2++;
            }
        }
        String str2 = this.zzgbz;
        if (str2 != null) {
            zzbur.zzf(7, str2);
        }
        Integer num2 = this.zzgca;
        if (num2 != null) {
            zzbur.zzv(8, num2.intValue());
        }
        String[] strArr = this.zzgcb;
        if (strArr != null && strArr.length > 0) {
            while (true) {
                String[] strArr2 = this.zzgcb;
                if (i >= strArr2.length) {
                    break;
                }
                String str3 = strArr2[i];
                if (str3 != null) {
                    zzbur.zzf(9, str3);
                }
                i++;
            }
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int[] iArr;
        int zzt = super.zzt() + zzbur.zzz(1, this.zzgbu.intValue());
        String str = this.url;
        if (str != null) {
            zzt += zzbur.zzg(2, str);
        }
        zzbvq zzbvq = this.zzgbv;
        if (zzbvq != null) {
            zzt += zzbur.zzb(3, (zzbuz) zzbvq);
        }
        zzbvr zzbvr = this.zzgbw;
        if (zzbvr != null) {
            zzt += zzbur.zzb(4, (zzbuz) zzbvr);
        }
        Integer num = this.zzgbx;
        if (num != null) {
            zzt += zzbur.zzz(5, num.intValue());
        }
        int[] iArr2 = this.zzgby;
        int i = 0;
        if (iArr2 != null && iArr2.length > 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                iArr = this.zzgby;
                if (i2 >= iArr.length) {
                    break;
                }
                i3 += zzbur.zzfe(iArr[i2]);
                i2++;
            }
            zzt = zzt + i3 + (iArr.length * 1);
        }
        String str2 = this.zzgbz;
        if (str2 != null) {
            zzt += zzbur.zzg(7, str2);
        }
        Integer num2 = this.zzgca;
        if (num2 != null) {
            zzt += zzbur.zzz(8, num2.intValue());
        }
        String[] strArr = this.zzgcb;
        if (strArr == null || strArr.length <= 0) {
            return zzt;
        }
        int i4 = 0;
        int i5 = 0;
        while (true) {
            String[] strArr2 = this.zzgcb;
            if (i >= strArr2.length) {
                return zzt + i4 + (i5 * 1);
            }
            String str3 = strArr2[i];
            if (str3 != null) {
                i5++;
                i4 += zzbur.zzfy(str3);
            }
            i++;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zzh */
    public final zzbvt zza(zzbuq zzbuq) throws IOException {
        int zzakx;
        while (true) {
            int zzaku = zzbuq.zzaku();
            switch (zzaku) {
                case 0:
                    return this;
                case 8:
                    this.zzgbu = Integer.valueOf(zzbuq.zzakx());
                    break;
                case 18:
                    this.url = zzbuq.readString();
                    break;
                case 26:
                    if (this.zzgbv == null) {
                        this.zzgbv = new zzbvq();
                    }
                    zzbuq.zza((zzbuz) this.zzgbv);
                    break;
                case 34:
                    if (this.zzgbw == null) {
                        this.zzgbw = new zzbvr();
                    }
                    zzbuq.zza((zzbuz) this.zzgbw);
                    break;
                case 40:
                    this.zzgbx = Integer.valueOf(zzbuq.zzakx());
                    break;
                case 48:
                    int zzb = zzbvc.zzb(zzbuq, 48);
                    int[] iArr = this.zzgby;
                    int length = iArr == null ? 0 : iArr.length;
                    int[] iArr2 = new int[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzgby, 0, iArr2, 0, length);
                    }
                    while (length < iArr2.length - 1) {
                        iArr2[length] = zzbuq.zzakx();
                        zzbuq.zzaku();
                        length++;
                    }
                    iArr2[length] = zzbuq.zzakx();
                    this.zzgby = iArr2;
                    break;
                case 50:
                    int zzer = zzbuq.zzer(zzbuq.zzalm());
                    int position = zzbuq.getPosition();
                    int i = 0;
                    while (zzbuq.zzapl() > 0) {
                        zzbuq.zzakx();
                        i++;
                    }
                    zzbuq.zzgc(position);
                    int[] iArr3 = this.zzgby;
                    int length2 = iArr3 == null ? 0 : iArr3.length;
                    int[] iArr4 = new int[(i + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzgby, 0, iArr4, 0, length2);
                    }
                    while (length2 < iArr4.length) {
                        iArr4[length2] = zzbuq.zzakx();
                        length2++;
                    }
                    this.zzgby = iArr4;
                    zzbuq.zzes(zzer);
                    break;
                case 58:
                    this.zzgbz = zzbuq.readString();
                    break;
                case 64:
                    try {
                        zzakx = zzbuq.zzakx();
                        if (zzakx >= 0 && zzakx <= 3) {
                            this.zzgca = Integer.valueOf(zzakx);
                            break;
                        } else {
                            StringBuilder sb = new StringBuilder(46);
                            sb.append(zzakx);
                            sb.append(" is not a valid enum AdResourceType");
                            break;
                        }
                    } catch (IllegalArgumentException unused) {
                        zzbuq.zzgc(zzbuq.getPosition());
                        zza(zzbuq, zzaku);
                        break;
                    }
                case 74:
                    int zzb2 = zzbvc.zzb(zzbuq, 74);
                    String[] strArr = this.zzgcb;
                    int length3 = strArr == null ? 0 : strArr.length;
                    String[] strArr2 = new String[(zzb2 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzgcb, 0, strArr2, 0, length3);
                    }
                    while (length3 < strArr2.length - 1) {
                        strArr2[length3] = zzbuq.readString();
                        zzbuq.zzaku();
                        length3++;
                    }
                    strArr2[length3] = zzbuq.readString();
                    this.zzgcb = strArr2;
                    break;
                default:
                    if (super.zza(zzbuq, zzaku)) {
                        break;
                    } else {
                        return this;
                    }
            }
        }
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append(zzakx);
        sb2.append(" is not a valid enum AdResourceType");
        throw new IllegalArgumentException(sb2.toString());
    }
}
