package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;

/* compiled from: IMASDK */
public final class py {
    private final ps a;
    private pt b;
    private qu c;
    private qw d;
    private lh e;
    private ti f;

    public py(so soVar) {
        this(new ps(soVar));
    }

    private py(ps psVar) {
        this.a = (ps) qi.a(psVar);
        this.c = new qi();
        this.d = qj.a;
        this.b = pt.a;
        this.f = new ti();
        this.e = new lh();
    }

    public final px a(Uri uri) {
        ps psVar = this.a;
        pt ptVar = this.b;
        lh lhVar = this.e;
        ti tiVar = this.f;
        px pxVar = new px(uri, psVar, ptVar, lhVar, tiVar, this.d.a(psVar, tiVar, this.c), false, null, 0);
        return pxVar;
    }
}
