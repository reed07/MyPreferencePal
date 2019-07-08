package io.requery.query.element;

import io.requery.util.function.Function;

class ExtendQueryOperation<T extends S, S> implements QueryOperation<S> {
    private final QueryOperation<S> operation;
    private final Function<S, T> transform;

    ExtendQueryOperation(Function<S, T> function, QueryOperation<S> queryOperation) {
        this.transform = function;
        this.operation = queryOperation;
    }

    public S evaluate(QueryElement<S> queryElement) {
        return this.transform.apply(this.operation.evaluate(queryElement));
    }
}
