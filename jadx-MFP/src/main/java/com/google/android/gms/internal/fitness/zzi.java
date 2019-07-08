package com.google.android.gms.internal.fitness;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.DeviceProperties;

public final class zzi {
    private static int zzek = -1;

    public static int zza(Context context) {
        if (zzek == -1) {
            if (DeviceProperties.isWearable(context)) {
                zzek = 3;
            } else {
                boolean z = false;
                if (DeviceProperties.isTv(context) || DeviceProperties.isAuto(context)) {
                    zzek = 0;
                } else {
                    if (DeviceProperties.isTablet(context.getResources()) && !zzb(context)) {
                        zzek = 2;
                    } else {
                        if (!TextUtils.isEmpty(Build.PRODUCT) && Build.PRODUCT.startsWith("glass_")) {
                            z = true;
                        }
                        if (z) {
                            zzek = 6;
                        } else {
                            zzek = 1;
                        }
                    }
                }
            }
        }
        return zzek;
    }

    private static boolean zzb(Context context) {
        try {
            if (((TelephonyManager) context.getSystemService("phone")).getPhoneType() != 0) {
                return true;
            }
            return false;
        } catch (NotFoundException e) {
            Log.wtf("Fitness", "Unable to determine type of device, assuming phone.", e);
            return true;
        }
    }
}
