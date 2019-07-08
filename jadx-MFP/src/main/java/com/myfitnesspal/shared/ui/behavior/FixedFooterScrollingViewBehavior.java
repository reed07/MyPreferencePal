package com.myfitnesspal.shared.ui.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.ScrollingViewBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

public class FixedFooterScrollingViewBehavior extends ScrollingViewBehavior {
    private AppBarLayout appBarLayout;
    /* access modifiers changed from: private */
    public boolean onAnimationRunnablePosted = false;

    public FixedFooterScrollingViewBehavior() {
    }

    public FixedFooterScrollingViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i) {
        AppBarLayout appBarLayout2 = this.appBarLayout;
        if (appBarLayout2 != null) {
            startAnimationRunnable(view, appBarLayout2);
        }
        return super.onStartNestedScroll(coordinatorLayout, view, view2, view3, i);
    }

    public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
        AppBarLayout appBarLayout2 = this.appBarLayout;
        if (appBarLayout2 != null) {
            int calculateBottomPadding = calculateBottomPadding(appBarLayout2);
            if (calculateBottomPadding != view.getPaddingBottom()) {
                view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), calculateBottomPadding);
            }
        }
        return super.onMeasureChild(coordinatorLayout, view, i, i2, i3, i4);
    }

    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (this.appBarLayout == null) {
            this.appBarLayout = (AppBarLayout) view2;
        }
        boolean onDependentViewChanged = super.onDependentViewChanged(coordinatorLayout, view, view2);
        int calculateBottomPadding = calculateBottomPadding(this.appBarLayout);
        boolean z = calculateBottomPadding != view.getPaddingBottom();
        if (z) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), calculateBottomPadding);
            view.requestLayout();
        }
        startAnimationRunnable(view, view2);
        if (z || onDependentViewChanged) {
            return true;
        }
        return false;
    }

    private int calculateBottomPadding(AppBarLayout appBarLayout2) {
        return appBarLayout2.getTotalScrollRange() + appBarLayout2.getTop();
    }

    private void startAnimationRunnable(View view, View view2) {
        if (!this.onAnimationRunnablePosted) {
            final int top = view.getTop();
            final int top2 = view2.getTop();
            this.onAnimationRunnablePosted = true;
            final View view3 = view;
            final View view4 = view2;
            AnonymousClass1 r1 = new Runnable() {
                private static final int MAX_COUNT_OF_FRAMES_WITH_NO_CHANGES = 5;
                private int countOfFramesWithNoChanges;
                private int previousChildTop = top;
                private int previousDependencyTop = top2;

                public void run() {
                    boolean z;
                    int top = view3.getTop();
                    int top2 = view4.getTop();
                    if (top != this.previousChildTop) {
                        this.previousChildTop = top;
                        this.countOfFramesWithNoChanges = 0;
                        z = true;
                    } else {
                        z = false;
                    }
                    if (top2 != this.previousDependencyTop) {
                        this.previousDependencyTop = top2;
                        this.countOfFramesWithNoChanges = 0;
                        z = true;
                    }
                    if (!z) {
                        this.countOfFramesWithNoChanges++;
                    }
                    if (this.countOfFramesWithNoChanges <= 5) {
                        view3.requestLayout();
                        view3.postOnAnimation(this);
                        return;
                    }
                    view3.requestLayout();
                    FixedFooterScrollingViewBehavior.this.onAnimationRunnablePosted = false;
                }
            };
            view.postOnAnimation(r1);
        }
    }
}
