package com.uacf.core.util;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VersionUtils {
    private static final Pattern VERSION_PATTERN = Pattern.compile("((\\d+)(\\.\\d+)*).*$", 2);
    private static String versionName = null;

    public static boolean isOsVersionGreaterThan(int i) {
        return VERSION.SDK_INT > i;
    }

    public static boolean isOsVersionGreaterThanOrEqualTo(int i) {
        return VERSION.SDK_INT >= i;
    }

    public static boolean isOsVersionLessThan(int i) {
        return VERSION.SDK_INT < i;
    }

    public static boolean isOsVersionLessThanOrEqualTo(int i) {
        return VERSION.SDK_INT <= i;
    }

    public static boolean isOsVersionEqualTo(int i) {
        return VERSION.SDK_INT == i;
    }

    public static int getPlatformMajorVersion() {
        return (int) getNumericAndroidVersion();
    }

    public static int getPlatformMinorVersion() {
        return ((int) (getNumericAndroidVersion() * 10.0f)) % 10;
    }

    public static float getNumericAndroidVersion() {
        float floatAtStartOf = NumberUtils.getFloatAtStartOf(VERSION.RELEASE);
        if (floatAtStartOf < 0.01f) {
            return 2.0f;
        }
        return floatAtStartOf;
    }

    public static int getAppVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            Ln.e(e);
            return 0;
        }
    }

    public static String getAppVersionName(Context context) {
        if (Strings.isEmpty(versionName)) {
            try {
                versionName = extractVersionFromString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
            } catch (NameNotFoundException e) {
                versionName = "unknown";
                Ln.e(e);
            }
        }
        return versionName;
    }

    public static String extractVersionFromString(String str) {
        if (Strings.notEmpty(str)) {
            Matcher matcher = VERSION_PATTERN.matcher(str);
            if (matcher.matches()) {
                return matcher.group(1);
            }
        }
        return "unknown";
    }

    public static boolean isLollipopOrHigher() {
        return isOsVersionGreaterThanOrEqualTo(21);
    }
}
