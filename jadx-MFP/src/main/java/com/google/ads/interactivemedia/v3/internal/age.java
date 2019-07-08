package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

/* compiled from: IMASDK */
public abstract class age<K, V> implements Serializable, Map<K, V> {
    private transient agg<Entry<K, V>> a;
    private transient agg<K> b;
    private transient aga<V> c;

    public static <K, V> age<K, V> a(Map<? extends K, ? extends V> map) {
        if (!(map instanceof age) || (map instanceof SortedMap)) {
            Set entrySet = map.entrySet();
            agj agj = new agj(entrySet instanceof Collection ? entrySet.size() : 4);
            agj.a((Iterable<? extends Entry<? extends K, ? extends V>>) entrySet);
            return agj.a();
        }
        age<K, V> age = (age) map;
        age.e();
        return age;
    }

    /* access modifiers changed from: 0000 */
    public abstract agg<Entry<K, V>> b();

    /* access modifiers changed from: 0000 */
    public abstract agg<K> c();

    /* access modifiers changed from: 0000 */
    public abstract aga<V> d();

    /* access modifiers changed from: 0000 */
    public abstract boolean e();

    public abstract V get(Object obj);

    age() {
    }

    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    public boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    public final V getOrDefault(Object obj, V v) {
        V v2 = get(obj);
        return v2 != null ? v2 : v;
    }

    /* renamed from: a */
    public final agg<Entry<K, V>> entrySet() {
        agg<Entry<K, V>> agg = this.a;
        if (agg != null) {
            return agg;
        }
        agg<Entry<K, V>> b2 = b();
        this.a = b2;
        return b2;
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public final aga<V> values() {
        aga<V> aga = this.c;
        if (aga != null) {
            return aga;
        }
        aga<V> d = d();
        this.c = d;
        return d;
    }

    public boolean equals(Object obj) {
        return agj.a((Map<?, ?>) this, obj);
    }

    public int hashCode() {
        return agk.a(entrySet());
    }

    public String toString() {
        return agj.a((Map<?, ?>) this);
    }

    /* access modifiers changed from: 0000 */
    public Object writeReplace() {
        return new agf(this);
    }

    public /* synthetic */ Set keySet() {
        agg<K> agg = this.b;
        if (agg != null) {
            return agg;
        }
        agg<K> c2 = c();
        this.b = c2;
        return c2;
    }
}
