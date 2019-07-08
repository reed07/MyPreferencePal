package io.requery.meta;

import io.requery.proxy.EntityProxy;
import io.requery.query.Expression;
import io.requery.util.function.Function;
import io.requery.util.function.Supplier;
import java.util.Set;

public interface Type<T> extends Expression<T> {
    Set<Attribute<T, ?>> getAttributes();

    Class<?> getBaseType();

    <B> Function<B, T> getBuildFunction();

    <B> Supplier<B> getBuilderFactory();

    Class<T> getClassType();

    Supplier<T> getFactory();

    Set<Attribute<T, ?>> getKeyAttributes();

    String getName();

    Function<T, EntityProxy<T>> getProxyProvider();

    Attribute<T, ?> getSingleKeyAttribute();

    String[] getTableCreateAttributes();

    String[] getTableUniqueIndexes();

    boolean isBuildable();

    boolean isCacheable();

    boolean isImmutable();

    boolean isReadOnly();

    boolean isStateless();

    boolean isView();
}
