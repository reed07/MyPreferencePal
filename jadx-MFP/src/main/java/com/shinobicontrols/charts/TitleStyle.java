package com.shinobicontrols.charts;

import android.graphics.Typeface;
import com.shinobicontrols.charts.Title.Position;

public abstract class TitleStyle {
    final dj<Integer> c = new dj<>(Integer.valueOf(-1));
    final dj<Typeface> d = new dj<>(null);
    final dj<Float> e = new dj<>(Float.valueOf(12.0f));
    final dj<Float> f = new dj<>(Float.valueOf(12.0f));
    final dj<Position> g = new dj<>(Position.CENTER);
    final dj<Integer> h = new dj<>(Integer.valueOf(-16777216));
    final dj<Float> i = new dj<>(Float.valueOf(6.0f));
    final dj<Float> j = new dj<>(Float.valueOf(10.0f));

    /* access modifiers changed from: 0000 */
    public void a(TitleStyle titleStyle) {
        if (titleStyle != null) {
            this.c.b(Integer.valueOf(titleStyle.getBackgroundColor()));
            this.d.b(titleStyle.getTypeface());
            this.e.b(Float.valueOf(titleStyle.getTextSize()));
            this.f.b(Float.valueOf(titleStyle.a()));
            this.g.b(titleStyle.getPosition());
            this.h.b(Integer.valueOf(titleStyle.getTextColor()));
        }
    }

    public float getPadding() {
        return ((Float) this.i.a).floatValue();
    }

    public void setPadding(float f2) {
        this.i.a(Float.valueOf(f2));
    }

    public float getMargin() {
        return ((Float) this.j.a).floatValue();
    }

    public void setMargin(float f2) {
        this.j.a = Float.valueOf(f2);
    }

    public int getBackgroundColor() {
        return ((Integer) this.c.a).intValue();
    }

    public void setBackgroundColor(int i2) {
        this.c.a(Integer.valueOf(i2));
    }

    public Typeface getTypeface() {
        return (Typeface) this.d.a;
    }

    public void setTypeface(Typeface typeface) {
        this.d.a(typeface);
    }

    public float getTextSize() {
        return ((Float) this.e.a).floatValue();
    }

    public void setTextSize(float f2) {
        this.e.a(Float.valueOf(f2));
    }

    /* access modifiers changed from: 0000 */
    public float a() {
        return ((Float) this.f.a).floatValue();
    }

    public Position getPosition() {
        return (Position) this.g.a;
    }

    public void setPosition(Position position) {
        this.g.a(position);
    }

    public int getTextColor() {
        return ((Integer) this.h.a).intValue();
    }

    public void setTextColor(int i2) {
        this.h.a(Integer.valueOf(i2));
    }
}
