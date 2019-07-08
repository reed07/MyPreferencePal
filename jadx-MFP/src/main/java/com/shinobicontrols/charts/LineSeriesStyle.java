package com.shinobicontrols.charts;

import com.shinobicontrols.charts.SeriesStyle.FillStyle;

public final class LineSeriesStyle extends SeriesStyle {
    final dj<Integer> a = new dj<>(Integer.valueOf(0));
    final dj<Integer> b = new dj<>(Integer.valueOf(0));
    final dj<Integer> c = new dj<>(Integer.valueOf(0));
    final dj<Integer> d = new dj<>(Integer.valueOf(0));
    final dj<Integer> e = new dj<>(Integer.valueOf(-16777216));
    final dj<Integer> f = new dj<>(Integer.valueOf(0));
    final dj<Float> g = new dj<>(Float.valueOf(1.0f));
    final dj<Integer> h = new dj<>(Integer.valueOf(-16777216));
    final dj<Integer> i = new dj<>(Integer.valueOf(-16777216));
    final dj<Float> j = new dj<>(Float.valueOf(1.0f));
    final dj<Boolean> k = new dj<>(Boolean.valueOf(true));
    final dj<FillStyle> l = new dj<>(FillStyle.NONE);
    dj<a> m = new dj<>(a.HORIZONTAL);
    private PointStyle o = new PointStyle(this);
    private PointStyle p = new PointStyle(this);

    enum a {
        HORIZONTAL,
        VERTICAL
    }

    /* access modifiers changed from: 0000 */
    public void a(SeriesStyle seriesStyle) {
        synchronized (x.a) {
            super.a(seriesStyle);
            LineSeriesStyle lineSeriesStyle = (LineSeriesStyle) seriesStyle;
            this.a.b(Integer.valueOf(lineSeriesStyle.getAreaColor()));
            this.b.b(Integer.valueOf(lineSeriesStyle.getAreaColorBelowBaseline()));
            this.c.b(Integer.valueOf(lineSeriesStyle.getAreaColorGradientBelowBaseline()));
            this.d.b(Integer.valueOf(lineSeriesStyle.getAreaColorGradient()));
            this.e.b(Integer.valueOf(lineSeriesStyle.getAreaLineColor()));
            this.f.b(Integer.valueOf(lineSeriesStyle.getAreaLineColorBelowBaseline()));
            this.l.b(lineSeriesStyle.getFillStyle());
            this.h.b(Integer.valueOf(lineSeriesStyle.getLineColor()));
            this.i.b(Integer.valueOf(lineSeriesStyle.getLineColorBelowBaseline()));
            this.j.b(Float.valueOf(lineSeriesStyle.getLineWidth()));
            this.k.b(Boolean.valueOf(lineSeriesStyle.isLineShown()));
            this.o.a(lineSeriesStyle.getPointStyle());
            this.p.a(lineSeriesStyle.getSelectedPointStyle());
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

    public int getAreaLineColor() {
        return ((Integer) this.e.a).intValue();
    }

    public void setAreaLineColor(int i2) {
        synchronized (x.a) {
            this.e.a(Integer.valueOf(i2));
            d();
        }
    }

    public int getAreaLineColorBelowBaseline() {
        return ((Integer) this.f.a).intValue();
    }

    public void setAreaLineColorBelowBaseline(int i2) {
        synchronized (x.a) {
            this.f.a(Integer.valueOf(i2));
            d();
        }
    }

    public float getAreaLineWidth() {
        return ((Float) this.g.a).floatValue();
    }

    public void setAreaLineWidth(float f2) {
        synchronized (x.a) {
            this.g.a(Float.valueOf(f2));
            d();
        }
    }

    public FillStyle getFillStyle() {
        return (FillStyle) this.l.a;
    }

    public void setFillStyle(FillStyle fillStyle) {
        synchronized (x.a) {
            this.l.a(fillStyle);
            d();
        }
    }

    public int getLineColor() {
        return ((Integer) this.h.a).intValue();
    }

    public void setLineColor(int i2) {
        synchronized (x.a) {
            this.h.a(Integer.valueOf(i2));
            d();
        }
    }

    public int getLineColorBelowBaseline() {
        return ((Integer) this.i.a).intValue();
    }

    public void setLineColorBelowBaseline(int i2) {
        synchronized (x.a) {
            this.i.a(Integer.valueOf(i2));
            d();
        }
    }

    public float getLineWidth() {
        return ((Float) this.j.a).floatValue();
    }

    public void setLineWidth(float f2) {
        synchronized (x.a) {
            this.j.a(Float.valueOf(f2));
            d();
        }
    }

    public PointStyle getPointStyle() {
        return this.o;
    }

    public void setPointStyle(PointStyle pointStyle) {
        if (pointStyle != null) {
            synchronized (x.a) {
                this.o.j = null;
                this.o = pointStyle;
                this.o.j = this;
                d();
            }
            return;
        }
        throw new IllegalArgumentException("Styles may not be null");
    }

    public PointStyle getSelectedPointStyle() {
        return this.p;
    }

    public void setSelectedPointStyle(PointStyle pointStyle) {
        if (pointStyle != null) {
            synchronized (x.a) {
                this.p.j = null;
                this.p = pointStyle;
                this.p.j = this;
                d();
            }
            return;
        }
        throw new IllegalArgumentException("Styles may not be null");
    }

    public boolean isLineShown() {
        return ((Boolean) this.k.a).booleanValue();
    }

    public void setLineShown(boolean z) {
        synchronized (x.a) {
            this.k.a(Boolean.valueOf(z));
            d();
        }
    }
}
