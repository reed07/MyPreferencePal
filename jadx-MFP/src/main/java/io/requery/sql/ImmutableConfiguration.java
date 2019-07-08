package io.requery.sql;

import io.requery.EntityCache;
import io.requery.TransactionIsolation;
import io.requery.TransactionListener;
import io.requery.meta.EntityModel;
import io.requery.util.Objects;
import io.requery.util.function.Function;
import io.requery.util.function.Supplier;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;

final class ImmutableConfiguration implements Configuration {
    private final int batchUpdateSize;
    private final EntityCache cache;
    private final Function<String, String> columnTransformer;
    private final ConnectionProvider connectionProvider;
    private final Set<EntityStateListener> entityStateListeners;
    private final Mapping mapping;
    private final EntityModel model;
    private final Platform platform;
    private final boolean quoteColumnNames;
    private final boolean quoteTableNames;
    private final int statementCacheSize;
    private final Set<StatementListener> statementListeners;
    private final Function<String, String> tableTransformer;
    private final TransactionIsolation transactionIsolation;
    private final Set<Supplier<TransactionListener>> transactionListenerFactories;
    private final TransactionMode transactionMode;
    private final boolean useDefaultLogging;
    private final Executor writeExecutor;

    ImmutableConfiguration(ConnectionProvider connectionProvider2, Platform platform2, EntityModel entityModel, EntityCache entityCache, Mapping mapping2, boolean z, int i, int i2, boolean z2, boolean z3, Function<String, String> function, Function<String, String> function2, Set<EntityStateListener> set, Set<StatementListener> set2, TransactionMode transactionMode2, TransactionIsolation transactionIsolation2, Set<Supplier<TransactionListener>> set3, Executor executor) {
        this.connectionProvider = connectionProvider2;
        this.platform = platform2;
        this.model = entityModel;
        this.cache = entityCache;
        this.mapping = mapping2;
        this.useDefaultLogging = z;
        this.statementCacheSize = i;
        this.batchUpdateSize = i2;
        this.quoteTableNames = z2;
        this.quoteColumnNames = z3;
        this.tableTransformer = function;
        this.columnTransformer = function2;
        this.transactionMode = transactionMode2;
        this.entityStateListeners = Collections.unmodifiableSet(set);
        this.statementListeners = Collections.unmodifiableSet(set2);
        this.transactionIsolation = transactionIsolation2;
        this.transactionListenerFactories = set3;
        this.writeExecutor = executor;
    }

    public EntityCache getCache() {
        return this.cache;
    }

    public ConnectionProvider getConnectionProvider() {
        return this.connectionProvider;
    }

    public Set<EntityStateListener> getEntityStateListeners() {
        return this.entityStateListeners;
    }

    public Mapping getMapping() {
        return this.mapping;
    }

    public EntityModel getModel() {
        return this.model;
    }

    public Platform getPlatform() {
        return this.platform;
    }

    public boolean getQuoteTableNames() {
        return this.quoteTableNames;
    }

    public boolean getQuoteColumnNames() {
        return this.quoteColumnNames;
    }

    public Function<String, String> getColumnTransformer() {
        return this.columnTransformer;
    }

    public Function<String, String> getTableTransformer() {
        return this.tableTransformer;
    }

    public int getStatementCacheSize() {
        return this.statementCacheSize;
    }

    public Set<StatementListener> getStatementListeners() {
        return this.statementListeners;
    }

    public TransactionIsolation getTransactionIsolation() {
        return this.transactionIsolation;
    }

    public Set<Supplier<TransactionListener>> getTransactionListenerFactories() {
        return this.transactionListenerFactories;
    }

    public TransactionMode getTransactionMode() {
        return this.transactionMode;
    }

    public boolean getUseDefaultLogging() {
        return this.useDefaultLogging;
    }

    public Executor getWriteExecutor() {
        return this.writeExecutor;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof Configuration)) {
            return false;
        }
        if (hashCode() == ((Configuration) obj).hashCode()) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hash(this.platform, this.connectionProvider, this.model, this.mapping, Boolean.valueOf(this.quoteColumnNames), Boolean.valueOf(this.quoteTableNames), this.transactionIsolation, this.transactionMode, Integer.valueOf(this.statementCacheSize), this.transactionListenerFactories, Boolean.valueOf(this.useDefaultLogging));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("platform: ");
        sb.append(this.platform);
        sb.append("connectionProvider: ");
        sb.append(this.connectionProvider);
        sb.append("model: ");
        sb.append(this.model);
        sb.append("quoteColumnNames: ");
        sb.append(this.quoteColumnNames);
        sb.append("quoteTableNames: ");
        sb.append(this.quoteTableNames);
        sb.append("transactionMode");
        sb.append(this.transactionMode);
        sb.append("transactionIsolation");
        sb.append(this.transactionIsolation);
        sb.append("statementCacheSize: ");
        sb.append(this.statementCacheSize);
        sb.append("useDefaultLogging: ");
        sb.append(this.useDefaultLogging);
        return sb.toString();
    }
}
