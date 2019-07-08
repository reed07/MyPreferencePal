package com.integralads.avid.library.inmobi.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AvidJSONUtil {
    private static String[] KEYS = {com.integralads.avid.library.mopub.utils.AvidJSONUtil.KEY_X, "y", "width", "height"};
    static float density = Resources.getSystem().getDisplayMetrics().density;

    public static void init(Context context) {
        if (context != null) {
            density = context.getResources().getDisplayMetrics().density;
        }
    }

    public static JSONObject getEmptyTreeJSONObject() {
        return getTreeJSONObject(getViewState(0, 0, 0, 0), AvidTimestamp.getCurrentTime());
    }

    public static JSONObject getTreeJSONObject(JSONObject jSONObject, double d) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("timestamp", d);
            jSONObject2.put(com.integralads.avid.library.mopub.utils.AvidJSONUtil.KEY_ROOT_VIEW, jSONObject);
        } catch (JSONException e) {
            AvidLogs.e("Error with creating treeJSONObject", e);
        }
        return jSONObject2;
    }

    public static JSONObject getViewState(int i, int i2, int i3, int i4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(com.integralads.avid.library.mopub.utils.AvidJSONUtil.KEY_X, (double) pxToDp(i));
            jSONObject.put("y", (double) pxToDp(i2));
            jSONObject.put("width", (double) pxToDp(i3));
            jSONObject.put("height", (double) pxToDp(i4));
        } catch (JSONException e) {
            AvidLogs.e("Error with creating viewStateObject", e);
        }
        return jSONObject;
    }

    static float pxToDp(int i) {
        return ((float) i) / density;
    }

    public static void addAvidId(JSONObject jSONObject, String str) {
        try {
            jSONObject.put("id", str);
        } catch (JSONException e) {
            AvidLogs.e("Error with setting avid id", e);
        }
    }

    public static void addFriendlyObstruction(JSONObject jSONObject, List<String> list) {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        try {
            jSONObject.put(com.integralads.avid.library.mopub.utils.AvidJSONUtil.KEY_IS_FRIENDLY_OBSTRUCTION_FOR, jSONArray);
        } catch (JSONException e) {
            AvidLogs.e("Error with setting friendly obstruction", e);
        }
    }

    public static void addChildState(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(com.integralads.avid.library.mopub.utils.AvidJSONUtil.KEY_CHILD_VIEWS);
            if (optJSONArray == null) {
                optJSONArray = new JSONArray();
                jSONObject.put(com.integralads.avid.library.mopub.utils.AvidJSONUtil.KEY_CHILD_VIEWS, optJSONArray);
            }
            optJSONArray.put(jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void fixStateFrame(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray(com.integralads.avid.library.mopub.utils.AvidJSONUtil.KEY_CHILD_VIEWS);
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i3);
                if (optJSONObject != null) {
                    int optInt = optJSONObject.optInt(com.integralads.avid.library.mopub.utils.AvidJSONUtil.KEY_X);
                    int optInt2 = optJSONObject.optInt("y");
                    int optInt3 = optJSONObject.optInt("width");
                    int optInt4 = optJSONObject.optInt("height");
                    i = Math.max(i, optInt + optInt3);
                    i2 = Math.max(i2, optInt2 + optInt4);
                }
            }
            try {
                jSONObject.put("width", i);
                jSONObject.put("height", i2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean equalStates(@NonNull JSONObject jSONObject, @Nullable JSONObject jSONObject2) {
        if (jSONObject2 != null && compareRequiredValues(jSONObject, jSONObject2) && compareSessionId(jSONObject, jSONObject2) && compareFriendlySessionIds(jSONObject, jSONObject2) && compareChildren(jSONObject, jSONObject2)) {
            return true;
        }
        return false;
    }

    private static boolean compareRequiredValues(JSONObject jSONObject, JSONObject jSONObject2) {
        String[] strArr;
        for (String str : KEYS) {
            if (jSONObject.optDouble(str) != jSONObject2.optDouble(str)) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareSessionId(JSONObject jSONObject, JSONObject jSONObject2) {
        return jSONObject.optString("id", "").equals(jSONObject2.optString("id", ""));
    }

    private static boolean compareFriendlySessionIds(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONArray optJSONArray = jSONObject.optJSONArray(com.integralads.avid.library.mopub.utils.AvidJSONUtil.KEY_IS_FRIENDLY_OBSTRUCTION_FOR);
        JSONArray optJSONArray2 = jSONObject2.optJSONArray(com.integralads.avid.library.mopub.utils.AvidJSONUtil.KEY_IS_FRIENDLY_OBSTRUCTION_FOR);
        if (!compareJSONArrays(optJSONArray, optJSONArray2)) {
            return false;
        }
        if (optJSONArray == null) {
            return true;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            if (!optJSONArray.optString(i, "").equals(optJSONArray2.optString(i, ""))) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareChildren(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONArray optJSONArray = jSONObject.optJSONArray(com.integralads.avid.library.mopub.utils.AvidJSONUtil.KEY_CHILD_VIEWS);
        JSONArray optJSONArray2 = jSONObject2.optJSONArray(com.integralads.avid.library.mopub.utils.AvidJSONUtil.KEY_CHILD_VIEWS);
        if (!compareJSONArrays(optJSONArray, optJSONArray2)) {
            return false;
        }
        if (optJSONArray == null) {
            return true;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            if (!equalStates(optJSONArray.optJSONObject(i), optJSONArray2.optJSONObject(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareJSONArrays(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray == null && jSONArray2 == null) {
            return true;
        }
        return (jSONArray != null || jSONArray2 == null) && (jSONArray == null || jSONArray2 != null) && jSONArray.length() == jSONArray2.length();
    }
}
