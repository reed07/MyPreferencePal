package io.requery.reactivex;

import io.reactivex.Single;
import io.requery.BlockingEntityStore;
import io.requery.EntityStore;
import io.requery.util.function.Function;
import javax.annotation.CheckReturnValue;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class ReactiveEntityStore<T> implements EntityStore<T, Object>, ReactiveQueryable<T> {
    @CheckReturnValue
    public abstract <E extends T> Single<E> insert(E e);

    @CheckReturnValue
    public abstract <R> Single<R> runInTransaction(Function<BlockingEntityStore<T>, R> function);

    @CheckReturnValue
    public abstract <E extends T> Single<E> update(E e);
}
