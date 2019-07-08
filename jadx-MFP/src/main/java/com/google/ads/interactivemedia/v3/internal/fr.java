package com.google.ads.interactivemedia.v3.internal;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: IMASDK */
public class fr {
    private final byte[] a = new byte[4096];
    private final sn b;
    private final long c;
    private long d;
    private byte[] e = new byte[65536];
    private int f;
    private int g;

    public int a(byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        int d2 = d(bArr, i, i2);
        if (d2 == 0) {
            d2 = a(bArr, i, i2, 0, true);
        }
        g(d2);
        return d2;
    }

    public boolean a(byte[] bArr, int i, int i2, boolean z) throws IOException, InterruptedException {
        int d2 = d(bArr, i, i2);
        while (d2 < i2 && d2 != -1) {
            d2 = a(bArr, i, i2, d2, z);
        }
        g(d2);
        return d2 != -1;
    }

    public void b(byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        a(bArr, i, i2, false);
    }

    public int a(int i) throws IOException, InterruptedException {
        int e2 = e(i);
        if (e2 == 0) {
            byte[] bArr = this.a;
            e2 = a(bArr, 0, Math.min(i, bArr.length), 0, true);
        }
        g(e2);
        return e2;
    }

    public void b(int i) throws IOException, InterruptedException {
        b(i, false);
    }

    public boolean b(byte[] bArr, int i, int i2, boolean z) throws IOException, InterruptedException {
        if (!a(i2, z)) {
            return false;
        }
        System.arraycopy(this.e, this.f - i2, bArr, i, i2);
        return true;
    }

    public void c(byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        b(bArr, i, i2, false);
    }

    public boolean a(int i, boolean z) throws IOException, InterruptedException {
        d(i);
        int i2 = this.g - this.f;
        while (i2 < i) {
            i2 = a(this.e, this.f, i, i2, z);
            if (i2 == -1) {
                return false;
            }
            this.g = this.f + i2;
        }
        this.f += i;
        return true;
    }

    public void c(int i) throws IOException, InterruptedException {
        a(i, false);
    }

    public void a() {
        this.f = 0;
    }

    public long b() {
        return this.d + ((long) this.f);
    }

    public long c() {
        return this.d;
    }

    public long d() {
        return this.c;
    }

    public fr(sn snVar, long j, long j2) {
        this.b = snVar;
        this.d = j;
        this.c = j2;
    }

    public boolean b(int i, boolean z) throws IOException, InterruptedException {
        int e2 = e(i);
        while (e2 < i && e2 != -1) {
            e2 = a(this.a, -e2, Math.min(i, this.a.length + e2), e2, false);
        }
        g(e2);
        return e2 != -1;
    }

    private void d(int i) {
        int i2 = this.f + i;
        byte[] bArr = this.e;
        if (i2 > bArr.length) {
            this.e = Arrays.copyOf(this.e, vf.a(bArr.length << 1, 65536 + i2, i2 + 524288));
        }
    }

    private int e(int i) {
        int min = Math.min(this.g, i);
        f(min);
        return min;
    }

    private int d(byte[] bArr, int i, int i2) {
        int i3 = this.g;
        if (i3 == 0) {
            return 0;
        }
        int min = Math.min(i3, i2);
        System.arraycopy(this.e, 0, bArr, i, min);
        f(min);
        return min;
    }

    private void f(int i) {
        this.g -= i;
        this.f = 0;
        byte[] bArr = this.e;
        int i2 = this.g;
        if (i2 < bArr.length - 524288) {
            bArr = new byte[(i2 + 65536)];
        }
        System.arraycopy(this.e, i, bArr, 0, this.g);
        this.e = bArr;
    }

    private int a(byte[] bArr, int i, int i2, int i3, boolean z) throws InterruptedException, IOException {
        if (!Thread.interrupted()) {
            int a2 = this.b.a(bArr, i + i3, i2 - i3);
            if (a2 != -1) {
                return i3 + a2;
            }
            if (i3 == 0 && z) {
                return -1;
            }
            throw new EOFException();
        }
        throw new InterruptedException();
    }

    private void g(int i) {
        if (i != -1) {
            this.d += (long) i;
        }
    }
}
