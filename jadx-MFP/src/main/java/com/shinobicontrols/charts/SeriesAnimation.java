package com.shinobicontrols.charts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class SeriesAnimation extends Animation {
    Float a = Float.valueOf(0.5f);
    Float b = Float.valueOf(0.5f);
    private AnimationCurve c = new FlatAnimationCurve();
    private AnimationCurve d = new FlatAnimationCurve();
    private AnimationCurve e = new FlatAnimationCurve();
    private AnimationCurve f = new FlatAnimationCurve();
    private AnimationCurve g = new FlatAnimationCurve();

    public /* bridge */ /* synthetic */ float getDuration() {
        return super.getDuration();
    }

    public /* bridge */ /* synthetic */ void setDuration(float f2) {
        super.setDuration(f2);
    }

    public SeriesAnimation() {
        setDuration(2.4f);
    }

    public static SeriesAnimation createGrowAnimation() {
        SeriesAnimation seriesAnimation = new SeriesAnimation();
        seriesAnimation.c = new BounceAnimationCurve();
        seriesAnimation.d = new BounceAnimationCurve();
        return seriesAnimation;
    }

    public static SeriesAnimation createGrowHorizontalAnimation() {
        SeriesAnimation seriesAnimation = new SeriesAnimation();
        seriesAnimation.c = new BounceAnimationCurve();
        seriesAnimation.a = null;
        return seriesAnimation;
    }

    public static SeriesAnimation createGrowVerticalAnimation() {
        SeriesAnimation seriesAnimation = new SeriesAnimation();
        seriesAnimation.d = new BounceAnimationCurve();
        seriesAnimation.b = null;
        return seriesAnimation;
    }

    public static SeriesAnimation createFadeAnimation() {
        SeriesAnimation seriesAnimation = new SeriesAnimation();
        seriesAnimation.g = new LinearAnimationCurve();
        return seriesAnimation;
    }

    public static SeriesAnimation createTelevisionAnimation() {
        SeriesAnimation seriesAnimation = new SeriesAnimation();
        seriesAnimation.c = new BounceDelayAnimationCurve();
        seriesAnimation.d = new DelayBounceAnimationCurve();
        return seriesAnimation;
    }

    public Float getXOrigin() {
        return this.a;
    }

    public void setXOrigin(Float f2) {
        this.a = f2;
    }

    public Float getYOrigin() {
        return this.b;
    }

    public void setYOrigin(Float f2) {
        this.b = f2;
    }

    public AnimationCurve getXScaleCurve() {
        return this.c;
    }

    public void setXScaleCurve(AnimationCurve animationCurve) {
        if (animationCurve != null) {
            this.c = animationCurve;
            return;
        }
        throw new IllegalArgumentException("Animation curves may not be null");
    }

    public AnimationCurve getYScaleCurve() {
        return this.d;
    }

    public void setYScaleCurve(AnimationCurve animationCurve) {
        if (animationCurve != null) {
            this.d = animationCurve;
            return;
        }
        throw new IllegalArgumentException("Animation curves may not be null");
    }

    public AnimationCurve getAlphaCurve() {
        return this.g;
    }

    public void setAlphaCurve(AnimationCurve animationCurve) {
        if (animationCurve != null) {
            this.g = animationCurve;
            return;
        }
        throw new IllegalArgumentException("Animation curves may not be null");
    }

    /* access modifiers changed from: 0000 */
    public float d() {
        if (a() <= BitmapDescriptorFactory.HUE_RED) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        if (a() >= getDuration()) {
            return 1.0f;
        }
        return this.c.valueAtTime(b());
    }

    /* access modifiers changed from: 0000 */
    public float e() {
        if (a() <= BitmapDescriptorFactory.HUE_RED) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        if (a() >= getDuration()) {
            return 1.0f;
        }
        return this.d.valueAtTime(b());
    }

    /* access modifiers changed from: 0000 */
    public float f() {
        if (a() <= BitmapDescriptorFactory.HUE_RED) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        if (a() >= getDuration()) {
            return 1.0f;
        }
        return this.g.valueAtTime(b());
    }
}
