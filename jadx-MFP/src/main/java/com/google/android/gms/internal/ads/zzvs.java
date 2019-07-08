package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzuw.zzn;
import com.google.android.gms.internal.ads.zzuw.zzq;
import java.io.IOException;

public final class zzvs extends zzbut<zzvs> {
    private zzvq zzcis;
    private zzvc zzcit;
    private zzq zzciu;
    private zzn zzciv;

    public zzvs() {
        this.zzcis = null;
        this.zzcit = null;
        this.zzciu = null;
        this.zzciv = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        zzvq zzvq = this.zzcis;
        if (zzvq != null) {
            zzbur.zza(1, (zzbuz) zzvq);
        }
        zzvc zzvc = this.zzcit;
        if (!(zzvc == null || zzvc == null)) {
            zzbur.zzv(2, zzvc.zzom());
        }
        zzq zzq = this.zzciu;
        if (zzq != null) {
            zzbur.zze(3, zzq);
        }
        zzn zzn = this.zzciv;
        if (zzn != null) {
            zzbur.zze(4, zzn);
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt();
        zzvq zzvq = this.zzcis;
        if (zzvq != null) {
            zzt += zzbur.zzb(1, (zzbuz) zzvq);
        }
        zzvc zzvc = this.zzcit;
        if (!(zzvc == null || zzvc == null)) {
            zzt += zzbur.zzz(2, zzvc.zzom());
        }
        zzq zzq = this.zzciu;
        if (zzq != null) {
            zzt += zzbqk.zzc(3, (zzbsl) zzq);
        }
        zzn zzn = this.zzciv;
        return zzn != null ? zzt + zzbqk.zzc(4, (zzbsl) zzn) : zzt;
    }

    public final /* synthetic */ zzbuz zza(zzbuq zzbuq) throws IOException {
        while (true) {
            int zzaku = zzbuq.zzaku();
            if (zzaku == 0) {
                return this;
            }
            if (zzaku == 10) {
                if (this.zzcis == null) {
                    this.zzcis = new zzvq();
                }
                zzbuq.zza((zzbuz) this.zzcis);
            } else if (zzaku == 16) {
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
                this.zzcit = zzvc.zzcd(zzalm);
            } else if (zzaku == 26) {
                this.zzciu = (zzq) zzbuq.zza(zzq.zzon());
            } else if (zzaku == 34) {
                this.zzciv = (zzn) zzbuq.zza(zzn.zzon());
            } else if (!super.zza(zzbuq, zzaku)) {
                return this;
            }
        }
    }
}
