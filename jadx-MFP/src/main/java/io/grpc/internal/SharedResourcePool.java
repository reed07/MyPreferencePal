package io.grpc.internal;

import io.grpc.internal.SharedResourceHolder.Resource;

public final class SharedResourcePool<T> implements ObjectPool<T> {
    private final Resource<T> resource;

    private SharedResourcePool(Resource<T> resource2) {
        this.resource = resource2;
    }

    public static <T> SharedResourcePool<T> forResource(Resource<T> resource2) {
        return new SharedResourcePool<>(resource2);
    }

    public T getObject() {
        return SharedResourceHolder.get(this.resource);
    }

    public T returnObject(Object obj) {
        SharedResourceHolder.release(this.resource, obj);
        return null;
    }
}
