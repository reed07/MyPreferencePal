package com.shinobicontrols.charts;

class AnimationManager {
    private final long nativeHandle = 0;

    private native void setCurrentState(float f, float f2, float f3, boolean z, boolean z2, boolean z3, double d, double d2, double d3);

    static {
        System.loadLibrary("shinobicharts-android");
    }

    AnimationManager() {
    }

    /* access modifiers changed from: 0000 */
    public void update(Series<?> series) {
        Series<?> series2 = series;
        if (series2.u != null) {
            SeriesAnimation seriesAnimation = series2.u;
            setCurrentState(seriesAnimation.d(), seriesAnimation.e(), seriesAnimation.f(), true, true, true, series.h(), series.b(), (double) seriesAnimation.getDuration());
            return;
        }
        setCurrentState(1.0f, 1.0f, 1.0f, false, true, false, 0.0d, 0.0d, 0.0d);
    }
}
