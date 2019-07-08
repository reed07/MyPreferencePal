package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.zzbv;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzatq extends zzasr {
    private static final Object sLock = new Object();
    @GuardedBy("sLock")
    private static zzatq zzeau;
    private final Context mContext;
    private final zzatp zzeav;
    private final ScheduledExecutorService zzeaw = Executors.newSingleThreadScheduledExecutor();

    public static zzatq zza(Context context, zzatp zzatp) {
        zzatq zzatq;
        synchronized (sLock) {
            if (zzeau == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                zzaan.initialize(context);
                zzeau = new zzatq(context, zzatp);
                if (context.getApplicationContext() != null) {
                    zzbv.zzlf().zzaj(context);
                }
                zzaxx.zzag(context);
            }
            zzatq = zzeau;
        }
        return zzatq;
    }

    public final void zza(zzatb zzatb, zzasw zzasw) {
        zzaxz.v("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }

    public final void zzb(zzatb zzatb, zzasw zzasw) {
        zzaxz.v("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }

    private static zzasm zza(Context context, zzatp zzatp, zzasi zzasi, ScheduledExecutorService scheduledExecutorService) {
        String str;
        zzasm zzasm;
        Context context2 = context;
        zzatp zzatp2 = zzatp;
        zzasi zzasi2 = zzasi;
        ScheduledExecutorService scheduledExecutorService2 = scheduledExecutorService;
        zzaxz.zzdn("Starting ad request from service using: google.afma.request.getAdDictionary");
        zzaba zzaba = new zzaba(((Boolean) zzwu.zzpz().zzd(zzaan.zzcpw)).booleanValue(), "load_ad", zzasi2.zzbst.zzckk);
        if (zzasi2.versionCode > 10 && zzasi2.zzdwv != -1) {
            zzaba.zza(zzaba.zzao(zzasi2.zzdwv), "cts");
        }
        zzaay zzrg = zzaba.zzrg();
        zzbcb zza = zzbbq.zza(zzatp2.zzear.zzm(context2), ((Long) zzwu.zzpz().zzd(zzaan.zzcvj)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        Future zzm = zzbbq.zzm(null);
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcxz)).booleanValue()) {
            zzm = zzatp2.zzeam.zzdo(zzasi2.zzdwh.packageName);
        }
        zzbcb zzdp = zzatp2.zzeam.zzdp(zzasi2.zzdwh.packageName);
        zzbcb zza2 = zzatp2.zzeas.zza(zzasi2.zzdwi, zzasi2.zzdwh);
        Future zzt = zzbv.zzlq().zzt(context2);
        zzbcb zzm2 = zzbbq.zzm(null);
        Bundle bundle = zzasi2.zzdwg.extras;
        boolean z = (bundle == null || bundle.getString("_ad") == null) ? false : true;
        if (zzasi2.zzdxb && !z) {
            zzm2 = zzatp2.zzeap.zza(zzasi2.applicationInfo);
        }
        zzbcb zza3 = zzbbq.zza(zzm2, ((Long) zzwu.zzpz().zzd(zzaan.zzcuu)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        Future zzm3 = zzbbq.zzm(null);
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcro)).booleanValue()) {
            zzm3 = zzbbq.zza(zzatp2.zzeas.zzad(context2), ((Long) zzwu.zzpz().zzd(zzaan.zzcrp)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        }
        Bundle bundle2 = (zzasi2.versionCode < 4 || zzasi2.zzdwm == null) ? null : zzasi2.zzdwm;
        zzbv.zzlf();
        if (zzayh.zzn(context2, "android.permission.ACCESS_NETWORK_STATE") && ((ConnectivityManager) context2.getSystemService("connectivity")).getActiveNetworkInfo() == null) {
            zzaxz.zzdn("Device is offline.");
        }
        if (zzasi2.versionCode >= 7) {
            str = zzasi2.zzdws;
        } else {
            str = UUID.randomUUID().toString();
        }
        if (zzasi2.zzdwg.extras != null) {
            String string = zzasi2.zzdwg.extras.getString("_ad");
            if (string != null) {
                return zzatv.zza(context2, zzasi2, string);
            }
        }
        List<String> zzf = zzatp2.zzean.zzf(zzasi2.zzdwt);
        zzaba zzaba2 = zzaba;
        zzaay zzaay = zzrg;
        Object obj = str;
        Bundle bundle3 = (Bundle) zzbbq.zza((Future<T>) zza, null, ((Long) zzwu.zzpz().zzd(zzaan.zzcvj)).longValue(), TimeUnit.MILLISECONDS);
        Location location = (Location) zzbbq.zza((Future<T>) zza3, null);
        Info info = (Info) zzbbq.zza(zzm3, null);
        String str2 = (String) zzbbq.zza((Future<T>) zza2, null);
        String str3 = (String) zzbbq.zza(zzm, null);
        String str4 = (String) zzbbq.zza((Future<T>) zzdp, null);
        zzatz zzatz = (zzatz) zzbbq.zza(zzt, null);
        if (zzatz == null) {
            zzaxz.zzeo("Error fetching device info. This is not recoverable.");
            return new zzasm(0);
        }
        zzato zzato = new zzato();
        zzato.zzeag = zzasi2;
        zzato.zzeah = zzatz;
        zzato.zzcjj = location;
        zzato.zzeac = bundle3;
        zzato.zzdwi = str2;
        zzato.zzeaf = info;
        if (zzf == null) {
            zzato.zzdwt.clear();
        }
        zzato.zzdwt = zzf;
        zzato.zzdwm = bundle2;
        zzato.zzead = str3;
        zzato.zzeae = str4;
        zzato.zzeai = zzatp2.zzeal.zzf(context2);
        zzato.zzeaj = zzatp2.zzeaj;
        JSONObject zza4 = zzatv.zza(context2, zzato);
        if (zza4 == null) {
            return new zzasm(0);
        }
        if (zzasi2.versionCode < 7) {
            try {
                zza4.put("request_id", obj);
            } catch (JSONException unused) {
            }
        }
        zzaba zzaba3 = zzaba2;
        zzaay zzaay2 = zzaay;
        zzaba3.zza(zzaay2, "arc");
        ScheduledExecutorService scheduledExecutorService3 = scheduledExecutorService;
        zzbcb zza5 = zzbbq.zza(zzbbq.zza(zzatp2.zzeat.zzwo().zzj(zza4), zzatr.zzbni, (Executor) scheduledExecutorService3), 10, TimeUnit.SECONDS, scheduledExecutorService3);
        zzbcb zzwy = zzatp2.zzeao.zzwy();
        if (zzwy != null) {
            zzbbo.zza(zzwy, "AdRequestServiceImpl.loadAd.flags");
            zzasm = null;
        } else {
            zzasm = null;
        }
        zzaty zzaty = (zzaty) zzbbq.zza((Future<T>) zza5, zzasm);
        if (zzaty == null) {
            return new zzasm(0);
        }
        if (zzaty.getErrorCode() != -2) {
            return new zzasm(zzaty.getErrorCode());
        }
        zzaba3.zzrj();
        if (!TextUtils.isEmpty(zzaty.zzwt())) {
            zzasm = zzatv.zza(context2, zzasi2, zzaty.zzwt());
        }
        if (zzasm == null && !TextUtils.isEmpty(zzaty.getUrl())) {
            zzasm = zza(zzasi, context, zzasi2.zzbsp.zzdp, zzaty.getUrl(), str3, str4, zzaty, zzaba3, zzatp);
        }
        if (zzasm == null) {
            zzasm = new zzasm(0);
        }
        zzaba3.zza(zzaay2, "tts");
        zzasm.zzdyq = zzaba3.zzrh();
        zzasm.zzdze = zzaty.zzwv();
        return zzasm;
    }

    private static void zza(String str, Map<String, List<String>> map, String str2, int i) {
        if (zzaxz.isLoggable(2)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 39);
            sb.append("Http Response: {\n  URL:\n    ");
            sb.append(str);
            sb.append("\n  Headers:");
            zzaxz.v(sb.toString());
            if (map != null) {
                for (String str3 : map.keySet()) {
                    StringBuilder sb2 = new StringBuilder(String.valueOf(str3).length() + 5);
                    sb2.append("    ");
                    sb2.append(str3);
                    sb2.append(":");
                    zzaxz.v(sb2.toString());
                    for (String valueOf : (List) map.get(str3)) {
                        String str4 = "      ";
                        String valueOf2 = String.valueOf(valueOf);
                        zzaxz.v(valueOf2.length() != 0 ? str4.concat(valueOf2) : new String(str4));
                    }
                }
            }
            zzaxz.v("  Body:");
            if (str2 != null) {
                int i2 = 0;
                while (i2 < Math.min(str2.length(), 100000)) {
                    int i3 = i2 + 1000;
                    zzaxz.v(str2.substring(i2, Math.min(str2.length(), i3)));
                    i2 = i3;
                }
            } else {
                zzaxz.v("    null");
            }
            StringBuilder sb3 = new StringBuilder(34);
            sb3.append("  Response Code:\n    ");
            sb3.append(i);
            sb3.append("\n}");
            zzaxz.v(sb3.toString());
        }
    }

    /* JADX INFO: used method not loaded: com.google.android.gms.internal.ads.zzatw.zza(long, com.google.android.gms.internal.ads.zzaty):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f0, code lost:
        r0 = r7.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r7 = new java.io.InputStreamReader(r12.getInputStream());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        com.google.android.gms.ads.internal.zzbv.zzlf();
        r11 = com.google.android.gms.internal.ads.zzayh.zza(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r7);
        r4.zzek(r11);
        zza(r0, r13, r11, r10);
        r6.zza(r0, r13, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0110, code lost:
        if (r2 == null) goto L_0x011c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0112, code lost:
        r2.zza(r5, "ufe");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x011c, code lost:
        r0 = r6.zza(r8, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        r12.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0123, code lost:
        if (r3 == null) goto L_0x012a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0125, code lost:
        r3.zzeaq.zzxa();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x012a, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x012b, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x012c, code lost:
        r16 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x012f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0130, code lost:
        r16 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0135, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0150, code lost:
        com.google.android.gms.internal.ads.zzaxz.zzeo("No location header to follow redirect.");
        r0 = new com.google.android.gms.internal.ads.zzasm(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        r12.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x015e, code lost:
        if (r3 == null) goto L_0x0165;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0160, code lost:
        r3.zzeaq.zzxa();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0165, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x017f, code lost:
        com.google.android.gms.internal.ads.zzaxz.zzeo("Too many redirects.");
        r0 = new com.google.android.gms.internal.ads.zzasm(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:?, code lost:
        r12.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x018d, code lost:
        if (r3 == null) goto L_0x0194;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x018f, code lost:
        r3.zzeaq.zzxa();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0194, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzasm zza(com.google.android.gms.internal.ads.zzasi r18, android.content.Context r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, com.google.android.gms.internal.ads.zzaty r24, com.google.android.gms.internal.ads.zzaba r25, com.google.android.gms.internal.ads.zzatp r26) {
        /*
            r0 = r18
            r1 = r24
            r2 = r25
            r3 = r26
            if (r2 == 0) goto L_0x000f
            com.google.android.gms.internal.ads.zzaay r5 = r25.zzrg()
            goto L_0x0010
        L_0x000f:
            r5 = 0
        L_0x0010:
            com.google.android.gms.internal.ads.zzatw r6 = new com.google.android.gms.internal.ads.zzatw     // Catch:{ IOException -> 0x01da }
            java.lang.String r7 = r24.zzwq()     // Catch:{ IOException -> 0x01da }
            r6.<init>(r0, r7)     // Catch:{ IOException -> 0x01da }
            java.lang.String r7 = "AdRequestServiceImpl: Sending request: "
            java.lang.String r8 = java.lang.String.valueOf(r21)     // Catch:{ IOException -> 0x01da }
            int r9 = r8.length()     // Catch:{ IOException -> 0x01da }
            if (r9 == 0) goto L_0x002a
            java.lang.String r7 = r7.concat(r8)     // Catch:{ IOException -> 0x01da }
            goto L_0x0030
        L_0x002a:
            java.lang.String r8 = new java.lang.String     // Catch:{ IOException -> 0x01da }
            r8.<init>(r7)     // Catch:{ IOException -> 0x01da }
            r7 = r8
        L_0x0030:
            com.google.android.gms.internal.ads.zzaxz.zzdn(r7)     // Catch:{ IOException -> 0x01da }
            java.net.URL r7 = new java.net.URL     // Catch:{ IOException -> 0x01da }
            r8 = r21
            r7.<init>(r8)     // Catch:{ IOException -> 0x01da }
            com.google.android.gms.common.util.Clock r8 = com.google.android.gms.ads.internal.zzbv.zzlm()     // Catch:{ IOException -> 0x01da }
            long r8 = r8.elapsedRealtime()     // Catch:{ IOException -> 0x01da }
            r10 = 0
            r11 = 0
        L_0x0044:
            if (r3 == 0) goto L_0x004b
            com.google.android.gms.internal.ads.zzauh r12 = r3.zzeaq     // Catch:{ IOException -> 0x01da }
            r12.zzwz()     // Catch:{ IOException -> 0x01da }
        L_0x004b:
            java.net.URLConnection r12 = r7.openConnection()     // Catch:{ IOException -> 0x01da }
            java.net.HttpURLConnection r12 = (java.net.HttpURLConnection) r12     // Catch:{ IOException -> 0x01da }
            com.google.android.gms.internal.ads.zzayh r13 = com.google.android.gms.ads.internal.zzbv.zzlf()     // Catch:{ all -> 0x01ce }
            r14 = r19
            r15 = r20
            r13.zza(r14, r15, r10, r12)     // Catch:{ all -> 0x01ce }
            boolean r13 = r24.zzws()     // Catch:{ all -> 0x01ce }
            if (r13 == 0) goto L_0x0085
            boolean r13 = android.text.TextUtils.isEmpty(r22)     // Catch:{ all -> 0x01ce }
            if (r13 != 0) goto L_0x0071
            java.lang.String r13 = "x-afma-drt-cookie"
            r10 = r22
            r12.addRequestProperty(r13, r10)     // Catch:{ all -> 0x01ce }
            goto L_0x0073
        L_0x0071:
            r10 = r22
        L_0x0073:
            boolean r13 = android.text.TextUtils.isEmpty(r23)     // Catch:{ all -> 0x01ce }
            if (r13 != 0) goto L_0x0082
            java.lang.String r13 = "x-afma-drt-v2-cookie"
            r4 = r23
            r12.addRequestProperty(r13, r4)     // Catch:{ all -> 0x01ce }
            goto L_0x0089
        L_0x0082:
            r4 = r23
            goto L_0x0089
        L_0x0085:
            r10 = r22
            r4 = r23
        L_0x0089:
            java.lang.String r13 = r0.zzdxc     // Catch:{ all -> 0x01ce }
            boolean r17 = android.text.TextUtils.isEmpty(r13)     // Catch:{ all -> 0x01ce }
            if (r17 != 0) goto L_0x009b
            java.lang.String r17 = "Sending webview cookie in ad request header."
            com.google.android.gms.internal.ads.zzaxz.zzdn(r17)     // Catch:{ all -> 0x01ce }
            java.lang.String r4 = "Cookie"
            r12.addRequestProperty(r4, r13)     // Catch:{ all -> 0x01ce }
        L_0x009b:
            r4 = 1
            if (r1 == 0) goto L_0x00d2
            java.lang.String r13 = r24.zzwr()     // Catch:{ all -> 0x01ce }
            boolean r13 = android.text.TextUtils.isEmpty(r13)     // Catch:{ all -> 0x01ce }
            if (r13 != 0) goto L_0x00d2
            r12.setDoOutput(r4)     // Catch:{ all -> 0x01ce }
            java.lang.String r13 = r24.zzwr()     // Catch:{ all -> 0x01ce }
            byte[] r13 = r13.getBytes()     // Catch:{ all -> 0x01ce }
            int r4 = r13.length     // Catch:{ all -> 0x01ce }
            r12.setFixedLengthStreamingMode(r4)     // Catch:{ all -> 0x01ce }
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x00cb }
            java.io.OutputStream r10 = r12.getOutputStream()     // Catch:{ all -> 0x00cb }
            r4.<init>(r10)     // Catch:{ all -> 0x00cb }
            r4.write(r13)     // Catch:{ all -> 0x00c7 }
            com.google.android.gms.common.util.IOUtils.closeQuietly(r4)     // Catch:{ all -> 0x01ce }
            goto L_0x00d3
        L_0x00c7:
            r0 = move-exception
            r16 = r4
            goto L_0x00ce
        L_0x00cb:
            r0 = move-exception
            r16 = 0
        L_0x00ce:
            com.google.android.gms.common.util.IOUtils.closeQuietly(r16)     // Catch:{ all -> 0x01ce }
            throw r0     // Catch:{ all -> 0x01ce }
        L_0x00d2:
            r13 = 0
        L_0x00d3:
            com.google.android.gms.internal.ads.zzbax r4 = new com.google.android.gms.internal.ads.zzbax     // Catch:{ all -> 0x01ce }
            java.lang.String r10 = r0.zzdws     // Catch:{ all -> 0x01ce }
            r4.<init>(r10)     // Catch:{ all -> 0x01ce }
            r4.zza(r12, r13)     // Catch:{ all -> 0x01ce }
            int r10 = r12.getResponseCode()     // Catch:{ all -> 0x01ce }
            java.util.Map r13 = r12.getHeaderFields()     // Catch:{ all -> 0x01ce }
            r4.zza(r12, r10)     // Catch:{ all -> 0x01ce }
            r0 = 200(0xc8, float:2.8E-43)
            r14 = 300(0x12c, float:4.2E-43)
            if (r10 < r0) goto L_0x0136
            if (r10 >= r14) goto L_0x0136
            java.lang.String r0 = r7.toString()     // Catch:{ all -> 0x01ce }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ all -> 0x012f }
            java.io.InputStream r11 = r12.getInputStream()     // Catch:{ all -> 0x012f }
            r7.<init>(r11)     // Catch:{ all -> 0x012f }
            com.google.android.gms.ads.internal.zzbv.zzlf()     // Catch:{ all -> 0x012b }
            java.lang.String r11 = com.google.android.gms.internal.ads.zzayh.zza(r7)     // Catch:{ all -> 0x012b }
            com.google.android.gms.common.util.IOUtils.closeQuietly(r7)     // Catch:{ all -> 0x01ce }
            r4.zzek(r11)     // Catch:{ all -> 0x01ce }
            zza(r0, r13, r11, r10)     // Catch:{ all -> 0x01ce }
            r6.zza(r0, r13, r11)     // Catch:{ all -> 0x01ce }
            if (r2 == 0) goto L_0x011c
            java.lang.String r0 = "ufe"
            java.lang.String[] r0 = new java.lang.String[]{r0}     // Catch:{ all -> 0x01ce }
            r2.zza(r5, r0)     // Catch:{ all -> 0x01ce }
        L_0x011c:
            com.google.android.gms.internal.ads.zzasm r0 = r6.zza(r8, r1)     // Catch:{ all -> 0x01ce }
            r12.disconnect()     // Catch:{ IOException -> 0x01da }
            if (r3 == 0) goto L_0x012a
            com.google.android.gms.internal.ads.zzauh r1 = r3.zzeaq     // Catch:{ IOException -> 0x01da }
            r1.zzxa()     // Catch:{ IOException -> 0x01da }
        L_0x012a:
            return r0
        L_0x012b:
            r0 = move-exception
            r16 = r7
            goto L_0x0132
        L_0x012f:
            r0 = move-exception
            r16 = 0
        L_0x0132:
            com.google.android.gms.common.util.IOUtils.closeQuietly(r16)     // Catch:{ all -> 0x01ce }
            throw r0     // Catch:{ all -> 0x01ce }
        L_0x0136:
            java.lang.String r0 = r7.toString()     // Catch:{ all -> 0x01ce }
            r4 = 0
            zza(r0, r13, r4, r10)     // Catch:{ all -> 0x01ce }
            if (r10 < r14) goto L_0x01a7
            r0 = 400(0x190, float:5.6E-43)
            if (r10 >= r0) goto L_0x01a7
            java.lang.String r0 = "Location"
            java.lang.String r0 = r12.getHeaderField(r0)     // Catch:{ all -> 0x01ce }
            boolean r7 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x01ce }
            if (r7 == 0) goto L_0x0166
            java.lang.String r0 = "No location header to follow redirect."
            com.google.android.gms.internal.ads.zzaxz.zzeo(r0)     // Catch:{ all -> 0x01ce }
            com.google.android.gms.internal.ads.zzasm r0 = new com.google.android.gms.internal.ads.zzasm     // Catch:{ all -> 0x01ce }
            r1 = 0
            r0.<init>(r1)     // Catch:{ all -> 0x01ce }
            r12.disconnect()     // Catch:{ IOException -> 0x01da }
            if (r3 == 0) goto L_0x0165
            com.google.android.gms.internal.ads.zzauh r1 = r3.zzeaq     // Catch:{ IOException -> 0x01da }
            r1.zzxa()     // Catch:{ IOException -> 0x01da }
        L_0x0165:
            return r0
        L_0x0166:
            java.net.URL r7 = new java.net.URL     // Catch:{ all -> 0x01ce }
            r7.<init>(r0)     // Catch:{ all -> 0x01ce }
            r0 = 1
            int r11 = r11 + r0
            com.google.android.gms.internal.ads.zzaac<java.lang.Integer> r0 = com.google.android.gms.internal.ads.zzaan.zzcwx     // Catch:{ all -> 0x01ce }
            com.google.android.gms.internal.ads.zzaak r10 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ all -> 0x01ce }
            java.lang.Object r0 = r10.zzd(r0)     // Catch:{ all -> 0x01ce }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ all -> 0x01ce }
            int r0 = r0.intValue()     // Catch:{ all -> 0x01ce }
            if (r11 <= r0) goto L_0x0195
            java.lang.String r0 = "Too many redirects."
            com.google.android.gms.internal.ads.zzaxz.zzeo(r0)     // Catch:{ all -> 0x01ce }
            com.google.android.gms.internal.ads.zzasm r0 = new com.google.android.gms.internal.ads.zzasm     // Catch:{ all -> 0x01ce }
            r1 = 0
            r0.<init>(r1)     // Catch:{ all -> 0x01ce }
            r12.disconnect()     // Catch:{ IOException -> 0x01da }
            if (r3 == 0) goto L_0x0194
            com.google.android.gms.internal.ads.zzauh r1 = r3.zzeaq     // Catch:{ IOException -> 0x01da }
            r1.zzxa()     // Catch:{ IOException -> 0x01da }
        L_0x0194:
            return r0
        L_0x0195:
            r6.zzl(r13)     // Catch:{ all -> 0x01ce }
            r12.disconnect()     // Catch:{ IOException -> 0x01da }
            if (r3 == 0) goto L_0x01a2
            com.google.android.gms.internal.ads.zzauh r0 = r3.zzeaq     // Catch:{ IOException -> 0x01da }
            r0.zzxa()     // Catch:{ IOException -> 0x01da }
        L_0x01a2:
            r0 = r18
            r10 = 0
            goto L_0x0044
        L_0x01a7:
            r0 = 46
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ce }
            r1.<init>(r0)     // Catch:{ all -> 0x01ce }
            java.lang.String r0 = "Received error HTTP response code: "
            r1.append(r0)     // Catch:{ all -> 0x01ce }
            r1.append(r10)     // Catch:{ all -> 0x01ce }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x01ce }
            com.google.android.gms.internal.ads.zzaxz.zzeo(r0)     // Catch:{ all -> 0x01ce }
            com.google.android.gms.internal.ads.zzasm r0 = new com.google.android.gms.internal.ads.zzasm     // Catch:{ all -> 0x01ce }
            r1 = 0
            r0.<init>(r1)     // Catch:{ all -> 0x01ce }
            r12.disconnect()     // Catch:{ IOException -> 0x01da }
            if (r3 == 0) goto L_0x01cd
            com.google.android.gms.internal.ads.zzauh r1 = r3.zzeaq     // Catch:{ IOException -> 0x01da }
            r1.zzxa()     // Catch:{ IOException -> 0x01da }
        L_0x01cd:
            return r0
        L_0x01ce:
            r0 = move-exception
            r12.disconnect()     // Catch:{ IOException -> 0x01da }
            if (r3 == 0) goto L_0x01d9
            com.google.android.gms.internal.ads.zzauh r1 = r3.zzeaq     // Catch:{ IOException -> 0x01da }
            r1.zzxa()     // Catch:{ IOException -> 0x01da }
        L_0x01d9:
            throw r0     // Catch:{ IOException -> 0x01da }
        L_0x01da:
            r0 = move-exception
            java.lang.String r1 = "Error while connecting to ad server: "
            java.lang.String r0 = r0.getMessage()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r2 = r0.length()
            if (r2 == 0) goto L_0x01f0
            java.lang.String r0 = r1.concat(r0)
            goto L_0x01f5
        L_0x01f0:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r1)
        L_0x01f5:
            com.google.android.gms.internal.ads.zzaxz.zzeo(r0)
            com.google.android.gms.internal.ads.zzasm r0 = new com.google.android.gms.internal.ads.zzasm
            r1 = 2
            r0.<init>(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzatq.zza(com.google.android.gms.internal.ads.zzasi, android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.google.android.gms.internal.ads.zzaty, com.google.android.gms.internal.ads.zzaba, com.google.android.gms.internal.ads.zzatp):com.google.android.gms.internal.ads.zzasm");
    }

    private zzatq(Context context, zzatp zzatp) {
        this.mContext = context;
        this.zzeav = zzatp;
    }

    public final zzasm zzb(zzasi zzasi) {
        return zza(this.mContext, this.zzeav, zzasi, this.zzeaw);
    }

    public final void zza(zzasi zzasi, zzast zzast) {
        zzbv.zzlj().zzd(this.mContext, zzasi.zzbsp);
        zzbcb zzc = zzayf.zzc(new zzats(this, zzasi, zzast));
        zzbv.zzlv().zzaak();
        zzbv.zzlv().getHandler().postDelayed(new zzatt(this, zzc), DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS);
    }
}
