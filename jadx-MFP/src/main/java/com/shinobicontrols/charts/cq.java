package com.shinobicontrols.charts;

import java.util.List;

abstract class cq {
    protected final CartesianSeries<?> a;
    private final boolean b;

    /* access modifiers changed from: 0000 */
    public abstract void a();

    /* access modifiers changed from: 0000 */
    public void a(List<CartesianSeries<?>> list, a aVar, NumberRange numberRange) {
    }

    /* access modifiers changed from: 0000 */
    public void b(List<CartesianSeries<?>> list, a aVar, NumberRange numberRange) {
    }

    cq(boolean z, CartesianSeries<?> cartesianSeries) {
        this.b = z;
        this.a = cartesianSeries;
    }

    /* access modifiers changed from: 0000 */
    public void a(List<CartesianSeries<?>> list) {
        for (CartesianSeries cartesianSeries : list) {
            if (cartesianSeries.s()) {
                cartesianSeries.k.a();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public NumberRange a(Axis<?, ?> axis, List<CartesianSeries<?>> list) {
        NumberRange numberRange = new NumberRange();
        if (!this.b || axis.a(this.a.j) || list.size() == 1) {
            for (CartesianSeries cartesianSeries : list) {
                if (!cartesianSeries.y) {
                    numberRange.c(cartesianSeries.b(axis));
                }
            }
        } else {
            dc dcVar = new dc(list, false);
            while (dcVar.hasNext()) {
                a(list, dcVar.next(), numberRange);
            }
        }
        return numberRange;
    }

    /* access modifiers changed from: 0000 */
    public NumberRange b(Axis<?, ?> axis, List<CartesianSeries<?>> list) {
        NumberRange numberRange = new NumberRange();
        if (!this.b || axis.a(this.a.j) || list.size() == 1) {
            for (CartesianSeries c : list) {
                numberRange.c(c.c(axis));
            }
        } else {
            dc dcVar = new dc(list, true);
            while (dcVar.hasNext()) {
                b(list, dcVar.next(), numberRange);
            }
        }
        return numberRange;
    }
}
