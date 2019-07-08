package com.google.android.gms.internal.ads;

public final class zzpw {
    private byte[] data;
    private int zzbhx;
    private int zzbhy;
    private int zzbhz;

    public zzpw() {
    }

    public zzpw(byte[] bArr) {
        this(bArr, bArr.length);
    }

    private zzpw(byte[] bArr, int i) {
        this.data = bArr;
        this.zzbhz = i;
    }

    public final int zzbj(int i) {
        byte b;
        byte b2;
        boolean z = false;
        if (i == 0) {
            return 0;
        }
        int i2 = i / 8;
        int i3 = i;
        byte b3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = this.zzbhy;
            if (i5 != 0) {
                byte[] bArr = this.data;
                int i6 = this.zzbhx;
                b2 = ((bArr[i6 + 1] & 255) >>> (8 - i5)) | ((bArr[i6] & 255) << i5);
            } else {
                b2 = this.data[this.zzbhx];
            }
            i3 -= 8;
            b3 = b | ((255 & b2) << i3);
            this.zzbhx++;
        }
        if (i3 > 0) {
            int i7 = this.zzbhy + i3;
            byte b4 = (byte) (255 >> (8 - i3));
            if (i7 > 8) {
                byte[] bArr2 = this.data;
                int i8 = this.zzbhx;
                byte b5 = (b4 & (((bArr2[i8 + 1] & 255) >> (16 - i7)) | ((bArr2[i8] & 255) << (i7 - 8)))) | b;
                this.zzbhx = i8 + 1;
                b = b5;
            } else {
                byte[] bArr3 = this.data;
                int i9 = this.zzbhx;
                byte b6 = (b4 & ((bArr3[i9] & 255) >> (8 - i7))) | b;
                if (i7 == 8) {
                    this.zzbhx = i9 + 1;
                }
                b = b6;
            }
            this.zzbhy = i7 % 8;
        }
        int i10 = this.zzbhx;
        if (i10 >= 0) {
            int i11 = this.zzbhy;
            if (i11 >= 0 && i11 < 8) {
                int i12 = this.zzbhz;
                if (i10 < i12 || (i10 == i12 && i11 == 0)) {
                    z = true;
                }
            }
        }
        zzpo.checkState(z);
        return b;
    }
}
