package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbvd.zza.zzc;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import java.io.IOException;

public final class zzbvn extends zzbut<zzbvn> {
    public String url;
    public Integer zzgar;
    private zzc zzgas;
    public String zzgat;
    private String zzgau;
    public zzbvo zzgav;
    public zzbvt[] zzgaw;
    public String zzgax;
    public zzbvs zzgay;
    private Boolean zzgaz;
    private String[] zzgba;
    private String zzgbb;
    private Boolean zzgbc;
    private Boolean zzgbd;
    private byte[] zzgbe;
    public zzbvu zzgbf;
    public String[] zzgbg;
    public String[] zzgbh;

    public zzbvn() {
        this.zzgar = null;
        this.zzgas = null;
        this.url = null;
        this.zzgat = null;
        this.zzgau = null;
        this.zzgav = null;
        this.zzgaw = zzbvt.zzaqe();
        this.zzgax = null;
        this.zzgay = null;
        this.zzgaz = null;
        this.zzgba = zzbvc.zzfxc;
        this.zzgbb = null;
        this.zzgbc = null;
        this.zzgbd = null;
        this.zzgbe = null;
        this.zzgbf = null;
        this.zzgbg = zzbvc.zzfxc;
        this.zzgbh = zzbvc.zzfxc;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        String str = this.url;
        if (str != null) {
            zzbur.zzf(1, str);
        }
        String str2 = this.zzgat;
        if (str2 != null) {
            zzbur.zzf(2, str2);
        }
        String str3 = this.zzgau;
        if (str3 != null) {
            zzbur.zzf(3, str3);
        }
        zzbvt[] zzbvtArr = this.zzgaw;
        int i = 0;
        if (zzbvtArr != null && zzbvtArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzbvt[] zzbvtArr2 = this.zzgaw;
                if (i2 >= zzbvtArr2.length) {
                    break;
                }
                zzbvt zzbvt = zzbvtArr2[i2];
                if (zzbvt != null) {
                    zzbur.zza(4, (zzbuz) zzbvt);
                }
                i2++;
            }
        }
        Boolean bool = this.zzgaz;
        if (bool != null) {
            zzbur.zzj(5, bool.booleanValue());
        }
        String[] strArr = this.zzgba;
        if (strArr != null && strArr.length > 0) {
            int i3 = 0;
            while (true) {
                String[] strArr2 = this.zzgba;
                if (i3 >= strArr2.length) {
                    break;
                }
                String str4 = strArr2[i3];
                if (str4 != null) {
                    zzbur.zzf(6, str4);
                }
                i3++;
            }
        }
        String str5 = this.zzgbb;
        if (str5 != null) {
            zzbur.zzf(7, str5);
        }
        Boolean bool2 = this.zzgbc;
        if (bool2 != null) {
            zzbur.zzj(8, bool2.booleanValue());
        }
        Boolean bool3 = this.zzgbd;
        if (bool3 != null) {
            zzbur.zzj(9, bool3.booleanValue());
        }
        Integer num = this.zzgar;
        if (num != null) {
            zzbur.zzv(10, num.intValue());
        }
        zzc zzc = this.zzgas;
        if (!(zzc == null || zzc == null)) {
            zzbur.zzv(11, zzc.zzom());
        }
        zzbvo zzbvo = this.zzgav;
        if (zzbvo != null) {
            zzbur.zza(12, (zzbuz) zzbvo);
        }
        String str6 = this.zzgax;
        if (str6 != null) {
            zzbur.zzf(13, str6);
        }
        zzbvs zzbvs = this.zzgay;
        if (zzbvs != null) {
            zzbur.zza(14, (zzbuz) zzbvs);
        }
        byte[] bArr = this.zzgbe;
        if (bArr != null) {
            zzbur.zza(15, bArr);
        }
        zzbvu zzbvu = this.zzgbf;
        if (zzbvu != null) {
            zzbur.zza(17, (zzbuz) zzbvu);
        }
        String[] strArr3 = this.zzgbg;
        if (strArr3 != null && strArr3.length > 0) {
            int i4 = 0;
            while (true) {
                String[] strArr4 = this.zzgbg;
                if (i4 >= strArr4.length) {
                    break;
                }
                String str7 = strArr4[i4];
                if (str7 != null) {
                    zzbur.zzf(20, str7);
                }
                i4++;
            }
        }
        String[] strArr5 = this.zzgbh;
        if (strArr5 != null && strArr5.length > 0) {
            while (true) {
                String[] strArr6 = this.zzgbh;
                if (i >= strArr6.length) {
                    break;
                }
                String str8 = strArr6[i];
                if (str8 != null) {
                    zzbur.zzf(21, str8);
                }
                i++;
            }
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt();
        String str = this.url;
        if (str != null) {
            zzt += zzbur.zzg(1, str);
        }
        String str2 = this.zzgat;
        if (str2 != null) {
            zzt += zzbur.zzg(2, str2);
        }
        String str3 = this.zzgau;
        if (str3 != null) {
            zzt += zzbur.zzg(3, str3);
        }
        zzbvt[] zzbvtArr = this.zzgaw;
        int i = 0;
        if (zzbvtArr != null && zzbvtArr.length > 0) {
            int i2 = zzt;
            int i3 = 0;
            while (true) {
                zzbvt[] zzbvtArr2 = this.zzgaw;
                if (i3 >= zzbvtArr2.length) {
                    break;
                }
                zzbvt zzbvt = zzbvtArr2[i3];
                if (zzbvt != null) {
                    i2 += zzbur.zzb(4, (zzbuz) zzbvt);
                }
                i3++;
            }
            zzt = i2;
        }
        Boolean bool = this.zzgaz;
        if (bool != null) {
            bool.booleanValue();
            zzt += zzbur.zzfd(5) + 1;
        }
        String[] strArr = this.zzgba;
        if (strArr != null && strArr.length > 0) {
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (true) {
                String[] strArr2 = this.zzgba;
                if (i4 >= strArr2.length) {
                    break;
                }
                String str4 = strArr2[i4];
                if (str4 != null) {
                    i6++;
                    i5 += zzbur.zzfy(str4);
                }
                i4++;
            }
            zzt = zzt + i5 + (i6 * 1);
        }
        String str5 = this.zzgbb;
        if (str5 != null) {
            zzt += zzbur.zzg(7, str5);
        }
        Boolean bool2 = this.zzgbc;
        if (bool2 != null) {
            bool2.booleanValue();
            zzt += zzbur.zzfd(8) + 1;
        }
        Boolean bool3 = this.zzgbd;
        if (bool3 != null) {
            bool3.booleanValue();
            zzt += zzbur.zzfd(9) + 1;
        }
        Integer num = this.zzgar;
        if (num != null) {
            zzt += zzbur.zzz(10, num.intValue());
        }
        zzc zzc = this.zzgas;
        if (!(zzc == null || zzc == null)) {
            zzt += zzbur.zzz(11, zzc.zzom());
        }
        zzbvo zzbvo = this.zzgav;
        if (zzbvo != null) {
            zzt += zzbur.zzb(12, (zzbuz) zzbvo);
        }
        String str6 = this.zzgax;
        if (str6 != null) {
            zzt += zzbur.zzg(13, str6);
        }
        zzbvs zzbvs = this.zzgay;
        if (zzbvs != null) {
            zzt += zzbur.zzb(14, (zzbuz) zzbvs);
        }
        byte[] bArr = this.zzgbe;
        if (bArr != null) {
            zzt += zzbur.zzb(15, bArr);
        }
        zzbvu zzbvu = this.zzgbf;
        if (zzbvu != null) {
            zzt += zzbur.zzb(17, (zzbuz) zzbvu);
        }
        String[] strArr3 = this.zzgbg;
        if (strArr3 != null && strArr3.length > 0) {
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            while (true) {
                String[] strArr4 = this.zzgbg;
                if (i7 >= strArr4.length) {
                    break;
                }
                String str7 = strArr4[i7];
                if (str7 != null) {
                    i9++;
                    i8 += zzbur.zzfy(str7);
                }
                i7++;
            }
            zzt = zzt + i8 + (i9 * 2);
        }
        String[] strArr5 = this.zzgbh;
        if (strArr5 == null || strArr5.length <= 0) {
            return zzt;
        }
        int i10 = 0;
        int i11 = 0;
        while (true) {
            String[] strArr6 = this.zzgbh;
            if (i >= strArr6.length) {
                return zzt + i10 + (i11 * 2);
            }
            String str8 = strArr6[i];
            if (str8 != null) {
                i11++;
                i10 += zzbur.zzfy(str8);
            }
            i++;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zzf */
    public final zzbvn zza(zzbuq zzbuq) throws IOException {
        int zzakx;
        while (true) {
            int zzaku = zzbuq.zzaku();
            switch (zzaku) {
                case 0:
                    return this;
                case 10:
                    this.url = zzbuq.readString();
                    break;
                case 18:
                    this.zzgat = zzbuq.readString();
                    break;
                case 26:
                    this.zzgau = zzbuq.readString();
                    break;
                case 34:
                    int zzb = zzbvc.zzb(zzbuq, 34);
                    zzbvt[] zzbvtArr = this.zzgaw;
                    int length = zzbvtArr == null ? 0 : zzbvtArr.length;
                    zzbvt[] zzbvtArr2 = new zzbvt[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzgaw, 0, zzbvtArr2, 0, length);
                    }
                    while (length < zzbvtArr2.length - 1) {
                        zzbvtArr2[length] = new zzbvt();
                        zzbuq.zza((zzbuz) zzbvtArr2[length]);
                        zzbuq.zzaku();
                        length++;
                    }
                    zzbvtArr2[length] = new zzbvt();
                    zzbuq.zza((zzbuz) zzbvtArr2[length]);
                    this.zzgaw = zzbvtArr2;
                    break;
                case 40:
                    this.zzgaz = Boolean.valueOf(zzbuq.zzala());
                    break;
                case 50:
                    int zzb2 = zzbvc.zzb(zzbuq, 50);
                    String[] strArr = this.zzgba;
                    int length2 = strArr == null ? 0 : strArr.length;
                    String[] strArr2 = new String[(zzb2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzgba, 0, strArr2, 0, length2);
                    }
                    while (length2 < strArr2.length - 1) {
                        strArr2[length2] = zzbuq.readString();
                        zzbuq.zzaku();
                        length2++;
                    }
                    strArr2[length2] = zzbuq.readString();
                    this.zzgba = strArr2;
                    break;
                case 58:
                    this.zzgbb = zzbuq.readString();
                    break;
                case 64:
                    this.zzgbc = Boolean.valueOf(zzbuq.zzala());
                    break;
                case 72:
                    this.zzgbd = Boolean.valueOf(zzbuq.zzala());
                    break;
                case 80:
                    try {
                        zzakx = zzbuq.zzakx();
                        if (zzakx >= 0 && zzakx <= 9) {
                            this.zzgar = Integer.valueOf(zzakx);
                            break;
                        } else {
                            StringBuilder sb = new StringBuilder(42);
                            sb.append(zzakx);
                            sb.append(" is not a valid enum ReportType");
                            break;
                        }
                    } catch (IllegalArgumentException unused) {
                        zzbuq.zzgc(zzbuq.getPosition());
                        zza(zzbuq, zzaku);
                        break;
                    }
                case 88:
                    int position = zzbuq.getPosition();
                    int zzakx2 = zzbuq.zzakx();
                    switch (zzakx2) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                            this.zzgas = zzc.zzgi(zzakx2);
                            break;
                        default:
                            zzbuq.zzgc(position);
                            zza(zzbuq, zzaku);
                            break;
                    }
                case 98:
                    if (this.zzgav == null) {
                        this.zzgav = new zzbvo();
                    }
                    zzbuq.zza((zzbuz) this.zzgav);
                    break;
                case 106:
                    this.zzgax = zzbuq.readString();
                    break;
                case 114:
                    if (this.zzgay == null) {
                        this.zzgay = new zzbvs();
                    }
                    zzbuq.zza((zzbuz) this.zzgay);
                    break;
                case 122:
                    this.zzgbe = zzbuq.readBytes();
                    break;
                case 138:
                    if (this.zzgbf == null) {
                        this.zzgbf = new zzbvu();
                    }
                    zzbuq.zza((zzbuz) this.zzgbf);
                    break;
                case RequestCodes.MACROS_SUMMARY /*162*/:
                    int zzb3 = zzbvc.zzb(zzbuq, RequestCodes.MACROS_SUMMARY);
                    String[] strArr3 = this.zzgbg;
                    int length3 = strArr3 == null ? 0 : strArr3.length;
                    String[] strArr4 = new String[(zzb3 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzgbg, 0, strArr4, 0, length3);
                    }
                    while (length3 < strArr4.length - 1) {
                        strArr4[length3] = zzbuq.readString();
                        zzbuq.zzaku();
                        length3++;
                    }
                    strArr4[length3] = zzbuq.readString();
                    this.zzgbg = strArr4;
                    break;
                case RequestCodes.CHALLENGES /*170*/:
                    int zzb4 = zzbvc.zzb(zzbuq, RequestCodes.CHALLENGES);
                    String[] strArr5 = this.zzgbh;
                    int length4 = strArr5 == null ? 0 : strArr5.length;
                    String[] strArr6 = new String[(zzb4 + length4)];
                    if (length4 != 0) {
                        System.arraycopy(this.zzgbh, 0, strArr6, 0, length4);
                    }
                    while (length4 < strArr6.length - 1) {
                        strArr6[length4] = zzbuq.readString();
                        zzbuq.zzaku();
                        length4++;
                    }
                    strArr6[length4] = zzbuq.readString();
                    this.zzgbh = strArr6;
                    break;
                default:
                    if (super.zza(zzbuq, zzaku)) {
                        break;
                    } else {
                        return this;
                    }
            }
        }
        StringBuilder sb2 = new StringBuilder(42);
        sb2.append(zzakx);
        sb2.append(" is not a valid enum ReportType");
        throw new IllegalArgumentException(sb2.toString());
    }
}
