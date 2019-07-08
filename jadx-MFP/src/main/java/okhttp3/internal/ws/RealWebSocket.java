package okhttp3.internal.ws;

import com.google.common.net.HttpHeaders;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.ws.WebSocketReader.FrameCallback;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;

public final class RealWebSocket implements WebSocket, FrameCallback {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final List<Protocol> ONLY_HTTP1 = Collections.singletonList(Protocol.HTTP_1_1);
    private boolean awaitingPong;
    private Call call;
    private ScheduledFuture<?> cancelFuture;
    private boolean enqueuedClose;
    private ScheduledExecutorService executor;
    private boolean failed;
    private final String key;
    final WebSocketListener listener;
    private final ArrayDeque<Object> messageAndCloseQueue;
    private final long pingIntervalMillis;
    private final ArrayDeque<ByteString> pongQueue;
    private long queueSize;
    private final Random random;
    private WebSocketReader reader;
    private int receivedCloseCode;
    private String receivedCloseReason;
    private int receivedPingCount;
    private int receivedPongCount;
    private int sentPingCount;
    private Streams streams;
    private WebSocketWriter writer;
    private final Runnable writerRunnable;

    /* renamed from: okhttp3.internal.ws.RealWebSocket$1 reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ RealWebSocket this$0;

        public void run() {
            do {
                try {
                } catch (IOException e) {
                    this.this$0.failWebSocket(e, null);
                    return;
                }
            } while (this.this$0.writeOneFrame());
        }
    }

    /* renamed from: okhttp3.internal.ws.RealWebSocket$2 reason: invalid class name */
    class AnonymousClass2 implements Callback {
        final /* synthetic */ RealWebSocket this$0;
        final /* synthetic */ Request val$request;

        public void onResponse(Call call, Response response) {
            try {
                this.this$0.checkResponse(response);
                StreamAllocation streamAllocation = Internal.instance.streamAllocation(call);
                streamAllocation.noNewStreams();
                Streams newWebSocketStreams = streamAllocation.connection().newWebSocketStreams(streamAllocation);
                try {
                    this.this$0.listener.onOpen(this.this$0, response);
                    StringBuilder sb = new StringBuilder();
                    sb.append("OkHttp WebSocket ");
                    sb.append(this.val$request.url().redact());
                    this.this$0.initReaderAndWriter(sb.toString(), newWebSocketStreams);
                    streamAllocation.connection().socket().setSoTimeout(0);
                    this.this$0.loopReader();
                } catch (Exception e) {
                    this.this$0.failWebSocket(e, null);
                }
            } catch (ProtocolException e2) {
                this.this$0.failWebSocket(e2, response);
                Util.closeQuietly((Closeable) response);
            }
        }

        public void onFailure(Call call, IOException iOException) {
            this.this$0.failWebSocket(iOException, null);
        }
    }

    final class CancelRunnable implements Runnable {
        CancelRunnable() {
        }

        public void run() {
            RealWebSocket.this.cancel();
        }
    }

    static final class Close {
        final long cancelAfterCloseMillis;
        final int code;
        final ByteString reason;
    }

    static final class Message {
        final ByteString data;
        final int formatOpcode;
    }

    private final class PingRunnable implements Runnable {
        PingRunnable() {
        }

        public void run() {
            RealWebSocket.this.writePingFrame();
        }
    }

    public static abstract class Streams implements Closeable {
        public final boolean client;
        public final BufferedSink sink;
        public final BufferedSource source;

        public Streams(boolean z, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            this.client = z;
            this.source = bufferedSource;
            this.sink = bufferedSink;
        }
    }

    public void cancel() {
        this.call.cancel();
    }

