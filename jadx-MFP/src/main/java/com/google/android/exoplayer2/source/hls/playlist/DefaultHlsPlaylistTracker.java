package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher;
import com.google.android.exoplayer2.source.hls.HlsDataSourceFactory;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist.HlsUrl;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.Segment;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.Factory;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.PlaylistEventListener;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.PlaylistResetException;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.PlaylistStuckException;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.PrimaryPlaylistListener;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.Loader.Callback;
import com.google.android.exoplayer2.upstream.Loader.LoadErrorAction;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.upstream.ParsingLoadable.Parser;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.UriUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

public final class DefaultHlsPlaylistTracker implements HlsPlaylistTracker, Callback<ParsingLoadable<HlsPlaylist>> {
    public static final Factory FACTORY = $$Lambda$lKTLOVxne0MoBOOliKH0gO2KDMM.INSTANCE;
    private static final double PLAYLIST_STUCK_TARGET_DURATION_COEFFICIENT = 3.5d;
    /* access modifiers changed from: private */
    public final HlsDataSourceFactory dataSourceFactory;
    /* access modifiers changed from: private */
    @Nullable
    public EventDispatcher eventDispatcher;
    @Nullable
    private Loader initialPlaylistLoader;
    private long initialStartTimeUs;
    private boolean isLive;
    private final List<PlaylistEventListener> listeners;
    /* access modifiers changed from: private */
    public final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    /* access modifiers changed from: private */
    @Nullable
    public HlsMasterPlaylist masterPlaylist;
    /* access modifiers changed from: private */
    @Nullable
    public Parser<HlsPlaylist> mediaPlaylistParser;
    private final IdentityHashMap<HlsUrl, MediaPlaylistBundle> playlistBundles;
    private final HlsPlaylistParserFactory playlistParserFactory;
    /* access modifiers changed from: private */
    @Nullable
    public Handler playlistRefreshHandler;
    /* access modifiers changed from: private */
    @Nullable
    public HlsUrl primaryHlsUrl;
    @Nullable
    private PrimaryPlaylistListener primaryPlaylistListener;
    @Nullable
    private HlsMediaPlaylist primaryUrlSnapshot;

    private final class MediaPlaylistBundle implements Callback<ParsingLoadable<HlsPlaylist>>, Runnable {
        /* access modifiers changed from: private */
        public long blacklistUntilMs;
        private long earliestNextLoadTimeMs;
        private long lastSnapshotChangeMs;
        private long lastSnapshotLoadMs;
        private boolean loadPending;
        private final ParsingLoadable<HlsPlaylist> mediaPlaylistLoadable;
        private final Loader mediaPlaylistLoader = new Loader("DefaultHlsPlaylistTracker:MediaPlaylist");
        private IOException playlistError;
        private HlsMediaPlaylist playlistSnapshot;
        /* access modifiers changed from: private */
        public final HlsUrl playlistUrl;

        public MediaPlaylistBundle(HlsUrl hlsUrl) {
            this.playlistUrl = hlsUrl;
            this.mediaPlaylistLoadable = new ParsingLoadable<>(DefaultHlsPlaylistTracker.this.dataSourceFactory.createDataSource(4), UriUtil.resolveToUri(DefaultHlsPlaylistTracker.this.masterPlaylist.baseUri, hlsUrl.url), 4, DefaultHlsPlaylistTracker.this.mediaPlaylistParser);
        }

        public HlsMediaPlaylist getPlaylistSnapshot() {
            return this.playlistSnapshot;
        }

        public boolean isSnapshotValid() {
            boolean z = false;
            if (this.playlistSnapshot == null) {
                return false;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long max = Math.max(30000, C.usToMs(this.playlistSnapshot.durationUs));
            if (this.playlistSnapshot.hasEndTag || this.playlistSnapshot.playlistType == 2 || this.playlistSnapshot.playlistType == 1 || this.lastSnapshotLoadMs + max > elapsedRealtime) {
                z = true;
            }
            return z;
        }

        public void release() {
            this.mediaPlaylistLoader.release();
        }

        public void loadPlaylist() {
            this.blacklistUntilMs = 0;
            if (!this.loadPending && !this.mediaPlaylistLoader.isLoading()) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (elapsedRealtime < this.earliestNextLoadTimeMs) {
                    this.loadPending = true;
                    DefaultHlsPlaylistTracker.this.playlistRefreshHandler.postDelayed(this, this.earliestNextLoadTimeMs - elapsedRealtime);
                } else {
                    loadPlaylistImmediately();
                }
            }
        }

