package io.requery.reactivex;

import io.requery.TransactionListenable;
import io.requery.query.Result;
import io.requery.query.ResultDelegate;
import io.requery.query.element.QueryElement;
import io.requery.query.element.QueryWrapper;

public class ReactiveResult<E> extends ResultDelegate<E> implements TransactionListenable, QueryWrapper {
    ReactiveResult(Result<E> result) {
        super(result);
    }

    public QueryElement unwrapQuery() {
        return ((QueryWrapper) this.delegate).unwrapQuery();
    }
}
