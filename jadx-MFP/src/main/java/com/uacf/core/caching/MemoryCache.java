package com.uacf.core.caching;

import com.uacf.core.util.Ln;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryCache<T> extends DelegatingCache<T> {
    private Map<String, T> map = new ConcurrentHashMap();
    private Map<String, Long> metadataMap = new ConcurrentHashMap();

    public MemoryCache(Cache<T> cache) {
        super(cache);
    }

    public boolean contains(String str) {
        return this.map.containsKey(str) || super.contains(str);
    }

    public T get(String str) {
        if (this.map.containsKey(str)) {
            return this.map.get(str);
        }
        if (!super.contains(str)) {
            return null;
        }
        T t = super.get(str);
        if (t == null) {
            Ln.r("IDM1497: item for key %s is null", str);
            return null;
        }
        this.map.put(str, t);
        return t;
    }

    public long getMetadata(String str, long j) {
        return this.metadataMap.containsKey(str) ? ((Long) this.metadataMap.get(str)).longValue() : j;
    }

    public void put(String str, T t) {
        this.map.put(str, t);
        super.put(str, t);
    }

    public void putMetadata(String str, long j) {
        this.metadataMap.put(str, Long.valueOf(j));
        super.putMetadata(str, j);
    }

    public void clear() {
        this.map.clear();
        this.metadataMap.clear();
        super.clear();
    }

    public void remove(String str) {
        if (this.map.containsKey(str)) {
            this.map.remove(str);
        }
        super.remove(str);
    }

    public void flush() {
        super.flush();
        for (Entry entry : this.map.entrySet()) {
            super.put((String) entry.getKey(), entry.getValue());
        }
        for (Entry entry2 : this.metadataMap.entrySet()) {
            super.putMetadata((String) entry2.getKey(), ((Long) entry2.getValue()).longValue());
        }
    }

    public Map<String, T> allItems() {
        flush();
        return super.allItems();
    }
}
