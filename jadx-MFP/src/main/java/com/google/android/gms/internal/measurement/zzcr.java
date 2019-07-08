package com.google.android.gms.internal.measurement;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map.Entry;
import kotlin.text.Typography;

final class zzcr extends zzau {
    /* access modifiers changed from: private */
    public static final byte[] zzabn = "\n".getBytes();
    private final String zzabl = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{"GoogleAnalytics", zzav.VERSION, VERSION.RELEASE, zzdg.zza(Locale.getDefault()), Build.MODEL, Build.ID});
    private final zzdc zzabm;

    zzcr(zzaw zzaw) {
        super(zzaw);
        this.zzabm = new zzdc(zzaw.zzbx());
    }

    /* access modifiers changed from: protected */
    public final void zzag() {
        zza("Network initialized. User agent", this.zzabl);
    }

    public final boolean zzfb() {
        NetworkInfo networkInfo;
        zzk.zzaf();
        zzcl();
        try {
            networkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException unused) {
            networkInfo = null;
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        zzq("No network connectivity");
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0105  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<java.lang.Long> zzb(java.util.List<com.google.android.gms.internal.measurement.zzck> r9) {
        /*
            r8 = this;
            com.google.android.gms.analytics.zzk.zzaf()
            r8.zzcl()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r9)
            com.google.android.gms.internal.measurement.zzbx r0 = r8.zzbz()
            java.util.Set r0 = r0.zzeg()
            boolean r0 = r0.isEmpty()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0059
            com.google.android.gms.internal.measurement.zzdc r0 = r8.zzabm
            com.google.android.gms.internal.measurement.zzcg<java.lang.Integer> r3 = com.google.android.gms.internal.measurement.zzcf.zzzx
            java.lang.Object r3 = r3.get()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            long r3 = (long) r3
            r5 = 1000(0x3e8, double:4.94E-321)
            long r3 = r3 * r5
            boolean r0 = r0.zzj(r3)
            if (r0 != 0) goto L_0x0033
            goto L_0x0059
        L_0x0033:
            com.google.android.gms.internal.measurement.zzcg<java.lang.String> r0 = com.google.android.gms.internal.measurement.zzcf.zzzq
            java.lang.Object r0 = r0.get()
            java.lang.String r0 = (java.lang.String) r0
            com.google.android.gms.internal.measurement.zzbn r0 = com.google.android.gms.internal.measurement.zzbn.zzz(r0)
            com.google.android.gms.internal.measurement.zzbn r3 = com.google.android.gms.internal.measurement.zzbn.NONE
            if (r0 == r3) goto L_0x0045
            r0 = 1
            goto L_0x0046
        L_0x0045:
            r0 = 0
        L_0x0046:
            com.google.android.gms.internal.measurement.zzcg<java.lang.String> r3 = com.google.android.gms.internal.measurement.zzcf.zzzr
            java.lang.Object r3 = r3.get()
            java.lang.String r3 = (java.lang.String) r3
            com.google.android.gms.internal.measurement.zzbt r3 = com.google.android.gms.internal.measurement.zzbt.zzaa(r3)
            com.google.android.gms.internal.measurement.zzbt r4 = com.google.android.gms.internal.measurement.zzbt.GZIP
            if (r3 != r4) goto L_0x005a
            r3 = r0
            r0 = 1
            goto L_0x005c
        L_0x0059:
            r0 = 0
        L_0x005a:
            r3 = r0
            r0 = 0
        L_0x005c:
            r4 = 200(0xc8, float:2.8E-43)
            if (r3 == 0) goto L_0x0105
            boolean r1 = r9.isEmpty()
            r1 = r1 ^ r2
            com.google.android.gms.common.internal.Preconditions.checkArgument(r1)
            java.lang.String r1 = "Uploading batched hits. compression, count"
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r0)
            int r3 = r9.size()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r8.zza(r1, r2, r3)
            com.google.android.gms.internal.measurement.zzcs r1 = new com.google.android.gms.internal.measurement.zzcs
            r1.<init>(r8)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r9 = r9.iterator()
        L_0x0087:
            boolean r3 = r9.hasNext()
            if (r3 == 0) goto L_0x00a5
            java.lang.Object r3 = r9.next()
            com.google.android.gms.internal.measurement.zzck r3 = (com.google.android.gms.internal.measurement.zzck) r3
            boolean r5 = r1.zze(r3)
            if (r5 == 0) goto L_0x00a5
            long r5 = r3.zzeq()
            java.lang.Long r3 = java.lang.Long.valueOf(r5)
            r2.add(r3)
            goto L_0x0087
        L_0x00a5:
            int r9 = r1.zzfe()
            if (r9 != 0) goto L_0x00ac
            return r2
        L_0x00ac:
            java.net.URL r9 = r8.zzfc()
            if (r9 != 0) goto L_0x00b8
            java.lang.String r9 = "Failed to build batching endpoint url"
            r8.zzu(r9)
            goto L_0x0100
        L_0x00b8:
            if (r0 == 0) goto L_0x00c3
            byte[] r0 = r1.getPayload()
            int r9 = r8.zzb(r9, r0)
            goto L_0x00cb
        L_0x00c3:
            byte[] r0 = r1.getPayload()
            int r9 = r8.zza(r9, r0)
        L_0x00cb:
            if (r4 != r9) goto L_0x00db
            java.lang.String r9 = "Batched upload completed. Hits batched"
            int r0 = r1.zzfe()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r8.zza(r9, r0)
            return r2
        L_0x00db:
            java.lang.String r0 = "Network error uploading hits. status code"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r9)
            r8.zza(r0, r1)
            com.google.android.gms.internal.measurement.zzbx r0 = r8.zzbz()
            java.util.Set r0 = r0.zzeg()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            boolean r9 = r0.contains(r9)
            if (r9 == 0) goto L_0x0100
            java.lang.String r9 = "Server instructed the client to stop batching"
            r8.zzt(r9)
            com.google.android.gms.internal.measurement.zzdc r9 = r8.zzabm
            r9.start()
        L_0x0100:
            java.util.List r9 = java.util.Collections.emptyList()
            return r9
        L_0x0105:
            java.util.ArrayList r0 = new java.util.ArrayList
            int r3 = r9.size()
            r0.<init>(r3)
            java.util.Iterator r9 = r9.iterator()
        L_0x0112:
            boolean r3 = r9.hasNext()
            if (r3 == 0) goto L_0x01bb
            java.lang.Object r3 = r9.next()
            com.google.android.gms.internal.measurement.zzck r3 = (com.google.android.gms.internal.measurement.zzck) r3
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)
            boolean r5 = r3.zzet()
            r5 = r5 ^ r2
            java.lang.String r5 = r8.zza(r3, r5)
            if (r5 != 0) goto L_0x0138
            com.google.android.gms.internal.measurement.zzcp r5 = r8.zzby()
            java.lang.String r6 = "Error formatting hit for upload"
            r5.zza(r3, r6)
            r5 = 1
            goto L_0x01a4
        L_0x0138:
            int r6 = r5.length()
            com.google.android.gms.internal.measurement.zzcg<java.lang.Integer> r7 = com.google.android.gms.internal.measurement.zzcf.zzzp
            java.lang.Object r7 = r7.get()
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            if (r6 > r7) goto L_0x0160
            java.net.URL r5 = r8.zzb(r3, r5)
            if (r5 != 0) goto L_0x0156
            java.lang.String r5 = "Failed to build collect GET endpoint url"
            r8.zzu(r5)
            goto L_0x01a3
        L_0x0156:
            int r5 = r8.zza(r5)
            if (r5 != r4) goto L_0x015e
            r5 = 1
            goto L_0x01a4
        L_0x015e:
            r5 = 0
            goto L_0x01a4
        L_0x0160:
            java.lang.String r5 = r8.zza(r3, r1)
            if (r5 != 0) goto L_0x0171
            com.google.android.gms.internal.measurement.zzcp r5 = r8.zzby()
            java.lang.String r6 = "Error formatting hit for POST upload"
            r5.zza(r3, r6)
            r5 = 1
            goto L_0x01a4
        L_0x0171:
            byte[] r5 = r5.getBytes()
            int r6 = r5.length
            com.google.android.gms.internal.measurement.zzcg<java.lang.Integer> r7 = com.google.android.gms.internal.measurement.zzcf.zzzu
            java.lang.Object r7 = r7.get()
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            if (r6 <= r7) goto L_0x018f
            com.google.android.gms.internal.measurement.zzcp r5 = r8.zzby()
            java.lang.String r6 = "Hit payload exceeds size limit"
            r5.zza(r3, r6)
            r5 = 1
            goto L_0x01a4
        L_0x018f:
            java.net.URL r6 = r8.zzd(r3)
            if (r6 != 0) goto L_0x019b
            java.lang.String r5 = "Failed to build collect POST endpoint url"
            r8.zzu(r5)
            goto L_0x01a3
        L_0x019b:
            int r5 = r8.zza(r6, r5)
            if (r5 != r4) goto L_0x01a3
            r5 = 1
            goto L_0x01a4
        L_0x01a3:
            r5 = 0
        L_0x01a4:
            if (r5 == 0) goto L_0x01bb
            long r5 = r3.zzeq()
            java.lang.Long r3 = java.lang.Long.valueOf(r5)
            r0.add(r3)
            int r3 = r0.size()
            int r5 = com.google.android.gms.internal.measurement.zzbx.zzeb()
            if (r3 < r5) goto L_0x0112
        L_0x01bb:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzcr.zzb(java.util.List):java.util.List");
    }

    private final int zza(URL url) {
        Preconditions.checkNotNull(url);
        zzb("GET request", url);
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection zzb = zzb(url);
            zzb.connect();
            zza(zzb);
            int responseCode = zzb.getResponseCode();
            if (responseCode == 200) {
                zzcc().zzbv();
            }
            zzb("GET status", Integer.valueOf(responseCode));
            if (zzb != null) {
                zzb.disconnect();
            }
            return responseCode;
        } catch (IOException e) {
            zzd("Network GET connection error", e);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return 0;
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r4v1, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r4v2, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r4v4 */
    /* JADX WARNING: type inference failed for: r4v5 */
    /* JADX WARNING: type inference failed for: r4v7 */
    /* JADX WARNING: type inference failed for: r4v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0079 A[SYNTHETIC, Splitter:B:29:0x0079] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008d A[SYNTHETIC, Splitter:B:39:0x008d] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0099  */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(java.net.URL r4, byte[] r5) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            java.lang.String r0 = "POST bytes, url"
            int r1 = r5.length
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r3.zzb(r0, r1, r4)
            boolean r0 = zzck()
            if (r0 == 0) goto L_0x0020
            java.lang.String r0 = "Post payload\n"
            java.lang.String r1 = new java.lang.String
            r1.<init>(r5)
            r3.zza(r0, r1)
        L_0x0020:
            r0 = 0
            android.content.Context r1 = r3.getContext()     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            r1.getPackageName()     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            java.net.HttpURLConnection r4 = r3.zzb(r4)     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            r1 = 1
            r4.setDoOutput(r1)     // Catch:{ IOException -> 0x006b }
            int r1 = r5.length     // Catch:{ IOException -> 0x006b }
            r4.setFixedLengthStreamingMode(r1)     // Catch:{ IOException -> 0x006b }
            r4.connect()     // Catch:{ IOException -> 0x006b }
            java.io.OutputStream r0 = r4.getOutputStream()     // Catch:{ IOException -> 0x006b }
            r0.write(r5)     // Catch:{ IOException -> 0x006b }
            r3.zza(r4)     // Catch:{ IOException -> 0x006b }
            int r5 = r4.getResponseCode()     // Catch:{ IOException -> 0x006b }
            r1 = 200(0xc8, float:2.8E-43)
            if (r5 != r1) goto L_0x0050
            com.google.android.gms.internal.measurement.zzal r1 = r3.zzcc()     // Catch:{ IOException -> 0x006b }
            r1.zzbv()     // Catch:{ IOException -> 0x006b }
        L_0x0050:
            java.lang.String r1 = "POST status"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)     // Catch:{ IOException -> 0x006b }
            r3.zzb(r1, r2)     // Catch:{ IOException -> 0x006b }
            if (r0 == 0) goto L_0x0065
            r0.close()     // Catch:{ IOException -> 0x005f }
            goto L_0x0065
        L_0x005f:
            r0 = move-exception
            java.lang.String r1 = "Error closing http post connection output stream"
            r3.zze(r1, r0)
        L_0x0065:
            if (r4 == 0) goto L_0x006a
            r4.disconnect()
        L_0x006a:
            return r5
        L_0x006b:
            r5 = move-exception
            goto L_0x0072
        L_0x006d:
            r5 = move-exception
            r4 = r0
            goto L_0x008b
        L_0x0070:
            r5 = move-exception
            r4 = r0
        L_0x0072:
            java.lang.String r1 = "Network POST connection error"
            r3.zzd(r1, r5)     // Catch:{ all -> 0x008a }
            if (r0 == 0) goto L_0x0083
            r0.close()     // Catch:{ IOException -> 0x007d }
            goto L_0x0083
        L_0x007d:
            r5 = move-exception
            java.lang.String r0 = "Error closing http post connection output stream"
            r3.zze(r0, r5)
        L_0x0083:
            if (r4 == 0) goto L_0x0088
            r4.disconnect()
        L_0x0088:
            r4 = 0
            return r4
        L_0x008a:
            r5 = move-exception
        L_0x008b:
            if (r0 == 0) goto L_0x0097
            r0.close()     // Catch:{ IOException -> 0x0091 }
            goto L_0x0097
        L_0x0091:
            r0 = move-exception
            java.lang.String r1 = "Error closing http post connection output stream"
            r3.zze(r1, r0)
        L_0x0097:
            if (r4 == 0) goto L_0x009c
            r4.disconnect()
        L_0x009c:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzcr.zza(java.net.URL, byte[]):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d8 A[SYNTHETIC, Splitter:B:42:0x00d8] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00ec A[SYNTHETIC, Splitter:B:52:0x00ec] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00f8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzb(java.net.URL r10, byte[] r11) {
        /*
            r9 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r10)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r11)
            r0 = 0
            android.content.Context r1 = r9.getContext()     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r1.getPackageName()     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r1.<init>()     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r2.<init>(r1)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r2.write(r11)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r2.close()     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r1.close()     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            byte[] r1 = r1.toByteArray()     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            java.lang.String r2 = "POST compressed size, ratio %, url"
            int r3 = r1.length     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r4 = 100
            int r6 = r1.length     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            long r6 = (long) r6     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            long r6 = r6 * r4
            int r4 = r11.length     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            long r4 = (long) r4     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            long r6 = r6 / r4
            java.lang.Long r4 = java.lang.Long.valueOf(r6)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r9.zza(r2, r3, r4, r10)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            int r2 = r1.length     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            int r3 = r11.length     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            if (r2 <= r3) goto L_0x004f
            java.lang.String r2 = "Compressed payload is larger then uncompressed. compressed, uncompressed"
            int r3 = r1.length     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            int r4 = r11.length     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r9.zzc(r2, r3, r4)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
        L_0x004f:
            boolean r2 = zzck()     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            if (r2 == 0) goto L_0x0075
            java.lang.String r2 = "Post payload"
            java.lang.String r3 = "\n"
            java.lang.String r4 = new java.lang.String     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r4.<init>(r11)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            java.lang.String r11 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            int r4 = r11.length()     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            if (r4 == 0) goto L_0x006d
            java.lang.String r11 = r3.concat(r11)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            goto L_0x0072
        L_0x006d:
            java.lang.String r11 = new java.lang.String     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r11.<init>(r3)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
        L_0x0072:
            r9.zza(r2, r11)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
        L_0x0075:
            java.net.HttpURLConnection r10 = r9.zzb(r10)     // Catch:{ IOException -> 0x00cf, all -> 0x00cc }
            r11 = 1
            r10.setDoOutput(r11)     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            java.lang.String r11 = "Content-Encoding"
            java.lang.String r2 = "gzip"
            r10.addRequestProperty(r11, r2)     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            int r11 = r1.length     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            r10.setFixedLengthStreamingMode(r11)     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            r10.connect()     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            java.io.OutputStream r11 = r10.getOutputStream()     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            r11.write(r1)     // Catch:{ IOException -> 0x00bc, all -> 0x00b6 }
            r11.close()     // Catch:{ IOException -> 0x00bc, all -> 0x00b6 }
            r9.zza(r10)     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            int r11 = r10.getResponseCode()     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            r1 = 200(0xc8, float:2.8E-43)
            if (r11 != r1) goto L_0x00a7
            com.google.android.gms.internal.measurement.zzal r1 = r9.zzcc()     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            r1.zzbv()     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
        L_0x00a7:
            java.lang.String r1 = "POST status"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r11)     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            r9.zzb(r1, r2)     // Catch:{ IOException -> 0x00c7, all -> 0x00c2 }
            if (r10 == 0) goto L_0x00b5
            r10.disconnect()
        L_0x00b5:
            return r11
        L_0x00b6:
            r0 = move-exception
            r8 = r11
            r11 = r10
            r10 = r0
            r0 = r8
            goto L_0x00ea
        L_0x00bc:
            r0 = move-exception
            r8 = r11
            r11 = r10
            r10 = r0
            r0 = r8
            goto L_0x00d1
        L_0x00c2:
            r11 = move-exception
            r8 = r11
            r11 = r10
            r10 = r8
            goto L_0x00ea
        L_0x00c7:
            r11 = move-exception
            r8 = r11
            r11 = r10
            r10 = r8
            goto L_0x00d1
        L_0x00cc:
            r10 = move-exception
            r11 = r0
            goto L_0x00ea
        L_0x00cf:
            r10 = move-exception
            r11 = r0
        L_0x00d1:
            java.lang.String r1 = "Network compressed POST connection error"
            r9.zzd(r1, r10)     // Catch:{ all -> 0x00e9 }
            if (r0 == 0) goto L_0x00e2
            r0.close()     // Catch:{ IOException -> 0x00dc }
            goto L_0x00e2
        L_0x00dc:
            r10 = move-exception
            java.lang.String r0 = "Error closing http compressed post connection output stream"
            r9.zze(r0, r10)
        L_0x00e2:
            if (r11 == 0) goto L_0x00e7
            r11.disconnect()
        L_0x00e7:
            r10 = 0
            return r10
        L_0x00e9:
            r10 = move-exception
        L_0x00ea:
            if (r0 == 0) goto L_0x00f6
            r0.close()     // Catch:{ IOException -> 0x00f0 }
            goto L_0x00f6
        L_0x00f0:
            r0 = move-exception
            java.lang.String r1 = "Error closing http compressed post connection output stream"
            r9.zze(r1, r0)
        L_0x00f6:
            if (r11 == 0) goto L_0x00fb
            r11.disconnect()
        L_0x00fb:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzcr.zzb(java.net.URL, byte[]):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0022 A[SYNTHETIC, Splitter:B:19:0x0022] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zza(java.net.HttpURLConnection r3) throws java.io.IOException {
        /*
            r2 = this;
            java.io.InputStream r3 = r3.getInputStream()     // Catch:{ all -> 0x001e }
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x001c }
        L_0x0008:
            int r1 = r3.read(r0)     // Catch:{ all -> 0x001c }
            if (r1 > 0) goto L_0x0008
            if (r3 == 0) goto L_0x001b
            r3.close()     // Catch:{ IOException -> 0x0014 }
            return
        L_0x0014:
            r3 = move-exception
            java.lang.String r0 = "Error closing http connection input stream"
            r2.zze(r0, r3)
            return
        L_0x001b:
            return
        L_0x001c:
            r0 = move-exception
            goto L_0x0020
        L_0x001e:
            r0 = move-exception
            r3 = 0
        L_0x0020:
            if (r3 == 0) goto L_0x002c
            r3.close()     // Catch:{ IOException -> 0x0026 }
            goto L_0x002c
        L_0x0026:
            r3 = move-exception
            java.lang.String r1 = "Error closing http connection input stream"
            r2.zze(r1, r3)
        L_0x002c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzcr.zza(java.net.HttpURLConnection):void");
    }

    @VisibleForTesting
    private final HttpURLConnection zzb(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        if (openConnection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setDefaultUseCaches(false);
            httpURLConnection.setConnectTimeout(((Integer) zzcf.zzzz.get()).intValue());
            httpURLConnection.setReadTimeout(((Integer) zzcf.zzaaa.get()).intValue());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestProperty("User-Agent", this.zzabl);
            httpURLConnection.setDoInput(true);
            return httpURLConnection;
        }
        throw new IOException("Failed to obtain http connection");
    }

    private final URL zzd(zzck zzck) {
        String str;
        if (zzck.zzet()) {
            String valueOf = String.valueOf(zzbx.zzed());
            String valueOf2 = String.valueOf(zzbx.zzef());
            str = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        } else {
            String valueOf3 = String.valueOf(zzbx.zzee());
            String valueOf4 = String.valueOf(zzbx.zzef());
            str = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
        }
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private final URL zzb(zzck zzck, String str) {
        String str2;
        if (zzck.zzet()) {
            String zzed = zzbx.zzed();
            String zzef = zzbx.zzef();
            StringBuilder sb = new StringBuilder(String.valueOf(zzed).length() + 1 + String.valueOf(zzef).length() + String.valueOf(str).length());
            sb.append(zzed);
            sb.append(zzef);
            sb.append("?");
            sb.append(str);
            str2 = sb.toString();
        } else {
            String zzee = zzbx.zzee();
            String zzef2 = zzbx.zzef();
            StringBuilder sb2 = new StringBuilder(String.valueOf(zzee).length() + 1 + String.valueOf(zzef2).length() + String.valueOf(str).length());
            sb2.append(zzee);
            sb2.append(zzef2);
            sb2.append("?");
            sb2.append(str);
            str2 = sb2.toString();
        }
        try {
            return new URL(str2);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private final URL zzfc() {
        String valueOf = String.valueOf(zzbx.zzed());
        String valueOf2 = String.valueOf((String) zzcf.zzzo.get());
        try {
            return new URL(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final String zza(zzck zzck, boolean z) {
        String str;
        Preconditions.checkNotNull(zzck);
        StringBuilder sb = new StringBuilder();
        try {
            for (Entry entry : zzck.zzcw().entrySet()) {
                String str2 = (String) entry.getKey();
                if (!"ht".equals(str2) && !"qt".equals(str2) && !"AppUID".equals(str2) && !"z".equals(str2) && !"_gmsv".equals(str2)) {
                    zza(sb, str2, (String) entry.getValue());
                }
            }
            zza(sb, "ht", String.valueOf(zzck.zzer()));
            zza(sb, "qt", String.valueOf(zzbx().currentTimeMillis() - zzck.zzer()));
            if (z) {
                long zzeu = zzck.zzeu();
                if (zzeu != 0) {
                    str = String.valueOf(zzeu);
                } else {
                    str = String.valueOf(zzck.zzeq());
                }
                zza(sb, "z", str);
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            zze("Failed to encode name or value", e);
            return null;
        }
    }

    private static void zza(StringBuilder sb, String str, String str2) throws UnsupportedEncodingException {
        if (sb.length() != 0) {
            sb.append(Typography.amp);
        }
        sb.append(URLEncoder.encode(str, "UTF-8"));
        sb.append('=');
        sb.append(URLEncoder.encode(str2, "UTF-8"));
    }
}
