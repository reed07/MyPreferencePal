package io.requery;

import java.util.concurrent.Callable;
import javax.annotation.CheckReturnValue;

public interface BlockingEntityStore<T> extends EntityStore<T, Object>, Transactionable<Object> {
    @CheckReturnValue
    <E extends T, K> E findByKey(Class<E> cls, K k);

    <E extends T> E insert(E e);

    <E extends T> E refresh(E e);

    <V> V runInTransaction(Callable<V> callable, TransactionIsolation transactionIsolation);

    <E extends T> E update(E e);
}
