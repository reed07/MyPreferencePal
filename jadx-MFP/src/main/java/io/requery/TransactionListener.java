package io.requery;

import io.requery.meta.Type;
import java.util.Set;

public interface TransactionListener {
    void afterBegin(TransactionIsolation transactionIsolation);

    void afterCommit(Set<Type<?>> set);

    void afterRollback(Set<Type<?>> set);

    void beforeBegin(TransactionIsolation transactionIsolation);

    void beforeCommit(Set<Type<?>> set);

    void beforeRollback(Set<Type<?>> set);
}