        public void maybeThrowPlaylistRefreshError() throws IOException {
            this.mediaPlaylistLoader.maybeThrowError();
            IOException iOException = this.playlistError;
            if (iOException != null) {
                throw iOException;
            }
        }

        public void onLoadCompleted(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2) {
            HlsPlaylist hlsPlaylist = (HlsPlaylist) parsingLoadable.getResult();
            if (hlsPlaylist instanceof HlsMediaPlaylist) {
                long j3 = j2;
                processLoadedPlaylist((HlsMediaPlaylist) hlsPlaylist, j3);
                DefaultHlsPlaylistTracker.this.eventDispatcher.loadCompleted(parsingLoadable.dataSpec, parsingLoadable.getUri(), parsingLoadable.getResponseHeaders(), 4, j, j3, parsingLoadable.bytesLoaded());
                return;
            }
            this.playlistError = new ParserException("Loaded playlist has unexpected type.");
        }

        public void onLoadCanceled(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2, boolean z) {
            DefaultHlsPlaylistTracker.this.eventDispatcher.loadCanceled(parsingLoadable.dataSpec, parsingLoadable.getUri(), parsingLoadable.getResponseHeaders(), 4, j, j2, parsingLoadable.bytesLoaded());
        }

        public LoadErrorAction onLoadError(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2, IOException iOException, int i) {
            LoadErrorAction loadErrorAction;
            ParsingLoadable<HlsPlaylist> parsingLoadable2 = parsingLoadable;
            long blacklistDurationMsFor = DefaultHlsPlaylistTracker.this.loadErrorHandlingPolicy.getBlacklistDurationMsFor(parsingLoadable2.type, j2, iOException, i);
            boolean z = blacklistDurationMsFor != -9223372036854775807L;
            boolean z2 = DefaultHlsPlaylistTracker.this.notifyPlaylistError(this.playlistUrl, blacklistDurationMsFor) || !z;
            if (z) {
                z2 |= blacklistPlaylist(blacklistDurationMsFor);
            }
            if (z2) {
                long retryDelayMsFor = DefaultHlsPlaylistTracker.this.loadErrorHandlingPolicy.getRetryDelayMsFor(parsingLoadable2.type, j2, iOException, i);
                loadErrorAction = retryDelayMsFor != -9223372036854775807L ? Loader.createRetryAction(false, retryDelayMsFor) : Loader.DONT_RETRY_FATAL;
            } else {
                loadErrorAction = Loader.DONT_RETRY;
            }
            DefaultHlsPlaylistTracker.this.eventDispatcher.loadError(parsingLoadable2.dataSpec, parsingLoadable.getUri(), parsingLoadable.getResponseHeaders(), 4, j, j2, parsingLoadable.bytesLoaded(), iOException, !loadErrorAction.isRetry());
            return loadErrorAction;
        }

        public void run() {
            this.loadPending = false;
            loadPlaylistImmediately();
        }

        private void loadPlaylistImmediately() {
            DefaultHlsPlaylistTracker.this.eventDispatcher.loadStarted(this.mediaPlaylistLoadable.dataSpec, this.mediaPlaylistLoadable.type, this.mediaPlaylistLoader.startLoading(this.mediaPlaylistLoadable, this, DefaultHlsPlaylistTracker.this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(this.mediaPlaylistLoadable.type)));
        }

