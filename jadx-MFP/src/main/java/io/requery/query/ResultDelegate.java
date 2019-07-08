package io.requery.query;

import io.requery.util.CloseableIterator;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public class ResultDelegate<E> implements Result<E> {
    protected final Result<E> delegate;

    public ResultDelegate(Result<E> result) {
        this.delegate = result;
    }

    public CloseableIterator<E> iterator() {
        return this.delegate.iterator();
    }

    public CloseableIterator<E> iterator(int i, int i2) {
        return this.delegate.iterator(i, i2);
    }

    public void close() {
        this.delegate.close();
    }

    public <C extends Collection<E>> C collect(C c) {
        return this.delegate.collect(c);
    }

    public E first() throws NoSuchElementException {
        return this.delegate.first();
    }

    public E firstOr(E e) {
        return this.delegate.firstOr(e);
    }

    public E firstOrNull() {
        return this.delegate.firstOrNull();
    }

    public List<E> toList() {
        return this.delegate.toList();
    }
}
