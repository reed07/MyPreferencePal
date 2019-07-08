package io.requery.android.sqlite;

import com.brightcove.player.network.DownloadStatus;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.constants.SharedConstants.RequestCodes;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

public class SingleResultSet extends NonUpdateableResultSet {
    private final Statement statement;
    private final long value;

    public boolean absolute(int i) throws SQLException {
        return i == 1;
    }

    public void afterLast() throws SQLException {
    }

    public void beforeFirst() throws SQLException {
    }

    public void cancelRowUpdates() throws SQLException {
    }

    public void clearWarnings() throws SQLException {
    }

    public void close() throws SQLException {
    }

    public void deleteRow() throws SQLException {
    }

    public int findColumn(String str) throws SQLException {
        return 1;
    }

    public boolean first() throws SQLException {
        return true;
    }

    public Array getArray(int i) throws SQLException {
        return null;
    }

    public Array getArray(String str) throws SQLException {
        return null;
    }

    public InputStream getAsciiStream(int i) throws SQLException {
        return null;
    }

    public InputStream getAsciiStream(String str) throws SQLException {
        return null;
    }

    public BigDecimal getBigDecimal(int i) throws SQLException {
        return null;
    }

    public BigDecimal getBigDecimal(int i, int i2) throws SQLException {
        return null;
    }

    public BigDecimal getBigDecimal(String str) throws SQLException {
        return null;
    }

    public BigDecimal getBigDecimal(String str, int i) throws SQLException {
        return null;
    }

    public InputStream getBinaryStream(int i) throws SQLException {
        return null;
    }

    public InputStream getBinaryStream(String str) throws SQLException {
        return null;
    }

    public Blob getBlob(int i) throws SQLException {
        return null;
    }

    public Blob getBlob(String str) throws SQLException {
        return null;
    }

    public boolean getBoolean(int i) throws SQLException {
        return false;
    }

    public boolean getBoolean(String str) throws SQLException {
        return false;
    }

    public byte getByte(int i) throws SQLException {
        return 0;
    }

    public byte getByte(String str) throws SQLException {
        return 0;
    }

    public byte[] getBytes(int i) throws SQLException {
        return null;
    }

    public byte[] getBytes(String str) throws SQLException {
        return null;
    }

    public Reader getCharacterStream(int i) throws SQLException {
        return null;
    }

    public Reader getCharacterStream(String str) throws SQLException {
        return null;
    }

    public Clob getClob(int i) throws SQLException {
        return null;
    }

    public Clob getClob(String str) throws SQLException {
        return null;
    }

    public int getConcurrency() throws SQLException {
        return DownloadStatus.ERROR_DEVICE_NOT_FOUND;
    }

    public String getCursorName() throws SQLException {
        return null;
    }

    public Date getDate(int i) throws SQLException {
        return null;
    }

    public Date getDate(int i, Calendar calendar) throws SQLException {
        return null;
    }

    public Date getDate(String str) throws SQLException {
        return null;
    }

    public Date getDate(String str, Calendar calendar) throws SQLException {
        return null;
    }

    public double getDouble(int i) throws SQLException {
        return 0.0d;
    }

    public double getDouble(String str) throws SQLException {
        return 0.0d;
    }

    public int getFetchDirection() throws SQLException {
        return 1000;
    }

    public int getFetchSize() throws SQLException {
        return 0;
    }

    public float getFloat(int i) throws SQLException {
        return BitmapDescriptorFactory.HUE_RED;
    }

    public float getFloat(String str) throws SQLException {
        return BitmapDescriptorFactory.HUE_RED;
    }

    public int getHoldability() throws SQLException {
        return 2;
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return null;
    }

    public Reader getNCharacterStream(int i) throws SQLException {
        return null;
    }

    public Reader getNCharacterStream(String str) throws SQLException {
        return null;
    }

    public NClob getNClob(int i) throws SQLException {
        return null;
    }

    public NClob getNClob(String str) throws SQLException {
        return null;
    }

    public String getNString(int i) throws SQLException {
        return null;
    }

    public String getNString(String str) throws SQLException {
        return null;
    }

    public Ref getRef(int i) throws SQLException {
        return null;
    }

    public Ref getRef(String str) throws SQLException {
        return null;
    }

    public int getRow() throws SQLException {
        return 0;
    }