        /* access modifiers changed from: private */
        public void processLoadedPlaylist(HlsMediaPlaylist hlsMediaPlaylist, long j) {
            HlsMediaPlaylist hlsMediaPlaylist2 = hlsMediaPlaylist;
            HlsMediaPlaylist hlsMediaPlaylist3 = this.playlistSnapshot;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.lastSnapshotLoadMs = elapsedRealtime;
            this.playlistSnapshot = DefaultHlsPlaylistTracker.this.getLatestPlaylistSnapshot(hlsMediaPlaylist3, hlsMediaPlaylist2);
            HlsMediaPlaylist hlsMediaPlaylist4 = this.playlistSnapshot;
            if (hlsMediaPlaylist4 != hlsMediaPlaylist3) {
                this.playlistError = null;
                this.lastSnapshotChangeMs = elapsedRealtime;
                DefaultHlsPlaylistTracker.this.onPlaylistUpdated(this.playlistUrl, hlsMediaPlaylist4);
            } else if (!hlsMediaPlaylist4.hasEndTag) {
                if (hlsMediaPlaylist2.mediaSequence + ((long) hlsMediaPlaylist2.segments.size()) < this.playlistSnapshot.mediaSequence) {
                    this.playlistError = new PlaylistResetException(this.playlistUrl.url);
                    DefaultHlsPlaylistTracker.this.notifyPlaylistError(this.playlistUrl, -9223372036854775807L);
                } else if (((double) (elapsedRealtime - this.lastSnapshotChangeMs)) > ((double) C.usToMs(this.playlistSnapshot.targetDurationUs)) * DefaultHlsPlaylistTracker.PLAYLIST_STUCK_TARGET_DURATION_COEFFICIENT) {
                    this.playlistError = new PlaylistStuckException(this.playlistUrl.url);
                    long blacklistDurationMsFor = DefaultHlsPlaylistTracker.this.loadErrorHandlingPolicy.getBlacklistDurationMsFor(4, j, this.playlistError, 1);
                    DefaultHlsPlaylistTracker.this.notifyPlaylistError(this.playlistUrl, blacklistDurationMsFor);
                    if (blacklistDurationMsFor != -9223372036854775807L) {
                        blacklistPlaylist(blacklistDurationMsFor);
                    }
                }
            }
            HlsMediaPlaylist hlsMediaPlaylist5 = this.playlistSnapshot;
            this.earliestNextLoadTimeMs = elapsedRealtime + C.usToMs(hlsMediaPlaylist5 != hlsMediaPlaylist3 ? hlsMediaPlaylist5.targetDurationUs : hlsMediaPlaylist5.targetDurationUs / 2);
            if (this.playlistUrl == DefaultHlsPlaylistTracker.this.primaryHlsUrl && !this.playlistSnapshot.hasEndTag) {
                loadPlaylist();
            }
        }

        private boolean blacklistPlaylist(long j) {
            this.blacklistUntilMs = SystemClock.elapsedRealtime() + j;
            return DefaultHlsPlaylistTracker.this.primaryHlsUrl == this.playlistUrl && !DefaultHlsPlaylistTracker.this.maybeSelectNewPrimaryUrl();
        }
    }

    @Deprecated
    public DefaultHlsPlaylistTracker(HlsDataSourceFactory hlsDataSourceFactory, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, Parser<HlsPlaylist> parser) {
        this(hlsDataSourceFactory, loadErrorHandlingPolicy2, createFixedFactory(parser));
    }

    public DefaultHlsPlaylistTracker(HlsDataSourceFactory hlsDataSourceFactory, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, HlsPlaylistParserFactory hlsPlaylistParserFactory) {
        this.dataSourceFactory = hlsDataSourceFactory;
        this.playlistParserFactory = hlsPlaylistParserFactory;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.listeners = new ArrayList();
        this.playlistBundles = new IdentityHashMap<>();
        this.initialStartTimeUs = -9223372036854775807L;
    }

