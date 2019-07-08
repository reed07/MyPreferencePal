package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class ij {
    private final gc a;
    private long b;
    private boolean c;
    private int d;
    private long e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private long k;
    private long l;
    private boolean m;

    public ij(gc gcVar) {
        this.a = gcVar;
    }

    public final void a() {
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = false;
    }

    public final void a(long j2, int i2, int i3, long j3) {
        this.g = false;
        this.h = false;
        this.e = j3;
        this.d = 0;
        this.b = j2;
        boolean z = true;
        if (i3 >= 32) {
            if (!this.j && this.i) {
                a(i2);
                this.i = false;
            }
            if (i3 <= 34) {
                this.h = !this.j;
                this.j = true;
            }
        }
        this.c = i3 >= 16 && i3 <= 21;
        if (!this.c && i3 > 9) {
            z = false;
        }
        this.f = z;
    }

    public final void a(byte[] bArr, int i2, int i3) {
        if (this.f) {
            int i4 = i2 + 2;
            int i5 = this.d;
            int i6 = i4 - i5;
            if (i6 < i3) {
                this.g = (bArr[i6] & 128) != 0;
                this.f = false;
                return;
            }
            this.d = i5 + (i3 - i2);
        }
    }

    public final void a(long j2, int i2) {
        if (!this.j || !this.g) {
            if (this.h || this.g) {
                if (this.i) {
                    a(i2 + ((int) (j2 - this.b)));
                }
                this.k = this.b;
                this.l = this.e;
                this.i = true;
                this.m = this.c;
            }
            return;
        }
        this.m = this.c;
        this.j = false;
    }

    private final void a(int i2) {
        int i3 = (int) (this.b - this.k);
        int i4 = i2;
        this.a.a(this.l, this.m ? 1 : 0, i3, i4, null);
    }
}
