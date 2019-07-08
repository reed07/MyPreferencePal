package io.requery.sql;

import io.requery.CascadeAction;
import io.requery.EntityCache;
import io.requery.PersistenceException;
import io.requery.Queryable;
import io.requery.meta.Attribute;
import io.requery.meta.EntityModel;
import io.requery.meta.QueryAttribute;
import io.requery.meta.Type;
import io.requery.proxy.CollectionChanges;
import io.requery.proxy.EntityProxy;
import io.requery.proxy.PropertyState;
import io.requery.proxy.Settable;
import io.requery.query.Condition;
import io.requery.query.Expression;
import io.requery.query.FieldExpression;
import io.requery.query.Scalar;
import io.requery.query.Where;
import io.requery.query.element.QueryElement;
import io.requery.query.element.QueryType;
import io.requery.util.Objects;
import io.requery.util.ObservableCollection;
import io.requery.util.function.Function;
import io.requery.util.function.Predicate;
import io.requery.util.function.Supplier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class EntityWriter<E extends S, S> {
    private final Attribute<E, ?>[] associativeAttributes;
    private final Attribute<E, ?>[] bindableAttributes;
    private final EntityCache cache = this.context.getCache();
    private final boolean cacheable;
    private final EntityContext<S> context;
    private final Class<E> entityClass;
    /* access modifiers changed from: private */
    public final String[] generatedColumnNames;
    private final boolean hasDefaultValues;
    private final boolean hasForeignKeys;
    private final boolean hasGeneratedKey;
    private final Attribute<E, ?> keyAttribute;
    private final int keyCount;
    /* access modifiers changed from: private */
    public final Mapping mapping = this.context.getMapping();
    private final EntityModel model = this.context.getModel();
    private final Function<E, EntityProxy<E>> proxyProvider;
    private final Queryable<S> queryable;
    private final boolean stateless;
    private final Type<E> type;
    /* access modifiers changed from: private */
    public final Attribute<E, ?> versionAttribute;
    /* access modifiers changed from: private */
    public final Attribute<E, ?>[] whereAttributes;

    private enum Cascade {
        AUTO,
        INSERT,
        UPDATE,
        UPSERT
    }

    EntityWriter(Type<E> type2, EntityContext<S> entityContext, Queryable<S> queryable2) {
        int i;
        this.type = (Type) Objects.requireNotNull(type2);
        this.context = (EntityContext) Objects.requireNotNull(entityContext);
        this.queryable = (Queryable) Objects.requireNotNull(queryable2);
        Iterator it = type2.getAttributes().iterator();
        int i2 = 0;
        Attribute<E, ?> attribute = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            i = 1;
            if (!it.hasNext()) {
                break;
            }
            Attribute<E, ?> attribute2 = (Attribute) it.next();
            if (attribute2.isKey() && attribute2.isGenerated()) {
                z = true;
            }
            if (attribute2.isVersion()) {
                attribute = attribute2;
            }
            if (attribute2.isForeignKey()) {
                z2 = true;
            }
            if (attribute2.getDefaultValue() != null) {
                z3 = true;
            }
        }
        this.hasGeneratedKey = z;
        this.hasForeignKeys = z2;
        this.versionAttribute = attribute;
        this.hasDefaultValues = z3;
        this.keyAttribute = type2.getSingleKeyAttribute();
        this.keyCount = type2.getKeyAttributes().size();
        Set<Attribute> keyAttributes = type2.getKeyAttributes();
        ArrayList arrayList = new ArrayList();
        for (Attribute attribute3 : keyAttributes) {
            if (attribute3.isGenerated()) {
                arrayList.add(attribute3.getName());
            }
        }
        this.generatedColumnNames = (String[]) arrayList.toArray(new String[arrayList.size()]);
        this.entityClass = type2.getClassType();
        this.proxyProvider = type2.getProxyProvider();
        this.cacheable = !type2.getKeyAttributes().isEmpty() && type2.isCacheable();
        this.stateless = type2.isStateless();
        this.bindableAttributes = Attributes.toArray(type2.getAttributes(), new Predicate<Attribute<E, ?>>() {
            public boolean test(Attribute<E, ?> attribute) {
                boolean z = attribute.isGenerated() && attribute.isKey();
                boolean z2 = attribute.isVersion() && EntityWriter.this.hasSystemVersionColumn();
                boolean z3 = attribute.isAssociation() && !attribute.isForeignKey() && !attribute.isKey();
                boolean isReadOnly = attribute.isReadOnly();
                if (z || z2 || z3 || isReadOnly) {
                    return false;
                }
                return true;
            }
        });
        this.associativeAttributes = Attributes.toArray(type2.getAttributes(), new Predicate<Attribute<E, ?>>() {
            public boolean test(Attribute<E, ?> attribute) {
                return attribute.isAssociation() && !attribute.getCascadeActions().contains(CascadeAction.NONE);
            }
        });
        if (this.keyCount == 0) {
            this.whereAttributes = Attributes.newArray(type2.getAttributes().size());
            type2.getAttributes().toArray(this.whereAttributes);
            return;
        }
        if (attribute == null) {
            i = 0;
        }
        this.whereAttributes = Attributes.newArray(this.keyCount + i);
        for (Attribute<E, ?> attribute4 : keyAttributes) {
            int i3 = i2 + 1;
            this.whereAttributes[i2] = attribute4;
            i2 = i3;
        }
        if (i != 0) {
            this.whereAttributes[i2] = attribute;
        }
    }

    private void checkRowsAffected(int i, E e, EntityProxy<E> entityProxy) {
        if (entityProxy != null) {
            Attribute<E, ?> attribute = this.versionAttribute;
            if (attribute != null && i == 0) {
                throw new OptimisticLockException(e, entityProxy.get(attribute));
            }
        }
        if (i != 1) {
            throw new RowCountException(1, (long) i);
        }
    }

    /* access modifiers changed from: private */
    public boolean hasSystemVersionColumn() {
        return !this.context.getPlatform().versionColumnDefinition().createColumn();
    }

    private S foreignKeyReference(EntityProxy<E> entityProxy, Attribute<E, ?> attribute) {
        if (!attribute.isForeignKey() || !attribute.isAssociation()) {
            return null;
        }
        return entityProxy.get(attribute);
    }

    /* access modifiers changed from: private */
    public void readGeneratedKeys(Settable<E> settable, ResultSet resultSet) throws SQLException {
        Attribute<E, ?> attribute = this.keyAttribute;
        if (attribute != null) {
            readKeyFromResult(attribute, settable, resultSet);
            return;
        }
        for (Attribute readKeyFromResult : this.type.getKeyAttributes()) {
            readKeyFromResult(readKeyFromResult, settable, resultSet);
        }
    }

    private void readKeyFromResult(Attribute<E, ?> attribute, Settable<E> settable, ResultSet resultSet) throws SQLException {
        int i;
        try {
            i = resultSet.findColumn(attribute.getName());
        } catch (SQLException unused) {
            i = 1;
        }
        if (attribute.getPrimitiveKind() != null) {
            switch (attribute.getPrimitiveKind()) {
                case INT:
                    settable.setInt(attribute, this.mapping.readInt(resultSet, i), PropertyState.LOADED);
                    return;
                case LONG:
                    settable.setLong(attribute, this.mapping.readLong(resultSet, i), PropertyState.LOADED);
                    return;
                default:
                    return;
            }
        } else {
            Object read = this.mapping.read((Expression) attribute, resultSet, i);
            if (read != null) {
                settable.setObject(attribute, read, PropertyState.LOADED);
                return;
            }
            throw new MissingKeyException();
        }
    }

    public int bindParameters(PreparedStatement preparedStatement, E e, Predicate<Attribute<E, ?>> predicate) throws SQLException {
        Attribute<E, ?>[] attributeArr;
        EntityProxy entityProxy = (EntityProxy) this.type.getProxyProvider().apply(e);
        int i = 0;
        for (Attribute<E, ?> attribute : this.bindableAttributes) {
            if (predicate == null || predicate.test(attribute)) {
                if (attribute.isAssociation()) {
                    this.mapping.write((Expression) attribute, preparedStatement, i + 1, entityProxy.getKey(attribute));
                } else if (attribute.getPrimitiveKind() != null) {
                    mapPrimitiveType(entityProxy, attribute, preparedStatement, i + 1);
                } else {
                    this.mapping.write((Expression) attribute, preparedStatement, i + 1, entityProxy.get(attribute, false));
                }
                entityProxy.setState(attribute, PropertyState.LOADED);
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: private */
    public void mapPrimitiveType(EntityProxy<E> entityProxy, Attribute<E, ?> attribute, PreparedStatement preparedStatement, int i) throws SQLException {
        switch (attribute.getPrimitiveKind()) {
            case INT:
                this.mapping.writeInt(preparedStatement, i, entityProxy.getInt(attribute));
                return;
            case LONG:
                this.mapping.writeLong(preparedStatement, i, entityProxy.getLong(attribute));
                return;
            case BYTE:
                this.mapping.writeByte(preparedStatement, i, entityProxy.getByte(attribute));
                return;
            case SHORT:
                this.mapping.writeShort(preparedStatement, i, entityProxy.getShort(attribute));
                return;
            case BOOLEAN:
                this.mapping.writeBoolean(preparedStatement, i, entityProxy.getBoolean(attribute));
                return;
            case FLOAT:
                this.mapping.writeFloat(preparedStatement, i, entityProxy.getFloat(attribute));
                return;
            case DOUBLE:
                this.mapping.writeDouble(preparedStatement, i, entityProxy.getDouble(attribute));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: 0000 */
    public void insert(E e, EntityProxy<E> entityProxy, GeneratedKeys<E> generatedKeys) {
        insert(e, entityProxy, Cascade.AUTO, generatedKeys);
    }

    /* JADX WARNING: type inference failed for: r12v12, types: [io.requery.proxy.Settable] */
    /* JADX WARNING: type inference failed for: r12v13 */
    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void insert(E r9, io.requery.proxy.EntityProxy<E> r10, io.requery.sql.EntityWriter.Cascade r11, final io.requery.sql.GeneratedKeys<E> r12) {
        /*
            r8 = this;
            boolean r0 = r8.hasGeneratedKey
            r1 = 0
            if (r0 == 0) goto L_0x000f
            if (r12 != 0) goto L_0x0008
            r12 = r10
        L_0x0008:
            io.requery.sql.EntityWriter$4 r0 = new io.requery.sql.EntityWriter$4
            r0.<init>(r12)
            r5 = r0
            goto L_0x0010
        L_0x000f:
            r5 = r1
        L_0x0010:
            io.requery.util.function.Predicate r12 = r8.filterDefaultValues(r10)
            io.requery.sql.EntityWriter$5 r0 = new io.requery.sql.EntityWriter$5
            io.requery.sql.EntityContext<S> r4 = r8.context
            r2 = r0
            r3 = r8
            r6 = r9
            r7 = r12
            r2.<init>(r4, r5, r6, r7)
            io.requery.query.element.QueryElement r2 = new io.requery.query.element.QueryElement
            io.requery.query.element.QueryType r3 = io.requery.query.element.QueryType.INSERT
            io.requery.meta.EntityModel r4 = r8.model
            r2.<init>(r3, r4, r0)
            r0 = 1
            java.lang.Class[] r0 = new java.lang.Class[r0]
            java.lang.Class<E> r3 = r8.entityClass
            r4 = 0
            r0[r4] = r3
            r2.from(r0)
            io.requery.meta.Attribute<E, ?>[] r0 = r8.associativeAttributes
            int r3 = r0.length
            r5 = 0
        L_0x0037:
            if (r5 >= r3) goto L_0x0043
            r6 = r0[r5]
            io.requery.sql.EntityWriter$Cascade r7 = io.requery.sql.EntityWriter.Cascade.INSERT
            r8.cascadeKeyReference(r7, r10, r6)
            int r5 = r5 + 1
            goto L_0x0037
        L_0x0043:
            r8.incrementVersion(r10)
            io.requery.meta.Attribute<E, ?>[] r0 = r8.bindableAttributes
            int r3 = r0.length
        L_0x0049:
            if (r4 >= r3) goto L_0x005e
            r5 = r0[r4]
            if (r12 == 0) goto L_0x0056
            boolean r6 = r12.test(r5)
            if (r6 != 0) goto L_0x0056
            goto L_0x005b
        L_0x0056:
            io.requery.query.Expression r5 = (io.requery.query.Expression) r5
            r2.value(r5, r1)
        L_0x005b:
            int r4 = r4 + 1
            goto L_0x0049
        L_0x005e:
            io.requery.sql.EntityContext<S> r12 = r8.context
            io.requery.sql.CompositeEntityListener r12 = r12.getStateListener()
            r12.preInsert(r9, r10)
            java.lang.Object r12 = r2.get()
            io.requery.query.Scalar r12 = (io.requery.query.Scalar) r12
            java.lang.Object r12 = r12.value()
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r8.checkRowsAffected(r12, r9, r1)
            io.requery.sql.EntityContext<S> r12 = r8.context
            java.lang.Class<E> r0 = r8.entityClass
            io.requery.sql.EntityReader r12 = r12.read(r0)
            r10.link(r12)
            r8.updateAssociations(r11, r9, r10, r1)
            io.requery.sql.EntityContext<S> r11 = r8.context
            io.requery.sql.CompositeEntityListener r11 = r11.getStateListener()
            r11.postInsert(r9, r10)
            boolean r11 = r8.cacheable
            if (r11 == 0) goto L_0x00a0
            io.requery.EntityCache r11 = r8.cache
            java.lang.Class<E> r12 = r8.entityClass
            java.lang.Object r10 = r10.key()
            r11.put(r12, r10, r9)
        L_0x00a0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.requery.sql.EntityWriter.insert(java.lang.Object, io.requery.proxy.EntityProxy, io.requery.sql.EntityWriter$Cascade, io.requery.sql.GeneratedKeys):void");
    }

    private Predicate<Attribute<E, ?>> filterDefaultValues(final EntityProxy<E> entityProxy) {
        if (this.hasDefaultValues) {
            return new Predicate<Attribute<E, ?>>() {
                public boolean test(Attribute<E, ?> attribute) {
                    return attribute.getDefaultValue() == null || entityProxy.getState(attribute) == PropertyState.MODIFIED;
                }
            };
        }
        return null;
    }

    public void upsert(E e, EntityProxy<E> entityProxy) {
        if (this.hasGeneratedKey) {
            if (hasKey(entityProxy)) {
                update(e, entityProxy, Cascade.UPSERT, null, null);
                return;
            }
            insert(e, entityProxy, Cascade.UPSERT, null);
        } else if (this.context.getPlatform().supportsUpsert()) {
            this.context.getStateListener().preUpdate(e, entityProxy);
            for (Attribute<E, ?> cascadeKeyReference : this.associativeAttributes) {
                cascadeKeyReference(Cascade.UPSERT, entityProxy, cascadeKeyReference);
            }
            incrementVersion(entityProxy);
            List<Attribute> asList = Arrays.asList(this.bindableAttributes);
            UpdateOperation updateOperation = new UpdateOperation(this.context);
            QueryElement queryElement = new QueryElement(QueryType.UPSERT, this.model, updateOperation);
            for (Attribute attribute : asList) {
                queryElement.value((Expression) attribute, entityProxy.get(attribute, false));
            }
            int intValue = ((Integer) updateOperation.evaluate(queryElement).value()).intValue();
            if (intValue > 0) {
                entityProxy.link(this.context.read(this.entityClass));
                updateAssociations(Cascade.UPSERT, e, entityProxy, null);
                if (this.cacheable) {
                    this.cache.put(this.entityClass, entityProxy.key(), e);
                }
                this.context.getStateListener().postUpdate(e, entityProxy);
                return;
            }
            throw new RowCountException(1, (long) intValue);
        } else {
            if (update(e, entityProxy, Cascade.UPSERT, null, null) == 0) {
                insert(e, entityProxy, Cascade.UPSERT, null);
            }
        }
    }

    public void update(E e, EntityProxy<E> entityProxy) {
        int update = update(e, entityProxy, Cascade.AUTO, null, null);
        if (update != -1) {
            checkRowsAffected(update, e, entityProxy);
        }
    }

    private int update(E e, EntityProxy<E> entityProxy, Cascade cascade, Predicate<Attribute<E, ?>> predicate, Predicate<Attribute<E, ?>> predicate2) {
        Predicate<Attribute<E, ?>> predicate3;
        Attribute<E, ?>[] attributeArr;
        Attribute<E, ?>[] attributeArr2;
        Object obj;
        Attribute<E, ?>[] attributeArr3;
        E e2 = e;
        EntityProxy<E> entityProxy2 = entityProxy;
        Cascade cascade2 = cascade;
        Predicate<Attribute<E, ?>> predicate4 = predicate2;
        this.context.getStateListener().preUpdate(e2, entityProxy2);
        if (predicate == null) {
            final ArrayList arrayList = new ArrayList();
            for (Attribute<E, ?> attribute : this.bindableAttributes) {
                if (this.stateless || entityProxy2.getState(attribute) == PropertyState.MODIFIED) {
                    arrayList.add(attribute);
                }
            }
            predicate3 = new Predicate<Attribute<E, ?>>() {
                public boolean test(Attribute<E, ?> attribute) {
                    return arrayList.contains(attribute) || (attribute == EntityWriter.this.versionAttribute && !EntityWriter.this.hasSystemVersionColumn());
                }
            };
        } else {
            predicate3 = predicate;
        }
        boolean z = this.versionAttribute != null;
        final Object incrementVersion = z ? incrementVersion(entityProxy2, predicate3) : null;
        final E e3 = e;
        AnonymousClass10 r13 = r0;
        final Predicate<Attribute<E, ?>> predicate5 = predicate3;
        Object obj2 = incrementVersion;
        final EntityProxy<E> entityProxy3 = entityProxy;
        AnonymousClass10 r0 = new EntityUpdateOperation(this.context, null) {
            public int bindParameters(PreparedStatement preparedStatement) throws SQLException {
                Attribute[] access$500;
                Object obj;
                int bindParameters = EntityWriter.this.bindParameters(preparedStatement, e3, predicate5);
                int i = bindParameters;
                for (Attribute attribute : EntityWriter.this.whereAttributes) {
                    if (attribute == EntityWriter.this.versionAttribute) {
                        EntityWriter.this.mapping.write((Expression) attribute, preparedStatement, i + 1, incrementVersion);
                    } else if (attribute.getPrimitiveKind() != null) {
                        EntityWriter.this.mapPrimitiveType(entityProxy3, attribute, preparedStatement, i + 1);
                    } else {
                        if (!attribute.isKey() || !attribute.isAssociation()) {
                            obj = entityProxy3.get(attribute, false);
                        } else {
                            obj = entityProxy3.getKey(attribute);
                        }
                        EntityWriter.this.mapping.write((Expression) attribute, preparedStatement, i + 1, obj);
                    }
                    i++;
                }
                return i;
            }
        };
        QueryElement queryElement = new QueryElement(QueryType.UPDATE, this.model, r13);
        queryElement.from(this.entityClass);
        int i = 0;
        for (Attribute<E, ?> attribute2 : this.bindableAttributes) {
            if (predicate3.test(attribute2)) {
                Object foreignKeyReference = foreignKeyReference(entityProxy2, attribute2);
                if (foreignKeyReference == null || this.stateless) {
                    obj = null;
                } else if (!attribute2.getCascadeActions().contains(CascadeAction.NONE)) {
                    entityProxy2.setState(attribute2, PropertyState.LOADED);
                    obj = null;
                    cascadeWrite(cascade2, foreignKeyReference, null);
                } else {
                    obj = null;
                }
                queryElement.set((Expression) attribute2, obj);
                i++;
            }
        }
        int i2 = -1;
        if (i > 0) {
            Attribute<E, ?> attribute3 = this.keyAttribute;
            if (attribute3 != null) {
                queryElement.where((Condition) Attributes.query(attribute3).equal("?"));
            } else {
                for (Attribute<E, ?> attribute4 : this.whereAttributes) {
                    if (attribute4 != this.versionAttribute) {
                        queryElement.where((Condition) Attributes.query(attribute4).equal("?"));
                    }
                }
            }
            if (z) {
                addVersionCondition(queryElement, obj2);
            }
            i2 = ((Integer) ((Scalar) queryElement.get()).value()).intValue();
            EntityReader read = this.context.read(this.entityClass);
            entityProxy2.link(read);
            if (z && hasSystemVersionColumn()) {
                read.refresh(e2, entityProxy2, (Attribute<E, ?>[]) new Attribute[]{this.versionAttribute});
            }
            if (i2 > 0) {
                updateAssociations(cascade2, e2, entityProxy2, predicate4);
            }
        } else {
            updateAssociations(cascade2, e2, entityProxy2, predicate4);
        }
        this.context.getStateListener().postUpdate(e2, entityProxy2);
        return i2;
    }

    private void addVersionCondition(Where<?> where, Object obj) {
        QueryAttribute query = Attributes.query(this.versionAttribute);
        VersionColumnDefinition versionColumnDefinition = this.context.getPlatform().versionColumnDefinition();
        String columnName = versionColumnDefinition.columnName();
        if (versionColumnDefinition.createColumn() || columnName == null) {
            where.where((Condition) query.equal(obj));
        } else {
            where.where(((FieldExpression) query.as(columnName)).equal(obj));
        }
    }

    private void updateAssociations(Cascade cascade, E e, EntityProxy<E> entityProxy, Predicate<Attribute<E, ?>> predicate) {
        Attribute<E, ?>[] attributeArr;
        for (Attribute<E, ?> attribute : this.associativeAttributes) {
            if ((predicate != null && predicate.test(attribute)) || this.stateless || entityProxy.getState(attribute) == PropertyState.MODIFIED) {
                updateAssociation(cascade, e, entityProxy, attribute);
            }
        }
    }

    private void updateAssociation(Cascade cascade, E e, EntityProxy<E> entityProxy, Attribute<E, ?> attribute) {
        E e2;
        CollectionChanges collectionChanges;
        boolean z;
        Cascade cascade2 = cascade;
        E e3 = e;
        EntityProxy<E> entityProxy2 = entityProxy;
        Attribute<E, ?> attribute2 = attribute;
        boolean z2 = false;
        switch (attribute.getCardinality()) {
            case ONE_TO_ONE:
                e2 = e3;
                Object obj = entityProxy2.get(attribute2, false);
                if (obj != null) {
                    QueryAttribute queryAttribute = Attributes.get(attribute.getMappedAttribute());
                    EntityProxy proxyOf = this.context.proxyOf(obj, true);
                    proxyOf.set(queryAttribute, e2, PropertyState.MODIFIED);
                    cascadeWrite(cascade2, obj, proxyOf);
                    break;
                } else if (!this.stateless) {
                    throw new PersistenceException("1-1 relationship can only be removed from the owning side");
                }
                break;
            case ONE_TO_MANY:
                Object obj2 = entityProxy2.get(attribute2, false);
                if (obj2 instanceof ObservableCollection) {
                    CollectionChanges collectionChanges2 = (CollectionChanges) ((ObservableCollection) obj2).observer();
                    ArrayList<Object> arrayList = new ArrayList<>(collectionChanges2.addedElements());
                    ArrayList<Object> arrayList2 = new ArrayList<>(collectionChanges2.removedElements());
                    collectionChanges2.clear();
                    for (Object updateMappedAssociation : arrayList) {
                        updateMappedAssociation(cascade2, updateMappedAssociation, attribute2, e);
                    }
                    e2 = e;
                    for (Object updateMappedAssociation2 : arrayList2) {
                        updateMappedAssociation(Cascade.UPDATE, updateMappedAssociation2, attribute2, null);
                    }
                    break;
                } else {
                    e2 = e;
                    if (obj2 instanceof Iterable) {
                        for (Object updateMappedAssociation3 : (Iterable) obj2) {
                            updateMappedAssociation(cascade2, updateMappedAssociation3, attribute2, e2);
                        }
                        break;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("unsupported relation type ");
                        sb.append(obj2);
                        throw new IllegalStateException(sb.toString());
                    }
                }
            case MANY_TO_MANY:
                Class referencedClass = attribute.getReferencedClass();
                if (referencedClass != null) {
                    Type typeOf = this.model.typeOf(referencedClass);
                    QueryAttribute queryAttribute2 = null;
                    QueryAttribute queryAttribute3 = null;
                    for (Attribute attribute3 : typeOf.getAttributes()) {
                        Class referencedClass2 = attribute3.getReferencedClass();
                        if (referencedClass2 != null) {
                            if (queryAttribute2 == null && this.entityClass.isAssignableFrom(referencedClass2)) {
                                queryAttribute2 = Attributes.query(attribute3);
                            } else if (attribute.getElementClass() != null && attribute.getElementClass().isAssignableFrom(referencedClass2)) {
                                queryAttribute3 = Attributes.query(attribute3);
                            }
                        }
                    }
                    Objects.requireNotNull(queryAttribute2);
                    Objects.requireNotNull(queryAttribute3);
                    QueryAttribute queryAttribute4 = Attributes.get(queryAttribute2.getReferencedAttribute());
                    QueryAttribute queryAttribute5 = Attributes.get(queryAttribute3.getReferencedAttribute());
                    Object obj3 = entityProxy2.get(attribute2, false);
                    Iterable iterable = (Iterable) obj3;
                    boolean z3 = obj3 instanceof ObservableCollection;
                    if (z3) {
                        collectionChanges = (CollectionChanges) ((ObservableCollection) obj3).observer();
                        if (collectionChanges != null) {
                            iterable = collectionChanges.addedElements();
                        }
                    } else {
                        collectionChanges = null;
                    }
                    Iterator it = iterable.iterator();
                    while (it.hasNext()) {
                        Object next = it.next();
                        Object obj4 = typeOf.getFactory().get();
                        Iterator it2 = it;
                        EntityProxy proxyOf2 = this.context.proxyOf(obj4, z2);
                        EntityProxy proxyOf3 = this.context.proxyOf(next, z2);
                        if (attribute.getCascadeActions().contains(CascadeAction.SAVE)) {
                            cascadeWrite(cascade2, next, proxyOf3);
                            z = false;
                        } else {
                            z = false;
                        }
                        Object obj5 = entityProxy2.get(queryAttribute4, z);
                        Object obj6 = proxyOf3.get(queryAttribute5, z);
                        proxyOf2.set(queryAttribute2, obj5, PropertyState.MODIFIED);
                        proxyOf2.set(queryAttribute3, obj6, PropertyState.MODIFIED);
                        cascadeWrite((!z3 || cascade2 != Cascade.UPSERT) ? Cascade.INSERT : Cascade.UPSERT, obj4, null);
                        it = it2;
                        E e4 = e;
                        Attribute<E, ?> attribute4 = attribute;
                        z2 = false;
                    }
                    if (collectionChanges == null) {
                        attribute2 = attribute;
                        e2 = e;
                        break;
                    } else {
                        boolean z4 = false;
                        Object obj7 = entityProxy2.get(queryAttribute4, false);
                        for (Object proxyOf4 : collectionChanges.removedElements()) {
                            int intValue = ((Integer) ((Scalar) ((Supplier) this.queryable.delete(typeOf.getClassType()).where((Condition) queryAttribute2.equal(obj7)).and((Condition) queryAttribute3.equal(this.context.proxyOf(proxyOf4, z4).get(queryAttribute5)))).get()).value()).intValue();
                            if (intValue == 1) {
                                z4 = false;
                            } else {
                                throw new RowCountException(1, (long) intValue);
                            }
                        }
                        collectionChanges.clear();
                        attribute2 = attribute;
                        e2 = e;
                        break;
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Invalid referenced class in ");
                    sb2.append(attribute);
                    throw new IllegalStateException(sb2.toString());
                }
                break;
            default:
                e2 = e3;
                break;
        }
        this.context.read(this.type.getClassType()).refresh(e2, entityProxy2, (Attribute<E, ?>[]) new Attribute[]{attribute2});
    }

    private void incrementVersion(EntityProxy<E> entityProxy) {
        Object obj;
        if (this.versionAttribute != null && !hasSystemVersionColumn()) {
            Object obj2 = entityProxy.get(this.versionAttribute);
            Class<Timestamp> classType = this.versionAttribute.getClassType();
            if (classType == Long.class || classType == Long.TYPE) {
                obj = obj2 == null ? Long.valueOf(1) : Long.valueOf(((Long) obj2).longValue() + 1);
            } else if (classType == Integer.class || classType == Integer.TYPE) {
                obj = obj2 == null ? Integer.valueOf(1) : Integer.valueOf(((Integer) obj2).intValue() + 1);
            } else if (classType == Timestamp.class) {
                obj = new Timestamp(System.currentTimeMillis());
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Unsupported version type: ");
                sb.append(this.versionAttribute.getClassType());
                throw new PersistenceException(sb.toString());
            }
            entityProxy.setObject(this.versionAttribute, obj, PropertyState.MODIFIED);
        }
    }

    private Object incrementVersion(EntityProxy<E> entityProxy, Predicate<Attribute<E, ?>> predicate) {
        Attribute<E, ?>[] attributeArr = this.bindableAttributes;
        int length = attributeArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Attribute<E, ?> attribute = attributeArr[i];
            if (attribute != this.versionAttribute && predicate.test(attribute)) {
                z = true;
                break;
            }
            i++;
        }
        Object obj = entityProxy.get(this.versionAttribute, true);
        if (z) {
            if (obj != null) {
                incrementVersion(entityProxy);
            } else {
                throw new MissingVersionException(entityProxy);
            }
        }
        return obj;
    }

    private void updateMappedAssociation(Cascade cascade, S s, Attribute attribute, Object obj) {
        EntityProxy proxyOf = this.context.proxyOf(s, false);
        proxyOf.set(Attributes.get(attribute.getMappedAttribute()), obj, PropertyState.MODIFIED);
        cascadeWrite(cascade, s, proxyOf);
    }

    private void cascadeKeyReference(Cascade cascade, EntityProxy<E> entityProxy, Attribute<E, ?> attribute) {
        Object foreignKeyReference = foreignKeyReference(entityProxy, attribute);
        if (foreignKeyReference != null && entityProxy.getState(attribute) == PropertyState.MODIFIED && !this.context.proxyOf(foreignKeyReference, false).isLinked()) {
            entityProxy.setState(attribute, PropertyState.LOADED);
            cascadeWrite(cascade, foreignKeyReference, null);
        }
    }

    private <U extends S> void cascadeWrite(Cascade cascade, U u, EntityProxy<U> entityProxy) {
        Cascade cascade2;
        if (u != null) {
            EntityProxy<U> proxyOf = entityProxy == null ? this.context.proxyOf(u, false) : entityProxy;
            EntityWriter write = this.context.write(proxyOf.type().getClassType());
            if (cascade == Cascade.AUTO) {
                cascade2 = proxyOf.isLinked() ? Cascade.UPDATE : Cascade.UPSERT;
            } else {
                cascade2 = cascade;
            }
            switch (cascade2) {
                case INSERT:
                    write.insert(u, proxyOf, cascade2, null);
                    return;
                case UPDATE:
                    write.update(u, proxyOf, cascade2, null, null);
                    return;
                case UPSERT:
                    write.upsert(u, proxyOf);
                    return;
                default:
                    return;
            }
        }
    }

    private <U extends S> boolean hasKey(EntityProxy<U> entityProxy) {
        Type type2 = entityProxy.type();
        if (this.keyCount <= 0) {
            return false;
        }
        for (Attribute state : type2.getKeyAttributes()) {
            PropertyState state2 = entityProxy.getState(state);
            if (state2 != PropertyState.MODIFIED && state2 != PropertyState.LOADED) {
                return false;
            }
        }
        return true;
    }
}
