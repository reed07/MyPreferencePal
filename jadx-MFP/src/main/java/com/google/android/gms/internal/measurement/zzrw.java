package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.UserManager;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

public class zzrw {
    private static volatile UserManager zzbrb;
    private static volatile boolean zzbrc = (!zztj());

    private zzrw() {
    }

    public static boolean zztj() {
        return VERSION.SDK_INT >= 24;
    }

    public static boolean isUserUnlocked(Context context) {
        return !zztj() || zzab(context);
    }

    @TargetApi(24)
    @RequiresApi
    private static boolean zzab(Context context) {
        boolean z;
        boolean z2 = zzbrc;
        if (!z2) {
            boolean z3 = z2;
            int i = 1;
            while (true) {
                if (i > 2) {
                    z2 = z3;
                    break;
                }
                UserManager zzac = zzac(context);
                if (zzac == null) {
                    zzbrc = true;
                    return true;
                }
                try {
                    if (!zzac.isUserUnlocked()) {
                        if (zzac.isUserRunning(Process.myUserHandle())) {
                            z = false;
                            zzbrc = z;
                            z2 = z;
                        }
                    }
                    z = true;
                    zzbrc = z;
                    z2 = z;
                } catch (NullPointerException e) {
                    Log.w("DirectBootUtils", "Failed to check if user is unlocked", e);
                    zzbrb = null;
                    i++;
                }
            }
            if (z2) {
                zzbrb = null;
            }
        }
        return z2;
    }

    @VisibleForTesting
    @TargetApi(24)
    @RequiresApi
    private static UserManager zzac(Context context) {
        UserManager userManager = zzbrb;
        if (userManager == null) {
            synchronized (zzrw.class) {
                userManager = zzbrb;
                if (userManager == null) {
                    UserManager userManager2 = (UserManager) context.getSystemService(UserManager.class);
                    zzbrb = userManager2;
                    userManager = userManager2;
                }
            }
        }
        return userManager;
    }
}
