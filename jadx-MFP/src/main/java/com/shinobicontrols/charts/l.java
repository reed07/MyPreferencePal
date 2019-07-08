package com.shinobicontrols.charts;

import com.shinobicontrols.charts.Series.Orientation;
import java.util.List;

class l extends cq {
    l(BarColumnSeries<?> barColumnSeries) {
        super(true, barColumnSeries);
    }

    /* access modifiers changed from: 0000 */
    public void a(List<CartesianSeries<?>> list) {
        b(list);
        c(list);
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        m mVar = (m) this.a.p;
        mVar.m = (float) this.a.t.b(this.a);
        mVar.c = 0;
        mVar.a(this.a.n.a() * 2);
        mVar.b = null;
        for (InternalDataPoint internalDataPoint : this.a.n.c) {
            float[] fArr = mVar.a;
            int i = mVar.c;
            mVar.c = i + 1;
            fArr[i] = (float) internalDataPoint.c;
            float[] fArr2 = mVar.a;
            int i2 = mVar.c;
            mVar.c = i2 + 1;
            fArr2[i2] = (float) internalDataPoint.d;
        }
    }

    private static void b(List<CartesianSeries<?>> list) {
        for (CartesianSeries cartesianSeries : list) {
            m mVar = (m) cartesianSeries.p;
            mVar.m = (float) cartesianSeries.t.b(cartesianSeries);
            mVar.a(cartesianSeries.n.a() * 2);
            mVar.b(cartesianSeries.n.a());
            mVar.c = 0;
        }
    }

    private static void c(List<CartesianSeries<?>> list) {
        dc dcVar = new dc(list, false);
        while (dcVar.hasNext()) {
            a a = dcVar.next();
            CartesianSeries cartesianSeries = (CartesianSeries) list.get(list.size() - 1);
            do {
                b a2 = a.a(cartesianSeries);
                if (a2.a()) {
                    m mVar = (m) cartesianSeries.p;
                    InternalDataPoint internalDataPoint = cartesianSeries.n.c[a2.a];
                    if ((cartesianSeries.j == Orientation.HORIZONTAL ? internalDataPoint.a : internalDataPoint.b) == a.a) {
                        double a3 = a(cartesianSeries, a);
                        if (cartesianSeries.j == Orientation.HORIZONTAL) {
                            float[] fArr = mVar.a;
                            int i = mVar.c;
                            mVar.c = i + 1;
                            fArr[i] = (float) internalDataPoint.c;
                            float[] fArr2 = mVar.a;
                            int i2 = mVar.c;
                            mVar.c = i2 + 1;
                            fArr2[i2] = (float) a3;
                            mVar.b[a2.a] = (float) (a3 - internalDataPoint.b);
                            internalDataPoint.d = a3;
                        } else {
                            float[] fArr3 = mVar.a;
                            int i3 = mVar.c;
                            mVar.c = i3 + 1;
                            fArr3[i3] = (float) a3;
                            float[] fArr4 = mVar.a;
                            int i4 = mVar.c;
                            mVar.c = i4 + 1;
                            fArr4[i4] = (float) internalDataPoint.d;
                            mVar.b[a2.a] = (float) (a3 - internalDataPoint.a);
                            internalDataPoint.c = a3;
                        }
                    }
                }
                cartesianSeries = cartesianSeries.j();
            } while (cartesianSeries != null);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(List<CartesianSeries<?>> list, a aVar, NumberRange numberRange) {
        CartesianSeries cartesianSeries = (CartesianSeries) list.get(list.size() - 1);
        do {
            if (!cartesianSeries.y && aVar.a(cartesianSeries).a()) {
                numberRange.a(a(cartesianSeries, aVar) * 1.01d);
            }
            cartesianSeries = cartesianSeries.j();
        } while (cartesianSeries != null);
        CartesianSeries cartesianSeries2 = (CartesianSeries) list.get(0);
        numberRange.a(cartesianSeries2.t.b(cartesianSeries2));
    }

    /* access modifiers changed from: 0000 */
    public void b(List<CartesianSeries<?>> list, a aVar, NumberRange numberRange) {
        CartesianSeries cartesianSeries = (CartesianSeries) list.get(list.size() - 1);
        do {
            if (aVar.a(cartesianSeries).a()) {
                numberRange.a(a(cartesianSeries, aVar));
            }
            cartesianSeries = cartesianSeries.j();
        } while (cartesianSeries != null);
        numberRange.a(((CartesianSeries) list.get(0)).t.b((CartesianSeries) list.get(0)));
    }

    private static double a(CartesianSeries<?> cartesianSeries, a aVar) {
        CartesianSeries j = cartesianSeries.j();
        double a = j == null ? (double) ((m) cartesianSeries.p).m : a(j, aVar);
        b a2 = aVar.a(cartesianSeries);
        if (!a2.a()) {
            return a;
        }
        InternalDataPoint internalDataPoint = cartesianSeries.n.c[a2.a];
        return (cartesianSeries.j == Orientation.HORIZONTAL ? internalDataPoint.a : internalDataPoint.b) == aVar.a ? a + (cartesianSeries.j == Orientation.HORIZONTAL ? internalDataPoint.b : internalDataPoint.a) : a;
    }
}
