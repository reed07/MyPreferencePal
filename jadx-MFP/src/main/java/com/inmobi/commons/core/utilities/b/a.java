package com.inmobi.commons.core.utilities.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.security.NetworkSecurityPolicy;
import java.util.HashMap;
import java.util.Map;

/* compiled from: AppInfo */
public class a {
    private static final String d = "a";
    private static a e;
    private static Object f = new Object();
    public String a;
    public Map<String, String> b = new HashMap();
    public int c;
    private String g;
    private String h;
    private String i;

    private a() {
        Context b2 = com.inmobi.commons.a.a.b();
        try {
            PackageManager packageManager = b2.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(b2.getPackageName(), 128);
            if (applicationInfo != null) {
                this.g = applicationInfo.packageName;
                this.h = applicationInfo.loadLabel(packageManager).toString();
                this.a = packageManager.getInstallerPackageName(this.g);
            }
            PackageInfo packageInfo = packageManager.getPackageInfo(b2.getPackageName(), 128);
            String str = null;
            if (packageInfo != null) {
                str = packageInfo.versionName;
                if (str == null || str.equals("")) {
                    if (VERSION.SDK_INT < 28) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(packageInfo.versionCode);
                        str = sb.toString();
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(packageInfo.getLongVersionCode());
                        str = sb2.toString();
                    }
                }
            }
            if (str != null && !str.equals("")) {
                this.i = str;
            }
        } catch (Exception unused) {
        }
        this.c = b();
        this.b.put("u-appbid", this.g);
        this.b.put("u-appdnm", this.h);
        this.b.put("u-appver", this.i);
        this.b.put("u-appsecure", Integer.toString(this.c));
    }

    public static a a() {
        a aVar = e;
        if (aVar == null) {
            synchronized (f) {
                aVar = e;
                if (aVar == null) {
                    aVar = new a();
                    e = aVar;
                }
            }
        }
        return aVar;
    }

    @SuppressLint({"NewApi"})
    private static int b() {
        try {
            if (VERSION.SDK_INT < 23 || NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted()) {
                return 0;
            }
            return 1;
        } catch (Exception unused) {
            return 2;
        }
    }
}
