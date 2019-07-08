package com.uacf.core.mock.interceptor.legacy;

import com.google.gson.annotations.Expose;
import com.uacf.core.mock.interceptor.InterceptorResponse;
import com.uacf.core.util.Strings;
import java.util.Map;

public final class FileInterceptorResponse implements InterceptorResponse {
    @Expose
    public FileContent content;
    @Expose
    public int count;
    public int sent;
    @Expose
    public int statusCode;

    public Map<String, String> getHeaders() {
        return this.content.getHeaders();
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public byte[] getBytes() {
        return this.content.asBytes();
    }

    public String getBody() {
        return Strings.toString(this.content.asString());
    }
}
