package com.google.android.exoplayer2.offline;

import android.net.Uri;
import android.support.annotation.NonNull;
import com.google.android.exoplayer2.offline.FilterableManifest;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheUtil;
import com.google.android.exoplayer2.upstream.cache.CacheUtil.CachingCounters;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class SegmentDownloader<M extends FilterableManifest<M>> implements Downloader {
    private static final int BUFFER_SIZE_BYTES = 131072;
    private final Cache cache;
    private final CacheDataSource dataSource;
    private volatile long downloadedBytes;
    private volatile int downloadedSegments;
    private final AtomicBoolean isCanceled = new AtomicBoolean();
    private final Uri manifestUri;
    private final CacheDataSource offlineDataSource;
    private final PriorityTaskManager priorityTaskManager;
    private final ArrayList<StreamKey> streamKeys;
    private volatile int totalSegments = -1;

    protected static class Segment implements Comparable<Segment> {
        public final DataSpec dataSpec;
        public final long startTimeUs;

        public Segment(long j, DataSpec dataSpec2) {
            this.startTimeUs = j;
            this.dataSpec = dataSpec2;
        }

        public int compareTo(@NonNull Segment segment) {
            return Util.compareLong(this.startTimeUs, segment.startTimeUs);
        }
    }

    /* access modifiers changed from: protected */
    public abstract M getManifest(DataSource dataSource2, Uri uri) throws IOException;

    /* access modifiers changed from: protected */
    public abstract List<Segment> getSegments(DataSource dataSource2, M m, boolean z) throws InterruptedException, IOException;

    public SegmentDownloader(Uri uri, List<StreamKey> list, DownloaderConstructorHelper downloaderConstructorHelper) {
        this.manifestUri = uri;
        this.streamKeys = new ArrayList<>(list);
        this.cache = downloaderConstructorHelper.getCache();
        this.dataSource = downloaderConstructorHelper.buildCacheDataSource(false);
        this.offlineDataSource = downloaderConstructorHelper.buildCacheDataSource(true);
        this.priorityTaskManager = downloaderConstructorHelper.getPriorityTaskManager();
    }

    public final void download() throws IOException, InterruptedException {
        CachingCounters cachingCounters;
        this.priorityTaskManager.add(-1000);
        try {
            List initDownload = initDownload();
            Collections.sort(initDownload);
            byte[] bArr = new byte[131072];
            cachingCounters = new CachingCounters();
            for (int i = 0; i < initDownload.size(); i++) {
                CacheUtil.cache(((Segment) initDownload.get(i)).dataSpec, this.cache, this.dataSource, bArr, this.priorityTaskManager, -1000, cachingCounters, this.isCanceled, true);
                this.downloadedSegments++;
                this.downloadedBytes += cachingCounters.newlyCachedBytes;
            }
            this.priorityTaskManager.remove(-1000);
        } catch (Throwable th) {
            this.priorityTaskManager.remove(-1000);
            throw th;
        }
    }

    public void cancel() {
        this.isCanceled.set(true);
    }

    public final long getDownloadedBytes() {
        return this.downloadedBytes;
    }

    public final float getDownloadPercentage() {
        int i = this.totalSegments;
        int i2 = this.downloadedSegments;
        if (i == -1 || i2 == -1) {
            return -1.0f;
        }
        float f = 100.0f;
        if (i != 0) {
            f = (((float) i2) * 100.0f) / ((float) i);
        }
        return f;
    }

    public final void remove() throws InterruptedException {
        try {
            List segments = getSegments(this.offlineDataSource, getManifest(this.offlineDataSource, this.manifestUri), true);
            for (int i = 0; i < segments.size(); i++) {
                removeUri(((Segment) segments.get(i)).dataSpec.uri);
            }
        } catch (IOException unused) {
        } catch (Throwable th) {
            removeUri(this.manifestUri);
            throw th;
        }
        removeUri(this.manifestUri);
    }

    private List<Segment> initDownload() throws IOException, InterruptedException {
        FilterableManifest manifest = getManifest(this.dataSource, this.manifestUri);
        if (!this.streamKeys.isEmpty()) {
            manifest = (FilterableManifest) manifest.copy(this.streamKeys);
        }
        List<Segment> segments = getSegments(this.dataSource, manifest, false);
        CachingCounters cachingCounters = new CachingCounters();
        this.totalSegments = segments.size();
        this.downloadedSegments = 0;
        this.downloadedBytes = 0;
        for (int size = segments.size() - 1; size >= 0; size--) {
            CacheUtil.getCached(((Segment) segments.get(size)).dataSpec, this.cache, cachingCounters);
            this.downloadedBytes += cachingCounters.alreadyCachedBytes;
            if (cachingCounters.alreadyCachedBytes == cachingCounters.contentLength) {
                this.downloadedSegments++;
                segments.remove(size);
            }
        }
        return segments;
    }

    private void removeUri(Uri uri) {
        CacheUtil.remove(this.cache, CacheUtil.generateKey(uri));
    }
}
