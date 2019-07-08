package com.shinobicontrols.charts;

import android.annotation.SuppressLint;
import com.shinobicontrols.charts.Series.Orientation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint({"UseSparseArrays"})
class cr {
    private final Axis<?, ?> a;
    private final Axis<?, ?> b;
    private final List<CartesianSeries<?>> c = new ArrayList();
    private final List<CartesianSeries<?>> d = new ArrayList();
    private final Map<Integer, List<CartesianSeries<?>>> e = new HashMap();
    private final Class f;

    cr(Axis<?, ?> axis, Axis<?, ?> axis2, CartesianSeries<?> cartesianSeries) {
        if (cartesianSeries != null) {
            this.a = axis;
            this.b = axis2;
            a(cartesianSeries);
            this.f = cartesianSeries.getClass();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: 0000 */
    public boolean a(Axis<?, ?> axis) {
        return axis == this.a || axis == this.b;
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        for (CartesianSeries cartesianSeries : this.d) {
            cartesianSeries.k.a();
        }
        for (List list : this.e.values()) {
            ((CartesianSeries) list.get(0)).k.a(list);
        }
    }

    /* access modifiers changed from: 0000 */
    public NumberRange b(Axis axis) {
        NumberRange numberRange = new NumberRange();
        for (CartesianSeries cartesianSeries : this.d) {
            if (!cartesianSeries.y) {
                numberRange.c(cartesianSeries.b(axis));
            }
        }
        for (List list : this.e.values()) {
            numberRange.c(((CartesianSeries) list.get(0)).k.a(axis, list));
        }
        return numberRange;
    }

    /* access modifiers changed from: 0000 */
    public NumberRange c(Axis axis) {
        NumberRange numberRange = new NumberRange();
        for (CartesianSeries c2 : this.d) {
            numberRange.c(c2.c(axis));
        }
        for (List list : this.e.values()) {
            numberRange.c(((CartesianSeries) list.get(0)).k.b(axis, list));
        }
        return numberRange;
    }

    /* access modifiers changed from: 0000 */
    public void a(CartesianSeries<?> cartesianSeries) {
        if (!a((Series<?>) cartesianSeries)) {
            this.c.add(cartesianSeries);
        }
        cartesianSeries.a(this);
        b();
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        List list;
        this.d.clear();
        this.e.clear();
        int i = 0;
        for (CartesianSeries cartesianSeries : this.c) {
            if (cartesianSeries.c == null) {
                cartesianSeries.a(i, 0, null);
                this.d.add(cartesianSeries);
                i++;
            } else {
                if (this.e.containsKey(cartesianSeries.c)) {
                    list = (List) this.e.get(cartesianSeries.c);
                    cartesianSeries.a(((CartesianSeries) list.get(0)).i(), this.e.size(), (CartesianSeries) list.get(list.size() - 1));
                } else {
                    cartesianSeries.a(i, 0, null);
                    list = new ArrayList();
                    this.e.put(cartesianSeries.c, list);
                    i++;
                }
                list.add(cartesianSeries);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public int c() {
        return this.d.size() + this.e.size();
    }

    /* access modifiers changed from: 0000 */
    public void d(Axis<?, ?> axis) {
        for (CartesianSeries a2 : this.c) {
            a2.a(axis);
        }
    }

    /* access modifiers changed from: 0000 */
    public int d() {
        return this.c.size();
    }

    /* access modifiers changed from: 0000 */
    public boolean a(Series<?> series) {
        return this.c.contains(series);
    }

    /* access modifiers changed from: 0000 */
    public void b(Series<?> series) {
        if (a(series)) {
            this.c.remove(series);
            series.a((cr) null);
            b();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(Series<?> series, Axis<?, ?> axis, Axis<?, ?> axis2) {
        return this.a == axis && this.b == axis2 && series.getClass().equals(this.f);
    }

    /* access modifiers changed from: 0000 */
    public double b(CartesianSeries<?> cartesianSeries) {
        if (cartesianSeries.l()) {
            return b(cartesianSeries.j());
        }
        if (cartesianSeries.b != null) {
            Axis c2 = c(cartesianSeries);
            if (c2.isDataValid(cartesianSeries.b)) {
                return c2.translatePoint(cartesianSeries.b);
            }
            throw new IllegalStateException(cartesianSeries.o != null ? cartesianSeries.o.getContext().getString(R.string.CartesianBaselineWrongType) : "Current baseline for series is invalid for the assigned x/y axes.");
        } else if ((cartesianSeries instanceof BarColumnSeries) || cartesianSeries.c != null) {
            Axis c3 = c(cartesianSeries);
            return c3.translatePoint(c3.getDefaultBaseline());
        } else {
            Axis c4 = c(cartesianSeries);
            return c4.l != null ? c4.l.a : c4.e();
        }
    }

    private Axis<?, ?> c(CartesianSeries<?> cartesianSeries) {
        return cartesianSeries.j == Orientation.HORIZONTAL ? this.b : this.a;
    }

    /* access modifiers changed from: 0000 */
    public List<? extends Series<?>> c(Series<?> series) {
        for (List<? extends Series<?>> list : this.e.values()) {
            if (list.contains(series)) {
                return list;
            }
        }
        return Collections.emptyList();
    }
}
