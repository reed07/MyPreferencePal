package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: IMASDK */
public final class is implements ip {
    private ve a;
    private gc b;
    private boolean c;

    public final void a(ve veVar, fs fsVar, jd jdVar) {
        this.a = veVar;
        jdVar.a();
        this.b = fsVar.a(jdVar.b(), 4);
        this.b.a(bs.a(jdVar.c(), MimeTypes.APPLICATION_SCTE35, (String) null, -1, (fa) null));
    }

    public final void a(ut utVar) {
        if (!this.c) {
            if (this.a.c() != -9223372036854775807L) {
                this.b.a(bs.a(null, MimeTypes.APPLICATION_SCTE35, this.a.c()));
                this.c = true;
            } else {
                return;
            }
        }
        int b2 = utVar.b();
        this.b.a(utVar, b2);
        this.b.a(this.a.b(), 1, b2, 0, null);
    }
}
