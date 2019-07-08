package com.google.ads.interactivemedia.v3.internal;

import java.nio.ByteBuffer;

/* compiled from: IMASDK */
public final class ep extends ed {
    private int e;
    private boolean f;
    private byte[] g = vf.f;
    private byte[] h = vf.f;
    private int i;
    private int j;
    private int k;
    private boolean l;
    private long m;

    public final void a(boolean z) {
        this.f = z;
        h();
    }

    public final long n() {
        return this.m;
    }

    public final boolean a(int i2, int i3, int i4) throws dk {
        if (i4 == 2) {
            this.e = i3 << 1;
            return b(i2, i3, i4);
        }
        throw new dk(i2, i3, i4);
    }

    public final boolean a() {
        return super.a() && this.f;
    }

    public final void a(ByteBuffer byteBuffer) {
        int position;
        while (byteBuffer.hasRemaining() && !j()) {
            switch (this.i) {
                case 0:
                    int limit = byteBuffer.limit();
                    byteBuffer.limit(Math.min(limit, byteBuffer.position() + this.g.length));
                    int limit2 = byteBuffer.limit() - 1;
                    while (true) {
                        if (limit2 < byteBuffer.position()) {
                            position = byteBuffer.position();
                        } else if (Math.abs(byteBuffer.get(limit2)) > 4) {
                            int i2 = this.e;
                            position = ((limit2 / i2) * i2) + i2;
                        } else {
                            limit2 -= 2;
                        }
                    }
                    if (position == byteBuffer.position()) {
                        this.i = 1;
                    } else {
                        byteBuffer.limit(position);
                        int remaining = byteBuffer.remaining();
                        a(remaining).put(byteBuffer).flip();
                        if (remaining > 0) {
                            this.l = true;
                        }
                    }
                    byteBuffer.limit(limit);
                    break;
                case 1:
                    int limit3 = byteBuffer.limit();
                    int b = b(byteBuffer);
                    int position2 = b - byteBuffer.position();
                    byte[] bArr = this.g;
                    int length = bArr.length;
                    int i3 = this.j;
                    int i4 = length - i3;
                    if (b < limit3 && position2 < i4) {
                        a(bArr, i3);
                        this.j = 0;
                        this.i = 0;
                        break;
                    } else {
                        int min = Math.min(position2, i4);
                        byteBuffer.limit(byteBuffer.position() + min);
                        byteBuffer.get(this.g, this.j, min);
                        this.j += min;
                        int i5 = this.j;
                        byte[] bArr2 = this.g;
                        if (i5 == bArr2.length) {
                            if (this.l) {
                                a(bArr2, this.k);
                                this.m += (long) ((this.j - (this.k << 1)) / this.e);
                            } else {
                                this.m += (long) ((i5 - this.k) / this.e);
                            }
                            a(byteBuffer, this.g, this.j);
                            this.j = 0;
                            this.i = 2;
                        }
                        byteBuffer.limit(limit3);
                        break;
                    }
                case 2:
                    int limit4 = byteBuffer.limit();
                    int b2 = b(byteBuffer);
                    byteBuffer.limit(b2);
                    this.m += (long) (byteBuffer.remaining() / this.e);
                    a(byteBuffer, this.h, this.k);
                    if (b2 >= limit4) {
                        break;
                    } else {
                        a(this.h, this.k);
                        this.i = 0;
                        byteBuffer.limit(limit4);
                        break;
                    }
                default:
                    throw new IllegalStateException();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void k() {
        int i2 = this.j;
        if (i2 > 0) {
            a(this.g, i2);
        }
        if (!this.l) {
            this.m += (long) (this.k / this.e);
        }
    }

    /* access modifiers changed from: protected */
    public final void l() {
        if (a()) {
            int a = a(150000) * this.e;
            if (this.g.length != a) {
                this.g = new byte[a];
            }
            this.k = a(20000) * this.e;
            int length = this.h.length;
            int i2 = this.k;
            if (length != i2) {
                this.h = new byte[i2];
            }
        }
        this.i = 0;
        this.m = 0;
        this.j = 0;
        this.l = false;
    }

    /* access modifiers changed from: protected */
    public final void m() {
        this.f = false;
        this.k = 0;
        this.g = vf.f;
        this.h = vf.f;
    }

    private final void a(byte[] bArr, int i2) {
        a(i2).put(bArr, 0, i2).flip();
        if (i2 > 0) {
            this.l = true;
        }
    }

    private final void a(ByteBuffer byteBuffer, byte[] bArr, int i2) {
        int min = Math.min(byteBuffer.remaining(), this.k);
        int i3 = this.k - min;
        System.arraycopy(bArr, i2 - i3, this.h, 0, i3);
        byteBuffer.position(byteBuffer.limit() - min);
        byteBuffer.get(this.h, i3, min);
    }

    private final int a(long j2) {
        return (int) ((j2 * ((long) this.b)) / 1000000);
    }

    private final int b(ByteBuffer byteBuffer) {
        for (int position = byteBuffer.position() + 1; position < byteBuffer.limit(); position += 2) {
            if (Math.abs(byteBuffer.get(position)) > 4) {
                int i2 = this.e;
                return i2 * (position / i2);
            }
        }
        return byteBuffer.limit();
    }
}
