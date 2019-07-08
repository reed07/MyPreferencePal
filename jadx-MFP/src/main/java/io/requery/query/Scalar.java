package io.requery.query;

import java.util.concurrent.Callable;

public interface Scalar<E> extends Callable<E> {
    E call() throws Exception;

    E value();
}
