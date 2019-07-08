package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
public class fh {
    private final fi a;
    private final fm b;
    private fj c;
    private final int d = 940;

    protected fh(fk fkVar, fm fmVar, long j, long j2, long j3, long j4, long j5, long j6, int i) {
        this.b = fmVar;
        fi fiVar = new fi(fkVar, j, 0, j3, 0, j5, 188);
        this.a = fiVar;
    }

    public final fy a() {
        return this.a;
    }

    public final void a(long j) {
        long j2 = j;
        fj fjVar = this.c;
        if (fjVar == null || fjVar.d() != j2) {
            fj fjVar2 = r1;
            fj fjVar3 = new fj(j, this.a.b(j2), this.a.c, this.a.d, this.a.e, this.a.f, this.a.g);
            this.c = fjVar2;
        }
    }

    public final boolean b() {
        return this.c != null;
    }

    public final int a(fr frVar, fx fxVar, ho hoVar) throws InterruptedException, IOException {
        fm fmVar = (fm) qi.a(this.b);
        while (true) {
            fj fjVar = (fj) qi.a(this.c);
            long b2 = fjVar.a();
            long c2 = fjVar.b();
            long d2 = fjVar.e();
            if (c2 - b2 <= ((long) this.d)) {
                a(false, b2);
                return a(frVar, b2, fxVar);
            } else if (!a(frVar, d2)) {
                return a(frVar, d2, fxVar);
            } else {
                frVar.a();
                fl a2 = fmVar.a(frVar, fjVar.c());
                switch (a2.b) {
                    case -3:
                        a(false, d2);
                        return a(frVar, d2, fxVar);
                    case -2:
                        fjVar.a(a2.c, a2.d);
                        break;
                    case -1:
                        fjVar.b(a2.c, a2.d);
                        break;
                    case 0:
                        a(true, a2.d);
                        a(frVar, a2.d);
                        return a(frVar, a2.d, fxVar);
                    default:
                        throw new IllegalStateException("Invalid case");
                }
            }
        }
    }

    private final void a(boolean z, long j) {
        this.c = null;
        this.b.a();
    }

    private static boolean a(fr frVar, long j) throws IOException, InterruptedException {
        long c2 = j - frVar.c();
        if (c2 < 0 || c2 > 262144) {
            return false;
        }
        frVar.b((int) c2);
        return true;
    }

    private static int a(fr frVar, long j, fx fxVar) {
        if (j == frVar.c()) {
            return 0;
        }
        fxVar.a = j;
        return 1;
    }
}
