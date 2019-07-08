package io.requery.sql;

import io.requery.BlockingEntityStore;
import io.requery.EntityCache;
import io.requery.PersistenceException;
import io.requery.ReadOnlyException;
import io.requery.RollbackException;
import io.requery.TransactionException;
import io.requery.TransactionIsolation;
import io.requery.TransactionListener;
import io.requery.cache.EmptyEntityCache;
import io.requery.meta.Attribute;
import io.requery.meta.EntityModel;
import io.requery.meta.QueryAttribute;
import io.requery.meta.Type;
import io.requery.proxy.CompositeKey;
import io.requery.proxy.EntityProxy;
import io.requery.query.Condition;
import io.requery.query.Deletion;
import io.requery.query.Expression;
import io.requery.query.Result;
import io.requery.query.Scalar;
import io.requery.query.Selection;
import io.requery.query.Tuple;
import io.requery.query.Update;
import io.requery.query.element.QueryElement;
import io.requery.query.element.QueryType;
import io.requery.query.function.Count;
import io.requery.sql.QueryBuilder.Options;
import io.requery.sql.gen.StatementGenerator;
import io.requery.sql.platform.PlatformDelegate;
import io.requery.util.ClassMap;
import io.requery.util.Objects;
import io.requery.util.function.Supplier;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class EntityDataStore<T> implements BlockingEntityStore<T> {
    private final AtomicBoolean closed = new AtomicBoolean();
    /* access modifiers changed from: private */
    public final Configuration configuration;
    /* access modifiers changed from: private */
    public final ConnectionProvider connectionProvider;
    private final DataContext context;
    private final SelectCountOperation countOperation;
    /* access modifiers changed from: private */
    public final EntityCache entityCache;
    /* access modifiers changed from: private */
    public final EntityModel entityModel;
    /* access modifiers changed from: private */
    public Mapping mapping;
    private boolean metadataChecked;
    /* access modifiers changed from: private */
    public Platform platform;
    /* access modifiers changed from: private */
    public Options queryOptions;
    /* access modifiers changed from: private */
    public final ClassMap<EntityReader<?, ?>> readers = new ClassMap<>();
    /* access modifiers changed from: private */
    public final CompositeEntityListener<T> stateListeners;
    /* access modifiers changed from: private */
    public PreparedStatementCache statementCache;
    /* access modifiers changed from: private */
    public StatementGenerator statementGenerator;
    /* access modifiers changed from: private */
    public final CompositeStatementListener statementListeners;
    private boolean supportsBatchUpdates;
    /* access modifiers changed from: private */
    public TransactionMode transactionMode;
    /* access modifiers changed from: private */
    public final TransactionProvider transactionProvider;
    private final UpdateOperation updateOperation;
    /* access modifiers changed from: private */
    public final ClassMap<EntityWriter<?, ?>> writers = new ClassMap<>();

    private class DataContext implements ConnectionProvider, EntityContext<T> {
        private DataContext() {
        }

        public <E> EntityProxy<E> proxyOf(E e, boolean z) {
            EntityDataStore.this.checkClosed();
            Type typeOf = EntityDataStore.this.entityModel.typeOf(e.getClass());
            EntityProxy<E> entityProxy = (EntityProxy) typeOf.getProxyProvider().apply(e);
            if (!z || !typeOf.isReadOnly()) {
                if (z) {
                    EntityTransaction entityTransaction = EntityDataStore.this.transactionProvider.get();
                    if (entityTransaction != null && entityTransaction.active()) {
                        entityTransaction.addToTransaction(entityProxy);
                    }
                }
                return entityProxy;
            }
            throw new ReadOnlyException();
        }

        public synchronized Connection getConnection() throws SQLException {
            Connection connection;
            connection = null;
            EntityTransaction entityTransaction = EntityDataStore.this.transactionProvider.get();
            if (entityTransaction != null && entityTransaction.active() && (entityTransaction instanceof ConnectionProvider)) {
                connection = ((ConnectionProvider) entityTransaction).getConnection();
            }
            if (connection == null) {
                connection = EntityDataStore.this.connectionProvider.getConnection();
                if (EntityDataStore.this.statementCache != null) {
                    connection = new StatementCachingConnection(EntityDataStore.this.statementCache, connection);
                }
            }
            if (EntityDataStore.this.platform == null) {
                EntityDataStore.this.platform = new PlatformDelegate(connection);
            }
            if (EntityDataStore.this.mapping == null) {
                EntityDataStore.this.mapping = new GenericMapping(EntityDataStore.this.platform);
            }
            return connection;
        }

        public synchronized <E extends T> EntityReader<E, T> read(Class<? extends E> cls) {
            EntityReader<E, T> entityReader;
            entityReader = (EntityReader) EntityDataStore.this.readers.get(cls);
            if (entityReader == null) {
                EntityDataStore.this.checkConnectionMetadata();
                entityReader = new EntityReader<>(EntityDataStore.this.entityModel.typeOf(cls), this, EntityDataStore.this);
                EntityDataStore.this.readers.put(cls, entityReader);
            }
            return entityReader;
        }

        public synchronized <E extends T> EntityWriter<E, T> write(Class<? extends E> cls) {
            EntityWriter<E, T> entityWriter;
            entityWriter = (EntityWriter) EntityDataStore.this.writers.get(cls);
            if (entityWriter == null) {
                EntityDataStore.this.checkConnectionMetadata();
                entityWriter = new EntityWriter<>(EntityDataStore.this.entityModel.typeOf(cls), this, EntityDataStore.this);
                EntityDataStore.this.writers.put(cls, entityWriter);
            }
            return entityWriter;
        }

        public CompositeEntityListener<T> getStateListener() {
            return EntityDataStore.this.stateListeners;
        }

        public Options getQueryBuilderOptions() {
            EntityDataStore.this.checkConnectionMetadata();
            return EntityDataStore.this.queryOptions;
        }

        public Mapping getMapping() {
            return EntityDataStore.this.mapping;
        }

        public EntityModel getModel() {
            return EntityDataStore.this.entityModel;
        }

        public EntityCache getCache() {
            return EntityDataStore.this.entityCache;
        }

        public Platform getPlatform() {
            EntityDataStore.this.checkConnectionMetadata();
            return EntityDataStore.this.platform;
        }

        public StatementGenerator getStatementGenerator() {
            if (EntityDataStore.this.statementGenerator == null) {
                EntityDataStore.this.statementGenerator = new StatementGenerator(getPlatform());
            }
            return EntityDataStore.this.statementGenerator;
        }

        public StatementListener getStatementListener() {
            return EntityDataStore.this.statementListeners;
        }

        public Set<Supplier<TransactionListener>> getTransactionListenerFactories() {
            return EntityDataStore.this.configuration.getTransactionListenerFactories();
        }

        public TransactionProvider getTransactionProvider() {
            return EntityDataStore.this.transactionProvider;
        }

        public TransactionMode getTransactionMode() {
            EntityDataStore.this.checkConnectionMetadata();
            return EntityDataStore.this.transactionMode;
        }

        public TransactionIsolation getTransactionIsolation() {
            return EntityDataStore.this.configuration.getTransactionIsolation();
        }

        public Executor getWriteExecutor() {
            return EntityDataStore.this.configuration.getWriteExecutor();
        }
    }

    public BlockingEntityStore<T> toBlocking() {
        return this;
    }

    public EntityDataStore(Configuration configuration2) {
        EntityCache entityCache2;
        this.entityModel = (EntityModel) Objects.requireNotNull(configuration2.getModel());
        this.connectionProvider = (ConnectionProvider) Objects.requireNotNull(configuration2.getConnectionProvider());
        this.mapping = configuration2.getMapping();
        this.platform = configuration2.getPlatform();
        this.transactionMode = configuration2.getTransactionMode();
        this.configuration = configuration2;
        this.statementListeners = new CompositeStatementListener(configuration2.getStatementListeners());
        this.stateListeners = new CompositeEntityListener<>();
        if (configuration2.getCache() == null) {
            entityCache2 = new EmptyEntityCache();
        } else {
            entityCache2 = configuration2.getCache();
        }
        this.entityCache = entityCache2;
        int statementCacheSize = configuration2.getStatementCacheSize();
        if (statementCacheSize > 0) {
            this.statementCache = new PreparedStatementCache(statementCacheSize);
        }
        Platform platform2 = this.platform;
        if (platform2 != null && this.mapping == null) {
            this.mapping = new GenericMapping(platform2);
        }
        this.context = new DataContext<>();
        this.transactionProvider = new TransactionProvider(this.context);
        this.updateOperation = new UpdateOperation(this.context);
        this.countOperation = new SelectCountOperation(this.context);
        LinkedHashSet<EntityStateListener> linkedHashSet = new LinkedHashSet<>();
        if (configuration2.getUseDefaultLogging()) {
            LoggingListener loggingListener = new LoggingListener();
            linkedHashSet.add(loggingListener);
            this.statementListeners.add(loggingListener);
        }
        if (!configuration2.getEntityStateListeners().isEmpty()) {
            for (EntityStateListener add : configuration2.getEntityStateListeners()) {
                linkedHashSet.add(add);
            }
        }
        if (!linkedHashSet.isEmpty()) {
            this.stateListeners.enableStateListeners(true);
            for (EntityStateListener entityStateListener : linkedHashSet) {
                this.stateListeners.addPostLoadListener(entityStateListener);
                this.stateListeners.addPostInsertListener(entityStateListener);
                this.stateListeners.addPostDeleteListener(entityStateListener);
                this.stateListeners.addPostUpdateListener(entityStateListener);
                this.stateListeners.addPreInsertListener(entityStateListener);
                this.stateListeners.addPreDeleteListener(entityStateListener);
                this.stateListeners.addPreUpdateListener(entityStateListener);
            }
        }
    }

    public <E extends T> E insert(E e) {
        insert(e, null);
        return e;
    }

    public <K, E extends T> K insert(E e, @Nullable Class<K> cls) {
        GeneratedKeys generatedKeys;
        TransactionScope transactionScope = new TransactionScope(this.transactionProvider);
        try {
            EntityProxy proxyOf = this.context.proxyOf(e, true);
            synchronized (proxyOf.syncObject()) {
                EntityWriter write = this.context.write(proxyOf.type().getClassType());
                if (cls != null) {
                    generatedKeys = new GeneratedKeys(proxyOf.type().isImmutable() ? null : proxyOf);
                } else {
                    generatedKeys = null;
                }
                write.insert(e, proxyOf, generatedKeys);
                transactionScope.commit();
                if (generatedKeys == null || generatedKeys.size() <= 0) {
                    transactionScope.close();
                    return null;
                }
                K cast = cls.cast(generatedKeys.get(0));
                transactionScope.close();
                return cast;
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                throw th2;
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
        }
        throw th;
    }

    public <E extends T> E update(E e) {
        TransactionScope transactionScope = new TransactionScope(this.transactionProvider);
        try {
            EntityProxy proxyOf = this.context.proxyOf(e, true);
            synchronized (proxyOf.syncObject()) {
                this.context.write(proxyOf.type().getClassType()).update(e, proxyOf);
                transactionScope.commit();
            }
            transactionScope.close();
            return e;
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                throw th2;
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
        }
        throw th;
    }

    public <E extends T> E refresh(E e) {
        E refresh;
        EntityProxy proxyOf = this.context.proxyOf(e, false);
        synchronized (proxyOf.syncObject()) {
            refresh = this.context.read(proxyOf.type().getClassType()).refresh(e, proxyOf);
        }
        return refresh;
    }

    public <E extends T, K> E findByKey(Class<E> cls, K k) {
        Type typeOf = this.entityModel.typeOf(cls);
        if (typeOf.isCacheable()) {
            EntityCache entityCache2 = this.entityCache;
            if (entityCache2 != null) {
                E e = entityCache2.get(cls, k);
                if (e != null) {
                    return e;
                }
            }
        }
        Set<Attribute> keyAttributes = typeOf.getKeyAttributes();
        if (!keyAttributes.isEmpty()) {
            Selection select = select(cls, new QueryAttribute[0]);
            if (keyAttributes.size() == 1) {
                select.where((Condition) Attributes.query((Attribute) keyAttributes.iterator().next()).equal(k));
            } else if (k instanceof CompositeKey) {
                CompositeKey compositeKey = (CompositeKey) k;
                for (Attribute query : keyAttributes) {
                    QueryAttribute query2 = Attributes.query(query);
                    select.where((Condition) query2.equal(compositeKey.get((Expression<V>) query2)));
                }
            } else {
                throw new IllegalArgumentException("CompositeKey required");
            }
            return ((Result) select.get()).firstOrNull();
        }
        throw new MissingKeyException();
    }

    public void close() {
        if (this.closed.compareAndSet(false, true)) {
            this.entityCache.clear();
            PreparedStatementCache preparedStatementCache = this.statementCache;
            if (preparedStatementCache != null) {
                preparedStatementCache.close();
            }
        }
    }

    public Selection<? extends Result<Tuple>> select(Expression<?>... expressionArr) {
        return new QueryElement(QueryType.SELECT, this.entityModel, new SelectOperation(this.context, new TupleResultReader(this.context))).select(expressionArr);
    }

    public <E extends T> Selection<? extends Result<E>> select(Class<E> cls, QueryAttribute<?, ?>... queryAttributeArr) {
        Set set;
        ResultReader resultReader;
        checkClosed();
        EntityReader read = this.context.read(cls);
        if (queryAttributeArr.length == 0) {
            set = read.defaultSelection();
            resultReader = read.newResultReader(read.defaultSelectionAttributes());
        } else {
            Set linkedHashSet = new LinkedHashSet(Arrays.asList(queryAttributeArr));
            resultReader = read.newResultReader(queryAttributeArr);
            set = linkedHashSet;
        }
        return new QueryElement(QueryType.SELECT, this.entityModel, new SelectOperation(this.context, resultReader)).select(set).from(cls);
    }

    public <E extends T> Update<? extends Scalar<Integer>> update(Class<E> cls) {
        checkClosed();
        return new QueryElement(QueryType.UPDATE, this.entityModel, this.updateOperation).from(cls);
    }

    public <E extends T> Deletion<? extends Scalar<Integer>> delete(Class<E> cls) {
        checkClosed();
        return new QueryElement(QueryType.DELETE, this.entityModel, this.updateOperation).from(cls);
    }

    public <E extends T> Selection<? extends Scalar<Integer>> count(Class<E> cls) {
        checkClosed();
        Objects.requireNotNull(cls);
        return new QueryElement(QueryType.SELECT, this.entityModel, this.countOperation).select((Expression<?>[]) new Expression[]{Count.count(cls)}).from(cls);
    }

    public <V> V runInTransaction(Callable<V> callable, @Nullable TransactionIsolation transactionIsolation) {
        Objects.requireNotNull(callable);
        checkClosed();
        EntityTransaction entityTransaction = this.transactionProvider.get();
        if (entityTransaction != null) {
            try {
                entityTransaction.begin(transactionIsolation);
                V call = callable.call();
                entityTransaction.commit();
                return call;
            } catch (Exception e) {
                entityTransaction.rollback();
                throw new RollbackException(e);
            }
        } else {
            throw new TransactionException("no transaction");
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void checkConnectionMetadata() {
        Connection connection;
        if (!this.metadataChecked) {
            try {
                connection = this.context.getConnection();
                DatabaseMetaData metaData = connection.getMetaData();
                if (!metaData.supportsTransactions()) {
                    this.transactionMode = TransactionMode.NONE;
                }
                this.supportsBatchUpdates = metaData.supportsBatchUpdates();
                Options options = new Options(metaData.getIdentifierQuoteString(), true, this.configuration.getTableTransformer(), this.configuration.getColumnTransformer(), this.configuration.getQuoteTableNames(), this.configuration.getQuoteColumnNames());
                this.queryOptions = options;
                this.metadataChecked = true;
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new PersistenceException((Throwable) e);
            } catch (Throwable th) {
                r1.addSuppressed(th);
            }
        }
        return;
        throw th;
    }

    /* access modifiers changed from: protected */
    public void checkClosed() {
        if (this.closed.get()) {
            throw new PersistenceException("closed");
        }
    }
}
