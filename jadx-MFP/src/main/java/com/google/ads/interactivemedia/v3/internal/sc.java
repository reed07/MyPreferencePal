package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/* compiled from: IMASDK */
public final class sc extends FrameLayout {
    private final sd a;
    private float b;
    private int c;

    public sc(Context context) {
        this(context, null);
    }

    private sc(Context context, AttributeSet attributeSet) {
        super(context, null);
        this.c = 0;
        this.a = new sd(this, 0);
    }

    public final void a(float f) {
        if (this.b != f) {
            this.b = f;
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            super.onMeasure(r8, r9)
            float r8 = r7.b
            r9 = 0
            int r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r8 > 0) goto L_0x000b
            return
        L_0x000b:
            int r8 = r7.getMeasuredWidth()
            int r0 = r7.getMeasuredHeight()
            float r1 = (float) r8
            float r2 = (float) r0
            float r3 = r1 / r2
            float r4 = r7.b
            float r4 = r4 / r3
            r5 = 1065353216(0x3f800000, float:1.0)
            float r4 = r4 - r5
            float r5 = java.lang.Math.abs(r4)
            r6 = 1008981770(0x3c23d70a, float:0.01)
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 > 0) goto L_0x0031
            com.google.ads.interactivemedia.v3.internal.sd r8 = r7.a
            float r9 = r7.b
            r0 = 0
            r8.a(r9, r3, r0)
            return
        L_0x0031:
            int r5 = r7.c
            r6 = 4
            if (r5 == r6) goto L_0x004e
            switch(r5) {
                case 0: goto L_0x0045;
                case 1: goto L_0x0040;
                case 2: goto L_0x003a;
                default: goto L_0x0039;
            }
        L_0x0039:
            goto L_0x005c
        L_0x003a:
            float r8 = r7.b
            float r2 = r2 * r8
            int r8 = (int) r2
            goto L_0x005c
        L_0x0040:
            float r9 = r7.b
            float r1 = r1 / r9
            int r0 = (int) r1
            goto L_0x005c
        L_0x0045:
            int r9 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r9 <= 0) goto L_0x0057
            float r9 = r7.b
            float r1 = r1 / r9
            int r0 = (int) r1
            goto L_0x005c
        L_0x004e:
            int r9 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r9 > 0) goto L_0x0057
            float r9 = r7.b
            float r1 = r1 / r9
            int r0 = (int) r1
            goto L_0x005c
        L_0x0057:
            float r8 = r7.b
            float r2 = r2 * r8
            int r8 = (int) r2
        L_0x005c:
            com.google.ads.interactivemedia.v3.internal.sd r9 = r7.a
            float r1 = r7.b
            r2 = 1
            r9.a(r1, r3, r2)
            r9 = 1073741824(0x40000000, float:2.0)
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r9)
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r9)
            super.onMeasure(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.sc.onMeasure(int, int):void");
    }
}
