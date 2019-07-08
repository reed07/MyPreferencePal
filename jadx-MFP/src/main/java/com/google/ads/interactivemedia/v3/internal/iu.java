package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class iu implements fm {
    private final ve a;
    private final ut b = new ut();
    private final int c;

    public iu(int i, ve veVar) {
        this.c = i;
        this.a = veVar;
    }

    public final fl a(fr frVar, long j) throws IOException, InterruptedException {
        long c2 = frVar.c();
        int min = (int) Math.min(112800, frVar.d() - c2);
        this.b.a(min);
        frVar.c(this.b.a, 0, min);
        ut utVar = this.b;
        int c3 = utVar.c();
        long j2 = -1;
        long j3 = -1;
        long j4 = -9223372036854775807L;
        while (utVar.b() >= 188) {
            int a2 = ho.a(utVar.a, utVar.d(), c3);
            int i = a2 + 188;
            if (i > c3) {
                break;
            }
            long a3 = ho.a(utVar, a2, this.c);
            if (a3 != -9223372036854775807L) {
                long b2 = this.a.b(a3);
                if (b2 > j) {
                    if (j4 == -9223372036854775807L) {
                        return fl.a(b2, c2);
                    }
                    return fl.a(c2 + j3);
                } else if (100000 + b2 > j) {
                    return fl.a(c2 + ((long) a2));
                } else {
                    j3 = (long) a2;
                    j4 = b2;
                }
            }
            utVar.c(i);
            j2 = (long) i;
        }
        if (j4 != -9223372036854775807L) {
            return fl.b(j4, c2 + j2);
        }
        return fl.a;
    }

    public final void a() {
        ut utVar = this.b;
        byte[] bArr = vf.f;
        utVar.a(bArr, bArr.length);
    }
}
