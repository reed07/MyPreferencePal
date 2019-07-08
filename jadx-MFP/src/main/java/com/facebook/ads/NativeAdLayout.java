package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.facebook.ads.internal.view.a.c;
import com.facebook.ads.internal.w.b.x;

public class NativeAdLayout extends FrameLayout {
    private View a;
    private int b = 0;
    private int c = 0;

    public NativeAdLayout(Context context) {
        super(context);
    }

    public NativeAdLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NativeAdLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void clearAdReportingLayout() {
        x.a((ViewGroup) this);
        removeView(this.a);
        this.a = null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        if (r1 > r2) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r1, int r2) {
        /*
            r0 = this;
            super.onMeasure(r1, r2)
            int r1 = r0.c
            if (r1 <= 0) goto L_0x0010
            int r1 = r0.getMeasuredWidth()
            int r2 = r0.c
            if (r1 <= r2) goto L_0x0010
            goto L_0x0018
        L_0x0010:
            int r1 = r0.getMeasuredWidth()
            int r2 = r0.b
            if (r1 >= r2) goto L_0x001f
        L_0x0018:
            int r1 = r0.getMeasuredHeight()
            r0.setMeasuredDimension(r2, r1)
        L_0x001f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.NativeAdLayout.onMeasure(int, int):void");
    }

    public void setAdReportingLayout(c cVar) {
        this.a = cVar;
        this.a.setLayoutParams(new LayoutParams(-1, -1));
        x.a((ViewGroup) this);
        addView(this.a);
    }

    public void setMaxWidth(int i) {
        this.c = i;
    }

    public void setMinWidth(int i) {
        this.b = i;
    }
}
