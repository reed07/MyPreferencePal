package com.uacf.core.database;

import android.database.Cursor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.HashMap;
import java.util.Map;

public class CursorMapper extends ColumnMapper<CursorMapper> {
    private Class<?> clazz;
    private final Map<String, Integer> columnIndexMap = new HashMap();
    private final Cursor cursor;

    public CursorMapper(Cursor cursor2) {
        this.cursor = cursor2;
    }

    public void close() {
        Cursor cursor2 = this.cursor;
        if (cursor2 != null && !cursor2.isClosed()) {
            this.cursor.close();
        }
    }

    public CursorMapper forClass(Class<?> cls) {
        this.clazz = cls;
        return this;
    }

    public long getLong(String str) {
        int columnIndex = getColumnIndex(str);
        if (columnIndex >= 0) {
            return this.cursor.getLong(columnIndex);
        }
        return 0;
    }

    public int getInt(String str) {
        int columnIndex = getColumnIndex(str);
        if (columnIndex >= 0) {
            return this.cursor.getInt(columnIndex);
        }
        return 0;
    }

    public String getString(String str) {
        int columnIndex = getColumnIndex(str);
        if (columnIndex >= 0) {
            return this.cursor.getString(columnIndex);
        }
        return null;
    }

    public Boolean getBoolean(String str) {
        return Boolean.valueOf(getInt(str) != 0);
    }

    public float getFloat(String str) {
        int columnIndex = getColumnIndex(str);
        return columnIndex >= 0 ? this.cursor.getFloat(columnIndex) : BitmapDescriptorFactory.HUE_RED;
    }

    public double getDouble(String str) {
        int columnIndex = getColumnIndex(str);
        if (columnIndex >= 0) {
            return this.cursor.getDouble(columnIndex);
        }
        return 0.0d;
    }

    public int getCount() {
        return this.cursor.getCount();
    }

    public boolean moveToNext() {
        return this.cursor.moveToNext();
    }

    private int getColumnIndex(String str) {
        String actualColumnName = getActualColumnName(this.clazz, str);
        Integer num = (Integer) this.columnIndexMap.get(actualColumnName);
        if (num != null) {
            return num.intValue();
        }
        Integer valueOf = Integer.valueOf(this.cursor.getColumnIndex(actualColumnName));
        this.columnIndexMap.put(actualColumnName, valueOf);
        return valueOf.intValue();
    }
}
