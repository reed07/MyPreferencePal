package com.squareup.okhttp.internal.http;

import com.myfitnesspal.shared.constants.Constants.ABTest.ExplorePremiumCopyTest201610;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.Response.Builder;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.framed.FramedConnection;
import com.squareup.okhttp.internal.framed.FramedStream;
import com.squareup.okhttp.internal.framed.Header;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okio.ByteString;
import okio.Okio;
import okio.Sink;

public final class FramedTransport implements Transport {
    private static final List<ByteString> HTTP_2_PROHIBITED_HEADERS = Util.immutableList((T[]) new ByteString[]{ByteString.encodeUtf8("connection"), ByteString.encodeUtf8("host"), ByteString.encodeUtf8("keep-alive"), ByteString.encodeUtf8("proxy-connection"), ByteString.encodeUtf8("te"), ByteString.encodeUtf8("transfer-encoding"), ByteString.encodeUtf8("encoding"), ByteString.encodeUtf8(ExplorePremiumCopyTest201610.UPGRADE)});
    private static final List<ByteString> SPDY_3_PROHIBITED_HEADERS = Util.immutableList((T[]) new ByteString[]{ByteString.encodeUtf8("connection"), ByteString.encodeUtf8("host"), ByteString.encodeUtf8("keep-alive"), ByteString.encodeUtf8("proxy-connection"), ByteString.encodeUtf8("transfer-encoding")});
    private final FramedConnection framedConnection;
    private final HttpEngine httpEngine;
    private FramedStream stream;

    public boolean canReuseConnection() {
        return true;
    }

    public void releaseConnectionOnIdle() {
    }

    public FramedTransport(HttpEngine httpEngine2, FramedConnection framedConnection2) {
        this.httpEngine = httpEngine2;
        this.framedConnection = framedConnection2;
    }

    public Sink createRequestBody(Request request, long j) throws IOException {
        return this.stream.getSink();
    }

    public void writeRequestHeaders(Request request) throws IOException {
        if (this.stream == null) {
            this.httpEngine.writingRequestHeaders();
            boolean permitsRequestBody = this.httpEngine.permitsRequestBody();
            String version = RequestLine.version(this.httpEngine.getConnection().getProtocol());
            FramedConnection framedConnection2 = this.framedConnection;
            this.stream = framedConnection2.newStream(writeNameValueBlock(request, framedConnection2.getProtocol(), version), permitsRequestBody, true);
            this.stream.readTimeout().timeout((long) this.httpEngine.client.getReadTimeout(), TimeUnit.MILLISECONDS);
        }
    }

    public void writeRequestBody(RetryableSink retryableSink) throws IOException {
        retryableSink.writeToSocket(this.stream.getSink());
    }

    public void finishRequest() throws IOException {
        this.stream.getSink().close();
    }

    public Builder readResponseHeaders() throws IOException {
        return readNameValueBlock(this.stream.getResponseHeaders(), this.framedConnection.getProtocol());
    }

    public static List<Header> writeNameValueBlock(Request request, Protocol protocol, String str) {
        Headers headers = request.headers();
        ArrayList arrayList = new ArrayList(headers.size() + 10);
        arrayList.add(new Header(Header.TARGET_METHOD, request.method()));
        arrayList.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(request.httpUrl())));
        String hostHeader = Util.hostHeader(request.httpUrl());
        if (Protocol.SPDY_3 == protocol) {
            arrayList.add(new Header(Header.VERSION, str));
            arrayList.add(new Header(Header.TARGET_HOST, hostHeader));
        } else if (Protocol.HTTP_2 == protocol) {
            arrayList.add(new Header(Header.TARGET_AUTHORITY, hostHeader));
        } else {
            throw new AssertionError();
        }
        arrayList.add(new Header(Header.TARGET_SCHEME, request.httpUrl().scheme()));
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            ByteString encodeUtf8 = ByteString.encodeUtf8(headers.name(i).toLowerCase(Locale.US));
            String value = headers.value(i);
            if (!isProhibitedHeader(protocol, encodeUtf8) && !encodeUtf8.equals(Header.TARGET_METHOD) && !encodeUtf8.equals(Header.TARGET_PATH) && !encodeUtf8.equals(Header.TARGET_SCHEME) && !encodeUtf8.equals(Header.TARGET_AUTHORITY) && !encodeUtf8.equals(Header.TARGET_HOST) && !encodeUtf8.equals(Header.VERSION)) {
                if (linkedHashSet.add(encodeUtf8)) {
                    arrayList.add(new Header(encodeUtf8, value));
                } else {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= arrayList.size()) {
                            break;
                        } else if (((Header) arrayList.get(i2)).name.equals(encodeUtf8)) {
                            arrayList.set(i2, new Header(encodeUtf8, joinOnNull(((Header) arrayList.get(i2)).value.utf8(), value)));
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private static String joinOnNull(String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(0);
        sb.append(str2);
        return sb.toString();
    }

    public static Builder readNameValueBlock(List<Header> list, Protocol protocol) throws IOException {
        Headers.Builder builder = new Headers.Builder();
        builder.set(OkHeaders.SELECTED_PROTOCOL, protocol.toString());
        int size = list.size();
        String str = null;
        String str2 = "HTTP/1.1";
        int i = 0;
        while (i < size) {
            ByteString byteString = ((Header) list.get(i)).name;
            String utf8 = ((Header) list.get(i)).value.utf8();
            String str3 = str2;
            String str4 = str;
            int i2 = 0;
            while (i2 < utf8.length()) {
                int indexOf = utf8.indexOf(0, i2);
                if (indexOf == -1) {
                    indexOf = utf8.length();
                }
                String substring = utf8.substring(i2, indexOf);
                if (byteString.equals(Header.RESPONSE_STATUS)) {
                    str4 = substring;
                } else if (byteString.equals(Header.VERSION)) {
                    str3 = substring;
                } else if (!isProhibitedHeader(protocol, byteString)) {
                    builder.add(byteString.utf8(), substring);
                }
                i2 = indexOf + 1;
            }
            i++;
            str = str4;
            str2 = str3;
        }
        if (str != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(" ");
            sb.append(str);
            StatusLine parse = StatusLine.parse(sb.toString());
            return new Builder().protocol(protocol).code(parse.code).message(parse.message).headers(builder.build());
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    public ResponseBody openResponseBody(Response response) throws IOException {
        return new RealResponseBody(response.headers(), Okio.buffer(this.stream.getSource()));
    }

    private static boolean isProhibitedHeader(Protocol protocol, ByteString byteString) {
        if (protocol == Protocol.SPDY_3) {
            return SPDY_3_PROHIBITED_HEADERS.contains(byteString);
        }
        if (protocol == Protocol.HTTP_2) {
            return HTTP_2_PROHIBITED_HEADERS.contains(byteString);
        }
        throw new AssertionError(protocol);
    }
}
