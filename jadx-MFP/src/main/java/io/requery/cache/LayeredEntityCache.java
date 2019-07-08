package io.requery.cache;

import io.requery.EntityCache;
import java.util.List;

public class LayeredEntityCache implements EntityCache {
    private final List<EntityCache> caches;

    public <T> T get(Class<T> cls, Object obj) {
        for (EntityCache entityCache : this.caches) {
            T t = entityCache.get(cls, obj);
            if (t != null) {
                return t;
            }
        }
        return null;
    }

    public <T> void put(Class<T> cls, Object obj, T t) {
        for (EntityCache put : this.caches) {
            put.put(cls, obj, t);
        }
    }

    public void invalidate(Class<?> cls, Object obj) {
        for (EntityCache invalidate : this.caches) {
            invalidate.invalidate(cls, obj);
        }
    }

    public void clear() {
        for (EntityCache clear : this.caches) {
            clear.clear();
        }
    }
}
