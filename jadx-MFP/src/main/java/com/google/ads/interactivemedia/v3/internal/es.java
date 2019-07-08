package com.google.ads.interactivemedia.v3.internal;

import java.nio.ByteBuffer;

/* compiled from: IMASDK */
final class es extends ed {
    private boolean e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private int j;
    private byte[] k = vf.f;
    private int l;
    private long m;

    public final void a(int i2, int i3) {
        this.f = i2;
        this.g = i3;
    }

    public final void n() {
        this.m = 0;
    }

    public final long o() {
        return this.m;
    }

    public final boolean a(int i2, int i3, int i4) throws dk {
        if (i4 == 2) {
            int i5 = this.l;
            if (i5 > 0) {
                this.m += (long) (i5 / this.h);
            }
            this.h = vf.b(2, i3);
            int i6 = this.g;
            int i7 = this.h;
            this.k = new byte[(i6 * i7)];
            this.l = 0;
            int i8 = this.f;
            this.j = i7 * i8;
            boolean z = this.e;
            this.e = (i8 == 0 && i6 == 0) ? false : true;
            this.i = false;
            b(i2, i3, i4);
            if (z != this.e) {
                return true;
            }
            return false;
        }
        throw new dk(i2, i3, i4);
    }

    public final boolean a() {
        return this.e;
    }

    public final void a(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i2 = limit - position;
        if (i2 != 0) {
            this.i = true;
            int min = Math.min(i2, this.j);
            this.m += (long) (min / this.h);
            this.j -= min;
            byteBuffer.position(position + min);
            if (this.j <= 0) {
                int i3 = i2 - min;
                int length = (this.l + i3) - this.k.length;
                ByteBuffer a = a(length);
                int a2 = vf.a(length, 0, this.l);
                a.put(this.k, 0, a2);
                int a3 = vf.a(length - a2, 0, i3);
                byteBuffer.limit(byteBuffer.position() + a3);
                a.put(byteBuffer);
                byteBuffer.limit(limit);
                int i4 = i3 - a3;
                this.l -= a2;
                byte[] bArr = this.k;
                System.arraycopy(bArr, a2, bArr, 0, this.l);
                byteBuffer.get(this.k, this.l, i4);
                this.l += i4;
                a.flip();
            }
        }
    }

    public final ByteBuffer f() {
        if (super.g()) {
            int i2 = this.l;
            if (i2 > 0) {
                a(i2).put(this.k, 0, this.l).flip();
                this.l = 0;
            }
        }
        return super.f();
    }

    public final boolean g() {
        return super.g() && this.l == 0;
    }

    /* access modifiers changed from: protected */
    public final void l() {
        if (this.i) {
            this.j = 0;
        }
        this.l = 0;
    }

    /* access modifiers changed from: protected */
    public final void m() {
        this.k = vf.f;
    }
}
