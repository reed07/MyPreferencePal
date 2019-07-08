package com.shinobicontrols.charts;

public final class GridStripeStyle {
    final dj<Integer> a = new dj<>(Integer.valueOf(-3355444));
    final dj<Integer> b = new dj<>(Integer.valueOf(-7829368));
    final dj<Boolean> c = new dj<>(Boolean.valueOf(false));

    /* access modifiers changed from: 0000 */
    public void a(GridStripeStyle gridStripeStyle) {
        if (gridStripeStyle != null) {
            this.a.b(Integer.valueOf(gridStripeStyle.getStripeColor()));
            this.b.b(Integer.valueOf(gridStripeStyle.getAlternateStripeColor()));
            this.c.b(Boolean.valueOf(gridStripeStyle.areGridStripesShown()));
        }
    }

    public int getStripeColor() {
        return ((Integer) this.a.a).intValue();
    }

    public void setStripeColor(int i) {
        this.a.a(Integer.valueOf(i));
    }

    public int getAlternateStripeColor() {
        return ((Integer) this.b.a).intValue();
    }

    public void setAlternateStripeColor(int i) {
        this.b.a(Integer.valueOf(i));
    }

    public boolean areGridStripesShown() {
        return ((Boolean) this.c.a).booleanValue();
    }

    public void setGridStripesShown(boolean z) {
        this.c.a(Boolean.valueOf(z));
    }
}
