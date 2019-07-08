package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzuw.zzh;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import java.io.IOException;

public final class zzvp extends zzbut<zzvp> {
    private Integer zzcho;
    public String zzchp;
    private Integer zzchq;
    private zzvc zzchr;
    private zzvq zzchs;
    public long[] zzcht;
    public zzvn zzchu;
    private zzvo zzchv;
    private zzh zzchw;
    public zzvl zzchx;

    public zzvp() {
        this.zzcho = null;
        this.zzchp = null;
        this.zzchq = null;
        this.zzchr = null;
        this.zzchs = null;
        this.zzcht = zzbvc.zzfwy;
        this.zzchu = null;
        this.zzchv = null;
        this.zzchw = null;
        this.zzchx = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        Integer num = this.zzcho;
        if (num != null) {
            zzbur.zzv(9, num.intValue());
        }
        String str = this.zzchp;
        if (str != null) {
            zzbur.zzf(10, str);
        }
        Integer num2 = this.zzchq;
        int i = 0;
        if (num2 != null) {
            int intValue = num2.intValue();
            zzbur.zzu(11, 0);
            zzbur.zzge(intValue);
        }
        zzvc zzvc = this.zzchr;
        if (!(zzvc == null || zzvc == null)) {
            zzbur.zzv(12, zzvc.zzom());
        }
        zzvq zzvq = this.zzchs;
        if (zzvq != null) {
            zzbur.zza(13, (zzbuz) zzvq);
        }
        long[] jArr = this.zzcht;
        if (jArr != null && jArr.length > 0) {
            while (true) {
                long[] jArr2 = this.zzcht;
                if (i >= jArr2.length) {
                    break;
                }
                zzbur.zzj(14, jArr2[i]);
                i++;
            }
        }
        zzvn zzvn = this.zzchu;
        if (zzvn != null) {
            zzbur.zza(15, (zzbuz) zzvn);
        }
        zzvo zzvo = this.zzchv;
        if (zzvo != null) {
            zzbur.zza(16, (zzbuz) zzvo);
        }
        zzh zzh = this.zzchw;
        if (zzh != null) {
            zzbur.zze(17, zzh);
        }
        zzvl zzvl = this.zzchx;
        if (zzvl != null) {
            zzbur.zza(18, (zzbuz) zzvl);
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        long[] jArr;
        int zzt = super.zzt();
        Integer num = this.zzcho;
        if (num != null) {
            zzt += zzbur.zzz(9, num.intValue());
        }
        String str = this.zzchp;
        if (str != null) {
            zzt += zzbur.zzg(10, str);
        }
        Integer num2 = this.zzchq;
        if (num2 != null) {
            zzt += zzbur.zzfd(11) + zzbur.zzfl(num2.intValue());
        }
        zzvc zzvc = this.zzchr;
        if (!(zzvc == null || zzvc == null)) {
            zzt += zzbur.zzz(12, zzvc.zzom());
        }
        zzvq zzvq = this.zzchs;
        if (zzvq != null) {
            zzt += zzbur.zzb(13, (zzbuz) zzvq);
        }
        long[] jArr2 = this.zzcht;
        if (jArr2 != null && jArr2.length > 0) {
            int i = 0;
            int i2 = 0;
            while (true) {
                jArr = this.zzcht;
                if (i >= jArr.length) {
                    break;
                }
                i2 += zzbur.zzbl(jArr[i]);
                i++;
            }
            zzt = zzt + i2 + (jArr.length * 1);
        }
        zzvn zzvn = this.zzchu;
        if (zzvn != null) {
            zzt += zzbur.zzb(15, (zzbuz) zzvn);
        }
        zzvo zzvo = this.zzchv;
        if (zzvo != null) {
            zzt += zzbur.zzb(16, (zzbuz) zzvo);
        }
        zzh zzh = this.zzchw;
        if (zzh != null) {
            zzt += zzbqk.zzc(17, (zzbsl) zzh);
        }
        zzvl zzvl = this.zzchx;
        return zzvl != null ? zzt + zzbur.zzb(18, (zzbuz) zzvl) : zzt;
    }

    public final /* synthetic */ zzbuz zza(zzbuq zzbuq) throws IOException {
        while (true) {
            int zzaku = zzbuq.zzaku();
            switch (zzaku) {
                case 0:
                    return this;
                case 72:
                    this.zzcho = Integer.valueOf(zzbuq.zzalm());
                    break;
                case 82:
                    this.zzchp = zzbuq.readString();
                    break;
                case 88:
                    this.zzchq = Integer.valueOf(zzbuq.zzalm());
                    break;
                case 96:
                    int position = zzbuq.getPosition();
                    int zzalm = zzbuq.zzalm();
                    if (zzalm != 1000) {
                        switch (zzalm) {
                            case 0:
                            case 1:
                                break;
                            default:
                                zzbuq.zzgc(position);
                                zza(zzbuq, zzaku);
                                continue;
                        }
                    }
                    this.zzchr = zzvc.zzcd(zzalm);
                    break;
                case 106:
                    if (this.zzchs == null) {
                        this.zzchs = new zzvq();
                    }
                    zzbuq.zza((zzbuz) this.zzchs);
                    break;
                case 112:
                    int zzb = zzbvc.zzb(zzbuq, 112);
                    long[] jArr = this.zzcht;
                    int length = jArr == null ? 0 : jArr.length;
                    long[] jArr2 = new long[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzcht, 0, jArr2, 0, length);
                    }
                    while (length < jArr2.length - 1) {
                        jArr2[length] = zzbuq.zzaln();
                        zzbuq.zzaku();
                        length++;
                    }
                    jArr2[length] = zzbuq.zzaln();
                    this.zzcht = jArr2;
                    break;
                case 114:
                    int zzer = zzbuq.zzer(zzbuq.zzalm());
                    int position2 = zzbuq.getPosition();
                    int i = 0;
                    while (zzbuq.zzapl() > 0) {
                        zzbuq.zzaln();
                        i++;
                    }
                    zzbuq.zzgc(position2);
                    long[] jArr3 = this.zzcht;
                    int length2 = jArr3 == null ? 0 : jArr3.length;
                    long[] jArr4 = new long[(i + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzcht, 0, jArr4, 0, length2);
                    }
                    while (length2 < jArr4.length) {
                        jArr4[length2] = zzbuq.zzaln();
                        length2++;
                    }
                    this.zzcht = jArr4;
                    zzbuq.zzes(zzer);
                    break;
                case 122:
                    if (this.zzchu == null) {
                        this.zzchu = new zzvn();
                    }
                    zzbuq.zza((zzbuz) this.zzchu);
                    break;
                case 130:
                    if (this.zzchv == null) {
                        this.zzchv = new zzvo();
                    }
                    zzbuq.zza((zzbuz) this.zzchv);
                    break;
                case 138:
                    this.zzchw = (zzh) zzbuq.zza(zzh.zzon());
                    break;
                case PacketTypes.SuggestUsername /*146*/:
                    if (this.zzchx == null) {
                        this.zzchx = new zzvl();
                    }
                    zzbuq.zza((zzbuz) this.zzchx);
                    break;
                default:
                    if (super.zza(zzbuq, zzaku)) {
                        break;
                    } else {
                        return this;
                    }
            }
        }
    }
}
