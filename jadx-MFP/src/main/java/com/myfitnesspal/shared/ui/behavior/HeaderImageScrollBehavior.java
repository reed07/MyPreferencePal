package com.myfitnesspal.shared.ui.behavior;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.NestedScrollView.OnScrollChangeListener;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.DecelerateInterpolator;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.activity.MfpActivity;

public class HeaderImageScrollBehavior extends Behavior<View> {
    private static final long COLLAPSE_DURATION_MS = 250;
    private static final float MAX_ALPHA = 255.0f;
    private static final float MIN_ELEVATION = 0.1f;
    /* access modifiers changed from: private */
    public ValueAnimator collapseAnim;
    private final int defaultImageHeight;
    /* access modifiers changed from: private */
    public View imageHeaderContainer;
    private final int maxImageHeight;
    private final View toolbar;
    private final View toolbarContainer;
    private final float toolbarElevation;
    private final float toolbarHeight;

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i) {
        return true;
    }

    public HeaderImageScrollBehavior(MfpActivity mfpActivity, View view, int i) {
        this(mfpActivity, view, i, null);
    }

    public HeaderImageScrollBehavior(MfpActivity mfpActivity, View view, int i, View view2) {
        this.imageHeaderContainer = view;
        Resources resources = mfpActivity.getResources();
        this.defaultImageHeight = resources.getDimensionPixelSize(R.dimen.toolbar_large_image_height_default);
        this.maxImageHeight = resources.getDimensionPixelSize(R.dimen.toolbar_large_image_height_max);
        this.toolbarElevation = resources.getDimension(R.dimen.default_toolbar_elevation);
        this.toolbarHeight = resources.getDimension(R.dimen.toolbar_height);
        this.toolbarContainer = mfpActivity.findViewById(R.id.toolbar_container);
        this.toolbar = mfpActivity.getToolbar();
        if (view2 != null) {
            view2.setPadding(0, (int) this.toolbarHeight, 0, 0);
        }
        TypedArray obtainStyledAttributes = mfpActivity.obtainStyledAttributes(new TypedValue().data, new int[]{R.attr.colorPrimary});
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        ColorDrawable colorDrawable = new ColorDrawable(color);
        colorDrawable.setAlpha(0);
        this.toolbarContainer.setBackgroundResource(R.drawable.transparent_toolbar_gradient);
        this.toolbar.setBackground(colorDrawable);
        ViewCompat.setElevation(this.toolbarContainer, 0.1f);
        ViewCompat.setElevation(this.toolbar, 0.1f);
        ((NestedScrollView) mfpActivity.findViewById(i)).setOnScrollChangeListener(new OnScrollChangeListener() {
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                HeaderImageScrollBehavior.this.updateToolbarOpacity(nestedScrollView);
            }
        });
    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int[] iArr) {
        int i3 = this.imageHeaderContainer.getLayoutParams().height;
        int i4 = this.defaultImageHeight;
        if (i3 > i4 && i2 > 0) {
            int min = Math.min(i2, i3 - i4);
            if (min > 0) {
                LayoutParams layoutParams = this.imageHeaderContainer.getLayoutParams();
                layoutParams.height -= min;
                this.imageHeaderContainer.requestLayout();
                iArr[1] = min;
            }
        }
        super.onNestedPreScroll(coordinatorLayout, view, view2, i, i2, iArr);
    }

    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int i3, int i4) {
        if (i4 >= 0 || view.getId() != R.id.content_frame) {
            super.onNestedScroll(coordinatorLayout, view, view2, i, i2, i3, i4);
            return;
        }
        ValueAnimator valueAnimator = this.collapseAnim;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.collapseAnim = null;
        }
        LayoutParams layoutParams = this.imageHeaderContainer.getLayoutParams();
        int min = Math.min(this.maxImageHeight, layoutParams.height + Math.abs(i4));
        if (min != layoutParams.height) {
            layoutParams.height = min;
            this.imageHeaderContainer.setLayoutParams(layoutParams);
            this.imageHeaderContainer.requestLayout();
        }
    }

    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (this.collapseAnim == null) {
            int i = this.imageHeaderContainer.getLayoutParams().height;
            int i2 = this.defaultImageHeight;
            if (i != i2) {
                this.collapseAnim = ValueAnimator.ofInt(new int[]{i, i2});
                this.collapseAnim.setInterpolator(new DecelerateInterpolator());
                this.collapseAnim.addUpdateListener(new AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        HeaderImageScrollBehavior.this.imageHeaderContainer.getLayoutParams().height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                        HeaderImageScrollBehavior.this.imageHeaderContainer.requestLayout();
                    }
                });
                this.collapseAnim.addListener(new AnimatorListener() {
                    public void onAnimationRepeat(Animator animator) {
                    }

                    public void onAnimationStart(Animator animator) {
                    }

                    public void onAnimationEnd(Animator animator) {
                        HeaderImageScrollBehavior.this.collapseAnim = null;
                    }

                    public void onAnimationCancel(Animator animator) {
                        HeaderImageScrollBehavior.this.collapseAnim = null;
                    }
                });
                this.collapseAnim.setDuration(COLLAPSE_DURATION_MS);
                this.collapseAnim.start();
            }
        }
        super.onStopNestedScroll(coordinatorLayout, view, view2);
    }

    /* access modifiers changed from: private */
    public void updateToolbarOpacity(View view) {
        if (view instanceof NestedScrollView) {
            int scrollY = ((NestedScrollView) view).getScrollY();
            int i = this.defaultImageHeight;
            float f = this.toolbarHeight;
            int i2 = i - ((int) (2.0f * f));
            if (scrollY >= i2) {
                int min = Math.min(255, ((scrollY - i2) * 255) / ((int) f));
                this.toolbar.getBackground().setAlpha(min);
                float max = Math.max(0.1f, (((float) min) / MAX_ALPHA) * this.toolbarElevation);
                ViewCompat.setElevation(this.toolbarContainer, max);
                ViewCompat.setElevation(this.toolbar, max);
                return;
            }
            this.toolbar.getBackground().setAlpha(0);
            ViewCompat.setElevation(this.toolbarContainer, 0.1f);
            ViewCompat.setElevation(this.toolbar, 0.1f);
        }
    }
}
