package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
@ParametersAreNonnullByDefault
public final class zzawg implements zzawr {
    /* access modifiers changed from: private */
    public static List<Future<Void>> zzefp = Collections.synchronizedList(new ArrayList());
    private static ScheduledExecutorService zzefq = Executors.newSingleThreadScheduledExecutor();
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private final zzawo zzecd;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final zzbvn zzefr;
    @GuardedBy("mLock")
    private final LinkedHashMap<String, zzbvt> zzefs;
    @GuardedBy("mLock")
    private final List<String> zzeft = new ArrayList();
    @GuardedBy("mLock")
    private final List<String> zzefu = new ArrayList();
    private final zzawt zzefv;
    @VisibleForTesting
    private boolean zzefw;
    private final zzawu zzefx;
    private HashSet<String> zzefy = new HashSet<>();
    private boolean zzefz = false;
    private boolean zzega = false;
    private boolean zzegb = false;

    public zzawg(Context context, zzbbi zzbbi, zzawo zzawo, String str, zzawt zzawt) {
        Preconditions.checkNotNull(zzawo, "SafeBrowsing config is not present.");
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.mContext = context;
        this.zzefs = new LinkedHashMap<>();
        this.zzefv = zzawt;
        this.zzecd = zzawo;
        for (String lowerCase : this.zzecd.zzegl) {
            this.zzefy.add(lowerCase.toLowerCase(Locale.ENGLISH));
        }
        this.zzefy.remove("cookie".toLowerCase(Locale.ENGLISH));
        zzbvn zzbvn = new zzbvn();
        zzbvn.zzgar = Integer.valueOf(8);
        zzbvn.url = str;
        zzbvn.zzgat = str;
        zzbvn.zzgav = new zzbvo();
        zzbvn.zzgav.zzegh = this.zzecd.zzegh;
        zzbvu zzbvu = new zzbvu();
        zzbvu.zzgcc = zzbbi.zzdp;
        zzbvu.zzgce = Boolean.valueOf(Wrappers.packageManager(this.mContext).isCallerInstantApp());
        long apkVersion = (long) GoogleApiAvailabilityLight.getInstance().getApkVersion(this.mContext);
        if (apkVersion > 0) {
            zzbvu.zzgcd = Long.valueOf(apkVersion);
        }
        zzbvn.zzgbf = zzbvu;
        this.zzefr = zzbvn;
        this.zzefx = new zzawu(this.mContext, this.zzecd.zzego, this);
    }

    static final /* synthetic */ Void zzdm(String str) {
        return null;
    }

    public final zzawo zzxp() {
        return this.zzecd;
    }

    public final void zzdi(String str) {
        synchronized (this.mLock) {
            this.zzefr.zzgax = str;
        }
    }

    public final boolean zzxq() {
        return PlatformVersion.isAtLeastKitKat() && this.zzecd.zzegj && !this.zzega;
    }

