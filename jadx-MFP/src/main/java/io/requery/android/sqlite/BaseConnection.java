package io.requery.android.sqlite;

import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;

public abstract class BaseConnection implements Connection {
    protected boolean autoCommit = true;
    protected Properties clientInfo = new Properties();
    protected int holdability = 1;
    protected int savePointId;
    protected int transactionIsolation = 8;

    private static class DatabaseSavepoint implements Savepoint {
        private final int id;
        private final String name;

        DatabaseSavepoint(int i, String str) {
            this.id = i;
            this.name = str;
        }

        public int getSavepointId() throws SQLException {
            return this.id;
        }

        public String getSavepointName() throws SQLException {
            return this.name;
        }
    }

    public void clearWarnings() throws SQLException {
    }

    public void close() throws SQLException {
    }

    public void commit() throws SQLException {
    }

    /* access modifiers changed from: protected */
    public abstract void ensureTransaction();

    /* access modifiers changed from: protected */
    public abstract void execSQL(String str) throws SQLException;

    public String getCatalog() throws SQLException {
        return null;
    }

    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return null;
    }

    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    public boolean isWrapperFor(Class<?> cls) throws SQLException {
        return false;
    }

    public String nativeSQL(String str) throws SQLException {
        return str;
    }

    public <T> T unwrap(Class<T> cls) throws SQLException {
        return null;
    }

    protected BaseConnection() {
    }

    public static void throwSQLException(android.database.SQLException sQLException) throws SQLException {
        if (sQLException instanceof SQLiteConstraintException) {
            throw new SQLIntegrityConstraintViolationException(sQLException);
        } else if ((sQLException instanceof SQLiteCantOpenDatabaseException) || (sQLException instanceof SQLiteDatabaseCorruptException) || (sQLException instanceof SQLiteAccessPermException)) {
            throw new SQLNonTransientException(sQLException);
        } else {
            throw new SQLException(sQLException);
        }
    }

    public Statement createStatement(int i, int i2) throws SQLException {
        return createStatement(i, i2, 1);
    }

    public boolean getAutoCommit() throws SQLException {
        return this.autoCommit;
    }

    public int getHoldability() throws SQLException {
        return this.holdability;
    }

    public int getTransactionIsolation() throws SQLException {
        return this.transactionIsolation;
    }

    public CallableStatement prepareCall(String str) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public CallableStatement prepareCall(String str, int i, int i2) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public CallableStatement prepareCall(String str, int i, int i2, int i3) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public PreparedStatement prepareStatement(String str) throws SQLException {
        return prepareStatement(str, 2);
    }

    public PreparedStatement prepareStatement(String str, int[] iArr) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public PreparedStatement prepareStatement(String str, int i, int i2) throws SQLException {
        return prepareStatement(str, i, i2, 1);
    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("release savepoint ");
        sb.append(savepoint.getSavepointName());
        execSQL(sb.toString());
    }

    public void rollback(Savepoint savepoint) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("rollback to savepoint ");
        sb.append(savepoint.getSavepointName());
        execSQL(sb.toString());
    }

    public void setAutoCommit(boolean z) throws SQLException {
        this.autoCommit = z;
        ensureTransaction();
    }

    public void setCatalog(String str) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void setHoldability(int i) throws SQLException {
        this.holdability = i;
    }

    public void setReadOnly(boolean z) throws SQLException {
        throw new SQLFeatureNotSupportedException("cannot change readonly mode after db opened");
    }

    public Savepoint setSavepoint() throws SQLException {
        return setSavepoint(null);
    }

    public Savepoint setSavepoint(String str) throws SQLException {
        this.savePointId++;
        if (str == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("sp");
            sb.append(String.valueOf(this.savePointId));
            str = sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("savepoint ");
        sb2.append(str);
        execSQL(sb2.toString());
        return new DatabaseSavepoint(this.savePointId, str);
    }

    public void setTransactionIsolation(int i) throws SQLException {
        if (i != 4) {
            if (i != 8) {
                switch (i) {
                    case 0:
                    case 2:
                        break;
                    case 1:
                        execSQL("PRAGMA read_uncommitted = true");
                        this.transactionIsolation = i;
                        return;
                    default:
                        StringBuilder sb = new StringBuilder();
                        sb.append("invalid isolation ");
                        sb.append(i);
                        throw new SQLException(sb.toString());
                }
            }
            execSQL("PRAGMA read_uncommitted = false");
            this.transactionIsolation = i;
            return;
        }
        throw new SQLFeatureNotSupportedException();
    }

    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public Clob createClob() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public Blob createBlob() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public NClob createNClob() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public SQLXML createSQLXML() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean isValid(int i) throws SQLException {
        return !isClosed();
    }

    public void setClientInfo(String str, String str2) throws SQLClientInfoException {
        this.clientInfo.setProperty(str, str2);
    }

    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        if (properties != null) {
            this.clientInfo = properties;
        }
    }

    public String getClientInfo(String str) throws SQLException {
        return this.clientInfo.getProperty(str);
    }

    public Properties getClientInfo() throws SQLException {
        return this.clientInfo;
    }

    public Array createArrayOf(String str, Object[] objArr) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public Struct createStruct(String str, Object[] objArr) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
}
