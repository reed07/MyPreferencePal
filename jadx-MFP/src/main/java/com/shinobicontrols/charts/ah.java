package com.shinobicontrols.charts;

import android.graphics.Color;

class ah extends Color {
    static final ah a = new ah(0);
    final float b;
    final float c;
    final float d;
    final float e;

    private static float a(int i) {
        return ((float) i) * 0.003921569f;
    }

    ah(int i) {
        this(Color.red(i), Color.green(i), Color.blue(i), Color.alpha(i));
    }

    ah(int i, int i2, int i3, int i4) {
        this(a(i), a(i2), a(i3), a(i4));
    }

    ah(float f, float f2, float f3, float f4) {
        this.b = f;
        this.c = f2;
        this.d = f3;
        this.e = f4;
    }
}
