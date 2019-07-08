package com.shinobicontrols.charts;

public abstract class AnimationCurve {
    static final float a = ((float) Math.sqrt(0.75d));
    static final float b = ((float) Math.atan((double) (a / 0.5f)));

    public abstract float valueAtTime(float f);

    protected AnimationCurve() {
    }

    /* access modifiers changed from: 0000 */
    public final float a(float f) {
        return 1.0f - (((1.0f / a) * ((float) Math.exp((double) (-4.8368f * f)))) * ((float) Math.sin((double) (((a * 9.6736f) * f) + b))));
    }
}
