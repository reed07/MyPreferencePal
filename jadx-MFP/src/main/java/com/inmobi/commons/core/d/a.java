package com.inmobi.commons.core.d;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: DbHelper */
public final class a extends SQLiteOpenHelper {
    public static final String a = "com.im_7.2.6.db";

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public a(Context context) {
        super(context, a, null, 1);
    }
}
