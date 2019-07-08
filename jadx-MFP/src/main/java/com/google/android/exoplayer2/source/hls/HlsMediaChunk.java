package com.google.android.exoplayer2.source.hls;

import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.metadata.id3.PrivFrame;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist.HlsUrl;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

final class HlsMediaChunk extends MediaChunk {
    public static final String PRIV_TIMESTAMP_FRAME_OWNER = "com.apple.streaming.transportStreamTimestamp";
    private static final AtomicInteger uidSource = new AtomicInteger();
    public final int discontinuitySequenceNumber;
    private final DrmInitData drmInitData;
    private Extractor extractor;
    private final HlsExtractorFactory extractorFactory;
    private final boolean hasGapTag;
    public final HlsUrl hlsUrl;
    private final ParsableByteArray id3Data;
    private final Id3Decoder id3Decoder;
    private final DataSource initDataSource;
    private final DataSpec initDataSpec;
    private boolean initLoadCompleted;
    private int initSegmentBytesLoaded;
    private final boolean isEncrypted;
    private final boolean isMasterTimestampSource;
    private volatile boolean loadCanceled;
    private boolean loadCompleted;
    private final List<Format> muxedCaptionFormats;
    private int nextLoadPosition;
    private HlsSampleStreamWrapper output;
    private final Extractor previousExtractor;
    private final boolean shouldSpliceIn;
    private final TimestampAdjuster timestampAdjuster;
    public final int uid;

    public HlsMediaChunk(HlsExtractorFactory hlsExtractorFactory, DataSource dataSource, DataSpec dataSpec, DataSpec dataSpec2, HlsUrl hlsUrl2, List<Format> list, int i, Object obj, long j, long j2, long j3, int i2, boolean z, boolean z2, TimestampAdjuster timestampAdjuster2, HlsMediaChunk hlsMediaChunk, DrmInitData drmInitData2, byte[] bArr, byte[] bArr2) {
        HlsUrl hlsUrl3 = hlsUrl2;
        int i3 = i2;
        HlsMediaChunk hlsMediaChunk2 = hlsMediaChunk;
        super(buildDataSource(dataSource, bArr, bArr2), dataSpec, hlsUrl3.format, i, obj, j, j2, j3);
        this.discontinuitySequenceNumber = i3;
        this.initDataSpec = dataSpec2;
        this.hlsUrl = hlsUrl3;
        this.isMasterTimestampSource = z2;
        this.timestampAdjuster = timestampAdjuster2;
        boolean z3 = true;
        this.isEncrypted = bArr != null;
        this.hasGapTag = z;
        this.extractorFactory = hlsExtractorFactory;
        this.muxedCaptionFormats = list;
        this.drmInitData = drmInitData2;
        Extractor extractor2 = null;
        if (hlsMediaChunk2 != null) {
            this.id3Decoder = hlsMediaChunk2.id3Decoder;
            this.id3Data = hlsMediaChunk2.id3Data;
            if (hlsMediaChunk2.hlsUrl == hlsUrl3 && hlsMediaChunk2.loadCompleted) {
                z3 = false;
            }
            this.shouldSpliceIn = z3;
            if (hlsMediaChunk2.discontinuitySequenceNumber == i3 && !this.shouldSpliceIn) {
                extractor2 = hlsMediaChunk2.extractor;
            }
        } else {
            this.id3Decoder = new Id3Decoder();
            this.id3Data = new ParsableByteArray(10);
            this.shouldSpliceIn = false;
        }
        this.previousExtractor = extractor2;
        this.initDataSource = dataSource;
        this.uid = uidSource.getAndIncrement();
    }

    public void init(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
        this.output = hlsSampleStreamWrapper;
    }

    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    public void cancelLoad() {
        this.loadCanceled = true;
    }

    public void load() throws IOException, InterruptedException {
        maybeLoadInitData();
        if (!this.loadCanceled) {
            if (!this.hasGapTag) {
                loadMedia();
            }
            this.loadCompleted = true;
        }
    }

    private void maybeLoadInitData() throws IOException, InterruptedException {
        DefaultExtractorInput prepareExtraction;
        if (!this.initLoadCompleted) {
            DataSpec dataSpec = this.initDataSpec;
            if (dataSpec != null) {
                try {
                    prepareExtraction = prepareExtraction(this.initDataSource, dataSpec.subrange((long) this.initSegmentBytesLoaded));
                    int i = 0;
                    while (i == 0) {
                        if (this.loadCanceled) {
                            break;
                        }
                        i = this.extractor.read(prepareExtraction, null);
                    }
                    this.initSegmentBytesLoaded = (int) (prepareExtraction.getPosition() - this.initDataSpec.absoluteStreamPosition);
                    Util.closeQuietly(this.initDataSource);
                    this.initLoadCompleted = true;
                } catch (Throwable th) {
                    Util.closeQuietly(this.initDataSource);
                    throw th;
                }
            }
        }
    }

