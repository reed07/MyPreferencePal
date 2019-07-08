package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbs extends zzbut<zzbs> {
    public Integer zzfy;
    private Integer zzfz;
    public byte[] zzhm;
    public byte[][] zzhr;

    public zzbs() {
        this.zzhr = zzbvc.zzfxd;
        this.zzhm = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        byte[][] bArr = this.zzhr;
        if (bArr != null && bArr.length > 0) {
            int i = 0;
            while (true) {
                byte[][] bArr2 = this.zzhr;
                if (i >= bArr2.length) {
                    break;
                }
                byte[] bArr3 = bArr2[i];
                if (bArr3 != null) {
                    zzbur.zza(1, bArr3);
                }
                i++;
            }
        }
        byte[] bArr4 = this.zzhm;
        if (bArr4 != null) {
            zzbur.zza(2, bArr4);
        }
        Integer num = this.zzfz;
        if (num != null) {
            zzbur.zzv(3, num.intValue());
        }
        Integer num2 = this.zzfy;
        if (num2 != null) {
            zzbur.zzv(4, num2.intValue());
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int zzt = super.zzt();
        byte[][] bArr = this.zzhr;
        if (bArr != null && bArr.length > 0) {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                byte[][] bArr2 = this.zzhr;
                if (i >= bArr2.length) {
                    break;
                }
                byte[] bArr3 = bArr2[i];
                if (bArr3 != null) {
                    i3++;
                    i2 += zzbur.zzy(bArr3);
                }
                i++;
            }
            zzt = zzt + i2 + (i3 * 1);
        }
        byte[] bArr4 = this.zzhm;
        if (bArr4 != null) {
            zzt += zzbur.zzb(2, bArr4);
        }
        Integer num = this.zzfz;
        if (num != null) {
            zzt += zzbur.zzz(3, num.intValue());
        }
        Integer num2 = this.zzfy;
        return num2 != null ? zzt + zzbur.zzz(4, num2.intValue()) : zzt;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzbs zza(zzbuq zzbuq) throws IOException {
        while (true) {
            int zzaku = zzbuq.zzaku();
            if (zzaku == 0) {
                return this;
            }
            if (zzaku == 10) {
                int zzb = zzbvc.zzb(zzbuq, 10);
                byte[][] bArr = this.zzhr;
                int length = bArr == null ? 0 : bArr.length;
                byte[][] bArr2 = new byte[(zzb + length)][];
                if (length != 0) {
                    System.arraycopy(this.zzhr, 0, bArr2, 0, length);
                }
                while (length < bArr2.length - 1) {
                    bArr2[length] = zzbuq.readBytes();
                    zzbuq.zzaku();
                    length++;
                }
                bArr2[length] = zzbuq.readBytes();
                this.zzhr = bArr2;
            } else if (zzaku == 18) {
                this.zzhm = zzbuq.readBytes();
            } else if (zzaku == 24) {
                int position = zzbuq.getPosition();
                try {
                    this.zzfz = Integer.valueOf(zzbk.zze(zzbuq.zzalm()));
                } catch (IllegalArgumentException unused) {
                    zzbuq.zzgc(position);
                    zza(zzbuq, zzaku);
                }
            } else if (zzaku == 32) {
                int position2 = zzbuq.getPosition();
                try {
                    this.zzfy = Integer.valueOf(zzbk.zzf(zzbuq.zzalm()));
                } catch (IllegalArgumentException unused2) {
                    zzbuq.zzgc(position2);
                    zza(zzbuq, zzaku);
                }
            } else if (!super.zza(zzbuq, zzaku)) {
                return this;
            }
        }
    }
}
