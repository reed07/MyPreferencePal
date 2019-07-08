package com.google.android.exoplayer2.source.chunk;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.chunk.ChunkSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.Loader.Callback;
import com.google.android.exoplayer2.upstream.Loader.LoadErrorAction;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChunkSampleStream<T extends ChunkSource> implements SampleStream, SequenceableLoader, Callback<Chunk>, com.google.android.exoplayer2.upstream.Loader.ReleaseCallback {
    private static final String TAG = "ChunkSampleStream";
    private final SequenceableLoader.Callback<ChunkSampleStream<T>> callback;
    private final T chunkSource;
    long decodeOnlyUntilPositionUs;
    private final SampleQueue[] embeddedSampleQueues;
    /* access modifiers changed from: private */
    public final Format[] embeddedTrackFormats;
    /* access modifiers changed from: private */
    public final int[] embeddedTrackTypes;
    /* access modifiers changed from: private */
    public final boolean[] embeddedTracksSelected;
    /* access modifiers changed from: private */
    public final EventDispatcher eventDispatcher;
    /* access modifiers changed from: private */
    public long lastSeekPositionUs;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final Loader loader;
    boolean loadingFinished;
    private final BaseMediaChunkOutput mediaChunkOutput;
    private final ArrayList<BaseMediaChunk> mediaChunks;
    private final ChunkHolder nextChunkHolder;
    private int nextNotifyPrimaryFormatMediaChunkIndex;
    private long pendingResetPositionUs;
    private Format primaryDownstreamTrackFormat;
    private final SampleQueue primarySampleQueue;
    public final int primaryTrackType;
    private final List<BaseMediaChunk> readOnlyMediaChunks;
    @Nullable
    private ReleaseCallback<T> releaseCallback;

    public final class EmbeddedSampleStream implements SampleStream {
        private final int index;
        private boolean notifiedDownstreamFormat;
        public final ChunkSampleStream<T> parent;
        private final SampleQueue sampleQueue;

        public void maybeThrowError() throws IOException {
        }

        public EmbeddedSampleStream(ChunkSampleStream<T> chunkSampleStream, SampleQueue sampleQueue2, int i) {
            this.parent = chunkSampleStream;
            this.sampleQueue = sampleQueue2;
            this.index = i;
        }

        public boolean isReady() {
            return ChunkSampleStream.this.loadingFinished || (!ChunkSampleStream.this.isPendingReset() && this.sampleQueue.hasNextSample());
        }

        public int skipData(long j) {
            int i = 0;
            if (ChunkSampleStream.this.isPendingReset()) {
                return 0;
            }
            maybeNotifyDownstreamFormat();
            if (!ChunkSampleStream.this.loadingFinished || j <= this.sampleQueue.getLargestQueuedTimestampUs()) {
                int advanceTo = this.sampleQueue.advanceTo(j, true, true);
                if (advanceTo != -1) {
                    i = advanceTo;
                }
            } else {
                i = this.sampleQueue.advanceToEnd();
            }
            return i;
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
            if (ChunkSampleStream.this.isPendingReset()) {
                return -3;
            }
            maybeNotifyDownstreamFormat();
            return this.sampleQueue.read(formatHolder, decoderInputBuffer, z, ChunkSampleStream.this.loadingFinished, ChunkSampleStream.this.decodeOnlyUntilPositionUs);
        }

        public void release() {
            Assertions.checkState(ChunkSampleStream.this.embeddedTracksSelected[this.index]);
            ChunkSampleStream.this.embeddedTracksSelected[this.index] = false;
        }

        private void maybeNotifyDownstreamFormat() {
            if (!this.notifiedDownstreamFormat) {
                ChunkSampleStream.this.eventDispatcher.downstreamFormatChanged(ChunkSampleStream.this.embeddedTrackTypes[this.index], ChunkSampleStream.this.embeddedTrackFormats[this.index], 0, null, ChunkSampleStream.this.lastSeekPositionUs);
                this.notifiedDownstreamFormat = true;
            }
        }
    }

    public interface ReleaseCallback<T extends ChunkSource> {
        void onSampleStreamReleased(ChunkSampleStream<T> chunkSampleStream);
    }

    @Deprecated
    public ChunkSampleStream(int i, int[] iArr, Format[] formatArr, T t, SequenceableLoader.Callback<ChunkSampleStream<T>> callback2, Allocator allocator, long j, int i2, EventDispatcher eventDispatcher2) {
        this(i, iArr, formatArr, t, callback2, allocator, j, (LoadErrorHandlingPolicy) new DefaultLoadErrorHandlingPolicy(i2), eventDispatcher2);
    }

    public ChunkSampleStream(int i, int[] iArr, Format[] formatArr, T t, SequenceableLoader.Callback<ChunkSampleStream<T>> callback2, Allocator allocator, long j, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, EventDispatcher eventDispatcher2) {
        int i2;
        this.primaryTrackType = i;
        this.embeddedTrackTypes = iArr;
        this.embeddedTrackFormats = formatArr;
        this.chunkSource = t;
        this.callback = callback2;
        this.eventDispatcher = eventDispatcher2;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.loader = new Loader("Loader:ChunkSampleStream");
        this.nextChunkHolder = new ChunkHolder();
        this.mediaChunks = new ArrayList<>();
        this.readOnlyMediaChunks = Collections.unmodifiableList(this.mediaChunks);
        int i3 = 0;
        if (iArr == null) {
            i2 = 0;
        } else {
            i2 = iArr.length;
        }
        this.embeddedSampleQueues = new SampleQueue[i2];
        this.embeddedTracksSelected = new boolean[i2];
        int i4 = i2 + 1;
        int[] iArr2 = new int[i4];
        SampleQueue[] sampleQueueArr = new SampleQueue[i4];
        this.primarySampleQueue = new SampleQueue(allocator);
        iArr2[0] = i;
        sampleQueueArr[0] = this.primarySampleQueue;
        while (i3 < i2) {
            SampleQueue sampleQueue = new SampleQueue(allocator);
            this.embeddedSampleQueues[i3] = sampleQueue;
            int i5 = i3 + 1;
            sampleQueueArr[i5] = sampleQueue;
            iArr2[i5] = iArr[i3];
            i3 = i5;
        }
        this.mediaChunkOutput = new BaseMediaChunkOutput(iArr2, sampleQueueArr);
        this.pendingResetPositionUs = j;
        this.lastSeekPositionUs = j;
    }

    public void discardBuffer(long j, boolean z) {
        if (!isPendingReset()) {
            int firstIndex = this.primarySampleQueue.getFirstIndex();
            this.primarySampleQueue.discardTo(j, z, true);
            int firstIndex2 = this.primarySampleQueue.getFirstIndex();
            if (firstIndex2 > firstIndex) {
                long firstTimestampUs = this.primarySampleQueue.getFirstTimestampUs();
                int i = 0;
                while (true) {
                    SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
                    if (i >= sampleQueueArr.length) {
                        break;
                    }
                    sampleQueueArr[i].discardTo(firstTimestampUs, z, this.embeddedTracksSelected[i]);
                    i++;
                }
            }
            discardDownstreamMediaChunks(firstIndex2);
        }
    }

    public EmbeddedSampleStream selectEmbeddedTrack(long j, int i) {
        for (int i2 = 0; i2 < this.embeddedSampleQueues.length; i2++) {
            if (this.embeddedTrackTypes[i2] == i) {
                Assertions.checkState(!this.embeddedTracksSelected[i2]);
                this.embeddedTracksSelected[i2] = true;
                this.embeddedSampleQueues[i2].rewind();
                this.embeddedSampleQueues[i2].advanceTo(j, true, true);
                return new EmbeddedSampleStream<>(this, this.embeddedSampleQueues[i2], i2);
            }
        }
        throw new IllegalStateException();
    }

    public T getChunkSource() {
        return this.chunkSource;
    }

    public long getBufferedPositionUs() {
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        long j = this.lastSeekPositionUs;
        BaseMediaChunk lastMediaChunk = getLastMediaChunk();
        if (!lastMediaChunk.isLoadCompleted()) {
            if (this.mediaChunks.size() > 1) {
                ArrayList<BaseMediaChunk> arrayList = this.mediaChunks;
                lastMediaChunk = (BaseMediaChunk) arrayList.get(arrayList.size() - 2);
            } else {
                lastMediaChunk = null;
            }
        }
        if (lastMediaChunk != null) {
            j = Math.max(j, lastMediaChunk.endTimeUs);
        }
        return Math.max(j, this.primarySampleQueue.getLargestQueuedTimestampUs());
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        return this.chunkSource.getAdjustedSeekPositionUs(j, seekParameters);
    }

    public void seekToUs(long j) {
        boolean z;
        SampleQueue[] sampleQueueArr;
        this.lastSeekPositionUs = j;
        if (isPendingReset()) {
            this.pendingResetPositionUs = j;
            return;
        }
        BaseMediaChunk baseMediaChunk = null;
        int i = 0;
        while (true) {
            if (i >= this.mediaChunks.size()) {
                break;
            }
            BaseMediaChunk baseMediaChunk2 = (BaseMediaChunk) this.mediaChunks.get(i);
            int i2 = (baseMediaChunk2.startTimeUs > j ? 1 : (baseMediaChunk2.startTimeUs == j ? 0 : -1));
            if (i2 == 0 && baseMediaChunk2.clippedStartTimeUs == -9223372036854775807L) {
                baseMediaChunk = baseMediaChunk2;
                break;
            } else if (i2 > 0) {
                break;
            } else {
                i++;
            }
        }
        this.primarySampleQueue.rewind();
        if (baseMediaChunk != null) {
            z = this.primarySampleQueue.setReadPosition(baseMediaChunk.getFirstSampleIndex(0));
            this.decodeOnlyUntilPositionUs = 0;
        } else {
            z = this.primarySampleQueue.advanceTo(j, true, (j > getNextLoadPositionUs() ? 1 : (j == getNextLoadPositionUs() ? 0 : -1)) < 0) != -1;
            this.decodeOnlyUntilPositionUs = this.lastSeekPositionUs;
        }
        if (z) {
            this.nextNotifyPrimaryFormatMediaChunkIndex = primarySampleIndexToMediaChunkIndex(this.primarySampleQueue.getReadIndex(), 0);
            for (SampleQueue sampleQueue : this.embeddedSampleQueues) {
                sampleQueue.rewind();
                sampleQueue.advanceTo(j, true, false);
            }
        } else {
            this.pendingResetPositionUs = j;
            this.loadingFinished = false;
            this.mediaChunks.clear();
            this.nextNotifyPrimaryFormatMediaChunkIndex = 0;
            if (this.loader.isLoading()) {
                this.loader.cancelLoading();
            } else {
                this.primarySampleQueue.reset();
                for (SampleQueue reset : this.embeddedSampleQueues) {
                    reset.reset();
                }
            }
        }
    }

    public void release() {
        release(null);
    }

    public void release(@Nullable ReleaseCallback<T> releaseCallback2) {
        this.releaseCallback = releaseCallback2;
        this.primarySampleQueue.discardToEnd();
        for (SampleQueue discardToEnd : this.embeddedSampleQueues) {
            discardToEnd.discardToEnd();
        }
        this.loader.release(this);
    }

    public void onLoaderReleased() {
        this.primarySampleQueue.reset();
        for (SampleQueue reset : this.embeddedSampleQueues) {
            reset.reset();
        }
        ReleaseCallback<T> releaseCallback2 = this.releaseCallback;
        if (releaseCallback2 != null) {
            releaseCallback2.onSampleStreamReleased(this);
        }
    }

    public boolean isReady() {
        return this.loadingFinished || (!isPendingReset() && this.primarySampleQueue.hasNextSample());
    }

    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError();
        if (!this.loader.isLoading()) {
            this.chunkSource.maybeThrowError();
        }
    }

    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
        if (isPendingReset()) {
            return -3;
        }
        maybeNotifyPrimaryTrackFormatChanged();
        return this.primarySampleQueue.read(formatHolder, decoderInputBuffer, z, this.loadingFinished, this.decodeOnlyUntilPositionUs);
    }

    public int skipData(long j) {
        int i = 0;
        if (isPendingReset()) {
            return 0;
        }
        if (!this.loadingFinished || j <= this.primarySampleQueue.getLargestQueuedTimestampUs()) {
            int advanceTo = this.primarySampleQueue.advanceTo(j, true, true);
            if (advanceTo != -1) {
                i = advanceTo;
            }
        } else {
            i = this.primarySampleQueue.advanceToEnd();
        }
        maybeNotifyPrimaryTrackFormatChanged();
        return i;
    }

    public void onLoadCompleted(Chunk chunk, long j, long j2) {
        Chunk chunk2 = chunk;
        long j3 = j;
        long j4 = j2;
        this.chunkSource.onChunkLoadCompleted(chunk2);
        this.eventDispatcher.loadCompleted(chunk2.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), chunk2.type, this.primaryTrackType, chunk2.trackFormat, chunk2.trackSelectionReason, chunk2.trackSelectionData, chunk2.startTimeUs, chunk2.endTimeUs, j3, j4, chunk.bytesLoaded());
        this.callback.onContinueLoadingRequested(this);
    }

    public void onLoadCanceled(Chunk chunk, long j, long j2, boolean z) {
        Chunk chunk2 = chunk;
        this.eventDispatcher.loadCanceled(chunk2.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), chunk2.type, this.primaryTrackType, chunk2.trackFormat, chunk2.trackSelectionReason, chunk2.trackSelectionData, chunk2.startTimeUs, chunk2.endTimeUs, j, j2, chunk.bytesLoaded());
        if (!z) {
            this.primarySampleQueue.reset();
            for (SampleQueue reset : this.embeddedSampleQueues) {
                reset.reset();
            }
            this.callback.onContinueLoadingRequested(this);
        }
    }

    public LoadErrorAction onLoadError(Chunk chunk, long j, long j2, IOException iOException, int i) {
        LoadErrorAction loadErrorAction;
        Chunk chunk2 = chunk;
        long bytesLoaded = chunk.bytesLoaded();
        boolean isMediaChunk = isMediaChunk(chunk);
        int size = this.mediaChunks.size() - 1;
        boolean z = bytesLoaded == 0 || !isMediaChunk || !haveReadFromMediaChunk(size);
        LoadErrorAction loadErrorAction2 = null;
        if (this.chunkSource.onChunkLoadError(chunk, z, iOException, z ? this.loadErrorHandlingPolicy.getBlacklistDurationMsFor(chunk2.type, j2, iOException, i) : -9223372036854775807L)) {
            if (z) {
                loadErrorAction2 = Loader.DONT_RETRY;
                if (isMediaChunk) {
                    Assertions.checkState(discardUpstreamMediaChunksFromIndex(size) == chunk2);
                    if (this.mediaChunks.isEmpty()) {
                        this.pendingResetPositionUs = this.lastSeekPositionUs;
                    }
                }
            } else {
                Log.w(TAG, "Ignoring attempt to cancel non-cancelable load.");
            }
        }
        if (loadErrorAction2 == null) {
            long retryDelayMsFor = this.loadErrorHandlingPolicy.getRetryDelayMsFor(chunk2.type, j2, iOException, i);
            loadErrorAction = retryDelayMsFor != -9223372036854775807L ? Loader.createRetryAction(false, retryDelayMsFor) : Loader.DONT_RETRY_FATAL;
        } else {
            loadErrorAction = loadErrorAction2;
        }
        boolean z2 = !loadErrorAction.isRetry();
        this.eventDispatcher.loadError(chunk2.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), chunk2.type, this.primaryTrackType, chunk2.trackFormat, chunk2.trackSelectionReason, chunk2.trackSelectionData, chunk2.startTimeUs, chunk2.endTimeUs, j, j2, bytesLoaded, iOException, z2);
        if (z2) {
            this.callback.onContinueLoadingRequested(this);
        }
        return loadErrorAction;
    }

    public boolean continueLoading(long j) {
        List list;
        long j2;
        long j3;
        boolean z = false;
        if (this.loadingFinished || this.loader.isLoading()) {
            return false;
        }
        boolean isPendingReset = isPendingReset();
        if (isPendingReset) {
            list = Collections.emptyList();
            j2 = this.pendingResetPositionUs;
        } else {
            list = this.readOnlyMediaChunks;
            j2 = getLastMediaChunk().endTimeUs;
        }
        this.chunkSource.getNextChunk(j, j2, list, this.nextChunkHolder);
        boolean z2 = this.nextChunkHolder.endOfStream;
        Chunk chunk = this.nextChunkHolder.chunk;
        this.nextChunkHolder.clear();
        if (z2) {
            this.pendingResetPositionUs = -9223372036854775807L;
            this.loadingFinished = true;
            return true;
        } else if (chunk == null) {
            return false;
        } else {
            if (isMediaChunk(chunk)) {
                BaseMediaChunk baseMediaChunk = (BaseMediaChunk) chunk;
                if (isPendingReset) {
                    if (baseMediaChunk.startTimeUs == this.pendingResetPositionUs) {
                        z = true;
                    }
                    if (z) {
                        j3 = 0;
                    } else {
                        j3 = this.pendingResetPositionUs;
                    }
                    this.decodeOnlyUntilPositionUs = j3;
                    this.pendingResetPositionUs = -9223372036854775807L;
                }
                baseMediaChunk.init(this.mediaChunkOutput);
                this.mediaChunks.add(baseMediaChunk);
            }
            long startLoading = this.loader.startLoading(chunk, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(chunk.type));
            this.eventDispatcher.loadStarted(chunk.dataSpec, chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, startLoading);
            return true;
        }
    }

    public long getNextLoadPositionUs() {
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        return this.loadingFinished ? Long.MIN_VALUE : getLastMediaChunk().endTimeUs;
    }

    public void reevaluateBuffer(long j) {
        if (!this.loader.isLoading() && !isPendingReset()) {
            int size = this.mediaChunks.size();
            int preferredQueueSize = this.chunkSource.getPreferredQueueSize(j, this.readOnlyMediaChunks);
            if (size > preferredQueueSize) {
                while (true) {
                    if (preferredQueueSize >= size) {
                        preferredQueueSize = size;
                        break;
                    } else if (!haveReadFromMediaChunk(preferredQueueSize)) {
                        break;
                    } else {
                        preferredQueueSize++;
                    }
                }
                if (preferredQueueSize != size) {
                    long j2 = getLastMediaChunk().endTimeUs;
                    BaseMediaChunk discardUpstreamMediaChunksFromIndex = discardUpstreamMediaChunksFromIndex(preferredQueueSize);
                    if (this.mediaChunks.isEmpty()) {
                        this.pendingResetPositionUs = this.lastSeekPositionUs;
                    }
                    this.loadingFinished = false;
                    this.eventDispatcher.upstreamDiscarded(this.primaryTrackType, discardUpstreamMediaChunksFromIndex.startTimeUs, j2);
                }
            }
        }
    }

    private boolean isMediaChunk(Chunk chunk) {
        return chunk instanceof BaseMediaChunk;
    }

    private boolean haveReadFromMediaChunk(int i) {
        int readIndex;
        BaseMediaChunk baseMediaChunk = (BaseMediaChunk) this.mediaChunks.get(i);
        if (this.primarySampleQueue.getReadIndex() > baseMediaChunk.getFirstSampleIndex(0)) {
            return true;
        }
        int i2 = 0;
        do {
            SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
            if (i2 >= sampleQueueArr.length) {
                return false;
            }
            readIndex = sampleQueueArr[i2].getReadIndex();
            i2++;
        } while (readIndex <= baseMediaChunk.getFirstSampleIndex(i2));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean isPendingReset() {
        return this.pendingResetPositionUs != -9223372036854775807L;
    }

    private void discardDownstreamMediaChunks(int i) {
        int min = Math.min(primarySampleIndexToMediaChunkIndex(i, 0), this.nextNotifyPrimaryFormatMediaChunkIndex);
        if (min > 0) {
            Util.removeRange(this.mediaChunks, 0, min);
            this.nextNotifyPrimaryFormatMediaChunkIndex -= min;
        }
    }

    private void maybeNotifyPrimaryTrackFormatChanged() {
        int primarySampleIndexToMediaChunkIndex = primarySampleIndexToMediaChunkIndex(this.primarySampleQueue.getReadIndex(), this.nextNotifyPrimaryFormatMediaChunkIndex - 1);
        while (true) {
            int i = this.nextNotifyPrimaryFormatMediaChunkIndex;
            if (i <= primarySampleIndexToMediaChunkIndex) {
                this.nextNotifyPrimaryFormatMediaChunkIndex = i + 1;
                maybeNotifyPrimaryTrackFormatChanged(i);
            } else {
                return;
            }
        }
    }

    private void maybeNotifyPrimaryTrackFormatChanged(int i) {
        BaseMediaChunk baseMediaChunk = (BaseMediaChunk) this.mediaChunks.get(i);
        Format format = baseMediaChunk.trackFormat;
        if (!format.equals(this.primaryDownstreamTrackFormat)) {
            this.eventDispatcher.downstreamFormatChanged(this.primaryTrackType, format, baseMediaChunk.trackSelectionReason, baseMediaChunk.trackSelectionData, baseMediaChunk.startTimeUs);
        }
        this.primaryDownstreamTrackFormat = format;
    }

    private int primarySampleIndexToMediaChunkIndex(int i, int i2) {
        do {
            i2++;
            if (i2 >= this.mediaChunks.size()) {
                return this.mediaChunks.size() - 1;
            }
        } while (((BaseMediaChunk) this.mediaChunks.get(i2)).getFirstSampleIndex(0) <= i);
        return i2 - 1;
    }

    private BaseMediaChunk getLastMediaChunk() {
        ArrayList<BaseMediaChunk> arrayList = this.mediaChunks;
        return (BaseMediaChunk) arrayList.get(arrayList.size() - 1);
    }

    private BaseMediaChunk discardUpstreamMediaChunksFromIndex(int i) {
        BaseMediaChunk baseMediaChunk = (BaseMediaChunk) this.mediaChunks.get(i);
        ArrayList<BaseMediaChunk> arrayList = this.mediaChunks;
        Util.removeRange(arrayList, i, arrayList.size());
        this.nextNotifyPrimaryFormatMediaChunkIndex = Math.max(this.nextNotifyPrimaryFormatMediaChunkIndex, this.mediaChunks.size());
        int i2 = 0;
        this.primarySampleQueue.discardUpstreamSamples(baseMediaChunk.getFirstSampleIndex(0));
        while (true) {
            SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
            if (i2 >= sampleQueueArr.length) {
                return baseMediaChunk;
            }
            SampleQueue sampleQueue = sampleQueueArr[i2];
            i2++;
            sampleQueue.discardUpstreamSamples(baseMediaChunk.getFirstSampleIndex(i2));
        }
    }
}
