package com.shinobicontrols.charts;

import com.shinobicontrols.charts.SeriesStyle.FillStyle;

abstract class BarColumnSeriesStyle extends SeriesStyle {
    final dj<Integer> a = new dj<>(Integer.valueOf(-16777216));
    final dj<Integer> b = new dj<>(Integer.valueOf(-16777216));
    final dj<Integer> c = new dj<>(Integer.valueOf(-16777216));
    final dj<Integer> d = new dj<>(Integer.valueOf(-16777216));
    final dj<Integer> e = new dj<>(Integer.valueOf(-16777216));
    final dj<Integer> f = new dj<>(Integer.valueOf(-16777216));
    final dj<Float> g = new dj<>(Float.valueOf(1.0f));
    final dj<Boolean> h = new dj<>(Boolean.valueOf(false));
    final dj<FillStyle> i = new dj<>(FillStyle.GRADIENT);

    BarColumnSeriesStyle() {
    }

    /* access modifiers changed from: 0000 */
    public void a(SeriesStyle seriesStyle) {
        synchronized (x.a) {
            super.a(seriesStyle);
            BarColumnSeriesStyle barColumnSeriesStyle = (BarColumnSeriesStyle) seriesStyle;
            this.a.b(Integer.valueOf(barColumnSeriesStyle.getAreaColor()));
            this.b.b(Integer.valueOf(barColumnSeriesStyle.getAreaColorBelowBaseline()));
            this.c.b(Integer.valueOf(barColumnSeriesStyle.getAreaColorGradientBelowBaseline()));
            this.d.b(Integer.valueOf(barColumnSeriesStyle.getAreaColorGradient()));
            this.e.b(Integer.valueOf(barColumnSeriesStyle.getLineColor()));
            this.f.b(Integer.valueOf(barColumnSeriesStyle.getLineColorBelowBaseline()));
            this.g.b(Float.valueOf(barColumnSeriesStyle.getLineWidth()));
            this.i.b(barColumnSeriesStyle.getFillStyle());
            this.h.b(Boolean.valueOf(barColumnSeriesStyle.isLineShown()));
        }
    }

    public int getAreaColor() {
        return ((Integer) this.a.a).intValue();
    }

    public void setAreaColor(int i2) {
        synchronized (x.a) {
            this.a.a(Integer.valueOf(i2));
            d();
        }
    }

    public int getAreaColorBelowBaseline() {
        return ((Integer) this.b.a).intValue();
    }

    public void setAreaColorBelowBaseline(int i2) {
        synchronized (x.a) {
            this.b.a(Integer.valueOf(i2));
            d();
        }
    }

    public int getAreaColorGradient() {
        return ((Integer) this.d.a).intValue();
    }

    public void setAreaColorGradient(int i2) {
        synchronized (x.a) {
            this.d.a(Integer.valueOf(i2));
            d();
        }
    }

    public int getAreaColorGradientBelowBaseline() {
        return ((Integer) this.c.a).intValue();
    }

    public void setAreaColorGradientBelowBaseline(int i2) {
        synchronized (x.a) {
            this.c.a(Integer.valueOf(i2));
            d();
        }
    }

    public int getLineColor() {
        return ((Integer) this.e.a).intValue();
    }

    public void setLineColor(int i2) {
        synchronized (x.a) {
            this.e.a(Integer.valueOf(i2));
            d();
        }
    }

    public int getLineColorBelowBaseline() {
        return ((Integer) this.f.a).intValue();
    }

    public void setLineColorBelowBaseline(int i2) {
        synchronized (x.a) {
            this.f.a(Integer.valueOf(i2));
            d();
        }
    }

    public float getLineWidth() {
        return ((Float) this.g.a).floatValue();
    }

    public void setLineWidth(float f2) {
        synchronized (x.a) {
            this.g.a(Float.valueOf(f2));
            d();
        }
    }

    public boolean isLineShown() {
        return ((Boolean) this.h.a).booleanValue();
    }

    public void setLineShown(boolean z) {
        synchronized (x.a) {
            this.h.a(Boolean.valueOf(z));
            d();
        }
    }

    public FillStyle getFillStyle() {
        return (FillStyle) this.i.a;
    }

    public void setFillStyle(FillStyle fillStyle) {
        synchronized (x.a) {
            this.i.a(fillStyle);
            d();
        }
    }
}
