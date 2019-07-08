package com.uacf.floatingbuttonmenu.animation;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.uacf.floatingbuttonmenu.FloatingButtonMenu;
import com.uacf.floatingbuttonmenu.FloatingButtonMenu.OnItemClickListener;
import com.uacf.floatingbuttonmenu.FloatingButtonMenu.OnStateChangeListener;

public abstract class FloatingButtonAnimationHandlerBase {
    protected Interpolator closeInterpolator;
    protected long duration;
    protected Context mContext;
    protected FloatingButtonMenu mFloatingButtonMenu;
    /* access modifiers changed from: private */
    public boolean mIsAnimating;
    protected Interpolator openInterpolator;
    protected long startOffsetBetweenEachChild;

    public static abstract class Builder<T extends Builder, U extends FloatingButtonAnimationHandlerBase> {
        protected Interpolator closeInterpolator = new DecelerateInterpolator(1.5f);
        protected long duration = 500;
        protected FloatingButtonMenu mFloatingButtonMenu;
        protected Interpolator openInterpolator = new OvershootInterpolator(1.5f);
        protected long startOffsetBetweenEachChild = 80;

        /* access modifiers changed from: protected */
        public abstract FloatingButtonAnimationHandlerBase makeAndSetSpecialProperties();

        protected Builder(FloatingButtonMenu floatingButtonMenu) {
            this.mFloatingButtonMenu = floatingButtonMenu;
        }

        public T setStartOffsetBetweenEachChild(long j) {
            this.startOffsetBetweenEachChild = j;
            return this;
        }

        public T setDuration(long j) {
            this.duration = j;
            return this;
        }

        public T setOpenInterpolator(Interpolator interpolator) {
            this.openInterpolator = interpolator;
            return this;
        }

        public T setCloseInterpolator(Interpolator interpolator) {
            this.closeInterpolator = interpolator;
            return this;
        }

        /* access modifiers changed from: protected */
        public void setCommonProperties(FloatingButtonAnimationHandlerBase floatingButtonAnimationHandlerBase) {
            floatingButtonAnimationHandlerBase.setDuration(this.duration);
            floatingButtonAnimationHandlerBase.setStartOffsetBetweenEachChild(this.startOffsetBetweenEachChild);
            floatingButtonAnimationHandlerBase.setOpenInterpolator(this.openInterpolator);
            floatingButtonAnimationHandlerBase.setCloseInterpolator(this.closeInterpolator);
        }

        public U build() {
            U makeAndSetSpecialProperties = makeAndSetSpecialProperties();
            setCommonProperties(makeAndSetSpecialProperties);
            return makeAndSetSpecialProperties;
        }
    }

    private int getTransformedIndex(int i, int i2, boolean z) {
        return z ? i : (i2 - i) - 1;
    }

    /* access modifiers changed from: protected */
    public abstract Animation getAnimation(View view, int i, float f, boolean z, int i2);

    protected FloatingButtonAnimationHandlerBase(FloatingButtonMenu floatingButtonMenu) {
        this.mFloatingButtonMenu = floatingButtonMenu;
        this.mContext = floatingButtonMenu.getContext();
    }

