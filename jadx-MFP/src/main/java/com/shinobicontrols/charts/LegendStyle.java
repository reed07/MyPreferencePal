package com.shinobicontrols.charts;

import android.graphics.Typeface;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.shinobicontrols.charts.Legend.SymbolAlignment;

public final class LegendStyle {
    final dj<Integer> a = new dj<>(Integer.valueOf(0));
    final dj<Integer> b = new dj<>(Integer.valueOf(0));
    final dj<Float> c = new dj<>(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
    final dj<Float> d = new dj<>(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
    final dj<Typeface> e = new dj<>(null);
    final dj<Integer> f = new dj<>(Integer.valueOf(-16777216));
    final dj<Float> g = new dj<>(Float.valueOf(10.0f));
    final dj<Float> h = new dj<>(Float.valueOf(4.0f));
    final dj<Float> i = new dj<>(Float.valueOf(4.0f));
    final dj<Boolean> j = new dj<>(Boolean.valueOf(true));
    final dj<SymbolAlignment> k = new dj<>(SymbolAlignment.LEFT);
    final dj<Float> l = new dj<>(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
    final dj<Float> m = new dj<>(Float.valueOf(32.0f));
    final dj<Integer> n = new dj<>(Integer.valueOf(0));
    final dj<Typeface> o = new dj<>(null);
    final dj<Integer> p = new dj<>(Integer.valueOf(-16777216));
    final dj<Float> q = new dj<>(Float.valueOf(12.0f));
    final dj<Float> r = new dj<>(Float.valueOf(4.0f));
    final bf s = new bf();

    /* access modifiers changed from: 0000 */
    public void a(LegendStyle legendStyle) {
        if (legendStyle != null) {
            this.a.b(Integer.valueOf(legendStyle.getBackgroundColor()));
            this.b.b(Integer.valueOf(legendStyle.getBorderColor()));
            this.c.b(Float.valueOf(legendStyle.getBorderWidth()));
            this.d.b(Float.valueOf(legendStyle.getCornerRadius()));
            this.e.b(legendStyle.getTypeface());
            this.f.b(Integer.valueOf(legendStyle.getTextColor()));
            this.g.b(Float.valueOf(legendStyle.getTextSize()));
            this.h.b(Float.valueOf(legendStyle.getSymbolLabelGap()));
            this.i.b(Float.valueOf(legendStyle.getPadding()));
            this.j.b(Boolean.valueOf(legendStyle.areSymbolsShown()));
            this.k.b(legendStyle.getSymbolAlignment());
            this.l.b(Float.valueOf(legendStyle.getSymbolCornerRadius()));
            this.m.b(Float.valueOf(legendStyle.getSymbolWidth()));
            this.n.b(Integer.valueOf(legendStyle.getTextAlignment()));
            this.o.b(legendStyle.getTitleTypeface());
            this.p.b(Integer.valueOf(legendStyle.getTitleTextColor()));
            this.q.b(Float.valueOf(legendStyle.getTitleTextSize()));
            this.r.b(Float.valueOf(legendStyle.getRowVerticalMargin()));
        }
    }

    public float getTitlePadding() {
        return this.s.getPadding();
    }

    public void setTitlePadding(float f2) {
        this.s.setPadding(f2);
    }

    public float getTitleMargin() {
        return this.s.getMargin();
    }

    public void setTitleMargin(float f2) {
        this.s.setMargin(f2);
    }

    public int getBackgroundColor() {
        return ((Integer) this.a.a).intValue();
    }

    public int getBorderColor() {
        return ((Integer) this.b.a).intValue();
    }

    public float getBorderWidth() {
        return ((Float) this.c.a).floatValue();
    }

    public float getCornerRadius() {
        return ((Float) this.d.a).floatValue();
    }

    public Typeface getTypeface() {
        return (Typeface) this.e.a;
    }

    public int getTextColor() {
        return ((Integer) this.f.a).intValue();
    }

    public float getTextSize() {
        return ((Float) this.g.a).floatValue();
    }

    public float getSymbolLabelGap() {
        return ((Float) this.h.a).floatValue();
    }

    public float getPadding() {
        return ((Float) this.i.a).floatValue();
    }

    public SymbolAlignment getSymbolAlignment() {
        return (SymbolAlignment) this.k.a;
    }

    public float getSymbolCornerRadius() {
        return ((Float) this.l.a).floatValue();
    }

    public float getSymbolWidth() {
        return ((Float) this.m.a).floatValue();
    }

    public int getTextAlignment() {
        return ((Integer) this.n.a).intValue();
    }

    public Typeface getTitleTypeface() {
        return this.s.getTypeface();
    }

    public int getTitleTextColor() {
        return this.s.getTextColor();
    }

    public float getTitleTextSize() {
        return this.s.getTextSize();
    }

    public float getRowVerticalMargin() {
        return ((Float) this.r.a).floatValue();
    }

    public boolean areSymbolsShown() {
        return ((Boolean) this.j.a).booleanValue();
    }

    public void setBackgroundColor(int i2) {
        this.a.a(Integer.valueOf(i2));
    }

    public void setBorderColor(int i2) {
        this.b.a(Integer.valueOf(i2));
    }

    public void setBorderWidth(float f2) {
        this.c.a(Float.valueOf(f2));
    }

    public void setCornerRadius(float f2) {
        this.d.a(Float.valueOf(f2));
    }

    public void setTypeface(Typeface typeface) {
        this.e.a(typeface);
    }

    public void setTextColor(int i2) {
        this.f.a(Integer.valueOf(i2));
    }

    public void setTextSize(float f2) {
        this.g.a(Float.valueOf(f2));
    }

    public void setSymbolLabelGap(float f2) {
        this.h.a(Float.valueOf(f2));
    }

    public void setPadding(float f2) {
        this.i.a(Float.valueOf(f2));
    }

    public void setSymbolsShown(boolean z) {
        this.j.a(Boolean.valueOf(z));
    }

    public void setSymbolAlignment(SymbolAlignment symbolAlignment) {
        this.k.a(symbolAlignment);
    }

    public void setSymbolCornerRadius(float f2) {
        this.l.a(Float.valueOf(f2));
    }

    public void setSymbolWidth(float f2) {
        this.m.a(Float.valueOf(f2));
    }

    public void setTextAlignment(int i2) {
        this.n.a(Integer.valueOf(i2));
    }

    public void setTitleTypeface(Typeface typeface) {
        this.s.d.a(typeface);
    }

    public void setTitleTextColor(int i2) {
        this.s.h.a(Integer.valueOf(i2));
    }

    public void setTitleTextSize(float f2) {
        this.s.e.a(Float.valueOf(f2));
    }

    public void setRowVerticalMargin(float f2) {
        this.r.a(Float.valueOf(f2));
    }

    /* access modifiers changed from: 0000 */
    public bf a() {
        return this.s;
    }
}
