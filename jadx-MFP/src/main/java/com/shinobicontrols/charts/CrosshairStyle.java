package com.shinobicontrols.charts;

import android.graphics.Typeface;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class CrosshairStyle {
    final dj<Float> a = new dj<>(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
    final dj<Integer> b = new dj<>(Integer.valueOf(-16777216));
    final dj<Float> c = new dj<>(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
    final dj<Typeface> d = new dj<>(null);
    final dj<Float> e = new dj<>(Float.valueOf(12.0f));
    final dj<Integer> f = new dj<>(Integer.valueOf(-16777216));
    final dj<Integer> g = new dj<>(Integer.valueOf(0));
    final dj<Integer> h = new dj<>(Integer.valueOf(0));
    final dj<Float> i = new dj<>(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
    final dj<Float> j = new dj<>(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
    final dj<Integer> k = new dj<>(Integer.valueOf(0));

    public float getLineWidth() {
        return ((Float) this.a.a).floatValue();
    }

    public void setLineWidth(float f2) {
        this.a.a(Float.valueOf(f2));
    }

    public int getLineColor() {
        return ((Integer) this.b.a).intValue();
    }

    public void setLineColor(int i2) {
        this.b.a(Integer.valueOf(i2));
    }

    public float getTooltipPadding() {
        return ((Float) this.c.a).floatValue();
    }

    public void setTooltipPadding(float f2) {
        this.c.a(Float.valueOf(f2));
    }

    public Typeface getTooltipTypeface() {
        return (Typeface) this.d.a;
    }

    public void setTooltipTypeface(Typeface typeface) {
        this.d.a(typeface);
    }

    public float getTooltipTextSize() {
        return ((Float) this.e.a).floatValue();
    }

    public void setTooltipTextSize(float f2) {
        this.e.a(Float.valueOf(f2));
    }

    public int getTooltipTextColor() {
        return ((Integer) this.f.a).intValue();
    }

    public void setTooltipTextColor(int i2) {
        this.f.a(Integer.valueOf(i2));
    }

    public int getTooltipLabelBackgroundColor() {
        return ((Integer) this.g.a).intValue();
    }

    public void setTooltipLabelBackgroundColor(int i2) {
        this.g.a(Integer.valueOf(i2));
    }

    public int getTooltipBackgroundColor() {
        return ((Integer) this.h.a).intValue();
    }

    public void setTooltipBackgroundColor(int i2) {
        this.h.a(Integer.valueOf(i2));
    }

    public float getTooltipCornerRadius() {
        return ((Float) this.i.a).floatValue();
    }

    public void setTooltipCornerRadius(float f2) {
        this.i.a(Float.valueOf(f2));
    }

    public float getTooltipBorderWidth() {
        return ((Float) this.j.a).floatValue();
    }

    public void setTooltipBorderWidth(float f2) {
        this.j.a(Float.valueOf(f2));
    }

    public int getTooltipBorderColor() {
        return ((Integer) this.k.a).intValue();
    }

    public void setTooltipBorderColor(int i2) {
        this.k.a(Integer.valueOf(i2));
    }

    /* access modifiers changed from: 0000 */
    public void a(CrosshairStyle crosshairStyle) {
        if (crosshairStyle != null) {
            this.a.b(crosshairStyle.a.a);
            this.b.b(crosshairStyle.b.a);
            this.c.b(crosshairStyle.c.a);
            this.d.b(crosshairStyle.d.a);
            this.e.b(crosshairStyle.e.a);
            this.f.b(crosshairStyle.f.a);
            this.g.b(crosshairStyle.g.a);
            this.h.b(crosshairStyle.h.a);
            this.i.b(crosshairStyle.i.a);
            this.j.b(crosshairStyle.j.a);
            this.k.b(crosshairStyle.k.a);
        }
    }
}
