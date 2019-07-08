package com.google.android.exoplayer2;

import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;

final class PlaybackInfo {
    private static final MediaPeriodId DUMMY_MEDIA_PERIOD_ID = new MediaPeriodId(new Object());
    public volatile long bufferedPositionUs;
    public final long contentPositionUs;
    public final boolean isLoading;
    public final MediaPeriodId loadingMediaPeriodId;
    @Nullable
    public final Object manifest;
    public final MediaPeriodId periodId;
    public final int playbackState;
    public volatile long positionUs;
    public final long startPositionUs;
    public final Timeline timeline;
    public volatile long totalBufferedDurationUs;
    public final TrackGroupArray trackGroups;
    public final TrackSelectorResult trackSelectorResult;

    public static PlaybackInfo createDummy(long j, TrackSelectorResult trackSelectorResult2) {
        TrackSelectorResult trackSelectorResult3 = trackSelectorResult2;
        PlaybackInfo playbackInfo = new PlaybackInfo(Timeline.EMPTY, null, DUMMY_MEDIA_PERIOD_ID, j, -9223372036854775807L, 1, false, TrackGroupArray.EMPTY, trackSelectorResult3, DUMMY_MEDIA_PERIOD_ID, j, 0, j);
        return playbackInfo;
    }

    public PlaybackInfo(Timeline timeline2, @Nullable Object obj, MediaPeriodId mediaPeriodId, long j, long j2, int i, boolean z, TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult2, MediaPeriodId mediaPeriodId2, long j3, long j4, long j5) {
        this.timeline = timeline2;
        this.manifest = obj;
        this.periodId = mediaPeriodId;
        this.startPositionUs = j;
        this.contentPositionUs = j2;
        this.playbackState = i;
        this.isLoading = z;
        this.trackGroups = trackGroupArray;
        this.trackSelectorResult = trackSelectorResult2;
        this.loadingMediaPeriodId = mediaPeriodId2;
        this.bufferedPositionUs = j3;
        this.totalBufferedDurationUs = j4;
        this.positionUs = j5;
    }

    public MediaPeriodId getDummyFirstMediaPeriodId(boolean z, Window window) {
        if (this.timeline.isEmpty()) {
            return DUMMY_MEDIA_PERIOD_ID;
        }
        Timeline timeline2 = this.timeline;
        return new MediaPeriodId(this.timeline.getUidOfPeriod(timeline2.getWindow(timeline2.getFirstWindowIndex(z), window).firstPeriodIndex));
    }

    @CheckResult
    public PlaybackInfo resetToNewPosition(MediaPeriodId mediaPeriodId, long j, long j2) {
        PlaybackInfo playbackInfo = new PlaybackInfo(this.timeline, this.manifest, mediaPeriodId, j, mediaPeriodId.isAd() ? j2 : -9223372036854775807L, this.playbackState, this.isLoading, this.trackGroups, this.trackSelectorResult, mediaPeriodId, j, 0, j);
        return playbackInfo;
    }

    @CheckResult
    public PlaybackInfo copyWithNewPosition(MediaPeriodId mediaPeriodId, long j, long j2, long j3) {
        PlaybackInfo playbackInfo = new PlaybackInfo(this.timeline, this.manifest, mediaPeriodId, j, mediaPeriodId.isAd() ? j2 : -9223372036854775807L, this.playbackState, this.isLoading, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, j3, j);
        return playbackInfo;
    }

    @CheckResult
    public PlaybackInfo copyWithTimeline(Timeline timeline2, Object obj) {
        Timeline timeline3 = timeline2;
        PlaybackInfo playbackInfo = new PlaybackInfo(timeline2, obj, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, this.isLoading, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
        return playbackInfo;
    }

    @CheckResult
    public PlaybackInfo copyWithPlaybackState(int i) {
        int i2 = i;
        PlaybackInfo playbackInfo = new PlaybackInfo(this.timeline, this.manifest, this.periodId, this.startPositionUs, this.contentPositionUs, i2, this.isLoading, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
        return playbackInfo;
    }

    @CheckResult
    public PlaybackInfo copyWithIsLoading(boolean z) {
        boolean z2 = z;
        PlaybackInfo playbackInfo = new PlaybackInfo(this.timeline, this.manifest, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, z2, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
        return playbackInfo;
    }

    @CheckResult
    public PlaybackInfo copyWithTrackInfo(TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult2) {
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        TrackSelectorResult trackSelectorResult3 = trackSelectorResult2;
        PlaybackInfo playbackInfo = new PlaybackInfo(this.timeline, this.manifest, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, this.isLoading, trackGroupArray2, trackSelectorResult3, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
        return playbackInfo;
    }

    @CheckResult
    public PlaybackInfo copyWithLoadingMediaPeriodId(MediaPeriodId mediaPeriodId) {
        MediaPeriodId mediaPeriodId2 = mediaPeriodId;
        PlaybackInfo playbackInfo = new PlaybackInfo(this.timeline, this.manifest, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, this.isLoading, this.trackGroups, this.trackSelectorResult, mediaPeriodId2, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
        return playbackInfo;
    }
}
