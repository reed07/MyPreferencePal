package com.shinobicontrols.charts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class DelayBounceAnimationCurve extends AnimationCurve {
    public float valueAtTime(float f) {
        return a(f < 0.5f ? BitmapDescriptorFactory.HUE_RED : (f - 0.5f) * 2.0f);
    }
}
