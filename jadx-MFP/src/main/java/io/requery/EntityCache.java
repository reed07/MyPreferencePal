package io.requery;

public interface EntityCache {
    void clear();

    <T> T get(Class<T> cls, Object obj);

    void invalidate(Class<?> cls, Object obj);

    <T> void put(Class<T> cls, Object obj, T t);
}
