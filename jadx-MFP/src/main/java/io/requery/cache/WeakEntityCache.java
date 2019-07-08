package io.requery.cache;

import io.requery.EntityCache;
import io.requery.util.Objects;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class WeakEntityCache implements EntityCache {
    private final Map<Class<?>, WeakReferenceMap<?>> maps = new HashMap();

    private static class KeyReference<S> extends WeakReference<S> {
        private final Object key;

        KeyReference(Object obj, S s, ReferenceQueue<S> referenceQueue) {
            super(s, referenceQueue);
            Objects.requireNotNull(obj);
            Objects.requireNotNull(s);
            this.key = obj;
        }

        /* access modifiers changed from: 0000 */
        public Object getKey() {
            return this.key;
        }
    }

    private static class WeakReferenceMap<T> extends HashMap<Object, Reference<T>> {
        private final ReferenceQueue<T> referenceQueue;

        private WeakReferenceMap() {
            this.referenceQueue = new ReferenceQueue<>();
        }

        public T getValue(Object obj) {
            removeStaleEntries();
            Reference reference = (Reference) get(obj);
            if (reference == null) {
                return null;
            }
            return reference.get();
        }

        public void putValue(Object obj, T t) {
            removeStaleEntries();
            put(obj, new KeyReference(obj, t, this.referenceQueue));
        }

        private void removeStaleEntries() {
            while (true) {
                Reference poll = this.referenceQueue.poll();
                if (poll == null) {
                    return;
                }
                if (poll.get() == null) {
                    remove(((KeyReference) poll).getKey());
                }
            }
        }
    }

    public <T> T get(Class<T> cls, Object obj) {
        synchronized (this.maps) {
            WeakReferenceMap weakReferenceMap = (WeakReferenceMap) this.maps.get(cls);
            if (weakReferenceMap == null) {
                return null;
            }
            T cast = cls.cast(weakReferenceMap.getValue(obj));
            return cast;
        }
    }

    public <T> void put(Class<T> cls, Object obj, T t) {
        Objects.requireNotNull(cls);
        synchronized (this.maps) {
            WeakReferenceMap weakReferenceMap = (WeakReferenceMap) this.maps.get(cls);
            if (weakReferenceMap == null) {
                Map<Class<?>, WeakReferenceMap<?>> map = this.maps;
                WeakReferenceMap weakReferenceMap2 = new WeakReferenceMap();
                map.put(cls, weakReferenceMap2);
                weakReferenceMap = weakReferenceMap2;
            }
            weakReferenceMap.putValue(obj, t);
        }
    }

    public void invalidate(Class<?> cls, Object obj) {
        synchronized (this.maps) {
            WeakReferenceMap weakReferenceMap = (WeakReferenceMap) this.maps.get(cls);
            if (weakReferenceMap != null) {
                weakReferenceMap.remove(obj);
            }
        }
    }

    public void clear() {
        synchronized (this.maps) {
            this.maps.clear();
        }
    }
}
