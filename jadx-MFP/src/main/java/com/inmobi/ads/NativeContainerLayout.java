package com.inmobi.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class NativeContainerLayout extends ViewGroup {
    private static final String a = "NativeContainerLayout";

    public static class a extends LayoutParams {
        public int a;
        public int b;

        public a(int i, int i2) {
            super(i, i2);
        }

        public a(LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public NativeContainerLayout(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        measureChildren(i, i2);
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                a aVar = (a) childAt.getLayoutParams();
                int measuredWidth = aVar.a + childAt.getMeasuredWidth();
                int measuredHeight = aVar.b + childAt.getMeasuredHeight();
                i4 = Math.max(i4, measuredWidth);
                i3 = Math.max(i3, measuredHeight);
            }
        }
        setMeasuredDimension(resolveSize(Math.max(i4, getSuggestedMinimumWidth()), i), resolveSize(Math.max(i3, getSuggestedMinimumHeight()), i2));
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new a(-2, -2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                a aVar = (a) childAt.getLayoutParams();
                childAt.layout(aVar.a, aVar.b, aVar.a + childAt.getMeasuredWidth(), aVar.b + childAt.getMeasuredHeight());
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return new a(layoutParams);
    }
}
