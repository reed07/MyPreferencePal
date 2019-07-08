package com.mopub.network;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class MoPubRequestUtils {
    public static String truncateQueryParamsIfPost(@NonNull String str) {
        Preconditions.checkNotNull(str);
        if (!isMoPubRequest(str)) {
            return str;
        }
        int indexOf = str.indexOf(63);
        if (indexOf == -1) {
            return str;
        }
        return str.substring(0, indexOf);
    }

    public static boolean isMoPubRequest(@NonNull String str) {
        Preconditions.checkNotNull(str);
        return str.startsWith("http://ads.mopub.com") || str.startsWith("https://ads.mopub.com");
    }

    public static int chooseMethod(String str) {
        return isMoPubRequest(str) ? 1 : 0;
    }

    @NonNull
    public static Map<String, String> convertQueryToMap(@NonNull Context context, @NonNull String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        HashMap hashMap = new HashMap();
        Uri parse = Uri.parse(Networking.getUrlRewriter(context).rewriteUrl(str));
        for (String str2 : parse.getQueryParameterNames()) {
            hashMap.put(str2, TextUtils.join(",", parse.getQueryParameters(str2)));
        }
        return hashMap;
    }

    @Nullable
    public static String generateBodyFromParams(@Nullable Map<String, String> map, @NonNull String str) {
        Preconditions.checkNotNull(str);
        if (!isMoPubRequest(str) || map == null || map.isEmpty()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str2 : map.keySet()) {
            try {
                jSONObject.put(str2, map.get(str2));
            } catch (JSONException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to add ");
                sb.append(str2);
                sb.append(" to JSON body.");
                MoPubLog.d(sb.toString());
            }
        }
        return jSONObject.toString();
    }

    private MoPubRequestUtils() {
    }
}
