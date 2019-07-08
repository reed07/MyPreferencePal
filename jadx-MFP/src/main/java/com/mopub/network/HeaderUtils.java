package com.mopub.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.ResponseHeader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HeaderUtils {
    @NonNull
    public static String extractHeader(@Nullable JSONObject jSONObject, @NonNull ResponseHeader responseHeader) {
        Preconditions.checkNotNull(responseHeader);
        if (jSONObject == null) {
            return "";
        }
        return jSONObject.optString(responseHeader.getKey());
    }

    @Nullable
    public static Integer extractIntegerHeader(JSONObject jSONObject, ResponseHeader responseHeader) {
        return formatIntHeader(extractHeader(jSONObject, responseHeader));
    }

    public static boolean extractBooleanHeader(JSONObject jSONObject, ResponseHeader responseHeader, boolean z) {
        return formatBooleanHeader(extractHeader(jSONObject, responseHeader), z);
    }

    @Nullable
    public static Integer extractPercentHeader(JSONObject jSONObject, ResponseHeader responseHeader) {
        return formatPercentHeader(extractHeader(jSONObject, responseHeader));
    }

    @NonNull
    static List<String> extractStringArray(@NonNull JSONObject jSONObject, @NonNull ResponseHeader responseHeader) {
        Preconditions.checkNotNull(jSONObject);
        Preconditions.checkNotNull(responseHeader);
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray(responseHeader.getKey());
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                arrayList.add(optJSONArray.getString(i));
            } catch (JSONException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to parse item ");
                sb.append(i);
                sb.append(" from ");
                sb.append(responseHeader.getKey());
                MoPubLog.d(sb.toString());
            }
        }
        return arrayList;
    }

    @Nullable
    static String extractPercentHeaderString(JSONObject jSONObject, ResponseHeader responseHeader) {
        Integer extractPercentHeader = extractPercentHeader(jSONObject, responseHeader);
        if (extractPercentHeader != null) {
            return extractPercentHeader.toString();
        }
        return null;
    }

    private static boolean formatBooleanHeader(@Nullable String str, boolean z) {
        return str == null ? z : str.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES);
    }

    @Nullable
    private static Integer formatIntHeader(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (Exception unused) {
            NumberFormat instance = NumberFormat.getInstance(Locale.US);
            instance.setParseIntegerOnly(true);
            try {
                return Integer.valueOf(instance.parse(str.trim()).intValue());
            } catch (Exception unused2) {
                return null;
            }
        }
    }

    @Nullable
    private static Integer formatPercentHeader(@Nullable String str) {
        if (str == null) {
            return null;
        }
        Integer formatIntHeader = formatIntHeader(str.replace("%", ""));
        if (formatIntHeader == null || formatIntHeader.intValue() < 0 || formatIntHeader.intValue() > 100) {
            return null;
        }
        return formatIntHeader;
    }
}
