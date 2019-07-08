package io.requery.sql;

import io.requery.meta.Type;
import io.requery.util.function.Supplier;
import java.util.Collection;
import java.util.Set;

class TransactionScope implements AutoCloseable {
    private final boolean enteredTransaction;
    private final EntityTransaction transaction;

    TransactionScope(Supplier<? extends EntityTransaction> supplier) {
        this(supplier, null);
    }

    TransactionScope(Supplier<? extends EntityTransaction> supplier, Set<Type<?>> set) {
        this.transaction = (EntityTransaction) supplier.get();
        if (!this.transaction.active()) {
            this.transaction.begin();
            this.enteredTransaction = true;
        } else {
            this.enteredTransaction = false;
        }
        if (set != null) {
            this.transaction.addToTransaction((Collection<Type<?>>) set);
        }
    }

    public void commit() {
        if (this.enteredTransaction) {
            this.transaction.commit();
        }
    }

    public void close() {
        if (this.enteredTransaction) {
            this.transaction.close();
        }
    }
}
