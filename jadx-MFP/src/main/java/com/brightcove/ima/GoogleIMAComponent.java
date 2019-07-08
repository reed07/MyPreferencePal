package com.brightcove.ima;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout.LayoutParams;
import com.brightcove.player.event.AbstractComponent;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.model.CuePoint;
import com.brightcove.player.model.CuePoint.PositionType;
import com.brightcove.player.model.Video;
import com.brightcove.player.util.Objects;
import com.brightcove.player.view.BaseVideoView;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventListener;
import com.google.ads.interactivemedia.v3.api.AdPodInfo;
import com.google.ads.interactivemedia.v3.api.AdsLoader;
import com.google.ads.interactivemedia.v3.api.AdsLoader.AdsLoadedListener;
import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.ImaSdkFactory;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ListensFor(events = {"activityCreated", "activityPaused", "activityResumed", "activitySaveInstanceState", "activityStarted", "completed", "cuePoint", "fragmentCreatedView", "fragmentPaused", "fragmentResumed", "fragmentSaveInstanceState", "fragmentStarted", "play", "progress", "seekTo", "willChangeVideo", "adsManagerLoaded", "didFailToPlayAd"})
@Emits(events = {"adBreakCompleted", "adCompleted", "adError", "adPaused", "adProgress", "adResumed", "adBreakStarted", "adStarted", "completed", "error", "pause", "play", "willInterruptContent", "willResumeContent", "registerPlugin", "setCuePoints", "adsManagerLoaded", "adsRequestForVideo", "didFailToPlayAd", "showSeekControls", "hideSeekControls"})
public class GoogleIMAComponent extends AbstractComponent implements AdErrorListener, AdEventListener, AdsLoadedListener, ContentProgressProvider {
    /* access modifiers changed from: private */
    public static final String TAG = "GoogleIMAComponent";
    /* access modifiers changed from: private */
    public int adPlayheadPosition;
    /* access modifiers changed from: private */
    public boolean adWasPlaying;
    /* access modifiers changed from: private */
    public AdsLoader adsLoader;
    /* access modifiers changed from: private */
    public AdsManager adsManager;
    /* access modifiers changed from: private */
    public AdsManagerState adsManagerState;
    private AdsRenderingSettings adsRenderingSettings;
    /* access modifiers changed from: private */
    public BaseVideoView baseVideoView;
    /* access modifiers changed from: private */
    public ArrayList<CuePoint> cuePoints;
    /* access modifiers changed from: private */
    public int currentAdIndex;
    /* access modifiers changed from: private */
    public ArrayList<AdsRequest> currentAdsRequests;
    /* access modifiers changed from: private */
    public int duration;
    private GoogleIMAVideoAdPlayer googleIMAVideoAdPlayer;
    /* access modifiers changed from: private */
    public boolean isPresentingAd;
    /* access modifiers changed from: private */
    public boolean isSwitchingVideos;
    private boolean lifecyclePaused;
    /* access modifiers changed from: private */
    public int mTargetSeekWithoutAdsPosition;
    /* access modifiers changed from: private */
    public Event originalEvent;
    /* access modifiers changed from: private */
    public int playheadPosition;
    private ImaSdkFactory sdkFactory;
    private ImaSdkSettings sdkSettings;
    /* access modifiers changed from: private */
    public boolean useAdRules;
    /* access modifiers changed from: private */
    public Video video;
    /* access modifiers changed from: private */
    public boolean videoHasCompleted;

    private enum AdsManagerState {
        DESTROYED,
        LOADING,
        LOADED,
        INITIALIZED,
        STARTED
    }

    private class OnActivityCreatedListener implements EventListener {
        private OnActivityCreatedListener() {
        }

        @Default
        public void processEvent(Event event) {
            GoogleIMAComponent.this.onCreate(event);
        }
    }

    private class OnActivityPausedListener implements EventListener {
        private OnActivityPausedListener() {
        }

