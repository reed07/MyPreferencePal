package io.requery.query;

public class ScalarDelegate<E> implements Scalar<E> {
    private final Scalar<E> delegate;

    public ScalarDelegate(Scalar<E> scalar) {
        this.delegate = scalar;
    }

    public E value() {
        return this.delegate.value();
    }

    public E call() throws Exception {
        return this.delegate.call();
    }
}
