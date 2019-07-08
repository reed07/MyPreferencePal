package com.myfitnesspal.feature.registration.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import com.myfitnesspal.android.R;

public class SplashScreenGradientView extends View {
    private static Paint DEFAULT_PAINT = null;
    private static final float LARGER_DIMENSION_SCALING = 4.5f;
    private static final float RADIUS_FACTOR = 2.5f;
    private static final float SMALLER_DIMENSION_SCALING = 1.8f;
    private static final float X_CENTER_FACTOR_PORTRAIT = 3.6f;
    private static final float X_DIMENSION_CENTER_FACTOR_LANDSCAPE = 8.6f;
    private static final float Y_CENTER_FACTOR_PORTRAIT = 12.0f;
    private static final float Y_DIMENSION_CENTER_FACTOR_LANDSCAPE = 5.0f;
    private Rect canvasBounds = new Rect();
    private Paint gradientPaint;
    private float horizontalScaling;
    private float verticalScaling;

    public SplashScreenGradientView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public SplashScreenGradientView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        float f;
        float f2;
        float f3;
        if (DEFAULT_PAINT == null) {
            DEFAULT_PAINT = new Paint();
            DEFAULT_PAINT.setColor(-16777216);
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        int[] iArr = {getResources().getColor(R.color.splash_gradient_color_light), getResources().getColor(R.color.splash_gradient_color_dark)};
        if (i2 > i) {
            f3 = X_CENTER_FACTOR_PORTRAIT;
            f2 = 12.0f;
            f = ((float) i) / RADIUS_FACTOR;
            this.horizontalScaling = SMALLER_DIMENSION_SCALING;
            this.verticalScaling = LARGER_DIMENSION_SCALING;
        } else {
            f3 = X_DIMENSION_CENTER_FACTOR_LANDSCAPE;
            f2 = Y_DIMENSION_CENTER_FACTOR_LANDSCAPE;
            f = ((float) i2) / RADIUS_FACTOR;
            this.horizontalScaling = LARGER_DIMENSION_SCALING;
            this.verticalScaling = SMALLER_DIMENSION_SCALING;
        }
        RadialGradient radialGradient = new RadialGradient((float) Math.round(((float) i) / f3), (float) Math.round(((float) i2) / f2), f, iArr, null, TileMode.CLAMP);
        this.gradientPaint = new Paint();
        this.gradientPaint.setDither(true);
        this.gradientPaint.setShader(radialGradient);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.getClipBounds(this.canvasBounds);
        canvas.drawRect(this.canvasBounds, DEFAULT_PAINT);
        canvas.scale(this.horizontalScaling, this.verticalScaling);
        canvas.drawPaint(this.gradientPaint);
    }
}
