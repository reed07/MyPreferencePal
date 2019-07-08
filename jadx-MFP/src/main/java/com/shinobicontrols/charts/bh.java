package com.shinobicontrols.charts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.shinobicontrols.charts.Series.Orientation;
import java.util.List;

class bh extends cq {
    private int b;
    private boolean c;

    bh(LineSeries lineSeries) {
        super(true, lineSeries);
    }

    public void a(List<CartesianSeries<?>> list) {
        LineSeries lineSeries = (LineSeries) list.get(0);
        bh bhVar = (bh) lineSeries.k;
        bhVar.a((CartesianSeries<?>) lineSeries);
        b(list);
        c(list);
        bhVar.a(true);
        bhVar.b();
        bhVar.c();
    }

    public void a() {
        a(this.a);
        a(false);
        b();
        c();
    }

    private void a(CartesianSeries<?> cartesianSeries) {
        ((bi) cartesianSeries.p).m = (float) cartesianSeries.t.b(cartesianSeries);
    }

    private void a(boolean z) {
        float f = z ? ((bi) this.a.p).m : BitmapDescriptorFactory.HUE_RED;
        for (InternalDataPoint internalDataPoint : this.a.n.c) {
            if (this.a.j == Orientation.VERTICAL) {
                internalDataPoint.c = internalDataPoint.a + ((double) f);
            } else {
                internalDataPoint.d = internalDataPoint.b + ((double) f);
            }
        }
    }

    private void b() {
        bi biVar = (bi) this.a.p;
        d();
        int length = this.a.n.c.length + (biVar.a * 2);
        biVar.h = 0;
        biVar.i = 0;
        biVar.a(length * 2);
        biVar.e(this.a.n.c.length);
    }

    private void c() {
        boolean z;
        boolean z2;
        bi biVar = (bi) this.a.p;
        int i = 0;
        int i2 = 0;
        boolean z3 = true;
        while (i < this.a.n.c.length) {
            InternalDataPoint internalDataPoint = this.a.n.c[i];
            float f = (float) (this.a.j == Orientation.VERTICAL ? internalDataPoint.c : internalDataPoint.d);
            float f2 = (float) (this.a.j == Orientation.VERTICAL ? internalDataPoint.d : internalDataPoint.c);
            if (i2 > 0) {
                if (f >= biVar.m) {
                    if (!z3) {
                        z2 = true;
                        z = true;
                    }
                } else if (z3) {
                    z2 = true;
                    z = false;
                }
                z = z3;
                z2 = false;
            } else {
                z = f >= biVar.m;
                z2 = false;
            }
            if (z2) {
                int i3 = i2 - 2;
                float abs = Math.abs(biVar.f[a(i3)] - biVar.m);
                float abs2 = Math.abs(f - biVar.m);
                float f3 = (abs == BitmapDescriptorFactory.HUE_RED && abs2 == BitmapDescriptorFactory.HUE_RED) ? 1.0f : abs / (abs2 + abs);
                float f4 = biVar.m;
                float f5 = biVar.f[b(i3)];
                float f6 = f5 + ((f2 - f5) * f3);
                biVar.f[a(i2)] = f4;
                biVar.f[b(i2)] = f6;
                int i4 = i2 + 2;
                biVar.f[a(i4)] = f4;
                biVar.f[b(i4)] = f6;
                i2 = i4 + 2;
            }
            biVar.f[i2] = (float) internalDataPoint.c;
            biVar.f[i2 + 1] = (float) internalDataPoint.d;
            a(biVar, internalDataPoint);
            i2 += 2;
            i++;
            z3 = z;
        }
        if (biVar.d()) {
            biVar.c(this.a.n.c.length);
            biVar.d(this.a.n.c.length);
        }
        biVar.b = i2;
    }

    private static void a(bi biVar, InternalDataPoint internalDataPoint) {
        if (internalDataPoint.h) {
            biVar.e = true;
            biVar.h++;
            return;
        }
        biVar.i++;
    }

    private static void b(List<CartesianSeries<?>> list) {
        int i = 0;
        dc dcVar = new dc(list, false);
        while (dcVar.hasNext()) {
            dcVar.next();
            i++;
        }
        Object obj = list.get(list.size() - 1);
        while (true) {
            LineSeries lineSeries = (LineSeries) obj;
            if (lineSeries.l()) {
                ((bh) lineSeries.k).a(lineSeries, i);
                obj = lineSeries.j();
            } else {
                return;
            }
        }
    }

    private void a(LineSeries lineSeries, int i) {
        bi biVar = (bi) lineSeries.p;
        int i2 = i * 2;
        biVar.a(i2);
        biVar.b(i2);
        biVar.a = 0;
        biVar.b = 0;
        biVar.h = 0;
        biVar.i = 0;
        this.b = 0;
        this.c = false;
    }

    private static void c(List<CartesianSeries<?>> list) {
        dc dcVar = new dc(list, false);
        while (dcVar.hasNext()) {
            a a = dcVar.next();
            LineSeries lineSeries = (LineSeries) list.get(list.size() - 1);
            while (lineSeries.l()) {
                LineSeries lineSeries2 = (LineSeries) lineSeries.j();
                b a2 = a.a(lineSeries);
                if (a(a2, a.a)) {
                    InternalDataPoint internalDataPoint = lineSeries.n.c[a2.a];
                    bi biVar = (bi) lineSeries.p;
                    biVar.m = (float) lineSeries.t.b((CartesianSeries<?>) lineSeries);
                    ((bh) lineSeries.k).a(biVar, internalDataPoint, a, lineSeries2);
                    a(biVar, internalDataPoint);
                }
                lineSeries = lineSeries2;
            }
        }
    }

