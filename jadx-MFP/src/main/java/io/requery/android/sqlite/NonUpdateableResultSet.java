package io.requery.android.sqlite;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;

abstract class NonUpdateableResultSet implements ResultSet {
    NonUpdateableResultSet() {
    }

    public void updateArray(int i, Array array) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateArray(String str, Array array) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateAsciiStream(int i, InputStream inputStream, int i2) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateAsciiStream(String str, InputStream inputStream, int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBigDecimal(int i, BigDecimal bigDecimal) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBigDecimal(String str, BigDecimal bigDecimal) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBinaryStream(int i, InputStream inputStream, int i2) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBinaryStream(String str, InputStream inputStream, int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBlob(int i, Blob blob) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBlob(String str, Blob blob) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBoolean(int i, boolean z) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBoolean(String str, boolean z) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateByte(int i, byte b) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateByte(String str, byte b) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBytes(int i, byte[] bArr) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBytes(String str, byte[] bArr) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateCharacterStream(int i, Reader reader, int i2) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateCharacterStream(String str, Reader reader, int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateClob(int i, Clob clob) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateClob(String str, Clob clob) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateDate(int i, Date date) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateDate(String str, Date date) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateDouble(int i, double d) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateDouble(String str, double d) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateFloat(int i, float f) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateFloat(String str, float f) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateInt(int i, int i2) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateInt(String str, int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateLong(int i, long j) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateLong(String str, long j) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNull(int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNull(String str) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateObject(int i, Object obj) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateObject(int i, Object obj, int i2) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateObject(String str, Object obj) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateObject(String str, Object obj, int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateRef(int i, Ref ref) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateRef(String str, Ref ref) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateRow() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateShort(int i, short s) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateShort(String str, short s) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateString(int i, String str) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateString(String str, String str2) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateTime(int i, Time time) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateTime(String str, Time time) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateTimestamp(int i, Timestamp timestamp) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateTimestamp(String str, Timestamp timestamp) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateRowId(int i, RowId rowId) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateRowId(String str, RowId rowId) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNString(int i, String str) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNString(String str, String str2) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNClob(int i, NClob nClob) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNClob(String str, NClob nClob) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateSQLXML(int i, SQLXML sqlxml) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateSQLXML(String str, SQLXML sqlxml) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNCharacterStream(int i, Reader reader, long j) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNCharacterStream(String str, Reader reader, long j) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateAsciiStream(int i, InputStream inputStream, long j) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBinaryStream(int i, InputStream inputStream, long j) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateCharacterStream(int i, Reader reader, long j) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateAsciiStream(String str, InputStream inputStream, long j) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBinaryStream(String str, InputStream inputStream, long j) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateCharacterStream(String str, Reader reader, long j) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBlob(int i, InputStream inputStream, long j) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBlob(String str, InputStream inputStream, long j) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateClob(int i, Reader reader, long j) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateClob(String str, Reader reader, long j) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNClob(int i, Reader reader, long j) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNClob(String str, Reader reader, long j) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNCharacterStream(int i, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNCharacterStream(String str, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateAsciiStream(int i, InputStream inputStream) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBinaryStream(int i, InputStream inputStream) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateCharacterStream(int i, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateAsciiStream(String str, InputStream inputStream) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBinaryStream(String str, InputStream inputStream) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateCharacterStream(String str, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBlob(int i, InputStream inputStream) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBlob(String str, InputStream inputStream) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateClob(int i, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateClob(String str, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNClob(int i, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNClob(String str, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
}
