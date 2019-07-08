package io.requery.query;

import io.requery.proxy.CollectionChanges;
import io.requery.util.CloseableIterator;
import io.requery.util.CollectionObserver;
import io.requery.util.CompositeIterator;
import io.requery.util.FilteringIterator;
import io.requery.util.ObservableCollection;
import io.requery.util.function.Predicate;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ModifiableResult<E> implements MutableResult<E>, ObservableCollection<E> {
    /* access modifiers changed from: private */
    public final CollectionChanges<?, E> changes;
    private final Result<E> result;

    public ModifiableResult(Result<E> result2, CollectionChanges<?, E> collectionChanges) {
        this.result = result2;
        this.changes = collectionChanges;
    }

    public CollectionObserver<E> observer() {
        return this.changes;
    }

    public CloseableIterator<E> iterator() {
        Result<E> result2 = this.result;
        final Iterator emptyIterator = result2 == null ? Collections.emptyIterator() : result2.iterator();
        final FilteringIterator filteringIterator = new FilteringIterator(new CompositeIterator(emptyIterator, this.changes.addedElements().iterator()), new Predicate<E>() {
            public boolean test(E e) {
                return !ModifiableResult.this.changes.removedElements().contains(e);
            }
        });
        return new CloseableIterator<E>() {
            public void close() {
                Iterator it = emptyIterator;
                if (it instanceof CloseableIterator) {
                    ((CloseableIterator) it).close();
                }
            }

            public boolean hasNext() {
                return filteringIterator.hasNext();
            }

            public E next() {
                return filteringIterator.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public CloseableIterator<E> iterator(int i, int i2) {
        return iterator();
    }

    public void close() {
        Result<E> result2 = this.result;
        if (result2 != null) {
            result2.close();
        }
    }

    public List<E> toList() {
        Result<E> result2 = this.result;
        return result2 == null ? Collections.emptyList() : result2.toList();
    }

    public <C extends Collection<E>> C collect(C c) {
        Result<E> result2 = this.result;
        return result2 != null ? result2.collect(c) : c;
    }

    public E first() throws NoSuchElementException {
        Result<E> result2 = this.result;
        if (result2 != null) {
            return result2.first();
        }
        throw new NoSuchElementException();
    }

    public E firstOr(E e) {
        Result<E> result2 = this.result;
        return result2 == null ? e : result2.firstOr(e);
    }

    public E firstOrNull() {
        return firstOr(null);
    }
}