    public void animateMenu(int i) {
        float f;
        View view;
        boolean z;
        int i2;
        Animation animation;
        int i3 = i;
        this.mIsAnimating = true;
        int menuChildCount = this.mFloatingButtonMenu.getMenuChildCount();
        float toDegrees = this.mFloatingButtonMenu.getToDegrees();
        float fromDegrees = this.mFloatingButtonMenu.getFromDegrees();
        final boolean isExpanded = this.mFloatingButtonMenu.isExpanded();
        boolean z2 = i3 >= 0 && !isExpanded;
        int i4 = menuChildCount - 1;
        float f2 = (toDegrees - fromDegrees) / ((float) i4);
        final View floatingButtonView = this.mFloatingButtonMenu.getFloatingButtonView();
        Animation floatingButtonAnimation = getFloatingButtonAnimation(isExpanded);
        floatingButtonView.startAnimation(floatingButtonAnimation);
        floatingButtonAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                floatingButtonView.setRotation(isExpanded ? 135.0f : BitmapDescriptorFactory.HUE_RED);
            }
        });
        OnStateChangeListener onStateChangeListener = this.mFloatingButtonMenu.getOnStateChangeListener();
        if (onStateChangeListener != null) {
            onStateChangeListener.onMenuStateChanged(isExpanded ? 0 : 2);
        }
        handleBackgroundView(isExpanded);
        if (!isExpanded) {
            this.mFloatingButtonMenu.hideItemText();
        } else {
            this.mFloatingButtonMenu.showItemText();
        }
        float f3 = fromDegrees;
        final int i5 = 0;
        while (i5 < menuChildCount) {
            View menuChildAt = this.mFloatingButtonMenu.getMenuChildAt(i5);
            int transformedIndex = getTransformedIndex(i5, menuChildCount, isExpanded);
            boolean z3 = i5 == i3;
            if (z2) {
                z = z3;
                i2 = transformedIndex;
                view = menuChildAt;
                f = f3;
                animation = getOptionAnimation(menuChildAt, f3, z3, i5);
            } else {
                z = z3;
                i2 = transformedIndex;
                view = menuChildAt;
                f = f3;
                animation = getAnimation(menuChildAt, transformedIndex, f3, isExpanded, i5);
            }
            final boolean z4 = isExpanded;
            final View view2 = view;
            final boolean z5 = z2;
            Animation animation2 = animation;
            final boolean z6 = z;
            int i6 = i5;
            OnStateChangeListener onStateChangeListener2 = onStateChangeListener;
            final boolean z7 = i2 == i4;
            int i7 = i4;
            final OnStateChangeListener onStateChangeListener3 = onStateChangeListener2;
            AnonymousClass2 r0 = new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                    if (z4) {
                        view2.setVisibility(0);
                    }
                }

                public void onAnimationEnd(Animation animation) {
                    if (!z4) {
                        view2.setVisibility(4);
                    }
                    if (z5 && z6) {
                        OnItemClickListener onItemClickListener = FloatingButtonAnimationHandlerBase.this.mFloatingButtonMenu.getOnItemClickListener();
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(view2, i5);
                        }
                    }
                    if (z7) {
                        FloatingButtonAnimationHandlerBase.this.mIsAnimating = false;
                        OnStateChangeListener onStateChangeListener = onStateChangeListener3;
                        if (onStateChangeListener != null) {
                            onStateChangeListener.onMenuStateChanged(z4 ? 1 : 3);
                        }
                    }
                }
            };
            animation2.setAnimationListener(r0);
            view.setAnimation(animation2);
            f3 = f + f2;
            i5 = i6 + 1;
            i4 = i7;
            onStateChangeListener = onStateChangeListener2;
        }
    }

    private Point getChildOriginPoint(View view, float f, boolean z, int i) {
        Point floatingButtonTopCornerCoor = this.mFloatingButtonMenu.getFloatingButtonTopCornerCoor();
        int i2 = floatingButtonTopCornerCoor.x;
        int i3 = floatingButtonTopCornerCoor.y;
        Rect computeChildFrame = this.mFloatingButtonMenu.computeChildFrame(f, view.getMeasuredWidth(), view.getMeasuredHeight(), i);
        return new Point(z ? Math.abs(computeChildFrame.left - i2) : view.getLeft() - computeChildFrame.left, z ? Math.abs(computeChildFrame.top - i3) : view.getTop() - computeChildFrame.top);
    }

    /* access modifiers changed from: protected */
    public Animation getStaticTranslateAnimation(View view, float f, int i) {
        Point childOriginPoint = getChildOriginPoint(view, f, false, i);
        TranslateAnimation translateAnimation = new TranslateAnimation(0, (float) childOriginPoint.x, 0, (float) childOriginPoint.x, 0, (float) childOriginPoint.y, 0, (float) childOriginPoint.y);
        return translateAnimation;
    }

    /* access modifiers changed from: protected */
    public Animation getTranslateAnimation(View view, float f, boolean z, int i) {
        Point childOriginPoint = getChildOriginPoint(view, f, z, i);
        TranslateAnimation translateAnimation = new TranslateAnimation(0, (float) childOriginPoint.x, 0, BitmapDescriptorFactory.HUE_RED, 0, (float) childOriginPoint.y, 0, BitmapDescriptorFactory.HUE_RED);
        return translateAnimation;
    }

    /* access modifiers changed from: protected */
    public Animation getAlphaAnimation(boolean z) {
        float f = BitmapDescriptorFactory.HUE_RED;
        float f2 = z ? BitmapDescriptorFactory.HUE_RED : 1.0f;
        if (z) {
            f = 1.0f;
        }
        return new AlphaAnimation(f2, f);
    }

    /* access modifiers changed from: protected */
    public Animation getRotateAnimation() {
        return getRotateAnimation(BitmapDescriptorFactory.HUE_RED, 1440.0f);
    }

    /* access modifiers changed from: protected */
    public Animation getRotateAnimation(float f, float f2) {
        RotateAnimation rotateAnimation = new RotateAnimation(f, f2, 1, 0.5f, 1, 0.5f);
        return rotateAnimation;
    }

    /* access modifiers changed from: protected */
    public Animation getScaleAnimation(float f, float f2) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, f, f2, 1, 0.5f, 1, 0.5f);
        return scaleAnimation;
    }

    private void handleBackgroundView(final boolean z) {
        if (this.mFloatingButtonMenu.shouldShowBackground()) {
            final View backgroundView = this.mFloatingButtonMenu.getBackgroundView();
            Animation backgroundAlphaAnimation = getBackgroundAlphaAnimation(z);
            backgroundView.startAnimation(backgroundAlphaAnimation);
            backgroundAlphaAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    backgroundView.setVisibility(z ? 0 : 4);
                }
            });
        }
    }

    private Animation getBackgroundAlphaAnimation(boolean z) {
        Animation alphaAnimation = getAlphaAnimation(z);
        alphaAnimation.setDuration(100);
        return alphaAnimation;
    }

    private Animation getFloatingButtonAnimation(boolean z) {
        Animation rotateAnimation = getRotateAnimation(BitmapDescriptorFactory.HUE_RED, z ? 135.0f : -135.0f);
        rotateAnimation.setDuration(250);
        rotateAnimation.setFillBefore(true);
        rotateAnimation.setFillEnabled(true);
        return rotateAnimation;
    }

    private Animation getOptionAnimation(View view, float f, boolean z, int i) {
        AnimationSet animationSet = new AnimationSet(false);
        Animation staticTranslateAnimation = getStaticTranslateAnimation(view, f, i);
        Animation scaleAnimation = getScaleAnimation(1.0f, z ? 2.0f : BitmapDescriptorFactory.HUE_RED);
        Animation alphaAnimation = getAlphaAnimation(false);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(staticTranslateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(250);
        return animationSet;
    }

    /* access modifiers changed from: protected */
    public long getChildStartOffset(int i) {
        return ((long) i) * this.startOffsetBetweenEachChild;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public void setStartOffsetBetweenEachChild(long j) {
        this.startOffsetBetweenEachChild = j;
    }

    public void setOpenInterpolator(Interpolator interpolator) {
        this.openInterpolator = interpolator;
    }

    public void setCloseInterpolator(Interpolator interpolator) {
        this.closeInterpolator = interpolator;
    }

    public boolean isAnimating() {
        return this.mIsAnimating;
    }
}
