package com.shinobicontrols.charts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

class bu extends Animation {
    private AnimationCurve a = new bp();
    private AnimationCurve b = new bp();
    private AnimationCurve c = new bp();

    /* access modifiers changed from: 0000 */
    public void a(AnimationCurve animationCurve) {
        if (animationCurve == null) {
            this.a = new bp();
        } else {
            this.a = animationCurve;
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(AnimationCurve animationCurve) {
        if (animationCurve == null) {
            this.b = new bp();
        } else {
            this.b = animationCurve;
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(AnimationCurve animationCurve) {
        if (animationCurve == null) {
            this.c = new bp();
        } else {
            this.c = animationCurve;
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

    /* access modifiers changed from: 0000 */
    public float e() {
        if (a() < BitmapDescriptorFactory.HUE_RED) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        if (a() > getDuration()) {
            return 1.0f;
        }
        return 1.0f - this.b.valueAtTime(1.0f - b());
    }

    /* access modifiers changed from: 0000 */
    public double f() {
        if (a() < BitmapDescriptorFactory.HUE_RED) {
            return 0.0d;
        }
        if (a() > getDuration()) {
            return 1.0d;
        }
        return (double) this.c.valueAtTime(b());
    }
}
