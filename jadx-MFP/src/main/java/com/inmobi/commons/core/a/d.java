package com.inmobi.commons.core.a;

import android.content.ContentValues;
import android.util.Log;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CrashEvent */
public class d {
    private static final String g = "d";
    int a;
    String b;
    String c;
    String d;
    long e;
    public String f;

    public d(Thread thread, Throwable th) {
        this("crashReporting", "CrashEvent");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", th.getClass().getSimpleName());
            jSONObject.put("message", th.getMessage());
            jSONObject.put("stack", Log.getStackTraceString(th));
            jSONObject.put("thread", thread.getName());
            this.f = jSONObject.toString();
        } catch (JSONException e2) {
            new StringBuilder("JSONException: ").append(e2);
        }
    }

    public d(String str, String str2) {
        this.b = UUID.randomUUID().toString();
        this.d = str;
        this.c = str2;
        this.f = null;
        this.e = System.currentTimeMillis();
    }

    private d(String str, String str2, String str3, String str4) {
        this.b = str;
        this.d = str2;
        this.c = str3;
        this.f = str4;
        this.e = System.currentTimeMillis();
    }

    public final String a() {
        String str = this.f;
        return str == null ? "" : str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.c);
        sb.append("@");
        sb.append(this.d);
        sb.append(" ");
        return sb.toString();
    }

    public static d a(ContentValues contentValues) {
        String asString = contentValues.getAsString("eventId");
        String asString2 = contentValues.getAsString("eventType");
        String asString3 = contentValues.getAsString("componentType");
        String asString4 = contentValues.getAsString("payload");
        long longValue = Long.valueOf(contentValues.getAsString("ts")).longValue();
        d dVar = new d(asString, asString3, asString2, asString4);
        dVar.e = longValue;
        dVar.a = contentValues.getAsInteger("id").intValue();
        return dVar;
    }
}
