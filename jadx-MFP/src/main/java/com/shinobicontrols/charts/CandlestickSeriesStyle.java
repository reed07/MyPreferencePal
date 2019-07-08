package com.shinobicontrols.charts;

public class CandlestickSeriesStyle extends SeriesStyle {
    final dj<Integer> a = new dj<>(Integer.valueOf(-16777216));
    final dj<Integer> b = new dj<>(Integer.valueOf(0));
    final dj<Integer> c = new dj<>(Integer.valueOf(-16777216));
    final dj<Integer> d = new dj<>(Integer.valueOf(0));
    final dj<Integer> e = new dj<>(Integer.valueOf(-16777216));
    final dj<Integer> f = new dj<>(Integer.valueOf(-16777216));
    final dj<Float> g = new dj<>(Float.valueOf(2.0f));
    final dj<Float> h = new dj<>(Float.valueOf(2.0f));

    /* access modifiers changed from: 0000 */
    public void a(SeriesStyle seriesStyle) {
        synchronized (x.a) {
            super.a(seriesStyle);
            CandlestickSeriesStyle candlestickSeriesStyle = (CandlestickSeriesStyle) seriesStyle;
            this.c.b(Integer.valueOf(candlestickSeriesStyle.getFallingColor()));
            this.d.b(Integer.valueOf(candlestickSeriesStyle.getFallingColorGradient()));
            this.a.b(Integer.valueOf(candlestickSeriesStyle.getRisingColor()));
            this.b.b(Integer.valueOf(candlestickSeriesStyle.getRisingColorGradient()));
            this.e.b(Integer.valueOf(candlestickSeriesStyle.getOutlineColor()));
            this.h.b(Float.valueOf(candlestickSeriesStyle.getOutlineWidth()));
            this.f.b(Integer.valueOf(candlestickSeriesStyle.getStickColor()));
            this.g.b(Float.valueOf(candlestickSeriesStyle.getStickWidth()));
        }
    }

    public int getRisingColor() {
        return ((Integer) this.a.a).intValue();
    }

    public void setRisingColor(int i) {
        synchronized (x.a) {
            this.a.a(Integer.valueOf(i));
            d();
        }
    }

    public int getRisingColorGradient() {
        return ((Integer) this.b.a).intValue();
    }

    public void setRisingColorGradient(int i) {
        synchronized (x.a) {
            this.b.a(Integer.valueOf(i));
            d();
        }
    }

    public int getFallingColor() {
        return ((Integer) this.c.a).intValue();
    }

    public void setFallingColor(int i) {
        synchronized (x.a) {
            this.c.a(Integer.valueOf(i));
            d();
        }
    }

    public int getFallingColorGradient() {
        return ((Integer) this.d.a).intValue();
    }

    public void setFallingColorGradient(int i) {
        synchronized (x.a) {
            this.d.a(Integer.valueOf(i));
            d();
        }
    }

    public int getOutlineColor() {
        return ((Integer) this.e.a).intValue();
    }

    public void setOutlineColor(int i) {
        synchronized (x.a) {
            this.e.a(Integer.valueOf(i));
            d();
        }
    }

    public int getStickColor() {
        return ((Integer) this.f.a).intValue();
    }

    public void setStickColor(int i) {
        synchronized (x.a) {
            this.f.a(Integer.valueOf(i));
            d();
        }
    }

    public float getStickWidth() {
        return ((Float) this.g.a).floatValue();
    }

    public void setStickWidth(float f2) {
        synchronized (x.a) {
            this.g.a(Float.valueOf(f2));
            d();
        }
    }

    public float getOutlineWidth() {
        return ((Float) this.h.a).floatValue();
    }

    public void setOutlineWidth(float f2) {
        synchronized (x.a) {
            this.h.a(Float.valueOf(f2));
            d();
        }
    }
}
