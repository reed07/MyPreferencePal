package com.facebook.ads.internal.l;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.ads.internal.w.b.d;
import com.facebook.ads.internal.w.h.a;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.concurrent.atomic.AtomicBoolean;

public class b {
    public static final String a = VERSION.RELEASE;
    private final Context b;
    private final AtomicBoolean c = new AtomicBoolean();

    public b(Context context) {
        this.b = context.getApplicationContext();
    }

    public String a() {
        return (Build.MANUFACTURER == null || Build.MANUFACTURER.length() <= 0) ? "" : Build.MANUFACTURER;
    }

    public String b() {
        return (Build.MODEL == null || Build.MODEL.length() <= 0) ? "" : Build.MODEL;
    }

    public String c() {
        TelephonyManager telephonyManager = (TelephonyManager) this.b.getSystemService("phone");
        if (telephonyManager != null) {
            String networkOperatorName = telephonyManager.getNetworkOperatorName();
            if (networkOperatorName != null && networkOperatorName.length() > 0) {
                return networkOperatorName;
            }
        }
        return "";
    }

    public String d() {
        try {
            CharSequence applicationLabel = this.b.getPackageManager().getApplicationLabel(this.b.getPackageManager().getApplicationInfo(f(), 0));
            if (applicationLabel != null && applicationLabel.length() > 0) {
                return applicationLabel.toString();
            }
        } catch (NameNotFoundException unused) {
        }
        return "";
    }

    public String e() {
        try {
            String f = f();
            if (f != null && f.length() >= 0) {
                String installerPackageName = this.b.getPackageManager().getInstallerPackageName(f);
                if (installerPackageName != null && installerPackageName.length() > 0) {
                    return installerPackageName;
                }
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public String f() {
        PendingIntent activity = PendingIntent.getActivity(this.b, 0, new Intent(), 0);
        if (activity == null) {
            if (!this.c.getAndSet(true)) {
                a.b(this.b, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, com.facebook.ads.internal.w.h.b.B, new Exception("PI_NULL"));
            }
            return "";
        }
        return VERSION.SDK_INT >= 17 ? activity.getCreatorPackage() : activity.getTargetPackage();
    }

    public String g() {
        String str;
        try {
            str = this.b.getPackageManager().getPackageInfo(f(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            str = null;
        }
        return !TextUtils.isEmpty(str) ? str : "";
    }

    public int h() {
        int i = 0;
        try {
            return this.b.getPackageManager().getPackageInfo(f(), i).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return i;
        }
    }

    public boolean i() {
        return this.b.checkCallingOrSelfPermission("android.permission.BIND_ACCESSIBILITY_SERVICE") == 0;
    }

    public int j() {
        return d.a(this.b);
    }
}
