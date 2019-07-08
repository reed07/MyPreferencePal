package io.requery.proxy;

import io.requery.meta.Attribute;

public interface PropertyLoader<E> {
    <V> void load(E e, EntityProxy<E> entityProxy, Attribute<E, V> attribute);
}
