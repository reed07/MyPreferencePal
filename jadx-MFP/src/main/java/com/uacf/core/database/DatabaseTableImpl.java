package com.uacf.core.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.ArrayUtil;
import com.uacf.core.util.Strings;
import java.util.Arrays;

public abstract class DatabaseTableImpl implements DatabaseTable {
    private static final String ADD_COLUMN_FORMAT = "ALTER TABLE %s ADD %s %s";
    private static final String CREATE_INDEX_FORMAT = "CREATE %s INDEX %s on %s (%s)";
    public static final String CREATE_TABLE_DELIMITER = ",\n";
    private static final String CREATE_TABLE_FORMAT = "CREATE TABLE %s (%s)";
    public static final String DATETIME_FORMAT = Format.newDatabaseDateTimeFormat().toPattern();
    public static final String DATE_FORMAT = Format.newIso8601DateFormat().toPattern();
    private static final String DELETE_ALL_DATA_FORMAT = "DELETE FROM %s";
    private static final String DELETE_DATA_FORMAT = "DELETE FROM %s WHERE %s";
    private static final String DISTINCT = "DISTINCT";
    private static final String DROP_INDEX_IF_EXISTS_FORMAT = "DROP INDEX IF EXISTS %s";
    private static final String DROP_TABLE_FORMAT = "DROP TABLE %s";
    private static final String DROP_TABLE_IF_EXISTS_FORMAT = "DROP TABLE IF EXISTS %s";
    private static final String INSERT_DATA_FORMAT = "INSERT INTO %s (%s) values (%s)";
    private static final String INSERT_DATA_FROM_OTHER_TABLE_FORMAT = "INSERT INTO %s (%s) SELECT %s FROM %s";
    private static final String RENAME_TABLE_FORMAT = "ALTER TABLE %s RENAME TO %s";
    private static final String SELECT_DATA_FORMAT = "SELECT %s %s FROM %s WHERE %s";
    private static final String UPDATE_DATA_FORMAT = "UPDATE %s SET %s = (%s) WHERE %s";
    protected final SQLiteDatabaseWrapper database;
    private final String tableName;

    /* access modifiers changed from: protected */
    public boolean shouldRunUpgrade(int i, int i2, int i3) {
        return i2 < i && i3 >= i;
    }

    public String getTableName() {
        return this.tableName;
    }

    public SQLiteDatabaseWrapper getDatabase() {
        return this.database;
    }

    protected DatabaseTableImpl(SQLiteDatabaseWrapper sQLiteDatabaseWrapper, String str) {
        this.database = sQLiteDatabaseWrapper;
        this.tableName = str;
    }

    public void execSQL(String str, Object... objArr) {
        this.database.execSQL(str, objArr);
    }

