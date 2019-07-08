package io.requery;

public interface Transaction extends AutoCloseable {
    boolean active();

    Transaction begin();

    Transaction begin(TransactionIsolation transactionIsolation);

    void close();

    void commit();

    void rollback();
}
