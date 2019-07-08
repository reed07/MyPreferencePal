package com.google.android.exoplayer2.source;

import android.os.Handler;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import java.io.IOException;

public interface MediaSource {

    /* renamed from: com.google.android.exoplayer2.source.MediaSource$-CC reason: invalid class name */
    public final /* synthetic */ class CC {
        @Nullable
        public static Object $default$getTag(MediaSource mediaSource) {
            return null;
        }
    }

    public static final class MediaPeriodId {
        public final int adGroupIndex;
        public final int adIndexInAdGroup;
        public final long endPositionUs;
        public final Object periodUid;
        public final long windowSequenceNumber;

        public MediaPeriodId(Object obj) {
            this(obj, -1);
        }

        public MediaPeriodId(Object obj, long j) {
            this(obj, -1, -1, j, Long.MIN_VALUE);
        }

        public MediaPeriodId(Object obj, long j, long j2) {
            this(obj, -1, -1, j, j2);
        }

        public MediaPeriodId(Object obj, int i, int i2, long j) {
            this(obj, i, i2, j, Long.MIN_VALUE);
        }

        private MediaPeriodId(Object obj, int i, int i2, long j, long j2) {
            this.periodUid = obj;
            this.adGroupIndex = i;
            this.adIndexInAdGroup = i2;
            this.windowSequenceNumber = j;
            this.endPositionUs = j2;
        }

        public MediaPeriodId copyWithPeriodUid(Object obj) {
            if (this.periodUid.equals(obj)) {
                return this;
            }
            MediaPeriodId mediaPeriodId = new MediaPeriodId(obj, this.adGroupIndex, this.adIndexInAdGroup, this.windowSequenceNumber, this.endPositionUs);
            return mediaPeriodId;
        }

        public boolean isAd() {
            return this.adGroupIndex != -1;
        }

        public boolean equals(@Nullable Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            MediaPeriodId mediaPeriodId = (MediaPeriodId) obj;
            if (!(this.periodUid.equals(mediaPeriodId.periodUid) && this.adGroupIndex == mediaPeriodId.adGroupIndex && this.adIndexInAdGroup == mediaPeriodId.adIndexInAdGroup && this.windowSequenceNumber == mediaPeriodId.windowSequenceNumber && this.endPositionUs == mediaPeriodId.endPositionUs)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return ((((((((527 + this.periodUid.hashCode()) * 31) + this.adGroupIndex) * 31) + this.adIndexInAdGroup) * 31) + ((int) this.windowSequenceNumber)) * 31) + ((int) this.endPositionUs);
        }
    }

    public interface SourceInfoRefreshListener {
        void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline, @Nullable Object obj);
    }

    void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener);

    MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator, long j);

    @Nullable
    Object getTag();

    void maybeThrowSourceInfoRefreshError() throws IOException;

    void prepareSource(SourceInfoRefreshListener sourceInfoRefreshListener, @Nullable TransferListener transferListener);

    void releasePeriod(MediaPeriod mediaPeriod);

    void releaseSource(SourceInfoRefreshListener sourceInfoRefreshListener);

    void removeEventListener(MediaSourceEventListener mediaSourceEventListener);
}
