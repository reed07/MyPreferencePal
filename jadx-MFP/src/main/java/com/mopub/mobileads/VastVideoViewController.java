package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.VideoView;
import com.mopub.common.ExternalViewabilitySession.VideoEvent;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.IntentActions;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.BaseVideoViewController.BaseVideoViewControllerListener;
import com.mopub.network.TrackingRequest;
import java.io.Serializable;
import java.util.Map;

public class VastVideoViewController extends BaseVideoViewController {
    public static final int WEBVIEW_PADDING = 16;
    /* access modifiers changed from: private */
    @NonNull
    public View mAdsByView;
    /* access modifiers changed from: private */
    @NonNull
    public ImageView mBlurredLastVideoFrameImageView;
    /* access modifiers changed from: private */
    @NonNull
    public VastVideoGradientStripWidget mBottomGradientStripWidget;
    @NonNull
    private final OnTouchListener mClickThroughListener;
    @NonNull
    private VastVideoCloseButtonWidget mCloseButtonWidget;
    @NonNull
    private final VastVideoViewCountdownRunnable mCountdownRunnable;
    /* access modifiers changed from: private */
    @NonNull
    public VastVideoCtaButtonWidget mCtaButtonWidget;
    /* access modifiers changed from: private */
    public int mDuration;
    /* access modifiers changed from: private */
    @NonNull
    public ExternalViewabilitySessionManager mExternalViewabilitySessionManager;
    private boolean mHasSkipOffset = false;
    /* access modifiers changed from: private */
    public boolean mHasSocialActions = false;
    /* access modifiers changed from: private */
    @NonNull
    public final View mIconView;
    /* access modifiers changed from: private */
    public boolean mIsCalibrationDone = false;
    /* access modifiers changed from: private */
    public boolean mIsClosing = false;
    /* access modifiers changed from: private */
    public boolean mIsVideoFinishedPlaying;
    /* access modifiers changed from: private */
    @NonNull
    public final View mLandscapeCompanionAdView;
    /* access modifiers changed from: private */
    @NonNull
    public final View mPortraitCompanionAdView;
    /* access modifiers changed from: private */
    @NonNull
    public VastVideoProgressBarWidget mProgressBarWidget;
    @NonNull
    private final VastVideoViewProgressRunnable mProgressCheckerRunnable;
    /* access modifiers changed from: private */
    @NonNull
    public VastVideoRadialCountdownWidget mRadialCountdownWidget;
    private int mSeekerPositionOnPause = -1;
    /* access modifiers changed from: private */
    public int mShowCloseButtonDelay = 5000;
    private boolean mShowCloseButtonEventFired;
    @NonNull
    private final Map<String, VastCompanionAdConfig> mSocialActionsCompanionAds;
    @NonNull
    private final View mSocialActionsView;
    /* access modifiers changed from: private */
    @NonNull
    public VastVideoGradientStripWidget mTopGradientStripWidget;
    /* access modifiers changed from: private */
    @Nullable
    public VastCompanionAdConfig mVastCompanionAdConfig;
    @Nullable
    private final VastIconConfig mVastIconConfig;
    /* access modifiers changed from: private */
    public final VastVideoConfig mVastVideoConfig;
    /* access modifiers changed from: private */
    public boolean mVideoError;
    /* access modifiers changed from: private */
    @NonNull
    public final VastVideoView mVideoView;

