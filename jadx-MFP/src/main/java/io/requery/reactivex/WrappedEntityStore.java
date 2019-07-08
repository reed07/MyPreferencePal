package io.requery.reactivex;

import io.reactivex.Single;
import io.requery.BlockingEntityStore;
import io.requery.meta.QueryAttribute;
import io.requery.query.Deletion;
import io.requery.query.Expression;
import io.requery.query.Result;
import io.requery.query.Return;
import io.requery.query.Scalar;
import io.requery.query.Selection;
import io.requery.query.Tuple;
import io.requery.query.Update;
import io.requery.query.element.QueryElement;
import io.requery.util.Objects;
import io.requery.util.function.Function;
import java.util.concurrent.Callable;
import javax.annotation.CheckReturnValue;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
class WrappedEntityStore<T> extends ReactiveEntityStore<T> {
    /* access modifiers changed from: private */
    public final BlockingEntityStore<T> delegate;

    WrappedEntityStore(BlockingEntityStore<T> blockingEntityStore) {
        this.delegate = (BlockingEntityStore) Objects.requireNotNull(blockingEntityStore);
    }

    public <E extends T> Single<E> insert(final E e) {
        return Single.fromCallable(new Callable<E>() {
            public E call() throws Exception {
                return WrappedEntityStore.this.delegate.insert(e);
            }
        });
    }

    public <E extends T> Single<E> update(final E e) {
        return Single.fromCallable(new Callable<E>() {
            public E call() throws Exception {
                return WrappedEntityStore.this.delegate.update(e);
            }
        });
    }

    public void close() {
        this.delegate.close();
    }

    public Selection<ReactiveResult<Tuple>> select(Expression<?>... expressionArr) {
        return result(this.delegate.select(expressionArr));
    }

    public <E extends T> Selection<ReactiveResult<E>> select(Class<E> cls, QueryAttribute<?, ?>... queryAttributeArr) {
        return result(this.delegate.select(cls, queryAttributeArr));
    }

    public <E extends T> Update<ReactiveScalar<Integer>> update(Class<E> cls) {
        return scalar(this.delegate.update(cls));
    }

    public <E extends T> Deletion<ReactiveScalar<Integer>> delete(Class<E> cls) {
        return scalar(this.delegate.delete(cls));
    }

    public <E extends T> Selection<ReactiveScalar<Integer>> count(Class<E> cls) {
        return scalar(this.delegate.count(cls));
    }

    public BlockingEntityStore<T> toBlocking() {
        return this.delegate;
    }

    @CheckReturnValue
    public <R> Single<R> runInTransaction(final Function<BlockingEntityStore<T>, R> function) {
        return Single.fromCallable(new Callable<R>() {
            public R call() throws Exception {
                return function.apply(WrappedEntityStore.this.toBlocking());
            }
        });
    }

    private static <E> QueryElement<ReactiveResult<E>> result(Return<? extends Result<E>> returnR) {
        return ((QueryElement) returnR).extend(new Function<Result<E>, ReactiveResult<E>>() {
            public ReactiveResult<E> apply(Result<E> result) {
                return new ReactiveResult<>(result);
            }
        });
    }

    private static <E> QueryElement<ReactiveScalar<E>> scalar(Return<? extends Scalar<E>> returnR) {
        return ((QueryElement) returnR).extend(new Function<Scalar<E>, ReactiveScalar<E>>() {
            public ReactiveScalar<E> apply(Scalar<E> scalar) {
                return new ReactiveScalar<>(scalar);
            }
        });
    }
}
