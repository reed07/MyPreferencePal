package com.mopub.nativeads;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Drawables;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.VastVideoProgressBarWidget;
import com.mopub.mobileads.resource.CloseButtonDrawable;
import com.mopub.mobileads.resource.CtaButtonDrawable;
import com.mopub.mobileads.resource.DrawableConstants;
import com.mopub.mobileads.resource.DrawableConstants.GradientStrip;

public class NativeFullScreenVideoView extends RelativeLayout {
    @NonNull
    private final ImageView mBottomGradient;
    @NonNull
    private final ImageView mCachedVideoFrameView;
    @NonNull
    private final ImageView mCloseControl;
    @VisibleForTesting
    final int mCloseControlSizePx;
    @VisibleForTesting
    final int mClosePaddingPx;
    @NonNull
    private final ImageView mCtaButton;
    @VisibleForTesting
    final int mCtaHeightPx;
    @VisibleForTesting
    final int mCtaMarginPx;
    @VisibleForTesting
    final int mCtaWidthPx;
    @VisibleForTesting
    final int mGradientStripHeightPx;
    @NonNull
    private final ProgressBar mLoadingSpinner;
    @NonNull
    @VisibleForTesting
    Mode mMode;
    private int mOrientation;
    @NonNull
    private final View mOverlay;
    @NonNull
    private final ImageView mPlayButton;
    @VisibleForTesting
    final int mPlayControlSizePx;
    @NonNull
    private final ImageView mPrivacyInformationIcon;
    @VisibleForTesting
    final int mPrivacyInformationIconSizePx;
    @NonNull
    private final ImageView mTopGradient;
    @NonNull
    private final VastVideoProgressBarWidget mVideoProgress;
    @NonNull
    private final TextureView mVideoTexture;

    @VisibleForTesting
    static class LoadingBackground extends Drawable {
        @NonNull
        private final RectF mButtonRect;
        @VisibleForTesting
        final int mCornerRadiusPx;
        @NonNull
        private final Paint mPaint;

        public int getOpacity() {
            return 0;
        }

        public void setAlpha(int i) {
        }

        public void setColorFilter(ColorFilter colorFilter) {
        }

        LoadingBackground(@NonNull Context context) {
            this(context, new RectF(), new Paint());
        }

        LoadingBackground(@NonNull Context context, @NonNull RectF rectF, @NonNull Paint paint) {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(rectF);
            Preconditions.checkNotNull(paint);
            this.mButtonRect = rectF;
            this.mPaint = paint;
            this.mPaint.setColor(-16777216);
            this.mPaint.setAlpha(128);
            this.mPaint.setAntiAlias(true);
            this.mCornerRadiusPx = Dips.asIntPixels(5.0f, context);
        }

        public void draw(Canvas canvas) {
            this.mButtonRect.set(getBounds());
            RectF rectF = this.mButtonRect;
            int i = this.mCornerRadiusPx;
            canvas.drawRoundRect(rectF, (float) i, (float) i, this.mPaint);
        }
    }

    public enum Mode {
        LOADING,
        PLAYING,
        PAUSED,
        FINISHED
    }

    public NativeFullScreenVideoView(@NonNull Context context, int i, @Nullable String str) {
        Context context2 = context;
        this(context2, i, str, new ImageView(context2), new TextureView(context2), new ProgressBar(context2), new ImageView(context2), new ImageView(context2), new VastVideoProgressBarWidget(context2), new View(context2), new ImageView(context2), new ImageView(context2), new ImageView(context2), new ImageView(context2));
    }