    /* access modifiers changed from: 0000 */
    public void checkResponse(Response response) throws ProtocolException {
        if (response.code() == 101) {
            String header = response.header(HttpHeaders.CONNECTION);
            if (HttpHeaders.UPGRADE.equalsIgnoreCase(header)) {
                String header2 = response.header(HttpHeaders.UPGRADE);
                if ("websocket".equalsIgnoreCase(header2)) {
                    String header3 = response.header("Sec-WebSocket-Accept");
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.key);
                    sb.append("258EAFA5-E914-47DA-95CA-C5AB0DC85B11");
                    String base64 = ByteString.encodeUtf8(sb.toString()).sha1().base64();
                    if (!base64.equals(header3)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Expected 'Sec-WebSocket-Accept' header value '");
                        sb2.append(base64);
                        sb2.append("' but was '");
                        sb2.append(header3);
                        sb2.append("'");
                        throw new ProtocolException(sb2.toString());
                    }
                    return;
                }
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Expected 'Upgrade' header value 'websocket' but was '");
                sb3.append(header2);
                sb3.append("'");
                throw new ProtocolException(sb3.toString());
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Expected 'Connection' header value 'Upgrade' but was '");
            sb4.append(header);
            sb4.append("'");
            throw new ProtocolException(sb4.toString());
        }
        StringBuilder sb5 = new StringBuilder();
        sb5.append("Expected HTTP 101 response but was '");
        sb5.append(response.code());
        sb5.append(" ");
        sb5.append(response.message());
        sb5.append("'");
        throw new ProtocolException(sb5.toString());
    }

