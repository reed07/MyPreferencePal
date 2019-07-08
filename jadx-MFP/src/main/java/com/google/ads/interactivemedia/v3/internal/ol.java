package com.google.ads.interactivemedia.v3.internal;

import android.os.SystemClock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: IMASDK */
public final class ol implements no {
    private final ts a;
    private final int[] b;
    private final rt c;
    private final int d;
    private final sn e;
    private final long f;
    private final int g;
    private final os h;
    private final om[] i;
    private tc j;
    private int k;
    private IOException l;
    private boolean m;
    private long n = -9223372036854775807L;

    public ol(ts tsVar, tc tcVar, int i2, int[] iArr, rt rtVar, int i3, sn snVar, long j2, int i4, boolean z, boolean z2, os osVar) {
        rt rtVar2 = rtVar;
        this.a = tsVar;
        this.j = tcVar;
        this.b = iArr;
        this.c = rtVar2;
        this.d = i3;
        this.e = snVar;
        this.k = i2;
        this.f = j2;
        this.g = i4;
        this.h = osVar;
        long c2 = tcVar.c(i2);
        ArrayList b2 = b();
        this.i = new om[rtVar.g()];
        for (int i5 = 0; i5 < this.i.length; i5++) {
            oy oyVar = (oy) b2.get(rtVar2.b(i5));
            om[] omVarArr = this.i;
            om omVar = new om(c2, i3, oyVar, z, z2, osVar);
            omVarArr[i5] = omVar;
        }
    }

