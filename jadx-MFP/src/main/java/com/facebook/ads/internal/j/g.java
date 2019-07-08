package com.facebook.ads.internal.j;

import android.database.sqlite.SQLiteDatabase;

public abstract class g {
    protected final d k;

    protected g(d dVar) {
        this.k = dVar;
    }

    public static String a(String str, b[] bVarArr) {
        StringBuilder sb = new StringBuilder("SELECT ");
        for (int i = 0; i < bVarArr.length - 1; i++) {
            sb.append(bVarArr[i].b);
            sb.append(", ");
        }
        sb.append(bVarArr[bVarArr.length - 1].b);
        sb.append(" FROM ");
        sb.append(str);
        return sb.toString();
    }

    private String c() {
        b[] b = b();
        if (b.length < 1) {
            return null;
        }
        String str = "";
        for (int i = 0; i < b.length - 1; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(b[i].a());
            sb.append(", ");
            str = sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(b[b.length - 1].a());
        return sb2.toString();
    }

    public abstract String a();

    public void a(SQLiteDatabase sQLiteDatabase) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(a());
        sb.append(" (");
        sb.append(c());
        sb.append(")");
        sQLiteDatabase.execSQL(sb.toString());
    }

    public void b(SQLiteDatabase sQLiteDatabase) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE IF EXISTS ");
        sb.append(a());
        sQLiteDatabase.execSQL(sb.toString());
    }

    public abstract b[] b();

    public void e() {
    }

    /* access modifiers changed from: protected */
    public SQLiteDatabase f() {
        return this.k.a();
    }

    /* access modifiers changed from: protected */
    public boolean g() {
        return f().delete(a(), null, null) > 0;
    }
}