    public RowId getRowId(int i) throws SQLException {
        return null;
    }

    public RowId getRowId(String str) throws SQLException {
        return null;
    }

    public SQLXML getSQLXML(int i) throws SQLException {
        return null;
    }

    public SQLXML getSQLXML(String str) throws SQLException {
        return null;
    }

    public Time getTime(int i) throws SQLException {
        return null;
    }

    public Time getTime(int i, Calendar calendar) throws SQLException {
        return null;
    }

    public Time getTime(String str) throws SQLException {
        return null;
    }

    public Time getTime(String str, Calendar calendar) throws SQLException {
        return null;
    }

    public Timestamp getTimestamp(int i) throws SQLException {
        return null;
    }

    public Timestamp getTimestamp(int i, Calendar calendar) throws SQLException {
        return null;
    }

    public Timestamp getTimestamp(String str) throws SQLException {
        return null;
    }

    public Timestamp getTimestamp(String str, Calendar calendar) throws SQLException {
        return null;
    }

    public int getType() throws SQLException {
        return RequestCodes.PICK_FROM_CAMERA;
    }

    public URL getURL(int i) throws SQLException {
        return null;
    }

    public URL getURL(String str) throws SQLException {
        return null;
    }

    public InputStream getUnicodeStream(int i) throws SQLException {
        return null;
    }

    public InputStream getUnicodeStream(String str) throws SQLException {
        return null;
    }

    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    public void insertRow() throws SQLException {
    }

    public boolean isAfterLast() throws SQLException {
        return false;
    }

    public boolean isBeforeFirst() throws SQLException {
        return false;
    }

    public boolean isClosed() throws SQLException {
        return false;
    }

    public boolean isFirst() throws SQLException {
        return false;
    }

    public boolean isLast() throws SQLException {
        return true;
    }

    public boolean isWrapperFor(Class<?> cls) throws SQLException {
        return false;
    }

    public boolean last() throws SQLException {
        return false;
    }

    public boolean next() throws SQLException {
        return true;
    }

    public boolean previous() throws SQLException {
        return false;
    }

    public void refreshRow() throws SQLException {
    }

    public boolean relative(int i) throws SQLException {
        return false;
    }

    public boolean rowDeleted() throws SQLException {
        return false;
    }

    public boolean rowInserted() throws SQLException {
        return false;
    }

    public boolean rowUpdated() throws SQLException {
        return false;
    }

    public void setFetchDirection(int i) throws SQLException {
    }

    public void setFetchSize(int i) throws SQLException {
    }

    public <T> T unwrap(Class<T> cls) throws SQLException {
        return null;
    }

    public boolean wasNull() throws SQLException {
        return false;
    }

    public /* bridge */ /* synthetic */ void updateArray(int i, Array array) throws SQLException {
        super.updateArray(i, array);
    }

    public /* bridge */ /* synthetic */ void updateArray(String str, Array array) throws SQLException {
        super.updateArray(str, array);
    }

    public /* bridge */ /* synthetic */ void updateAsciiStream(int i, InputStream inputStream) throws SQLException {
        super.updateAsciiStream(i, inputStream);
    }

    public /* bridge */ /* synthetic */ void updateAsciiStream(int i, InputStream inputStream, int i2) throws SQLException {
        super.updateAsciiStream(i, inputStream, i2);
    }

    public /* bridge */ /* synthetic */ void updateAsciiStream(int i, InputStream inputStream, long j) throws SQLException {
        super.updateAsciiStream(i, inputStream, j);
    }

    public /* bridge */ /* synthetic */ void updateAsciiStream(String str, InputStream inputStream) throws SQLException {
        super.updateAsciiStream(str, inputStream);
    }

    public /* bridge */ /* synthetic */ void updateAsciiStream(String str, InputStream inputStream, int i) throws SQLException {
        super.updateAsciiStream(str, inputStream, i);
    }

    public /* bridge */ /* synthetic */ void updateAsciiStream(String str, InputStream inputStream, long j) throws SQLException {
        super.updateAsciiStream(str, inputStream, j);
    }

    public /* bridge */ /* synthetic */ void updateBigDecimal(int i, BigDecimal bigDecimal) throws SQLException {
        super.updateBigDecimal(i, bigDecimal);
    }

