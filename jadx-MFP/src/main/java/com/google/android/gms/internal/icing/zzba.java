package com.google.android.gms.internal.icing;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.UserManager;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

public class zzba {
    private static volatile UserManager zzcr;
    private static volatile boolean zzcs = (!zzq());

    private zzba() {
    }

    public static boolean zzq() {
        return VERSION.SDK_INT >= 24;
    }

    public static boolean isUserUnlocked(Context context) {
        return !zzq() || zza(context);
    }

    @TargetApi(24)
    @RequiresApi
    private static boolean zza(Context context) {
        boolean z;
        boolean z2 = zzcs;
        if (!z2) {
            boolean z3 = z2;
            int i = 1;
            while (true) {
                if (i > 2) {
                    z2 = z3;
                    break;
                }
                UserManager zzb = zzb(context);
                if (zzb == null) {
                    zzcs = true;
                    return true;
                }
                try {
                    if (!zzb.isUserUnlocked()) {
                        if (zzb.isUserRunning(Process.myUserHandle())) {
                            z = false;
                            zzcs = z;
                            z2 = z;
                        }
                    }
                    z = true;
                    zzcs = z;
                    z2 = z;
                } catch (NullPointerException e) {
                    Log.w("DirectBootUtils", "Failed to check if user is unlocked", e);
                    zzcr = null;
                    i++;
                }
            }
            if (z2) {
                zzcr = null;
            }
        }
        return z2;
    }

    @VisibleForTesting
    @TargetApi(24)
    @RequiresApi
    private static UserManager zzb(Context context) {
        UserManager userManager = zzcr;
        if (userManager == null) {
            synchronized (zzba.class) {
                userManager = zzcr;
                if (userManager == null) {
                    UserManager userManager2 = (UserManager) context.getSystemService(UserManager.class);
                    zzcr = userManager2;
                    userManager = userManager2;
                }
            }
        }
        return userManager;
    }
}
