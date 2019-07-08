package com.facebook.ads.internal.s;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.VisibleForTesting;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.k.e;
import com.facebook.ads.internal.s.b.a;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class i implements a {
    private static final String a = "i";
    private Context b;
    private d c;

    @VisibleForTesting
    public i(Context context, d dVar) {
        this.b = context;
        this.c = dVar;
    }

    private static JSONArray a(JSONArray jSONArray, JSONArray jSONArray2, int i) {
        JSONArray jSONArray3 = jSONArray;
        JSONArray jSONArray4 = jSONArray2;
        if (jSONArray3 == null) {
            return jSONArray4;
        }
        if (jSONArray4 == null) {
            return jSONArray3;
        }
        int length = jSONArray.length();
        int length2 = jSONArray2.length();
        JSONArray jSONArray5 = new JSONArray();
        int i2 = 0;
        int i3 = i;
        double d = Double.MAX_VALUE;
        double d2 = Double.MAX_VALUE;
        JSONObject jSONObject = null;
        JSONObject jSONObject2 = null;
        int i4 = 0;
        while (true) {
            if ((i2 < length || i4 < length2) && i3 > 0) {
                if (i2 < length && jSONObject == null) {
                    try {
                        jSONObject = jSONArray3.getJSONObject(i2);
                        d = jSONObject.getDouble(TimestampAnalyticsHelper.ATTR_TIME);
                    } catch (JSONException unused) {
                        d = Double.MAX_VALUE;
                        jSONObject = null;
                    }
                    i2++;
                }
                if (i4 < length2 && jSONObject2 == null) {
                    try {
                        jSONObject2 = jSONArray4.getJSONObject(i4);
                        d2 = jSONObject2.getDouble(TimestampAnalyticsHelper.ATTR_TIME);
                    } catch (JSONException unused2) {
                        d2 = Double.MAX_VALUE;
                        jSONObject2 = null;
                    }
                    i4++;
                }
                if (jSONObject != null || jSONObject2 != null) {
                    if (jSONObject == null || d2 < d) {
                        jSONArray5.put(jSONObject2);
                        d2 = Double.MAX_VALUE;
                        jSONObject2 = null;
                    } else {
                        jSONArray5.put(jSONObject);
                        d = Double.MAX_VALUE;
                        jSONObject = null;
                    }
                    i3--;
                }
            }
        }
        if (i3 > 0) {
            if (jSONObject != null) {
                jSONArray5.put(jSONObject);
            } else if (jSONObject2 != null) {
                jSONArray5.put(jSONObject2);
            }
        }
        return jSONArray5;
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00f3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.json.JSONObject a(int r10) {
        /*
            r9 = this;
            r0 = 0
            com.facebook.ads.internal.j.d r1 = r9.c     // Catch:{ JSONException -> 0x00ea, all -> 0x00dc }
            android.database.Cursor r1 = r1.d()     // Catch:{ JSONException -> 0x00ea, all -> 0x00dc }
            com.facebook.ads.internal.j.d r2 = r9.c     // Catch:{ JSONException -> 0x00da, all -> 0x00d7 }
            android.database.Cursor r2 = r2.a(r10)     // Catch:{ JSONException -> 0x00da, all -> 0x00d7 }
            int r3 = r2.getCount()     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            if (r3 <= 0) goto L_0x0097
            org.json.JSONObject r3 = r9.a(r2)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            r4.<init>()     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            r5 = -1
            r2.moveToPosition(r5)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
        L_0x0020:
            boolean r5 = r2.moveToNext()     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            if (r5 == 0) goto L_0x0099
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            r5.<init>()     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            java.lang.String r6 = "id"
            r7 = 2
            java.lang.String r7 = r2.getString(r7)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            r5.put(r6, r7)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            java.lang.String r6 = "token_id"
            r7 = 0
            java.lang.String r7 = r2.getString(r7)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            r5.put(r6, r7)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            java.lang.String r6 = "type"
            r7 = 4
            java.lang.String r7 = r2.getString(r7)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            r5.put(r6, r7)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            java.lang.String r6 = "time"
            r7 = 5
            double r7 = r2.getDouble(r7)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            java.lang.String r7 = com.facebook.ads.internal.w.b.v.a(r7)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            r5.put(r6, r7)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            java.lang.String r6 = "session_time"
            r7 = 6
            double r7 = r2.getDouble(r7)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            java.lang.String r7 = com.facebook.ads.internal.w.b.v.a(r7)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            r5.put(r6, r7)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            java.lang.String r6 = "session_id"
            r7 = 7
            java.lang.String r7 = r2.getString(r7)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            r5.put(r6, r7)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            r6 = 8
            java.lang.String r6 = r2.getString(r6)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            java.lang.String r7 = "data"
            if (r6 == 0) goto L_0x0080
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            r8.<init>(r6)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            goto L_0x0085
        L_0x0080:
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            r8.<init>()     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
        L_0x0085:
            r5.put(r7, r8)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            java.lang.String r6 = "attempt"
            r7 = 9
            java.lang.String r7 = r2.getString(r7)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            r5.put(r6, r7)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            r4.put(r5)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            goto L_0x0020
        L_0x0097:
            r3 = r0
            r4 = r3
        L_0x0099:
            android.content.Context r5 = r9.b     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            boolean r5 = com.facebook.ads.internal.r.a.p(r5)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            if (r5 == 0) goto L_0x00b3
            android.content.Context r5 = r9.b     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            org.json.JSONArray r5 = com.facebook.ads.internal.k.e.a(r5, r10)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            if (r5 == 0) goto L_0x00b3
            int r6 = r5.length()     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            if (r6 <= 0) goto L_0x00b3
            org.json.JSONArray r4 = a(r5, r4, r10)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
        L_0x00b3:
            if (r4 == 0) goto L_0x00c7
            org.json.JSONObject r10 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            r10.<init>()     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            if (r3 == 0) goto L_0x00c1
            java.lang.String r5 = "tokens"
            r10.put(r5, r3)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
        L_0x00c1:
            java.lang.String r3 = "events"
            r10.put(r3, r4)     // Catch:{ JSONException -> 0x00d5, all -> 0x00d3 }
            goto L_0x00c8
        L_0x00c7:
            r10 = r0
        L_0x00c8:
            if (r1 == 0) goto L_0x00cd
            r1.close()
        L_0x00cd:
            if (r2 == 0) goto L_0x00d2
            r2.close()
        L_0x00d2:
            return r10
        L_0x00d3:
            r10 = move-exception
            goto L_0x00df
        L_0x00d5:
            goto L_0x00ec
        L_0x00d7:
            r10 = move-exception
            r2 = r0
            goto L_0x00df
        L_0x00da:
            r2 = r0
            goto L_0x00ec
        L_0x00dc:
            r10 = move-exception
            r1 = r0
            r2 = r1
        L_0x00df:
            if (r1 == 0) goto L_0x00e4
            r1.close()
        L_0x00e4:
            if (r2 == 0) goto L_0x00e9
            r2.close()
        L_0x00e9:
            throw r10
        L_0x00ea:
            r1 = r0
            r2 = r1
        L_0x00ec:
            if (r1 == 0) goto L_0x00f1
            r1.close()
        L_0x00f1:
            if (r2 == 0) goto L_0x00f6
            r2.close()
        L_0x00f6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.s.i.a(int):org.json.JSONObject");
    }

    private JSONObject a(Cursor cursor) {
        JSONObject jSONObject = new JSONObject();
        while (cursor.moveToNext()) {
            jSONObject.put(cursor.getString(0), cursor.getString(1));
        }
        return jSONObject;
    }

    private void a(String str) {
        if (e.c(str)) {
            e.a(str);
        } else {
            this.c.a(str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0121  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.json.JSONObject e() {
        /*
            r10 = this;
            r0 = 0
            com.facebook.ads.internal.j.d r1 = r10.c     // Catch:{ JSONException -> 0x0118, all -> 0x0109 }
            android.database.Cursor r1 = r1.f()     // Catch:{ JSONException -> 0x0118, all -> 0x0109 }
            com.facebook.ads.internal.j.d r2 = r10.c     // Catch:{ JSONException -> 0x0107, all -> 0x0102 }
            android.database.Cursor r2 = r2.e()     // Catch:{ JSONException -> 0x0107, all -> 0x0102 }
            int r3 = r1.getCount()     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            if (r3 <= 0) goto L_0x00b4
            int r3 = r2.getCount()     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            if (r3 <= 0) goto L_0x00b4
            org.json.JSONObject r3 = r10.a(r1)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            r4.<init>()     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            r5 = -1
            r2.moveToPosition(r5)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
        L_0x0026:
            boolean r5 = r2.moveToNext()     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            if (r5 == 0) goto L_0x00b6
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            r5.<init>()     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            java.lang.String r6 = "id"
            com.facebook.ads.internal.j.b r7 = com.facebook.ads.internal.j.c.a     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            int r7 = r7.a     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            java.lang.String r7 = r2.getString(r7)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            r5.put(r6, r7)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            java.lang.String r6 = "token_id"
            com.facebook.ads.internal.j.b r7 = com.facebook.ads.internal.j.c.b     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            int r7 = r7.a     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            java.lang.String r7 = r2.getString(r7)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            r5.put(r6, r7)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            java.lang.String r6 = "type"
            com.facebook.ads.internal.j.b r7 = com.facebook.ads.internal.j.c.d     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            int r7 = r7.a     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            java.lang.String r7 = r2.getString(r7)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            r5.put(r6, r7)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            java.lang.String r6 = "time"
            com.facebook.ads.internal.j.b r7 = com.facebook.ads.internal.j.c.e     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            int r7 = r7.a     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            double r7 = r2.getDouble(r7)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            java.lang.String r7 = com.facebook.ads.internal.w.b.v.a(r7)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            r5.put(r6, r7)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            java.lang.String r6 = "session_time"
            com.facebook.ads.internal.j.b r7 = com.facebook.ads.internal.j.c.f     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            int r7 = r7.a     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            double r7 = r2.getDouble(r7)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            java.lang.String r7 = com.facebook.ads.internal.w.b.v.a(r7)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            r5.put(r6, r7)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            java.lang.String r6 = "session_id"
            com.facebook.ads.internal.j.b r7 = com.facebook.ads.internal.j.c.g     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            int r7 = r7.a     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            java.lang.String r7 = r2.getString(r7)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            r5.put(r6, r7)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            com.facebook.ads.internal.j.b r6 = com.facebook.ads.internal.j.c.h     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            int r6 = r6.a     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            java.lang.String r6 = r2.getString(r6)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            java.lang.String r7 = "data"
            if (r6 == 0) goto L_0x009a
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            r8.<init>(r6)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            goto L_0x009f
        L_0x009a:
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            r8.<init>()     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
        L_0x009f:
            r5.put(r7, r8)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            java.lang.String r6 = "attempt"
            com.facebook.ads.internal.j.b r7 = com.facebook.ads.internal.j.c.i     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            int r7 = r7.a     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            java.lang.String r7 = r2.getString(r7)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            r5.put(r6, r7)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            r4.put(r5)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            goto L_0x0026
        L_0x00b4:
            r3 = r0
            r4 = r3
        L_0x00b6:
            android.content.Context r5 = r10.b     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            boolean r5 = com.facebook.ads.internal.r.a.p(r5)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            if (r5 == 0) goto L_0x00df
            android.content.Context r5 = r10.b     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            org.json.JSONArray r5 = com.facebook.ads.internal.k.e.a(r5)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            if (r5 == 0) goto L_0x00df
            int r6 = r5.length()     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            if (r6 <= 0) goto L_0x00df
            r6 = 0
            if (r5 == 0) goto L_0x00d4
            int r7 = r5.length()     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            int r6 = r6 + r7
        L_0x00d4:
            if (r4 == 0) goto L_0x00db
            int r7 = r4.length()     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            int r6 = r6 + r7
        L_0x00db:
            org.json.JSONArray r4 = a(r5, r4, r6)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
        L_0x00df:
            if (r4 == 0) goto L_0x00f3
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            r5.<init>()     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            if (r3 == 0) goto L_0x00ed
            java.lang.String r6 = "tokens"
            r5.put(r6, r3)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
        L_0x00ed:
            java.lang.String r3 = "events"
            r5.put(r3, r4)     // Catch:{ JSONException -> 0x0100, all -> 0x00fe }
            r0 = r5
        L_0x00f3:
            if (r1 == 0) goto L_0x00f8
            r1.close()
        L_0x00f8:
            if (r2 == 0) goto L_0x00fd
            r2.close()
        L_0x00fd:
            return r0
        L_0x00fe:
            r0 = move-exception
            goto L_0x010d
        L_0x0100:
            goto L_0x011a
        L_0x0102:
            r2 = move-exception
            r9 = r2
            r2 = r0
            r0 = r9
            goto L_0x010d
        L_0x0107:
            r2 = r0
            goto L_0x011a
        L_0x0109:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L_0x010d:
            if (r1 == 0) goto L_0x0112
            r1.close()
        L_0x0112:
            if (r2 == 0) goto L_0x0117
            r2.close()
        L_0x0117:
            throw r0
        L_0x0118:
            r1 = r0
            r2 = r1
        L_0x011a:
            if (r1 == 0) goto L_0x011f
            r1.close()
        L_0x011f:
            if (r2 == 0) goto L_0x0124
            r2.close()
        L_0x0124:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.s.i.e():org.json.JSONObject");
    }

    public JSONObject a() {
        int x = com.facebook.ads.internal.r.a.x(this.b);
        return x > 0 ? a(x) : e();
    }

    public boolean a(JSONArray jSONArray) {
        boolean p = com.facebook.ads.internal.r.a.p(this.b);
        boolean z = true;
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("id");
                int i2 = jSONObject.getInt("code");
                if (i2 == 1) {
                    if (!this.c.b(string)) {
                        if (!p) {
                        }
                    }
                } else if (i2 < 1000 || i2 >= 2000) {
                    if (i2 >= 2000) {
                        if (i2 < 3000) {
                            if (!this.c.b(string)) {
                                if (!p) {
                                }
                            }
                        }
                    }
                } else {
                    a(string);
                    z = false;
                }
                e.b(string);
            } catch (JSONException unused) {
            }
        }
        return z;
    }

    public void b() {
        this.c.g();
        this.c.b();
        e.d(this.b);
    }

    public void b(JSONArray jSONArray) {
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            try {
                a(jSONArray.getJSONObject(i).getString("id"));
            } catch (JSONException unused) {
            }
        }
    }

    public void c() {
        this.c.h();
        e.b(this.b);
    }

    /* JADX INFO: finally extract failed */
    public boolean d() {
        int x = com.facebook.ads.internal.r.a.x(this.b);
        boolean z = true;
        if (x < 1) {
            return false;
        }
        Cursor cursor = null;
        try {
            Cursor d = this.c.d();
            if ((d.moveToFirst() ? d.getInt(0) : 0) + e.c(this.b) <= x) {
                z = false;
            }
            if (d != null) {
                d.close();
            }
            return z;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }
}
