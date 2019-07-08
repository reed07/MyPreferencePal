package io.requery.meta;

final class ImmutableAttribute<T, V> extends BaseAttribute<T, V> {
    ImmutableAttribute(AttributeBuilder<T, V> attributeBuilder) {
        this.builderProperty = attributeBuilder.getBuilderProperty();
        this.cardinality = attributeBuilder.getCardinality();
        this.cascadeActions = attributeBuilder.getCascadeActions();
        this.classType = attributeBuilder.getClassType();
        this.collate = attributeBuilder.getCollate();
        this.converter = attributeBuilder.getConverter();
        this.defaultValue = attributeBuilder.getDefaultValue();
        this.definition = attributeBuilder.getDefinition();
        this.deleteAction = attributeBuilder.getDeleteAction();
        this.elementClass = attributeBuilder.getElementClass();
        this.indexNames = attributeBuilder.getIndexNames();
        this.initializer = attributeBuilder.getInitializer();
        this.isForeignKey = attributeBuilder.isForeignKey();
        this.isGenerated = attributeBuilder.isGenerated();
        this.isIndex = attributeBuilder.isIndexed();
        this.isKey = attributeBuilder.isKey();
        this.isLazy = attributeBuilder.isLazy();
        this.isNullable = attributeBuilder.isNullable();
        this.isReadOnly = attributeBuilder.isReadOnly();
        this.isUnique = attributeBuilder.isUnique();
        this.isVersion = attributeBuilder.isVersion();
        this.length = attributeBuilder.getLength();
        this.mapKeyClass = attributeBuilder.getMapKeyClass();
        this.mappedAttribute = attributeBuilder.getMappedAttribute();
        this.name = attributeBuilder.getName();
        this.orderByAttribute = attributeBuilder.getOrderByAttribute();
        this.orderByDirection = attributeBuilder.getOrderByDirection();
        this.primitiveKind = attributeBuilder.getPrimitiveKind();
        this.property = attributeBuilder.getProperty();
        this.propertyName = attributeBuilder.getPropertyName();
        this.propertyState = attributeBuilder.getPropertyState();
        this.referencedAttribute = attributeBuilder.getReferencedAttribute();
        this.referencedClass = attributeBuilder.getReferencedClass();
        this.updateAction = attributeBuilder.getUpdateAction();
    }
}
