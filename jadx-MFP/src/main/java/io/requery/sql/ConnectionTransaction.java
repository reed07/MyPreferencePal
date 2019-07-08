package io.requery.sql;

import io.requery.EntityCache;
import io.requery.Transaction;
import io.requery.TransactionException;
import io.requery.TransactionIsolation;
import io.requery.TransactionListener;
import io.requery.meta.Type;
import io.requery.proxy.EntityProxy;
import io.requery.util.Objects;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

class ConnectionTransaction implements ConnectionProvider, EntityTransaction {
    private boolean committed;
    private Connection connection;
    private final ConnectionProvider connectionProvider;
    private final TransactionEntitiesSet entities;
    private int previousIsolationLevel = -1;
    private boolean rolledBack;
    private final boolean supportsTransaction;
    private final TransactionListener transactionListener;
    private Connection uncloseableConnection;

    ConnectionTransaction(TransactionListener transactionListener2, ConnectionProvider connectionProvider2, EntityCache entityCache, boolean z) {
        this.transactionListener = (TransactionListener) Objects.requireNotNull(transactionListener2);
        this.connectionProvider = (ConnectionProvider) Objects.requireNotNull(connectionProvider2);
        this.supportsTransaction = z;
        this.entities = new TransactionEntitiesSet(entityCache);
    }

    public Connection getConnection() {
        return this.uncloseableConnection;
    }

    public Transaction begin() {
        return begin(null);
    }

    public Transaction begin(TransactionIsolation transactionIsolation) {
        int i;
        if (!active()) {
            try {
                this.transactionListener.beforeBegin(transactionIsolation);
                this.connection = this.connectionProvider.getConnection();
                this.uncloseableConnection = new UncloseableConnection(this.connection);
                if (this.supportsTransaction) {
                    this.connection.setAutoCommit(false);
                    if (transactionIsolation != null) {
                        this.previousIsolationLevel = this.connection.getTransactionIsolation();
                        switch (transactionIsolation) {
                            case NONE:
                                i = 0;
                                break;
                            case READ_UNCOMMITTED:
                                i = 1;
                                break;
                            case READ_COMMITTED:
                                i = 2;
                                break;
                            case REPEATABLE_READ:
                                i = 4;
                                break;
                            case SERIALIZABLE:
                                i = 8;
                                break;
                            default:
                                throw new UnsupportedOperationException();
                        }
                        this.connection.setTransactionIsolation(i);
                    }
                }
                this.committed = false;
                this.rolledBack = false;
                this.entities.clear();
                this.transactionListener.afterBegin(transactionIsolation);
                return this;
            } catch (SQLException e) {
                throw new TransactionException((Throwable) e);
            }
        } else {
            throw new IllegalStateException("transaction already active");
        }
    }

    public void close() {
        if (this.connection != null) {
            if (!this.committed && !this.rolledBack) {
                try {
                    rollback();
                } catch (Exception unused) {
                }
            }
            try {
                this.connection.close();
                this.connection = null;
            } catch (SQLException e) {
                throw new TransactionException((Throwable) e);
            } catch (Throwable th) {
                this.connection = null;
                throw th;
            }
        }
    }

    public void commit() {
        try {
            this.transactionListener.beforeCommit(this.entities.types());
            if (this.supportsTransaction) {
                this.connection.commit();
                this.committed = true;
            }
            this.transactionListener.afterCommit(this.entities.types());
            this.entities.clear();
            resetConnection();
            close();
        } catch (SQLException e) {
            throw new TransactionException((Throwable) e);
        } catch (Throwable th) {
            resetConnection();
            close();
            throw th;
        }
    }

    public void rollback() {
        try {
            this.transactionListener.beforeRollback(this.entities.types());
            if (this.supportsTransaction) {
                this.connection.rollback();
                this.rolledBack = true;
                this.entities.clearAndInvalidate();
            }
            this.transactionListener.afterRollback(this.entities.types());
            this.entities.clear();
            resetConnection();
        } catch (SQLException e) {
            throw new TransactionException((Throwable) e);
        } catch (Throwable th) {
            resetConnection();
            throw th;
        }
    }

    public boolean active() {
        boolean z = false;
        try {
            if (this.connection != null && !this.connection.getAutoCommit()) {
                z = true;
            }
            return z;
        } catch (SQLException unused) {
            return false;
        }
    }

    public void addToTransaction(EntityProxy<?> entityProxy) {
        this.entities.add(entityProxy);
    }

    public void addToTransaction(Collection<Type<?>> collection) {
        this.entities.types().addAll(collection);
    }

    private void resetConnection() {
        if (this.supportsTransaction) {
            try {
                this.connection.setAutoCommit(true);
                if (this.previousIsolationLevel != -1) {
                    this.connection.setTransactionIsolation(this.previousIsolationLevel);
                }
            } catch (SQLException unused) {
            }
        }
    }
}
