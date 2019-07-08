package io.requery.sql;

import io.requery.EntityCache;
import io.requery.Transaction;
import io.requery.TransactionIsolation;
import io.requery.meta.Type;
import io.requery.proxy.EntityProxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

class ThreadLocalTransaction implements ConnectionProvider, EntityTransaction {
    private final RuntimeConfiguration configuration;
    private final ThreadLocal<EntityTransaction> threadLocal = new ThreadLocal<>();

    ThreadLocalTransaction(RuntimeConfiguration runtimeConfiguration) {
        this.configuration = runtimeConfiguration;
    }

    public Transaction begin() {
        return begin(this.configuration.getTransactionIsolation());
    }

    public Transaction begin(TransactionIsolation transactionIsolation) {
        EntityTransaction entityTransaction = (EntityTransaction) this.threadLocal.get();
        if (entityTransaction == null) {
            EntityCache cache = this.configuration.getCache();
            TransactionMode transactionMode = this.configuration.getTransactionMode();
            CompositeTransactionListener compositeTransactionListener = new CompositeTransactionListener(this.configuration.getTransactionListenerFactories());
            if (transactionMode == TransactionMode.MANAGED) {
                entityTransaction = new ManagedTransaction(compositeTransactionListener, this.configuration, cache);
            } else {
                entityTransaction = new ConnectionTransaction(compositeTransactionListener, this.configuration, cache, transactionMode != TransactionMode.NONE);
            }
            this.threadLocal.set(entityTransaction);
        }
        entityTransaction.begin(transactionIsolation);
        return this;
    }

    public void commit() {
        Transaction transaction = (Transaction) this.threadLocal.get();
        if (transaction != null) {
            transaction.commit();
            return;
        }
        throw new IllegalStateException();
    }

    public void rollback() {
        Transaction transaction = (Transaction) this.threadLocal.get();
        if (transaction != null) {
            transaction.rollback();
            return;
        }
        throw new IllegalStateException();
    }

    public boolean active() {
        Transaction transaction = (Transaction) this.threadLocal.get();
        return transaction != null && transaction.active();
    }

    public void addToTransaction(EntityProxy<?> entityProxy) {
        EntityTransaction entityTransaction = (EntityTransaction) this.threadLocal.get();
        if (entityTransaction != null) {
            entityTransaction.addToTransaction(entityProxy);
        }
    }

    public void addToTransaction(Collection<Type<?>> collection) {
        EntityTransaction entityTransaction = (EntityTransaction) this.threadLocal.get();
        if (entityTransaction != null) {
            entityTransaction.addToTransaction(collection);
        }
    }

    public void close() {
        Transaction transaction = (Transaction) this.threadLocal.get();
        if (transaction != null) {
            try {
                transaction.close();
            } catch (Throwable th) {
                this.threadLocal.remove();
                throw th;
            }
        }
        this.threadLocal.remove();
    }

    public Connection getConnection() throws SQLException {
        Transaction transaction = (Transaction) this.threadLocal.get();
        if (transaction instanceof ConnectionProvider) {
            return ((ConnectionProvider) transaction).getConnection();
        }
        return null;
    }
}
