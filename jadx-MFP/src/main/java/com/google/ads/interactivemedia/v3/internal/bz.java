package com.google.ads.interactivemedia.v3.internal;

import android.util.Pair;

/* compiled from: IMASDK */
final class bz {
    private final cs a = new cs();
    private final ct b = new ct();
    private long c;
    private cq d = cq.a;
    private int e;
    private boolean f;
    private bx g;
    private bx h;
    private bx i;
    private int j;
    private Object k;
    private long l;

    public final void a(cq cqVar) {
        this.d = cqVar;
    }

    public final boolean a(int i2) {
        this.e = i2;
        return i();
    }

    public final boolean a(boolean z) {
        this.f = z;
        return i();
    }

    public final boolean a(ll llVar) {
        bx bxVar = this.i;
        return bxVar != null && bxVar.a == llVar;
    }

    public final void a(long j2) {
        bx bxVar = this.i;
        if (bxVar != null) {
            bxVar.c(j2);
        }
    }

    public final boolean a() {
        bx bxVar = this.i;
        return bxVar == null || (!bxVar.f.g && this.i.c() && this.i.f.e != -9223372036854775807L && this.j < 100);
    }

    public final by a(long j2, cb cbVar) {
        bx bxVar = this.i;
        if (bxVar != null) {
            return a(bxVar, j2);
        }
        return a(cbVar.c, cbVar.e, cbVar.d);
    }

    public final ll a(cj[] cjVarArr, rz rzVar, sf sfVar, ln lnVar, by byVar) {
        long j2;
        bx bxVar = this.i;
        if (bxVar == null) {
            j2 = byVar.b;
        } else {
            j2 = bxVar.a() + this.i.f.e;
        }
        bx bxVar2 = new bx(cjVarArr, j2, rzVar, sfVar, lnVar, byVar);
        if (this.i != null) {
            qi.c(f());
            this.i.a(bxVar2);
        }
        this.k = null;
        this.i = bxVar2;
        this.j++;
        return bxVar2.a;
    }

    public final bx b() {
        return this.i;
    }

    public final bx c() {
        return this.g;
    }

    public final bx d() {
        return this.h;
    }

    public final bx e() {
        return f() ? this.g : this.i;
    }

    public final boolean f() {
        return this.g != null;
    }

    public final bx g() {
        bx bxVar = this.h;
        qi.c((bxVar == null || bxVar.f() == null) ? false : true);
        this.h = this.h.f();
        return this.h;
    }

    public final bx h() {
        bx bxVar = this.g;
        if (bxVar != null) {
            if (bxVar == this.h) {
                this.h = bxVar.f();
            }
            this.g.e();
            this.j--;
            if (this.j == 0) {
                this.i = null;
                this.k = this.g.b;
                this.l = this.g.f.a.d;
            }
            this.g = this.g.f();
        } else {
            bx bxVar2 = this.i;
            this.g = bxVar2;
            this.h = bxVar2;
        }
        return this.g;
    }

    public final boolean a(bx bxVar) {
        boolean z = false;
        qi.c(bxVar != null);
        this.i = bxVar;
        while (bxVar.f() != null) {
            bxVar = bxVar.f();
            if (bxVar == this.h) {
                this.h = this.g;
                z = true;
            }
            bxVar.e();
            this.j--;
        }
        this.i.a((bx) null);
        return z;
    }

    public final void b(boolean z) {
        bx e2 = e();
        if (e2 != null) {
            this.k = z ? e2.b : null;
            this.l = e2.f.a.d;
            e2.e();
            a(e2);
        } else if (!z) {
            this.k = null;
        }
        this.g = null;
        this.i = null;
        this.h = null;
        this.j = 0;
    }

