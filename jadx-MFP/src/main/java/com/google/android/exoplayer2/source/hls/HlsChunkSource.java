package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.BaseMediaChunkIterator;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.DataChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunkIterator;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist.HlsUrl;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.Segment;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.trackselection.BaseTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

class HlsChunkSource {
    private final DataSource encryptionDataSource;
    private byte[] encryptionIv;
    private String encryptionIvString;
    private byte[] encryptionKey;
    private Uri encryptionKeyUri;
    private HlsUrl expectedPlaylistUrl;
    private final HlsExtractorFactory extractorFactory;
    private IOException fatalError;
    private boolean independentSegments;
    private boolean isTimestampMaster;
    private long liveEdgeInPeriodTimeUs = -9223372036854775807L;
    private final DataSource mediaDataSource;
    private final List<Format> muxedCaptionFormats;
    private final HlsPlaylistTracker playlistTracker;
    private byte[] scratchSpace;
    private boolean seenExpectedPlaylistError;
    private final TimestampAdjusterProvider timestampAdjusterProvider;
    private final TrackGroup trackGroup;
    private TrackSelection trackSelection;
    private final HlsUrl[] variants;

    private static final class EncryptionKeyChunk extends DataChunk {
        public final String iv;
        private byte[] result;

        public EncryptionKeyChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, byte[] bArr, String str) {
            super(dataSource, dataSpec, 3, format, i, obj, bArr);
            this.iv = str;
        }

        /* access modifiers changed from: protected */
        public void consume(byte[] bArr, int i) throws IOException {
            this.result = Arrays.copyOf(bArr, i);
        }

