package com.facebook.stetho.inspector.database;

import android.annotation.TargetApi;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build.VERSION;

public abstract class SQLiteDatabaseCompat {
    public static final int ENABLE_FOREIGN_KEY_CONSTRAINTS = 2;
    public static final int ENABLE_WRITE_AHEAD_LOGGING = 1;
    private static final SQLiteDatabaseCompat sInstance;

    @TargetApi(11)
    private static class HoneycombImpl extends SQLiteDatabaseCompat {
        public int provideOpenFlags(@SQLiteOpenOptions int i) {
            return 0;
        }

        private HoneycombImpl() {
        }

        public void enableFeatures(@SQLiteOpenOptions int i, SQLiteDatabase sQLiteDatabase) {
            if ((i & 1) != 0) {
                sQLiteDatabase.enableWriteAheadLogging();
            }
            if ((i & 2) != 0) {
                sQLiteDatabase.execSQL("PRAGMA foreign_keys = ON");
            }
        }
    }

    @TargetApi(16)
    private static class JellyBeanAndBeyondImpl extends SQLiteDatabaseCompat {
        public int provideOpenFlags(@SQLiteOpenOptions int i) {
            return (i & 1) != 0 ? 536870912 : 0;
        }

        private JellyBeanAndBeyondImpl() {
        }

        public void enableFeatures(@SQLiteOpenOptions int i, SQLiteDatabase sQLiteDatabase) {
            if ((i & 2) != 0) {
                sQLiteDatabase.setForeignKeyConstraintsEnabled(true);
            }
        }
    }

    private static class NoopImpl extends SQLiteDatabaseCompat {
        public void enableFeatures(@SQLiteOpenOptions int i, SQLiteDatabase sQLiteDatabase) {
        }

        public int provideOpenFlags(@SQLiteOpenOptions int i) {
            return 0;
        }

        private NoopImpl() {
        }
    }

    public @interface SQLiteOpenOptions {
    }

    public abstract void enableFeatures(@SQLiteOpenOptions int i, SQLiteDatabase sQLiteDatabase);

    public abstract int provideOpenFlags(@SQLiteOpenOptions int i);

    static {
        if (VERSION.SDK_INT >= 16) {
            sInstance = new JellyBeanAndBeyondImpl();
        } else if (VERSION.SDK_INT >= 11) {
            sInstance = new HoneycombImpl();
        } else {
            sInstance = new NoopImpl();
        }
    }

    public static SQLiteDatabaseCompat getInstance() {
        return sInstance;
    }
}
