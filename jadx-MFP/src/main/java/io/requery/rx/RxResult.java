package io.requery.rx;

import io.requery.TransactionListenable;
import io.requery.query.ResultDelegate;
import io.requery.query.element.QueryElement;
import io.requery.query.element.QueryWrapper;

public class RxResult<E> extends ResultDelegate<E> implements TransactionListenable, QueryWrapper {
    public QueryElement unwrapQuery() {
        return ((QueryWrapper) this.delegate).unwrapQuery();
    }
}
