package io.requery.sql;

import io.requery.Converter;
import io.requery.PersistenceException;
import io.requery.ReferentialAction;
import io.requery.meta.Attribute;
import io.requery.meta.EntityModel;
import io.requery.meta.Type;
import io.requery.sql.QueryBuilder.Appender;
import io.requery.sql.QueryBuilder.Options;
import io.requery.sql.platform.PlatformDelegate;
import io.requery.sql.type.IntegerType;
import io.requery.util.Objects;
import io.requery.util.function.Predicate;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Set;

public class SchemaModifier implements ConnectionProvider {
    private final Configuration configuration;
    private final ConnectionProvider connectionProvider;
    private Mapping mapping;
    private final EntityModel model;
    /* access modifiers changed from: private */
    public Platform platform;
    private Options queryOptions;
    private final CompositeStatementListener statementListeners;

    public SchemaModifier(Configuration configuration2) {
        this.configuration = configuration2;
        this.connectionProvider = configuration2.getConnectionProvider();
        this.platform = configuration2.getPlatform();
        this.model = (EntityModel) Objects.requireNotNull(configuration2.getModel());
        this.mapping = configuration2.getMapping();
        this.statementListeners = new CompositeStatementListener(configuration2.getStatementListeners());
        if (configuration2.getUseDefaultLogging()) {
            this.statementListeners.add(new LoggingListener());
        }
    }

    public synchronized Connection getConnection() throws SQLException {
        Connection connection;
        connection = this.connectionProvider.getConnection();
        if (this.platform == null) {
            this.platform = new PlatformDelegate(connection);
        }
        if (this.mapping == null) {
            this.mapping = new GenericMapping(this.platform);
        }
        return connection;
    }

    private QueryBuilder createQueryBuilder() {
        Connection connection;
        if (this.queryOptions == null) {
            try {
                connection = getConnection();
                Options options = new Options(connection.getMetaData().getIdentifierQuoteString(), true, this.configuration.getTableTransformer(), this.configuration.getColumnTransformer(), this.configuration.getQuoteTableNames(), this.configuration.getQuoteColumnNames());
                this.queryOptions = options;
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new PersistenceException((Throwable) e);
            } catch (Throwable th) {
                r1.addSuppressed(th);
            }
        }
        return new QueryBuilder(this.queryOptions);
        throw th;
    }

