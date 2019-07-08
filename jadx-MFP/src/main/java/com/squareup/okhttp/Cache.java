package com.squareup.okhttp;

import com.myfitnesspal.shared.constants.HttpConstants;
import com.squareup.okhttp.Headers.Builder;
import com.squareup.okhttp.internal.DiskLruCache;
import com.squareup.okhttp.internal.DiskLruCache.Editor;
import com.squareup.okhttp.internal.DiskLruCache.Snapshot;
import com.squareup.okhttp.internal.InternalCache;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.CacheRequest;
import com.squareup.okhttp.internal.http.CacheStrategy;
import com.squareup.okhttp.internal.http.HttpMethod;
import com.squareup.okhttp.internal.http.OkHeaders;
import com.squareup.okhttp.internal.http.StatusLine;
import com.squareup.okhttp.internal.io.FileSystem;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class Cache {
    private final DiskLruCache cache;
    private int hitCount;
    final InternalCache internalCache;
    private int networkCount;
    private int requestCount;
    /* access modifiers changed from: private */
    public int writeAbortCount;
    /* access modifiers changed from: private */
    public int writeSuccessCount;

    private final class CacheRequestImpl implements CacheRequest {
        private Sink body;
        private Sink cacheOut;
        /* access modifiers changed from: private */
        public boolean done;
        private final Editor editor;

        public CacheRequestImpl(final Editor editor2) throws IOException {
            this.editor = editor2;
            this.cacheOut = editor2.newSink(1);
            this.body = new ForwardingSink(this.cacheOut, Cache.this) {
                public void close() throws IOException {
                    synchronized (Cache.this) {
                        if (!CacheRequestImpl.this.done) {
                            CacheRequestImpl.this.done = true;
                            
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0019: INVOKE  (wrap: com.squareup.okhttp.Cache
                                  0x0017: IGET  (r1v5 com.squareup.okhttp.Cache) = (wrap: com.squareup.okhttp.Cache$CacheRequestImpl
                                  0x0015: IGET  (r1v4 com.squareup.okhttp.Cache$CacheRequestImpl) = (r3v0 'this' com.squareup.okhttp.Cache$CacheRequestImpl$1 A[THIS]) com.squareup.okhttp.Cache.CacheRequestImpl.1.this$1 com.squareup.okhttp.Cache$CacheRequestImpl) com.squareup.okhttp.Cache.CacheRequestImpl.this$0 com.squareup.okhttp.Cache) com.squareup.okhttp.Cache.access$808(com.squareup.okhttp.Cache):int type: STATIC in method: com.squareup.okhttp.Cache.CacheRequestImpl.1.close():void, dex: classes4.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                	at jadx.core.codegen.RegionGen.makeSynchronizedRegion(RegionGen.java:248)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:70)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                                	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                                	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                                	at jadx.core.codegen.ClassGen.addInnerClasses(ClassGen.java:237)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:224)
                                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
                                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
                                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
                                	at jadx.core.ProcessClass.process(ProcessClass.java:36)
                                	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                                	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                                	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                                Caused by: org.objenesis.ObjenesisException: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                                	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:57)
                                	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.newConstructorForSerialization(SunReflectionFactoryHelper.java:37)
                                	at org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.<init>(SunReflectionFactoryInstantiator.java:41)
                                	at org.objenesis.strategy.StdInstantiatorStrategy.newInstantiatorOf(StdInstantiatorStrategy.java:68)
                                	at org.objenesis.ObjenesisBase.getInstantiatorOf(ObjenesisBase.java:94)
                                	at org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
                                	at com.rits.cloning.ObjenesisInstantiationStrategy.newInstance(ObjenesisInstantiationStrategy.java:17)
                                	at com.rits.cloning.Cloner.newInstance(Cloner.java:300)
                                	at com.rits.cloning.Cloner.cloneObject(Cloner.java:461)
                                	at com.rits.cloning.Cloner.cloneInternal(Cloner.java:456)
                                	at com.rits.cloning.Cloner.deepClone(Cloner.java:326)
                                	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:352)
                                	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                                	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:24)
                                	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:9)
                                	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                                	... 53 more
                                Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                                	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                                	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                                	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                                	at java.base/java.lang.Class.forName0(Native Method)
                                	at java.base/java.lang.Class.forName(Unknown Source)
                                	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                                	... 71 more
                                */
                            /*
                                this = this;
                                com.squareup.okhttp.Cache$CacheRequestImpl r0 = com.squareup.okhttp.Cache.CacheRequestImpl.this
                                com.squareup.okhttp.Cache r0 = com.squareup.okhttp.Cache.this
                                monitor-enter(r0)
                                com.squareup.okhttp.Cache$CacheRequestImpl r1 = com.squareup.okhttp.Cache.CacheRequestImpl.this     // Catch:{ all -> 0x0026 }
                                boolean r1 = r1.done     // Catch:{ all -> 0x0026 }
                                if (r1 == 0) goto L_0x000f     // Catch:{ all -> 0x0026 }
                                monitor-exit(r0)     // Catch:{ all -> 0x0026 }
                                return     // Catch:{ all -> 0x0026 }
                            L_0x000f:
                                com.squareup.okhttp.Cache$CacheRequestImpl r1 = com.squareup.okhttp.Cache.CacheRequestImpl.this     // Catch:{ all -> 0x0026 }
                                r2 = 1     // Catch:{ all -> 0x0026 }
                                r1.done = r2     // Catch:{ all -> 0x0026 }
                                com.squareup.okhttp.Cache$CacheRequestImpl r1 = com.squareup.okhttp.Cache.CacheRequestImpl.this     // Catch:{ all -> 0x0026 }
                                com.squareup.okhttp.Cache r1 = com.squareup.okhttp.Cache.this     // Catch:{ all -> 0x0026 }
                                
                                // error: 0x0019: INVOKE  (r1 I:com.squareup.okhttp.Cache) com.squareup.okhttp.Cache.access$808(com.squareup.okhttp.Cache):int type: STATIC
                                monitor-exit(r0)     // Catch:{ all -> 0x0026 }
                                super.close()
                                com.squareup.okhttp.internal.DiskLruCache$Editor r0 = r4
                                r0.commit()
                                return
                            L_0x0026:
                                r1 = move-exception
                                monitor-exit(r0)     // Catch:{ all -> 0x0026 }
                                throw r1
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.Cache.CacheRequestImpl.AnonymousClass1.close():void");
                        }
                    };
                }

                /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
                    r2.editor.abort();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
                    com.squareup.okhttp.internal.Util.closeQuietly((java.io.Closeable) r2.cacheOut);
                 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void abort() {
                    /*
                        r2 = this;
                        com.squareup.okhttp.Cache r0 = com.squareup.okhttp.Cache.this
                        monitor-enter(r0)
                        boolean r1 = r2.done     // Catch:{ all -> 0x001d }
                        if (r1 == 0) goto L_0x0009
                        monitor-exit(r0)     // Catch:{ all -> 0x001d }
                        return
                    L_0x0009:
                        r1 = 1
                        r2.done = r1     // Catch:{ all -> 0x001d }
                        com.squareup.okhttp.Cache r1 = com.squareup.okhttp.Cache.this     // Catch:{ all -> 0x001d }
                        
                        // error: 0x000e: INVOKE  (r1 I:com.squareup.okhttp.Cache) com.squareup.okhttp.Cache.access$908(com.squareup.okhttp.Cache):int type: STATIC
                        monitor-exit(r0)     // Catch:{ all -> 0x001d }
                        okio.Sink r0 = r2.cacheOut
                        com.squareup.okhttp.internal.Util.closeQuietly(r0)
                        com.squareup.okhttp.internal.DiskLruCache$Editor r0 = r2.editor     // Catch:{ IOException -> 0x001c }
                        r0.abort()     // Catch:{ IOException -> 0x001c }
                    L_0x001c:
                        return
                    L_0x001d:
                        r1 = move-exception
                        monitor-exit(r0)     // Catch:{ all -> 0x001d }
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.Cache.CacheRequestImpl.abort():void");
                }

                public Sink body() {
                    return this.body;
                }
            }

            private static class CacheResponseBody extends ResponseBody {
                private final BufferedSource bodySource;
                private final String contentLength;
                private final String contentType;
                /* access modifiers changed from: private */
                public final Snapshot snapshot;

                public CacheResponseBody(final Snapshot snapshot2, String str, String str2) {
                    this.snapshot = snapshot2;
                    this.contentType = str;
                    this.contentLength = str2;
                    this.bodySource = Okio.buffer((Source) new ForwardingSource(snapshot2.getSource(1)) {
                        public void close() throws IOException {
                            snapshot2.close();
                            super.close();
                        }
                    });
                }

                public long contentLength() {
                    long j = -1;
                    try {
                        if (this.contentLength != null) {
                            j = Long.parseLong(this.contentLength);
                        }
                        return j;
                    } catch (NumberFormatException unused) {
                        return -1;
                    }
                }

                public BufferedSource source() {
                    return this.bodySource;
                }
            }

            private static final class Entry {
                private final int code;
                private final Handshake handshake;
                private final String message;
                private final Protocol protocol;
                private final String requestMethod;
                private final Headers responseHeaders;
                private final String url;
                private final Headers varyHeaders;

                public Entry(Source source) throws IOException {
                    try {
                        BufferedSource buffer = Okio.buffer(source);
                        this.url = buffer.readUtf8LineStrict();
                        this.requestMethod = buffer.readUtf8LineStrict();
                        Builder builder = new Builder();
                        int access$1000 = Cache.readInt(buffer);
                        for (int i = 0; i < access$1000; i++) {
                            builder.addLenient(buffer.readUtf8LineStrict());
                        }
                        this.varyHeaders = builder.build();
                        StatusLine parse = StatusLine.parse(buffer.readUtf8LineStrict());
                        this.protocol = parse.protocol;
                        this.code = parse.code;
                        this.message = parse.message;
                        Builder builder2 = new Builder();
                        int access$10002 = Cache.readInt(buffer);
                        for (int i2 = 0; i2 < access$10002; i2++) {
                            builder2.addLenient(buffer.readUtf8LineStrict());
                        }
                        this.responseHeaders = builder2.build();
                        if (isHttps()) {
                            String readUtf8LineStrict = buffer.readUtf8LineStrict();
                            if (readUtf8LineStrict.length() <= 0) {
                                this.handshake = Handshake.get(buffer.readUtf8LineStrict(), readCertificateList(buffer), readCertificateList(buffer));
                            } else {
                                StringBuilder sb = new StringBuilder();
                                sb.append("expected \"\" but was \"");
                                sb.append(readUtf8LineStrict);
                                sb.append("\"");
                                throw new IOException(sb.toString());
                            }
                        } else {
                            this.handshake = null;
                        }
                    } finally {
                        source.close();
                    }
                }

                public Entry(Response response) {
                    this.url = response.request().urlString();
                    this.varyHeaders = OkHeaders.varyHeaders(response);
                    this.requestMethod = response.request().method();
                    this.protocol = response.protocol();
                    this.code = response.code();
                    this.message = response.message();
                    this.responseHeaders = response.headers();
                    this.handshake = response.handshake();
                }

                public void writeTo(Editor editor) throws IOException {
                    BufferedSink buffer = Okio.buffer(editor.newSink(0));
                    buffer.writeUtf8(this.url);
                    buffer.writeByte(10);
                    buffer.writeUtf8(this.requestMethod);
                    buffer.writeByte(10);
                    buffer.writeDecimalLong((long) this.varyHeaders.size());
                    buffer.writeByte(10);
                    int size = this.varyHeaders.size();
                    for (int i = 0; i < size; i++) {
                        buffer.writeUtf8(this.varyHeaders.name(i));
                        buffer.writeUtf8(": ");
                        buffer.writeUtf8(this.varyHeaders.value(i));
                        buffer.writeByte(10);
                    }
                    buffer.writeUtf8(new StatusLine(this.protocol, this.code, this.message).toString());
                    buffer.writeByte(10);
                    buffer.writeDecimalLong((long) this.responseHeaders.size());
                    buffer.writeByte(10);
                    int size2 = this.responseHeaders.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        buffer.writeUtf8(this.responseHeaders.name(i2));
                        buffer.writeUtf8(": ");
                        buffer.writeUtf8(this.responseHeaders.value(i2));
                        buffer.writeByte(10);
                    }
                    if (isHttps()) {
                        buffer.writeByte(10);
                        buffer.writeUtf8(this.handshake.cipherSuite());
                        buffer.writeByte(10);
                        writeCertList(buffer, this.handshake.peerCertificates());
                        writeCertList(buffer, this.handshake.localCertificates());
                    }
                    buffer.close();
                }

                private boolean isHttps() {
                    return this.url.startsWith("https://");
                }

                private List<Certificate> readCertificateList(BufferedSource bufferedSource) throws IOException {
                    int access$1000 = Cache.readInt(bufferedSource);
                    if (access$1000 == -1) {
                        return Collections.emptyList();
                    }
                    try {
                        CertificateFactory instance = CertificateFactory.getInstance("X.509");
                        ArrayList arrayList = new ArrayList(access$1000);
                        for (int i = 0; i < access$1000; i++) {
                            String readUtf8LineStrict = bufferedSource.readUtf8LineStrict();
                            Buffer buffer = new Buffer();
                            buffer.write(ByteString.decodeBase64(readUtf8LineStrict));
                            arrayList.add(instance.generateCertificate(buffer.inputStream()));
                        }
                        return arrayList;
                    } catch (CertificateException e) {
                        throw new IOException(e.getMessage());
                    }
                }

                private void writeCertList(BufferedSink bufferedSink, List<Certificate> list) throws IOException {
                    try {
                        bufferedSink.writeDecimalLong((long) list.size());
                        bufferedSink.writeByte(10);
                        int size = list.size();
                        for (int i = 0; i < size; i++) {
                            bufferedSink.writeUtf8(ByteString.of(((Certificate) list.get(i)).getEncoded()).base64());
                            bufferedSink.writeByte(10);
                        }
                    } catch (CertificateEncodingException e) {
                        throw new IOException(e.getMessage());
                    }
                }

                public boolean matches(Request request, Response response) {
                    return this.url.equals(request.urlString()) && this.requestMethod.equals(request.method()) && OkHeaders.varyMatches(response, this.varyHeaders, request);
                }

                public Response response(Request request, Snapshot snapshot) {
                    String str = this.responseHeaders.get("Content-Type");
                    String str2 = this.responseHeaders.get("Content-Length");
                    return new Response.Builder().request(new Request.Builder().url(this.url).method(this.requestMethod, null).headers(this.varyHeaders).build()).protocol(this.protocol).code(this.code).message(this.message).headers(this.responseHeaders).body(new CacheResponseBody(snapshot, str, str2)).handshake(this.handshake).build();
                }
            }

            public Cache(File file, long j) {
                this(file, j, FileSystem.SYSTEM);
            }

            Cache(File file, long j, FileSystem fileSystem) {
                this.internalCache = new InternalCache() {
                    public Response get(Request request) throws IOException {
                        return Cache.this.get(request);
                    }

                    public CacheRequest put(Response response) throws IOException {
                        return Cache.this.put(response);
                    }

                    public void remove(Request request) throws IOException {
                        Cache.this.remove(request);
                    }

                    public void update(Response response, Response response2) throws IOException {
                        Cache.this.update(response, response2);
                    }

                    public void trackConditionalCacheHit() {
                        Cache.this.trackConditionalCacheHit();
                    }

                    public void trackResponse(CacheStrategy cacheStrategy) {
                        Cache.this.trackResponse(cacheStrategy);
                    }
                };
                this.cache = DiskLruCache.create(fileSystem, file, 201105, 2, j);
            }

            private static String urlToKey(Request request) {
                return Util.md5Hex(request.urlString());
            }

            /* access modifiers changed from: 0000 */
            public Response get(Request request) {
                try {
                    Snapshot snapshot = this.cache.get(urlToKey(request));
                    if (snapshot == null) {
                        return null;
                    }
                    try {
                        Entry entry = new Entry(snapshot.getSource(0));
                        Response response = entry.response(request, snapshot);
                        if (entry.matches(request, response)) {
                            return response;
                        }
                        Util.closeQuietly((Closeable) response.body());
                        return null;
                    } catch (IOException unused) {
                        Util.closeQuietly((Closeable) snapshot);
                        return null;
                    }
                } catch (IOException unused2) {
                    return null;
                }
            }

            /* access modifiers changed from: private */
            public CacheRequest put(Response response) throws IOException {
                Editor editor;
                String method = response.request().method();
                if (HttpMethod.invalidatesCache(response.request().method())) {
                    try {
                        remove(response.request());
                    } catch (IOException unused) {
                    }
                    return null;
                } else if (!method.equals(HttpConstants.METHOD_GET) || OkHeaders.hasVaryAll(response)) {
                    return null;
                } else {
                    Entry entry = new Entry(response);
                    try {
                        editor = this.cache.edit(urlToKey(response.request()));
                        if (editor == null) {
                            return null;
                        }
                        try {
                            entry.writeTo(editor);
                            return new CacheRequestImpl(editor);
                        } catch (IOException unused2) {
                            abortQuietly(editor);
                            return null;
                        }
                    } catch (IOException unused3) {
                        editor = null;
                        abortQuietly(editor);
                        return null;
                    }
                }
            }

            /* access modifiers changed from: private */
            public void remove(Request request) throws IOException {
                this.cache.remove(urlToKey(request));
            }

            /* access modifiers changed from: private */
            public void update(Response response, Response response2) {
                Editor editor;
                Entry entry = new Entry(response2);
                try {
                    editor = ((CacheResponseBody) response.body()).snapshot.edit();
                    if (editor != null) {
                        try {
                            entry.writeTo(editor);
                            editor.commit();
                        } catch (IOException unused) {
                        }
                    }
                } catch (IOException unused2) {
                    editor = null;
                    abortQuietly(editor);
                }
            }

            private void abortQuietly(Editor editor) {
                if (editor != null) {
                    try {
                        editor.abort();
                    } catch (IOException unused) {
                    }
                }
            }

            public void close() throws IOException {
                this.cache.close();
            }

            /* access modifiers changed from: private */
            public synchronized void trackResponse(CacheStrategy cacheStrategy) {
                this.requestCount++;
                if (cacheStrategy.networkRequest != null) {
                    this.networkCount++;
                } else if (cacheStrategy.cacheResponse != null) {
                    this.hitCount++;
                }
            }

            /* access modifiers changed from: private */
            public synchronized void trackConditionalCacheHit() {
                this.hitCount++;
            }

            /* access modifiers changed from: private */
            public static int readInt(BufferedSource bufferedSource) throws IOException {
                try {
                    long readDecimalLong = bufferedSource.readDecimalLong();
                    String readUtf8LineStrict = bufferedSource.readUtf8LineStrict();
                    if (readDecimalLong >= 0 && readDecimalLong <= 2147483647L && readUtf8LineStrict.isEmpty()) {
                        return (int) readDecimalLong;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("expected an int but was \"");
                    sb.append(readDecimalLong);
                    sb.append(readUtf8LineStrict);
                    sb.append("\"");
                    throw new IOException(sb.toString());
                } catch (NumberFormatException e) {
                    throw new IOException(e.getMessage());
                }
            }
        }
