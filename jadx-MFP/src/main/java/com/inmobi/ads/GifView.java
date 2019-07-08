package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import com.inmobi.ads.t.a;

public class GifView extends View implements a {
    private t a;
    private float b;
    private float c;
    private float d;
    private int e;
    private int f;
    private boolean g;

    public GifView(Context context) {
        this(context, null);
    }

    public GifView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = true;
        setLayerType(1, null);
    }

    public GifView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = true;
        setLayerType(1, null);
    }

    public void setPaused(boolean z) {
        this.a.a(z);
    }

    public void setGif(t tVar) {
        this.a = tVar;
        t tVar2 = this.a;
        if (tVar2 != null) {
            tVar2.a((a) this);
            this.a.a();
        }
        requestLayout();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r5, int r6) {
        /*
            r4 = this;
            com.inmobi.ads.t r0 = r4.a
            if (r0 == 0) goto L_0x0053
            int r0 = r0.b()
            com.inmobi.ads.t r1 = r4.a
            int r1 = r1.c()
            int r2 = android.view.View.MeasureSpec.getMode(r5)
            r3 = 1065353216(0x3f800000, float:1.0)
            if (r2 == 0) goto L_0x0021
            int r5 = android.view.View.MeasureSpec.getSize(r5)
            if (r0 <= r5) goto L_0x0021
            float r2 = (float) r0
            float r5 = (float) r5
            float r5 = r2 / r5
            goto L_0x0023
        L_0x0021:
            r5 = 1065353216(0x3f800000, float:1.0)
        L_0x0023:
            int r2 = android.view.View.MeasureSpec.getMode(r6)
            if (r2 == 0) goto L_0x0034
            int r6 = android.view.View.MeasureSpec.getSize(r6)
            if (r1 <= r6) goto L_0x0034
            float r2 = (float) r1
            float r6 = (float) r6
            float r6 = r2 / r6
            goto L_0x0036
        L_0x0034:
            r6 = 1065353216(0x3f800000, float:1.0)
        L_0x0036:
            float r5 = java.lang.Math.max(r5, r6)
            float r3 = r3 / r5
            r4.d = r3
            float r5 = (float) r0
            float r6 = r4.d
            float r5 = r5 * r6
            int r5 = (int) r5
            r4.e = r5
            float r5 = (float) r1
            float r5 = r5 * r6
            int r5 = (int) r5
            r4.f = r5
            int r5 = r4.e
            int r6 = r4.f
            r4.setMeasuredDimension(r5, r6)
            return
        L_0x0053:
            int r5 = r4.getSuggestedMinimumWidth()
            int r6 = r4.getSuggestedMinimumHeight()
            r4.setMeasuredDimension(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.GifView.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.b = ((float) (getWidth() - this.e)) / 2.0f;
        this.c = ((float) (getHeight() - this.f)) / 2.0f;
        this.g = getVisibility() == 0;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        t tVar = this.a;
        if (tVar != null) {
            if (tVar.d()) {
                this.a.e();
                a(canvas);
                b();
                return;
            }
            a(canvas);
        }
    }

    private void a(Canvas canvas) {
        canvas.save();
        float f2 = this.d;
        canvas.scale(f2, f2);
        t tVar = this.a;
        float f3 = this.b;
        float f4 = this.d;
        tVar.a(canvas, f3 / f4, this.c / f4);
        canvas.restore();
    }

    private void b() {
        if (this.g) {
            if (VERSION.SDK_INT >= 16) {
                postInvalidateOnAnimation();
                return;
            }
            invalidate();
        }
    }

    @SuppressLint({"NewApi"})
    public void onScreenStateChanged(int i) {
        super.onScreenStateChanged(i);
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        this.g = z;
        b();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
        this.g = i == 0;
        b();
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.g = i == 0;
        b();
    }

    public final void a() {
        invalidate();
    }
}