    public final boolean a(long j2, long j3) {
        by byVar;
        by byVar2;
        long j4;
        bx e2 = e();
        bx bxVar = null;
        while (true) {
            bx bxVar2 = bxVar;
            bxVar = e2;
            bx bxVar3 = bxVar2;
            if (bxVar == null) {
                return true;
            }
            by byVar3 = bxVar.f;
            if (bxVar3 == null) {
                byVar = a(byVar3);
                long j5 = j2;
            } else {
                by a2 = a(bxVar3, j2);
                if (a2 != null) {
                    if (byVar3.b == a2.b && byVar3.a.equals(a2.a)) {
                        byVar = a2;
                    } else if (!a(bxVar3)) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (!a(bxVar3)) {
                    return true;
                } else {
                    return false;
                }
            }
            long j6 = byVar3.c;
            if (j6 == byVar.c) {
                byVar2 = byVar;
            } else {
                by byVar4 = new by(byVar.a, byVar.b, j6, byVar.d, byVar.e, byVar.f, byVar.g);
                byVar2 = byVar4;
            }
            bxVar.f = byVar2;
            long j7 = byVar3.e;
            if (!(j7 == -9223372036854775807L || j7 == byVar.e)) {
                if (byVar.e == -9223372036854775807L) {
                    j4 = Long.MAX_VALUE;
                } else {
                    j4 = bxVar.a(byVar.e);
                }
                boolean z = bxVar == this.h && (j3 == Long.MIN_VALUE || j3 >= j4);
                if (a(bxVar) || z) {
                    return false;
                }
                return true;
            }
            e2 = bxVar.f();
        }
    }

    public final by a(by byVar) {
        long j2;
        lo loVar = byVar.a;
        boolean a2 = a(loVar);
        boolean a3 = a(loVar, a2);
        this.d.a(byVar.a.a, this.a);
        if (loVar.a()) {
            j2 = this.a.c(loVar.b, loVar.c);
        } else if (byVar.d == -9223372036854775807L || byVar.d == Long.MIN_VALUE) {
            j2 = this.a.c;
        } else {
            j2 = byVar.d;
        }
        by byVar2 = new by(loVar, byVar.b, byVar.c, byVar.d, j2, a2, a3);
        return byVar2;
    }

    public final lo a(Object obj, long j2) {
        long j3;
        int i2 = this.d.a(obj, this.a).b;
        Object obj2 = this.k;
        if (obj2 != null) {
            int a2 = this.d.a(obj2);
            if (a2 != -1 && this.d.a(a2, this.a, false).b == i2) {
                j3 = this.l;
                return a(obj, j2, j3);
            }
        }
        bx e2 = e();
        while (true) {
            if (e2 == null) {
                bx e3 = e();
                while (true) {
                    if (e3 == null) {
                        long j4 = this.c;
                        this.c = 1 + j4;
                        j3 = j4;
                        break;
                    }
                    int a3 = this.d.a(e3.b);
                    if (a3 != -1 && this.d.a(a3, this.a, false).b == i2) {
                        j3 = e3.f.a.d;
                        break;
                    }
                    e3 = e3.f();
                }
            } else if (e2.b.equals(obj)) {
                j3 = e2.f.a.d;
                break;
            } else {
                e2 = e2.f();
            }
        }
        return a(obj, j2, j3);
    }

    private final lo a(Object obj, long j2, long j3) {
        this.d.a(obj, this.a);
        int a2 = this.a.a(j2);
        if (a2 == -1) {
            return new lo(obj, j3, this.a.b(j2));
        }
        lo loVar = new lo(obj, a2, this.a.b(a2), j3);
        return loVar;
    }

    private final boolean i() {
        bx e2 = e();
        if (e2 == null) {
            return true;
        }
        int a2 = this.d.a(e2.b);
        while (true) {
            a2 = this.d.a(a2, this.a, this.b, this.e, this.f);
            while (e2.f() != null && !e2.f.f) {
                e2 = e2.f();
            }
            bx f2 = e2.f();
            if (a2 == -1 || f2 == null || this.d.a(f2.b) != a2) {
                boolean a3 = a(e2);
                e2.f = a(e2.f);
            } else {
                e2 = f2;
            }
        }
        boolean a32 = a(e2);
        e2.f = a(e2.f);
        if (!a32 || !f()) {
            return true;
        }
        return false;
    }

    private final by a(bx bxVar, long j2) {
        long j3;
        long j4;
        Object obj;
        by byVar = bxVar.f;
        long a2 = (bxVar.a() + byVar.e) - j2;
        long j5 = 0;
        if (byVar.f) {
            int a3 = this.d.a(byVar.a.a);
            int a4 = this.d.a(a3, this.a, this.b, this.e, this.f);
            if (a4 == -1) {
                return null;
            }
            int i2 = this.d.a(a4, this.a, true).b;
            Object obj2 = this.a.a;
            int i3 = i2;
            long j6 = byVar.a.d;
            if (this.d.a(i3, this.b, false, 0).b == a4) {
                Pair a5 = this.d.a(this.b, this.a, i2, -9223372036854775807L, Math.max(0, a2));
                if (a5 == null) {
                    return null;
                }
                Object obj3 = a5.first;
                long longValue = ((Long) a5.second).longValue();
                bx f2 = bxVar.f();
                if (f2 == null || !f2.b.equals(obj3)) {
                    long j7 = this.c;
                    this.c = 1 + j7;
                    j5 = longValue;
                    j4 = j7;
                    obj = obj3;
                } else {
                    j5 = longValue;
                    j4 = f2.f.a.d;
                    obj = obj3;
                }
            } else {
                obj = obj2;
                j4 = j6;
            }
            long j8 = j5;
            return a(a(obj, j8, j4), j8, j5);
        }
        lo loVar = byVar.a;
        this.d.a(loVar.a, this.a);
        if (loVar.a()) {
            int i4 = loVar.b;
            int c2 = this.a.c(i4);
            if (c2 == -1) {
                return null;
            }
            int a6 = this.a.a(i4, loVar.c);
            if (a6 >= c2) {
                long j9 = byVar.c;
                if (this.a.c() == 1 && this.a.a(0) == 0) {
                    cq cqVar = this.d;
                    ct ctVar = this.b;
                    cs csVar = this.a;
                    Pair a7 = cqVar.a(ctVar, csVar, csVar.b, -9223372036854775807L, Math.max(0, a2));
                    if (a7 == null) {
                        return null;
                    }
                    j3 = ((Long) a7.second).longValue();
                } else {
                    j3 = j9;
                }
                return b(loVar.a, j3, loVar.d);
            } else if (!this.a.b(i4, a6)) {
                return null;
            } else {
                return a(loVar.a, i4, a6, byVar.c, loVar.d);
            }
        } else {
            int a8 = this.a.a(byVar.d);
            if (a8 == -1) {
                return b(loVar.a, byVar.e, loVar.d);
            }
            int b2 = this.a.b(a8);
            if (!this.a.b(a8, b2)) {
                return null;
            }
            return a(loVar.a, a8, b2, byVar.e, loVar.d);
        }
    }

    private final by a(lo loVar, long j2, long j3) {
        this.d.a(loVar.a, this.a);
        if (!loVar.a()) {
            return b(loVar.a, j3, loVar.d);
        } else if (!this.a.b(loVar.b, loVar.c)) {
            return null;
        } else {
            return a(loVar.a, loVar.b, loVar.c, j2, loVar.d);
        }
    }

    private final by a(Object obj, int i2, int i3, long j2, long j3) {
        lo loVar = new lo(obj, i2, i3, j3);
        by byVar = new by(loVar, i3 == this.a.b(i2) ? this.a.d() : 0, j2, -9223372036854775807L, this.d.a(loVar.a, this.a).c(loVar.b, loVar.c), false, false);
        return byVar;
    }

    private final by b(Object obj, long j2, long j3) {
        long j4;
        int b2 = this.a.b(j2);
        Object obj2 = obj;
        lo loVar = new lo(obj, j3, b2);
        boolean a2 = a(loVar);
        boolean a3 = a(loVar, a2);
        long a4 = b2 != -1 ? this.a.a(b2) : -9223372036854775807L;
        if (a4 == -9223372036854775807L || a4 == Long.MIN_VALUE) {
            j4 = this.a.c;
        } else {
            j4 = a4;
        }
        by byVar = new by(loVar, j2, -9223372036854775807L, a4, j4, a2, a3);
        return byVar;
    }

    private static boolean a(lo loVar) {
        return !loVar.a() && loVar.e == -1;
    }

    private final boolean a(lo loVar, boolean z) {
        int a2 = this.d.a(loVar.a);
        if (this.d.a(this.d.a(a2, this.a, false).b, this.b, false, 0).a || this.d.a(a2, this.a, this.b, this.e, this.f) != -1 || !z) {
            return false;
        }
        return true;
    }
}
