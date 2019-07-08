package com.uacf.core.util;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class UriUtils {
    public static Bundle getQueryParams(Uri uri) {
        if (uri == null) {
            return new Bundle();
        }
        Bundle urlDecode = urlDecode(uri.getQuery());
        urlDecode.putAll(urlDecode(uri.getFragment()));
        return urlDecode;
    }

    public static Bundle getQueryParams(String str) {
        return getQueryParams(Uri.parse(str.replaceFirst(".*:\\/\\/", "http://")));
    }

    public static Uri appendAllQueryParams(Uri uri, Bundle bundle) {
        return appendAllQueryParams(uri.buildUpon(), bundle).build();
    }

    public static Builder appendAllQueryParams(Builder builder, Bundle bundle) {
        if (builder != null) {
            for (String str : bundle.keySet()) {
                builder.appendQueryParameter(str, Strings.toString(bundle.get(str)));
            }
        }
        return builder;
    }

    public static Bundle urlDecode(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String split : str.split("&")) {
                String[] split2 = split.split("=");
                if (split2.length == 2) {
                    bundle.putString(URLDecoder.decode(split2[0]), URLDecoder.decode(split2[1]));
                }
            }
        }
        return bundle;
    }

    public static Set<String> getQueryParameterNames(Uri uri) {
        if (!uri.isOpaque()) {
            String encodedQuery = uri.getEncodedQuery();
            if (encodedQuery == null) {
                return Collections.emptySet();
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int i = 0;
            do {
                int indexOf = encodedQuery.indexOf(38, i);
                if (indexOf == -1) {
                    indexOf = encodedQuery.length();
                }
                int indexOf2 = encodedQuery.indexOf(61, i);
                if (indexOf2 > indexOf || indexOf2 == -1) {
                    indexOf2 = indexOf;
                }
                linkedHashSet.add(Uri.decode(encodedQuery.substring(i, indexOf2)));
                i = indexOf + 1;
            } while (i < encodedQuery.length());
            return Collections.unmodifiableSet(linkedHashSet);
        }
        throw new UnsupportedOperationException("This isn't a hierarchical URI.");
    }

    public static Map<String, String> queryStringToMap(String str) {
        HashMap hashMap = new HashMap();
        if (Strings.notEmpty(str)) {
            for (String split : str.split("&")) {
                String[] split2 = split.split("=");
                if (split2.length == 2) {
                    hashMap.put(split2[0], split2[1]);
                }
            }
        }
        return hashMap;
    }

    public static String mapToQueryString(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : map.keySet()) {
            if (!z) {
                sb.append("&");
            }
            z = false;
            sb.append(URLEncoder.encode(str));
            sb.append("=");
            sb.append(URLEncoder.encode((String) map.get(str)));
        }
        return sb.toString();
    }

    public static String removeQueryParams(String str) {
        return Uri.parse(str).buildUpon().clearQuery().toString();
    }

    public static String encodeBrackets(String str) {
        if (str != null) {
            return str.replace("[", "%5B").replace("]", "%5D");
        }
        return null;
    }

    public static String getHeaderValue(Map<String, List<String>> map, String str) {
        if (map != null) {
            List list = (List) map.get(str);
            if (CollectionUtils.notEmpty((Collection<?>) list)) {
                return (String) list.get(0);
            }
        }
        return null;
    }
}
