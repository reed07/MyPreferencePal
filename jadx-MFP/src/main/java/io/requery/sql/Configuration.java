package io.requery.sql;

import io.requery.EntityCache;
import io.requery.TransactionIsolation;
import io.requery.TransactionListener;
import io.requery.meta.EntityModel;
import io.requery.util.function.Function;
import io.requery.util.function.Supplier;
import java.util.Set;
import java.util.concurrent.Executor;

public interface Configuration {
    EntityCache getCache();

    Function<String, String> getColumnTransformer();

    ConnectionProvider getConnectionProvider();

    Set<EntityStateListener> getEntityStateListeners();

    Mapping getMapping();

    EntityModel getModel();

    Platform getPlatform();

    boolean getQuoteColumnNames();

    boolean getQuoteTableNames();

    int getStatementCacheSize();

    Set<StatementListener> getStatementListeners();

    Function<String, String> getTableTransformer();

    TransactionIsolation getTransactionIsolation();

    Set<Supplier<TransactionListener>> getTransactionListenerFactories();

    TransactionMode getTransactionMode();

    boolean getUseDefaultLogging();

    Executor getWriteExecutor();
}