    @VisibleForTesting
    NativeFullScreenVideoView(@NonNull Context context, int i, @Nullable String str, @NonNull ImageView imageView, @NonNull TextureView textureView, @NonNull ProgressBar progressBar, @NonNull ImageView imageView2, @NonNull ImageView imageView3, @NonNull VastVideoProgressBarWidget vastVideoProgressBarWidget, @NonNull View view, @NonNull ImageView imageView4, @NonNull ImageView imageView5, @NonNull ImageView imageView6, @NonNull ImageView imageView7) {
        ImageView imageView8;
        Context context2 = context;
        super(context);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(imageView);
        Preconditions.checkNotNull(textureView);
        Preconditions.checkNotNull(progressBar);
        Preconditions.checkNotNull(imageView2);
        Preconditions.checkNotNull(imageView3);
        Preconditions.checkNotNull(vastVideoProgressBarWidget);
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(imageView4);
        Preconditions.checkNotNull(imageView5);
        Preconditions.checkNotNull(imageView6);
        Preconditions.checkNotNull(imageView7);
        this.mOrientation = i;
        this.mMode = Mode.LOADING;
        this.mCtaWidthPx = Dips.asIntPixels(200.0f, context);
        this.mCtaHeightPx = Dips.asIntPixels(42.0f, context);
        this.mCtaMarginPx = Dips.asIntPixels(10.0f, context);
        this.mCloseControlSizePx = Dips.asIntPixels(50.0f, context);
        this.mClosePaddingPx = Dips.asIntPixels(8.0f, context);
        this.mPrivacyInformationIconSizePx = Dips.asIntPixels(44.0f, context);
        this.mPlayControlSizePx = Dips.asIntPixels(50.0f, context);
        this.mGradientStripHeightPx = Dips.asIntPixels(45.0f, context);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.mVideoTexture = textureView;
        this.mVideoTexture.setId((int) Utils.generateUniqueId());
        this.mVideoTexture.setLayoutParams(layoutParams);
        addView(this.mVideoTexture);
        this.mCachedVideoFrameView = imageView;
        this.mCachedVideoFrameView.setId((int) Utils.generateUniqueId());
        this.mCachedVideoFrameView.setLayoutParams(layoutParams);
        this.mCachedVideoFrameView.setBackgroundColor(0);
        addView(this.mCachedVideoFrameView);
        int i2 = this.mPlayControlSizePx;
        LayoutParams layoutParams2 = new LayoutParams(i2, i2);
        layoutParams2.addRule(13);
        this.mLoadingSpinner = progressBar;
        this.mLoadingSpinner.setId((int) Utils.generateUniqueId());
        this.mLoadingSpinner.setBackground(new LoadingBackground(context));
        this.mLoadingSpinner.setLayoutParams(layoutParams2);
        this.mLoadingSpinner.setIndeterminate(true);
        addView(this.mLoadingSpinner);
        LayoutParams layoutParams3 = new LayoutParams(-1, this.mGradientStripHeightPx);
        layoutParams3.addRule(8, this.mVideoTexture.getId());
        this.mBottomGradient = imageView2;
        this.mBottomGradient.setId((int) Utils.generateUniqueId());
        this.mBottomGradient.setLayoutParams(layoutParams3);
        this.mBottomGradient.setImageDrawable(new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{GradientStrip.START_COLOR, GradientStrip.END_COLOR}));
        addView(this.mBottomGradient);
        LayoutParams layoutParams4 = new LayoutParams(-1, this.mGradientStripHeightPx);
        layoutParams4.addRule(10);
        this.mTopGradient = imageView3;
        this.mTopGradient.setId((int) Utils.generateUniqueId());
        this.mTopGradient.setLayoutParams(layoutParams4);
        this.mTopGradient.setImageDrawable(new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{GradientStrip.START_COLOR, GradientStrip.END_COLOR}));
        addView(this.mTopGradient);
        this.mVideoProgress = vastVideoProgressBarWidget;
        this.mVideoProgress.setId((int) Utils.generateUniqueId());
        this.mVideoProgress.setAnchorId(this.mVideoTexture.getId());
        this.mVideoProgress.calibrateAndMakeVisible(1000, 0);
        addView(this.mVideoProgress);
        LayoutParams layoutParams5 = new LayoutParams(-1, -1);
        layoutParams5.addRule(13);
        this.mOverlay = view;
        this.mOverlay.setId((int) Utils.generateUniqueId());
        this.mOverlay.setLayoutParams(layoutParams5);
        this.mOverlay.setBackgroundColor(DrawableConstants.TRANSPARENT_GRAY);
        addView(this.mOverlay);
        int i3 = this.mPlayControlSizePx;
        LayoutParams layoutParams6 = new LayoutParams(i3, i3);
        layoutParams6.addRule(13);
        this.mPlayButton = imageView4;
        this.mPlayButton.setId((int) Utils.generateUniqueId());
        this.mPlayButton.setLayoutParams(layoutParams6);
        this.mPlayButton.setImageDrawable(Drawables.NATIVE_PLAY.createDrawable(context));
        addView(this.mPlayButton);
        this.mPrivacyInformationIcon = imageView5;
        this.mPrivacyInformationIcon.setId((int) Utils.generateUniqueId());
        ImageView imageView9 = this.mPrivacyInformationIcon;
        int i4 = this.mClosePaddingPx;
        imageView9.setPadding(i4, i4, i4 * 2, i4 * 2);
        addView(this.mPrivacyInformationIcon);
        CtaButtonDrawable ctaButtonDrawable = new CtaButtonDrawable(context);
        if (!TextUtils.isEmpty(str)) {
            String str2 = str;
            ctaButtonDrawable.setCtaText(str);
            imageView8 = imageView6;
        } else {
            imageView8 = imageView6;
        }
        this.mCtaButton = imageView8;
        this.mCtaButton.setId((int) Utils.generateUniqueId());
        this.mCtaButton.setImageDrawable(ctaButtonDrawable);
        addView(this.mCtaButton);
        this.mCloseControl = imageView7;
        this.mCloseControl.setId((int) Utils.generateUniqueId());
        this.mCloseControl.setImageDrawable(new CloseButtonDrawable());
        ImageView imageView10 = this.mCloseControl;
        int i5 = this.mClosePaddingPx;
        imageView10.setPadding(i5 * 3, i5, i5, i5 * 3);
        addView(this.mCloseControl);
        updateViewState();
    }

    public void resetProgress() {
        this.mVideoProgress.reset();
    }

    public void setMode(@NonNull Mode mode) {
        Preconditions.checkNotNull(mode);
        if (this.mMode != mode) {
            this.mMode = mode;
            updateViewState();
        }
    }

    @NonNull
    public TextureView getTextureView() {
        return this.mVideoTexture;
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            updateViewState();
        }
    }

    public void setSurfaceTextureListener(@Nullable SurfaceTextureListener surfaceTextureListener) {
        this.mVideoTexture.setSurfaceTextureListener(surfaceTextureListener);
        SurfaceTexture surfaceTexture = this.mVideoTexture.getSurfaceTexture();
        if (surfaceTexture != null && surfaceTextureListener != null) {
            surfaceTextureListener.onSurfaceTextureAvailable(surfaceTexture, this.mVideoTexture.getWidth(), this.mVideoTexture.getHeight());
        }
    }

    public void setCloseControlListener(@Nullable OnClickListener onClickListener) {
        this.mCloseControl.setOnClickListener(onClickListener);
    }

    public void setPrivacyInformationClickListener(@Nullable OnClickListener onClickListener) {
        this.mPrivacyInformationIcon.setOnClickListener(onClickListener);
    }

    public void setPrivacyInformationIconImageUrl(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            this.mPrivacyInformationIcon.setImageDrawable(Drawables.NATIVE_PRIVACY_INFORMATION_ICON.createDrawable(this.mPrivacyInformationIcon.getContext()));
        } else {
            NativeImageHelper.loadImageView(str, this.mPrivacyInformationIcon);
        }
    }

    public void setCtaClickListener(@Nullable OnClickListener onClickListener) {
        this.mCtaButton.setOnClickListener(onClickListener);
    }

    public void setPlayControlClickListener(@Nullable OnClickListener onClickListener) {
        this.mPlayButton.setOnClickListener(onClickListener);
        this.mOverlay.setOnClickListener(onClickListener);
    }

    public void updateProgress(int i) {
        this.mVideoProgress.updateProgress(i);
    }

    public void setCachedVideoFrame(@Nullable Bitmap bitmap) {
        this.mCachedVideoFrameView.setImageBitmap(bitmap);
    }

    private void updateViewState() {
        switch (this.mMode) {
            case LOADING:
                setCachedImageVisibility(0);
                setLoadingSpinnerVisibility(0);
                setVideoProgressVisibility(4);
                setPlayButtonVisibility(4);
                break;
            case PLAYING:
                setCachedImageVisibility(4);
                setLoadingSpinnerVisibility(4);
                setVideoProgressVisibility(0);
                setPlayButtonVisibility(4);
                break;
            case PAUSED:
                setCachedImageVisibility(4);
                setLoadingSpinnerVisibility(4);
                setVideoProgressVisibility(0);
                setPlayButtonVisibility(0);
                break;
            case FINISHED:
                setCachedImageVisibility(0);
                setLoadingSpinnerVisibility(4);
                setVideoProgressVisibility(4);
                setPlayButtonVisibility(0);
                break;
        }
        updateVideoTextureLayout();
        updateControlLayouts();
    }

    private void setCachedImageVisibility(int i) {
        this.mCachedVideoFrameView.setVisibility(i);
    }

    private void setLoadingSpinnerVisibility(int i) {
        this.mLoadingSpinner.setVisibility(i);
    }

    private void setVideoProgressVisibility(int i) {
        this.mVideoProgress.setVisibility(i);
    }

    private void setPlayButtonVisibility(int i) {
        this.mPlayButton.setVisibility(i);
        this.mOverlay.setVisibility(i);
    }

    private void updateVideoTextureLayout() {
        Configuration configuration = getContext().getResources().getConfiguration();
        ViewGroup.LayoutParams layoutParams = this.mVideoTexture.getLayoutParams();
        int dipsToIntPixels = Dips.dipsToIntPixels((float) configuration.screenWidthDp, getContext());
        if (dipsToIntPixels != layoutParams.width) {
            layoutParams.width = dipsToIntPixels;
        }
        int dipsToIntPixels2 = Dips.dipsToIntPixels((((float) configuration.screenWidthDp) * 9.0f) / 16.0f, getContext());
        if (dipsToIntPixels2 != layoutParams.height) {
            layoutParams.height = dipsToIntPixels2;
        }
    }

    private void updateControlLayouts() {
        LayoutParams layoutParams = new LayoutParams(this.mCtaWidthPx, this.mCtaHeightPx);
        int i = this.mCtaMarginPx;
        layoutParams.setMargins(i, i, i, i);
        int i2 = this.mPrivacyInformationIconSizePx;
        LayoutParams layoutParams2 = new LayoutParams(i2, i2);
        int i3 = this.mCloseControlSizePx;
        LayoutParams layoutParams3 = new LayoutParams(i3, i3);
        switch (this.mOrientation) {
            case 1:
                layoutParams.addRule(3, this.mVideoTexture.getId());
                layoutParams.addRule(14);
                layoutParams2.addRule(10);
                layoutParams2.addRule(9);
                layoutParams3.addRule(10);
                layoutParams3.addRule(11);
                break;
            case 2:
                layoutParams.addRule(2, this.mVideoProgress.getId());
                layoutParams.addRule(11);
                layoutParams2.addRule(6, this.mVideoTexture.getId());
                layoutParams2.addRule(5, this.mVideoTexture.getId());
                layoutParams3.addRule(6, this.mVideoTexture.getId());
                layoutParams3.addRule(7, this.mVideoTexture.getId());
                break;
        }
        this.mCtaButton.setLayoutParams(layoutParams);
        this.mPrivacyInformationIcon.setLayoutParams(layoutParams2);
        this.mCloseControl.setLayoutParams(layoutParams3);
    }

    /* access modifiers changed from: 0000 */
    @Deprecated
    @VisibleForTesting
    public ImageView getCtaButton() {
        return this.mCtaButton;
    }
}
