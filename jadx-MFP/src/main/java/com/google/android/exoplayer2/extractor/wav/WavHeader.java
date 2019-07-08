package com.google.android.exoplayer2.extractor.wav;

import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekMap.SeekPoints;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.Util;

final class WavHeader implements SeekMap {
    private final int averageBytesPerSecond;
    private final int bitsPerSample;
    private final int blockAlignment;
    private long dataSize;
    private long dataStartPosition;
    private final int encoding;
    private final int numChannels;
    private final int sampleRateHz;

    public boolean isSeekable() {
        return true;
    }

    public WavHeader(int i, int i2, int i3, int i4, int i5, int i6) {
        this.numChannels = i;
        this.sampleRateHz = i2;
        this.averageBytesPerSecond = i3;
        this.blockAlignment = i4;
        this.bitsPerSample = i5;
        this.encoding = i6;
    }

    public void setDataBounds(long j, long j2) {
        this.dataStartPosition = j;
        this.dataSize = j2;
    }

    public long getDataLimit() {
        if (hasDataBounds()) {
            return this.dataStartPosition + this.dataSize;
        }
        return -1;
    }

    public boolean hasDataBounds() {
        return (this.dataStartPosition == 0 || this.dataSize == 0) ? false : true;
    }

    public long getDurationUs() {
        return ((this.dataSize / ((long) this.blockAlignment)) * 1000000) / ((long) this.sampleRateHz);
    }

    public SeekPoints getSeekPoints(long j) {
        long j2 = (((long) this.averageBytesPerSecond) * j) / 1000000;
        int i = this.blockAlignment;
        long constrainValue = Util.constrainValue((j2 / ((long) i)) * ((long) i), 0, this.dataSize - ((long) i));
        long j3 = this.dataStartPosition + constrainValue;
        long timeUs = getTimeUs(j3);
        SeekPoint seekPoint = new SeekPoint(timeUs, j3);
        if (timeUs < j) {
            long j4 = this.dataSize;
            int i2 = this.blockAlignment;
            if (constrainValue != j4 - ((long) i2)) {
                long j5 = j3 + ((long) i2);
                return new SeekPoints(seekPoint, new SeekPoint(getTimeUs(j5), j5));
            }
        }
        return new SeekPoints(seekPoint);
    }

    public long getTimeUs(long j) {
        return (Math.max(0, j - this.dataStartPosition) * 1000000) / ((long) this.averageBytesPerSecond);
    }

    public int getBytesPerFrame() {
        return this.blockAlignment;
    }

    public int getBitrate() {
        return this.sampleRateHz * this.bitsPerSample * this.numChannels;
    }

    public int getSampleRateHz() {
        return this.sampleRateHz;
    }

    public int getNumChannels() {
        return this.numChannels;
    }

    public int getEncoding() {
        return this.encoding;
    }
}
