package io.requery.util;

public interface CollectionObserver<E> {
    void elementAdded(E e);

    void elementRemoved(E e);
}
