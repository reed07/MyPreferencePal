package com.uacf.core.mock.interceptor.simple;

import java.net.URL;
import java.util.regex.Pattern;

public final class SimpleInterceptorRequest {
    private String body;
    private String method;
    private String query;
    private Pattern uriPathPattern;
    private String urlPath;

    public SimpleInterceptorRequest(String str, String str2, String str3, String str4) {
        this.method = str;
        this.query = str3;
        this.body = str4;
        StringBuilder sb = new StringBuilder();
        sb.append(!str2.startsWith("/") ? "/" : "");
        sb.append(str2);
        this.urlPath = sb.toString();
        try {
            this.urlPath = new URL(this.urlPath).getPath();
        } catch (Exception unused) {
        }
        this.uriPathPattern = Pattern.compile(this.urlPath, 34);
    }

    public String getMethod() {
        return this.method;
    }

    public String getUrlPath() {
        return this.urlPath;
    }

    public String getQuery() {
        return this.query;
    }

    public String getBody() {
        return this.body;
    }

    public Pattern getUriPathPattern() {
        return this.uriPathPattern;
    }
}
