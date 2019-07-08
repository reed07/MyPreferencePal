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
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.UserTransaction;

class ManagedTransaction implements ConnectionProvider, EntityTransaction, Synchronization {
    private boolean committed;
    private boolean completed;
    private Connection connection;
    private final ConnectionProvider connectionProvider;
    private final TransactionEntitiesSet entities;
    private boolean initiatedTransaction;
    private TransactionSynchronizationRegistry registry;
    private boolean rolledBack;
    private final TransactionListener transactionListener;
    private Connection uncloseableConnection;
    private UserTransaction userTransaction;

    ManagedTransaction(TransactionListener transactionListener2, ConnectionProvider connectionProvider2, EntityCache entityCache) {
        this.transactionListener = (TransactionListener) Objects.requireNotNull(transactionListener2);
        this.connectionProvider = (ConnectionProvider) Objects.requireNotNull(connectionProvider2);
        this.entities = new TransactionEntitiesSet(entityCache);
    }

    private TransactionSynchronizationRegistry getSynchronizationRegistry() {
        if (this.registry == null) {
            try {
                this.registry = (TransactionSynchronizationRegistry) InitialContext.doLookup("java:comp/TransactionSynchronizationRegistry");
            } catch (NamingException e) {
                throw new TransactionException((Throwable) e);
            }
        }
        return this.registry;
    }

    private UserTransaction getUserTransaction() {
        if (this.userTransaction == null) {
            try {
                this.userTransaction = (UserTransaction) InitialContext.doLookup("java:comp/UserTransaction");
            } catch (NamingException e) {
                throw new TransactionException((Throwable) e);
            }
        }
        return this.userTransaction;
    }

    public Connection getConnection() {
        return this.uncloseableConnection;
    }

    public Transaction begin() {
        if (!active()) {
            this.transactionListener.beforeBegin(null);
            if (getSynchronizationRegistry().getTransactionStatus() == 6) {
                try {
                    getUserTransaction().begin();
                    this.initiatedTransaction = true;
                } catch (NotSupportedException | SystemException e) {
                    throw new TransactionException((Throwable) e);
                }
            }
            getSynchronizationRegistry().registerInterposedSynchronization(this);
            try {
                this.connection = this.connectionProvider.getConnection();
                this.uncloseableConnection = new UncloseableConnection(this.connection);
                this.committed = false;
                this.rolledBack = false;
                this.entities.clear();
                this.transactionListener.afterBegin(null);
                return this;
            } catch (SQLException e2) {
                throw new TransactionException((Throwable) e2);
            }
        } else {
            throw new IllegalStateException("transaction already active");
        }
    }

    public Transaction begin(TransactionIsolation transactionIsolation) {
        if (transactionIsolation == null) {
            return begin();
        }
        throw new TransactionException("isolation can't be specified in managed mode");
    }

    public void close() {
        if (this.connection != null) {
            if (!this.committed && !this.rolledBack) {
                rollback();
            }
            try {
                this.connection.close();
            } catch (SQLException unused) {
            } catch (Throwable th) {
                this.connection = null;
                throw th;
            }
            this.connection = null;
        }
    }

    public void commit() {
        if (this.initiatedTransaction) {
            try {
                this.transactionListener.beforeCommit(this.entities.types());
                getUserTransaction().commit();
                this.transactionListener.afterCommit(this.entities.types());
            } catch (HeuristicMixedException | HeuristicRollbackException | RollbackException | SystemException e) {
                throw new TransactionException((Throwable) e);
            }
        }
        try {
            this.entities.clear();
        } finally {
            close();
        }
    }

    public void rollback() {
        if (!this.rolledBack) {
            try {
                if (!this.completed) {
                    this.transactionListener.beforeRollback(this.entities.types());
                    if (this.initiatedTransaction) {
                        getUserTransaction().rollback();
                    } else if (active()) {
                        getSynchronizationRegistry().setRollbackOnly();
                    }
                    this.transactionListener.afterRollback(this.entities.types());
                }
                this.rolledBack = true;
                this.entities.clearAndInvalidate();
            } catch (SystemException e) {
                throw new TransactionException((Throwable) e);
            } catch (Throwable th) {
                this.rolledBack = true;
                this.entities.clearAndInvalidate();
                throw th;
            }
        }
    }

    public boolean active() {
        TransactionSynchronizationRegistry synchronizationRegistry = getSynchronizationRegistry();
        return synchronizationRegistry != null && synchronizationRegistry.getTransactionStatus() == 0;
    }

    public void addToTransaction(EntityProxy<?> entityProxy) {
        this.entities.add(entityProxy);
    }

    public void addToTransaction(Collection<Type<?>> collection) {
        this.entities.types().addAll(collection);
    }
}
