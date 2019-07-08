package io.requery.query;

import java.util.concurrent.Executor;

public abstract class BaseScalar<E> implements Scalar<E> {
    private boolean computed;
    private final Executor executor;
    private E value;

    /* access modifiers changed from: protected */
    public abstract E evaluate();

    protected BaseScalar(Executor executor2) {
        this.executor = executor2;
    }

    public synchronized E value() {
        if (!this.computed) {
            this.computed = true;
            this.value = evaluate();
        }
        return this.value;
    }

    public E call() throws Exception {
        return value();
    }
}
