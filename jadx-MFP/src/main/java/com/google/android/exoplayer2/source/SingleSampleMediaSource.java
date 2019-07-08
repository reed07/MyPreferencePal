package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSourceEventListener.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaSourceEventListener.MediaLoadData;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;

public final class SingleSampleMediaSource extends BaseMediaSource {
    private final com.google.android.exoplayer2.upstream.DataSource.Factory dataSourceFactory;
    private final DataSpec dataSpec;
    private final long durationUs;
    private final Format format;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    @Nullable
    private final Object tag;
    private final Timeline timeline;
    @Nullable
    private TransferListener transferListener;
    private final boolean treatLoadErrorsAsEndOfStream;

    @Deprecated
    public interface EventListener {
        void onLoadError(int i, IOException iOException);
    }

    @Deprecated
    private static final class EventListenerWrapper extends DefaultMediaSourceEventListener {
        private final EventListener eventListener;
        private final int eventSourceId;

        public EventListenerWrapper(EventListener eventListener2, int i) {
            this.eventListener = (EventListener) Assertions.checkNotNull(eventListener2);
            this.eventSourceId = i;
        }

        public void onLoadError(int i, @Nullable MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
            this.eventListener.onLoadError(this.eventSourceId, iOException);
        }
    }

    public static final class Factory {
        private final com.google.android.exoplayer2.upstream.DataSource.Factory dataSourceFactory;
        private boolean isCreateCalled;
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy();
        @Nullable
        private Object tag;
        private boolean treatLoadErrorsAsEndOfStream;

        public Factory(com.google.android.exoplayer2.upstream.DataSource.Factory factory) {
            this.dataSourceFactory = (com.google.android.exoplayer2.upstream.DataSource.Factory) Assertions.checkNotNull(factory);
        }

        public Factory setTag(Object obj) {
            Assertions.checkState(!this.isCreateCalled);
            this.tag = obj;
            return this;
        }

        @Deprecated
        public Factory setMinLoadableRetryCount(int i) {
            return setLoadErrorHandlingPolicy(new DefaultLoadErrorHandlingPolicy(i));
        }

        public Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy2) {
            Assertions.checkState(!this.isCreateCalled);
            this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
            return this;
        }

        public Factory setTreatLoadErrorsAsEndOfStream(boolean z) {
            Assertions.checkState(!this.isCreateCalled);
            this.treatLoadErrorsAsEndOfStream = z;
            return this;
        }

        public SingleSampleMediaSource createMediaSource(Uri uri, Format format, long j) {
            this.isCreateCalled = true;
            SingleSampleMediaSource singleSampleMediaSource = new SingleSampleMediaSource(uri, this.dataSourceFactory, format, j, this.loadErrorHandlingPolicy, this.treatLoadErrorsAsEndOfStream, this.tag);
            return singleSampleMediaSource;
        }

        @Deprecated
        public SingleSampleMediaSource createMediaSource(Uri uri, Format format, long j, @Nullable Handler handler, @Nullable MediaSourceEventListener mediaSourceEventListener) {
            SingleSampleMediaSource createMediaSource = createMediaSource(uri, format, j);
            if (!(handler == null || mediaSourceEventListener == null)) {
                createMediaSource.addEventListener(handler, mediaSourceEventListener);
            }
            return createMediaSource;
        }
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
    }

    public void releaseSourceInternal() {
    }

    @Deprecated
    public SingleSampleMediaSource(Uri uri, com.google.android.exoplayer2.upstream.DataSource.Factory factory, Format format2, long j) {
        this(uri, factory, format2, j, 3);
    }

    @Deprecated
    public SingleSampleMediaSource(Uri uri, com.google.android.exoplayer2.upstream.DataSource.Factory factory, Format format2, long j, int i) {
        this(uri, factory, format2, j, new DefaultLoadErrorHandlingPolicy(i), false, null);
    }

    @Deprecated
    public SingleSampleMediaSource(Uri uri, com.google.android.exoplayer2.upstream.DataSource.Factory factory, Format format2, long j, int i, Handler handler, EventListener eventListener, int i2, boolean z) {
        Handler handler2 = handler;
        EventListener eventListener2 = eventListener;
        this(uri, factory, format2, j, new DefaultLoadErrorHandlingPolicy(i), z, null);
        if (handler2 == null || eventListener2 == null) {
            return;
        }
        EventListenerWrapper eventListenerWrapper = new EventListenerWrapper(eventListener2, i2);
        addEventListener(handler2, eventListenerWrapper);
    }

    private SingleSampleMediaSource(Uri uri, com.google.android.exoplayer2.upstream.DataSource.Factory factory, Format format2, long j, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, boolean z, @Nullable Object obj) {
        this.dataSourceFactory = factory;
        this.format = format2;
        this.durationUs = j;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.treatLoadErrorsAsEndOfStream = z;
        this.tag = obj;
        this.dataSpec = new DataSpec(uri, 3);
        SinglePeriodTimeline singlePeriodTimeline = new SinglePeriodTimeline(j, true, false, obj);
        this.timeline = singlePeriodTimeline;
    }

    @Nullable
    public Object getTag() {
        return this.tag;
    }

    public void prepareSourceInternal(@Nullable TransferListener transferListener2) {
        this.transferListener = transferListener2;
        refreshSourceInfo(this.timeline, null);
    }

    public MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        SingleSampleMediaPeriod singleSampleMediaPeriod = new SingleSampleMediaPeriod(this.dataSpec, this.dataSourceFactory, this.transferListener, this.format, this.durationUs, this.loadErrorHandlingPolicy, createEventDispatcher(mediaPeriodId), this.treatLoadErrorsAsEndOfStream);
        return singleSampleMediaPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((SingleSampleMediaPeriod) mediaPeriod).release();
    }
}
