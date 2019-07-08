package io.requery.sql;

import io.requery.EntityCache;
import io.requery.PersistenceException;
import io.requery.Queryable;
import io.requery.meta.Attribute;
import io.requery.meta.QueryAttribute;
import io.requery.meta.Type;
import io.requery.proxy.CompositeKey;
import io.requery.proxy.EntityBuilderProxy;
import io.requery.proxy.EntityProxy;
import io.requery.proxy.Initializer;
import io.requery.proxy.PropertyLoader;
import io.requery.proxy.PropertyState;
import io.requery.proxy.QueryInitializer;
import io.requery.proxy.Settable;
import io.requery.query.AliasedExpression;
import io.requery.query.Condition;
import io.requery.query.Expression;
import io.requery.query.Functional;
import io.requery.query.Result;
import io.requery.query.WhereAndOr;
import io.requery.sql.QueryBuilder.Appender;
import io.requery.util.FilteringIterator;
import io.requery.util.Objects;
import io.requery.util.function.Predicate;
import io.requery.util.function.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

class EntityReader<E extends S, S> implements PropertyLoader<E> {
    private final EntityCache cache = this.context.getCache();
    private final boolean cacheable;
    /* access modifiers changed from: private */
    public final EntityContext<S> context;
    private final Set<Expression<?>> defaultSelection;
    private final Attribute<E, ?>[] defaultSelectionAttributes;
    private final QueryAttribute<E, ?> keyAttribute;
    private final Mapping mapping = this.context.getMapping();
    private final Queryable<S> queryable;
    private final boolean stateless;
    private final Type<E> type;

