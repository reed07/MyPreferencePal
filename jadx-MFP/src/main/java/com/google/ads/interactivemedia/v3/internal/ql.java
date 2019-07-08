package com.google.ads.interactivemedia.v3.internal;

import android.os.SystemClock;
import java.io.IOException;

/* compiled from: IMASDK */
final class ql implements tl<tu<qr>>, Runnable {
    /* access modifiers changed from: private */
    public final qo a;
    private final tj b = new tj("DefaultHlsPlaylistTracker:MediaPlaylist");
    private final tu<qr> c;
    private qp d;
    private long e;
    private long f;
    private long g;
    /* access modifiers changed from: private */
    public long h;
    private boolean i;
    private IOException j;
    private final /* synthetic */ qj k;

    public ql(qj qjVar, qo qoVar) {
        this.k = qjVar;
        this.a = qoVar;
        this.c = new tu<>(qjVar.b.a(), qi.a(qjVar.m.n, qoVar.a), 4, qjVar.h);
    }

    public final qp a() {
        return this.d;
    }

    public final boolean b() {
        if (this.d == null) {
            return false;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long max = Math.max(30000, at.a(this.d.m));
        if (this.d.i || this.d.a == 2 || this.d.a == 1 || this.e + max > elapsedRealtime) {
            return true;
        }
        return false;
    }

    public final void c() {
        this.b.a((tp) null);
    }

    public final void d() {
        this.h = 0;
        if (!this.i && !this.b.b()) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime < this.g) {
                this.i = true;
                this.k.k.postDelayed(this, this.g - elapsedRealtime);
                return;
            }
            f();
        }
    }

    public final void e() throws IOException {
        this.b.a();
        IOException iOException = this.j;
        if (iOException != null) {
            throw iOException;
        }
    }

    public final void run() {
        this.i = false;
        f();
    }

    private final void f() {
        this.k.i.a(this.c.a, this.c.b, this.b.a(this.c, this, this.k.d.a(this.c.b)));
    }

    /* access modifiers changed from: private */
    public final void a(qp qpVar, long j2) {
        long j3;
        qp qpVar2 = this.d;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.e = elapsedRealtime;
        this.d = this.k.a(qpVar2, qpVar);
        qp qpVar3 = this.d;
        if (qpVar3 != qpVar2) {
            this.j = null;
            this.f = elapsedRealtime;
            this.k.a(this.a, qpVar3);
        } else if (!qpVar3.i) {
            if (qpVar.f + ((long) qpVar.l.size()) < this.d.f) {
                this.j = new qy(this.a.a);
                this.k.a(this.a, -9223372036854775807L);
            } else if (((double) (elapsedRealtime - this.f)) > ((double) at.a(this.d.h)) * this.k.g) {
                this.j = new qz(this.a.a);
                long a2 = this.k.d.a(this.j);
                this.k.a(this.a, a2);
                if (a2 != -9223372036854775807L) {
                    a(a2);
                }
            }
        }
        qp qpVar4 = this.d;
        if (qpVar4 != qpVar2) {
            j3 = qpVar4.h;
        } else {
            j3 = qpVar4.h / 2;
        }
        this.g = elapsedRealtime + at.a(j3);
        if (this.a == this.k.n && !this.d.i) {
            d();
        }
    }

    private final boolean a(long j2) {
        this.h = SystemClock.elapsedRealtime() + j2;
        return this.k.n == this.a && !this.k.f();
    }

    public final /* synthetic */ tm a(to toVar, long j2, long j3, IOException iOException, int i2) {
        tm tmVar;
        tm tmVar2;
        IOException iOException2 = iOException;
        tu tuVar = (tu) toVar;
        long a2 = this.k.d.a(iOException2);
        boolean z = a2 != -9223372036854775807L;
        boolean z2 = this.k.a(this.a, a2) || !z;
        if (z) {
            z2 |= a(a2);
        }
        if (z2) {
            long a3 = this.k.d.a(iOException2, i2);
            if (a3 != -9223372036854775807L) {
                tmVar2 = tj.a(false, a3);
            } else {
                tmVar2 = tj.c;
            }
            tmVar = tmVar2;
        } else {
            tmVar = tj.b;
        }
        this.k.i.a(tuVar.a, tuVar.e(), tuVar.f(), 4, j2, j3, tuVar.d(), iOException, !tmVar.a());
        return tmVar;
    }

    public final /* synthetic */ void a(to toVar, long j2, long j3, boolean z) {
        tu tuVar = (tu) toVar;
        this.k.i.b(tuVar.a, tuVar.e(), tuVar.f(), 4, j2, j3, tuVar.d());
    }

    public final /* synthetic */ void a(to toVar, long j2, long j3) {
        tu tuVar = (tu) toVar;
        qr qrVar = (qr) tuVar.c();
        if (qrVar instanceof qp) {
            long j4 = j3;
            a((qp) qrVar, j4);
            this.k.i.a(tuVar.a, tuVar.e(), tuVar.f(), 4, j2, j4, tuVar.d());
            return;
        }
        this.j = new ca("Loaded playlist has unexpected type.");
    }
}
