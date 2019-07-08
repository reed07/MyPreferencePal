package io.requery.android.sqlite;

import android.database.Cursor;
import com.brightcove.player.network.DownloadStatus;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Map;

public class CursorResultSet extends NonUpdateableResultSet implements ResultSetMetaData {
    private final boolean closeCursor;
    private final Cursor cursor;
    private int lastColumnIndex;
    private final Statement statement;

    public void clearWarnings() throws SQLException {
    }

    public String getCatalogName(int i) throws SQLException {
        return null;
    }

    public String getColumnClassName(int i) throws SQLException {
        return null;
    }

    public int getColumnDisplaySize(int i) throws SQLException {
        return 0;
    }

    public String getColumnLabel(int i) throws SQLException {
        return "";
    }

    public String getColumnTypeName(int i) throws SQLException {
        return null;
    }

    public int getConcurrency() throws SQLException {
        return DownloadStatus.ERROR_DEVICE_NOT_FOUND;
    }

    public String getCursorName() throws SQLException {
        return null;
    }

    public int getFetchDirection() throws SQLException {
        return 1000;
    }

    public int getFetchSize() throws SQLException {
        return 0;
    }

    public int getHoldability() throws SQLException {
        return 1;
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return this;
    }

    public int getPrecision(int i) throws SQLException {
        return 0;
    }

    public int getScale(int i) throws SQLException {
        return 0;
    }

    public String getSchemaName(int i) throws SQLException {
        return null;
    }

    public String getTableName(int i) throws SQLException {
        return null;
    }

    public int getType() throws SQLException {
        return DownloadStatus.ERROR_TOO_MANY_REDIRECTS;
    }

    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    public boolean isAutoIncrement(int i) throws SQLException {
        return false;
    }

    public boolean isCaseSensitive(int i) throws SQLException {
        return false;
    }

    public boolean isCurrency(int i) throws SQLException {
        return false;
    }

    public boolean isDefinitelyWritable(int i) throws SQLException {
        return false;
    }

    public int isNullable(int i) throws SQLException {
        return 2;
    }

    public boolean isReadOnly(int i) throws SQLException {
        return false;
    }

    public boolean isSearchable(int i) throws SQLException {
        return false;
    }

    public boolean isSigned(int i) throws SQLException {
        return false;
    }

    public boolean isWritable(int i) throws SQLException {
        return false;
    }

    public void moveToCurrentRow() throws SQLException {
    }

    public void moveToInsertRow() throws SQLException {
    }

