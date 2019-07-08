package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.TrackOutput.CryptoData;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

final class SampleMetadataQueue {
    private static final int SAMPLE_CAPACITY_INCREMENT = 1000;
    private int absoluteFirstIndex;
    private int capacity = 1000;
    private CryptoData[] cryptoDatas;
    private int[] flags;
    private Format[] formats;
    private boolean isLastSampleQueued;
    private long largestDiscardedTimestampUs;
    private long largestQueuedTimestampUs;
    private int length;
    private long[] offsets;
    private int readPosition;
    private int relativeFirstIndex;
    private int[] sizes;
    private int[] sourceIds;
    private long[] timesUs;
    private Format upstreamFormat;
    private boolean upstreamFormatRequired;
    private boolean upstreamKeyframeRequired;
    private int upstreamSourceId;

    public static final class SampleExtrasHolder {
        public CryptoData cryptoData;
        public long offset;
        public int size;
    }

    public SampleMetadataQueue() {
        int i = this.capacity;
        this.sourceIds = new int[i];
        this.offsets = new long[i];
        this.timesUs = new long[i];
        this.flags = new int[i];
        this.sizes = new int[i];
        this.cryptoDatas = new CryptoData[i];
        this.formats = new Format[i];
        this.largestDiscardedTimestampUs = Long.MIN_VALUE;
        this.largestQueuedTimestampUs = Long.MIN_VALUE;
        this.upstreamFormatRequired = true;
        this.upstreamKeyframeRequired = true;
    }

    public void reset(boolean z) {
        this.length = 0;
        this.absoluteFirstIndex = 0;
        this.relativeFirstIndex = 0;
        this.readPosition = 0;
        this.upstreamKeyframeRequired = true;
        this.largestDiscardedTimestampUs = Long.MIN_VALUE;
        this.largestQueuedTimestampUs = Long.MIN_VALUE;
        this.isLastSampleQueued = false;
        if (z) {
            this.upstreamFormat = null;
            this.upstreamFormatRequired = true;
        }
    }

    public int getWriteIndex() {
        return this.absoluteFirstIndex + this.length;
    }

