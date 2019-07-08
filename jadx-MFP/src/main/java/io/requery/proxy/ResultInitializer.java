package io.requery.proxy;

import io.requery.meta.Attribute;
import io.requery.query.ModifiableResult;
import io.requery.query.Result;
import io.requery.util.function.Supplier;

public class ResultInitializer<E, V> implements Initializer<E, V>, QueryInitializer<E, V> {
    public V initialize(EntityProxy<E> entityProxy, Attribute<E, V> attribute) {
        return initialize(entityProxy, attribute, null);
    }

    public <U> V initialize(EntityProxy<E> entityProxy, Attribute<E, V> attribute, Supplier<? extends Result<U>> supplier) {
        Result result;
        Class classType = attribute.getClassType();
        CollectionChanges collectionChanges = new CollectionChanges(entityProxy, attribute);
        if (supplier == null) {
            result = null;
        } else {
            result = (Result) supplier.get();
        }
        if (Iterable.class.isAssignableFrom(classType)) {
            return attribute.getClassType().cast(new ModifiableResult(result, collectionChanges));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unsupported result type ");
        sb.append(classType);
        throw new IllegalStateException(sb.toString());
    }
}
