package io.requery.proxy;

public interface PostLoadListener<T> {
    void postLoad(T t);
}
