package com.shinobicontrols.charts;

import android.graphics.drawable.Drawable;

public class OHLCSeries extends BarColumnSeries<OHLCSeriesStyle> {
    public OHLCSeries() {
        this.p = new bs(this);
        this.k = new br(this);
        setStyle(d());
        setSelectedStyle(d());
        this.v = SeriesAnimation.createGrowVerticalAnimation();
        this.w = SeriesAnimation.createGrowVerticalAnimation();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public OHLCSeriesStyle d() {
        return new OHLCSeriesStyle();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public OHLCSeriesStyle b(dd ddVar, int i, boolean z) {
        return ddVar.c(i, z);
    }

    /* access modifiers changed from: 0000 */
    public Drawable a(float f) {
        OHLCSeriesStyle oHLCSeriesStyle = (OHLCSeriesStyle) ((!isSelected() || this.r == null) ? this.q : this.r);
        if (oHLCSeriesStyle.c()) {
            return new bd();
        }
        return new ba(oHLCSeriesStyle.getRisingColor(), oHLCSeriesStyle.getRisingColor(), f);
    }

    /* access modifiers changed from: 0000 */
    public InternalDataPoint a(Data<?, ?> data, Axis<?, ?> axis, Axis<?, ?> axis2) {
        return b(data, axis, axis2);
    }

    static InternalDataPoint b(Data<?, ?> data, Axis<?, ?> axis, Axis<?, ?> axis2) {
        InternalDataPoint internalDataPoint = new InternalDataPoint();
        double convertPoint = axis.convertPoint(data.getX());
        internalDataPoint.a = convertPoint;
        internalDataPoint.c = convertPoint;
        MultiValueData multiValueData = (MultiValueData) data;
        InternalDataPoint internalDataPoint2 = internalDataPoint;
        internalDataPoint2.a(axis2.convertPoint(multiValueData.getOpen()), axis2.convertPoint(multiValueData.getHigh()), axis2.convertPoint(multiValueData.getLow()), axis2.convertPoint(multiValueData.getClose()));
        if (data instanceof SelectableData) {
            internalDataPoint.h = ((SelectableData) data).getSelected();
        }
        return internalDataPoint;
    }

    /* access modifiers changed from: 0000 */
    public void a(ao aoVar, InternalDataPoint internalDataPoint) {
        b(aoVar, internalDataPoint);
    }

    static void b(ao aoVar, InternalDataPoint internalDataPoint) {
        aoVar.a.a(internalDataPoint.a);
        aoVar.b.a(((Double) internalDataPoint.j.get("Low")).doubleValue());
        aoVar.b.a(((Double) internalDataPoint.j.get("High")).doubleValue());
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
        cx.b(this.o != null ? this.o.getContext().getString(R.string.OHLCSeriesBaselineNotApplicable) : "Baseline not applicable for OHLCSeries.");
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
        return b(axis, axis2, data);
    }

    static boolean b(Axis<?, ?> axis, Axis<?, ?> axis2, Data<?, ?> data) {
        return a(axis, data) || b(axis2, data);
    }

    private static boolean a(Axis<?, ?> axis, Data<?, ?> data) {
        return axis.isUserDataPointWithinASkipRange(data.getX());
    }

    private static boolean b(Axis<?, ?> axis, Data<?, ?> data) {
        MultiValueData multiValueData = (MultiValueData) data;
        return axis.isUserDataPointWithinASkipRange(multiValueData.getOpen()) || axis.isUserDataPointWithinASkipRange(multiValueData.getHigh()) || axis.isUserDataPointWithinASkipRange(multiValueData.getLow()) || axis.isUserDataPointWithinASkipRange(multiValueData.getClose());
    }
}
