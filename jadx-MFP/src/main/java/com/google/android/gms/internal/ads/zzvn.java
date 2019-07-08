package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzuw.zzn;
import java.io.IOException;

public final class zzvn extends zzbut<zzvn> {
    public String zzchc;
    private zzn zzchd;
    private Integer zzche;
    public zzvq zzchf;
    private Integer zzchg;
    private zzvc zzchh;
    private zzvc zzchi;
    private zzvc zzchj;

    public zzvn() {
        this.zzchc = null;
        this.zzchd = null;
        this.zzche = null;
        this.zzchf = null;
        this.zzchg = null;
        this.zzchh = null;
        this.zzchi = null;
        this.zzchj = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        String str = this.zzchc;
        if (str != null) {
            zzbur.zzf(1, str);
        }
        zzn zzn = this.zzchd;
        if (zzn != null) {
            zzbur.zze(2, zzn);
        }
        Integer num = this.zzche;
        if (num != null) {
            zzbur.zzv(3, num.intValue());
        }
        zzvq zzvq = this.zzchf;
        if (zzvq != null) {
            zzbur.zza(4, (zzbuz) zzvq);
        }
        Integer num2 = this.zzchg;
        if (num2 != null) {
            zzbur.zzv(5, num2.intValue());
        }
        zzvc zzvc = this.zzchh;
        if (!(zzvc == null || zzvc == null)) {
            zzbur.zzv(6, zzvc.zzom());
        }
        zzvc zzvc2 = this.zzchi;
        if (!(zzvc2 == null || zzvc2 == null)) {
            zzbur.zzv(7, zzvc2.zzom());
        }
        zzvc zzvc3 = this.zzchj;
        if (!(zzvc3 == null || zzvc3 == null)) {
            zzbur.zzv(8, zzvc3.zzom());
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt();
        String str = this.zzchc;
        if (str != null) {
            zzt += zzbur.zzg(1, str);
        }
        zzn zzn = this.zzchd;
        if (zzn != null) {
            zzt += zzbqk.zzc(2, (zzbsl) zzn);
        }
        Integer num = this.zzche;
        if (num != null) {
            zzt += zzbur.zzz(3, num.intValue());
        }
        zzvq zzvq = this.zzchf;
        if (zzvq != null) {
            zzt += zzbur.zzb(4, (zzbuz) zzvq);
        }
        Integer num2 = this.zzchg;
        if (num2 != null) {
            zzt += zzbur.zzz(5, num2.intValue());
        }
        zzvc zzvc = this.zzchh;
        if (!(zzvc == null || zzvc == null)) {
            zzt += zzbur.zzz(6, zzvc.zzom());
        }
        zzvc zzvc2 = this.zzchi;
        if (!(zzvc2 == null || zzvc2 == null)) {
            zzt += zzbur.zzz(7, zzvc2.zzom());
        }
        zzvc zzvc3 = this.zzchj;
        return (zzvc3 == null || zzvc3 == null) ? zzt : zzt + zzbur.zzz(8, zzvc3.zzom());
    }

    public final /* synthetic */ zzbuz zza(zzbuq zzbuq) throws IOException {
        while (true) {
            int zzaku = zzbuq.zzaku();
            if (zzaku == 0) {
                return this;
            }
            if (zzaku == 10) {
                this.zzchc = zzbuq.readString();
            } else if (zzaku == 18) {
                this.zzchd = (zzn) zzbuq.zza(zzn.zzon());
            } else if (zzaku == 24) {
                this.zzche = Integer.valueOf(zzbuq.zzalm());
            } else if (zzaku == 34) {
                if (this.zzchf == null) {
                    this.zzchf = new zzvq();
                }
                zzbuq.zza((zzbuz) this.zzchf);
            } else if (zzaku == 40) {
                this.zzchg = Integer.valueOf(zzbuq.zzalm());
            } else if (zzaku == 48) {
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
                this.zzchh = zzvc.zzcd(zzalm);
            } else if (zzaku == 56) {
                int position2 = zzbuq.getPosition();
                int zzalm2 = zzbuq.zzalm();
                if (zzalm2 != 1000) {
                    switch (zzalm2) {
                        case 0:
                        case 1:
                            break;
                        default:
                            zzbuq.zzgc(position2);
                            zza(zzbuq, zzaku);
                            continue;
                    }
                }
                this.zzchi = zzvc.zzcd(zzalm2);
            } else if (zzaku == 64) {
                int position3 = zzbuq.getPosition();
                int zzalm3 = zzbuq.zzalm();
                if (zzalm3 != 1000) {
                    switch (zzalm3) {
                        case 0:
                        case 1:
                            break;
                        default:
                            zzbuq.zzgc(position3);
                            zza(zzbuq, zzaku);
                            continue;
                    }
                }
                this.zzchj = zzvc.zzcd(zzalm3);
            } else if (!super.zza(zzbuq, zzaku)) {
                return this;
            }
        }
    }
}
