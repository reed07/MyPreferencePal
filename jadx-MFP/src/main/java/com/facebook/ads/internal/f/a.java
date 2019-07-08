package com.facebook.ads.internal.f;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.internal.w.h.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private static final String[] a = {"hide_ad", "hide_ad_description", "hide_ad_follow_up_heading", "hide_ad_options", "report_ad", "report_ad_description", "report_ad_follow_up_heading", "report_ad_options", "manage_ad_preferences", "finished_hide_ad", "finished_report_ad", "finished_description", "why_am_i_seeing_this", "ad_choices_uri", "manage_ad_preferences_uri"};
    private static a b;
    private final SharedPreferences c;

    private a(Context context) {
        this.c = context.getApplicationContext().getSharedPreferences(com.facebook.ads.internal.w.f.a.a("com.facebook.ads.AD_REPORTING_CONFIG", context), 0);
    }

    public static long a(Context context) {
        return o(context).c.getLong("last_updated_timestamp", 0);
    }

    private String a(String str, String str2) {
        String string = this.c.getString(str, str2);
        return (string == null || string.equals("null")) ? str2 : string;
    }

    private static List<c> a(String str) {
        if (TextUtils.isEmpty(str) || str.equalsIgnoreCase("null")) {
            return new ArrayList();
        }
        JSONArray jSONArray = new JSONArray(str);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = (JSONObject) jSONArray.get(i);
            c cVar = new c(jSONObject.getInt("option_value"), jSONObject.getString("option_text"), jSONObject.optString("children_heading"));
            for (c a2 : a(jSONObject.optString("children_options"))) {
                cVar.a(a2);
            }
            arrayList.add(cVar);
        }
        return arrayList;
    }

    private void a() {
        Editor edit = this.c.edit();
        edit.putLong("last_updated_timestamp", 0);
        edit.apply();
    }

    public static void a(Context context, @Nullable String str) {
        String[] strArr;
        Map b2 = b(context, str);
        if (b2 != null && b2.size() == a.length) {
            Editor edit = o(context).c.edit();
            for (String str2 : a) {
                edit.putString(str2, (String) b2.get(str2));
            }
            edit.putLong("last_updated_timestamp", System.currentTimeMillis());
            edit.apply();
        }
    }

    public static boolean a(Context context, boolean z) {
        return (z ? com.facebook.ads.internal.r.a.O(context) : com.facebook.ads.internal.r.a.P(context)) && a(context) > 0;
    }

    public static String b(Context context) {
        return o(context).a("hide_ad", "Hide Ad");
    }

    @Nullable
    private static Map<String, String> b(Context context, @Nullable String str) {
        String[] strArr;
        if (str != null && !str.isEmpty() && !str.equals("[]")) {
            HashMap hashMap = new HashMap();
            try {
                JSONObject jSONObject = new JSONObject(str);
                for (String str2 : a) {
                    if (!jSONObject.has(str2)) {
                        return null;
                    }
                    hashMap.put(str2, jSONObject.getString(str2));
                }
                if (a(jSONObject.getString("report_ad_options")).size() == 0) {
                    com.facebook.ads.internal.w.h.a.b(context, "reporting", b.M, new Exception("No report ad options"));
                    return null;
                } else if (a(jSONObject.getString("hide_ad_options")).size() != 0) {
                    return hashMap;
                } else {
                    com.facebook.ads.internal.w.h.a.b(context, "reporting", b.O, new Exception("No hide ad options"));
                    return null;
                }
            } catch (JSONException e) {
                com.facebook.ads.internal.w.h.a.b(context, "reporting", b.L, e);
            }
        }
        return null;
    }

    public static String c(Context context) {
        return o(context).a("hide_ad_description", "See fewer ads like this");
    }

    public static c d(Context context) {
        c cVar = new c(o(context).a("hide_ad_follow_up_heading", "Help us understand what is happening. Why don't you want to see this?"));
        try {
            for (c a2 : a(o(context).a("hide_ad_options", ""))) {
                cVar.a(a2);
            }
        } catch (JSONException e) {
            o(context).a();
            com.facebook.ads.internal.w.h.a.b(context, "reporting", b.P, e);
        }
        return cVar;
    }

    public static String e(Context context) {
        return o(context).a("report_ad", "Report Ad");
    }

    public static String f(Context context) {
        return o(context).a("report_ad_description", " Mark ad as offensive or inappropriate");
    }

    public static c g(Context context) {
        c cVar = new c(o(context).a("report_ad_follow_up_heading", "Help us understand what is happening. Why is this inappropriate?"));
        try {
            for (c a2 : a(o(context).a("report_ad_options", ""))) {
                cVar.a(a2);
            }
        } catch (JSONException e) {
            o(context).a();
            com.facebook.ads.internal.w.h.a.b(context, "reporting", b.N, e);
        }
        return cVar;
    }

    public static String h(Context context) {
        return o(context).a("manage_ad_preferences", "Manage ad preferences");
    }

    public static String i(Context context) {
        return o(context).a("finished_hide_ad", "Ad hidden.");
    }

    public static String j(Context context) {
        return o(context).a("finished_report_ad", "Ad reported.");
    }

    public static String k(Context context) {
        return o(context).a("finished_description", "Your submission is now being reviewed.");
    }

    public static String l(Context context) {
        return o(context).a("why_am_i_seeing_this", "Why am I seeing this?");
    }

    public static String m(Context context) {
        return o(context).a("ad_choices_uri", "");
    }

    public static String n(Context context) {
        return o(context).a("manage_ad_preferences_uri", "");
    }

    private static a o(Context context) {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a(context);
                }
            }
        }
        return b;
    }
}
