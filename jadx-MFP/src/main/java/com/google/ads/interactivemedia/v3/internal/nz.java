package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import java.io.IOException;
import java.util.Map;

/* compiled from: IMASDK */
public final class nz extends ln {
    private long A;
    private long B;
    private long C;
    private int D;
    private long E;
    private int F;
    private final boolean a;
    private final so b;
    private final nw c;
    private final lh d;
    private final ti e;
    private final long f;
    private final boolean g;
    private final lr h;
    private final tv<? extends tc> i;
    private final of j;
    private final Object k;
    private final SparseArray<nx> l;
    private final Runnable m;
    private final Runnable n;
    private final or o;
    private final ts p;
    private final Object q;
    private sn r;
    /* access modifiers changed from: private */
    public tj s;
    private tz t;
    /* access modifiers changed from: private */
    public IOException u;
    private Handler v;
    private Uri w;
    private Uri x;
    private tc y;
    private boolean z;

    private nz(tc tcVar, Uri uri, so soVar, tv<? extends tc> tvVar, nw nwVar, lh lhVar, ti tiVar, long j2, boolean z2, Object obj) {
        this.w = uri;
        this.y = tcVar;
        this.x = uri;
        this.b = soVar;
        this.i = tvVar;
        this.c = nwVar;
        this.e = tiVar;
        this.f = j2;
        this.g = z2;
        this.d = lhVar;
        this.q = obj;
        this.a = tcVar != null;
        this.h = a((lo) null);
        this.k = new Object();
        this.l = new SparseArray<>();
        this.o = new or(this, 0);
        this.E = -9223372036854775807L;
        if (this.a) {
            qi.c(!tcVar.d);
            this.j = null;
            this.m = null;
            this.n = null;
            this.p = new tt();
            return;
        }
        this.j = new of(this, 0);
        this.p = new og(this);
        this.m = new oa(this);
        this.n = new ob(this);
    }

    public final void a(tz tzVar) {
        this.t = tzVar;
        if (this.a) {
            a(false);
            return;
        }
        this.r = this.b.a();
        this.s = new tj("Loader:DashMediaSource");
        this.v = new Handler();
        e();
    }

    public final void a() throws IOException {
        this.p.a();
    }

    public final ll a(lo loVar, sf sfVar) {
        lo loVar2 = loVar;
        int intValue = ((Integer) loVar2.a).intValue() - this.F;
        lr a2 = a(loVar2, this.y.a(intValue).b);
        nx nxVar = new nx(this.F + intValue, this.y, intValue, this.c, this.t, this.e, a2, this.C, this.p, sfVar, this.d, this.o);
        this.l.put(nxVar.a, nxVar);
        return nxVar;
    }

    public final void a(ll llVar) {
        nx nxVar = (nx) llVar;
        nxVar.f();
        this.l.remove(nxVar.a);
    }