    EntityReader(Type<E> type2, EntityContext<S> entityContext, Queryable<S> queryable2) {
        this.type = (Type) Objects.requireNotNull(type2);
        this.context = (EntityContext) Objects.requireNotNull(entityContext);
        this.queryable = (Queryable) Objects.requireNotNull(queryable2);
        this.stateless = type2.isStateless();
        this.cacheable = type2.isCacheable();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        for (Attribute attribute : type2.getAttributes()) {
            boolean z = attribute.isForeignKey() || attribute.isKey();
            if (!attribute.isLazy() && (z || !attribute.isAssociation())) {
                if (attribute.isVersion()) {
                    linkedHashSet.add(aliasVersion(attribute));
                } else {
                    linkedHashSet.add((Expression) attribute);
                }
                linkedHashSet2.add(attribute);
            }
        }
        this.defaultSelection = Collections.unmodifiableSet(linkedHashSet);
        this.keyAttribute = Attributes.query(type2.getSingleKeyAttribute());
        this.defaultSelectionAttributes = Attributes.toArray(linkedHashSet2, new Predicate<Attribute<E, ?>>() {
            public boolean test(Attribute<E, ?> attribute) {
                return true;
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public Set<Expression<?>> defaultSelection() {
        return this.defaultSelection;
    }

    /* access modifiers changed from: 0000 */
    public Attribute<E, ?>[] defaultSelectionAttributes() {
        return this.defaultSelectionAttributes;
    }

    /* access modifiers changed from: 0000 */
    public ResultReader<E> newResultReader(Attribute[] attributeArr) {
        if (this.type.isBuildable()) {
            return new BuildableEntityResultReader(this, attributeArr);
        }
        return new EntityResultReader(this, attributeArr);
    }

    private Expression aliasVersion(Attribute attribute) {
        String columnName = this.context.getPlatform().versionColumnDefinition().columnName();
        if (!attribute.isVersion() || columnName == null) {
            return (Expression) attribute;
        }
        Expression expression = (Expression) attribute;
        return new AliasedExpression(expression, columnName, expression.getName());
    }

    public <V> void load(E e, EntityProxy<E> entityProxy, Attribute<E, V> attribute) {
        refresh(e, entityProxy, (Attribute<E, ?>[]) new Attribute[]{attribute});
    }

    public E refresh(E e, EntityProxy<E> entityProxy) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Attribute attribute : this.type.getAttributes()) {
            if (this.stateless || entityProxy.getState(attribute) == PropertyState.LOADED) {
                linkedHashSet.add(attribute);
            }
        }
        return refresh(e, entityProxy, (Set<Attribute<E, ?>>) linkedHashSet);
    }

    @SafeVarargs
    public final E refresh(E e, EntityProxy<E> entityProxy, Attribute<E, ?>... attributeArr) {
        Set set;
        if (attributeArr == null || attributeArr.length == 0) {
            return e;
        }
        if (attributeArr.length == 1) {
            set = Collections.singleton(attributeArr[0]);
        } else {
            Set linkedHashSet = new LinkedHashSet(attributeArr.length);
            Collections.addAll(linkedHashSet, attributeArr);
            set = linkedHashSet;
        }
        return refresh(e, entityProxy, set);
    }

    private E refresh(E e, EntityProxy<E> entityProxy, final Set<Attribute<E, ?>> set) {
        Connection connection;
        Throwable th;
        PreparedStatement prepareStatement;
        Throwable th2;
        Throwable th3;
        FilteringIterator filteringIterator = new FilteringIterator(set.iterator(), new Predicate<Attribute<E, ?>>() {
            public boolean test(Attribute<E, ?> attribute) {
                return set.contains(attribute) && (!attribute.isAssociation() || attribute.isForeignKey());
            }
        });
        if (filteringIterator.hasNext()) {
            int i = 1;
            String queryBuilder = new QueryBuilder(this.context.getQueryBuilderOptions()).keyword(Keyword.SELECT).commaSeparated((Iterator<? extends T>) filteringIterator, (Appender<T>) new Appender<Attribute<E, ?>>() {
                public void append(QueryBuilder queryBuilder, Attribute<E, ?> attribute) {
                    String columnName = EntityReader.this.context.getPlatform().versionColumnDefinition().columnName();
                    if (!attribute.isVersion() || columnName == null) {
                        queryBuilder.attribute(attribute);
                    } else {
                        queryBuilder.append(columnName).space().append(Keyword.AS).space().append(attribute.getName()).space();
                    }
                }
            }).keyword(Keyword.FROM).tableName(this.type.getName()).keyword(Keyword.WHERE).appendWhereConditions(this.type.getKeyAttributes()).toString();
            try {
                connection = this.context.getConnection();
                try {
                    prepareStatement = connection.prepareStatement(queryBuilder);
                    try {
                        for (Attribute attribute : this.type.getKeyAttributes()) {
                            Object key = entityProxy.getKey(attribute);
                            if (key != null) {
                                int i2 = i + 1;
                                this.mapping.write((Expression) attribute, prepareStatement, i, key);
                                i = i2;
                            } else {
                                throw new MissingKeyException(entityProxy);
                            }
                        }
                        this.context.getStatementListener().beforeExecuteQuery(prepareStatement, queryBuilder, null);
                        ResultSet executeQuery = prepareStatement.executeQuery();
                        this.context.getStatementListener().afterExecuteQuery(prepareStatement);
                        if (executeQuery.next()) {
                            Attribute[] attributeArr = new Attribute[set.size()];
                            set.toArray(attributeArr);
                            if (this.type.isImmutable()) {
                                e = fromBuilder(executeQuery, attributeArr);
                            } else {
                                e = fromResult(e, executeQuery, attributeArr);
                            }
                        }
                        if (prepareStatement != null) {
                            prepareStatement.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (Throwable th4) {
                        Throwable th5 = th4;
                        th2 = r12;
                        th3 = th5;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    throw th;
                }
            } catch (SQLException e2) {
                throw new PersistenceException((Throwable) e2);
            } catch (Throwable th7) {
                th.addSuppressed(th7);
            }
        }
        for (Attribute attribute2 : set) {
            if (attribute2.isAssociation()) {
                refreshAssociation(entityProxy, attribute2);
            }
        }
        return e;
        if (prepareStatement != null) {
            if (th2 != null) {
                prepareStatement.close();
            } else {
                prepareStatement.close();
            }
        }
        throw th3;
        throw th3;
        throw th;
    }

    private <V> void refreshAssociation(EntityProxy<E> entityProxy, Attribute<E, V> attribute) {
        Supplier associativeQuery = associativeQuery(entityProxy, attribute);
        switch (attribute.getCardinality()) {
            case ONE_TO_ONE:
            case MANY_TO_ONE:
                entityProxy.set(attribute, attribute.getClassType().cast(associativeQuery == null ? null : ((Result) associativeQuery.get()).firstOrNull()), PropertyState.LOADED);
                return;
            case ONE_TO_MANY:
            case MANY_TO_MANY:
                Initializer initializer = attribute.getInitializer();
                if (initializer instanceof QueryInitializer) {
                    entityProxy.set(attribute, ((QueryInitializer) initializer).initialize(entityProxy, attribute, associativeQuery), PropertyState.LOADED);
                    return;
                }
                return;
            default:
                throw new IllegalStateException();
        }
    }

    private <Q extends S> Supplier<? extends Result<Q>> associativeQuery(EntityProxy<E> entityProxy, Attribute<E, ?> attribute) {
        Object obj;
        Class cls;
        QueryAttribute queryAttribute;
        QueryAttribute queryAttribute2 = null;
        switch (attribute.getCardinality()) {
            case ONE_TO_ONE:
            case MANY_TO_ONE:
            case ONE_TO_MANY:
                if (attribute.isForeignKey()) {
                    queryAttribute = Attributes.get(attribute.getReferencedAttribute());
                    cls = queryAttribute.getDeclaringType().getClassType();
                    Object cast = cls.cast(entityProxy.get(attribute, false));
                    if (cast == null) {
                        return null;
                    }
                    obj = ((EntityProxy) this.context.getModel().typeOf(cls).getProxyProvider().apply(cast)).get(queryAttribute);
                } else {
                    queryAttribute = Attributes.get(attribute.getMappedAttribute());
                    cls = queryAttribute.getDeclaringType().getClassType();
                    obj = entityProxy.get(Attributes.get(queryAttribute.getReferencedAttribute()));
                }
                return order(this.queryable.select(cls, new QueryAttribute[0]).where((Condition) queryAttribute.equal(obj)), attribute.getOrderByAttribute());
            case MANY_TO_MANY:
                Class elementClass = attribute.getElementClass();
                Type typeOf = this.context.getModel().typeOf(attribute.getReferencedClass());
                QueryAttribute queryAttribute3 = null;
                for (Attribute attribute2 : typeOf.getAttributes()) {
                    Class referencedClass = attribute2.getReferencedClass();
                    if (referencedClass != null) {
                        if (queryAttribute2 == null && this.type.getClassType().isAssignableFrom(referencedClass)) {
                            queryAttribute2 = Attributes.query(attribute2);
                        } else if (elementClass.isAssignableFrom(referencedClass)) {
                            queryAttribute3 = Attributes.query(attribute2);
                        }
                    }
                }
                Objects.requireNotNull(queryAttribute2);
                Objects.requireNotNull(queryAttribute3);
                QueryAttribute queryAttribute4 = Attributes.get(queryAttribute2.getReferencedAttribute());
                QueryAttribute queryAttribute5 = Attributes.get(queryAttribute3.getReferencedAttribute());
                Object obj2 = entityProxy.get(queryAttribute4);
                if (obj2 != null) {
                    return order(this.queryable.select(elementClass, new QueryAttribute[0]).join(typeOf.getClassType()).on((Condition) queryAttribute5.equal((Expression<V>) queryAttribute3)).join(this.type.getClassType()).on((Condition) queryAttribute2.equal((Expression<V>) queryAttribute4)).where((Condition) queryAttribute4.equal(obj2)), attribute.getOrderByAttribute());
                }
                throw new IllegalStateException();
            default:
                throw new IllegalStateException();
        }
    }

    private <Q extends S> Supplier<? extends Result<Q>> order(WhereAndOr<? extends Result<Q>> whereAndOr, Supplier<Attribute> supplier) {
        if (supplier != null) {
            Attribute attribute = (Attribute) supplier.get();
            if (attribute.getOrderByDirection() != null && (attribute instanceof Functional)) {
                switch (attribute.getOrderByDirection()) {
                    case ASC:
                        whereAndOr.orderBy((Expression<V>) ((Functional) attribute).asc());
                        break;
                    case DESC:
                        whereAndOr.orderBy((Expression<V>) ((Functional) attribute).desc());
                        break;
                }
            } else {
                whereAndOr.orderBy((Expression) attribute);
            }
        }
        return whereAndOr;
    }

    private E createEntity() {
        E e = this.type.getFactory().get();
        ((EntityProxy) this.type.getProxyProvider().apply(e)).link(this);
        return e;
    }

    private Object readCacheKey(ResultSet resultSet) throws SQLException {
        QueryAttribute<E, ?> queryAttribute = this.keyAttribute;
        if (queryAttribute != null) {
            return readKey(queryAttribute, resultSet, resultSet.findColumn(queryAttribute.getName()));
        }
        int size = this.type.getKeyAttributes().size();
        if (size <= 1) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(size);
        for (Attribute attribute : this.type.getKeyAttributes()) {
            linkedHashMap.put(attribute, readKey(attribute, resultSet, resultSet.findColumn(attribute.getName())));
        }
        return new CompositeKey(linkedHashMap);
    }

    private Object readKey(Attribute<E, ?> attribute, ResultSet resultSet, int i) throws SQLException {
        if (attribute.isAssociation()) {
            attribute = Attributes.get(attribute.getReferencedAttribute());
        }
        return this.mapping.read((Expression) attribute, resultSet, i);
    }

    /* access modifiers changed from: 0000 */
    public final E fromResult(E e, ResultSet resultSet, Attribute[] attributeArr) throws SQLException {
        E e2;
        E e3;
        ResultSet resultSet2 = resultSet;
        Attribute[] attributeArr2 = attributeArr;
        boolean z = false;
        boolean z2 = e != null || this.stateless;
        if (e != null) {
            e2 = e;
        } else if (this.cacheable) {
            synchronized (this.type) {
                Object readCacheKey = readCacheKey(resultSet2);
                e3 = readCacheKey != null ? this.cache.get(this.type.getClassType(), readCacheKey) : e;
                if (e3 == null) {
                    e3 = createEntity();
                    if (readCacheKey != null) {
                        this.cache.put(this.type.getClassType(), readCacheKey, e3);
                    }
                }
            }
            e2 = e3;
        } else {
            e2 = createEntity();
        }
        EntityProxy entityProxy = (EntityProxy) this.type.getProxyProvider().apply(e2);
        synchronized (entityProxy.syncObject()) {
            entityProxy.link(this);
            int length = attributeArr2.length;
            int i = 0;
            int i2 = 1;
            while (i < length) {
                Attribute attribute = attributeArr2[i];
                boolean isAssociation = attribute.isAssociation();
                if ((attribute.isForeignKey() || attribute.isKey()) && isAssociation) {
                    Object read = this.mapping.read(Attributes.get(attribute.getReferencedAttribute()), resultSet2, i2);
                    if (read != null) {
                        Object obj = entityProxy.get(attribute, z);
                        if (obj == null) {
                            obj = this.context.read(attribute.getClassType()).createEntity();
                        }
                        this.context.proxyOf(obj, z).set(Attributes.get(attribute.getReferencedAttribute()), read, PropertyState.LOADED);
                        PropertyState propertyState = PropertyState.LOADED;
                        if (!this.stateless) {
                            propertyState = entityProxy.getState(attribute);
                            if (propertyState != PropertyState.LOADED) {
                                propertyState = PropertyState.FETCH;
                            }
                        }
                        entityProxy.setObject(attribute, obj, propertyState);
                    }
                } else if (isAssociation) {
                    i++;
                    z = false;
                } else if (z2 || entityProxy.getState(attribute) != PropertyState.MODIFIED) {
                    if (attribute.getPrimitiveKind() != null) {
                        readPrimitiveField(entityProxy, attribute, resultSet2, i2);
                    } else {
                        entityProxy.setObject(attribute, this.mapping.read((Expression) attribute, resultSet2, i2), PropertyState.LOADED);
                    }
                }
                i2++;
                i++;
                z = false;
            }
        }
        this.context.getStateListener().postLoad(e2, entityProxy);
        return e2;
    }

    /* access modifiers changed from: 0000 */
    public final <B> E fromBuilder(ResultSet resultSet, Attribute[] attributeArr) throws SQLException {
        EntityBuilderProxy entityBuilderProxy = new EntityBuilderProxy(this.type);
        int i = 1;
        for (Attribute attribute : attributeArr) {
            if (attribute.getPrimitiveKind() != null) {
                readPrimitiveField(entityBuilderProxy, attribute, resultSet, i);
            } else {
                entityBuilderProxy.setObject(attribute, this.mapping.read((Expression) attribute, resultSet, i), PropertyState.LOADED);
            }
            i++;
        }
        return entityBuilderProxy.build();
    }

    private void readPrimitiveField(Settable<E> settable, Attribute<E, ?> attribute, ResultSet resultSet, int i) throws SQLException {
        switch (attribute.getPrimitiveKind()) {
            case INT:
                settable.setInt(attribute, this.mapping.readInt(resultSet, i), PropertyState.LOADED);
                return;
            case LONG:
                settable.setLong(attribute, this.mapping.readLong(resultSet, i), PropertyState.LOADED);
                return;
            case SHORT:
                settable.setShort(attribute, this.mapping.readShort(resultSet, i), PropertyState.LOADED);
                return;
            case BYTE:
                settable.setByte(attribute, this.mapping.readByte(resultSet, i), PropertyState.LOADED);
                return;
            case BOOLEAN:
                settable.setBoolean(attribute, this.mapping.readBoolean(resultSet, i), PropertyState.LOADED);
                return;
            case FLOAT:
                settable.setFloat(attribute, this.mapping.readFloat(resultSet, i), PropertyState.LOADED);
                return;
            case DOUBLE:
                settable.setDouble(attribute, this.mapping.readDouble(resultSet, i), PropertyState.LOADED);
                return;
            default:
                return;
        }
    }
}
