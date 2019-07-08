package io.requery;

import javax.annotation.CheckReturnValue;

public interface EntityStore<T, R> extends Queryable<T>, AutoCloseable {
    void close();

    @CheckReturnValue
    BlockingEntityStore<T> toBlocking();
}
