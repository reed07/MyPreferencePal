package com.google.android.exoplayer2.source.dash.manifest;

import com.google.android.exoplayer2.source.dash.DashSegmentIndex;

final class SingleSegmentIndex implements DashSegmentIndex {
    private final RangedUri uri;

    public long getDurationUs(long j, long j2) {
        return j2;
    }

    public long getFirstSegmentNum() {
        return 0;
    }

    public int getSegmentCount(long j) {
        return 1;
    }

    public long getSegmentNum(long j, long j2) {
        return 0;
    }

    public long getTimeUs(long j) {
        return 0;
    }

    public boolean isExplicit() {
        return true;
    }

    public SingleSegmentIndex(RangedUri rangedUri) {
        this.uri = rangedUri;
    }

    public RangedUri getSegmentUrl(long j) {
        return this.uri;
    }
}
