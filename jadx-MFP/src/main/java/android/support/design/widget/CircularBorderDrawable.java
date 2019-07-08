package android.support.design.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.ColorUtils;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

@RestrictTo
public class CircularBorderDrawable extends Drawable {
    private static final float DRAW_STROKE_WIDTH_MULTIPLE = 1.3333f;
    private ColorStateList borderTint;
    @Dimension
    float borderWidth;
    @ColorInt
    private int bottomInnerStrokeColor;
    @ColorInt
    private int bottomOuterStrokeColor;
    @ColorInt
    private int currentBorderTintColor;
    private boolean invalidateShader = true;
    final Paint paint = new Paint(1);
    final Rect rect = new Rect();
    final RectF rectF = new RectF();
    @FloatRange
    private float rotation;
    final CircularBorderState state = new CircularBorderState();
    @ColorInt
    private int topInnerStrokeColor;
    @ColorInt
    private int topOuterStrokeColor;

    private class CircularBorderState extends ConstantState {
        public int getChangingConfigurations() {
            return 0;
        }

        private CircularBorderState() {
        }

        @NonNull
        public Drawable newDrawable() {
            return CircularBorderDrawable.this;
        }
    }

    public CircularBorderDrawable() {
        this.paint.setStyle(Style.STROKE);
    }

    @Nullable
    public ConstantState getConstantState() {
        return this.state;
    }

    public void setGradientColors(@ColorInt int i, @ColorInt int i2, @ColorInt int i3, @ColorInt int i4) {
        this.topOuterStrokeColor = i;
        this.topInnerStrokeColor = i2;
        this.bottomOuterStrokeColor = i3;
        this.bottomInnerStrokeColor = i4;
    }

    public void setBorderWidth(@Dimension float f) {
        if (this.borderWidth != f) {
            this.borderWidth = f;
            this.paint.setStrokeWidth(f * DRAW_STROKE_WIDTH_MULTIPLE);
            this.invalidateShader = true;
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        if (this.invalidateShader) {
            this.paint.setShader(createGradientShader());
            this.invalidateShader = false;
        }
        float strokeWidth = this.paint.getStrokeWidth() / 2.0f;
        RectF rectF2 = this.rectF;
        copyBounds(this.rect);
        rectF2.set(this.rect);
        rectF2.left += strokeWidth;
        rectF2.top += strokeWidth;
        rectF2.right -= strokeWidth;
        rectF2.bottom -= strokeWidth;
        canvas.save();
        canvas.rotate(this.rotation, rectF2.centerX(), rectF2.centerY());
        canvas.drawOval(rectF2, this.paint);
        canvas.restore();
    }

    public boolean getPadding(Rect rect2) {
        int round = Math.round(this.borderWidth);
        rect2.set(round, round, round, round);
        return true;
    }

    public void setAlpha(@IntRange int i) {
        this.paint.setAlpha(i);
        invalidateSelf();
    }

    public void setBorderTint(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.currentBorderTintColor = colorStateList.getColorForState(getState(), this.currentBorderTintColor);
        }
        this.borderTint = colorStateList;
        this.invalidateShader = true;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public int getOpacity() {
        return this.borderWidth > BitmapDescriptorFactory.HUE_RED ? -3 : -2;
    }

    public final void setRotation(float f) {
        if (f != this.rotation) {
            this.rotation = f;
            invalidateSelf();
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect2) {
        this.invalidateShader = true;
    }

    public boolean isStateful() {
        ColorStateList colorStateList = this.borderTint;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        ColorStateList colorStateList = this.borderTint;
        if (colorStateList != null) {
            int colorForState = colorStateList.getColorForState(iArr, this.currentBorderTintColor);
            if (colorForState != this.currentBorderTintColor) {
                this.invalidateShader = true;
                this.currentBorderTintColor = colorForState;
            }
        }
        if (this.invalidateShader) {
            invalidateSelf();
        }
        return this.invalidateShader;
    }

    private Shader createGradientShader() {
        Rect rect2 = this.rect;
        copyBounds(rect2);
        float height = this.borderWidth / ((float) rect2.height());
        LinearGradient linearGradient = new LinearGradient(BitmapDescriptorFactory.HUE_RED, (float) rect2.top, BitmapDescriptorFactory.HUE_RED, (float) rect2.bottom, new int[]{ColorUtils.compositeColors(this.topOuterStrokeColor, this.currentBorderTintColor), ColorUtils.compositeColors(this.topInnerStrokeColor, this.currentBorderTintColor), ColorUtils.compositeColors(ColorUtils.setAlphaComponent(this.topInnerStrokeColor, 0), this.currentBorderTintColor), ColorUtils.compositeColors(ColorUtils.setAlphaComponent(this.bottomInnerStrokeColor, 0), this.currentBorderTintColor), ColorUtils.compositeColors(this.bottomInnerStrokeColor, this.currentBorderTintColor), ColorUtils.compositeColors(this.bottomOuterStrokeColor, this.currentBorderTintColor)}, new float[]{0.0f, height, 0.5f, 0.5f, 1.0f - height, 1.0f}, TileMode.CLAMP);
        return linearGradient;
    }
}
