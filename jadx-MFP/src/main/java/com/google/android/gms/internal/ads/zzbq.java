package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbq extends zzbut<zzbq> {
    public Long zzhh;
    private String zzhp;
    private byte[] zzhq;

    public zzbq() {
        this.zzhh = null;
        this.zzhp = null;
        this.zzhq = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        Long l = this.zzhh;
        if (l != null) {
            zzbur.zzr(1, l.longValue());
        }
        String str = this.zzhp;
        if (str != null) {
            zzbur.zzf(3, str);
        }
        byte[] bArr = this.zzhq;
        if (bArr != null) {
            zzbur.zza(4, bArr);
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt();
        Long l = this.zzhh;
        if (l != null) {
            zzt += zzbur.zzm(1, l.longValue());
        }
        String str = this.zzhp;
        if (str != null) {
            zzt += zzbur.zzg(3, str);
        }
        byte[] bArr = this.zzhq;
        return bArr != null ? zzt + zzbur.zzb(4, bArr) : zzt;
    }

    public final /* synthetic */ zzbuz zza(zzbuq zzbuq) throws IOException {
        while (true) {
            int zzaku = zzbuq.zzaku();
            if (zzaku == 0) {
                return this;
            }
            if (zzaku == 8) {
                this.zzhh = Long.valueOf(zzbuq.zzaln());
            } else if (zzaku == 26) {
                this.zzhp = zzbuq.readString();
            } else if (zzaku == 34) {
                this.zzhq = zzbuq.readBytes();
            } else if (!super.zza(zzbuq, zzaku)) {
                return this;
            }
        }
    }
}
