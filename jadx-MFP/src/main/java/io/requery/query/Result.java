package io.requery.query;

import io.requery.util.CloseableIterable;
import io.requery.util.CloseableIterator;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import javax.annotation.CheckReturnValue;

public interface Result<E> extends CloseableIterable<E>, AutoCloseable {
    void close();

    <C extends Collection<E>> C collect(C c);

    @CheckReturnValue
    E first() throws NoSuchElementException;

    @CheckReturnValue
    E firstOr(E e);

    @CheckReturnValue
    E firstOrNull();

    CloseableIterator<E> iterator();

    CloseableIterator<E> iterator(int i, int i2);

    @CheckReturnValue
    List<E> toList();
}
