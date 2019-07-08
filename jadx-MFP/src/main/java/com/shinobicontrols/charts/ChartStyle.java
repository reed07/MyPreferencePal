package com.shinobicontrols.charts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public final class ChartStyle {
    final dj<Integer> a = new dj<>(Integer.valueOf(-1));
    final dj<Integer> b = new dj<>(Integer.valueOf(-1));
    final dj<Integer> c = new dj<>(Integer.valueOf(-1));
    final dj<Float> d = new dj<>(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
    final dj<Integer> e = new dj<>(Integer.valueOf(-1));
    final dj<Integer> f = new dj<>(Integer.valueOf(-1));
    final dj<Integer> g = new dj<>(Integer.valueOf(-1));
    final dj<Float> h = new dj<>(Float.valueOf(BitmapDescriptorFactory.HUE_RED));

    /* access modifiers changed from: 0000 */
    public void a(ChartStyle chartStyle) {
        if (chartStyle != null) {
            this.a.b(Integer.valueOf(chartStyle.getBackgroundColor()));
            this.b.b(Integer.valueOf(chartStyle.a()));
            this.c.b(Integer.valueOf(chartStyle.b()));
            this.d.b(Float.valueOf(chartStyle.c()));
            this.e.b(Integer.valueOf(chartStyle.getCanvasBackgroundColor()));
            this.f.b(Integer.valueOf(chartStyle.getPlotAreaBackgroundColor()));
            this.g.b(Integer.valueOf(chartStyle.d()));
            this.h.b(Float.valueOf(chartStyle.e()));
        }
    }

    public int getBackgroundColor() {
        return ((Integer) this.a.a).intValue();
    }

    public void setBackgroundColor(int i) {
        this.a.a(Integer.valueOf(i));
    }

    /* access modifiers changed from: 0000 */
    public int a() {
        return ((Integer) this.b.a).intValue();
    }

    /* access modifiers changed from: 0000 */
    public int b() {
        return ((Integer) this.c.a).intValue();
    }

    /* access modifiers changed from: 0000 */
    public float c() {
        return ((Float) this.d.a).floatValue();
    }

    public int getCanvasBackgroundColor() {
        return ((Integer) this.e.a).intValue();
    }

    public void setCanvasBackgroundColor(int i) {
        this.e.a(Integer.valueOf(i));
    }

    public int getPlotAreaBackgroundColor() {
        return ((Integer) this.f.a).intValue();
    }

    public void setPlotAreaBackgroundColor(int i) {
        this.f.a(Integer.valueOf(i));
    }

    /* access modifiers changed from: 0000 */
    public int d() {
        return ((Integer) this.g.a).intValue();
    }

    /* access modifiers changed from: 0000 */
    public float e() {
        return ((Float) this.h.a).floatValue();
    }
}
