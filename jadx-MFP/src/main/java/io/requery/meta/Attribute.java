package io.requery.meta;

import io.requery.CascadeAction;
import io.requery.Converter;
import io.requery.ReferentialAction;
import io.requery.proxy.Initializer;
import io.requery.proxy.Property;
import io.requery.proxy.PropertyState;
import io.requery.query.Order;
import io.requery.util.function.Supplier;
import java.util.Set;

public interface Attribute<T, V> {
    Property<?, V> getBuilderProperty();

    Cardinality getCardinality();

    Set<CascadeAction> getCascadeActions();

    Class<V> getClassType();

    String getCollate();

    Converter<V, ?> getConverter();

    Type<T> getDeclaringType();

    String getDefaultValue();

    String getDefinition();

    ReferentialAction getDeleteAction();

    Class<?> getElementClass();

    Set<String> getIndexNames();

    Initializer<T, V> getInitializer();

    Integer getLength();

    Supplier<Attribute> getMappedAttribute();

    String getName();

    Supplier<Attribute> getOrderByAttribute();

    Order getOrderByDirection();

    PrimitiveKind getPrimitiveKind();

    Property<T, V> getProperty();

    Property<T, PropertyState> getPropertyState();

    Supplier<Attribute> getReferencedAttribute();

    Class<?> getReferencedClass();

    ReferentialAction getUpdateAction();

    boolean isAssociation();

    boolean isForeignKey();

    boolean isGenerated();

    boolean isIndexed();

    boolean isKey();

    boolean isLazy();

    boolean isNullable();

    boolean isReadOnly();

    boolean isUnique();

    boolean isVersion();
}
