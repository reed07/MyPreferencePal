package com.facebook.ads.internal.g;

public class c {

    public static class a {
        public String a;
        public String b;
        public boolean c;

        public a(String str, String str2, boolean z) {
            this.a = str;
            this.b = str2;
            this.c = z;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:21|22|(1:24)|25) */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r2 = new com.facebook.ads.internal.g.c.a(null, null, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0068, code lost:
        if (r11 != null) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006a, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006d, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006e, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0063 */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0071  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.ads.internal.g.c.a a(android.content.ContentResolver r11) {
        /*
            r0 = 0
            r1 = 0
            java.lang.String r2 = "aid"
            java.lang.String r3 = "androidid"
            java.lang.String r4 = "limit_tracking"
            java.lang.String[] r7 = new java.lang.String[]{r2, r3, r4}     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            java.lang.String r2 = "content://com.facebook.katana.provider.AttributionIdProvider"
            android.net.Uri r6 = android.net.Uri.parse(r2)     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            r8 = 0
            r9 = 0
            r10 = 0
            r5 = r11
            android.database.Cursor r11 = r5.query(r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            if (r11 == 0) goto L_0x0054
            boolean r2 = r11.moveToFirst()     // Catch:{ Exception -> 0x0063 }
            if (r2 != 0) goto L_0x0023
            goto L_0x0054
        L_0x0023:
            java.lang.String r2 = "aid"
            int r2 = r11.getColumnIndex(r2)     // Catch:{ Exception -> 0x0063 }
            java.lang.String r2 = r11.getString(r2)     // Catch:{ Exception -> 0x0063 }
            java.lang.String r3 = "androidid"
            int r3 = r11.getColumnIndex(r3)     // Catch:{ Exception -> 0x0063 }
            java.lang.String r3 = r11.getString(r3)     // Catch:{ Exception -> 0x0063 }
            java.lang.String r4 = "limit_tracking"
            int r4 = r11.getColumnIndex(r4)     // Catch:{ Exception -> 0x0063 }
            java.lang.String r4 = r11.getString(r4)     // Catch:{ Exception -> 0x0063 }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ Exception -> 0x0063 }
            com.facebook.ads.internal.g.c$a r5 = new com.facebook.ads.internal.g.c$a     // Catch:{ Exception -> 0x0063 }
            boolean r4 = r4.booleanValue()     // Catch:{ Exception -> 0x0063 }
            r5.<init>(r2, r3, r4)     // Catch:{ Exception -> 0x0063 }
            if (r11 == 0) goto L_0x0053
            r11.close()
        L_0x0053:
            return r5
        L_0x0054:
            com.facebook.ads.internal.g.c$a r2 = new com.facebook.ads.internal.g.c$a     // Catch:{ Exception -> 0x0063 }
            r2.<init>(r1, r1, r0)     // Catch:{ Exception -> 0x0063 }
            if (r11 == 0) goto L_0x005e
            r11.close()
        L_0x005e:
            return r2
        L_0x005f:
            r0 = move-exception
            r11 = r1
            goto L_0x006f
        L_0x0062:
            r11 = r1
        L_0x0063:
            com.facebook.ads.internal.g.c$a r2 = new com.facebook.ads.internal.g.c$a     // Catch:{ all -> 0x006e }
            r2.<init>(r1, r1, r0)     // Catch:{ all -> 0x006e }
            if (r11 == 0) goto L_0x006d
            r11.close()
        L_0x006d:
            return r2
        L_0x006e:
            r0 = move-exception
        L_0x006f:
            if (r11 == 0) goto L_0x0074
            r11.close()
        L_0x0074:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.g.c.a(android.content.ContentResolver):com.facebook.ads.internal.g.c$a");
    }
}
