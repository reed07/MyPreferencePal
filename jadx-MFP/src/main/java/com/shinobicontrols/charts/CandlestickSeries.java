package com.shinobicontrols.charts;

import android.graphics.drawable.Drawable;

public class CandlestickSeries extends BarColumnSeries<CandlestickSeriesStyle> {
    public CandlestickSeries() {
        this.p = new p(this);
        this.k = new o(this);
        setStyle(d());
        setSelectedStyle(d());
        this.v = SeriesAnimation.createGrowVerticalAnimation();
        this.w = SeriesAnimation.createGrowVerticalAnimation();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public CandlestickSeriesStyle d() {
        return new CandlestickSeriesStyle();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public CandlestickSeriesStyle b(dd ddVar, int i, boolean z) {
        return ddVar.b(i, z);
    }

    /* access modifiers changed from: 0000 */
    public Drawable a(float f) {
        CandlestickSeriesStyle candlestickSeriesStyle = (CandlestickSeriesStyle) ((!isSelected() || this.r == null) ? this.q : this.r);
        if (candlestickSeriesStyle.c()) {
            return new bb();
        }
        return new ba(candlestickSeriesStyle.getRisingColor(), candlestickSeriesStyle.getOutlineColor(), f);
    }

    /* access modifiers changed from: 0000 */
    public InternalDataPoint a(Data<?, ?> data, Axis<?, ?> axis, Axis<?, ?> axis2) {
        return OHLCSeries.b(data, axis, axis2);
    }

    /* access modifiers changed from: 0000 */
    public void a(ao aoVar, InternalDataPoint internalDataPoint) {
        OHLCSeries.b(aoVar, internalDataPoint);
    }

    /* access modifiers changed from: 0000 */
    public void a(Axis<?, ?> axis, Axis<?, ?> axis2) {
        super.a(axis, axis2);
        super.n();
    }

    /* access modifiers changed from: 0000 */
    public NumberRange b(Axis<?, ?> axis) {
        return axis.a() ? a(axis.c(), o()) : a(p());
    }

    /* access modifiers changed from: 0000 */
    public NumberRange a(NumberRange numberRange) {
        if (Range.a((Range<?>) numberRange)) {
            return numberRange;
        }
        return new NumberRange(Double.valueOf(numberRange.a * 1.01d), Double.valueOf(numberRange.b * 1.01d));
    }

    /* access modifiers changed from: 0000 */
    public a f() {
        return a.HORIZONTAL;
    }

    /* access modifiers changed from: 0000 */
    public a g() {
        return a.HORIZONTAL;
    }

    public void setBaseline(Object obj) {
        cx.b(this.o != null ? this.o.getContext().getString(R.string.CandlestickSeriesBaselineNotApplicable) : "Baseline not applicable for CandlestickSeries.");
    }

    /* access modifiers changed from: 0000 */
    public aa c() {
        return new d();
    }

    /* access modifiers changed from: 0000 */
    public double b() {
        if (this.u.b != null) {
            return (getYAxis().i.a * (1.0d - ((double) this.u.b.floatValue()))) + (getYAxis().i.b * ((double) this.u.b.floatValue()));
        }
        return this.t.b((CartesianSeries<?>) this);
    }

    /* access modifiers changed from: 0000 */
    public boolean a(Axis<?, ?> axis, Axis<?, ?> axis2, Data<?, ?> data) {
        return OHLCSeries.b(axis, axis2, data);
    }
}
