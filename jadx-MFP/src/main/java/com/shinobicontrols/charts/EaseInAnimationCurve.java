package com.shinobicontrols.charts;

public class EaseInAnimationCurve extends AnimationCurve {
    public float valueAtTime(float f) {
        return (float) Math.pow((double) f, 4.0d);
    }
}
