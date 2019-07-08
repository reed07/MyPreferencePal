package com.facebook.ads.internal.j;

import android.database.Cursor;
import android.database.SQLException;
import android.support.annotation.WorkerThread;
import com.mopub.common.Constants;
import com.myfitnesspal.feature.payments.util.GooglePlayConstants;

public class h extends g {
    public static final b a = new b(0, "token_id", "TEXT PRIMARY KEY");
    public static final b b = new b(1, GooglePlayConstants.BILLING_JSON_FIELD_TOKEN, "TEXT");
    public static final b[] c = {a, b};
    private static final String d = h.class.getSimpleName();
    private static final String e = a("tokens", c);
    private static final String f;
    private static final String g;

    static {
        b[] bVarArr = c;
        b bVar = b;
        StringBuilder sb = new StringBuilder(g.a("tokens", bVarArr));
        sb.append(" WHERE ");
        sb.append(bVar.b);
        sb.append(" = ?");
        f = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("DELETE FROM tokens WHERE NOT EXISTS (SELECT 1 FROM events WHERE tokens.");
        sb2.append(a.b);
        sb2.append(" = ");
        sb2.append(Constants.VIDEO_TRACKING_EVENTS_KEY);
        sb2.append(".");
        sb2.append(c.b.b);
        sb2.append(")");
        g = sb2.toString();
    }

    public h(d dVar) {
        super(dVar);
    }

    public String a() {
        return "tokens";
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0064  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(java.lang.String r6) {
        /*
            r5 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 != 0) goto L_0x0068
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.f()     // Catch:{ all -> 0x0060 }
            java.lang.String r2 = f     // Catch:{ all -> 0x0060 }
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ all -> 0x0060 }
            r4 = 0
            r3[r4] = r6     // Catch:{ all -> 0x0060 }
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch:{ all -> 0x0060 }
            boolean r2 = r1.moveToNext()     // Catch:{ all -> 0x005e }
            if (r2 == 0) goto L_0x0026
            com.facebook.ads.internal.j.b r2 = a     // Catch:{ all -> 0x005e }
            int r2 = r2.a     // Catch:{ all -> 0x005e }
            java.lang.String r2 = r1.getString(r2)     // Catch:{ all -> 0x005e }
            goto L_0x0027
        L_0x0026:
            r2 = r0
        L_0x0027:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x005e }
            if (r3 != 0) goto L_0x0033
            if (r1 == 0) goto L_0x0032
            r1.close()
        L_0x0032:
            return r2
        L_0x0033:
            java.util.UUID r2 = java.util.UUID.randomUUID()     // Catch:{ all -> 0x005e }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x005e }
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch:{ all -> 0x005e }
            r4 = 2
            r3.<init>(r4)     // Catch:{ all -> 0x005e }
            com.facebook.ads.internal.j.b r4 = a     // Catch:{ all -> 0x005e }
            java.lang.String r4 = r4.b     // Catch:{ all -> 0x005e }
            r3.put(r4, r2)     // Catch:{ all -> 0x005e }
            com.facebook.ads.internal.j.b r4 = b     // Catch:{ all -> 0x005e }
            java.lang.String r4 = r4.b     // Catch:{ all -> 0x005e }
            r3.put(r4, r6)     // Catch:{ all -> 0x005e }
            android.database.sqlite.SQLiteDatabase r6 = r5.f()     // Catch:{ all -> 0x005e }
            java.lang.String r4 = "tokens"
            r6.insertOrThrow(r4, r0, r3)     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x005d
            r1.close()
        L_0x005d:
            return r2
        L_0x005e:
            r6 = move-exception
            goto L_0x0062
        L_0x0060:
            r6 = move-exception
            r1 = r0
        L_0x0062:
            if (r1 == 0) goto L_0x0067
            r1.close()
        L_0x0067:
            throw r6
        L_0x0068:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Invalid token."
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.j.h.a(java.lang.String):java.lang.String");
    }

    public b[] b() {
        return c;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public Cursor c() {
        return f().rawQuery(e, null);
    }

    @WorkerThread
    public void d() {
        try {
            f().execSQL(g);
        } catch (SQLException unused) {
        }
    }
}
