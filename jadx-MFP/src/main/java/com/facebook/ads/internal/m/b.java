package com.facebook.ads.internal.m;

import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    public String a;
    public String b;
    public String c;
    public Date d;
    public boolean e;

    public b(String str, String str2, String str3, Date date) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = date;
        this.e = (str2 == null || str3 == null) ? false : true;
    }

    public b(JSONObject jSONObject) {
        this(jSONObject.optString("url"), jSONObject.optString(IpcUtil.KEY_CODE), jSONObject.optString("value"), new Date(jSONObject.optLong("expiration") * 1000));
    }

    public static List<b> a(String str) {
        JSONArray jSONArray;
        JSONObject jSONObject;
        if (str == null) {
            return null;
        }
        try {
            jSONArray = new JSONArray(str);
        } catch (JSONException unused) {
            jSONArray = null;
        }
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                jSONObject = jSONArray.getJSONObject(i);
            } catch (JSONException unused2) {
                jSONObject = null;
            }
            if (jSONObject != null) {
                arrayList.add(new b(jSONObject));
            }
        }
        return arrayList;
    }

    public String a() {
        Date date = this.d;
        if (date == null) {
            date = new Date();
            date.setTime(date.getTime() + 3600000);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(date);
    }

    public boolean b() {
        return (this.b == null || this.c == null || this.a == null) ? false : true;
    }
}
