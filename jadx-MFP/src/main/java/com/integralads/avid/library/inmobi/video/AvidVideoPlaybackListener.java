package com.integralads.avid.library.inmobi.video;

public interface AvidVideoPlaybackListener {
    void recordAdClickThruEvent();

    void recordAdCompleteEvent();

    void recordAdEnteredFullscreenEvent();

    void recordAdError(String str);

    void recordAdExitedFullscreenEvent();

    void recordAdExpandedChangeEvent();

    void recordAdImpressionEvent();

    void recordAdLoadedEvent();

    void recordAdPausedEvent();

    void recordAdPlayingEvent();

    void recordAdSkippedEvent();

    void recordAdStartedEvent();

    void recordAdStoppedEvent();

    void recordAdUserCloseEvent();

    void recordAdUserMinimizeEvent();

    void recordAdVideoFirstQuartileEvent();

    void recordAdVideoMidpointEvent();

    void recordAdVideoStartEvent();

    void recordAdVideoThirdQuartileEvent();

    void recordAdVolumeChangeEvent(Integer num);
}
