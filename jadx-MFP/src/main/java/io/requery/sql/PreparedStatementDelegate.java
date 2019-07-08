package io.requery.sql;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

class PreparedStatementDelegate extends StatementDelegate implements PreparedStatement {
    private final PreparedStatement statement;

    PreparedStatementDelegate(PreparedStatement preparedStatement) {
        super(preparedStatement);
        this.statement = preparedStatement;
    }

    public ResultSet executeQuery() throws SQLException {
        return this.statement.executeQuery();
    }

    public int executeUpdate() throws SQLException {
        return this.statement.executeUpdate();
    }

    public void setNull(int i, int i2) throws SQLException {
        this.statement.setNull(i, i2);
    }

    public void setBoolean(int i, boolean z) throws SQLException {
        this.statement.setBoolean(i, z);
    }

    public void setByte(int i, byte b) throws SQLException {
        this.statement.setByte(i, b);
    }

    public void setShort(int i, short s) throws SQLException {
        this.statement.setShort(i, s);
    }

    public void setInt(int i, int i2) throws SQLException {
        this.statement.setInt(i, i2);
    }

    public void setLong(int i, long j) throws SQLException {
        this.statement.setLong(i, j);
    }

    public void setFloat(int i, float f) throws SQLException {
        this.statement.setFloat(i, f);
    }

    public void setDouble(int i, double d) throws SQLException {
        this.statement.setDouble(i, d);
    }

    public void setBigDecimal(int i, BigDecimal bigDecimal) throws SQLException {
        this.statement.setBigDecimal(i, bigDecimal);
    }

    public void setString(int i, String str) throws SQLException {
        this.statement.setString(i, str);
    }

    public void setBytes(int i, byte[] bArr) throws SQLException {
        this.statement.setBytes(i, bArr);
    }

    public void setDate(int i, Date date) throws SQLException {
        this.statement.setDate(i, date);
    }

    public void setTime(int i, Time time) throws SQLException {
        this.statement.setTime(i, time);
    }

    public void setTimestamp(int i, Timestamp timestamp) throws SQLException {
        this.statement.setTimestamp(i, timestamp);
    }

    public void setAsciiStream(int i, InputStream inputStream, int i2) throws SQLException {
        this.statement.setAsciiStream(i, inputStream, i2);
    }

    public void setUnicodeStream(int i, InputStream inputStream, int i2) throws SQLException {
        this.statement.setUnicodeStream(i, inputStream, i2);
    }

    public void setBinaryStream(int i, InputStream inputStream, int i2) throws SQLException {
        this.statement.setBinaryStream(i, inputStream, i2);
    }

    public void clearParameters() throws SQLException {
        this.statement.clearParameters();
    }

    public void setObject(int i, Object obj, int i2) throws SQLException {
        this.statement.setObject(i, obj, i2);
    }

    public void setObject(int i, Object obj) throws SQLException {
        this.statement.setObject(i, obj);
    }

    public boolean execute() throws SQLException {
        return this.statement.execute();
    }

    public void addBatch() throws SQLException {
        this.statement.addBatch();
    }

    public void setCharacterStream(int i, Reader reader, int i2) throws SQLException {
        this.statement.setCharacterStream(i, reader, i2);
    }

    public void setRef(int i, Ref ref) throws SQLException {
        this.statement.setRef(i, ref);
    }

    public void setBlob(int i, Blob blob) throws SQLException {
        this.statement.setBlob(i, blob);
    }

    public void setClob(int i, Clob clob) throws SQLException {
        this.statement.setClob(i, clob);
    }

    public void setArray(int i, Array array) throws SQLException {
        this.statement.setArray(i, array);
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return this.statement.getMetaData();
    }

    public void setDate(int i, Date date, Calendar calendar) throws SQLException {
        this.statement.setDate(i, date, calendar);
    }

    public void setTime(int i, Time time, Calendar calendar) throws SQLException {
        this.statement.setTime(i, time, calendar);
    }

    public void setTimestamp(int i, Timestamp timestamp, Calendar calendar) throws SQLException {
        this.statement.setTimestamp(i, timestamp, calendar);
    }

    public void setNull(int i, int i2, String str) throws SQLException {
        this.statement.setNull(i, i2, str);
    }

    public void setURL(int i, URL url) throws SQLException {
        this.statement.setURL(i, url);
    }

    public ParameterMetaData getParameterMetaData() throws SQLException {
        return this.statement.getParameterMetaData();
    }

    public void setRowId(int i, RowId rowId) throws SQLException {
        this.statement.setRowId(i, rowId);
    }

    public void setNString(int i, String str) throws SQLException {
        this.statement.setNString(i, str);
    }

    public void setNCharacterStream(int i, Reader reader, long j) throws SQLException {
        this.statement.setNCharacterStream(i, reader, j);
    }

    public void setNClob(int i, NClob nClob) throws SQLException {
        this.statement.setNClob(i, nClob);
    }

    public void setClob(int i, Reader reader, long j) throws SQLException {
        this.statement.setClob(i, reader, j);
    }

    public void setBlob(int i, InputStream inputStream, long j) throws SQLException {
        this.statement.setBlob(i, inputStream, j);
    }

    public void setNClob(int i, Reader reader, long j) throws SQLException {
        this.statement.setNClob(i, reader, j);
    }

    public void setSQLXML(int i, SQLXML sqlxml) throws SQLException {
        this.statement.setSQLXML(i, sqlxml);
    }

    public void setObject(int i, Object obj, int i2, int i3) throws SQLException {
        this.statement.setObject(i, obj, i2, i3);
    }

    public void setAsciiStream(int i, InputStream inputStream, long j) throws SQLException {
        this.statement.setAsciiStream(i, inputStream, j);
    }

    public void setBinaryStream(int i, InputStream inputStream, long j) throws SQLException {
        this.statement.setBinaryStream(i, inputStream, j);
    }

    public void setCharacterStream(int i, Reader reader, long j) throws SQLException {
        this.statement.setCharacterStream(i, reader, j);
    }

    public void setAsciiStream(int i, InputStream inputStream) throws SQLException {
        this.statement.setAsciiStream(i, inputStream);
    }

    public void setBinaryStream(int i, InputStream inputStream) throws SQLException {
        this.statement.setBinaryStream(i, inputStream);
    }

    public void setCharacterStream(int i, Reader reader) throws SQLException {
        this.statement.setCharacterStream(i, reader);
    }

    public void setNCharacterStream(int i, Reader reader) throws SQLException {
        this.statement.setNCharacterStream(i, reader);
    }

    public void setClob(int i, Reader reader) throws SQLException {
        this.statement.setClob(i, reader);
    }

    public void setBlob(int i, InputStream inputStream) throws SQLException {
        this.statement.setBlob(i, inputStream);
    }

    public void setNClob(int i, Reader reader) throws SQLException {
        this.statement.setNClob(i, reader);
    }
}
