package com.shinobicontrols.charts;

public class OHLCSeriesStyle extends SeriesStyle {
    final dj<Integer> a = new dj<>(Integer.valueOf(-16777216));
    final dj<Integer> b = new dj<>(Integer.valueOf(0));
    final dj<Integer> c = new dj<>(Integer.valueOf(-16777216));
    final dj<Integer> d = new dj<>(Integer.valueOf(0));
    final dj<Float> e = new dj<>(Float.valueOf(2.0f));
    final dj<Float> f = new dj<>(Float.valueOf(2.0f));

    /* access modifiers changed from: 0000 */
    public void a(SeriesStyle seriesStyle) {
        synchronized (x.a) {
            super.a(seriesStyle);
            OHLCSeriesStyle oHLCSeriesStyle = (OHLCSeriesStyle) seriesStyle;
            this.f.b(Float.valueOf(oHLCSeriesStyle.getArmWidth()));
            this.c.b(Integer.valueOf(oHLCSeriesStyle.getFallingColor()));
            this.d.b(Integer.valueOf(oHLCSeriesStyle.getFallingColorGradient()));
            this.a.b(Integer.valueOf(oHLCSeriesStyle.getRisingColor()));
            this.b.b(Integer.valueOf(oHLCSeriesStyle.getRisingColorGradient()));
            this.e.b(Float.valueOf(oHLCSeriesStyle.getTrunkWidth()));
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

    public float getTrunkWidth() {
        return ((Float) this.e.a).floatValue();
    }

    public void setTrunkWidth(float f2) {
        synchronized (x.a) {
            this.e.a(Float.valueOf(f2));
            d();
        }
    }

    public float getArmWidth() {
        return ((Float) this.f.a).floatValue();
    }

    public void setArmWidth(float f2) {
        synchronized (x.a) {
            this.f.a(Float.valueOf(f2));
            d();
        }
    }
}
