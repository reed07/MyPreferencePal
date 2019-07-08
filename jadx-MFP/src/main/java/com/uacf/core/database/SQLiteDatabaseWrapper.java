package com.uacf.core.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class SQLiteDatabaseWrapper {
    private SQLiteDatabase db;

    public SQLiteDatabaseWrapper(SQLiteDatabase sQLiteDatabase) {
        this.db = sQLiteDatabase;
    }

    public SQLiteDatabase getDb() {
        return this.db;
    }

    public void beginTransaction() {
        this.db.beginTransaction();
    }

    public void endTransaction() {
        this.db.endTransaction();
    }

    public void setTransactionSuccessful() {
        this.db.setTransactionSuccessful();
    }

    public boolean inTransaction() {
        return this.db.inTransaction();
    }

    public int getVersion() {
        return this.db.getVersion();
    }

    public SQLiteStatement compileStatement(String str) throws SQLException {
        return this.db.compileStatement(str);
    }

    public Cursor query(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        return this.db.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6);
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5) {
        return this.db.query(str, strArr, str2, strArr2, str3, str4, str5);
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        return this.db.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
    }

    public Cursor rawQuery(String str, String[] strArr) {
        return this.db.rawQuery(str, strArr);
    }

    public long insert(String str, String str2, ContentValues contentValues) {
        return this.db.insert(str, str2, contentValues);
    }

    public long insertOrThrow(String str, String str2, ContentValues contentValues) throws SQLException {
        return this.db.insertOrThrow(str, str2, contentValues);
    }

    public long insertWithOnConflict(String str, String str2, ContentValues contentValues, int i) {
        return this.db.insertWithOnConflict(str, str2, contentValues, i);
    }

    public int delete(String str, String str2, String[] strArr) {
        return this.db.delete(str, str2, strArr);
    }

    public int update(String str, ContentValues contentValues, String str2, String[] strArr) {
        return this.db.update(str, contentValues, str2, strArr);
    }

    public void execSQL(String str) throws SQLException {
        this.db.execSQL(str);
    }

    public void execSQL(String str, Object[] objArr) throws SQLException {
        this.db.execSQL(str, objArr);
    }

    public boolean isOpen() {
        return this.db.isOpen();
    }

    public String toString() {
        return this.db.toString();
    }

    public void close() {
        this.db.close();
    }

    public boolean equals(Object obj) {
        return this.db.equals(obj);
    }

    public int hashCode() {
        return this.db.hashCode();
    }
}
