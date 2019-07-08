package io.requery.proxy;

public interface EntityStateEventListenable<T> {
    void addPreInsertListener(PreInsertListener<T> preInsertListener);

    void addPreUpdateListener(PreUpdateListener<T> preUpdateListener);
}
