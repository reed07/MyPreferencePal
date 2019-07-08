package com.google.ads.interactivemedia.v3.internal;

import com.google.common.base.Ascii;
import java.nio.charset.Charset;

/* compiled from: IMASDK */
public final class ut {
    public byte[] a;
    private int b;
    private int c;

    public ut() {
        this.a = vf.f;
    }

    public ut(int i) {
        this.a = new byte[i];
        this.c = i;
    }

    public ut(byte[] bArr) {
        this.a = bArr;
        this.c = bArr.length;
    }

    public ut(byte[] bArr, int i) {
        this.a = bArr;
        this.c = i;
    }

    public final void a() {
        this.b = 0;
        this.c = 0;
    }

    public final void a(int i) {
        byte[] bArr = this.a;
        if (bArr.length < i) {
            bArr = new byte[i];
        }
        a(bArr, i);
    }

    public final void a(byte[] bArr, int i) {
        this.a = bArr;
        this.c = i;
        this.b = 0;
    }

    public final int b() {
        return this.c - this.b;
    }

    public final int c() {
        return this.c;
    }

    public final void b(int i) {
        qi.b(i >= 0 && i <= this.a.length);
        this.c = i;
    }

    public final int d() {
        return this.b;
    }

    public final void c(int i) {
        qi.b(i >= 0 && i <= this.c);
        this.b = i;
    }

    public final void d(int i) {
        c(this.b + i);
    }

    public final void a(us usVar, int i) {
        a(usVar.a, 0, i);
        usVar.a(0);
    }

    public final void a(byte[] bArr, int i, int i2) {
        System.arraycopy(this.a, this.b, bArr, i, i2);
        this.b += i2;
    }

