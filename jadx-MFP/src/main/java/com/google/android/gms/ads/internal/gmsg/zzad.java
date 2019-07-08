package com.google.android.gms.ads.internal.gmsg;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.View;
import com.google.android.exoplayer2.C;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzcu;
import java.util.ArrayList;
import java.util.List;

@VisibleForTesting
public final class zzad {
    private final Context mContext;
    private final View mView;
    private final zzcu zzdgf;

    public zzad(Context context, zzcu zzcu, View view) {
        this.mContext = context;
        this.zzdgf = zzcu;
        this.mView = view;
    }

    private static Intent zze(Uri uri) {
        if (uri == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        intent.setData(uri);
        intent.setAction("android.intent.action.VIEW");
        return intent;
    }

    @VisibleForTesting
    private final ResolveInfo zzb(Intent intent) {
        return zza(intent, new ArrayList<>());
    }

    @VisibleForTesting
    private final ResolveInfo zza(Intent intent, ArrayList<ResolveInfo> arrayList) {
        ResolveInfo resolveInfo = null;
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
            if (queryIntentActivities != null && resolveActivity != null) {
                int i = 0;
                while (true) {
                    if (i >= queryIntentActivities.size()) {
                        break;
                    }
                    ResolveInfo resolveInfo2 = (ResolveInfo) queryIntentActivities.get(i);
                    if (resolveActivity != null && resolveActivity.activityInfo.name.equals(resolveInfo2.activityInfo.name)) {
                        resolveInfo = resolveActivity;
                        break;
                    }
                    i++;
                }
            }
            arrayList.addAll(queryIntentActivities);
            return resolveInfo;
        } catch (Throwable th) {
            zzbv.zzlj().zza(th, "OpenSystemBrowserHandler.getDefaultBrowserResolverForIntent");
        }
    }

    private static Intent zza(Intent intent, ResolveInfo resolveInfo) {
        Intent intent2 = new Intent(intent);
        intent2.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        return intent2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00c8  */
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.content.Intent zzi(java.util.Map<java.lang.String, java.lang.String> r12) {
        /*
            r11 = this;
            android.content.Context r0 = r11.mContext
            java.lang.String r1 = "activity"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0
            java.lang.String r1 = "u"
            java.lang.Object r1 = r12.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            r3 = 0
            if (r2 == 0) goto L_0x001b
            return r3
        L_0x001b:
            android.content.Context r2 = r11.mContext
            com.google.android.gms.internal.ads.zzcu r4 = r11.zzdgf
            android.view.View r5 = r11.mView
            java.lang.String r1 = com.google.android.gms.ads.internal.gmsg.zzac.zza(r2, r4, r1, r5, r3)
            android.net.Uri r1 = android.net.Uri.parse(r1)
            java.lang.String r2 = "use_first_package"
            java.lang.Object r2 = r12.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            boolean r2 = java.lang.Boolean.parseBoolean(r2)
            java.lang.String r4 = "use_running_process"
            java.lang.Object r4 = r12.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            boolean r4 = java.lang.Boolean.parseBoolean(r4)
            java.lang.String r5 = "use_custom_tabs"
            java.lang.Object r12 = r12.get(r5)
            java.lang.String r12 = (java.lang.String) r12
            boolean r12 = java.lang.Boolean.parseBoolean(r12)
            r5 = 0
            if (r12 != 0) goto L_0x0068
            com.google.android.gms.internal.ads.zzaac<java.lang.Boolean> r12 = com.google.android.gms.internal.ads.zzaan.zzcwb
            com.google.android.gms.internal.ads.zzaak r6 = com.google.android.gms.internal.ads.zzwu.zzpz()
            java.lang.Object r12 = r6.zzd(r12)
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L_0x0066
            goto L_0x0068
        L_0x0066:
            r12 = 0
            goto L_0x0069
        L_0x0068:
            r12 = 1
        L_0x0069:
            java.lang.String r6 = "http"
            java.lang.String r7 = r1.getScheme()
            boolean r6 = r6.equalsIgnoreCase(r7)
            if (r6 == 0) goto L_0x0084
            android.net.Uri$Builder r3 = r1.buildUpon()
            java.lang.String r6 = "https"
            android.net.Uri$Builder r3 = r3.scheme(r6)
            android.net.Uri r3 = r3.build()
            goto L_0x009e
        L_0x0084:
            java.lang.String r6 = "https"
            java.lang.String r7 = r1.getScheme()
            boolean r6 = r6.equalsIgnoreCase(r7)
            if (r6 == 0) goto L_0x009e
            android.net.Uri$Builder r3 = r1.buildUpon()
            java.lang.String r6 = "http"
            android.net.Uri$Builder r3 = r3.scheme(r6)
            android.net.Uri r3 = r3.build()
        L_0x009e:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            android.content.Intent r1 = zze(r1)
            android.content.Intent r3 = zze(r3)
            if (r12 == 0) goto L_0x00bd
            com.google.android.gms.ads.internal.zzbv.zzlf()
            android.content.Context r12 = r11.mContext
            com.google.android.gms.internal.ads.zzayh.zzb(r12, r1)
            com.google.android.gms.ads.internal.zzbv.zzlf()
            android.content.Context r12 = r11.mContext
            com.google.android.gms.internal.ads.zzayh.zzb(r12, r3)
        L_0x00bd:
            android.content.pm.ResolveInfo r12 = r11.zza(r1, r6)
            if (r12 == 0) goto L_0x00c8
            android.content.Intent r12 = zza(r1, r12)
            return r12
        L_0x00c8:
            if (r3 == 0) goto L_0x00db
            android.content.pm.ResolveInfo r12 = r11.zzb(r3)
            if (r12 == 0) goto L_0x00db
            android.content.Intent r12 = zza(r1, r12)
            android.content.pm.ResolveInfo r3 = r11.zzb(r12)
            if (r3 == 0) goto L_0x00db
            return r12
        L_0x00db:
            int r12 = r6.size()
            if (r12 != 0) goto L_0x00e2
            return r1
        L_0x00e2:
            if (r4 == 0) goto L_0x011f
            if (r0 == 0) goto L_0x011f
            java.util.List r12 = r0.getRunningAppProcesses()
            if (r12 == 0) goto L_0x011f
            r0 = r6
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r3 = r0.size()
            r4 = 0
        L_0x00f4:
            if (r4 >= r3) goto L_0x011f
            java.lang.Object r7 = r0.get(r4)
            int r4 = r4 + 1
            android.content.pm.ResolveInfo r7 = (android.content.pm.ResolveInfo) r7
            java.util.Iterator r8 = r12.iterator()
        L_0x0102:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x00f4
            java.lang.Object r9 = r8.next()
            android.app.ActivityManager$RunningAppProcessInfo r9 = (android.app.ActivityManager.RunningAppProcessInfo) r9
            java.lang.String r9 = r9.processName
            android.content.pm.ActivityInfo r10 = r7.activityInfo
            java.lang.String r10 = r10.packageName
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0102
            android.content.Intent r12 = zza(r1, r7)
            return r12
        L_0x011f:
            if (r2 == 0) goto L_0x012c
            java.lang.Object r12 = r6.get(r5)
            android.content.pm.ResolveInfo r12 = (android.content.pm.ResolveInfo) r12
            android.content.Intent r12 = zza(r1, r12)
            return r12
        L_0x012c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.gmsg.zzad.zzi(java.util.Map):android.content.Intent");
    }
}
