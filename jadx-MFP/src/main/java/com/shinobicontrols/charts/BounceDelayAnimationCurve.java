package com.shinobicontrols.charts;

public class BounceDelayAnimationCurve extends AnimationCurve {
    public float valueAtTime(float f) {
        return a(f > 0.5f ? 1.0f : f * 2.0f);
    }
}