    public final long a(long j2, cm cmVar) {
        om[] omVarArr = this.i;
        int length = omVarArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            om omVar = omVarArr[i2];
            if (omVar.c != null) {
                long c2 = omVar.c(j2);
                long a2 = omVar.a(c2);
                return vf.a(j2, cmVar, a2, (a2 >= j2 || c2 >= ((long) (omVar.b() + -1))) ? a2 : omVar.a(c2 + 1));
            }
        }
        return j2;
    }

    public final void a(tc tcVar, int i2) {
        try {
            this.j = tcVar;
            this.k = i2;
            long c2 = this.j.c(this.k);
            ArrayList b2 = b();
            for (int i3 = 0; i3 < this.i.length; i3++) {
                this.i[i3] = this.i[i3].a(c2, (oy) b2.get(this.c.b(i3)));
            }
        } catch (ld e2) {
            this.l = e2;
        }
    }

    public final void a() throws IOException {
        IOException iOException = this.l;
        if (iOException == null) {
            this.a.a();
            return;
        }
        throw iOException;
    }

    public final int a(long j2, List<? extends ns> list) {
        if (this.l != null || this.c.g() < 2) {
            return list.size();
        }
        return this.c.a(j2, list);
    }

    public final void a(long j2, long j3, List<? extends ns> list, nk nkVar) {
        long j4;
        ns nsVar;
        np npVar;
        long j5;
        nt[] ntVarArr;
        int i2;
        nk nkVar2 = nkVar;
        if (this.l == null) {
            long j6 = j3 - j2;
            boolean z = false;
            long j7 = this.j.d && (this.n > -9223372036854775807L ? 1 : (this.n == -9223372036854775807L ? 0 : -1)) != 0 ? this.n - j2 : -9223372036854775807L;
            long b2 = at.b(this.j.a) + at.b(this.j.a(this.k).b) + j3;
            os osVar = this.h;
            if (osVar == null || !osVar.a.a(b2)) {
                if (this.f != 0) {
                    j4 = (SystemClock.elapsedRealtime() + this.f) * 1000;
                } else {
                    j4 = System.currentTimeMillis() * 1000;
                }
                if (list.isEmpty()) {
                    List<? extends ns> list2 = list;
                    nsVar = null;
                } else {
                    nsVar = (ns) list.get(list.size() - 1);
                }
                nt[] ntVarArr2 = new nt[this.c.g()];
                int i3 = 0;
                while (i3 < ntVarArr2.length) {
                    om omVar = this.i[i3];
                    if (omVar.c == null) {
                        ntVarArr2[i3] = nt.a;
                        i2 = i3;
                        ntVarArr = ntVarArr2;
                        j5 = j4;
                    } else {
                        long a2 = omVar.a(this.j, this.k, j4);
                        long b3 = omVar.b(this.j, this.k, j4);
                        i2 = i3;
                        om omVar2 = omVar;
                        ntVarArr = ntVarArr2;
                        j5 = j4;
                        long a3 = a(omVar, nsVar, j3, a2, b3);
                        if (a3 < a2) {
                            ntVarArr[i2] = nt.a;
                        } else {
                            on onVar = new on(omVar2, a3, b3);
                            ntVarArr[i2] = onVar;
                        }
                    }
                    i3 = i2 + 1;
                    List<? extends ns> list3 = list;
                    ntVarArr2 = ntVarArr;
                    j4 = j5;
                }
                long j8 = j4;
                this.c.a(j2, j6, j7, list, ntVarArr2);
                om omVar3 = this.i[this.c.a()];
                if (omVar3.a != null) {
                    oy oyVar = omVar3.b;
                    ox c2 = omVar3.a.c() == null ? oyVar.c() : null;
                    ox d2 = omVar3.c == null ? oyVar.d() : null;
                    if (!(c2 == null && d2 == null)) {
                        sn snVar = this.e;
                        bs h2 = this.c.h();
                        int b4 = this.c.b();
                        Object c3 = this.c.c();
                        String str = omVar3.b.b;
                        if (c2 != null) {
                            d2 = c2.a(d2, str);
                            if (d2 == null) {
                                d2 = c2;
                            }
                        }
                        sr srVar = new sr(d2.a(str), d2.a, d2.b, omVar3.b.f());
                        nr nrVar = new nr(snVar, srVar, h2, b4, c3, omVar3.a);
                        nkVar2.a = nrVar;
                        return;
                    }
                }
                long a4 = omVar3.d;
                int i4 = (a4 > -9223372036854775807L ? 1 : (a4 == -9223372036854775807L ? 0 : -1));
                if (i4 != 0) {
                    z = true;
                }
                if (omVar3.b() == 0) {
                    nkVar2.b = z;
                    return;
                }
                long j9 = j8;
                long a5 = omVar3.a(this.j, this.k, j9);
                long b5 = omVar3.b(this.j, this.k, j9);
                this.n = this.j.d ? omVar3.b(b5) : -9223372036854775807L;
                long j10 = b5;
                long a6 = a(omVar3, nsVar, j3, a5, b5);
                if (a6 < a5) {
                    this.l = new ld();
                    return;
                }
                int i5 = (a6 > j10 ? 1 : (a6 == j10 ? 0 : -1));
                if (i5 > 0 || (this.m && i5 >= 0)) {
                    nkVar2.b = z;
                } else if (!z || omVar3.a(a6) < a4) {
                    int min = (int) Math.min((long) this.g, (j10 - a6) + 1);
                    if (i4 != 0) {
                        while (min > 1 && omVar3.a((((long) min) + a6) - 1) >= a4) {
                            min--;
                        }
                    }
                    long j11 = list.isEmpty() ? j3 : -9223372036854775807L;
                    sn snVar2 = this.e;
                    int i6 = this.d;
                    bs h3 = this.c.h();
                    int b6 = this.c.b();
                    Object c4 = this.c.c();
                    oy oyVar2 = omVar3.b;
                    long a7 = omVar3.a(a6);
                    ox d3 = omVar3.d(a6);
                    String str2 = oyVar2.b;
                    if (omVar3.a == null) {
                        long b7 = omVar3.b(a6);
                        sr srVar2 = new sr(d3.a(str2), d3.a, d3.b, oyVar2.f());
                        nv nvVar = new nv(snVar2, srVar2, h3, b6, c4, a7, b7, a6, i6, h3);
                        npVar = nvVar;
                    } else {
                        int i7 = 1;
                        int i8 = 1;
                        while (i7 < min) {
                            ox a8 = d3.a(omVar3.d(((long) i7) + a6), str2);
                            if (a8 == null) {
                                break;
                            }
                            i8++;
                            i7++;
                            d3 = a8;
                        }
                        long b8 = omVar3.b((((long) i8) + a6) - 1);
                        long a9 = omVar3.d;
                        long j12 = (a9 == -9223372036854775807L || a9 > b8) ? -9223372036854775807L : a9;
                        sr srVar3 = r15;
                        sr srVar4 = new sr(d3.a(str2), d3.a, d3.b, oyVar2.f());
                        np npVar2 = new np(snVar2, srVar3, h3, b6, c4, a7, b8, j11, j12, a6, i8, -oyVar2.c, omVar3.a);
                        npVar = npVar2;
                    }
                    nkVar2.a = npVar;
                } else {
                    nkVar2.b = true;
                }
            }
        }
    }

    public final void a(ng ngVar) {
        if (ngVar instanceof nr) {
            int a2 = this.c.a(((nr) ngVar).e);
            om omVar = this.i[a2];
            if (omVar.c == null) {
                fy b2 = omVar.a.b();
                if (b2 != null) {
                    this.i[a2] = omVar.a((ok) new qm((fn) b2, omVar.b.c));
                }
            }
        }
        os osVar = this.h;
        if (osVar != null) {
            osVar.a.b(ngVar);
        }
    }

    public final boolean a(ng ngVar, boolean z, Exception exc, long j2) {
        if (!z) {
            return false;
        }
        os osVar = this.h;
        if (osVar != null && osVar.a.a(ngVar)) {
            return true;
        }
        if (!this.j.d && (ngVar instanceof ns) && (exc instanceof tg) && ((tg) exc).a == 404) {
            om omVar = this.i[this.c.a(ngVar.e)];
            int b2 = omVar.b();
            if (!(b2 == -1 || b2 == 0)) {
                if (((ns) ngVar).g() > (omVar.a() + ((long) b2)) - 1) {
                    this.m = true;
                    return true;
                }
            }
        }
        if (j2 != -9223372036854775807L) {
            rt rtVar = this.c;
            if (rtVar.a(rtVar.a(ngVar.e), j2)) {
                return true;
            }
        }
        return false;
    }

    private static long a(om omVar, ns nsVar, long j2, long j3, long j4) {
        if (nsVar != null) {
            return nsVar.g();
        }
        return vf.a(omVar.c(j2), j3, j4);
    }

    private final ArrayList<oy> b() {
        List<rr> list = this.j.a(this.k).c;
        ArrayList<oy> arrayList = new ArrayList<>();
        for (int i2 : this.b) {
            arrayList.addAll(((rr) list.get(i2)).c);
        }
        return arrayList;
    }
}
