package io.requery.proxy;

import io.requery.meta.Attribute;

public interface Initializer<E, V> {
    V initialize(EntityProxy<E> entityProxy, Attribute<E, V> attribute);
}
