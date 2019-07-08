package io.requery.sql;

import io.requery.query.Result;
import io.requery.query.element.QueryElement;
import io.requery.query.element.QueryOperation;

class SelectOperation<E> implements QueryOperation<Result<E>> {
    private final RuntimeConfiguration configuration;
    private final ResultReader<E> reader;

    SelectOperation(RuntimeConfiguration runtimeConfiguration, ResultReader<E> resultReader) {
        this.configuration = runtimeConfiguration;
        this.reader = resultReader;
    }

    public Result<E> evaluate(QueryElement<Result<E>> queryElement) {
        return new SelectResult(this.configuration, queryElement, this.reader);
    }
}
