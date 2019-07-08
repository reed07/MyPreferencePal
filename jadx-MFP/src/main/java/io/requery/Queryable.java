package io.requery;

import io.requery.meta.QueryAttribute;
import io.requery.query.Deletion;
import io.requery.query.Expression;
import io.requery.query.Result;
import io.requery.query.Scalar;
import io.requery.query.Selection;
import io.requery.query.Tuple;
import io.requery.query.Update;
import javax.annotation.CheckReturnValue;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface Queryable<T> {
    @CheckReturnValue
    <E extends T> Selection<? extends Scalar<Integer>> count(Class<E> cls);

    @CheckReturnValue
    <E extends T> Deletion<? extends Scalar<Integer>> delete(Class<E> cls);

    @CheckReturnValue
    <E extends T> Selection<? extends Result<E>> select(Class<E> cls, QueryAttribute<?, ?>... queryAttributeArr);

    @CheckReturnValue
    Selection<? extends Result<Tuple>> select(Expression<?>... expressionArr);

    @CheckReturnValue
    <E extends T> Update<? extends Scalar<Integer>> update(Class<E> cls);
}
