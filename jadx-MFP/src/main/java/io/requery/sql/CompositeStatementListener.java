package io.requery.sql;

import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

class CompositeStatementListener implements StatementListener {
    private final Set<StatementListener> listeners = new HashSet();

    CompositeStatementListener(Set<StatementListener> set) {
        if (set != null) {
            this.listeners.addAll(set);
        }
    }

    public void add(StatementListener statementListener) {
        this.listeners.add(statementListener);
    }

    public void beforeExecuteUpdate(Statement statement, String str, BoundParameters boundParameters) {
        for (StatementListener beforeExecuteUpdate : this.listeners) {
            beforeExecuteUpdate.beforeExecuteUpdate(statement, str, boundParameters);
        }
    }

    public void afterExecuteUpdate(Statement statement, int i) {
        for (StatementListener afterExecuteUpdate : this.listeners) {
            afterExecuteUpdate.afterExecuteUpdate(statement, i);
        }
    }

    public void beforeExecuteQuery(Statement statement, String str, BoundParameters boundParameters) {
        for (StatementListener beforeExecuteQuery : this.listeners) {
            beforeExecuteQuery.beforeExecuteQuery(statement, str, boundParameters);
        }
    }

    public void afterExecuteQuery(Statement statement) {
        for (StatementListener afterExecuteQuery : this.listeners) {
            afterExecuteQuery.afterExecuteQuery(statement);
        }
    }
}
