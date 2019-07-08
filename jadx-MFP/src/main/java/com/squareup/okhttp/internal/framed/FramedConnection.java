package com.squareup.okhttp.internal.framed;

import com.google.android.exoplayer2.C;
import com.myfitnesspal.shared.constants.Constants.BusyStates;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.NamedRunnable;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.framed.FrameReader.Handler;
import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

public final class FramedConnection implements Closeable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    /* access modifiers changed from: private */
    public static final ExecutorService executor;
    long bytesLeftInWriteWindow;
    final boolean client;
    /* access modifiers changed from: private */
    public final Set<Integer> currentPushRequests;
    final FrameWriter frameWriter;
    /* access modifiers changed from: private */
    public final IncomingStreamHandler handler;
    /* access modifiers changed from: private */
    public final String hostName;
    private long idleStartTimeNs;
    /* access modifiers changed from: private */
    public int lastGoodStreamId;
    private int nextPingId;
    /* access modifiers changed from: private */
    public int nextStreamId;
    final Settings okHttpSettings;
    final Settings peerSettings;
    private Map<Integer, Ping> pings;
    final Protocol protocol;
    private final ExecutorService pushExecutor;
    /* access modifiers changed from: private */
    public final PushObserver pushObserver;
    final Reader readerRunnable;
    /* access modifiers changed from: private */
    public boolean receivedInitialPeerSettings;
    /* access modifiers changed from: private */
    public boolean shutdown;
    final Socket socket;
    /* access modifiers changed from: private */
    public final Map<Integer, FramedStream> streams;
    long unacknowledgedBytesRead;
    final Variant variant;

    public static class Builder {
        /* access modifiers changed from: private */
        public boolean client;
        /* access modifiers changed from: private */
        public IncomingStreamHandler handler = IncomingStreamHandler.REFUSE_INCOMING_STREAMS;
        /* access modifiers changed from: private */
        public String hostName;
        /* access modifiers changed from: private */
        public Protocol protocol = Protocol.SPDY_3;
        /* access modifiers changed from: private */
        public PushObserver pushObserver = PushObserver.CANCEL;
        /* access modifiers changed from: private */
        public Socket socket;

        public Builder(String str, boolean z, Socket socket2) throws IOException {
            this.hostName = str;
            this.client = z;
            this.socket = socket2;
        }

        public Builder protocol(Protocol protocol2) {
            this.protocol = protocol2;
            return this;
        }

        public FramedConnection build() throws IOException {
            return new FramedConnection(this);
        }
    }

    class Reader extends NamedRunnable implements Handler {
        FrameReader frameReader;

        public void ackSettings() {
        }

        public void priority(int i, int i2, int i3, boolean z) {
        }

        private Reader() {
            super("OkHttp %s", FramedConnection.this.hostName);
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0039, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            r0 = com.squareup.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR;
            r1 = com.squareup.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            r2 = r5.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            com.squareup.okhttp.internal.framed.FramedConnection.access$1000(r5.this$0, r0, r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x004f, code lost:
            com.squareup.okhttp.internal.Util.closeQuietly((java.io.Closeable) r5.frameReader);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0054, code lost:
            throw r2;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x003b */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void execute() {
            /*
                r5 = this;
                com.squareup.okhttp.internal.framed.ErrorCode r0 = com.squareup.okhttp.internal.framed.ErrorCode.INTERNAL_ERROR
                com.squareup.okhttp.internal.framed.ErrorCode r1 = com.squareup.okhttp.internal.framed.ErrorCode.INTERNAL_ERROR
                com.squareup.okhttp.internal.framed.FramedConnection r2 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ IOException -> 0x003b }
                com.squareup.okhttp.internal.framed.Variant r2 = r2.variant     // Catch:{ IOException -> 0x003b }
                com.squareup.okhttp.internal.framed.FramedConnection r3 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ IOException -> 0x003b }
                java.net.Socket r3 = r3.socket     // Catch:{ IOException -> 0x003b }
                okio.Source r3 = okio.Okio.source(r3)     // Catch:{ IOException -> 0x003b }
                okio.BufferedSource r3 = okio.Okio.buffer(r3)     // Catch:{ IOException -> 0x003b }
                com.squareup.okhttp.internal.framed.FramedConnection r4 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ IOException -> 0x003b }
                boolean r4 = r4.client     // Catch:{ IOException -> 0x003b }
                com.squareup.okhttp.internal.framed.FrameReader r2 = r2.newReader(r3, r4)     // Catch:{ IOException -> 0x003b }
                r5.frameReader = r2     // Catch:{ IOException -> 0x003b }
                com.squareup.okhttp.internal.framed.FramedConnection r2 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ IOException -> 0x003b }
                boolean r2 = r2.client     // Catch:{ IOException -> 0x003b }
                if (r2 != 0) goto L_0x0029
                com.squareup.okhttp.internal.framed.FrameReader r2 = r5.frameReader     // Catch:{ IOException -> 0x003b }
                r2.readConnectionPreface()     // Catch:{ IOException -> 0x003b }
            L_0x0029:
                com.squareup.okhttp.internal.framed.FrameReader r2 = r5.frameReader     // Catch:{ IOException -> 0x003b }
                boolean r2 = r2.nextFrame(r5)     // Catch:{ IOException -> 0x003b }
                if (r2 == 0) goto L_0x0032
                goto L_0x0029
            L_0x0032:
                com.squareup.okhttp.internal.framed.ErrorCode r0 = com.squareup.okhttp.internal.framed.ErrorCode.NO_ERROR     // Catch:{ IOException -> 0x003b }
                com.squareup.okhttp.internal.framed.ErrorCode r1 = com.squareup.okhttp.internal.framed.ErrorCode.CANCEL     // Catch:{ IOException -> 0x003b }
                com.squareup.okhttp.internal.framed.FramedConnection r2 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ IOException -> 0x0044 }
                goto L_0x0041
            L_0x0039:
                r2 = move-exception
                goto L_0x004a
            L_0x003b:
                com.squareup.okhttp.internal.framed.ErrorCode r0 = com.squareup.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR     // Catch:{ all -> 0x0039 }
                com.squareup.okhttp.internal.framed.ErrorCode r1 = com.squareup.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR     // Catch:{ all -> 0x0039 }
                com.squareup.okhttp.internal.framed.FramedConnection r2 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ IOException -> 0x0044 }
            L_0x0041:
                r2.close(r0, r1)     // Catch:{ IOException -> 0x0044 }
            L_0x0044:
                com.squareup.okhttp.internal.framed.FrameReader r0 = r5.frameReader
                com.squareup.okhttp.internal.Util.closeQuietly(r0)
                return
            L_0x004a:
                com.squareup.okhttp.internal.framed.FramedConnection r3 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ IOException -> 0x004f }
                r3.close(r0, r1)     // Catch:{ IOException -> 0x004f }
            L_0x004f:
                com.squareup.okhttp.internal.framed.FrameReader r0 = r5.frameReader
                com.squareup.okhttp.internal.Util.closeQuietly(r0)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.framed.FramedConnection.Reader.execute():void");
        }

        public void data(boolean z, int i, BufferedSource bufferedSource, int i2) throws IOException {
            if (FramedConnection.this.pushedStream(i)) {
                FramedConnection.this.pushDataLater(i, bufferedSource, i2, z);
                return;
            }
            FramedStream stream = FramedConnection.this.getStream(i);
            if (stream == null) {
                FramedConnection.this.writeSynResetLater(i, ErrorCode.INVALID_STREAM);
                bufferedSource.skip((long) i2);
                return;
            }
            stream.receiveData(bufferedSource, i2);
            if (z) {
                stream.receiveFin();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:31:0x008f, code lost:
            if (r14.failIfStreamPresent() == false) goto L_0x009c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0091, code lost:
            r0.closeLater(com.squareup.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR);
            r8.this$0.removeStream(r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x009b, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x009c, code lost:
            r0.receiveHeaders(r13, r14);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x009f, code lost:
            if (r10 == false) goto L_0x00a4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a1, code lost:
            r0.receiveFin();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a4, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void headers(boolean r9, boolean r10, int r11, int r12, java.util.List<com.squareup.okhttp.internal.framed.Header> r13, com.squareup.okhttp.internal.framed.HeadersMode r14) {
            /*
                r8 = this;
                com.squareup.okhttp.internal.framed.FramedConnection r12 = com.squareup.okhttp.internal.framed.FramedConnection.this
                boolean r12 = r12.pushedStream(r11)
                if (r12 == 0) goto L_0x000e
                com.squareup.okhttp.internal.framed.FramedConnection r9 = com.squareup.okhttp.internal.framed.FramedConnection.this
                r9.pushHeadersLater(r11, r13, r10)
                return
            L_0x000e:
                com.squareup.okhttp.internal.framed.FramedConnection r12 = com.squareup.okhttp.internal.framed.FramedConnection.this
                monitor-enter(r12)
                com.squareup.okhttp.internal.framed.FramedConnection r0 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ all -> 0x00a5 }
                boolean r0 = r0.shutdown     // Catch:{ all -> 0x00a5 }
                if (r0 == 0) goto L_0x001b
                monitor-exit(r12)     // Catch:{ all -> 0x00a5 }
                return
            L_0x001b:
                com.squareup.okhttp.internal.framed.FramedConnection r0 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ all -> 0x00a5 }
                com.squareup.okhttp.internal.framed.FramedStream r0 = r0.getStream(r11)     // Catch:{ all -> 0x00a5 }
                if (r0 != 0) goto L_0x008a
                boolean r14 = r14.failIfStreamAbsent()     // Catch:{ all -> 0x00a5 }
                if (r14 == 0) goto L_0x0032
                com.squareup.okhttp.internal.framed.FramedConnection r9 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ all -> 0x00a5 }
                com.squareup.okhttp.internal.framed.ErrorCode r10 = com.squareup.okhttp.internal.framed.ErrorCode.INVALID_STREAM     // Catch:{ all -> 0x00a5 }
                r9.writeSynResetLater(r11, r10)     // Catch:{ all -> 0x00a5 }
                monitor-exit(r12)     // Catch:{ all -> 0x00a5 }
                return
            L_0x0032:
                com.squareup.okhttp.internal.framed.FramedConnection r14 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ all -> 0x00a5 }
                int r14 = r14.lastGoodStreamId     // Catch:{ all -> 0x00a5 }
                if (r11 > r14) goto L_0x003c
                monitor-exit(r12)     // Catch:{ all -> 0x00a5 }
                return
            L_0x003c:
                int r14 = r11 % 2
                com.squareup.okhttp.internal.framed.FramedConnection r0 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ all -> 0x00a5 }
                int r0 = r0.nextStreamId     // Catch:{ all -> 0x00a5 }
                r1 = 2
                int r0 = r0 % r1
                if (r14 != r0) goto L_0x004a
                monitor-exit(r12)     // Catch:{ all -> 0x00a5 }
                return
            L_0x004a:
                com.squareup.okhttp.internal.framed.FramedStream r14 = new com.squareup.okhttp.internal.framed.FramedStream     // Catch:{ all -> 0x00a5 }
                com.squareup.okhttp.internal.framed.FramedConnection r4 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ all -> 0x00a5 }
                r2 = r14
                r3 = r11
                r5 = r9
                r6 = r10
                r7 = r13
                r2.<init>(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00a5 }
                com.squareup.okhttp.internal.framed.FramedConnection r9 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ all -> 0x00a5 }
                r9.lastGoodStreamId = r11     // Catch:{ all -> 0x00a5 }
                com.squareup.okhttp.internal.framed.FramedConnection r9 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ all -> 0x00a5 }
                java.util.Map r9 = r9.streams     // Catch:{ all -> 0x00a5 }
                java.lang.Integer r10 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x00a5 }
                r9.put(r10, r14)     // Catch:{ all -> 0x00a5 }
                java.util.concurrent.ExecutorService r9 = com.squareup.okhttp.internal.framed.FramedConnection.executor     // Catch:{ all -> 0x00a5 }
                com.squareup.okhttp.internal.framed.FramedConnection$Reader$1 r10 = new com.squareup.okhttp.internal.framed.FramedConnection$Reader$1     // Catch:{ all -> 0x00a5 }
                java.lang.String r13 = "OkHttp %s stream %d"
                java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ all -> 0x00a5 }
                r1 = 0
                com.squareup.okhttp.internal.framed.FramedConnection r2 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ all -> 0x00a5 }
                java.lang.String r2 = r2.hostName     // Catch:{ all -> 0x00a5 }
                r0[r1] = r2     // Catch:{ all -> 0x00a5 }
                r1 = 1
                java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x00a5 }
                r0[r1] = r11     // Catch:{ all -> 0x00a5 }
                r10.<init>(r13, r0, r14)     // Catch:{ all -> 0x00a5 }
                r9.execute(r10)     // Catch:{ all -> 0x00a5 }
                monitor-exit(r12)     // Catch:{ all -> 0x00a5 }
                return
            L_0x008a:
                monitor-exit(r12)     // Catch:{ all -> 0x00a5 }
                boolean r9 = r14.failIfStreamPresent()
                if (r9 == 0) goto L_0x009c
                com.squareup.okhttp.internal.framed.ErrorCode r9 = com.squareup.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                r0.closeLater(r9)
                com.squareup.okhttp.internal.framed.FramedConnection r9 = com.squareup.okhttp.internal.framed.FramedConnection.this
                r9.removeStream(r11)
                return
            L_0x009c:
                r0.receiveHeaders(r13, r14)
                if (r10 == 0) goto L_0x00a4
                r0.receiveFin()
            L_0x00a4:
                return
            L_0x00a5:
                r9 = move-exception
                monitor-exit(r12)     // Catch:{ all -> 0x00a5 }
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.framed.FramedConnection.Reader.headers(boolean, boolean, int, int, java.util.List, com.squareup.okhttp.internal.framed.HeadersMode):void");
        }

        public void rstStream(int i, ErrorCode errorCode) {
            if (FramedConnection.this.pushedStream(i)) {
                FramedConnection.this.pushResetLater(i, errorCode);
                return;
            }
            FramedStream removeStream = FramedConnection.this.removeStream(i);
            if (removeStream != null) {
                removeStream.receiveRstStream(errorCode);
            }
        }

        public void settings(boolean z, Settings settings) {
            FramedStream[] framedStreamArr;
            long j;
            synchronized (FramedConnection.this) {
                int initialWindowSize = FramedConnection.this.peerSettings.getInitialWindowSize(65536);
                if (z) {
                    FramedConnection.this.peerSettings.clear();
                }
                FramedConnection.this.peerSettings.merge(settings);
                if (FramedConnection.this.getProtocol() == Protocol.HTTP_2) {
                    ackSettingsLater(settings);
                }
                int initialWindowSize2 = FramedConnection.this.peerSettings.getInitialWindowSize(65536);
                framedStreamArr = null;
                if (initialWindowSize2 == -1 || initialWindowSize2 == initialWindowSize) {
                    j = 0;
                } else {
                    j = (long) (initialWindowSize2 - initialWindowSize);
                    if (!FramedConnection.this.receivedInitialPeerSettings) {
                        FramedConnection.this.addBytesToWriteWindow(j);
                        FramedConnection.this.receivedInitialPeerSettings = true;
                    }
                    if (!FramedConnection.this.streams.isEmpty()) {
                        framedStreamArr = (FramedStream[]) FramedConnection.this.streams.values().toArray(new FramedStream[FramedConnection.this.streams.size()]);
                    }
                }
            }
            if (framedStreamArr != null && j != 0) {
                for (FramedStream framedStream : framedStreamArr) {
                    synchronized (framedStream) {
                        framedStream.addBytesToWriteWindow(j);
                    }
                }
            }
        }

        private void ackSettingsLater(final Settings settings) {
            FramedConnection.executor.execute(new NamedRunnable("OkHttp %s ACK Settings", new Object[]{FramedConnection.this.hostName}) {
                public void execute() {
                    try {
                        FramedConnection.this.frameWriter.ackSettings(settings);
                    } catch (IOException unused) {
                    }
                }
            });
        }

        public void ping(boolean z, int i, int i2) {
            if (z) {
                Ping access$2200 = FramedConnection.this.removePing(i);
                if (access$2200 != null) {
                    access$2200.receive();
                    return;
                }
                return;
            }
            FramedConnection.this.writePingLater(true, i, i2, null);
        }

        public void goAway(int i, ErrorCode errorCode, ByteString byteString) {
            FramedStream[] framedStreamArr;
            byteString.size();
            synchronized (FramedConnection.this) {
                framedStreamArr = (FramedStream[]) FramedConnection.this.streams.values().toArray(new FramedStream[FramedConnection.this.streams.size()]);
                FramedConnection.this.shutdown = true;
            }
            for (FramedStream framedStream : framedStreamArr) {
                if (framedStream.getId() > i && framedStream.isLocallyInitiated()) {
                    framedStream.receiveRstStream(ErrorCode.REFUSED_STREAM);
                    FramedConnection.this.removeStream(framedStream.getId());
                }
            }
        }

        public void windowUpdate(int i, long j) {
            if (i == 0) {
                synchronized (FramedConnection.this) {
                    FramedConnection.this.bytesLeftInWriteWindow += j;
                    FramedConnection.this.notifyAll();
                }
                return;
            }
            FramedStream stream = FramedConnection.this.getStream(i);
            if (stream != null) {
                synchronized (stream) {
                    stream.addBytesToWriteWindow(j);
                }
            }
        }

        public void pushPromise(int i, int i2, List<Header> list) {
            FramedConnection.this.pushRequestLater(i2, list);
        }
    }

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp FramedConnection", true));
        executor = threadPoolExecutor;
    }

    private FramedConnection(Builder builder) throws IOException {
        this.streams = new HashMap();
        this.idleStartTimeNs = System.nanoTime();
        this.unacknowledgedBytesRead = 0;
        this.okHttpSettings = new Settings();
        this.peerSettings = new Settings();
        this.receivedInitialPeerSettings = false;
        this.currentPushRequests = new LinkedHashSet();
        this.protocol = builder.protocol;
        this.pushObserver = builder.pushObserver;
        this.client = builder.client;
        this.handler = builder.handler;
        int i = 2;
        this.nextStreamId = builder.client ? 1 : 2;
        if (builder.client && this.protocol == Protocol.HTTP_2) {
            this.nextStreamId += 2;
        }
        if (builder.client) {
            i = 1;
        }
        this.nextPingId = i;
        if (builder.client) {
            this.okHttpSettings.set(7, 0, C.DEFAULT_MUXED_BUFFER_SIZE);
        }
        this.hostName = builder.hostName;
        if (this.protocol == Protocol.HTTP_2) {
            this.variant = new Http2();
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory(String.format("OkHttp %s Push Observer", new Object[]{this.hostName}), true));
            this.pushExecutor = threadPoolExecutor;
            this.peerSettings.set(7, 0, BusyStates.ALL);
            this.peerSettings.set(5, 0, com.brightcove.player.C.DASH_ROLE_CAPTION_FLAG);
        } else if (this.protocol == Protocol.SPDY_3) {
            this.variant = new Spdy3();
            this.pushExecutor = null;
        } else {
            throw new AssertionError(this.protocol);
        }
        this.bytesLeftInWriteWindow = (long) this.peerSettings.getInitialWindowSize(65536);
        this.socket = builder.socket;
        this.frameWriter = this.variant.newWriter(Okio.buffer(Okio.sink(builder.socket)), this.client);
        this.readerRunnable = new Reader();
        new Thread(this.readerRunnable).start();
    }

    public Protocol getProtocol() {
        return this.protocol;
    }

    /* access modifiers changed from: 0000 */
    public synchronized FramedStream getStream(int i) {
        return (FramedStream) this.streams.get(Integer.valueOf(i));
    }

    /* access modifiers changed from: 0000 */
    public synchronized FramedStream removeStream(int i) {
        FramedStream framedStream;
        framedStream = (FramedStream) this.streams.remove(Integer.valueOf(i));
        if (framedStream != null && this.streams.isEmpty()) {
            setIdle(true);
        }
        notifyAll();
        return framedStream;
    }

    private synchronized void setIdle(boolean z) {
        long j;
        if (z) {
            try {
                j = System.nanoTime();
            } catch (Throwable th) {
                throw th;
            }
        } else {
            j = Long.MAX_VALUE;
        }
        this.idleStartTimeNs = j;
    }

    public synchronized boolean isIdle() {
        return this.idleStartTimeNs != Long.MAX_VALUE;
    }

    public synchronized long getIdleStartTimeNs() {
        return this.idleStartTimeNs;
    }

    public FramedStream newStream(List<Header> list, boolean z, boolean z2) throws IOException {
        return newStream(0, list, z, z2);
    }

    private FramedStream newStream(int i, List<Header> list, boolean z, boolean z2) throws IOException {
        int i2;
        FramedStream framedStream;
        boolean z3 = !z;
        boolean z4 = !z2;
        synchronized (this.frameWriter) {
            synchronized (this) {
                if (!this.shutdown) {
                    i2 = this.nextStreamId;
                    this.nextStreamId += 2;
                    framedStream = new FramedStream(i2, this, z3, z4, list);
                    if (framedStream.isOpen()) {
                        this.streams.put(Integer.valueOf(i2), framedStream);
                        setIdle(false);
                    }
                } else {
                    throw new IOException("shutdown");
                }
            }
            if (i == 0) {
                this.frameWriter.synStream(z3, z4, i2, i, list);
            } else if (!this.client) {
                this.frameWriter.pushPromise(i, i2, list);
            } else {
                throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
            }
        }
        if (!z) {
            this.frameWriter.flush();
        }
        return framedStream;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:26|27|28) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3 = java.lang.Math.min((int) java.lang.Math.min(r12, r8.bytesLeftInWriteWindow), r8.frameWriter.maxDataLength());
        r6 = (long) r3;
        r8.bytesLeftInWriteWindow -= r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005f, code lost:
        throw new java.io.InterruptedIOException();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x005a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeData(int r9, boolean r10, okio.Buffer r11, long r12) throws java.io.IOException {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
            int r3 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r3 != 0) goto L_0x000d
            com.squareup.okhttp.internal.framed.FrameWriter r12 = r8.frameWriter
            r12.data(r10, r9, r11, r0)
            return
        L_0x000d:
            int r3 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r3 <= 0) goto L_0x0062
            monitor-enter(r8)
        L_0x0012:
            long r3 = r8.bytesLeftInWriteWindow     // Catch:{ InterruptedException -> 0x005a }
            int r5 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r5 > 0) goto L_0x0030
            java.util.Map<java.lang.Integer, com.squareup.okhttp.internal.framed.FramedStream> r3 = r8.streams     // Catch:{ InterruptedException -> 0x005a }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch:{ InterruptedException -> 0x005a }
            boolean r3 = r3.containsKey(r4)     // Catch:{ InterruptedException -> 0x005a }
            if (r3 == 0) goto L_0x0028
            r8.wait()     // Catch:{ InterruptedException -> 0x005a }
            goto L_0x0012
        L_0x0028:
            java.io.IOException r9 = new java.io.IOException     // Catch:{ InterruptedException -> 0x005a }
            java.lang.String r10 = "stream closed"
            r9.<init>(r10)     // Catch:{ InterruptedException -> 0x005a }
            throw r9     // Catch:{ InterruptedException -> 0x005a }
        L_0x0030:
            long r3 = r8.bytesLeftInWriteWindow     // Catch:{ all -> 0x0058 }
            long r3 = java.lang.Math.min(r12, r3)     // Catch:{ all -> 0x0058 }
            int r4 = (int) r3     // Catch:{ all -> 0x0058 }
            com.squareup.okhttp.internal.framed.FrameWriter r3 = r8.frameWriter     // Catch:{ all -> 0x0058 }
            int r3 = r3.maxDataLength()     // Catch:{ all -> 0x0058 }
            int r3 = java.lang.Math.min(r4, r3)     // Catch:{ all -> 0x0058 }
            long r4 = r8.bytesLeftInWriteWindow     // Catch:{ all -> 0x0058 }
            long r6 = (long) r3     // Catch:{ all -> 0x0058 }
            long r4 = r4 - r6
            r8.bytesLeftInWriteWindow = r4     // Catch:{ all -> 0x0058 }
            monitor-exit(r8)     // Catch:{ all -> 0x0058 }
            long r12 = r12 - r6
            com.squareup.okhttp.internal.framed.FrameWriter r4 = r8.frameWriter
            if (r10 == 0) goto L_0x0053
            int r5 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r5 != 0) goto L_0x0053
            r5 = 1
            goto L_0x0054
        L_0x0053:
            r5 = 0
        L_0x0054:
            r4.data(r5, r9, r11, r3)
            goto L_0x000d
        L_0x0058:
            r9 = move-exception
            goto L_0x0060
        L_0x005a:
            java.io.InterruptedIOException r9 = new java.io.InterruptedIOException     // Catch:{ all -> 0x0058 }
            r9.<init>()     // Catch:{ all -> 0x0058 }
            throw r9     // Catch:{ all -> 0x0058 }
        L_0x0060:
            monitor-exit(r8)     // Catch:{ all -> 0x0058 }
            throw r9
        L_0x0062:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.framed.FramedConnection.writeData(int, boolean, okio.Buffer, long):void");
    }

    /* access modifiers changed from: 0000 */
    public void addBytesToWriteWindow(long j) {
        this.bytesLeftInWriteWindow += j;
        if (j > 0) {
            notifyAll();
        }
    }

    /* access modifiers changed from: 0000 */
    public void writeSynResetLater(int i, ErrorCode errorCode) {
        ExecutorService executorService = executor;
        final int i2 = i;
        final ErrorCode errorCode2 = errorCode;
        AnonymousClass1 r1 = new NamedRunnable("OkHttp %s stream %d", new Object[]{this.hostName, Integer.valueOf(i)}) {
            public void execute() {
                try {
                    FramedConnection.this.writeSynReset(i2, errorCode2);
                } catch (IOException unused) {
                }
            }
        };
        executorService.submit(r1);
    }

    /* access modifiers changed from: 0000 */
    public void writeSynReset(int i, ErrorCode errorCode) throws IOException {
        this.frameWriter.rstStream(i, errorCode);
    }

    /* access modifiers changed from: 0000 */
    public void writeWindowUpdateLater(int i, long j) {
        ExecutorService executorService = executor;
        final int i2 = i;
        final long j2 = j;
        AnonymousClass2 r1 = new NamedRunnable("OkHttp Window Update %s stream %d", new Object[]{this.hostName, Integer.valueOf(i)}) {
            public void execute() {
                try {
                    FramedConnection.this.frameWriter.windowUpdate(i2, j2);
                } catch (IOException unused) {
                }
            }
        };
        executorService.execute(r1);
    }

    /* access modifiers changed from: private */
    public void writePingLater(boolean z, int i, int i2, Ping ping) {
        ExecutorService executorService = executor;
        final boolean z2 = z;
        final int i3 = i;
        final int i4 = i2;
        final Ping ping2 = ping;
        AnonymousClass3 r1 = new NamedRunnable("OkHttp %s ping %08x%08x", new Object[]{this.hostName, Integer.valueOf(i), Integer.valueOf(i2)}) {
            public void execute() {
                try {
                    FramedConnection.this.writePing(z2, i3, i4, ping2);
                } catch (IOException unused) {
                }
            }
        };
        executorService.execute(r1);
    }

    /* access modifiers changed from: private */
    public void writePing(boolean z, int i, int i2, Ping ping) throws IOException {
        synchronized (this.frameWriter) {
            if (ping != null) {
                ping.send();
            }
            this.frameWriter.ping(z, i, i2);
        }
    }

    /* access modifiers changed from: private */
    public synchronized Ping removePing(int i) {
        return this.pings != null ? (Ping) this.pings.remove(Integer.valueOf(i)) : null;
    }

    public void flush() throws IOException {
        this.frameWriter.flush();
    }

    public void shutdown(ErrorCode errorCode) throws IOException {
        synchronized (this.frameWriter) {
            synchronized (this) {
                if (!this.shutdown) {
                    this.shutdown = true;
                    int i = this.lastGoodStreamId;
                    this.frameWriter.goAway(i, errorCode, Util.EMPTY_BYTE_ARRAY);
                }
            }
        }
    }

    public void close() throws IOException {
        close(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    /* access modifiers changed from: private */
    public void close(ErrorCode errorCode, ErrorCode errorCode2) throws IOException {
        int i;
        FramedStream[] framedStreamArr;
        Ping[] pingArr = null;
        try {
            shutdown(errorCode);
            e = null;
        } catch (IOException e) {
            e = e;
        }
        synchronized (this) {
            if (!this.streams.isEmpty()) {
                framedStreamArr = (FramedStream[]) this.streams.values().toArray(new FramedStream[this.streams.size()]);
                this.streams.clear();
                setIdle(false);
            } else {
                framedStreamArr = null;
            }
            if (this.pings != null) {
                Ping[] pingArr2 = (Ping[]) this.pings.values().toArray(new Ping[this.pings.size()]);
                this.pings = null;
                pingArr = pingArr2;
            }
        }
        if (framedStreamArr != null) {
            IOException iOException = e;
            for (FramedStream close : framedStreamArr) {
                try {
                    close.close(errorCode2);
                } catch (IOException e2) {
                    if (iOException != null) {
                        iOException = e2;
                    }
                }
            }
            e = iOException;
        }
        if (pingArr != null) {
            for (Ping cancel : pingArr) {
                cancel.cancel();
            }
        }
        try {
            this.frameWriter.close();
        } catch (IOException e3) {
            if (e == null) {
                e = e3;
            }
        }
        try {
            this.socket.close();
        } catch (IOException e4) {
            e = e4;
        }
        if (e != null) {
            throw e;
        }
    }

    public void sendConnectionPreface() throws IOException {
        this.frameWriter.connectionPreface();
        this.frameWriter.settings(this.okHttpSettings);
        int initialWindowSize = this.okHttpSettings.getInitialWindowSize(65536);
        if (initialWindowSize != 65536) {
            this.frameWriter.windowUpdate(0, (long) (initialWindowSize - 65536));
        }
    }

    /* access modifiers changed from: private */
    public boolean pushedStream(int i) {
        return this.protocol == Protocol.HTTP_2 && i != 0 && (i & 1) == 0;
    }

    /* access modifiers changed from: private */
    public void pushRequestLater(int i, List<Header> list) {
        synchronized (this) {
            if (this.currentPushRequests.contains(Integer.valueOf(i))) {
                writeSynResetLater(i, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.currentPushRequests.add(Integer.valueOf(i));
            ExecutorService executorService = this.pushExecutor;
            final int i2 = i;
            final List<Header> list2 = list;
            AnonymousClass4 r1 = new NamedRunnable("OkHttp %s Push Request[%s]", new Object[]{this.hostName, Integer.valueOf(i)}) {
                public void execute() {
                    if (FramedConnection.this.pushObserver.onRequest(i2, list2)) {
                        try {
                            FramedConnection.this.frameWriter.rstStream(i2, ErrorCode.CANCEL);
                            synchronized (FramedConnection.this) {
                                FramedConnection.this.currentPushRequests.remove(Integer.valueOf(i2));
                            }
                        } catch (IOException unused) {
                        }
                    }
                }
            };
            executorService.execute(r1);
        }
    }

    /* access modifiers changed from: private */
    public void pushHeadersLater(int i, List<Header> list, boolean z) {
        ExecutorService executorService = this.pushExecutor;
        final int i2 = i;
        final List<Header> list2 = list;
        final boolean z2 = z;
        AnonymousClass5 r1 = new NamedRunnable("OkHttp %s Push Headers[%s]", new Object[]{this.hostName, Integer.valueOf(i)}) {
            public void execute() {
                boolean onHeaders = FramedConnection.this.pushObserver.onHeaders(i2, list2, z2);
                if (onHeaders) {
                    try {
                        FramedConnection.this.frameWriter.rstStream(i2, ErrorCode.CANCEL);
                    } catch (IOException unused) {
                        return;
                    }
                }
                if (onHeaders || z2) {
                    synchronized (FramedConnection.this) {
                        FramedConnection.this.currentPushRequests.remove(Integer.valueOf(i2));
                    }
                }
            }
        };
        executorService.execute(r1);
    }

    /* access modifiers changed from: private */
    public void pushDataLater(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException {
        final Buffer buffer = new Buffer();
        long j = (long) i2;
        bufferedSource.require(j);
        bufferedSource.read(buffer, j);
        if (buffer.size() == j) {
            ExecutorService executorService = this.pushExecutor;
            final int i3 = i;
            final int i4 = i2;
            final boolean z2 = z;
            AnonymousClass6 r0 = new NamedRunnable("OkHttp %s Push Data[%s]", new Object[]{this.hostName, Integer.valueOf(i)}) {
                public void execute() {
                    try {
                        boolean onData = FramedConnection.this.pushObserver.onData(i3, buffer, i4, z2);
                        if (onData) {
                            FramedConnection.this.frameWriter.rstStream(i3, ErrorCode.CANCEL);
                        }
                        if (onData || z2) {
                            synchronized (FramedConnection.this) {
                                FramedConnection.this.currentPushRequests.remove(Integer.valueOf(i3));
                            }
                        }
                    } catch (IOException unused) {
                    }
                }
            };
            executorService.execute(r0);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(buffer.size());
        sb.append(" != ");
        sb.append(i2);
        throw new IOException(sb.toString());
    }

    /* access modifiers changed from: private */
    public void pushResetLater(int i, ErrorCode errorCode) {
        ExecutorService executorService = this.pushExecutor;
        final int i2 = i;
        final ErrorCode errorCode2 = errorCode;
        AnonymousClass7 r1 = new NamedRunnable("OkHttp %s Push Reset[%s]", new Object[]{this.hostName, Integer.valueOf(i)}) {
            public void execute() {
                FramedConnection.this.pushObserver.onReset(i2, errorCode2);
                synchronized (FramedConnection.this) {
                    FramedConnection.this.currentPushRequests.remove(Integer.valueOf(i2));
                }
            }
        };
        executorService.execute(r1);
    }
}
