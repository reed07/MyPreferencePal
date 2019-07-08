package io.requery.reactor;

import io.requery.query.Scalar;
import io.requery.query.ScalarDelegate;

public class ReactorScalar<E> extends ScalarDelegate<E> {
    ReactorScalar(Scalar<E> scalar) {
        super(scalar);
    }
}