    private void loadMedia() throws IOException, InterruptedException {
        boolean z;
        DataSpec dataSpec;
        DefaultExtractorInput prepareExtraction;
        int i = 0;
        if (this.isEncrypted) {
            dataSpec = this.dataSpec;
            z = this.nextLoadPosition != 0;
        } else {
            dataSpec = this.dataSpec.subrange((long) this.nextLoadPosition);
            z = false;
        }
        if (!this.isMasterTimestampSource) {
            this.timestampAdjuster.waitUntilInitialized();
        } else if (this.timestampAdjuster.getFirstSampleTimestampUs() == Long.MAX_VALUE) {
            this.timestampAdjuster.setFirstSampleTimestampUs(this.startTimeUs);
        }
        try {
            prepareExtraction = prepareExtraction(this.dataSource, dataSpec);
            if (z) {
                prepareExtraction.skipFully(this.nextLoadPosition);
            }
            while (i == 0) {
                if (this.loadCanceled) {
                    break;
                }
                i = this.extractor.read(prepareExtraction, null);
            }
            this.nextLoadPosition = (int) (prepareExtraction.getPosition() - this.dataSpec.absoluteStreamPosition);
            Util.closeQuietly((DataSource) this.dataSource);
        } catch (Throwable th) {
            Util.closeQuietly((DataSource) this.dataSource);
            throw th;
        }
    }

    private DefaultExtractorInput prepareExtraction(DataSource dataSource, DataSpec dataSpec) throws IOException, InterruptedException {
        DataSpec dataSpec2 = dataSpec;
        DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(dataSource, dataSpec2.absoluteStreamPosition, dataSource.open(dataSpec));
        if (this.extractor != null) {
            return defaultExtractorInput;
        }
        long peekId3PrivTimestamp = peekId3PrivTimestamp(defaultExtractorInput);
        defaultExtractorInput.resetPeekPosition();
        DefaultExtractorInput defaultExtractorInput2 = defaultExtractorInput;
        Pair createExtractor = this.extractorFactory.createExtractor(this.previousExtractor, dataSpec2.uri, this.trackFormat, this.muxedCaptionFormats, this.drmInitData, this.timestampAdjuster, dataSource.getResponseHeaders(), defaultExtractorInput2);
        this.extractor = (Extractor) createExtractor.first;
        boolean z = true;
        boolean z2 = this.extractor == this.previousExtractor;
        if (((Boolean) createExtractor.second).booleanValue()) {
            this.output.setSampleOffsetUs(peekId3PrivTimestamp != -9223372036854775807L ? this.timestampAdjuster.adjustTsTimestamp(peekId3PrivTimestamp) : this.startTimeUs);
        }
        if (!z2 || this.initDataSpec == null) {
            z = false;
        }
        this.initLoadCompleted = z;
        this.output.init(this.uid, this.shouldSpliceIn, z2);
        if (z2) {
            return defaultExtractorInput2;
        }
        this.extractor.init(this.output);
        return defaultExtractorInput2;
    }

    private long peekId3PrivTimestamp(ExtractorInput extractorInput) throws IOException, InterruptedException {
        extractorInput.resetPeekPosition();
        try {
            extractorInput.peekFully(this.id3Data.data, 0, 10);
            this.id3Data.reset(10);
            if (this.id3Data.readUnsignedInt24() != Id3Decoder.ID3_TAG) {
                return -9223372036854775807L;
            }
            this.id3Data.skipBytes(3);
            int readSynchSafeInt = this.id3Data.readSynchSafeInt();
            int i = readSynchSafeInt + 10;
            if (i > this.id3Data.capacity()) {
                byte[] bArr = this.id3Data.data;
                this.id3Data.reset(i);
                System.arraycopy(bArr, 0, this.id3Data.data, 0, 10);
            }
            extractorInput.peekFully(this.id3Data.data, 10, readSynchSafeInt);
            Metadata decode = this.id3Decoder.decode(this.id3Data.data, readSynchSafeInt);
            if (decode == null) {
                return -9223372036854775807L;
            }
            int length = decode.length();
            for (int i2 = 0; i2 < length; i2++) {
                Entry entry = decode.get(i2);
                if (entry instanceof PrivFrame) {
                    PrivFrame privFrame = (PrivFrame) entry;
                    if (PRIV_TIMESTAMP_FRAME_OWNER.equals(privFrame.owner)) {
                        System.arraycopy(privFrame.privateData, 0, this.id3Data.data, 0, 8);
                        this.id3Data.reset(8);
                        return this.id3Data.readLong() & 8589934591L;
                    }
                }
            }
            return -9223372036854775807L;
        } catch (EOFException unused) {
            return -9223372036854775807L;
        }
    }

    private static DataSource buildDataSource(DataSource dataSource, byte[] bArr, byte[] bArr2) {
        return bArr != null ? new Aes128DataSource(dataSource, bArr, bArr2) : dataSource;
    }
}
