package io.requery.meta;

import io.requery.proxy.EntityProxy;
import io.requery.query.Expression;
import io.requery.query.ExpressionType;
import io.requery.util.Objects;
import io.requery.util.function.Function;
import io.requery.util.function.Supplier;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class TypeBuilder<T> extends BaseType<T> {
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ Set getAttributes() {
        return super.getAttributes();
    }

    public /* bridge */ /* synthetic */ Class getBaseType() {
        return super.getBaseType();
    }

    public /* bridge */ /* synthetic */ Function getBuildFunction() {
        return super.getBuildFunction();
    }

    public /* bridge */ /* synthetic */ Supplier getBuilderFactory() {
        return super.getBuilderFactory();
    }

    public /* bridge */ /* synthetic */ Class getClassType() {
        return super.getClassType();
    }

    public /* bridge */ /* synthetic */ ExpressionType getExpressionType() {
        return super.getExpressionType();
    }

    public /* bridge */ /* synthetic */ Supplier getFactory() {
        return super.getFactory();
    }

    public /* bridge */ /* synthetic */ Expression getInnerExpression() {
        return super.getInnerExpression();
    }

    public /* bridge */ /* synthetic */ Set getKeyAttributes() {
        return super.getKeyAttributes();
    }

    public /* bridge */ /* synthetic */ String getName() {
        return super.getName();
    }

    public /* bridge */ /* synthetic */ Function getProxyProvider() {
        return super.getProxyProvider();
    }

    public /* bridge */ /* synthetic */ Attribute getSingleKeyAttribute() {
        return super.getSingleKeyAttribute();
    }

    public /* bridge */ /* synthetic */ String[] getTableCreateAttributes() {
        return super.getTableCreateAttributes();
    }

    public /* bridge */ /* synthetic */ String[] getTableUniqueIndexes() {
        return super.getTableUniqueIndexes();
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ boolean isBuildable() {
        return super.isBuildable();
    }

    public /* bridge */ /* synthetic */ boolean isCacheable() {
        return super.isCacheable();
    }

    public /* bridge */ /* synthetic */ boolean isImmutable() {
        return super.isImmutable();
    }

    public /* bridge */ /* synthetic */ boolean isReadOnly() {
        return super.isReadOnly();
    }

    public /* bridge */ /* synthetic */ boolean isStateless() {
        return super.isStateless();
    }

    public /* bridge */ /* synthetic */ boolean isView() {
        return super.isView();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public TypeBuilder(Class<T> cls, String str) {
        Objects.requireNotNull(cls);
        this.type = cls;
        this.attributes = new TreeSet(new Comparator<Attribute<T, ?>>() {
            public int compare(Attribute<T, ?> attribute, Attribute<T, ?> attribute2) {
                if (attribute.isKey()) {
                    return -1;
                }
                if (attribute2.isKey()) {
                    return 1;
                }
                return attribute.getName().compareTo(attribute2.getName());
            }
        });
        this.name = str;
        this.referencedTypes = new LinkedHashSet();
        this.expressions = new LinkedHashSet();
    }

    public TypeBuilder<T> setBaseType(Class<? super T> cls) {
        this.baseType = cls;
        return this;
    }

    public TypeBuilder<T> setCacheable(boolean z) {
        this.cacheable = z;
        return this;
    }

    public TypeBuilder<T> setImmutable(boolean z) {
        this.immutable = z;
        return this;
    }

    public TypeBuilder<T> setReadOnly(boolean z) {
        this.readOnly = z;
        return this;
    }

    public TypeBuilder<T> setStateless(boolean z) {
        this.stateless = z;
        return this;
    }

    public TypeBuilder<T> setView(boolean z) {
        this.isView = z;
        return this;
    }

    public TypeBuilder<T> setFactory(Supplier<T> supplier) {
        this.factory = supplier;
        return this;
    }

    public TypeBuilder<T> setProxyProvider(Function<T, EntityProxy<T>> function) {
        this.proxyProvider = function;
        return this;
    }

    public TypeBuilder<T> addAttribute(Attribute<T, ?> attribute) {
        this.attributes.add(attribute);
        return this;
    }

    public TypeBuilder<T> addExpression(QueryExpression<?> queryExpression) {
        this.expressions.add(queryExpression);
        return this;
    }

    public Type<T> build() {
        return new ImmutableType(this);
    }
}
