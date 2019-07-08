package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: IMASDK */
public final class ik implements ic {
    private final ut a = new ut(10);
    private gc b;
    private boolean c;
    private long d;
    private int e;
    private int f;

    public final void a() {
        this.c = false;
    }

    public final void a(fs fsVar, jd jdVar) {
        jdVar.a();
        this.b = fsVar.a(jdVar.b(), 4);
        this.b.a(bs.a(jdVar.c(), MimeTypes.APPLICATION_ID3, (String) null, -1, (fa) null));
    }

    public final void a(long j, int i) {
        if ((i & 4) != 0) {
            this.c = true;
            this.d = j;
            this.e = 0;
            this.f = 0;
        }
    }

    public final void a(ut utVar) {
        if (this.c) {
            int b2 = utVar.b();
            int i = this.f;
            if (i < 10) {
                int min = Math.min(b2, 10 - i);
                System.arraycopy(utVar.a, utVar.d(), this.a.a, this.f, min);
                if (this.f + min == 10) {
                    this.a.c(0);
                    if (73 == this.a.e() && 68 == this.a.e() && 51 == this.a.e()) {
                        this.a.d(3);
                        this.e = this.a.o() + 10;
                    } else {
                        Log.w("Id3Reader", "Discarding invalid ID3 tag");
                        this.c = false;
                        return;
                    }
                }
            }
            int min2 = Math.min(b2, this.e - this.f);
            this.b.a(utVar, min2);
            this.f += min2;
        }
    }

    public final void b() {
        if (this.c) {
            int i = this.e;
            if (i != 0 && this.f == i) {
                this.b.a(this.d, 1, i, 0, null);
                this.c = false;
            }
        }
    }
}
