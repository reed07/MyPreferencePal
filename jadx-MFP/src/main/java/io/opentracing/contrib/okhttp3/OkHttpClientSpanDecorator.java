package io.opentracing.contrib.okhttp3;

import io.opentracing.Span;
import io.opentracing.tag.Tags;
import java.net.Inet4Address;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Connection;
import okhttp3.Request;
import okhttp3.Response;

public interface OkHttpClientSpanDecorator {
    public static final OkHttpClientSpanDecorator STANDARD_TAGS = new OkHttpClientSpanDecorator() {
        public void onRequest(Request request, Span span) {
            Tags.COMPONENT.set(span, "okhttp");
            Tags.HTTP_METHOD.set(span, request.method());
            Tags.HTTP_URL.set(span, request.url().toString());
        }

        public void onError(Throwable th, Span span) {
            Tags.ERROR.set(span, Boolean.TRUE);
            span.log(errorLogs(th));
        }

        public void onResponse(Connection connection, Response response, Span span) {
            Tags.HTTP_STATUS.set(span, Integer.valueOf(response.code()));
            Tags.PEER_HOSTNAME.set(span, connection.socket().getInetAddress().getHostName());
            Tags.PEER_PORT.set(span, Integer.valueOf(connection.socket().getPort()));
            if (connection.socket().getInetAddress() instanceof Inet4Address) {
                Tags.PEER_HOST_IPV4.set(span, Integer.valueOf(ByteBuffer.wrap(connection.socket().getInetAddress().getAddress()).getInt()));
                return;
            }
            Tags.PEER_HOST_IPV6.set(span, connection.socket().getInetAddress().toString());
        }

        /* access modifiers changed from: protected */
        public Map<String, Object> errorLogs(Throwable th) {
            HashMap hashMap = new HashMap(2);
            hashMap.put("event", Tags.ERROR.getKey());
            hashMap.put("error.object", th);
            return hashMap;
        }
    };

    void onError(Throwable th, Span span);

    void onRequest(Request request, Span span);

    void onResponse(Connection connection, Response response, Span span);
}
