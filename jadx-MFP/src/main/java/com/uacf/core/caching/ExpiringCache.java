package com.uacf.core.caching;

public class ExpiringCache<T> extends DelegatingCache<T> {
    private int itemExpirationTime = 86400000;

    public ExpiringCache(Cache<T> cache) {
        super(cache);
    }

    public int getItemExpirationTime() {
        return this.itemExpirationTime;
    }

    public ExpiringCache<T> setItemExpirationTime(int i) {
        this.itemExpirationTime = i;
        return this;
    }

    public boolean contains(String str) {
        return !purgeStaleItem(str) && super.contains(str);
    }

    public T get(String str) {
        if (purgeStaleItem(str)) {
            return null;
        }
        return super.get(str);
    }

    public void put(String str, T t) {
        super.put(str, t);
        putMetadata(CacheKeyBuilder.getExpirationKeyFrom(str), System.currentTimeMillis());
    }

    /* access modifiers changed from: protected */
    public boolean purgeStaleItem(String str) {
        String expirationKeyFrom = CacheKeyBuilder.getExpirationKeyFrom(str);
        boolean z = System.currentTimeMillis() - getMetadata(expirationKeyFrom, Long.MAX_VALUE) > ((long) getItemExpirationTime());
        if (z) {
            remove(str);
            remove(expirationKeyFrom);
        }
        return z;
    }
}
