package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbvp extends zzbut<zzbvp> {
    private static volatile zzbvp[] zzgbi;
    public byte[] zzgbj;
    public byte[] zzgbk;

    public static zzbvp[] zzaqd() {
        if (zzgbi == null) {
            synchronized (zzbux.zzfws) {
                if (zzgbi == null) {
                    zzgbi = new zzbvp[0];
                }
            }
        }
        return zzgbi;
    }

    public zzbvp() {
        this.zzgbj = null;
        this.zzgbk = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        zzbur.zza(1, this.zzgbj);
        byte[] bArr = this.zzgbk;
        if (bArr != null) {
            zzbur.zza(2, bArr);
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt() + zzbur.zzb(1, this.zzgbj);
        byte[] bArr = this.zzgbk;
        return bArr != null ? zzt + zzbur.zzb(2, bArr) : zzt;
    }

    public final /* synthetic */ zzbuz zza(zzbuq zzbuq) throws IOException {
        while (true) {
            int zzaku = zzbuq.zzaku();
            if (zzaku == 0) {
                return this;
            }
            if (zzaku == 10) {
                this.zzgbj = zzbuq.readBytes();
            } else if (zzaku == 18) {
                this.zzgbk = zzbuq.readBytes();
            } else if (!super.zza(zzbuq, zzaku)) {
                return this;
            }
        }
    }
}
