package com.google.android.exoplayer2.analytics;

import android.support.annotation.Nullable;
import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.analytics.AnalyticsListener.CC;
import com.google.android.exoplayer2.analytics.AnalyticsListener.EventTime;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.MediaSourceEventListener.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaSourceEventListener.MediaLoadData;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import java.io.IOException;

@Deprecated
public abstract class DefaultAnalyticsListener implements AnalyticsListener {
    public /* synthetic */ void onAudioAttributesChanged(EventTime eventTime, AudioAttributes audioAttributes) {
        CC.$default$onAudioAttributesChanged(this, eventTime, audioAttributes);
    }

    public /* synthetic */ void onAudioSessionId(EventTime eventTime, int i) {
        CC.$default$onAudioSessionId(this, eventTime, i);
    }

    public /* synthetic */ void onAudioUnderrun(EventTime eventTime, int i, long j, long j2) {
        CC.$default$onAudioUnderrun(this, eventTime, i, j, j2);
    }

    public /* synthetic */ void onBandwidthEstimate(EventTime eventTime, int i, long j, long j2) {
        CC.$default$onBandwidthEstimate(this, eventTime, i, j, j2);
    }

    public /* synthetic */ void onDecoderDisabled(EventTime eventTime, int i, DecoderCounters decoderCounters) {
        CC.$default$onDecoderDisabled(this, eventTime, i, decoderCounters);
    }

    public /* synthetic */ void onDecoderEnabled(EventTime eventTime, int i, DecoderCounters decoderCounters) {
        CC.$default$onDecoderEnabled(this, eventTime, i, decoderCounters);
    }

    public /* synthetic */ void onDecoderInitialized(EventTime eventTime, int i, String str, long j) {
        CC.$default$onDecoderInitialized(this, eventTime, i, str, j);
    }

    public /* synthetic */ void onDecoderInputFormatChanged(EventTime eventTime, int i, Format format) {
        CC.$default$onDecoderInputFormatChanged(this, eventTime, i, format);
    }

    public /* synthetic */ void onDownstreamFormatChanged(EventTime eventTime, MediaLoadData mediaLoadData) {
        CC.$default$onDownstreamFormatChanged(this, eventTime, mediaLoadData);
    }

    public /* synthetic */ void onDrmKeysLoaded(EventTime eventTime) {
        CC.$default$onDrmKeysLoaded(this, eventTime);
    }

    public /* synthetic */ void onDrmKeysRemoved(EventTime eventTime) {
        CC.$default$onDrmKeysRemoved(this, eventTime);
    }

    public /* synthetic */ void onDrmKeysRestored(EventTime eventTime) {
        CC.$default$onDrmKeysRestored(this, eventTime);
    }

    public /* synthetic */ void onDrmSessionAcquired(EventTime eventTime) {
        CC.$default$onDrmSessionAcquired(this, eventTime);
    }

    public /* synthetic */ void onDrmSessionManagerError(EventTime eventTime, Exception exc) {
        CC.$default$onDrmSessionManagerError(this, eventTime, exc);
    }

    public /* synthetic */ void onDrmSessionReleased(EventTime eventTime) {
        CC.$default$onDrmSessionReleased(this, eventTime);
    }

    public /* synthetic */ void onDroppedVideoFrames(EventTime eventTime, int i, long j) {
        CC.$default$onDroppedVideoFrames(this, eventTime, i, j);
    }

    public /* synthetic */ void onLoadCanceled(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        CC.$default$onLoadCanceled(this, eventTime, loadEventInfo, mediaLoadData);
    }

    public /* synthetic */ void onLoadCompleted(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        CC.$default$onLoadCompleted(this, eventTime, loadEventInfo, mediaLoadData);
    }

    public /* synthetic */ void onLoadError(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        CC.$default$onLoadError(this, eventTime, loadEventInfo, mediaLoadData, iOException, z);
    }

    public /* synthetic */ void onLoadStarted(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        CC.$default$onLoadStarted(this, eventTime, loadEventInfo, mediaLoadData);
    }

    public /* synthetic */ void onLoadingChanged(EventTime eventTime, boolean z) {
        CC.$default$onLoadingChanged(this, eventTime, z);
    }

    public /* synthetic */ void onMediaPeriodCreated(EventTime eventTime) {
        CC.$default$onMediaPeriodCreated(this, eventTime);
    }

    public /* synthetic */ void onMediaPeriodReleased(EventTime eventTime) {
        CC.$default$onMediaPeriodReleased(this, eventTime);
    }

    public /* synthetic */ void onMetadata(EventTime eventTime, Metadata metadata) {
        CC.$default$onMetadata(this, eventTime, metadata);
    }

    public /* synthetic */ void onPlaybackParametersChanged(EventTime eventTime, PlaybackParameters playbackParameters) {
        CC.$default$onPlaybackParametersChanged(this, eventTime, playbackParameters);
    }

    public /* synthetic */ void onPlayerError(EventTime eventTime, ExoPlaybackException exoPlaybackException) {
        CC.$default$onPlayerError(this, eventTime, exoPlaybackException);
    }

    public /* synthetic */ void onPlayerStateChanged(EventTime eventTime, boolean z, int i) {
        CC.$default$onPlayerStateChanged(this, eventTime, z, i);
    }

    public /* synthetic */ void onPositionDiscontinuity(EventTime eventTime, int i) {
        CC.$default$onPositionDiscontinuity(this, eventTime, i);
    }

    public /* synthetic */ void onReadingStarted(EventTime eventTime) {
        CC.$default$onReadingStarted(this, eventTime);
    }

    public /* synthetic */ void onRenderedFirstFrame(EventTime eventTime, @Nullable Surface surface) {
        CC.$default$onRenderedFirstFrame(this, eventTime, surface);
    }

    public /* synthetic */ void onRepeatModeChanged(EventTime eventTime, int i) {
        CC.$default$onRepeatModeChanged(this, eventTime, i);
    }

    public /* synthetic */ void onSeekProcessed(EventTime eventTime) {
        CC.$default$onSeekProcessed(this, eventTime);
    }

    public /* synthetic */ void onSeekStarted(EventTime eventTime) {
        CC.$default$onSeekStarted(this, eventTime);
    }

    public /* synthetic */ void onShuffleModeChanged(EventTime eventTime, boolean z) {
        CC.$default$onShuffleModeChanged(this, eventTime, z);
    }

    public /* synthetic */ void onSurfaceSizeChanged(EventTime eventTime, int i, int i2) {
        CC.$default$onSurfaceSizeChanged(this, eventTime, i, i2);
    }

    public /* synthetic */ void onTimelineChanged(EventTime eventTime, int i) {
        CC.$default$onTimelineChanged(this, eventTime, i);
    }

    public /* synthetic */ void onTracksChanged(EventTime eventTime, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        CC.$default$onTracksChanged(this, eventTime, trackGroupArray, trackSelectionArray);
    }

    public /* synthetic */ void onUpstreamDiscarded(EventTime eventTime, MediaLoadData mediaLoadData) {
        CC.$default$onUpstreamDiscarded(this, eventTime, mediaLoadData);
    }

    public /* synthetic */ void onVideoSizeChanged(EventTime eventTime, int i, int i2, int i3, float f) {
        CC.$default$onVideoSizeChanged(this, eventTime, i, i2, i3, f);
    }

    public /* synthetic */ void onVolumeChanged(EventTime eventTime, float f) {
        CC.$default$onVolumeChanged(this, eventTime, f);
    }
}
