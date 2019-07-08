package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.cache.Cache.CacheException;
import java.util.Comparator;
import java.util.TreeSet;

public final class LeastRecentlyUsedCacheEvictor implements CacheEvictor, Comparator<CacheSpan> {
    private long currentSize;
    private final TreeSet<CacheSpan> leastRecentlyUsed = new TreeSet<>(this);
    private final long maxBytes;

    public void onCacheInitialized() {
    }

    public LeastRecentlyUsedCacheEvictor(long j) {
        this.maxBytes = j;
    }

    public void onStartFile(Cache cache, String str, long j, long j2) {
        evictCache(cache, j2);
    }

    public void onSpanAdded(Cache cache, CacheSpan cacheSpan) {
        this.leastRecentlyUsed.add(cacheSpan);
        this.currentSize += cacheSpan.length;
        evictCache(cache, 0);
    }

    public void onSpanRemoved(Cache cache, CacheSpan cacheSpan) {
        this.leastRecentlyUsed.remove(cacheSpan);
        this.currentSize -= cacheSpan.length;
    }

    public void onSpanTouched(Cache cache, CacheSpan cacheSpan, CacheSpan cacheSpan2) {
        onSpanRemoved(cache, cacheSpan);
        onSpanAdded(cache, cacheSpan2);
    }

    public int compare(CacheSpan cacheSpan, CacheSpan cacheSpan2) {
        if (cacheSpan.lastAccessTimestamp - cacheSpan2.lastAccessTimestamp == 0) {
            return cacheSpan.compareTo(cacheSpan2);
        }
        return cacheSpan.lastAccessTimestamp < cacheSpan2.lastAccessTimestamp ? -1 : 1;
    }

    private void evictCache(Cache cache, long j) {
        while (this.currentSize + j > this.maxBytes && !this.leastRecentlyUsed.isEmpty()) {
            try {
                cache.removeSpan((CacheSpan) this.leastRecentlyUsed.first());
            } catch (CacheException unused) {
            }
        }
    }
}
