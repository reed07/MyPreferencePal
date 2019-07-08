package com.google.android.exoplayer2.source.smoothstreaming;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.source.BaseMediaSource;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.DefaultCompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher;
import com.google.android.exoplayer2.source.SinglePeriodTimeline;
import com.google.android.exoplayer2.source.ads.AdsMediaSource.MediaSourceFactory;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest.StreamElement;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsUtil;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.Loader.Callback;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower.Dummy;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.upstream.ParsingLoadable.Parser;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.ArrayList;

public final class SsMediaSource extends BaseMediaSource implements Callback<ParsingLoadable<SsManifest>> {
    public static final long DEFAULT_LIVE_PRESENTATION_DELAY_MS = 30000;
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT = 3;
    private static final int MINIMUM_MANIFEST_REFRESH_PERIOD_MS = 5000;
    private static final long MIN_LIVE_DEFAULT_START_POSITION_US = 5000000;
    private final com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource.Factory chunkSourceFactory;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final long livePresentationDelayMs;
    private SsManifest manifest;
    private DataSource manifestDataSource;
    private final com.google.android.exoplayer2.upstream.DataSource.Factory manifestDataSourceFactory;
    private final EventDispatcher manifestEventDispatcher;
    private long manifestLoadStartTimestamp;
    private Loader manifestLoader;
    private LoaderErrorThrower manifestLoaderErrorThrower;
    private final Parser<? extends SsManifest> manifestParser;
    private Handler manifestRefreshHandler;
    private final Uri manifestUri;
    private final ArrayList<SsMediaPeriod> mediaPeriods;
    private final int minLoadableRetryCount;
    private final boolean sideloadedManifest;
    @Nullable
    private final Object tag;

    public static final class Factory implements MediaSourceFactory {
        private final com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource.Factory chunkSourceFactory;
        private CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory = new DefaultCompositeSequenceableLoaderFactory();
        private boolean isCreateCalled;
        private long livePresentationDelayMs = 30000;
        @Nullable
        private final com.google.android.exoplayer2.upstream.DataSource.Factory manifestDataSourceFactory;
        @Nullable
        private Parser<? extends SsManifest> manifestParser;
        private int minLoadableRetryCount = 3;
        @Nullable
        private Object tag;

        public Factory(com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource.Factory factory, @Nullable com.google.android.exoplayer2.upstream.DataSource.Factory factory2) {
            this.chunkSourceFactory = (com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource.Factory) Assertions.checkNotNull(factory);
            this.manifestDataSourceFactory = factory2;
        }

        public Factory setTag(Object obj) {
            Assertions.checkState(!this.isCreateCalled);
            this.tag = obj;
            return this;
        }

        public Factory setMinLoadableRetryCount(int i) {
            Assertions.checkState(!this.isCreateCalled);
            this.minLoadableRetryCount = i;
            return this;
        }

        public Factory setLivePresentationDelayMs(long j) {
            Assertions.checkState(!this.isCreateCalled);
            this.livePresentationDelayMs = j;
            return this;
        }

        public Factory setManifestParser(Parser<? extends SsManifest> parser) {
            Assertions.checkState(!this.isCreateCalled);
            this.manifestParser = (Parser) Assertions.checkNotNull(parser);
            return this;
        }

        public Factory setCompositeSequenceableLoaderFactory(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2) {
            Assertions.checkState(!this.isCreateCalled);
            this.compositeSequenceableLoaderFactory = (CompositeSequenceableLoaderFactory) Assertions.checkNotNull(compositeSequenceableLoaderFactory2);
            return this;
        }

        public SsMediaSource createMediaSource(SsManifest ssManifest) {
            Assertions.checkArgument(!ssManifest.isLive);
            this.isCreateCalled = true;
            SsMediaSource ssMediaSource = new SsMediaSource(ssManifest, null, null, null, this.chunkSourceFactory, this.compositeSequenceableLoaderFactory, this.minLoadableRetryCount, this.livePresentationDelayMs, this.tag);
            return ssMediaSource;
        }

        @Deprecated
        public SsMediaSource createMediaSource(SsManifest ssManifest, @Nullable Handler handler, @Nullable MediaSourceEventListener mediaSourceEventListener) {
            SsMediaSource createMediaSource = createMediaSource(ssManifest);
            if (!(handler == null || mediaSourceEventListener == null)) {
                createMediaSource.addEventListener(handler, mediaSourceEventListener);
            }
            return createMediaSource;
        }