    public void start(Uri uri, EventDispatcher eventDispatcher2, PrimaryPlaylistListener primaryPlaylistListener2) {
        this.playlistRefreshHandler = new Handler();
        this.eventDispatcher = eventDispatcher2;
        this.primaryPlaylistListener = primaryPlaylistListener2;
        ParsingLoadable parsingLoadable = new ParsingLoadable(this.dataSourceFactory.createDataSource(4), uri, 4, this.playlistParserFactory.createPlaylistParser());
        Assertions.checkState(this.initialPlaylistLoader == null);
        this.initialPlaylistLoader = new Loader("DefaultHlsPlaylistTracker:MasterPlaylist");
        eventDispatcher2.loadStarted(parsingLoadable.dataSpec, parsingLoadable.type, this.initialPlaylistLoader.startLoading(parsingLoadable, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(parsingLoadable.type)));
    }

    public void stop() {
        this.primaryHlsUrl = null;
        this.primaryUrlSnapshot = null;
        this.masterPlaylist = null;
        this.initialStartTimeUs = -9223372036854775807L;
        this.initialPlaylistLoader.release();
        this.initialPlaylistLoader = null;
        for (MediaPlaylistBundle release : this.playlistBundles.values()) {
            release.release();
        }
        this.playlistRefreshHandler.removeCallbacksAndMessages(null);
        this.playlistRefreshHandler = null;
        this.playlistBundles.clear();
    }

    public void addListener(PlaylistEventListener playlistEventListener) {
        this.listeners.add(playlistEventListener);
    }

    public void removeListener(PlaylistEventListener playlistEventListener) {
        this.listeners.remove(playlistEventListener);
    }

    @Nullable
    public HlsMasterPlaylist getMasterPlaylist() {
        return this.masterPlaylist;
    }

    public HlsMediaPlaylist getPlaylistSnapshot(HlsUrl hlsUrl, boolean z) {
        HlsMediaPlaylist playlistSnapshot = ((MediaPlaylistBundle) this.playlistBundles.get(hlsUrl)).getPlaylistSnapshot();
        if (playlistSnapshot != null && z) {
            maybeSetPrimaryUrl(hlsUrl);
        }
        return playlistSnapshot;
    }

    public long getInitialStartTimeUs() {
        return this.initialStartTimeUs;
    }

    public boolean isSnapshotValid(HlsUrl hlsUrl) {
        return ((MediaPlaylistBundle) this.playlistBundles.get(hlsUrl)).isSnapshotValid();
    }

    public void maybeThrowPrimaryPlaylistRefreshError() throws IOException {
        Loader loader = this.initialPlaylistLoader;
        if (loader != null) {
            loader.maybeThrowError();
        }
        HlsUrl hlsUrl = this.primaryHlsUrl;
        if (hlsUrl != null) {
            maybeThrowPlaylistRefreshError(hlsUrl);
        }
    }

    public void maybeThrowPlaylistRefreshError(HlsUrl hlsUrl) throws IOException {
        ((MediaPlaylistBundle) this.playlistBundles.get(hlsUrl)).maybeThrowPlaylistRefreshError();
    }

    public void refreshPlaylist(HlsUrl hlsUrl) {
        ((MediaPlaylistBundle) this.playlistBundles.get(hlsUrl)).loadPlaylist();
    }

    public boolean isLive() {
        return this.isLive;
    }

    public void onLoadCompleted(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2) {
        HlsMasterPlaylist hlsMasterPlaylist;
        HlsPlaylist hlsPlaylist = (HlsPlaylist) parsingLoadable.getResult();
        boolean z = hlsPlaylist instanceof HlsMediaPlaylist;
        if (z) {
            hlsMasterPlaylist = HlsMasterPlaylist.createSingleVariantMasterPlaylist(hlsPlaylist.baseUri);
        } else {
            hlsMasterPlaylist = (HlsMasterPlaylist) hlsPlaylist;
        }
        this.masterPlaylist = hlsMasterPlaylist;
        this.mediaPlaylistParser = this.playlistParserFactory.createPlaylistParser(hlsMasterPlaylist);
        this.primaryHlsUrl = (HlsUrl) hlsMasterPlaylist.variants.get(0);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(hlsMasterPlaylist.variants);
        arrayList.addAll(hlsMasterPlaylist.audios);
        arrayList.addAll(hlsMasterPlaylist.subtitles);
        createBundles(arrayList);
        MediaPlaylistBundle mediaPlaylistBundle = (MediaPlaylistBundle) this.playlistBundles.get(this.primaryHlsUrl);
        if (z) {
            mediaPlaylistBundle.processLoadedPlaylist((HlsMediaPlaylist) hlsPlaylist, j2);
        } else {
            long j3 = j2;
            mediaPlaylistBundle.loadPlaylist();
        }
        this.eventDispatcher.loadCompleted(parsingLoadable.dataSpec, parsingLoadable.getUri(), parsingLoadable.getResponseHeaders(), 4, j, j2, parsingLoadable.bytesLoaded());
    }

