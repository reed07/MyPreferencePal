package com.moat.analytics.mobile.und;

import android.util.Log;
import com.mopub.mobileads.VastExtensionXmlManager;

class p {
    p() {
    }

    private static String a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(VastExtensionXmlManager.MOAT);
        sb.append(str);
        return sb.toString();
    }

    static void a(int i, String str, Object obj, String str2) {
        String str3;
        String str4;
        if (w.a().b) {
            if (obj == null) {
                str4 = a(str);
                str3 = String.format("message = %s", new Object[]{str2});
            } else {
                str4 = a(str);
                str3 = String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2});
            }
            Log.println(i, str4, str3);
        }
    }

    static void a(String str, Object obj, String str2, Throwable th) {
        if (w.a().b) {
            Log.e(a(str), String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2}), th);
        }
    }

    static void a(String str, String str2) {
        if (!w.a().b && ((k) MoatAnalytics.getInstance()).a) {
            int i = 2;
            if (str.equals("[ERROR] ")) {
                i = 6;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(str2);
            Log.println(i, "MoatAnalytics", sb.toString());
        }
    }

    static void b(int i, String str, Object obj, String str2) {
        if (w.a().c) {
            String a = a(str);
            String str3 = "id = %s, message = %s";
            Object[] objArr = new Object[2];
            objArr[0] = obj == null ? "null" : Integer.valueOf(obj.hashCode());
            objArr[1] = str2;
            Log.println(i, a, String.format(str3, objArr));
        }
    }
}
