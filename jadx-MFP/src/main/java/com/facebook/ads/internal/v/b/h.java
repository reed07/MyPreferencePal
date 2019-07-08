package com.facebook.ads.internal.v.b;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.google.common.net.HttpHeaders;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class h implements n {
    public final String a;
    private HttpURLConnection b;
    private InputStream c;
    private volatile int d;
    private volatile String e;

    public h(h hVar) {
        this.d = Integer.MIN_VALUE;
        this.a = hVar.a;
        this.e = hVar.e;
        this.d = hVar.d;
    }

    public h(String str) {
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        this(str, TextUtils.isEmpty(fileExtensionFromUrl) ? null : singleton.getMimeTypeFromExtension(fileExtensionFromUrl));
    }

    public h(String str, String str2) {
        this.d = Integer.MIN_VALUE;
        this.a = (String) j.a(str);
        this.e = str2;
    }

    private HttpURLConnection a(int i, int i2) {
        String str;
        HttpURLConnection httpURLConnection;
        boolean z;
        String str2 = this.a;
        int i3 = 0;
        do {
            String str3 = "ProxyCache";
            StringBuilder sb = new StringBuilder();
            sb.append("Open connection ");
            if (i > 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(" with offset ");
                sb2.append(i);
                str = sb2.toString();
            } else {
                str = "";
            }
            sb.append(str);
            sb.append(" to ");
            sb.append(str2);
            Log.d(str3, sb.toString());
            httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
            if (i > 0) {
                String str4 = HttpHeaders.RANGE;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("bytes=");
                sb3.append(i);
                sb3.append("-");
                httpURLConnection.setRequestProperty(str4, sb3.toString());
            }
            if (i2 > 0) {
                httpURLConnection.setConnectTimeout(i2);
                httpURLConnection.setReadTimeout(i2);
            }
            int responseCode = httpURLConnection.getResponseCode();
            z = responseCode == 301 || responseCode == 302 || responseCode == 303;
            if (z) {
                str2 = httpURLConnection.getHeaderField(HttpHeaders.LOCATION);
                i3++;
                httpURLConnection.disconnect();
            }
            if (i3 > 5) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Too many redirects: ");
                sb4.append(i3);
                throw new l(sb4.toString());
            }
        } while (z);
        return httpURLConnection;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d() {
        /*
            r6 = this;
            java.lang.String r0 = "ProxyCache"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Read content info from "
            r1.append(r2)
            java.lang.String r2 = r6.a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r0, r1)
            r0 = 0
            r1 = 10000(0x2710, float:1.4013E-41)
            r2 = 0
            java.net.HttpURLConnection r0 = r6.a(r0, r1)     // Catch:{ IOException -> 0x0069, all -> 0x0066 }
            int r1 = r0.getContentLength()     // Catch:{ IOException -> 0x0064 }
            r6.d = r1     // Catch:{ IOException -> 0x0064 }
            java.lang.String r1 = r0.getContentType()     // Catch:{ IOException -> 0x0064 }
            r6.e = r1     // Catch:{ IOException -> 0x0064 }
            java.io.InputStream r2 = r0.getInputStream()     // Catch:{ IOException -> 0x0064 }
            java.lang.String r1 = "ProxyCache"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0064 }
            r3.<init>()     // Catch:{ IOException -> 0x0064 }
            java.lang.String r4 = "Content info for `"
            r3.append(r4)     // Catch:{ IOException -> 0x0064 }
            java.lang.String r4 = r6.a     // Catch:{ IOException -> 0x0064 }
            r3.append(r4)     // Catch:{ IOException -> 0x0064 }
            java.lang.String r4 = "`: mime: "
            r3.append(r4)     // Catch:{ IOException -> 0x0064 }
            java.lang.String r4 = r6.e     // Catch:{ IOException -> 0x0064 }
            r3.append(r4)     // Catch:{ IOException -> 0x0064 }
            java.lang.String r4 = ", content-length: "
            r3.append(r4)     // Catch:{ IOException -> 0x0064 }
            int r4 = r6.d     // Catch:{ IOException -> 0x0064 }
            r3.append(r4)     // Catch:{ IOException -> 0x0064 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0064 }
            android.util.Log.i(r1, r3)     // Catch:{ IOException -> 0x0064 }
            com.facebook.ads.internal.v.b.m.a(r2)
            if (r0 == 0) goto L_0x008b
            goto L_0x0088
        L_0x0062:
            r1 = move-exception
            goto L_0x008c
        L_0x0064:
            r1 = move-exception
            goto L_0x006b
        L_0x0066:
            r1 = move-exception
            r0 = r2
            goto L_0x008c
        L_0x0069:
            r1 = move-exception
            r0 = r2
        L_0x006b:
            java.lang.String r3 = "ProxyCache"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0062 }
            r4.<init>()     // Catch:{ all -> 0x0062 }
            java.lang.String r5 = "Error fetching info from "
            r4.append(r5)     // Catch:{ all -> 0x0062 }
            java.lang.String r5 = r6.a     // Catch:{ all -> 0x0062 }
            r4.append(r5)     // Catch:{ all -> 0x0062 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0062 }
            android.util.Log.e(r3, r4, r1)     // Catch:{ all -> 0x0062 }
            com.facebook.ads.internal.v.b.m.a(r2)
            if (r0 == 0) goto L_0x008b
        L_0x0088:
            r0.disconnect()
        L_0x008b:
            return
        L_0x008c:
            com.facebook.ads.internal.v.b.m.a(r2)
            if (r0 == 0) goto L_0x0094
            r0.disconnect()
        L_0x0094:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.v.b.h.d():void");
    }

    public synchronized int a() {
        if (this.d == Integer.MIN_VALUE) {
            d();
        }
        return this.d;
    }

    public int a(byte[] bArr) {
        InputStream inputStream = this.c;
        if (inputStream != null) {
            try {
                return inputStream.read(bArr, 0, bArr.length);
            } catch (InterruptedIOException e2) {
                StringBuilder sb = new StringBuilder();
                sb.append("Reading source ");
                sb.append(this.a);
                sb.append(" is interrupted");
                throw new i(sb.toString(), e2);
            } catch (IOException e3) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Error reading data from ");
                sb2.append(this.a);
                throw new l(sb2.toString(), e3);
            }
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Error reading data from ");
            sb3.append(this.a);
            sb3.append(": connection is absent!");
            throw new l(sb3.toString());
        }
    }

    public void a(int i) {
        try {
            this.b = a(i, -1);
            this.e = this.b.getContentType();
            this.c = new BufferedInputStream(this.b.getInputStream(), 8192);
            HttpURLConnection httpURLConnection = this.b;
            int responseCode = this.b.getResponseCode();
            int contentLength = httpURLConnection.getContentLength();
            if (responseCode != 200) {
                contentLength = responseCode == 206 ? contentLength + i : this.d;
            }
            this.d = contentLength;
        } catch (IOException e2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error opening connection for ");
            sb.append(this.a);
            sb.append(" with offset ");
            sb.append(i);
            throw new l(sb.toString(), e2);
        }
    }

    public void b() {
        HttpURLConnection httpURLConnection = this.b;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (NullPointerException e2) {
                throw new l("Error disconnecting HttpUrlConnection", e2);
            }
        }
    }

    public synchronized String c() {
        if (TextUtils.isEmpty(this.e)) {
            d();
        }
        return this.e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("HttpUrlSource{url='");
        sb.append(this.a);
        sb.append("}");
        return sb.toString();
    }
}
