package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
final class RegularImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    static final RegularImmutableBiMap<Object, Object> EMPTY = new RegularImmutableBiMap<>();
    @VisibleForTesting
    final transient Object[] alternatingKeysAndValues;
    private final transient RegularImmutableBiMap<V, K> inverse;
    private final transient int[] keyHashTable;
    private final transient int keyOffset;
    private final transient int size;

    /* access modifiers changed from: 0000 */
    public boolean isPartialView() {
        return false;
    }

    private RegularImmutableBiMap() {
        this.keyHashTable = null;
        this.alternatingKeysAndValues = new Object[0];
        this.keyOffset = 0;
        this.size = 0;
        this.inverse = this;
    }

    RegularImmutableBiMap(Object[] objArr, int i) {
        this.alternatingKeysAndValues = objArr;
        this.size = i;
        this.keyOffset = 0;
        int chooseTableSize = i >= 2 ? ImmutableSet.chooseTableSize(i) : 0;
        this.keyHashTable = RegularImmutableMap.createHashTable(objArr, i, chooseTableSize, 0);
        this.inverse = new RegularImmutableBiMap<>(RegularImmutableMap.createHashTable(objArr, i, chooseTableSize, 1), objArr, i, this);
    }

    private RegularImmutableBiMap(int[] iArr, Object[] objArr, int i, RegularImmutableBiMap<V, K> regularImmutableBiMap) {
        this.keyHashTable = iArr;
        this.alternatingKeysAndValues = objArr;
        this.keyOffset = 1;
        this.size = i;
        this.inverse = regularImmutableBiMap;
    }

    public int size() {
        return this.size;
    }

    public ImmutableBiMap<V, K> inverse() {
        return this.inverse;
    }

    public V get(@NullableDecl Object obj) {
        return RegularImmutableMap.get(this.keyHashTable, this.alternatingKeysAndValues, this.size, this.keyOffset, obj);
    }

    /* access modifiers changed from: 0000 */
    public ImmutableSet<Entry<K, V>> createEntrySet() {
        return new EntrySet(this, this.alternatingKeysAndValues, this.keyOffset, this.size);
    }

    /* access modifiers changed from: 0000 */
    public ImmutableSet<K> createKeySet() {
        return new KeySet(this, new KeysOrValuesAsList(this.alternatingKeysAndValues, this.keyOffset, this.size));
    }
}
