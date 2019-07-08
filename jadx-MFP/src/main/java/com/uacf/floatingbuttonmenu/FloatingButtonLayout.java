package com.uacf.floatingbuttonmenu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.google.logging.type.LogSeverity;
import com.uacf.floatingbuttonmenu.animation.FloatingButtonAnimationHandlerBase;

class FloatingButtonLayout extends ViewGroup {
    private View floatingButton;
    private FloatingButtonAnimationHandlerBase mAnimationHandler;
    private boolean mExpanded;
    private float mFromDegrees = 180.0f;
    private int mItemSpacing;
    private int mRadius = LogSeverity.NOTICE_VALUE;
    private float mToDegrees = 270.0f;

    public FloatingButtonLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setFloatingButton(View view) {
        this.floatingButton = view;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        measureChildren(i, i2);
        setMeasuredDimension(MeasureSpec.getSize(i), MeasureSpec.getSize(i2));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Point floatingButtonCenter = getFloatingButtonCenter();
        int childCount = getChildCount();
        float f = this.mToDegrees;
        float f2 = this.mFromDegrees;
        float f3 = (f - f2) / ((float) (childCount - 1));
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            Rect computeChildFrame = computeChildFrame(floatingButtonCenter, f2, childAt.getMeasuredWidth(), childAt.getMeasuredHeight(), i5);
            getChildAt(i5).layout(computeChildFrame.left, computeChildFrame.top, computeChildFrame.right, computeChildFrame.bottom);
            f2 += f3;
        }
    }

    public Rect computeChildFrame(float f, int i, int i2, int i3) {
        return computeChildFrame(getFloatingButtonCenter(), f, i, i2, i3);
    }

    private Rect computeChildFrame(Point point, float f, int i, int i2, int i3) {
        double d = (double) point.x;
        double d2 = (double) point.y;
        if (this.mExpanded) {
            if (isPhoneLandscape()) {
                d -= (double) (this.mExpanded ? this.mItemSpacing + ((i3 + 1) * i) : 0);
            } else {
                double d3 = (double) f;
                d += ((double) this.mRadius) * Math.cos(Math.toRadians(d3));
                d2 += ((double) this.mRadius) * Math.sin(Math.toRadians(d3));
            }
        }
        double d4 = (double) (i / 2);
        double d5 = (double) (i2 / 2);
        return new Rect((int) (d - d4), (int) (d2 - d5), (int) (d + d4), (int) (d2 + d5));
    }

    public void toggleState(boolean z) {
        setState(!this.mExpanded, z);
    }

    public void setState(boolean z, boolean z2) {
        setState(z, z2, -1);
    }

    public void setState(boolean z, boolean z2, int i) {
        if (!this.mAnimationHandler.isAnimating() && this.mExpanded != z) {
            this.mExpanded = z;
            if (z2) {
                this.mAnimationHandler.animateMenu(i);
            }
            requestLayout();
        }
    }

    public void setArc(float f, float f2) {
        if (this.mFromDegrees != f || this.mToDegrees != f2) {
            this.mFromDegrees = f;
            this.mToDegrees = f2;
            requestLayout();
        }
    }

    public void setRadius(int i) {
        if (this.mRadius != i) {
            this.mRadius = i;
            requestLayout();
        }
    }

    public void setAnimationHandler(FloatingButtonAnimationHandlerBase floatingButtonAnimationHandlerBase) {
        this.mAnimationHandler = floatingButtonAnimationHandlerBase;
    }

    public float getToDegrees() {
        return this.mToDegrees;
    }

    public float getFromDegrees() {
        return this.mFromDegrees;
    }

    public boolean isExpanded() {
        return this.mExpanded;
    }

    public int getRadius() {
        return this.mRadius;
    }

    public boolean isPointOutsideFloatingButtonLayout(int i, int i2) {
        return !getFloatingButtonLayoutTouchArea().contains(i, i2);
    }

    private Rect getFloatingButtonLayoutTouchArea() {
        Point floatingButtonTopCornerCoordinates = getFloatingButtonTopCornerCoordinates();
        Point floatingButtonSize = getFloatingButtonSize();
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.floating_button_layout_additional_padding);
        int i = floatingButtonTopCornerCoordinates.x;
        int i2 = floatingButtonTopCornerCoordinates.y;
        int childCount = getChildCount();
        int i3 = floatingButtonSize.x;
        int i4 = floatingButtonSize.y;
        int i5 = i - dimensionPixelOffset;
        int i6 = i2 - dimensionPixelOffset;
        if (this.mExpanded && childCount > 0) {
            View childAt = getChildAt(0);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (isPhoneLandscape()) {
                i5 -= (measuredWidth + this.mItemSpacing) * childCount;
            } else {
                int i7 = this.mRadius;
                i5 -= (measuredWidth + i7) - (i3 / 2);
                i6 -= (i7 + measuredHeight) - (i4 / 2);
            }
        }
        return new Rect(i5, i6, i + i3 + dimensionPixelOffset, i2 + i4 + dimensionPixelOffset);
    }

    public Point getFloatingButtonTopCornerCoordinates() {
        return new Point(this.floatingButton.getLeft(), this.floatingButton.getTop());
    }

    private boolean isPhoneLandscape() {
        Resources resources = getContext().getResources();
        return resources.getBoolean(R.bool.isPhone) && resources.getConfiguration().orientation == 2;
    }

    private Point getFloatingButtonSize() {
        return new Point(this.floatingButton.getMeasuredWidth(), this.floatingButton.getMeasuredHeight());
    }

    private Point getFloatingButtonCenter() {
        Point floatingButtonSize = getFloatingButtonSize();
        Point floatingButtonTopCornerCoordinates = getFloatingButtonTopCornerCoordinates();
        return new Point(floatingButtonTopCornerCoordinates.x + ((int) ((float) (floatingButtonSize.x / 2))), floatingButtonTopCornerCoordinates.y + ((int) ((float) (floatingButtonSize.y / 2))));
    }
}
