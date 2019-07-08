package com.google.android.gms.internal.icing;

final class zzcj extends zzco {
    private final int zzgc;
    private final int zzgd;

    zzcj(byte[] bArr, int i, int i2) {
        super(bArr);
        zzb(i, i + i2, bArr.length);
        this.zzgc = i;
        this.zzgd = i2;
    }

    public final byte zzk(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzgf[this.zzgc + i];
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(22);
            sb.append("Index < 0: ");
            sb.append(i);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("Index > length: ");
        sb2.append(i);
        sb2.append(", ");
        sb2.append(size);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }

    /* access modifiers changed from: 0000 */
    public final byte zzl(int i) {
        return this.zzgf[this.zzgc + i];
    }

    public final int size() {
        return this.zzgd;
    }

    /* access modifiers changed from: protected */
    public final int zzar() {
        return this.zzgc;
    }
}
