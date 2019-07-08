package com.uacf.floatingbuttonmenu.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import com.uacf.floatingbuttonmenu.FloatingButtonMenu;

public class RotateTranslateAlphaFloatingButtonAnimationHandler extends FloatingButtonAnimationHandlerBase {

    public static class Builder extends com.uacf.floatingbuttonmenu.animation.FloatingButtonAnimationHandlerBase.Builder<Builder, TranslateAlphaFloatingButtonAnimationHandler> {
        /* access modifiers changed from: protected */
        public FloatingButtonAnimationHandlerBase makeAndSetSpecialProperties() {
            return new RotateTranslateAlphaFloatingButtonAnimationHandler(this.mFloatingButtonMenu);
        }
    }

    protected RotateTranslateAlphaFloatingButtonAnimationHandler(FloatingButtonMenu floatingButtonMenu) {
        super(floatingButtonMenu);
    }

    /* access modifiers changed from: protected */
    public Animation getAnimation(View view, int i, float f, boolean z, int i2) {
        AnimationSet animationSet = new AnimationSet(true);
        Animation rotateAnimation = getRotateAnimation();
        Animation translateAnimation = getTranslateAnimation(view, f, z, i2);
        Animation alphaAnimation = getAlphaAnimation(z);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(this.duration);
        animationSet.setStartOffset(getChildStartOffset(i));
        animationSet.setInterpolator(z ? this.openInterpolator : this.closeInterpolator);
        return animationSet;
    }
}
