package com.uacf.core.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class MapUtil {

    public static class Builder<K, V> {
        private Map<K, V> map = new HashMap();

        public Builder() {
        }

        public Builder(Map<K, V> map2) {
            this.map.putAll(map2);
        }

        public Builder<K, V> put(K k, V v) {
            this.map.put(k, v);
            return this;
        }

        public Builder<K, V> putAll(Map<K, V> map2) {
            this.map.putAll(map2);
            return this;
        }

        public Map<K, V> build() {
            return new HashMap(this.map);
        }
    }

    public static Map<String, String> createMap(String str, String str2, String str3) {
        String[] split;
        Builder builder = new Builder();
        if (Strings.notEmpty(str) && Strings.notEmpty(str2) && Strings.notEmpty(str3)) {
            for (String str4 : str.split(str3)) {
                if (!Strings.isEmpty(str4)) {
                    String[] split2 = str4.split(str2);
                    if (!Strings.isEmpty(split2[0])) {
                        builder.put(split2[0], split2.length > 1 ? Strings.toString(split2[1]) : "");
                    }
                }
            }
        }
        return builder.build();
    }

    public static Map<String, String> createMap(String... strArr) {
        if (strArr.length % 2 == 0) {
            Builder builder = new Builder();
            for (int i = 0; i < strArr.length; i += 2) {
                builder.put(strArr[i], strArr[i + 1]);
            }
            return builder.build();
        }
        throw new RuntimeException("Name value pairs must be a length of equal to a even number.");
    }

    public static Map<String, String> mergeMap(Map<String, String> map, String... strArr) {
        if (strArr.length % 2 == 0) {
            for (int i = 0; i < strArr.length; i += 2) {
                map.put(strArr[i], strArr[i + 1]);
            }
            return map;
        }
        throw new RuntimeException("Name value pairs must be a length of equal to a even number.");
    }

    public static <K, V> Map<K, V> newLruHashMap(final int i) {
        return new LinkedHashMap<K, V>(i) {
            /* access modifiers changed from: protected */
            public boolean removeEldestEntry(Entry<K, V> entry) {
                return size() > i;
            }
        };
    }
}
