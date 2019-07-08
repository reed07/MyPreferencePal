package io.requery.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nonnull;

public class ObservableSet<E> implements ObservableCollection<E>, Set<E> {
    private final CollectionObserver<E> observer;
    private final Set<E> set;

    public ObservableSet(Set<E> set2, CollectionObserver<E> collectionObserver) {
        this.set = (Set) Objects.requireNotNull(set2);
        this.observer = collectionObserver;
    }

    public CollectionObserver<E> observer() {
        return this.observer;
    }

    public int size() {
        return this.set.size();
    }

    public boolean isEmpty() {
        return this.set.isEmpty();
    }

    public boolean contains(Object obj) {
        return this.set.contains(obj);
    }

    @Nonnull
    public Iterator<E> iterator() {
        return new ObservableCollectionIterator(this.set, this.observer);
    }

    @Nonnull
    public Object[] toArray() {
        return this.set.toArray();
    }

    @Nonnull
    public <T> T[] toArray(@Nonnull T[] tArr) {
        return this.set.toArray(tArr);
    }

    public boolean add(E e) {
        boolean add = this.set.add(e);
        if (add) {
            CollectionObserver<E> collectionObserver = this.observer;
            if (collectionObserver != null) {
                collectionObserver.elementAdded(e);
            }
        }
        return add;
    }

    public boolean remove(Object obj) {
        boolean remove = this.set.remove(obj);
        if (remove) {
            CollectionObserver<E> collectionObserver = this.observer;
            if (collectionObserver != null) {
                collectionObserver.elementRemoved(obj);
            }
        }
        return remove;
    }

    public boolean containsAll(@Nonnull Collection<?> collection) {
        return this.set.containsAll(collection);
    }

    public boolean addAll(@Nonnull Collection<? extends E> collection) {
        boolean z = false;
        for (Object add : collection) {
            boolean add2 = add(add);
            if (!z && add2) {
                z = true;
            }
        }
        return z;
    }

    public boolean retainAll(@Nonnull Collection<?> collection) {
        ArrayList arrayList = new ArrayList();
        Iterator it = iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (!collection.contains(next)) {
                arrayList.add(next);
            }
        }
        return removeAll(arrayList);
    }

    public boolean removeAll(@Nonnull Collection<?> collection) {
        boolean z = false;
        for (Object remove : collection) {
            boolean remove2 = remove(remove);
            if (!z && remove2) {
                z = true;
            }
        }
        return z;
    }

    public void clear() {
        if (this.observer != null) {
            Iterator it = iterator();
            while (it.hasNext()) {
                this.observer.elementRemoved(it.next());
            }
        }
        this.set.clear();
    }
}
