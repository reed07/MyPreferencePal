package com.shinobicontrols.charts;

import java.util.Locale;

class bj {
    final double a;
    final double b;

    bj(double d, double d2) {
        this.a = d;
        this.b = d2;
    }

    /* access modifiers changed from: 0000 */
    public double a(double d) {
        return (this.a * d) + this.b;
    }

    public String toString() {
        return String.format(Locale.US, "f(x) = %fx + %f", new Object[]{Double.valueOf(this.a), Double.valueOf(this.b)});
    }
}
