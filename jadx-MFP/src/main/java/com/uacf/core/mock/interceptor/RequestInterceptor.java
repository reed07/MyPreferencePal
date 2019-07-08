package com.uacf.core.mock.interceptor;

public interface RequestInterceptor {
    InterceptorResponse getResponse(String str, String str2, String str3, String str4);

    void reset();
}
