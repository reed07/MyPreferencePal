package io.requery.proxy;

public interface PreInsertListener<T> {
    void preInsert(T t);
}
