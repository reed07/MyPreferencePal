package com.amplitude.api;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
    private static AmplitudeLog logger = AmplitudeLog.getLogger();

    static JSONObject cloneJSONObject(JSONObject jSONObject) {
        JSONArray jSONArray;
        if (jSONObject == null) {
            return null;
        }
        try {
            jSONArray = jSONObject.names();
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.e("com.amplitude.api.Utils", e.toString());
            jSONArray = null;
        }
        int length = jSONArray != null ? jSONArray.length() : 0;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = jSONArray.optString(i);
        }
        try {
            return new JSONObject(jSONObject, strArr);
        } catch (JSONException e2) {
            logger.e("com.amplitude.api.Utils", e2.toString());
            return null;
        }
    }

    static boolean compareJSONObjects(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == jSONObject2) {
            return true;
        }
        if ((jSONObject != null && jSONObject2 == null) || (jSONObject == null && jSONObject2 != null)) {
            return false;
        }
        try {
            if (jSONObject.length() != jSONObject2.length()) {
                return false;
            }
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (!jSONObject2.has(str)) {
                    return false;
                }
                Object obj = jSONObject.get(str);
                Object obj2 = jSONObject2.get(str);
                if (!obj.getClass().equals(obj2.getClass())) {
                    return false;
                }
                if (obj.getClass() == JSONObject.class) {
                    if (!compareJSONObjects((JSONObject) obj, (JSONObject) obj2)) {
                        return false;
                    }
                } else if (!obj.equals(obj2)) {
                    return false;
                }
            }
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    public static boolean isEmptyString(String str) {
        return str == null || str.length() == 0;
    }

    static String normalizeInstanceName(String str) {
        if (isEmptyString(str)) {
            str = "$default_instance";
        }
        return str.toLowerCase();
    }
}
