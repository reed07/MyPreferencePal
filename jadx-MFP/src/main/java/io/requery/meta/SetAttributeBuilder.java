package io.requery.meta;

import java.util.Set;

public class SetAttributeBuilder<T, S extends Set<E>, E> extends CollectionAttributeBuilder<T, S, E> {
    public SetAttributeBuilder(String str, Class<? extends Set> cls, Class<E> cls2) {
        super(str, cls, cls2);
    }
}
