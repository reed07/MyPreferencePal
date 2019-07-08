package com.shinobicontrols.charts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

class cv {
    private final b a = new b();
    private final b b = new b();
    private final a c = new a();
    private final List<cr> d = new ArrayList();

    static class a extends HashMap<Axis<?, ?>, Set<CartesianSeries<?>>> {
        private static final long serialVersionUID = 1;

        a() {
        }
    }

    static class b extends LinkedHashMap<CartesianSeries<?>, Axis<?, ?>> {
        private static final long serialVersionUID = 1;

        b() {
        }
    }

    cv() {
    }

    /* access modifiers changed from: 0000 */
    public void a(Axis<?, ?> axis) {
        for (CartesianSeries cartesianSeries : this.a.keySet()) {
            if (this.a.get(cartesianSeries) == null) {
                a(cartesianSeries, axis, (Axis) this.b.get(cartesianSeries));
                cartesianSeries.k();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(Axis<?, ?> axis) {
        for (CartesianSeries cartesianSeries : this.b.keySet()) {
            if (this.b.get(cartesianSeries) == null) {
                a(cartesianSeries, (Axis) this.a.get(cartesianSeries), axis);
                cartesianSeries.k();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(CartesianSeries<?> cartesianSeries, Axis<?, ?> axis, Axis<?, ?> axis2) {
        if (cartesianSeries == null) {
            throw new IllegalStateException();
        } else if (axis == null || axis2 == null || axis != axis2) {
            a(cartesianSeries, axis, this.a);
            a(cartesianSeries, axis2, this.b);
            b(cartesianSeries, axis, axis2);
        } else {
            throw new IllegalStateException();
        }
    }

    private void b(CartesianSeries<?> cartesianSeries, Axis<?, ?> axis, Axis<?, ?> axis2) {
        if (axis != null && axis2 != null) {
            boolean z = false;
            Iterator it = this.d.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                cr crVar = (cr) it.next();
                if (crVar.a(cartesianSeries, axis, axis2)) {
                    crVar.a(cartesianSeries);
                    z = true;
                    break;
                }
            }
            if (!z) {
                this.d.add(new cr(axis, axis2, cartesianSeries));
            }
        }
    }

    private void a(CartesianSeries<?> cartesianSeries, Axis<?, ?> axis, b bVar) {
        bVar.put(cartesianSeries, axis);
        if (axis != null) {
            Set set = (Set) this.c.get(axis);
            if (set == null) {
                set = new HashSet();
                this.c.put(axis, set);
            }
            set.add(cartesianSeries);
            axis.a((Series<?>) cartesianSeries);
            cartesianSeries.d(axis);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Series<?> series) {
        Axis b2 = b(series);
        if (b2 != null) {
            ((Set) this.c.get(b2)).remove(series);
            b2.b(series);
            series.e(b2);
        }
        Axis c2 = c(series);
        if (c2 != null) {
            ((Set) this.c.get(c2)).remove(series);
            c2.b(series);
            series.e(c2);
        }
        this.a.remove(series);
        this.b.remove(series);
        d(series);
    }

    private void d(Series<?> series) {
        ArrayList<cr> arrayList = new ArrayList<>();
        for (cr crVar : this.d) {
            if (crVar.a(series)) {
                crVar.b(series);
                if (crVar.d() == 0) {
                    arrayList.add(crVar);
                }
            }
        }
        for (cr remove : arrayList) {
            this.d.remove(remove);
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(Axis<?, ?> axis) {
        a(axis, this.a);
    }

    /* access modifiers changed from: 0000 */
    public void d(Axis<?, ?> axis) {
        a(axis, this.b);
    }

    private void a(Axis<?, ?> axis, b bVar) {
        Set<CartesianSeries> set = (Set) this.c.get(axis);
        if (set != null) {
            for (CartesianSeries cartesianSeries : set) {
                bVar.put(cartesianSeries, null);
                axis.b((Series<?>) cartesianSeries);
                cartesianSeries.e(axis);
            }
            this.c.remove(axis);
        }
        this.d.removeAll(f(axis));
    }

    private List<cr> f(Axis<?, ?> axis) {
        ArrayList arrayList = new ArrayList();
        for (cr crVar : this.d) {
            if (crVar.a(axis)) {
                arrayList.add(crVar);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public Axis<?, ?> b(Series<?> series) {
        return (Axis) this.a.get(series);
    }

    /* access modifiers changed from: 0000 */
    public Axis<?, ?> c(Series<?> series) {
        return (Axis) this.b.get(series);
    }

    /* access modifiers changed from: 0000 */
    public Set<CartesianSeries<?>> e(Axis<?, ?> axis) {
        return (Set) this.c.get(axis);
    }

    /* access modifiers changed from: 0000 */
    public List<cr> a() {
        return this.d;
    }
}
