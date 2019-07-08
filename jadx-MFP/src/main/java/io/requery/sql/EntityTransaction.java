package io.requery.sql;

import io.requery.Transaction;
import io.requery.meta.Type;
import io.requery.proxy.EntityProxy;
import java.util.Collection;

interface EntityTransaction extends Transaction {
    void addToTransaction(EntityProxy<?> entityProxy);

    void addToTransaction(Collection<Type<?>> collection);
}
