package com.shinobicontrols.charts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

class g extends Animation {
    private AnimationCurve a = new bp();

    /* access modifiers changed from: 0000 */
    public void a(AnimationCurve animationCurve) {
        if (animationCurve == null) {
            this.a = new bp();
        } else {
            this.a = animationCurve;
        }
    }

    /* access modifiers changed from: 0000 */
    public float d() {
        if (a() < BitmapDescriptorFactory.HUE_RED) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        if (a() > getDuration()) {
            return 1.0f;
        }
        return this.a.valueAtTime(b());
    }
}
