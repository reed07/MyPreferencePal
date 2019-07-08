package com.google.android.exoplayer2.upstream.cache;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceException;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.TeeDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.upstream.cache.Cache.CacheException;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class CacheDataSource implements DataSource {
    public static final int CACHE_IGNORED_REASON_ERROR = 0;
    public static final int CACHE_IGNORED_REASON_UNSET_LENGTH = 1;
    private static final int CACHE_NOT_IGNORED = -1;
    public static final long DEFAULT_MAX_CACHE_FILE_SIZE = 2097152;
    public static final int FLAG_BLOCK_ON_CACHE = 1;
    public static final int FLAG_IGNORE_CACHE_FOR_UNSET_LENGTH_REQUESTS = 4;
    public static final int FLAG_IGNORE_CACHE_ON_ERROR = 2;
    private static final long MIN_READ_BEFORE_CHECKING_CACHE = 102400;
    @Nullable
    private Uri actualUri;
    private final boolean blockOnCache;
    private long bytesRemaining;
    private final Cache cache;
    private final CacheKeyFactory cacheKeyFactory;
    private final DataSource cacheReadDataSource;
    @Nullable
    private final DataSource cacheWriteDataSource;
    private long checkCachePosition;
    @Nullable
    private DataSource currentDataSource;
    private boolean currentDataSpecLengthUnset;
    @Nullable
    private CacheSpan currentHoleSpan;
    private boolean currentRequestIgnoresCache;
    @Nullable
    private final EventListener eventListener;
    private int flags;
    private int httpMethod;
    private final boolean ignoreCacheForUnsetLengthRequests;
    private final boolean ignoreCacheOnError;
    @Nullable
    private String key;
    private long readPosition;
    private boolean seenCacheError;
    private long totalCachedBytesRead;
    private final DataSource upstreamDataSource;
    @Nullable
    private Uri uri;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface CacheIgnoredReason {
    }

    public interface EventListener {
        void onCacheIgnored(int i);

        void onCachedBytesRead(long j, long j2);
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public CacheDataSource(Cache cache2, DataSource dataSource) {
        this(cache2, dataSource, 0, DEFAULT_MAX_CACHE_FILE_SIZE);
    }

    public CacheDataSource(Cache cache2, DataSource dataSource, int i) {
        this(cache2, dataSource, i, DEFAULT_MAX_CACHE_FILE_SIZE);
    }

    public CacheDataSource(Cache cache2, DataSource dataSource, int i, long j) {
        this(cache2, dataSource, new FileDataSource(), new CacheDataSink(cache2, j), i, null);
    }

    public CacheDataSource(Cache cache2, DataSource dataSource, DataSource dataSource2, DataSink dataSink, int i, @Nullable EventListener eventListener2) {
        this(cache2, dataSource, dataSource2, dataSink, i, eventListener2, null);
    }

    public CacheDataSource(Cache cache2, DataSource dataSource, DataSource dataSource2, DataSink dataSink, int i, @Nullable EventListener eventListener2, @Nullable CacheKeyFactory cacheKeyFactory2) {
        this.cache = cache2;
        this.cacheReadDataSource = dataSource2;
        if (cacheKeyFactory2 == null) {
            cacheKeyFactory2 = CacheUtil.DEFAULT_CACHE_KEY_FACTORY;
        }
        this.cacheKeyFactory = cacheKeyFactory2;
        boolean z = false;
        this.blockOnCache = (i & 1) != 0;
        this.ignoreCacheOnError = (i & 2) != 0;
        if ((i & 4) != 0) {
            z = true;
        }
        this.ignoreCacheForUnsetLengthRequests = z;
        this.upstreamDataSource = dataSource;
        if (dataSink != null) {
            this.cacheWriteDataSource = new TeeDataSource(dataSource, dataSink);
        } else {
            this.cacheWriteDataSource = null;
        }
        this.eventListener = eventListener2;
    }

    public void addTransferListener(TransferListener transferListener) {
        this.cacheReadDataSource.addTransferListener(transferListener);
        this.upstreamDataSource.addTransferListener(transferListener);
    }

    public long open(DataSpec dataSpec) throws IOException {
        try {
            this.key = this.cacheKeyFactory.buildCacheKey(dataSpec);
            this.uri = dataSpec.uri;
            this.actualUri = getRedirectedUriOrDefault(this.cache, this.key, this.uri);
            this.httpMethod = dataSpec.httpMethod;
            this.flags = dataSpec.flags;
            this.readPosition = dataSpec.position;
            int shouldIgnoreCacheForRequest = shouldIgnoreCacheForRequest(dataSpec);
            this.currentRequestIgnoresCache = shouldIgnoreCacheForRequest != -1;
            if (this.currentRequestIgnoresCache) {
                notifyCacheIgnored(shouldIgnoreCacheForRequest);
            }
            if (dataSpec.length == -1) {
                if (!this.currentRequestIgnoresCache) {
                    this.bytesRemaining = this.cache.getContentLength(this.key);
                    if (this.bytesRemaining != -1) {
                        this.bytesRemaining -= dataSpec.position;
                        if (this.bytesRemaining <= 0) {
                            throw new DataSourceException(0);
                        }
                    }
                    openNextSource(false);
                    return this.bytesRemaining;
                }
            }
            this.bytesRemaining = dataSpec.length;
            openNextSource(false);
            return this.bytesRemaining;
        } catch (IOException e) {
            handleBeforeThrow(e);
            throw e;
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        try {
            if (this.readPosition >= this.checkCachePosition) {
                openNextSource(true);
            }
            int read = this.currentDataSource.read(bArr, i, i2);
            if (read != -1) {
                if (isReadingFromCache()) {
                    this.totalCachedBytesRead += (long) read;
                }
                long j = (long) read;
                this.readPosition += j;
                if (this.bytesRemaining != -1) {
                    this.bytesRemaining -= j;
                }
            } else if (this.currentDataSpecLengthUnset) {
                setNoBytesRemainingAndMaybeStoreLength();
            } else {
                if (this.bytesRemaining <= 0) {
                    if (this.bytesRemaining == -1) {
                    }
                }
                closeCurrentSource();
                openNextSource(false);
                return read(bArr, i, i2);
            }
            return read;
        } catch (IOException e) {
            if (!this.currentDataSpecLengthUnset || !isCausedByPositionOutOfRange(e)) {
                handleBeforeThrow(e);
                throw e;
            }
            setNoBytesRemainingAndMaybeStoreLength();
            return -1;
        }
    }

    @Nullable
    public Uri getUri() {
        return this.actualUri;
    }

    public Map<String, List<String>> getResponseHeaders() {
        if (isReadingFromUpstream()) {
            return this.upstreamDataSource.getResponseHeaders();
        }
        return Collections.emptyMap();
    }

    public void close() throws IOException {
        this.uri = null;
        this.actualUri = null;
        this.httpMethod = 1;
        notifyBytesRead();
        try {
            closeCurrentSource();
        } catch (IOException e) {
            handleBeforeThrow(e);
            throw e;
        }
    }

    private void openNextSource(boolean z) throws IOException {
        CacheSpan cacheSpan;
        DataSource dataSource;
        DataSpec dataSpec;
        long j;
        CacheSpan cacheSpan2 = null;
        if (this.currentRequestIgnoresCache) {
            cacheSpan = null;
        } else if (this.blockOnCache) {
            try {
                cacheSpan = this.cache.startReadWrite(this.key, this.readPosition);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            }
        } else {
            cacheSpan = this.cache.startReadWriteNonBlocking(this.key, this.readPosition);
        }
        if (cacheSpan == null) {
            DataSource dataSource2 = this.upstreamDataSource;
            Uri uri2 = this.uri;
            int i = this.httpMethod;
            long j2 = this.readPosition;
            dataSpec = new DataSpec(uri2, i, null, j2, j2, this.bytesRemaining, this.key, this.flags);
            dataSource = dataSource2;
            cacheSpan2 = cacheSpan;
        } else if (cacheSpan.isCached) {
            Uri fromFile = Uri.fromFile(cacheSpan.file);
            long j3 = this.readPosition - cacheSpan.position;
            long j4 = cacheSpan.length - j3;
            long j5 = this.bytesRemaining;
            DataSpec dataSpec2 = new DataSpec(fromFile, this.readPosition, j3, j5 != -1 ? Math.min(j4, j5) : j4, this.key, this.flags);
            dataSource = this.cacheReadDataSource;
            dataSpec = dataSpec2;
            cacheSpan2 = cacheSpan;
        } else {
            if (cacheSpan.isOpenEnded()) {
                j = this.bytesRemaining;
            } else {
                long j6 = cacheSpan.length;
                long j7 = this.bytesRemaining;
                j = j7 != -1 ? Math.min(j6, j7) : j6;
            }
            Uri uri3 = this.uri;
            int i2 = this.httpMethod;
            long j8 = this.readPosition;
            dataSpec = new DataSpec(uri3, i2, null, j8, j8, j, this.key, this.flags);
            dataSource = this.cacheWriteDataSource;
            if (dataSource != null) {
                cacheSpan2 = cacheSpan;
            } else {
                dataSource = this.upstreamDataSource;
                this.cache.releaseHoleSpan(cacheSpan);
            }
        }
        this.checkCachePosition = (this.currentRequestIgnoresCache || dataSource != this.upstreamDataSource) ? Long.MAX_VALUE : this.readPosition + MIN_READ_BEFORE_CHECKING_CACHE;
        if (z) {
            Assertions.checkState(isBypassingCache());
            if (dataSource != this.upstreamDataSource) {
                try {
                    closeCurrentSource();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    if (cacheSpan2.isHoleSpan()) {
                        this.cache.releaseHoleSpan(cacheSpan2);
                    }
                    throw th2;
                }
            } else {
                return;
            }
        }
        if (cacheSpan2 != null && cacheSpan2.isHoleSpan()) {
            this.currentHoleSpan = cacheSpan2;
        }
        this.currentDataSource = dataSource;
        this.currentDataSpecLengthUnset = dataSpec.length == -1;
        long open = dataSource.open(dataSpec);
        ContentMetadataMutations contentMetadataMutations = new ContentMetadataMutations();
        if (this.currentDataSpecLengthUnset && open != -1) {
            this.bytesRemaining = open;
            ContentMetadataInternal.setContentLength(contentMetadataMutations, this.readPosition + this.bytesRemaining);
        }
        if (isReadingFromUpstream()) {
            this.actualUri = this.currentDataSource.getUri();
            if (true ^ this.uri.equals(this.actualUri)) {
                ContentMetadataInternal.setRedirectedUri(contentMetadataMutations, this.actualUri);
            } else {
                ContentMetadataInternal.removeRedirectedUri(contentMetadataMutations);
            }
        }
        if (isWritingToCache()) {
            this.cache.applyContentMetadataMutations(this.key, contentMetadataMutations);
        }
    }

    private void setNoBytesRemainingAndMaybeStoreLength() throws IOException {
        this.bytesRemaining = 0;
        if (isWritingToCache()) {
            this.cache.setContentLength(this.key, this.readPosition);
        }
    }

    private static Uri getRedirectedUriOrDefault(Cache cache2, String str, Uri uri2) {
        Uri redirectedUri = ContentMetadataInternal.getRedirectedUri(cache2.getContentMetadata(str));
        return redirectedUri == null ? uri2 : redirectedUri;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.io.IOException, code=java.lang.Throwable, for r1v0, types: [java.lang.Throwable, java.io.IOException] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean isCausedByPositionOutOfRange(java.lang.Throwable r1) {
        /*
        L_0x0000:
            if (r1 == 0) goto L_0x0014
            boolean r0 = r1 instanceof com.google.android.exoplayer2.upstream.DataSourceException
            if (r0 == 0) goto L_0x000f
            r0 = r1
            com.google.android.exoplayer2.upstream.DataSourceException r0 = (com.google.android.exoplayer2.upstream.DataSourceException) r0
            int r0 = r0.reason
            if (r0 != 0) goto L_0x000f
            r1 = 1
            return r1
        L_0x000f:
            java.lang.Throwable r1 = r1.getCause()
            goto L_0x0000
        L_0x0014:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.cache.CacheDataSource.isCausedByPositionOutOfRange(java.io.IOException):boolean");
    }

    private boolean isReadingFromUpstream() {
        return !isReadingFromCache();
    }

    private boolean isBypassingCache() {
        return this.currentDataSource == this.upstreamDataSource;
    }

    private boolean isReadingFromCache() {
        return this.currentDataSource == this.cacheReadDataSource;
    }

    private boolean isWritingToCache() {
        return this.currentDataSource == this.cacheWriteDataSource;
    }

    private void closeCurrentSource() throws IOException {
        DataSource dataSource = this.currentDataSource;
        if (dataSource != null) {
            try {
                dataSource.close();
            } finally {
                this.currentDataSource = null;
                this.currentDataSpecLengthUnset = false;
                CacheSpan cacheSpan = this.currentHoleSpan;
                if (cacheSpan != null) {
                    this.cache.releaseHoleSpan(cacheSpan);
                    this.currentHoleSpan = null;
                }
            }
        }
    }

    private void handleBeforeThrow(IOException iOException) {
        if (isReadingFromCache() || (iOException instanceof CacheException)) {
            this.seenCacheError = true;
        }
    }

    private int shouldIgnoreCacheForRequest(DataSpec dataSpec) {
        if (!this.ignoreCacheOnError || !this.seenCacheError) {
            return (!this.ignoreCacheForUnsetLengthRequests || dataSpec.length != -1) ? -1 : 1;
        }
        return 0;
    }

    private void notifyCacheIgnored(int i) {
        EventListener eventListener2 = this.eventListener;
        if (eventListener2 != null) {
            eventListener2.onCacheIgnored(i);
        }
    }

    private void notifyBytesRead() {
        EventListener eventListener2 = this.eventListener;
        if (eventListener2 != null && this.totalCachedBytesRead > 0) {
            eventListener2.onCachedBytesRead(this.cache.getCacheSpace(), this.totalCachedBytesRead);
            this.totalCachedBytesRead = 0;
        }
    }
}