        public byte[] getResult() {
            return this.result;
        }
    }

    public static final class HlsChunkHolder {
        public Chunk chunk;
        public boolean endOfStream;
        public HlsUrl playlist;

        public HlsChunkHolder() {
            clear();
        }

        public void clear() {
            this.chunk = null;
            this.endOfStream = false;
            this.playlist = null;
        }
    }

    private static final class HlsMediaPlaylistSegmentIterator extends BaseMediaChunkIterator {
        private final HlsMediaPlaylist playlist;
        private final long startOfPlaylistInPeriodUs;

        public HlsMediaPlaylistSegmentIterator(HlsMediaPlaylist hlsMediaPlaylist, long j, int i) {
            super((long) i, (long) (hlsMediaPlaylist.segments.size() - 1));
            this.playlist = hlsMediaPlaylist;
            this.startOfPlaylistInPeriodUs = j;
        }

        public DataSpec getDataSpec() {
            checkInBounds();
            Segment segment = (Segment) this.playlist.segments.get((int) getCurrentIndex());
            DataSpec dataSpec = new DataSpec(UriUtil.resolveToUri(this.playlist.baseUri, segment.url), segment.byterangeOffset, segment.byterangeLength, null);
            return dataSpec;
        }

        public long getChunkStartTimeUs() {
            checkInBounds();
            return this.startOfPlaylistInPeriodUs + ((Segment) this.playlist.segments.get((int) getCurrentIndex())).relativeStartTimeUs;
        }

        public long getChunkEndTimeUs() {
            checkInBounds();
            Segment segment = (Segment) this.playlist.segments.get((int) getCurrentIndex());
            return this.startOfPlaylistInPeriodUs + segment.relativeStartTimeUs + segment.durationUs;
        }
    }

    private static final class InitializationTrackSelection extends BaseTrackSelection {
        private int selectedIndex;

        public Object getSelectionData() {
            return null;
        }

        public int getSelectionReason() {
            return 0;
        }

        public InitializationTrackSelection(TrackGroup trackGroup, int[] iArr) {
            super(trackGroup, iArr);
            this.selectedIndex = indexOf(trackGroup.getFormat(0));
        }

        public void updateSelectedTrack(long j, long j2, long j3, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (isBlacklisted(this.selectedIndex, elapsedRealtime)) {
                for (int i = this.length - 1; i >= 0; i--) {
                    if (!isBlacklisted(i, elapsedRealtime)) {
                        this.selectedIndex = i;
                        return;
                    }
                }
                throw new IllegalStateException();
            }
        }

        public int getSelectedIndex() {
            return this.selectedIndex;
        }
    }

    public HlsChunkSource(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, HlsUrl[] hlsUrlArr, HlsDataSourceFactory hlsDataSourceFactory, @Nullable TransferListener transferListener, TimestampAdjusterProvider timestampAdjusterProvider2, List<Format> list) {
        this.extractorFactory = hlsExtractorFactory;
        this.playlistTracker = hlsPlaylistTracker;
        this.variants = hlsUrlArr;
        this.timestampAdjusterProvider = timestampAdjusterProvider2;
        this.muxedCaptionFormats = list;
        Format[] formatArr = new Format[hlsUrlArr.length];
        int[] iArr = new int[hlsUrlArr.length];
        for (int i = 0; i < hlsUrlArr.length; i++) {
            formatArr[i] = hlsUrlArr[i].format;
            iArr[i] = i;
        }
        this.mediaDataSource = hlsDataSourceFactory.createDataSource(1);
        if (transferListener != null) {
            this.mediaDataSource.addTransferListener(transferListener);
        }
        this.encryptionDataSource = hlsDataSourceFactory.createDataSource(3);
        this.trackGroup = new TrackGroup(formatArr);
        this.trackSelection = new InitializationTrackSelection(this.trackGroup, iArr);
    }

    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException == null) {
            HlsUrl hlsUrl = this.expectedPlaylistUrl;
            if (hlsUrl != null && this.seenExpectedPlaylistError) {
                this.playlistTracker.maybeThrowPlaylistRefreshError(hlsUrl);
                return;
            }
            return;
        }
        throw iOException;
    }

    public TrackGroup getTrackGroup() {
        return this.trackGroup;
    }

    public void selectTracks(TrackSelection trackSelection2) {
        this.trackSelection = trackSelection2;
    }

    public TrackSelection getTrackSelection() {
        return this.trackSelection;
    }

    public void reset() {
        this.fatalError = null;
    }

    public void setIsTimestampMaster(boolean z) {
        this.isTimestampMaster = z;
    }

    public void getNextChunk(long j, long j2, List<HlsMediaChunk> list, HlsChunkHolder hlsChunkHolder) {
        HlsMediaChunk hlsMediaChunk;
        int i;
        long j3;
        long j4;
        long j5;
        int i2;
        HlsUrl hlsUrl;
        long j6 = j2;
        HlsChunkHolder hlsChunkHolder2 = hlsChunkHolder;
        if (list.isEmpty()) {
            List<HlsMediaChunk> list2 = list;
            hlsMediaChunk = null;
        } else {
            hlsMediaChunk = (HlsMediaChunk) list.get(list.size() - 1);
        }
        if (hlsMediaChunk == null) {
            i = -1;
        } else {
            i = this.trackGroup.indexOf(hlsMediaChunk.trackFormat);
        }
        long j7 = j6 - j;
        long resolveTimeToLiveEdgeUs = resolveTimeToLiveEdgeUs(j);
        if (hlsMediaChunk == null || this.independentSegments) {
            j4 = j7;
            j3 = resolveTimeToLiveEdgeUs;
        } else {
            long durationUs = hlsMediaChunk.getDurationUs();
            long max = Math.max(0, j7 - durationUs);
            if (resolveTimeToLiveEdgeUs != -9223372036854775807L) {
                j4 = max;
                j3 = Math.max(0, resolveTimeToLiveEdgeUs - durationUs);
            } else {
                j4 = max;
                j3 = resolveTimeToLiveEdgeUs;
            }
        }
        this.trackSelection.updateSelectedTrack(j, j4, j3, list, createMediaChunkIterators(hlsMediaChunk, j6));
        int selectedIndexInTrackGroup = this.trackSelection.getSelectedIndexInTrackGroup();
        boolean z = false;
        boolean z2 = i != selectedIndexInTrackGroup;
        HlsUrl hlsUrl2 = this.variants[selectedIndexInTrackGroup];
        if (!this.playlistTracker.isSnapshotValid(hlsUrl2)) {
            hlsChunkHolder2.playlist = hlsUrl2;
            boolean z3 = this.seenExpectedPlaylistError;
            if (this.expectedPlaylistUrl == hlsUrl2) {
                z = true;
            }
            this.seenExpectedPlaylistError = z3 & z;
            this.expectedPlaylistUrl = hlsUrl2;
            return;
        }
        HlsMediaPlaylist playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(hlsUrl2, true);
        this.independentSegments = playlistSnapshot.hasIndependentSegments;
        updateLiveEdgeTimeUs(playlistSnapshot);
        long initialStartTimeUs = playlistSnapshot.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
        HlsMediaChunk hlsMediaChunk2 = hlsMediaChunk;
        int i3 = i;
        long chunkMediaSequence = getChunkMediaSequence(hlsMediaChunk, z2, playlistSnapshot, initialStartTimeUs, j2);
        if (chunkMediaSequence >= playlistSnapshot.mediaSequence) {
            j5 = chunkMediaSequence;
            i2 = selectedIndexInTrackGroup;
            hlsUrl = hlsUrl2;
        } else if (hlsMediaChunk2 == null || !z2) {
            this.fatalError = new BehindLiveWindowException();
            return;
        } else {
            hlsUrl = this.variants[i3];
            playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(hlsUrl, true);
            initialStartTimeUs = playlistSnapshot.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
            j5 = hlsMediaChunk2.getNextChunkIndex();
            i2 = i3;
        }
        int i4 = (int) (j5 - playlistSnapshot.mediaSequence);
        if (i4 >= playlistSnapshot.segments.size()) {
            if (playlistSnapshot.hasEndTag) {
                hlsChunkHolder2.endOfStream = true;
            } else {
                hlsChunkHolder2.playlist = hlsUrl;
                boolean z4 = this.seenExpectedPlaylistError;
                if (this.expectedPlaylistUrl == hlsUrl) {
                    z = true;
                }
                this.seenExpectedPlaylistError = z4 & z;
                this.expectedPlaylistUrl = hlsUrl;
            }
            return;
        }
        this.seenExpectedPlaylistError = false;
        DataSpec dataSpec = null;
        this.expectedPlaylistUrl = null;
        Segment segment = (Segment) playlistSnapshot.segments.get(i4);
        if (segment.fullSegmentEncryptionKeyUri != null) {
            Uri resolveToUri = UriUtil.resolveToUri(playlistSnapshot.baseUri, segment.fullSegmentEncryptionKeyUri);
            if (!resolveToUri.equals(this.encryptionKeyUri)) {
                hlsChunkHolder2.chunk = newEncryptionKeyChunk(resolveToUri, segment.encryptionIV, i2, this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData());
                return;
            } else if (!Util.areEqual(segment.encryptionIV, this.encryptionIvString)) {
                setEncryptionData(resolveToUri, segment.encryptionIV, this.encryptionKey);
            }
        } else {
            clearEncryptionData();
        }
        Segment segment2 = segment.initializationSegment;
        if (segment2 != null) {
            dataSpec = new DataSpec(UriUtil.resolveToUri(playlistSnapshot.baseUri, segment2.url), segment2.byterangeOffset, segment2.byterangeLength, null);
        }
        long j8 = segment.relativeStartTimeUs + initialStartTimeUs;
        long j9 = j8;
        int i5 = playlistSnapshot.discontinuitySequence + segment.relativeDiscontinuitySequence;
        int i6 = i5;
        TimestampAdjuster adjuster = this.timestampAdjusterProvider.getAdjuster(i5);
        DataSpec dataSpec2 = r32;
        DataSpec dataSpec3 = new DataSpec(UriUtil.resolveToUri(playlistSnapshot.baseUri, segment.url), segment.byterangeOffset, segment.byterangeLength, null);
        HlsMediaChunk hlsMediaChunk3 = new HlsMediaChunk(this.extractorFactory, this.mediaDataSource, dataSpec2, dataSpec, hlsUrl, this.muxedCaptionFormats, this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), j9, j8 + segment.durationUs, j5, i6, segment.hasGapTag, this.isTimestampMaster, adjuster, hlsMediaChunk2, segment.drmInitData, this.encryptionKey, this.encryptionIv);
        hlsChunkHolder2.chunk = hlsMediaChunk3;
    }

    public void onChunkLoadCompleted(Chunk chunk) {
        if (chunk instanceof EncryptionKeyChunk) {
            EncryptionKeyChunk encryptionKeyChunk = (EncryptionKeyChunk) chunk;
            this.scratchSpace = encryptionKeyChunk.getDataHolder();
            setEncryptionData(encryptionKeyChunk.dataSpec.uri, encryptionKeyChunk.iv, encryptionKeyChunk.getResult());
        }
    }

    public boolean maybeBlacklistTrack(Chunk chunk, long j) {
        TrackSelection trackSelection2 = this.trackSelection;
        return trackSelection2.blacklist(trackSelection2.indexOf(this.trackGroup.indexOf(chunk.trackFormat)), j);
    }

    public boolean onPlaylistError(HlsUrl hlsUrl, long j) {
        int indexOf = this.trackGroup.indexOf(hlsUrl.format);
        boolean z = true;
        if (indexOf == -1) {
            return true;
        }
        int indexOf2 = this.trackSelection.indexOf(indexOf);
        if (indexOf2 == -1) {
            return true;
        }
        this.seenExpectedPlaylistError = (this.expectedPlaylistUrl == hlsUrl) | this.seenExpectedPlaylistError;
        if (j != -9223372036854775807L && !this.trackSelection.blacklist(indexOf2, j)) {
            z = false;
        }
        return z;
    }

    public MediaChunkIterator[] createMediaChunkIterators(@Nullable HlsMediaChunk hlsMediaChunk, long j) {
        int i;
        HlsMediaChunk hlsMediaChunk2 = hlsMediaChunk;
        if (hlsMediaChunk2 == null) {
            i = -1;
        } else {
            i = this.trackGroup.indexOf(hlsMediaChunk2.trackFormat);
        }
        MediaChunkIterator[] mediaChunkIteratorArr = new MediaChunkIterator[this.trackSelection.length()];
        for (int i2 = 0; i2 < mediaChunkIteratorArr.length; i2++) {
            int indexInTrackGroup = this.trackSelection.getIndexInTrackGroup(i2);
            HlsUrl hlsUrl = this.variants[indexInTrackGroup];
            if (!this.playlistTracker.isSnapshotValid(hlsUrl)) {
                mediaChunkIteratorArr[i2] = MediaChunkIterator.EMPTY;
            } else {
                HlsMediaPlaylist playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(hlsUrl, false);
                long initialStartTimeUs = playlistSnapshot.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
                long j2 = initialStartTimeUs;
                long chunkMediaSequence = getChunkMediaSequence(hlsMediaChunk, indexInTrackGroup != i, playlistSnapshot, initialStartTimeUs, j);
                if (chunkMediaSequence < playlistSnapshot.mediaSequence) {
                    mediaChunkIteratorArr[i2] = MediaChunkIterator.EMPTY;
                } else {
                    mediaChunkIteratorArr[i2] = new HlsMediaPlaylistSegmentIterator(playlistSnapshot, j2, (int) (chunkMediaSequence - playlistSnapshot.mediaSequence));
                }
            }
        }
        return mediaChunkIteratorArr;
    }

    private long getChunkMediaSequence(@Nullable HlsMediaChunk hlsMediaChunk, boolean z, HlsMediaPlaylist hlsMediaPlaylist, long j, long j2) {
        if (hlsMediaChunk != null && !z) {
            return hlsMediaChunk.getNextChunkIndex();
        }
        long j3 = hlsMediaPlaylist.durationUs + j;
        if (hlsMediaChunk != null && !this.independentSegments) {
            j2 = hlsMediaChunk.startTimeUs;
        }
        if (!hlsMediaPlaylist.hasEndTag && j2 >= j3) {
            return hlsMediaPlaylist.mediaSequence + ((long) hlsMediaPlaylist.segments.size());
        }
        return ((long) Util.binarySearchFloor(hlsMediaPlaylist.segments, Long.valueOf(j2 - j), true, !this.playlistTracker.isLive() || hlsMediaChunk == null)) + hlsMediaPlaylist.mediaSequence;
    }

    private long resolveTimeToLiveEdgeUs(long j) {
        if (this.liveEdgeInPeriodTimeUs != -9223372036854775807L) {
            return this.liveEdgeInPeriodTimeUs - j;
        }
        return -9223372036854775807L;
    }

    private void updateLiveEdgeTimeUs(HlsMediaPlaylist hlsMediaPlaylist) {
        long j;
        if (hlsMediaPlaylist.hasEndTag) {
            j = -9223372036854775807L;
        } else {
            j = hlsMediaPlaylist.getEndTimeUs() - this.playlistTracker.getInitialStartTimeUs();
        }
        this.liveEdgeInPeriodTimeUs = j;
    }

    private EncryptionKeyChunk newEncryptionKeyChunk(Uri uri, String str, int i, int i2, Object obj) {
        DataSpec dataSpec = new DataSpec(uri, 0, -1, null, 1);
        EncryptionKeyChunk encryptionKeyChunk = new EncryptionKeyChunk(this.encryptionDataSource, dataSpec, this.variants[i].format, i2, obj, this.scratchSpace, str);
        return encryptionKeyChunk;
    }

    private void setEncryptionData(Uri uri, String str, byte[] bArr) {
        byte[] byteArray = new BigInteger(Util.toLowerInvariant(str).startsWith("0x") ? str.substring(2) : str, 16).toByteArray();
        byte[] bArr2 = new byte[16];
        int length = byteArray.length > 16 ? byteArray.length - 16 : 0;
        System.arraycopy(byteArray, length, bArr2, (bArr2.length - byteArray.length) + length, byteArray.length - length);
        this.encryptionKeyUri = uri;
        this.encryptionKey = bArr;
        this.encryptionIvString = str;
        this.encryptionIv = bArr2;
    }

    private void clearEncryptionData() {
        this.encryptionKeyUri = null;
        this.encryptionKey = null;
        this.encryptionIvString = null;
        this.encryptionIv = null;
    }
}
