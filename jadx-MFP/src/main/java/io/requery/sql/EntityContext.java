package io.requery.sql;

import io.requery.proxy.EntityProxy;

interface EntityContext<T> extends RuntimeConfiguration {
    CompositeEntityListener<T> getStateListener();

    <E> EntityProxy<E> proxyOf(E e, boolean z);

    <E extends T> EntityReader<E, T> read(Class<? extends E> cls);

    <E extends T> EntityWriter<E, T> write(Class<? extends E> cls);
}
