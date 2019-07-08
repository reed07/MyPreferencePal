package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Collections;
import java.util.List;

/* compiled from: IMASDK */
public final class ib implements ic {
    private final List<ja> a;
    private final gc[] b;
    private boolean c;
    private int d;
    private int e;
    private long f;

    public ib(List<ja> list) {
        this.a = list;
        this.b = new gc[list.size()];
    }

    public final void a() {
        this.c = false;
    }

    public final void a(fs fsVar, jd jdVar) {
        for (int i = 0; i < this.b.length; i++) {
            ja jaVar = (ja) this.a.get(i);
            jdVar.a();
            gc a2 = fsVar.a(jdVar.b(), 3);
            a2.a(bs.a(jdVar.c(), MimeTypes.APPLICATION_DVBSUBS, (String) null, -1, 0, Collections.singletonList(jaVar.b), jaVar.a, (fa) null));
            this.b[i] = a2;
        }
    }

    public final void a(long j, int i) {
        if ((i & 4) != 0) {
            this.c = true;
            this.f = j;
            this.e = 0;
            this.d = 2;
        }
    }

    public final void b() {
        if (this.c) {
            for (gc a2 : this.b) {
                a2.a(this.f, 1, this.e, 0, null);
            }
            this.c = false;
        }
    }

    public final void a(ut utVar) {
        gc[] gcVarArr;
        if (this.c && (this.d != 2 || a(utVar, 32))) {
            if (this.d != 1 || a(utVar, 0)) {
                int d2 = utVar.d();
                int b2 = utVar.b();
                for (gc gcVar : this.b) {
                    utVar.c(d2);
                    gcVar.a(utVar, b2);
                }
                this.e += b2;
            }
        }
    }

    private final boolean a(ut utVar, int i) {
        if (utVar.b() == 0) {
            return false;
        }
        if (utVar.e() != i) {
            this.c = false;
        }
        this.d--;
        return this.c;
    }
}
