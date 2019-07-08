package com.inmobi.commons.core.d;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.inmobi.commons.a.a;

/* compiled from: DbStore */
public final class b {
    private static final String a = "b";
    private static volatile b b;
    private static final Object c = new Object();
    private static final Object d = new Object();
    private static int e = 0;
    private SQLiteDatabase f;

    private b() {
        try {
            this.f = new a(a.b()).getWritableDatabase();
            b = this;
        } catch (Exception unused) {
        }
    }

    public static synchronized b a() {
        b bVar;
        b bVar2;
        synchronized (b.class) {
            synchronized (d) {
                e++;
            }
            bVar = b;
            if (bVar == null) {
                synchronized (c) {
                    bVar2 = b;
                    if (bVar2 == null) {
                        bVar2 = new b();
                        b = bVar2;
                    }
                }
                bVar = bVar2;
            }
        }
        return bVar;
    }

    public final synchronized void a(String str, ContentValues contentValues, String str2, String[] strArr) {
        try {
            if (!a(str, contentValues)) {
                b(str, contentValues, str2, strArr);
            }
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in DbStore.insertOrUpdate() method; ").append(e2.getMessage());
        }
    }

    public final synchronized boolean a(String str, ContentValues contentValues) {
        try {
            return this.f.insertWithOnConflict(str, null, contentValues, 4) != -1;
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in DbStore.insert() method; ").append(e2.getMessage());
            return false;
        }
    }

    public final synchronized int a(String str, String str2, String[] strArr) {
        try {
        } catch (Exception e2) {
            new StringBuilder("SDK encountered an unexpected error in DbStore.delete() method; ").append(e2.getMessage());
            return -1;
        }
        return this.f.delete(str, str2, strArr);
    }

    public final synchronized int b(String str, ContentValues contentValues, String str2, String[] strArr) {
        try {
        } catch (Exception e2) {
            new StringBuilder("SDK encountered an unexpected error in DbStore.delete() method; ").append(e2.getMessage());
            return -1;
        }
        return this.f.updateWithOnConflict(str, contentValues, str2, strArr, 4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0036, code lost:
        if (r3 != null) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r3.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.util.List<android.content.ContentValues> a(java.lang.String r14, java.lang.String[] r15, java.lang.String r16, java.lang.String[] r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21) {
        /*
            r13 = this;
            r1 = r13
            monitor-enter(r13)
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0058 }
            r2.<init>()     // Catch:{ all -> 0x0058 }
            r3 = 0
            android.database.sqlite.SQLiteDatabase r4 = r1.f     // Catch:{ Exception -> 0x003e }
            r5 = r14
            r6 = r15
            r7 = r16
            r8 = r17
            r9 = r18
            r10 = r19
            r11 = r20
            r12 = r21
            android.database.Cursor r3 = r4.query(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x003e }
            boolean r0 = r3.moveToFirst()     // Catch:{ Exception -> 0x003e }
            if (r0 == 0) goto L_0x0033
        L_0x0022:
            android.content.ContentValues r0 = new android.content.ContentValues     // Catch:{ Exception -> 0x003e }
            r0.<init>()     // Catch:{ Exception -> 0x003e }
            android.database.DatabaseUtils.cursorRowToContentValues(r3, r0)     // Catch:{ Exception -> 0x003e }
            r2.add(r0)     // Catch:{ Exception -> 0x003e }
            boolean r0 = r3.moveToNext()     // Catch:{ Exception -> 0x003e }
            if (r0 != 0) goto L_0x0022
        L_0x0033:
            r3.close()     // Catch:{ Exception -> 0x003e }
            if (r3 == 0) goto L_0x0050
        L_0x0038:
            r3.close()     // Catch:{ all -> 0x0058 }
            goto L_0x0050
        L_0x003c:
            r0 = move-exception
            goto L_0x0052
        L_0x003e:
            r0 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x003c }
            java.lang.String r5 = "SDK encountered unexpected error in DbStore.getRows() method; "
            r4.<init>(r5)     // Catch:{ all -> 0x003c }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x003c }
            r4.append(r0)     // Catch:{ all -> 0x003c }
            if (r3 == 0) goto L_0x0050
            goto L_0x0038
        L_0x0050:
            monitor-exit(r13)
            return r2
        L_0x0052:
            if (r3 == 0) goto L_0x0057
            r3.close()     // Catch:{ all -> 0x0058 }
        L_0x0057:
            throw r0     // Catch:{ all -> 0x0058 }
        L_0x0058:
            r0 = move-exception
            monitor-exit(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.d.b.a(java.lang.String, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    public final synchronized int a(String str) {
        int i;
        try {
            StringBuilder sb = new StringBuilder("SELECT COUNT(*) FROM ");
            sb.append(str);
            sb.append(" ; ");
            Cursor rawQuery = this.f.rawQuery(sb.toString(), null);
            rawQuery.moveToFirst();
            i = rawQuery.getInt(0);
            rawQuery.close();
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in DbStore.getCount() method; ").append(e2.getMessage());
            return -1;
        }
        return i;
    }

    public final synchronized int b(String str, String str2, String[] strArr) {
        int i;
        try {
            StringBuilder sb = new StringBuilder("SELECT COUNT(*) FROM ");
            sb.append(str);
            sb.append(" WHERE ");
            sb.append(str2);
            sb.append(" ; ");
            Cursor rawQuery = this.f.rawQuery(sb.toString(), strArr);
            rawQuery.moveToFirst();
            i = rawQuery.getInt(0);
            rawQuery.close();
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in DbStore.getCount() method; ").append(e2.getMessage());
            return -1;
        }
        return i;
    }

    public final synchronized void a(String str, String str2) {
        try {
            StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
            sb.append(str);
            sb.append(str2);
            sb.append(";");
            this.f.execSQL(sb.toString());
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in DbStore.createTableIfNotExists() method; ").append(e2.getMessage());
        }
    }

    public final synchronized void b() {
        try {
            synchronized (d) {
                int i = e - 1;
                e = i;
                if (i == 0) {
                    this.f.close();
                    b = null;
                }
            }
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in DbStore.close() method; ").append(e2.getMessage());
        }
    }
}