    /* access modifiers changed from: protected */
    public void createTableWithName(String str, String... strArr) {
        execSQL(String.format(CREATE_TABLE_FORMAT, new Object[]{str, Strings.join(CREATE_TABLE_DELIMITER, (T[]) strArr)}), new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void createTable(String... strArr) {
        execSQL(String.format(CREATE_TABLE_FORMAT, new Object[]{this.tableName, Strings.join(CREATE_TABLE_DELIMITER, (T[]) strArr)}), new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void addColumn(String str, String str2) {
        execSQL(String.format(ADD_COLUMN_FORMAT, new Object[]{this.tableName, str, str2}), new Object[0]);
    }

    public void deleteData() {
        execSQL(String.format(DELETE_ALL_DATA_FORMAT, new Object[]{this.tableName}), new Object[0]);
    }

    public void deleteData(String str) {
        execSQL(String.format(DELETE_DATA_FORMAT, new Object[]{this.tableName, str}), new Object[0]);
    }

    public int deleteData(String str, Object... objArr) {
        return this.database.delete(this.tableName, str, Strings.toStringArray(objArr));
    }

    /* access modifiers changed from: protected */
    public void createUniqueIndex(String str, String... strArr) {
        createIndex(true, str, strArr);
    }

    /* access modifiers changed from: protected */
    public void createIndex(String str, String... strArr) {
        createIndex(false, str, strArr);
    }

    /* access modifiers changed from: protected */
    public void updateData(String str, String str2, String str3) {
        execSQL(String.format(UPDATE_DATA_FORMAT, new Object[]{this.tableName, str, str2, str3}), new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void renameTable(String str) {
        execSQL(String.format(RENAME_TABLE_FORMAT, new Object[]{this.tableName, str}), new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void renameTable(String str, String str2) {
        execSQL(String.format(RENAME_TABLE_FORMAT, new Object[]{str, str2}), new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void dropTable() {
        dropTable(this.tableName);
    }

    /* access modifiers changed from: protected */
    public void dropTable(String str) {
        execSQL(String.format(DROP_TABLE_FORMAT, new Object[]{str}), new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void dropTableIfExists() {
        dropTable(this.tableName);
    }

    /* access modifiers changed from: protected */
    public void dropTableIfExists(String str) {
        execSQL(String.format(DROP_TABLE_IF_EXISTS_FORMAT, new Object[]{str}), new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void dropIndexIfExists(String str) {
        execSQL(String.format(DROP_INDEX_IF_EXISTS_FORMAT, new Object[]{str}), new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void insertDataFromOtherTable(String str, String str2, String str3) {
        execSQL(String.format(INSERT_DATA_FROM_OTHER_TABLE_FORMAT, new Object[]{this.tableName, str, str2, str3}), new Object[0]);
    }

    public void insertData(Object[] objArr, String... strArr) {
        if (objArr.length >= strArr.length) {
            execSQL(generateInsertDataSql(strArr), objArr);
            return;
        }
        throw new IllegalArgumentException("Not enough data to fill the columns");
    }

    /* access modifiers changed from: protected */
    public String generateInsertDataSql(String... strArr) {
        return String.format(INSERT_DATA_FORMAT, new Object[]{this.tableName, Strings.join(",", (T[]) strArr), getQuestionMarkString(strArr.length)});
    }

    /* access modifiers changed from: protected */
    public String generateSelectSQL(String str, boolean z, String... strArr) {
        String str2 = SELECT_DATA_FORMAT;
        Object[] objArr = new Object[4];
        objArr[0] = this.tableName;
        objArr[1] = z ? DISTINCT : "";
        objArr[2] = Strings.join(",", (T[]) strArr);
        objArr[3] = str;
        return String.format(str2, objArr);
    }

    public long insertData(ContentValues contentValues) {
        return insertData(contentValues, (String) null);
    }

    public long insertData(ContentValues contentValues, String str) {
        return this.database.insertOrThrow(this.tableName, str, contentValues);
    }

    public int updateData(ContentValues contentValues, String str, Object... objArr) {
        return this.database.update(this.tableName, contentValues, str, objArr == null ? null : Strings.toStringArray(objArr));
    }

    public long insertOrUpdateData(ContentValues contentValues, String str, Object... objArr) {
        if (any(str, objArr)) {
            return (long) updateData(contentValues, str, objArr);
        }
        return insertData(contentValues);
    }

    public Cursor queryData(String[] strArr) {
        return queryData(false, strArr, "", new Object[0]);
    }

    public Cursor queryData(String[] strArr, String str, Object... objArr) {
        return queryData(false, strArr, str, objArr);
    }

    public Cursor queryData(boolean z, String[] strArr, String str, Object... objArr) {
        return this.database.query(z, this.tableName, strArr, str, Strings.toStringArray(objArr), null, null, null, null);
    }

    public Cursor queryDataOrderBy(boolean z, String[] strArr, String str, String str2, Object... objArr) {
        return this.database.query(z, this.tableName, strArr, str, Strings.toStringArray(objArr), null, null, str2, null);
    }

    public Cursor rawQuery(String str, Object... objArr) {
        int size = ArrayUtil.size(objArr);
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = Strings.toString(objArr[i]);
        }
        return rawQuery(str, strArr);
    }

    public Cursor rawQuery(String str, String[] strArr) {
        return this.database.rawQuery(str, strArr);
    }

    public boolean any(String[] strArr, String str, Object... objArr) {
        Cursor queryData = queryData(strArr, str, objArr);
        if (queryData == null) {
            return false;
        }
        try {
            return queryData.moveToFirst();
        } finally {
            queryData.close();
        }
    }

    public boolean any(String str, Object... objArr) {
        return count(str, objArr) > 0;
    }

    /* access modifiers changed from: protected */
    public int count(String str, Object... objArr) {
        return count(false, str, objArr);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public int count(boolean z, String str, Object... objArr) {
        Cursor cursor = null;
        try {
            Cursor queryData = queryData(z, new String[]{"COUNT(*)"}, str, objArr);
            int i = 0;
            if (queryData.moveToFirst()) {
                i = queryData.getInt(0);
            }
            if (queryData != null) {
                queryData.close();
            }
            return i;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public SQLiteStatement compileStatement(String str) {
        return this.database.compileStatement(str);
    }

    private void createIndex(boolean z, String str, String... strArr) {
        String str2 = CREATE_INDEX_FORMAT;
        Object[] objArr = new Object[4];
        objArr[0] = z ? "UNIQUE" : "";
        objArr[1] = str;
        objArr[2] = this.tableName;
        objArr[3] = Strings.join(",", (T[]) strArr);
        execSQL(String.format(str2, objArr), new Object[0]);
    }

    private String getQuestionMarkString(int i) {
        String[] strArr = new String[i];
        Arrays.fill(strArr, "?");
        return Strings.join(",", (T[]) strArr);
    }
}
