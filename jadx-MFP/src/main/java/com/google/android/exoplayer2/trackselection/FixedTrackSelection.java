package com.google.android.exoplayer2.trackselection;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunkIterator;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import java.util.List;

public final class FixedTrackSelection extends BaseTrackSelection {
    @Nullable
    private final Object data;
    private final int reason;

    @Deprecated
    public static final class Factory implements com.google.android.exoplayer2.trackselection.TrackSelection.Factory {
        @Nullable
        private final Object data;
        private final int reason;

        public Factory() {
            this.reason = 0;
            this.data = null;
        }

        public Factory(int i, @Nullable Object obj) {
            this.reason = i;
            this.data = obj;
        }

        public FixedTrackSelection createTrackSelection(TrackGroup trackGroup, BandwidthMeter bandwidthMeter, int... iArr) {
            boolean z = true;
            if (iArr.length != 1) {
                z = false;
            }
            Assertions.checkArgument(z);
            return new FixedTrackSelection(trackGroup, iArr[0], this.reason, this.data);
        }
    }

    public int getSelectedIndex() {
        return 0;
    }

    public void updateSelectedTrack(long j, long j2, long j3, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
    }

    public FixedTrackSelection(TrackGroup trackGroup, int i) {
        this(trackGroup, i, 0, null);
    }

    public FixedTrackSelection(TrackGroup trackGroup, int i, int i2, @Nullable Object obj) {
        super(trackGroup, i);
        this.reason = i2;
        this.data = obj;
    }

    public int getSelectionReason() {
        return this.reason;
    }

    @Nullable
    public Object getSelectionData() {
        return this.data;
    }
}
