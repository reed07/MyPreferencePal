package io.requery.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

class StatementDelegate implements Statement {
    private final Statement statement;

    StatementDelegate(Statement statement2) {
        this.statement = statement2;
    }

    public ResultSet executeQuery(String str) throws SQLException {
        return this.statement.executeQuery(str);
    }

    public int executeUpdate(String str) throws SQLException {
        return this.statement.executeUpdate(str);
    }

    public void close() throws SQLException {
        this.statement.close();
    }

    public int getMaxFieldSize() throws SQLException {
        return this.statement.getMaxFieldSize();
    }

    public void setMaxFieldSize(int i) throws SQLException {
        this.statement.setMaxFieldSize(i);
    }

    public int getMaxRows() throws SQLException {
        return this.statement.getMaxRows();
    }

    public void setMaxRows(int i) throws SQLException {
        this.statement.setMaxRows(i);
    }

    public void setEscapeProcessing(boolean z) throws SQLException {
        this.statement.setEscapeProcessing(z);
    }

    public int getQueryTimeout() throws SQLException {
        return this.statement.getQueryTimeout();
    }

    public void setQueryTimeout(int i) throws SQLException {
        this.statement.setQueryTimeout(i);
    }

    public void cancel() throws SQLException {
        this.statement.cancel();
    }

    public SQLWarning getWarnings() throws SQLException {
        return this.statement.getWarnings();
    }

    public void clearWarnings() throws SQLException {
        this.statement.clearWarnings();
    }

    public void setCursorName(String str) throws SQLException {
        this.statement.setCursorName(str);
    }

    public boolean execute(String str) throws SQLException {
        return this.statement.execute(str);
    }

    public ResultSet getResultSet() throws SQLException {
        return this.statement.getResultSet();
    }

    public int getUpdateCount() throws SQLException {
        return this.statement.getUpdateCount();
    }

    public boolean getMoreResults() throws SQLException {
        return this.statement.getMoreResults();
    }

    public void setFetchDirection(int i) throws SQLException {
        this.statement.setFetchDirection(i);
    }

    public int getFetchDirection() throws SQLException {
        return this.statement.getFetchDirection();
    }

    public void setFetchSize(int i) throws SQLException {
        this.statement.setFetchSize(i);
    }

    public int getFetchSize() throws SQLException {
        return this.statement.getFetchSize();
    }

    public int getResultSetConcurrency() throws SQLException {
        return this.statement.getResultSetConcurrency();
    }

    public int getResultSetType() throws SQLException {
        return this.statement.getResultSetType();
    }

    public void addBatch(String str) throws SQLException {
        this.statement.addBatch(str);
    }

    public void clearBatch() throws SQLException {
        this.statement.clearBatch();
    }

    public int[] executeBatch() throws SQLException {
        return this.statement.executeBatch();
    }

    public Connection getConnection() throws SQLException {
        return this.statement.getConnection();
    }

    public boolean getMoreResults(int i) throws SQLException {
        return this.statement.getMoreResults(i);
    }

    public ResultSet getGeneratedKeys() throws SQLException {
        return this.statement.getGeneratedKeys();
    }

    public int executeUpdate(String str, int i) throws SQLException {
        return this.statement.executeUpdate(str, i);
    }

    public int executeUpdate(String str, int[] iArr) throws SQLException {
        return this.statement.executeUpdate(str, iArr);
    }

    public int executeUpdate(String str, String[] strArr) throws SQLException {
        return this.statement.executeUpdate(str, strArr);
    }

    public boolean execute(String str, int i) throws SQLException {
        return this.statement.execute(str, i);
    }

    public boolean execute(String str, int[] iArr) throws SQLException {
        return this.statement.execute(str, iArr);
    }

    public boolean execute(String str, String[] strArr) throws SQLException {
        return this.statement.execute(str, strArr);
    }

    public int getResultSetHoldability() throws SQLException {
        return this.statement.getResultSetHoldability();
    }

    public boolean isClosed() throws SQLException {
        return this.statement.isClosed();
    }

    public void setPoolable(boolean z) throws SQLException {
        this.statement.setPoolable(z);
    }

    public boolean isPoolable() throws SQLException {
        return this.statement.isPoolable();
    }

    public <T> T unwrap(Class<T> cls) throws SQLException {
        return this.statement.unwrap(cls);
    }

    public boolean isWrapperFor(Class<?> cls) throws SQLException {
        return this.statement.isWrapperFor(cls);
    }
}
