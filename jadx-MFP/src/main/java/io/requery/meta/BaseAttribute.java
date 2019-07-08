package io.requery.meta;

import io.requery.CascadeAction;
import io.requery.Converter;
import io.requery.ReferentialAction;
import io.requery.proxy.Initializer;
import io.requery.proxy.Property;
import io.requery.proxy.PropertyState;
import io.requery.query.ExpressionType;
import io.requery.query.FieldExpression;
import io.requery.query.Order;
import io.requery.util.Objects;
import io.requery.util.function.Supplier;
import java.util.Collections;
import java.util.Set;

abstract class BaseAttribute<T, V> extends FieldExpression<V> implements QueryAttribute<T, V>, TypeDeclarable<T> {
    Property<?, V> builderProperty;
    Cardinality cardinality;
    Set<CascadeAction> cascadeActions;
    Class<V> classType;
    String collate;
    Converter<V, ?> converter;
    Type<T> declaringType;
    String defaultValue;
    String definition;
    ReferentialAction deleteAction;
    Class<?> elementClass;
    Set<String> indexNames;
    Initializer<T, V> initializer;
    boolean isForeignKey;
    boolean isGenerated;
    boolean isIndex;
    boolean isKey;
    boolean isLazy;
    boolean isNullable;
    boolean isReadOnly;
    boolean isUnique;
    boolean isVersion;
    Integer length;
    Class<?> mapKeyClass;
    Supplier<Attribute> mappedAttribute;
    String name;
    Supplier<Attribute> orderByAttribute;
    Order orderByDirection;
    PrimitiveKind primitiveKind;
    Property<T, V> property;
    String propertyName;
    Property<T, PropertyState> propertyState;
    Supplier<Attribute> referencedAttribute;
    Class<?> referencedClass;
    ReferentialAction updateAction;

    BaseAttribute() {
    }

    public Property<?, V> getBuilderProperty() {
        return this.builderProperty;
    }

    public Class<V> getClassType() {
        return this.classType;
    }

    public Cardinality getCardinality() {
        return this.cardinality;
    }

    public Set<CascadeAction> getCascadeActions() {
        Set<CascadeAction> set = this.cascadeActions;
        return set == null ? Collections.emptySet() : set;
    }

    public String getCollate() {
        return this.collate;
    }

    public Converter<V, ?> getConverter() {
        return this.converter;
    }

    public Type<T> getDeclaringType() {
        return this.declaringType;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public String getDefinition() {
        return this.definition;
    }

    public ReferentialAction getDeleteAction() {
        return this.deleteAction;
    }

    public Class<?> getElementClass() {
        return this.elementClass;
    }

    public ExpressionType getExpressionType() {
        return ExpressionType.ATTRIBUTE;
    }

    public Set<String> getIndexNames() {
        return this.indexNames;
    }

    public Initializer<T, V> getInitializer() {
        return this.initializer;
    }

    public Integer getLength() {
        Converter<V, ?> converter2 = this.converter;
        return converter2 != null ? converter2.getPersistedSize() : this.length;
    }

    public Class<?> getMapKeyClass() {
        return this.mapKeyClass;
    }

    public Supplier<Attribute> getMappedAttribute() {
        return this.mappedAttribute;
    }

    public String getName() {
        return this.name;
    }

    public Supplier<Attribute> getOrderByAttribute() {
        return this.orderByAttribute;
    }

    public Order getOrderByDirection() {
        return this.orderByDirection;
    }

    public PrimitiveKind getPrimitiveKind() {
        return this.primitiveKind;
    }

    public Property<T, V> getProperty() {
        return this.property;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public Property<T, PropertyState> getPropertyState() {
        return this.propertyState;
    }

    public Supplier<Attribute> getReferencedAttribute() {
        return this.referencedAttribute;
    }

    public Class<?> getReferencedClass() {
        return this.referencedClass;
    }

    public ReferentialAction getUpdateAction() {
        return this.updateAction;
    }

    public boolean isAssociation() {
        return this.cardinality != null;
    }

    public boolean isForeignKey() {
        return this.isForeignKey;
    }

    public boolean isGenerated() {
        return this.isGenerated;
    }

    public boolean isIndexed() {
        return this.isIndex;
    }

    public boolean isKey() {
        return this.isKey;
    }

    public boolean isLazy() {
        return this.isLazy;
    }

    public boolean isNullable() {
        return this.isNullable;
    }

    public boolean isUnique() {
        return this.isUnique;
    }

    public boolean isReadOnly() {
        return this.isReadOnly;
    }

    public boolean isVersion() {
        return this.isVersion;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof Attribute)) {
            return false;
        }
        Attribute attribute = (Attribute) obj;
        if (Objects.equals(this.name, attribute.getName()) && Objects.equals(this.classType, attribute.getClassType()) && Objects.equals(this.declaringType, attribute.getDeclaringType())) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hash(this.name, this.classType, this.declaringType);
    }

    public String toString() {
        if (getDeclaringType() == null) {
            return getName();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getDeclaringType().getName());
        sb.append(".");
        sb.append(getName());
        return sb.toString();
    }

    public void setDeclaringType(Type<T> type) {
        this.declaringType = type;
    }
}
