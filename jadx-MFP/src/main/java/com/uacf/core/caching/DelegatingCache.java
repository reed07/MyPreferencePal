package com.uacf.core.caching;

import com.uacf.core.mapping.Mapper2;
import java.util.HashMap;
import java.util.Map;

public abstract class DelegatingCache<T> implements Cache<T> {
    private final Cache<T> delegate;

    protected DelegatingCache(Cache<T> cache) {
        this.delegate = cache;
    }

    public Cache<T> withMapper(Mapper2 mapper2) {
        Cache<T> cache = this.delegate;
        if (cache != null) {
            cache.withMapper(mapper2);
        }
        return this;
    }

    public void put(String str, T t) {
        Cache<T> cache = this.delegate;
        if (cache != null) {
            cache.put(str, t);
        }
    }

    public T get(String str) {
        if (this.delegate == null) {
            return null;
        }
        if (contains(str)) {
            return this.delegate.get(str);
        }
        remove(str);
        return null;
    }

    public void remove(String str) {
        Cache<T> cache = this.delegate;
        if (cache != null) {
            cache.remove(str);
        }
    }

    public boolean contains(String str) {
        Cache<T> cache = this.delegate;
        return cache != null && cache.contains(str);
    }

    public long getMetadata(String str, long j) {
        Cache<T> cache = this.delegate;
        return cache != null ? cache.getMetadata(str, j) : j;
    }

    public void putMetadata(String str, long j) {
        Cache<T> cache = this.delegate;
        if (cache != null) {
            cache.putMetadata(str, j);
        }
    }

    public void clear() {
        Cache<T> cache = this.delegate;
        if (cache != null) {
            cache.clear();
        }
    }

    public Map<String, T> allItems() {
        Cache<T> cache = this.delegate;
        return cache != null ? cache.allItems() : new HashMap();
    }

    public void flush() {
        Cache<T> cache = this.delegate;
        if (cache != null) {
            cache.flush();
        }
    }
}