    public void createTables(TableCreationMode tableCreationMode) {
        Connection connection;
        Throwable th;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            createTables(connection, tableCreationMode, true);
            connection.commit();
            if (connection != null) {
                connection.close();
                return;
            }
            return;
        } catch (SQLException e) {
            throw new TableModificationException(e);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public void createTables(Connection connection, TableCreationMode tableCreationMode, boolean z) {
        Statement createStatement;
        Throwable th;
        ArrayList sortTypes = sortTypes();
        try {
            createStatement = connection.createStatement();
            if (tableCreationMode == TableCreationMode.DROP_CREATE) {
                executeDropStatements(createStatement);
            }
            Iterator it = sortTypes.iterator();
            while (it.hasNext()) {
                String tableCreateStatement = tableCreateStatement((Type) it.next(), tableCreationMode);
                this.statementListeners.beforeExecuteUpdate(createStatement, tableCreateStatement, null);
                createStatement.execute(tableCreateStatement);
                this.statementListeners.afterExecuteUpdate(createStatement, 0);
            }
            if (z) {
                Iterator it2 = sortTypes.iterator();
                while (it2.hasNext()) {
                    createIndexes(connection, tableCreationMode, (Type) it2.next());
                }
            }
            if (createStatement != null) {
                createStatement.close();
                return;
            }
            return;
        } catch (SQLException e) {
            throw new TableModificationException(e);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public void createIndexes(Connection connection, TableCreationMode tableCreationMode) {
        Iterator it = sortTypes().iterator();
        while (it.hasNext()) {
            createIndexes(connection, tableCreationMode, (Type) it.next());
        }
    }

    private void executeDropStatements(Statement statement) throws SQLException {
        ArrayList sortTypes = sortTypes();
        Collections.reverse(sortTypes);
        Iterator it = sortTypes.iterator();
        while (it.hasNext()) {
            Type type = (Type) it.next();
            QueryBuilder createQueryBuilder = createQueryBuilder();
            createQueryBuilder.keyword(Keyword.DROP, Keyword.TABLE);
            if (this.platform.supportsIfExists()) {
                createQueryBuilder.keyword(Keyword.IF, Keyword.EXISTS);
            }
            createQueryBuilder.tableName(type.getName());
            try {
                String queryBuilder = createQueryBuilder.toString();
                this.statementListeners.beforeExecuteUpdate(statement, queryBuilder, null);
                statement.execute(queryBuilder);
                this.statementListeners.afterExecuteUpdate(statement, 0);
            } catch (SQLException e) {
                if (this.platform.supportsIfExists()) {
                    throw e;
                }
            }
        }
    }

    public <T> void addColumn(Connection connection, Attribute<T, ?> attribute, boolean z) {
        Type declaringType = attribute.getDeclaringType();
        QueryBuilder createQueryBuilder = createQueryBuilder();
        createQueryBuilder.keyword(Keyword.ALTER, Keyword.TABLE).tableName(declaringType.getName());
        if (!attribute.isForeignKey()) {
            createQueryBuilder.keyword(Keyword.ADD, Keyword.COLUMN);
            createColumn(createQueryBuilder, attribute, z);
        } else if (this.platform.supportsAddingConstraint()) {
            createQueryBuilder.keyword(Keyword.ADD, Keyword.COLUMN);
            createColumn(createQueryBuilder, attribute);
            executeSql(connection, createQueryBuilder);
            createQueryBuilder = createQueryBuilder();
            createQueryBuilder.keyword(Keyword.ALTER, Keyword.TABLE).tableName(declaringType.getName()).keyword(Keyword.ADD);
            createForeignKeyColumn(createQueryBuilder, attribute, false, false);
        } else {
            createQueryBuilder = createQueryBuilder();
            createQueryBuilder.keyword(Keyword.ALTER, Keyword.TABLE).tableName(declaringType.getName()).keyword(Keyword.ADD);
            createForeignKeyColumn(createQueryBuilder, attribute, false, true);
        }
        executeSql(connection, createQueryBuilder);
    }

    private void executeSql(Connection connection, QueryBuilder queryBuilder) {
        Statement createStatement;
        Throwable th;
        try {
            createStatement = connection.createStatement();
            String queryBuilder2 = queryBuilder.toString();
            this.statementListeners.beforeExecuteUpdate(createStatement, queryBuilder2, null);
            createStatement.execute(queryBuilder2);
            this.statementListeners.afterExecuteUpdate(createStatement, 0);
            if (createStatement != null) {
                createStatement.close();
                return;
            }
            return;
        } catch (SQLException e) {
            throw new PersistenceException((Throwable) e);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    private ArrayList<Type<?>> sortTypes() {
        ArrayDeque arrayDeque = new ArrayDeque(this.model.getTypes());
        ArrayList<Type<?>> arrayList = new ArrayList<>();
        while (!arrayDeque.isEmpty()) {
            Type type = (Type) arrayDeque.poll();
            if (!type.isView()) {
                Set<Type> referencedTypesOf = referencedTypesOf(type);
                for (Type type2 : referencedTypesOf) {
                    if (referencedTypesOf(type2).contains(type)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("circular reference detected between ");
                        sb.append(type.getName());
                        sb.append(" and ");
                        sb.append(type2.getName());
                        throw new CircularReferenceException(sb.toString());
                    }
                }
                if (referencedTypesOf.isEmpty() || arrayList.containsAll(referencedTypesOf)) {
                    arrayList.add(type);
                    arrayDeque.remove(type);
                } else {
                    arrayDeque.offer(type);
                }
            }
        }
        return arrayList;
    }

    private Set<Type<?>> referencedTypesOf(Type<?> type) {
        Class cls;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Attribute attribute : type.getAttributes()) {
            if (attribute.isForeignKey()) {
                if (attribute.getReferencedClass() == null) {
                    cls = attribute.getClassType();
                } else {
                    cls = attribute.getReferencedClass();
                }
                if (cls != null) {
                    for (Type<?> type2 : this.model.getTypes()) {
                        if (type != type2 && cls.isAssignableFrom(type2.getClassType())) {
                            linkedHashSet.add(type2);
                        }
                    }
                }
            }
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    public <T> String tableCreateStatement(Type<T> type, TableCreationMode tableCreationMode) {
        String name = type.getName();
        QueryBuilder createQueryBuilder = createQueryBuilder();
        createQueryBuilder.keyword(Keyword.CREATE);
        if (type.getTableCreateAttributes() != null) {
            for (String append : type.getTableCreateAttributes()) {
                createQueryBuilder.append(append, true);
            }
        }
        createQueryBuilder.keyword(Keyword.TABLE);
        if (tableCreationMode == TableCreationMode.CREATE_NOT_EXISTS) {
            createQueryBuilder.keyword(Keyword.IF, Keyword.NOT, Keyword.EXISTS);
        }
        createQueryBuilder.tableName(name);
        createQueryBuilder.openParenthesis();
        AnonymousClass1 r11 = new Predicate<Attribute>() {
            public boolean test(Attribute attribute) {
                boolean z = false;
                if (attribute.isVersion() && !SchemaModifier.this.platform.versionColumnDefinition().createColumn()) {
                    return false;
                }
                if (SchemaModifier.this.platform.supportsInlineForeignKeyReference()) {
                    if (!attribute.isForeignKey() && !attribute.isAssociation()) {
                        z = true;
                    }
                    return z;
                }
                if (attribute.isForeignKey() || !attribute.isAssociation()) {
                    z = true;
                }
                return z;
            }
        };
        Set<Attribute> attributes = type.getAttributes();
        int i = 0;
        for (Attribute attribute : attributes) {
            if (r11.test(attribute)) {
                if (i > 0) {
                    createQueryBuilder.comma();
                }
                createColumn(createQueryBuilder, attribute);
                i++;
            }
        }
        for (Attribute attribute2 : attributes) {
            if (attribute2.isForeignKey()) {
                if (i > 0) {
                    createQueryBuilder.comma();
                }
                createForeignKeyColumn(createQueryBuilder, attribute2, true, false);
                i++;
            }
        }
        if (type.getKeyAttributes().size() > 1) {
            if (i > 0) {
                createQueryBuilder.comma();
            }
            createQueryBuilder.keyword(Keyword.PRIMARY, Keyword.KEY);
            createQueryBuilder.openParenthesis();
            createQueryBuilder.commaSeparated((Iterable<? extends T>) type.getKeyAttributes(), (Appender<T>) new Appender<Attribute<T, ?>>() {
                public void append(QueryBuilder queryBuilder, Attribute<T, ?> attribute) {
                    queryBuilder.attribute(attribute);
                }
            });
            createQueryBuilder.closeParenthesis();
        }
        createQueryBuilder.closeParenthesis();
        return createQueryBuilder.toString();
    }

    private void createForeignKeyColumn(QueryBuilder queryBuilder, Attribute<?, ?> attribute, boolean z, boolean z2) {
        Attribute attribute2;
        Type typeOf = this.model.typeOf(attribute.getReferencedClass() != null ? attribute.getReferencedClass() : attribute.getClassType());
        if (attribute.getReferencedAttribute() != null) {
            attribute2 = (Attribute) attribute.getReferencedAttribute().get();
        } else {
            attribute2 = (Attribute) typeOf.getKeyAttributes().iterator().next();
        }
        if (z2 || (this.platform.supportsInlineForeignKeyReference() && z)) {
            queryBuilder.attribute(attribute);
            FieldType fieldType = null;
            if (attribute2 != null) {
                fieldType = this.mapping.mapAttribute(attribute2);
            }
            if (fieldType == null) {
                fieldType = new IntegerType(Integer.TYPE);
            }
            queryBuilder.value(fieldType.getIdentifier());
        } else {
            queryBuilder.keyword(Keyword.FOREIGN, Keyword.KEY).openParenthesis().attribute(attribute).closeParenthesis().space();
        }
        queryBuilder.keyword(Keyword.REFERENCES);
        queryBuilder.tableName(typeOf.getName());
        if (attribute2 != null) {
            queryBuilder.openParenthesis().attribute(attribute2).closeParenthesis().space();
        }
        if (attribute.getDeleteAction() != null) {
            queryBuilder.keyword(Keyword.ON, Keyword.DELETE);
            appendReferentialAction(queryBuilder, attribute.getDeleteAction());
        }
        if (this.platform.supportsOnUpdateCascade() && attribute2 != null && !attribute2.isGenerated() && attribute.getUpdateAction() != null) {
            queryBuilder.keyword(Keyword.ON, Keyword.UPDATE);
            appendReferentialAction(queryBuilder, attribute.getUpdateAction());
        }
        if (this.platform.supportsInlineForeignKeyReference()) {
            if (!attribute.isNullable()) {
                queryBuilder.keyword(Keyword.NOT, Keyword.NULL);
            }
            if (attribute.isUnique()) {
                queryBuilder.keyword(Keyword.UNIQUE);
            }
        }
    }

    private void appendReferentialAction(QueryBuilder queryBuilder, ReferentialAction referentialAction) {
        switch (referentialAction) {
            case CASCADE:
                queryBuilder.keyword(Keyword.CASCADE);
                return;
            case NO_ACTION:
                queryBuilder.keyword(Keyword.NO, Keyword.ACTION);
                return;
            case RESTRICT:
                queryBuilder.keyword(Keyword.RESTRICT);
                return;
            case SET_DEFAULT:
                queryBuilder.keyword(Keyword.SET, Keyword.DEFAULT);
                return;
            case SET_NULL:
                queryBuilder.keyword(Keyword.SET, Keyword.NULL);
                return;
            default:
                return;
        }
    }

    private void createColumn(QueryBuilder queryBuilder, Attribute<?, ?> attribute) {
        createColumn(queryBuilder, attribute, true);
    }

    private void createColumn(QueryBuilder queryBuilder, Attribute<?, ?> attribute, boolean z) {
        queryBuilder.attribute(attribute);
        FieldType mapAttribute = this.mapping.mapAttribute(attribute);
        GeneratedColumnDefinition generatedColumnDefinition = this.platform.generatedColumnDefinition();
        if (!attribute.isGenerated() || !generatedColumnDefinition.skipTypeIdentifier()) {
            Object identifier = mapAttribute.getIdentifier();
            Converter converter = attribute.getConverter();
            if (converter == null) {
                Mapping mapping2 = this.mapping;
                if (mapping2 instanceof GenericMapping) {
                    converter = ((GenericMapping) mapping2).converterForType(attribute.getClassType());
                }
            }
            boolean z2 = mapAttribute.hasLength() || !(converter == null || converter.getPersistedSize() == null);
            if (attribute.getDefinition() != null && attribute.getDefinition().length() > 0) {
                queryBuilder.append(attribute.getDefinition());
            } else if (z2) {
                Integer length = attribute.getLength();
                if (length == null && converter != null) {
                    length = converter.getPersistedSize();
                }
                if (length == null) {
                    length = mapAttribute.getDefaultLength();
                }
                if (length == null) {
                    length = Integer.valueOf(255);
                }
                queryBuilder.append(identifier).openParenthesis().append(length).closeParenthesis();
            } else {
                queryBuilder.append(identifier);
            }
            queryBuilder.space();
        }
        String identifierSuffix = mapAttribute.getIdentifierSuffix();
        if (identifierSuffix != null) {
            queryBuilder.append(identifierSuffix).space();
        }
        if (attribute.isKey() && !attribute.isForeignKey()) {
            if (attribute.isGenerated() && !generatedColumnDefinition.postFixPrimaryKey()) {
                generatedColumnDefinition.appendGeneratedSequence(queryBuilder, attribute);
                queryBuilder.space();
            }
            if (attribute.getDeclaringType().getKeyAttributes().size() == 1) {
                queryBuilder.keyword(Keyword.PRIMARY, Keyword.KEY);
            }
            if (attribute.isGenerated() && generatedColumnDefinition.postFixPrimaryKey()) {
                generatedColumnDefinition.appendGeneratedSequence(queryBuilder, attribute);
                queryBuilder.space();
            }
        } else if (attribute.isGenerated()) {
            generatedColumnDefinition.appendGeneratedSequence(queryBuilder, attribute);
            queryBuilder.space();
        }
        if (attribute.getCollate() != null && attribute.getCollate().length() > 0) {
            queryBuilder.keyword(Keyword.COLLATE);
            queryBuilder.append(attribute.getCollate());
            queryBuilder.space();
        }
        if (attribute.getDefaultValue() != null && attribute.getDefaultValue().length() > 0) {
            queryBuilder.keyword(Keyword.DEFAULT);
            queryBuilder.append(attribute.getDefaultValue());
            queryBuilder.space();
        }
        if (!attribute.isNullable()) {
            queryBuilder.keyword(Keyword.NOT, Keyword.NULL);
        }
        if (z && attribute.isUnique()) {
            queryBuilder.keyword(Keyword.UNIQUE);
        }
    }

    public void createIndex(Connection connection, Attribute<?, ?> attribute, TableCreationMode tableCreationMode) {
        QueryBuilder createQueryBuilder = createQueryBuilder();
        StringBuilder sb = new StringBuilder();
        sb.append(attribute.getName());
        sb.append("_index");
        createIndex(createQueryBuilder, sb.toString(), Collections.singleton(attribute), attribute.getDeclaringType(), tableCreationMode);
        executeSql(connection, createQueryBuilder);
    }

    private <T> void createIndexes(Connection connection, TableCreationMode tableCreationMode, Type<T> type) {
        Set<Attribute> attributes = type.getAttributes();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Attribute attribute : attributes) {
            if (attribute.isIndexed()) {
                for (String str : new LinkedHashSet(attribute.getIndexNames())) {
                    if (str.isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(attribute.getName());
                        sb.append("_index");
                        str = sb.toString();
                    }
                    Set set = (Set) linkedHashMap.get(str);
                    if (set == null) {
                        set = new LinkedHashSet();
                        linkedHashMap.put(str, set);
                    }
                    set.add(attribute);
                }
            }
        }
        for (Entry entry : linkedHashMap.entrySet()) {
            QueryBuilder createQueryBuilder = createQueryBuilder();
            createIndex(createQueryBuilder, (String) entry.getKey(), (Set) entry.getValue(), type, tableCreationMode);
            executeSql(connection, createQueryBuilder);
        }
    }

    private void createIndex(QueryBuilder queryBuilder, String str, Set<? extends Attribute<?, ?>> set, Type<?> type, TableCreationMode tableCreationMode) {
        queryBuilder.keyword(Keyword.CREATE);
        if ((set.size() >= 1 && ((Attribute) set.iterator().next()).isUnique()) || (type.getTableUniqueIndexes() != null && Arrays.asList(type.getTableUniqueIndexes()).contains(str))) {
            queryBuilder.keyword(Keyword.UNIQUE);
        }
        queryBuilder.keyword(Keyword.INDEX);
        if (tableCreationMode == TableCreationMode.CREATE_NOT_EXISTS) {
            queryBuilder.keyword(Keyword.IF, Keyword.NOT, Keyword.EXISTS);
        }
        queryBuilder.append(str).space().keyword(Keyword.ON).tableName(type.getName()).openParenthesis().commaSeparated((Iterable<? extends T>) set, (Appender<T>) new Appender<Attribute>() {
            public void append(QueryBuilder queryBuilder, Attribute attribute) {
                queryBuilder.attribute(attribute);
            }
        }).closeParenthesis();
    }
}
