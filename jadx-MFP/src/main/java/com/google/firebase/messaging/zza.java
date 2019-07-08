package com.google.firebase.messaging;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import com.myfitnesspal.shared.constants.SharedConstants.Params;
import java.util.Arrays;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

final class zza {
    private static final AtomicInteger zzdn = new AtomicInteger((int) SystemClock.elapsedRealtime());
    private Bundle zzdo;
    private final Context zzx;

    public zza(Context context) {
        this.zzx = context.getApplicationContext();
    }

    static boolean zzf(Bundle bundle) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(zza(bundle, "gcm.n.e")) || zza(bundle, "gcm.n.icon") != null;
    }

    static String zza(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace("gcm.n.", "gcm.notification.")) : string;
    }

    static String zzb(Bundle bundle, String str) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_key");
        return zza(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    static Object[] zzc(Bundle bundle, String str) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_args");
        String zza = zza(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        if (TextUtils.isEmpty(zza)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(zza);
            Object[] objArr = new String[jSONArray.length()];
            for (int i = 0; i < objArr.length; i++) {
                objArr[i] = jSONArray.opt(i);
            }
            return objArr;
        } catch (JSONException unused) {
            String str2 = "FirebaseMessaging";
            String valueOf3 = String.valueOf(str);
            String valueOf4 = String.valueOf("_loc_args");
            String substring = (valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3)).substring(6);
            StringBuilder sb = new StringBuilder(String.valueOf(substring).length() + 41 + String.valueOf(zza).length());
            sb.append("Malformed ");
            sb.append(substring);
            sb.append(": ");
            sb.append(zza);
            sb.append("  Default value will be used.");
            Log.w(str2, sb.toString());
            return null;
        }
    }

    @Nullable
    static Uri zzg(@NonNull Bundle bundle) {
        String zza = zza(bundle, "gcm.n.link_android");
        if (TextUtils.isEmpty(zza)) {
            zza = zza(bundle, "gcm.n.link");
        }
        if (!TextUtils.isEmpty(zza)) {
            return Uri.parse(zza);
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x02ec  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0315  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x031e  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x032f  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0338  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x033d  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0342  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0347  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x035c  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0373  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x038c  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x018f  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01a3  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01da  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01dc  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0222  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0224  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0232  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0264  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0290  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x02cd  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x02dd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzh(android.os.Bundle r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            java.lang.String r2 = "1"
            java.lang.String r3 = "gcm.n.noui"
            java.lang.String r3 = zza(r1, r3)
            boolean r2 = r2.equals(r3)
            r3 = 1
            if (r2 == 0) goto L_0x0014
            return r3
        L_0x0014:
            android.content.Context r2 = r0.zzx
            java.lang.String r4 = "keyguard"
            java.lang.Object r2 = r2.getSystemService(r4)
            android.app.KeyguardManager r2 = (android.app.KeyguardManager) r2
            boolean r2 = r2.inKeyguardRestrictedInputMode()
            r4 = 0
            if (r2 != 0) goto L_0x0062
            boolean r2 = com.google.android.gms.common.util.PlatformVersion.isAtLeastLollipop()
            if (r2 != 0) goto L_0x0030
            r5 = 10
            android.os.SystemClock.sleep(r5)
        L_0x0030:
            int r2 = android.os.Process.myPid()
            android.content.Context r5 = r0.zzx
            java.lang.String r6 = "activity"
            java.lang.Object r5 = r5.getSystemService(r6)
            android.app.ActivityManager r5 = (android.app.ActivityManager) r5
            java.util.List r5 = r5.getRunningAppProcesses()
            if (r5 == 0) goto L_0x0062
            java.util.Iterator r5 = r5.iterator()
        L_0x0048:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0062
            java.lang.Object r6 = r5.next()
            android.app.ActivityManager$RunningAppProcessInfo r6 = (android.app.ActivityManager.RunningAppProcessInfo) r6
            int r7 = r6.pid
            if (r7 != r2) goto L_0x0048
            int r2 = r6.importance
            r5 = 100
            if (r2 != r5) goto L_0x0060
            r2 = 1
            goto L_0x0063
        L_0x0060:
            r2 = 0
            goto L_0x0063
        L_0x0062:
            r2 = 0
        L_0x0063:
            if (r2 == 0) goto L_0x0066
            return r4
        L_0x0066:
            java.lang.String r2 = "gcm.n.title"
            java.lang.String r2 = r0.zzd(r1, r2)
            boolean r5 = android.text.TextUtils.isEmpty(r2)
            if (r5 == 0) goto L_0x0082
            android.content.Context r2 = r0.zzx
            android.content.pm.ApplicationInfo r2 = r2.getApplicationInfo()
            android.content.Context r5 = r0.zzx
            android.content.pm.PackageManager r5 = r5.getPackageManager()
            java.lang.CharSequence r2 = r2.loadLabel(r5)
        L_0x0082:
            java.lang.String r5 = "gcm.n.body"
            java.lang.String r5 = r0.zzd(r1, r5)
            java.lang.String r6 = "gcm.n.icon"
            java.lang.String r6 = zza(r1, r6)
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 != 0) goto L_0x00e9
            android.content.Context r7 = r0.zzx
            android.content.res.Resources r7 = r7.getResources()
            java.lang.String r8 = "drawable"
            android.content.Context r9 = r0.zzx
            java.lang.String r9 = r9.getPackageName()
            int r8 = r7.getIdentifier(r6, r8, r9)
            if (r8 == 0) goto L_0x00af
            boolean r9 = r0.zzb(r8)
            if (r9 == 0) goto L_0x00af
            goto L_0x0114
        L_0x00af:
            java.lang.String r8 = "mipmap"
            android.content.Context r9 = r0.zzx
            java.lang.String r9 = r9.getPackageName()
            int r8 = r7.getIdentifier(r6, r8, r9)
            if (r8 == 0) goto L_0x00c4
            boolean r7 = r0.zzb(r8)
            if (r7 == 0) goto L_0x00c4
            goto L_0x0114
        L_0x00c4:
            java.lang.String r7 = "FirebaseMessaging"
            java.lang.String r8 = java.lang.String.valueOf(r6)
            int r8 = r8.length()
            int r8 = r8 + 61
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r8)
            java.lang.String r8 = "Icon resource "
            r9.append(r8)
            r9.append(r6)
            java.lang.String r6 = " not found. Notification will use default icon."
            r9.append(r6)
            java.lang.String r6 = r9.toString()
            android.util.Log.w(r7, r6)
        L_0x00e9:
            android.os.Bundle r6 = r16.zzas()
            java.lang.String r7 = "com.google.firebase.messaging.default_notification_icon"
            int r6 = r6.getInt(r7, r4)
            if (r6 == 0) goto L_0x00fb
            boolean r7 = r0.zzb(r6)
            if (r7 != 0) goto L_0x0103
        L_0x00fb:
            android.content.Context r6 = r0.zzx
            android.content.pm.ApplicationInfo r6 = r6.getApplicationInfo()
            int r6 = r6.icon
        L_0x0103:
            if (r6 == 0) goto L_0x010e
            boolean r7 = r0.zzb(r6)
            if (r7 != 0) goto L_0x010c
            goto L_0x010e
        L_0x010c:
            r8 = r6
            goto L_0x0114
        L_0x010e:
            r6 = 17301651(0x1080093, float:2.4979667E-38)
            r8 = 17301651(0x1080093, float:2.4979667E-38)
        L_0x0114:
            java.lang.String r6 = "gcm.n.color"
            java.lang.String r6 = zza(r1, r6)
            java.lang.Integer r6 = r0.zzl(r6)
            java.lang.String r7 = zzi(r17)
            boolean r9 = android.text.TextUtils.isEmpty(r7)
            r10 = 0
            if (r9 == 0) goto L_0x012b
            r7 = r10
            goto L_0x0183
        L_0x012b:
            java.lang.String r9 = "default"
            boolean r9 = r9.equals(r7)
            if (r9 != 0) goto L_0x017e
            android.content.Context r9 = r0.zzx
            android.content.res.Resources r9 = r9.getResources()
            java.lang.String r11 = "raw"
            android.content.Context r12 = r0.zzx
            java.lang.String r12 = r12.getPackageName()
            int r9 = r9.getIdentifier(r7, r11, r12)
            if (r9 == 0) goto L_0x017e
            android.content.Context r9 = r0.zzx
            java.lang.String r9 = r9.getPackageName()
            java.lang.String r11 = java.lang.String.valueOf(r9)
            int r11 = r11.length()
            int r11 = r11 + 24
            java.lang.String r12 = java.lang.String.valueOf(r7)
            int r12 = r12.length()
            int r11 = r11 + r12
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>(r11)
            java.lang.String r11 = "android.resource://"
            r12.append(r11)
            r12.append(r9)
            java.lang.String r9 = "/raw/"
            r12.append(r9)
            r12.append(r7)
            java.lang.String r7 = r12.toString()
            android.net.Uri r7 = android.net.Uri.parse(r7)
            goto L_0x0183
        L_0x017e:
            r7 = 2
            android.net.Uri r7 = android.media.RingtoneManager.getDefaultUri(r7)
        L_0x0183:
            java.lang.String r9 = "gcm.n.click_action"
            java.lang.String r9 = zza(r1, r9)
            boolean r11 = android.text.TextUtils.isEmpty(r9)
            if (r11 != 0) goto L_0x01a3
            android.content.Intent r11 = new android.content.Intent
            r11.<init>(r9)
            android.content.Context r9 = r0.zzx
            java.lang.String r9 = r9.getPackageName()
            r11.setPackage(r9)
            r9 = 268435456(0x10000000, float:2.5243549E-29)
            r11.setFlags(r9)
            goto L_0x01d6
        L_0x01a3:
            android.net.Uri r9 = zzg(r17)
            if (r9 == 0) goto L_0x01bd
            android.content.Intent r11 = new android.content.Intent
            java.lang.String r12 = "android.intent.action.VIEW"
            r11.<init>(r12)
            android.content.Context r12 = r0.zzx
            java.lang.String r12 = r12.getPackageName()
            r11.setPackage(r12)
            r11.setData(r9)
            goto L_0x01d6
        L_0x01bd:
            android.content.Context r9 = r0.zzx
            android.content.pm.PackageManager r9 = r9.getPackageManager()
            android.content.Context r11 = r0.zzx
            java.lang.String r11 = r11.getPackageName()
            android.content.Intent r11 = r9.getLaunchIntentForPackage(r11)
            if (r11 != 0) goto L_0x01d6
            java.lang.String r9 = "FirebaseMessaging"
            java.lang.String r12 = "No activity found to launch app"
            android.util.Log.w(r9, r12)
        L_0x01d6:
            r9 = 1073741824(0x40000000, float:2.0)
            if (r11 != 0) goto L_0x01dc
            r11 = r10
            goto L_0x0220
        L_0x01dc:
            r12 = 67108864(0x4000000, float:1.5046328E-36)
            r11.addFlags(r12)
            android.os.Bundle r12 = new android.os.Bundle
            r12.<init>(r1)
            com.google.firebase.messaging.FirebaseMessagingService.zzj(r12)
            r11.putExtras(r12)
            java.util.Set r12 = r12.keySet()
            java.util.Iterator r12 = r12.iterator()
        L_0x01f4:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x0214
            java.lang.Object r13 = r12.next()
            java.lang.String r13 = (java.lang.String) r13
            java.lang.String r14 = "gcm.n."
            boolean r14 = r13.startsWith(r14)
            if (r14 != 0) goto L_0x0210
            java.lang.String r14 = "gcm.notification."
            boolean r14 = r13.startsWith(r14)
            if (r14 == 0) goto L_0x01f4
        L_0x0210:
            r11.removeExtra(r13)
            goto L_0x01f4
        L_0x0214:
            android.content.Context r12 = r0.zzx
            java.util.concurrent.atomic.AtomicInteger r13 = zzdn
            int r13 = r13.incrementAndGet()
            android.app.PendingIntent r11 = android.app.PendingIntent.getActivity(r12, r13, r11, r9)
        L_0x0220:
            if (r1 != 0) goto L_0x0224
            r12 = 0
            goto L_0x0230
        L_0x0224:
            java.lang.String r12 = "1"
            java.lang.String r13 = "google.c.a.e"
            java.lang.String r13 = r1.getString(r13)
            boolean r12 = r12.equals(r13)
        L_0x0230:
            if (r12 == 0) goto L_0x0264
            android.content.Intent r12 = new android.content.Intent
            java.lang.String r13 = "com.google.firebase.messaging.NOTIFICATION_OPEN"
            r12.<init>(r13)
            zza(r12, r1)
            java.lang.String r13 = "pending_intent"
            r12.putExtra(r13, r11)
            android.content.Context r11 = r0.zzx
            java.util.concurrent.atomic.AtomicInteger r13 = zzdn
            int r13 = r13.incrementAndGet()
            android.app.PendingIntent r11 = com.google.firebase.iid.zzav.zza(r11, r13, r12, r9)
            android.content.Intent r12 = new android.content.Intent
            java.lang.String r13 = "com.google.firebase.messaging.NOTIFICATION_DISMISS"
            r12.<init>(r13)
            zza(r12, r1)
            android.content.Context r13 = r0.zzx
            java.util.concurrent.atomic.AtomicInteger r14 = zzdn
            int r14 = r14.incrementAndGet()
            android.app.PendingIntent r9 = com.google.firebase.iid.zzav.zza(r13, r14, r12, r9)
            goto L_0x0265
        L_0x0264:
            r9 = r10
        L_0x0265:
            java.lang.String r12 = "gcm.n.android_channel_id"
            java.lang.String r12 = zza(r1, r12)
            boolean r13 = com.google.android.gms.common.util.PlatformVersion.isAtLeastO()
            r14 = 3
            if (r13 == 0) goto L_0x0300
            android.content.Context r13 = r0.zzx
            android.content.pm.ApplicationInfo r13 = r13.getApplicationInfo()
            int r13 = r13.targetSdkVersion
            r15 = 26
            if (r13 >= r15) goto L_0x0280
            goto L_0x0300
        L_0x0280:
            android.content.Context r10 = r0.zzx
            java.lang.Class<android.app.NotificationManager> r13 = android.app.NotificationManager.class
            java.lang.Object r10 = r10.getSystemService(r13)
            android.app.NotificationManager r10 = (android.app.NotificationManager) r10
            boolean r13 = android.text.TextUtils.isEmpty(r12)
            if (r13 != 0) goto L_0x02bd
            android.app.NotificationChannel r13 = r10.getNotificationChannel(r12)
            if (r13 == 0) goto L_0x0298
            r10 = r12
            goto L_0x0300
        L_0x0298:
            java.lang.String r13 = "FirebaseMessaging"
            java.lang.String r15 = java.lang.String.valueOf(r12)
            int r15 = r15.length()
            int r15 = r15 + 122
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r15)
            java.lang.String r15 = "Notification Channel requested ("
            r4.append(r15)
            r4.append(r12)
            java.lang.String r12 = ") has not been created by the app. Manifest configuration, or default, value will be used."
            r4.append(r12)
            java.lang.String r4 = r4.toString()
            android.util.Log.w(r13, r4)
        L_0x02bd:
            android.os.Bundle r4 = r16.zzas()
            java.lang.String r12 = "com.google.firebase.messaging.default_notification_channel_id"
            java.lang.String r4 = r4.getString(r12)
            boolean r12 = android.text.TextUtils.isEmpty(r4)
            if (r12 != 0) goto L_0x02dd
            android.app.NotificationChannel r12 = r10.getNotificationChannel(r4)
            if (r12 == 0) goto L_0x02d5
            r10 = r4
            goto L_0x0300
        L_0x02d5:
            java.lang.String r4 = "FirebaseMessaging"
            java.lang.String r12 = "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used."
            android.util.Log.w(r4, r12)
            goto L_0x02e4
        L_0x02dd:
            java.lang.String r4 = "FirebaseMessaging"
            java.lang.String r12 = "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used."
            android.util.Log.w(r4, r12)
        L_0x02e4:
            java.lang.String r4 = "fcm_fallback_notification_channel"
            android.app.NotificationChannel r4 = r10.getNotificationChannel(r4)
            if (r4 != 0) goto L_0x02fe
            android.app.NotificationChannel r4 = new android.app.NotificationChannel
            java.lang.String r12 = "fcm_fallback_notification_channel"
            android.content.Context r13 = r0.zzx
            int r15 = com.google.firebase.messaging.R.string.fcm_fallback_notification_channel_label
            java.lang.String r13 = r13.getString(r15)
            r4.<init>(r12, r13, r14)
            r10.createNotificationChannel(r4)
        L_0x02fe:
            java.lang.String r10 = "fcm_fallback_notification_channel"
        L_0x0300:
            android.support.v4.app.NotificationCompat$Builder r4 = new android.support.v4.app.NotificationCompat$Builder
            android.content.Context r12 = r0.zzx
            r4.<init>(r12)
            android.support.v4.app.NotificationCompat$Builder r4 = r4.setAutoCancel(r3)
            android.support.v4.app.NotificationCompat$Builder r4 = r4.setSmallIcon(r8)
            boolean r8 = android.text.TextUtils.isEmpty(r2)
            if (r8 != 0) goto L_0x0318
            r4.setContentTitle(r2)
        L_0x0318:
            boolean r2 = android.text.TextUtils.isEmpty(r5)
            if (r2 != 0) goto L_0x032d
            r4.setContentText(r5)
            android.support.v4.app.NotificationCompat$BigTextStyle r2 = new android.support.v4.app.NotificationCompat$BigTextStyle
            r2.<init>()
            android.support.v4.app.NotificationCompat$BigTextStyle r2 = r2.bigText(r5)
            r4.setStyle(r2)
        L_0x032d:
            if (r6 == 0) goto L_0x0336
            int r2 = r6.intValue()
            r4.setColor(r2)
        L_0x0336:
            if (r7 == 0) goto L_0x033b
            r4.setSound(r7)
        L_0x033b:
            if (r11 == 0) goto L_0x0340
            r4.setContentIntent(r11)
        L_0x0340:
            if (r9 == 0) goto L_0x0345
            r4.setDeleteIntent(r9)
        L_0x0345:
            if (r10 == 0) goto L_0x034a
            r4.setChannelId(r10)
        L_0x034a:
            android.app.Notification r2 = r4.build()
            java.lang.String r4 = "gcm.n.tag"
            java.lang.String r1 = zza(r1, r4)
            java.lang.String r4 = "FirebaseMessaging"
            boolean r4 = android.util.Log.isLoggable(r4, r14)
            if (r4 == 0) goto L_0x0363
            java.lang.String r4 = "FirebaseMessaging"
            java.lang.String r5 = "Showing notification"
            android.util.Log.d(r4, r5)
        L_0x0363:
            android.content.Context r4 = r0.zzx
            java.lang.String r5 = "notification"
            java.lang.Object r4 = r4.getSystemService(r5)
            android.app.NotificationManager r4 = (android.app.NotificationManager) r4
            boolean r5 = android.text.TextUtils.isEmpty(r1)
            if (r5 == 0) goto L_0x038c
            long r5 = android.os.SystemClock.uptimeMillis()
            r1 = 37
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r1)
            java.lang.String r1 = "FCM-Notification:"
            r7.append(r1)
            r7.append(r5)
            java.lang.String r1 = r7.toString()
            r5 = 0
            goto L_0x038d
        L_0x038c:
            r5 = 0
        L_0x038d:
            r4.notify(r1, r5, r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.zza.zzh(android.os.Bundle):boolean");
    }

    private final String zzd(Bundle bundle, String str) {
        String zza = zza(bundle, str);
        if (!TextUtils.isEmpty(zza)) {
            return zza;
        }
        String zzb = zzb(bundle, str);
        if (TextUtils.isEmpty(zzb)) {
            return null;
        }
        Resources resources = this.zzx.getResources();
        int identifier = resources.getIdentifier(zzb, "string", this.zzx.getPackageName());
        if (identifier == 0) {
            String str2 = "FirebaseMessaging";
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf("_loc_key");
            String substring = (valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).substring(6);
            StringBuilder sb = new StringBuilder(String.valueOf(substring).length() + 49 + String.valueOf(zzb).length());
            sb.append(substring);
            sb.append(" resource not found: ");
            sb.append(zzb);
            sb.append(" Default value will be used.");
            Log.w(str2, sb.toString());
            return null;
        }
        Object[] zzc = zzc(bundle, str);
        if (zzc == null) {
            return resources.getString(identifier);
        }
        try {
            return resources.getString(identifier, zzc);
        } catch (MissingFormatArgumentException e) {
            String arrays = Arrays.toString(zzc);
            StringBuilder sb2 = new StringBuilder(String.valueOf(zzb).length() + 58 + String.valueOf(arrays).length());
            sb2.append("Missing format argument for ");
            sb2.append(zzb);
            sb2.append(": ");
            sb2.append(arrays);
            sb2.append(" Default value will be used.");
            Log.w("FirebaseMessaging", sb2.toString(), e);
            return null;
        }
    }

    @TargetApi(26)
    private final boolean zzb(int i) {
        if (VERSION.SDK_INT != 26) {
            return true;
        }
        try {
            if (!(this.zzx.getResources().getDrawable(i, null) instanceof AdaptiveIconDrawable)) {
                return true;
            }
            StringBuilder sb = new StringBuilder(77);
            sb.append("Adaptive icons cannot be used in notifications. Ignoring icon id: ");
            sb.append(i);
            Log.e("FirebaseMessaging", sb.toString());
            return false;
        } catch (NotFoundException unused) {
            return false;
        }
    }

    private final Integer zzl(String str) {
        if (VERSION.SDK_INT < 21) {
            return null;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.valueOf(Color.parseColor(str));
            } catch (IllegalArgumentException unused) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 54);
                sb.append("Color ");
                sb.append(str);
                sb.append(" not valid. Notification will use default color.");
                Log.w("FirebaseMessaging", sb.toString());
            }
        }
        int i = zzas().getInt("com.google.firebase.messaging.default_notification_color", 0);
        if (i != 0) {
            try {
                return Integer.valueOf(ContextCompat.getColor(this.zzx, i));
            } catch (NotFoundException unused2) {
                Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
            }
        }
        return null;
    }

    static String zzi(Bundle bundle) {
        String zza = zza(bundle, "gcm.n.sound2");
        return TextUtils.isEmpty(zza) ? zza(bundle, "gcm.n.sound") : zza;
    }

    private static void zza(Intent intent, Bundle bundle) {
        for (String str : bundle.keySet()) {
            if (str.startsWith("google.c.a.") || str.equals(Params.FROM)) {
                intent.putExtra(str, bundle.getString(str));
            }
        }
    }

    private final Bundle zzas() {
        Bundle bundle = this.zzdo;
        if (bundle != null) {
            return bundle;
        }
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = this.zzx.getPackageManager().getApplicationInfo(this.zzx.getPackageName(), 128);
        } catch (NameNotFoundException unused) {
        }
        if (applicationInfo == null || applicationInfo.metaData == null) {
            return Bundle.EMPTY;
        }
        this.zzdo = applicationInfo.metaData;
        return this.zzdo;
    }
}
