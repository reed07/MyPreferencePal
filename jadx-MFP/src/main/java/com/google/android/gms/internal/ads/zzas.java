package com.google.android.gms.internal.ads;

import android.support.annotation.VisibleForTesting;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLSocketFactory;

public final class zzas extends zzai {
    private final zzau zzci;
    private final SSLSocketFactory zzcj;

    public zzas() {
        this(null);
    }

    private zzas(zzau zzau) {
        this(null, null);
    }

    private zzas(zzau zzau, SSLSocketFactory sSLSocketFactory) {
        this.zzci = null;
        this.zzcj = null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x0128  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzaq zza(com.google.android.gms.internal.ads.zzr<?> r7, java.util.Map<java.lang.String, java.lang.String> r8) throws java.io.IOException, com.google.android.gms.internal.ads.zza {
        /*
            r6 = this;
            java.lang.String r0 = r7.getUrl()
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r1.putAll(r8)
            java.util.Map r8 = r7.getHeaders()
            r1.putAll(r8)
            com.google.android.gms.internal.ads.zzau r8 = r6.zzci
            if (r8 == 0) goto L_0x003a
            java.lang.String r8 = r8.zzg(r0)
            if (r8 != 0) goto L_0x003b
            java.io.IOException r7 = new java.io.IOException
            java.lang.String r8 = "URL blocked by rewriter: "
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r1 = r0.length()
            if (r1 == 0) goto L_0x0030
            java.lang.String r8 = r8.concat(r0)
            goto L_0x0036
        L_0x0030:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r8)
            r8 = r0
        L_0x0036:
            r7.<init>(r8)
            throw r7
        L_0x003a:
            r8 = r0
        L_0x003b:
            java.net.URL r0 = new java.net.URL
            r0.<init>(r8)
            java.net.URLConnection r8 = r0.openConnection()
            java.net.HttpURLConnection r8 = (java.net.HttpURLConnection) r8
            boolean r2 = java.net.HttpURLConnection.getFollowRedirects()
            r8.setInstanceFollowRedirects(r2)
            int r2 = r7.zzj()
            r8.setConnectTimeout(r2)
            r8.setReadTimeout(r2)
            r2 = 0
            r8.setUseCaches(r2)
            r3 = 1
            r8.setDoInput(r3)
            java.lang.String r4 = "https"
            java.lang.String r0 = r0.getProtocol()
            r4.equals(r0)
            java.util.Set r0 = r1.keySet()     // Catch:{ all -> 0x0125 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0125 }
        L_0x0070:
            boolean r4 = r0.hasNext()     // Catch:{ all -> 0x0125 }
            if (r4 == 0) goto L_0x0086
            java.lang.Object r4 = r0.next()     // Catch:{ all -> 0x0125 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x0125 }
            java.lang.Object r5 = r1.get(r4)     // Catch:{ all -> 0x0125 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x0125 }
            r8.setRequestProperty(r4, r5)     // Catch:{ all -> 0x0125 }
            goto L_0x0070
        L_0x0086:
            int r0 = r7.getMethod()     // Catch:{ all -> 0x0125 }
            switch(r0) {
                case -1: goto L_0x00c9;
                case 0: goto L_0x00c4;
                case 1: goto L_0x00bb;
                case 2: goto L_0x00b2;
                case 3: goto L_0x00ac;
                case 4: goto L_0x00a6;
                case 5: goto L_0x00a0;
                case 6: goto L_0x009a;
                case 7: goto L_0x0091;
                default: goto L_0x008d;
            }     // Catch:{ all -> 0x0125 }
        L_0x008d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0125 }
            goto L_0x011f
        L_0x0091:
            java.lang.String r0 = "PATCH"
            r8.setRequestMethod(r0)     // Catch:{ all -> 0x0125 }
            zza(r8, r7)     // Catch:{ all -> 0x0125 }
            goto L_0x00c9
        L_0x009a:
            java.lang.String r0 = "TRACE"
            r8.setRequestMethod(r0)     // Catch:{ all -> 0x0125 }
            goto L_0x00c9
        L_0x00a0:
            java.lang.String r0 = "OPTIONS"
            r8.setRequestMethod(r0)     // Catch:{ all -> 0x0125 }
            goto L_0x00c9
        L_0x00a6:
            java.lang.String r0 = "HEAD"
            r8.setRequestMethod(r0)     // Catch:{ all -> 0x0125 }
            goto L_0x00c9
        L_0x00ac:
            java.lang.String r0 = "DELETE"
            r8.setRequestMethod(r0)     // Catch:{ all -> 0x0125 }
            goto L_0x00c9
        L_0x00b2:
            java.lang.String r0 = "PUT"
            r8.setRequestMethod(r0)     // Catch:{ all -> 0x0125 }
            zza(r8, r7)     // Catch:{ all -> 0x0125 }
            goto L_0x00c9
        L_0x00bb:
            java.lang.String r0 = "POST"
            r8.setRequestMethod(r0)     // Catch:{ all -> 0x0125 }
            zza(r8, r7)     // Catch:{ all -> 0x0125 }
            goto L_0x00c9
        L_0x00c4:
            java.lang.String r0 = "GET"
            r8.setRequestMethod(r0)     // Catch:{ all -> 0x0125 }
        L_0x00c9:
            int r0 = r8.getResponseCode()     // Catch:{ all -> 0x0125 }
            r1 = -1
            if (r0 == r1) goto L_0x0117
            int r7 = r7.getMethod()     // Catch:{ all -> 0x0125 }
            r1 = 4
            if (r7 == r1) goto L_0x00e9
            r7 = 100
            if (r7 > r0) goto L_0x00df
            r7 = 200(0xc8, float:2.8E-43)
            if (r0 < r7) goto L_0x00e9
        L_0x00df:
            r7 = 204(0xcc, float:2.86E-43)
            if (r0 == r7) goto L_0x00e9
            r7 = 304(0x130, float:4.26E-43)
            if (r0 == r7) goto L_0x00e9
            r7 = 1
            goto L_0x00ea
        L_0x00e9:
            r7 = 0
        L_0x00ea:
            if (r7 != 0) goto L_0x00fd
            com.google.android.gms.internal.ads.zzaq r7 = new com.google.android.gms.internal.ads.zzaq     // Catch:{ all -> 0x0125 }
            java.util.Map r1 = r8.getHeaderFields()     // Catch:{ all -> 0x0125 }
            java.util.List r1 = zza(r1)     // Catch:{ all -> 0x0125 }
            r7.<init>(r0, r1)     // Catch:{ all -> 0x0125 }
            r8.disconnect()
            return r7
        L_0x00fd:
            com.google.android.gms.internal.ads.zzaq r7 = new com.google.android.gms.internal.ads.zzaq     // Catch:{ all -> 0x0114 }
            java.util.Map r1 = r8.getHeaderFields()     // Catch:{ all -> 0x0114 }
            java.util.List r1 = zza(r1)     // Catch:{ all -> 0x0114 }
            int r2 = r8.getContentLength()     // Catch:{ all -> 0x0114 }
            com.google.android.gms.internal.ads.zzat r4 = new com.google.android.gms.internal.ads.zzat     // Catch:{ all -> 0x0114 }
            r4.<init>(r8)     // Catch:{ all -> 0x0114 }
            r7.<init>(r0, r1, r2, r4)     // Catch:{ all -> 0x0114 }
            return r7
        L_0x0114:
            r7 = move-exception
            r2 = 1
            goto L_0x0126
        L_0x0117:
            java.io.IOException r7 = new java.io.IOException     // Catch:{ all -> 0x0125 }
            java.lang.String r0 = "Could not retrieve response code from HttpUrlConnection."
            r7.<init>(r0)     // Catch:{ all -> 0x0125 }
            throw r7     // Catch:{ all -> 0x0125 }
        L_0x011f:
            java.lang.String r0 = "Unknown method type."
            r7.<init>(r0)     // Catch:{ all -> 0x0125 }
            throw r7     // Catch:{ all -> 0x0125 }
        L_0x0125:
            r7 = move-exception
        L_0x0126:
            if (r2 != 0) goto L_0x012b
            r8.disconnect()
        L_0x012b:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzas.zza(com.google.android.gms.internal.ads.zzr, java.util.Map):com.google.android.gms.internal.ads.zzaq");
    }

    @VisibleForTesting
    private static List<zzl> zza(Map<String, List<String>> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Entry entry : map.entrySet()) {
            if (entry.getKey() != null) {
                for (String zzl : (List) entry.getValue()) {
                    arrayList.add(new zzl((String) entry.getKey(), zzl));
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static InputStream zza(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getInputStream();
        } catch (IOException unused) {
            return httpURLConnection.getErrorStream();
        }
    }

    private static void zza(HttpURLConnection httpURLConnection, zzr<?> zzr) throws IOException, zza {
        byte[] zzh = zzr.zzh();
        if (zzh != null) {
            httpURLConnection.setDoOutput(true);
            if (!httpURLConnection.getRequestProperties().containsKey("Content-Type")) {
                String str = "Content-Type";
                String str2 = "application/x-www-form-urlencoded; charset=";
                String valueOf = String.valueOf("UTF-8");
                httpURLConnection.setRequestProperty(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(zzh);
            dataOutputStream.close();
        }
    }
}
