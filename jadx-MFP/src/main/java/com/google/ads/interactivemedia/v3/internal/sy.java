package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import com.facebook.appevents.AppEventsConstants;
import com.google.common.net.HttpHeaders;
import com.mopub.common.Constants;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

/* compiled from: IMASDK */
public final class sy extends sj implements tb {
    private static final Pattern a = Pattern.compile("^bytes (\\d+)-(\\d+)/(\\d+)$");
    private static final AtomicReference<byte[]> b = new AtomicReference<>();
    private final boolean c;
    private final int d;
    private final int e;
    private final String f;
    private final uv<String> g = null;
    private final th h;
    private final th i = new th();
    private sr j;
    private HttpURLConnection k;
    private InputStream l;
    private boolean m;
    private long n;
    private long o;
    private long p;
    private long q;

    public sy(String str, uv<String> uvVar, int i2, int i3, boolean z, th thVar) {
        super(true);
        this.f = qi.a(str);
        this.d = i2;
        this.e = i3;
        this.c = z;
        this.h = thVar;
    }

    public final Uri a() {
        HttpURLConnection httpURLConnection = this.k;
        if (httpURLConnection == null) {
            return null;
        }
        return Uri.parse(httpURLConnection.getURL().toString());
    }

    public final Map<String, List<String>> b() {
        HttpURLConnection httpURLConnection = this.k;
        return httpURLConnection == null ? Collections.emptyMap() : httpURLConnection.getHeaderFields();
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x011b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long a(com.google.ads.interactivemedia.v3.internal.sr r25) throws com.google.ads.interactivemedia.v3.internal.te {
        /*
            r24 = this;
            r12 = r24
            r13 = r25
            r12.j = r13
            r14 = 0
            r12.q = r14
            r12.p = r14
            r24.b(r25)
            r11 = 1
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x01b2 }
            android.net.Uri r0 = r13.a     // Catch:{ IOException -> 0x01b2 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x01b2 }
            r2.<init>(r0)     // Catch:{ IOException -> 0x01b2 }
            int r3 = r13.b     // Catch:{ IOException -> 0x01b2 }
            byte[] r4 = r13.c     // Catch:{ IOException -> 0x01b2 }
            long r9 = r13.e     // Catch:{ IOException -> 0x01b2 }
            long r7 = r13.f     // Catch:{ IOException -> 0x01b2 }
            boolean r0 = r13.a(r11)     // Catch:{ IOException -> 0x01b2 }
            r5 = 2
            boolean r16 = r13.a(r5)     // Catch:{ IOException -> 0x01b2 }
            boolean r1 = r12.c     // Catch:{ IOException -> 0x01b2 }
            r6 = 0
            if (r1 != 0) goto L_0x0042
            r17 = 1
            r1 = r24
            r5 = r9
            r9 = r0
            r10 = r16
            r14 = 1
            r11 = r17
            java.net.HttpURLConnection r0 = r1.a(r2, r3, r4, r5, r7, r9, r10, r11)     // Catch:{ IOException -> 0x01b2 }
            goto L_0x00b2
        L_0x0042:
            r14 = 1
            r15 = r2
            r11 = r3
            r17 = r4
            r1 = 0
        L_0x0048:
            int r6 = r1 + 1
            r2 = 20
            if (r1 > r2) goto L_0x0195
            r18 = 0
            r1 = r24
            r2 = r15
            r3 = r11
            r4 = r17
            r19 = r6
            r5 = r9
            r20 = r7
            r22 = r9
            r9 = r0
            r10 = r16
            r14 = r11
            r11 = r18
            java.net.HttpURLConnection r1 = r1.a(r2, r3, r4, r5, r7, r9, r10, r11)     // Catch:{ IOException -> 0x01b2 }
            int r2 = r1.getResponseCode()     // Catch:{ IOException -> 0x01b2 }
            java.lang.String r3 = "Location"
            java.lang.String r3 = r1.getHeaderField(r3)     // Catch:{ IOException -> 0x01b2 }
            r4 = 303(0x12f, float:4.25E-43)
            r5 = 302(0x12e, float:4.23E-43)
            r6 = 301(0x12d, float:4.22E-43)
            r7 = 300(0x12c, float:4.2E-43)
            r8 = 1
            if (r14 == r8) goto L_0x0082
            r8 = 3
            if (r14 != r8) goto L_0x0080
            goto L_0x0082
        L_0x0080:
            r8 = 2
            goto L_0x0094
        L_0x0082:
            if (r2 == r7) goto L_0x017f
            if (r2 == r6) goto L_0x017f
            if (r2 == r5) goto L_0x017f
            if (r2 == r4) goto L_0x017f
            r8 = 307(0x133, float:4.3E-43)
            if (r2 == r8) goto L_0x017f
            r8 = 308(0x134, float:4.32E-43)
            if (r2 != r8) goto L_0x0080
            goto L_0x017f
        L_0x0094:
            if (r14 != r8) goto L_0x00b1
            if (r2 == r7) goto L_0x009e
            if (r2 == r6) goto L_0x009e
            if (r2 == r5) goto L_0x009e
            if (r2 != r4) goto L_0x00b1
        L_0x009e:
            r1.disconnect()     // Catch:{ IOException -> 0x01b2 }
            r17 = 0
            java.net.URL r15 = a(r15, r3)     // Catch:{ IOException -> 0x01b2 }
            r1 = r19
            r7 = r20
            r9 = r22
            r5 = 2
            r11 = 1
            r14 = 1
            goto L_0x0048
        L_0x00b1:
            r0 = r1
        L_0x00b2:
            r12.k = r0     // Catch:{ IOException -> 0x01b2 }
            java.net.HttpURLConnection r0 = r12.k     // Catch:{ IOException -> 0x0157 }
            int r0 = r0.getResponseCode()     // Catch:{ IOException -> 0x0157 }
            java.net.HttpURLConnection r1 = r12.k     // Catch:{ IOException -> 0x0157 }
            java.lang.String r1 = r1.getResponseMessage()     // Catch:{ IOException -> 0x0157 }
            r2 = 200(0xc8, float:2.8E-43)
            if (r0 < r2) goto L_0x013b
            r3 = 299(0x12b, float:4.19E-43)
            if (r0 <= r3) goto L_0x00ca
            goto L_0x013b
        L_0x00ca:
            java.net.HttpURLConnection r1 = r12.k
            java.lang.String r1 = r1.getContentType()
            com.google.ads.interactivemedia.v3.internal.uv<java.lang.String> r3 = r12.g
            if (r3 == 0) goto L_0x00e4
            boolean r3 = r3.a(r1)
            if (r3 == 0) goto L_0x00db
            goto L_0x00e4
        L_0x00db:
            r24.e()
            com.google.ads.interactivemedia.v3.internal.tf r0 = new com.google.ads.interactivemedia.v3.internal.tf
            r0.<init>(r1, r13)
            throw r0
        L_0x00e4:
            if (r0 != r2) goto L_0x00f1
            long r0 = r13.e
            r4 = 0
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 == 0) goto L_0x00f3
            long r14 = r13.e
            goto L_0x00f4
        L_0x00f1:
            r4 = 0
        L_0x00f3:
            r14 = r4
        L_0x00f4:
            r12.n = r14
            r1 = 1
            boolean r0 = r13.a(r1)
            if (r0 != 0) goto L_0x011b
            long r0 = r13.f
            r2 = -1
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x010a
            long r0 = r13.f
            r12.o = r0
            goto L_0x011f
        L_0x010a:
            java.net.HttpURLConnection r0 = r12.k
            long r0 = a(r0)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0118
            long r2 = r12.n
            long r2 = r0 - r2
        L_0x0118:
            r12.o = r2
            goto L_0x011f
        L_0x011b:
            long r0 = r13.f
            r12.o = r0
        L_0x011f:
            java.net.HttpURLConnection r0 = r12.k     // Catch:{ IOException -> 0x0130 }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ IOException -> 0x0130 }
            r12.l = r0     // Catch:{ IOException -> 0x0130 }
            r1 = 1
            r12.m = r1
            r24.c(r25)
            long r0 = r12.o
            return r0
        L_0x0130:
            r0 = move-exception
            r24.e()
            com.google.ads.interactivemedia.v3.internal.te r1 = new com.google.ads.interactivemedia.v3.internal.te
            r2 = 1
            r1.<init>(r0, r13, r2)
            throw r1
        L_0x013b:
            java.net.HttpURLConnection r2 = r12.k
            java.util.Map r2 = r2.getHeaderFields()
            r24.e()
            com.google.ads.interactivemedia.v3.internal.tg r3 = new com.google.ads.interactivemedia.v3.internal.tg
            r3.<init>(r0, r1, r2, r13)
            r1 = 416(0x1a0, float:5.83E-43)
            if (r0 != r1) goto L_0x0156
            com.google.ads.interactivemedia.v3.internal.sp r0 = new com.google.ads.interactivemedia.v3.internal.sp
            r2 = 0
            r0.<init>(r2)
            r3.initCause(r0)
        L_0x0156:
            throw r3
        L_0x0157:
            r0 = move-exception
            r24.e()
            com.google.ads.interactivemedia.v3.internal.te r1 = new com.google.ads.interactivemedia.v3.internal.te
            java.lang.String r2 = "Unable to connect to "
            android.net.Uri r3 = r13.a
            java.lang.String r3 = r3.toString()
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r4 = r3.length()
            if (r4 == 0) goto L_0x0174
            java.lang.String r2 = r2.concat(r3)
            goto L_0x017a
        L_0x0174:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r2)
            r2 = r3
        L_0x017a:
            r3 = 1
            r1.<init>(r2, r0, r13, r3)
            throw r1
        L_0x017f:
            r2 = 0
            r4 = 0
            r8 = 2
            r1.disconnect()     // Catch:{ IOException -> 0x01b2 }
            java.net.URL r15 = a(r15, r3)     // Catch:{ IOException -> 0x01b2 }
            r11 = r14
            r1 = r19
            r7 = r20
            r9 = r22
            r5 = 2
            r14 = 1
            goto L_0x0048
        L_0x0195:
            r19 = r6
            java.net.NoRouteToHostException r0 = new java.net.NoRouteToHostException     // Catch:{ IOException -> 0x01b2 }
            r1 = 31
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01b2 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x01b2 }
            java.lang.String r1 = "Too many redirects: "
            r2.append(r1)     // Catch:{ IOException -> 0x01b2 }
            r1 = r19
            r2.append(r1)     // Catch:{ IOException -> 0x01b2 }
            java.lang.String r1 = r2.toString()     // Catch:{ IOException -> 0x01b2 }
            r0.<init>(r1)     // Catch:{ IOException -> 0x01b2 }
            throw r0     // Catch:{ IOException -> 0x01b2 }
        L_0x01b2:
            r0 = move-exception
            com.google.ads.interactivemedia.v3.internal.te r1 = new com.google.ads.interactivemedia.v3.internal.te
            java.lang.String r2 = "Unable to connect to "
            android.net.Uri r3 = r13.a
            java.lang.String r3 = r3.toString()
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r4 = r3.length()
            if (r4 == 0) goto L_0x01cc
            java.lang.String r2 = r2.concat(r3)
            goto L_0x01d2
        L_0x01cc:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r2)
            r2 = r3
        L_0x01d2:
            r3 = 1
            r1.<init>(r2, r0, r13, r3)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.sy.a(com.google.ads.interactivemedia.v3.internal.sr):long");
    }

    public final int a(byte[] bArr, int i2, int i3) throws te {
        try {
            if (this.p != this.n) {
                byte[] bArr2 = (byte[]) b.getAndSet(null);
                if (bArr2 == null) {
                    bArr2 = new byte[4096];
                }
                while (this.p != this.n) {
                    int read = this.l.read(bArr2, 0, (int) Math.min(this.n - this.p, (long) bArr2.length));
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedIOException();
                    } else if (read != -1) {
                        this.p += (long) read;
                        a(read);
                    } else {
                        throw new EOFException();
                    }
                }
                b.set(bArr2);
            }
            if (i3 == 0) {
                return 0;
            }
            if (this.o != -1) {
                long j2 = this.o - this.q;
                if (j2 == 0) {
                    return -1;
                }
                i3 = (int) Math.min((long) i3, j2);
            }
            int read2 = this.l.read(bArr, i2, i3);
            if (read2 != -1) {
                this.q += (long) read2;
                a(read2);
                return read2;
            } else if (this.o == -1) {
                return -1;
            } else {
                throw new EOFException();
            }
        } catch (IOException e2) {
            throw new te(e2, this.j, 2);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:4|(1:6)(1:7)|8|(5:13|14|(2:16|(1:18))(2:19|(1:21))|22|(1:26))|27|28) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c() throws com.google.ads.interactivemedia.v3.internal.te {
        /*
            r9 = this;
            r0 = 0
            r1 = 0
            java.io.InputStream r2 = r9.l     // Catch:{ all -> 0x008c }
            if (r2 == 0) goto L_0x007c
            java.net.HttpURLConnection r2 = r9.k     // Catch:{ all -> 0x008c }
            long r3 = r9.o     // Catch:{ all -> 0x008c }
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0013
            long r3 = r9.o     // Catch:{ all -> 0x008c }
            goto L_0x0018
        L_0x0013:
            long r3 = r9.o     // Catch:{ all -> 0x008c }
            long r7 = r9.q     // Catch:{ all -> 0x008c }
            long r3 = r3 - r7
        L_0x0018:
            int r7 = com.google.ads.interactivemedia.v3.internal.vf.a     // Catch:{ all -> 0x008c }
            r8 = 19
            if (r7 == r8) goto L_0x0024
            int r7 = com.google.ads.interactivemedia.v3.internal.vf.a     // Catch:{ all -> 0x008c }
            r8 = 20
            if (r7 != r8) goto L_0x006c
        L_0x0024:
            java.io.InputStream r2 = r2.getInputStream()     // Catch:{ Exception -> 0x006c }
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0034
            int r3 = r2.read()     // Catch:{ Exception -> 0x006c }
            r4 = -1
            if (r3 != r4) goto L_0x003b
            goto L_0x006c
        L_0x0034:
            r5 = 2048(0x800, double:1.0118E-320)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 > 0) goto L_0x003b
            goto L_0x006c
        L_0x003b:
            java.lang.Class r3 = r2.getClass()     // Catch:{ Exception -> 0x006c }
            java.lang.String r3 = r3.getName()     // Catch:{ Exception -> 0x006c }
            java.lang.String r4 = "com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream"
            boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x006c }
            if (r4 != 0) goto L_0x0053
            java.lang.String r4 = "com.android.okhttp.internal.http.HttpTransport$FixedLengthInputStream"
            boolean r3 = r4.equals(r3)     // Catch:{ Exception -> 0x006c }
            if (r3 == 0) goto L_0x006c
        L_0x0053:
            java.lang.Class r3 = r2.getClass()     // Catch:{ Exception -> 0x006c }
            java.lang.Class r3 = r3.getSuperclass()     // Catch:{ Exception -> 0x006c }
            java.lang.String r4 = "unexpectedEndOfInput"
            java.lang.Class[] r5 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x006c }
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r5)     // Catch:{ Exception -> 0x006c }
            r4 = 1
            r3.setAccessible(r4)     // Catch:{ Exception -> 0x006c }
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x006c }
            r3.invoke(r2, r4)     // Catch:{ Exception -> 0x006c }
        L_0x006c:
            java.io.InputStream r2 = r9.l     // Catch:{ IOException -> 0x0072 }
            r2.close()     // Catch:{ IOException -> 0x0072 }
            goto L_0x007c
        L_0x0072:
            r2 = move-exception
            com.google.ads.interactivemedia.v3.internal.te r3 = new com.google.ads.interactivemedia.v3.internal.te     // Catch:{ all -> 0x008c }
            com.google.ads.interactivemedia.v3.internal.sr r4 = r9.j     // Catch:{ all -> 0x008c }
            r5 = 3
            r3.<init>(r2, r4, r5)     // Catch:{ all -> 0x008c }
            throw r3     // Catch:{ all -> 0x008c }
        L_0x007c:
            r9.l = r0
            r9.e()
            boolean r0 = r9.m
            if (r0 == 0) goto L_0x008b
            r9.m = r1
            r9.d()
            return
        L_0x008b:
            return
        L_0x008c:
            r2 = move-exception
            r9.l = r0
            r9.e()
            boolean r0 = r9.m
            if (r0 == 0) goto L_0x009b
            r9.m = r1
            r9.d()
        L_0x009b:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.sy.c():void");
    }

    private final HttpURLConnection a(URL url, int i2, byte[] bArr, long j2, long j3, boolean z, boolean z2, boolean z3) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(this.d);
        httpURLConnection.setReadTimeout(this.e);
        th thVar = this.h;
        if (thVar != null) {
            for (Entry entry : thVar.a().entrySet()) {
                httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
        }
        for (Entry entry2 : this.i.a().entrySet()) {
            httpURLConnection.setRequestProperty((String) entry2.getKey(), (String) entry2.getValue());
        }
        if (!(j2 == 0 && j3 == -1)) {
            StringBuilder sb = new StringBuilder(27);
            sb.append("bytes=");
            sb.append(j2);
            sb.append("-");
            String sb2 = sb.toString();
            if (j3 != -1) {
                String valueOf = String.valueOf(sb2);
                long j4 = (j2 + j3) - 1;
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 20);
                sb3.append(valueOf);
                sb3.append(j4);
                sb2 = sb3.toString();
            }
            httpURLConnection.setRequestProperty(HttpHeaders.RANGE, sb2);
        }
        httpURLConnection.setRequestProperty("User-Agent", this.f);
        if (!z) {
            httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "identity");
        }
        if (z2) {
            httpURLConnection.setRequestProperty("Icy-MetaData", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
        httpURLConnection.setInstanceFollowRedirects(z3);
        httpURLConnection.setDoOutput(bArr != null);
        httpURLConnection.setRequestMethod(sr.b(i2));
        if (bArr != null) {
            httpURLConnection.setFixedLengthStreamingMode(bArr.length);
            httpURLConnection.connect();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(bArr);
            outputStream.close();
        } else {
            httpURLConnection.connect();
        }
        return httpURLConnection;
    }

    private static URL a(URL url, String str) throws IOException {
        if (str != null) {
            URL url2 = new URL(url, str);
            String protocol = url2.getProtocol();
            if (Constants.HTTPS.equals(protocol) || Constants.HTTP.equals(protocol)) {
                return url2;
            }
            String str2 = "Unsupported protocol redirect: ";
            String valueOf = String.valueOf(protocol);
            throw new ProtocolException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        throw new ProtocolException("Null location redirect");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long a(java.net.HttpURLConnection r8) {
        /*
            java.lang.String r0 = "Content-Length"
            java.lang.String r0 = r8.getHeaderField(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x0036
            long r1 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x0011 }
            goto L_0x0038
        L_0x0011:
            java.lang.String r1 = "DefaultHttpDataSource"
            java.lang.String r2 = java.lang.String.valueOf(r0)
            int r2 = r2.length()
            int r2 = r2 + 28
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Unexpected Content-Length ["
            r3.append(r2)
            r3.append(r0)
            java.lang.String r2 = "]"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            android.util.Log.e(r1, r2)
        L_0x0036:
            r1 = -1
        L_0x0038:
            java.lang.String r3 = "Content-Range"
            java.lang.String r8 = r8.getHeaderField(r3)
            boolean r3 = android.text.TextUtils.isEmpty(r8)
            if (r3 != 0) goto L_0x00d4
            java.util.regex.Pattern r3 = a
            java.util.regex.Matcher r3 = r3.matcher(r8)
            boolean r4 = r3.find()
            if (r4 == 0) goto L_0x00d4
            r4 = 2
            java.lang.String r4 = r3.group(r4)     // Catch:{ NumberFormatException -> 0x00af }
            long r4 = java.lang.Long.parseLong(r4)     // Catch:{ NumberFormatException -> 0x00af }
            r6 = 1
            java.lang.String r3 = r3.group(r6)     // Catch:{ NumberFormatException -> 0x00af }
            long r6 = java.lang.Long.parseLong(r3)     // Catch:{ NumberFormatException -> 0x00af }
            long r4 = r4 - r6
            r6 = 1
            long r3 = r4 + r6
            r5 = 0
            int r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r7 >= 0) goto L_0x006f
            r1 = r3
            goto L_0x00d4
        L_0x006f:
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x00d4
            java.lang.String r5 = "DefaultHttpDataSource"
            java.lang.String r6 = java.lang.String.valueOf(r0)     // Catch:{ NumberFormatException -> 0x00af }
            int r6 = r6.length()     // Catch:{ NumberFormatException -> 0x00af }
            int r6 = r6 + 26
            java.lang.String r7 = java.lang.String.valueOf(r8)     // Catch:{ NumberFormatException -> 0x00af }
            int r7 = r7.length()     // Catch:{ NumberFormatException -> 0x00af }
            int r6 = r6 + r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x00af }
            r7.<init>(r6)     // Catch:{ NumberFormatException -> 0x00af }
            java.lang.String r6 = "Inconsistent headers ["
            r7.append(r6)     // Catch:{ NumberFormatException -> 0x00af }
            r7.append(r0)     // Catch:{ NumberFormatException -> 0x00af }
            java.lang.String r0 = "] ["
            r7.append(r0)     // Catch:{ NumberFormatException -> 0x00af }
            r7.append(r8)     // Catch:{ NumberFormatException -> 0x00af }
            java.lang.String r0 = "]"
            r7.append(r0)     // Catch:{ NumberFormatException -> 0x00af }
            java.lang.String r0 = r7.toString()     // Catch:{ NumberFormatException -> 0x00af }
            android.util.Log.w(r5, r0)     // Catch:{ NumberFormatException -> 0x00af }
            long r0 = java.lang.Math.max(r1, r3)     // Catch:{ NumberFormatException -> 0x00af }
            r1 = r0
            goto L_0x00d4
        L_0x00af:
            java.lang.String r0 = "DefaultHttpDataSource"
            java.lang.String r3 = java.lang.String.valueOf(r8)
            int r3 = r3.length()
            int r3 = r3 + 27
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Unexpected Content-Range ["
            r4.append(r3)
            r4.append(r8)
            java.lang.String r8 = "]"
            r4.append(r8)
            java.lang.String r8 = r4.toString()
            android.util.Log.e(r0, r8)
        L_0x00d4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.sy.a(java.net.HttpURLConnection):long");
    }

    private final void e() {
        HttpURLConnection httpURLConnection = this.k;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e2) {
                uk.b("DefaultHttpDataSource", "Unexpected error while disconnecting", e2);
            }
            this.k = null;
        }
    }
}
