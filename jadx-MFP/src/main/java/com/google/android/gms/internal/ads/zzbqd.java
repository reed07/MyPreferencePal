package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.charset.Charset;

class zzbqd extends zzbqc {
    protected final byte[] zzflp;

    zzbqd(byte[] bArr) {
        if (bArr != null) {
            this.zzflp = bArr;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: protected */
    public int zzakr() {
        return 0;
    }

    public byte zzem(int i) {
        return this.zzflp[i];
    }

    public int size() {
        return this.zzflp.length;
    }

    public final zzbpu zzt(int i, int i2) {
        int zzg = zzg(0, i2, size());
        if (zzg == 0) {
            return zzbpu.zzfli;
        }
        return new zzbpy(this.zzflp, zzakr(), zzg);
    }

    /* access modifiers changed from: protected */
    public void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzflp, 0, bArr, 0, i3);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzbpt zzbpt) throws IOException {
        zzbpt.zzh(this.zzflp, zzakr(), size());
    }

    /* access modifiers changed from: protected */
    public final String zza(Charset charset) {
        return new String(this.zzflp, zzakr(), size(), charset);
    }

    public final boolean zzako() {
        int zzakr = zzakr();
        return zzbuc.zzm(this.zzflp, zzakr, size() + zzakr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbpu) || size() != ((zzbpu) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzbqd)) {
            return obj.equals(this);
        }
        zzbqd zzbqd = (zzbqd) obj;
        int zzakq = zzakq();
        int zzakq2 = zzbqd.zzakq();
        if (zzakq == 0 || zzakq2 == 0 || zzakq == zzakq2) {
            return zza(zzbqd, 0, size());
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(zzbpu zzbpu, int i, int i2) {
        if (i2 > zzbpu.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 > zzbpu.size()) {
            int size2 = zzbpu.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzbpu instanceof zzbqd)) {
            return zzbpu.zzt(0, i2).equals(zzt(0, i2));
        } else {
            zzbqd zzbqd = (zzbqd) zzbpu;
            byte[] bArr = this.zzflp;
            byte[] bArr2 = zzbqd.zzflp;
            int zzakr = zzakr() + i2;
            int zzakr2 = zzakr();
            int zzakr3 = zzbqd.zzakr();
            while (zzakr2 < zzakr) {
                if (bArr[zzakr2] != bArr2[zzakr3]) {
                    return false;
                }
                zzakr2++;
                zzakr3++;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public final int zzf(int i, int i2, int i3) {
        return zzbrf.zza(i, this.zzflp, zzakr(), i3);
    }

    public final zzbqf zzakp() {
        return zzbqf.zzb(this.zzflp, zzakr(), size(), true);
    }
}
