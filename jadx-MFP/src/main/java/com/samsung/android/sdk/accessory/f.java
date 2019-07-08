package com.samsung.android.sdk.accessory;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.samsung.android.sdk.SsdkVendorCheck;

public final class f {
    private static boolean a = false;

    static {
        Log.d("[SA_SDK]SAGSIMLog", "init()");
        boolean a2 = a();
        a = a2;
        if (!a2) {
            Log.e("[SA_SDK]SAGSIMLog", "GSIM logging is not enabled.");
        }
    }

    protected static void a(Context context, String str) {
        a(context, str, null);
    }

    protected static void a(Context context, String str, String str2) {
        if (SsdkVendorCheck.isSamsungDevice()) {
            try {
                int i = context.getPackageManager().getPackageInfo("com.samsung.android.providers.context", 128).versionCode;
                StringBuilder sb = new StringBuilder("versionCode: ");
                sb.append(i);
                Log.d("[SA_SDK]SAGSIMLog", sb.toString());
                if (i <= 1) {
                    Log.d("[SA_SDK]SAGSIMLog", "Add WRITE_USE_APP_FEATURE_SURVEY permission");
                } else if (context.checkCallingOrSelfPermission("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY") != 0) {
                    Log.d("[SA_SDK]SAGSIMLog", "WRITE_USE_APP_FEATURE_SURVEY is not allowed");
                } else if (a) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("app_id", "com.samsung.android.sdk.accessory");
                    contentValues.put(Attributes.FEATURE, str);
                    contentValues.put("extra", str2);
                    Intent intent = new Intent();
                    intent.setAction("com.samsung.android.providers.context.log.action.USE_APP_FEATURE_SURVEY");
                    intent.setPackage("com.samsung.android.providers.context");
                    intent.putExtra("data", contentValues);
                    context.sendBroadcast(intent);
                }
            } catch (NameNotFoundException unused) {
                Log.d("[SA_SDK]SAGSIMLog", "Could not find ContextProvider");
            } catch (SecurityException unused2) {
                Log.e("[SA_SDK]SAGSIMLog", "SecurityException : WRITE_USE_APP_FEATURE_SURVEY permission is required.");
            }
        }
    }

    private static boolean a() {
        boolean z;
        try {
            Class cls = Class.forName("com.samsung.android.feature.FloatingFeature");
            Object invoke = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            z = ((Boolean) cls.getMethod("getEnableStatus", new Class[]{String.class}).invoke(invoke, new Object[]{"SEC_FLOATING_FEATURE_CONTEXTSERVICE_ENABLE_SURVEY_MODE"})).booleanValue();
            StringBuilder sb = new StringBuilder("[SecFloating] floating feature : ");
            sb.append(z);
            Log.d("[SA_SDK]SAGSIMLog", sb.toString());
        } catch (Exception unused) {
            Log.d("[SA_SDK]SAGSIMLog", "[SecFloating] feature is not supported (non-samsung device)");
            try {
                Class cls2 = Class.forName("com.samsung.android.feature.SemFloatingFeature");
                Object invoke2 = cls2.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                z = ((Boolean) cls2.getMethod("getBoolean", new Class[]{String.class}).invoke(invoke2, new Object[]{"SEC_FLOATING_FEATURE_CONTEXTSERVICE_ENABLE_SURVEY_MODE"})).booleanValue();
                StringBuilder sb2 = new StringBuilder("[SecFloating] floating feature : ");
                sb2.append(z);
                Log.d("[SA_SDK]SAGSIMLog", sb2.toString());
            } catch (Exception unused2) {
                Log.d("[SA_SDK]SAGSIMLog", "[SecFloating] feature is not supported this device (non-samsung device)");
                return false;
            }
        }
        return z;
    }
}
