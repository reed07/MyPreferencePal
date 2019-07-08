package com.uacf.core.mock.interceptor;

import java.util.Map;

public interface InterceptorResponse {
    String getBody();

    byte[] getBytes();

    Map<String, String> getHeaders();

    int getStatusCode();
}
