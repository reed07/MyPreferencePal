package io.grpc.okhttp.internal.framed;

public enum HeadersMode {
    SPDY_SYN_STREAM,
    SPDY_REPLY,
    SPDY_HEADERS,
    HTTP_20_HEADERS
}
