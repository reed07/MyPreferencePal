package io.requery.cache;

import io.requery.EntityCache;

public class EmptyEntityCache implements EntityCache {
    public void clear() {
    }

    public <T> T get(Class<T> cls, Object obj) {
        return null;
    }

    public void invalidate(Class<?> cls, Object obj) {
    }

    public <T> void put(Class<T> cls, Object obj, T t) {
    }
}
