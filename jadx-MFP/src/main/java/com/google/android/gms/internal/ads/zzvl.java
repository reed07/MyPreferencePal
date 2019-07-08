package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzuw.zza;
import com.google.android.gms.internal.ads.zzuw.zzb;
import com.google.android.gms.internal.ads.zzuw.zzc;
import com.google.android.gms.internal.ads.zzuw.zzf;
import com.google.android.gms.internal.ads.zzuw.zzg;
import com.google.android.gms.internal.ads.zzuw.zzi;
import com.google.android.gms.internal.ads.zzuw.zzj;
import java.io.IOException;

public final class zzvl extends zzbut<zzvl> {
    public Integer zzcgm;
    private zzvc zzcgn;
    private zzb zzcgo;
    public zzvm zzcgp;
    private zza[] zzcgq;
    private zzc zzcgr;
    private zzj zzcgs;
    private zzi zzcgt;
    private zzf zzcgu;
    private zzg zzcgv;
    private zzvr[] zzcgw;

    public zzvl() {
        this.zzcgm = null;
        this.zzcgn = null;
        this.zzcgo = null;
        this.zzcgp = null;
        this.zzcgq = new zza[0];
        this.zzcgr = null;
        this.zzcgs = null;
        this.zzcgt = null;
        this.zzcgu = null;
        this.zzcgv = null;
        this.zzcgw = zzvr.zzpl();
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        Integer num = this.zzcgm;
        if (num != null) {
            zzbur.zzv(7, num.intValue());
        }
        zzvc zzvc = this.zzcgn;
        if (!(zzvc == null || zzvc == null)) {
            zzbur.zzv(8, zzvc.zzom());
        }
        zzb zzb = this.zzcgo;
        if (zzb != null) {
            zzbur.zze(9, zzb);
        }
        zzvm zzvm = this.zzcgp;
        if (zzvm != null) {
            zzbur.zza(10, (zzbuz) zzvm);
        }
        zza[] zzaArr = this.zzcgq;
        int i = 0;
        if (zzaArr != null && zzaArr.length > 0) {
            int i2 = 0;
            while (true) {
                zza[] zzaArr2 = this.zzcgq;
                if (i2 >= zzaArr2.length) {
                    break;
                }
                zza zza = zzaArr2[i2];
                if (zza != null) {
                    zzbur.zze(11, zza);
                }
                i2++;
            }
        }
        zzc zzc = this.zzcgr;
        if (zzc != null) {
            zzbur.zze(12, zzc);
        }
        zzj zzj = this.zzcgs;
        if (zzj != null) {
            zzbur.zze(13, zzj);
        }
        zzi zzi = this.zzcgt;
        if (zzi != null) {
            zzbur.zze(14, zzi);
        }
        zzf zzf = this.zzcgu;
        if (zzf != null) {
            zzbur.zze(15, zzf);
        }
        zzg zzg = this.zzcgv;
        if (zzg != null) {
            zzbur.zze(16, zzg);
        }
        zzvr[] zzvrArr = this.zzcgw;
        if (zzvrArr != null && zzvrArr.length > 0) {
            while (true) {
                zzvr[] zzvrArr2 = this.zzcgw;
                if (i >= zzvrArr2.length) {
                    break;
                }
                zzvr zzvr = zzvrArr2[i];
                if (zzvr != null) {
                    zzbur.zza(17, (zzbuz) zzvr);
                }
                i++;
            }
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt();
        Integer num = this.zzcgm;
        if (num != null) {
            zzt += zzbur.zzz(7, num.intValue());
        }
        zzvc zzvc = this.zzcgn;
        if (!(zzvc == null || zzvc == null)) {
            zzt += zzbur.zzz(8, zzvc.zzom());
        }
        zzb zzb = this.zzcgo;
        if (zzb != null) {
            zzt += zzbqk.zzc(9, (zzbsl) zzb);
        }
        zzvm zzvm = this.zzcgp;
        if (zzvm != null) {
            zzt += zzbur.zzb(10, (zzbuz) zzvm);
        }
        zza[] zzaArr = this.zzcgq;
        int i = 0;
        if (zzaArr != null && zzaArr.length > 0) {
            int i2 = zzt;
            int i3 = 0;
            while (true) {
                zza[] zzaArr2 = this.zzcgq;
                if (i3 >= zzaArr2.length) {
                    break;
                }
                zza zza = zzaArr2[i3];
                if (zza != null) {
                    i2 += zzbqk.zzc(11, (zzbsl) zza);
                }
                i3++;
            }
            zzt = i2;
        }
        zzc zzc = this.zzcgr;
        if (zzc != null) {
            zzt += zzbqk.zzc(12, (zzbsl) zzc);
        }
        zzj zzj = this.zzcgs;
        if (zzj != null) {
            zzt += zzbqk.zzc(13, (zzbsl) zzj);
        }
        zzi zzi = this.zzcgt;
        if (zzi != null) {
            zzt += zzbqk.zzc(14, (zzbsl) zzi);
        }
        zzf zzf = this.zzcgu;
        if (zzf != null) {
            zzt += zzbqk.zzc(15, (zzbsl) zzf);
        }
        zzg zzg = this.zzcgv;
        if (zzg != null) {
            zzt += zzbqk.zzc(16, (zzbsl) zzg);
        }
        zzvr[] zzvrArr = this.zzcgw;
        if (zzvrArr != null && zzvrArr.length > 0) {
            while (true) {
                zzvr[] zzvrArr2 = this.zzcgw;
                if (i >= zzvrArr2.length) {
                    break;
                }
                zzvr zzvr = zzvrArr2[i];
                if (zzvr != null) {
                    zzt += zzbur.zzb(17, (zzbuz) zzvr);
                }
                i++;
            }
        }
        return zzt;
    }

    /* access modifiers changed from: private */
    /* renamed from: zze */
    public final zzvl zza(zzbuq zzbuq) throws IOException {
        int zzalm;
        while (true) {
            int zzaku = zzbuq.zzaku();
            switch (zzaku) {
                case 0:
                    return this;
                case 56:
                    try {
                        zzalm = zzbuq.zzalm();
                        if (zzalm >= 0 && zzalm <= 9) {
                            this.zzcgm = Integer.valueOf(zzalm);
                            break;
                        } else {
                            StringBuilder sb = new StringBuilder(43);
                            sb.append(zzalm);
                            sb.append(" is not a valid enum AdInitiater");
                            break;
                        }
                    } catch (IllegalArgumentException unused) {
                        zzbuq.zzgc(zzbuq.getPosition());
                        zza(zzbuq, zzaku);
                        break;
                    }
                case 64:
                    int position = zzbuq.getPosition();
                    int zzalm2 = zzbuq.zzalm();
                    if (zzalm2 != 1000) {
                        switch (zzalm2) {
                            case 0:
                            case 1:
                                break;
                            default:
                                zzbuq.zzgc(position);
                                zza(zzbuq, zzaku);
                                continue;
                        }
                    }
                    this.zzcgn = zzvc.zzcd(zzalm2);
                    break;
                case 74:
                    this.zzcgo = (zzb) zzbuq.zza(zzb.zzon());
                    break;
                case 82:
                    if (this.zzcgp == null) {
                        this.zzcgp = new zzvm();
                    }
                    zzbuq.zza((zzbuz) this.zzcgp);
                    break;
                case 90:
                    int zzb = zzbvc.zzb(zzbuq, 90);
                    zza[] zzaArr = this.zzcgq;
                    int length = zzaArr == null ? 0 : zzaArr.length;
                    zza[] zzaArr2 = new zza[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzcgq, 0, zzaArr2, 0, length);
                    }
                    while (length < zzaArr2.length - 1) {
                        zzaArr2[length] = (zza) zzbuq.zza(zza.zzon());
                        zzbuq.zzaku();
                        length++;
                    }
                    zzaArr2[length] = (zza) zzbuq.zza(zza.zzon());
                    this.zzcgq = zzaArr2;
                    break;
                case 98:
                    this.zzcgr = (zzc) zzbuq.zza(zzc.zzon());
                    break;
                case 106:
                    this.zzcgs = (zzj) zzbuq.zza(zzj.zzon());
                    break;
                case 114:
                    this.zzcgt = (zzi) zzbuq.zza(zzi.zzon());
                    break;
                case 122:
                    this.zzcgu = (zzf) zzbuq.zza(zzf.zzon());
                    break;
                case 130:
                    this.zzcgv = (zzg) zzbuq.zza(zzg.zzon());
                    break;
                case 138:
                    int zzb2 = zzbvc.zzb(zzbuq, 138);
                    zzvr[] zzvrArr = this.zzcgw;
                    int length2 = zzvrArr == null ? 0 : zzvrArr.length;
                    zzvr[] zzvrArr2 = new zzvr[(zzb2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzcgw, 0, zzvrArr2, 0, length2);
                    }
                    while (length2 < zzvrArr2.length - 1) {
                        zzvrArr2[length2] = new zzvr();
                        zzbuq.zza((zzbuz) zzvrArr2[length2]);
                        zzbuq.zzaku();
                        length2++;
                    }
                    zzvrArr2[length2] = new zzvr();
                    zzbuq.zza((zzbuz) zzvrArr2[length2]);
                    this.zzcgw = zzvrArr2;
                    break;
                default:
                    if (super.zza(zzbuq, zzaku)) {
                        break;
                    } else {
                        return this;
                    }
            }
        }
        StringBuilder sb2 = new StringBuilder(43);
        sb2.append(zzalm);
        sb2.append(" is not a valid enum AdInitiater");
        throw new IllegalArgumentException(sb2.toString());
    }
}
