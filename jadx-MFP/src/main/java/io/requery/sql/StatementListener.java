package io.requery.sql;

import java.sql.Statement;

public interface StatementListener {
    void afterExecuteQuery(Statement statement);

    void afterExecuteUpdate(Statement statement, int i);

    void beforeExecuteQuery(Statement statement, String str, BoundParameters boundParameters);

    void beforeExecuteUpdate(Statement statement, String str, BoundParameters boundParameters);
}
