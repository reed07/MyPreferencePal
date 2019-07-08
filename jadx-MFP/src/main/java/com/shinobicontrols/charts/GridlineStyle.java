package com.shinobicontrols.charts;

public class GridlineStyle {
    final dj<Boolean> a = new dj<>(Boolean.valueOf(false));
    final dj<Boolean> b = new dj<>(Boolean.valueOf(false));
    final dj<Integer> c = new dj<>(Integer.valueOf(-12303292));
    final dj<float[]> d = new dj<>(new float[]{1.0f, 1.0f});
    private final dj<Float> e = new dj<>(Float.valueOf(1.0f));

    /* access modifiers changed from: 0000 */
    public void a(GridlineStyle gridlineStyle) {
        if (gridlineStyle != null) {
            this.a.b(Boolean.valueOf(gridlineStyle.areGridlinesShown()));
            this.b.b(Boolean.valueOf(gridlineStyle.areGridlinesDashed()));
            this.c.b(Integer.valueOf(gridlineStyle.getLineColor()));
            this.e.b(Float.valueOf(gridlineStyle.getLineWidth()));
            this.d.b(gridlineStyle.getDashStyle());
        }
    }

    public boolean areGridlinesShown() {
        return ((Boolean) this.a.a).booleanValue();
    }

    public void setGridlinesShown(boolean z) {
        this.a.a(Boolean.valueOf(z));
    }

    public boolean areGridlinesDashed() {
        return ((Boolean) this.b.a).booleanValue();
    }

    public void setGridlinesDashed(boolean z) {
        this.b.a(Boolean.valueOf(z));
    }

    public int getLineColor() {
        return ((Integer) this.c.a).intValue();
    }

    public void setLineColor(int i) {
        this.c.a(Integer.valueOf(i));
    }

    public float getLineWidth() {
        return ((Float) this.e.a).floatValue();
    }

    public void setLineWidth(float f) {
        this.e.a(Float.valueOf(f));
    }

    public float[] getDashStyle() {
        return (float[]) this.d.a;
    }

    public void setDashStyle(float[] fArr) {
        this.d.a(fArr);
    }
}