        @Default
        public void processEvent(Event event) {
            GoogleIMAComponent.this.onPause();
        }
    }

    private class OnActivityResumedListener implements EventListener {
        private OnActivityResumedListener() {
        }

        @Default
        public void processEvent(Event event) {
            GoogleIMAComponent.this.onResume();
        }
    }

    private class OnActivitySaveInstanceStateListener implements EventListener {
        private OnActivitySaveInstanceStateListener() {
        }

        @Default
        public void processEvent(Event event) {
            GoogleIMAComponent.this.onSaveInstanceState(event);
        }
    }

    private class OnActivityStartedListener implements EventListener {
        private OnActivityStartedListener() {
        }

        @Default
        public void processEvent(Event event) {
            GoogleIMAComponent.this.onStart();
        }
    }

    private class OnAdsRequestForVideoListener implements EventListener {
        private OnAdsRequestForVideoListener() {
        }

        public void processEvent(Event event) {
            Log.v(GoogleIMAComponent.TAG, "OnAdsRequestForVideoListener");
            ArrayList arrayList = (ArrayList) event.properties.get("adsRequests");
            String access$1600 = GoogleIMAComponent.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("OnAdsRequestForVideoListener: adsRequests = ");
            sb.append(arrayList);
            Log.v(access$1600, sb.toString());
            if (arrayList != null && !arrayList.isEmpty()) {
                GoogleIMAComponent.this.currentAdsRequests = arrayList;
                GoogleIMAComponent.this.currentAdIndex = 0;
                GoogleIMAComponent.this.isPresentingAd = false;
                GoogleIMAComponent.this.isSwitchingVideos = false;
                if (!GoogleIMAComponent.this.useAdRules) {
                    GoogleIMAComponent.this.adsLoader.contentComplete();
                }
                AdsRequest adsRequest = (AdsRequest) arrayList.get(GoogleIMAComponent.this.currentAdIndex);
                adsRequest.setContentProgressProvider(GoogleIMAComponent.this);
                String access$16002 = GoogleIMAComponent.TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("OnAdsRequestForVideoListener: adsRequest = ");
                sb2.append(adsRequest);
                Log.v(access$16002, sb2.toString());
                GoogleIMAComponent.this.adsLoader.requestAds(adsRequest);
            } else if (GoogleIMAComponent.this.originalEvent != null) {
                GoogleIMAComponent.this.eventEmitter.emit(GoogleIMAComponent.this.originalEvent.getType(), GoogleIMAComponent.this.originalEvent.properties);
                GoogleIMAComponent.this.originalEvent = null;
            }
        }
    }

    private class OnCompletedListener implements EventListener {
        private OnCompletedListener() {
        }

        public void processEvent(Event event) {
            Log.v(GoogleIMAComponent.TAG, "OnCompletedListener");
            GoogleIMAComponent.this.videoHasCompleted = true;
            if (GoogleIMAComponent.this.adsManager != null && !event.properties.containsKey(AbstractEvent.SKIP_CUE_POINTS) && GoogleIMAComponent.this.adsManager.getAdCuePoints().contains(Float.valueOf(-1.0f))) {
                GoogleIMAComponent.this.originalEvent = event;
                GoogleIMAComponent.this.originalEvent.properties.put(AbstractEvent.SKIP_CUE_POINTS, Boolean.valueOf(true));
                String access$1600 = GoogleIMAComponent.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("original event: ");
                sb.append(GoogleIMAComponent.this.originalEvent);
                Log.v(access$1600, sb.toString());
                event.stopPropagation();
                event.preventDefault();
            }
            GoogleIMAComponent.this.adsLoader.contentComplete();
        }
    }

    private class OnCuePointListener implements EventListener {
        private OnCuePointListener() {
        }

