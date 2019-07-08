package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzuw.zzd.zzb;
import java.io.IOException;

public final class zzvo extends zzbut<zzvo> {
    private zzb zzchk;
    private zzvq zzchl;
    private String zzchm;
    private String zzchn;

    public zzvo() {
        this.zzchk = null;
        this.zzchl = null;
        this.zzchm = null;
        this.zzchn = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        zzb zzb = this.zzchk;
        if (!(zzb == null || zzb == null)) {
            zzbur.zzv(5, zzb.zzom());
        }
        zzvq zzvq = this.zzchl;
        if (zzvq != null) {
            zzbur.zza(6, (zzbuz) zzvq);
        }
        String str = this.zzchm;
        if (str != null) {
            zzbur.zzf(7, str);
        }
        String str2 = this.zzchn;
        if (str2 != null) {
            zzbur.zzf(8, str2);
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt();
        zzb zzb = this.zzchk;
        if (!(zzb == null || zzb == null)) {
            zzt += zzbur.zzz(5, zzb.zzom());
        }
        zzvq zzvq = this.zzchl;
        if (zzvq != null) {
            zzt += zzbur.zzb(6, (zzbuz) zzvq);
        }
        String str = this.zzchm;
        if (str != null) {
            zzt += zzbur.zzg(7, str);
        }
        String str2 = this.zzchn;
        return str2 != null ? zzt + zzbur.zzg(8, str2) : zzt;
    }

    public final /* synthetic */ zzbuz zza(zzbuq zzbuq) throws IOException {
        while (true) {
            int zzaku = zzbuq.zzaku();
            if (zzaku != 0) {
                if (zzaku == 40) {
                    int position = zzbuq.getPosition();
                    int zzalm = zzbuq.zzalm();
                    switch (zzalm) {
                        case 0:
                        case 1:
                        case 2:
                            this.zzchk = zzb.zzcc(zzalm);
                            break;
                        default:
                            zzbuq.zzgc(position);
                            zza(zzbuq, zzaku);
                            break;
                    }
                } else if (zzaku == 50) {
                    if (this.zzchl == null) {
                        this.zzchl = new zzvq();
                    }
                    zzbuq.zza((zzbuz) this.zzchl);
                } else if (zzaku == 58) {
                    this.zzchm = zzbuq.readString();
                } else if (zzaku == 66) {
                    this.zzchn = zzbuq.readString();
                } else if (!super.zza(zzbuq, zzaku)) {
                    return this;
                }
            } else {
                return this;
            }
        }
    }
}
