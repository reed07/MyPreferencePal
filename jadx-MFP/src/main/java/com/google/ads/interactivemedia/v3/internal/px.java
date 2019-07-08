package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.io.IOException;
import java.util.List;

/* compiled from: IMASDK */
public final class px extends ln implements ra {
    private final pt a;
    private final Uri b;
    private final ps c;
    private final lh d;
    private final ti e;
    private final boolean f;
    private final qv g;
    private final Object h;
    private tz i;

    private px(Uri uri, ps psVar, pt ptVar, lh lhVar, ti tiVar, qv qvVar, boolean z, Object obj) {
        this.b = uri;
        this.c = psVar;
        this.a = ptVar;
        this.d = lhVar;
        this.e = tiVar;
        this.g = qvVar;
        this.f = z;
        this.h = obj;
    }

    public final void a(tz tzVar) {
        this.i = tzVar;
        this.g.a(this.b, a((lo) null), this);
    }

    public final void a() throws IOException {
        this.g.d();
    }

    public final ll a(lo loVar, sf sfVar) {
        qe qeVar = new qe(this.a, this.g, this.c, this.i, this.e, a(loVar), sfVar, this.d, this.f);
        return qeVar;
    }

    public final void a(ll llVar) {
        ((qe) llVar).g();
    }

    public final void b() {
        this.g.a();
    }

    public final void a(qp qpVar) {
        mw mwVar;
        long j;
        long j2;
        qp qpVar2 = qpVar;
        long a2 = qpVar2.j ? at.a(qpVar2.c) : -9223372036854775807L;
        long j3 = (qpVar2.a == 2 || qpVar2.a == 1) ? a2 : -9223372036854775807L;
        long j4 = qpVar2.b;
        if (this.g.e()) {
            long c2 = qpVar2.c - this.g.c();
            long j5 = qpVar2.i ? c2 + qpVar2.m : -9223372036854775807L;
            List<qq> list = qpVar2.l;
            if (j4 == -9223372036854775807L) {
                if (list.isEmpty()) {
                    j2 = 0;
                } else {
                    j2 = ((qq) list.get(Math.max(0, list.size() - 3))).e;
                }
                j = j2;
            } else {
                j = j4;
            }
            mw mwVar2 = new mw(j3, a2, j5, qpVar2.m, c2, j, true, !qpVar2.i, this.h);
            mwVar = mwVar2;
        } else {
            mwVar = new mw(j3, a2, qpVar2.m, qpVar2.m, 0, j4 == -9223372036854775807L ? 0 : j4, true, false, this.h);
        }
        a((cq) mwVar, (Object) new pv(this.g.b(), qpVar2));
    }

    /* synthetic */ px(Uri uri, ps psVar, pt ptVar, lh lhVar, ti tiVar, qv qvVar, boolean z, Object obj, byte b2) {
        this(uri, psVar, ptVar, lhVar, tiVar, qvVar, z, obj);
    }

    static {
        br.a("goog.exo.hls");
    }
}
