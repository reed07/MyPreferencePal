package com.facebook.ads.internal.adapters.b;

public enum p {
    WEBVIEW_PRECACHE,
    PROXY_PRECACHE,
    FILE_PRECACHE;

    public static p a(String str) {
        try {
            return valueOf(str);
        } catch (IllegalArgumentException unused) {
            return FILE_PRECACHE;
        }
    }
}
