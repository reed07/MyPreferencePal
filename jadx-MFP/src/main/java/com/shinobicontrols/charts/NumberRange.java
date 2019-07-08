package com.shinobicontrols.charts;

public class NumberRange extends Range<Double> {
    NumberRange() {
        super(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
    }

    public NumberRange(Double d, Double d2) {
        super(d.doubleValue(), d2.doubleValue());
    }

    /* access modifiers changed from: 0000 */
    public void a(double d, double d2) {
        this.a = d;
        this.b = d2;
    }

    public Double getMinimum() {
        return Double.valueOf(this.a);
    }

    public Double getMaximum() {
        return Double.valueOf(this.b);
    }

    /* access modifiers changed from: 0000 */
    public Range<Double> a() {
        return new NumberRange(Double.valueOf(this.a), Double.valueOf(this.b));
    }
}
