package com.shinobicontrols.charts;

import android.graphics.Typeface;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.shinobicontrols.charts.PieDonutSeries.RadialEffect;

abstract class PieDonutSeriesStyle extends SeriesStyle {
    final dj<Boolean> a = new dj<>(Boolean.valueOf(true));
    final dj<Boolean> b = new dj<>(Boolean.valueOf(true));
    final dj<Boolean> c = new dj<>(Boolean.valueOf(true));
    final dj<RadialEffect> d = new dj<>(RadialEffect.DEFAULT);
    final dj<Float> e = new dj<>(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
    final dj<Integer[]> f = new dj<>(new Integer[]{Integer.valueOf(-16777216), Integer.valueOf(-1)});
    final dj<Float> g = new dj<>(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
    final dj<Integer[]> h = new dj<>(new Integer[]{Integer.valueOf(-16777216), Integer.valueOf(-1)});
    final dj<Float> i = new dj<>(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
    final dj<Typeface> j = new dj<>(null);
    final dj<Float> k = new dj<>(Float.valueOf(10.0f));
    final dj<Integer> l = new dj<>(Integer.valueOf(-16777216));
    final dj<Integer> m = new dj<>(Integer.valueOf(0));

    PieDonutSeriesStyle() {
    }

    /* access modifiers changed from: 0000 */
    public void a(SeriesStyle seriesStyle) {
        synchronized (x.a) {
            super.a(seriesStyle);
            PieDonutSeriesStyle pieDonutSeriesStyle = (PieDonutSeriesStyle) seriesStyle;
            this.a.b(Boolean.valueOf(pieDonutSeriesStyle.isCrustShown()));
            this.b.b(Boolean.valueOf(pieDonutSeriesStyle.isFlavorShown()));
            this.c.b(Boolean.valueOf(pieDonutSeriesStyle.areLabelsShown()));
            this.d.b(pieDonutSeriesStyle.getRadialEffect());
            this.e.b(Float.valueOf(pieDonutSeriesStyle.getInitialRotation()));
            this.f.b(pieDonutSeriesStyle.f.a);
            this.g.b(Float.valueOf(pieDonutSeriesStyle.getCrustThickness()));
            this.h.b(pieDonutSeriesStyle.h.a);
            this.i.b(Float.valueOf(pieDonutSeriesStyle.getProtrusion()));
            this.j.b(pieDonutSeriesStyle.getLabelTypeface());
            this.k.b(Float.valueOf(pieDonutSeriesStyle.getLabelTextSize()));
            this.l.b(Integer.valueOf(pieDonutSeriesStyle.getLabelTextColor()));
            this.m.b(Integer.valueOf(pieDonutSeriesStyle.getLabelBackgroundColor()));
        }
    }

    public boolean isCrustShown() {
        return ((Boolean) this.a.a).booleanValue();
    }

    public void setCrustShown(boolean z) {
        synchronized (x.a) {
            this.a.a(Boolean.valueOf(z));
            d();
        }
    }

    public boolean isFlavorShown() {
        return ((Boolean) this.b.a).booleanValue();
    }

    public void setFlavorShown(boolean z) {
        synchronized (x.a) {
            this.b.a(Boolean.valueOf(z));
            d();
        }
    }

    public boolean areLabelsShown() {
        return ((Boolean) this.c.a).booleanValue();
    }

    public void setLabelsShown(boolean z) {
        synchronized (x.a) {
            this.c.a(Boolean.valueOf(z));
            d();
        }
    }

    public RadialEffect getRadialEffect() {
        return (RadialEffect) this.d.a;
    }

    public void setRadialEffect(RadialEffect radialEffect) {
        synchronized (x.a) {
            this.d.a(radialEffect);
            d();
        }
    }

    public float getInitialRotation() {
        return ((Float) this.e.a).floatValue();
    }

    public void setInitialRotation(float f2) {
        synchronized (x.a) {
            this.e.a(Float.valueOf(f2));
            d();
        }
    }

    public int crustColorAtIndex(int i2) {
        Integer[] numArr = (Integer[]) this.f.a;
        return numArr[i2 % numArr.length].intValue();
    }

    /* access modifiers changed from: 0000 */
    public Integer[] a() {
        return (Integer[]) this.f.a;
    }

    public void setCrustColors(int[] iArr) {
        synchronized (x.a) {
            if (iArr != null) {
                Integer[] numArr = new Integer[iArr.length];
                for (int i2 = 0; i2 < iArr.length; i2++) {
                    numArr[i2] = Integer.valueOf(iArr[i2]);
                }
                this.f.a(numArr);
                d();
            }
        }
    }

    public float getCrustThickness() {
        return ((Float) this.g.a).floatValue();
    }

    public void setCrustThickness(float f2) {
        synchronized (x.a) {
            if (f2 < 1.0f) {
                cx.b("Ignoring setting of crustThickness: cannot have a crustThickness of less than 1");
            } else {
                this.g.a(Float.valueOf(f2));
                d();
            }
        }
    }

    public int flavorColorAtIndex(int i2) {
        Integer[] numArr = (Integer[]) this.h.a;
        return numArr[i2 % numArr.length].intValue();
    }

    /* access modifiers changed from: 0000 */
    public Integer[] b() {
        return (Integer[]) this.h.a;
    }

    public void setFlavorColors(int[] iArr) {
        synchronized (x.a) {
            if (iArr != null) {
                Integer[] numArr = new Integer[iArr.length];
                for (int i2 = 0; i2 < iArr.length; i2++) {
                    numArr[i2] = Integer.valueOf(iArr[i2]);
                }
                this.h.a(numArr);
                d();
            }
        }
    }

    public float getProtrusion() {
        return ((Float) this.i.a).floatValue();
    }

    public void setProtrusion(float f2) {
        synchronized (x.a) {
            if (f2 >= BitmapDescriptorFactory.HUE_RED) {
                try {
                    this.i.a(Float.valueOf(f2));
                    d();
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                throw new IllegalArgumentException("Protrusion must be positive");
            }
        }
    }

    public Typeface getLabelTypeface() {
        return (Typeface) this.j.a;
    }

    public void setLabelTypeface(Typeface typeface) {
        this.j.a(typeface);
        d();
    }

    public int getLabelTextColor() {
        return ((Integer) this.l.a).intValue();
    }

    public void setLabelTextColor(int i2) {
        this.l.a(Integer.valueOf(i2));
        d();
    }

    public int getLabelBackgroundColor() {
        return ((Integer) this.m.a).intValue();
    }

    public void setLabelBackgroundColor(int i2) {
        this.m.a(Integer.valueOf(i2));
        d();
    }

    public float getLabelTextSize() {
        return ((Float) this.k.a).floatValue();
    }

    public void setLabelTextSize(float f2) {
        this.k.a(Float.valueOf(f2));
        d();
    }
}
