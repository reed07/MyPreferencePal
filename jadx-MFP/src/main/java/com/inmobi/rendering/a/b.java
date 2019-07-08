package com.inmobi.rendering.a;

import android.content.ContentValues;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ClickDao */
public class b {
    static final String[] a = {"id", "pending_attempts", "url", "ping_in_webview", "follow_redirect", "ts", "created_ts", "track_extras"};
    private static final String b = "b";

    b() {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        a2.a("click", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, pending_attempts INTEGER NOT NULL, url TEXT NOT NULL, ping_in_webview TEXT NOT NULL, follow_redirect TEXT NOT NULL, ts TEXT NOT NULL, track_extras TEXT, created_ts TEXT NOT NULL )");
        a2.b();
    }

    public static boolean a() {
        return com.inmobi.commons.core.d.b.a().a("click") == 0;
    }

    public final synchronized boolean a(a aVar, int i) {
        ContentValues b2 = b(aVar);
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        if (a2.a("click") >= i) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("pingUrl", aVar.b);
                hashMap.put("errorCode", "MaxDbLimitBreach");
                com.inmobi.commons.core.e.b.a();
                com.inmobi.commons.core.e.b.a("ads", "PingDiscarded", hashMap);
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                sb.append(e.getMessage());
                sb.append(")");
            }
            a a3 = a((ContentValues) a2.a("click", a, "ts= (SELECT MIN(ts) FROM click LIMIT 1)", null, null, null, null, null).get(0));
            StringBuilder sb2 = new StringBuilder("Deleting click (");
            sb2.append(a3.a);
            sb2.append(")");
            a(a3);
        }
        a2.a("click", b2);
        a2.b();
        return true;
    }

    public static void a(a aVar) {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        a2.a("click", "id = ?", new String[]{String.valueOf(aVar.a)});
        a2.b();
    }

    static a a(ContentValues contentValues) {
        int intValue = contentValues.getAsInteger("id").intValue();
        int intValue2 = contentValues.getAsInteger("pending_attempts").intValue();
        String asString = contentValues.getAsString("url");
        long longValue = Long.valueOf(contentValues.getAsString("ts")).longValue();
        long longValue2 = Long.valueOf(contentValues.getAsString("created_ts")).longValue();
        boolean booleanValue = Boolean.valueOf(contentValues.getAsString("follow_redirect")).booleanValue();
        boolean booleanValue2 = Boolean.valueOf(contentValues.getAsString("ping_in_webview")).booleanValue();
        String asString2 = contentValues.getAsString("track_extras");
        HashMap hashMap = new HashMap();
        if (asString2 != null) {
            try {
                hashMap.putAll(a(new JSONObject(asString2)));
            } catch (JSONException e) {
                StringBuilder sb = new StringBuilder("JSONException in parsing extras (");
                sb.append(e.getMessage());
                sb.append(")");
            } catch (Exception e2) {
                StringBuilder sb2 = new StringBuilder("Exception in parsing extras (");
                sb2.append(e2.getMessage());
                sb2.append(")");
            }
        }
        a aVar = new a(intValue, asString, hashMap, booleanValue, booleanValue2, intValue2, longValue, longValue2);
        return aVar;
    }

    static ContentValues b(a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(aVar.a));
        contentValues.put("url", aVar.b);
        contentValues.put("pending_attempts", Integer.valueOf(aVar.f));
        contentValues.put("ts", Long.toString(aVar.d));
        contentValues.put("created_ts", Long.toString(aVar.e));
        contentValues.put("follow_redirect", Boolean.toString(aVar.i));
        contentValues.put("ping_in_webview", Boolean.toString(aVar.h));
        if (aVar.c != null && aVar.c.size() > 0) {
            contentValues.put("track_extras", new JSONObject(aVar.c).toString());
        }
        return contentValues;
    }

    private static Map<String, String> a(JSONObject jSONObject) throws JSONException {
        HashMap hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashMap.put(str, (String) jSONObject.get(str));
        }
        return hashMap;
    }
}
