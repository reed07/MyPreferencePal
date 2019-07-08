package com.google.android.exoplayer2.util;

import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Timeline.Period;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.analytics.AnalyticsListener.CC;
import com.google.android.exoplayer2.analytics.AnalyticsListener.EventTime;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.MediaSourceEventListener.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaSourceEventListener.MediaLoadData;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector.MappedTrackInfo;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class EventLogger implements AnalyticsListener {
    private static final String DEFAULT_TAG = "EventLogger";
    private static final int MAX_TIMELINE_ITEM_LINES = 3;
    private static final NumberFormat TIME_FORMAT = NumberFormat.getInstance(Locale.US);
    private final Period period;
    private final long startTimeMs;
    private final String tag;
    @Nullable
    private final MappingTrackSelector trackSelector;
    private final Window window;

    private static String getAdaptiveSupportString(int i, int i2) {
        return i < 2 ? "N/A" : i2 != 0 ? i2 != 8 ? i2 != 16 ? "?" : "YES" : "YES_NOT_SEAMLESS" : "NO";
    }

    private static String getDiscontinuityReasonString(int i) {
        switch (i) {
            case 0:
                return "PERIOD_TRANSITION";
            case 1:
                return "SEEK";
            case 2:
                return "SEEK_ADJUSTMENT";
            case 3:
                return "AD_INSERTION";
            case 4:
                return "INTERNAL";
            default:
                return "?";
        }
    }

    private static String getFormatSupportString(int i) {
        switch (i) {
            case 0:
                return "NO";
            case 1:
                return "NO_UNSUPPORTED_TYPE";
            case 2:
                return "NO_UNSUPPORTED_DRM";
            case 3:
                return "NO_EXCEEDS_CAPABILITIES";
            case 4:
                return "YES";
            default:
                return "?";
        }
    }

    private static String getRepeatModeString(int i) {
        switch (i) {
            case 0:
                return "OFF";
            case 1:
                return "ONE";
            case 2:
                return "ALL";
            default:
                return "?";
        }
    }

    private static String getStateString(int i) {
        switch (i) {
            case 1:
                return "IDLE";
            case 2:
                return "BUFFERING";
            case 3:
                return "READY";
            case 4:
                return "ENDED";
            default:
                return "?";
        }
    }

    private static String getTimelineChangeReasonString(int i) {
        switch (i) {
            case 0:
                return "PREPARED";
            case 1:
                return "RESET";
            case 2:
                return "DYNAMIC";
            default:
                return "?";
        }
    }

    private static String getTrackStatusString(boolean z) {
        return z ? "[X]" : "[ ]";
    }

    public /* synthetic */ void onAudioAttributesChanged(EventTime eventTime, AudioAttributes audioAttributes) {
        CC.$default$onAudioAttributesChanged(this, eventTime, audioAttributes);
    }

    public void onBandwidthEstimate(EventTime eventTime, int i, long j, long j2) {
    }

    public void onLoadCanceled(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public void onLoadCompleted(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public void onLoadStarted(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public /* synthetic */ void onVolumeChanged(EventTime eventTime, float f) {
        CC.$default$onVolumeChanged(this, eventTime, f);
    }

    static {
        TIME_FORMAT.setMinimumFractionDigits(2);
        TIME_FORMAT.setMaximumFractionDigits(2);
        TIME_FORMAT.setGroupingUsed(false);
    }

    public EventLogger(@Nullable MappingTrackSelector mappingTrackSelector) {
        this(mappingTrackSelector, DEFAULT_TAG);
    }

    public EventLogger(@Nullable MappingTrackSelector mappingTrackSelector, String str) {
        this.trackSelector = mappingTrackSelector;
        this.tag = str;
        this.window = new Window();
        this.period = new Period();
        this.startTimeMs = SystemClock.elapsedRealtime();
    }

    public void onLoadingChanged(EventTime eventTime, boolean z) {
        logd(eventTime, "loading", Boolean.toString(z));
    }

    public void onPlayerStateChanged(EventTime eventTime, boolean z, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(z);
        sb.append(", ");
        sb.append(getStateString(i));
        logd(eventTime, "state", sb.toString());
    }

    public void onRepeatModeChanged(EventTime eventTime, int i) {
        logd(eventTime, "repeatMode", getRepeatModeString(i));
    }

    public void onShuffleModeChanged(EventTime eventTime, boolean z) {
        logd(eventTime, "shuffleModeEnabled", Boolean.toString(z));
    }

    public void onPositionDiscontinuity(EventTime eventTime, int i) {
        logd(eventTime, "positionDiscontinuity", getDiscontinuityReasonString(i));
    }

    public void onSeekStarted(EventTime eventTime) {
        logd(eventTime, "seekStarted");
    }

    public void onPlaybackParametersChanged(EventTime eventTime, PlaybackParameters playbackParameters) {
        logd(eventTime, "playbackParameters", Util.formatInvariant("speed=%.2f, pitch=%.2f, skipSilence=%s", Float.valueOf(playbackParameters.speed), Float.valueOf(playbackParameters.pitch), Boolean.valueOf(playbackParameters.skipSilence)));
    }

    public void onTimelineChanged(EventTime eventTime, int i) {
        int periodCount = eventTime.timeline.getPeriodCount();
        int windowCount = eventTime.timeline.getWindowCount();
        StringBuilder sb = new StringBuilder();
        sb.append("timelineChanged [");
        sb.append(getEventTimeString(eventTime));
        sb.append(", periodCount=");
        sb.append(periodCount);
        sb.append(", windowCount=");
        sb.append(windowCount);
        sb.append(", reason=");
        sb.append(getTimelineChangeReasonString(i));
        logd(sb.toString());
        for (int i2 = 0; i2 < Math.min(periodCount, 3); i2++) {
            eventTime.timeline.getPeriod(i2, this.period);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  period [");
            sb2.append(getTimeString(this.period.getDurationMs()));
            sb2.append("]");
            logd(sb2.toString());
        }
        if (periodCount > 3) {
            logd("  ...");
        }
        for (int i3 = 0; i3 < Math.min(windowCount, 3); i3++) {
            eventTime.timeline.getWindow(i3, this.window);
            StringBuilder sb3 = new StringBuilder();
            sb3.append("  window [");
            sb3.append(getTimeString(this.window.getDurationMs()));
            sb3.append(", ");
            sb3.append(this.window.isSeekable);
            sb3.append(", ");
            sb3.append(this.window.isDynamic);
            sb3.append("]");
            logd(sb3.toString());
        }
        if (windowCount > 3) {
            logd("  ...");
        }
        logd("]");
    }

    public void onPlayerError(EventTime eventTime, ExoPlaybackException exoPlaybackException) {
        loge(eventTime, "playerFailed", exoPlaybackException);
    }

    public void onTracksChanged(EventTime eventTime, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        MappingTrackSelector mappingTrackSelector = this.trackSelector;
        MappedTrackInfo currentMappedTrackInfo = mappingTrackSelector != null ? mappingTrackSelector.getCurrentMappedTrackInfo() : null;
        if (currentMappedTrackInfo == null) {
            logd(eventTime, "tracksChanged", "[]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("tracksChanged [");
        sb.append(getEventTimeString(eventTime));
        sb.append(", ");
        logd(sb.toString());
        int rendererCount = currentMappedTrackInfo.getRendererCount();
        for (int i = 0; i < rendererCount; i++) {
            TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(i);
            TrackSelection trackSelection = trackSelectionArray.get(i);
            if (trackGroups.length > 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("  Renderer:");
                sb2.append(i);
                sb2.append(" [");
                logd(sb2.toString());
                for (int i2 = 0; i2 < trackGroups.length; i2++) {
                    TrackGroup trackGroup = trackGroups.get(i2);
                    String adaptiveSupportString = getAdaptiveSupportString(trackGroup.length, currentMappedTrackInfo.getAdaptiveSupport(i, i2, false));
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("    Group:");
                    sb3.append(i2);
                    sb3.append(", adaptive_supported=");
                    sb3.append(adaptiveSupportString);
                    sb3.append(" [");
                    logd(sb3.toString());
                    for (int i3 = 0; i3 < trackGroup.length; i3++) {
                        String trackStatusString = getTrackStatusString(trackSelection, trackGroup, i3);
                        String formatSupportString = getFormatSupportString(currentMappedTrackInfo.getTrackSupport(i, i2, i3));
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("      ");
                        sb4.append(trackStatusString);
                        sb4.append(" Track:");
                        sb4.append(i3);
                        sb4.append(", ");
                        sb4.append(Format.toLogString(trackGroup.getFormat(i3)));
                        sb4.append(", supported=");
                        sb4.append(formatSupportString);
                        logd(sb4.toString());
                    }
                    logd("    ]");
                }
                if (trackSelection != null) {
                    int i4 = 0;
                    while (true) {
                        if (i4 >= trackSelection.length()) {
                            break;
                        }
                        Metadata metadata = trackSelection.getFormat(i4).metadata;
                        if (metadata != null) {
                            logd("    Metadata [");
                            printMetadata(metadata, "      ");
                            logd("    ]");
                            break;
                        }
                        i4++;
                    }
                }
                logd("  ]");
            }
        }
        TrackGroupArray unmappedTrackGroups = currentMappedTrackInfo.getUnmappedTrackGroups();
        if (unmappedTrackGroups.length > 0) {
            logd("  Renderer:None [");
            for (int i5 = 0; i5 < unmappedTrackGroups.length; i5++) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append("    Group:");
                sb5.append(i5);
                sb5.append(" [");
                logd(sb5.toString());
                TrackGroup trackGroup2 = unmappedTrackGroups.get(i5);
                for (int i6 = 0; i6 < trackGroup2.length; i6++) {
                    String trackStatusString2 = getTrackStatusString(false);
                    String formatSupportString2 = getFormatSupportString(0);
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("      ");
                    sb6.append(trackStatusString2);
                    sb6.append(" Track:");
                    sb6.append(i6);
                    sb6.append(", ");
                    sb6.append(Format.toLogString(trackGroup2.getFormat(i6)));
                    sb6.append(", supported=");
                    sb6.append(formatSupportString2);
                    logd(sb6.toString());
                }
                logd("    ]");
            }
            logd("  ]");
        }
        logd("]");
    }

    public void onSeekProcessed(EventTime eventTime) {
        logd(eventTime, "seekProcessed");
    }

    public void onMetadata(EventTime eventTime, Metadata metadata) {
        StringBuilder sb = new StringBuilder();
        sb.append("metadata [");
        sb.append(getEventTimeString(eventTime));
        sb.append(", ");
        logd(sb.toString());
        printMetadata(metadata, "  ");
        logd("]");
    }

    public void onDecoderEnabled(EventTime eventTime, int i, DecoderCounters decoderCounters) {
        logd(eventTime, "decoderEnabled", getTrackTypeString(i));
    }

    public void onAudioSessionId(EventTime eventTime, int i) {
        logd(eventTime, "audioSessionId", Integer.toString(i));
    }

    public void onDecoderInitialized(EventTime eventTime, int i, String str, long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(getTrackTypeString(i));
        sb.append(", ");
        sb.append(str);
        logd(eventTime, "decoderInitialized", sb.toString());
    }

    public void onDecoderInputFormatChanged(EventTime eventTime, int i, Format format) {
        StringBuilder sb = new StringBuilder();
        sb.append(getTrackTypeString(i));
        sb.append(", ");
        sb.append(Format.toLogString(format));
        logd(eventTime, "decoderInputFormatChanged", sb.toString());
    }

    public void onDecoderDisabled(EventTime eventTime, int i, DecoderCounters decoderCounters) {
        logd(eventTime, "decoderDisabled", getTrackTypeString(i));
    }

    public void onAudioUnderrun(EventTime eventTime, int i, long j, long j2) {
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(", ");
        sb.append(j);
        sb.append(", ");
        sb.append(j2);
        sb.append("]");
        loge(eventTime, "audioTrackUnderrun", sb.toString(), null);
    }

    public void onDroppedVideoFrames(EventTime eventTime, int i, long j) {
        logd(eventTime, "droppedFrames", Integer.toString(i));
    }

    public void onVideoSizeChanged(EventTime eventTime, int i, int i2, int i3, float f) {
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(", ");
        sb.append(i2);
        logd(eventTime, "videoSizeChanged", sb.toString());
    }

    public void onRenderedFirstFrame(EventTime eventTime, @Nullable Surface surface) {
        logd(eventTime, "renderedFirstFrame", String.valueOf(surface));
    }

    public void onMediaPeriodCreated(EventTime eventTime) {
        logd(eventTime, "mediaPeriodCreated");
    }

    public void onMediaPeriodReleased(EventTime eventTime) {
        logd(eventTime, "mediaPeriodReleased");
    }

    public void onLoadError(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        printInternalError(eventTime, "loadError", iOException);
    }

    public void onReadingStarted(EventTime eventTime) {
        logd(eventTime, "mediaPeriodReadingStarted");
    }

    public void onSurfaceSizeChanged(EventTime eventTime, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(", ");
        sb.append(i2);
        logd(eventTime, "surfaceSizeChanged", sb.toString());
    }

    public void onUpstreamDiscarded(EventTime eventTime, MediaLoadData mediaLoadData) {
        logd(eventTime, "upstreamDiscarded", Format.toLogString(mediaLoadData.trackFormat));
    }

    public void onDownstreamFormatChanged(EventTime eventTime, MediaLoadData mediaLoadData) {
        logd(eventTime, "downstreamFormatChanged", Format.toLogString(mediaLoadData.trackFormat));
    }

    public void onDrmSessionAcquired(EventTime eventTime) {
        logd(eventTime, "drmSessionAcquired");
    }

    public void onDrmSessionManagerError(EventTime eventTime, Exception exc) {
        printInternalError(eventTime, "drmSessionManagerError", exc);
    }

    public void onDrmKeysRestored(EventTime eventTime) {
        logd(eventTime, "drmKeysRestored");
    }

    public void onDrmKeysRemoved(EventTime eventTime) {
        logd(eventTime, "drmKeysRemoved");
    }

    public void onDrmKeysLoaded(EventTime eventTime) {
        logd(eventTime, "drmKeysLoaded");
    }

    public void onDrmSessionReleased(EventTime eventTime) {
        logd(eventTime, "drmSessionReleased");
    }

    /* access modifiers changed from: protected */
    public void logd(String str) {
        Log.d(this.tag, str);
    }

    /* access modifiers changed from: protected */
    public void loge(String str, @Nullable Throwable th) {
        Log.e(this.tag, str, th);
    }

    private void logd(EventTime eventTime, String str) {
        logd(getEventString(eventTime, str));
    }

    private void logd(EventTime eventTime, String str, String str2) {
        logd(getEventString(eventTime, str, str2));
    }

    private void loge(EventTime eventTime, String str, @Nullable Throwable th) {
        loge(getEventString(eventTime, str), th);
    }

    private void loge(EventTime eventTime, String str, String str2, @Nullable Throwable th) {
        loge(getEventString(eventTime, str, str2), th);
    }

    private void printInternalError(EventTime eventTime, String str, Exception exc) {
        loge(eventTime, "internalError", str, exc);
    }

    private void printMetadata(Metadata metadata, String str) {
        for (int i = 0; i < metadata.length(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(metadata.get(i));
            logd(sb.toString());
        }
    }

    private String getEventString(EventTime eventTime, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" [");
        sb.append(getEventTimeString(eventTime));
        sb.append("]");
        return sb.toString();
    }

    private String getEventString(EventTime eventTime, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" [");
        sb.append(getEventTimeString(eventTime));
        sb.append(", ");
        sb.append(str2);
        sb.append("]");
        return sb.toString();
    }

    private String getEventTimeString(EventTime eventTime) {
        StringBuilder sb = new StringBuilder();
        sb.append("window=");
        sb.append(eventTime.windowIndex);
        String sb2 = sb.toString();
        if (eventTime.mediaPeriodId != null) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2);
            sb3.append(", period=");
            sb3.append(eventTime.timeline.getIndexOfPeriod(eventTime.mediaPeriodId.periodUid));
            sb2 = sb3.toString();
            if (eventTime.mediaPeriodId.isAd()) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(sb2);
                sb4.append(", adGroup=");
                sb4.append(eventTime.mediaPeriodId.adGroupIndex);
                String sb5 = sb4.toString();
                StringBuilder sb6 = new StringBuilder();
                sb6.append(sb5);
                sb6.append(", ad=");
                sb6.append(eventTime.mediaPeriodId.adIndexInAdGroup);
                sb2 = sb6.toString();
            }
        }
        StringBuilder sb7 = new StringBuilder();
        sb7.append(getTimeString(eventTime.realtimeMs - this.startTimeMs));
        sb7.append(", ");
        sb7.append(getTimeString(eventTime.currentPlaybackPositionMs));
        sb7.append(", ");
        sb7.append(sb2);
        return sb7.toString();
    }

    private static String getTimeString(long j) {
        return j == -9223372036854775807L ? "?" : TIME_FORMAT.format((double) (((float) j) / 1000.0f));
    }

    private static String getTrackStatusString(@Nullable TrackSelection trackSelection, TrackGroup trackGroup, int i) {
        return getTrackStatusString((trackSelection == null || trackSelection.getTrackGroup() != trackGroup || trackSelection.indexOf(i) == -1) ? false : true);
    }

    private static String getTrackTypeString(int i) {
        String str;
        switch (i) {
            case 0:
                return "default";
            case 1:
                return MimeTypes.BASE_TYPE_AUDIO;
            case 2:
                return "video";
            case 3:
                return "text";
            case 4:
                return TtmlNode.TAG_METADATA;
            case 5:
                return "camera motion";
            case 6:
                return "none";
            default:
                if (i >= 10000) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("custom (");
                    sb.append(i);
                    sb.append(")");
                    str = sb.toString();
                } else {
                    str = "?";
                }
                return str;
        }
    }
}
