package com.mopub.nativeads;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.VideoView;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.BaseVideoViewController;
import com.mopub.mobileads.BaseVideoViewController.BaseVideoViewControllerListener;
import com.mopub.mobileads.VastVideoConfig;
import com.mopub.nativeads.NativeFullScreenVideoView.Mode;
import com.mopub.nativeads.NativeVideoController.Listener;
import com.mopub.nativeads.NativeVideoController.NativeVideoProgressRunnable.ProgressListener;

public class NativeVideoViewController extends BaseVideoViewController implements OnAudioFocusChangeListener, SurfaceTextureListener, Listener {
    /* access modifiers changed from: private */
    @Nullable
    public Bitmap mCachedVideoFrame;
    /* access modifiers changed from: private */
    public boolean mEnded;
    private boolean mError;
    /* access modifiers changed from: private */
    @NonNull
    public final NativeFullScreenVideoView mFullScreenVideoView;
    private int mLatestVideoControllerState;
    /* access modifiers changed from: private */
    @NonNull
    public final NativeVideoController mNativeVideoController;
    /* access modifiers changed from: private */
    @NonNull
    public VastVideoConfig mVastVideoConfig;
    @NonNull
    private VideoState mVideoState;

    enum VideoState {
        NONE,
        LOADING,
        BUFFERING,
        PAUSED,
        PLAYING,
        ENDED,
        FAILED_LOAD
    }

