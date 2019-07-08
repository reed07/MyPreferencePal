package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
final class ImmutableMapValues<K, V> extends ImmutableCollection<V> {
    /* access modifiers changed from: private */
    @Weak
    public final ImmutableMap<K, V> map;

    @GwtIncompatible
    private static class SerializedForm<V> implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap<?, V> map;

        SerializedForm(ImmutableMap<?, V> immutableMap) {
            this.map = immutableMap;
        }

        /* access modifiers changed from: 0000 */
        public Object readResolve() {
            return this.map.values();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isPartialView() {
        return true;
    }

    ImmutableMapValues(ImmutableMap<K, V> immutableMap) {
        this.map = immutableMap;
    }

    public int size() {
        return this.map.size();
    }

    public UnmodifiableIterator<V> iterator() {
        return new UnmodifiableIterator<V>() {
            final UnmodifiableIterator<Entry<K, V>> entryItr = ImmutableMapValues.this.map.entrySet().iterator();

            public boolean hasNext() {
                return this.entryItr.hasNext();
            }

            public V next() {
                return ((Entry) this.entryItr.next()).getValue();
            }
        };
    }

    public boolean contains(@NullableDecl Object obj) {
        return obj != null && Iterators.contains(iterator(), obj);
    }

    public ImmutableList<V> asList() {
        final ImmutableList asList = this.map.entrySet().asList();
        return new ImmutableList<V>() {
            /* access modifiers changed from: 0000 */
            public boolean isPartialView() {
                return true;
            }

            public V get(int i) {
                return ((Entry) asList.get(i)).getValue();
            }

            public int size() {
                return asList.size();
            }
        };
    }

    /* access modifiers changed from: 0000 */
    @GwtIncompatible
    public Object writeReplace() {
        return new SerializedForm(this.map);
    }
}
