package com.uacf.core.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class SQLiteDatabaseWrapperOpenHelper extends SQLiteOpenHelper {
    private SQLiteDatabaseWrapper readable;
    private SQLiteDatabaseWrapper writable;

    public SQLiteDatabaseWrapperOpenHelper(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }

    public SQLiteDatabaseWrapperOpenHelper(Context context, String str, CursorFactory cursorFactory, int i, DatabaseErrorHandler databaseErrorHandler) {
        super(context, str, cursorFactory, i, databaseErrorHandler);
    }

    public SQLiteDatabaseWrapper getWritableDatabaseWrapper() {
        if (this.writable == null) {
            this.writable = SQLiteDatabaseWrapperFactory.wrap(super.getWritableDatabase());
        }
        return this.writable;
    }

    public SQLiteDatabaseWrapper getReadableDatabaseWrapper() {
        if (this.readable == null) {
            this.readable = SQLiteDatabaseWrapperFactory.wrap(super.getReadableDatabase());
        }
        return this.readable;
    }
}
