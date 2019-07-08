package com.google.android.exoplayer2.source.smoothstreaming;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.extractor.mp4.Track;
import com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.ChunkExtractorWrapper;
import com.google.android.exoplayer2.source.chunk.ChunkHolder;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.exoplayer2.source.chunk.ContainerMediaChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest.StreamElement;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.List;

public class DefaultSsChunkSource implements SsChunkSource {
    private int currentManifestChunkOffset;
    private final DataSource dataSource;
    private final ChunkExtractorWrapper[] extractorWrappers;
    private IOException fatalError;
    private SsManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int streamElementIndex;
    private final TrackSelection trackSelection;

    public static final class Factory implements com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource.Factory {
        private final com.google.android.exoplayer2.upstream.DataSource.Factory dataSourceFactory;

        public Factory(com.google.android.exoplayer2.upstream.DataSource.Factory factory) {
            this.dataSourceFactory = factory;
        }

        public SsChunkSource createChunkSource(LoaderErrorThrower loaderErrorThrower, SsManifest ssManifest, int i, TrackSelection trackSelection, TrackEncryptionBox[] trackEncryptionBoxArr) {
            DefaultSsChunkSource defaultSsChunkSource = new DefaultSsChunkSource(loaderErrorThrower, ssManifest, i, trackSelection, this.dataSourceFactory.createDataSource(), trackEncryptionBoxArr);
            return defaultSsChunkSource;
        }
    }

    public void onChunkLoadCompleted(Chunk chunk) {
    }

    public DefaultSsChunkSource(LoaderErrorThrower loaderErrorThrower, SsManifest ssManifest, int i, TrackSelection trackSelection2, DataSource dataSource2, TrackEncryptionBox[] trackEncryptionBoxArr) {
        SsManifest ssManifest2 = ssManifest;
        int i2 = i;
        TrackSelection trackSelection3 = trackSelection2;
        this.manifestLoaderErrorThrower = loaderErrorThrower;
        this.manifest = ssManifest2;
        this.streamElementIndex = i2;
        this.trackSelection = trackSelection3;
        this.dataSource = dataSource2;
        StreamElement streamElement = ssManifest2.streamElements[i2];
        this.extractorWrappers = new ChunkExtractorWrapper[trackSelection2.length()];
        int i3 = 0;
        while (i3 < this.extractorWrappers.length) {
            int indexInTrackGroup = trackSelection3.getIndexInTrackGroup(i3);
            Format format = streamElement.formats[indexInTrackGroup];
            int i4 = i3;
            Track track = r7;
            Track track2 = new Track(indexInTrackGroup, streamElement.type, streamElement.timescale, -9223372036854775807L, ssManifest2.durationUs, format, 0, trackEncryptionBoxArr, streamElement.type == 2 ? 4 : 0, null, null);
            this.extractorWrappers[i4] = new ChunkExtractorWrapper(new FragmentedMp4Extractor(3, null, track, null), streamElement.type, format);
            i3 = i4 + 1;
        }
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        StreamElement streamElement = this.manifest.streamElements[this.streamElementIndex];
        int chunkIndex = streamElement.getChunkIndex(j);
        long startTimeUs = streamElement.getStartTimeUs(chunkIndex);
        return Util.resolveSeekPositionUs(j, seekParameters, startTimeUs, (startTimeUs >= j || chunkIndex >= streamElement.chunkCount + -1) ? startTimeUs : streamElement.getStartTimeUs(chunkIndex + 1));
    }

