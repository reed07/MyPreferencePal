package io.requery.proxy;

public interface PostInsertListener<T> {
    void postInsert(T t);
}
