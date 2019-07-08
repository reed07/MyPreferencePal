package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;

/* compiled from: IMASDK */
public class sf {
    private final boolean a;
    private final int b;
    private final byte[] c;
    private final se[] d;
    private int e;
    private int f;
    private int g;
    private se[] h;

    public synchronized se a() {
        se seVar;
        this.f++;
        if (this.g > 0) {
            se[] seVarArr = this.h;
            int i = this.g - 1;
            this.g = i;
            seVar = seVarArr[i];
            this.h[this.g] = null;
        } else {
            seVar = new se(new byte[this.b], 0);
        }
        return seVar;
    }

    public synchronized void a(se seVar) {
        this.d[0] = seVar;
        a(this.d);
    }

    public synchronized void a(se[] seVarArr) {
        if (this.g + seVarArr.length >= this.h.length) {
            this.h = (se[]) Arrays.copyOf(this.h, Math.max(this.h.length << 1, this.g + seVarArr.length));
        }
        for (se seVar : seVarArr) {
            se[] seVarArr2 = this.h;
            int i = this.g;
            this.g = i + 1;
            seVarArr2[i] = seVar;
        }
        this.f -= seVarArr.length;
        notifyAll();
    }

    public synchronized void b() {
        int i = 0;
        int max = Math.max(0, vf.a(this.e, this.b) - this.f);
        if (max < this.g) {
            if (this.c != null) {
                int i2 = this.g - 1;
                while (i <= i2) {
                    se seVar = this.h[i];
                    if (seVar.a == this.c) {
                        i++;
                    } else {
                        se seVar2 = this.h[i2];
                        if (seVar2.a != this.c) {
                            i2--;
                        } else {
                            int i3 = i + 1;
                            this.h[i] = seVar2;
                            int i4 = i2 - 1;
                            this.h[i2] = seVar;
                            i2 = i4;
                            i = i3;
                        }
                    }
                }
                max = Math.max(max, i);
                if (max >= this.g) {
                    return;
                }
            }
            Arrays.fill(this.h, max, this.g, null);
            this.g = max;
        }
    }

    public int c() {
        return this.b;
    }

    public sf(boolean z, int i) {
        this(true, 65536, 0);
    }

    public sf(boolean z, int i, int i2) {
        qi.b(i > 0);
        qi.b(true);
        this.a = z;
        this.b = i;
        this.g = 0;
        this.h = new se[100];
        this.c = null;
        this.d = new se[1];
    }

    public synchronized void d() {
        if (this.a) {
            a(0);
        }
    }

    public synchronized void a(int i) {
        boolean z = i < this.e;
        this.e = i;
        if (z) {
            b();
        }
    }

    public synchronized int e() {
        return this.f * this.b;
    }
}
