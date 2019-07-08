package com.facebook.ads.internal.o;

import android.support.annotation.Nullable;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    public static String a;
    private String b;
    private Map<String, Object> c;
    private int d;
    private String e;

    public a(String str, Map<String, Object> map, int i, String str2) {
        this.b = str;
        this.c = map;
        this.d = i;
        this.e = str2;
    }

    public static a a(@Nullable Throwable th, @Nullable String str) {
        HashMap hashMap = new HashMap();
        if (th != null) {
            hashMap.put("ex", th.getClass().getSimpleName());
            hashMap.put("ex_msg", th.getMessage());
        }
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        String str2 = "error";
        if (str == null) {
            str = a;
        }
        return new a(str2, hashMap, currentTimeMillis, str);
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.b);
            jSONObject.put("data", new JSONObject(this.c));
            jSONObject.put(TimestampAnalyticsHelper.ATTR_TIME, this.d);
            jSONObject.put("request_id", this.e);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
