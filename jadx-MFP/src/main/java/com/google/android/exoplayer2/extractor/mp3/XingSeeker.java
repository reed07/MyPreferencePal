package com.google.android.exoplayer2.extractor.mp3;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.extractor.MpegAudioHeader;
import com.google.android.exoplayer2.extractor.SeekMap.SeekPoints;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

final class XingSeeker implements Seeker {
    private static final String TAG = "XingSeeker";
    private final long dataEndPosition;
    private final long dataSize;
    private final long dataStartPosition;
    private final long durationUs;
    @Nullable
    private final long[] tableOfContents;
    private final int xingFrameSize;

    @Nullable
    public static XingSeeker create(long j, long j2, MpegAudioHeader mpegAudioHeader, ParsableByteArray parsableByteArray) {
        long j3 = j;
        MpegAudioHeader mpegAudioHeader2 = mpegAudioHeader;
        int i = mpegAudioHeader2.samplesPerFrame;
        int i2 = mpegAudioHeader2.sampleRate;
        int readInt = parsableByteArray.readInt();
        if ((readInt & 1) == 1) {
            int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            if (readUnsignedIntToInt != 0) {
                long scaleLargeTimestamp = Util.scaleLargeTimestamp((long) readUnsignedIntToInt, ((long) i) * 1000000, (long) i2);
                if ((readInt & 6) != 6) {
                    XingSeeker xingSeeker = new XingSeeker(j2, mpegAudioHeader2.frameSize, scaleLargeTimestamp);
                    return xingSeeker;
                }
                long readUnsignedIntToInt2 = (long) parsableByteArray.readUnsignedIntToInt();
                long[] jArr = new long[100];
                for (int i3 = 0; i3 < 100; i3++) {
                    jArr[i3] = (long) parsableByteArray.readUnsignedByte();
                }
                if (j3 != -1) {
                    long j4 = j2 + readUnsignedIntToInt2;
                    if (j3 != j4) {
                        String str = TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("XING data size mismatch: ");
                        sb.append(j3);
                        sb.append(", ");
                        sb.append(j4);
                        Log.w(str, sb.toString());
                    }
                }
                XingSeeker xingSeeker2 = new XingSeeker(j2, mpegAudioHeader2.frameSize, scaleLargeTimestamp, readUnsignedIntToInt2, jArr);
                return xingSeeker2;
            }
        }
        return null;
    }

    private XingSeeker(long j, int i, long j2) {
        this(j, i, j2, -1, null);
    }

    private XingSeeker(long j, int i, long j2, long j3, @Nullable long[] jArr) {
        this.dataStartPosition = j;
        this.xingFrameSize = i;
        this.durationUs = j2;
        this.tableOfContents = jArr;
        this.dataSize = j3;
        long j4 = -1;
        if (j3 != -1) {
            j4 = j + j3;
        }
        this.dataEndPosition = j4;
    }

    public boolean isSeekable() {
        return this.tableOfContents != null;
    }

    public SeekPoints getSeekPoints(long j) {
        double d;
        if (!isSeekable()) {
            return new SeekPoints(new SeekPoint(0, this.dataStartPosition + ((long) this.xingFrameSize)));
        }
        long constrainValue = Util.constrainValue(j, 0, this.durationUs);
        double d2 = (((double) constrainValue) * 100.0d) / ((double) this.durationUs);
        double d3 = 0.0d;
        if (d2 > 0.0d) {
            if (d2 >= 100.0d) {
                d3 = 256.0d;
            } else {
                int i = (int) d2;
                long[] jArr = (long[]) Assertions.checkNotNull(this.tableOfContents);
                double d4 = (double) jArr[i];
                if (i == 99) {
                    d = 256.0d;
                } else {
                    d = (double) jArr[i + 1];
                }
                d3 = d4 + ((d2 - ((double) i)) * (d - d4));
            }
        }
        return new SeekPoints(new SeekPoint(constrainValue, this.dataStartPosition + Util.constrainValue(Math.round((d3 / 256.0d) * ((double) this.dataSize)), (long) this.xingFrameSize, this.dataSize - 1)));
    }

    public long getTimeUs(long j) {
        long j2;
        long j3 = j - this.dataStartPosition;
        if (!isSeekable() || j3 <= ((long) this.xingFrameSize)) {
            return 0;
        }
        long[] jArr = (long[]) Assertions.checkNotNull(this.tableOfContents);
        double d = (((double) j3) * 256.0d) / ((double) this.dataSize);
        int binarySearchFloor = Util.binarySearchFloor(jArr, (long) d, true, true);
        long timeUsForTableIndex = getTimeUsForTableIndex(binarySearchFloor);
        long j4 = jArr[binarySearchFloor];
        int i = binarySearchFloor + 1;
        long timeUsForTableIndex2 = getTimeUsForTableIndex(i);
        if (binarySearchFloor == 99) {
            j2 = 256;
        } else {
            j2 = jArr[i];
        }
        return timeUsForTableIndex + Math.round((j4 == j2 ? 0.0d : (d - ((double) j4)) / ((double) (j2 - j4))) * ((double) (timeUsForTableIndex2 - timeUsForTableIndex)));
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    private long getTimeUsForTableIndex(int i) {
        return (this.durationUs * ((long) i)) / 100;
    }
}
