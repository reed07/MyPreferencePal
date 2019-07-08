package com.shinobicontrols.charts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class DelayBounceWithMinimumAnimationCurve extends AnimationCurve {
    public float valueAtTime(float f) {
        float a = a(f < 0.5f ? BitmapDescriptorFactory.HUE_RED : (f - 0.5f) * 2.0f);
        if (a < 0.05f) {
            return 0.05f;
        }
        return a;
    }
}
