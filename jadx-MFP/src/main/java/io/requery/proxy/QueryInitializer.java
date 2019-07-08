package io.requery.proxy;

import io.requery.meta.Attribute;
import io.requery.query.Result;
import io.requery.util.function.Supplier;

public interface QueryInitializer<E, V> {
    <U> V initialize(EntityProxy<E> entityProxy, Attribute<E, V> attribute, Supplier<? extends Result<U>> supplier);
}
