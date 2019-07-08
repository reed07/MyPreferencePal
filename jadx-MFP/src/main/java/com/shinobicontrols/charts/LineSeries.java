package com.shinobicontrols.charts;

import android.graphics.drawable.Drawable;
import com.shinobicontrols.charts.SeriesStyle.FillStyle;

public final class LineSeries extends CartesianSeries<LineSeriesStyle> {
    private final ca a;

    public LineSeries() {
        this.a = new ca();
        this.p = new bi(this);
        this.k = new bh(this);
        setStyle(d());
        setSelectedStyle(d());
        this.v = SeriesAnimation.createTelevisionAnimation();
        this.w = SeriesAnimation.createTelevisionAnimation();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public LineSeriesStyle d() {
        return new LineSeriesStyle();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public LineSeriesStyle b(dd ddVar, int i, boolean z) {
        return ddVar.f(i, z);
    }

    /* access modifiers changed from: 0000 */
    public Drawable a(float f) {
        LineSeriesStyle lineSeriesStyle = (LineSeriesStyle) ((!isSelected() || this.r == null) ? this.q : this.r);
        if (lineSeriesStyle.c()) {
            return new bc();
        }
        return new ba(lineSeriesStyle.getFillStyle() == FillStyle.NONE ? lineSeriesStyle.getLineColor() : lineSeriesStyle.getAreaColor(), lineSeriesStyle.getFillStyle() == FillStyle.NONE ? lineSeriesStyle.getLineColor() : lineSeriesStyle.getAreaLineColor(), f);
    }

    /* access modifiers changed from: 0000 */
    public void a(a aVar, bz bzVar, boolean z) {
        cn.a((CartesianSeries<?>) this, aVar, bzVar, z);
    }

    /* access modifiers changed from: 0000 */
    public void a(a aVar, bz bzVar) {
        if (this.o.h == null || !this.o.h.b) {
            super.a(aVar, bzVar);
            return;
        }
        bz a2 = a(aVar.b(), bzVar);
        aVar.a(new bz(a2.b, a2.c));
    }

    /* access modifiers changed from: 0000 */
    public a g() {
        switch ((a) ((LineSeriesStyle) this.q).m.a) {
            case HORIZONTAL:
                return a.HORIZONTAL;
            case VERTICAL:
                return a.VERTICAL;
            default:
                return a.HORIZONTAL;
        }
    }

    private bz a(InternalDataPoint internalDataPoint, bz bzVar) {
        return this.a.a(internalDataPoint, bzVar, this.n.c, ((LineSeriesStyle) this.q).m.a == a.HORIZONTAL);
    }

    /* access modifiers changed from: 0000 */
    public aa c() {
        return new c();
    }

    /* access modifiers changed from: 0000 */
    public double b() {
        if (this.u.b != null) {
            return (getYAxis().i.a * (1.0d - ((double) this.u.b.floatValue()))) + (getYAxis().i.b * ((double) this.u.b.floatValue()));
        }
        return this.t.b((CartesianSeries<?>) this);
    }
}
