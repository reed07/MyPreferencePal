package com.shinobicontrols.charts;

import android.graphics.drawable.Drawable;
import com.shinobicontrols.charts.Series.Orientation;

public class BandSeries extends CartesianSeries<BandSeriesStyle> {
    public BandSeries() {
        this(Orientation.HORIZONTAL);
    }

    public BandSeries(Orientation orientation) {
        this.j = orientation;
        this.p = new k(this);
        this.k = new j(this);
        setStyle(d());
        setSelectedStyle(d());
        this.v = SeriesAnimation.createTelevisionAnimation();
        this.w = SeriesAnimation.createTelevisionAnimation();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public BandSeriesStyle d() {
        return new BandSeriesStyle();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public BandSeriesStyle b(dd ddVar, int i, boolean z) {
        return ddVar.a(i, z);
    }

    /* access modifiers changed from: 0000 */
    public Drawable a(float f) {
        BandSeriesStyle bandSeriesStyle = (BandSeriesStyle) ((!isSelected() || this.r == null) ? this.q : this.r);
        if (bandSeriesStyle.c()) {
            return new az();
        }
        return new ba(bandSeriesStyle.getAreaColorNormal(), bandSeriesStyle.getLineColorHigh(), f);
    }

    /* access modifiers changed from: 0000 */
    public InternalDataPoint a(Data<?, ?> data, Axis<?, ?> axis, Axis<?, ?> axis2) {
        double d;
        double d2;
        MultiValueData multiValueData = (MultiValueData) data;
        InternalDataPoint internalDataPoint = new InternalDataPoint();
        if (this.j == Orientation.HORIZONTAL) {
            double convertPoint = axis.convertPoint(data.getX());
            internalDataPoint.a = convertPoint;
            internalDataPoint.c = convertPoint;
            d2 = axis2.convertPoint(multiValueData.getHigh());
            d = axis2.convertPoint(multiValueData.getLow());
        } else {
            double convertPoint2 = axis2.convertPoint(data.getX());
            internalDataPoint.a = convertPoint2;
            internalDataPoint.c = convertPoint2;
            d2 = axis.convertPoint(multiValueData.getHigh());
            d = axis.convertPoint(multiValueData.getLow());
        }
        internalDataPoint.a(d, d2);
        if (data instanceof SelectableData) {
            internalDataPoint.h = ((SelectableData) data).getSelected();
        }
        return internalDataPoint;
    }

    /* access modifiers changed from: 0000 */
    public void a(ao aoVar, InternalDataPoint internalDataPoint) {
        if (this.j == Orientation.HORIZONTAL) {
            aoVar.a.a(internalDataPoint.a);
            aoVar.b.a(((Double) internalDataPoint.j.get("Low")).doubleValue());
            aoVar.b.a(((Double) internalDataPoint.j.get("High")).doubleValue());
            return;
        }
        aoVar.b.a(internalDataPoint.a);
        aoVar.a.a(((Double) internalDataPoint.j.get("Low")).doubleValue());
        aoVar.a.a(((Double) internalDataPoint.j.get("High")).doubleValue());
    }

    /* access modifiers changed from: 0000 */
    public void a(Axis<?, ?> axis, Axis<?, ?> axis2) {
        super.a(axis, axis2);
        super.n();
    }

    /* access modifiers changed from: 0000 */
    public double b() {
        if (this.u.b != null) {
            return (getYAxis().i.a * (1.0d - ((double) this.u.b.floatValue()))) + (getYAxis().i.b * ((double) this.u.b.floatValue()));
        }
        return this.t.b((CartesianSeries<?>) this);
    }

    public void setBaseline(Object obj) {
        cx.b(this.o != null ? this.o.getContext().getString(R.string.BandSeriesBaselineNotApplicable) : "Baseline not applicable for BandSeries.");
    }

    /* access modifiers changed from: 0000 */
    public aa c() {
        return new d();
    }

    /* access modifiers changed from: 0000 */
    public boolean a(Axis<?, ?> axis, Axis<?, ?> axis2, Data<?, ?> data) {
        return (this.j == Orientation.HORIZONTAL ? a(axis, data) : a(axis2, data)) || (this.j == Orientation.HORIZONTAL ? b(axis2, data) : b(axis, data));
    }

    private boolean a(Axis<?, ?> axis, Data<?, ?> data) {
        return axis.isUserDataPointWithinASkipRange(data.getX());
    }

    private boolean b(Axis<?, ?> axis, Data<?, ?> data) {
        MultiValueData multiValueData = (MultiValueData) data;
        return axis.isUserDataPointWithinASkipRange(multiValueData.getHigh()) || axis.isUserDataPointWithinASkipRange(multiValueData.getLow());
    }
}
