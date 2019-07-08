package com.shinobicontrols.charts;

import android.graphics.drawable.Drawable;
import com.shinobicontrols.charts.Series.Orientation;

public final class ColumnSeries extends BarColumnSeries<ColumnSeriesStyle> {
    public ColumnSeries() {
        this.j = Orientation.HORIZONTAL;
        this.i = true;
        this.p = new m(this);
        this.k = new l(this);
        setStyle(d());
        setSelectedStyle(d());
        this.v = SeriesAnimation.createGrowVerticalAnimation();
        this.w = SeriesAnimation.createGrowVerticalAnimation();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public ColumnSeriesStyle d() {
        return new ColumnSeriesStyle();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public ColumnSeriesStyle b(dd ddVar, int i, boolean z) {
        return ddVar.d(i, z);
    }

    /* access modifiers changed from: 0000 */
    public NumberRange b(Axis<?, ?> axis) {
        return axis.a() ? a(axis.c(), o()) : a(p());
    }

    /* access modifiers changed from: 0000 */
    public Drawable a(float f) {
        if (((ColumnSeriesStyle) this.q).c()) {
            return null;
        }
        return c(f);
    }

    /* access modifiers changed from: 0000 */
    public void a(a aVar, bz bzVar, boolean z) {
        cn.b((BarColumnSeries<?>) this, aVar, bzVar, z);
    }

    /* access modifiers changed from: 0000 */
    public a f() {
        return a.HORIZONTAL;
    }

    /* access modifiers changed from: 0000 */
    public a g() {
        return a.HORIZONTAL;
    }

    /* access modifiers changed from: 0000 */
    public double b() {
        if (this.u.b != null) {
            return (getYAxis().i.a * (1.0d - ((double) this.u.b.floatValue()))) + (getYAxis().i.b * ((double) this.u.b.floatValue()));
        }
        return this.t.b((CartesianSeries<?>) this);
    }

    /* access modifiers changed from: 0000 */
    public aa c() {
        return new b();
    }
}
