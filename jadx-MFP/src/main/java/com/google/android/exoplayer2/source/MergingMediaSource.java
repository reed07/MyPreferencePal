package com.google.android.exoplayer2.source;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public final class MergingMediaSource extends CompositeMediaSource<Integer> {
    private static final int PERIOD_COUNT_UNSET = -1;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final MediaSource[] mediaSources;
    private IllegalMergeException mergeError;
    private final ArrayList<MediaSource> pendingTimelineSources;
    private int periodCount;
    private Object primaryManifest;
    private final Timeline[] timelines;

    public static final class IllegalMergeException extends IOException {
        public static final int REASON_PERIOD_COUNT_MISMATCH = 0;
        public final int reason;

        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface Reason {
        }

        public IllegalMergeException(int i) {
            this.reason = i;
        }
    }

    public MergingMediaSource(MediaSource... mediaSourceArr) {
        this(new DefaultCompositeSequenceableLoaderFactory(), mediaSourceArr);
    }

    public MergingMediaSource(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, MediaSource... mediaSourceArr) {
        this.mediaSources = mediaSourceArr;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.pendingTimelineSources = new ArrayList<>(Arrays.asList(mediaSourceArr));
        this.periodCount = -1;
        this.timelines = new Timeline[mediaSourceArr.length];
    }

    @Nullable
    public Object getTag() {
        MediaSource[] mediaSourceArr = this.mediaSources;
        if (mediaSourceArr.length > 0) {
            return mediaSourceArr[0].getTag();
        }
        return null;
    }

    public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        for (int i = 0; i < this.mediaSources.length; i++) {
            prepareChildSource(Integer.valueOf(i), this.mediaSources[i]);
        }
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        IllegalMergeException illegalMergeException = this.mergeError;
        if (illegalMergeException == null) {
            super.maybeThrowSourceInfoRefreshError();
            return;
        }
        throw illegalMergeException;
    }

    public MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        MediaPeriod[] mediaPeriodArr = new MediaPeriod[this.mediaSources.length];
        int indexOfPeriod = this.timelines[0].getIndexOfPeriod(mediaPeriodId.periodUid);
        for (int i = 0; i < mediaPeriodArr.length; i++) {
            mediaPeriodArr[i] = this.mediaSources[i].createPeriod(mediaPeriodId.copyWithPeriodUid(this.timelines[i].getUidOfPeriod(indexOfPeriod)), allocator, j);
        }
        return new MergingMediaPeriod(this.compositeSequenceableLoaderFactory, mediaPeriodArr);
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        MergingMediaPeriod mergingMediaPeriod = (MergingMediaPeriod) mediaPeriod;
        int i = 0;
        while (true) {
            MediaSource[] mediaSourceArr = this.mediaSources;
            if (i < mediaSourceArr.length) {
                mediaSourceArr[i].releasePeriod(mergingMediaPeriod.periods[i]);
                i++;
            } else {
                return;
            }
        }
    }

    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        Arrays.fill(this.timelines, null);
        this.primaryManifest = null;
        this.periodCount = -1;
        this.mergeError = null;
        this.pendingTimelineSources.clear();
        Collections.addAll(this.pendingTimelineSources, this.mediaSources);
    }

    /* access modifiers changed from: protected */
    public void onChildSourceInfoRefreshed(Integer num, MediaSource mediaSource, Timeline timeline, @Nullable Object obj) {
        if (this.mergeError == null) {
            this.mergeError = checkTimelineMerges(timeline);
        }
        if (this.mergeError == null) {
            this.pendingTimelineSources.remove(mediaSource);
            this.timelines[num.intValue()] = timeline;
            if (mediaSource == this.mediaSources[0]) {
                this.primaryManifest = obj;
            }
            if (this.pendingTimelineSources.isEmpty()) {
                refreshSourceInfo(this.timelines[0], this.primaryManifest);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Integer num, MediaPeriodId mediaPeriodId) {
        if (num.intValue() == 0) {
            return mediaPeriodId;
        }
        return null;
    }

    private IllegalMergeException checkTimelineMerges(Timeline timeline) {
        if (this.periodCount == -1) {
            this.periodCount = timeline.getPeriodCount();
        } else if (timeline.getPeriodCount() != this.periodCount) {
            return new IllegalMergeException(0);
        }
        return null;
    }
}
