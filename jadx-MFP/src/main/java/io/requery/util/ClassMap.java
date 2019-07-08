package io.requery.util;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nonnull;

public class ClassMap<V> implements Map<Class<?>, V> {
    private Class[] keys;
    private final IdentityHashMap<Class<?>, V> map = new IdentityHashMap<>();

    private Class<?> findKey(Class<?> cls) {
        Class<?>[] clsArr;
        Class<?>[] clsArr2;
        if (this.keys == null) {
            Set keySet = keySet();
            this.keys = (Class[]) keySet.toArray(new Class[keySet.size()]);
        }
        for (Class<?> cls2 : this.keys) {
            if (cls2 == cls) {
                return cls2;
            }
        }
        for (Class<?> cls3 : this.keys) {
            if (cls3.isAssignableFrom(cls)) {
                return cls3;
            }
        }
        return null;
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public boolean containsKey(Object obj) {
        return this.map.containsKey(findKey((Class) obj));
    }

    public boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    public V get(Object obj) {
        return this.map.get(findKey((Class) obj));
    }

    public V put(Class<?> cls, V v) {
        if (cls != null) {
            this.keys = null;
            return this.map.put(cls, v);
        }
        throw new IllegalArgumentException();
    }

    public V remove(Object obj) {
        return this.map.remove(obj);
    }

    public void putAll(@Nonnull Map<? extends Class<?>, ? extends V> map2) {
        for (Entry entry : map2.entrySet()) {
            put((Class) entry.getKey(), (V) entry.getValue());
        }
    }

    public void clear() {
        this.map.clear();
    }

    @Nonnull
    public Set<Class<?>> keySet() {
        return this.map.keySet();
    }

    @Nonnull
    public Collection<V> values() {
        return this.map.values();
    }

    @Nonnull
    public Set<Entry<Class<?>, V>> entrySet() {
        return this.map.entrySet();
    }
}
