package io.requery.sql;

import io.requery.util.function.Supplier;

class TransactionProvider implements Supplier<EntityTransaction> {
    private final ThreadLocalTransaction threadLocalTransaction;

    TransactionProvider(RuntimeConfiguration runtimeConfiguration) {
        this.threadLocalTransaction = new ThreadLocalTransaction(runtimeConfiguration);
    }

    public EntityTransaction get() {
        return this.threadLocalTransaction;
    }
}
