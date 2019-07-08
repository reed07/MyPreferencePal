package com.facebook.ads.internal.view.component;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.annotation.Keep;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class CircularProgressView extends View {
    private final float a = (Resources.getSystem().getDisplayMetrics().density * 3.0f);
    private final RectF b = new RectF();
    private final Paint c = new Paint(1);
    private final Paint d;
    private float e = BitmapDescriptorFactory.HUE_RED;

    public CircularProgressView(Context context) {
        super(context);
        this.c.setStyle(Style.STROKE);
        this.c.setStrokeWidth(this.a);
        this.d = new Paint(1);
        this.d.setStyle(Style.STROKE);
        this.d.setStrokeWidth(this.a);
    }

    public void a(int i, int i2) {
        this.c.setColor(i);
        this.d.setColor(i2);
    }

    @Keep
    public float getProgress() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.b, BitmapDescriptorFactory.HUE_RED, 360.0f, false, this.c);
        canvas.drawArc(this.b, -90.0f, (this.e * 360.0f) / 100.0f, false, this.d);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int min = Math.min(getDefaultSize(getSuggestedMinimumHeight(), i2), getDefaultSize(getSuggestedMinimumWidth(), i));
        setMeasuredDimension(min, min);
        float f = (float) min;
        this.b.set((this.a / 2.0f) + BitmapDescriptorFactory.HUE_RED + ((float) getPaddingLeft()), (this.a / 2.0f) + BitmapDescriptorFactory.HUE_RED + ((float) getPaddingTop()), (f - (this.a / 2.0f)) - ((float) getPaddingRight()), (f - (this.a / 2.0f)) - ((float) getPaddingBottom()));
    }

    @Keep
    public void setProgress(float f) {
        this.e = Math.min(f, 100.0f);
        postInvalidate();
    }

    public void setProgressWithAnimation(float f) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "progress", new float[]{f});
        ofFloat.setDuration(400);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
    }
}
