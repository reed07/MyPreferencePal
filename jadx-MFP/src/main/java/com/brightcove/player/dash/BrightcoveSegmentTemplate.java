package com.brightcove.player.dash;

import com.google.android.exoplayer2.source.dash.manifest.RangedUri;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTemplate;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTimelineElement;
import com.google.android.exoplayer2.source.dash.manifest.UrlTemplate;
import java.util.List;

public class BrightcoveSegmentTemplate extends SegmentTemplate {
    private long duration;
    private List<SegmentTimelineElement> segmentTimeline;
    private long startNumber;
    private long timescale;

    public BrightcoveSegmentTemplate(RangedUri rangedUri, long j, long j2, long j3, long j4, List<SegmentTimelineElement> list, UrlTemplate urlTemplate, UrlTemplate urlTemplate2) {
        super(rangedUri, j, j2, j3, j4, list, urlTemplate, urlTemplate2);
        this.segmentTimeline = list;
        this.duration = j4;
        this.startNumber = j3;
        this.timescale = j;
    }

    public int getSegmentCount(long j) {
        List<SegmentTimelineElement> list = this.segmentTimeline;
        if (list != null) {
            return list.size();
        }
        if (j == -9223372036854775807L) {
            return -1;
        }
        return (int) DashUtil.ceilDivide((double) j, ((double) (this.duration * 1000000)) / ((double) this.timescale));
    }
}
