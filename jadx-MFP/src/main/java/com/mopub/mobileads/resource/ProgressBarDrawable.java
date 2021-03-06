package com.mopub.mobileads.resource;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.mobileads.resource.DrawableConstants.ProgressBar;

public class ProgressBarDrawable extends BaseWidgetDrawable {
    @NonNull
    private final Paint mBackgroundPaint = new Paint();
    private int mCurrentProgress;
    private int mDuration;
    private int mLastProgress;
    private final int mNuggetWidth;
    @NonNull
    private final Paint mProgressPaint;
    private int mSkipOffset;
    private float mSkipRatio;

    public ProgressBarDrawable(@NonNull Context context) {
        this.mBackgroundPaint.setColor(-1);
        this.mBackgroundPaint.setAlpha(128);
        this.mBackgroundPaint.setStyle(ProgressBar.BACKGROUND_STYLE);
        this.mBackgroundPaint.setAntiAlias(true);
        this.mProgressPaint = new Paint();
        this.mProgressPaint.setColor(ProgressBar.PROGRESS_COLOR);
        this.mProgressPaint.setAlpha(255);
        this.mProgressPaint.setStyle(ProgressBar.PROGRESS_STYLE);
        this.mProgressPaint.setAntiAlias(true);
        this.mNuggetWidth = Dips.dipsToIntPixels(4.0f, context);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(getBounds(), this.mBackgroundPaint);
        Canvas canvas2 = canvas;
        canvas2.drawRect((float) getBounds().left, (float) getBounds().top, ((float) getBounds().right) * (((float) this.mCurrentProgress) / ((float) this.mDuration)), (float) getBounds().bottom, this.mProgressPaint);
        int i = this.mSkipOffset;
        if (i > 0 && i < this.mDuration) {
            float f = ((float) getBounds().right) * this.mSkipRatio;
            canvas.drawRect(f, (float) getBounds().top, f + ((float) this.mNuggetWidth), (float) getBounds().bottom, this.mProgressPaint);
        }
    }

    public void reset() {
        this.mLastProgress = 0;
    }

    public void setDurationAndSkipOffset(int i, int i2) {
        this.mDuration = i;
        this.mSkipOffset = i2;
        this.mSkipRatio = ((float) this.mSkipOffset) / ((float) this.mDuration);
    }

    public void setProgress(int i) {
        int i2 = this.mLastProgress;
        if (i >= i2) {
            this.mCurrentProgress = i;
            this.mLastProgress = i;
        } else if (i != 0) {
            MoPubLog.d(String.format("Progress not monotonically increasing: last = %d, current = %d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i)}));
            forceCompletion();
        }
        invalidateSelf();
    }

    @VisibleForTesting
    public void forceCompletion() {
        this.mCurrentProgress = this.mDuration;
    }

    @Deprecated
    @VisibleForTesting
    public float getSkipRatio() {
        return this.mSkipRatio;
    }

    @Deprecated
    @VisibleForTesting
    public int getCurrentProgress() {
        return this.mCurrentProgress;
    }
}
