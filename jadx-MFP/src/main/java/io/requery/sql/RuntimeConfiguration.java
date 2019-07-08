package io.requery.sql;

import io.requery.EntityCache;
import io.requery.TransactionIsolation;
import io.requery.TransactionListener;
import io.requery.meta.EntityModel;
import io.requery.sql.QueryBuilder.Options;
import io.requery.sql.gen.StatementGenerator;
import io.requery.util.function.Supplier;
import java.util.Set;
import java.util.concurrent.Executor;

public interface RuntimeConfiguration extends ConnectionProvider {
    EntityCache getCache();

    Mapping getMapping();

    EntityModel getModel();

    Platform getPlatform();

    Options getQueryBuilderOptions();

    StatementGenerator getStatementGenerator();

    StatementListener getStatementListener();

    TransactionIsolation getTransactionIsolation();

    Set<Supplier<TransactionListener>> getTransactionListenerFactories();

    TransactionMode getTransactionMode();

    TransactionProvider getTransactionProvider();

    Executor getWriteExecutor();
}
