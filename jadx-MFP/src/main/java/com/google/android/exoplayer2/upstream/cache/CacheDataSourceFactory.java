package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.FileDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource.EventListener;

public final class CacheDataSourceFactory implements Factory {
    private final Cache cache;
    private final Factory cacheReadDataSourceFactory;
    private final DataSink.Factory cacheWriteDataSinkFactory;
    private final EventListener eventListener;
    private final int flags;
    private final Factory upstreamFactory;

    public CacheDataSourceFactory(Cache cache2, Factory factory) {
        this(cache2, factory, 0);
    }

    public CacheDataSourceFactory(Cache cache2, Factory factory, int i) {
        this(cache2, factory, i, CacheDataSource.DEFAULT_MAX_CACHE_FILE_SIZE);
    }

    public CacheDataSourceFactory(Cache cache2, Factory factory, int i, long j) {
        this(cache2, factory, new FileDataSourceFactory(), new CacheDataSinkFactory(cache2, j), i, null);
    }

    public CacheDataSourceFactory(Cache cache2, Factory factory, Factory factory2, DataSink.Factory factory3, int i, EventListener eventListener2) {
        this.cache = cache2;
        this.upstreamFactory = factory;
        this.cacheReadDataSourceFactory = factory2;
        this.cacheWriteDataSinkFactory = factory3;
        this.flags = i;
        this.eventListener = eventListener2;
    }

    public CacheDataSource createDataSource() {
        Cache cache2 = this.cache;
        DataSource createDataSource = this.upstreamFactory.createDataSource();
        DataSource createDataSource2 = this.cacheReadDataSourceFactory.createDataSource();
        DataSink.Factory factory = this.cacheWriteDataSinkFactory;
        CacheDataSource cacheDataSource = new CacheDataSource(cache2, createDataSource, createDataSource2, factory != null ? factory.createDataSink() : null, this.flags, this.eventListener);
        return cacheDataSource;
    }
}
