package io.requery.meta;

import io.requery.proxy.CollectionInitializer;
import io.requery.util.Objects;
import java.util.Collection;

public class CollectionAttributeBuilder<T, C extends Collection, E> extends AttributeBuilder<T, C> {
    public CollectionAttributeBuilder(String str, Class<? extends Collection> cls, Class<E> cls2) {
        super(str, cls);
        this.elementClass = (Class) Objects.requireNotNull(cls2);
        setInitializer(new CollectionInitializer());
    }
}