    public void refreshRow() throws SQLException {
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

    public void setFetchSize(int i) throws SQLException {
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

    public CursorResultSet(Statement statement2, Cursor cursor2, boolean z) {
        if (cursor2 != null) {
            this.statement = statement2;
            this.cursor = cursor2;
            this.closeCursor = z;
            cursor2.moveToPosition(-1);
            return;
        }
        throw new IllegalArgumentException("null cursor");
    }

    public boolean absolute(int i) throws SQLException {
        return this.cursor.moveToPosition(i - 1);
    }

    public void afterLast() throws SQLException {
        this.cursor.moveToLast();
        this.cursor.moveToNext();
    }

    public void beforeFirst() throws SQLException {
        this.cursor.moveToPosition(-1);
    }

    public void cancelRowUpdates() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void close() throws SQLException {
        if (this.closeCursor) {
            this.cursor.close();
        }
    }

    public void deleteRow() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public int findColumn(String str) throws SQLException {
        int columnIndex = this.cursor.getColumnIndex(str);
        if (columnIndex != -1) {
            return columnIndex + 1;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("no column ");
        sb.append(str);
        throw new SQLDataException(sb.toString());
    }

    public boolean first() throws SQLException {
        return this.cursor.moveToFirst();
    }

    public Array getArray(int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public Array getArray(String str) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public InputStream getAsciiStream(int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public InputStream getAsciiStream(String str) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public BigDecimal getBigDecimal(int i) throws SQLException {
        String string = getString(i);
        if (string == null) {
            return null;
        }
        return new BigDecimal(string);
    }

    public BigDecimal getBigDecimal(int i, int i2) throws SQLException {
        BigDecimal bigDecimal;
        String string = getString(i);
        if (string == null) {
            bigDecimal = null;
        } else {
            bigDecimal = new BigDecimal(string);
        }
        return bigDecimal != null ? bigDecimal.setScale(i2, 1) : bigDecimal;
    }

    public BigDecimal getBigDecimal(String str) throws SQLException {
        return getBigDecimal(findColumn(str));
    }

    public BigDecimal getBigDecimal(String str, int i) throws SQLException {
        return getBigDecimal(findColumn(str));
    }

    public InputStream getBinaryStream(int i) throws SQLException {
        return new ByteArrayInputStream(getBytes(i));
    }

    public InputStream getBinaryStream(String str) throws SQLException {
        return getBinaryStream(findColumn(str));
    }

    public Blob getBlob(int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public Blob getBlob(String str) throws SQLException {
        return getBlob(findColumn(str));
    }

    public boolean getBoolean(int i) throws SQLException {
        return getInt(i) > 0;
    }

    public boolean getBoolean(String str) throws SQLException {
        return getBoolean(findColumn(str));
    }

    public byte getByte(int i) throws SQLException {
        return (byte) getShort(i);
    }

    public byte getByte(String str) throws SQLException {
        return getByte(findColumn(str));
    }

    public byte[] getBytes(int i) throws SQLException {
        this.lastColumnIndex = i;
        return this.cursor.getBlob(i - 1);
    }

    public byte[] getBytes(String str) throws SQLException {
        return getBytes(findColumn(str));
    }

    public Reader getCharacterStream(int i) throws SQLException {
        return new StringReader(getString(i));
    }

    public Reader getCharacterStream(String str) throws SQLException {
        return new StringReader(getString(str));
    }

    public Clob getClob(int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public Clob getClob(String str) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public Date getDate(int i) throws SQLException {
        this.lastColumnIndex = i;
        int i2 = i - 1;
        if (this.cursor.isNull(i2)) {
            return null;
        }
        if (this.cursor.getType(i2) == 1) {
            return new Date(this.cursor.getLong(i2));
        }
        try {
            return new Date(((DateFormat) BasePreparedStatement.ISO8601_FORMAT.get()).parse(this.cursor.getString(i2)).getTime());
        } catch (ParseException e) {
            throw new SQLException(e);
        }
    }

    public Date getDate(int i, Calendar calendar) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public Date getDate(String str) throws SQLException {
        return getDate(findColumn(str));
    }

    public Date getDate(String str, Calendar calendar) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public double getDouble(int i) throws SQLException {
        this.lastColumnIndex = i;
        return this.cursor.getDouble(i - 1);
    }

    public double getDouble(String str) throws SQLException {
        return getDouble(findColumn(str));
    }

    public float getFloat(int i) throws SQLException {
        this.lastColumnIndex = i;
        return this.cursor.getFloat(i - 1);
    }

    public float getFloat(String str) throws SQLException {
        return getFloat(findColumn(str));
    }

    public int getInt(int i) throws SQLException {
        this.lastColumnIndex = i;
        return this.cursor.getInt(i - 1);
    }

    public int getInt(String str) throws SQLException {
        return getInt(findColumn(str));
    }

    public long getLong(int i) throws SQLException {
        this.lastColumnIndex = i;
        return this.cursor.getLong(i - 1);
    }

    public long getLong(String str) throws SQLException {
        return getLong(findColumn(str));
    }

    public Object getObject(int i) throws SQLException {
        this.lastColumnIndex = i;
        int i2 = i - 1;
        int type = this.cursor.getType(i2);
        if (!this.cursor.isNull(i2)) {
            switch (type) {
                case 0:
                    return null;
                case 1:
                    return Integer.valueOf(this.cursor.getInt(i2));
                case 2:
                    return Float.valueOf(this.cursor.getFloat(i2));
                case 3:
                    return this.cursor.getString(i2);
                case 4:
                    return this.cursor.getBlob(i2);
            }
        }
        return null;
    }

    public Object getObject(int i, Map<String, Class<?>> map) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public Object getObject(String str) throws SQLException {
        return getObject(findColumn(str));
    }

    public Object getObject(String str, Map<String, Class<?>> map) throws SQLException {
        return getObject(findColumn(str), map);
    }

    public Ref getRef(int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public Ref getRef(String str) throws SQLException {
        return getRef(findColumn(str));
    }

    public int getRow() throws SQLException {
        return this.cursor.getPosition() + 1;
    }

    public short getShort(int i) throws SQLException {
        this.lastColumnIndex = i;
        return this.cursor.getShort(i - 1);
    }

    public short getShort(String str) throws SQLException {
        return getShort(findColumn(str));
    }

    public Statement getStatement() throws SQLException {
        return this.statement;
    }

    public String getString(int i) throws SQLException {
        this.lastColumnIndex = i;
        int i2 = i - 1;
        if (this.cursor.isNull(i2)) {
            return null;
        }
        return this.cursor.getString(i2);
    }

    public String getString(String str) throws SQLException {
        return getString(findColumn(str));
    }

    public Time getTime(int i) throws SQLException {
        this.lastColumnIndex = i;
        int i2 = i - 1;
        if (this.cursor.isNull(i2)) {
            return null;
        }
        return new Time(getLong(i2));
    }

    public Time getTime(int i, Calendar calendar) throws SQLException {
        this.lastColumnIndex = i;
        int i2 = i - 1;
        if (this.cursor.isNull(i2)) {
            return null;
        }
        return new Time(getLong(i2));
    }

    public Time getTime(String str) throws SQLException {
        return getTime(findColumn(str));
    }

    public Time getTime(String str, Calendar calendar) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public Timestamp getTimestamp(int i) throws SQLException {
        this.lastColumnIndex = i;
        int i2 = i - 1;
        if (this.cursor.isNull(i2)) {
            return null;
        }
        return new Timestamp(this.cursor.getLong(i2));
    }

    public Timestamp getTimestamp(int i, Calendar calendar) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public Timestamp getTimestamp(String str) throws SQLException {
        return getTimestamp(findColumn(str));
    }

    public Timestamp getTimestamp(String str, Calendar calendar) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public InputStream getUnicodeStream(int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public InputStream getUnicodeStream(String str) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public URL getURL(int i) throws SQLException {
        String string = getString(i);
        if (string == null) {
            return null;
        }
        try {
            return new URL(string);
        } catch (MalformedURLException e) {
            throw new SQLException(e);
        }
    }

    public URL getURL(String str) throws SQLException {
        return getURL(findColumn(str));
    }

    public void insertRow() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public boolean isAfterLast() throws SQLException {
        return this.cursor.isAfterLast();
    }

    public boolean isBeforeFirst() throws SQLException {
        return this.cursor.getCount() != 0 && this.cursor.isBeforeFirst();
    }

    public boolean isFirst() throws SQLException {
        return this.cursor.isFirst();
    }

    public boolean isLast() throws SQLException {
        return this.cursor.isLast() || this.cursor.getCount() == 0;
    }

    public boolean last() throws SQLException {
        return this.cursor.moveToLast();
    }

    public boolean next() throws SQLException {
        return this.cursor.moveToNext();
    }

    public boolean previous() throws SQLException {
        return this.cursor.moveToPrevious();
    }

    public boolean relative(int i) throws SQLException {
        return this.cursor.move(i);
    }

    public void setFetchDirection(int i) throws SQLException {
        if (i != 1000) {
            throw new SQLException("Only FETCH_FORWARD is supported");
        }
    }

    public boolean wasNull() throws SQLException {
        return this.cursor.isNull(this.lastColumnIndex - 1);
    }

    public RowId getRowId(int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public RowId getRowId(String str) throws SQLException {
        return getRowId(findColumn(str));
    }

    public boolean isClosed() throws SQLException {
        return this.cursor.isClosed();
    }

    public NClob getNClob(int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public NClob getNClob(String str) throws SQLException {
        return getNClob(findColumn(str));
    }

    public SQLXML getSQLXML(int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public SQLXML getSQLXML(String str) throws SQLException {
        return getSQLXML(findColumn(str));
    }

    public String getNString(int i) throws SQLException {
        return getString(i);
    }

    public String getNString(String str) throws SQLException {
        return getNString(findColumn(str));
    }

    public Reader getNCharacterStream(int i) throws SQLException {
        return getCharacterStream(i);
    }

    public Reader getNCharacterStream(String str) throws SQLException {
        return getNCharacterStream(findColumn(str));
    }

    public <T> T unwrap(Class<T> cls) throws SQLException {
        if (cls == Cursor.class) {
            return cls.cast(this.cursor);
        }
        throw new SQLFeatureNotSupportedException();
    }

    public boolean isWrapperFor(Class<?> cls) throws SQLException {
        return cls == Cursor.class;
    }

    public int getColumnCount() throws SQLException {
        return this.cursor.getColumnCount();
    }

    public String getColumnName(int i) throws SQLException {
        return this.cursor.getColumnName(i - 1);
    }

    public int getColumnType(int i) throws SQLException {
        switch (this.cursor.getType(i - 1)) {
            case 0:
                return 0;
            case 1:
                return 4;
            case 2:
                return 6;
            case 3:
                return 12;
            case 4:
                return -3;
            default:
                return 0;
        }
    }
}