    public final void zzs(View view) {
        if (this.zzecd.zzegj && !this.zzega) {
            zzbv.zzlf();
            Bitmap zzu = zzayh.zzu(view);
            if (zzu == null) {
                zzawq.zzdn("Failed to capture the webview bitmap.");
                return;
            }
            this.zzega = true;
            zzayh.zzd((Runnable) new zzawj(this, zzu));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r7, java.util.Map<java.lang.String, java.lang.String> r8, int r9) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            r1 = 3
            if (r9 != r1) goto L_0x0009
            r2 = 1
            r6.zzegb = r2     // Catch:{ all -> 0x00c6 }
        L_0x0009:
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.zzbvt> r2 = r6.zzefs     // Catch:{ all -> 0x00c6 }
            boolean r2 = r2.containsKey(r7)     // Catch:{ all -> 0x00c6 }
            if (r2 == 0) goto L_0x0023
            if (r9 != r1) goto L_0x0021
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.zzbvt> r8 = r6.zzefs     // Catch:{ all -> 0x00c6 }
            java.lang.Object r7 = r8.get(r7)     // Catch:{ all -> 0x00c6 }
            com.google.android.gms.internal.ads.zzbvt r7 = (com.google.android.gms.internal.ads.zzbvt) r7     // Catch:{ all -> 0x00c6 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x00c6 }
            r7.zzgca = r8     // Catch:{ all -> 0x00c6 }
        L_0x0021:
            monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
            return
        L_0x0023:
            com.google.android.gms.internal.ads.zzbvt r1 = new com.google.android.gms.internal.ads.zzbvt     // Catch:{ all -> 0x00c6 }
            r1.<init>()     // Catch:{ all -> 0x00c6 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x00c6 }
            r1.zzgca = r9     // Catch:{ all -> 0x00c6 }
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.zzbvt> r9 = r6.zzefs     // Catch:{ all -> 0x00c6 }
            int r9 = r9.size()     // Catch:{ all -> 0x00c6 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x00c6 }
            r1.zzgbu = r9     // Catch:{ all -> 0x00c6 }
            r1.url = r7     // Catch:{ all -> 0x00c6 }
            com.google.android.gms.internal.ads.zzbvq r9 = new com.google.android.gms.internal.ads.zzbvq     // Catch:{ all -> 0x00c6 }
            r9.<init>()     // Catch:{ all -> 0x00c6 }
            r1.zzgbv = r9     // Catch:{ all -> 0x00c6 }
            java.util.HashSet<java.lang.String> r9 = r6.zzefy     // Catch:{ all -> 0x00c6 }
            int r9 = r9.size()     // Catch:{ all -> 0x00c6 }
            if (r9 <= 0) goto L_0x00bf
            if (r8 == 0) goto L_0x00bf
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ all -> 0x00c6 }
            r9.<init>()     // Catch:{ all -> 0x00c6 }
            java.util.Set r8 = r8.entrySet()     // Catch:{ all -> 0x00c6 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ all -> 0x00c6 }
        L_0x005a:
            boolean r2 = r8.hasNext()     // Catch:{ all -> 0x00c6 }
            if (r2 == 0) goto L_0x00b2
            java.lang.Object r2 = r8.next()     // Catch:{ all -> 0x00c6 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x00c6 }
            java.lang.Object r3 = r2.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            if (r3 == 0) goto L_0x0073
            java.lang.Object r3 = r2.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            goto L_0x0075
        L_0x0073:
            java.lang.String r3 = ""
        L_0x0075:
            java.lang.Object r4 = r2.getValue()     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            if (r4 == 0) goto L_0x0082
            java.lang.Object r2 = r2.getValue()     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            goto L_0x0084
        L_0x0082:
            java.lang.String r2 = ""
        L_0x0084:
            java.util.Locale r4 = java.util.Locale.ENGLISH     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.lang.String r4 = r3.toLowerCase(r4)     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.util.HashSet<java.lang.String> r5 = r6.zzefy     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            boolean r4 = r5.contains(r4)     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            if (r4 != 0) goto L_0x0093
            goto L_0x005a
        L_0x0093:
            com.google.android.gms.internal.ads.zzbvp r4 = new com.google.android.gms.internal.ads.zzbvp     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            r4.<init>()     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.lang.String r5 = "UTF-8"
            byte[] r3 = r3.getBytes(r5)     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            r4.zzgbj = r3     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.lang.String r3 = "UTF-8"
            byte[] r2 = r2.getBytes(r3)     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            r4.zzgbk = r2     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            r9.add(r4)     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            goto L_0x005a
        L_0x00ac:
            java.lang.String r2 = "Cannot convert string to bytes, skip header."
            com.google.android.gms.internal.ads.zzawq.zzdn(r2)     // Catch:{ all -> 0x00c6 }
            goto L_0x005a
        L_0x00b2:
            int r8 = r9.size()     // Catch:{ all -> 0x00c6 }
            com.google.android.gms.internal.ads.zzbvp[] r8 = new com.google.android.gms.internal.ads.zzbvp[r8]     // Catch:{ all -> 0x00c6 }
            r9.toArray(r8)     // Catch:{ all -> 0x00c6 }
            com.google.android.gms.internal.ads.zzbvq r9 = r1.zzgbv     // Catch:{ all -> 0x00c6 }
            r9.zzgbm = r8     // Catch:{ all -> 0x00c6 }
        L_0x00bf:
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.zzbvt> r8 = r6.zzefs     // Catch:{ all -> 0x00c6 }
            r8.put(r7, r1)     // Catch:{ all -> 0x00c6 }
            monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
            return
        L_0x00c6:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzawg.zza(java.lang.String, java.util.Map, int):void");
    }

    /* access modifiers changed from: 0000 */
    public final void zzdj(String str) {
        synchronized (this.mLock) {
            this.zzeft.add(str);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzdk(String str) {
        synchronized (this.mLock) {
            this.zzefu.add(str);
        }
    }

    public final String[] zzb(String[] strArr) {
        return (String[]) this.zzefx.zzc(strArr).toArray(new String[0]);
    }

    public final void zzxr() {
        this.zzefz = true;
    }

    @Nullable
    private final zzbvt zzdl(String str) {
        zzbvt zzbvt;
        synchronized (this.mLock) {
            zzbvt = (zzbvt) this.zzefs.get(str);
        }
        return zzbvt;
    }

    public final void zzxs() {
        synchronized (this.mLock) {
            zzbcb zza = zzbbq.zza(this.zzefv.zza(this.mContext, this.zzefs.keySet()), (zzbbl<? super A, ? extends B>) new zzawh<Object,Object>(this), zzbcg.zzepp);
            zzbcb zza2 = zzbbq.zza(zza, 10, TimeUnit.SECONDS, zzefq);
            zzbbq.zza(zza, (zzbbn<V>) new zzawk<V>(this, zza2), zzbcg.zzepp);
            zzefp.add(zza2);
        }
    }

    @VisibleForTesting
    private final zzbcb<Void> zzxt() {
        zzbcb<Void> zza;
        zzbvt[] zzbvtArr;
        if (!((this.zzefw && this.zzecd.zzegn) || (this.zzegb && this.zzecd.zzegm) || (!this.zzefw && this.zzecd.zzegk))) {
            return zzbbq.zzm(null);
        }
        synchronized (this.mLock) {
            this.zzefr.zzgaw = new zzbvt[this.zzefs.size()];
            this.zzefs.values().toArray(this.zzefr.zzgaw);
            this.zzefr.zzgbg = (String[]) this.zzeft.toArray(new String[0]);
            this.zzefr.zzgbh = (String[]) this.zzefu.toArray(new String[0]);
            if (zzawq.isEnabled()) {
                String str = this.zzefr.url;
                String str2 = this.zzefr.zzgax;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 53 + String.valueOf(str2).length());
                sb.append("Sending SB report\n  url: ");
                sb.append(str);
                sb.append("\n  clickUrl: ");
                sb.append(str2);
                sb.append("\n  resources: \n");
                StringBuilder sb2 = new StringBuilder(sb.toString());
                for (zzbvt zzbvt : this.zzefr.zzgaw) {
                    sb2.append("    [");
                    sb2.append(zzbvt.zzgcb.length);
                    sb2.append("] ");
                    sb2.append(zzbvt.url);
                }
                zzawq.zzdn(sb2.toString());
            }
            zzbcb zza2 = new zzazs(this.mContext).zza(1, this.zzecd.zzegi, null, zzbuz.zzb(this.zzefr));
            if (zzawq.isEnabled()) {
                zza2.zza(new zzawl(this), zzayf.zzeky);
            }
            zza = zzbbq.zza(zza2, zzawi.zzegd, zzbcg.zzepp);
        }
        return zza;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ zzbcb zzm(Map map) throws Exception {
        if (map != null) {
            try {
                for (String str : map.keySet()) {
                    JSONArray optJSONArray = new JSONObject((String) map.get(str)).optJSONArray("matches");
                    if (optJSONArray != null) {
                        synchronized (this.mLock) {
                            int length = optJSONArray.length();
                            zzbvt zzdl = zzdl(str);
                            if (zzdl == null) {
                                String str2 = "Cannot find the corresponding resource object for ";
                                String valueOf = String.valueOf(str);
                                zzawq.zzdn(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                            } else {
                                zzdl.zzgcb = new String[length];
                                boolean z = false;
                                for (int i = 0; i < length; i++) {
                                    zzdl.zzgcb[i] = optJSONArray.getJSONObject(i).getString("threat_type");
                                }
                                boolean z2 = this.zzefw;
                                if (length > 0) {
                                    z = true;
                                }
                                this.zzefw = z | z2;
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                String str3 = "Failed to get SafeBrowsing metadata";
                if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcvm)).booleanValue()) {
                    zzaxz.zza(str3, e);
                }
                return zzbbq.zzd(new Exception("Safebrowsing report transmission failed."));
            }
        }
        if (this.zzefw) {
            synchronized (this.mLock) {
                this.zzefr.zzgar = Integer.valueOf(9);
            }
        }
        return zzxt();
    }
}
