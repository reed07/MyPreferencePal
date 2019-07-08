package com.mopub.common.util;

import android.text.TextUtils;
import com.mopub.common.logging.MoPubLog;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Json {
    public static Map<String, String> jsonStringToMap(String str) throws JSONException {
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(str)) {
            return hashMap;
        }
        JSONObject jSONObject = (JSONObject) new JSONTokener(str).nextValue();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            hashMap.put(str2, jSONObject.getString(str2));
        }
        return hashMap;
    }

    public static String mapToJsonString(Map<String, String> map) {
        if (map == null) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean z = true;
        for (Entry entry : map.entrySet()) {
            if (!z) {
                sb.append(",");
            }
            sb.append("\"");
            sb.append((String) entry.getKey());
            sb.append("\":\"");
            sb.append((String) entry.getValue());
            sb.append("\"");
            z = false;
        }
        sb.append("}");
        return sb.toString();
    }

    public static String[] jsonArrayToStringArray(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("{key:");
        sb.append(str);
        sb.append("}");
        try {
            JSONArray jSONArray = ((JSONObject) new JSONTokener(sb.toString()).nextValue()).getJSONArray(IpcUtil.KEY_CODE);
            String[] strArr = new String[jSONArray.length()];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = jSONArray.getString(i);
            }
            return strArr;
        } catch (JSONException unused) {
            return new String[0];
        }
    }

    public static <T> T getJsonValue(JSONObject jSONObject, String str, Class<T> cls) {
        if (jSONObject == null || str == null || cls == null) {
            throw new IllegalArgumentException("Cannot pass any null argument to getJsonValue");
        }
        Object opt = jSONObject.opt(str);
        if (opt == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Tried to get Json value with key: ");
            sb.append(str);
            sb.append(", but it was null");
            MoPubLog.w(sb.toString());
            return null;
        } else if (cls.isInstance(opt)) {
            return cls.cast(opt);
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Tried to get Json value with key: ");
            sb2.append(str);
            sb2.append(", of type: ");
            sb2.append(cls.toString());
            sb2.append(", its type did not match");
            MoPubLog.w(sb2.toString());
            return null;
        }
    }
}
