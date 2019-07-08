package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;

@GwtCompatible
final class WellBehavedMap<K, V> extends ForwardingMap<K, V> {
    private final Map<K, V> delegate;
    @MonotonicNonNullDecl
    private Set<Entry<K, V>> entrySet;

    private final class EntrySet extends EntrySet<K, V> {
        private EntrySet() {
        }

        /* access modifiers changed from: 0000 */
        public Map<K, V> map() {
            return WellBehavedMap.this;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new TransformedIterator<K, Entry<K, V>>(WellBehavedMap.this.keySet().iterator()) {
                /* access modifiers changed from: 0000 */
                public Entry<K, V> transform(final K k) {
                    return new AbstractMapEntry<K, V>() {
                        public K getKey() {
                            return k;
                        }

                        public V getValue() {
                            return WellBehavedMap.this.get(k);
                        }

                        public V setValue(V v) {
                            return WellBehavedMap.this.put(k, v);
                        }
                    };
                }
            };
        }
    }

    private WellBehavedMap(Map<K, V> map) {
        this.delegate = map;
    }

    static <K, V> WellBehavedMap<K, V> wrap(Map<K, V> map) {
        return new WellBehavedMap<>(map);
    }

    /* access modifiers changed from: protected */
    public Map<K, V> delegate() {
        return this.delegate;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }
}
