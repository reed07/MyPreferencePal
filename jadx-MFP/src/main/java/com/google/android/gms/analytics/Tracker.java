package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzau;
import com.google.android.gms.internal.measurement.zzaw;
import com.google.android.gms.internal.measurement.zzcn;
import com.google.android.gms.internal.measurement.zzdf;
import com.google.android.gms.internal.measurement.zzdg;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.myfitnesspal.shared.constants.UtmParams;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

@VisibleForTesting
public class Tracker extends zzau {
    private boolean zzsx;
    private final Map<String, String> zzsy = new HashMap();
    private final Map<String, String> zzsz = new HashMap();
    /* access modifiers changed from: private */
    public final zzcn zzta;
    /* access modifiers changed from: private */
    public final zza zztb;
    private ExceptionReporter zztc;
    /* access modifiers changed from: private */
    public zzdf zztd;

    class zza extends zzau implements zza {
        private boolean zztm;
        private int zztn;
        private long zzto = -1;
        private boolean zztp;
        private long zztq;

        protected zza(zzaw zzaw) {
            super(zzaw);
        }

        /* access modifiers changed from: protected */
        public final void zzag() {
        }

        public final void setSessionTimeout(long j) {
            this.zzto = j;
            zzai();
        }

        public final void enableAutoActivityTracking(boolean z) {
            this.zztm = z;
            zzai();
        }

        public final synchronized boolean zzah() {
            boolean z;
            z = this.zztp;
            this.zztp = false;
            return z;
        }

        private final void zzai() {
            if (this.zzto >= 0 || this.zztm) {
                zzcb().zza((zza) Tracker.this.zztb);
            } else {
                zzcb().zzb((zza) Tracker.this.zztb);
            }
        }

        public final void zzc(Activity activity) {
            String str;
            if (this.zztn == 0) {
                if (zzbx().elapsedRealtime() >= this.zztq + Math.max(1000, this.zzto)) {
                    this.zztp = true;
                }
            }
            this.zztn++;
            if (this.zztm) {
                Intent intent = activity.getIntent();
                if (intent != null) {
                    Tracker.this.setCampaignParamsOnNextHit(intent.getData());
                }
                HashMap hashMap = new HashMap();
                hashMap.put("&t", "screenview");
                Tracker tracker = Tracker.this;
                String str2 = "&cd";
                if (tracker.zztd != null) {
                    zzdf zzk = Tracker.this.zztd;
                    str = activity.getClass().getCanonicalName();
                    String str3 = (String) zzk.zzaco.get(str);
                    if (str3 != null) {
                        str = str3;
                    }
                } else {
                    str = activity.getClass().getCanonicalName();
                }
                tracker.set(str2, str);
                if (TextUtils.isEmpty((CharSequence) hashMap.get("&dr"))) {
                    Preconditions.checkNotNull(activity);
                    Intent intent2 = activity.getIntent();
                    CharSequence charSequence = null;
                    if (intent2 != null) {
                        String stringExtra = intent2.getStringExtra("android.intent.extra.REFERRER_NAME");
                        if (!TextUtils.isEmpty(stringExtra)) {
                            charSequence = stringExtra;
                        }
                    }
                    if (!TextUtils.isEmpty(charSequence)) {
                        hashMap.put("&dr", charSequence);
                    }
                }
                Tracker.this.send(hashMap);
            }
        }

        public final void zzd(Activity activity) {
            this.zztn--;
            this.zztn = Math.max(0, this.zztn);
            if (this.zztn == 0) {
                this.zztq = zzbx().elapsedRealtime();
            }
        }
    }