    public void onLoadCanceled(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2, boolean z) {
        EventDispatcher eventDispatcher2 = this.eventDispatcher;
        DataSpec dataSpec = parsingLoadable.dataSpec;
        Uri uri = parsingLoadable.getUri();
        eventDispatcher2.loadCanceled(dataSpec, uri, parsingLoadable.getResponseHeaders(), 4, j, j2, parsingLoadable.bytesLoaded());
    }

    public LoadErrorAction onLoadError(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2, IOException iOException, int i) {
        ParsingLoadable<HlsPlaylist> parsingLoadable2 = parsingLoadable;
        long retryDelayMsFor = this.loadErrorHandlingPolicy.getRetryDelayMsFor(parsingLoadable2.type, j2, iOException, i);
        boolean z = retryDelayMsFor == -9223372036854775807L;
        this.eventDispatcher.loadError(parsingLoadable2.dataSpec, parsingLoadable.getUri(), parsingLoadable.getResponseHeaders(), 4, j, j2, parsingLoadable.bytesLoaded(), iOException, z);
        if (z) {
            return Loader.DONT_RETRY_FATAL;
        }
        return Loader.createRetryAction(false, retryDelayMsFor);
    }

    /* access modifiers changed from: private */
    public boolean maybeSelectNewPrimaryUrl() {
        List<HlsUrl> list = this.masterPlaylist.variants;
        int size = list.size();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        for (int i = 0; i < size; i++) {
            MediaPlaylistBundle mediaPlaylistBundle = (MediaPlaylistBundle) this.playlistBundles.get(list.get(i));
            if (elapsedRealtime > mediaPlaylistBundle.blacklistUntilMs) {
                this.primaryHlsUrl = mediaPlaylistBundle.playlistUrl;
                mediaPlaylistBundle.loadPlaylist();
                return true;
            }
        }
        return false;
    }

    private void maybeSetPrimaryUrl(HlsUrl hlsUrl) {
        if (hlsUrl != this.primaryHlsUrl && this.masterPlaylist.variants.contains(hlsUrl)) {
            HlsMediaPlaylist hlsMediaPlaylist = this.primaryUrlSnapshot;
            if (hlsMediaPlaylist == null || !hlsMediaPlaylist.hasEndTag) {
                this.primaryHlsUrl = hlsUrl;
                ((MediaPlaylistBundle) this.playlistBundles.get(this.primaryHlsUrl)).loadPlaylist();
            }
        }
    }

