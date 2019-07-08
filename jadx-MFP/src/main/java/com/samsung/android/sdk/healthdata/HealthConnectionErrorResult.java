package com.samsung.android.sdk.healthdata;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;

public final class HealthConnectionErrorResult {
    public static final int CONNECTION_FAILURE = 1;
    public static final int OLD_VERSION_PLATFORM = 4;
    public static final int OLD_VERSION_SDK = 3;
    public static final int PLATFORM_DISABLED = 6;
    public static final int PLATFORM_INITIALIZING = -2;
    public static final int PLATFORM_NOT_INSTALLED = 2;
    public static final int PLATFORM_SIGNATURE_FAILURE = 8;
    public static final int SUCCESS = -1;
    public static final int TIMEOUT = 5;
    public static final int UNKNOWN = 0;
    public static final int USER_AGREEMENT_NEEDED = 9;
    public static final int USER_PASSWORD_NEEDED = 7;
    public static final int USER_PASSWORD_POPUP = -3;
    private PackageManager a;
    private final int b;
    private final boolean c;

    public HealthConnectionErrorResult(int i, boolean z) {
        this.b = i;
        this.c = z;
    }

    public final int getErrorCode() {
        return this.b;
    }

    public final boolean hasResolution() {
        int i = this.b;
        if (i == 6 || i == 9) {
            return true;
        }
        if (i != 2 && i != 4) {
            return false;
        }
        if (!a()) {
            return true;
        }
        return this.c;
    }

    public final void resolve(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("The input argument is null");
        } else if (activity.getBaseContext() != null) {
            int i = this.b;
            if (i == 2 || i == 4) {
                if (hasResolution()) {
                    activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(a() ? "samsungapps://ProductDetail/com.sec.android.app.shealth" : "market://details?id=com.sec.android.app.shealth")));
                }
            } else if (i == 6) {
                StringBuilder sb = new StringBuilder("package:");
                sb.append(HealthDataStore.getPlatformPackageName());
                activity.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse(sb.toString())));
            } else if (i == 9) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.setPackage(HealthDataStore.getPlatformPackageName());
                intent.addCategory("android.intent.category.LAUNCHER");
                activity.startActivity(intent);
            }
        } else {
            throw new IllegalArgumentException("The input activity is wrong");
        }
    }

    public final void setPackageManager(PackageManager packageManager) {
        this.a = packageManager;
    }

    private boolean a() {
        PackageManager packageManager = this.a;
        if (packageManager == null) {
            return false;
        }
        try {
            if (packageManager.getPackageInfo("com.sec.android.app.samsungapps", 128).applicationInfo.enabled) {
                return true;
            }
        } catch (NameNotFoundException unused) {
        }
        return false;
    }
}
