package com.squareup.okhttp;

import com.google.common.net.HttpHeaders;
import com.mopub.common.Constants;
import com.squareup.okhttp.internal.ConnectionSpecSelector;
import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.framed.FramedConnection;
import com.squareup.okhttp.internal.framed.FramedConnection.Builder;
import com.squareup.okhttp.internal.http.FramedTransport;
import com.squareup.okhttp.internal.http.HttpConnection;
import com.squareup.okhttp.internal.http.HttpEngine;
import com.squareup.okhttp.internal.http.HttpTransport;
import com.squareup.okhttp.internal.http.OkHeaders;
import com.squareup.okhttp.internal.http.RouteException;
import com.squareup.okhttp.internal.http.Transport;
import java.io.IOException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.UnknownServiceException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okio.Source;

public final class Connection {
    private boolean connected = false;
    private FramedConnection framedConnection;
    private Handshake handshake;
    private HttpConnection httpConnection;
    private long idleStartTimeNs;
    private Object owner;
    private final ConnectionPool pool;
    private Protocol protocol = Protocol.HTTP_1_1;
    private int recycleCount;
    private final Route route;
    private Socket socket;

    public Connection(ConnectionPool connectionPool, Route route2) {
        this.pool = connectionPool;
        this.route = route2;
    }

