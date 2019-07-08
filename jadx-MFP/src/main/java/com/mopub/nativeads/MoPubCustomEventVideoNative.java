package com.mopub.nativeads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import com.mopub.common.DataKeys;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibilityTracker;
import com.mopub.common.VisibilityTracker.VisibilityTrackerListener;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.MraidVideoPlayerActivity;
import com.mopub.mobileads.VastManager;
import com.mopub.mobileads.VastManager.VastManagerListener;
import com.mopub.mobileads.VastTracker;
import com.mopub.mobileads.VastVideoConfig;
import com.mopub.mobileads.VideoViewabilityTracker;
import com.mopub.mobileads.factories.VastManagerFactory;
import com.mopub.nativeads.CustomEventNative.CustomEventNativeListener;
import com.mopub.nativeads.MediaLayout.Mode;
import com.mopub.nativeads.MediaLayout.MuteState;
import com.mopub.nativeads.NativeImageHelper.ImageListener;
import com.mopub.nativeads.NativeVideoController.NativeVideoProgressRunnable.ProgressListener;
import com.mopub.network.TrackingRequest;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MoPubCustomEventVideoNative extends CustomEventNative {
    private MoPubVideoNativeAd videoNativeAd;

    @VisibleForTesting
    static class HeaderVisibilityStrategy implements OnTrackedStrategy {
        @NonNull
        private final WeakReference<MoPubVideoNativeAd> mMoPubVideoNativeAd;

        HeaderVisibilityStrategy(@NonNull MoPubVideoNativeAd moPubVideoNativeAd) {
            this.mMoPubVideoNativeAd = new WeakReference<>(moPubVideoNativeAd);
        }

        public void execute() {
            MoPubVideoNativeAd moPubVideoNativeAd = (MoPubVideoNativeAd) this.mMoPubVideoNativeAd.get();
            if (moPubVideoNativeAd != null) {
                moPubVideoNativeAd.notifyAdImpressed();
            }
        }
    }

    public static class MoPubVideoNativeAd extends VideoNativeAd implements OnAudioFocusChangeListener, VastManagerListener, ProgressListener {
        /* access modifiers changed from: private */
        @NonNull
        public final Context mContext;
        /* access modifiers changed from: private */
        @NonNull
        public final CustomEventNativeListener mCustomEventNativeListener;
        /* access modifiers changed from: private */
        public boolean mEnded;
        private boolean mError;
        /* access modifiers changed from: private */
        public final long mId;
        @NonNull
        private final JSONObject mJsonObject;
        /* access modifiers changed from: private */
        public int mLatestVideoControllerState;
        /* access modifiers changed from: private */
        public boolean mLatestVisibility;
        /* access modifiers changed from: private */
        @Nullable
        public MediaLayout mMediaLayout;
        @NonNull
        private final String mMoPubClickTrackingUrl;
        /* access modifiers changed from: private */
        public boolean mMuted;
        /* access modifiers changed from: private */
        @Nullable
        public NativeVideoController mNativeVideoController;
        @NonNull
        private final NativeVideoControllerFactory mNativeVideoControllerFactory;
        /* access modifiers changed from: private */
        public boolean mNeedsPrepare;
        /* access modifiers changed from: private */
        public boolean mNeedsSeek;
        private boolean mPauseCanBeTracked;
        private boolean mResumeCanBeTracked;
        @Nullable
        private View mRootView;
        /* access modifiers changed from: private */
        @NonNull
        public final VastManager mVastManager;
        @Nullable
        VastVideoConfig mVastVideoConfig;
        @NonNull
        private final VideoResponseHeaders mVideoResponseHeaders;
        @NonNull
        private VideoState mVideoState;
        @NonNull
        private final VisibilityTracker mVideoVisibleTracking;

        enum Parameter {
            IMPRESSION_TRACKER("imptracker", true),
            CLICK_TRACKER("clktracker", true),
            TITLE("title", false),
            TEXT("text", false),
            IMAGE_URL("mainimage", false),
            ICON_URL("iconimage", false),
            CLICK_DESTINATION("clk", false),
            FALLBACK("fallback", false),
            CALL_TO_ACTION("ctatext", false),
            VAST_VIDEO("video", false),
            PRIVACY_INFORMATION_ICON_IMAGE_URL("privacyicon", false),
            PRIVACY_INFORMATION_ICON_CLICKTHROUGH_URL("privacyclkurl", false);
            
            @NonNull
            @VisibleForTesting
            static final Set<String> requiredKeys = null;
            @NonNull
            final String mName;
            final boolean mRequired;

            static {
                int i;
                Parameter[] values;
                requiredKeys = new HashSet();
                for (Parameter parameter : values()) {
                    if (parameter.mRequired) {
                        requiredKeys.add(parameter.mName);
                    }
                }
            }

            private Parameter(String str, boolean z) {
                Preconditions.checkNotNull(str);
                this.mName = str;
                this.mRequired = z;
            }

            @Nullable
            static Parameter from(@NonNull String str) {
                Parameter[] values;
                Preconditions.checkNotNull(str);
                for (Parameter parameter : values()) {
                    if (parameter.mName.equals(str)) {
                        return parameter;
                    }
                }
                return null;
            }
        }

        public enum VideoState {
            CREATED,
            LOADING,
            BUFFERING,
            PAUSED,
            PLAYING,
            PLAYING_MUTED,
            ENDED,
            FAILED_LOAD
        }

        public MoPubVideoNativeAd(@NonNull Context context, @NonNull JSONObject jSONObject, @NonNull CustomEventNativeListener customEventNativeListener, @NonNull VideoResponseHeaders videoResponseHeaders, @NonNull String str) {
            this(context, jSONObject, customEventNativeListener, videoResponseHeaders, new VisibilityTracker(context), new NativeVideoControllerFactory(), str, VastManagerFactory.create(context.getApplicationContext(), false));
        }

        @VisibleForTesting
        MoPubVideoNativeAd(@NonNull Context context, @NonNull JSONObject jSONObject, @NonNull CustomEventNativeListener customEventNativeListener, @NonNull VideoResponseHeaders videoResponseHeaders, @NonNull VisibilityTracker visibilityTracker, @NonNull NativeVideoControllerFactory nativeVideoControllerFactory, @NonNull String str, @NonNull VastManager vastManager) {
            this.mPauseCanBeTracked = false;
            this.mResumeCanBeTracked = false;
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(jSONObject);
            Preconditions.checkNotNull(customEventNativeListener);
            Preconditions.checkNotNull(videoResponseHeaders);
            Preconditions.checkNotNull(visibilityTracker);
            Preconditions.checkNotNull(nativeVideoControllerFactory);
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(vastManager);
            this.mContext = context.getApplicationContext();
            this.mJsonObject = jSONObject;
            this.mCustomEventNativeListener = customEventNativeListener;
            this.mVideoResponseHeaders = videoResponseHeaders;
            this.mNativeVideoControllerFactory = nativeVideoControllerFactory;
            this.mMoPubClickTrackingUrl = str;
            this.mId = Utils.generateUniqueId();
            this.mNeedsSeek = true;
            this.mVideoState = VideoState.CREATED;
            this.mNeedsPrepare = true;
            this.mLatestVideoControllerState = 1;
            this.mMuted = true;
            this.mVideoVisibleTracking = visibilityTracker;
            this.mVideoVisibleTracking.setVisibilityTrackerListener(new VisibilityTrackerListener() {
                public void onVisibilityChanged(List<View> list, List<View> list2) {
                    if (!list.isEmpty() && !MoPubVideoNativeAd.this.mLatestVisibility) {
                        MoPubVideoNativeAd.this.mLatestVisibility = true;
                        MoPubVideoNativeAd.this.maybeChangeState();
                    } else if (!list2.isEmpty() && MoPubVideoNativeAd.this.mLatestVisibility) {
                        MoPubVideoNativeAd.this.mLatestVisibility = false;
                        MoPubVideoNativeAd.this.maybeChangeState();
                    }
                }
            });
            this.mVastManager = vastManager;
        }

        /* access modifiers changed from: 0000 */
        public void loadAd() throws IllegalArgumentException {
            if (containsRequiredKeys(this.mJsonObject)) {
                Iterator keys = this.mJsonObject.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    Parameter from = Parameter.from(str);
                    if (from != null) {
                        try {
                            addInstanceVariable(from, this.mJsonObject.opt(str));
                        } catch (ClassCastException unused) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("JSONObject key (");
                            sb.append(str);
                            sb.append(") contained unexpected value.");
                            throw new IllegalArgumentException(sb.toString());
                        }
                    } else {
                        addExtra(str, this.mJsonObject.opt(str));
                    }
                }
                if (TextUtils.isEmpty(getPrivacyInformationIconClickThroughUrl())) {
                    setPrivacyInformationIconClickThroughUrl("https://www.mopub.com/optout/");
                }
                NativeImageHelper.preCacheImages(this.mContext, getAllImageUrls(), new ImageListener() {
                    public void onImagesCached() {
                        if (!MoPubVideoNativeAd.this.isInvalidated()) {
                            VastManager access$300 = MoPubVideoNativeAd.this.mVastManager;
                            String vastVideo = MoPubVideoNativeAd.this.getVastVideo();
                            MoPubVideoNativeAd moPubVideoNativeAd = MoPubVideoNativeAd.this;
                            access$300.prepareVastVideoConfiguration(vastVideo, moPubVideoNativeAd, null, moPubVideoNativeAd.mContext);
                        }
                    }

                    public void onImagesFailedToCache(NativeErrorCode nativeErrorCode) {
                        if (!MoPubVideoNativeAd.this.isInvalidated()) {
                            MoPubVideoNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(nativeErrorCode);
                        }
                    }
                });
                return;
            }
            throw new IllegalArgumentException("JSONObject did not contain required keys.");
        }

        public void onVastVideoConfigurationPrepared(@Nullable VastVideoConfig vastVideoConfig) {
            if (vastVideoConfig == null) {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
                return;
            }
            ArrayList arrayList = new ArrayList();
            VisibilityTrackingEvent visibilityTrackingEvent = new VisibilityTrackingEvent();
            visibilityTrackingEvent.strategy = new HeaderVisibilityStrategy(this);
            visibilityTrackingEvent.minimumPercentageVisible = this.mVideoResponseHeaders.getImpressionMinVisiblePercent();
            visibilityTrackingEvent.totalRequiredPlayTimeMs = this.mVideoResponseHeaders.getImpressionVisibleMs();
            arrayList.add(visibilityTrackingEvent);
            visibilityTrackingEvent.minimumVisiblePx = this.mVideoResponseHeaders.getImpressionVisiblePx();
            for (VastTracker vastTracker : vastVideoConfig.getImpressionTrackers()) {
                VisibilityTrackingEvent visibilityTrackingEvent2 = new VisibilityTrackingEvent();
                visibilityTrackingEvent2.strategy = new PayloadVisibilityStrategy(this.mContext, vastTracker.getContent());
                visibilityTrackingEvent2.minimumPercentageVisible = this.mVideoResponseHeaders.getImpressionMinVisiblePercent();
                visibilityTrackingEvent2.totalRequiredPlayTimeMs = this.mVideoResponseHeaders.getImpressionVisibleMs();
                arrayList.add(visibilityTrackingEvent2);
                visibilityTrackingEvent2.minimumVisiblePx = this.mVideoResponseHeaders.getImpressionVisiblePx();
            }
            this.mVastVideoConfig = vastVideoConfig;
            VideoViewabilityTracker videoViewabilityTracker = this.mVastVideoConfig.getVideoViewabilityTracker();
            if (videoViewabilityTracker != null) {
                VisibilityTrackingEvent visibilityTrackingEvent3 = new VisibilityTrackingEvent();
                visibilityTrackingEvent3.strategy = new PayloadVisibilityStrategy(this.mContext, videoViewabilityTracker.getContent());
                visibilityTrackingEvent3.minimumPercentageVisible = videoViewabilityTracker.getPercentViewable();
                visibilityTrackingEvent3.totalRequiredPlayTimeMs = videoViewabilityTracker.getViewablePlaytimeMS();
                arrayList.add(visibilityTrackingEvent3);
            }
            this.mVastVideoConfig.setPrivacyInformationIconImageUrl(getPrivacyInformationIconImageUrl());
            this.mVastVideoConfig.setPrivacyInformationIconClickthroughUrl(getPrivacyInformationIconClickThroughUrl());
            HashSet<String> hashSet = new HashSet<>();
            hashSet.add(this.mMoPubClickTrackingUrl);
            hashSet.addAll(getClickTrackers());
            ArrayList arrayList2 = new ArrayList();
            for (String vastTracker2 : hashSet) {
                arrayList2.add(new VastTracker(vastTracker2, false));
            }
            this.mVastVideoConfig.addClickTrackers(arrayList2);
            this.mVastVideoConfig.setClickThroughUrl(getClickDestinationUrl());
            this.mNativeVideoController = this.mNativeVideoControllerFactory.createForId(this.mId, this.mContext, arrayList, this.mVastVideoConfig);
            this.mCustomEventNativeListener.onNativeAdLoaded(this);
            JSONObject videoTrackers = this.mVideoResponseHeaders.getVideoTrackers();
            if (videoTrackers != null) {
                this.mVastVideoConfig.addVideoTrackers(videoTrackers);
            }
        }

        private boolean containsRequiredKeys(@NonNull JSONObject jSONObject) {
            Preconditions.checkNotNull(jSONObject);
            HashSet hashSet = new HashSet();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                hashSet.add(keys.next());
            }
            return hashSet.containsAll(Parameter.requiredKeys);
        }

        private void addInstanceVariable(@NonNull Parameter parameter, @Nullable Object obj) throws ClassCastException {
            Preconditions.checkNotNull(parameter);
            Preconditions.checkNotNull(obj);
            try {
                switch (parameter) {
                    case IMPRESSION_TRACKER:
                        addImpressionTrackers(obj);
                        return;
                    case TITLE:
                        setTitle((String) obj);
                        return;
                    case TEXT:
                        setText((String) obj);
                        return;
                    case IMAGE_URL:
                        setMainImageUrl((String) obj);
                        return;
                    case ICON_URL:
                        setIconImageUrl((String) obj);
                        return;
                    case CLICK_DESTINATION:
                        setClickDestinationUrl((String) obj);
                        return;
                    case CLICK_TRACKER:
                        parseClickTrackers(obj);
                        return;
                    case CALL_TO_ACTION:
                        setCallToAction((String) obj);
                        return;
                    case VAST_VIDEO:
                        setVastVideo((String) obj);
                        return;
                    case PRIVACY_INFORMATION_ICON_IMAGE_URL:
                        setPrivacyInformationIconImageUrl((String) obj);
                        return;
                    case PRIVACY_INFORMATION_ICON_CLICKTHROUGH_URL:
                        setPrivacyInformationIconClickThroughUrl((String) obj);
                        return;
                    default:
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unable to add JSON key to internal mapping: ");
                        sb.append(parameter.mName);
                        MoPubLog.d(sb.toString());
                        return;
                }
            } catch (ClassCastException e) {
                if (!parameter.mRequired) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Ignoring class cast exception for optional key: ");
                    sb2.append(parameter.mName);
                    MoPubLog.d(sb2.toString());
                    return;
                }
                throw e;
            }
        }

        private void parseClickTrackers(@NonNull Object obj) {
            if (obj instanceof JSONArray) {
                addClickTrackers(obj);
            } else {
                addClickTracker((String) obj);
            }
        }

        public void render(@NonNull MediaLayout mediaLayout) {
            Preconditions.checkNotNull(mediaLayout);
            this.mVideoVisibleTracking.addView(this.mRootView, mediaLayout, this.mVideoResponseHeaders.getPlayVisiblePercent(), this.mVideoResponseHeaders.getPauseVisiblePercent(), this.mVideoResponseHeaders.getImpressionVisiblePx());
            this.mMediaLayout = mediaLayout;
            this.mMediaLayout.initForVideo();
            this.mMediaLayout.setSurfaceTextureListener(new SurfaceTextureListener() {
                public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                }

                public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                }

                public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                    MoPubVideoNativeAd.this.mNativeVideoController.setListener(MoPubVideoNativeAd.this);
                    MoPubVideoNativeAd.this.mNativeVideoController.setOnAudioFocusChangeListener(MoPubVideoNativeAd.this);
                    MoPubVideoNativeAd.this.mNativeVideoController.setProgressListener(MoPubVideoNativeAd.this);
                    MoPubVideoNativeAd.this.mNativeVideoController.setTextureView(MoPubVideoNativeAd.this.mMediaLayout.getTextureView());
                    MoPubVideoNativeAd.this.mMediaLayout.resetProgress();
                    long duration = MoPubVideoNativeAd.this.mNativeVideoController.getDuration();
                    long currentPosition = MoPubVideoNativeAd.this.mNativeVideoController.getCurrentPosition();
                    if (MoPubVideoNativeAd.this.mLatestVideoControllerState == 4 || (duration > 0 && duration - currentPosition < 750)) {
                        MoPubVideoNativeAd.this.mEnded = true;
                    }
                    if (MoPubVideoNativeAd.this.mNeedsPrepare) {
                        MoPubVideoNativeAd.this.mNeedsPrepare = false;
                        MoPubVideoNativeAd.this.mNativeVideoController.prepare(MoPubVideoNativeAd.this);
                    }
                    MoPubVideoNativeAd.this.mNeedsSeek = true;
                    MoPubVideoNativeAd.this.maybeChangeState();
                }

                public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                    MoPubVideoNativeAd.this.mNeedsPrepare = true;
                    MoPubVideoNativeAd.this.mNativeVideoController.release(MoPubVideoNativeAd.this);
                    MoPubVideoNativeAd.this.applyState(VideoState.PAUSED);
                    return true;
                }
            });
            this.mMediaLayout.setPlayButtonClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MoPubVideoNativeAd.this.mMediaLayout.resetProgress();
                    MoPubVideoNativeAd.this.mNativeVideoController.seekTo(0);
                    MoPubVideoNativeAd.this.mEnded = false;
                    MoPubVideoNativeAd.this.mNeedsSeek = false;
                }
            });
            this.mMediaLayout.setMuteControlClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MoPubVideoNativeAd moPubVideoNativeAd = MoPubVideoNativeAd.this;
                    moPubVideoNativeAd.mMuted = !moPubVideoNativeAd.mMuted;
                    MoPubVideoNativeAd.this.maybeChangeState();
                }
            });
            this.mMediaLayout.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MoPubVideoNativeAd.this.prepareToLeaveView();
                    MoPubVideoNativeAd.this.mNativeVideoController.triggerImpressionTrackers();
                    MraidVideoPlayerActivity.startNativeVideo(MoPubVideoNativeAd.this.mContext, MoPubVideoNativeAd.this.mId, MoPubVideoNativeAd.this.mVastVideoConfig);
                }
            });
            if (this.mNativeVideoController.getPlaybackState() == 5) {
                this.mNativeVideoController.prepare(this);
            }
            applyState(VideoState.PAUSED);
        }

        public void prepare(@NonNull View view) {
            Preconditions.checkNotNull(view);
            this.mRootView = view;
            this.mRootView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MoPubVideoNativeAd.this.prepareToLeaveView();
                    MoPubVideoNativeAd.this.mNativeVideoController.triggerImpressionTrackers();
                    MoPubVideoNativeAd.this.mNativeVideoController.handleCtaClick(MoPubVideoNativeAd.this.mContext);
                }
            });
        }

        public void clear(@NonNull View view) {
            Preconditions.checkNotNull(view);
            this.mNativeVideoController.clear();
            cleanUpMediaLayout();
        }

        public void destroy() {
            invalidate();
            cleanUpMediaLayout();
            this.mNativeVideoController.setPlayWhenReady(false);
            this.mNativeVideoController.release(this);
            NativeVideoController.remove(this.mId);
            this.mVideoVisibleTracking.destroy();
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

        public void updateProgress(int i) {
            this.mMediaLayout.updateProgress(i);
        }

        public void onAudioFocusChange(int i) {
            if (i == -1 || i == -2) {
                this.mMuted = true;
                maybeChangeState();
            } else if (i == -3) {
                this.mNativeVideoController.setAudioVolume(0.3f);
            } else if (i == 1) {
                this.mNativeVideoController.setAudioVolume(1.0f);
                maybeChangeState();
            }
        }

        private void cleanUpMediaLayout() {
            MediaLayout mediaLayout = this.mMediaLayout;
            if (mediaLayout != null) {
                mediaLayout.setMode(Mode.IMAGE);
                this.mMediaLayout.setSurfaceTextureListener(null);
                this.mMediaLayout.setPlayButtonClickListener(null);
                this.mMediaLayout.setMuteControlClickListener(null);
                this.mMediaLayout.setOnClickListener(null);
                this.mVideoVisibleTracking.removeView(this.mMediaLayout);
                this.mMediaLayout = null;
            }
        }

        /* access modifiers changed from: private */
        public void prepareToLeaveView() {
            this.mNeedsSeek = true;
            this.mNeedsPrepare = true;
            this.mNativeVideoController.setListener(null);
            this.mNativeVideoController.setOnAudioFocusChangeListener(null);
            this.mNativeVideoController.setProgressListener(null);
            this.mNativeVideoController.clear();
            applyState(VideoState.PAUSED, true);
        }

        /* access modifiers changed from: private */
        public void maybeChangeState() {
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
                } else if (i == 4) {
                    this.mEnded = true;
                    videoState = VideoState.ENDED;
                } else if (i == 3) {
                    videoState = this.mLatestVisibility ? this.mMuted ? VideoState.PLAYING_MUTED : VideoState.PLAYING : VideoState.PAUSED;
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
            if (this.mVastVideoConfig != null && this.mNativeVideoController != null && this.mMediaLayout != null) {
                VideoState videoState2 = this.mVideoState;
                if (videoState2 != videoState) {
                    this.mVideoState = videoState;
                    switch (videoState) {
                        case FAILED_LOAD:
                            this.mVastVideoConfig.handleError(this.mContext, null, 0);
                            this.mNativeVideoController.setAppAudioEnabled(false);
                            this.mMediaLayout.setMode(Mode.IMAGE);
                            break;
                        case CREATED:
                        case LOADING:
                            this.mNativeVideoController.setPlayWhenReady(true);
                            this.mMediaLayout.setMode(Mode.LOADING);
                            break;
                        case BUFFERING:
                            this.mNativeVideoController.setPlayWhenReady(true);
                            this.mMediaLayout.setMode(Mode.BUFFERING);
                            break;
                        case PAUSED:
                            if (z) {
                                this.mResumeCanBeTracked = false;
                            }
                            if (!z) {
                                this.mNativeVideoController.setAppAudioEnabled(false);
                                if (this.mPauseCanBeTracked) {
                                    TrackingRequest.makeVastTrackingHttpRequest(this.mVastVideoConfig.getPauseTrackers(), null, Integer.valueOf((int) this.mNativeVideoController.getCurrentPosition()), null, this.mContext);
                                    this.mPauseCanBeTracked = false;
                                    this.mResumeCanBeTracked = true;
                                }
                            }
                            this.mNativeVideoController.setPlayWhenReady(false);
                            this.mMediaLayout.setMode(Mode.PAUSED);
                            break;
                        case PLAYING:
                            handleResumeTrackersAndSeek(videoState2);
                            this.mNativeVideoController.setPlayWhenReady(true);
                            this.mNativeVideoController.setAudioEnabled(true);
                            this.mNativeVideoController.setAppAudioEnabled(true);
                            this.mMediaLayout.setMode(Mode.PLAYING);
                            this.mMediaLayout.setMuteState(MuteState.UNMUTED);
                            break;
                        case PLAYING_MUTED:
                            handleResumeTrackersAndSeek(videoState2);
                            this.mNativeVideoController.setPlayWhenReady(true);
                            this.mNativeVideoController.setAudioEnabled(false);
                            this.mNativeVideoController.setAppAudioEnabled(false);
                            this.mMediaLayout.setMode(Mode.PLAYING);
                            this.mMediaLayout.setMuteState(MuteState.MUTED);
                            break;
                        case ENDED:
                            if (this.mNativeVideoController.hasFinalFrame()) {
                                this.mMediaLayout.setMainImageDrawable(this.mNativeVideoController.getFinalFrame());
                            }
                            this.mPauseCanBeTracked = false;
                            this.mResumeCanBeTracked = false;
                            this.mVastVideoConfig.handleComplete(this.mContext, 0);
                            this.mNativeVideoController.setAppAudioEnabled(false);
                            this.mMediaLayout.setMode(Mode.FINISHED);
                            this.mMediaLayout.updateProgress(1000);
                            break;
                    }
                }
            }
        }

        private void handleResumeTrackersAndSeek(VideoState videoState) {
            if (!(!this.mResumeCanBeTracked || videoState == VideoState.PLAYING || videoState == VideoState.PLAYING_MUTED)) {
                TrackingRequest.makeVastTrackingHttpRequest(this.mVastVideoConfig.getResumeTrackers(), null, Integer.valueOf((int) this.mNativeVideoController.getCurrentPosition()), null, this.mContext);
                this.mResumeCanBeTracked = false;
            }
            this.mPauseCanBeTracked = true;
            if (this.mNeedsSeek) {
                this.mNeedsSeek = false;
                NativeVideoController nativeVideoController = this.mNativeVideoController;
                nativeVideoController.seekTo(nativeVideoController.getCurrentPosition());
            }
        }

        private boolean isImageKey(@Nullable String str) {
            return str != null && str.toLowerCase(Locale.US).endsWith("image");
        }

        @NonNull
        private List<String> getExtrasImageUrls() {
            ArrayList arrayList = new ArrayList(getExtras().size());
            for (Entry entry : getExtras().entrySet()) {
                if (isImageKey((String) entry.getKey()) && (entry.getValue() instanceof String)) {
                    arrayList.add((String) entry.getValue());
                }
            }
            return arrayList;
        }

        @NonNull
        private List<String> getAllImageUrls() {
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(getMainImageUrl())) {
                arrayList.add(getMainImageUrl());
            }
            if (!TextUtils.isEmpty(getIconImageUrl())) {
                arrayList.add(getIconImageUrl());
            }
            if (!TextUtils.isEmpty(getPrivacyInformationIconImageUrl())) {
                arrayList.add(getPrivacyInformationIconImageUrl());
            }
            arrayList.addAll(getExtrasImageUrls());
            return arrayList;
        }
    }

    @VisibleForTesting
    static class NativeVideoControllerFactory {
        NativeVideoControllerFactory() {
        }

        public NativeVideoController createForId(long j, @NonNull Context context, @NonNull List<VisibilityTrackingEvent> list, @NonNull VastVideoConfig vastVideoConfig) {
            return NativeVideoController.createForId(j, context, list, vastVideoConfig);
        }
    }

    @VisibleForTesting
    static class PayloadVisibilityStrategy implements OnTrackedStrategy {
        @NonNull
        private final Context mContext;
        @NonNull
        private final String mUrl;

        PayloadVisibilityStrategy(@NonNull Context context, @NonNull String str) {
            this.mContext = context.getApplicationContext();
            this.mUrl = str;
        }

        public void execute() {
            TrackingRequest.makeTrackingHttpRequest(this.mUrl, this.mContext);
        }
    }

    @VisibleForTesting
    static class VideoResponseHeaders {
        private boolean mHeadersAreValid;
        private int mImpressionMinVisiblePercent;
        private int mImpressionVisibleMs;
        private Integer mImpressionVisiblePx;
        private int mMaxBufferMs;
        private int mPauseVisiblePercent;
        private int mPlayVisiblePercent;
        private JSONObject mVideoTrackers;

        VideoResponseHeaders(@NonNull Map<String, String> map) {
            try {
                this.mPlayVisiblePercent = Integer.parseInt((String) map.get(DataKeys.PLAY_VISIBLE_PERCENT));
                this.mPauseVisiblePercent = Integer.parseInt((String) map.get(DataKeys.PAUSE_VISIBLE_PERCENT));
                this.mImpressionVisibleMs = Integer.parseInt((String) map.get(DataKeys.IMPRESSION_VISIBLE_MS));
                this.mMaxBufferMs = Integer.parseInt((String) map.get(DataKeys.MAX_BUFFER_MS));
                this.mHeadersAreValid = true;
            } catch (NumberFormatException unused) {
                this.mHeadersAreValid = false;
            }
            String str = (String) map.get(DataKeys.IMPRESSION_MIN_VISIBLE_PX);
            if (!TextUtils.isEmpty(str)) {
                try {
                    this.mImpressionVisiblePx = Integer.valueOf(Integer.parseInt(str));
                } catch (NumberFormatException unused2) {
                    MoPubLog.d("Unable to parse impression min visible px from server extras.");
                }
            }
            try {
                this.mImpressionMinVisiblePercent = Integer.parseInt((String) map.get(DataKeys.IMPRESSION_MIN_VISIBLE_PERCENT));
            } catch (NumberFormatException unused3) {
                MoPubLog.d("Unable to parse impression min visible percent from server extras.");
                Integer num = this.mImpressionVisiblePx;
                if (num == null || num.intValue() < 0) {
                    this.mHeadersAreValid = false;
                }
            }
            String str2 = (String) map.get(DataKeys.VIDEO_TRACKERS_KEY);
            if (!TextUtils.isEmpty(str2)) {
                try {
                    this.mVideoTrackers = new JSONObject(str2);
                } catch (JSONException e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to parse video trackers to JSON: ");
                    sb.append(str2);
                    MoPubLog.d(sb.toString(), e);
                    this.mVideoTrackers = null;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean hasValidHeaders() {
            return this.mHeadersAreValid;
        }

        /* access modifiers changed from: 0000 */
        public int getPlayVisiblePercent() {
            return this.mPlayVisiblePercent;
        }

        /* access modifiers changed from: 0000 */
        public int getPauseVisiblePercent() {
            return this.mPauseVisiblePercent;
        }

        /* access modifiers changed from: 0000 */
        public int getImpressionMinVisiblePercent() {
            return this.mImpressionMinVisiblePercent;
        }

        /* access modifiers changed from: 0000 */
        public int getImpressionVisibleMs() {
            return this.mImpressionVisibleMs;
        }

        /* access modifiers changed from: 0000 */
        @Nullable
        public Integer getImpressionVisiblePx() {
            return this.mImpressionVisiblePx;
        }

        /* access modifiers changed from: 0000 */
        public JSONObject getVideoTrackers() {
            return this.mVideoTrackers;
        }
    }

    /* access modifiers changed from: protected */
    public void loadNativeAd(@NonNull Context context, @NonNull CustomEventNativeListener customEventNativeListener, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) {
        Object obj = map.get(DataKeys.JSON_BODY_KEY);
        if (!(obj instanceof JSONObject)) {
            customEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
            return;
        }
        map.get(DataKeys.EVENT_DETAILS);
        VideoResponseHeaders videoResponseHeaders = new VideoResponseHeaders(map2);
        if (!videoResponseHeaders.hasValidHeaders()) {
            customEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
            return;
        }
        Object obj2 = map.get(DataKeys.CLICK_TRACKING_URL_KEY);
        if (obj2 instanceof String) {
            String str = (String) obj2;
            if (!TextUtils.isEmpty(str)) {
                MoPubVideoNativeAd moPubVideoNativeAd = new MoPubVideoNativeAd(context, (JSONObject) obj, customEventNativeListener, videoResponseHeaders, str);
                this.videoNativeAd = moPubVideoNativeAd;
                try {
                    this.videoNativeAd.loadAd();
                } catch (IllegalArgumentException unused) {
                    customEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
                }
                return;
            }
        }
        customEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        MoPubVideoNativeAd moPubVideoNativeAd = this.videoNativeAd;
        if (moPubVideoNativeAd != null) {
            moPubVideoNativeAd.invalidate();
        }
    }
}
