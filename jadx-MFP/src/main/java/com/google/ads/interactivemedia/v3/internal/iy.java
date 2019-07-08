package com.google.ads.interactivemedia.v3.internal;

import android.util.SparseArray;
import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: IMASDK */
final class iy implements ip {
    private final us a = new us(new byte[5]);
    private final SparseArray<iz> b = new SparseArray<>();
    private final SparseIntArray c = new SparseIntArray();
    private final int d;
    private final /* synthetic */ iw e;

    public iy(iw iwVar, int i) {
        this.e = iwVar;
        this.d = i;
    }

    public final void a(ve veVar, fs fsVar, jd jdVar) {
    }

    public final void a(ut utVar) {
        ve veVar;
        int i;
        ve veVar2;
        iz izVar;
        int i2;
        ve veVar3;
        ut utVar2 = utVar;
        if (utVar.e() == 2) {
            if (this.e.d == 1 || this.e.d == 2 || this.e.o == 1) {
                veVar = (ve) this.e.e.get(0);
            } else {
                veVar = new ve(((ve) this.e.e.get(0)).a());
                this.e.e.add(veVar);
            }
            utVar2.d(2);
            int f = utVar.f();
            int i3 = 3;
            utVar2.d(3);
            utVar2.a(this.a, 2);
            this.a.b(3);
            int i4 = 13;
            this.e.u = this.a.c(13);
            utVar2.a(this.a, 2);
            int i5 = 4;
            this.a.b(4);
            int i6 = 12;
            utVar2.d(this.a.c(12));
            if (this.e.d == 2 && this.e.s == null) {
                jb jbVar = new jb(21, null, null, vf.f);
                iw iwVar = this.e;
                iwVar.s = iwVar.h.a(21, jbVar);
                this.e.s.a(veVar, this.e.n, new jd(f, 21, 8192));
            }
            this.b.clear();
            this.c.clear();
            int b2 = utVar.b();
            while (b2 > 0) {
                int i7 = 5;
                utVar2.a(this.a, 5);
                int c2 = this.a.c(8);
                this.a.b(i3);
                int c3 = this.a.c(i4);
                this.a.b(i5);
                int c4 = this.a.c(i6);
                int d2 = utVar.d();
                int i8 = d2 + c4;
                String str = null;
                int i9 = -1;
                List list = null;
                while (utVar.d() < i8) {
                    int e2 = utVar.e();
                    int d3 = utVar.d() + utVar.e();
                    if (e2 == i7) {
                        long j = utVar.j();
                        if (j == iw.a) {
                            veVar3 = veVar;
                            i2 = f;
                            i9 = 129;
                        } else if (j == iw.b) {
                            veVar3 = veVar;
                            i2 = f;
                            i9 = 135;
                        } else {
                            if (j == iw.c) {
                                i9 = 36;
                            }
                            veVar3 = veVar;
                            i2 = f;
                        }
                    } else if (e2 == 106) {
                        veVar3 = veVar;
                        i2 = f;
                        i9 = 129;
                    } else if (e2 == 122) {
                        veVar3 = veVar;
                        i2 = f;
                        i9 = 135;
                    } else if (e2 == 123) {
                        veVar3 = veVar;
                        i2 = f;
                        i9 = 138;
                    } else if (e2 == 10) {
                        str = utVar2.e(3).trim();
                        veVar3 = veVar;
                        i2 = f;
                    } else {
                        int i10 = 3;
                        if (e2 == 89) {
                            ArrayList arrayList = new ArrayList();
                            while (utVar.d() < d3) {
                                String trim = utVar2.e(i10).trim();
                                int e3 = utVar.e();
                                ve veVar4 = veVar;
                                byte[] bArr = new byte[4];
                                int i11 = f;
                                utVar2.a(bArr, 0, 4);
                                arrayList.add(new ja(trim, e3, bArr));
                                veVar = veVar4;
                                f = i11;
                                i10 = 3;
                            }
                            veVar3 = veVar;
                            i2 = f;
                            list = arrayList;
                            i9 = 89;
                        } else {
                            veVar3 = veVar;
                            i2 = f;
                        }
                    }
                    utVar2.d(d3 - utVar.d());
                    veVar = veVar3;
                    f = i2;
                    i7 = 5;
                }
                ve veVar5 = veVar;
                int i12 = f;
                utVar2.c(i8);
                jb jbVar2 = new jb(i9, str, list, Arrays.copyOfRange(utVar2.a, d2, i8));
                if (c2 == 6) {
                    c2 = jbVar2.a;
                }
                b2 -= c4 + 5;
                int i13 = this.e.d == 2 ? c2 : c3;
                if (!this.e.j.get(i13)) {
                    if (this.e.d == 2) {
                        if (c2 == 21) {
                            izVar = this.e.s;
                            if (this.e.d == 2 || c3 < this.c.get(i13, 8192)) {
                                this.c.put(i13, c3);
                                this.b.put(i13, izVar);
                            }
                        }
                    }
                    izVar = this.e.h.a(c2, jbVar2);
                    if (this.e.d == 2) {
                    }
                    this.c.put(i13, c3);
                    this.b.put(i13, izVar);
                }
                veVar = veVar5;
                f = i12;
                i3 = 3;
                i5 = 4;
                i4 = 13;
                i6 = 12;
            }
            ve veVar6 = veVar;
            int i14 = f;
            int size = this.c.size();
            int i15 = 0;
            while (i15 < size) {
                int keyAt = this.c.keyAt(i15);
                int valueAt = this.c.valueAt(i15);
                this.e.j.put(keyAt, true);
                this.e.k.put(valueAt, true);
                iz izVar2 = (iz) this.b.valueAt(i15);
                if (izVar2 != null) {
                    if (izVar2 != this.e.s) {
                        fs h = this.e.n;
                        i = i14;
                        jd jdVar = new jd(i, keyAt, 8192);
                        veVar2 = veVar6;
                        izVar2.a(veVar2, h, jdVar);
                    } else {
                        veVar2 = veVar6;
                        i = i14;
                    }
                    this.e.i.put(valueAt, izVar2);
                } else {
                    veVar2 = veVar6;
                    i = i14;
                }
                i15++;
                veVar6 = veVar2;
                i14 = i;
            }
            if (this.e.d != 2) {
                this.e.i.remove(this.d);
                iw iwVar2 = this.e;
                iwVar2.o = iwVar2.d == 1 ? 0 : this.e.o - 1;
                if (this.e.o == 0) {
                    this.e.n.a();
                    this.e.p = true;
                }
            } else if (!this.e.p) {
                this.e.n.a();
                this.e.o = 0;
                this.e.p = true;
            }
        }
    }
}
