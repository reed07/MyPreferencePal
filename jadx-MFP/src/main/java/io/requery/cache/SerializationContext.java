package io.requery.cache;

import io.requery.meta.Type;
import io.requery.util.ClassMap;

class SerializationContext {
    private static final ClassMap<Type<?>> types = new ClassMap<>();

    public static <E> Type<E> getType(Class<E> cls) {
        Type<E> type = (Type) types.get(cls);
        if (type != null) {
            return type;
        }
        throw new IllegalStateException();
    }

    private SerializationContext() {
    }
}
