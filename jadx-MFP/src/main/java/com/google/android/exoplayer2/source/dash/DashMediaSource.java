package com.google.android.exoplayer2.source.dash;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Timeline.Period;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.source.BaseMediaSource;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.DefaultCompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher;
import com.google.android.exoplayer2.source.ads.AdsMediaSource.MediaSourceFactory;
import com.google.android.exoplayer2.source.dash.PlayerEmsgHandler.PlayerEmsgCallback;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.DashManifestParser;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.source.dash.manifest.UtcTimingElement;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.Loader.Callback;
import com.google.android.exoplayer2.upstream.Loader.LoadErrorAction;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower.Dummy;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.upstream.ParsingLoadable.Parser;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DashMediaSource extends BaseMediaSource {
    @Deprecated
    public static final long DEFAULT_LIVE_PRESENTATION_DELAY_FIXED_MS = 30000;
    public static final long DEFAULT_LIVE_PRESENTATION_DELAY_MS = 30000;
    @Deprecated
    public static final long DEFAULT_LIVE_PRESENTATION_DELAY_PREFER_MANIFEST_MS = -1;
    private static final long MIN_LIVE_DEFAULT_START_POSITION_US = 5000000;
    private static final int NOTIFY_MANIFEST_INTERVAL_MS = 5000;
    private static final String TAG = "DashMediaSource";
    private final com.google.android.exoplayer2.source.dash.DashChunkSource.Factory chunkSourceFactory;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private DataSource dataSource;
    private long elapsedRealtimeOffsetMs;
    private long expiredManifestPublishTimeUs;
    private int firstPeriodId;
    private Handler handler;
    private Uri initialManifestUri;
    private final long livePresentationDelayMs;
    private final boolean livePresentationDelayOverridesManifest;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    /* access modifiers changed from: private */
    public Loader loader;
    private DashManifest manifest;
    private final ManifestCallback manifestCallback;
    private final com.google.android.exoplayer2.upstream.DataSource.Factory manifestDataSourceFactory;
    private final EventDispatcher manifestEventDispatcher;
    /* access modifiers changed from: private */
    public IOException manifestFatalError;
    private long manifestLoadEndTimestampMs;
    private final LoaderErrorThrower manifestLoadErrorThrower;
    private boolean manifestLoadPending;
    private long manifestLoadStartTimestampMs;
    private final Parser<? extends DashManifest> manifestParser;
    private Uri manifestUri;
    private final Object manifestUriLock;
    @Nullable
    private TransferListener mediaTransferListener;
    private final SparseArray<DashMediaPeriod> periodsById;
    private final PlayerEmsgCallback playerEmsgCallback;
    private final Runnable refreshManifestRunnable;
    private final boolean sideloadedManifest;
    private final Runnable simulateManifestRefreshRunnable;
    private int staleManifestReloadAttempt;
    @Nullable
    private final Object tag;

    private static final class DashTimeline extends Timeline {
        private final int firstPeriodId;
        private final DashManifest manifest;
        private final long offsetInFirstPeriodUs;
        private final long presentationStartTimeMs;
        private final long windowDefaultStartPositionUs;
        private final long windowDurationUs;
        private final long windowStartTimeMs;
        @Nullable
        private final Object windowTag;

        public int getWindowCount() {
            return 1;
        }

        public DashTimeline(long j, long j2, int i, long j3, long j4, long j5, DashManifest dashManifest, @Nullable Object obj) {
            this.presentationStartTimeMs = j;
            this.windowStartTimeMs = j2;
            this.firstPeriodId = i;
            this.offsetInFirstPeriodUs = j3;
            this.windowDurationUs = j4;
            this.windowDefaultStartPositionUs = j5;
            this.manifest = dashManifest;
            this.windowTag = obj;
        }

        public int getPeriodCount() {
            return this.manifest.getPeriodCount();
        }

        public Period getPeriod(int i, Period period, boolean z) {
            Assertions.checkIndex(i, 0, getPeriodCount());
            Integer num = null;
            Object obj = z ? this.manifest.getPeriod(i).id : null;
            if (z) {
                num = Integer.valueOf(this.firstPeriodId + i);
            }
            return period.set(obj, num, 0, this.manifest.getPeriodDurationUs(i), C.msToUs(this.manifest.getPeriod(i).startMs - this.manifest.getPeriod(0).startMs) - this.offsetInFirstPeriodUs);
        }

        public Window getWindow(int i, Window window, boolean z, long j) {
            Assertions.checkIndex(i, 0, 1);
            return window.set(z ? this.windowTag : null, this.presentationStartTimeMs, this.windowStartTimeMs, true, this.manifest.dynamic && this.manifest.minUpdatePeriodMs != -9223372036854775807L && this.manifest.durationMs == -9223372036854775807L, getAdjustedWindowDefaultStartPositionUs(j), this.windowDurationUs, 0, getPeriodCount() - 1, this.offsetInFirstPeriodUs);
        }

        public int getIndexOfPeriod(Object obj) {
            if (!(obj instanceof Integer)) {
                return -1;
            }
            int intValue = ((Integer) obj).intValue() - this.firstPeriodId;
            if (intValue < 0 || intValue >= getPeriodCount()) {
                intValue = -1;
            }
            return intValue;
        }

        private long getAdjustedWindowDefaultStartPositionUs(long j) {
            long j2 = this.windowDefaultStartPositionUs;
            if (!this.manifest.dynamic) {
                return j2;
            }
            if (j > 0) {
                j2 += j;
                if (j2 > this.windowDurationUs) {
                    return -9223372036854775807L;
                }
            }
            long j3 = this.offsetInFirstPeriodUs + j2;
            long periodDurationUs = this.manifest.getPeriodDurationUs(0);
            long j4 = j3;
            int i = 0;
            while (i < this.manifest.getPeriodCount() - 1 && j4 >= periodDurationUs) {
                j4 -= periodDurationUs;
                i++;
                periodDurationUs = this.manifest.getPeriodDurationUs(i);
            }
            com.google.android.exoplayer2.source.dash.manifest.Period period = this.manifest.getPeriod(i);
            int adaptationSetIndex = period.getAdaptationSetIndex(2);
            if (adaptationSetIndex == -1) {
                return j2;
            }
            DashSegmentIndex index = ((Representation) ((AdaptationSet) period.adaptationSets.get(adaptationSetIndex)).representations.get(0)).getIndex();
            return (index == null || index.getSegmentCount(periodDurationUs) == 0) ? j2 : (j2 + index.getTimeUs(index.getSegmentNum(j4, periodDurationUs))) - j4;
        }

        public Object getUidOfPeriod(int i) {
            Assertions.checkIndex(i, 0, getPeriodCount());
            return Integer.valueOf(this.firstPeriodId + i);
        }
    }

    private final class DefaultPlayerEmsgCallback implements PlayerEmsgCallback {
        private DefaultPlayerEmsgCallback() {
        }

        public void onDashManifestRefreshRequested() {
            DashMediaSource.this.onDashManifestRefreshRequested();
        }

        public void onDashManifestPublishTimeExpired(long j) {
            DashMediaSource.this.onDashManifestPublishTimeExpired(j);
        }
    }

    public static final class Factory implements MediaSourceFactory {
        private final com.google.android.exoplayer2.source.dash.DashChunkSource.Factory chunkSourceFactory;
        private CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
        private boolean isCreateCalled;
        private long livePresentationDelayMs;
        private boolean livePresentationDelayOverridesManifest;
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
        @Nullable
        private final com.google.android.exoplayer2.upstream.DataSource.Factory manifestDataSourceFactory;
        @Nullable
        private Parser<? extends DashManifest> manifestParser;
        @Nullable
        private Object tag;

        public Factory(com.google.android.exoplayer2.upstream.DataSource.Factory factory) {
            this(new com.google.android.exoplayer2.source.dash.DefaultDashChunkSource.Factory(factory), factory);
        }

        public Factory(com.google.android.exoplayer2.source.dash.DashChunkSource.Factory factory, @Nullable com.google.android.exoplayer2.upstream.DataSource.Factory factory2) {
            this.chunkSourceFactory = (com.google.android.exoplayer2.source.dash.DashChunkSource.Factory) Assertions.checkNotNull(factory);
            this.manifestDataSourceFactory = factory2;
            this.loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy();
            this.livePresentationDelayMs = 30000;
            this.compositeSequenceableLoaderFactory = new DefaultCompositeSequenceableLoaderFactory();
        }

        public Factory setTag(Object obj) {
            Assertions.checkState(!this.isCreateCalled);
            this.tag = obj;
            return this;
        }

        @Deprecated
        public Factory setMinLoadableRetryCount(int i) {
            return setLoadErrorHandlingPolicy(new DefaultLoadErrorHandlingPolicy(i));
        }

        public Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy2) {
            Assertions.checkState(!this.isCreateCalled);
            this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
            return this;
        }

        @Deprecated
        public Factory setLivePresentationDelayMs(long j) {
            if (j == -1) {
                return setLivePresentationDelayMs(30000, false);
            }
            return setLivePresentationDelayMs(j, true);
        }

        public Factory setLivePresentationDelayMs(long j, boolean z) {
            Assertions.checkState(!this.isCreateCalled);
            this.livePresentationDelayMs = j;
            this.livePresentationDelayOverridesManifest = z;
            return this;
        }

        public Factory setManifestParser(Parser<? extends DashManifest> parser) {
            Assertions.checkState(!this.isCreateCalled);
            this.manifestParser = (Parser) Assertions.checkNotNull(parser);
            return this;
        }

        public Factory setCompositeSequenceableLoaderFactory(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2) {
            Assertions.checkState(!this.isCreateCalled);
            this.compositeSequenceableLoaderFactory = (CompositeSequenceableLoaderFactory) Assertions.checkNotNull(compositeSequenceableLoaderFactory2);
            return this;
        }

        public DashMediaSource createMediaSource(DashManifest dashManifest) {
            Assertions.checkArgument(!dashManifest.dynamic);
            this.isCreateCalled = true;
            DashMediaSource dashMediaSource = new DashMediaSource(dashManifest, null, null, null, this.chunkSourceFactory, this.compositeSequenceableLoaderFactory, this.loadErrorHandlingPolicy, this.livePresentationDelayMs, this.livePresentationDelayOverridesManifest, this.tag);
            return dashMediaSource;
        }

        @Deprecated
        public DashMediaSource createMediaSource(DashManifest dashManifest, @Nullable Handler handler, @Nullable MediaSourceEventListener mediaSourceEventListener) {
            DashMediaSource createMediaSource = createMediaSource(dashManifest);
            if (!(handler == null || mediaSourceEventListener == null)) {
                createMediaSource.addEventListener(handler, mediaSourceEventListener);
            }
            return createMediaSource;
        }

        public DashMediaSource createMediaSource(Uri uri) {
            this.isCreateCalled = true;
            if (this.manifestParser == null) {
                this.manifestParser = new DashManifestParser();
            }
            DashMediaSource dashMediaSource = new DashMediaSource(null, (Uri) Assertions.checkNotNull(uri), this.manifestDataSourceFactory, this.manifestParser, this.chunkSourceFactory, this.compositeSequenceableLoaderFactory, this.loadErrorHandlingPolicy, this.livePresentationDelayMs, this.livePresentationDelayOverridesManifest, this.tag);
            return dashMediaSource;
        }

        @Deprecated
        public DashMediaSource createMediaSource(Uri uri, @Nullable Handler handler, @Nullable MediaSourceEventListener mediaSourceEventListener) {
            DashMediaSource createMediaSource = createMediaSource(uri);
            if (!(handler == null || mediaSourceEventListener == null)) {
                createMediaSource.addEventListener(handler, mediaSourceEventListener);
            }
            return createMediaSource;
        }

        public int[] getSupportedTypes() {
            return new int[]{0};
        }
    }

    static final class Iso8601Parser implements Parser<Long> {
        private static final Pattern TIMESTAMP_WITH_TIMEZONE_PATTERN = Pattern.compile("(.+?)(Z|((\\+|-|−)(\\d\\d)(:?(\\d\\d))?))");

        Iso8601Parser() {
        }

        public Long parse(Uri uri, InputStream inputStream) throws IOException {
            String readLine = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8"))).readLine();
            try {
                Matcher matcher = TIMESTAMP_WITH_TIMEZONE_PATTERN.matcher(readLine);
                if (matcher.matches()) {
                    String group = matcher.group(1);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    long time = simpleDateFormat.parse(group).getTime();
                    if (!"Z".equals(matcher.group(2))) {
                        long j = "+".equals(matcher.group(4)) ? 1 : -1;
                        long parseLong = Long.parseLong(matcher.group(5));
                        String group2 = matcher.group(7);
                        time -= j * ((((parseLong * 60) + (TextUtils.isEmpty(group2) ? 0 : Long.parseLong(group2))) * 60) * 1000);
                    }
                    return Long.valueOf(time);
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Couldn't parse timestamp: ");
                sb.append(readLine);
                throw new ParserException(sb.toString());
            } catch (ParseException e) {
                throw new ParserException((Throwable) e);
            }
        }
    }

    private final class ManifestCallback implements Callback<ParsingLoadable<DashManifest>> {
        private ManifestCallback() {
        }

        public void onLoadCompleted(ParsingLoadable<DashManifest> parsingLoadable, long j, long j2) {
            DashMediaSource.this.onManifestLoadCompleted(parsingLoadable, j, j2);
        }

        public void onLoadCanceled(ParsingLoadable<DashManifest> parsingLoadable, long j, long j2, boolean z) {
            DashMediaSource.this.onLoadCanceled(parsingLoadable, j, j2);
        }

        public LoadErrorAction onLoadError(ParsingLoadable<DashManifest> parsingLoadable, long j, long j2, IOException iOException, int i) {
            return DashMediaSource.this.onManifestLoadError(parsingLoadable, j, j2, iOException);
        }
    }

    final class ManifestLoadErrorThrower implements LoaderErrorThrower {
        ManifestLoadErrorThrower() {
        }

        public void maybeThrowError() throws IOException {
            DashMediaSource.this.loader.maybeThrowError();
            maybeThrowManifestError();
        }

        public void maybeThrowError(int i) throws IOException {
            DashMediaSource.this.loader.maybeThrowError(i);
            maybeThrowManifestError();
        }

        private void maybeThrowManifestError() throws IOException {
            if (DashMediaSource.this.manifestFatalError != null) {
                throw DashMediaSource.this.manifestFatalError;
            }
        }
    }

    private static final class PeriodSeekInfo {
        public final long availableEndTimeUs;
        public final long availableStartTimeUs;
        public final boolean isIndexExplicit;

        public static PeriodSeekInfo createPeriodSeekInfo(com.google.android.exoplayer2.source.dash.manifest.Period period, long j) {
            boolean z;
            int i;
            boolean z2;
            com.google.android.exoplayer2.source.dash.manifest.Period period2 = period;
            long j2 = j;
            int size = period2.adaptationSets.size();
            int i2 = 0;
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    z = false;
                    break;
                }
                int i4 = ((AdaptationSet) period2.adaptationSets.get(i3)).type;
                if (i4 == 1 || i4 == 2) {
                    z = true;
                } else {
                    i3++;
                }
            }
            long j3 = Long.MAX_VALUE;
            int i5 = 0;
            boolean z3 = false;
            boolean z4 = false;
            long j4 = 0;
            while (i5 < size) {
                AdaptationSet adaptationSet = (AdaptationSet) period2.adaptationSets.get(i5);
                if (!z || adaptationSet.type != 3) {
                    DashSegmentIndex index = ((Representation) adaptationSet.representations.get(i2)).getIndex();
                    if (index == null) {
                        PeriodSeekInfo periodSeekInfo = new PeriodSeekInfo(true, 0, j);
                        return periodSeekInfo;
                    }
                    boolean isExplicit = index.isExplicit() | z4;
                    int segmentCount = index.getSegmentCount(j2);
                    if (segmentCount == 0) {
                        i = size;
                        z2 = z;
                        z4 = isExplicit;
                        z3 = true;
                        j4 = 0;
                        j3 = 0;
                    } else if (!z3) {
                        z2 = z;
                        long firstSegmentNum = index.getFirstSegmentNum();
                        i = size;
                        long max = Math.max(j4, index.getTimeUs(firstSegmentNum));
                        if (segmentCount != -1) {
                            long j5 = (firstSegmentNum + ((long) segmentCount)) - 1;
                            j4 = max;
                            j3 = Math.min(j3, index.getTimeUs(j5) + index.getDurationUs(j5, j2));
                            z4 = isExplicit;
                        } else {
                            j4 = max;
                            z4 = isExplicit;
                        }
                    } else {
                        i = size;
                        z2 = z;
                        z4 = isExplicit;
                    }
                } else {
                    i = size;
                    z2 = z;
                }
                i5++;
                z = z2;
                size = i;
                period2 = period;
                i2 = 0;
            }
            PeriodSeekInfo periodSeekInfo2 = new PeriodSeekInfo(z4, j4, j3);
            return periodSeekInfo2;
        }

        private PeriodSeekInfo(boolean z, long j, long j2) {
            this.isIndexExplicit = z;
            this.availableStartTimeUs = j;
            this.availableEndTimeUs = j2;
        }
    }

    private final class UtcTimestampCallback implements Callback<ParsingLoadable<Long>> {
        private UtcTimestampCallback() {
        }

        public void onLoadCompleted(ParsingLoadable<Long> parsingLoadable, long j, long j2) {
            DashMediaSource.this.onUtcTimestampLoadCompleted(parsingLoadable, j, j2);
        }

        public void onLoadCanceled(ParsingLoadable<Long> parsingLoadable, long j, long j2, boolean z) {
            DashMediaSource.this.onLoadCanceled(parsingLoadable, j, j2);
        }

        public LoadErrorAction onLoadError(ParsingLoadable<Long> parsingLoadable, long j, long j2, IOException iOException, int i) {
            return DashMediaSource.this.onUtcTimestampLoadError(parsingLoadable, j, j2, iOException);
        }
    }

    private static final class XsDateTimeParser implements Parser<Long> {
        private XsDateTimeParser() {
        }

        public Long parse(Uri uri, InputStream inputStream) throws IOException {
            return Long.valueOf(Util.parseXsDateTime(new BufferedReader(new InputStreamReader(inputStream)).readLine()));
        }
    }

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.dash");
    }

    @Deprecated
    public DashMediaSource(DashManifest dashManifest, com.google.android.exoplayer2.source.dash.DashChunkSource.Factory factory, Handler handler2, MediaSourceEventListener mediaSourceEventListener) {
        this(dashManifest, factory, 3, handler2, mediaSourceEventListener);
    }

    @Deprecated
    public DashMediaSource(DashManifest dashManifest, com.google.android.exoplayer2.source.dash.DashChunkSource.Factory factory, int i, Handler handler2, MediaSourceEventListener mediaSourceEventListener) {
        Handler handler3 = handler2;
        MediaSourceEventListener mediaSourceEventListener2 = mediaSourceEventListener;
        this(dashManifest, null, null, null, factory, new DefaultCompositeSequenceableLoaderFactory(), new DefaultLoadErrorHandlingPolicy(i), 30000, false, null);
        if (handler3 == null || mediaSourceEventListener2 == null) {
            return;
        }
        addEventListener(handler3, mediaSourceEventListener2);
    }

    @Deprecated
    public DashMediaSource(Uri uri, com.google.android.exoplayer2.upstream.DataSource.Factory factory, com.google.android.exoplayer2.source.dash.DashChunkSource.Factory factory2, Handler handler2, MediaSourceEventListener mediaSourceEventListener) {
        this(uri, factory, factory2, 3, -1, handler2, mediaSourceEventListener);
    }

    @Deprecated
    public DashMediaSource(Uri uri, com.google.android.exoplayer2.upstream.DataSource.Factory factory, com.google.android.exoplayer2.source.dash.DashChunkSource.Factory factory2, int i, long j, Handler handler2, MediaSourceEventListener mediaSourceEventListener) {
        this(uri, factory, new DashManifestParser(), factory2, i, j, handler2, mediaSourceEventListener);
    }

    @Deprecated
    public DashMediaSource(Uri uri, com.google.android.exoplayer2.upstream.DataSource.Factory factory, Parser<? extends DashManifest> parser, com.google.android.exoplayer2.source.dash.DashChunkSource.Factory factory2, int i, long j, Handler handler2, MediaSourceEventListener mediaSourceEventListener) {
        Handler handler3 = handler2;
        MediaSourceEventListener mediaSourceEventListener2 = mediaSourceEventListener;
        int i2 = (j > -1 ? 1 : (j == -1 ? 0 : -1));
        this(null, uri, factory, parser, factory2, new DefaultCompositeSequenceableLoaderFactory(), new DefaultLoadErrorHandlingPolicy(i), i2 == 0 ? 30000 : j, i2 != 0, null);
        if (handler3 == null || mediaSourceEventListener2 == null) {
            return;
        }
        addEventListener(handler3, mediaSourceEventListener2);
    }

    private DashMediaSource(DashManifest dashManifest, Uri uri, com.google.android.exoplayer2.upstream.DataSource.Factory factory, Parser<? extends DashManifest> parser, com.google.android.exoplayer2.source.dash.DashChunkSource.Factory factory2, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, long j, boolean z, @Nullable Object obj) {
        this.initialManifestUri = uri;
        this.manifest = dashManifest;
        this.manifestUri = uri;
        this.manifestDataSourceFactory = factory;
        this.manifestParser = parser;
        this.chunkSourceFactory = factory2;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.livePresentationDelayMs = j;
        this.livePresentationDelayOverridesManifest = z;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.tag = obj;
        this.sideloadedManifest = dashManifest != null;
        this.manifestEventDispatcher = createEventDispatcher(null);
        this.manifestUriLock = new Object();
        this.periodsById = new SparseArray<>();
        this.playerEmsgCallback = new DefaultPlayerEmsgCallback();
        this.expiredManifestPublishTimeUs = -9223372036854775807L;
        if (this.sideloadedManifest) {
            Assertions.checkState(!dashManifest.dynamic);
            this.manifestCallback = null;
            this.refreshManifestRunnable = null;
            this.simulateManifestRefreshRunnable = null;
            this.manifestLoadErrorThrower = new Dummy();
            return;
        }
        this.manifestCallback = new ManifestCallback();
        this.manifestLoadErrorThrower = new ManifestLoadErrorThrower();
        this.refreshManifestRunnable = new Runnable() {
            public final void run() {
                DashMediaSource.this.startLoadingManifest();
            }
        };
        this.simulateManifestRefreshRunnable = new Runnable() {
            public final void run() {
                DashMediaSource.this.processManifest(false);
            }
        };
    }

    public void replaceManifestUri(Uri uri) {
        synchronized (this.manifestUriLock) {
            this.manifestUri = uri;
            this.initialManifestUri = uri;
        }
    }

    @Nullable
    public Object getTag() {
        return this.tag;
    }

    public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        this.mediaTransferListener = transferListener;
        if (this.sideloadedManifest) {
            processManifest(false);
            return;
        }
        this.dataSource = this.manifestDataSourceFactory.createDataSource();
        this.loader = new Loader("Loader:DashMediaSource");
        this.handler = new Handler();
        startLoadingManifest();
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        this.manifestLoadErrorThrower.maybeThrowError();
    }

    public MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        MediaPeriodId mediaPeriodId2 = mediaPeriodId;
        int intValue = ((Integer) mediaPeriodId2.periodUid).intValue() - this.firstPeriodId;
        EventDispatcher createEventDispatcher = createEventDispatcher(mediaPeriodId2, this.manifest.getPeriod(intValue).startMs);
        DashMediaPeriod dashMediaPeriod = new DashMediaPeriod(this.firstPeriodId + intValue, this.manifest, intValue, this.chunkSourceFactory, this.mediaTransferListener, this.loadErrorHandlingPolicy, createEventDispatcher, this.elapsedRealtimeOffsetMs, this.manifestLoadErrorThrower, allocator, this.compositeSequenceableLoaderFactory, this.playerEmsgCallback);
        this.periodsById.put(dashMediaPeriod.id, dashMediaPeriod);
        return dashMediaPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        DashMediaPeriod dashMediaPeriod = (DashMediaPeriod) mediaPeriod;
        dashMediaPeriod.release();
        this.periodsById.remove(dashMediaPeriod.id);
    }

    public void releaseSourceInternal() {
        this.manifestLoadPending = false;
        this.dataSource = null;
        Loader loader2 = this.loader;
        if (loader2 != null) {
            loader2.release();
            this.loader = null;
        }
        this.manifestLoadStartTimestampMs = 0;
        this.manifestLoadEndTimestampMs = 0;
        this.manifest = this.sideloadedManifest ? this.manifest : null;
        this.manifestUri = this.initialManifestUri;
        this.manifestFatalError = null;
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
            this.handler = null;
        }
        this.elapsedRealtimeOffsetMs = 0;
        this.staleManifestReloadAttempt = 0;
        this.expiredManifestPublishTimeUs = -9223372036854775807L;
        this.firstPeriodId = 0;
        this.periodsById.clear();
    }

    /* access modifiers changed from: 0000 */
    public void onDashManifestRefreshRequested() {
        this.handler.removeCallbacks(this.simulateManifestRefreshRunnable);
        startLoadingManifest();
    }

    /* access modifiers changed from: 0000 */
    public void onDashManifestPublishTimeExpired(long j) {
        long j2 = this.expiredManifestPublishTimeUs;
        if (j2 == -9223372036854775807L || j2 < j) {
            this.expiredManifestPublishTimeUs = j;
        }
    }

    /* access modifiers changed from: 0000 */
    public void onManifestLoadCompleted(ParsingLoadable<DashManifest> parsingLoadable, long j, long j2) {
        boolean z;
        ParsingLoadable<DashManifest> parsingLoadable2 = parsingLoadable;
        long j3 = j;
        this.manifestEventDispatcher.loadCompleted(parsingLoadable2.dataSpec, parsingLoadable.getUri(), parsingLoadable.getResponseHeaders(), parsingLoadable2.type, j, j2, parsingLoadable.bytesLoaded());
        DashManifest dashManifest = (DashManifest) parsingLoadable.getResult();
        DashManifest dashManifest2 = this.manifest;
        boolean z2 = false;
        int periodCount = dashManifest2 == null ? 0 : dashManifest2.getPeriodCount();
        long j4 = dashManifest.getPeriod(0).startMs;
        int i = 0;
        while (i < periodCount && this.manifest.getPeriod(i).startMs < j4) {
            i++;
        }
        if (dashManifest.dynamic) {
            if (periodCount - i > dashManifest.getPeriodCount()) {
                Log.w(TAG, "Loaded out of sync manifest");
                z = true;
            } else if (this.expiredManifestPublishTimeUs == -9223372036854775807L || dashManifest.publishTimeMs * 1000 > this.expiredManifestPublishTimeUs) {
                z = false;
            } else {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Loaded stale dynamic manifest: ");
                sb.append(dashManifest.publishTimeMs);
                sb.append(", ");
                sb.append(this.expiredManifestPublishTimeUs);
                Log.w(str, sb.toString());
                z = true;
            }
            if (z) {
                int i2 = this.staleManifestReloadAttempt;
                this.staleManifestReloadAttempt = i2 + 1;
                if (i2 < this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(parsingLoadable2.type)) {
                    scheduleManifestRefresh(getManifestLoadRetryDelayMillis());
                } else {
                    this.manifestFatalError = new DashManifestStaleException();
                }
                return;
            }
            this.staleManifestReloadAttempt = 0;
        }
        this.manifest = dashManifest;
        this.manifestLoadPending &= this.manifest.dynamic;
        this.manifestLoadStartTimestampMs = j3 - j2;
        this.manifestLoadEndTimestampMs = j3;
        if (this.manifest.location != null) {
            synchronized (this.manifestUriLock) {
                if (parsingLoadable2.dataSpec.uri == this.manifestUri) {
                    z2 = true;
                }
                if (z2) {
                    this.manifestUri = this.manifest.location;
                }
            }
        }
        if (periodCount != 0) {
            this.firstPeriodId += i;
            processManifest(true);
        } else if (this.manifest.utcTiming != null) {
            resolveUtcTimingElement(this.manifest.utcTiming);
        } else {
            processManifest(true);
        }
    }

    /* access modifiers changed from: 0000 */
    public LoadErrorAction onManifestLoadError(ParsingLoadable<DashManifest> parsingLoadable, long j, long j2, IOException iOException) {
        ParsingLoadable<DashManifest> parsingLoadable2 = parsingLoadable;
        IOException iOException2 = iOException;
        boolean z = iOException2 instanceof ParserException;
        this.manifestEventDispatcher.loadError(parsingLoadable2.dataSpec, parsingLoadable.getUri(), parsingLoadable.getResponseHeaders(), parsingLoadable2.type, j, j2, parsingLoadable.bytesLoaded(), iOException2, z);
        return z ? Loader.DONT_RETRY_FATAL : Loader.RETRY;
    }

    /* access modifiers changed from: 0000 */
    public void onUtcTimestampLoadCompleted(ParsingLoadable<Long> parsingLoadable, long j, long j2) {
        ParsingLoadable<Long> parsingLoadable2 = parsingLoadable;
        this.manifestEventDispatcher.loadCompleted(parsingLoadable2.dataSpec, parsingLoadable.getUri(), parsingLoadable.getResponseHeaders(), parsingLoadable2.type, j, j2, parsingLoadable.bytesLoaded());
        onUtcTimestampResolved(((Long) parsingLoadable.getResult()).longValue() - j);
    }

    /* access modifiers changed from: 0000 */
    public LoadErrorAction onUtcTimestampLoadError(ParsingLoadable<Long> parsingLoadable, long j, long j2, IOException iOException) {
        ParsingLoadable<Long> parsingLoadable2 = parsingLoadable;
        EventDispatcher eventDispatcher = this.manifestEventDispatcher;
        DataSpec dataSpec = parsingLoadable2.dataSpec;
        Uri uri = parsingLoadable.getUri();
        Map responseHeaders = parsingLoadable.getResponseHeaders();
        int i = parsingLoadable2.type;
        eventDispatcher.loadError(dataSpec, uri, responseHeaders, i, j, j2, parsingLoadable.bytesLoaded(), iOException, true);
        onUtcTimestampResolutionError(iOException);
        return Loader.DONT_RETRY;
    }

    /* access modifiers changed from: 0000 */
    public void onLoadCanceled(ParsingLoadable<?> parsingLoadable, long j, long j2) {
        ParsingLoadable<?> parsingLoadable2 = parsingLoadable;
        this.manifestEventDispatcher.loadCanceled(parsingLoadable2.dataSpec, parsingLoadable.getUri(), parsingLoadable.getResponseHeaders(), parsingLoadable2.type, j, j2, parsingLoadable.bytesLoaded());
    }

    private void resolveUtcTimingElement(UtcTimingElement utcTimingElement) {
        String str = utcTimingElement.schemeIdUri;
        if (Util.areEqual(str, "urn:mpeg:dash:utc:direct:2014") || Util.areEqual(str, "urn:mpeg:dash:utc:direct:2012")) {
            resolveUtcTimingElementDirect(utcTimingElement);
        } else if (Util.areEqual(str, "urn:mpeg:dash:utc:http-iso:2014") || Util.areEqual(str, "urn:mpeg:dash:utc:http-iso:2012")) {
            resolveUtcTimingElementHttp(utcTimingElement, new Iso8601Parser());
        } else if (Util.areEqual(str, "urn:mpeg:dash:utc:http-xsdate:2014") || Util.areEqual(str, "urn:mpeg:dash:utc:http-xsdate:2012")) {
            resolveUtcTimingElementHttp(utcTimingElement, new XsDateTimeParser());
        } else {
            onUtcTimestampResolutionError(new IOException("Unsupported UTC timing scheme"));
        }
    }

    private void resolveUtcTimingElementDirect(UtcTimingElement utcTimingElement) {
        try {
            onUtcTimestampResolved(Util.parseXsDateTime(utcTimingElement.value) - this.manifestLoadEndTimestampMs);
        } catch (ParserException e) {
            onUtcTimestampResolutionError(e);
        }
    }

    private void resolveUtcTimingElementHttp(UtcTimingElement utcTimingElement, Parser<Long> parser) {
        startLoading(new ParsingLoadable(this.dataSource, Uri.parse(utcTimingElement.value), 5, parser), new UtcTimestampCallback(), 1);
    }

    private void onUtcTimestampResolved(long j) {
        this.elapsedRealtimeOffsetMs = j;
        processManifest(true);
    }

    private void onUtcTimestampResolutionError(IOException iOException) {
        Log.e(TAG, "Failed to resolve UtcTiming element.", iOException);
        processManifest(true);
    }

    /* access modifiers changed from: private */
    public void processManifest(boolean z) {
        long j;
        boolean z2;
        long j2;
        for (int i = 0; i < this.periodsById.size(); i++) {
            int keyAt = this.periodsById.keyAt(i);
            if (keyAt >= this.firstPeriodId) {
                ((DashMediaPeriod) this.periodsById.valueAt(i)).updateManifest(this.manifest, keyAt - this.firstPeriodId);
            }
        }
        int periodCount = this.manifest.getPeriodCount() - 1;
        PeriodSeekInfo createPeriodSeekInfo = PeriodSeekInfo.createPeriodSeekInfo(this.manifest.getPeriod(0), this.manifest.getPeriodDurationUs(0));
        PeriodSeekInfo createPeriodSeekInfo2 = PeriodSeekInfo.createPeriodSeekInfo(this.manifest.getPeriod(periodCount), this.manifest.getPeriodDurationUs(periodCount));
        long j3 = createPeriodSeekInfo.availableStartTimeUs;
        long j4 = createPeriodSeekInfo2.availableEndTimeUs;
        if (!this.manifest.dynamic || createPeriodSeekInfo2.isIndexExplicit) {
            j = j3;
            z2 = false;
        } else {
            j4 = Math.min((getNowUnixTimeUs() - C.msToUs(this.manifest.availabilityStartTimeMs)) - C.msToUs(this.manifest.getPeriod(periodCount).startMs), j4);
            if (this.manifest.timeShiftBufferDepthMs != -9223372036854775807L) {
                long msToUs = j4 - C.msToUs(this.manifest.timeShiftBufferDepthMs);
                while (msToUs < 0 && periodCount > 0) {
                    periodCount--;
                    msToUs += this.manifest.getPeriodDurationUs(periodCount);
                }
                if (periodCount == 0) {
                    j3 = Math.max(j3, msToUs);
                } else {
                    j3 = this.manifest.getPeriodDurationUs(0);
                }
            }
            j = j3;
            z2 = true;
        }
        long j5 = j4 - j;
        for (int i2 = 0; i2 < this.manifest.getPeriodCount() - 1; i2++) {
            j5 += this.manifest.getPeriodDurationUs(i2);
        }
        if (this.manifest.dynamic) {
            long j6 = this.livePresentationDelayMs;
            if (!this.livePresentationDelayOverridesManifest && this.manifest.suggestedPresentationDelayMs != -9223372036854775807L) {
                j6 = this.manifest.suggestedPresentationDelayMs;
            }
            long msToUs2 = j5 - C.msToUs(j6);
            j2 = msToUs2 < MIN_LIVE_DEFAULT_START_POSITION_US ? Math.min(MIN_LIVE_DEFAULT_START_POSITION_US, j5 / 2) : msToUs2;
        } else {
            j2 = 0;
        }
        DashTimeline dashTimeline = new DashTimeline(this.manifest.availabilityStartTimeMs, this.manifest.availabilityStartTimeMs + this.manifest.getPeriod(0).startMs + C.usToMs(j), this.firstPeriodId, j, j5, j2, this.manifest, this.tag);
        refreshSourceInfo(dashTimeline, this.manifest);
        if (!this.sideloadedManifest) {
            this.handler.removeCallbacks(this.simulateManifestRefreshRunnable);
            if (z2) {
                this.handler.postDelayed(this.simulateManifestRefreshRunnable, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
            }
            if (this.manifestLoadPending) {
                startLoadingManifest();
            } else if (z && this.manifest.dynamic && this.manifest.minUpdatePeriodMs != -9223372036854775807L) {
                long j7 = this.manifest.minUpdatePeriodMs;
                if (j7 == 0) {
                    j7 = 5000;
                }
                scheduleManifestRefresh(Math.max(0, (this.manifestLoadStartTimestampMs + j7) - SystemClock.elapsedRealtime()));
            }
        }
    }

    private void scheduleManifestRefresh(long j) {
        this.handler.postDelayed(this.refreshManifestRunnable, j);
    }

    /* access modifiers changed from: private */
    public void startLoadingManifest() {
        Uri uri;
        this.handler.removeCallbacks(this.refreshManifestRunnable);
        if (this.loader.isLoading()) {
            this.manifestLoadPending = true;
            return;
        }
        synchronized (this.manifestUriLock) {
            uri = this.manifestUri;
        }
        this.manifestLoadPending = false;
        startLoading(new ParsingLoadable(this.dataSource, uri, 4, this.manifestParser), this.manifestCallback, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(4));
    }

    private long getManifestLoadRetryDelayMillis() {
        return (long) Math.min((this.staleManifestReloadAttempt - 1) * 1000, 5000);
    }

    private <T> void startLoading(ParsingLoadable<T> parsingLoadable, Callback<ParsingLoadable<T>> callback, int i) {
        this.manifestEventDispatcher.loadStarted(parsingLoadable.dataSpec, parsingLoadable.type, this.loader.startLoading(parsingLoadable, callback, i));
    }

    private long getNowUnixTimeUs() {
        if (this.elapsedRealtimeOffsetMs != 0) {
            return C.msToUs(SystemClock.elapsedRealtime() + this.elapsedRealtimeOffsetMs);
        }
        return C.msToUs(System.currentTimeMillis());
    }
}
