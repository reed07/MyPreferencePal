package com.google.android.gms.internal.ads;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.common.util.VisibleForTesting;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import javax.annotation.concurrent.GuardedBy;

@zzark
public final class zzaxp {
    private final Object mLock = new Object();
    @VisibleForTesting
    private long zzejm = -1;
    @VisibleForTesting
    private long zzejn = -1;
    @GuardedBy("mLock")
    @VisibleForTesting
    private int zzejo = -1;
    @VisibleForTesting
    int zzejp = -1;
    @VisibleForTesting
    private long zzejq = 0;
    @VisibleForTesting
    private final String zzejr;
    private final zzayb zzejs;
    @GuardedBy("mLock")
    @VisibleForTesting
    private int zzejt = 0;
    @GuardedBy("mLock")
    @VisibleForTesting
    private int zzeju = 0;

    public zzaxp(String str, zzayb zzayb) {
        this.zzejr = str;
        this.zzejs = zzayb;
    }

    public final void zzxw() {
        synchronized (this.mLock) {
            this.zzejt++;
        }
    }

    public final void zzxv() {
        synchronized (this.mLock) {
            this.zzeju++;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.internal.ads.zzwb r11, long r12) {
        /*
            r10 = this;
            java.lang.Object r0 = r10.mLock
            monitor-enter(r0)
            com.google.android.gms.internal.ads.zzayb r1 = r10.zzejs     // Catch:{ all -> 0x007b }
            long r1 = r1.zzzj()     // Catch:{ all -> 0x007b }
            com.google.android.gms.common.util.Clock r3 = com.google.android.gms.ads.internal.zzbv.zzlm()     // Catch:{ all -> 0x007b }
            long r3 = r3.currentTimeMillis()     // Catch:{ all -> 0x007b }
            long r5 = r10.zzejn     // Catch:{ all -> 0x007b }
            r7 = -1
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x0042
            long r1 = r3 - r1
            com.google.android.gms.internal.ads.zzaac<java.lang.Long> r5 = com.google.android.gms.internal.ads.zzaan.zzcrn     // Catch:{ all -> 0x007b }
            com.google.android.gms.internal.ads.zzaak r6 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ all -> 0x007b }
            java.lang.Object r5 = r6.zzd(r5)     // Catch:{ all -> 0x007b }
            java.lang.Long r5 = (java.lang.Long) r5     // Catch:{ all -> 0x007b }
            long r5 = r5.longValue()     // Catch:{ all -> 0x007b }
            int r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0033
            r1 = -1
            r10.zzejp = r1     // Catch:{ all -> 0x007b }
            goto L_0x003b
        L_0x0033:
            com.google.android.gms.internal.ads.zzayb r1 = r10.zzejs     // Catch:{ all -> 0x007b }
            int r1 = r1.zzzk()     // Catch:{ all -> 0x007b }
            r10.zzejp = r1     // Catch:{ all -> 0x007b }
        L_0x003b:
            r10.zzejn = r12     // Catch:{ all -> 0x007b }
            long r12 = r10.zzejn     // Catch:{ all -> 0x007b }
            r10.zzejm = r12     // Catch:{ all -> 0x007b }
            goto L_0x0044
        L_0x0042:
            r10.zzejm = r12     // Catch:{ all -> 0x007b }
        L_0x0044:
            r12 = 1
            if (r11 == 0) goto L_0x0058
            android.os.Bundle r13 = r11.extras     // Catch:{ all -> 0x007b }
            if (r13 == 0) goto L_0x0058
            android.os.Bundle r11 = r11.extras     // Catch:{ all -> 0x007b }
            java.lang.String r13 = "gw"
            r1 = 2
            int r11 = r11.getInt(r13, r1)     // Catch:{ all -> 0x007b }
            if (r11 != r12) goto L_0x0058
            monitor-exit(r0)     // Catch:{ all -> 0x007b }
            return
        L_0x0058:
            int r11 = r10.zzejo     // Catch:{ all -> 0x007b }
            int r11 = r11 + r12
            r10.zzejo = r11     // Catch:{ all -> 0x007b }
            int r11 = r10.zzejp     // Catch:{ all -> 0x007b }
            int r11 = r11 + r12
            r10.zzejp = r11     // Catch:{ all -> 0x007b }
            int r11 = r10.zzejp     // Catch:{ all -> 0x007b }
            if (r11 != 0) goto L_0x0070
            r11 = 0
            r10.zzejq = r11     // Catch:{ all -> 0x007b }
            com.google.android.gms.internal.ads.zzayb r11 = r10.zzejs     // Catch:{ all -> 0x007b }
            r11.zzav(r3)     // Catch:{ all -> 0x007b }
            goto L_0x0079
        L_0x0070:
            com.google.android.gms.internal.ads.zzayb r11 = r10.zzejs     // Catch:{ all -> 0x007b }
            long r11 = r11.zzzl()     // Catch:{ all -> 0x007b }
            long r3 = r3 - r11
            r10.zzejq = r3     // Catch:{ all -> 0x007b }
        L_0x0079:
            monitor-exit(r0)     // Catch:{ all -> 0x007b }
            return
        L_0x007b:
            r11 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x007b }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaxp.zzb(com.google.android.gms.internal.ads.zzwb, long):void");
    }

    public final Bundle zzl(Context context, String str) {
        Bundle bundle;
        synchronized (this.mLock) {
            bundle = new Bundle();
            bundle.putString(Attributes.SESSION_ID, this.zzejr);
            bundle.putLong("basets", this.zzejn);
            bundle.putLong("currts", this.zzejm);
            bundle.putString("seq_num", str);
            bundle.putInt("preqs", this.zzejo);
            bundle.putInt("preqs_in_session", this.zzejp);
            bundle.putLong("time_in_session", this.zzejq);
            bundle.putInt("pclick", this.zzejt);
            bundle.putInt("pimp", this.zzeju);
            bundle.putBoolean("support_transparent_background", zzaf(context));
        }
        return bundle;
    }

    private static boolean zzaf(Context context) {
        int identifier = context.getResources().getIdentifier("Theme.Translucent", "style", "android");
        if (identifier == 0) {
            zzaxz.zzen("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        try {
            if (identifier == context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), AdActivity.CLASS_NAME), 0).theme) {
                return true;
            }
            zzaxz.zzen("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        } catch (NameNotFoundException unused) {
            zzaxz.zzeo("Fail to fetch AdActivity theme");
            zzaxz.zzen("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
    }
}
