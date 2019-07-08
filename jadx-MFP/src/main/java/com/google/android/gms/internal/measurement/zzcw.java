package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.stats.WakeLock;

public final class zzcw {
    static Object lock = new Object();
    static WakeLock zzabx;
    private static Boolean zzre;

    public static boolean zza(Context context) {
        Preconditions.checkNotNull(context);
        Boolean bool = zzre;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean zza = zzdg.zza(context, "com.google.android.gms.analytics.AnalyticsReceiver", false);
        zzre = Boolean.valueOf(zza);
        return zza;
    }

    @RequiresPermission
    public static void onReceive(Context context, Intent intent) {
        zzcp zzby = zzaw.zzc(context).zzby();
        if (intent == null) {
            zzby.zzt("AnalyticsReceiver called with null intent");
            return;
        }
        String action = intent.getAction();
        zzby.zza("Local AnalyticsReceiver got", action);
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            boolean zze = zzcx.zze(context);
            Intent intent2 = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            intent2.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
            intent2.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            synchronized (lock) {
                context.startService(intent2);
                if (zze) {
                    try {
                        if (zzabx == null) {
                            WakeLock wakeLock = new WakeLock(context, 1, "Analytics WakeLock");
                            zzabx = wakeLock;
                            wakeLock.setReferenceCounted(false);
                        }
                        zzabx.acquire(1000);
                    } catch (SecurityException unused) {
                        zzby.zzt("Analytics service at risk of not starting. For more reliable analytics, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                    }
                }
            }
        }
    }
}
