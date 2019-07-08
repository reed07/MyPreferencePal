package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbvd.zzb.zzd.C0028zzb;
import java.io.IOException;

public final class zzbvq extends zzbut<zzbvq> {
    private C0028zzb zzgbl;
    public zzbvp[] zzgbm;
    private byte[] zzgbn;
    private byte[] zzgbo;
    private Integer zzgbp;

    public zzbvq() {
        this.zzgbl = null;
        this.zzgbm = zzbvp.zzaqd();
        this.zzgbn = null;
        this.zzgbo = null;
        this.zzgbp = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        C0028zzb zzb = this.zzgbl;
        if (zzb != null) {
            zzbur.zze(1, zzb);
        }
        zzbvp[] zzbvpArr = this.zzgbm;
        if (zzbvpArr != null && zzbvpArr.length > 0) {
            int i = 0;
            while (true) {
                zzbvp[] zzbvpArr2 = this.zzgbm;
                if (i >= zzbvpArr2.length) {
                    break;
                }
                zzbvp zzbvp = zzbvpArr2[i];
                if (zzbvp != null) {
                    zzbur.zza(2, (zzbuz) zzbvp);
                }
                i++;
            }
        }
        byte[] bArr = this.zzgbn;
        if (bArr != null) {
            zzbur.zza(3, bArr);
        }
        byte[] bArr2 = this.zzgbo;
        if (bArr2 != null) {
            zzbur.zza(4, bArr2);
        }
        Integer num = this.zzgbp;
        if (num != null) {
            zzbur.zzv(5, num.intValue());
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt();
        C0028zzb zzb = this.zzgbl;
        if (zzb != null) {
            zzt += zzbqk.zzc(1, (zzbsl) zzb);
        }
        zzbvp[] zzbvpArr = this.zzgbm;
        if (zzbvpArr != null && zzbvpArr.length > 0) {
            int i = 0;
            while (true) {
                zzbvp[] zzbvpArr2 = this.zzgbm;
                if (i >= zzbvpArr2.length) {
                    break;
                }
                zzbvp zzbvp = zzbvpArr2[i];
                if (zzbvp != null) {
                    zzt += zzbur.zzb(2, (zzbuz) zzbvp);
                }
                i++;
            }
        }
        byte[] bArr = this.zzgbn;
        if (bArr != null) {
            zzt += zzbur.zzb(3, bArr);
        }
        byte[] bArr2 = this.zzgbo;
        if (bArr2 != null) {
            zzt += zzbur.zzb(4, bArr2);
        }
        Integer num = this.zzgbp;
        return num != null ? zzt + zzbur.zzz(5, num.intValue()) : zzt;
    }

    public final /* synthetic */ zzbuz zza(zzbuq zzbuq) throws IOException {
        while (true) {
            int zzaku = zzbuq.zzaku();
            if (zzaku == 0) {
                return this;
            }
            if (zzaku == 10) {
                this.zzgbl = (C0028zzb) zzbuq.zza(C0028zzb.zzon());
            } else if (zzaku == 18) {
                int zzb = zzbvc.zzb(zzbuq, 18);
                zzbvp[] zzbvpArr = this.zzgbm;
                int length = zzbvpArr == null ? 0 : zzbvpArr.length;
                zzbvp[] zzbvpArr2 = new zzbvp[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzgbm, 0, zzbvpArr2, 0, length);
                }
                while (length < zzbvpArr2.length - 1) {
                    zzbvpArr2[length] = new zzbvp();
                    zzbuq.zza((zzbuz) zzbvpArr2[length]);
                    zzbuq.zzaku();
                    length++;
                }
                zzbvpArr2[length] = new zzbvp();
                zzbuq.zza((zzbuz) zzbvpArr2[length]);
                this.zzgbm = zzbvpArr2;
            } else if (zzaku == 26) {
                this.zzgbn = zzbuq.readBytes();
            } else if (zzaku == 34) {
                this.zzgbo = zzbuq.readBytes();
            } else if (zzaku == 40) {
                this.zzgbp = Integer.valueOf(zzbuq.zzakx());
            } else if (!super.zza(zzbuq, zzaku)) {
                return this;
            }
        }
    }
}
