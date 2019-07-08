package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSink.Factory;

public final class CacheDataSinkFactory implements Factory {
    private final int bufferSize;
    private final Cache cache;
    private final long maxCacheFileSize;

    public CacheDataSinkFactory(Cache cache2, long j) {
        this(cache2, j, CacheDataSink.DEFAULT_BUFFER_SIZE);
    }

    public CacheDataSinkFactory(Cache cache2, long j, int i) {
        this.cache = cache2;
        this.maxCacheFileSize = j;
        this.bufferSize = i;
    }

    public DataSink createDataSink() {
        return new CacheDataSink(this.cache, this.maxCacheFileSize, this.bufferSize);
    }
}
