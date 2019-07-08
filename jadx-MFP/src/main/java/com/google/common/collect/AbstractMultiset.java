package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset.Entry;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
abstract class AbstractMultiset<E> extends AbstractCollection<E> implements Multiset<E> {
    @MonotonicNonNullDecl
    private transient Set<E> elementSet;
    @MonotonicNonNullDecl
    private transient Set<Entry<E>> entrySet;

    class ElementSet extends ElementSet<E> {
        ElementSet() {
        }

        /* access modifiers changed from: 0000 */
        public Multiset<E> multiset() {
            return AbstractMultiset.this;
        }

        public Iterator<E> iterator() {
            return AbstractMultiset.this.elementIterator();
        }
    }

    class EntrySet extends EntrySet<E> {
        EntrySet() {
        }

        /* access modifiers changed from: 0000 */
        public Multiset<E> multiset() {
            return AbstractMultiset.this;
        }

        public Iterator<Entry<E>> iterator() {
            return AbstractMultiset.this.entryIterator();
        }

        public int size() {
            return AbstractMultiset.this.distinctElements();
        }
    }

    public abstract void clear();

    /* access modifiers changed from: 0000 */
    public abstract int distinctElements();

    /* access modifiers changed from: 0000 */
    public abstract Iterator<E> elementIterator();

    /* access modifiers changed from: 0000 */
    public abstract Iterator<Entry<E>> entryIterator();

    AbstractMultiset() {
    }

    public boolean isEmpty() {
        return entrySet().isEmpty();
    }

    public boolean contains(@NullableDecl Object obj) {
        return count(obj) > 0;
    }

    @CanIgnoreReturnValue
    public final boolean add(@NullableDecl E e) {
        add(e, 1);
        return true;
    }

    @CanIgnoreReturnValue
    public int add(@NullableDecl E e, int i) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    public final boolean remove(@NullableDecl Object obj) {
        return remove(obj, 1) > 0;
    }

    @CanIgnoreReturnValue
    public int remove(@NullableDecl Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    public int setCount(@NullableDecl E e, int i) {
        return Multisets.setCountImpl(this, e, i);
    }

    @CanIgnoreReturnValue
    public boolean setCount(@NullableDecl E e, int i, int i2) {
        return Multisets.setCountImpl(this, e, i, i2);
    }

    @CanIgnoreReturnValue
    public final boolean addAll(Collection<? extends E> collection) {
        return Multisets.addAllImpl((Multiset<E>) this, collection);
    }

    @CanIgnoreReturnValue
    public final boolean removeAll(Collection<?> collection) {
        return Multisets.removeAllImpl(this, collection);
    }

    @CanIgnoreReturnValue
    public final boolean retainAll(Collection<?> collection) {
        return Multisets.retainAllImpl(this, collection);
    }

    public Set<E> elementSet() {
        Set<E> set = this.elementSet;
        if (set != null) {
            return set;
        }
        Set<E> createElementSet = createElementSet();
        this.elementSet = createElementSet;
        return createElementSet;
    }

    /* access modifiers changed from: 0000 */
    public Set<E> createElementSet() {
        return new ElementSet();
    }

    public Set<Entry<E>> entrySet() {
        Set<Entry<E>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        Set<Entry<E>> createEntrySet = createEntrySet();
        this.entrySet = createEntrySet;
        return createEntrySet;
    }

    /* access modifiers changed from: 0000 */
    public Set<Entry<E>> createEntrySet() {
        return new EntrySet();
    }

    public final boolean equals(@NullableDecl Object obj) {
        return Multisets.equalsImpl(this, obj);
    }

    public final int hashCode() {
        return entrySet().hashCode();
    }

    public final String toString() {
        return entrySet().toString();
    }
}
