package com.inmobi.ads.cache;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.inmobi.commons.core.d.b;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/* compiled from: AssetDao */
public class d {
    public static final String[] a = {"id", "pending_attempts", "url", "disk_uri", "ts", "created_ts", "ttl", "soft_ttl"};
    private static final String b = "d";
    private static d c;
    private static final Object d = new Object();

    public static d a() {
        d dVar = c;
        if (dVar == null) {
            synchronized (d) {
                dVar = c;
                if (dVar == null) {
                    dVar = new d();
                    c = dVar;
                }
            }
        }
        return dVar;
    }

    private d() {
        b a2 = b.a();
        a2.a("asset", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, pending_attempts INTEGER NOT NULL, url TEXT NOT NULL, disk_uri TEXT, ts TEXT NOT NULL, created_ts TEXT NOT NULL, ttl TEXT NOT NULL, soft_ttl TEXT NOT NULL)");
        a2.b();
    }

    public final synchronized void a(a aVar) {
        if (b(aVar) <= 0) {
            ContentValues d2 = d(aVar);
            b a2 = b.a();
            a2.a("asset", d2);
            a2.b();
        }
    }

    public static List<a> b() {
        ArrayList arrayList = new ArrayList();
        b a2 = b.a();
        if (a2.a("asset") == 0) {
            return arrayList;
        }
        List<ContentValues> a3 = a2.a("asset", a, "disk_uri IS NOT NULL", null, null, null, "created_ts DESC ", null);
        a2.b();
        for (ContentValues a4 : a3) {
            arrayList.add(a(a4));
        }
        return arrayList;
    }

    public static String c() {
        List<a> b2 = b();
        if (b2.size() == 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (a aVar : b2) {
            try {
                jSONArray.put(URLEncoder.encode(aVar.d, "UTF-8"));
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return jSONArray.toString();
    }

    @Nullable
    static a a(String str) {
        b a2 = b.a();
        b bVar = a2;
        List a3 = bVar.a("asset", a, "url=? ", new String[]{str}, null, null, "created_ts DESC ", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        a2.b();
        if (a3.isEmpty()) {
            return null;
        }
        return a((ContentValues) a3.get(0));
    }

    public static List<String> d() {
        ArrayList arrayList = new ArrayList();
        b a2 = b.a();
        if (a2.a("asset") == 0) {
            return arrayList;
        }
        List<ContentValues> a3 = a2.a("asset", new String[]{"url"}, null, null, null, null, "created_ts DESC ", null);
        a2.b();
        for (ContentValues asString : a3) {
            arrayList.add(asString.getAsString("url"));
        }
        return arrayList;
    }

    @NonNull
    static List<a> e() {
        ArrayList arrayList = new ArrayList();
        b a2 = b.a();
        for (ContentValues a3 : a2.a("asset", a, "disk_uri IS NULL", null, null, null, "ts ASC ", null)) {
            arrayList.add(a(a3));
        }
        a2.b();
        return arrayList;
    }

    public static a b(String str) {
        b a2 = b.a();
        b bVar = a2;
        List a3 = bVar.a("asset", a, "url=? ", new String[]{str}, null, null, "created_ts DESC ", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        a2.b();
        if (a3.isEmpty()) {
            return null;
        }
        return a((ContentValues) a3.get(0));
    }

    public static int b(a aVar) {
        b a2 = b.a();
        int b2 = a2.b("asset", d(aVar), "url = ?", new String[]{String.valueOf(aVar.d)});
        a2.b();
        return b2;
    }

    public static void c(a aVar) {
        b a2 = b.a();
        a2.a("asset", "id = ?", new String[]{String.valueOf(aVar.b)});
        a2.b();
    }

    public static a a(ContentValues contentValues) {
        a aVar = new a(contentValues.getAsInteger("id").intValue(), contentValues.getAsString("url"), contentValues.getAsString("disk_uri"), contentValues.getAsInteger("pending_attempts").intValue(), Long.valueOf(contentValues.getAsString("ts")).longValue(), Long.valueOf(contentValues.getAsString("created_ts")).longValue(), Long.valueOf(contentValues.getAsString("ttl")).longValue(), Long.valueOf(contentValues.getAsString("soft_ttl")).longValue());
        return aVar;
    }

    private static ContentValues d(a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(aVar.b));
        contentValues.put("url", aVar.d);
        contentValues.put("disk_uri", aVar.e);
        contentValues.put("pending_attempts", Integer.valueOf(aVar.c));
        contentValues.put("ts", Long.toString(aVar.f));
        contentValues.put("created_ts", Long.toString(aVar.g));
        contentValues.put("ttl", Long.toString(aVar.h));
        contentValues.put("soft_ttl", Long.toString(aVar.i));
        return contentValues;
    }
}
