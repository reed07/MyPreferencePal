package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.BinarySearchSeeker;
import com.google.android.exoplayer2.extractor.BinarySearchSeeker.DefaultSeekTimestampConverter;
import com.google.android.exoplayer2.extractor.BinarySearchSeeker.OutputFrameHolder;
import com.google.android.exoplayer2.extractor.BinarySearchSeeker.TimestampSearchResult;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

final class TsBinarySearchSeeker extends BinarySearchSeeker {
    private static final int MINIMUM_SEARCH_RANGE_BYTES = 940;
    private static final long SEEK_TOLERANCE_US = 100000;
    private static final int TIMESTAMP_SEARCH_BYTES = 112800;

    private static final class TsPcrSeeker implements TimestampSeeker {
        private final ParsableByteArray packetBuffer = new ParsableByteArray();
        private final int pcrPid;
        private final TimestampAdjuster pcrTimestampAdjuster;

        public TsPcrSeeker(int i, TimestampAdjuster timestampAdjuster) {
            this.pcrPid = i;
            this.pcrTimestampAdjuster = timestampAdjuster;
        }

        public TimestampSearchResult searchForTimestamp(ExtractorInput extractorInput, long j, OutputFrameHolder outputFrameHolder) throws IOException, InterruptedException {
            long position = extractorInput.getPosition();
            int min = (int) Math.min(112800, extractorInput.getLength() - position);
            this.packetBuffer.reset(min);
            extractorInput.peekFully(this.packetBuffer.data, 0, min);
            return searchForPcrValueInBuffer(this.packetBuffer, j, position);
        }

        private TimestampSearchResult searchForPcrValueInBuffer(ParsableByteArray parsableByteArray, long j, long j2) {
            ParsableByteArray parsableByteArray2 = parsableByteArray;
            long j3 = j2;
            int limit = parsableByteArray.limit();
            long j4 = -1;
            long j5 = -1;
            long j6 = -9223372036854775807L;
            while (parsableByteArray.bytesLeft() >= 188) {
                int findSyncBytePosition = TsUtil.findSyncBytePosition(parsableByteArray2.data, parsableByteArray.getPosition(), limit);
                int i = findSyncBytePosition + 188;
                if (i > limit) {
                    break;
                }
                long readPcrFromPacket = TsUtil.readPcrFromPacket(parsableByteArray2, findSyncBytePosition, this.pcrPid);
                if (readPcrFromPacket != -9223372036854775807L) {
                    long adjustTsTimestamp = this.pcrTimestampAdjuster.adjustTsTimestamp(readPcrFromPacket);
                    if (adjustTsTimestamp > j) {
                        if (j6 == -9223372036854775807L) {
                            return TimestampSearchResult.overestimatedResult(adjustTsTimestamp, j3);
                        }
                        return TimestampSearchResult.targetFoundResult(j3 + j5);
                    } else if (TsBinarySearchSeeker.SEEK_TOLERANCE_US + adjustTsTimestamp > j) {
                        return TimestampSearchResult.targetFoundResult(j3 + ((long) findSyncBytePosition));
                    } else {
                        j5 = (long) findSyncBytePosition;
                        j6 = adjustTsTimestamp;
                    }
                }
                parsableByteArray2.setPosition(i);
                j4 = (long) i;
            }
            if (j6 != -9223372036854775807L) {
                return TimestampSearchResult.underestimatedResult(j6, j3 + j4);
            }
            return TimestampSearchResult.NO_TIMESTAMP_IN_RANGE_RESULT;
        }

        public void onSeekFinished() {
            this.packetBuffer.reset(Util.EMPTY_BYTE_ARRAY);
        }
    }

    public TsBinarySearchSeeker(TimestampAdjuster timestampAdjuster, long j, long j2, int i) {
        long j3 = j;
        super(new DefaultSeekTimestampConverter(), new TsPcrSeeker(i, timestampAdjuster), j3, 0, j + 1, 0, j2, 188, MINIMUM_SEARCH_RANGE_BYTES);
    }
}
