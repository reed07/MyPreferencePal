package com.facebook.ads.internal.j;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.facebook.ads.internal.r.a;
import java.util.Locale;

public class e extends SQLiteOpenHelper {
    public static final String a = "e";
    private final d b;

    public e(Context context, d dVar) {
        String format = String.format(Locale.US, "ads%s.db", new Object[]{""});
        if (a.Q(context)) {
            String packageName = context.getPackageName();
            String a2 = com.facebook.ads.internal.w.f.a.a(context);
            if (!packageName.equals(a2) && !TextUtils.isEmpty(a2)) {
                StringBuilder sb = new StringBuilder();
                sb.append('_');
                sb.append(a2);
                format = String.format(Locale.US, "ads%s.db", new Object[]{sb.toString()});
            }
        }
        super(context, format, null, 4);
        if (dVar != null) {
            this.b = dVar;
            return;
        }
        throw new IllegalArgumentException("AdDatabaseHelper can not be null");
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        for (g a2 : this.b.c()) {
            a2.a(sQLiteDatabase);
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        g[] c;
        for (g gVar : this.b.c()) {
            gVar.b(sQLiteDatabase);
            gVar.a(sQLiteDatabase);
        }
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        if (!sQLiteDatabase.isReadOnly()) {
            sQLiteDatabase.execSQL("PRAGMA foreign_keys = ON;");
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 2 && i2 >= 3) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS crashes");
        }
        if (i <= 3 && i2 >= 4) {
            b bVar = c.i;
            StringBuilder sb = new StringBuilder();
            sb.append("ALTER TABLE events ADD COLUMN ");
            sb.append(bVar.b);
            sb.append(" ");
            sb.append(bVar.c);
            sb.append(" DEFAULT 0");
            sQLiteDatabase.execSQL(sb.toString());
        }
    }
}
