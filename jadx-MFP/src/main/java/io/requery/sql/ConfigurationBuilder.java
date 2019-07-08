package io.requery.sql;

import io.requery.EntityCache;
import io.requery.TransactionIsolation;
import io.requery.TransactionListener;
import io.requery.cache.WeakEntityCache;
import io.requery.meta.EntityModel;
import io.requery.util.Objects;
import io.requery.util.function.Function;
import io.requery.util.function.Supplier;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Executor;

public class ConfigurationBuilder {
    private int batchUpdateSize;
    private EntityCache cache;
    private Function<String, String> columnTransformer;
    private final ConnectionProvider connectionProvider;
    private final Set<EntityStateListener> entityStateListeners = new LinkedHashSet();
    private Mapping mapping;
    private final EntityModel model;
    private Platform platform;
    private boolean quoteColumnNames;
    private boolean quoteTableNames;
    private int statementCacheSize;
    private final Set<StatementListener> statementListeners = new LinkedHashSet();
    private Function<String, String> tableTransformer;
    private TransactionIsolation transactionIsolation;
    private final Set<Supplier<TransactionListener>> transactionListenerFactory = new LinkedHashSet();
    private TransactionMode transactionMode;
    private boolean useDefaultLogging;
    private Executor writeExecutor;

    public ConfigurationBuilder(ConnectionProvider connectionProvider2, EntityModel entityModel) {
        this.connectionProvider = (ConnectionProvider) Objects.requireNotNull(connectionProvider2);
        this.model = (EntityModel) Objects.requireNotNull(entityModel);
        setQuoteTableNames(false);
        setQuoteColumnNames(false);
        setEntityCache(new WeakEntityCache());
        setStatementCacheSize(0);
        setBatchUpdateSize(64);
        setTransactionMode(TransactionMode.AUTO);
        setTransactionIsolation(null);
        setTableTransformer(null);
        setColumnTransformer(null);
    }

    public ConfigurationBuilder setMapping(Mapping mapping2) {
        this.mapping = mapping2;
        return this;
    }

    public ConfigurationBuilder setPlatform(Platform platform2) {
        this.platform = platform2;
        return this;
    }

    public ConfigurationBuilder setEntityCache(EntityCache entityCache) {
        this.cache = entityCache;
        return this;
    }

    public ConfigurationBuilder setStatementCacheSize(int i) {
        if (i >= 0) {
            this.statementCacheSize = i;
            return this;
        }
        throw new IllegalArgumentException();
    }

    public ConfigurationBuilder setBatchUpdateSize(int i) {
        if (i >= 0) {
            this.batchUpdateSize = i;
            return this;
        }
        throw new IllegalArgumentException();
    }

    public ConfigurationBuilder setQuoteTableNames(boolean z) {
        this.quoteTableNames = z;
        return this;
    }

    public ConfigurationBuilder setTableTransformer(Function<String, String> function) {
        this.tableTransformer = function;
        return this;
    }

    public ConfigurationBuilder setColumnTransformer(Function<String, String> function) {
        this.columnTransformer = function;
        return this;
    }

    public ConfigurationBuilder setQuoteColumnNames(boolean z) {
        this.quoteColumnNames = z;
        return this;
    }

    public ConfigurationBuilder addStatementListener(StatementListener statementListener) {
        this.statementListeners.add(Objects.requireNotNull(statementListener));
        return this;
    }

    public ConfigurationBuilder setTransactionIsolation(TransactionIsolation transactionIsolation2) {
        this.transactionIsolation = transactionIsolation2;
        return this;
    }

    public ConfigurationBuilder setTransactionMode(TransactionMode transactionMode2) {
        this.transactionMode = transactionMode2;
        return this;
    }

    public Configuration build() {
        ImmutableConfiguration immutableConfiguration = new ImmutableConfiguration(this.connectionProvider, this.platform, this.model, this.cache, this.mapping, this.useDefaultLogging, this.statementCacheSize, this.batchUpdateSize, this.quoteTableNames, this.quoteColumnNames, this.tableTransformer, this.columnTransformer, this.entityStateListeners, this.statementListeners, this.transactionMode, this.transactionIsolation, this.transactionListenerFactory, this.writeExecutor);
        return immutableConfiguration;
    }
}