    public void initReaderAndWriter(String str, Streams streams2) throws IOException {
        synchronized (this) {
            this.streams = streams2;
            this.writer = new WebSocketWriter(streams2.client, streams2.sink, this.random);
            this.executor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(str, false));
            if (this.pingIntervalMillis != 0) {
                this.executor.scheduleAtFixedRate(new PingRunnable(), this.pingIntervalMillis, this.pingIntervalMillis, TimeUnit.MILLISECONDS);
            }
            if (!this.messageAndCloseQueue.isEmpty()) {
                runWriter();
            }
        }
        this.reader = new WebSocketReader(streams2.client, streams2.source, this);
    }

    public void loopReader() throws IOException {
        while (this.receivedCloseCode == -1) {
            this.reader.processNextFrame();
        }
    }

    public void onReadMessage(String str) throws IOException {
        this.listener.onMessage((WebSocket) this, str);
    }

    public void onReadMessage(ByteString byteString) throws IOException {
        this.listener.onMessage((WebSocket) this, byteString);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onReadPing(okio.ByteString r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.failed     // Catch:{ all -> 0x0024 }
            if (r0 != 0) goto L_0x0022
            boolean r0 = r1.enqueuedClose     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x0012
            java.util.ArrayDeque<java.lang.Object> r0 = r1.messageAndCloseQueue     // Catch:{ all -> 0x0024 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x0012
            goto L_0x0022
        L_0x0012:
            java.util.ArrayDeque<okio.ByteString> r0 = r1.pongQueue     // Catch:{ all -> 0x0024 }
            r0.add(r2)     // Catch:{ all -> 0x0024 }
            r1.runWriter()     // Catch:{ all -> 0x0024 }
            int r2 = r1.receivedPingCount     // Catch:{ all -> 0x0024 }
            int r2 = r2 + 1
            r1.receivedPingCount = r2     // Catch:{ all -> 0x0024 }
            monitor-exit(r1)
            return
        L_0x0022:
            monitor-exit(r1)
            return
        L_0x0024:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.onReadPing(okio.ByteString):void");
    }

    public synchronized void onReadPong(ByteString byteString) {
        this.receivedPongCount++;
        this.awaitingPong = false;
    }

    public void onReadClose(int i, String str) {
        Closeable closeable;
        if (i != -1) {
            synchronized (this) {
                if (this.receivedCloseCode == -1) {
                    this.receivedCloseCode = i;
                    this.receivedCloseReason = str;
                    if (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty()) {
                        closeable = null;
                    } else {
                        closeable = this.streams;
                        this.streams = null;
                        if (this.cancelFuture != null) {
                            this.cancelFuture.cancel(false);
                        }
                        this.executor.shutdown();
                    }
                } else {
                    throw new IllegalStateException("already closed");
                }
            }
            try {
                this.listener.onClosing(this, i, str);
                if (closeable != null) {
                    this.listener.onClosed(this, i, str);
                }
            } finally {
                Util.closeQuietly(closeable);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void runWriter() {
        ScheduledExecutorService scheduledExecutorService = this.executor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.execute(this.writerRunnable);
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0051, code lost:
        if (r2 == null) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r0.writePong(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0059, code lost:
        if ((r5 instanceof okhttp3.internal.ws.RealWebSocket.Message) == false) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005b, code lost:
        r1 = ((okhttp3.internal.ws.RealWebSocket.Message) r5).data;
        r0 = okio.Okio.buffer(r0.newMessageSink(((okhttp3.internal.ws.RealWebSocket.Message) r5).formatOpcode, (long) r1.size()));
        r0.write(r1);
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0077, code lost:
        monitor-enter(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r11.queueSize -= (long) r1.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0082, code lost:
        monitor-exit(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0089, code lost:
        if ((r5 instanceof okhttp3.internal.ws.RealWebSocket.Close) == false) goto L_0x00a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008b, code lost:
        r5 = (okhttp3.internal.ws.RealWebSocket.Close) r5;
        r0.writeClose(r5.code, r5.reason);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0094, code lost:
        if (r4 == null) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0096, code lost:
        r11.listener.onClosed(r11, r1, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009b, code lost:
        okhttp3.internal.Util.closeQuietly(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009f, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a5, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a6, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a7, code lost:
        okhttp3.internal.Util.closeQuietly(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00aa, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean writeOneFrame() throws java.io.IOException {
        /*
            r11 = this;
            monitor-enter(r11)
            boolean r0 = r11.failed     // Catch:{ all -> 0x00ab }
            r1 = 0
            if (r0 == 0) goto L_0x0008
            monitor-exit(r11)     // Catch:{ all -> 0x00ab }
            return r1
        L_0x0008:
            okhttp3.internal.ws.WebSocketWriter r0 = r11.writer     // Catch:{ all -> 0x00ab }
            java.util.ArrayDeque<okio.ByteString> r2 = r11.pongQueue     // Catch:{ all -> 0x00ab }
            java.lang.Object r2 = r2.poll()     // Catch:{ all -> 0x00ab }
            okio.ByteString r2 = (okio.ByteString) r2     // Catch:{ all -> 0x00ab }
            r3 = -1
            r4 = 0
            if (r2 != 0) goto L_0x004d
            java.util.ArrayDeque<java.lang.Object> r5 = r11.messageAndCloseQueue     // Catch:{ all -> 0x00ab }
            java.lang.Object r5 = r5.poll()     // Catch:{ all -> 0x00ab }
            boolean r6 = r5 instanceof okhttp3.internal.ws.RealWebSocket.Close     // Catch:{ all -> 0x00ab }
            if (r6 == 0) goto L_0x0046
            int r1 = r11.receivedCloseCode     // Catch:{ all -> 0x00ab }
            java.lang.String r6 = r11.receivedCloseReason     // Catch:{ all -> 0x00ab }
            if (r1 == r3) goto L_0x0031
            okhttp3.internal.ws.RealWebSocket$Streams r3 = r11.streams     // Catch:{ all -> 0x00ab }
            r11.streams = r4     // Catch:{ all -> 0x00ab }
            java.util.concurrent.ScheduledExecutorService r4 = r11.executor     // Catch:{ all -> 0x00ab }
            r4.shutdown()     // Catch:{ all -> 0x00ab }
            r4 = r3
            goto L_0x0050
        L_0x0031:
            java.util.concurrent.ScheduledExecutorService r3 = r11.executor     // Catch:{ all -> 0x00ab }
            okhttp3.internal.ws.RealWebSocket$CancelRunnable r7 = new okhttp3.internal.ws.RealWebSocket$CancelRunnable     // Catch:{ all -> 0x00ab }
            r7.<init>()     // Catch:{ all -> 0x00ab }
            r8 = r5
            okhttp3.internal.ws.RealWebSocket$Close r8 = (okhttp3.internal.ws.RealWebSocket.Close) r8     // Catch:{ all -> 0x00ab }
            long r8 = r8.cancelAfterCloseMillis     // Catch:{ all -> 0x00ab }
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x00ab }
            java.util.concurrent.ScheduledFuture r3 = r3.schedule(r7, r8, r10)     // Catch:{ all -> 0x00ab }
            r11.cancelFuture = r3     // Catch:{ all -> 0x00ab }
            goto L_0x0050
        L_0x0046:
            if (r5 != 0) goto L_0x004a
            monitor-exit(r11)     // Catch:{ all -> 0x00ab }
            return r1
        L_0x004a:
            r6 = r4
            r1 = -1
            goto L_0x0050
        L_0x004d:
            r5 = r4
            r6 = r5
            r1 = -1
        L_0x0050:
            monitor-exit(r11)     // Catch:{ all -> 0x00ab }
            if (r2 == 0) goto L_0x0057
            r0.writePong(r2)     // Catch:{ all -> 0x00a6 }
            goto L_0x009b
        L_0x0057:
            boolean r2 = r5 instanceof okhttp3.internal.ws.RealWebSocket.Message     // Catch:{ all -> 0x00a6 }
            if (r2 == 0) goto L_0x0087
            r1 = r5
            okhttp3.internal.ws.RealWebSocket$Message r1 = (okhttp3.internal.ws.RealWebSocket.Message) r1     // Catch:{ all -> 0x00a6 }
            okio.ByteString r1 = r1.data     // Catch:{ all -> 0x00a6 }
            okhttp3.internal.ws.RealWebSocket$Message r5 = (okhttp3.internal.ws.RealWebSocket.Message) r5     // Catch:{ all -> 0x00a6 }
            int r2 = r5.formatOpcode     // Catch:{ all -> 0x00a6 }
            int r3 = r1.size()     // Catch:{ all -> 0x00a6 }
            long r5 = (long) r3     // Catch:{ all -> 0x00a6 }
            okio.Sink r0 = r0.newMessageSink(r2, r5)     // Catch:{ all -> 0x00a6 }
            okio.BufferedSink r0 = okio.Okio.buffer(r0)     // Catch:{ all -> 0x00a6 }
            r0.write(r1)     // Catch:{ all -> 0x00a6 }
            r0.close()     // Catch:{ all -> 0x00a6 }
            monitor-enter(r11)     // Catch:{ all -> 0x00a6 }
            long r2 = r11.queueSize     // Catch:{ all -> 0x0084 }
            int r0 = r1.size()     // Catch:{ all -> 0x0084 }
            long r0 = (long) r0     // Catch:{ all -> 0x0084 }
            long r2 = r2 - r0
            r11.queueSize = r2     // Catch:{ all -> 0x0084 }
            monitor-exit(r11)     // Catch:{ all -> 0x0084 }
            goto L_0x009b
        L_0x0084:
            r0 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x0084 }
            throw r0     // Catch:{ all -> 0x00a6 }
        L_0x0087:
            boolean r2 = r5 instanceof okhttp3.internal.ws.RealWebSocket.Close     // Catch:{ all -> 0x00a6 }
            if (r2 == 0) goto L_0x00a0
            okhttp3.internal.ws.RealWebSocket$Close r5 = (okhttp3.internal.ws.RealWebSocket.Close) r5     // Catch:{ all -> 0x00a6 }
            int r2 = r5.code     // Catch:{ all -> 0x00a6 }
            okio.ByteString r3 = r5.reason     // Catch:{ all -> 0x00a6 }
            r0.writeClose(r2, r3)     // Catch:{ all -> 0x00a6 }
            if (r4 == 0) goto L_0x009b
            okhttp3.WebSocketListener r0 = r11.listener     // Catch:{ all -> 0x00a6 }
            r0.onClosed(r11, r1, r6)     // Catch:{ all -> 0x00a6 }
        L_0x009b:
            r0 = 1
            okhttp3.internal.Util.closeQuietly(r4)
            return r0
        L_0x00a0:
            java.lang.AssertionError r0 = new java.lang.AssertionError     // Catch:{ all -> 0x00a6 }
            r0.<init>()     // Catch:{ all -> 0x00a6 }
            throw r0     // Catch:{ all -> 0x00a6 }
        L_0x00a6:
            r0 = move-exception
            okhttp3.internal.Util.closeQuietly(r4)
            throw r0
        L_0x00ab:
            r0 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x00ab }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.writeOneFrame():boolean");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        if (r1 == -1) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        r2 = new java.lang.StringBuilder();
        r2.append("sent ping but didn't receive pong within ");
        r2.append(r7.pingIntervalMillis);
        r2.append("ms (after ");
        r2.append(r1 - 1);
        r2.append(" successful ping/pongs)");
        failWebSocket(new java.net.SocketTimeoutException(r2.toString()), null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0047, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0.writePing(okio.ByteString.EMPTY);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004f, code lost:
        failWebSocket(r0, null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writePingFrame() {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.failed     // Catch:{ all -> 0x0053 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r7)     // Catch:{ all -> 0x0053 }
            return
        L_0x0007:
            okhttp3.internal.ws.WebSocketWriter r0 = r7.writer     // Catch:{ all -> 0x0053 }
            boolean r1 = r7.awaitingPong     // Catch:{ all -> 0x0053 }
            r2 = -1
            if (r1 == 0) goto L_0x0011
            int r1 = r7.sentPingCount     // Catch:{ all -> 0x0053 }
            goto L_0x0012
        L_0x0011:
            r1 = -1
        L_0x0012:
            int r3 = r7.sentPingCount     // Catch:{ all -> 0x0053 }
            r4 = 1
            int r3 = r3 + r4
            r7.sentPingCount = r3     // Catch:{ all -> 0x0053 }
            r7.awaitingPong = r4     // Catch:{ all -> 0x0053 }
            monitor-exit(r7)     // Catch:{ all -> 0x0053 }
            r3 = 0
            if (r1 == r2) goto L_0x0048
            java.net.SocketTimeoutException r0 = new java.net.SocketTimeoutException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "sent ping but didn't receive pong within "
            r2.append(r5)
            long r5 = r7.pingIntervalMillis
            r2.append(r5)
            java.lang.String r5 = "ms (after "
            r2.append(r5)
            int r1 = r1 - r4
            r2.append(r1)
            java.lang.String r1 = " successful ping/pongs)"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            r7.failWebSocket(r0, r3)
            return
        L_0x0048:
            okio.ByteString r1 = okio.ByteString.EMPTY     // Catch:{ IOException -> 0x004e }
            r0.writePing(r1)     // Catch:{ IOException -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r0 = move-exception
            r7.failWebSocket(r0, r3)
        L_0x0052:
            return
        L_0x0053:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0053 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.writePingFrame():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r3.listener.onFailure(r3, r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002c, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002d, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0030, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void failWebSocket(java.lang.Exception r4, @javax.annotation.Nullable okhttp3.Response r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.failed     // Catch:{ all -> 0x0031 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r3)     // Catch:{ all -> 0x0031 }
            return
        L_0x0007:
            r0 = 1
            r3.failed = r0     // Catch:{ all -> 0x0031 }
            okhttp3.internal.ws.RealWebSocket$Streams r0 = r3.streams     // Catch:{ all -> 0x0031 }
            r1 = 0
            r3.streams = r1     // Catch:{ all -> 0x0031 }
            java.util.concurrent.ScheduledFuture<?> r1 = r3.cancelFuture     // Catch:{ all -> 0x0031 }
            if (r1 == 0) goto L_0x0019
            java.util.concurrent.ScheduledFuture<?> r1 = r3.cancelFuture     // Catch:{ all -> 0x0031 }
            r2 = 0
            r1.cancel(r2)     // Catch:{ all -> 0x0031 }
        L_0x0019:
            java.util.concurrent.ScheduledExecutorService r1 = r3.executor     // Catch:{ all -> 0x0031 }
            if (r1 == 0) goto L_0x0022
            java.util.concurrent.ScheduledExecutorService r1 = r3.executor     // Catch:{ all -> 0x0031 }
            r1.shutdown()     // Catch:{ all -> 0x0031 }
        L_0x0022:
            monitor-exit(r3)     // Catch:{ all -> 0x0031 }
            okhttp3.WebSocketListener r1 = r3.listener     // Catch:{ all -> 0x002c }
            r1.onFailure(r3, r4, r5)     // Catch:{ all -> 0x002c }
            okhttp3.internal.Util.closeQuietly(r0)
            return
        L_0x002c:
            r4 = move-exception
            okhttp3.internal.Util.closeQuietly(r0)
            throw r4
        L_0x0031:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0031 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.failWebSocket(java.lang.Exception, okhttp3.Response):void");
    }
}