        public void processEvent(Event event) {
            if (!GoogleIMAComponent.this.useAdRules) {
                GoogleIMAComponent.this.resetAdsLoader();
                if (event.getIntegerProperty("startTime") <= event.getIntegerProperty(AbstractEvent.END_TIME)) {
                    GoogleIMAComponent.this.originalEvent = (Event) event.properties.get(AbstractEvent.ORIGINAL_EVENT);
                    String access$1600 = GoogleIMAComponent.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("original event: ");
                    sb.append(GoogleIMAComponent.this.originalEvent);
                    Log.v(access$1600, sb.toString());
                    event.preventDefault();
                    GoogleIMAComponent.this.cuePoints = (ArrayList) event.properties.get(AbstractEvent.CUE_POINTS);
                    GoogleIMAComponent.this.internalInitializeAdsRequests();
                }
            }
        }
    }

    private class OnFragmentCreatedViewListener implements EventListener {
        private OnFragmentCreatedViewListener() {
        }

        @Default
        public void processEvent(Event event) {
            GoogleIMAComponent.this.onCreate(event);
        }
    }

    private class OnFragmentPausedListener implements EventListener {
        private OnFragmentPausedListener() {
        }

        @Default
        public void processEvent(Event event) {
            GoogleIMAComponent.this.onPause();
        }
    }

    private class OnFragmentResumedListener implements EventListener {
        private OnFragmentResumedListener() {
        }

        @Default
        public void processEvent(Event event) {
            GoogleIMAComponent.this.onResume();
        }
    }

    private class OnFragmentSaveInstanceStateListener implements EventListener {
        private OnFragmentSaveInstanceStateListener() {
        }

        @Default
        public void processEvent(Event event) {
            GoogleIMAComponent.this.onSaveInstanceState(event);
        }
    }

    private class OnFragmentStartedListener implements EventListener {
        private OnFragmentStartedListener() {
        }

        @Default
        public void processEvent(Event event) {
            GoogleIMAComponent.this.onStart();
        }
    }

    private class OnPlayListener implements EventListener {
        private OnPlayListener() {
        }

        public void processEvent(final Event event) {
            String access$1600 = GoogleIMAComponent.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("isPresentingAd = ");
            sb.append(GoogleIMAComponent.this.isPresentingAd);
            sb.append(", useAdRules = ");
            sb.append(GoogleIMAComponent.this.useAdRules);
            sb.append(", adsManagerState = ");
            sb.append(GoogleIMAComponent.this.adsManagerState);
            Log.v(access$1600, sb.toString());
            if (GoogleIMAComponent.this.isPresentingAd) {
                event.stopPropagation();
                event.preventDefault();
            } else if (GoogleIMAComponent.this.useAdRules) {
                if (GoogleIMAComponent.this.adsManager != null && GoogleIMAComponent.this.adsManagerState == AdsManagerState.LOADED && !event.properties.containsKey(AbstractEvent.SKIP_CUE_POINTS)) {
                    GoogleIMAComponent.this.initializeAdsManager();
                    GoogleIMAComponent.this.adsManagerState = AdsManagerState.INITIALIZED;
                    event.stopPropagation();
                    event.preventDefault();
                } else if (GoogleIMAComponent.this.adsManagerState == AdsManagerState.LOADING) {
                    GoogleIMAComponent.this.eventEmitter.once("adsManagerLoaded", new EventListener() {
                        public void processEvent(Event event) {
                            GoogleIMAComponent.this.eventEmitter.emit(event.getType(), event.properties);
                        }
                    });
                    GoogleIMAComponent.this.eventEmitter.once("didFailToPlayAd", new EventListener() {
                        public void processEvent(Event event) {
                            GoogleIMAComponent.this.eventEmitter.emit(event.getType(), event.properties);
                        }
                    });
                    event.stopPropagation();
                    event.preventDefault();
                }
                GoogleIMAComponent.this.originalEvent = event;
            }
            GoogleIMAComponent.this.videoHasCompleted = false;
        }
    }

    private class OnProgressListener implements EventListener {
        private OnProgressListener() {
        }

