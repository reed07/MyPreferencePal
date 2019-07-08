package com.squareup.okhttp.internal.http;

import com.facebook.appevents.AppEventsConstants;
import com.google.common.net.HttpHeaders;
import com.myfitnesspal.shared.constants.HttpConstants;
import com.squareup.okhttp.Address;
import com.squareup.okhttp.CertificatePinner;
import com.squareup.okhttp.Connection;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Interceptor.Chain;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.Response.Builder;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.Route;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.InternalCache;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.Version;
import com.squareup.okhttp.internal.http.CacheStrategy.Factory;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.CookieHandler;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class HttpEngine {
    private static final ResponseBody EMPTY_BODY = new ResponseBody() {
        public long contentLength() {
            return 0;
        }

        public BufferedSource source() {
            return new Buffer();
        }
    };
    private Address address;
    public final boolean bufferRequestBody;
    private BufferedSink bufferedRequestBody;
    private Response cacheResponse;
    private CacheStrategy cacheStrategy;
    private final boolean callerWritesRequestBody;
    final OkHttpClient client;
    /* access modifiers changed from: private */
    public Connection connection;
    private final boolean forWebSocket;
    /* access modifiers changed from: private */
    public Request networkRequest;
    private final Response priorResponse;
    private Sink requestBodyOut;
    private Route route;
    private RouteSelector routeSelector;
    long sentRequestMillis = -1;
    private CacheRequest storeRequest;
    private boolean transparentGzip;
    /* access modifiers changed from: private */
    public Transport transport;
    private final Request userRequest;
    private Response userResponse;

    class NetworkInterceptorChain implements Chain {
        private int calls;
        private final int index;
        private final Request request;

        NetworkInterceptorChain(int i, Request request2) {
            this.index = i;
            this.request = request2;
        }

        public Connection connection() {
            return HttpEngine.this.connection;
        }

        public Response proceed(Request request2) throws IOException {
            this.calls++;
            if (this.index > 0) {
                Interceptor interceptor = (Interceptor) HttpEngine.this.client.networkInterceptors().get(this.index - 1);
                Address address = connection().getRoute().getAddress();
                if (!request2.httpUrl().host().equals(address.getUriHost()) || request2.httpUrl().port() != address.getUriPort()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("network interceptor ");
                    sb.append(interceptor);
                    sb.append(" must retain the same host and port");
                    throw new IllegalStateException(sb.toString());
                } else if (this.calls > 1) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("network interceptor ");
                    sb2.append(interceptor);
                    sb2.append(" must call proceed() exactly once");
                    throw new IllegalStateException(sb2.toString());
                }
            }
            if (this.index < HttpEngine.this.client.networkInterceptors().size()) {
                NetworkInterceptorChain networkInterceptorChain = new NetworkInterceptorChain(this.index + 1, request2);
                Interceptor interceptor2 = (Interceptor) HttpEngine.this.client.networkInterceptors().get(this.index);
                Response intercept = interceptor2.intercept(networkInterceptorChain);
                if (networkInterceptorChain.calls == 1) {
                    return intercept;
                }
                StringBuilder sb3 = new StringBuilder();
                sb3.append("network interceptor ");
                sb3.append(interceptor2);
                sb3.append(" must call proceed() exactly once");
                throw new IllegalStateException(sb3.toString());
            }
            HttpEngine.this.transport.writeRequestHeaders(request2);
            HttpEngine.this.networkRequest = request2;
            if (HttpEngine.this.permitsRequestBody() && request2.body() != null) {
                BufferedSink buffer = Okio.buffer(HttpEngine.this.transport.createRequestBody(request2, request2.body().contentLength()));
                request2.body().writeTo(buffer);
                buffer.close();
            }
            Response access$300 = HttpEngine.this.readNetworkResponse();
            int code = access$300.code();
            if ((code != 204 && code != 205) || access$300.body().contentLength() <= 0) {
                return access$300;
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append("HTTP ");
            sb4.append(code);
            sb4.append(" had non-zero Content-Length: ");
            sb4.append(access$300.body().contentLength());
            throw new ProtocolException(sb4.toString());
        }
    }

    public HttpEngine(OkHttpClient okHttpClient, Request request, boolean z, boolean z2, boolean z3, Connection connection2, RouteSelector routeSelector2, RetryableSink retryableSink, Response response) {
        this.client = okHttpClient;
        this.userRequest = request;
        this.bufferRequestBody = z;
        this.callerWritesRequestBody = z2;
        this.forWebSocket = z3;
        this.connection = connection2;
        this.routeSelector = routeSelector2;
        this.requestBodyOut = retryableSink;
        this.priorResponse = response;
        if (connection2 != null) {
            Internal.instance.setOwner(connection2, this);
            this.route = connection2.getRoute();
            return;
        }
        this.route = null;
    }

    public void sendRequest() throws RequestException, RouteException, IOException {
        if (this.cacheStrategy == null) {
            if (this.transport == null) {
                Request networkRequest2 = networkRequest(this.userRequest);
                InternalCache internalCache = Internal.instance.internalCache(this.client);
                Response response = internalCache != null ? internalCache.get(networkRequest2) : null;
                this.cacheStrategy = new Factory(System.currentTimeMillis(), networkRequest2, response).get();
                this.networkRequest = this.cacheStrategy.networkRequest;
                this.cacheResponse = this.cacheStrategy.cacheResponse;
                if (internalCache != null) {
                    internalCache.trackResponse(this.cacheStrategy);
                }
                if (response != null && this.cacheResponse == null) {
                    Util.closeQuietly((Closeable) response.body());
                }
                if (this.networkRequest != null) {
                    if (this.connection == null) {
                        connect();
                    }
                    this.transport = Internal.instance.newTransport(this.connection, this);
                    if (this.callerWritesRequestBody && permitsRequestBody() && this.requestBodyOut == null) {
                        long contentLength = OkHeaders.contentLength(networkRequest2);
                        if (!this.bufferRequestBody) {
                            this.transport.writeRequestHeaders(this.networkRequest);
                            this.requestBodyOut = this.transport.createRequestBody(this.networkRequest, contentLength);
                        } else if (contentLength > 2147483647L) {
                            throw new IllegalStateException("Use setFixedLengthStreamingMode() or setChunkedStreamingMode() for requests larger than 2 GiB.");
                        } else if (contentLength != -1) {
                            this.transport.writeRequestHeaders(this.networkRequest);
                            this.requestBodyOut = new RetryableSink((int) contentLength);
                        } else {
                            this.requestBodyOut = new RetryableSink();
                        }
                    }
                } else {
                    if (this.connection != null) {
                        Internal.instance.recycle(this.client.getConnectionPool(), this.connection);
                        this.connection = null;
                    }
                    Response response2 = this.cacheResponse;
                    if (response2 != null) {
                        this.userResponse = response2.newBuilder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).cacheResponse(stripBody(this.cacheResponse)).build();
                    } else {
                        this.userResponse = new Builder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (only-if-cached)").body(EMPTY_BODY).build();
                    }
                    this.userResponse = unzip(this.userResponse);
                }
                return;
            }
            throw new IllegalStateException();
        }
    }

    private static Response stripBody(Response response) {
        return (response == null || response.body() == null) ? response : response.newBuilder().body(null).build();
    }

    private void connect() throws RequestException, RouteException {
        if (this.connection == null) {
            if (this.routeSelector == null) {
                this.address = createAddress(this.client, this.networkRequest);
                try {
                    this.routeSelector = RouteSelector.get(this.address, this.networkRequest, this.client);
                } catch (IOException e) {
                    throw new RequestException(e);
                }
            }
            this.connection = createNextConnection();
            Internal.instance.connectAndSetOwner(this.client, this.connection, this, this.networkRequest);
            this.route = this.connection.getRoute();
            return;
        }
        throw new IllegalStateException();
    }

    private Connection createNextConnection() throws RouteException {
        Connection connection2;
        ConnectionPool connectionPool = this.client.getConnectionPool();
        while (true) {
            connection2 = connectionPool.get(this.address);
            if (connection2 == null) {
                try {
                    return new Connection(connectionPool, this.routeSelector.next());
                } catch (IOException e) {
                    throw new RouteException(e);
                }
            } else if (this.networkRequest.method().equals(HttpConstants.METHOD_GET) || Internal.instance.isReadable(connection2)) {
                return connection2;
            } else {
                Util.closeQuietly(connection2.getSocket());
            }
        }
        return connection2;
    }

    public void writingRequestHeaders() {
        if (this.sentRequestMillis == -1) {
            this.sentRequestMillis = System.currentTimeMillis();
            return;
        }
        throw new IllegalStateException();
    }

    /* access modifiers changed from: 0000 */
    public boolean permitsRequestBody() {
        return HttpMethod.permitsRequestBody(this.userRequest.method());
    }

    public Request getRequest() {
        return this.userRequest;
    }

    public Response getResponse() {
        Response response = this.userResponse;
        if (response != null) {
            return response;
        }
        throw new IllegalStateException();
    }

    public Connection getConnection() {
        return this.connection;
    }

    public HttpEngine recover(RouteException routeException) {
        RouteSelector routeSelector2 = this.routeSelector;
        if (!(routeSelector2 == null || this.connection == null)) {
            connectFailed(routeSelector2, routeException.getLastConnectException());
        }
        if (!(this.routeSelector == null && this.connection == null)) {
            RouteSelector routeSelector3 = this.routeSelector;
            if ((routeSelector3 == null || routeSelector3.hasNext()) && isRecoverable(routeException)) {
                HttpEngine httpEngine = new HttpEngine(this.client, this.userRequest, this.bufferRequestBody, this.callerWritesRequestBody, this.forWebSocket, close(), this.routeSelector, (RetryableSink) this.requestBodyOut, this.priorResponse);
                return httpEngine;
            }
        }
        return null;
    }

    private boolean isRecoverable(RouteException routeException) {
        if (!this.client.getRetryOnConnectionFailure()) {
            return false;
        }
        IOException lastConnectException = routeException.getLastConnectException();
        if ((lastConnectException instanceof ProtocolException) || (lastConnectException instanceof InterruptedIOException)) {
            return false;
        }
        if ((!(lastConnectException instanceof SSLHandshakeException) || !(lastConnectException.getCause() instanceof CertificateException)) && !(lastConnectException instanceof SSLPeerUnverifiedException)) {
            return true;
        }
        return false;
    }

    public HttpEngine recover(IOException iOException, Sink sink) {
        RouteSelector routeSelector2 = this.routeSelector;
        if (!(routeSelector2 == null || this.connection == null)) {
            connectFailed(routeSelector2, iOException);
        }
        boolean z = sink == null || (sink instanceof RetryableSink);
        if (!(this.routeSelector == null && this.connection == null)) {
            RouteSelector routeSelector3 = this.routeSelector;
            if ((routeSelector3 == null || routeSelector3.hasNext()) && isRecoverable(iOException) && z) {
                HttpEngine httpEngine = new HttpEngine(this.client, this.userRequest, this.bufferRequestBody, this.callerWritesRequestBody, this.forWebSocket, close(), this.routeSelector, (RetryableSink) sink, this.priorResponse);
                return httpEngine;
            }
        }
        return null;
    }

    private void connectFailed(RouteSelector routeSelector2, IOException iOException) {
        if (Internal.instance.recycleCount(this.connection) <= 0) {
            routeSelector2.connectFailed(this.connection.getRoute(), iOException);
        }
    }

    private boolean isRecoverable(IOException iOException) {
        if (this.client.getRetryOnConnectionFailure() && !(iOException instanceof ProtocolException) && !(iOException instanceof InterruptedIOException)) {
            return true;
        }
        return false;
    }

    public Route getRoute() {
        return this.route;
    }

    private void maybeCache() throws IOException {
        InternalCache internalCache = Internal.instance.internalCache(this.client);
        if (internalCache != null) {
            if (!CacheStrategy.isCacheable(this.userResponse, this.networkRequest)) {
                if (HttpMethod.invalidatesCache(this.networkRequest.method())) {
                    try {
                        internalCache.remove(this.networkRequest);
                    } catch (IOException unused) {
                    }
                }
                return;
            }
            this.storeRequest = internalCache.put(stripBody(this.userResponse));
        }
    }

    public void releaseConnection() throws IOException {
        Transport transport2 = this.transport;
        if (!(transport2 == null || this.connection == null)) {
            transport2.releaseConnectionOnIdle();
        }
        this.connection = null;
    }

    public Connection close() {
        BufferedSink bufferedSink = this.bufferedRequestBody;
        if (bufferedSink != null) {
            Util.closeQuietly((Closeable) bufferedSink);
        } else {
            Sink sink = this.requestBodyOut;
            if (sink != null) {
                Util.closeQuietly((Closeable) sink);
            }
        }
        Response response = this.userResponse;
        if (response == null) {
            Connection connection2 = this.connection;
            if (connection2 != null) {
                Util.closeQuietly(connection2.getSocket());
            }
            this.connection = null;
            return null;
        }
        Util.closeQuietly((Closeable) response.body());
        Transport transport2 = this.transport;
        if (transport2 == null || this.connection == null || transport2.canReuseConnection()) {
            if (this.connection != null && !Internal.instance.clearOwner(this.connection)) {
                this.connection = null;
            }
            Connection connection3 = this.connection;
            this.connection = null;
            return connection3;
        }
        Util.closeQuietly(this.connection.getSocket());
        this.connection = null;
        return null;
    }

    private Response unzip(Response response) throws IOException {
        if (!this.transparentGzip || !"gzip".equalsIgnoreCase(this.userResponse.header(HttpHeaders.CONTENT_ENCODING)) || response.body() == null) {
            return response;
        }
        GzipSource gzipSource = new GzipSource(response.body().source());
        Headers build = response.headers().newBuilder().removeAll(HttpHeaders.CONTENT_ENCODING).removeAll("Content-Length").build();
        return response.newBuilder().headers(build).body(new RealResponseBody(build, Okio.buffer((Source) gzipSource))).build();
    }

    public static boolean hasBody(Response response) {
        if (response.request().method().equals("HEAD")) {
            return false;
        }
        int code = response.code();
        if (((code >= 100 && code < 200) || code == 204 || code == 304) && OkHeaders.contentLength(response) == -1 && !"chunked".equalsIgnoreCase(response.header(HttpHeaders.TRANSFER_ENCODING))) {
            return false;
        }
        return true;
    }

    private Request networkRequest(Request request) throws IOException {
        Request.Builder newBuilder = request.newBuilder();
        if (request.header(HttpHeaders.HOST) == null) {
            newBuilder.header(HttpHeaders.HOST, Util.hostHeader(request.httpUrl()));
        }
        Connection connection2 = this.connection;
        if ((connection2 == null || connection2.getProtocol() != Protocol.HTTP_1_0) && request.header(HttpHeaders.CONNECTION) == null) {
            newBuilder.header(HttpHeaders.CONNECTION, "Keep-Alive");
        }
        if (request.header(HttpHeaders.ACCEPT_ENCODING) == null) {
            this.transparentGzip = true;
            newBuilder.header(HttpHeaders.ACCEPT_ENCODING, "gzip");
        }
        CookieHandler cookieHandler = this.client.getCookieHandler();
        if (cookieHandler != null) {
            OkHeaders.addCookies(newBuilder, cookieHandler.get(request.uri(), OkHeaders.toMultimap(newBuilder.build().headers(), null)));
        }
        if (request.header("User-Agent") == null) {
            newBuilder.header("User-Agent", Version.userAgent());
        }
        return newBuilder.build();
    }

    public void readResponse() throws IOException {
        Response response;
        if (this.userResponse == null) {
            if (this.networkRequest == null && this.cacheResponse == null) {
                throw new IllegalStateException("call sendRequest() first!");
            }
            Request request = this.networkRequest;
            if (request != null) {
                if (this.forWebSocket) {
                    this.transport.writeRequestHeaders(request);
                    response = readNetworkResponse();
                } else if (!this.callerWritesRequestBody) {
                    response = new NetworkInterceptorChain(0, request).proceed(this.networkRequest);
                } else {
                    BufferedSink bufferedSink = this.bufferedRequestBody;
                    if (bufferedSink != null && bufferedSink.buffer().size() > 0) {
                        this.bufferedRequestBody.emit();
                    }
                    if (this.sentRequestMillis == -1) {
                        if (OkHeaders.contentLength(this.networkRequest) == -1) {
                            Sink sink = this.requestBodyOut;
                            if (sink instanceof RetryableSink) {
                                this.networkRequest = this.networkRequest.newBuilder().header("Content-Length", Long.toString(((RetryableSink) sink).contentLength())).build();
                            }
                        }
                        this.transport.writeRequestHeaders(this.networkRequest);
                    }
                    Sink sink2 = this.requestBodyOut;
                    if (sink2 != null) {
                        BufferedSink bufferedSink2 = this.bufferedRequestBody;
                        if (bufferedSink2 != null) {
                            bufferedSink2.close();
                        } else {
                            sink2.close();
                        }
                        Sink sink3 = this.requestBodyOut;
                        if (sink3 instanceof RetryableSink) {
                            this.transport.writeRequestBody((RetryableSink) sink3);
                        }
                    }
                    response = readNetworkResponse();
                }
                receiveHeaders(response.headers());
                Response response2 = this.cacheResponse;
                if (response2 != null) {
                    if (validate(response2, response)) {
                        this.userResponse = this.cacheResponse.newBuilder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).headers(combine(this.cacheResponse.headers(), response.headers())).cacheResponse(stripBody(this.cacheResponse)).networkResponse(stripBody(response)).build();
                        response.body().close();
                        releaseConnection();
                        InternalCache internalCache = Internal.instance.internalCache(this.client);
                        internalCache.trackConditionalCacheHit();
                        internalCache.update(this.cacheResponse, stripBody(this.userResponse));
                        this.userResponse = unzip(this.userResponse);
                        return;
                    }
                    Util.closeQuietly((Closeable) this.cacheResponse.body());
                }
                this.userResponse = response.newBuilder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).cacheResponse(stripBody(this.cacheResponse)).networkResponse(stripBody(response)).build();
                if (hasBody(this.userResponse)) {
                    maybeCache();
                    this.userResponse = unzip(cacheWritingResponse(this.storeRequest, this.userResponse));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public Response readNetworkResponse() throws IOException {
        this.transport.finishRequest();
        Response build = this.transport.readResponseHeaders().request(this.networkRequest).handshake(this.connection.getHandshake()).header(OkHeaders.SENT_MILLIS, Long.toString(this.sentRequestMillis)).header(OkHeaders.RECEIVED_MILLIS, Long.toString(System.currentTimeMillis())).build();
        if (!this.forWebSocket) {
            build = build.newBuilder().body(this.transport.openResponseBody(build)).build();
        }
        Internal.instance.setProtocol(this.connection, build.protocol());
        return build;
    }

    private Response cacheWritingResponse(final CacheRequest cacheRequest, Response response) throws IOException {
        if (cacheRequest == null) {
            return response;
        }
        Sink body = cacheRequest.body();
        if (body == null) {
            return response;
        }
        final BufferedSource source = response.body().source();
        final BufferedSink buffer = Okio.buffer(body);
        return response.newBuilder().body(new RealResponseBody(response.headers(), Okio.buffer((Source) new Source() {
            boolean cacheRequestClosed;

            public long read(Buffer buffer, long j) throws IOException {
                try {
                    long read = source.read(buffer, j);
                    if (read == -1) {
                        if (!this.cacheRequestClosed) {
                            this.cacheRequestClosed = true;
                            buffer.close();
                        }
                        return -1;
                    }
                    buffer.copyTo(buffer.buffer(), buffer.size() - read, read);
                    buffer.emitCompleteSegments();
                    return read;
                } catch (IOException e) {
                    if (!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        cacheRequest.abort();
                    }
                    throw e;
                }
            }

            public Timeout timeout() {
                return source.timeout();
            }

            public void close() throws IOException {
                if (!this.cacheRequestClosed && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    this.cacheRequestClosed = true;
                    cacheRequest.abort();
                }
                source.close();
            }
        }))).build();
    }

    private static boolean validate(Response response, Response response2) {
        if (response2.code() == 304) {
            return true;
        }
        Date date = response.headers().getDate(HttpHeaders.LAST_MODIFIED);
        if (date != null) {
            Date date2 = response2.headers().getDate(HttpHeaders.LAST_MODIFIED);
            if (date2 != null && date2.getTime() < date.getTime()) {
                return true;
            }
        }
        return false;
    }

    private static Headers combine(Headers headers, Headers headers2) throws IOException {
        Headers.Builder builder = new Headers.Builder();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String name = headers.name(i);
            String value = headers.value(i);
            if ((!HttpHeaders.WARNING.equalsIgnoreCase(name) || !value.startsWith(AppEventsConstants.EVENT_PARAM_VALUE_YES)) && (!OkHeaders.isEndToEnd(name) || headers2.get(name) == null)) {
                builder.add(name, value);
            }
        }
        int size2 = headers2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            String name2 = headers2.name(i2);
            if (!"Content-Length".equalsIgnoreCase(name2) && OkHeaders.isEndToEnd(name2)) {
                builder.add(name2, headers2.value(i2));
            }
        }
        return builder.build();
    }

    public void receiveHeaders(Headers headers) throws IOException {
        CookieHandler cookieHandler = this.client.getCookieHandler();
        if (cookieHandler != null) {
            cookieHandler.put(this.userRequest.uri(), OkHeaders.toMultimap(headers, null));
        }
    }

    public Request followUpRequest() throws IOException {
        Proxy proxy;
        if (this.userResponse != null) {
            if (getRoute() != null) {
                proxy = getRoute().getProxy();
            } else {
                proxy = this.client.getProxy();
            }
            int code = this.userResponse.code();
            if (code != 401) {
                if (code != 407) {
                    switch (code) {
                        case NOTICE_VALUE:
                        case 301:
                        case 302:
                        case 303:
                            break;
                        default:
                            switch (code) {
                                case 307:
                                case 308:
                                    if (!this.userRequest.method().equals(HttpConstants.METHOD_GET) && !this.userRequest.method().equals("HEAD")) {
                                        return null;
                                    }
                                default:
                                    return null;
                            }
                    }
                    if (!this.client.getFollowRedirects()) {
                        return null;
                    }
                    String header = this.userResponse.header(HttpHeaders.LOCATION);
                    if (header == null) {
                        return null;
                    }
                    HttpUrl resolve = this.userRequest.httpUrl().resolve(header);
                    if (resolve == null) {
                        return null;
                    }
                    if (!resolve.scheme().equals(this.userRequest.httpUrl().scheme()) && !this.client.getFollowSslRedirects()) {
                        return null;
                    }
                    Request.Builder newBuilder = this.userRequest.newBuilder();
                    if (HttpMethod.permitsRequestBody(this.userRequest.method())) {
                        newBuilder.method(HttpConstants.METHOD_GET, null);
                        newBuilder.removeHeader(HttpHeaders.TRANSFER_ENCODING);
                        newBuilder.removeHeader("Content-Length");
                        newBuilder.removeHeader("Content-Type");
                    }
                    if (!sameConnection(resolve)) {
                        newBuilder.removeHeader("Authorization");
                    }
                    return newBuilder.url(resolve).build();
                } else if (proxy.type() != Type.HTTP) {
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                }
            }
            return OkHeaders.processAuthHeader(this.client.getAuthenticator(), this.userResponse, proxy);
        }
        throw new IllegalStateException();
    }

    public boolean sameConnection(HttpUrl httpUrl) {
        HttpUrl httpUrl2 = this.userRequest.httpUrl();
        return httpUrl2.host().equals(httpUrl.host()) && httpUrl2.port() == httpUrl.port() && httpUrl2.scheme().equals(httpUrl.scheme());
    }

    private static Address createAddress(OkHttpClient okHttpClient, Request request) {
        CertificatePinner certificatePinner;
        HostnameVerifier hostnameVerifier;
        SSLSocketFactory sSLSocketFactory;
        if (request.isHttps()) {
            SSLSocketFactory sslSocketFactory = okHttpClient.getSslSocketFactory();
            hostnameVerifier = okHttpClient.getHostnameVerifier();
            sSLSocketFactory = sslSocketFactory;
            certificatePinner = okHttpClient.getCertificatePinner();
        } else {
            sSLSocketFactory = null;
            hostnameVerifier = null;
            certificatePinner = null;
        }
        Address address2 = new Address(request.httpUrl().host(), request.httpUrl().port(), okHttpClient.getSocketFactory(), sSLSocketFactory, hostnameVerifier, certificatePinner, okHttpClient.getAuthenticator(), okHttpClient.getProxy(), okHttpClient.getProtocols(), okHttpClient.getConnectionSpecs(), okHttpClient.getProxySelector());
        return address2;
    }
}
