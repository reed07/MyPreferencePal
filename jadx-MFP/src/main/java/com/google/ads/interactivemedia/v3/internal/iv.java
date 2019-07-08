package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class iv {
    private final ve a = new ve(0);
    private final ut b = new ut();
    private boolean c;
    private boolean d;
    private boolean e;
    private long f = -9223372036854775807L;
    private long g = -9223372036854775807L;
    private long h = -9223372036854775807L;

    iv() {
    }

    public final boolean a() {
        return this.c;
    }

    public final int a(fr frVar, fx fxVar, int i) throws IOException, InterruptedException {
        long j;
        long j2;
        if (i <= 0) {
            return a(frVar);
        }
        if (!this.e) {
            long d2 = frVar.d();
            int min = (int) Math.min(112800, d2);
            long j3 = d2 - ((long) min);
            if (frVar.c() != j3) {
                fxVar.a = j3;
                return 1;
            }
            this.b.a(min);
            frVar.a();
            frVar.c(this.b.a, 0, min);
            ut utVar = this.b;
            int d3 = utVar.d();
            int c2 = utVar.c() - 1;
            while (true) {
                if (c2 < d3) {
                    j2 = -9223372036854775807L;
                    break;
                }
                if (utVar.a[c2] == 71) {
                    j2 = ho.a(utVar, c2, i);
                    if (j2 != -9223372036854775807L) {
                        break;
                    }
                }
                c2--;
            }
            this.g = j2;
            this.e = true;
            return 0;
        } else if (this.g == -9223372036854775807L) {
            return a(frVar);
        } else {
            if (!this.d) {
                int min2 = (int) Math.min(112800, frVar.d());
                if (frVar.c() != 0) {
                    fxVar.a = 0;
                    return 1;
                }
                this.b.a(min2);
                frVar.a();
                frVar.c(this.b.a, 0, min2);
                ut utVar2 = this.b;
                int d4 = utVar2.d();
                int c3 = utVar2.c();
                while (true) {
                    if (d4 >= c3) {
                        j = -9223372036854775807L;
                        break;
                    }
                    if (utVar2.a[d4] == 71) {
                        j = ho.a(utVar2, d4, i);
                        if (j != -9223372036854775807L) {
                            break;
                        }
                    }
                    d4++;
                }
                this.f = j;
                this.d = true;
                return 0;
            }
            long j4 = this.f;
            if (j4 == -9223372036854775807L) {
                return a(frVar);
            }
            this.h = this.a.b(this.g) - this.a.b(j4);
            return a(frVar);
        }
    }

    public final long b() {
        return this.h;
    }

    public final ve c() {
        return this.a;
    }

    private final int a(fr frVar) {
        ut utVar = this.b;
        byte[] bArr = vf.f;
        utVar.a(bArr, bArr.length);
        this.c = true;
        frVar.a();
        return 0;
    }
}
