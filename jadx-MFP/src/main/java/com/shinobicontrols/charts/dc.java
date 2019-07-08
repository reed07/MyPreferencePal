package com.shinobicontrols.charts;

import com.shinobicontrols.charts.Series.Orientation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class dc implements Iterator<a> {
    private final List<CartesianSeries<?>> a;
    private final Orientation b;
    private final a c;
    private final boolean d;

    class a {
        double a;
        private final Map<CartesianSeries<?>, b> c;

        private a(List<CartesianSeries<?>> list) {
            this.a = Double.NEGATIVE_INFINITY;
            this.c = new HashMap();
            for (CartesianSeries cartesianSeries : list) {
                this.c.put(cartesianSeries, new b(cartesianSeries));
            }
        }

        public b a(CartesianSeries<?> cartesianSeries) {
            return (b) this.c.get(cartesianSeries);
        }
    }

    class b {
        int a;
        final CartesianSeries<?> b;

        private b(CartesianSeries<?> cartesianSeries) {
            this.a = -1;
            this.b = cartesianSeries;
        }

        /* access modifiers changed from: private */
        public boolean b() {
            return this.a == -1;
        }

        /* access modifiers changed from: private */
        public boolean c() {
            int i = this.a;
            return i != -2 && i + 1 < this.b.n.a();
        }

        /* access modifiers changed from: 0000 */
        public boolean a() {
            int i = this.a;
            return (i == -1 || i == -2) ? false : true;
        }

        /* access modifiers changed from: private */
        public void d() {
            this.a++;
            if (this.a >= this.b.n.a()) {
                this.a = -2;
            }
        }
    }

    public dc(List<CartesianSeries<?>> list, boolean z) {
        if (list.size() >= 1) {
            this.a = list;
            this.b = ((CartesianSeries) list.get(0)).j;
            this.c = new a(list);
            this.d = z;
            return;
        }
        throw new IllegalStateException("There must be at least one series in a stacking group");
    }

    public boolean hasNext() {
        for (CartesianSeries cartesianSeries : this.a) {
            if (a(cartesianSeries)) {
                b a2 = this.c.a(cartesianSeries);
                if (a2.c()) {
                    return true;
                }
                if (a2.a()) {
                    InternalDataPoint internalDataPoint = cartesianSeries.n.c[a2.a];
                    if ((this.b == Orientation.HORIZONTAL ? internalDataPoint.a : internalDataPoint.b) > this.c.a) {
                        return true;
                    }
                } else {
                    continue;
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    public a next() {
        for (CartesianSeries cartesianSeries : this.a) {
            if (a(cartesianSeries)) {
                b a2 = this.c.a(cartesianSeries);
                if (a2.b()) {
                    a2.d();
                } else if (a2.a()) {
                    InternalDataPoint internalDataPoint = cartesianSeries.n.c[a2.a];
                    if ((this.b == Orientation.HORIZONTAL ? internalDataPoint.a : internalDataPoint.b) == this.c.a) {
                        a2.d();
                    }
                }
            }
        }
        double d2 = Double.POSITIVE_INFINITY;
        for (CartesianSeries cartesianSeries2 : this.a) {
            if (a(cartesianSeries2)) {
                b a3 = this.c.a(cartesianSeries2);
                if (a3.a()) {
                    InternalDataPoint internalDataPoint2 = cartesianSeries2.n.c[a3.a];
                    double d3 = this.b == Orientation.HORIZONTAL ? internalDataPoint2.a : internalDataPoint2.b;
                    if (d3 <= this.c.a) {
                        throw new IllegalStateException(cartesianSeries2.o.getContext().getString(R.string.StackSeriesIteratorOrdinatesOutofOrder));
                    } else if (d3 < d2) {
                        d2 = d3;
                    }
                } else {
                    continue;
                }
            }
        }
        a aVar = this.c;
        aVar.a = d2;
        return aVar;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    private boolean a(Series<?> series) {
        if (this.d) {
            return true;
        }
        return !series.y;
    }
}
