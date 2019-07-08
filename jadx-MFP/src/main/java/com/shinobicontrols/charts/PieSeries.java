package com.shinobicontrols.charts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class PieSeries extends PieDonutSeries<PieSeriesStyle> {
    /* access modifiers changed from: 0000 */
    public float b(float f) {
        return BitmapDescriptorFactory.HUE_RED;
    }

    public PieSeries() {
        this.p = new bt(this);
        setStyle(d());
        setSelectedStyle(d());
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public PieSeriesStyle d() {
        return new PieSeriesStyle();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public PieSeriesStyle b(dd ddVar, int i, boolean z) {
        return ddVar.g(i, z);
    }
}
