package com.google.android.exoplayer2.source;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.Loader.Callback;
import com.google.android.exoplayer2.upstream.Loader.LoadErrorAction;
import com.google.android.exoplayer2.upstream.Loader.Loadable;
import com.google.android.exoplayer2.upstream.StatsDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

final class SingleSampleMediaPeriod implements MediaPeriod, Callback<SourceLoadable> {
    private static final int INITIAL_SAMPLE_SIZE = 1024;
    private final Factory dataSourceFactory;
    private final DataSpec dataSpec;
    private final long durationUs;
    /* access modifiers changed from: private */
    public final EventDispatcher eventDispatcher;
    final Format format;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    final Loader loader = new Loader("Loader:SingleSampleMediaPeriod");
    boolean loadingFinished;
    boolean loadingSucceeded;
    boolean notifiedReadingStarted;
    byte[] sampleData;
    int sampleSize;
    private final ArrayList<SampleStreamImpl> sampleStreams = new ArrayList<>();
    private final TrackGroupArray tracks;
    @Nullable
    private final TransferListener transferListener;
    final boolean treatLoadErrorsAsEndOfStream;

    private final class SampleStreamImpl implements SampleStream {
        private static final int STREAM_STATE_END_OF_STREAM = 2;
        private static final int STREAM_STATE_SEND_FORMAT = 0;
        private static final int STREAM_STATE_SEND_SAMPLE = 1;
        private boolean notifiedDownstreamFormat;
        private int streamState;

        private SampleStreamImpl() {
        }

        public void reset() {
            if (this.streamState == 2) {
                this.streamState = 1;
            }
        }

        public boolean isReady() {
            return SingleSampleMediaPeriod.this.loadingFinished;
        }

        public void maybeThrowError() throws IOException {
            if (!SingleSampleMediaPeriod.this.treatLoadErrorsAsEndOfStream) {
                SingleSampleMediaPeriod.this.loader.maybeThrowError();
            }
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
            maybeNotifyDownstreamFormat();
            int i = this.streamState;
            if (i == 2) {
                decoderInputBuffer.addFlag(4);
                return -4;
            } else if (z || i == 0) {
                formatHolder.format = SingleSampleMediaPeriod.this.format;
                this.streamState = 1;
                return -5;
            } else if (!SingleSampleMediaPeriod.this.loadingFinished) {
                return -3;
            } else {
                if (SingleSampleMediaPeriod.this.loadingSucceeded) {
                    decoderInputBuffer.timeUs = 0;
                    decoderInputBuffer.addFlag(1);
                    decoderInputBuffer.ensureSpaceForWrite(SingleSampleMediaPeriod.this.sampleSize);
                    decoderInputBuffer.data.put(SingleSampleMediaPeriod.this.sampleData, 0, SingleSampleMediaPeriod.this.sampleSize);
                } else {
                    decoderInputBuffer.addFlag(4);
                }
                this.streamState = 2;
                return -4;
            }
        }

        public int skipData(long j) {
            maybeNotifyDownstreamFormat();
            if (j <= 0 || this.streamState == 2) {
                return 0;
            }
            this.streamState = 2;
            return 1;
        }

        private void maybeNotifyDownstreamFormat() {
            if (!this.notifiedDownstreamFormat) {
                SingleSampleMediaPeriod.this.eventDispatcher.downstreamFormatChanged(MimeTypes.getTrackType(SingleSampleMediaPeriod.this.format.sampleMimeType), SingleSampleMediaPeriod.this.format, 0, null, 0);
                this.notifiedDownstreamFormat = true;
            }
        }
    }

    static final class SourceLoadable implements Loadable {
        /* access modifiers changed from: private */
        public final StatsDataSource dataSource;
        public final DataSpec dataSpec;
        /* access modifiers changed from: private */
        public byte[] sampleData;

        public void cancelLoad() {
        }

        public SourceLoadable(DataSpec dataSpec2, DataSource dataSource2) {
            this.dataSpec = dataSpec2;
            this.dataSource = new StatsDataSource(dataSource2);
        }

        public void load() throws IOException, InterruptedException {
            this.dataSource.resetBytesRead();
            try {
                this.dataSource.open(this.dataSpec);
                int i = 0;
                while (i != -1) {
                    int bytesRead = (int) this.dataSource.getBytesRead();
                    if (this.sampleData == null) {
                        this.sampleData = new byte[SingleSampleMediaPeriod.INITIAL_SAMPLE_SIZE];
                    } else if (bytesRead == this.sampleData.length) {
                        this.sampleData = Arrays.copyOf(this.sampleData, this.sampleData.length * 2);
                    }
                    i = this.dataSource.read(this.sampleData, bytesRead, this.sampleData.length - bytesRead);
                }
            } finally {
                Util.closeQuietly((DataSource) this.dataSource);
            }
        }
    }

    public void discardBuffer(long j, boolean z) {
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        return j;
    }

    public void maybeThrowPrepareError() throws IOException {
    }

    public void reevaluateBuffer(long j) {
    }

