package com.squareup.okhttp.internal.http;

import com.myfitnesspal.shared.constants.HttpConstants;

public final class HttpMethod {
    public static boolean invalidatesCache(String str) {
        return str.equals(HttpConstants.METHOD_POST) || str.equals("PATCH") || str.equals(HttpConstants.METHOD_PUT) || str.equals(HttpConstants.METHOD_DELETE);
    }

    public static boolean requiresRequestBody(String str) {
        return str.equals(HttpConstants.METHOD_POST) || str.equals(HttpConstants.METHOD_PUT) || str.equals("PATCH");
    }

    public static boolean permitsRequestBody(String str) {
        return requiresRequestBody(str) || str.equals(HttpConstants.METHOD_DELETE);
    }

    private HttpMethod() {
    }
}
