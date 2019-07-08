package com.shinobicontrols.charts;

public class BandSeriesStyle extends SeriesStyle {
    final dj<Boolean> a = new dj<>(Boolean.valueOf(true));
    final dj<Integer> b = new dj<>(Integer.valueOf(-16777216));
    final dj<Integer> c = new dj<>(Integer.valueOf(-16777216));
    final dj<Float> d = new dj<>(Float.valueOf(2.0f));
    final dj<Integer> e = new dj<>(Integer.valueOf(-16776961));
    final dj<Integer> f = new dj<>(Integer.valueOf(0));
    PointStyle g = new PointStyle(this);
    PointStyle h = new PointStyle(this);

    /* access modifiers changed from: 0000 */
    public void a(SeriesStyle seriesStyle) {
        synchronized (x.a) {
            super.a(seriesStyle);
            BandSeriesStyle bandSeriesStyle = (BandSeriesStyle) seriesStyle;
            this.f.b(Integer.valueOf(bandSeriesStyle.getAreaColorInverted()));
            this.e.b(Integer.valueOf(bandSeriesStyle.getAreaColorNormal()));
            this.b.b(Integer.valueOf(bandSeriesStyle.getLineColorHigh()));
            this.c.b(Integer.valueOf(bandSeriesStyle.getLineColorLow()));
            this.d.b(Float.valueOf(bandSeriesStyle.getLineWidth()));
            this.a.b(Boolean.valueOf(bandSeriesStyle.isFillShown()));
            this.g.a(bandSeriesStyle.getPointStyle());
            this.h.a(bandSeriesStyle.getSelectedPointStyle());
        }
    }

    public boolean isFillShown() {
        return ((Boolean) this.a.a).booleanValue();
    }

    public void setFillShown(boolean z) {
        synchronized (x.a) {
            this.a.a(Boolean.valueOf(z));
            d();
        }
    }

    public int getLineColorHigh() {
        return ((Integer) this.b.a).intValue();
    }

    public void setLineColorHigh(int i) {
        synchronized (x.a) {
            this.b.a(Integer.valueOf(i));
            d();
        }
    }

    public int getLineColorLow() {
        return ((Integer) this.c.a).intValue();
    }

    public void setLineColorLow(int i) {
        synchronized (x.a) {
            this.c.a(Integer.valueOf(i));
            d();
        }
    }

    public float getLineWidth() {
        return ((Float) this.d.a).floatValue();
    }

    public void setLineWidth(float f2) {
        synchronized (x.a) {
            this.d.a(Float.valueOf(f2));
            d();
        }
    }

    public int getAreaColorNormal() {
        return ((Integer) this.e.a).intValue();
    }

    public void setAreaColorNormal(int i) {
        synchronized (x.a) {
            this.e.a(Integer.valueOf(i));
            d();
        }
    }

    public int getAreaColorInverted() {
        return ((Integer) this.f.a).intValue();
    }

    public void setAreaColorInverted(int i) {
        synchronized (x.a) {
            this.f.a(Integer.valueOf(i));
            d();
        }
    }

    public PointStyle getPointStyle() {
        return this.g;
    }

    public void setPointStyle(PointStyle pointStyle) {
        if (pointStyle != null) {
            synchronized (x.a) {
                this.g.j = null;
                this.g = pointStyle;
                this.g.j = this;
                d();
            }
            return;
        }
        throw new IllegalArgumentException("Styles may not be null");
    }

    public PointStyle getSelectedPointStyle() {
        return this.h;
    }

    public void setSelectedPointStyle(PointStyle pointStyle) {
        if (pointStyle != null) {
            synchronized (x.a) {
                this.h.j = null;
                this.h = pointStyle;
                this.h.j = this;
                d();
            }
            return;
        }
        throw new IllegalArgumentException("Styles may not be null");
    }
}
