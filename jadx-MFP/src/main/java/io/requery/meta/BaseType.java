package io.requery.meta;

import io.requery.proxy.EntityProxy;
import io.requery.query.Expression;
import io.requery.query.ExpressionType;
import io.requery.util.Objects;
import io.requery.util.function.Function;
import io.requery.util.function.Supplier;
import java.util.LinkedHashSet;
import java.util.Set;

abstract class BaseType<T> implements Type<T> {
    Set<Attribute<T, ?>> attributes;
    Class<? super T> baseType;
    Function<?, T> buildFunction;
    Supplier<?> builderFactory;
    boolean cacheable = true;
    Set<QueryExpression<?>> expressions;
    Supplier<T> factory;
    boolean immutable;
    boolean isView;
    Attribute<T, ?> keyAttribute;
    Set<Attribute<T, ?>> keyAttributes;
    String name;
    Function<T, EntityProxy<T>> proxyProvider;
    boolean readOnly;
    Set<Class<?>> referencedTypes = new LinkedHashSet();
    boolean stateless;
    String[] tableCreateAttributes;
    String[] tableUniqueIndexes;
    Class<T> type;

    public Expression<T> getInnerExpression() {
        return null;
    }

    BaseType() {
    }

    public String getName() {
        return this.name;
    }

    public Class<T> getClassType() {
        return this.type;
    }

    public Class<? super T> getBaseType() {
        return this.baseType;
    }

    public ExpressionType getExpressionType() {
        return ExpressionType.NAME;
    }

    public boolean isBuildable() {
        return this.builderFactory != null;
    }

    public boolean isCacheable() {
        return this.cacheable;
    }

    public boolean isImmutable() {
        return this.immutable;
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public boolean isStateless() {
        return this.stateless;
    }

    public boolean isView() {
        return this.isView;
    }

    public Set<Attribute<T, ?>> getAttributes() {
        return this.attributes;
    }

    public Set<Attribute<T, ?>> getKeyAttributes() {
        return this.keyAttributes;
    }

    public Attribute<T, ?> getSingleKeyAttribute() {
        return this.keyAttribute;
    }

    public <B> Supplier<B> getBuilderFactory() {
        return this.builderFactory;
    }

    public <B> Function<B, T> getBuildFunction() {
        return this.buildFunction;
    }

    public Supplier<T> getFactory() {
        return this.factory;
    }

    public Function<T, EntityProxy<T>> getProxyProvider() {
        return this.proxyProvider;
    }

    public String[] getTableCreateAttributes() {
        return this.tableCreateAttributes;
    }

    public String[] getTableUniqueIndexes() {
        return this.tableUniqueIndexes;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof Type)) {
            return false;
        }
        Type type2 = (Type) obj;
        if (Objects.equals(getClassType(), type2.getClassType()) && Objects.equals(getName(), type2.getName())) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hash(this.name, this.type);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("classType: ");
        sb.append(this.type.toString());
        sb.append(" name: ");
        sb.append(this.name);
        sb.append(" readonly: ");
        sb.append(this.readOnly);
        sb.append(" immutable: ");
        sb.append(this.immutable);
        sb.append(" stateless: ");
        sb.append(this.stateless);
        sb.append(" cacheable: ");
        sb.append(this.cacheable);
        return sb.toString();
    }
}
