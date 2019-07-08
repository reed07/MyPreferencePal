package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class uu {
    private byte[] a;
    private int b;
    private int c;
    private int d;

    public uu(byte[] bArr, int i, int i2) {
        a(bArr, i, i2);
    }

    public final void a(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.c = i;
        this.b = i2;
        this.d = 0;
        g();
    }

    public final void a() {
        int i = 1;
        int i2 = this.d + 1;
        this.d = i2;
        if (i2 == 8) {
            this.d = 0;
            int i3 = this.c;
            if (d(i3 + 1)) {
                i = 2;
            }
            this.c = i3 + i;
        }
        g();
    }

    public final void a(int i) {
        int i2 = this.c;
        int i3 = i / 8;
        this.c = i2 + i3;
        this.d += i - (i3 << 3);
        int i4 = this.d;
        if (i4 > 7) {
            this.c++;
            this.d = i4 - 8;
        }
        while (true) {
            i2++;
            if (i2 > this.c) {
                g();
                return;
            } else if (d(i2)) {
                this.c++;
                i2 += 2;
            }
        }
    }

    public final boolean b(int i) {
        int i2 = this.c;
        int i3 = i / 8;
        int i4 = i2 + i3;
        int i5 = (this.d + i) - (i3 << 3);
        if (i5 > 7) {
            i4++;
            i5 -= 8;
        }
        int i6 = i2 + 1;
        while (i6 <= i4 && i4 < this.b) {
            if (d(i6)) {
                i4++;
                i6 += 2;
            }
            i6++;
        }
        int i7 = this.b;
        if (i4 < i7) {
            return true;
        }
        if (i4 == i7 && i5 == 0) {
            return true;
        }
        return false;
    }

    public final boolean b() {
        boolean z = (this.a[this.c] & (128 >> this.d)) != 0;
        a();
        return z;
    }

    public final int c(int i) {
        int i2;
        int i3;
        this.d += i;
        int i4 = 0;
        while (true) {
            i2 = this.d;
            i3 = 2;
            if (i2 <= 8) {
                break;
            }
            this.d = i2 - 8;
            byte[] bArr = this.a;
            int i5 = this.c;
            i4 |= (bArr[i5] & 255) << this.d;
            if (!d(i5 + 1)) {
                i3 = 1;
            }
            this.c = i5 + i3;
        }
        byte[] bArr2 = this.a;
        int i6 = this.c;
        int i7 = (-1 >>> (32 - i)) & (i4 | ((bArr2[i6] & 255) >> (8 - i2)));
        if (i2 == 8) {
            this.d = 0;
            if (!d(i6 + 1)) {
                i3 = 1;
            }
            this.c = i6 + i3;
        }
        g();
        return i7;
    }

    public final boolean c() {
        int i = this.c;
        int i2 = this.d;
        int i3 = 0;
        while (this.c < this.b && !b()) {
            i3++;
        }
        boolean z = this.c == this.b;
        this.c = i;
        this.d = i2;
        if (z || !b((i3 << 1) + 1)) {
            return false;
        }
        return true;
    }

    public final int d() {
        return f();
    }

    public final int e() {
        int f = f();
        return (f % 2 == 0 ? -1 : 1) * ((f + 1) / 2);
    }

    private final int f() {
        int i = 0;
        int i2 = 0;
        while (!b()) {
            i2++;
        }
        int i3 = (1 << i2) - 1;
        if (i2 > 0) {
            i = c(i2);
        }
        return i3 + i;
    }

    private final boolean d(int i) {
        if (2 <= i && i < this.b) {
            byte[] bArr = this.a;
            if (bArr[i] == 3 && bArr[i - 2] == 0 && bArr[i - 1] == 0) {
                return true;
            }
        }
        return false;
    }

    private final void g() {
        boolean z;
        int i = this.c;
        if (i >= 0) {
            int i2 = this.b;
            if (i < i2 || (i == i2 && this.d == 0)) {
                z = true;
                qi.c(z);
            }
        }
        z = false;
        qi.c(z);
    }
}