    /* access modifiers changed from: protected */
    public VideoView getVideoView() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
    }

    /* access modifiers changed from: protected */
    public void onPause() {
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(@NonNull Bundle bundle) {
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public NativeVideoViewController(@NonNull Context context, @NonNull Bundle bundle, @NonNull Bundle bundle2, @NonNull BaseVideoViewControllerListener baseVideoViewControllerListener) {
        this(context, bundle, bundle2, baseVideoViewControllerListener, new NativeFullScreenVideoView(context, context.getResources().getConfiguration().orientation, ((VastVideoConfig) bundle.get(Constants.NATIVE_VAST_VIDEO_CONFIG)).getCustomCtaText()));
    }

    @VisibleForTesting
    NativeVideoViewController(@NonNull Context context, @NonNull Bundle bundle, @NonNull Bundle bundle2, @NonNull BaseVideoViewControllerListener baseVideoViewControllerListener, @NonNull NativeFullScreenVideoView nativeFullScreenVideoView) {
        super(context, null, baseVideoViewControllerListener);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotNull(baseVideoViewControllerListener);
        Preconditions.checkNotNull(nativeFullScreenVideoView);
        this.mVideoState = VideoState.NONE;
        this.mVastVideoConfig = (VastVideoConfig) bundle.get(Constants.NATIVE_VAST_VIDEO_CONFIG);
        this.mFullScreenVideoView = nativeFullScreenVideoView;
        this.mNativeVideoController = NativeVideoController.getForId(((Long) bundle.get(Constants.NATIVE_VIDEO_ID)).longValue());
        Preconditions.checkNotNull(this.mVastVideoConfig);
        Preconditions.checkNotNull(this.mNativeVideoController);
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        this.mFullScreenVideoView.setSurfaceTextureListener(this);
        this.mFullScreenVideoView.setMode(Mode.LOADING);
        this.mFullScreenVideoView.setPlayControlClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (NativeVideoViewController.this.mEnded) {
                    NativeVideoViewController.this.mEnded = false;
                    NativeVideoViewController.this.mFullScreenVideoView.resetProgress();
                    NativeVideoViewController.this.mNativeVideoController.seekTo(0);
                }
                NativeVideoViewController.this.applyState(VideoState.PLAYING);
            }
        });
        this.mFullScreenVideoView.setCloseControlListener(new OnClickListener() {
            public void onClick(View view) {
                NativeVideoViewController.this.applyState(VideoState.PAUSED, true);
                NativeVideoViewController.this.getBaseVideoViewControllerListener().onFinish();
            }
        });
        this.mFullScreenVideoView.setCtaClickListener(new OnClickListener() {
            public void onClick(View view) {
                NativeVideoViewController.this.mNativeVideoController.setPlayWhenReady(false);
                NativeVideoViewController nativeVideoViewController = NativeVideoViewController.this;
                nativeVideoViewController.mCachedVideoFrame = nativeVideoViewController.mFullScreenVideoView.getTextureView().getBitmap();
                NativeVideoViewController.this.mNativeVideoController.handleCtaClick((Activity) NativeVideoViewController.this.getContext());
            }
        });
        this.mFullScreenVideoView.setPrivacyInformationClickListener(new OnClickListener() {
            public void onClick(View view) {
                NativeVideoViewController.this.mNativeVideoController.setPlayWhenReady(false);
                NativeVideoViewController nativeVideoViewController = NativeVideoViewController.this;
                nativeVideoViewController.mCachedVideoFrame = nativeVideoViewController.mFullScreenVideoView.getTextureView().getBitmap();
                String privacyInformationIconClickthroughUrl = NativeVideoViewController.this.mVastVideoConfig.getPrivacyInformationIconClickthroughUrl();
                if (TextUtils.isEmpty(privacyInformationIconClickthroughUrl)) {
                    privacyInformationIconClickthroughUrl = "https://www.mopub.com/optout/";
                }
                new Builder().withSupportedUrlActions(UrlAction.OPEN_IN_APP_BROWSER, new UrlAction[0]).build().handleUrl(NativeVideoViewController.this.getContext(), privacyInformationIconClickthroughUrl);
            }
        });
        this.mFullScreenVideoView.setPrivacyInformationIconImageUrl(this.mVastVideoConfig.getPrivacyInformationIconImageUrl());
        this.mFullScreenVideoView.setLayoutParams(new LayoutParams(-1, -1));
        getBaseVideoViewControllerListener().onSetContentView(this.mFullScreenVideoView);
        this.mNativeVideoController.setProgressListener(new ProgressListener() {
            public void updateProgress(int i) {
                NativeVideoViewController.this.mFullScreenVideoView.updateProgress(i);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Bitmap bitmap = this.mCachedVideoFrame;
        if (bitmap != null) {
            this.mFullScreenVideoView.setCachedVideoFrame(bitmap);
        }
        this.mNativeVideoController.prepare(this);
        this.mNativeVideoController.setListener(this);
        this.mNativeVideoController.setOnAudioFocusChangeListener(this);
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        this.mFullScreenVideoView.setOrientation(configuration.orientation);
    }

    /* access modifiers changed from: protected */
    public void onBackPressed() {
        applyState(VideoState.PAUSED, true);
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.mNativeVideoController.setTextureView(this.mFullScreenVideoView.getTextureView());
        if (!this.mEnded) {
            NativeVideoController nativeVideoController = this.mNativeVideoController;
            nativeVideoController.seekTo(nativeVideoController.getCurrentPosition());
        }
        this.mNativeVideoController.setPlayWhenReady(!this.mEnded);
        if (this.mNativeVideoController.getDuration() - this.mNativeVideoController.getCurrentPosition() < 750) {
            this.mEnded = true;
            maybeChangeState();
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.mNativeVideoController.release(this);
        applyState(VideoState.PAUSED);
        return true;
    }

    public void onStateChanged(boolean z, int i) {
        this.mLatestVideoControllerState = i;
        maybeChangeState();
    }

    public void onError(Exception exc) {
        MoPubLog.w("Error playing back video.", exc);
        this.mError = true;
        maybeChangeState();
    }

    public void onAudioFocusChange(int i) {
        if (i == -1 || i == -2) {
            applyState(VideoState.PAUSED);
        } else if (i == -3) {
            this.mNativeVideoController.setAudioVolume(0.3f);
        } else if (i == 1) {
            this.mNativeVideoController.setAudioVolume(1.0f);
            maybeChangeState();
        }
    }

    private void maybeChangeState() {
        VideoState videoState = this.mVideoState;
        if (this.mError) {
            videoState = VideoState.FAILED_LOAD;
        } else if (this.mEnded) {
            videoState = VideoState.ENDED;
        } else {
            int i = this.mLatestVideoControllerState;
            if (i == 1) {
                videoState = VideoState.LOADING;
            } else if (i == 2) {
                videoState = VideoState.BUFFERING;
            } else if (i == 3) {
                videoState = VideoState.PLAYING;
            } else if (i == 4 || i == 5) {
                videoState = VideoState.ENDED;
            }
        }
        applyState(videoState);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void applyState(@NonNull VideoState videoState) {
        applyState(videoState, false);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void applyState(@NonNull VideoState videoState, boolean z) {
        Preconditions.checkNotNull(videoState);
        if (this.mVideoState != videoState) {
            switch (videoState) {
                case FAILED_LOAD:
                    this.mNativeVideoController.setPlayWhenReady(false);
                    this.mNativeVideoController.setAudioEnabled(false);
                    this.mNativeVideoController.setAppAudioEnabled(false);
                    this.mFullScreenVideoView.setMode(Mode.LOADING);
                    this.mVastVideoConfig.handleError(getContext(), null, 0);
                    break;
                case LOADING:
                case BUFFERING:
                    this.mNativeVideoController.setPlayWhenReady(true);
                    this.mFullScreenVideoView.setMode(Mode.LOADING);
                    break;
                case PLAYING:
                    this.mNativeVideoController.setPlayWhenReady(true);
                    this.mNativeVideoController.setAudioEnabled(true);
                    this.mNativeVideoController.setAppAudioEnabled(true);
                    this.mFullScreenVideoView.setMode(Mode.PLAYING);
                    break;
                case PAUSED:
                    if (!z) {
                        this.mNativeVideoController.setAppAudioEnabled(false);
                    }
                    this.mNativeVideoController.setPlayWhenReady(false);
                    this.mFullScreenVideoView.setMode(Mode.PAUSED);
                    break;
                case ENDED:
                    this.mEnded = true;
                    this.mNativeVideoController.setAppAudioEnabled(false);
                    this.mFullScreenVideoView.updateProgress(1000);
                    this.mFullScreenVideoView.setMode(Mode.FINISHED);
                    this.mVastVideoConfig.handleComplete(getContext(), 0);
                    break;
            }
            this.mVideoState = videoState;
        }
    }
}
