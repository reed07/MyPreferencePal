package com.shinobicontrols.charts;

public class EaseInOutAnimationCurve extends AnimationCurve {
    public float valueAtTime(float f) {
        if (f < 0.5f) {
            float f2 = f * 2.0f;
            return f2 * f2 * f2 * 0.5f;
        }
        float f3 = 1.0f - ((f - 0.5f) * 2.0f);
        return ((1.0f - ((f3 * f3) * f3)) * 0.5f) + 0.5f;
    }
}