    VastVideoViewController(final Activity activity, Bundle bundle, @Nullable Bundle bundle2, long j, BaseVideoViewControllerListener baseVideoViewControllerListener) throws IllegalStateException {
        super(activity, Long.valueOf(j), baseVideoViewControllerListener);
        Serializable serializable = bundle2 != null ? bundle2.getSerializable("resumed_vast_config") : null;
        Serializable serializable2 = bundle.getSerializable("vast_video_config");
        if (serializable != null && (serializable instanceof VastVideoConfig)) {
            this.mVastVideoConfig = (VastVideoConfig) serializable;
            this.mSeekerPositionOnPause = bundle2.getInt("current_position", -1);
        } else if (serializable2 == null || !(serializable2 instanceof VastVideoConfig)) {
            throw new IllegalStateException("VastVideoConfig is invalid");
        } else {
            this.mVastVideoConfig = (VastVideoConfig) serializable2;
        }
        if (this.mVastVideoConfig.getDiskMediaFileUrl() != null) {
            this.mVastCompanionAdConfig = this.mVastVideoConfig.getVastCompanionAd(activity.getResources().getConfiguration().orientation);
            this.mSocialActionsCompanionAds = this.mVastVideoConfig.getSocialActionsCompanionAds();
            this.mVastIconConfig = this.mVastVideoConfig.getVastIconConfig();
            this.mClickThroughListener = new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 1 && VastVideoViewController.this.shouldAllowClickThrough()) {
                        VastVideoViewController.this.mExternalViewabilitySessionManager.recordVideoEvent(VideoEvent.AD_CLICK_THRU, VastVideoViewController.this.getCurrentPosition());
                        VastVideoViewController.this.mIsClosing = true;
                        VastVideoViewController.this.broadcastAction(IntentActions.ACTION_INTERSTITIAL_CLICK);
                        VastVideoViewController.this.mVastVideoConfig.handleClickForResult(activity, VastVideoViewController.this.mIsVideoFinishedPlaying ? VastVideoViewController.this.mDuration : VastVideoViewController.this.getCurrentPosition(), 1);
                    }
                    return true;
                }
            };
            getLayout().setBackgroundColor(-16777216);
            addBlurredLastVideoFrameImageView(activity, 4);
            this.mVideoView = createVideoView(activity, 0);
            this.mVideoView.requestFocus();
            this.mExternalViewabilitySessionManager = new ExternalViewabilitySessionManager(activity);
            this.mExternalViewabilitySessionManager.createVideoSession(activity, this.mVideoView, this.mVastVideoConfig);
            this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mBlurredLastVideoFrameImageView);
            this.mLandscapeCompanionAdView = createCompanionAdView(activity, this.mVastVideoConfig.getVastCompanionAd(2), 4);
            this.mPortraitCompanionAdView = createCompanionAdView(activity, this.mVastVideoConfig.getVastCompanionAd(1), 4);
            addTopGradientStripWidget(activity);
            addProgressBarWidget(activity, 4);
            addBottomGradientStripWidget(activity);
            addRadialCountdownWidget(activity, 4);
            this.mIconView = createIconView(activity, this.mVastIconConfig, 4);
            this.mIconView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    VastVideoViewController vastVideoViewController = VastVideoViewController.this;
                    vastVideoViewController.mAdsByView = vastVideoViewController.createAdsByView(activity);
                    VastVideoViewController.this.mIconView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            });
            addCtaButtonWidget(activity);
            this.mSocialActionsView = createSocialActionsView(activity, (VastCompanionAdConfig) this.mSocialActionsCompanionAds.get(VastXmlManagerAggregator.SOCIAL_ACTIONS_AD_SLOT_ID), Dips.dipsToIntPixels(38.0f, activity), 6, this.mCtaButtonWidget, 4, 16);
            addCloseButtonWidget(activity, 8);
            Handler handler = new Handler(Looper.getMainLooper());
            this.mProgressCheckerRunnable = new VastVideoViewProgressRunnable(this, this.mVastVideoConfig, handler);
            this.mCountdownRunnable = new VastVideoViewCountdownRunnable(this, handler);
            return;
        }
        throw new IllegalStateException("VastVideoConfig does not have a video disk path");
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public View createAdsByView(Activity activity) {
        return createSocialActionsView(activity, (VastCompanionAdConfig) this.mSocialActionsCompanionAds.get(VastXmlManagerAggregator.ADS_BY_AD_SLOT_ID), this.mIconView.getHeight(), 1, this.mIconView, 0, 6);
    }

    /* access modifiers changed from: protected */
    public VideoView getVideoView() {
        return this.mVideoView;
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        super.onCreate();
        switch (this.mVastVideoConfig.getCustomForceOrientation()) {
            case FORCE_PORTRAIT:
                getBaseVideoViewControllerListener().onSetRequestedOrientation(1);
                break;
            case FORCE_LANDSCAPE:
                getBaseVideoViewControllerListener().onSetRequestedOrientation(6);
                break;
        }
        this.mVastVideoConfig.handleImpression(getContext(), getCurrentPosition());
        broadcastAction(IntentActions.ACTION_INTERSTITIAL_SHOW);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        startRunnables();
        if (this.mSeekerPositionOnPause > 0) {
            this.mExternalViewabilitySessionManager.recordVideoEvent(VideoEvent.AD_PLAYING, this.mSeekerPositionOnPause);
            this.mVideoView.seekTo(this.mSeekerPositionOnPause);
        } else {
            this.mExternalViewabilitySessionManager.recordVideoEvent(VideoEvent.AD_LOADED, getCurrentPosition());
        }
        if (!this.mIsVideoFinishedPlaying) {
            this.mVideoView.start();
        }
        if (this.mSeekerPositionOnPause != -1) {
            this.mVastVideoConfig.handleResume(getContext(), this.mSeekerPositionOnPause);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        stopRunnables();
        this.mSeekerPositionOnPause = getCurrentPosition();
        this.mVideoView.pause();
        if (!this.mIsVideoFinishedPlaying && !this.mIsClosing) {
            this.mExternalViewabilitySessionManager.recordVideoEvent(VideoEvent.AD_PAUSED, getCurrentPosition());
            this.mVastVideoConfig.handlePause(getContext(), this.mSeekerPositionOnPause);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        stopRunnables();
        this.mExternalViewabilitySessionManager.recordVideoEvent(VideoEvent.AD_STOPPED, getCurrentPosition());
        this.mExternalViewabilitySessionManager.endVideoSession();
        broadcastAction(IntentActions.ACTION_INTERSTITIAL_DISMISS);
        this.mVideoView.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        bundle.putInt("current_position", this.mSeekerPositionOnPause);
        bundle.putSerializable("resumed_vast_config", this.mVastVideoConfig);
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        int i = getContext().getResources().getConfiguration().orientation;
        this.mVastCompanionAdConfig = this.mVastVideoConfig.getVastCompanionAd(i);
        if (this.mLandscapeCompanionAdView.getVisibility() == 0 || this.mPortraitCompanionAdView.getVisibility() == 0) {
            if (i == 1) {
                this.mLandscapeCompanionAdView.setVisibility(4);
                this.mPortraitCompanionAdView.setVisibility(0);
            } else {
                this.mPortraitCompanionAdView.setVisibility(4);
                this.mLandscapeCompanionAdView.setVisibility(0);
            }
            VastCompanionAdConfig vastCompanionAdConfig = this.mVastCompanionAdConfig;
            if (vastCompanionAdConfig != null) {
                vastCompanionAdConfig.handleImpression(getContext(), this.mDuration);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onBackPressed() {
        if (!this.mIsVideoFinishedPlaying) {
            this.mExternalViewabilitySessionManager.recordVideoEvent(VideoEvent.AD_SKIPPED, getCurrentPosition());
        }
    }

    public boolean backButtonEnabled() {
        return this.mShowCloseButtonEventFired;
    }

    /* access modifiers changed from: 0000 */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            getBaseVideoViewControllerListener().onFinish();
        }
    }

    /* access modifiers changed from: private */
    public void adjustSkipOffset() {
        int duration = getDuration();
        if (this.mVastVideoConfig.isRewardedVideo()) {
            this.mShowCloseButtonDelay = duration;
            return;
        }
        if (duration < 16000) {
            this.mShowCloseButtonDelay = duration;
        }
        Integer skipOffsetMillis = this.mVastVideoConfig.getSkipOffsetMillis(duration);
        if (skipOffsetMillis != null) {
            this.mShowCloseButtonDelay = skipOffsetMillis.intValue();
            this.mHasSkipOffset = true;
        }
    }

    private VastVideoView createVideoView(@NonNull final Context context, int i) {
        if (this.mVastVideoConfig.getDiskMediaFileUrl() != null) {
            final VastVideoView vastVideoView = new VastVideoView(context);
            vastVideoView.setId((int) Utils.generateUniqueId());
            vastVideoView.setOnPreparedListener(new OnPreparedListener() {
                public void onPrepared(MediaPlayer mediaPlayer) {
                    VastVideoViewController vastVideoViewController = VastVideoViewController.this;
                    vastVideoViewController.mDuration = vastVideoViewController.mVideoView.getDuration();
                    VastVideoViewController.this.mExternalViewabilitySessionManager.onVideoPrepared(VastVideoViewController.this.getLayout(), VastVideoViewController.this.mDuration);
                    VastVideoViewController.this.adjustSkipOffset();
                    if (VastVideoViewController.this.mVastCompanionAdConfig == null || VastVideoViewController.this.mHasSocialActions) {
                        vastVideoView.prepareBlurredLastVideoFrame(VastVideoViewController.this.mBlurredLastVideoFrameImageView, VastVideoViewController.this.mVastVideoConfig.getDiskMediaFileUrl());
                    }
                    VastVideoViewController.this.mProgressBarWidget.calibrateAndMakeVisible(VastVideoViewController.this.getDuration(), VastVideoViewController.this.mShowCloseButtonDelay);
                    VastVideoViewController.this.mRadialCountdownWidget.calibrateAndMakeVisible(VastVideoViewController.this.mShowCloseButtonDelay);
                    VastVideoViewController.this.mIsCalibrationDone = true;
                }
            });
            vastVideoView.setOnTouchListener(this.mClickThroughListener);
            vastVideoView.setOnCompletionListener(new OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    VastVideoViewController.this.stopRunnables();
                    VastVideoViewController.this.makeVideoInteractable();
                    VastVideoViewController.this.videoCompleted(false);
                    VastVideoViewController.this.mIsVideoFinishedPlaying = true;
                    if (VastVideoViewController.this.mVastVideoConfig.isRewardedVideo()) {
                        VastVideoViewController.this.broadcastAction(IntentActions.ACTION_REWARDED_VIDEO_COMPLETE);
                    }
                    if (!VastVideoViewController.this.mVideoError && VastVideoViewController.this.mVastVideoConfig.getRemainingProgressTrackerCount() == 0) {
                        VastVideoViewController.this.mExternalViewabilitySessionManager.recordVideoEvent(VideoEvent.AD_COMPLETE, VastVideoViewController.this.getCurrentPosition());
                        VastVideoViewController.this.mVastVideoConfig.handleComplete(VastVideoViewController.this.getContext(), VastVideoViewController.this.getCurrentPosition());
                    }
                    vastVideoView.setVisibility(4);
                    VastVideoViewController.this.mProgressBarWidget.setVisibility(8);
                    if (!VastVideoViewController.this.mHasSocialActions) {
                        VastVideoViewController.this.mIconView.setVisibility(8);
                    } else if (VastVideoViewController.this.mBlurredLastVideoFrameImageView.getDrawable() != null) {
                        VastVideoViewController.this.mBlurredLastVideoFrameImageView.setScaleType(ScaleType.CENTER_CROP);
                        VastVideoViewController.this.mBlurredLastVideoFrameImageView.setVisibility(0);
                    }
                    VastVideoViewController.this.mTopGradientStripWidget.notifyVideoComplete();
                    VastVideoViewController.this.mBottomGradientStripWidget.notifyVideoComplete();
                    VastVideoViewController.this.mCtaButtonWidget.notifyVideoComplete();
                    if (VastVideoViewController.this.mVastCompanionAdConfig != null) {
                        if (context.getResources().getConfiguration().orientation == 1) {
                            VastVideoViewController.this.mPortraitCompanionAdView.setVisibility(0);
                        } else {
                            VastVideoViewController.this.mLandscapeCompanionAdView.setVisibility(0);
                        }
                        VastVideoViewController.this.mVastCompanionAdConfig.handleImpression(context, VastVideoViewController.this.mDuration);
                    } else if (VastVideoViewController.this.mBlurredLastVideoFrameImageView.getDrawable() != null) {
                        VastVideoViewController.this.mBlurredLastVideoFrameImageView.setVisibility(0);
                    }
                }
            });
            vastVideoView.setOnErrorListener(new OnErrorListener() {
                public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    VastVideoViewController.this.mExternalViewabilitySessionManager.recordVideoEvent(VideoEvent.RECORD_AD_ERROR, VastVideoViewController.this.getCurrentPosition());
                    VastVideoViewController.this.stopRunnables();
                    VastVideoViewController.this.makeVideoInteractable();
                    VastVideoViewController.this.videoError(false);
                    VastVideoViewController.this.mVideoError = true;
                    VastVideoViewController.this.mVastVideoConfig.handleError(VastVideoViewController.this.getContext(), VastErrorCode.GENERAL_LINEAR_AD_ERROR, VastVideoViewController.this.getCurrentPosition());
                    return false;
                }
            });
            vastVideoView.setVideoPath(this.mVastVideoConfig.getDiskMediaFileUrl());
            vastVideoView.setVisibility(i);
            return vastVideoView;
        }
        throw new IllegalStateException("VastVideoConfig does not have a video disk path");
    }

    private void addTopGradientStripWidget(@NonNull Context context) {
        VastVideoGradientStripWidget vastVideoGradientStripWidget = new VastVideoGradientStripWidget(context, Orientation.TOP_BOTTOM, this.mVastVideoConfig.getCustomForceOrientation(), this.mVastCompanionAdConfig != null, 0, 6, getLayout().getId());
        this.mTopGradientStripWidget = vastVideoGradientStripWidget;
        getLayout().addView(this.mTopGradientStripWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mTopGradientStripWidget);
    }

    private void addBottomGradientStripWidget(@NonNull Context context) {
        VastVideoGradientStripWidget vastVideoGradientStripWidget = new VastVideoGradientStripWidget(context, Orientation.BOTTOM_TOP, this.mVastVideoConfig.getCustomForceOrientation(), this.mVastCompanionAdConfig != null, 8, 2, this.mProgressBarWidget.getId());
        this.mBottomGradientStripWidget = vastVideoGradientStripWidget;
        getLayout().addView(this.mBottomGradientStripWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mBottomGradientStripWidget);
    }

    private void addProgressBarWidget(@NonNull Context context, int i) {
        this.mProgressBarWidget = new VastVideoProgressBarWidget(context);
        this.mProgressBarWidget.setAnchorId(this.mVideoView.getId());
        this.mProgressBarWidget.setVisibility(i);
        getLayout().addView(this.mProgressBarWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mProgressBarWidget);
    }

    private void addRadialCountdownWidget(@NonNull Context context, int i) {
        this.mRadialCountdownWidget = new VastVideoRadialCountdownWidget(context);
        this.mRadialCountdownWidget.setVisibility(i);
        getLayout().addView(this.mRadialCountdownWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mRadialCountdownWidget);
    }

    private void addCtaButtonWidget(@NonNull Context context) {
        this.mCtaButtonWidget = new VastVideoCtaButtonWidget(context, this.mVideoView.getId(), this.mVastCompanionAdConfig != null, true ^ TextUtils.isEmpty(this.mVastVideoConfig.getClickThroughUrl()));
        getLayout().addView(this.mCtaButtonWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mCtaButtonWidget);
        this.mCtaButtonWidget.setOnTouchListener(this.mClickThroughListener);
        String customCtaText = this.mVastVideoConfig.getCustomCtaText();
        if (customCtaText != null) {
            this.mCtaButtonWidget.updateCtaText(customCtaText);
        }
    }

    private void addCloseButtonWidget(@NonNull Context context, int i) {
        this.mCloseButtonWidget = new VastVideoCloseButtonWidget(context);
        this.mCloseButtonWidget.setVisibility(i);
        getLayout().addView(this.mCloseButtonWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mCloseButtonWidget);
        this.mCloseButtonWidget.setOnTouchListenerToContent(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int i;
                if (VastVideoViewController.this.mIsVideoFinishedPlaying) {
                    i = VastVideoViewController.this.mDuration;
                } else {
                    i = VastVideoViewController.this.getCurrentPosition();
                }
                if (motionEvent.getAction() == 1) {
                    VastVideoViewController.this.mIsClosing = true;
                    if (!VastVideoViewController.this.mIsVideoFinishedPlaying) {
                        VastVideoViewController.this.mExternalViewabilitySessionManager.recordVideoEvent(VideoEvent.AD_SKIPPED, VastVideoViewController.this.getCurrentPosition());
                    }
                    VastVideoViewController.this.mVastVideoConfig.handleClose(VastVideoViewController.this.getContext(), i);
                    VastVideoViewController.this.getBaseVideoViewControllerListener().onFinish();
                }
                return true;
            }
        });
        String customSkipText = this.mVastVideoConfig.getCustomSkipText();
        if (customSkipText != null) {
            this.mCloseButtonWidget.updateCloseButtonText(customSkipText);
        }
        String customCloseIconUrl = this.mVastVideoConfig.getCustomCloseIconUrl();
        if (customCloseIconUrl != null) {
            this.mCloseButtonWidget.updateCloseButtonIcon(customCloseIconUrl);
        }
    }

    private void addBlurredLastVideoFrameImageView(@NonNull Context context, int i) {
        this.mBlurredLastVideoFrameImageView = new ImageView(context);
        this.mBlurredLastVideoFrameImageView.setVisibility(i);
        getLayout().addView(this.mBlurredLastVideoFrameImageView, new LayoutParams(-1, -1));
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @VisibleForTesting
    public View createCompanionAdView(@NonNull Context context, @Nullable VastCompanionAdConfig vastCompanionAdConfig, int i) {
        Preconditions.checkNotNull(context);
        if (vastCompanionAdConfig == null) {
            View view = new View(context);
            view.setVisibility(4);
            return view;
        }
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setGravity(17);
        getLayout().addView(relativeLayout, new LayoutParams(-1, -1));
        this.mExternalViewabilitySessionManager.registerVideoObstruction(relativeLayout);
        VastWebView createCompanionVastWebView = createCompanionVastWebView(context, vastCompanionAdConfig);
        createCompanionVastWebView.setVisibility(i);
        LayoutParams layoutParams = new LayoutParams(Dips.dipsToIntPixels((float) (vastCompanionAdConfig.getWidth() + 16), context), Dips.dipsToIntPixels((float) (vastCompanionAdConfig.getHeight() + 16), context));
        layoutParams.addRule(13, -1);
        relativeLayout.addView(createCompanionVastWebView, layoutParams);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(createCompanionVastWebView);
        return createCompanionVastWebView;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @VisibleForTesting
    public View createSocialActionsView(@NonNull Context context, @Nullable VastCompanionAdConfig vastCompanionAdConfig, int i, int i2, @NonNull View view, int i3, int i4) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(view);
        if (vastCompanionAdConfig == null) {
            View view2 = new View(context);
            view2.setVisibility(4);
            return view2;
        }
        this.mHasSocialActions = true;
        this.mCtaButtonWidget.setHasSocialActions(this.mHasSocialActions);
        VastWebView createCompanionVastWebView = createCompanionVastWebView(context, vastCompanionAdConfig);
        int dipsToIntPixels = Dips.dipsToIntPixels((float) vastCompanionAdConfig.getWidth(), context);
        int dipsToIntPixels2 = Dips.dipsToIntPixels((float) vastCompanionAdConfig.getHeight(), context);
        int i5 = (i - dipsToIntPixels2) / 2;
        int dipsToIntPixels3 = Dips.dipsToIntPixels((float) i4, context);
        LayoutParams layoutParams = new LayoutParams(dipsToIntPixels, dipsToIntPixels2);
        layoutParams.addRule(i2, view.getId());
        layoutParams.addRule(6, view.getId());
        layoutParams.setMargins(dipsToIntPixels3, i5, 0, 0);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setGravity(16);
        relativeLayout.addView(createCompanionVastWebView, new LayoutParams(-2, -2));
        this.mExternalViewabilitySessionManager.registerVideoObstruction(createCompanionVastWebView);
        getLayout().addView(relativeLayout, layoutParams);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(relativeLayout);
        createCompanionVastWebView.setVisibility(i3);
        return createCompanionVastWebView;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @VisibleForTesting
    public View createIconView(@NonNull final Context context, @Nullable final VastIconConfig vastIconConfig, int i) {
        Preconditions.checkNotNull(context);
        if (vastIconConfig == null) {
            return new View(context);
        }
        VastWebView createView = VastWebView.createView(context, vastIconConfig.getVastResource());
        createView.setVastWebViewClickListener(new VastWebViewClickListener() {
            public void onVastWebViewClick() {
                TrackingRequest.makeVastTrackingHttpRequest(vastIconConfig.getClickTrackingUris(), null, Integer.valueOf(VastVideoViewController.this.getCurrentPosition()), VastVideoViewController.this.getNetworkMediaFileUrl(), context);
                vastIconConfig.handleClick(VastVideoViewController.this.getContext(), null, VastVideoViewController.this.mVastVideoConfig.getDspCreativeId());
            }
        });
        createView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                vastIconConfig.handleClick(VastVideoViewController.this.getContext(), str, VastVideoViewController.this.mVastVideoConfig.getDspCreativeId());
                return true;
            }
        });
        createView.setVisibility(i);
        LayoutParams layoutParams = new LayoutParams(Dips.asIntPixels((float) vastIconConfig.getWidth(), context), Dips.asIntPixels((float) vastIconConfig.getHeight(), context));
        layoutParams.setMargins(Dips.dipsToIntPixels(12.0f, context), Dips.dipsToIntPixels(12.0f, context), 0, 0);
        getLayout().addView(createView, layoutParams);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(createView);
        return createView;
    }

    /* access modifiers changed from: 0000 */
    public int getDuration() {
        return this.mVideoView.getDuration();
    }

    /* access modifiers changed from: 0000 */
    public int getCurrentPosition() {
        return this.mVideoView.getCurrentPosition();
    }

    /* access modifiers changed from: 0000 */
    public void makeVideoInteractable() {
        this.mShowCloseButtonEventFired = true;
        this.mRadialCountdownWidget.setVisibility(8);
        this.mCloseButtonWidget.setVisibility(0);
        this.mCtaButtonWidget.notifyVideoSkippable();
        this.mSocialActionsView.setVisibility(0);
    }

    /* access modifiers changed from: 0000 */
    public boolean shouldBeInteractable() {
        return !this.mShowCloseButtonEventFired && getCurrentPosition() >= this.mShowCloseButtonDelay;
    }

    /* access modifiers changed from: 0000 */
    public void updateCountdown() {
        if (this.mIsCalibrationDone) {
            this.mRadialCountdownWidget.updateCountdownProgress(this.mShowCloseButtonDelay, getCurrentPosition());
        }
    }

    /* access modifiers changed from: 0000 */
    public void updateProgressBar() {
        this.mProgressBarWidget.updateProgress(getCurrentPosition());
    }

    /* access modifiers changed from: 0000 */
    public String getNetworkMediaFileUrl() {
        VastVideoConfig vastVideoConfig = this.mVastVideoConfig;
        if (vastVideoConfig == null) {
            return null;
        }
        return vastVideoConfig.getNetworkMediaFileUrl();
    }

    /* access modifiers changed from: 0000 */
    public void handleIconDisplay(int i) {
        VastIconConfig vastIconConfig = this.mVastIconConfig;
        if (vastIconConfig != null && i >= vastIconConfig.getOffsetMS()) {
            this.mIconView.setVisibility(0);
            this.mVastIconConfig.handleImpression(getContext(), i, getNetworkMediaFileUrl());
            if (this.mVastIconConfig.getDurationMS() != null && i >= this.mVastIconConfig.getOffsetMS() + this.mVastIconConfig.getDurationMS().intValue()) {
                this.mIconView.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void handleViewabilityQuartileEvent(@NonNull String str) {
        this.mExternalViewabilitySessionManager.recordVideoEvent((VideoEvent) Enum.valueOf(VideoEvent.class, str), getCurrentPosition());
    }

    /* access modifiers changed from: private */
    public boolean shouldAllowClickThrough() {
        return this.mShowCloseButtonEventFired;
    }

    private void startRunnables() {
        this.mProgressCheckerRunnable.startRepeating(50);
        this.mCountdownRunnable.startRepeating(250);
    }

    /* access modifiers changed from: private */
    public void stopRunnables() {
        this.mProgressCheckerRunnable.stop();
        this.mCountdownRunnable.stop();
    }

    @NonNull
    private VastWebView createCompanionVastWebView(@NonNull final Context context, @NonNull final VastCompanionAdConfig vastCompanionAdConfig) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(vastCompanionAdConfig);
        VastWebView createView = VastWebView.createView(context, vastCompanionAdConfig.getVastResource());
        createView.setVastWebViewClickListener(new VastWebViewClickListener() {
            public void onVastWebViewClick() {
                VastVideoViewController.this.broadcastAction(IntentActions.ACTION_INTERSTITIAL_CLICK);
                TrackingRequest.makeVastTrackingHttpRequest(vastCompanionAdConfig.getClickTrackers(), null, Integer.valueOf(VastVideoViewController.this.mDuration), null, context);
                vastCompanionAdConfig.handleClick(context, 1, null, VastVideoViewController.this.mVastVideoConfig.getDspCreativeId());
            }
        });
        createView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                vastCompanionAdConfig.handleClick(context, 1, str, VastVideoViewController.this.mVastVideoConfig.getDspCreativeId());
                return true;
            }
        });
        return createView;
    }
}