    public /* bridge */ /* synthetic */ void updateBigDecimal(String str, BigDecimal bigDecimal) throws SQLException {
        super.updateBigDecimal(str, bigDecimal);
    }

    public /* bridge */ /* synthetic */ void updateBinaryStream(int i, InputStream inputStream) throws SQLException {
        super.updateBinaryStream(i, inputStream);
    }

    public /* bridge */ /* synthetic */ void updateBinaryStream(int i, InputStream inputStream, int i2) throws SQLException {
        super.updateBinaryStream(i, inputStream, i2);
    }

    public /* bridge */ /* synthetic */ void updateBinaryStream(int i, InputStream inputStream, long j) throws SQLException {
        super.updateBinaryStream(i, inputStream, j);
    }

    public /* bridge */ /* synthetic */ void updateBinaryStream(String str, InputStream inputStream) throws SQLException {
        super.updateBinaryStream(str, inputStream);
    }

    public /* bridge */ /* synthetic */ void updateBinaryStream(String str, InputStream inputStream, int i) throws SQLException {
        super.updateBinaryStream(str, inputStream, i);
    }

    public /* bridge */ /* synthetic */ void updateBinaryStream(String str, InputStream inputStream, long j) throws SQLException {
        super.updateBinaryStream(str, inputStream, j);
    }

    public /* bridge */ /* synthetic */ void updateBlob(int i, InputStream inputStream) throws SQLException {
        super.updateBlob(i, inputStream);
    }

    public /* bridge */ /* synthetic */ void updateBlob(int i, InputStream inputStream, long j) throws SQLException {
        super.updateBlob(i, inputStream, j);
    }

    public /* bridge */ /* synthetic */ void updateBlob(int i, Blob blob) throws SQLException {
        super.updateBlob(i, blob);
    }

    public /* bridge */ /* synthetic */ void updateBlob(String str, InputStream inputStream) throws SQLException {
        super.updateBlob(str, inputStream);
    }

    public /* bridge */ /* synthetic */ void updateBlob(String str, InputStream inputStream, long j) throws SQLException {
        super.updateBlob(str, inputStream, j);
    }

    public /* bridge */ /* synthetic */ void updateBlob(String str, Blob blob) throws SQLException {
        super.updateBlob(str, blob);
    }

    public /* bridge */ /* synthetic */ void updateBoolean(int i, boolean z) throws SQLException {
        super.updateBoolean(i, z);
    }

    public /* bridge */ /* synthetic */ void updateBoolean(String str, boolean z) throws SQLException {
        super.updateBoolean(str, z);
    }

    public /* bridge */ /* synthetic */ void updateByte(int i, byte b) throws SQLException {
        super.updateByte(i, b);
    }

    public /* bridge */ /* synthetic */ void updateByte(String str, byte b) throws SQLException {
        super.updateByte(str, b);
    }

    public /* bridge */ /* synthetic */ void updateBytes(int i, byte[] bArr) throws SQLException {
        super.updateBytes(i, bArr);
    }

    public /* bridge */ /* synthetic */ void updateBytes(String str, byte[] bArr) throws SQLException {
        super.updateBytes(str, bArr);
    }

    public /* bridge */ /* synthetic */ void updateCharacterStream(int i, Reader reader) throws SQLException {
        super.updateCharacterStream(i, reader);
    }

    public /* bridge */ /* synthetic */ void updateCharacterStream(int i, Reader reader, int i2) throws SQLException {
        super.updateCharacterStream(i, reader, i2);
    }

    public /* bridge */ /* synthetic */ void updateCharacterStream(int i, Reader reader, long j) throws SQLException {
        super.updateCharacterStream(i, reader, j);
    }

    public /* bridge */ /* synthetic */ void updateCharacterStream(String str, Reader reader) throws SQLException {
        super.updateCharacterStream(str, reader);
    }

    public /* bridge */ /* synthetic */ void updateCharacterStream(String str, Reader reader, int i) throws SQLException {
        super.updateCharacterStream(str, reader, i);
    }

    public /* bridge */ /* synthetic */ void updateCharacterStream(String str, Reader reader, long j) throws SQLException {
        super.updateCharacterStream(str, reader, j);
    }

    public /* bridge */ /* synthetic */ void updateClob(int i, Reader reader) throws SQLException {
        super.updateClob(i, reader);
    }

