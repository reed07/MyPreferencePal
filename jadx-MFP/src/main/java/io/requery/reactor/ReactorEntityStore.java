package io.requery.reactor;

import io.requery.BlockingEntityStore;
import io.requery.EntityStore;
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
import io.requery.util.function.Function;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ReactorEntityStore<T> implements EntityStore<T, Object>, ReactorQueryable<T> {
    private final BlockingEntityStore<T> delegate;

    public void close() {
        this.delegate.close();
    }

    public Selection<ReactorResult<Tuple>> select(Expression<?>... expressionArr) {
        return result(this.delegate.select(expressionArr));
    }

    public <E extends T> Selection<ReactorResult<E>> select(Class<E> cls, QueryAttribute<?, ?>... queryAttributeArr) {
        return result(this.delegate.select(cls, queryAttributeArr));
    }

    public <E extends T> Update<ReactorScalar<Integer>> update(Class<E> cls) {
        return scalar(this.delegate.update(cls));
    }

    public <E extends T> Deletion<ReactorScalar<Integer>> delete(Class<E> cls) {
        return scalar(this.delegate.delete(cls));
    }

    public <E extends T> Selection<ReactorScalar<Integer>> count(Class<E> cls) {
        return scalar(this.delegate.count(cls));
    }

    public BlockingEntityStore<T> toBlocking() {
        return this.delegate;
    }

    private static <E> QueryElement<ReactorResult<E>> result(Return<? extends Result<E>> returnR) {
        return ((QueryElement) returnR).extend(new Function<Result<E>, ReactorResult<E>>() {
            public ReactorResult<E> apply(Result<E> result) {
                return new ReactorResult<>(result);
            }
        });
    }

    private static <E> QueryElement<ReactorScalar<E>> scalar(Return<? extends Scalar<E>> returnR) {
        return ((QueryElement) returnR).extend(new Function<Scalar<E>, ReactorScalar<E>>() {
            public ReactorScalar<E> apply(Scalar<E> scalar) {
                return new ReactorScalar<>(scalar);
            }
        });
    }
}
