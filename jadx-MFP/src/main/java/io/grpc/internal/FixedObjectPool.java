package io.grpc.internal;

public final class FixedObjectPool<T> implements ObjectPool<T> {
    private final T object;

    public T returnObject(Object obj) {
        return null;
    }

    public T getObject() {
        return this.object;
    }
}
