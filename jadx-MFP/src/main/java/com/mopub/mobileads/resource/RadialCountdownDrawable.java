package com.mopub.mobileads.resource;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Numbers;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;

public class RadialCountdownDrawable extends BaseWidgetDrawable {
    @NonNull
    private final Paint mArcPaint;
    @NonNull
    private final Paint mCirclePaint = new Paint();
    private int mInitialCountdownMilliseconds;
    private int mSecondsRemaining;
    private float mSweepAngle;
    @NonNull
    private final Paint mTextPaint;
    @NonNull
    private Rect mTextRect;

    public RadialCountdownDrawable(@NonNull Context context) {
        int dipsToIntPixels = Dips.dipsToIntPixels(3.0f, context);
        float dipsToFloatPixels = Dips.dipsToFloatPixels(18.0f, context);
        this.mCirclePaint.setColor(-1);
        this.mCirclePaint.setAlpha(128);
        this.mCirclePaint.setStyle(RadialCountdown.BACKGROUND_STYLE);
        float f = (float) dipsToIntPixels;
        this.mCirclePaint.setStrokeWidth(f);
        this.mCirclePaint.setAntiAlias(true);
        this.mArcPaint = new Paint();
        this.mArcPaint.setColor(-1);
        this.mArcPaint.setAlpha(255);
        this.mArcPaint.setStyle(RadialCountdown.PROGRESS_STYLE);
        this.mArcPaint.setStrokeWidth(f);
        this.mArcPaint.setAntiAlias(true);
        this.mTextPaint = new Paint();
        this.mTextPaint.setColor(-1);
        this.mTextPaint.setTextAlign(RadialCountdown.TEXT_ALIGN);
        this.mTextPaint.setTextSize(dipsToFloatPixels);
        this.mTextPaint.setAntiAlias(true);
        this.mTextRect = new Rect();
    }

    public void draw(Canvas canvas) {
        int centerX = getBounds().centerX();
        int centerY = getBounds().centerY();
        canvas.drawCircle((float) centerX, (float) centerY, (float) Math.min(centerX, centerY), this.mCirclePaint);
        drawTextWithinBounds(canvas, this.mTextPaint, this.mTextRect, String.valueOf(this.mSecondsRemaining));
        canvas.drawArc(new RectF(getBounds()), -90.0f, this.mSweepAngle, false, this.mArcPaint);
    }

    public void setInitialCountdown(int i) {
        this.mInitialCountdownMilliseconds = i;
    }

    public void updateCountdownProgress(int i) {
        this.mSecondsRemaining = (int) Numbers.convertMillisecondsToSecondsRoundedUp((long) (this.mInitialCountdownMilliseconds - i));
        this.mSweepAngle = (((float) i) * 360.0f) / ((float) this.mInitialCountdownMilliseconds);
        invalidateSelf();
    }

    @Deprecated
    @VisibleForTesting
    public int getInitialCountdownMilliseconds() {
        return this.mInitialCountdownMilliseconds;
    }
}