    public /* bridge */ /* synthetic */ void updateClob(int i, Reader reader, long j) throws SQLException {
        super.updateClob(i, reader, j);
    }

    public /* bridge */ /* synthetic */ void updateClob(int i, Clob clob) throws SQLException {
        super.updateClob(i, clob);
    }

    public /* bridge */ /* synthetic */ void updateClob(String str, Reader reader) throws SQLException {
        super.updateClob(str, reader);
    }

    public /* bridge */ /* synthetic */ void updateClob(String str, Reader reader, long j) throws SQLException {
        super.updateClob(str, reader, j);
    }

    public /* bridge */ /* synthetic */ void updateClob(String str, Clob clob) throws SQLException {
        super.updateClob(str, clob);
    }

    public /* bridge */ /* synthetic */ void updateDate(int i, Date date) throws SQLException {
        super.updateDate(i, date);
    }

    public /* bridge */ /* synthetic */ void updateDate(String str, Date date) throws SQLException {
        super.updateDate(str, date);
    }

    public /* bridge */ /* synthetic */ void updateDouble(int i, double d) throws SQLException {
        super.updateDouble(i, d);
    }

    public /* bridge */ /* synthetic */ void updateDouble(String str, double d) throws SQLException {
        super.updateDouble(str, d);
    }

    public /* bridge */ /* synthetic */ void updateFloat(int i, float f) throws SQLException {
        super.updateFloat(i, f);
    }

    public /* bridge */ /* synthetic */ void updateFloat(String str, float f) throws SQLException {
        super.updateFloat(str, f);
    }

    public /* bridge */ /* synthetic */ void updateInt(int i, int i2) throws SQLException {
        super.updateInt(i, i2);
    }

    public /* bridge */ /* synthetic */ void updateInt(String str, int i) throws SQLException {
        super.updateInt(str, i);
    }

    public /* bridge */ /* synthetic */ void updateLong(int i, long j) throws SQLException {
        super.updateLong(i, j);
    }

    public /* bridge */ /* synthetic */ void updateLong(String str, long j) throws SQLException {
        super.updateLong(str, j);
    }

    public /* bridge */ /* synthetic */ void updateNCharacterStream(int i, Reader reader) throws SQLException {
        super.updateNCharacterStream(i, reader);
    }

    public /* bridge */ /* synthetic */ void updateNCharacterStream(int i, Reader reader, long j) throws SQLException {
        super.updateNCharacterStream(i, reader, j);
    }

    public /* bridge */ /* synthetic */ void updateNCharacterStream(String str, Reader reader) throws SQLException {
        super.updateNCharacterStream(str, reader);
    }

    public /* bridge */ /* synthetic */ void updateNCharacterStream(String str, Reader reader, long j) throws SQLException {
        super.updateNCharacterStream(str, reader, j);
    }

    public /* bridge */ /* synthetic */ void updateNClob(int i, Reader reader) throws SQLException {
        super.updateNClob(i, reader);
    }

    public /* bridge */ /* synthetic */ void updateNClob(int i, Reader reader, long j) throws SQLException {
        super.updateNClob(i, reader, j);
    }

    public /* bridge */ /* synthetic */ void updateNClob(int i, NClob nClob) throws SQLException {
        super.updateNClob(i, nClob);
    }

    public /* bridge */ /* synthetic */ void updateNClob(String str, Reader reader) throws SQLException {
        super.updateNClob(str, reader);
    }

    public /* bridge */ /* synthetic */ void updateNClob(String str, Reader reader, long j) throws SQLException {
        super.updateNClob(str, reader, j);
    }

    public /* bridge */ /* synthetic */ void updateNClob(String str, NClob nClob) throws SQLException {
        super.updateNClob(str, nClob);
    }

    public /* bridge */ /* synthetic */ void updateNString(int i, String str) throws SQLException {
        super.updateNString(i, str);
    }

    public /* bridge */ /* synthetic */ void updateNString(String str, String str2) throws SQLException {
        super.updateNString(str, str2);
    }

    public /* bridge */ /* synthetic */ void updateNull(int i) throws SQLException {
        super.updateNull(i);
    }

    public /* bridge */ /* synthetic */ void updateNull(String str) throws SQLException {
        super.updateNull(str);
    }

