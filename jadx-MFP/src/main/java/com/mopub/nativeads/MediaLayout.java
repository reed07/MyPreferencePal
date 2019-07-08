package com.mopub.nativeads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Drawables;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.VastVideoProgressBarWidget;
import com.mopub.mobileads.resource.DrawableConstants.GradientStrip;

public class MediaLayout extends RelativeLayout {
    @Nullable
    private ImageView mBottomGradient;
    private final int mControlSizePx;
    private final int mGradientStripHeightPx;
    private boolean mIsInitialized;
    @Nullable
    private ProgressBar mLoadingSpinner;
    @NonNull
    private ImageView mMainImageView;
    @NonNull
    private volatile Mode mMode;
    @Nullable
    private ImageView mMuteControl;
    private final int mMuteSizePx;
    @NonNull
    private MuteState mMuteState;
    @Nullable
    private Drawable mMutedDrawable;
    @Nullable
    private View mOverlay;
    private final int mPaddingPx;
    @Nullable
    private ImageView mPlayButton;
    @Nullable
    private ImageView mTopGradient;
    @Nullable
    private Drawable mUnmutedDrawable;
    @Nullable
    private VastVideoProgressBarWidget mVideoProgress;
    @Nullable
    private TextureView mVideoTextureView;

    /* renamed from: com.mopub.nativeads.MediaLayout$2 reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$nativeads$MediaLayout$MuteState = new int[MuteState.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|(2:1|2)|3|5|6|7|8|9|10|11|12|(2:13|14)|15|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|(2:1|2)|3|5|6|7|8|9|10|11|12|(2:13|14)|15|17|18|19|20|22) */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|5|6|7|8|9|10|11|12|(2:13|14)|15|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0035 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002a */
        static {
            /*
                com.mopub.nativeads.MediaLayout$Mode[] r0 = com.mopub.nativeads.MediaLayout.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$nativeads$MediaLayout$Mode = r0
                r0 = 1
                int[] r1 = $SwitchMap$com$mopub$nativeads$MediaLayout$Mode     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.mopub.nativeads.MediaLayout$Mode r2 = com.mopub.nativeads.MediaLayout.Mode.IMAGE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = $SwitchMap$com$mopub$nativeads$MediaLayout$Mode     // Catch:{ NoSuchFieldError -> 0x001f }
                com.mopub.nativeads.MediaLayout$Mode r3 = com.mopub.nativeads.MediaLayout.Mode.LOADING     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r2 = $SwitchMap$com$mopub$nativeads$MediaLayout$Mode     // Catch:{ NoSuchFieldError -> 0x002a }
                com.mopub.nativeads.MediaLayout$Mode r3 = com.mopub.nativeads.MediaLayout.Mode.BUFFERING     // Catch:{ NoSuchFieldError -> 0x002a }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r4 = 3
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r2 = $SwitchMap$com$mopub$nativeads$MediaLayout$Mode     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.mopub.nativeads.MediaLayout$Mode r3 = com.mopub.nativeads.MediaLayout.Mode.PLAYING     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r4 = 4
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r2 = $SwitchMap$com$mopub$nativeads$MediaLayout$Mode     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.mopub.nativeads.MediaLayout$Mode r3 = com.mopub.nativeads.MediaLayout.Mode.PAUSED     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r4 = 5
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r2 = $SwitchMap$com$mopub$nativeads$MediaLayout$Mode     // Catch:{ NoSuchFieldError -> 0x004b }
                com.mopub.nativeads.MediaLayout$Mode r3 = com.mopub.nativeads.MediaLayout.Mode.FINISHED     // Catch:{ NoSuchFieldError -> 0x004b }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r4 = 6
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                com.mopub.nativeads.MediaLayout$MuteState[] r2 = com.mopub.nativeads.MediaLayout.MuteState.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$mopub$nativeads$MediaLayout$MuteState = r2
                int[] r2 = $SwitchMap$com$mopub$nativeads$MediaLayout$MuteState     // Catch:{ NoSuchFieldError -> 0x005e }
                com.mopub.nativeads.MediaLayout$MuteState r3 = com.mopub.nativeads.MediaLayout.MuteState.MUTED     // Catch:{ NoSuchFieldError -> 0x005e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x005e }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x005e }
            L_0x005e:
                int[] r0 = $SwitchMap$com$mopub$nativeads$MediaLayout$MuteState     // Catch:{ NoSuchFieldError -> 0x0068 }
                com.mopub.nativeads.MediaLayout$MuteState r2 = com.mopub.nativeads.MediaLayout.MuteState.UNMUTED     // Catch:{ NoSuchFieldError -> 0x0068 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0068 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0068 }
            L_0x0068:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.nativeads.MediaLayout.AnonymousClass2.<clinit>():void");
        }
    }

    public enum Mode {
        IMAGE,
        PLAYING,
        LOADING,
        BUFFERING,
        PAUSED,
        FINISHED
    }

    public enum MuteState {
        MUTED,
        UNMUTED
    }

    public MediaLayout(@NonNull Context context) {
        this(context, null);
    }

    public MediaLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MediaLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMode = Mode.IMAGE;
        Preconditions.checkNotNull(context);
        this.mMuteState = MuteState.MUTED;
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.mMainImageView = new ImageView(context);
        this.mMainImageView.setLayoutParams(layoutParams);
        this.mMainImageView.setScaleType(ScaleType.CENTER_CROP);
        addView(this.mMainImageView);
        this.mControlSizePx = Dips.asIntPixels(40.0f, context);
        this.mGradientStripHeightPx = Dips.asIntPixels(35.0f, context);
        this.mMuteSizePx = Dips.asIntPixels(36.0f, context);
        this.mPaddingPx = Dips.asIntPixels(10.0f, context);
    }

    public void setSurfaceTextureListener(@Nullable SurfaceTextureListener surfaceTextureListener) {
        TextureView textureView = this.mVideoTextureView;
        if (textureView != null) {
            textureView.setSurfaceTextureListener(surfaceTextureListener);
            SurfaceTexture surfaceTexture = this.mVideoTextureView.getSurfaceTexture();
            if (surfaceTexture != null && surfaceTextureListener != null) {
                surfaceTextureListener.onSurfaceTextureAvailable(surfaceTexture, this.mVideoTextureView.getWidth(), this.mVideoTextureView.getHeight());
            }
        }
    }

    public void initForVideo() {
        if (!this.mIsInitialized) {
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            layoutParams.addRule(13);
            this.mVideoTextureView = new TextureView(getContext());
            this.mVideoTextureView.setLayoutParams(layoutParams);
            this.mVideoTextureView.setId((int) Utils.generateUniqueId());
            addView(this.mVideoTextureView);
            this.mMainImageView.bringToFront();
            int i = this.mControlSizePx;
            LayoutParams layoutParams2 = new LayoutParams(i, i);
            layoutParams2.addRule(10);
            layoutParams2.addRule(11);
            this.mLoadingSpinner = new ProgressBar(getContext());
            this.mLoadingSpinner.setLayoutParams(layoutParams2);
            ProgressBar progressBar = this.mLoadingSpinner;
            int i2 = this.mPaddingPx;
            progressBar.setPadding(0, i2, i2, 0);
            this.mLoadingSpinner.setIndeterminate(true);
            addView(this.mLoadingSpinner);
            LayoutParams layoutParams3 = new LayoutParams(-1, this.mGradientStripHeightPx);
            layoutParams3.addRule(8, this.mVideoTextureView.getId());
            this.mBottomGradient = new ImageView(getContext());
            this.mBottomGradient.setLayoutParams(layoutParams3);
            this.mBottomGradient.setImageDrawable(new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{GradientStrip.START_COLOR, GradientStrip.END_COLOR}));
            addView(this.mBottomGradient);
            LayoutParams layoutParams4 = new LayoutParams(-1, this.mGradientStripHeightPx);
            layoutParams4.addRule(6, this.mVideoTextureView.getId());
            this.mTopGradient = new ImageView(getContext());
            this.mTopGradient.setLayoutParams(layoutParams4);
            this.mTopGradient.setImageDrawable(new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{GradientStrip.START_COLOR, GradientStrip.END_COLOR}));
            addView(this.mTopGradient);
            this.mVideoProgress = new VastVideoProgressBarWidget(getContext());
            this.mVideoProgress.setAnchorId(this.mVideoTextureView.getId());
            this.mVideoProgress.calibrateAndMakeVisible(1000, 0);
            addView(this.mVideoProgress);
            this.mMutedDrawable = Drawables.NATIVE_MUTED.createDrawable(getContext());
            this.mUnmutedDrawable = Drawables.NATIVE_UNMUTED.createDrawable(getContext());
            int i3 = this.mMuteSizePx;
            LayoutParams layoutParams5 = new LayoutParams(i3, i3);
            layoutParams5.addRule(9);
            layoutParams5.addRule(2, this.mVideoProgress.getId());
            this.mMuteControl = new ImageView(getContext());
            this.mMuteControl.setLayoutParams(layoutParams5);
            this.mMuteControl.setScaleType(ScaleType.CENTER_INSIDE);
            ImageView imageView = this.mMuteControl;
            int i4 = this.mPaddingPx;
            imageView.setPadding(i4, i4, i4, i4);
            this.mMuteControl.setImageDrawable(this.mMutedDrawable);
            addView(this.mMuteControl);
            LayoutParams layoutParams6 = new LayoutParams(-1, -1);
            layoutParams6.addRule(13);
            this.mOverlay = new View(getContext());
            this.mOverlay.setLayoutParams(layoutParams6);
            this.mOverlay.setBackgroundColor(0);
            addView(this.mOverlay);
            int i5 = this.mControlSizePx;
            LayoutParams layoutParams7 = new LayoutParams(i5, i5);
            layoutParams7.addRule(13);
            this.mPlayButton = new ImageView(getContext());
            this.mPlayButton.setLayoutParams(layoutParams7);
            this.mPlayButton.setImageDrawable(Drawables.NATIVE_PLAY.createDrawable(getContext()));
            addView(this.mPlayButton);
            this.mIsInitialized = true;
            updateViewState();
        }
    }

    public void reset() {
        setMode(Mode.IMAGE);
        setPlayButtonClickListener(null);
        setMuteControlClickListener(null);
        setVideoClickListener(null);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (mode != 1073741824) {
            if (mode == Integer.MIN_VALUE) {
                size = Math.min(size, measuredWidth);
            } else {
                size = measuredWidth;
            }
        }
        int i3 = (int) (((float) size) * 0.5625f);
        if (mode2 == 1073741824 && size2 < i3) {
            size = (int) (((float) size2) * 1.7777778f);
            i3 = size2;
        }
        if (Math.abs(i3 - measuredHeight) >= 2 || Math.abs(size - measuredWidth) >= 2) {
            MoPubLog.v(String.format("Resetting mediaLayout size to w: %d h: %d", new Object[]{Integer.valueOf(size), Integer.valueOf(i3)}));
            getLayoutParams().width = size;
            getLayoutParams().height = i3;
        }
        super.onMeasure(i, i2);
    }

    public void setMainImageDrawable(@NonNull Drawable drawable) {
        Preconditions.checkNotNull(drawable);
        this.mMainImageView.setImageDrawable(drawable);
    }

    public void resetProgress() {
        VastVideoProgressBarWidget vastVideoProgressBarWidget = this.mVideoProgress;
        if (vastVideoProgressBarWidget != null) {
            vastVideoProgressBarWidget.reset();
        }
    }

    public void updateProgress(int i) {
        VastVideoProgressBarWidget vastVideoProgressBarWidget = this.mVideoProgress;
        if (vastVideoProgressBarWidget != null) {
            vastVideoProgressBarWidget.updateProgress(i);
        }
    }

    public TextureView getTextureView() {
        return this.mVideoTextureView;
    }

    public void setMode(@NonNull Mode mode) {
        Preconditions.checkNotNull(mode);
        this.mMode = mode;
        post(new Runnable() {
            public void run() {
                MediaLayout.this.updateViewState();
            }
        });
    }

    @Nullable
    public ImageView getMainImageView() {
        return this.mMainImageView;
    }

    public void setMuteControlClickListener(@Nullable OnClickListener onClickListener) {
        ImageView imageView = this.mMuteControl;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }

    public void setPlayButtonClickListener(@Nullable OnClickListener onClickListener) {
        if (this.mPlayButton != null) {
            View view = this.mOverlay;
            if (view != null) {
                view.setOnClickListener(onClickListener);
                this.mPlayButton.setOnClickListener(onClickListener);
            }
        }
    }

    public void setVideoClickListener(@Nullable OnClickListener onClickListener) {
        TextureView textureView = this.mVideoTextureView;
        if (textureView != null) {
            textureView.setOnClickListener(onClickListener);
        }
    }

    public void setMuteState(@NonNull MuteState muteState) {
        Preconditions.checkNotNull(muteState);
        if (muteState != this.mMuteState) {
            this.mMuteState = muteState;
            if (this.mMuteControl != null) {
                if (AnonymousClass2.$SwitchMap$com$mopub$nativeads$MediaLayout$MuteState[this.mMuteState.ordinal()] != 1) {
                    this.mMuteControl.setImageDrawable(this.mUnmutedDrawable);
                } else {
                    this.mMuteControl.setImageDrawable(this.mMutedDrawable);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateViewState() {
        switch (this.mMode) {
            case IMAGE:
                setMainImageVisibility(0);
                setLoadingSpinnerVisibility(4);
                setVideoControlVisibility(4);
                setPlayButtonVisibility(4);
                return;
            case LOADING:
                setMainImageVisibility(0);
                setLoadingSpinnerVisibility(0);
                setVideoControlVisibility(4);
                setPlayButtonVisibility(4);
                return;
            case BUFFERING:
                setMainImageVisibility(4);
                setLoadingSpinnerVisibility(0);
                setVideoControlVisibility(0);
                setPlayButtonVisibility(4);
                break;
            case PLAYING:
                break;
            case PAUSED:
                setMainImageVisibility(4);
                setLoadingSpinnerVisibility(4);
                setVideoControlVisibility(0);
                setPlayButtonVisibility(0);
                return;
            case FINISHED:
                setMainImageVisibility(0);
                setLoadingSpinnerVisibility(4);
                setVideoControlVisibility(4);
                setPlayButtonVisibility(0);
                return;
            default:
                return;
        }
        setMainImageVisibility(4);
        setLoadingSpinnerVisibility(4);
        setVideoControlVisibility(0);
        setPlayButtonVisibility(4);
    }

    private void setMainImageVisibility(int i) {
        this.mMainImageView.setVisibility(i);
    }

    private void setLoadingSpinnerVisibility(int i) {
        ProgressBar progressBar = this.mLoadingSpinner;
        if (progressBar != null) {
            progressBar.setVisibility(i);
        }
        ImageView imageView = this.mTopGradient;
        if (imageView != null) {
            imageView.setVisibility(i);
        }
    }

    private void setVideoControlVisibility(int i) {
        ImageView imageView = this.mBottomGradient;
        if (imageView != null) {
            imageView.setVisibility(i);
        }
        VastVideoProgressBarWidget vastVideoProgressBarWidget = this.mVideoProgress;
        if (vastVideoProgressBarWidget != null) {
            vastVideoProgressBarWidget.setVisibility(i);
        }
        ImageView imageView2 = this.mMuteControl;
        if (imageView2 != null) {
            imageView2.setVisibility(i);
        }
    }

    private void setPlayButtonVisibility(int i) {
        ImageView imageView = this.mPlayButton;
        if (imageView != null && this.mOverlay != null) {
            imageView.setVisibility(i);
            this.mOverlay.setVisibility(i);
        }
    }
}