    private void a(bi biVar, InternalDataPoint internalDataPoint, a aVar, LineSeries lineSeries) {
        float f;
        float f2;
        float f3 = (float) aVar.a;
        if (internalDataPoint.a == aVar.a) {
            internalDataPoint.d = a(this.a, aVar);
            f = (float) (internalDataPoint.d - internalDataPoint.b);
            f2 = (float) internalDataPoint.d;
        } else {
            f2 = (float) a(this.a, aVar);
            f = lineSeries != null ? (float) a(this.a.j(), aVar) : biVar.m;
        }
        biVar.f[this.b] = f3;
        biVar.f[this.b + 1] = f2;
        if (this.a.j == Orientation.VERTICAL) {
            biVar.g[this.b] = f3;
            biVar.g[this.b + 1] = f2;
        } else {
            biVar.g[this.b] = f3;
            biVar.g[this.b + 1] = f;
        }
        boolean z = false;
        if (this.b <= 0) {
            if (biVar.f[a(this.b)] >= biVar.g[a(this.b)]) {
                z = true;
            }
            this.c = z;
        } else if (biVar.f[a(this.b)] >= biVar.g[a(this.b)]) {
            if (!this.c) {
                this.c = true;
                biVar.a++;
            }
        } else if (this.c) {
            this.c = false;
            biVar.a++;
        }
        this.b += 2;
        biVar.b = this.b;
    }

    private void d() {
        bi biVar = (bi) this.a.p;
        biVar.a = 0;
        float f = -3.4028235E38f;
        float f2 = Float.MAX_VALUE;
        boolean z = true;
        for (int i = 0; i < this.a.n.c.length; i++) {
            InternalDataPoint internalDataPoint = this.a.n.c[i];
            float f3 = (float) (this.a.j == Orientation.VERTICAL ? internalDataPoint.c : internalDataPoint.d);
            if (f3 > f) {
                f = f3;
            }
            if (f3 < f2) {
                f2 = f3;
            }
            if (i <= 0) {
                z = f3 >= biVar.m;
            } else if (f3 >= biVar.m) {
                if (!z) {
                    biVar.a++;
                    z = true;
                }
            } else if (z) {
                biVar.a++;
                z = false;
            }
        }
        biVar.c = Math.abs(f - biVar.m);
        if (biVar.c == BitmapDescriptorFactory.HUE_RED) {
            biVar.c = 0.01f;
        }
        biVar.d = Math.abs(biVar.m - f2);
        if (biVar.d == BitmapDescriptorFactory.HUE_RED) {
            biVar.d = 0.01f;
        }
    }

    private static double a(InternalDataPoint internalDataPoint, InternalDataPoint internalDataPoint2, double d, Orientation orientation) {
        if (orientation == Orientation.HORIZONTAL) {
            return internalDataPoint.b + (((internalDataPoint2.b - internalDataPoint.b) * (d - internalDataPoint.a)) / (internalDataPoint2.a - internalDataPoint.a));
        }
        return internalDataPoint.a + (((internalDataPoint2.a - internalDataPoint.a) * (d - internalDataPoint.b)) / (internalDataPoint2.b - internalDataPoint.b));
    }

    private static double a(CartesianSeries<?> cartesianSeries, a aVar) {
        CartesianSeries j = cartesianSeries.j();
        double b2 = j == null ? cartesianSeries.t.b(cartesianSeries) : a(j, aVar);
        b a = aVar.a(cartesianSeries);
        if (!a(a, aVar.a)) {
            return b2;
        }
        InternalDataPoint internalDataPoint = cartesianSeries.n.c[a.a];
        double d = cartesianSeries.j == Orientation.HORIZONTAL ? internalDataPoint.a : internalDataPoint.b;
        double d2 = cartesianSeries.j == Orientation.HORIZONTAL ? internalDataPoint.b : internalDataPoint.a;
        if (d == aVar.a) {
            return b2 + d2;
        }
        return b2 + a(cartesianSeries.n.c[a.a - 1], cartesianSeries.n.c[a.a], aVar.a, cartesianSeries.j);
    }

    /* access modifiers changed from: 0000 */
    public void a(List<CartesianSeries<?>> list, a aVar, NumberRange numberRange) {
        CartesianSeries cartesianSeries = (CartesianSeries) list.get(list.size() - 1);
        do {
            if (!cartesianSeries.y && a(aVar.a(cartesianSeries), aVar.a)) {
                numberRange.a(a(cartesianSeries, aVar));
            }
            cartesianSeries = cartesianSeries.j();
        } while (cartesianSeries != null);
    }

    /* access modifiers changed from: 0000 */
    public void b(List<CartesianSeries<?>> list, a aVar, NumberRange numberRange) {
        CartesianSeries cartesianSeries = (CartesianSeries) list.get(list.size() - 1);
        do {
            if (a(aVar.a(cartesianSeries), aVar.a)) {
                numberRange.a(a(cartesianSeries, aVar));
            }
            cartesianSeries = cartesianSeries.j();
        } while (cartesianSeries != null);
    }

    private int a(int i) {
        return this.a.j == Orientation.HORIZONTAL ? i + 1 : i;
    }

    private int b(int i) {
        return this.a.j == Orientation.HORIZONTAL ? i : i + 1;
    }

    private static boolean a(b bVar, double d) {
        if (!bVar.a()) {
            return false;
        }
        boolean z = true;
        if (bVar.b.n.c[0].a > d || bVar.b.n.c[bVar.b.n.a() - 1].a < d) {
            z = false;
        }
        return z;
    }
}
