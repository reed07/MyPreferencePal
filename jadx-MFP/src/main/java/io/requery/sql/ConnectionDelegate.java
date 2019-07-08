package io.requery.sql;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;

class ConnectionDelegate implements Connection {
    private final Connection connection;

    ConnectionDelegate(Connection connection2) {
        this.connection = connection2;
    }

    public Statement createStatement() throws SQLException {
        return this.connection.createStatement();
    }

    public PreparedStatement prepareStatement(String str) throws SQLException {
        return this.connection.prepareStatement(str);
    }

    public CallableStatement prepareCall(String str) throws SQLException {
        return this.connection.prepareCall(str);
    }

    public String nativeSQL(String str) throws SQLException {
        return this.connection.nativeSQL(str);
    }

    public void setAutoCommit(boolean z) throws SQLException {
        this.connection.setAutoCommit(z);
    }

    public boolean getAutoCommit() throws SQLException {
        return this.connection.getAutoCommit();
    }

    public void commit() throws SQLException {
        this.connection.commit();
    }

    public void rollback() throws SQLException {
        this.connection.rollback();
    }

    public void close() throws SQLException {
        this.connection.close();
    }

    public boolean isClosed() throws SQLException {
        return this.connection.isClosed();
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        return this.connection.getMetaData();
    }

    public void setReadOnly(boolean z) throws SQLException {
        this.connection.setReadOnly(z);
    }

    public boolean isReadOnly() throws SQLException {
        return this.connection.isReadOnly();
    }

    public void setCatalog(String str) throws SQLException {
        this.connection.setCatalog(str);
    }

    public String getCatalog() throws SQLException {
        return this.connection.getCatalog();
    }

    public void setTransactionIsolation(int i) throws SQLException {
        this.connection.setTransactionIsolation(i);
    }

    public int getTransactionIsolation() throws SQLException {
        return this.connection.getTransactionIsolation();
    }

    public SQLWarning getWarnings() throws SQLException {
        return this.connection.getWarnings();
    }

    public void clearWarnings() throws SQLException {
        this.connection.clearWarnings();
    }

    public Statement createStatement(int i, int i2) throws SQLException {
        return this.connection.createStatement(i, i2);
    }

    public PreparedStatement prepareStatement(String str, int i, int i2) throws SQLException {
        return this.connection.prepareStatement(str, i, i2);
    }

    public CallableStatement prepareCall(String str, int i, int i2) throws SQLException {
        return this.connection.prepareCall(str, i, i2);
    }

    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return this.connection.getTypeMap();
    }

    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        this.connection.setTypeMap(map);
    }

    public void setHoldability(int i) throws SQLException {
        this.connection.setHoldability(i);
    }

    public int getHoldability() throws SQLException {
        return this.connection.getHoldability();
    }

    public Savepoint setSavepoint() throws SQLException {
        return this.connection.setSavepoint();
    }

    public Savepoint setSavepoint(String str) throws SQLException {
        return this.connection.setSavepoint(str);
    }

    public void rollback(Savepoint savepoint) throws SQLException {
        this.connection.rollback(savepoint);
    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        this.connection.releaseSavepoint(savepoint);
    }

    public Statement createStatement(int i, int i2, int i3) throws SQLException {
        return this.connection.createStatement(i, i2, i3);
    }

    public PreparedStatement prepareStatement(String str, int i, int i2, int i3) throws SQLException {
        return this.connection.prepareStatement(str, i, i2, i3);
    }

    public CallableStatement prepareCall(String str, int i, int i2, int i3) throws SQLException {
        return this.connection.prepareCall(str, i, i2, i3);
    }

    public PreparedStatement prepareStatement(String str, int i) throws SQLException {
        return this.connection.prepareStatement(str, i);
    }

    public PreparedStatement prepareStatement(String str, int[] iArr) throws SQLException {
        return this.connection.prepareStatement(str, iArr);
    }

    public PreparedStatement prepareStatement(String str, String[] strArr) throws SQLException {
        return this.connection.prepareStatement(str, strArr);
    }

    public Clob createClob() throws SQLException {
        return this.connection.createClob();
    }

    public Blob createBlob() throws SQLException {
        return this.connection.createBlob();
    }

    public NClob createNClob() throws SQLException {
        return this.connection.createNClob();
    }

    public SQLXML createSQLXML() throws SQLException {
        return this.connection.createSQLXML();
    }

    public boolean isValid(int i) throws SQLException {
        return this.connection.isValid(i);
    }

    public void setClientInfo(String str, String str2) throws SQLClientInfoException {
        this.connection.setClientInfo(str, str2);
    }

    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        this.connection.setClientInfo(properties);
    }

    public String getClientInfo(String str) throws SQLException {
        return this.connection.getClientInfo(str);
    }

    public Properties getClientInfo() throws SQLException {
        return this.connection.getClientInfo();
    }

    public Array createArrayOf(String str, Object[] objArr) throws SQLException {
        return this.connection.createArrayOf(str, objArr);
    }

    public Struct createStruct(String str, Object[] objArr) throws SQLException {
        return this.connection.createStruct(str, objArr);
    }

    public <T> T unwrap(Class<T> cls) throws SQLException {
        return this.connection.unwrap(cls);
    }

    public boolean isWrapperFor(Class<?> cls) throws SQLException {
        return this.connection.isWrapperFor(cls);
    }
}
