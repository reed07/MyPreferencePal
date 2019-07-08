package com.google.android.exoplayer2.source;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSourceEventListener.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaSourceEventListener.MediaLoadData;
import java.io.IOException;

public abstract class DefaultMediaSourceEventListener implements MediaSourceEventListener {
    public void onDownstreamFormatChanged(int i, @Nullable MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
    }

    public void onLoadCanceled(int i, @Nullable MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public void onLoadCompleted(int i, @Nullable MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public void onLoadError(int i, @Nullable MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
    }

    public void onLoadStarted(int i, @Nullable MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public void onMediaPeriodCreated(int i, MediaPeriodId mediaPeriodId) {
    }

    public void onMediaPeriodReleased(int i, MediaPeriodId mediaPeriodId) {
    }

    public void onReadingStarted(int i, MediaPeriodId mediaPeriodId) {
    }

    public void onUpstreamDiscarded(int i, @Nullable MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
    }
}
