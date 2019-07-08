package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;

/* compiled from: IMASDK */
final class ie {
    private static final byte[] d = {0, 0, 1};
    public int a;
    public int b;
    public byte[] c = new byte[128];
    private boolean e;

    public ie(int i) {
    }

    public final void a() {
        this.e = false;
        this.a = 0;
        this.b = 0;
    }

    public final boolean a(int i, int i2) {
        if (this.e) {
            this.a -= i2;
            if (this.b == 0 && i == 181) {
                this.b = this.a;
            } else {
                this.e = false;
                return true;
            }
        } else if (i == 179) {
            this.e = true;
        }
        byte[] bArr = d;
        a(bArr, 0, bArr.length);
        return false;
    }

    public final void a(byte[] bArr, int i, int i2) {
        if (this.e) {
            int i3 = i2 - i;
            byte[] bArr2 = this.c;
            int length = bArr2.length;
            int i4 = this.a;
            if (length < i4 + i3) {
                this.c = Arrays.copyOf(bArr2, (i4 + i3) << 1);
            }
            System.arraycopy(bArr, i, this.c, this.a, i3);
            this.a += i3;
        }
    }
}
