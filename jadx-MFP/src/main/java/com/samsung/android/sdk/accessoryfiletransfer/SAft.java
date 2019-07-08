package com.samsung.android.sdk.accessoryfiletransfer;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.util.Log;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.samsung.android.sdk.SsdkInterface;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.SsdkVendorCheck;
import java.util.List;

public class SAft implements SsdkInterface {
    public static final int DEVICE_ACCESSORY = 0;
    public static String FILE_TRANSFER_SERVICE_INTENT = "com.samsung.accessory.ISAFTManager";
    private static int c = 218;
    private boolean a;
    private int b;
    private boolean d = false;

    private static boolean b() {
        boolean z;
        try {
            Class cls = Class.forName("com.samsung.android.feature.FloatingFeature");
            Object invoke = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            z = ((Boolean) cls.getMethod("getEnableStatus", new Class[]{String.class}).invoke(invoke, new Object[]{"SEC_FLOATING_FEATURE_CONTEXTSERVICE_ENABLE_SURVEY_MODE"})).booleanValue();
            StringBuilder sb = new StringBuilder("floating feature : ");
            sb.append(z);
            Log.d("SecFloating", sb.toString());
        } catch (Exception unused) {
            Log.d("SecFloating", "Floating feature is not supported (non-samsung device)");
            try {
                Class cls2 = Class.forName("com.samsung.android.feature.SemFloatingFeature");
                Object invoke2 = cls2.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                z = ((Boolean) cls2.getMethod("getBoolean", new Class[]{String.class}).invoke(invoke2, new Object[]{"SEC_FLOATING_FEATURE_CONTEXTSERVICE_ENABLE_SURVEY_MODE"})).booleanValue();
                StringBuilder sb2 = new StringBuilder("floating feature : ");
                sb2.append(z);
                Log.d("SecFloating", sb2.toString());
            } catch (Exception unused2) {
                Log.d("SecFloating", "Floating feature is not supported this device (non-samsung device)");
                return false;
            }
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public final boolean a() {
        StringBuilder sb = new StringBuilder("Samsung Accessory File Transfer CORE version: ");
        sb.append(this.b);
        Log.d("[SA_SDK]SAft", sb.toString());
        return this.b > c;
    }

    public String getFileTransferPackageName(Context context) {
        List queryIntentServices = context.getPackageManager().queryIntentServices(new Intent(FILE_TRANSFER_SERVICE_INTENT), 0);
        if (queryIntentServices.size() == 1) {
            return ((ResolveInfo) queryIntentServices.get(0)).serviceInfo.packageName;
        }
        return null;
    }

    public int getVersionCode() {
        return 3;
    }

    public String getVersionName() {
        return "2.5.3";
    }

    public void initialize(Context context) throws SsdkUnsupportedException {
        String str;
        String str2;
        if (context == null) {
            throw new IllegalArgumentException("Illegal argument input: context");
        } else if (!this.a) {
            if (!this.d) {
                try {
                    if (SsdkVendorCheck.isSamsungDevice()) {
                        int i = -1;
                        try {
                            i = context.getPackageManager().getPackageInfo("com.samsung.android.providers.context", 128).versionCode;
                        } catch (NameNotFoundException unused) {
                            Log.d("SM_SDK", "Could not find ContextProvider");
                        }
                        StringBuilder sb = new StringBuilder("versionCode: ");
                        sb.append(i);
                        Log.d("SM_SDK", sb.toString());
                        if (i <= 1) {
                            str = "SM_SDK";
                            str2 = "Add com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission";
                        } else if (context.checkCallingOrSelfPermission("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY") != 0) {
                            str = "SM_SDK";
                            str2 = "com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY is not allowed";
                        } else if (b()) {
                            ContentValues contentValues = new ContentValues();
                            String name = getClass().getPackage().getName();
                            StringBuilder sb2 = new StringBuilder(String.valueOf(context.getPackageName()));
                            sb2.append("#");
                            sb2.append(getVersionCode());
                            String sb3 = sb2.toString();
                            contentValues.put("app_id", name);
                            contentValues.put(Attributes.FEATURE, sb3);
                            Intent intent = new Intent();
                            intent.setAction("com.samsung.android.providers.context.log.action.USE_APP_FEATURE_SURVEY");
                            intent.putExtra("data", contentValues);
                            intent.setPackage("com.samsung.android.providers.context");
                            context.sendBroadcast(intent);
                        }
                        Log.d(str, str2);
                    }
                } catch (SecurityException unused2) {
                    Log.e("[SA_SDK]SAft", "SecurityException : com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission is required.");
                }
                this.d = true;
            }
            PackageManager packageManager = context.getPackageManager();
            try {
                if (packageManager.getPackageInfo("com.samsung.accessory", 0) != null) {
                    String fileTransferPackageName = getFileTransferPackageName(context);
                    if (fileTransferPackageName != null) {
                        PackageInfo packageInfo = packageManager.getPackageInfo(fileTransferPackageName, 0);
                        if (packageInfo != null) {
                            this.b = packageInfo.versionCode;
                            String str3 = packageInfo.versionName;
                            Log.d("[SA_SDK]SAft", "Samsung Accessory File Transfer SDK version: 2.5.3");
                            this.a = true;
                            return;
                        }
                        throw new SsdkUnsupportedException("Samsung Accessory Framework not installed", 2);
                    }
                    throw new SsdkUnsupportedException("Samsung Accessory Framework not installed", 2);
                }
                throw new SsdkUnsupportedException("Device not supported", 1);
            } catch (NameNotFoundException unused3) {
                Log.e("[SA_SDK]SAft", "Samsung Accessory Framework not installed");
                throw new SsdkUnsupportedException("Samsung Accessory Framework not installed", 2);
            }
        }
    }

    public boolean isFeatureEnabled(int i) {
        if (i == 0) {
            return true;
        }
        throw new IllegalArgumentException();
    }
}
