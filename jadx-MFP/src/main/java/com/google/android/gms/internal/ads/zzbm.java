package com.google.android.gms.internal.ads;

import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import java.io.IOException;

public final class zzbm extends zzbut<zzbm> {
    private static volatile zzbm[] zzgk;
    public Long zzei;
    public Long zzej;
    public Long zzgl;
    public Long zzgm;
    public Long zzgn;
    public Long zzgo;
    public Integer zzgp;
    public Long zzgq;
    public Long zzgr;
    public Long zzgs;
    public Integer zzgt;
    public Long zzgu;
    public Long zzgv;
    public Long zzgw;
    public Long zzgx;
    public Long zzgy;
    public Long zzgz;
    public Long zzha;
    public Long zzhb;
    private Long zzhc;
    private Long zzhd;

    public static zzbm[] zzu() {
        if (zzgk == null) {
            synchronized (zzbux.zzfws) {
                if (zzgk == null) {
                    zzgk = new zzbm[0];
                }
            }
        }
        return zzgk;
    }

    public zzbm() {
        this.zzei = null;
        this.zzej = null;
        this.zzgl = null;
        this.zzgm = null;
        this.zzgn = null;
        this.zzgo = null;
        this.zzgq = null;
        this.zzgr = null;
        this.zzgs = null;
        this.zzgu = null;
        this.zzgv = null;
        this.zzgw = null;
        this.zzgx = null;
        this.zzgy = null;
        this.zzgz = null;
        this.zzha = null;
        this.zzhb = null;
        this.zzhc = null;
        this.zzhd = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        Long l = this.zzei;
        if (l != null) {
            zzbur.zzr(1, l.longValue());
        }
        Long l2 = this.zzej;
        if (l2 != null) {
            zzbur.zzr(2, l2.longValue());
        }
        Long l3 = this.zzgl;
        if (l3 != null) {
            zzbur.zzr(3, l3.longValue());
        }
        Long l4 = this.zzgm;
        if (l4 != null) {
            zzbur.zzr(4, l4.longValue());
        }
        Long l5 = this.zzgn;
        if (l5 != null) {
            zzbur.zzr(5, l5.longValue());
        }
        Long l6 = this.zzgo;
        if (l6 != null) {
            zzbur.zzr(6, l6.longValue());
        }
        Integer num = this.zzgp;
        if (num != null) {
            zzbur.zzv(7, num.intValue());
        }
        Long l7 = this.zzgq;
        if (l7 != null) {
            zzbur.zzr(8, l7.longValue());
        }
        Long l8 = this.zzgr;
        if (l8 != null) {
            zzbur.zzr(9, l8.longValue());
        }
        Long l9 = this.zzgs;
        if (l9 != null) {
            zzbur.zzr(10, l9.longValue());
        }
        Integer num2 = this.zzgt;
        if (num2 != null) {
            zzbur.zzv(11, num2.intValue());
        }
        Long l10 = this.zzgu;
        if (l10 != null) {
            zzbur.zzr(12, l10.longValue());
        }
        Long l11 = this.zzgv;
        if (l11 != null) {
            zzbur.zzr(13, l11.longValue());
        }
        Long l12 = this.zzgw;
        if (l12 != null) {
            zzbur.zzr(14, l12.longValue());
        }
        Long l13 = this.zzgx;
        if (l13 != null) {
            zzbur.zzr(15, l13.longValue());
        }
        Long l14 = this.zzgy;
        if (l14 != null) {
            zzbur.zzr(16, l14.longValue());
        }
        Long l15 = this.zzgz;
        if (l15 != null) {
            zzbur.zzr(17, l15.longValue());
        }
        Long l16 = this.zzha;
        if (l16 != null) {
            zzbur.zzr(18, l16.longValue());
        }
        Long l17 = this.zzhb;
        if (l17 != null) {
            zzbur.zzr(19, l17.longValue());
        }
        Long l18 = this.zzhc;
        if (l18 != null) {
            zzbur.zzr(20, l18.longValue());
        }
        Long l19 = this.zzhd;
        if (l19 != null) {
            zzbur.zzr(21, l19.longValue());
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt();
        Long l = this.zzei;
        if (l != null) {
            zzt += zzbur.zzm(1, l.longValue());
        }
        Long l2 = this.zzej;
        if (l2 != null) {
            zzt += zzbur.zzm(2, l2.longValue());
        }
        Long l3 = this.zzgl;
        if (l3 != null) {
            zzt += zzbur.zzm(3, l3.longValue());
        }
        Long l4 = this.zzgm;
        if (l4 != null) {
            zzt += zzbur.zzm(4, l4.longValue());
        }
        Long l5 = this.zzgn;
        if (l5 != null) {
            zzt += zzbur.zzm(5, l5.longValue());
        }
        Long l6 = this.zzgo;
        if (l6 != null) {
            zzt += zzbur.zzm(6, l6.longValue());
        }
        Integer num = this.zzgp;
        if (num != null) {
            zzt += zzbur.zzz(7, num.intValue());
        }
        Long l7 = this.zzgq;
        if (l7 != null) {
            zzt += zzbur.zzm(8, l7.longValue());
        }
        Long l8 = this.zzgr;
        if (l8 != null) {
            zzt += zzbur.zzm(9, l8.longValue());
        }
        Long l9 = this.zzgs;
        if (l9 != null) {
            zzt += zzbur.zzm(10, l9.longValue());
        }
        Integer num2 = this.zzgt;
        if (num2 != null) {
            zzt += zzbur.zzz(11, num2.intValue());
        }
        Long l10 = this.zzgu;
        if (l10 != null) {
            zzt += zzbur.zzm(12, l10.longValue());
        }
        Long l11 = this.zzgv;
        if (l11 != null) {
            zzt += zzbur.zzm(13, l11.longValue());
        }
        Long l12 = this.zzgw;
        if (l12 != null) {
            zzt += zzbur.zzm(14, l12.longValue());
        }
        Long l13 = this.zzgx;
        if (l13 != null) {
            zzt += zzbur.zzm(15, l13.longValue());
        }
        Long l14 = this.zzgy;
        if (l14 != null) {
            zzt += zzbur.zzm(16, l14.longValue());
        }
        Long l15 = this.zzgz;
        if (l15 != null) {
            zzt += zzbur.zzm(17, l15.longValue());
        }
        Long l16 = this.zzha;
        if (l16 != null) {
            zzt += zzbur.zzm(18, l16.longValue());
        }
        Long l17 = this.zzhb;
        if (l17 != null) {
            zzt += zzbur.zzm(19, l17.longValue());
        }
        Long l18 = this.zzhc;
        if (l18 != null) {
            zzt += zzbur.zzm(20, l18.longValue());
        }
        Long l19 = this.zzhd;
        return l19 != null ? zzt + zzbur.zzm(21, l19.longValue()) : zzt;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzc */
    public final zzbm zza(zzbuq zzbuq) throws IOException {
        while (true) {
            int zzaku = zzbuq.zzaku();
            switch (zzaku) {
                case 0:
                    return this;
                case 8:
                    this.zzei = Long.valueOf(zzbuq.zzaln());
                    break;
                case 16:
                    this.zzej = Long.valueOf(zzbuq.zzaln());
                    break;
                case 24:
                    this.zzgl = Long.valueOf(zzbuq.zzaln());
                    break;
                case 32:
                    this.zzgm = Long.valueOf(zzbuq.zzaln());
                    break;
                case 40:
                    this.zzgn = Long.valueOf(zzbuq.zzaln());
                    break;
                case 48:
                    this.zzgo = Long.valueOf(zzbuq.zzaln());
                    break;
                case 56:
                    int position = zzbuq.getPosition();
                    try {
                        this.zzgp = Integer.valueOf(zzbk.zzd(zzbuq.zzalm()));
                        break;
                    } catch (IllegalArgumentException unused) {
                        zzbuq.zzgc(position);
                        zza(zzbuq, zzaku);
                        break;
                    }
                case 64:
                    this.zzgq = Long.valueOf(zzbuq.zzaln());
                    break;
                case 72:
                    this.zzgr = Long.valueOf(zzbuq.zzaln());
                    break;
                case 80:
                    this.zzgs = Long.valueOf(zzbuq.zzaln());
                    break;
                case 88:
                    int position2 = zzbuq.getPosition();
                    try {
                        this.zzgt = Integer.valueOf(zzbk.zzd(zzbuq.zzalm()));
                        break;
                    } catch (IllegalArgumentException unused2) {
                        zzbuq.zzgc(position2);
                        zza(zzbuq, zzaku);
                        break;
                    }
                case 96:
                    this.zzgu = Long.valueOf(zzbuq.zzaln());
                    break;
                case 104:
                    this.zzgv = Long.valueOf(zzbuq.zzaln());
                    break;
                case 112:
                    this.zzgw = Long.valueOf(zzbuq.zzaln());
                    break;
                case 120:
                    this.zzgx = Long.valueOf(zzbuq.zzaln());
                    break;
                case 128:
                    this.zzgy = Long.valueOf(zzbuq.zzaln());
                    break;
                case 136:
                    this.zzgz = Long.valueOf(zzbuq.zzaln());
                    break;
                case 144:
                    this.zzha = Long.valueOf(zzbuq.zzaln());
                    break;
                case PacketTypes.EmailUniquenessCheckResponse /*152*/:
                    this.zzhb = Long.valueOf(zzbuq.zzaln());
                    break;
                case 160:
                    this.zzhc = Long.valueOf(zzbuq.zzaln());
                    break;
                case 168:
                    this.zzhd = Long.valueOf(zzbuq.zzaln());
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
