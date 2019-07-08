package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbvs extends zzbut<zzbvs> {
    public String mimeType;
    public Integer zzgar;
    public byte[] zzgbs;

    public zzbvs() {
        this.zzgar = null;
        this.mimeType = null;
        this.zzgbs = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        Integer num = this.zzgar;
        if (num != null) {
            zzbur.zzv(1, num.intValue());
        }
        String str = this.mimeType;
        if (str != null) {
            zzbur.zzf(2, str);
        }
        byte[] bArr = this.zzgbs;
        if (bArr != null) {
            zzbur.zza(3, bArr);
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt();
        Integer num = this.zzgar;
        if (num != null) {
            zzt += zzbur.zzz(1, num.intValue());
        }
        String str = this.mimeType;
        if (str != null) {
            zzt += zzbur.zzg(2, str);
        }
        byte[] bArr = this.zzgbs;
        return bArr != null ? zzt + zzbur.zzb(3, bArr) : zzt;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzg */
    public final zzbvs zza(zzbuq zzbuq) throws IOException {
        int zzakx;
        while (true) {
            int zzaku = zzbuq.zzaku();
            if (zzaku == 0) {
                return this;
            }
            if (zzaku == 8) {
                try {
                    zzakx = zzbuq.zzakx();
                    if (zzakx < 0 || zzakx > 1) {
                        StringBuilder sb = new StringBuilder(36);
                        sb.append(zzakx);
                        sb.append(" is not a valid enum Type");
                    } else {
                        this.zzgar = Integer.valueOf(zzakx);
                    }
                } catch (IllegalArgumentException unused) {
                    zzbuq.zzgc(zzbuq.getPosition());
                    zza(zzbuq, zzaku);
                }
            } else if (zzaku == 18) {
                this.mimeType = zzbuq.readString();
            } else if (zzaku == 26) {
                this.zzgbs = zzbuq.readBytes();
            } else if (!super.zza(zzbuq, zzaku)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(36);
        sb2.append(zzakx);
        sb2.append(" is not a valid enum Type");
        throw new IllegalArgumentException(sb2.toString());
    }
}
