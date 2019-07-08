package io.requery.meta;

import io.requery.CascadeAction;
import io.requery.Converter;
import io.requery.ReferentialAction;
import io.requery.proxy.Initializer;
import io.requery.proxy.Property;
import io.requery.proxy.PropertyState;
import io.requery.query.ExpressionType;
import io.requery.query.Order;
import io.requery.util.Objects;
import io.requery.util.function.Supplier;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

public class AttributeBuilder<T, V> extends BaseAttribute<T, V> {
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ Property getBuilderProperty() {
        return super.getBuilderProperty();
    }

    public /* bridge */ /* synthetic */ Cardinality getCardinality() {
        return super.getCardinality();
    }

    public /* bridge */ /* synthetic */ Set getCascadeActions() {
        return super.getCascadeActions();
    }

    public /* bridge */ /* synthetic */ Class getClassType() {
        return super.getClassType();
    }

    public /* bridge */ /* synthetic */ String getCollate() {
        return super.getCollate();
    }

    public /* bridge */ /* synthetic */ Converter getConverter() {
        return super.getConverter();
    }

    public /* bridge */ /* synthetic */ Type getDeclaringType() {
        return super.getDeclaringType();
    }

    public /* bridge */ /* synthetic */ String getDefaultValue() {
        return super.getDefaultValue();
    }

    public /* bridge */ /* synthetic */ String getDefinition() {
        return super.getDefinition();
    }

    public /* bridge */ /* synthetic */ ReferentialAction getDeleteAction() {
        return super.getDeleteAction();
    }

    public /* bridge */ /* synthetic */ Class getElementClass() {
        return super.getElementClass();
    }

    public /* bridge */ /* synthetic */ ExpressionType getExpressionType() {
        return super.getExpressionType();
    }

    public /* bridge */ /* synthetic */ Set getIndexNames() {
        return super.getIndexNames();
    }

    public /* bridge */ /* synthetic */ Initializer getInitializer() {
        return super.getInitializer();
    }

    public /* bridge */ /* synthetic */ Integer getLength() {
        return super.getLength();
    }

    public /* bridge */ /* synthetic */ Class getMapKeyClass() {
        return super.getMapKeyClass();
    }

    public /* bridge */ /* synthetic */ Supplier getMappedAttribute() {
        return super.getMappedAttribute();
    }

    public /* bridge */ /* synthetic */ String getName() {
        return super.getName();
    }

    public /* bridge */ /* synthetic */ Supplier getOrderByAttribute() {
        return super.getOrderByAttribute();
    }

    public /* bridge */ /* synthetic */ Order getOrderByDirection() {
        return super.getOrderByDirection();
    }

    public /* bridge */ /* synthetic */ PrimitiveKind getPrimitiveKind() {
        return super.getPrimitiveKind();
    }

    public /* bridge */ /* synthetic */ Property getProperty() {
        return super.getProperty();
    }

    public /* bridge */ /* synthetic */ String getPropertyName() {
        return super.getPropertyName();
    }

    public /* bridge */ /* synthetic */ Property getPropertyState() {
        return super.getPropertyState();
    }

    public /* bridge */ /* synthetic */ Supplier getReferencedAttribute() {
        return super.getReferencedAttribute();
    }

    public /* bridge */ /* synthetic */ Class getReferencedClass() {
        return super.getReferencedClass();
    }

    public /* bridge */ /* synthetic */ ReferentialAction getUpdateAction() {
        return super.getUpdateAction();
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ boolean isAssociation() {
        return super.isAssociation();
    }

    public /* bridge */ /* synthetic */ boolean isForeignKey() {
        return super.isForeignKey();
    }

    public /* bridge */ /* synthetic */ boolean isGenerated() {
        return super.isGenerated();
    }

    public /* bridge */ /* synthetic */ boolean isIndexed() {
        return super.isIndexed();
    }

    public /* bridge */ /* synthetic */ boolean isKey() {
        return super.isKey();
    }

    public /* bridge */ /* synthetic */ boolean isLazy() {
        return super.isLazy();
    }

    public /* bridge */ /* synthetic */ boolean isNullable() {
        return super.isNullable();
    }

    public /* bridge */ /* synthetic */ boolean isReadOnly() {
        return super.isReadOnly();
    }

    public /* bridge */ /* synthetic */ boolean isUnique() {
        return super.isUnique();
    }

    public /* bridge */ /* synthetic */ boolean isVersion() {
        return super.isVersion();
    }

    public /* bridge */ /* synthetic */ void setDeclaringType(Type type) {
        super.setDeclaringType(type);
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public AttributeBuilder(String str, Class<V> cls) {
        this.name = (String) Objects.requireNotNull(str);
        this.classType = (Class) Objects.requireNotNull(cls);
        this.primitiveKind = PrimitiveKind.fromClass(this.classType);
    }

    public AttributeBuilder<T, V> setCardinality(Cardinality cardinality) {
        this.cardinality = cardinality;
        return this;
    }

    public AttributeBuilder<T, V> setCascadeAction(CascadeAction... cascadeActionArr) {
        this.cascadeActions = EnumSet.copyOf(Arrays.asList(cascadeActionArr));
        return this;
    }

    public AttributeBuilder<T, V> setConverter(Converter<V, ?> converter) {
        this.converter = converter;
        return this;
    }

    public AttributeBuilder<T, V> setDeleteAction(ReferentialAction referentialAction) {
        this.deleteAction = referentialAction;
        return this;
    }

    public AttributeBuilder<T, V> setForeignKey(boolean z) {
        this.isForeignKey = z;
        return this;
    }

    public AttributeBuilder<T, V> setGenerated(boolean z) {
        this.isGenerated = z;
        return this;
    }

    public AttributeBuilder<T, V> setReadOnly(boolean z) {
        this.isReadOnly = z;
        return this;
    }

    public AttributeBuilder<T, V> setInitializer(Initializer<T, V> initializer) {
        this.initializer = initializer;
        return this;
    }

    public AttributeBuilder<T, V> setKey(boolean z) {
        this.isKey = z;
        return this;
    }

    public AttributeBuilder<T, V> setLazy(boolean z) {
        this.isLazy = z;
        return this;
    }

    public AttributeBuilder<T, V> setMappedAttribute(Supplier<Attribute> supplier) {
        this.mappedAttribute = supplier;
        return this;
    }

    public AttributeBuilder<T, V> setNullable(boolean z) {
        this.isNullable = z;
        return this;
    }

    public AttributeBuilder<T, V> setProperty(Property<T, V> property) {
        this.property = property;
        return this;
    }

    public AttributeBuilder<T, V> setPropertyName(String str) {
        this.propertyName = str;
        return this;
    }

    public AttributeBuilder<T, V> setPropertyState(Property<T, PropertyState> property) {
        this.propertyState = property;
        return this;
    }

    public AttributeBuilder<T, V> setReferencedAttribute(Supplier<Attribute> supplier) {
        this.referencedAttribute = supplier;
        return this;
    }

    public AttributeBuilder<T, V> setReferencedClass(Class<?> cls) {
        this.referencedClass = cls;
        return this;
    }

    public AttributeBuilder<T, V> setUnique(boolean z) {
        this.isUnique = z;
        return this;
    }

    public AttributeBuilder<T, V> setUpdateAction(ReferentialAction referentialAction) {
        this.updateAction = referentialAction;
        return this;
    }

    public QueryAttribute<T, V> build() {
        return new ImmutableAttribute(this);
    }
}
