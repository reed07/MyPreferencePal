package com.shinobicontrols.charts;

import android.graphics.drawable.Drawable;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public final class PointStyle {
    final dj<Integer> a = new dj<>(Integer.valueOf(-16777216));
    final dj<Integer> b = new dj<>(Integer.valueOf(-16777216));
    final dj<Float> c = new dj<>(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
    final dj<Integer> d = new dj<>(Integer.valueOf(-16777216));
    final dj<Integer> e = new dj<>(Integer.valueOf(-16777216));
    final dj<Float> f = new dj<>(Float.valueOf(3.0f));
    final dj<Float> g = new dj<>(Float.valueOf(5.0f));
    final dj<Boolean> h = new dj<>(Boolean.valueOf(false));
    final dj<Drawable> i = new dj<>(null);
    SeriesStyle j = null;

    public PointStyle() {
    }

    PointStyle(SeriesStyle seriesStyle) {
        this.j = seriesStyle;
    }

    /* access modifiers changed from: 0000 */
    public void a(PointStyle pointStyle) {
        synchronized (x.a) {
            if (pointStyle != null) {
                this.a.b(Integer.valueOf(pointStyle.getColor()));
                this.b.b(Integer.valueOf(pointStyle.getColorBelowBaseline()));
                this.c.b(Float.valueOf(pointStyle.a()));
                this.d.b(Integer.valueOf(pointStyle.getInnerColor()));
                this.e.b(Integer.valueOf(pointStyle.getInnerColorBelowBaseline()));
                this.f.b(Float.valueOf(pointStyle.getInnerRadius()));
                this.g.b(Float.valueOf(pointStyle.getRadius()));
                this.h.b(Boolean.valueOf(pointStyle.arePointsShown()));
                this.i.b(pointStyle.b());
            }
        }
    }

    public int getColor() {
        return ((Integer) this.a.a).intValue();
    }

    public void setColor(int i2) {
        synchronized (x.a) {
            this.a.a(Integer.valueOf(i2));
            c();
        }
    }

    public int getColorBelowBaseline() {
        return ((Integer) this.b.a).intValue();
    }

    public void setColorBelowBaseline(int i2) {
        synchronized (x.a) {
            this.b.a(Integer.valueOf(i2));
            c();
        }
    }

    /* access modifiers changed from: 0000 */
    public float a() {
        return ((Float) this.c.a).floatValue();
    }

    /* access modifiers changed from: 0000 */
    public void a(float f2) {
        synchronized (x.a) {
            this.c.a(Float.valueOf(f2));
            c();
        }
    }

    public int getInnerColor() {
        return ((Integer) this.d.a).intValue();
    }

    public void setInnerColor(int i2) {
        synchronized (x.a) {
            this.d.a(Integer.valueOf(i2));
            c();
        }
    }

    public int getInnerColorBelowBaseline() {
        return ((Integer) this.e.a).intValue();
    }

    public void setInnerColorBelowBaseline(int i2) {
        synchronized (x.a) {
            this.e.a(Integer.valueOf(i2));
            c();
        }
    }

    public float getInnerRadius() {
        return ((Float) this.f.a).floatValue();
    }

    public void setInnerRadius(float f2) {
        synchronized (x.a) {
            this.f.a(Float.valueOf(f2));
            c();
        }
    }

    public float getRadius() {
        return ((Float) this.g.a).floatValue();
    }

    public void setRadius(float f2) {
        synchronized (x.a) {
            this.g.a(Float.valueOf(f2));
            c();
        }
    }

    public boolean arePointsShown() {
        return ((Boolean) this.h.a).booleanValue();
    }

    public void setPointsShown(boolean z) {
        synchronized (x.a) {
            this.h.a(Boolean.valueOf(z));
            c();
        }
    }

    /* access modifiers changed from: 0000 */
    public Drawable b() {
        return (Drawable) this.i.a;
    }

    /* access modifiers changed from: 0000 */
    public final void c() {
        SeriesStyle seriesStyle = this.j;
        if (seriesStyle != null) {
            seriesStyle.d();
        }
    }
}
