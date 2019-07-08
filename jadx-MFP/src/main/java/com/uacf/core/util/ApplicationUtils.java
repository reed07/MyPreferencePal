package com.uacf.core.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Binder;
import android.os.Build;
import android.util.Log;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.io.File;

public class ApplicationUtils {
    public static boolean isDebuggable(Context context) {
        String str = "";
        boolean z = false;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            if (((applicationInfo != null ? applicationInfo.flags : 0) & 2) != 0) {
                z = true;
            }
            return z;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error configuring StrictMode for package '");
            sb.append(str);
            sb.append("'");
            Log.e(str, sb.toString(), e);
            return false;
        }
    }

    public static long getInstallationDate(Context context) {
        try {
            String str = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
            if (Strings.notEmpty(str)) {
                File file = new File(str);
                if (file.exists()) {
                    return file.lastModified();
                }
            }
        } catch (NameNotFoundException e) {
            Ln.e(e);
        }
        return 0;
    }

    public static boolean hasCameraFeature(Context context) {
        try {
            return context.getPackageManager().hasSystemFeature("android.hardware.camera");
        } catch (Exception e) {
            Ln.e(e);
            return false;
        }
    }

    public static boolean isRunningInsideEmulator() {
        Ln.d("CONTENT: finger = %s, model = %s, manu = %s, brand = %s, device = %s, product = %s", Build.FINGERPRINT, Build.MODEL, Build.MANUFACTURER, Build.BRAND, Build.DEVICE, Build.PRODUCT);
        if (Build.FINGERPRINT.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE) || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || ((Build.BRAND.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE) && Build.DEVICE.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE)) || "google_sdk".equals(Build.PRODUCT))) {
            return true;
        }
        return false;
    }

    public static boolean validateAppSignatureForCallingPackage(Context context, String str) {
        int callingUid = Binder.getCallingUid();
        String nameForUid = context.getPackageManager().getNameForUid(callingUid);
        Ln.d("CONTENT: validateAppSignatureForCallingPackage, callingUid = %s, name = %s", Integer.valueOf(callingUid), nameForUid);
        return validateAppSignatureForPackage(context, nameForUid, str);
    }

    public static boolean validateAppSignatureForPackage(Context context, String str, String str2) {
        Ln.d("CONTENT: validateAppSignatureForPackage, packageName = %s, sig = %s", str, str2);
        if (Strings.isEmpty(str)) {
            throw new IllegalArgumentException("packageName may not be null or empty");
        } else if (!Strings.isEmpty(str2)) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
                int size = ArrayUtil.size(packageInfo.signatures);
                if (size == 1) {
                    Signature[] signatureArr = packageInfo.signatures;
                    int length = signatureArr.length;
                    int i = 0;
                    while (i < length) {
                        Signature signature = signatureArr[i];
                        if (!Strings.notEmpty((Object) signature) || !str2.equals(signature.toCharsString())) {
                            i++;
                        } else {
                            Ln.d("CONTENT: validated %s", str);
                            return true;
                        }
                    }
                    Ln.d("CONTENT: unable to validate %s", str);
                    return false;
                }
                Ln.d("CONTENT: package has %s signatures which is != 1", Integer.valueOf(size));
                throw new IllegalArgumentException("Invalid signature list");
            } catch (NameNotFoundException unused) {
                return false;
            }
        } else {
            throw new IllegalArgumentException("signatureToCompare may not be null or empty");
        }
    }
}