        public void processEvent(Event event) {
            GoogleIMAComponent.this.duration = event.getIntegerProperty("duration");
            GoogleIMAComponent.this.playheadPosition = event.getIntegerProperty(AbstractEvent.PLAYHEAD_POSITION);
        }
    }

    private class OnSeekToListener implements EventListener {
        private OnSeekToListener() {
        }

        public void processEvent(Event event) {
            if (GoogleIMAComponent.this.isPresentingAd) {
                event.stopPropagation();
                event.preventDefault();
            }
            int intValue = ((Integer) event.properties.get(AbstractEvent.SEEK_POSITION)).intValue();
            if (intValue <= 0 || !GoogleIMAComponent.this.baseVideoView.getPlaybackController().isAdsDisabled()) {
                GoogleIMAComponent.this.mTargetSeekWithoutAdsPosition = -1;
            } else {
                GoogleIMAComponent.this.mTargetSeekWithoutAdsPosition = intValue;
            }
        }
    }

    private class OnWillChangeVideoListener implements EventListener {
        private OnWillChangeVideoListener() {
        }

        @Default
        public void processEvent(Event event) {
            if (GoogleIMAComponent.this.isPresentingAd && !GoogleIMAComponent.this.isSwitchingVideos) {
                GoogleIMAComponent.this.isSwitchingVideos = true;
                GoogleIMAComponent.this.adsManager.pause();
                GoogleIMAComponent.this.willResumeContent();
            }
            GoogleIMAComponent.this.playheadPosition = -1;
            GoogleIMAComponent.this.duration = -1;
            GoogleIMAComponent.this.mTargetSeekWithoutAdsPosition = -1;
            if (GoogleIMAComponent.this.adsManager != null) {
                GoogleIMAComponent.this.adsManager.destroy();
            }
            GoogleIMAComponent.this.adsManager = null;
            GoogleIMAComponent.this.adPlayheadPosition = -1;
            GoogleIMAComponent.this.adWasPlaying = false;
            GoogleIMAComponent.this.adsManagerState = AdsManagerState.DESTROYED;
            GoogleIMAComponent.this.isPresentingAd = false;
            Video video = (Video) event.properties.get(AbstractEvent.NEXT_VIDEO);
            if (video != null) {
                GoogleIMAComponent.this.video = video;
                if (GoogleIMAComponent.this.useAdRules) {
                    GoogleIMAComponent.this.internalInitializeAdsRequests();
                }
            }
        }
    }

    public GoogleIMAComponent(BaseVideoView baseVideoView2, EventEmitter eventEmitter) {
        this(baseVideoView2, eventEmitter, false, null, null);
    }

