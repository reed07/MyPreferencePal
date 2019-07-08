package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbvu extends zzbut<zzbvu> {
    public String zzgcc;
    public Long zzgcd;
    public Boolean zzgce;

    public zzbvu() {
        this.zzgcc = null;
        this.zzgcd = null;
        this.zzgce = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        String str = this.zzgcc;
        if (str != null) {
            zzbur.zzf(1, str);
        }
        Long l = this.zzgcd;
        if (l != null) {
            zzbur.zzr(2, l.longValue());
        }
        Boolean bool = this.zzgce;
        if (bool != null) {
            zzbur.zzj(3, bool.booleanValue());
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt();
        String str = this.zzgcc;
        if (str != null) {
            zzt += zzbur.zzg(1, str);
        }
        Long l = this.zzgcd;
        if (l != null) {
            zzt += zzbur.zzm(2, l.longValue());
        }
        Boolean bool = this.zzgce;
        if (bool == null) {
            return zzt;
        }
        bool.booleanValue();
        return zzt + zzbur.zzfd(3) + 1;
    }

    public final /* synthetic */ zzbuz zza(zzbuq zzbuq) throws IOException {
        while (true) {
            int zzaku = zzbuq.zzaku();
            if (zzaku == 0) {
                return this;
            }
            if (zzaku == 10) {
                this.zzgcc = zzbuq.readString();
            } else if (zzaku == 16) {
                this.zzgcd = Long.valueOf(zzbuq.zzakw());
            } else if (zzaku == 24) {
                this.zzgce = Boolean.valueOf(zzbuq.zzala());
            } else if (!super.zza(zzbuq, zzaku)) {
                return this;
            }
        }
    }
}