    public SingleSampleMediaPeriod(DataSpec dataSpec2, Factory factory, @Nullable TransferListener transferListener2, Format format2, long j, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, EventDispatcher eventDispatcher2, boolean z) {
        this.dataSpec = dataSpec2;
        this.dataSourceFactory = factory;
        this.transferListener = transferListener2;
        this.format = format2;
        this.durationUs = j;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.eventDispatcher = eventDispatcher2;
        this.treatLoadErrorsAsEndOfStream = z;
        this.tracks = new TrackGroupArray(new TrackGroup(format2));
        eventDispatcher2.mediaPeriodCreated();
    }

    public void release() {
        this.loader.release();
        this.eventDispatcher.mediaPeriodReleased();
    }

    public void prepare(MediaPeriod.Callback callback, long j) {
        callback.onPrepared(this);
    }

    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        for (int i = 0; i < trackSelectionArr.length; i++) {
            if (sampleStreamArr[i] != null && (trackSelectionArr[i] == null || !zArr[i])) {
                this.sampleStreams.remove(sampleStreamArr[i]);
                sampleStreamArr[i] = null;
            }
            if (sampleStreamArr[i] == null && trackSelectionArr[i] != null) {
                SampleStreamImpl sampleStreamImpl = new SampleStreamImpl();
                this.sampleStreams.add(sampleStreamImpl);
                sampleStreamArr[i] = sampleStreamImpl;
                zArr2[i] = true;
            }
        }
        return j;
    }

    public boolean continueLoading(long j) {
        if (this.loadingFinished || this.loader.isLoading()) {
            return false;
        }
        DataSource createDataSource = this.dataSourceFactory.createDataSource();
        TransferListener transferListener2 = this.transferListener;
        if (transferListener2 != null) {
            createDataSource.addTransferListener(transferListener2);
        }
        this.eventDispatcher.loadStarted(this.dataSpec, 1, -1, this.format, 0, null, 0, this.durationUs, this.loader.startLoading(new SourceLoadable(this.dataSpec, createDataSource), this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(1)));
        return true;
    }

    public long readDiscontinuity() {
        if (!this.notifiedReadingStarted) {
            this.eventDispatcher.readingStarted();
            this.notifiedReadingStarted = true;
        }
        return -9223372036854775807L;
    }

    public long getNextLoadPositionUs() {
        return (this.loadingFinished || this.loader.isLoading()) ? Long.MIN_VALUE : 0;
    }

    public long getBufferedPositionUs() {
        return this.loadingFinished ? Long.MIN_VALUE : 0;
    }

    public long seekToUs(long j) {
        for (int i = 0; i < this.sampleStreams.size(); i++) {
            ((SampleStreamImpl) this.sampleStreams.get(i)).reset();
        }
        return j;
    }

    public void onLoadCompleted(SourceLoadable sourceLoadable, long j, long j2) {
        long j3 = j;
        long j4 = j2;
        this.sampleSize = (int) sourceLoadable.dataSource.getBytesRead();
        this.sampleData = sourceLoadable.sampleData;
        this.loadingFinished = true;
        this.loadingSucceeded = true;
        this.eventDispatcher.loadCompleted(sourceLoadable.dataSpec, sourceLoadable.dataSource.getLastOpenedUri(), sourceLoadable.dataSource.getLastResponseHeaders(), 1, -1, this.format, 0, null, 0, this.durationUs, j3, j4, (long) this.sampleSize);
    }

    public void onLoadCanceled(SourceLoadable sourceLoadable, long j, long j2, boolean z) {
        this.eventDispatcher.loadCanceled(sourceLoadable.dataSpec, sourceLoadable.dataSource.getLastOpenedUri(), sourceLoadable.dataSource.getLastResponseHeaders(), 1, -1, null, 0, null, 0, this.durationUs, j, j2, sourceLoadable.dataSource.getBytesRead());
    }

    public LoadErrorAction onLoadError(SourceLoadable sourceLoadable, long j, long j2, IOException iOException, int i) {
        LoadErrorAction loadErrorAction;
        long retryDelayMsFor = this.loadErrorHandlingPolicy.getRetryDelayMsFor(1, this.durationUs, iOException, i);
        int i2 = (retryDelayMsFor > -9223372036854775807L ? 1 : (retryDelayMsFor == -9223372036854775807L ? 0 : -1));
        boolean z = i2 == 0 || i >= this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(1);
        if (!this.treatLoadErrorsAsEndOfStream || !z) {
            loadErrorAction = i2 != 0 ? Loader.createRetryAction(false, retryDelayMsFor) : Loader.DONT_RETRY_FATAL;
        } else {
            this.loadingFinished = true;
            loadErrorAction = Loader.DONT_RETRY;
        }
        this.eventDispatcher.loadError(sourceLoadable.dataSpec, sourceLoadable.dataSource.getLastOpenedUri(), sourceLoadable.dataSource.getLastResponseHeaders(), 1, -1, this.format, 0, null, 0, this.durationUs, j, j2, sourceLoadable.dataSource.getBytesRead(), iOException, !loadErrorAction.isRetry());
        return loadErrorAction;
    }
}