        public SsMediaSource createMediaSource(Uri uri) {
            this.isCreateCalled = true;
            if (this.manifestParser == null) {
                this.manifestParser = new SsManifestParser();
            }
            SsMediaSource ssMediaSource = new SsMediaSource(null, (Uri) Assertions.checkNotNull(uri), this.manifestDataSourceFactory, this.manifestParser, this.chunkSourceFactory, this.compositeSequenceableLoaderFactory, this.minLoadableRetryCount, this.livePresentationDelayMs, this.tag);
            return ssMediaSource;
        }

        @Deprecated
        public SsMediaSource createMediaSource(Uri uri, @Nullable Handler handler, @Nullable MediaSourceEventListener mediaSourceEventListener) {
            SsMediaSource createMediaSource = createMediaSource(uri);
            if (!(handler == null || mediaSourceEventListener == null)) {
                createMediaSource.addEventListener(handler, mediaSourceEventListener);
            }
            return createMediaSource;
        }

        public int[] getSupportedTypes() {
            return new int[]{1};
        }
    }

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.smoothstreaming");
    }

    @Deprecated
    public SsMediaSource(SsManifest ssManifest, com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource.Factory factory, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        this(ssManifest, factory, 3, handler, mediaSourceEventListener);
    }

    @Deprecated
    public SsMediaSource(SsManifest ssManifest, com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource.Factory factory, int i, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        Handler handler2 = handler;
        MediaSourceEventListener mediaSourceEventListener2 = mediaSourceEventListener;
        this(ssManifest, null, null, null, factory, new DefaultCompositeSequenceableLoaderFactory(), i, 30000, null);
        if (handler2 == null || mediaSourceEventListener2 == null) {
            return;
        }
        addEventListener(handler2, mediaSourceEventListener2);
    }

    @Deprecated
    public SsMediaSource(Uri uri, com.google.android.exoplayer2.upstream.DataSource.Factory factory, com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource.Factory factory2, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        this(uri, factory, factory2, 3, 30000, handler, mediaSourceEventListener);
    }

    @Deprecated
    public SsMediaSource(Uri uri, com.google.android.exoplayer2.upstream.DataSource.Factory factory, com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource.Factory factory2, int i, long j, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        this(uri, factory, new SsManifestParser(), factory2, i, j, handler, mediaSourceEventListener);
    }

    @Deprecated
    public SsMediaSource(Uri uri, com.google.android.exoplayer2.upstream.DataSource.Factory factory, Parser<? extends SsManifest> parser, com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource.Factory factory2, int i, long j, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        Handler handler2 = handler;
        MediaSourceEventListener mediaSourceEventListener2 = mediaSourceEventListener;
        this(null, uri, factory, parser, factory2, new DefaultCompositeSequenceableLoaderFactory(), i, j, null);
        if (handler2 == null || mediaSourceEventListener2 == null) {
            return;
        }
        addEventListener(handler2, mediaSourceEventListener2);
    }

    private SsMediaSource(SsManifest ssManifest, Uri uri, com.google.android.exoplayer2.upstream.DataSource.Factory factory, Parser<? extends SsManifest> parser, com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource.Factory factory2, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, int i, long j, @Nullable Object obj) {
        Uri uri2;
        boolean z = false;
        Assertions.checkState(ssManifest == null || !ssManifest.isLive);
        this.manifest = ssManifest;
        if (uri == null) {
            uri2 = null;
        } else {
            uri2 = SsUtil.fixManifestUri(uri);
        }
        this.manifestUri = uri2;
        this.manifestDataSourceFactory = factory;
        this.manifestParser = parser;
        this.chunkSourceFactory = factory2;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.minLoadableRetryCount = i;
        this.livePresentationDelayMs = j;
        this.manifestEventDispatcher = createEventDispatcher(null);
        this.tag = obj;
        if (ssManifest != null) {
            z = true;
        }
        this.sideloadedManifest = z;
        this.mediaPeriods = new ArrayList<>();
    }

    public void prepareSourceInternal(ExoPlayer exoPlayer, boolean z) {
        if (this.sideloadedManifest) {
            this.manifestLoaderErrorThrower = new Dummy();
            processManifest();
            return;
        }
        this.manifestDataSource = this.manifestDataSourceFactory.createDataSource();
        this.manifestLoader = new Loader("Loader:Manifest");
        this.manifestLoaderErrorThrower = this.manifestLoader;
        this.manifestRefreshHandler = new Handler();
        startLoadingManifest();
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    public MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator) {
        Assertions.checkArgument(mediaPeriodId.periodIndex == 0);
        SsMediaPeriod ssMediaPeriod = new SsMediaPeriod(this.manifest, this.chunkSourceFactory, this.compositeSequenceableLoaderFactory, this.minLoadableRetryCount, createEventDispatcher(mediaPeriodId), this.manifestLoaderErrorThrower, allocator);
        this.mediaPeriods.add(ssMediaPeriod);
        return ssMediaPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((SsMediaPeriod) mediaPeriod).release();
        this.mediaPeriods.remove(mediaPeriod);
    }

    public void releaseSourceInternal() {
        this.manifest = this.sideloadedManifest ? this.manifest : null;
        this.manifestDataSource = null;
        this.manifestLoadStartTimestamp = 0;
        Loader loader = this.manifestLoader;
        if (loader != null) {
            loader.release();
            this.manifestLoader = null;
        }
        Handler handler = this.manifestRefreshHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.manifestRefreshHandler = null;
        }
    }

    public void onLoadCompleted(ParsingLoadable<SsManifest> parsingLoadable, long j, long j2) {
        this.manifestEventDispatcher.loadCompleted(parsingLoadable.dataSpec, parsingLoadable.type, j, j2, parsingLoadable.bytesLoaded());
        this.manifest = (SsManifest) parsingLoadable.getResult();
        this.manifestLoadStartTimestamp = j - j2;
        processManifest();
        scheduleManifestRefresh();
    }

    public void onLoadCanceled(ParsingLoadable<SsManifest> parsingLoadable, long j, long j2, boolean z) {
        this.manifestEventDispatcher.loadCanceled(parsingLoadable.dataSpec, parsingLoadable.type, j, j2, parsingLoadable.bytesLoaded());
    }

    public int onLoadError(ParsingLoadable<SsManifest> parsingLoadable, long j, long j2, IOException iOException) {
        ParsingLoadable<SsManifest> parsingLoadable2 = parsingLoadable;
        IOException iOException2 = iOException;
        boolean z = iOException2 instanceof ParserException;
        this.manifestEventDispatcher.loadError(parsingLoadable2.dataSpec, parsingLoadable2.type, j, j2, parsingLoadable.bytesLoaded(), iOException2, z);
        return z ? 3 : 0;
    }

    private void processManifest() {
        StreamElement[] streamElementArr;
        SinglePeriodTimeline singlePeriodTimeline;
        for (int i = 0; i < this.mediaPeriods.size(); i++) {
            ((SsMediaPeriod) this.mediaPeriods.get(i)).updateManifest(this.manifest);
        }
        long j = Long.MIN_VALUE;
        long j2 = Long.MAX_VALUE;
        for (StreamElement streamElement : this.manifest.streamElements) {
            if (streamElement.chunkCount > 0) {
                long min = Math.min(j2, streamElement.getStartTimeUs(0));
                j = Math.max(j, streamElement.getStartTimeUs(streamElement.chunkCount - 1) + streamElement.getChunkDurationUs(streamElement.chunkCount - 1));
                j2 = min;
            }
        }
        if (j2 == Long.MAX_VALUE) {
            singlePeriodTimeline = new SinglePeriodTimeline(this.manifest.isLive ? -9223372036854775807L : 0, 0, 0, 0, true, this.manifest.isLive, this.tag);
        } else if (this.manifest.isLive) {
            long max = (this.manifest.dvrWindowLengthUs == -9223372036854775807L || this.manifest.dvrWindowLengthUs <= 0) ? j2 : Math.max(j2, j - this.manifest.dvrWindowLengthUs);
            long j3 = j - max;
            long msToUs = j3 - C.msToUs(this.livePresentationDelayMs);
            singlePeriodTimeline = new SinglePeriodTimeline(-9223372036854775807L, j3, max, msToUs < MIN_LIVE_DEFAULT_START_POSITION_US ? Math.min(MIN_LIVE_DEFAULT_START_POSITION_US, j3 / 2) : msToUs, true, true, this.tag);
        } else {
            long j4 = this.manifest.durationUs != -9223372036854775807L ? this.manifest.durationUs : j - j2;
            singlePeriodTimeline = new SinglePeriodTimeline(j2 + j4, j4, j2, 0, true, false, this.tag);
        }
        refreshSourceInfo(singlePeriodTimeline, this.manifest);
    }

    private void scheduleManifestRefresh() {
        if (this.manifest.isLive) {
            this.manifestRefreshHandler.postDelayed(new Runnable() {
                public void run() {
                    SsMediaSource.this.startLoadingManifest();
                }
            }, Math.max(0, (this.manifestLoadStartTimestamp + DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS) - SystemClock.elapsedRealtime()));
        }
    }

    /* access modifiers changed from: private */
    public void startLoadingManifest() {
        ParsingLoadable parsingLoadable = new ParsingLoadable(this.manifestDataSource, this.manifestUri, 4, this.manifestParser);
        this.manifestEventDispatcher.loadStarted(parsingLoadable.dataSpec, parsingLoadable.type, this.manifestLoader.startLoading(parsingLoadable, this, this.minLoadableRetryCount));
    }
}
