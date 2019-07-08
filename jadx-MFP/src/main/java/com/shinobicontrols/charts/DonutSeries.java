package com.shinobicontrols.charts;

public class DonutSeries extends PieDonutSeries<DonutSeriesStyle> {
    /* access modifiers changed from: 0000 */
    public float b(float f) {
        return f;
    }

    public DonutSeries() {
        this.p = new bt(this);
        setStyle(d());
        setSelectedStyle(d());
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public DonutSeriesStyle d() {
        return new DonutSeriesStyle();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public DonutSeriesStyle b(dd ddVar, int i, boolean z) {
        return ddVar.h(i, z);
    }

    public float getInnerRadius() {
        return super.getInnerRadius();
    }

    public void setInnerRadius(float f) {
        synchronized (x.a) {
            this.a.a(Float.valueOf(f));
            a_();
        }
    }
}
