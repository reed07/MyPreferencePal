package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.nio.charset.Charset;

class zzco extends zzcn {
    protected final byte[] zzgf;

    zzco(byte[] bArr) {
        if (bArr != null) {
            this.zzgf = bArr;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: protected */
    public int zzar() {
        return 0;
    }

    public byte zzk(int i) {
        return this.zzgf[i];
    }

    /* access modifiers changed from: 0000 */
    public byte zzl(int i) {
        return this.zzgf[i];
    }

    public int size() {
        return this.zzgf.length;
    }

    public final zzce zza(int i, int i2) {
        int zzb = zzb(0, i2, size());
        if (zzb == 0) {
            return zzce.zzfx;
        }
        return new zzcj(this.zzgf, zzar(), zzb);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzcd zzcd) throws IOException {
        zzcd.zza(this.zzgf, zzar(), size());
    }

    /* access modifiers changed from: protected */
    public final String zza(Charset charset) {
        return new String(this.zzgf, zzar(), size(), charset);
    }

    public final boolean zzap() {
        int zzar = zzar();
        return zzgf.zzc(this.zzgf, zzar, size() + zzar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzce) || size() != ((zzce) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzco)) {
            return obj.equals(this);
        }
        zzco zzco = (zzco) obj;
        int zzaq = zzaq();
        int zzaq2 = zzco.zzaq();
        if (zzaq == 0 || zzaq2 == 0 || zzaq == zzaq2) {
            return zza(zzco, 0, size());
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(zzce zzce, int i, int i2) {
        if (i2 > zzce.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 > zzce.size()) {
            int size2 = zzce.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzce instanceof zzco)) {
            return zzce.zza(0, i2).equals(zza(0, i2));
        } else {
            zzco zzco = (zzco) zzce;
            byte[] bArr = this.zzgf;
            byte[] bArr2 = zzco.zzgf;
            int zzar = zzar() + i2;
            int zzar2 = zzar();
            int zzar3 = zzco.zzar();
            while (zzar2 < zzar) {
                if (bArr[zzar2] != bArr2[zzar3]) {
                    return false;
                }
                zzar2++;
                zzar3++;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public final int zza(int i, int i2, int i3) {
        return zzdl.zza(i, this.zzgf, zzar(), i3);
    }
}
