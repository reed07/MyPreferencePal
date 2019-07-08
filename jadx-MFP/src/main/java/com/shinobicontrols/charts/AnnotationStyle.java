package com.shinobicontrols.charts;

import android.graphics.Typeface;

public class AnnotationStyle {
    final dj<Integer> a = new dj<>(Integer.valueOf(-16777216));
    final dj<Float> b = new dj<>(Float.valueOf(12.0f));
    final dj<Typeface> c = new dj<>(null);
    final dj<Integer> d = new dj<>(Integer.valueOf(0));

    public int getTextColor() {
        return ((Integer) this.a.a).intValue();
    }

    public void setTextColor(int i) {
        this.a.a(Integer.valueOf(i));
    }

    public float getTextSize() {
        return ((Float) this.b.a).floatValue();
    }

    public void setTextSize(float f) {
        this.b.a(Float.valueOf(f));
    }

    public Typeface getTypeface() {
        return (Typeface) this.c.a;
    }

    public void setTypeface(Typeface typeface) {
        this.c.a(typeface);
    }

    public int getBackgroundColor() {
        return ((Integer) this.d.a).intValue();
    }

    public void setBackgroundColor(int i) {
        this.d.a(Integer.valueOf(i));
    }

    /* access modifiers changed from: 0000 */
    public void a(AnnotationStyle annotationStyle) {
        if (annotationStyle != null) {
            this.d.b(Integer.valueOf(annotationStyle.getBackgroundColor()));
            this.a.b(Integer.valueOf(annotationStyle.getTextColor()));
            this.b.b(Float.valueOf(annotationStyle.getTextSize()));
            this.c.b(annotationStyle.getTypeface());
        }
    }
}
