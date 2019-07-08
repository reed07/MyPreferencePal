package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzuw.zzn;
import com.google.android.gms.internal.ads.zzuw.zzp;
import com.google.android.gms.internal.ads.zzuw.zzr;
import com.google.android.gms.internal.ads.zzuw.zzs;
import com.google.android.gms.internal.ads.zzuw.zzt;
import com.google.android.gms.internal.ads.zzuw.zzu;
import com.google.android.gms.internal.ads.zzuw.zzv;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import java.io.IOException;

public final class zzvr extends zzbut<zzvr> {
    private static volatile zzvr[] zzcib;
    private zzr zzcic;
    private zzt zzcid;
    private zzu zzcie;
    private zzv zzcif;
    private zzp zzcig;
    private zzs zzcih;
    private zzvs zzcii;
    private Integer zzcij;
    private Integer zzcik;
    private zzn zzcil;
    private Integer zzcim;
    private Integer zzcin;
    private Integer zzcio;
    private Integer zzcip;
    private Integer zzciq;
    private Long zzcir;

    public static zzvr[] zzpl() {
        if (zzcib == null) {
            synchronized (zzbux.zzfws) {
                if (zzcib == null) {
                    zzcib = new zzvr[0];
                }
            }
        }
        return zzcib;
    }

    public zzvr() {
        this.zzcic = null;
        this.zzcid = null;
        this.zzcie = null;
        this.zzcif = null;
        this.zzcig = null;
        this.zzcih = null;
        this.zzcii = null;
        this.zzcij = null;
        this.zzcik = null;
        this.zzcil = null;
        this.zzcim = null;
        this.zzcin = null;
        this.zzcio = null;
        this.zzcip = null;
        this.zzciq = null;
        this.zzcir = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        zzr zzr = this.zzcic;
        if (zzr != null) {
            zzbur.zze(5, zzr);
        }
        zzt zzt = this.zzcid;
        if (zzt != null) {
            zzbur.zze(6, zzt);
        }
        zzu zzu = this.zzcie;
        if (zzu != null) {
            zzbur.zze(7, zzu);
        }
        zzv zzv = this.zzcif;
        if (zzv != null) {
            zzbur.zze(8, zzv);
        }
        zzp zzp = this.zzcig;
        if (zzp != null) {
            zzbur.zze(9, zzp);
        }
        zzs zzs = this.zzcih;
        if (zzs != null) {
            zzbur.zze(10, zzs);
        }
        zzvs zzvs = this.zzcii;
        if (zzvs != null) {
            zzbur.zza(11, (zzbuz) zzvs);
        }
        Integer num = this.zzcij;
        if (num != null) {
            zzbur.zzv(12, num.intValue());
        }
        Integer num2 = this.zzcik;
        if (num2 != null) {
            zzbur.zzv(13, num2.intValue());
        }
        zzn zzn = this.zzcil;
        if (zzn != null) {
            zzbur.zze(14, zzn);
        }
        Integer num3 = this.zzcim;
        if (num3 != null) {
            zzbur.zzv(15, num3.intValue());
        }
        Integer num4 = this.zzcin;
        if (num4 != null) {
            zzbur.zzv(16, num4.intValue());
        }
        Integer num5 = this.zzcio;
        if (num5 != null) {
            zzbur.zzv(17, num5.intValue());
        }
        Integer num6 = this.zzcip;
        if (num6 != null) {
            zzbur.zzv(18, num6.intValue());
        }
        Integer num7 = this.zzciq;
        if (num7 != null) {
            zzbur.zzv(19, num7.intValue());
        }
        Long l = this.zzcir;
        if (l != null) {
            zzbur.zzj(20, l.longValue());
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt();
        zzr zzr = this.zzcic;
        if (zzr != null) {
            zzt += zzbqk.zzc(5, (zzbsl) zzr);
        }
        zzt zzt2 = this.zzcid;
        if (zzt2 != null) {
            zzt += zzbqk.zzc(6, (zzbsl) zzt2);
        }
        zzu zzu = this.zzcie;
        if (zzu != null) {
            zzt += zzbqk.zzc(7, (zzbsl) zzu);
        }
        zzv zzv = this.zzcif;
        if (zzv != null) {
            zzt += zzbqk.zzc(8, (zzbsl) zzv);
        }
        zzp zzp = this.zzcig;
        if (zzp != null) {
            zzt += zzbqk.zzc(9, (zzbsl) zzp);
        }
        zzs zzs = this.zzcih;
        if (zzs != null) {
            zzt += zzbqk.zzc(10, (zzbsl) zzs);
        }
        zzvs zzvs = this.zzcii;
        if (zzvs != null) {
            zzt += zzbur.zzb(11, (zzbuz) zzvs);
        }
        Integer num = this.zzcij;
        if (num != null) {
            zzt += zzbur.zzz(12, num.intValue());
        }
        Integer num2 = this.zzcik;
        if (num2 != null) {
            zzt += zzbur.zzz(13, num2.intValue());
        }
        zzn zzn = this.zzcil;
        if (zzn != null) {
            zzt += zzbqk.zzc(14, (zzbsl) zzn);
        }
        Integer num3 = this.zzcim;
        if (num3 != null) {
            zzt += zzbur.zzz(15, num3.intValue());
        }
        Integer num4 = this.zzcin;
        if (num4 != null) {
            zzt += zzbur.zzz(16, num4.intValue());
        }
        Integer num5 = this.zzcio;
        if (num5 != null) {
            zzt += zzbur.zzz(17, num5.intValue());
        }
        Integer num6 = this.zzcip;
        if (num6 != null) {
            zzt += zzbur.zzz(18, num6.intValue());
        }
        Integer num7 = this.zzciq;
        if (num7 != null) {
            zzt += zzbur.zzz(19, num7.intValue());
        }
        Long l = this.zzcir;
        return l != null ? zzt + zzbur.zzn(20, l.longValue()) : zzt;
    }

    public final /* synthetic */ zzbuz zza(zzbuq zzbuq) throws IOException {
        while (true) {
            int zzaku = zzbuq.zzaku();
            switch (zzaku) {
                case 0:
                    return this;
                case 42:
                    this.zzcic = (zzr) zzbuq.zza(zzr.zzon());
                    break;
                case 50:
                    this.zzcid = (zzt) zzbuq.zza(zzt.zzon());
                    break;
                case 58:
                    this.zzcie = (zzu) zzbuq.zza(zzu.zzon());
                    break;
                case 66:
                    this.zzcif = (zzv) zzbuq.zza(zzv.zzon());
                    break;
                case 74:
                    this.zzcig = (zzp) zzbuq.zza(zzp.zzon());
                    break;
                case 82:
                    this.zzcih = (zzs) zzbuq.zza(zzs.zzon());
                    break;
                case 90:
                    if (this.zzcii == null) {
                        this.zzcii = new zzvs();
                    }
                    zzbuq.zza((zzbuz) this.zzcii);
                    break;
                case 96:
                    this.zzcij = Integer.valueOf(zzbuq.zzalm());
                    break;
                case 104:
                    this.zzcik = Integer.valueOf(zzbuq.zzalm());
                    break;
                case 114:
                    this.zzcil = (zzn) zzbuq.zza(zzn.zzon());
                    break;
                case 120:
                    this.zzcim = Integer.valueOf(zzbuq.zzalm());
                    break;
                case 128:
                    this.zzcin = Integer.valueOf(zzbuq.zzalm());
                    break;
                case 136:
                    this.zzcio = Integer.valueOf(zzbuq.zzalm());
                    break;
                case 144:
                    this.zzcip = Integer.valueOf(zzbuq.zzalm());
                    break;
                case PacketTypes.EmailUniquenessCheckResponse /*152*/:
                    this.zzciq = Integer.valueOf(zzbuq.zzalm());
                    break;
                case 160:
                    this.zzcir = Long.valueOf(zzbuq.zzaln());
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
