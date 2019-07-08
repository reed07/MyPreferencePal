package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
abstract class ImmutableMapEntrySet<K, V> extends ImmutableSet<Entry<K, V>> {

    @GwtIncompatible
    private static class EntrySetSerializedForm<K, V> implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap<K, V> map;

        EntrySetSerializedForm(ImmutableMap<K, V> immutableMap) {
            this.map = immutableMap;
        }

        /* access modifiers changed from: 0000 */
        public Object readResolve() {
            return this.map.entrySet();
        }
    }

    static final class RegularEntrySet<K, V> extends ImmutableMapEntrySet<K, V> {
        private final transient ImmutableList<Entry<K, V>> entries;
        @Weak
        private final transient ImmutableMap<K, V> map;

        RegularEntrySet(ImmutableMap<K, V> immutableMap, Entry<K, V>[] entryArr) {
            this(immutableMap, ImmutableList.asImmutableList(entryArr));
        }

        RegularEntrySet(ImmutableMap<K, V> immutableMap, ImmutableList<Entry<K, V>> immutableList) {
            this.map = immutableMap;
            this.entries = immutableList;
        }

        /* access modifiers changed from: 0000 */
        public ImmutableMap<K, V> map() {
            return this.map;
        }

        /* access modifiers changed from: 0000 */
        @GwtIncompatible("not used in GWT")
        public int copyIntoArray(Object[] objArr, int i) {
            return this.entries.copyIntoArray(objArr, i);
        }

        public UnmodifiableIterator<Entry<K, V>> iterator() {
            return this.entries.iterator();
        }

        /* access modifiers changed from: 0000 */
        public ImmutableList<Entry<K, V>> createAsList() {
            return this.entries;
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract ImmutableMap<K, V> map();

    ImmutableMapEntrySet() {
    }

    public int size() {
        return map().size();
    }

    public boolean contains(@NullableDecl Object obj) {
        boolean z = false;
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        Object obj2 = map().get(entry.getKey());
        if (obj2 != null && obj2.equals(entry.getValue())) {
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public boolean isPartialView() {
        return map().isPartialView();
    }

    /* access modifiers changed from: 0000 */
    @GwtIncompatible
    public boolean isHashCodeFast() {
        return map().isHashCodeFast();
    }

    public int hashCode() {
        return map().hashCode();
    }

    /* access modifiers changed from: 0000 */
    @GwtIncompatible
    public Object writeReplace() {
        return new EntrySetSerializedForm(map());
    }
}
