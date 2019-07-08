package com.shinobicontrols.charts;

import android.graphics.drawable.Drawable;
import com.shinobicontrols.charts.Series.Orientation;

public final class BarSeries extends BarColumnSeries<BarSeriesStyle> {
    public BarSeries() {
        this.j = Orientation.VERTICAL;
        this.i = true;
        this.p = new m(this);
        this.k = new l(this);
        setStyle(d());
        setSelectedStyle(d());
        this.v = SeriesAnimation.createGrowHorizontalAnimation();
        this.w = SeriesAnimation.createGrowHorizontalAnimation();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public BarSeriesStyle d() {
        return new BarSeriesStyle();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public BarSeriesStyle b(dd ddVar, int i, boolean z) {
        return ddVar.e(i, z);
    }

    /* access modifiers changed from: 0000 */
    public NumberRange b(Axis<?, ?> axis) {
        return axis.a() ? a(o()) : a(axis.c(), p());
    }

    /* access modifiers changed from: 0000 */
    public Drawable a(float f) {
        if (((BarSeriesStyle) this.q).c()) {
            return null;
        }
        return c(f);
    }

    /* access modifiers changed from: 0000 */
    public void a(a aVar, bz bzVar, boolean z) {
        cn.a((BarColumnSeries<?>) this, aVar, bzVar, z);
    }

    /* access modifiers changed from: 0000 */
    public a f() {
        return a.VERTICAL;
    }

    /* access modifiers changed from: 0000 */
    public a g() {
        return a.VERTICAL;
    }

    /* access modifiers changed from: 0000 */
    public aa c() {
        return new a();
    }

    /* access modifiers changed from: 0000 */
    public double h() {
        if (this.u.a != null) {
            return (getXAxis().i.a * (1.0d - ((double) this.u.a.floatValue()))) + (getXAxis().i.b * ((double) this.u.a.floatValue()));
        }
        return this.t.b((CartesianSeries<?>) this);
    }
}
