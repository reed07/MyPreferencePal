package io.requery;

import javax.annotation.Nullable;

public interface Converter<A, B> {
    A convertToMapped(Class<? extends A> cls, @Nullable B b);

    B convertToPersisted(A a);

    Class<A> getMappedType();

    @Nullable
    Integer getPersistedSize();

    Class<B> getPersistedType();
}
