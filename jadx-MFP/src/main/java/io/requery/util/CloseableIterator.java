package io.requery.util;

import java.util.Iterator;

public interface CloseableIterator<E> extends AutoCloseable, Iterator<E> {
    void close();
}
