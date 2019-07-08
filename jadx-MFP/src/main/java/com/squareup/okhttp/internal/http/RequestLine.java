package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import java.net.Proxy.Type;

public final class RequestLine {
    private RequestLine() {
    }

    static String get(Request request, Type type, Protocol protocol) {
        StringBuilder sb = new StringBuilder();
        sb.append(request.method());
        sb.append(' ');
        if (includeAuthorityInRequestLine(request, type)) {
            sb.append(request.httpUrl());
        } else {
            sb.append(requestPath(request.httpUrl()));
        }
        sb.append(' ');
        sb.append(version(protocol));
        return sb.toString();
    }

    private static boolean includeAuthorityInRequestLine(Request request, Type type) {
        return !request.isHttps() && type == Type.HTTP;
    }

    public static String requestPath(HttpUrl httpUrl) {
        String encodedPath = httpUrl.encodedPath();
        String encodedQuery = httpUrl.encodedQuery();
        if (encodedQuery == null) {
            return encodedPath;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(encodedPath);
        sb.append('?');
        sb.append(encodedQuery);
        return sb.toString();
    }

    public static String version(Protocol protocol) {
        return protocol == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1";
    }
}