    public GoogleIMAComponent(BaseVideoView baseVideoView2, EventEmitter eventEmitter, boolean z, ImaSdkSettings imaSdkSettings, AdsRenderingSettings adsRenderingSettings2) {
        super(eventEmitter, GoogleIMAComponent.class);
        this.mTargetSeekWithoutAdsPosition = -1;
        this.baseVideoView = baseVideoView2;
        this.useAdRules = z;
        this.adsRenderingSettings = adsRenderingSettings2;
        this.sdkFactory = ImaSdkFactory.getInstance();
        if (imaSdkSettings != null) {
            this.sdkSettings = imaSdkSettings;
        } else {
            this.sdkSettings = this.sdkFactory.createImaSdkSettings();
        }
        checkPlayerTypeAndVersion(this.sdkSettings);
        resetAdsLoader();
        addListener(EventType.CUE_POINT, new OnCuePointListener());
        addListener(EventType.WILL_CHANGE_VIDEO, new OnWillChangeVideoListener());
        addListener("completed", new OnCompletedListener());
        addListener(EventType.PLAY, new OnPlayListener());
        addListener("progress", new OnProgressListener());
        addListener(EventType.SEEK_TO, new OnSeekToListener());
        addListener(EventType.ACTIVITY_CREATED, new OnActivityCreatedListener());
        addListener(EventType.ACTIVITY_PAUSED, new OnActivityPausedListener());
        addListener(EventType.ACTIVITY_RESUMED, new OnActivityResumedListener());
        addListener(EventType.ACTIVITY_SAVE_INSTANCE_STATE, new OnActivitySaveInstanceStateListener());
        addListener(EventType.ACTIVITY_STARTED, new OnActivityStartedListener());
        addListener(EventType.FRAGMENT_CREATED_VIEW, new OnFragmentCreatedViewListener());
        addListener(EventType.FRAGMENT_PAUSED, new OnFragmentPausedListener());
        addListener(EventType.FRAGMENT_RESUMED, new OnFragmentResumedListener());
        addListener(EventType.FRAGMENT_STARTED, new OnFragmentStartedListener());
        addListener(EventType.FRAGMENT_SAVE_INSTANCE_STATE, new OnFragmentSaveInstanceStateListener());
        this.googleIMAVideoAdPlayer = new GoogleIMAVideoAdPlayer(baseVideoView2);
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractEvent.PLUGIN_NAME, "ima");
        eventEmitter.emit(EventType.REGISTER_PLUGIN, hashMap);
    }

    private void checkPlayerTypeAndVersion(ImaSdkSettings imaSdkSettings) {
        Objects.requireNonNull(imaSdkSettings, "settings must not be null");
        String playerType = imaSdkSettings.getPlayerType();
        if (playerType == null || playerType.trim().isEmpty()) {
            imaSdkSettings.setPlayerType(this.baseVideoView.getContext().getString(R.string.ima_player_type));
        }
        String playerVersion = imaSdkSettings.getPlayerVersion();
        if (playerVersion == null || playerVersion.trim().isEmpty()) {
            imaSdkSettings.setPlayerVersion(this.baseVideoView.getContext().getString(R.string.ima_player_version));
        }
    }

    public GoogleIMAVideoAdPlayer getVideoAdPlayer() {
        return this.googleIMAVideoAdPlayer;
    }

    /* access modifiers changed from: private */
    public void internalInitializeAdsRequests() {
        if (!EdgeTask.FREE.equals(this.video.getProperties().get(EdgeTask.ECONOMICS))) {
            if (this.useAdRules) {
                this.adsManagerState = AdsManagerState.LOADING;
            }
            this.currentAdsRequests = null;
            this.currentAdIndex = -1;
            HashMap hashMap = new HashMap();
            hashMap.put("video", this.video);
            ArrayList<CuePoint> arrayList = this.cuePoints;
            if (arrayList != null) {
                hashMap.put(AbstractEvent.CUE_POINTS, arrayList);
            }
            this.eventEmitter.request("adsRequestForVideo", hashMap, new OnAdsRequestForVideoListener());
        }
    }

    /* access modifiers changed from: private */
    public void willResumeContent() {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("willResumeContent: originalEvent = ");
        sb.append(this.originalEvent);
        Log.d(str, sb.toString());
        this.isPresentingAd = false;
        this.baseVideoView.removeView(this.googleIMAVideoAdPlayer);
        this.currentAdsRequests = null;
        HashMap hashMap = new HashMap();
        if (!this.isSwitchingVideos) {
            if (this.originalEvent == null && !this.videoHasCompleted) {
                this.originalEvent = new Event(EventType.PLAY);
                this.originalEvent.properties.put(AbstractEvent.SKIP_CUE_POINTS, Boolean.valueOf(true));
            }
            hashMap.put(AbstractEvent.ORIGINAL_EVENT, this.originalEvent);
        }
        this.eventEmitter.emit(EventType.AD_BREAK_COMPLETED);
        this.eventEmitter.emit(EventType.WILL_RESUME_CONTENT, hashMap);
        this.eventEmitter.emit(EventType.SHOW_SEEK_CONTROLS);
        this.originalEvent = null;
    }

    public void onAdError(AdErrorEvent adErrorEvent) {
        Log.e(TAG, adErrorEvent.getError().getMessage());
        if (this.useAdRules) {
            this.adsManagerState = AdsManagerState.DESTROYED;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("error", adErrorEvent.getError());
        hashMap.put(AbstractEvent.AD_ID, this.googleIMAVideoAdPlayer.getAdId());
        hashMap.put("adTitle", this.googleIMAVideoAdPlayer.getAdTitle());
        this.eventEmitter.emit("didFailToPlayAd", hashMap);
        this.eventEmitter.emit("error", hashMap);
        this.eventEmitter.emit(EventType.AD_ERROR, hashMap);
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onAdError: isSwitchingVideos = ");
        sb.append(this.isSwitchingVideos);
        sb.append(", isPresentingAd = ");
        sb.append(this.isPresentingAd);
        sb.append(", originalEvent = ");
        sb.append(this.originalEvent);
        sb.append(", useAdRules = ");
        sb.append(this.useAdRules);
        Log.v(str, sb.toString());
        if (this.isSwitchingVideos) {
            return;
        }
        if (!this.isPresentingAd && this.originalEvent != null) {
            this.eventEmitter.emit(this.originalEvent.getType(), this.originalEvent.properties);
            this.originalEvent = null;
        } else if (!this.useAdRules && !this.baseVideoView.isPlaying()) {
            willResumeContent();
        }
    }

    public void onAdsManagerLoaded(AdsManagerLoadedEvent adsManagerLoadedEvent) {
        Log.v(TAG, "onAdsLoaded");
        if (this.useAdRules) {
            this.adsManagerState = AdsManagerState.DESTROYED;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("video", this.video);
        hashMap.put("adsManager", adsManagerLoadedEvent.getAdsManager());
        this.eventEmitter.emit("adsManagerLoaded", hashMap);
        this.adsManager = adsManagerLoadedEvent.getAdsManager();
        this.adsManager.addAdErrorListener(this);
        this.adsManager.addAdEventListener(this);
        this.adsManagerState = AdsManagerState.LOADED;
        if (!this.useAdRules) {
            initializeAdsManager();
            this.adsManagerState = AdsManagerState.INITIALIZED;
            return;
        }
        List<Float> adCuePoints = this.adsManager.getAdCuePoints();
        ArrayList arrayList = new ArrayList();
        for (Float intValue : adCuePoints) {
            arrayList.add(getCuePoint(intValue.intValue() * 1000));
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put(AbstractEvent.CUE_POINTS, arrayList);
        this.eventEmitter.emit(EventType.SET_CUE_POINTS, hashMap2);
    }

    private Map<String, Object> getAdEventProperties(AdEvent adEvent) {
        HashMap hashMap = new HashMap();
        hashMap.put("adEvent", adEvent);
        hashMap.put("video", this.video);
        hashMap.put(AbstractEvent.CUE_POINTS, this.cuePoints);
        hashMap.put(AbstractEvent.AD_ID, adEvent.getAd().getAdId());
        hashMap.put("adTitle", adEvent.getAd().getTitle());
        this.googleIMAVideoAdPlayer.setAdId(adEvent.getAd().getAdId());
        this.googleIMAVideoAdPlayer.setAdTitle(adEvent.getAd().getTitle());
        ArrayList<AdsRequest> arrayList = this.currentAdsRequests;
        if (arrayList != null && this.currentAdIndex < arrayList.size()) {
            hashMap.put("adTagUrl", ((AdsRequest) this.currentAdsRequests.get(this.currentAdIndex)).getAdTagUrl());
        }
        return hashMap;
    }

    public void onAdEvent(AdEvent adEvent) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onAdEvent: ");
        sb.append(adEvent);
        Log.v(str, sb.toString());
        switch (adEvent.getType()) {
            case LOADED:
                if (!this.lifecyclePaused) {
                    AdsManager adsManager2 = this.adsManager;
                    if (adsManager2 != null) {
                        adsManager2.start();
                        this.adsManagerState = AdsManagerState.STARTED;
                        return;
                    }
                    return;
                }
                return;
            case CONTENT_PAUSE_REQUESTED:
                onContentPauseRequested();
                return;
            case CONTENT_RESUME_REQUESTED:
                onContentResumeRequested();
                return;
            case STARTED:
                this.eventEmitter.emit(EventType.AD_STARTED, getAdEventProperties(adEvent));
                return;
            case COMPLETED:
                this.eventEmitter.emit(EventType.AD_COMPLETED, getAdEventProperties(adEvent));
                return;
            case PAUSED:
                this.eventEmitter.emit(EventType.AD_PAUSED, getAdEventProperties(adEvent));
                return;
            case RESUMED:
                this.eventEmitter.emit(EventType.AD_RESUMED, getAdEventProperties(adEvent));
                return;
            case ALL_ADS_COMPLETED:
                if (!this.useAdRules) {
                    AdsManager adsManager3 = this.adsManager;
                    if (adsManager3 != null) {
                        adsManager3.destroy();
                        this.adsManagerState = AdsManagerState.DESTROYED;
                    }
                }
                this.googleIMAVideoAdPlayer.removeCallbacks();
                return;
            default:
                return;
        }
    }

    public void onContentPauseRequested() {
        Log.v(TAG, "onContentPauseRequested");
        if (this.useAdRules && !this.videoHasCompleted) {
            this.originalEvent = null;
        }
        if (shouldSkipAdBreak()) {
            this.adsManager.discardAdBreak();
            return;
        }
        if (this.googleIMAVideoAdPlayer.getParent() == null) {
            this.baseVideoView.addView(this.googleIMAVideoAdPlayer, new LayoutParams(-1, -1, 17));
        }
        if (!this.isPresentingAd) {
            this.isPresentingAd = true;
            this.eventEmitter.emit(EventType.AD_BREAK_STARTED);
            this.eventEmitter.emit(EventType.WILL_INTERRUPT_CONTENT);
            this.eventEmitter.emit(EventType.HIDE_SEEK_CONTROLS);
        }
    }

    public void onContentResumeRequested() {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onContentResumeRequested: isPresentingAd = ");
        sb.append(this.isPresentingAd);
        sb.append(", originalEvent = ");
        sb.append(this.originalEvent);
        Log.v(str, sb.toString());
        ArrayList<AdsRequest> arrayList = this.currentAdsRequests;
        if (arrayList != null) {
            int i = this.currentAdIndex + 1;
            this.currentAdIndex = i;
            if (i < arrayList.size()) {
                AdsRequest adsRequest = (AdsRequest) this.currentAdsRequests.get(this.currentAdIndex);
                adsRequest.setContentProgressProvider(this);
                this.adsLoader.requestAds(adsRequest);
                return;
            }
        }
        if (this.isPresentingAd) {
            willResumeContent();
        } else if (this.originalEvent != null) {
            this.eventEmitter.emit(this.originalEvent.getType(), this.originalEvent.properties);
            this.originalEvent = null;
        }
    }

    private boolean shouldSkipAdBreak() {
        int i;
        if (this.mTargetSeekWithoutAdsPosition > 0 && this.useAdRules) {
            AdPodInfo adPodInfo = this.adsManager.getCurrentAd().getAdPodInfo();
            if (adPodInfo == null) {
                i = -1;
            } else {
                i = ((int) adPodInfo.getTimeOffset()) * 1000;
            }
            if ((adPodInfo == null || adPodInfo.getPodIndex() != -1) && i >= 0 && i < this.mTargetSeekWithoutAdsPosition) {
                Log.v(TAG, "Discarding Ad break");
                return true;
            }
        }
        return false;
    }

    private CuePoint getCuePoint(int i) {
        HashMap hashMap = new HashMap();
        if (i == 0) {
            return new CuePoint(PositionType.BEFORE, "ad", (Map<String, Object>) hashMap);
        }
        if (i < 0) {
            return new CuePoint(PositionType.AFTER, "ad", (Map<String, Object>) hashMap);
        }
        return new CuePoint(i, "ad", (Map<String, Object>) hashMap);
    }

    /* access modifiers changed from: private */
    public void onCreate(Event event) {
        Bundle bundle = (Bundle) event.properties.get(AbstractEvent.INSTANCE_STATE);
        if (bundle != null) {
            this.adPlayheadPosition = bundle.getInt("adPlayheadPosition");
            this.adWasPlaying = bundle.getBoolean("adWasPlaying");
        }
    }

    /* access modifiers changed from: private */
    public void onStart() {
        this.googleIMAVideoAdPlayer.loadAd();
        int i = this.adPlayheadPosition;
        if (i != -1) {
            this.googleIMAVideoAdPlayer.seekTo(i);
        }
    }

    /* access modifiers changed from: private */
    public void onPause() {
        this.lifecyclePaused = true;
        if (this.adsManager != null && (this.adsManagerState == AdsManagerState.INITIALIZED || this.adsManagerState == AdsManagerState.STARTED)) {
            this.adsManager.pause();
        }
        if (this.googleIMAVideoAdPlayer.isPlaying()) {
            this.adWasPlaying = true;
            this.googleIMAVideoAdPlayer.pauseAd();
        } else {
            this.adWasPlaying = false;
        }
        this.adPlayheadPosition = this.googleIMAVideoAdPlayer.getCurrentPosition();
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onPause: adWasPlaying = ");
        sb.append(this.adWasPlaying);
        Log.v(str, sb.toString());
    }

    /* access modifiers changed from: private */
    public void onResume() {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onResume: adWasPlaying = ");
        sb.append(this.adWasPlaying);
        Log.v(str, sb.toString());
        this.lifecyclePaused = false;
        if (this.adsManager != null && (this.adsManagerState == AdsManagerState.INITIALIZED || this.adsManagerState == AdsManagerState.STARTED)) {
            this.adsManager.resume();
            this.adsManagerState = AdsManagerState.STARTED;
        }
        if (this.adWasPlaying) {
            this.googleIMAVideoAdPlayer.resumeAd();
        }
    }

    /* access modifiers changed from: private */
    public void onSaveInstanceState(Event event) {
        Bundle bundle = (Bundle) event.properties.get(AbstractEvent.INSTANCE_STATE);
        if (bundle != null) {
            bundle.putInt("adPlayheadPosition", this.adPlayheadPosition);
            bundle.putBoolean("adWasPlaying", this.adWasPlaying);
        }
    }

    public VideoProgressUpdate getContentProgress() {
        VideoProgressUpdate videoProgressUpdate = VideoProgressUpdate.VIDEO_TIME_NOT_READY;
        if (this.isPresentingAd) {
            return videoProgressUpdate;
        }
        int i = this.duration;
        return i > 0 ? new VideoProgressUpdate((long) this.playheadPosition, (long) i) : videoProgressUpdate;
    }

    /* access modifiers changed from: private */
    public void initializeAdsManager() {
        AdsRenderingSettings adsRenderingSettings2 = this.adsRenderingSettings;
        if (adsRenderingSettings2 != null) {
            this.adsManager.init(adsRenderingSettings2);
        } else {
            this.adsManager.init();
        }
    }

    /* access modifiers changed from: private */
    public void resetAdsLoader() {
        AdsLoader adsLoader2 = this.adsLoader;
        if (adsLoader2 != null) {
            adsLoader2.removeAdErrorListener(this);
            this.adsLoader.removeAdsLoadedListener(this);
        }
        this.adsLoader = this.sdkFactory.createAdsLoader(this.baseVideoView.getContext(), this.sdkSettings);
        this.adsLoader.addAdErrorListener(this);
        this.adsLoader.addAdsLoadedListener(this);
    }
}
