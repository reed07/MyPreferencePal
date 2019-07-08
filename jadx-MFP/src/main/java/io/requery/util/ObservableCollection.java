package io.requery.util;

public interface ObservableCollection<E> {
    CollectionObserver<E> observer();
}
