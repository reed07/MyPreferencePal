package com.integralads.avid.library.inmobi.video;

import com.integralads.avid.library.inmobi.base.AvidBaseListenerImpl;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.inmobi.session.internal.jsbridge.AvidBridgeManager;
import org.json.JSONException;
import org.json.JSONObject;

public class AvidVideoPlaybackListenerImpl extends AvidBaseListenerImpl implements AvidVideoPlaybackListener {
    public AvidVideoPlaybackListenerImpl(InternalAvidAdSession internalAvidAdSession, AvidBridgeManager avidBridgeManager) {
        super(internalAvidAdSession, avidBridgeManager);
    }

    public void recordAdImpressionEvent() {
        publishVideoEvent("AdImpression", null);
    }

    public void recordAdStartedEvent() {
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_STARTED, null);
    }

    public void recordAdLoadedEvent() {
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_LOADED, null);
    }

    public void recordAdVideoStartEvent() {
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_VIDEO_START, null);
    }

    public void recordAdStoppedEvent() {
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_STOPPED, null);
    }

    public void recordAdCompleteEvent() {
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_VIDEO_COMPLETE, null);
    }

    public void recordAdClickThruEvent() {
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_CLICK_THRU, null);
    }

    public void recordAdVideoFirstQuartileEvent() {
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_VIDEO_FIRST_QUARTILE, null);
    }

    public void recordAdVideoMidpointEvent() {
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_VIDEO_MIDPOINT, null);
    }

    public void recordAdVideoThirdQuartileEvent() {
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_VIDEO_THIRD_QUARTILE, null);
    }

    public void recordAdPausedEvent() {
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_PAUSED, null);
    }

    public void recordAdPlayingEvent() {
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_PLAYING, null);
    }

    public void recordAdExpandedChangeEvent() {
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_EXPANDED_CHANGE, null);
    }

    public void recordAdUserMinimizeEvent() {
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_USER_MINIMIZE, null);
    }

    public void recordAdUserCloseEvent() {
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_USER_CLOSE, null);
    }

    public void recordAdSkippedEvent() {
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_SKIPPED, null);
    }

    public void recordAdVolumeChangeEvent(Integer num) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("volume", num);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_VOLUME_CHANGE, jSONObject);
    }

    public void recordAdEnteredFullscreenEvent() {
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_ENTERED_FULLSCREEN, null);
    }

    public void recordAdExitedFullscreenEvent() {
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_EXITED_FULLSCREEN, null);
    }

    public void recordAdError(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("message", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        publishVideoEvent(com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl.AD_ERROR, jSONObject);
    }

    private void publishVideoEvent(String str, JSONObject jSONObject) {
        assertSessionIsNotEnded();
        assertSessionIsReady();
        getAvidBridgeManager().publishVideoEvent(str, jSONObject);
    }

    private void assertSessionIsReady() {
        if (!getAvidAdSession().isReady()) {
            throw new IllegalStateException("The AVID ad session is not ready. Please ensure you have called recordReadyEvent for the deferred AVID ad session before recording any video event.");
        }
    }
}
