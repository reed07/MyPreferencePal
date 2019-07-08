package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import android.util.Log;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.common.util.VisibleForTesting;

@Deprecated
@VisibleForTesting
public final class zzco {
    private static volatile Logger zzabg = new zzby();

    @SuppressLint({"LogTagMismatch"})
    public static void zzf(String str, Object obj) {
        String str2;
        zzcp zzex = zzcp.zzex();
        if (zzex != null) {
            zzex.zze(str, obj);
        } else if (isLoggable(3)) {
            if (obj != null) {
                String valueOf = String.valueOf(obj);
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(valueOf).length());
                sb.append(str);
                sb.append(":");
                sb.append(valueOf);
                str2 = sb.toString();
            } else {
                str2 = str;
            }
            Log.e((String) zzcf.zzyx.get(), str2);
        }
        Logger logger = zzabg;
        if (logger != null) {
            logger.error(str);
        }
    }

    @SuppressLint({"LogTagMismatch"})
    public static void v(String str) {
        zzcp zzex = zzcp.zzex();
        if (zzex != null) {
            zzex.zzq(str);
        } else if (isLoggable(0)) {
            Log.v((String) zzcf.zzyx.get(), str);
        }
        Logger logger = zzabg;
        if (logger != null) {
            logger.verbose(str);
        }
    }

    @SuppressLint({"LogTagMismatch"})
    public static void zzab(String str) {
        zzcp zzex = zzcp.zzex();
        if (zzex != null) {
            zzex.zzt(str);
        } else if (isLoggable(2)) {
            Log.w((String) zzcf.zzyx.get(), str);
        }
        Logger logger = zzabg;
        if (logger != null) {
            logger.warn(str);
        }
    }

    private static boolean isLoggable(int i) {
        if (zzabg == null || zzabg.getLogLevel() > i) {
            return false;
        }
        return true;
    }

    @VisibleForTesting
    public static void setLogger(Logger logger) {
        zzabg = logger;
    }

    @VisibleForTesting
    public static Logger getLogger() {
        return zzabg;
    }
}
