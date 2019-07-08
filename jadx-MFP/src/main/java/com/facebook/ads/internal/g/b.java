package com.facebook.ads.internal.g;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import com.facebook.ads.internal.g.a.c;
import com.facebook.ads.internal.g.c.a;

@WorkerThread
public class b {
    public static String a = "";
    public static String b = "";
    public static boolean c = false;
    public static String d = "";

    public static void a(Context context) {
        a aVar;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(com.facebook.ads.internal.w.f.a.a("SDKIDFA", context), 0);
            if (sharedPreferences.contains("attributionId")) {
                a = sharedPreferences.getString("attributionId", "");
            }
            if (sharedPreferences.contains("advertisingId")) {
                b = sharedPreferences.getString("advertisingId", "");
                c = sharedPreferences.getBoolean("limitAdTracking", c);
                d = c.SHARED_PREFS.name();
            }
            a aVar2 = null;
            try {
                aVar = c.a(context.getContentResolver());
            } catch (Exception e) {
                com.facebook.ads.internal.o.b.a(com.facebook.ads.internal.o.a.a(e, "Error retrieving attribution id from fb4a"));
                aVar = null;
            }
            if (!(aVar == null || aVar.a == null)) {
                a = aVar.a;
            }
            if (com.facebook.ads.internal.w.b.a.a() && com.facebook.ads.internal.w.b.a.b("aid_override")) {
                a = com.facebook.ads.internal.w.b.a.a("aid_override");
            }
            try {
                aVar2 = a.a(context, aVar);
            } catch (Exception e2) {
                com.facebook.ads.internal.o.b.a(com.facebook.ads.internal.o.a.a(e2, "Error retrieving advertising id from Google Play Services"));
            }
            if (aVar2 != null) {
                String a2 = aVar2.a();
                Boolean valueOf = Boolean.valueOf(aVar2.b());
                if (a2 != null) {
                    b = a2;
                    c = valueOf.booleanValue();
                    d = aVar2.c().name();
                }
            }
            Editor edit = sharedPreferences.edit();
            edit.putString("attributionId", a);
            edit.putString("advertisingId", b);
            edit.putBoolean("limitAdTracking", c);
            edit.apply();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
