package com.uacf.core.database;

import android.content.ContentValues;
import android.database.Cursor;

public interface DatabaseTable {
    int deleteData(String str, Object... objArr);

    SQLiteDatabaseWrapper getDatabase();

    String getTableName();

    void onCreate();

    void onUpgrade(int i, int i2);

    Cursor queryData(String[] strArr, String str, Object... objArr);

    int updateData(ContentValues contentValues, String str, Object... objArr);
}
