package io.requery.reactivex;

import io.requery.query.Scalar;
import io.requery.query.ScalarDelegate;

public class ReactiveScalar<E> extends ScalarDelegate<E> {
    ReactiveScalar(Scalar<E> scalar) {
        super(scalar);
    }
}
