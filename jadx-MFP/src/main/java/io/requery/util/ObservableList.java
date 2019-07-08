package io.requery.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.Nonnull;

public class ObservableList<E> implements ObservableCollection<E>, List<E> {
    private final List<E> list;
    private final CollectionObserver<E> observer;

    public ObservableList(List<E> list2, CollectionObserver<E> collectionObserver) {
        this.list = list2;
        this.observer = collectionObserver;
    }

    public CollectionObserver<E> observer() {
        return this.observer;
    }

    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public boolean contains(Object obj) {
        return this.list.contains(obj);
    }

    @Nonnull
    public Iterator<E> iterator() {
        return new ObservableCollectionIterator(this.list, this.observer);
    }

    @Nonnull
    public Object[] toArray() {
        return this.list.toArray();
    }

    @Nonnull
    public <T> T[] toArray(@Nonnull T[] tArr) {
        return this.list.toArray(tArr);
    }

    public boolean add(E e) {
        boolean add = this.list.add(e);
        if (add) {
            CollectionObserver<E> collectionObserver = this.observer;
            if (collectionObserver != null) {
                collectionObserver.elementAdded(e);
            }
        }
        return add;
    }

    public boolean remove(Object obj) {
        boolean remove = this.list.remove(obj);
        if (remove) {
            CollectionObserver<E> collectionObserver = this.observer;
            if (collectionObserver != null) {
                collectionObserver.elementRemoved(obj);
            }
        }
        return remove;
    }

    public boolean containsAll(@Nonnull Collection<?> collection) {
        return this.list.containsAll(collection);
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

    public boolean addAll(int i, @Nonnull Collection<? extends E> collection) {
        return this.list.addAll(collection);
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

    public void clear() {
        if (this.observer != null) {
            Iterator it = iterator();
            while (it.hasNext()) {
                this.observer.elementRemoved(it.next());
            }
        }
        this.list.clear();
    }

    public E get(int i) {
        return this.list.get(i);
    }

    public E set(int i, E e) {
        Objects.requireNotNull(e);
        E e2 = this.list.set(i, e);
        CollectionObserver<E> collectionObserver = this.observer;
        if (collectionObserver != null) {
            if (e2 != null) {
                collectionObserver.elementRemoved(e);
            }
            this.observer.elementAdded(e);
        }
        return e2;
    }

    public void add(int i, E e) {
        Objects.requireNotNull(e);
        this.list.add(i, e);
        CollectionObserver<E> collectionObserver = this.observer;
        if (collectionObserver != null) {
            collectionObserver.elementAdded(e);
        }
    }

    public E remove(int i) {
        E remove = this.list.remove(i);
        if (remove != null) {
            CollectionObserver<E> collectionObserver = this.observer;
            if (collectionObserver != null) {
                collectionObserver.elementRemoved(remove);
            }
        }
        return remove;
    }

    public int indexOf(Object obj) {
        return this.list.indexOf(obj);
    }

    public int lastIndexOf(Object obj) {
        return this.list.lastIndexOf(obj);
    }

    @Nonnull
    public ListIterator<E> listIterator() {
        return this.list.listIterator();
    }

    @Nonnull
    public ListIterator<E> listIterator(int i) {
        return this.list.listIterator(i);
    }

    @Nonnull
    public List<E> subList(int i, int i2) {
        return this.list.subList(i, i2);
    }
}
