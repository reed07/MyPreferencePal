package com.shinobicontrols.charts;

import android.graphics.drawable.Drawable;
import com.shinobicontrols.charts.Axis.Orientation;
import com.shinobicontrols.charts.SeriesStyle;
import com.shinobicontrols.charts.SeriesStyle.FillStyle;

abstract class BarColumnSeries<T extends SeriesStyle> extends CartesianSeries<T> {
    float a = 0.8f;

    BarColumnSeries() {
    }

    /* access modifiers changed from: 0000 */
    public float a() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public void b(float f) {
        synchronized (x.a) {
            this.a = f;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Axis<?, ?> axis) {
        if (axis.a(this.j)) {
            for (InternalDataPoint a2 : this.n.c) {
                a(a2, axis);
            }
        }
    }

    private void a(InternalDataPoint internalDataPoint, Axis<?, ?> axis) {
        if (axis.c == Orientation.HORIZONTAL) {
            internalDataPoint.c = a(internalDataPoint.a, axis);
            internalDataPoint.d = internalDataPoint.b;
            return;
        }
        internalDataPoint.d = a(internalDataPoint.b, axis);
        internalDataPoint.c = internalDataPoint.a;
    }

    private double a(double d, Axis<?, ?> axis) {
        int c = this.t.c();
        int i = i();
        double b = axis.j.b();
        if (b == 0.0d) {
            b = 1.0d;
        }
        double d2 = axis.j.a;
        double d3 = d2 + (((axis.e - d2) * b) / b);
        double floatValue = ((double) (1.0f - ((Float) axis.g.b.a).floatValue())) * axis.f;
        double d4 = (double) c;
        double floatValue2 = (floatValue / d4) * ((double) (1.0f - ((Float) axis.g.a.a).floatValue()));
        b((float) floatValue2);
        return (((d3 + (d - axis.e)) + (floatValue2 / 2.0d)) - (floatValue / 2.0d)) + ((((double) ((Float) axis.g.a.a).floatValue()) * floatValue) / ((double) (c * 2))) + ((((double) i) * floatValue) / d4);
    }

    /* access modifiers changed from: 0000 */
    public NumberRange a(double d, NumberRange numberRange) {
        if (Range.a((Range<?>) numberRange)) {
            return numberRange;
        }
        double d2 = d * 0.5d;
        return new NumberRange(Double.valueOf(numberRange.a - d2), Double.valueOf(numberRange.b + d2));
    }

    /* access modifiers changed from: 0000 */
    public NumberRange a(NumberRange numberRange) {
        if (Range.a((Range<?>) numberRange)) {
            return numberRange;
        }
        NumberRange numberRange2 = new NumberRange(Double.valueOf(numberRange.a * 1.01d), Double.valueOf(numberRange.b * 1.01d));
        numberRange2.a(this.t.b((CartesianSeries<?>) this));
        return numberRange2;
    }

    /* access modifiers changed from: 0000 */
    public Drawable c(float f) {
        BarColumnSeriesStyle barColumnSeriesStyle = (BarColumnSeriesStyle) ((!isSelected() || this.r == null) ? this.q : this.r);
        int i = 0;
        int areaColor = barColumnSeriesStyle.getFillStyle() == FillStyle.NONE ? 0 : barColumnSeriesStyle.getAreaColor();
        if (barColumnSeriesStyle.isLineShown()) {
            i = barColumnSeriesStyle.getLineColor();
        }
        return new ba(areaColor, i, f);
    }
}