    public final int e() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        return bArr[i] & 255;
    }

    public final int f() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = (bArr[i] & 255) << 8;
        int i3 = this.b;
        this.b = i3 + 1;
        return (bArr[i3] & 255) | i2;
    }

    public final int g() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        byte b2 = bArr[i] & 255;
        int i2 = this.b;
        this.b = i2 + 1;
        return ((bArr[i2] & 255) << 8) | b2;
    }

    public final short h() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = (bArr[i] & 255) << 8;
        int i3 = this.b;
        this.b = i3 + 1;
        return (short) ((bArr[i3] & 255) | i2);
    }

    public final int i() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = (bArr[i] & 255) << Ascii.DLE;
        int i3 = this.b;
        this.b = i3 + 1;
        byte b2 = i2 | ((bArr[i3] & 255) << 8);
        int i4 = this.b;
        this.b = i4 + 1;
        return (bArr[i4] & 255) | b2;
    }

    public final long j() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        long j = (((long) bArr[i]) & 255) << 24;
        int i2 = this.b;
        this.b = i2 + 1;
        long j2 = j | ((((long) bArr[i2]) & 255) << 16);
        int i3 = this.b;
        this.b = i3 + 1;
        long j3 = j2 | ((((long) bArr[i3]) & 255) << 8);
        int i4 = this.b;
        this.b = i4 + 1;
        return j3 | (255 & ((long) bArr[i4]));
    }

    public final long k() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        long j = ((long) bArr[i]) & 255;
        int i2 = this.b;
        this.b = i2 + 1;
        long j2 = j | ((((long) bArr[i2]) & 255) << 8);
        int i3 = this.b;
        this.b = i3 + 1;
        long j3 = j2 | ((((long) bArr[i3]) & 255) << 16);
        int i4 = this.b;
        this.b = i4 + 1;
        return j3 | ((255 & ((long) bArr[i4])) << 24);
    }

    public final int l() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = (bArr[i] & 255) << Ascii.CAN;
        int i3 = this.b;
        this.b = i3 + 1;
        byte b2 = i2 | ((bArr[i3] & 255) << Ascii.DLE);
        int i4 = this.b;
        this.b = i4 + 1;
        byte b3 = b2 | ((bArr[i4] & 255) << 8);
        int i5 = this.b;
        this.b = i5 + 1;
        return (bArr[i5] & 255) | b3;
    }

    public final long m() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        long j = (((long) bArr[i]) & 255) << 56;
        int i2 = this.b;
        this.b = i2 + 1;
        long j2 = j | ((((long) bArr[i2]) & 255) << 48);
        int i3 = this.b;
        this.b = i3 + 1;
        long j3 = j2 | ((((long) bArr[i3]) & 255) << 40);
        int i4 = this.b;
        this.b = i4 + 1;
        long j4 = j3 | ((((long) bArr[i4]) & 255) << 32);
        int i5 = this.b;
        this.b = i5 + 1;
        long j5 = j4 | ((((long) bArr[i5]) & 255) << 24);
        int i6 = this.b;
        this.b = i6 + 1;
        long j6 = j5 | ((((long) bArr[i6]) & 255) << 16);
        int i7 = this.b;
        this.b = i7 + 1;
        long j7 = j6 | ((((long) bArr[i7]) & 255) << 8);
        int i8 = this.b;
        this.b = i8 + 1;
        return j7 | (255 & ((long) bArr[i8]));
    }

    public final int n() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = (bArr[i] & 255) << 8;
        int i3 = this.b;
        this.b = i3 + 1;
        byte b2 = (bArr[i3] & 255) | i2;
        this.b += 2;
        return b2;
    }

    public final int o() {
        return (e() << 21) | (e() << 14) | (e() << 7) | e();
    }

    public final int p() {
        int l = l();
        if (l >= 0) {
            return l;
        }
        StringBuilder sb = new StringBuilder(29);
        sb.append("Top bit not zero: ");
        sb.append(l);
        throw new IllegalStateException(sb.toString());
    }

    public final long q() {
        long m = m();
        if (m >= 0) {
            return m;
        }
        StringBuilder sb = new StringBuilder(38);
        sb.append("Top bit not zero: ");
        sb.append(m);
        throw new IllegalStateException(sb.toString());
    }

    public final String e(int i) {
        String str = new String(this.a, this.b, i, Charset.forName("UTF-8"));
        this.b += i;
        return str;
    }

    public final String f(int i) {
        if (i == 0) {
            return "";
        }
        int i2 = (this.b + i) - 1;
        String a2 = vf.a(this.a, this.b, (i2 >= this.c || this.a[i2] != 0) ? i : i - 1);
        this.b += i;
        return a2;
    }

    public final String r() {
        if (b() == 0) {
            return null;
        }
        int i = this.b;
        while (i < this.c && this.a[i] != 0) {
            i++;
        }
        byte[] bArr = this.a;
        int i2 = this.b;
        String a2 = vf.a(bArr, i2, i - i2);
        this.b = i;
        int i3 = this.b;
        if (i3 < this.c) {
            this.b = i3 + 1;
        }
        return a2;
    }

    public final String s() {
        if (b() == 0) {
            return null;
        }
        int i = this.b;
        while (i < this.c && !vf.a((int) this.a[i])) {
            i++;
        }
        int i2 = this.b;
        if (i - i2 >= 3) {
            byte[] bArr = this.a;
            if (bArr[i2] == -17 && bArr[i2 + 1] == -69 && bArr[i2 + 2] == -65) {
                this.b = i2 + 3;
            }
        }
        byte[] bArr2 = this.a;
        int i3 = this.b;
        String a2 = vf.a(bArr2, i3, i - i3);
        this.b = i;
        int i4 = this.b;
        int i5 = this.c;
        if (i4 == i5) {
            return a2;
        }
        if (this.a[i4] == 13) {
            this.b = i4 + 1;
            if (this.b == i5) {
                return a2;
            }
        }
        byte[] bArr3 = this.a;
        int i6 = this.b;
        if (bArr3[i6] == 10) {
            this.b = i6 + 1;
        }
        return a2;
    }
}
