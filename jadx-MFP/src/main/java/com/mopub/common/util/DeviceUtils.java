package com.mopub.common.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.os.Build.VERSION;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.brightcove.player.event.AbstractEvent;
import com.mopub.common.CreativeOrientation;
import com.mopub.common.Preconditions;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Reflection.MethodBuilder;
import com.myfitnesspal.shared.constants.Constants.Extras;
import java.io.File;
import java.net.SocketException;

public class DeviceUtils {

    public enum ForceOrientation {
        FORCE_PORTRAIT(Extras.ORIENTATION_PORTRAIT),
        FORCE_LANDSCAPE(Extras.ORIENTATION_LANDSCAPE),
        DEVICE_ORIENTATION("device"),
        UNDEFINED("");
        
        @NonNull
        private final String mKey;

        private ForceOrientation(String str) {
            this.mKey = str;
        }

        @NonNull
        public static ForceOrientation getForceOrientation(@Nullable String str) {
            ForceOrientation[] values;
            for (ForceOrientation forceOrientation : values()) {
                if (forceOrientation.mKey.equalsIgnoreCase(str)) {
                    return forceOrientation;
                }
            }
            return UNDEFINED;
        }
    }

    @Deprecated
    public enum IP {
        IPv4,
        IPv6
    }

    @Nullable
    @Deprecated
    public static String getHashedUdid(Context context) {
        return null;
    }

    @Nullable
    @Deprecated
    public static String getIpAddress(IP ip) throws SocketException {
        return null;
    }

    private DeviceUtils() {
    }

    public static boolean isNetworkAvailable(@Nullable Context context) {
        if (context == null || !isPermissionGranted(context, "android.permission.INTERNET")) {
            return false;
        }
        if (!isPermissionGranted(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo().isConnected();
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public static int memoryCacheSizeBytes(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(AbstractEvent.ACTIVITY);
        long memoryClass = (long) activityManager.getMemoryClass();
        try {
            if (Utils.bitMaskContainsFlag(context.getApplicationInfo().flags, ApplicationInfo.class.getDeclaredField("FLAG_LARGE_HEAP").getInt(null))) {
                memoryClass = (long) ((Integer) new MethodBuilder(activityManager, "getLargeMemoryClass").execute()).intValue();
            }
        } catch (Exception unused) {
            MoPubLog.d("Unable to reflectively determine large heap size.");
        }
        return (int) Math.min(31457280, (memoryClass / 8) * 1024 * 1024);
    }

    public static long diskCacheSizeBytes(File file, long j) {
        try {
            StatFs statFs = new StatFs(file.getAbsolutePath());
            j = (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 50;
        } catch (IllegalArgumentException unused) {
            MoPubLog.d("Unable to calculate 2% of available disk space, defaulting to minimum");
        }
        return Math.max(Math.min(j, 104857600), 31457280);
    }

    public static long diskCacheSizeBytes(File file) {
        return diskCacheSizeBytes(file, 31457280);
    }

    public static int getScreenOrientation(@NonNull Activity activity) {
        return getScreenOrientationFromRotationAndOrientation(activity.getWindowManager().getDefaultDisplay().getRotation(), activity.getResources().getConfiguration().orientation);
    }

    static int getScreenOrientationFromRotationAndOrientation(int i, int i2) {
        if (1 == i2) {
            switch (i) {
                case 1:
                case 2:
                    return 9;
                default:
                    return 1;
            }
        } else if (2 == i2) {
            switch (i) {
                case 2:
                case 3:
                    return 8;
                default:
                    return 0;
            }
        } else {
            MoPubLog.d("Unknown screen orientation. Defaulting to portrait.");
            return 9;
        }
    }

    public static void lockOrientation(@NonNull Activity activity, @NonNull CreativeOrientation creativeOrientation) {
        if (NoThrow.checkNotNull(creativeOrientation) && NoThrow.checkNotNull(activity)) {
            int screenOrientationFromRotationAndOrientation = getScreenOrientationFromRotationAndOrientation(((WindowManager) activity.getSystemService("window")).getDefaultDisplay().getRotation(), activity.getResources().getConfiguration().orientation);
            int i = 8;
            if (CreativeOrientation.PORTRAIT == creativeOrientation) {
                i = 9 == screenOrientationFromRotationAndOrientation ? 9 : 1;
            } else if (CreativeOrientation.LANDSCAPE != creativeOrientation) {
                return;
            } else {
                if (8 != screenOrientationFromRotationAndOrientation) {
                    i = 0;
                }
            }
            activity.setRequestedOrientation(i);
        }
    }

    @TargetApi(17)
    public static Point getDeviceDimensions(@NonNull Context context) {
        Integer num;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Integer num2 = null;
        if (VERSION.SDK_INT >= 17) {
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            Integer valueOf = Integer.valueOf(point.x);
            num2 = Integer.valueOf(point.y);
            num = valueOf;
        } else {
            try {
                num = (Integer) new MethodBuilder(defaultDisplay, "getRawWidth").execute();
                try {
                    num2 = (Integer) new MethodBuilder(defaultDisplay, "getRawHeight").execute();
                } catch (Exception e) {
                    e = e;
                    MoPubLog.v("Display#getRawWidth/Height failed.", e);
                    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                    num = Integer.valueOf(displayMetrics.widthPixels);
                    num2 = Integer.valueOf(displayMetrics.heightPixels);
                    return new Point(num.intValue(), num2.intValue());
                }
            } catch (Exception e2) {
                e = e2;
                num = null;
                MoPubLog.v("Display#getRawWidth/Height failed.", e);
                DisplayMetrics displayMetrics2 = context.getResources().getDisplayMetrics();
                num = Integer.valueOf(displayMetrics2.widthPixels);
                num2 = Integer.valueOf(displayMetrics2.heightPixels);
                return new Point(num.intValue(), num2.intValue());
            }
        }
        if (num == null || num2 == null) {
            DisplayMetrics displayMetrics22 = context.getResources().getDisplayMetrics();
            num = Integer.valueOf(displayMetrics22.widthPixels);
            num2 = Integer.valueOf(displayMetrics22.heightPixels);
        }
        return new Point(num.intValue(), num2.intValue());
    }

    public static boolean isPermissionGranted(@NonNull Context context, @NonNull String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        boolean z = false;
        try {
            if (ContextCompat.checkSelfPermission(context, str) == 0) {
                z = true;
            }
            return z;
        } catch (Exception unused) {
            return false;
        }
    }
}
