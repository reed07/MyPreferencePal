package com.google.ads.interactivemedia.v3.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/* compiled from: IMASDK */
public final class er implements dj {
    private int b = -1;
    private int c = -1;
    private float d = 1.0f;
    private float e = 1.0f;
    private int f = -1;
    private int g = -1;
    private boolean h;
    private eq i;
    private ByteBuffer j = a;
    private ShortBuffer k = this.j.asShortBuffer();
    private ByteBuffer l = a;
    private long m;
    private long n;
    private boolean o;

    public final int c() {
        return 2;
    }

    public final float a(float f2) {
        float a = vf.a(f2, 0.1f, 8.0f);
        if (this.d != a) {
            this.d = a;
            this.h = true;
        }
        h();
        return a;
    }

    public final float b(float f2) {
        float a = vf.a(f2, 0.1f, 8.0f);
        if (this.e != a) {
            this.e = a;
            this.h = true;
        }
        h();
        return a;
    }

    public final long a(long j2) {
        long j3 = this.n;
        if (j3 < 1024) {
            return (long) (((double) this.d) * ((double) j2));
        }
        int i2 = this.f;
        int i3 = this.c;
        if (i2 == i3) {
            return vf.c(j2, this.m, j3);
        }
        return vf.c(j2, this.m * ((long) i2), j3 * ((long) i3));
    }

    public final boolean a(int i2, int i3, int i4) throws dk {
        if (i4 == 2) {
            int i5 = this.g;
            if (i5 == -1) {
                i5 = i2;
            }
            if (this.c == i2 && this.b == i3 && this.f == i5) {
                return false;
            }
            this.c = i2;
            this.b = i3;
            this.f = i5;
            this.h = true;
            return true;
        }
        throw new dk(i2, i3, i4);
    }

    public final boolean a() {
        return this.c != -1 && (Math.abs(this.d - 1.0f) >= 0.01f || Math.abs(this.e - 1.0f) >= 0.01f || this.f != this.c);
    }

    public final int b() {
        return this.b;
    }

    public final int d() {
        return this.f;
    }

    public final void a(ByteBuffer byteBuffer) {
        eq eqVar = (eq) qi.a(this.i);
        if (byteBuffer.hasRemaining()) {
            ShortBuffer asShortBuffer = byteBuffer.asShortBuffer();
            int remaining = byteBuffer.remaining();
            this.m += (long) remaining;
            eqVar.a(asShortBuffer);
            byteBuffer.position(byteBuffer.position() + remaining);
        }
        int c2 = (eqVar.c() * this.b) << 1;
        if (c2 > 0) {
            if (this.j.capacity() < c2) {
                this.j = ByteBuffer.allocateDirect(c2).order(ByteOrder.nativeOrder());
                this.k = this.j.asShortBuffer();
            } else {
                this.j.clear();
                this.k.clear();
            }
            eqVar.b(this.k);
            this.n += (long) c2;
            this.j.limit(c2);
            this.l = this.j;
        }
    }

    public final void e() {
        eq eqVar = this.i;
        if (eqVar != null) {
            eqVar.a();
        }
        this.o = true;
    }

    public final ByteBuffer f() {
        ByteBuffer byteBuffer = this.l;
        this.l = a;
        return byteBuffer;
    }

    public final boolean g() {
        if (this.o) {
            eq eqVar = this.i;
            if (eqVar == null || eqVar.c() == 0) {
                return true;
            }
        }
        return false;
    }

    public final void h() {
        if (a()) {
            if (this.h) {
                eq eqVar = new eq(this.c, this.b, this.d, this.e, this.f);
                this.i = eqVar;
            } else {
                eq eqVar2 = this.i;
                if (eqVar2 != null) {
                    eqVar2.b();
                }
            }
        }
        this.l = a;
        this.m = 0;
        this.n = 0;
        this.o = false;
    }

    public final void i() {
        this.d = 1.0f;
        this.e = 1.0f;
        this.b = -1;
        this.c = -1;
        this.f = -1;
        this.j = a;
        this.k = this.j.asShortBuffer();
        this.l = a;
        this.g = -1;
        this.h = false;
        this.i = null;
        this.m = 0;
        this.n = 0;
        this.o = false;
    }
}
