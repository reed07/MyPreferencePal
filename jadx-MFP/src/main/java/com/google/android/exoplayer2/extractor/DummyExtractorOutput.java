package com.google.android.exoplayer2.extractor;

public final class DummyExtractorOutput implements ExtractorOutput {
    public void endTracks() {
    }

    public void seekMap(SeekMap seekMap) {
    }

    public TrackOutput track(int i, int i2) {
        return new DummyTrackOutput();
    }
}
