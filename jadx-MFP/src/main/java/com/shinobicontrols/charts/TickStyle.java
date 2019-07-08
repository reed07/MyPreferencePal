package com.shinobicontrols.charts;

import android.graphics.Typeface;
import com.shinobicontrols.charts.TickMark.Orientation;

public final class TickStyle {
    final dj<Integer> a = new dj<>(Integer.valueOf(-16777216));
    final dj<Typeface> b = new dj<>(Typeface.DEFAULT);
    final dj<Float> c = new dj<>(Float.valueOf(10.0f));
    final dj<Integer> d = new dj<>(Integer.valueOf(-1));
    final dj<Integer> e = new dj<>(Integer.valueOf(-16777216));
    final dj<Float> f = new dj<>(Float.valueOf(1.0f));
    final dj<Float> g = new dj<>(Float.valueOf(1.0f));
    final dj<Boolean> h = new dj<>(Boolean.valueOf(true));
    final dj<Boolean> i = new dj<>(Boolean.valueOf(true));
    final dj<Boolean> j = new dj<>(Boolean.valueOf(false));
    final dj<Float> k = new dj<>(Float.valueOf(1.0f));
    final dj<Orientation> l = new dj<>(Orientation.HORIZONTAL);

    /* access modifiers changed from: 0000 */
    public void a(TickStyle tickStyle) {
        if (tickStyle != null) {
            this.a.b(tickStyle.a.a);
            this.b.b(tickStyle.b.a);
            this.c.b(tickStyle.c.a);
            this.d.b(tickStyle.d.a);
            this.e.b(tickStyle.e.a);
            this.f.b(tickStyle.f.a);
            this.g.b(tickStyle.g.a);
            this.h.b(tickStyle.h.a);
            this.i.b(tickStyle.i.a);
            this.j.b(tickStyle.j.a);
            this.k.b(tickStyle.k.a);
            this.l.b(tickStyle.l.a);
        }
    }

    public int getLabelColor() {
        return ((Integer) this.a.a).intValue();
    }

    public Typeface getLabelTypeface() {
        return (Typeface) this.b.a;
    }

    public float getLabelTextSize() {
        return ((Float) this.c.a).floatValue();
    }

    public int getLabelTextShadowColor() {
        return ((Integer) this.d.a).intValue();
    }

    public int getLineColor() {
        return ((Integer) this.e.a).intValue();
    }

    public float getLineLength() {
        return ((Float) this.f.a).floatValue();
    }

    public float getLineWidth() {
        return ((Float) this.g.a).floatValue();
    }

    public float getTickGap() {
        return ((Float) this.k.a).floatValue();
    }

    public Orientation getLabelOrientation() {
        return (Orientation) this.l.a;
    }

    public boolean areLabelsShown() {
        return ((Boolean) this.h.a).booleanValue();
    }

    public boolean areMajorTicksShown() {
        return ((Boolean) this.i.a).booleanValue();
    }

    public boolean areMinorTicksShown() {
        return ((Boolean) this.j.a).booleanValue();
    }

    public void setLabelColor(int i2) {
        this.a.a(Integer.valueOf(i2));
    }

    public void setLabelTypeface(Typeface typeface) {
        this.b.a(typeface);
    }

    public void setLabelTextSize(float f2) {
        this.c.a(Float.valueOf(f2));
    }

    public void setLabelTextShadowColor(int i2) {
        this.d.a(Integer.valueOf(i2));
    }

    public void setLineColor(int i2) {
        this.e.a(Integer.valueOf(i2));
    }

    public void setLineLength(float f2) {
        this.f.a(Float.valueOf(f2));
    }

    public void setLineWidth(float f2) {
        this.g.a(Float.valueOf(f2));
    }

    public void setLabelsShown(boolean z) {
        this.h.a(Boolean.valueOf(z));
    }

    public void setMajorTicksShown(boolean z) {
        this.i.a(Boolean.valueOf(z));
    }

    public void setMinorTicksShown(boolean z) {
        this.j.a(Boolean.valueOf(z));
    }

    public void setTickGap(float f2) {
        this.k.a(Float.valueOf(f2));
    }

    public void setLabelOrientation(Orientation orientation) {
        this.l.a(orientation);
    }
}
