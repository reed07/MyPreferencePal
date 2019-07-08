package com.google.android.gms.internal.ads;

final class zzbpy extends zzbqd {
    private final int zzflm;
    private final int zzfln;

    zzbpy(byte[] bArr, int i, int i2) {
        super(bArr);
        zzg(i, i + i2, bArr.length);
        this.zzflm = i;
        this.zzfln = i2;
    }

    public final byte zzem(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzflp[this.zzflm + i];
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

    public final int size() {
        return this.zzfln;
    }

    /* access modifiers changed from: protected */
    public final int zzakr() {
        return this.zzflm;
    }

    /* access modifiers changed from: protected */
    public final void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzflp, zzakr(), bArr, 0, i3);
    }
}
