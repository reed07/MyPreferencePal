package com.uacf.core.database;

import android.database.sqlite.SQLiteDatabase;

public class SQLiteDatabaseWrapperFactory {
    private static volatile boolean loggingEnabled;

    public static final void setLoggingEnabled(boolean z) {
        loggingEnabled = z;
    }

    public static SQLiteDatabaseWrapper wrap(SQLiteDatabase sQLiteDatabase) {
        if (loggingEnabled) {
            return new LoggingSQLiteDatabaseWrapper(sQLiteDatabase);
        }
        return new SQLiteDatabaseWrapper(sQLiteDatabase);
    }
}
