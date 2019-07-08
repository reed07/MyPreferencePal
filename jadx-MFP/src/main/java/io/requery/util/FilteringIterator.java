package io.requery.util;

import io.requery.util.function.Predicate;
import java.util.Iterator;

public class FilteringIterator<E> implements Iterator<E> {
    private final Predicate<? super E> filter;
    private boolean hasNext;
    private final Iterator<E> iterator;
    private E pending;

    public FilteringIterator(Iterator<E> it, Predicate<? super E> predicate) {
        this.iterator = (Iterator) Objects.requireNotNull(it);
        this.filter = (Predicate) Objects.requireNotNull(predicate);
    }

    public boolean hasNext() {
        if (this.hasNext) {
            return true;
        }
        while (this.iterator.hasNext()) {
            E next = this.iterator.next();
            if (this.filter.test(next)) {
                this.pending = next;
                this.hasNext = true;
                return true;
            }
        }
        return false;
    }

    public E next() {
        if (this.hasNext) {
            E e = this.pending;
            this.pending = null;
            this.hasNext = false;
            return e;
        }
        E next = this.iterator.next();
        if (this.filter.test(next)) {
            return next;
        }
        return next();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
