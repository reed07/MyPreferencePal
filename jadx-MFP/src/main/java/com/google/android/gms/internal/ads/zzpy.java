package com.google.android.gms.internal.ads;

public final class zzpy {
    private byte[] data;
    private int zzbhx;
    private int zzbhy = 0;
    private int zzbhz;

    public zzpy(byte[] bArr, int i, int i2) {
        this.data = bArr;
        this.zzbhx = i;
        this.zzbhz = i2;
        zzhn();
    }

    public final void zzbn(int i) {
        int i2 = this.zzbhx;
        this.zzbhx = (i / 8) + i2;
        this.zzbhy += i % 8;
        int i3 = this.zzbhy;
        if (i3 > 7) {
            this.zzbhx++;
            this.zzbhy = i3 - 8;
        }
        while (true) {
            i2++;
            if (i2 > this.zzbhx) {
                zzhn();
                return;
            } else if (zzbo(i2)) {
                this.zzbhx++;
                i2 += 2;
            }
        }
    }

    public final boolean zzhj() {
        return zzbj(1) == 1;
    }

    public final int zzbj(int i) {
        byte b;
        byte b2;
        if (i == 0) {
            return 0;
        }
        int i2 = i / 8;
        byte b3 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = zzbo(this.zzbhx + 1) ? this.zzbhx + 2 : this.zzbhx + 1;
            int i5 = this.zzbhy;
            if (i5 != 0) {
                byte[] bArr = this.data;
                b2 = ((bArr[i4] & 255) >>> (8 - i5)) | ((bArr[this.zzbhx] & 255) << i5);
            } else {
                b2 = this.data[this.zzbhx];
            }
            i -= 8;
            b3 = b | ((255 & b2) << i);
            this.zzbhx = i4;
        }
        if (i > 0) {
            int i6 = this.zzbhy + i;
            byte b4 = (byte) (255 >> (8 - i));
            int i7 = zzbo(this.zzbhx + 1) ? this.zzbhx + 2 : this.zzbhx + 1;
            if (i6 > 8) {
                byte[] bArr2 = this.data;
                byte b5 = (b4 & (((255 & bArr2[i7]) >> (16 - i6)) | ((bArr2[this.zzbhx] & 255) << (i6 - 8)))) | b;
                this.zzbhx = i7;
                b = b5;
            } else {
                byte b6 = (b4 & ((255 & this.data[this.zzbhx]) >> (8 - i6))) | b;
                if (i6 == 8) {
                    this.zzbhx = i7;
                }
                b = b6;
            }
            this.zzbhy = i6 % 8;
        }
        zzhn();
        return b;
    }

    public final int zzhk() {
        return zzhm();
    }

    public final int zzhl() {
        int zzhm = zzhm();
        return (zzhm % 2 == 0 ? -1 : 1) * ((zzhm + 1) / 2);
    }

    private final int zzhm() {
        int i = 0;
        int i2 = 0;
        while (!zzhj()) {
            i2++;
        }
        int i3 = (1 << i2) - 1;
        if (i2 > 0) {
            i = zzbj(i2);
        }
        return i3 + i;
    }

    private final boolean zzbo(int i) {
        if (2 <= i && i < this.zzbhz) {
            byte[] bArr = this.data;
            if (bArr[i] == 3 && bArr[i - 2] == 0 && bArr[i - 1] == 0) {
                return true;
            }
        }
        return false;
    }

    private final void zzhn() {
        boolean z;
        int i = this.zzbhx;
        if (i >= 0) {
            int i2 = this.zzbhy;
            if (i2 >= 0 && i2 < 8) {
                int i3 = this.zzbhz;
                if (i < i3 || (i == i3 && i2 == 0)) {
                    z = true;
                    zzpo.checkState(z);
                }
            }
        }
        z = false;
        zzpo.checkState(z);
    }
}
