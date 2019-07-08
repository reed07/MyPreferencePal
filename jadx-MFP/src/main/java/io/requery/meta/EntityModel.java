package io.requery.meta;

import java.util.Set;

public interface EntityModel {
    <T> boolean containsTypeOf(Class<? extends T> cls);

    String getName();

    Set<Type<?>> getTypes();

    <T> Type<T> typeOf(Class<? extends T> cls) throws NotMappedException;
}