    Tracker(zzaw zzaw, String str, zzcn zzcn) {
        super(zzaw);
        if (str != null) {
            this.zzsy.put("&tid", str);
        }
        this.zzsy.put("useSecure", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        this.zzsy.put("&a", Integer.toString(new Random().nextInt(Integer.MAX_VALUE) + 1));
        this.zzta = new zzcn("tracking", zzbx());
        this.zztb = new zza(zzaw);
    }

    /* access modifiers changed from: protected */
    public final void zzag() {
        this.zztb.zzq();
        String zzaj = zzce().zzaj();
        if (zzaj != null) {
            set("&an", zzaj);
        }
        String zzak = zzce().zzak();
        if (zzak != null) {
            set("&av", zzak);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzdf zzdf) {
        zzq("Loading Tracker config values");
        this.zztd = zzdf;
        boolean z = false;
        if (this.zztd.zzaci != null) {
            String str = this.zztd.zzaci;
            set("&tid", str);
            zza("trackingId loaded", str);
        }
        if (this.zztd.zzacj >= 0.0d) {
            String d = Double.toString(this.zztd.zzacj);
            set("&sf", d);
            zza("Sample frequency loaded", d);
        }
        if (this.zztd.zzack >= 0) {
            int i = this.zztd.zzack;
            setSessionTimeout((long) i);
            zza("Session timeout loaded", Integer.valueOf(i));
        }
        if (this.zztd.zzacl != -1) {
            boolean z2 = this.zztd.zzacl == 1;
            enableAutoActivityTracking(z2);
            zza("Auto activity tracking loaded", Boolean.valueOf(z2));
        }
        if (this.zztd.zzacm != -1) {
            boolean z3 = this.zztd.zzacm == 1;
            if (z3) {
                set("&aip", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            }
            zza("Anonymize ip loaded", Boolean.valueOf(z3));
        }
        if (this.zztd.zzacn == 1) {
            z = true;
        }
        enableExceptionReporting(z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0037, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void enableExceptionReporting(boolean r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            com.google.android.gms.analytics.ExceptionReporter r0 = r2.zztc     // Catch:{ all -> 0x0038 }
            if (r0 == 0) goto L_0x0007
            r0 = 1
            goto L_0x0008
        L_0x0007:
            r0 = 0
        L_0x0008:
            if (r0 != r3) goto L_0x000c
            monitor-exit(r2)     // Catch:{ all -> 0x0038 }
            return
        L_0x000c:
            if (r3 == 0) goto L_0x0028
            android.content.Context r3 = r2.getContext()     // Catch:{ all -> 0x0038 }
            java.lang.Thread$UncaughtExceptionHandler r0 = java.lang.Thread.getDefaultUncaughtExceptionHandler()     // Catch:{ all -> 0x0038 }
            com.google.android.gms.analytics.ExceptionReporter r1 = new com.google.android.gms.analytics.ExceptionReporter     // Catch:{ all -> 0x0038 }
            r1.<init>(r2, r0, r3)     // Catch:{ all -> 0x0038 }
            r2.zztc = r1     // Catch:{ all -> 0x0038 }
            com.google.android.gms.analytics.ExceptionReporter r3 = r2.zztc     // Catch:{ all -> 0x0038 }
            java.lang.Thread.setDefaultUncaughtExceptionHandler(r3)     // Catch:{ all -> 0x0038 }
            java.lang.String r3 = "Uncaught exceptions will be reported to Google Analytics"
            r2.zzq(r3)     // Catch:{ all -> 0x0038 }
            goto L_0x0036
        L_0x0028:
            com.google.android.gms.analytics.ExceptionReporter r3 = r2.zztc     // Catch:{ all -> 0x0038 }
            java.lang.Thread$UncaughtExceptionHandler r3 = r3.zzp()     // Catch:{ all -> 0x0038 }
            java.lang.Thread.setDefaultUncaughtExceptionHandler(r3)     // Catch:{ all -> 0x0038 }
            java.lang.String r3 = "Uncaught exceptions will not be reported to Google Analytics"
            r2.zzq(r3)     // Catch:{ all -> 0x0038 }
        L_0x0036:
            monitor-exit(r2)     // Catch:{ all -> 0x0038 }
            return
        L_0x0038:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0038 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.Tracker.enableExceptionReporting(boolean):void");
    }

    public void setSessionTimeout(long j) {
        this.zztb.setSessionTimeout(j * 1000);
    }

    public void enableAutoActivityTracking(boolean z) {
        this.zztb.enableAutoActivityTracking(z);
    }

    private static String zza(Entry<String, String> entry) {
        String str = (String) entry.getKey();
        if (!(str.startsWith("&") && str.length() >= 2)) {
            return null;
        }
        return ((String) entry.getKey()).substring(1);
    }

    private static void zza(Map<String, String> map, Map<String, String> map2) {
        Preconditions.checkNotNull(map2);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                String zza2 = zza(entry);
                if (zza2 != null) {
                    map2.put(zza2, (String) entry.getValue());
                }
            }
        }
    }

    public void send(Map<String, String> map) {
        long currentTimeMillis = zzbx().currentTimeMillis();
        if (zzcb().getAppOptOut()) {
            zzr("AppOptOut is set to true. Not sending Google Analytics hit");
            return;
        }
        boolean isDryRunEnabled = zzcb().isDryRunEnabled();
        HashMap hashMap = new HashMap();
        zza(this.zzsy, hashMap);
        zza(map, hashMap);
        boolean zzb = zzdg.zzb((String) this.zzsy.get("useSecure"), true);
        Map<String, String> map2 = this.zzsz;
        Preconditions.checkNotNull(hashMap);
        if (map2 != null) {
            for (Entry entry : map2.entrySet()) {
                String zza2 = zza(entry);
                if (zza2 != null && !hashMap.containsKey(zza2)) {
                    hashMap.put(zza2, (String) entry.getValue());
                }
            }
        }
        this.zzsz.clear();
        String str = (String) hashMap.get("t");
        if (TextUtils.isEmpty(str)) {
            zzby().zza((Map<String, String>) hashMap, "Missing hit type parameter");
            return;
        }
        String str2 = (String) hashMap.get("tid");
        if (TextUtils.isEmpty(str2)) {
            zzby().zza((Map<String, String>) hashMap, "Missing tracking id parameter");
            return;
        }
        boolean z = this.zzsx;
        synchronized (this) {
            if ("screenview".equalsIgnoreCase(str) || "pageview".equalsIgnoreCase(str) || "appview".equalsIgnoreCase(str) || TextUtils.isEmpty(str)) {
                int parseInt = Integer.parseInt((String) this.zzsy.get("&a")) + 1;
                if (parseInt >= Integer.MAX_VALUE) {
                    parseInt = 1;
                }
                this.zzsy.put("&a", Integer.toString(parseInt));
            }
        }
        zzk zzca = zzca();
        zzp zzp = new zzp(this, hashMap, z, str, currentTimeMillis, isDryRunEnabled, zzb, str2);
        zzca.zza((Runnable) zzp);
    }

    public String get(String str) {
        zzcl();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.zzsy.containsKey(str)) {
            return (String) this.zzsy.get(str);
        }
        if (str.equals("&ul")) {
            return zzdg.zza(Locale.getDefault());
        }
        if (str.equals("&cid")) {
            return zzcg().zzdr();
        }
        if (str.equals("&sr")) {
            return zzcj().zzel();
        }
        if (str.equals("&aid")) {
            return zzci().zzdf().zzal();
        }
        if (str.equals("&an")) {
            return zzci().zzdf().zzaj();
        }
        if (str.equals("&av")) {
            return zzci().zzdf().zzak();
        }
        if (str.equals("&aiid")) {
            return zzci().zzdf().zzam();
        }
        return null;
    }

    public void set(String str, String str2) {
        Preconditions.checkNotNull(str, "Key should be non-null");
        if (!TextUtils.isEmpty(str)) {
            this.zzsy.put(str, str2);
        }
    }

    public void setSampleRate(double d) {
        set("&sf", Double.toString(d));
    }

    public void setUseSecure(boolean z) {
        set("useSecure", zzdg.zzc(z));
    }

    public void setScreenName(String str) {
        set("&cd", str);
    }

    public void setLocation(String str) {
        set("&dl", str);
    }

    public void setReferrer(String str) {
        set("&dr", str);
    }

    public void setPage(String str) {
        set("&dp", str);
    }

    public void setHostname(String str) {
        set("&dh", str);
    }

    public void setTitle(String str) {
        set("&dt", str);
    }

    public void setLanguage(String str) {
        set("&ul", str);
    }

    public void setEncoding(String str) {
        set("&de", str);
    }

    public void setScreenColors(String str) {
        set("&sd", str);
    }

    public void setScreenResolution(int i, int i2) {
        if (i >= 0 || i2 >= 0) {
            StringBuilder sb = new StringBuilder(23);
            sb.append(i);
            sb.append(AvidJSONUtil.KEY_X);
            sb.append(i2);
            set("&sr", sb.toString());
            return;
        }
        zzt("Invalid width or height. The values should be non-negative.");
    }

    public void setViewportSize(String str) {
        set("&vp", str);
    }

    public void setClientId(String str) {
        set("&cid", str);
    }

    public void setAppName(String str) {
        set("&an", str);
    }

    public void setAppId(String str) {
        set("&aid", str);
    }

    public void setAppInstallerId(String str) {
        set("&aiid", str);
    }

    public void setAppVersion(String str) {
        set("&av", str);
    }

    public void setAnonymizeIp(boolean z) {
        set("&aip", zzdg.zzc(z));
    }

    public void setCampaignParamsOnNextHit(Uri uri) {
        if (uri != null && !uri.isOpaque()) {
            String queryParameter = uri.getQueryParameter("referrer");
            if (!TextUtils.isEmpty(queryParameter)) {
                String str = "http://hostname/?";
                String valueOf = String.valueOf(queryParameter);
                Uri parse = Uri.parse(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                String queryParameter2 = parse.getQueryParameter("utm_id");
                if (queryParameter2 != null) {
                    this.zzsz.put("&ci", queryParameter2);
                }
                String queryParameter3 = parse.getQueryParameter("anid");
                if (queryParameter3 != null) {
                    this.zzsz.put("&anid", queryParameter3);
                }
                String queryParameter4 = parse.getQueryParameter("utm_campaign");
                if (queryParameter4 != null) {
                    this.zzsz.put("&cn", queryParameter4);
                }
                String queryParameter5 = parse.getQueryParameter(UtmParams.UTM_CONTENT);
                if (queryParameter5 != null) {
                    this.zzsz.put("&cc", queryParameter5);
                }
                String queryParameter6 = parse.getQueryParameter("utm_medium");
                if (queryParameter6 != null) {
                    this.zzsz.put("&cm", queryParameter6);
                }
                String queryParameter7 = parse.getQueryParameter("utm_source");
                if (queryParameter7 != null) {
                    this.zzsz.put("&cs", queryParameter7);
                }
                String queryParameter8 = parse.getQueryParameter(UtmParams.UTM_TERM);
                if (queryParameter8 != null) {
                    this.zzsz.put("&ck", queryParameter8);
                }
                String queryParameter9 = parse.getQueryParameter("dclid");
                if (queryParameter9 != null) {
                    this.zzsz.put("&dclid", queryParameter9);
                }
                String queryParameter10 = parse.getQueryParameter("gclid");
                if (queryParameter10 != null) {
                    this.zzsz.put("&gclid", queryParameter10);
                }
                String queryParameter11 = parse.getQueryParameter(Param.ACLID);
                if (queryParameter11 != null) {
                    this.zzsz.put("&aclid", queryParameter11);
                }
            }
        }
    }

    public void enableAdvertisingIdCollection(boolean z) {
        this.zzsx = z;
    }
}