    /* access modifiers changed from: 0000 */
    public void setOwner(Object obj) {
        if (!isFramed()) {
            synchronized (this.pool) {
                if (this.owner == null) {
                    this.owner = obj;
                } else {
                    throw new IllegalStateException("Connection already has an owner!");
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean clearOwner() {
        synchronized (this.pool) {
            if (this.owner == null) {
                return false;
            }
            this.owner = null;
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public void connect(int i, int i2, int i3, Request request, List<ConnectionSpec> list, boolean z) throws RouteException {
        Socket socket2;
        List<ConnectionSpec> list2 = list;
        if (!this.connected) {
            ConnectionSpecSelector connectionSpecSelector = new ConnectionSpecSelector(list2);
            Proxy proxy = this.route.getProxy();
            Address address = this.route.getAddress();
            if (this.route.address.getSslSocketFactory() != null || list2.contains(ConnectionSpec.CLEARTEXT)) {
                RouteException routeException = null;
                while (!this.connected) {
                    try {
                        if (proxy.type() != Type.DIRECT) {
                            if (proxy.type() != Type.HTTP) {
                                socket2 = new Socket(proxy);
                                this.socket = socket2;
                                connectSocket(i, i2, i3, request, connectionSpecSelector);
                                this.connected = true;
                            }
                        }
                        socket2 = address.getSocketFactory().createSocket();
                        this.socket = socket2;
                        connectSocket(i, i2, i3, request, connectionSpecSelector);
                        this.connected = true;
                    } catch (IOException e) {
                        Util.closeQuietly(this.socket);
                        this.socket = null;
                        if (routeException == null) {
                            routeException = new RouteException(e);
                        } else {
                            routeException.addConnectException(e);
                        }
                        if (!z || !connectionSpecSelector.connectionFailed(e)) {
                            throw routeException;
                        }
                    }
                }
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("CLEARTEXT communication not supported: ");
            sb.append(list2);
            throw new RouteException(new UnknownServiceException(sb.toString()));
        }
        throw new IllegalStateException("already connected");
    }

    private void connectSocket(int i, int i2, int i3, Request request, ConnectionSpecSelector connectionSpecSelector) throws IOException {
        this.socket.setSoTimeout(i2);
        Platform.get().connectSocket(this.socket, this.route.getSocketAddress(), i);
        if (this.route.address.getSslSocketFactory() != null) {
            connectTls(i2, i3, request, connectionSpecSelector);
        }
        if (this.protocol == Protocol.SPDY_3 || this.protocol == Protocol.HTTP_2) {
            this.socket.setSoTimeout(0);
            this.framedConnection = new Builder(this.route.address.uriHost, true, this.socket).protocol(this.protocol).build();
            this.framedConnection.sendConnectionPreface();
            return;
        }
        this.httpConnection = new HttpConnection(this.pool, this, this.socket);
    }

    /* JADX WARNING: type inference failed for: r6v2, types: [java.net.Socket, javax.net.ssl.SSLSocket] */
    /* JADX WARNING: type inference failed for: r7v3 */
    /* JADX WARNING: type inference failed for: r6v5 */
    /* JADX WARNING: type inference failed for: r7v4 */
    /* JADX WARNING: type inference failed for: r7v10 */
    /* JADX WARNING: type inference failed for: r6v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00f5 A[Catch:{ all -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00fb A[Catch:{ all -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00fe  */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void connectTls(int r5, int r6, com.squareup.okhttp.Request r7, com.squareup.okhttp.internal.ConnectionSpecSelector r8) throws java.io.IOException {
        /*
            r4 = this;
            com.squareup.okhttp.Route r0 = r4.route
            boolean r0 = r0.requiresTunnel()
            if (r0 == 0) goto L_0x000b
            r4.createTunnel(r5, r6, r7)
        L_0x000b:
            com.squareup.okhttp.Route r5 = r4.route
            com.squareup.okhttp.Address r5 = r5.getAddress()
            javax.net.ssl.SSLSocketFactory r6 = r5.getSslSocketFactory()
            r7 = 0
            java.net.Socket r0 = r4.socket     // Catch:{ AssertionError -> 0x00ee }
            java.lang.String r1 = r5.getUriHost()     // Catch:{ AssertionError -> 0x00ee }
            int r2 = r5.getUriPort()     // Catch:{ AssertionError -> 0x00ee }
            r3 = 1
            java.net.Socket r6 = r6.createSocket(r0, r1, r2, r3)     // Catch:{ AssertionError -> 0x00ee }
            javax.net.ssl.SSLSocket r6 = (javax.net.ssl.SSLSocket) r6     // Catch:{ AssertionError -> 0x00ee }
            com.squareup.okhttp.ConnectionSpec r8 = r8.configureSecureSocket(r6)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            boolean r0 = r8.supportsTlsExtensions()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            if (r0 == 0) goto L_0x0040
            com.squareup.okhttp.internal.Platform r0 = com.squareup.okhttp.internal.Platform.get()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r1 = r5.getUriHost()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.util.List r2 = r5.getProtocols()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r0.configureTlsExtensions(r6, r1, r2)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
        L_0x0040:
            r6.startHandshake()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            javax.net.ssl.SSLSession r0 = r6.getSession()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            com.squareup.okhttp.Handshake r0 = com.squareup.okhttp.Handshake.get(r0)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            javax.net.ssl.HostnameVerifier r1 = r5.getHostnameVerifier()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r2 = r5.getUriHost()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            javax.net.ssl.SSLSession r3 = r6.getSession()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            boolean r1 = r1.verify(r2, r3)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            if (r1 == 0) goto L_0x0093
            com.squareup.okhttp.CertificatePinner r1 = r5.getCertificatePinner()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r5 = r5.getUriHost()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.util.List r2 = r0.peerCertificates()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r1.check(r5, r2)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            boolean r5 = r8.supportsTlsExtensions()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            if (r5 == 0) goto L_0x007a
            com.squareup.okhttp.internal.Platform r5 = com.squareup.okhttp.internal.Platform.get()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r7 = r5.getSelectedProtocol(r6)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
        L_0x007a:
            if (r7 == 0) goto L_0x0081
            com.squareup.okhttp.Protocol r5 = com.squareup.okhttp.Protocol.get(r7)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            goto L_0x0083
        L_0x0081:
            com.squareup.okhttp.Protocol r5 = com.squareup.okhttp.Protocol.HTTP_1_1     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
        L_0x0083:
            r4.protocol = r5     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r4.handshake = r0     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r4.socket = r6     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            if (r6 == 0) goto L_0x0092
            com.squareup.okhttp.internal.Platform r5 = com.squareup.okhttp.internal.Platform.get()
            r5.afterHandshake(r6)
        L_0x0092:
            return
        L_0x0093:
            java.util.List r7 = r0.peerCertificates()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r8 = 0
            java.lang.Object r7 = r7.get(r8)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.security.cert.X509Certificate r7 = (java.security.cert.X509Certificate) r7     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            javax.net.ssl.SSLPeerUnverifiedException r8 = new javax.net.ssl.SSLPeerUnverifiedException     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r0.<init>()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r1 = "Hostname "
            r0.append(r1)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r5 = r5.getUriHost()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r0.append(r5)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r5 = " not verified:"
            r0.append(r5)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r5 = "\n    certificate: "
            r0.append(r5)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r5 = com.squareup.okhttp.CertificatePinner.pin(r7)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r0.append(r5)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r5 = "\n    DN: "
            r0.append(r5)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.security.Principal r5 = r7.getSubjectDN()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r5 = r5.getName()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r0.append(r5)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r5 = "\n    subjectAltNames: "
            r0.append(r5)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.util.List r5 = com.squareup.okhttp.internal.tls.OkHostnameVerifier.allSubjectAltNames(r7)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r0.append(r5)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            java.lang.String r5 = r0.toString()     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            r8.<init>(r5)     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
            throw r8     // Catch:{ AssertionError -> 0x00e8, all -> 0x00e6 }
        L_0x00e6:
            r5 = move-exception
            goto L_0x00fc
        L_0x00e8:
            r5 = move-exception
            r7 = r6
            goto L_0x00ef
        L_0x00eb:
            r5 = move-exception
            r6 = r7
            goto L_0x00fc
        L_0x00ee:
            r5 = move-exception
        L_0x00ef:
            boolean r6 = com.squareup.okhttp.internal.Util.isAndroidGetsocknameError(r5)     // Catch:{ all -> 0x00eb }
            if (r6 == 0) goto L_0x00fb
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x00eb }
            r6.<init>(r5)     // Catch:{ all -> 0x00eb }
            throw r6     // Catch:{ all -> 0x00eb }
        L_0x00fb:
            throw r5     // Catch:{ all -> 0x00eb }
        L_0x00fc:
            if (r6 == 0) goto L_0x0105
            com.squareup.okhttp.internal.Platform r7 = com.squareup.okhttp.internal.Platform.get()
            r7.afterHandshake(r6)
        L_0x0105:
            com.squareup.okhttp.internal.Util.closeQuietly(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.Connection.connectTls(int, int, com.squareup.okhttp.Request, com.squareup.okhttp.internal.ConnectionSpecSelector):void");
    }

    private void createTunnel(int i, int i2, Request request) throws IOException {
        Request createTunnelRequest = createTunnelRequest(request);
        HttpConnection httpConnection2 = new HttpConnection(this.pool, this, this.socket);
        httpConnection2.setTimeouts(i, i2);
        HttpUrl httpUrl = createTunnelRequest.httpUrl();
        StringBuilder sb = new StringBuilder();
        sb.append("CONNECT ");
        sb.append(httpUrl.host());
        sb.append(":");
        sb.append(httpUrl.port());
        sb.append(" HTTP/1.1");
        String sb2 = sb.toString();
        do {
            httpConnection2.writeRequest(createTunnelRequest.headers(), sb2);
            httpConnection2.flush();
            Response build = httpConnection2.readResponse().request(createTunnelRequest).build();
            long contentLength = OkHeaders.contentLength(build);
            if (contentLength == -1) {
                contentLength = 0;
            }
            Source newFixedLengthSource = httpConnection2.newFixedLengthSource(contentLength);
            Util.skipAll(newFixedLengthSource, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            newFixedLengthSource.close();
            int code = build.code();
            if (code != 200) {
                if (code == 407) {
                    createTunnelRequest = OkHeaders.processAuthHeader(this.route.getAddress().getAuthenticator(), build, this.route.getProxy());
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Unexpected response code for CONNECT: ");
                    sb3.append(build.code());
                    throw new IOException(sb3.toString());
                }
            } else if (httpConnection2.bufferSize() > 0) {
                throw new IOException("TLS tunnel buffered too many bytes!");
            } else {
                return;
            }
        } while (createTunnelRequest != null);
        throw new IOException("Failed to authenticate with proxy");
    }

    private Request createTunnelRequest(Request request) throws IOException {
        HttpUrl build = new HttpUrl.Builder().scheme(Constants.HTTPS).host(request.httpUrl().host()).port(request.httpUrl().port()).build();
        Request.Builder header = new Request.Builder().url(build).header(HttpHeaders.HOST, Util.hostHeader(build)).header("Proxy-Connection", "Keep-Alive");
        String header2 = request.header("User-Agent");
        if (header2 != null) {
            header.header("User-Agent", header2);
        }
        String header3 = request.header(HttpHeaders.PROXY_AUTHORIZATION);
        if (header3 != null) {
            header.header(HttpHeaders.PROXY_AUTHORIZATION, header3);
        }
        return header.build();
    }

    /* access modifiers changed from: 0000 */
    public void connectAndSetOwner(OkHttpClient okHttpClient, Object obj, Request request) throws RouteException {
        setOwner(obj);
        if (!isConnected()) {
            Request request2 = request;
            connect(okHttpClient.getConnectTimeout(), okHttpClient.getReadTimeout(), okHttpClient.getWriteTimeout(), request2, this.route.address.getConnectionSpecs(), okHttpClient.getRetryOnConnectionFailure());
            if (isFramed()) {
                okHttpClient.getConnectionPool().share(this);
            }
            okHttpClient.routeDatabase().connected(getRoute());
        }
        setTimeouts(okHttpClient.getReadTimeout(), okHttpClient.getWriteTimeout());
    }

    /* access modifiers changed from: 0000 */
    public boolean isConnected() {
        return this.connected;
    }

    public Route getRoute() {
        return this.route;
    }

    public Socket getSocket() {
        return this.socket;
    }

    /* access modifiers changed from: 0000 */
    public boolean isAlive() {
        return !this.socket.isClosed() && !this.socket.isInputShutdown() && !this.socket.isOutputShutdown();
    }

    /* access modifiers changed from: 0000 */
    public boolean isReadable() {
        HttpConnection httpConnection2 = this.httpConnection;
        if (httpConnection2 != null) {
            return httpConnection2.isReadable();
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void resetIdleStartTime() {
        if (this.framedConnection == null) {
            this.idleStartTimeNs = System.nanoTime();
            return;
        }
        throw new IllegalStateException("framedConnection != null");
    }

    /* access modifiers changed from: 0000 */
    public boolean isIdle() {
        FramedConnection framedConnection2 = this.framedConnection;
        return framedConnection2 == null || framedConnection2.isIdle();
    }

    /* access modifiers changed from: 0000 */
    public long getIdleStartTimeNs() {
        FramedConnection framedConnection2 = this.framedConnection;
        return framedConnection2 == null ? this.idleStartTimeNs : framedConnection2.getIdleStartTimeNs();
    }

    public Handshake getHandshake() {
        return this.handshake;
    }

    /* access modifiers changed from: 0000 */
    public Transport newTransport(HttpEngine httpEngine) throws IOException {
        FramedConnection framedConnection2 = this.framedConnection;
        return framedConnection2 != null ? new FramedTransport(httpEngine, framedConnection2) : new HttpTransport(httpEngine, this.httpConnection);
    }

    /* access modifiers changed from: 0000 */
    public boolean isFramed() {
        return this.framedConnection != null;
    }

    public Protocol getProtocol() {
        return this.protocol;
    }

    /* access modifiers changed from: 0000 */
    public void setProtocol(Protocol protocol2) {
        if (protocol2 != null) {
            this.protocol = protocol2;
            return;
        }
        throw new IllegalArgumentException("protocol == null");
    }

    /* access modifiers changed from: 0000 */
    public void setTimeouts(int i, int i2) throws RouteException {
        if (!this.connected) {
            throw new IllegalStateException("setTimeouts - not connected");
        } else if (this.httpConnection != null) {
            try {
                this.socket.setSoTimeout(i);
                this.httpConnection.setTimeouts(i, i2);
            } catch (IOException e) {
                throw new RouteException(e);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void incrementRecycleCount() {
        this.recycleCount++;
    }

    /* access modifiers changed from: 0000 */
    public int recycleCount() {
        return this.recycleCount;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.route.address.uriHost);
        sb.append(":");
        sb.append(this.route.address.uriPort);
        sb.append(", proxy=");
        sb.append(this.route.proxy);
        sb.append(" hostAddress=");
        sb.append(this.route.inetSocketAddress.getAddress().getHostAddress());
        sb.append(" cipherSuite=");
        Handshake handshake2 = this.handshake;
        sb.append(handshake2 != null ? handshake2.cipherSuite() : "none");
        sb.append(" protocol=");
        sb.append(this.protocol);
        sb.append('}');
        return sb.toString();
    }
}
