package com.google.android.exoplayer2.text.cea;

import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Collections;
import java.util.List;

final class CeaSubtitle implements Subtitle {
    private final List<Cue> cues;

    public int getEventTimeCount() {
        return 1;
    }

    public int getNextEventTimeIndex(long j) {
        return j < 0 ? 0 : -1;
    }

    public CeaSubtitle(List<Cue> list) {
        this.cues = list;
    }

    public long getEventTime(int i) {
        Assertions.checkArgument(i == 0);
        return 0;
    }

    public List<Cue> getCues(long j) {
        return j >= 0 ? this.cues : Collections.emptyList();
    }
}
