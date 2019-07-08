package android.support.design.transformation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.R;
import android.support.design.animation.AnimationUtils;
import android.support.design.animation.AnimatorSetCompat;
import android.support.design.animation.ArgbEvaluatorCompat;
import android.support.design.animation.ChildrenAlphaProperty;
import android.support.design.animation.DrawableAlphaProperty;
import android.support.design.animation.MotionSpec;
import android.support.design.animation.MotionTiming;
import android.support.design.animation.Positioning;
import android.support.design.circularreveal.CircularRevealCompat;
import android.support.design.circularreveal.CircularRevealHelper;
import android.support.design.circularreveal.CircularRevealWidget;
import android.support.design.circularreveal.CircularRevealWidget.CircularRevealScrimColorProperty;
import android.support.design.circularreveal.CircularRevealWidget.RevealInfo;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.LayoutParams;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.MathUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.List;

public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {
    private final int[] tmpArray = new int[2];
    private final Rect tmpRect = new Rect();
    private final RectF tmpRectF1 = new RectF();
    private final RectF tmpRectF2 = new RectF();

    protected static class FabTransformationSpec {
        public Positioning positioning;
        public MotionSpec timings;

        protected FabTransformationSpec() {
        }
    }

    /* access modifiers changed from: protected */
    public abstract FabTransformationSpec onCreateMotionSpec(Context context, boolean z);

    public FabTransformationBehavior() {
    }

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @CallSuper
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view.getVisibility() != 8) {
            boolean z = false;
            if (!(view2 instanceof FloatingActionButton)) {
                return false;
            }
            int expandedComponentIdHint = ((FloatingActionButton) view2).getExpandedComponentIdHint();
            if (expandedComponentIdHint == 0 || expandedComponentIdHint == view.getId()) {
                z = true;
            }
            return z;
        }
        throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
    }

    @CallSuper
    public void onAttachedToLayoutParams(@NonNull LayoutParams layoutParams) {
        if (layoutParams.dodgeInsetEdges == 0) {
            layoutParams.dodgeInsetEdges = 80;
        }
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AnimatorSet onCreateExpandedStateChangeAnimation(View view, View view2, boolean z, boolean z2) {
        final boolean z3 = z;
        FabTransformationSpec onCreateMotionSpec = onCreateMotionSpec(view2.getContext(), z3);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (VERSION.SDK_INT >= 21) {
            createElevationAnimation(view, view2, z, z2, onCreateMotionSpec, arrayList, arrayList2);
        }
        RectF rectF = this.tmpRectF1;
        View view3 = view;
        View view4 = view2;
        boolean z4 = z;
        boolean z5 = z2;
        FabTransformationSpec fabTransformationSpec = onCreateMotionSpec;
        ArrayList arrayList3 = arrayList;
        ArrayList arrayList4 = arrayList2;
        createTranslationAnimation(view3, view4, z4, z5, fabTransformationSpec, arrayList3, arrayList4, rectF);
        float width = rectF.width();
        float height = rectF.height();
        createIconFadeAnimation(view3, view4, z4, z5, fabTransformationSpec, arrayList3, arrayList4);
        createExpansionAnimation(view3, view4, z4, z5, fabTransformationSpec, width, height, arrayList, arrayList2);
        ArrayList arrayList5 = arrayList;
        ArrayList arrayList6 = arrayList2;
        createColorAnimation(view3, view4, z4, z5, fabTransformationSpec, arrayList5, arrayList6);
        createChildrenFadeAnimation(view3, view4, z4, z5, fabTransformationSpec, arrayList5, arrayList6);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        final View view5 = view;
        final View view6 = view2;
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                if (z3) {
                    view6.setVisibility(0);
                    view5.setAlpha(BitmapDescriptorFactory.HUE_RED);
                    view5.setVisibility(4);
                }
            }

            public void onAnimationEnd(Animator animator) {
                if (!z3) {
                    view6.setVisibility(4);
                    view5.setAlpha(1.0f);
                    view5.setVisibility(0);
                }
            }
        });
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            animatorSet.addListener((AnimatorListener) arrayList2.get(i));
        }
        return animatorSet;
    }

    @TargetApi(21)
    private void createElevationAnimation(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<AnimatorListener> list2) {
        ObjectAnimator objectAnimator;
        float elevation = ViewCompat.getElevation(view2) - ViewCompat.getElevation(view);
        if (z) {
            if (!z2) {
                view2.setTranslationZ(-elevation);
            }
            objectAnimator = ObjectAnimator.ofFloat(view2, View.TRANSLATION_Z, new float[]{0.0f});
        } else {
            objectAnimator = ObjectAnimator.ofFloat(view2, View.TRANSLATION_Z, new float[]{-elevation});
        }
        fabTransformationSpec.timings.getTiming("elevation").apply(objectAnimator);
        list.add(objectAnimator);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0096  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void createTranslationAnimation(android.view.View r17, android.view.View r18, boolean r19, boolean r20, android.support.design.transformation.FabTransformationBehavior.FabTransformationSpec r21, java.util.List<android.animation.Animator> r22, java.util.List<android.animation.Animator.AnimatorListener> r23, android.graphics.RectF r24) {
        /*
            r16 = this;
            r10 = r16
            r0 = r17
            r1 = r18
            r2 = r21
            r11 = r22
            android.support.design.animation.Positioning r3 = r2.positioning
            float r3 = r10.calculateTranslationX(r0, r1, r3)
            android.support.design.animation.Positioning r4 = r2.positioning
            float r0 = r10.calculateTranslationY(r0, r1, r4)
            r4 = 0
            int r5 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r5 == 0) goto L_0x0050
            int r5 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r5 != 0) goto L_0x0020
            goto L_0x0050
        L_0x0020:
            if (r19 == 0) goto L_0x0026
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 < 0) goto L_0x002a
        L_0x0026:
            if (r19 != 0) goto L_0x003d
            if (r5 <= 0) goto L_0x003d
        L_0x002a:
            android.support.design.animation.MotionSpec r5 = r2.timings
            java.lang.String r6 = "translationXCurveUpwards"
            android.support.design.animation.MotionTiming r5 = r5.getTiming(r6)
            android.support.design.animation.MotionSpec r6 = r2.timings
            java.lang.String r7 = "translationYCurveUpwards"
            android.support.design.animation.MotionTiming r6 = r6.getTiming(r7)
            r12 = r5
            r13 = r6
            goto L_0x0062
        L_0x003d:
            android.support.design.animation.MotionSpec r5 = r2.timings
            java.lang.String r6 = "translationXCurveDownwards"
            android.support.design.animation.MotionTiming r5 = r5.getTiming(r6)
            android.support.design.animation.MotionSpec r6 = r2.timings
            java.lang.String r7 = "translationYCurveDownwards"
            android.support.design.animation.MotionTiming r6 = r6.getTiming(r7)
            r12 = r5
            r13 = r6
            goto L_0x0062
        L_0x0050:
            android.support.design.animation.MotionSpec r5 = r2.timings
            java.lang.String r6 = "translationXLinear"
            android.support.design.animation.MotionTiming r5 = r5.getTiming(r6)
            android.support.design.animation.MotionSpec r6 = r2.timings
            java.lang.String r7 = "translationYLinear"
            android.support.design.animation.MotionTiming r6 = r6.getTiming(r7)
            r12 = r5
            r13 = r6
        L_0x0062:
            r5 = 0
            r6 = 1
            if (r19 == 0) goto L_0x0096
            if (r20 != 0) goto L_0x0070
            float r7 = -r3
            r1.setTranslationX(r7)
            float r7 = -r0
            r1.setTranslationY(r7)
        L_0x0070:
            android.util.Property r7 = android.view.View.TRANSLATION_X
            float[] r8 = new float[r6]
            r8[r5] = r4
            android.animation.ObjectAnimator r14 = android.animation.ObjectAnimator.ofFloat(r1, r7, r8)
            android.util.Property r7 = android.view.View.TRANSLATION_Y
            float[] r6 = new float[r6]
            r6[r5] = r4
            android.animation.ObjectAnimator r15 = android.animation.ObjectAnimator.ofFloat(r1, r7, r6)
            float r5 = -r3
            float r6 = -r0
            r7 = 0
            r8 = 0
            r0 = r16
            r1 = r18
            r2 = r21
            r3 = r12
            r4 = r13
            r9 = r24
            r0.calculateChildVisibleBoundsAtEndOfExpansion(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x00ac
        L_0x0096:
            android.util.Property r2 = android.view.View.TRANSLATION_X
            float[] r4 = new float[r6]
            float r3 = -r3
            r4[r5] = r3
            android.animation.ObjectAnimator r14 = android.animation.ObjectAnimator.ofFloat(r1, r2, r4)
            android.util.Property r2 = android.view.View.TRANSLATION_Y
            float[] r3 = new float[r6]
            float r0 = -r0
            r3[r5] = r0
            android.animation.ObjectAnimator r15 = android.animation.ObjectAnimator.ofFloat(r1, r2, r3)
        L_0x00ac:
            r12.apply(r14)
            r13.apply(r15)
            r11.add(r14)
            r11.add(r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.transformation.FabTransformationBehavior.createTranslationAnimation(android.view.View, android.view.View, boolean, boolean, android.support.design.transformation.FabTransformationBehavior$FabTransformationSpec, java.util.List, java.util.List, android.graphics.RectF):void");
    }

    private void createIconFadeAnimation(View view, final View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<AnimatorListener> list2) {
        ObjectAnimator objectAnimator;
        if ((view2 instanceof CircularRevealWidget) && (view instanceof ImageView)) {
            final CircularRevealWidget circularRevealWidget = (CircularRevealWidget) view2;
            final Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable != null) {
                drawable.mutate();
                if (z) {
                    if (!z2) {
                        drawable.setAlpha(255);
                    }
                    objectAnimator = ObjectAnimator.ofInt(drawable, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, new int[]{0});
                } else {
                    objectAnimator = ObjectAnimator.ofInt(drawable, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, new int[]{255});
                }
                objectAnimator.addUpdateListener(new AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        view2.invalidate();
                    }
                });
                fabTransformationSpec.timings.getTiming("iconFade").apply(objectAnimator);
                list.add(objectAnimator);
                list2.add(new AnimatorListenerAdapter() {
                    public void onAnimationStart(Animator animator) {
                        circularRevealWidget.setCircularRevealOverlayDrawable(drawable);
                    }

                    public void onAnimationEnd(Animator animator) {
                        circularRevealWidget.setCircularRevealOverlayDrawable(null);
                    }
                });
            }
        }
    }

    private void createExpansionAnimation(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, float f, float f2, List<Animator> list, List<AnimatorListener> list2) {
        Animator animator;
        View view3 = view;
        View view4 = view2;
        FabTransformationSpec fabTransformationSpec2 = fabTransformationSpec;
        if (view4 instanceof CircularRevealWidget) {
            final CircularRevealWidget circularRevealWidget = (CircularRevealWidget) view4;
            float calculateRevealCenterX = calculateRevealCenterX(view3, view4, fabTransformationSpec2.positioning);
            float calculateRevealCenterY = calculateRevealCenterY(view3, view4, fabTransformationSpec2.positioning);
            ((FloatingActionButton) view3).getContentRect(this.tmpRect);
            float width = ((float) this.tmpRect.width()) / 2.0f;
            MotionTiming timing = fabTransformationSpec2.timings.getTiming("expansion");
            if (z) {
                if (!z2) {
                    circularRevealWidget.setRevealInfo(new RevealInfo(calculateRevealCenterX, calculateRevealCenterY, width));
                }
                if (z2) {
                    width = circularRevealWidget.getRevealInfo().radius;
                }
                animator = CircularRevealCompat.createCircularReveal(circularRevealWidget, calculateRevealCenterX, calculateRevealCenterY, MathUtils.distanceToFurthestCorner(calculateRevealCenterX, calculateRevealCenterY, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, f, f2));
                animator.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        RevealInfo revealInfo = circularRevealWidget.getRevealInfo();
                        revealInfo.radius = Float.MAX_VALUE;
                        circularRevealWidget.setRevealInfo(revealInfo);
                    }
                });
                createPreFillRadialExpansion(view2, timing.getDelay(), (int) calculateRevealCenterX, (int) calculateRevealCenterY, width, list);
            } else {
                float f3 = circularRevealWidget.getRevealInfo().radius;
                Animator createCircularReveal = CircularRevealCompat.createCircularReveal(circularRevealWidget, calculateRevealCenterX, calculateRevealCenterY, width);
                int i = (int) calculateRevealCenterX;
                int i2 = (int) calculateRevealCenterY;
                View view5 = view2;
                int i3 = i;
                createPreFillRadialExpansion(view5, timing.getDelay(), i, i2, f3, list);
                createPostFillRadialExpansion(view5, timing.getDelay(), timing.getDuration(), fabTransformationSpec2.timings.getTotalDuration(), i3, i2, width, list);
                animator = createCircularReveal;
            }
            timing.apply(animator);
            list.add(animator);
            list2.add(CircularRevealCompat.createCircularRevealListener(circularRevealWidget));
        }
    }

    private void createColorAnimation(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<AnimatorListener> list2) {
        ObjectAnimator objectAnimator;
        if (view2 instanceof CircularRevealWidget) {
            CircularRevealWidget circularRevealWidget = (CircularRevealWidget) view2;
            int backgroundTint = getBackgroundTint(view);
            int i = 16777215 & backgroundTint;
            if (z) {
                if (!z2) {
                    circularRevealWidget.setCircularRevealScrimColor(backgroundTint);
                }
                objectAnimator = ObjectAnimator.ofInt(circularRevealWidget, CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, new int[]{i});
            } else {
                objectAnimator = ObjectAnimator.ofInt(circularRevealWidget, CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, new int[]{backgroundTint});
            }
            objectAnimator.setEvaluator(ArgbEvaluatorCompat.getInstance());
            fabTransformationSpec.timings.getTiming("color").apply(objectAnimator);
            list.add(objectAnimator);
        }
    }

    private void createChildrenFadeAnimation(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<AnimatorListener> list2) {
        ObjectAnimator objectAnimator;
        if (view2 instanceof ViewGroup) {
            if (!(view2 instanceof CircularRevealWidget) || CircularRevealHelper.STRATEGY != 0) {
                ViewGroup calculateChildContentContainer = calculateChildContentContainer(view2);
                if (calculateChildContentContainer != null) {
                    if (z) {
                        if (!z2) {
                            ChildrenAlphaProperty.CHILDREN_ALPHA.set(calculateChildContentContainer, Float.valueOf(BitmapDescriptorFactory.HUE_RED));
                        }
                        objectAnimator = ObjectAnimator.ofFloat(calculateChildContentContainer, ChildrenAlphaProperty.CHILDREN_ALPHA, new float[]{1.0f});
                    } else {
                        objectAnimator = ObjectAnimator.ofFloat(calculateChildContentContainer, ChildrenAlphaProperty.CHILDREN_ALPHA, new float[]{0.0f});
                    }
                    fabTransformationSpec.timings.getTiming("contentFade").apply(objectAnimator);
                    list.add(objectAnimator);
                }
            }
        }
    }

    private float calculateTranslationX(View view, View view2, Positioning positioning) {
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        int i = positioning.gravity & 7;
        float f = i != 1 ? i != 3 ? i != 5 ? BitmapDescriptorFactory.HUE_RED : rectF2.right - rectF.right : rectF2.left - rectF.left : rectF2.centerX() - rectF.centerX();
        return f + positioning.xAdjustment;
    }

    private float calculateTranslationY(View view, View view2, Positioning positioning) {
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        int i = positioning.gravity & 112;
        float f = i != 16 ? i != 48 ? i != 80 ? BitmapDescriptorFactory.HUE_RED : rectF2.bottom - rectF.bottom : rectF2.top - rectF.top : rectF2.centerY() - rectF.centerY();
        return f + positioning.yAdjustment;
    }

    private void calculateWindowBounds(View view, RectF rectF) {
        rectF.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (float) view.getWidth(), (float) view.getHeight());
        int[] iArr = this.tmpArray;
        view.getLocationInWindow(iArr);
        rectF.offsetTo((float) iArr[0], (float) iArr[1]);
        rectF.offset((float) ((int) (-view.getTranslationX())), (float) ((int) (-view.getTranslationY())));
    }

    private float calculateRevealCenterX(View view, View view2, Positioning positioning) {
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        rectF2.offset(-calculateTranslationX(view, view2, positioning), BitmapDescriptorFactory.HUE_RED);
        return rectF.centerX() - rectF2.left;
    }

    private float calculateRevealCenterY(View view, View view2, Positioning positioning) {
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        rectF2.offset(BitmapDescriptorFactory.HUE_RED, -calculateTranslationY(view, view2, positioning));
        return rectF.centerY() - rectF2.top;
    }

    private void calculateChildVisibleBoundsAtEndOfExpansion(View view, FabTransformationSpec fabTransformationSpec, MotionTiming motionTiming, MotionTiming motionTiming2, float f, float f2, float f3, float f4, RectF rectF) {
        float calculateValueOfAnimationAtEndOfExpansion = calculateValueOfAnimationAtEndOfExpansion(fabTransformationSpec, motionTiming, f, f3);
        float calculateValueOfAnimationAtEndOfExpansion2 = calculateValueOfAnimationAtEndOfExpansion(fabTransformationSpec, motionTiming2, f2, f4);
        Rect rect = this.tmpRect;
        view.getWindowVisibleDisplayFrame(rect);
        RectF rectF2 = this.tmpRectF1;
        rectF2.set(rect);
        RectF rectF3 = this.tmpRectF2;
        calculateWindowBounds(view, rectF3);
        rectF3.offset(calculateValueOfAnimationAtEndOfExpansion, calculateValueOfAnimationAtEndOfExpansion2);
        rectF3.intersect(rectF2);
        rectF.set(rectF3);
    }

    private float calculateValueOfAnimationAtEndOfExpansion(FabTransformationSpec fabTransformationSpec, MotionTiming motionTiming, float f, float f2) {
        long delay = motionTiming.getDelay();
        long duration = motionTiming.getDuration();
        MotionTiming timing = fabTransformationSpec.timings.getTiming("expansion");
        return AnimationUtils.lerp(f, f2, motionTiming.getInterpolator().getInterpolation(((float) (((timing.getDelay() + timing.getDuration()) + 17) - delay)) / ((float) duration)));
    }

    @Nullable
    private ViewGroup calculateChildContentContainer(View view) {
        View findViewById = view.findViewById(R.id.mtrl_child_content_container);
        if (findViewById != null) {
            return toViewGroupOrNull(findViewById);
        }
        if ((view instanceof TransformationChildLayout) || (view instanceof TransformationChildCard)) {
            return toViewGroupOrNull(((ViewGroup) view).getChildAt(0));
        }
        return toViewGroupOrNull(view);
    }

    @Nullable
    private ViewGroup toViewGroupOrNull(View view) {
        if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        }
        return null;
    }

    private int getBackgroundTint(View view) {
        ColorStateList backgroundTintList = ViewCompat.getBackgroundTintList(view);
        if (backgroundTintList != null) {
            return backgroundTintList.getColorForState(view.getDrawableState(), backgroundTintList.getDefaultColor());
        }
        return 0;
    }

    private void createPreFillRadialExpansion(View view, long j, int i, int i2, float f, List<Animator> list) {
        if (VERSION.SDK_INT >= 21 && j > 0) {
            Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view, i, i2, f, f);
            createCircularReveal.setStartDelay(0);
            createCircularReveal.setDuration(j);
            list.add(createCircularReveal);
        }
    }

    private void createPostFillRadialExpansion(View view, long j, long j2, long j3, int i, int i2, float f, List<Animator> list) {
        if (VERSION.SDK_INT >= 21) {
            long j4 = j + j2;
            if (j4 < j3) {
                Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view, i, i2, f, f);
                createCircularReveal.setStartDelay(j4);
                createCircularReveal.setDuration(j3 - j4);
                list.add(createCircularReveal);
            }
        }
    }
}
