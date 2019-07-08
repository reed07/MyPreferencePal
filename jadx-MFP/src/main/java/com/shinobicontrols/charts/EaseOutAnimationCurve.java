package com.shinobicontrols.charts;

public class EaseOutAnimationCurve extends AnimationCurve {
    public float valueAtTime(float f) {
        return 1.0f - ((float) Math.pow((double) (1.0f - f), 5.0d));
    }
}
