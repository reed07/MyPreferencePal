package com.uacf.core.caching;

import android.text.TextUtils;
import android.util.Log;
import com.uacf.core.mapping.Mapper2;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LruCache<T> implements Cache<T> {
    private android.util.LruCache<String, T> memoryCache;
    private Map<String, Long> metadataMap;

    public LruCache() {
        this(20);
    }

    public LruCache(int i) {
        this.memoryCache = new android.util.LruCache<>(i);
        this.metadataMap = new ConcurrentHashMap();
    }

    public Cache<T> withMapper(Mapper2 mapper2) {
        throw new UnsupportedOperationException();
    }

    public void put(String str, T t) {
        if (TextUtils.isEmpty(str)) {
            Log.d("LruCache", "Key value must not be empty or null");
        } else if (t == null) {
            Log.d("LruCache", "Item must not be null");
        } else {
            android.util.LruCache<String, T> lruCache = this.memoryCache;
            if (lruCache != null) {
                lruCache.put(str, t);
            }
        }
    }

    public T get(String str) {
        android.util.LruCache<String, T> lruCache = this.memoryCache;
        if (lruCache != null) {
            return lruCache.get(str);
        }
        return null;
    }

    public void remove(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.d("LruCache", "Key value must not be empty or null");
            return;
        }
        android.util.LruCache<String, T> lruCache = this.memoryCache;
        if (lruCache != null) {
            lruCache.remove(str);
        }
    }

    public boolean contains(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.d("LruCache", "Key value must not be empty or null");
            return false;
        }
        android.util.LruCache<String, T> lruCache = this.memoryCache;
        if (lruCache != null) {
            return lruCache.snapshot().containsKey(str);
        }
        return false;
    }

    public long getMetadata(String str, long j) {
        return ((Long) this.metadataMap.get(str)).longValue();
    }

    public void putMetadata(String str, long j) {
        this.metadataMap.put(str, Long.valueOf(j));
    }

    public void clear() {
        android.util.LruCache<String, T> lruCache = this.memoryCache;
        if (lruCache != null) {
            lruCache.evictAll();
        }
    }

    public Map<String, T> allItems() {
        android.util.LruCache<String, T> lruCache = this.memoryCache;
        if (lruCache != null) {
            return lruCache.snapshot();
        }
        return new HashMap();
    }

    public void flush() {
        android.util.LruCache<String, T> lruCache = this.memoryCache;
        if (lruCache != null) {
            lruCache.evictAll();
        }
    }
}
