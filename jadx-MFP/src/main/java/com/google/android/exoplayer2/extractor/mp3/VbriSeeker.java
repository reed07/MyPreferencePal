package com.google.android.exoplayer2.extractor.mp3;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.extractor.MpegAudioHeader;
import com.google.android.exoplayer2.extractor.SeekMap.SeekPoints;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

final class VbriSeeker implements Seeker {
    private static final String TAG = "VbriSeeker";
    private final long dataEndPosition;
    private final long durationUs;
    private final long[] positions;
    private final long[] timesUs;

    public boolean isSeekable() {
        return true;
    }

    @Nullable
    public static VbriSeeker create(long j, long j2, MpegAudioHeader mpegAudioHeader, ParsableByteArray parsableByteArray) {
        int i;
        long j3 = j;
        MpegAudioHeader mpegAudioHeader2 = mpegAudioHeader;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.skipBytes(10);
        int readInt = parsableByteArray.readInt();
        if (readInt <= 0) {
            return null;
        }
        int i2 = mpegAudioHeader2.sampleRate;
        long scaleLargeTimestamp = Util.scaleLargeTimestamp((long) readInt, 1000000 * ((long) (i2 >= 32000 ? 1152 : 576)), (long) i2);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
        int readUnsignedShort3 = parsableByteArray.readUnsignedShort();
        parsableByteArray2.skipBytes(2);
        long j4 = j2 + ((long) mpegAudioHeader2.frameSize);
        long[] jArr = new long[readUnsignedShort];
        long[] jArr2 = new long[readUnsignedShort];
        int i3 = 0;
        long j5 = j2;
        while (i3 < readUnsignedShort) {
            int i4 = readUnsignedShort2;
            jArr[i3] = (((long) i3) * scaleLargeTimestamp) / ((long) readUnsignedShort);
            jArr2[i3] = Math.max(j5, j4);
            switch (readUnsignedShort3) {
                case 1:
                    i = parsableByteArray.readUnsignedByte();
                    break;
                case 2:
                    i = parsableByteArray.readUnsignedShort();
                    break;
                case 3:
                    i = parsableByteArray.readUnsignedInt24();
                    break;
                case 4:
                    i = parsableByteArray.readUnsignedIntToInt();
                    break;
                default:
                    return null;
            }
            j5 += (long) (i * i4);
            i3++;
            readUnsignedShort2 = i4;
        }
        if (!(j3 == -1 || j3 == j5)) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("VBRI data size mismatch: ");
            sb.append(j3);
            sb.append(", ");
            sb.append(j5);
            Log.w(str, sb.toString());
        }
        VbriSeeker vbriSeeker = new VbriSeeker(jArr, jArr2, scaleLargeTimestamp, j5);
        return vbriSeeker;
    }

    private VbriSeeker(long[] jArr, long[] jArr2, long j, long j2) {
        this.timesUs = jArr;
        this.positions = jArr2;
        this.durationUs = j;
        this.dataEndPosition = j2;
    }

    public SeekPoints getSeekPoints(long j) {
        int binarySearchFloor = Util.binarySearchFloor(this.timesUs, j, true, true);
        SeekPoint seekPoint = new SeekPoint(this.timesUs[binarySearchFloor], this.positions[binarySearchFloor]);
        if (seekPoint.timeUs < j) {
            long[] jArr = this.timesUs;
            if (binarySearchFloor != jArr.length - 1) {
                int i = binarySearchFloor + 1;
                return new SeekPoints(seekPoint, new SeekPoint(jArr[i], this.positions[i]));
            }
        }
        return new SeekPoints(seekPoint);
    }

    public long getTimeUs(long j) {
        return this.timesUs[Util.binarySearchFloor(this.positions, j, true, true)];
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public long getDataEndPosition() {
        return this.dataEndPosition;
    }
}