    public /* bridge */ /* synthetic */ void updateObject(int i, Object obj) throws SQLException {
        super.updateObject(i, obj);
    }

    public /* bridge */ /* synthetic */ void updateObject(int i, Object obj, int i2) throws SQLException {
        super.updateObject(i, obj, i2);
    }

    public /* bridge */ /* synthetic */ void updateObject(String str, Object obj) throws SQLException {
        super.updateObject(str, obj);
    }

    public /* bridge */ /* synthetic */ void updateObject(String str, Object obj, int i) throws SQLException {
        super.updateObject(str, obj, i);
    }

    public /* bridge */ /* synthetic */ void updateRef(int i, Ref ref) throws SQLException {
        super.updateRef(i, ref);
    }

    public /* bridge */ /* synthetic */ void updateRef(String str, Ref ref) throws SQLException {
        super.updateRef(str, ref);
    }

    public /* bridge */ /* synthetic */ void updateRow() throws SQLException {
        super.updateRow();
    }

    public /* bridge */ /* synthetic */ void updateRowId(int i, RowId rowId) throws SQLException {
        super.updateRowId(i, rowId);
    }

    public /* bridge */ /* synthetic */ void updateRowId(String str, RowId rowId) throws SQLException {
        super.updateRowId(str, rowId);
    }

    public /* bridge */ /* synthetic */ void updateSQLXML(int i, SQLXML sqlxml) throws SQLException {
        super.updateSQLXML(i, sqlxml);
    }

    public /* bridge */ /* synthetic */ void updateSQLXML(String str, SQLXML sqlxml) throws SQLException {
        super.updateSQLXML(str, sqlxml);
    }

    public /* bridge */ /* synthetic */ void updateShort(int i, short s) throws SQLException {
        super.updateShort(i, s);
    }

    public /* bridge */ /* synthetic */ void updateShort(String str, short s) throws SQLException {
        super.updateShort(str, s);
    }

    public /* bridge */ /* synthetic */ void updateString(int i, String str) throws SQLException {
        super.updateString(i, str);
    }

    public /* bridge */ /* synthetic */ void updateString(String str, String str2) throws SQLException {
        super.updateString(str, str2);
    }

    public /* bridge */ /* synthetic */ void updateTime(int i, Time time) throws SQLException {
        super.updateTime(i, time);
    }

    public /* bridge */ /* synthetic */ void updateTime(String str, Time time) throws SQLException {
        super.updateTime(str, time);
    }

    public /* bridge */ /* synthetic */ void updateTimestamp(int i, Timestamp timestamp) throws SQLException {
        super.updateTimestamp(i, timestamp);
    }

    public /* bridge */ /* synthetic */ void updateTimestamp(String str, Timestamp timestamp) throws SQLException {
        super.updateTimestamp(str, timestamp);
    }

    public SingleResultSet(Statement statement2, long j) throws SQLException {
        if (j != -1) {
            this.statement = statement2;
            this.value = j;
            return;
        }
        throw new SQLException("invalid row id");
    }

    public int getInt(int i) throws SQLException {
        return (int) this.value;
    }

    public int getInt(String str) throws SQLException {
        return (int) this.value;
    }

    public long getLong(int i) throws SQLException {
        return this.value;
    }

    public long getLong(String str) throws SQLException {
        return this.value;
    }

    public Object getObject(int i) throws SQLException {
        return Long.valueOf(this.value);
    }

    public Object getObject(int i, Map<String, Class<?>> map) throws SQLException {
        return Long.valueOf(this.value);
    }

    public Object getObject(String str) throws SQLException {
        return Long.valueOf(this.value);
    }

    public Object getObject(String str, Map<String, Class<?>> map) throws SQLException {
        return Long.valueOf(this.value);
    }

    public short getShort(int i) throws SQLException {
        return (short) ((int) this.value);
    }

    public short getShort(String str) throws SQLException {
        return (short) ((int) this.value);
    }

    public Statement getStatement() throws SQLException {
        return this.statement;
    }

    public String getString(int i) throws SQLException {
        return String.valueOf(this.value);
    }

    public String getString(String str) throws SQLException {
        return String.valueOf(this.value);
    }

    public void moveToCurrentRow() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void moveToInsertRow() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
}
