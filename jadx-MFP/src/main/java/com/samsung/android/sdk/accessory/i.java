package com.samsung.android.sdk.accessory;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.samsung.accessory.api.ISAFrameworkManagerV2;
import java.util.List;

public final class i {
    public static String a = "com.samsung.accessory";
    private static int b = 1;
    private static int c = 1;
    private static int d;
    private static int e;
    private static int f;

    public i(Context context) throws c {
        if (context != null) {
            PackageManager packageManager = context.getPackageManager();
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(a, 0);
                if (packageInfo != null) {
                    b = packageInfo.versionCode;
                    StringBuilder sb = new StringBuilder("Accessory Framework:");
                    sb.append(packageInfo.versionName);
                    sb.append(" Accessory SDK:2.5.3 r0");
                    Log.i("[SA_SDK]SASdkConfig", sb.toString());
                    Intent intent = new Intent(ISAFrameworkManagerV2.class.getName());
                    intent.setPackage(a);
                    List queryIntentServices = packageManager.queryIntentServices(intent, 32);
                    if (queryIntentServices == null || queryIntentServices.size() == 0) {
                        Log.w("[SA_SDK]SASdkConfig", "Accessory framework does not support thin-sdk");
                        return;
                    }
                    return;
                }
                Log.e("[SA_SDK]SASdkConfig", "Accessory Framework Not installed");
                throw new c(2, "Accessory Framework Not installed");
            } catch (NameNotFoundException unused) {
                Log.e("[SA_SDK]SASdkConfig", "Accessory Framework Not installed");
                throw new c(2, "Accessory Framework Not installed");
            }
        } else {
            throw new IllegalArgumentException("Invalid Context");
        }
    }

    static int a() {
        return d;
    }

    static void a(int i) {
        d = i;
    }

    static boolean a(Context context) {
        String packageName = context.getPackageName();
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                Log.w("[SA_SDK]SASdkConfig", "Package Manager is null");
                return false;
            }
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 4096);
            if (packageInfo == null) {
                Log.w("[SA_SDK]SASdkConfig", "PackageInfo is null");
                return false;
            }
            String[] strArr = packageInfo.requestedPermissions;
            if (strArr == null) {
                return false;
            }
            int i = 0;
            while (true) {
                if (i >= strArr.length) {
                    i = -1;
                    break;
                } else if ("com.samsung.accessory.permission.ACCESSORY_FRAMEWORK".equals(strArr[i])) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == -1) {
                StringBuilder sb = new StringBuilder("Accessory service permission not granted for Package");
                sb.append(packageName);
                Log.w("[SA_SDK]SASdkConfig", sb.toString());
                return false;
            }
            StringBuilder sb2 = new StringBuilder("Accessory service permission available for Package");
            sb2.append(packageName);
            Log.i("[SA_SDK]SASdkConfig", sb2.toString());
            return true;
        } catch (NameNotFoundException unused) {
            StringBuilder sb3 = new StringBuilder("Admin Permission check failed for Package");
            sb3.append(packageName);
            Log.e("[SA_SDK]SASdkConfig", sb3.toString());
            return false;
        }
    }

    static int b() {
        return e;
    }

    static void b(int i) {
        e = i;
    }

    static int c() {
        return f;
    }

    static void c(int i) {
        f = i;
    }

    public static int d() {
        return b;
    }

    public static void d(int i) {
        c = i;
    }

    public static int e() {
        return c;
    }

    static String f() {
        return "UTF-8";
    }

    static boolean g() {
        return b >= 407;
    }

    static boolean h() {
        return c >= 407;
    }

    static boolean i() {
        return b >= 205;
    }

    static boolean j() {
        return b >= 218;
    }
}
