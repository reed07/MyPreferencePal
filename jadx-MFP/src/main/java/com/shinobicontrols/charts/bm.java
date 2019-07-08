package com.shinobicontrols.charts;

class bm extends AnimationCurve {
    private static final float c = (-((float) Math.log(0.012000000104308128d)));
    private final float d = a(1.0d);

    bm() {
    }

    private float a(double d2) {
        return (1.0f - ((float) Math.pow(2.718281828459045d, (-d2) * ((double) c)))) / c;
    }

    public float valueAtTime(float f) {
        return a((double) f) / this.d;
    }
}
