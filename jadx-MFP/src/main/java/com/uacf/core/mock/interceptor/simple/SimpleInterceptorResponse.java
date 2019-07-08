package com.uacf.core.mock.interceptor.simple;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uacf.core.mock.interceptor.InterceptorResponse;
import java.util.HashMap;
import java.util.Map;

public final class SimpleInterceptorResponse implements InterceptorResponse {
    private String body;
    @SerializedName("body")
    @Expose
    private JsonElement bodyElement;
    @Expose
    private Map<String, String> headers = new HashMap();
    @Expose
    private Integer statusCode;

    public SimpleInterceptorResponse() {
    }

    public SimpleInterceptorResponse(String str, int i, Map<String, String> map) {
        this.body = str;
        this.statusCode = Integer.valueOf(i);
        this.headers = map;
    }

    public String getBody() {
        JsonElement jsonElement = this.bodyElement;
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return this.body;
        }
        return this.bodyElement.toString();
    }

    public byte[] getBytes() {
        return getBody().getBytes();
    }

    public int getStatusCode() {
        return this.statusCode.intValue();
    }

    public Map<String, String> getHeaders() {
        Map<String, String> map = this.headers;
        return map == null ? new HashMap() : map;
    }
}