    public void updateManifest(SsManifest ssManifest) {
        StreamElement streamElement = this.manifest.streamElements[this.streamElementIndex];
        int i = streamElement.chunkCount;
        StreamElement streamElement2 = ssManifest.streamElements[this.streamElementIndex];
        if (i == 0 || streamElement2.chunkCount == 0) {
            this.currentManifestChunkOffset += i;
        } else {
            int i2 = i - 1;
            long startTimeUs = streamElement.getStartTimeUs(i2) + streamElement.getChunkDurationUs(i2);
            long startTimeUs2 = streamElement2.getStartTimeUs(0);
            if (startTimeUs <= startTimeUs2) {
                this.currentManifestChunkOffset += i;
            } else {
                this.currentManifestChunkOffset += streamElement.getChunkIndex(startTimeUs2);
            }
        }
        this.manifest = ssManifest;
    }

    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException == null) {
            this.manifestLoaderErrorThrower.maybeThrowError();
            return;
        }
        throw iOException;
    }

    public int getPreferredQueueSize(long j, List<? extends MediaChunk> list) {
        if (this.fatalError != null || this.trackSelection.length() < 2) {
            return list.size();
        }
        return this.trackSelection.evaluateQueueSize(j, list);
    }

    public final void getNextChunk(MediaChunk mediaChunk, long j, long j2, ChunkHolder chunkHolder) {
        int i;
        long j3 = j;
        long j4 = j2;
        ChunkHolder chunkHolder2 = chunkHolder;
        if (this.fatalError == null) {
            StreamElement streamElement = this.manifest.streamElements[this.streamElementIndex];
            if (streamElement.chunkCount == 0) {
                chunkHolder2.endOfStream = !this.manifest.isLive;
                return;
            }
            if (mediaChunk == null) {
                i = streamElement.getChunkIndex(j4);
            } else {
                int nextChunkIndex = (int) (mediaChunk.getNextChunkIndex() - ((long) this.currentManifestChunkOffset));
                if (nextChunkIndex < 0) {
                    this.fatalError = new BehindLiveWindowException();
                    return;
                }
                i = nextChunkIndex;
            }
            if (i >= streamElement.chunkCount) {
                chunkHolder2.endOfStream = !this.manifest.isLive;
                return;
            }
            this.trackSelection.updateSelectedTrack(j, j4 - j3, resolveTimeToLiveEdgeUs(j3));
            long startTimeUs = streamElement.getStartTimeUs(i);
            long chunkDurationUs = startTimeUs + streamElement.getChunkDurationUs(i);
            long j5 = mediaChunk == null ? j4 : -9223372036854775807L;
            int i2 = i + this.currentManifestChunkOffset;
            int selectedIndex = this.trackSelection.getSelectedIndex();
            chunkHolder2.chunk = newMediaChunk(this.trackSelection.getSelectedFormat(), this.dataSource, streamElement.buildRequestUri(this.trackSelection.getIndexInTrackGroup(selectedIndex), i), null, i2, startTimeUs, chunkDurationUs, j5, this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), this.extractorWrappers[selectedIndex]);
        }
    }

    public boolean onChunkLoadError(Chunk chunk, boolean z, Exception exc) {
        if (z) {
            TrackSelection trackSelection2 = this.trackSelection;
            if (ChunkedTrackBlacklistUtil.maybeBlacklistTrack(trackSelection2, trackSelection2.indexOf(chunk.trackFormat), exc)) {
                return true;
            }
        }
        return false;
    }

    private static MediaChunk newMediaChunk(Format format, DataSource dataSource2, Uri uri, String str, int i, long j, long j2, long j3, int i2, Object obj, ChunkExtractorWrapper chunkExtractorWrapper) {
        Format format2 = format;
        DataSource dataSource3 = dataSource2;
        long j4 = j;
        long j5 = j;
        long j6 = j2;
        long j7 = j3;
        int i3 = i2;
        Object obj2 = obj;
        ChunkExtractorWrapper chunkExtractorWrapper2 = chunkExtractorWrapper;
        DataSpec dataSpec = r25;
        DataSpec dataSpec2 = new DataSpec(uri, 0, -1, str);
        ContainerMediaChunk containerMediaChunk = new ContainerMediaChunk(dataSource3, dataSpec, format2, i3, obj2, j4, j6, j7, (long) i, 1, j5, chunkExtractorWrapper2);
        return containerMediaChunk;
    }

    private long resolveTimeToLiveEdgeUs(long j) {
        if (!this.manifest.isLive) {
            return -9223372036854775807L;
        }
        StreamElement streamElement = this.manifest.streamElements[this.streamElementIndex];
        int i = streamElement.chunkCount - 1;
        return (streamElement.getStartTimeUs(i) + streamElement.getChunkDurationUs(i)) - j;
    }
}
