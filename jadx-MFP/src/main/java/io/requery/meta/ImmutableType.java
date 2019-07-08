package io.requery.meta;

import io.requery.util.function.Supplier;
import java.util.Collections;
import java.util.LinkedHashSet;

final class ImmutableType<T> extends BaseType<T> {
    ImmutableType(TypeBuilder<T> typeBuilder) {
        this.type = typeBuilder.getClassType();
        this.baseType = typeBuilder.getBaseType();
        this.name = typeBuilder.getName();
        this.cacheable = typeBuilder.isCacheable();
        this.readOnly = typeBuilder.isReadOnly();
        this.immutable = typeBuilder.isImmutable();
        this.isView = typeBuilder.isView();
        this.stateless = typeBuilder.isStateless();
        this.factory = typeBuilder.getFactory();
        this.proxyProvider = typeBuilder.getProxyProvider();
        this.tableCreateAttributes = typeBuilder.getTableCreateAttributes();
        this.tableUniqueIndexes = typeBuilder.getTableUniqueIndexes();
        this.builderFactory = typeBuilder.getBuilderFactory();
        this.buildFunction = typeBuilder.getBuildFunction();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        for (Attribute attribute : typeBuilder.getAttributes()) {
            setDeclaringType(attribute);
            linkedHashSet.add(attribute);
            if (attribute.isKey()) {
                linkedHashSet2.add(attribute);
            }
        }
        this.attributes = Collections.unmodifiableSet(linkedHashSet);
        this.keyAttributes = Collections.unmodifiableSet(linkedHashSet2);
        if (linkedHashSet2.size() == 1) {
            this.keyAttribute = (Attribute) linkedHashSet2.iterator().next();
        }
        for (QueryExpression declaringType : typeBuilder.expressions) {
            setDeclaringType(declaringType);
        }
        if (this.factory == null) {
            this.factory = new Supplier<T>() {
                public T get() {
                    try {
                        return ImmutableType.this.getClassType().newInstance();
                    } catch (IllegalAccessException | InstantiationException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
        }
    }

    private void setDeclaringType(Object obj) {
        if (obj instanceof TypeDeclarable) {
            ((TypeDeclarable) obj).setDeclaringType(this);
            return;
        }
        throw new UnsupportedOperationException();
    }
}
