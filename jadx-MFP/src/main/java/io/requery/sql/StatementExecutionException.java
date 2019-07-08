package io.requery.sql;

import io.requery.PersistenceException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementExecutionException extends PersistenceException {
    private static boolean useSuppressed = (!System.getProperty("java.vendor").contains("Android"));
    private final String sql;

    static StatementExecutionException closing(Statement statement, Throwable th, String str) {
        StatementExecutionException statementExecutionException = new StatementExecutionException(th, str);
        if (statement != null) {
            Connection connection = null;
            try {
                connection = statement.getConnection();
            } catch (SQLException e) {
                if (useSuppressed) {
                    statementExecutionException.addSuppressed(e);
                }
            }
            statementExecutionException.closeSuppressed(statement);
            statementExecutionException.closeSuppressed(connection);
        }
        return statementExecutionException;
    }

    StatementExecutionException(Throwable th, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("Exception executing statement: ");
        sb.append(str);
        super(sb.toString(), th);
        this.sql = str;
    }

    private void closeSuppressed(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                if (useSuppressed) {
                    addSuppressed(e);
                } else {
                    e.printStackTrace();
                }
            }
        }
    }
}