    public final void b() {
        this.z = false;
        this.r = null;
        tj tjVar = this.s;
        if (tjVar != null) {
            tjVar.a((tp) null);
            this.s = null;
        }
        this.A = 0;
        this.B = 0;
        this.y = this.a ? this.y : null;
        this.x = this.w;
        this.u = null;
        Handler handler = this.v;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.v = null;
        }
        this.C = 0;
        this.D = 0;
        this.E = -9223372036854775807L;
        this.F = 0;
        this.l.clear();
    }

    /* access modifiers changed from: 0000 */
    public final void c() {
        this.v.removeCallbacks(this.n);
        e();
    }

    /* access modifiers changed from: 0000 */
    public final void a(long j2) {
        long j3 = this.E;
        if (j3 == -9223372036854775807L || j3 < j2) {
            this.E = j2;
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(tu<tc> tuVar, long j2, long j3) {
        boolean z2;
        tu<tc> tuVar2 = tuVar;
        long j4 = j2;
        this.h.a(tuVar2.a, tuVar.e(), tuVar.f(), tuVar2.b, j2, j3, tuVar.d());
        tc tcVar = (tc) tuVar.c();
        tc tcVar2 = this.y;
        int a2 = tcVar2 == null ? 0 : tcVar2.a();
        long j5 = tcVar.a(0).b;
        int i2 = 0;
        while (i2 < a2 && this.y.a(i2).b < j5) {
            i2++;
        }
        if (tcVar.d) {
            if (a2 - i2 > tcVar.a()) {
                Log.w("DashMediaSource", "Loaded out of sync manifest");
                z2 = true;
            } else if (this.E == -9223372036854775807L || tcVar.h * 1000 > this.E) {
                z2 = false;
            } else {
                long j6 = tcVar.h;
                long j7 = this.E;
                StringBuilder sb = new StringBuilder(73);
                sb.append("Loaded stale dynamic manifest: ");
                sb.append(j6);
                sb.append(", ");
                sb.append(j7);
                Log.w("DashMediaSource", sb.toString());
                z2 = true;
            }
            if (z2) {
                int i3 = this.D;
                this.D = i3 + 1;
                if (i3 < this.e.a(tuVar2.b)) {
                    c((long) Math.min((this.D - 1) * 1000, 5000));
                    return;
                } else {
                    this.u = new ahd();
                    return;
                }
            } else {
                this.D = 0;
            }
        }
        this.y = tcVar;
        this.z &= this.y.d;
        this.A = j4 - j3;
        this.B = j4;
        if (this.y.j != null) {
            synchronized (this.k) {
                if (tuVar2.a.a == this.x) {
                    this.x = this.y.j;
                }
            }
        }
        if (a2 != 0) {
            this.F += i2;
            a(true);
        } else if (!this.y.d || this.y.i == null) {
            a(true);
        } else {
            pj pjVar = this.y.i;
            String str = pjVar.a;
            if (vf.a((Object) str, (Object) "urn:mpeg:dash:utc:direct:2014") || vf.a((Object) str, (Object) "urn:mpeg:dash:utc:direct:2012")) {
                a(pjVar);
            } else if (vf.a((Object) str, (Object) "urn:mpeg:dash:utc:http-iso:2014") || vf.a((Object) str, (Object) "urn:mpeg:dash:utc:http-iso:2012")) {
                a(pjVar, (tv<Long>) new oe<Long>());
            } else if (vf.a((Object) str, (Object) "urn:mpeg:dash:utc:http-xsdate:2014") || vf.a((Object) str, (Object) "urn:mpeg:dash:utc:http-xsdate:2012")) {
                a(pjVar, (tv<Long>) new oj<Long>(0));
            } else {
                a(new IOException("Unsupported UTC timing scheme"));
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final tm a(tu<tc> tuVar, long j2, long j3, IOException iOException) {
        tu<tc> tuVar2 = tuVar;
        IOException iOException2 = iOException;
        boolean z2 = iOException2 instanceof ca;
        this.h.a(tuVar2.a, tuVar.e(), tuVar.f(), tuVar2.b, j2, j3, tuVar.d(), iOException2, z2);
        return z2 ? tj.c : tj.a;
    }

    /* access modifiers changed from: 0000 */
    public final void b(tu<Long> tuVar, long j2, long j3) {
        tu<Long> tuVar2 = tuVar;
        this.h.a(tuVar2.a, tuVar.e(), tuVar.f(), tuVar2.b, j2, j3, tuVar.d());
        b(((Long) tuVar.c()).longValue() - j2);
    }

    /* access modifiers changed from: 0000 */
    public final tm b(tu<Long> tuVar, long j2, long j3, IOException iOException) {
        tu<Long> tuVar2 = tuVar;
        lr lrVar = this.h;
        sr srVar = tuVar2.a;
        Uri e2 = tuVar.e();
        Map f2 = tuVar.f();
        int i2 = tuVar2.b;
        lrVar.a(srVar, e2, f2, i2, j2, j3, tuVar.d(), iOException, true);
        a(iOException);
        return tj.b;
    }

    /* access modifiers changed from: 0000 */
    public final void c(tu<?> tuVar, long j2, long j3) {
        tu<?> tuVar2 = tuVar;
        this.h.b(tuVar2.a, tuVar.e(), tuVar.f(), tuVar2.b, j2, j3, tuVar.d());
    }

    private final void a(pj pjVar) {
        try {
            b(vf.g(pjVar.b) - this.B);
        } catch (ca e2) {
            a((IOException) e2);
        }
    }

    private final void a(pj pjVar, tv<Long> tvVar) {
        a(new tu<>(this.r, Uri.parse(pjVar.b), 5, tvVar), (tl<tu<T>>) new oi<tu<T>>(this, 0), 1);
    }

    private final void b(long j2) {
        this.C = j2;
        a(true);
    }

    private final void a(IOException iOException) {
        uk.b("DashMediaSource", "Failed to resolve UtcTiming element.", iOException);
        a(true);
    }

    private final void a(boolean z2) {
        long j2;
        boolean z3;
        long j3;
        long j4;
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            int keyAt = this.l.keyAt(i2);
            if (keyAt >= this.F) {
                ((nx) this.l.valueAt(i2)).a(this.y, keyAt - this.F);
            }
        }
        int a2 = this.y.a() - 1;
        oh a3 = oh.a(this.y.a(0), this.y.c(0));
        oh a4 = oh.a(this.y.a(a2), this.y.c(a2));
        long j5 = a3.b;
        long j6 = a4.c;
        if (!this.y.d || a4.a) {
            j2 = j5;
            z3 = false;
        } else {
            if (this.C != 0) {
                j4 = at.b(SystemClock.elapsedRealtime() + this.C);
            } else {
                j4 = at.b(System.currentTimeMillis());
            }
            j6 = Math.min((j4 - at.b(this.y.a)) - at.b(this.y.a(a2).b), j6);
            if (this.y.f != -9223372036854775807L) {
                long b2 = j6 - at.b(this.y.f);
                while (b2 < 0 && a2 > 0) {
                    a2--;
                    b2 += this.y.c(a2);
                }
                if (a2 == 0) {
                    j5 = Math.max(j5, b2);
                } else {
                    j5 = this.y.c(0);
                }
            }
            j2 = j5;
            z3 = true;
        }
        long j7 = j6 - j2;
        for (int i3 = 0; i3 < this.y.a() - 1; i3++) {
            j7 += this.y.c(i3);
        }
        if (this.y.d) {
            long j8 = this.f;
            if (!this.g && this.y.g != -9223372036854775807L) {
                j8 = this.y.g;
            }
            long b3 = j7 - at.b(j8);
            j3 = b3 < 5000000 ? Math.min(5000000, j7 / 2) : b3;
        } else {
            j3 = 0;
        }
        oc ocVar = new oc(this.y.a, this.y.a + this.y.a(0).b + at.a(j2), this.F, j2, j7, j3, this.y, this.q);
        a((cq) ocVar, (Object) this.y);
        if (!this.a) {
            this.v.removeCallbacks(this.n);
            if (z3) {
                this.v.postDelayed(this.n, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
            }
            if (this.z) {
                e();
            } else if (z2 && this.y.d && this.y.e != -9223372036854775807L) {
                long j9 = this.y.e;
                if (j9 == 0) {
                    j9 = 5000;
                }
                c(Math.max(0, (this.A + j9) - SystemClock.elapsedRealtime()));
            }
        }
    }

    private final void c(long j2) {
        this.v.postDelayed(this.m, j2);
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public final void e() {
        Uri uri;
        this.v.removeCallbacks(this.m);
        if (this.s.b()) {
            this.z = true;
            return;
        }
        synchronized (this.k) {
            uri = this.x;
        }
        this.z = false;
        a(new tu<>(this.r, uri, 4, this.i), (tl<tu<T>>) this.j, this.e.a(4));
    }

    private final <T> void a(tu<T> tuVar, tl<tu<T>> tlVar, int i2) {
        this.h.a(tuVar.a, tuVar.b, this.s.a(tuVar, tlVar, i2));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void d() {
        a(false);
    }

    /* synthetic */ nz(tc tcVar, Uri uri, so soVar, tv tvVar, nw nwVar, lh lhVar, ti tiVar, long j2, boolean z2, Object obj, byte b2) {
        this(null, uri, soVar, tvVar, nwVar, lhVar, tiVar, j2, z2, obj);
    }

    static {
        br.a("goog.exo.dash");
    }
}
