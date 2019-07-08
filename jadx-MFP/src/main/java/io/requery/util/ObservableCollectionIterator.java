package io.requery.util;

import java.util.Collection;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

class ObservableCollectionIterator<T> implements Iterator<T> {
    private final Iterator<T> iterator;
    private T lastObject;
    private final CollectionObserver<T> observer;

    public ObservableCollectionIterator(@Nonnull Collection<T> collection, @Nullable CollectionObserver<T> collectionObserver) {
        this.iterator = collection.iterator();
        this.observer = collectionObserver;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public T next() {
        this.lastObject = this.iterator.next();
        return this.lastObject;
    }

    public void remove() {
        this.iterator.remove();
        CollectionObserver<T> collectionObserver = this.observer;
        if (collectionObserver != null) {
            T t = this.lastObject;
            if (t != null) {
                collectionObserver.elementRemoved(t);
            }
        }
    }
}