    private void createBundles(List<HlsUrl> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            HlsUrl hlsUrl = (HlsUrl) list.get(i);
            this.playlistBundles.put(hlsUrl, new MediaPlaylistBundle(hlsUrl));
        }
    }

    /* access modifiers changed from: private */
    public void onPlaylistUpdated(HlsUrl hlsUrl, HlsMediaPlaylist hlsMediaPlaylist) {
        if (hlsUrl == this.primaryHlsUrl) {
            if (this.primaryUrlSnapshot == null) {
                this.isLive = !hlsMediaPlaylist.hasEndTag;
                this.initialStartTimeUs = hlsMediaPlaylist.startTimeUs;
            }
            this.primaryUrlSnapshot = hlsMediaPlaylist;
            this.primaryPlaylistListener.onPrimaryPlaylistRefreshed(hlsMediaPlaylist);
        }
        int size = this.listeners.size();
        for (int i = 0; i < size; i++) {
            ((PlaylistEventListener) this.listeners.get(i)).onPlaylistChanged();
        }
    }

    /* access modifiers changed from: private */
    public boolean notifyPlaylistError(HlsUrl hlsUrl, long j) {
        boolean z = false;
        for (int i = 0; i < this.listeners.size(); i++) {
            z |= !((PlaylistEventListener) this.listeners.get(i)).onPlaylistError(hlsUrl, j);
        }
        return z;
    }

    /* access modifiers changed from: private */
    public HlsMediaPlaylist getLatestPlaylistSnapshot(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist hlsMediaPlaylist2) {
        if (!hlsMediaPlaylist2.isNewerThan(hlsMediaPlaylist)) {
            return hlsMediaPlaylist2.hasEndTag ? hlsMediaPlaylist.copyWithEndTag() : hlsMediaPlaylist;
        }
        return hlsMediaPlaylist2.copyWith(getLoadedPlaylistStartTimeUs(hlsMediaPlaylist, hlsMediaPlaylist2), getLoadedPlaylistDiscontinuitySequence(hlsMediaPlaylist, hlsMediaPlaylist2));
    }

    private long getLoadedPlaylistStartTimeUs(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist hlsMediaPlaylist2) {
        if (hlsMediaPlaylist2.hasProgramDateTime) {
            return hlsMediaPlaylist2.startTimeUs;
        }
        HlsMediaPlaylist hlsMediaPlaylist3 = this.primaryUrlSnapshot;
        long j = hlsMediaPlaylist3 != null ? hlsMediaPlaylist3.startTimeUs : 0;
        if (hlsMediaPlaylist == null) {
            return j;
        }
        int size = hlsMediaPlaylist.segments.size();
        Segment firstOldOverlappingSegment = getFirstOldOverlappingSegment(hlsMediaPlaylist, hlsMediaPlaylist2);
        if (firstOldOverlappingSegment != null) {
            return hlsMediaPlaylist.startTimeUs + firstOldOverlappingSegment.relativeStartTimeUs;
        }
        return ((long) size) == hlsMediaPlaylist2.mediaSequence - hlsMediaPlaylist.mediaSequence ? hlsMediaPlaylist.getEndTimeUs() : j;
    }

    private int getLoadedPlaylistDiscontinuitySequence(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist hlsMediaPlaylist2) {
        if (hlsMediaPlaylist2.hasDiscontinuitySequence) {
            return hlsMediaPlaylist2.discontinuitySequence;
        }
        HlsMediaPlaylist hlsMediaPlaylist3 = this.primaryUrlSnapshot;
        int i = hlsMediaPlaylist3 != null ? hlsMediaPlaylist3.discontinuitySequence : 0;
        if (hlsMediaPlaylist == null) {
            return i;
        }
        Segment firstOldOverlappingSegment = getFirstOldOverlappingSegment(hlsMediaPlaylist, hlsMediaPlaylist2);
        return firstOldOverlappingSegment != null ? (hlsMediaPlaylist.discontinuitySequence + firstOldOverlappingSegment.relativeDiscontinuitySequence) - ((Segment) hlsMediaPlaylist2.segments.get(0)).relativeDiscontinuitySequence : i;
    }

    private static Segment getFirstOldOverlappingSegment(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist hlsMediaPlaylist2) {
        int i = (int) (hlsMediaPlaylist2.mediaSequence - hlsMediaPlaylist.mediaSequence);
        List<Segment> list = hlsMediaPlaylist.segments;
        if (i < list.size()) {
            return (Segment) list.get(i);
        }
        return null;
    }

    private static HlsPlaylistParserFactory createFixedFactory(final Parser<HlsPlaylist> parser) {
        return new HlsPlaylistParserFactory() {
            public Parser<HlsPlaylist> createPlaylistParser() {
                return parser;
            }

            public Parser<HlsPlaylist> createPlaylistParser(HlsMasterPlaylist hlsMasterPlaylist) {
                return parser;
            }
        };
    }
}
