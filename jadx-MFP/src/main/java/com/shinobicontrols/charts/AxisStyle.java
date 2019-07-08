package com.shinobicontrols.charts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.shinobicontrols.charts.PropertyChangedEvent.Handler;

public final class AxisStyle {
    final dj<Float> a = new dj<>(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
    final dj<Float> b = new dj<>(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
    final dj<Integer> c = new dj<>(Integer.valueOf(-16777216));
    final dj<Float> d = new dj<>(Float.valueOf(1.0f));
    GridStripeStyle e = new GridStripeStyle();
    GridlineStyle f = new GridlineStyle();
    TickStyle g = new TickStyle();
    AxisTitleStyle h = new AxisTitleStyle();
    private final al i = new al();

    /* access modifiers changed from: 0000 */
    public void a(AxisStyle axisStyle) {
        if (axisStyle != null) {
            this.a.b(Float.valueOf(axisStyle.getInterSeriesPadding()));
            this.b.b(Float.valueOf(axisStyle.getInterSeriesSetPadding()));
            this.c.b(Integer.valueOf(axisStyle.getLineColor()));
            this.d.b(Float.valueOf(axisStyle.getLineWidth()));
            this.e.a(axisStyle.e);
            this.f.a(axisStyle.f);
            this.g.a(axisStyle.g);
            this.h.a(axisStyle.h);
        }
    }

    public GridStripeStyle getGridStripeStyle() {
        return this.e;
    }

    public void setGridStripeStyle(GridStripeStyle gridStripeStyle) {
        this.e = gridStripeStyle;
    }

    public float getInterSeriesPadding() {
        return ((Float) this.a.a).floatValue();
    }

    public void setInterSeriesPadding(float f2) {
        if (f2 < BitmapDescriptorFactory.HUE_RED || f2 >= 1.0f) {
            f2 = BitmapDescriptorFactory.HUE_RED;
        }
        synchronized (x.a) {
            this.a.a(Float.valueOf(f2));
            a();
        }
    }

    public float getInterSeriesSetPadding() {
        return ((Float) this.b.a).floatValue();
    }

    public void setInterSeriesSetPadding(float f2) {
        if (f2 < BitmapDescriptorFactory.HUE_RED || f2 >= 1.0f) {
            f2 = BitmapDescriptorFactory.HUE_RED;
        }
        synchronized (x.a) {
            this.b.a(Float.valueOf(f2));
            a();
        }
    }

    public int getLineColor() {
        return ((Integer) this.c.a).intValue();
    }

    public void setLineColor(int i2) {
        this.c.a(Integer.valueOf(i2));
    }

    public float getLineWidth() {
        return ((Float) this.d.a).floatValue();
    }

    public void setLineWidth(float f2) {
        this.d.a(Float.valueOf(f2));
    }

    public GridlineStyle getGridlineStyle() {
        return this.f;
    }

    public void setGridlineStyle(GridlineStyle gridlineStyle) {
        this.f = gridlineStyle;
    }

    public TickStyle getTickStyle() {
        return this.g;
    }

    public void setTickStyle(TickStyle tickStyle) {
        this.g = tickStyle;
    }

    public AxisTitleStyle getTitleStyle() {
        return this.h;
    }

    public void setTitleStyle(AxisTitleStyle axisTitleStyle) {
        this.h = axisTitleStyle;
    }

    private final void a() {
        this.i.a(new PropertyChangedEvent());
    }

    /* access modifiers changed from: 0000 */
    public am a(Handler handler) {
        return this.i.a(PropertyChangedEvent.a, (a) handler);
    }
}
