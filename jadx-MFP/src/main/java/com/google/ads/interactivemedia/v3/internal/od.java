package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;

/* compiled from: IMASDK */
public final class od {
    private final nw a;
    private final so b;
    private tv<? extends tc> c;
    private lh d;
    private ti e;
    private long f;

    public od(so soVar) {
        this(new nw(soVar), soVar);
    }

    private od(nw nwVar, so soVar) {
        this.a = (nw) qi.a(nwVar);
        this.b = soVar;
        this.e = new ti();
        this.f = 30000;
        this.d = new lh();
    }

    public final nz a(Uri uri) {
        if (this.c == null) {
            this.c = new ot();
        }
        nz nzVar = new nz(null, (Uri) qi.a(uri), this.b, this.c, this.a, this.d, this.e, this.f, false, null, 0);
        return nzVar;
    }
}
