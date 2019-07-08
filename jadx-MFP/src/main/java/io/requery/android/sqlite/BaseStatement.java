package io.requery.android.sqlite;

import com.brightcove.player.network.DownloadStatus;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.Statement;

public abstract class BaseStatement implements Statement {
    private boolean closed;
    protected final BaseConnection connection;
    private int fetchSize;
    protected ResultSet insertResult;
    private int maxFieldSize;
    private int maxRows;
    protected ResultSet queryResult;
    private int timeout;
    protected int updateCount;

    public void clearWarnings() throws SQLException {
    }

    public int getFetchDirection() throws SQLException {
        return 1000;
    }

    public boolean getMoreResults(int i) throws SQLException {
        return false;
    }

    public int getResultSetConcurrency() throws SQLException {
        return DownloadStatus.ERROR_DEVICE_NOT_FOUND;
    }

    public int getResultSetType() throws SQLException {
        return 1004;
    }

    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    public boolean isPoolable() throws SQLException {
        return false;
    }

    public boolean isWrapperFor(Class<?> cls) throws SQLException {
        return false;
    }

    public void setCursorName(String str) throws SQLException {
    }

    public void setEscapeProcessing(boolean z) throws SQLException {
    }

    protected BaseStatement(BaseConnection baseConnection) {
        if (baseConnection != null) {
            this.connection = baseConnection;
            return;
        }
        throw new IllegalArgumentException("null connection");
    }

    /* access modifiers changed from: protected */
    public void throwIfClosed() throws SQLException {
        if (isClosed()) {
            throw new SQLException("closed");
        }
    }

    public void addBatch(String str) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void cancel() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void clearBatch() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void close() throws SQLException {
        ResultSet resultSet = this.queryResult;
        if (resultSet != null) {
            resultSet.close();
        }
        this.closed = true;
    }

    public boolean execute(String str) throws SQLException {
        throwIfClosed();
        this.connection.execSQL(str);
        return false;
    }

    public boolean execute(String str, int[] iArr) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public boolean execute(String str, String[] strArr) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public int[] executeBatch() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public int executeUpdate(String str) throws SQLException {
        return executeUpdate(str, 2);
    }

    public int executeUpdate(String str, int[] iArr) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public int executeUpdate(String str, String[] strArr) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public Connection getConnection() throws SQLException {
        throwIfClosed();
        return this.connection;
    }

    public int getFetchSize() throws SQLException {
        return this.fetchSize;
    }

    public ResultSet getGeneratedKeys() throws SQLException {
        return this.insertResult;
    }

    public int getMaxFieldSize() throws SQLException {
        return this.maxFieldSize;
    }

    public int getMaxRows() throws SQLException {
        return this.maxRows;
    }

    public boolean getMoreResults() throws SQLException {
        return getMoreResults(1);
    }

    public int getQueryTimeout() throws SQLException {
        return this.timeout;
    }

    public ResultSet getResultSet() throws SQLException {
        throwIfClosed();
        return this.queryResult;
    }

    public int getResultSetHoldability() throws SQLException {
        return this.connection.getHoldability();
    }

    public int getUpdateCount() throws SQLException {
        return this.updateCount;
    }

    public void setFetchDirection(int i) throws SQLException {
        if (i != 1000) {
            throw new SQLFeatureNotSupportedException("only FETCH_FORWARD is supported");
        }
    }

    public void setFetchSize(int i) throws SQLException {
        this.fetchSize = i;
    }

    public void setMaxFieldSize(int i) throws SQLException {
        this.maxFieldSize = i;
    }

    public void setMaxRows(int i) throws SQLException {
        this.maxRows = i;
    }

    public void setQueryTimeout(int i) throws SQLException {
        this.timeout = i;
    }

    public boolean isClosed() throws SQLException {
        return this.closed;
    }

    public void setPoolable(boolean z) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public <T> T unwrap(Class<T> cls) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
}
