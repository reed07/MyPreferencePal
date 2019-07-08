package com.uacf.core.caching;

public class CacheKeyBuilder {
    public static String getExpirationKeyFrom(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_created_at");
        return sb.toString();
    }
}
