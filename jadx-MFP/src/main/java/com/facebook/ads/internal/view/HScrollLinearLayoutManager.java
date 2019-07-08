package com.facebook.ads.internal.view;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import com.facebook.ads.internal.view.d.b;

public class HScrollLinearLayoutManager extends LinearLayoutManager {
    private final b a;
    private final com.facebook.ads.internal.view.d.a b;
    private final Context c;
    private int[] d;
    /* access modifiers changed from: private */
    public int e = 0;
    /* access modifiers changed from: private */
    public float f = 50.0f;
    private a g;
    private int h;

    private class a extends LinearSmoothScroller {
        public a(Context context) {
            super(context);
        }

        public int calculateDxToMakeVisible(View view, int i) {
            LayoutManager layoutManager = getLayoutManager();
            if (!layoutManager.canScrollHorizontally()) {
                return 0;
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            return calculateDtToFit(layoutManager.getDecoratedLeft(view) - layoutParams.leftMargin, layoutManager.getDecoratedRight(view) + layoutParams.rightMargin, layoutManager.getPaddingLeft(), layoutManager.getWidth() - layoutManager.getPaddingRight(), i) + HScrollLinearLayoutManager.this.e;
        }

        /* access modifiers changed from: protected */
        public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return HScrollLinearLayoutManager.this.f / ((float) displayMetrics.densityDpi);
        }

        public PointF computeScrollVectorForPosition(int i) {
            return HScrollLinearLayoutManager.this.computeScrollVectorForPosition(i);
        }

        /* access modifiers changed from: protected */
        public int getHorizontalSnapPreference() {
            return -1;
        }
    }

    public HScrollLinearLayoutManager(Context context, b bVar, com.facebook.ads.internal.view.d.a aVar) {
        super(context);
        this.c = context;
        this.a = bVar;
        this.b = aVar;
        this.h = -1;
        this.g = new a(this.c);
    }

    public void a(double d2) {
        if (d2 <= 0.0d) {
            d2 = 1.0d;
        }
        this.f = (float) (50.0d / d2);
        this.g = new a(this.c);
    }

    /* access modifiers changed from: 0000 */
    public void a(int i) {
        this.h = i;
    }

    public void b(int i) {
        this.e = i;
    }

    public void onMeasure(Recycler recycler, State state, int i, int i2) {
        int[] iArr;
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        if ((mode == 1073741824 && getOrientation() == 1) || (mode2 == 1073741824 && getOrientation() == 0)) {
            super.onMeasure(recycler, state, i, i2);
            return;
        }
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (this.b.b(this.h)) {
            iArr = this.b.a(this.h);
        } else {
            int[] iArr2 = {0, 0};
            if (state.getItemCount() >= 1) {
                int childCount = getChildCount() > 0 ? 1 : getChildCount();
                for (int i3 = 0; i3 < childCount; i3++) {
                    this.d = this.a.a(findViewByPosition(i3), MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
                    if (getOrientation() == 0) {
                        int i4 = iArr2[0];
                        int[] iArr3 = this.d;
                        iArr2[0] = i4 + iArr3[0];
                        if (i3 == 0) {
                            iArr2[1] = iArr3[1] + getPaddingTop() + getPaddingBottom();
                        }
                    } else {
                        int i5 = iArr2[1];
                        int[] iArr4 = this.d;
                        iArr2[1] = i5 + iArr4[1];
                        if (i3 == 0) {
                            iArr2[0] = iArr4[0] + getPaddingLeft() + getPaddingRight();
                        }
                    }
                }
                int i6 = this.h;
                if (i6 != -1) {
                    this.b.a(i6, iArr2);
                }
            }
            iArr = iArr2;
        }
        if (mode == 1073741824) {
            iArr[0] = size;
        }
        if (mode2 == 1073741824) {
            iArr[1] = size2;
        }
        setMeasuredDimension(iArr[0], iArr[1]);
    }

    public void scrollToPosition(int i) {
        super.scrollToPositionWithOffset(i, this.e);
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i) {
        this.g.setTargetPosition(i);
        startSmoothScroll(this.g);
    }
}
