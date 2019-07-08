package io.requery.reactor;

import io.requery.query.Result;
import io.requery.query.ResultDelegate;
import io.requery.query.element.QueryElement;
import io.requery.query.element.QueryWrapper;

public class ReactorResult<E> extends ResultDelegate<E> implements QueryWrapper {
    ReactorResult(Result<E> result) {
        super(result);
    }

    public QueryElement unwrapQuery() {
        return ((QueryWrapper) this.delegate).unwrapQuery();
    }
}