    public long discardUpstreamSamples(int i) {
        int writeIndex = getWriteIndex() - i;
        boolean z = false;
        Assertions.checkArgument(writeIndex >= 0 && writeIndex <= this.length - this.readPosition);
        this.length -= writeIndex;
        this.largestQueuedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(this.length));
        if (writeIndex == 0 && this.isLastSampleQueued) {
            z = true;
        }
        this.isLastSampleQueued = z;
        int i2 = this.length;
        if (i2 == 0) {
            return 0;
        }
        int relativeIndex = getRelativeIndex(i2 - 1);
        return this.offsets[relativeIndex] + ((long) this.sizes[relativeIndex]);
    }

    public void sourceId(int i) {
        this.upstreamSourceId = i;
    }

    public int getFirstIndex() {
        return this.absoluteFirstIndex;
    }

    public int getReadIndex() {
        return this.absoluteFirstIndex + this.readPosition;
    }

    public int peekSourceId() {
        return hasNextSample() ? this.sourceIds[getRelativeIndex(this.readPosition)] : this.upstreamSourceId;
    }

    public synchronized boolean hasNextSample() {
        return this.readPosition != this.length;
    }

    public synchronized Format getUpstreamFormat() {
        return this.upstreamFormatRequired ? null : this.upstreamFormat;
    }

    public synchronized long getLargestQueuedTimestampUs() {
        return this.largestQueuedTimestampUs;
    }

    public synchronized boolean isLastSampleQueued() {
        return this.isLastSampleQueued;
    }

    public synchronized long getFirstTimestampUs() {
        return this.length == 0 ? Long.MIN_VALUE : this.timesUs[this.relativeFirstIndex];
    }

    public synchronized void rewind() {
        this.readPosition = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0022, code lost:
        return -3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(com.google.android.exoplayer2.FormatHolder r5, com.google.android.exoplayer2.decoder.DecoderInputBuffer r6, boolean r7, boolean r8, com.google.android.exoplayer2.Format r9, com.google.android.exoplayer2.source.SampleMetadataQueue.SampleExtrasHolder r10) {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.hasNextSample()     // Catch:{ all -> 0x006f }
            r1 = -5
            r2 = -3
            r3 = -4
            if (r0 != 0) goto L_0x0029
            if (r8 != 0) goto L_0x0023
            boolean r8 = r4.isLastSampleQueued     // Catch:{ all -> 0x006f }
            if (r8 == 0) goto L_0x0011
            goto L_0x0023
        L_0x0011:
            com.google.android.exoplayer2.Format r6 = r4.upstreamFormat     // Catch:{ all -> 0x006f }
            if (r6 == 0) goto L_0x0021
            if (r7 != 0) goto L_0x001b
            com.google.android.exoplayer2.Format r6 = r4.upstreamFormat     // Catch:{ all -> 0x006f }
            if (r6 == r9) goto L_0x0021
        L_0x001b:
            com.google.android.exoplayer2.Format r6 = r4.upstreamFormat     // Catch:{ all -> 0x006f }
            r5.format = r6     // Catch:{ all -> 0x006f }
            monitor-exit(r4)
            return r1
        L_0x0021:
            monitor-exit(r4)
            return r2
        L_0x0023:
            r5 = 4
            r6.setFlags(r5)     // Catch:{ all -> 0x006f }
            monitor-exit(r4)
            return r3
        L_0x0029:
            int r8 = r4.readPosition     // Catch:{ all -> 0x006f }
            int r8 = r4.getRelativeIndex(r8)     // Catch:{ all -> 0x006f }
            if (r7 != 0) goto L_0x0067
            com.google.android.exoplayer2.Format[] r7 = r4.formats     // Catch:{ all -> 0x006f }
            r7 = r7[r8]     // Catch:{ all -> 0x006f }
            if (r7 == r9) goto L_0x0038
            goto L_0x0067
        L_0x0038:
            boolean r5 = r6.isFlagsOnly()     // Catch:{ all -> 0x006f }
            if (r5 == 0) goto L_0x0040
            monitor-exit(r4)
            return r2
        L_0x0040:
            long[] r5 = r4.timesUs     // Catch:{ all -> 0x006f }
            r0 = r5[r8]     // Catch:{ all -> 0x006f }
            r6.timeUs = r0     // Catch:{ all -> 0x006f }
            int[] r5 = r4.flags     // Catch:{ all -> 0x006f }
            r5 = r5[r8]     // Catch:{ all -> 0x006f }
            r6.setFlags(r5)     // Catch:{ all -> 0x006f }
            int[] r5 = r4.sizes     // Catch:{ all -> 0x006f }
            r5 = r5[r8]     // Catch:{ all -> 0x006f }
            r10.size = r5     // Catch:{ all -> 0x006f }
            long[] r5 = r4.offsets     // Catch:{ all -> 0x006f }
            r6 = r5[r8]     // Catch:{ all -> 0x006f }
            r10.offset = r6     // Catch:{ all -> 0x006f }
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData[] r5 = r4.cryptoDatas     // Catch:{ all -> 0x006f }
            r5 = r5[r8]     // Catch:{ all -> 0x006f }
            r10.cryptoData = r5     // Catch:{ all -> 0x006f }
            int r5 = r4.readPosition     // Catch:{ all -> 0x006f }
            int r5 = r5 + 1
            r4.readPosition = r5     // Catch:{ all -> 0x006f }
            monitor-exit(r4)
            return r3
        L_0x0067:
            com.google.android.exoplayer2.Format[] r6 = r4.formats     // Catch:{ all -> 0x006f }
            r6 = r6[r8]     // Catch:{ all -> 0x006f }
            r5.format = r6     // Catch:{ all -> 0x006f }
            monitor-exit(r4)
            return r1
        L_0x006f:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleMetadataQueue.read(com.google.android.exoplayer2.FormatHolder, com.google.android.exoplayer2.decoder.DecoderInputBuffer, boolean, boolean, com.google.android.exoplayer2.Format, com.google.android.exoplayer2.source.SampleMetadataQueue$SampleExtrasHolder):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0038, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int advanceTo(long r9, boolean r11, boolean r12) {
        /*
            r8 = this;
            monitor-enter(r8)
            int r0 = r8.readPosition     // Catch:{ all -> 0x0039 }
            int r2 = r8.getRelativeIndex(r0)     // Catch:{ all -> 0x0039 }
            boolean r0 = r8.hasNextSample()     // Catch:{ all -> 0x0039 }
            r7 = -1
            if (r0 == 0) goto L_0x0037
            long[] r0 = r8.timesUs     // Catch:{ all -> 0x0039 }
            r3 = r0[r2]     // Catch:{ all -> 0x0039 }
            int r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r0 < 0) goto L_0x0037
            long r0 = r8.largestQueuedTimestampUs     // Catch:{ all -> 0x0039 }
            int r3 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x001f
            if (r12 != 0) goto L_0x001f
            goto L_0x0037
        L_0x001f:
            int r12 = r8.length     // Catch:{ all -> 0x0039 }
            int r0 = r8.readPosition     // Catch:{ all -> 0x0039 }
            int r3 = r12 - r0
            r1 = r8
            r4 = r9
            r6 = r11
            int r9 = r1.findSampleBefore(r2, r3, r4, r6)     // Catch:{ all -> 0x0039 }
            if (r9 != r7) goto L_0x0030
            monitor-exit(r8)
            return r7
        L_0x0030:
            int r10 = r8.readPosition     // Catch:{ all -> 0x0039 }
            int r10 = r10 + r9
            r8.readPosition = r10     // Catch:{ all -> 0x0039 }
            monitor-exit(r8)
            return r9
        L_0x0037:
            monitor-exit(r8)
            return r7
        L_0x0039:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleMetadataQueue.advanceTo(long, boolean, boolean):int");
    }

    public synchronized int advanceToEnd() {
        int i;
        i = this.length - this.readPosition;
        this.readPosition = this.length;
        return i;
    }

    public synchronized boolean setReadPosition(int i) {
        if (this.absoluteFirstIndex > i || i > this.absoluteFirstIndex + this.length) {
            return false;
        }
        this.readPosition = i - this.absoluteFirstIndex;
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0037, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized long discardTo(long r10, boolean r12, boolean r13) {
        /*
            r9 = this;
            monitor-enter(r9)
            int r0 = r9.length     // Catch:{ all -> 0x0038 }
            r1 = -1
            if (r0 == 0) goto L_0x0036
            long[] r0 = r9.timesUs     // Catch:{ all -> 0x0038 }
            int r3 = r9.relativeFirstIndex     // Catch:{ all -> 0x0038 }
            r3 = r0[r3]     // Catch:{ all -> 0x0038 }
            int r0 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x0012
            goto L_0x0036
        L_0x0012:
            if (r13 == 0) goto L_0x001f
            int r13 = r9.readPosition     // Catch:{ all -> 0x0038 }
            int r0 = r9.length     // Catch:{ all -> 0x0038 }
            if (r13 == r0) goto L_0x001f
            int r13 = r9.readPosition     // Catch:{ all -> 0x0038 }
            int r13 = r13 + 1
            goto L_0x0021
        L_0x001f:
            int r13 = r9.length     // Catch:{ all -> 0x0038 }
        L_0x0021:
            r5 = r13
            int r4 = r9.relativeFirstIndex     // Catch:{ all -> 0x0038 }
            r3 = r9
            r6 = r10
            r8 = r12
            int r10 = r3.findSampleBefore(r4, r5, r6, r8)     // Catch:{ all -> 0x0038 }
            r11 = -1
            if (r10 != r11) goto L_0x0030
            monitor-exit(r9)
            return r1
        L_0x0030:
            long r10 = r9.discardSamples(r10)     // Catch:{ all -> 0x0038 }
            monitor-exit(r9)
            return r10
        L_0x0036:
            monitor-exit(r9)
            return r1
        L_0x0038:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleMetadataQueue.discardTo(long, boolean, boolean):long");
    }

    public synchronized long discardToRead() {
        if (this.readPosition == 0) {
            return -1;
        }
        return discardSamples(this.readPosition);
    }

    public synchronized long discardToEnd() {
        if (this.length == 0) {
            return -1;
        }
        return discardSamples(this.length);
    }

    public synchronized boolean format(Format format) {
        if (format == null) {
            this.upstreamFormatRequired = true;
            return false;
        }
        this.upstreamFormatRequired = false;
        if (Util.areEqual(format, this.upstreamFormat)) {
            return false;
        }
        this.upstreamFormat = format;
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00e0, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void commitSample(long r6, int r8, long r9, int r11, com.google.android.exoplayer2.extractor.TrackOutput.CryptoData r12) {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.upstreamKeyframeRequired     // Catch:{ all -> 0x00e1 }
            r1 = 0
            if (r0 == 0) goto L_0x000e
            r0 = r8 & 1
            if (r0 != 0) goto L_0x000c
            monitor-exit(r5)
            return
        L_0x000c:
            r5.upstreamKeyframeRequired = r1     // Catch:{ all -> 0x00e1 }
        L_0x000e:
            boolean r0 = r5.upstreamFormatRequired     // Catch:{ all -> 0x00e1 }
            r2 = 1
            if (r0 != 0) goto L_0x0015
            r0 = 1
            goto L_0x0016
        L_0x0015:
            r0 = 0
        L_0x0016:
            com.google.android.exoplayer2.util.Assertions.checkState(r0)     // Catch:{ all -> 0x00e1 }
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r8
            if (r0 == 0) goto L_0x0020
            r0 = 1
            goto L_0x0021
        L_0x0020:
            r0 = 0
        L_0x0021:
            r5.isLastSampleQueued = r0     // Catch:{ all -> 0x00e1 }
            long r3 = r5.largestQueuedTimestampUs     // Catch:{ all -> 0x00e1 }
            long r3 = java.lang.Math.max(r3, r6)     // Catch:{ all -> 0x00e1 }
            r5.largestQueuedTimestampUs = r3     // Catch:{ all -> 0x00e1 }
            int r0 = r5.length     // Catch:{ all -> 0x00e1 }
            int r0 = r5.getRelativeIndex(r0)     // Catch:{ all -> 0x00e1 }
            long[] r3 = r5.timesUs     // Catch:{ all -> 0x00e1 }
            r3[r0] = r6     // Catch:{ all -> 0x00e1 }
            long[] r6 = r5.offsets     // Catch:{ all -> 0x00e1 }
            r6[r0] = r9     // Catch:{ all -> 0x00e1 }
            int[] r6 = r5.sizes     // Catch:{ all -> 0x00e1 }
            r6[r0] = r11     // Catch:{ all -> 0x00e1 }
            int[] r6 = r5.flags     // Catch:{ all -> 0x00e1 }
            r6[r0] = r8     // Catch:{ all -> 0x00e1 }
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData[] r6 = r5.cryptoDatas     // Catch:{ all -> 0x00e1 }
            r6[r0] = r12     // Catch:{ all -> 0x00e1 }
            com.google.android.exoplayer2.Format[] r6 = r5.formats     // Catch:{ all -> 0x00e1 }
            com.google.android.exoplayer2.Format r7 = r5.upstreamFormat     // Catch:{ all -> 0x00e1 }
            r6[r0] = r7     // Catch:{ all -> 0x00e1 }
            int[] r6 = r5.sourceIds     // Catch:{ all -> 0x00e1 }
            int r7 = r5.upstreamSourceId     // Catch:{ all -> 0x00e1 }
            r6[r0] = r7     // Catch:{ all -> 0x00e1 }
            int r6 = r5.length     // Catch:{ all -> 0x00e1 }
            int r6 = r6 + r2
            r5.length = r6     // Catch:{ all -> 0x00e1 }
            int r6 = r5.length     // Catch:{ all -> 0x00e1 }
            int r7 = r5.capacity     // Catch:{ all -> 0x00e1 }
            if (r6 != r7) goto L_0x00df
            int r6 = r5.capacity     // Catch:{ all -> 0x00e1 }
            int r6 = r6 + 1000
            int[] r7 = new int[r6]     // Catch:{ all -> 0x00e1 }
            long[] r8 = new long[r6]     // Catch:{ all -> 0x00e1 }
            long[] r9 = new long[r6]     // Catch:{ all -> 0x00e1 }
            int[] r10 = new int[r6]     // Catch:{ all -> 0x00e1 }
            int[] r11 = new int[r6]     // Catch:{ all -> 0x00e1 }
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData[] r12 = new com.google.android.exoplayer2.extractor.TrackOutput.CryptoData[r6]     // Catch:{ all -> 0x00e1 }
            com.google.android.exoplayer2.Format[] r0 = new com.google.android.exoplayer2.Format[r6]     // Catch:{ all -> 0x00e1 }
            int r2 = r5.capacity     // Catch:{ all -> 0x00e1 }
            int r3 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e1 }
            int r2 = r2 - r3
            long[] r3 = r5.offsets     // Catch:{ all -> 0x00e1 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r3, r4, r8, r1, r2)     // Catch:{ all -> 0x00e1 }
            long[] r3 = r5.timesUs     // Catch:{ all -> 0x00e1 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r3, r4, r9, r1, r2)     // Catch:{ all -> 0x00e1 }
            int[] r3 = r5.flags     // Catch:{ all -> 0x00e1 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r3, r4, r10, r1, r2)     // Catch:{ all -> 0x00e1 }
            int[] r3 = r5.sizes     // Catch:{ all -> 0x00e1 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r3, r4, r11, r1, r2)     // Catch:{ all -> 0x00e1 }
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData[] r3 = r5.cryptoDatas     // Catch:{ all -> 0x00e1 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r3, r4, r12, r1, r2)     // Catch:{ all -> 0x00e1 }
            com.google.android.exoplayer2.Format[] r3 = r5.formats     // Catch:{ all -> 0x00e1 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r3, r4, r0, r1, r2)     // Catch:{ all -> 0x00e1 }
            int[] r3 = r5.sourceIds     // Catch:{ all -> 0x00e1 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r3, r4, r7, r1, r2)     // Catch:{ all -> 0x00e1 }
            int r3 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e1 }
            long[] r4 = r5.offsets     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r4, r1, r8, r2, r3)     // Catch:{ all -> 0x00e1 }
            long[] r4 = r5.timesUs     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r4, r1, r9, r2, r3)     // Catch:{ all -> 0x00e1 }
            int[] r4 = r5.flags     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r4, r1, r10, r2, r3)     // Catch:{ all -> 0x00e1 }
            int[] r4 = r5.sizes     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r4, r1, r11, r2, r3)     // Catch:{ all -> 0x00e1 }
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData[] r4 = r5.cryptoDatas     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r4, r1, r12, r2, r3)     // Catch:{ all -> 0x00e1 }
            com.google.android.exoplayer2.Format[] r4 = r5.formats     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r4, r1, r0, r2, r3)     // Catch:{ all -> 0x00e1 }
            int[] r4 = r5.sourceIds     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r4, r1, r7, r2, r3)     // Catch:{ all -> 0x00e1 }
            r5.offsets = r8     // Catch:{ all -> 0x00e1 }
            r5.timesUs = r9     // Catch:{ all -> 0x00e1 }
            r5.flags = r10     // Catch:{ all -> 0x00e1 }
            r5.sizes = r11     // Catch:{ all -> 0x00e1 }
            r5.cryptoDatas = r12     // Catch:{ all -> 0x00e1 }
            r5.formats = r0     // Catch:{ all -> 0x00e1 }
            r5.sourceIds = r7     // Catch:{ all -> 0x00e1 }
            r5.relativeFirstIndex = r1     // Catch:{ all -> 0x00e1 }
            int r7 = r5.capacity     // Catch:{ all -> 0x00e1 }
            r5.length = r7     // Catch:{ all -> 0x00e1 }
            r5.capacity = r6     // Catch:{ all -> 0x00e1 }
        L_0x00df:
            monitor-exit(r5)
            return
        L_0x00e1:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleMetadataQueue.commitSample(long, int, long, int, com.google.android.exoplayer2.extractor.TrackOutput$CryptoData):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000f, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean attemptSplice(long r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            int r0 = r7.length     // Catch:{ all -> 0x004a }
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0010
            long r3 = r7.largestDiscardedTimestampUs     // Catch:{ all -> 0x004a }
            int r0 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r0 <= 0) goto L_0x000e
            r1 = 1
        L_0x000e:
            monitor-exit(r7)
            return r1
        L_0x0010:
            long r3 = r7.largestDiscardedTimestampUs     // Catch:{ all -> 0x004a }
            int r0 = r7.readPosition     // Catch:{ all -> 0x004a }
            long r5 = r7.getLargestTimestamp(r0)     // Catch:{ all -> 0x004a }
            long r3 = java.lang.Math.max(r3, r5)     // Catch:{ all -> 0x004a }
            int r0 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r0 < 0) goto L_0x0022
            monitor-exit(r7)
            return r1
        L_0x0022:
            int r0 = r7.length     // Catch:{ all -> 0x004a }
            int r1 = r7.length     // Catch:{ all -> 0x004a }
            int r1 = r1 - r2
            int r1 = r7.getRelativeIndex(r1)     // Catch:{ all -> 0x004a }
        L_0x002b:
            int r3 = r7.readPosition     // Catch:{ all -> 0x004a }
            if (r0 <= r3) goto L_0x0042
            long[] r3 = r7.timesUs     // Catch:{ all -> 0x004a }
            r4 = r3[r1]     // Catch:{ all -> 0x004a }
            int r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r3 < 0) goto L_0x0042
            int r0 = r0 + -1
            int r1 = r1 + -1
            r3 = -1
            if (r1 != r3) goto L_0x002b
            int r1 = r7.capacity     // Catch:{ all -> 0x004a }
            int r1 = r1 - r2
            goto L_0x002b
        L_0x0042:
            int r8 = r7.absoluteFirstIndex     // Catch:{ all -> 0x004a }
            int r8 = r8 + r0
            r7.discardUpstreamSamples(r8)     // Catch:{ all -> 0x004a }
            monitor-exit(r7)
            return r2
        L_0x004a:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleMetadataQueue.attemptSplice(long):boolean");
    }

    private int findSampleBefore(int i, int i2, long j, boolean z) {
        int i3 = i;
        int i4 = -1;
        for (int i5 = 0; i5 < i2 && this.timesUs[i3] <= j; i5++) {
            if (!z || (this.flags[i3] & 1) != 0) {
                i4 = i5;
            }
            i3++;
            if (i3 == this.capacity) {
                i3 = 0;
            }
        }
        return i4;
    }

    private long discardSamples(int i) {
        this.largestDiscardedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(i));
        this.length -= i;
        this.absoluteFirstIndex += i;
        this.relativeFirstIndex += i;
        int i2 = this.relativeFirstIndex;
        int i3 = this.capacity;
        if (i2 >= i3) {
            this.relativeFirstIndex = i2 - i3;
        }
        this.readPosition -= i;
        if (this.readPosition < 0) {
            this.readPosition = 0;
        }
        if (this.length != 0) {
            return this.offsets[this.relativeFirstIndex];
        }
        int i4 = this.relativeFirstIndex;
        if (i4 == 0) {
            i4 = this.capacity;
        }
        int i5 = i4 - 1;
        return this.offsets[i5] + ((long) this.sizes[i5]);
    }

    private long getLargestTimestamp(int i) {
        long j = Long.MIN_VALUE;
        if (i == 0) {
            return Long.MIN_VALUE;
        }
        int relativeIndex = getRelativeIndex(i - 1);
        for (int i2 = 0; i2 < i; i2++) {
            j = Math.max(j, this.timesUs[relativeIndex]);
            if ((this.flags[relativeIndex] & 1) != 0) {
                break;
            }
            relativeIndex--;
            if (relativeIndex == -1) {
                relativeIndex = this.capacity - 1;
            }
        }
        return j;
    }

    private int getRelativeIndex(int i) {
        int i2 = this.relativeFirstIndex + i;
        int i3 = this.capacity;
        return i2 < i3 ? i2 : i2 - i3;
    }
}
