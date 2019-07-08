package android.support.v4.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v4.util.Preconditions;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CircularProgressDrawable extends Drawable implements Animatable {
    private static final int[] COLORS = {-16777216};
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static final Interpolator MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
    private Animator mAnimator;
    boolean mFinishing;
    private Resources mResources;
    private final Ring mRing = new Ring();
    private float mRotation;
    float mRotationCount;

    @RestrictTo
    @Retention(RetentionPolicy.SOURCE)
    public @interface ProgressDrawableSize {
    }

    private static class Ring {
        int mAlpha = 255;
        Path mArrow;
        int mArrowHeight;
        final Paint mArrowPaint = new Paint();
        float mArrowScale = 1.0f;
        int mArrowWidth;
        final Paint mCirclePaint = new Paint();
        int mColorIndex;
        int[] mColors;
        int mCurrentColor;
        float mEndTrim = BitmapDescriptorFactory.HUE_RED;
        final Paint mPaint = new Paint();
        float mRingCenterRadius;
        float mRotation = BitmapDescriptorFactory.HUE_RED;
        boolean mShowArrow;
        float mStartTrim = BitmapDescriptorFactory.HUE_RED;
        float mStartingEndTrim;
        float mStartingRotation;
        float mStartingStartTrim;
        float mStrokeWidth = 5.0f;
        final RectF mTempBounds = new RectF();

        Ring() {
            this.mPaint.setStrokeCap(Cap.SQUARE);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Style.STROKE);
            this.mArrowPaint.setStyle(Style.FILL);
            this.mArrowPaint.setAntiAlias(true);
            this.mCirclePaint.setColor(0);
        }

        /* access modifiers changed from: 0000 */
        public void setArrowDimensions(float f, float f2) {
            this.mArrowWidth = (int) f;
            this.mArrowHeight = (int) f2;
        }

        /* access modifiers changed from: 0000 */
        public void draw(Canvas canvas, Rect rect) {
            RectF rectF = this.mTempBounds;
            float f = this.mRingCenterRadius;
            float f2 = (this.mStrokeWidth / 2.0f) + f;
            if (f <= BitmapDescriptorFactory.HUE_RED) {
                f2 = (((float) Math.min(rect.width(), rect.height())) / 2.0f) - Math.max((((float) this.mArrowWidth) * this.mArrowScale) / 2.0f, this.mStrokeWidth / 2.0f);
            }
            rectF.set(((float) rect.centerX()) - f2, ((float) rect.centerY()) - f2, ((float) rect.centerX()) + f2, ((float) rect.centerY()) + f2);
            float f3 = this.mStartTrim;
            float f4 = this.mRotation;
            float f5 = (f3 + f4) * 360.0f;
            float f6 = ((this.mEndTrim + f4) * 360.0f) - f5;
            this.mPaint.setColor(this.mCurrentColor);
            this.mPaint.setAlpha(this.mAlpha);
            float f7 = this.mStrokeWidth / 2.0f;
            rectF.inset(f7, f7);
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, this.mCirclePaint);
            float f8 = -f7;
            rectF.inset(f8, f8);
            canvas.drawArc(rectF, f5, f6, false, this.mPaint);
            drawTriangle(canvas, f5, f6, rectF);
        }

        /* access modifiers changed from: 0000 */
        public void drawTriangle(Canvas canvas, float f, float f2, RectF rectF) {
            if (this.mShowArrow) {
                Path path = this.mArrow;
                if (path == null) {
                    this.mArrow = new Path();
                    this.mArrow.setFillType(FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                float min = Math.min(rectF.width(), rectF.height()) / 2.0f;
                float f3 = (((float) this.mArrowWidth) * this.mArrowScale) / 2.0f;
                this.mArrow.moveTo(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
                this.mArrow.lineTo(((float) this.mArrowWidth) * this.mArrowScale, BitmapDescriptorFactory.HUE_RED);
                Path path2 = this.mArrow;
                float f4 = (float) this.mArrowWidth;
                float f5 = this.mArrowScale;
                path2.lineTo((f4 * f5) / 2.0f, ((float) this.mArrowHeight) * f5);
                this.mArrow.offset((min + rectF.centerX()) - f3, rectF.centerY() + (this.mStrokeWidth / 2.0f));
                this.mArrow.close();
                this.mArrowPaint.setColor(this.mCurrentColor);
                this.mArrowPaint.setAlpha(this.mAlpha);
                canvas.save();
                canvas.rotate(f + f2, rectF.centerX(), rectF.centerY());
                canvas.drawPath(this.mArrow, this.mArrowPaint);
                canvas.restore();
            }
        }

        /* access modifiers changed from: 0000 */
        public void setColors(@NonNull int[] iArr) {
            this.mColors = iArr;
            setColorIndex(0);
        }

        /* access modifiers changed from: 0000 */
        public void setColor(int i) {
            this.mCurrentColor = i;
        }

        /* access modifiers changed from: 0000 */
        public void setColorIndex(int i) {
            this.mColorIndex = i;
            this.mCurrentColor = this.mColors[this.mColorIndex];
        }

        /* access modifiers changed from: 0000 */
        public int getNextColor() {
            return this.mColors[getNextColorIndex()];
        }

        /* access modifiers changed from: 0000 */
        public int getNextColorIndex() {
            return (this.mColorIndex + 1) % this.mColors.length;
        }

        /* access modifiers changed from: 0000 */
        public void goToNextColor() {
            setColorIndex(getNextColorIndex());
        }

        /* access modifiers changed from: 0000 */
        public void setColorFilter(ColorFilter colorFilter) {
            this.mPaint.setColorFilter(colorFilter);
        }

        /* access modifiers changed from: 0000 */
        public void setAlpha(int i) {
            this.mAlpha = i;
        }

        /* access modifiers changed from: 0000 */
        public int getAlpha() {
            return this.mAlpha;
        }

        /* access modifiers changed from: 0000 */
        public void setStrokeWidth(float f) {
            this.mStrokeWidth = f;
            this.mPaint.setStrokeWidth(f);
        }

        /* access modifiers changed from: 0000 */
        public void setStartTrim(float f) {
            this.mStartTrim = f;
        }

        /* access modifiers changed from: 0000 */
        public float getStartTrim() {
            return this.mStartTrim;
        }

        /* access modifiers changed from: 0000 */
        public float getStartingStartTrim() {
            return this.mStartingStartTrim;
        }

        /* access modifiers changed from: 0000 */
        public float getStartingEndTrim() {
            return this.mStartingEndTrim;
        }

        /* access modifiers changed from: 0000 */
        public int getStartingColor() {
            return this.mColors[this.mColorIndex];
        }

        /* access modifiers changed from: 0000 */
        public void setEndTrim(float f) {
            this.mEndTrim = f;
        }

        /* access modifiers changed from: 0000 */
        public float getEndTrim() {
            return this.mEndTrim;
        }

        /* access modifiers changed from: 0000 */
        public void setRotation(float f) {
            this.mRotation = f;
        }

        /* access modifiers changed from: 0000 */
        public void setCenterRadius(float f) {
            this.mRingCenterRadius = f;
        }

        /* access modifiers changed from: 0000 */
        public void setShowArrow(boolean z) {
            if (this.mShowArrow != z) {
                this.mShowArrow = z;
            }
        }

        /* access modifiers changed from: 0000 */
        public void setArrowScale(float f) {
            if (f != this.mArrowScale) {
                this.mArrowScale = f;
            }
        }

        /* access modifiers changed from: 0000 */
        public float getStartingRotation() {
            return this.mStartingRotation;
        }

        /* access modifiers changed from: 0000 */
        public void storeOriginals() {
            this.mStartingStartTrim = this.mStartTrim;
            this.mStartingEndTrim = this.mEndTrim;
            this.mStartingRotation = this.mRotation;
        }

        /* access modifiers changed from: 0000 */
        public void resetOriginals() {
            this.mStartingStartTrim = BitmapDescriptorFactory.HUE_RED;
            this.mStartingEndTrim = BitmapDescriptorFactory.HUE_RED;
            this.mStartingRotation = BitmapDescriptorFactory.HUE_RED;
            setStartTrim(BitmapDescriptorFactory.HUE_RED);
            setEndTrim(BitmapDescriptorFactory.HUE_RED);
            setRotation(BitmapDescriptorFactory.HUE_RED);
        }
    }

    private int evaluateColorChange(float f, int i, int i2) {
        int i3 = (i >> 24) & 255;
        int i4 = (i >> 16) & 255;
        int i5 = (i >> 8) & 255;
        int i6 = i & 255;
        return ((i3 + ((int) (((float) (((i2 >> 24) & 255) - i3)) * f))) << 24) | ((i4 + ((int) (((float) (((i2 >> 16) & 255) - i4)) * f))) << 16) | ((i5 + ((int) (((float) (((i2 >> 8) & 255) - i5)) * f))) << 8) | (i6 + ((int) (f * ((float) ((i2 & 255) - i6)))));
    }

    public int getOpacity() {
        return -3;
    }

    public CircularProgressDrawable(@NonNull Context context) {
        this.mResources = ((Context) Preconditions.checkNotNull(context)).getResources();
        this.mRing.setColors(COLORS);
        setStrokeWidth(2.5f);
        setupAnimators();
    }

    private void setSizeParameters(float f, float f2, float f3, float f4) {
        Ring ring = this.mRing;
        float f5 = this.mResources.getDisplayMetrics().density;
        ring.setStrokeWidth(f2 * f5);
        ring.setCenterRadius(f * f5);
        ring.setColorIndex(0);
        ring.setArrowDimensions(f3 * f5, f4 * f5);
    }

    public void setStyle(int i) {
        if (i == 0) {
            setSizeParameters(11.0f, 3.0f, 12.0f, 6.0f);
        } else {
            setSizeParameters(7.5f, 2.5f, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    public void setStrokeWidth(float f) {
        this.mRing.setStrokeWidth(f);
        invalidateSelf();
    }

    public void setArrowEnabled(boolean z) {
        this.mRing.setShowArrow(z);
        invalidateSelf();
    }

    public void setArrowScale(float f) {
        this.mRing.setArrowScale(f);
        invalidateSelf();
    }

    public void setStartEndTrim(float f, float f2) {
        this.mRing.setStartTrim(f);
        this.mRing.setEndTrim(f2);
        invalidateSelf();
    }

    public void setProgressRotation(float f) {
        this.mRing.setRotation(f);
        invalidateSelf();
    }

    public void setColorSchemeColors(@NonNull int... iArr) {
        this.mRing.setColors(iArr);
        this.mRing.setColorIndex(0);
        invalidateSelf();
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.mRotation, bounds.exactCenterX(), bounds.exactCenterY());
        this.mRing.draw(canvas, bounds);
        canvas.restore();
    }

    public void setAlpha(int i) {
        this.mRing.setAlpha(i);
        invalidateSelf();
    }

    public int getAlpha() {
        return this.mRing.getAlpha();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mRing.setColorFilter(colorFilter);
        invalidateSelf();
    }

    private void setRotation(float f) {
        this.mRotation = f;
    }

    public boolean isRunning() {
        return this.mAnimator.isRunning();
    }

    public void start() {
        this.mAnimator.cancel();
        this.mRing.storeOriginals();
        if (this.mRing.getEndTrim() != this.mRing.getStartTrim()) {
            this.mFinishing = true;
            this.mAnimator.setDuration(666);
            this.mAnimator.start();
            return;
        }
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
        this.mAnimator.setDuration(1332);
        this.mAnimator.start();
    }

    public void stop() {
        this.mAnimator.cancel();
        setRotation(BitmapDescriptorFactory.HUE_RED);
        this.mRing.setShowArrow(false);
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
        invalidateSelf();
    }

    /* access modifiers changed from: 0000 */
    public void updateRingColor(float f, Ring ring) {
        if (f > 0.75f) {
            ring.setColor(evaluateColorChange((f - 0.75f) / 0.25f, ring.getStartingColor(), ring.getNextColor()));
        } else {
            ring.setColor(ring.getStartingColor());
        }
    }

    private void applyFinishTranslation(float f, Ring ring) {
        updateRingColor(f, ring);
        float floor = (float) (Math.floor((double) (ring.getStartingRotation() / 0.8f)) + 1.0d);
        ring.setStartTrim(ring.getStartingStartTrim() + (((ring.getStartingEndTrim() - 0.01f) - ring.getStartingStartTrim()) * f));
        ring.setEndTrim(ring.getStartingEndTrim());
        ring.setRotation(ring.getStartingRotation() + ((floor - ring.getStartingRotation()) * f));
    }

    /* access modifiers changed from: 0000 */
    public void applyTransformation(float f, Ring ring, boolean z) {
        float f2;
        float f3;
        if (this.mFinishing) {
            applyFinishTranslation(f, ring);
        } else if (f != 1.0f || z) {
            float startingRotation = ring.getStartingRotation();
            if (f < 0.5f) {
                float f4 = f / 0.5f;
                float startingStartTrim = ring.getStartingStartTrim();
                float f5 = startingStartTrim;
                f2 = (MATERIAL_INTERPOLATOR.getInterpolation(f4) * 0.79f) + 0.01f + startingStartTrim;
                f3 = f5;
            } else {
                f2 = ring.getStartingStartTrim() + 0.79f;
                f3 = f2 - (((1.0f - MATERIAL_INTERPOLATOR.getInterpolation((f - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
            }
            float f6 = startingRotation + (0.20999998f * f);
            float f7 = (f + this.mRotationCount) * 216.0f;
            ring.setStartTrim(f3);
            ring.setEndTrim(f2);
            ring.setRotation(f6);
            setRotation(f7);
        }
    }

    private void setupAnimators() {
        final Ring ring = this.mRing;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{BitmapDescriptorFactory.HUE_RED, 1.0f});
        ofFloat.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                CircularProgressDrawable.this.updateRingColor(floatValue, ring);
                CircularProgressDrawable.this.applyTransformation(floatValue, ring, false);
                CircularProgressDrawable.this.invalidateSelf();
            }
        });
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(LINEAR_INTERPOLATOR);
        ofFloat.addListener(new AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                CircularProgressDrawable.this.mRotationCount = BitmapDescriptorFactory.HUE_RED;
            }

            public void onAnimationRepeat(Animator animator) {
                CircularProgressDrawable.this.applyTransformation(1.0f, ring, true);
                ring.storeOriginals();
                ring.goToNextColor();
                if (CircularProgressDrawable.this.mFinishing) {
                    CircularProgressDrawable.this.mFinishing = false;
                    animator.cancel();
                    animator.setDuration(1332);
                    animator.start();
                    ring.setShowArrow(false);
                    return;
                }
                CircularProgressDrawable.this.mRotationCount += 1.0f;
            }
        });
        this.mAnimator = ofFloat;
    }
}
