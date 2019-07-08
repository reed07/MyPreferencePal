package com.myfitnesspal.feature.appgallery.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.Collection;

public class AppStateUtil {
    public static boolean isConnected(MfpPlatformApp mfpPlatformApp) {
        return mfpPlatformApp.hasUserConnected();
    }

    public static boolean isConnectable(MfpPlatformApp mfpPlatformApp) {
        return Strings.notEmpty(Strings.toStringOrDefaultIfEmpty(mfpPlatformApp.getDeepConnectUri(), mfpPlatformApp.getConnectUri()));
    }

    public static boolean isInstallable(MfpPlatformApp mfpPlatformApp) {
        return Strings.notEmpty(mfpPlatformApp.getStoreLink());
    }

    public static boolean hasCompleteDeepLink(MfpPlatformApp mfpPlatformApp) {
        try {
            return Strings.notEmpty(Uri.parse(mfpPlatformApp.getDeepConnectUri()).getHost());
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean canDetectInstalled(MfpPlatformApp mfpPlatformApp) {
        return Strings.notEmpty(mfpPlatformApp.getDeepConnectUri());
    }

    public static boolean isInstalled(Context context, MfpPlatformApp mfpPlatformApp) {
        return getStartIntent(context, mfpPlatformApp) != null;
    }

    public static Intent getStartIntent(Context context, MfpPlatformApp mfpPlatformApp) {
        String deepConnectUri = mfpPlatformApp.getDeepConnectUri();
        if (Strings.notEmpty(deepConnectUri)) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(deepConnectUri));
                if (installedAppCanHandle(context, intent)) {
                    return intent;
                }
            } catch (Exception e) {
                Ln.e(e);
            }
        }
        return null;
    }

    private static boolean installedAppCanHandle(Context context, Intent intent) {
        try {
            return CollectionUtils.notEmpty((Collection<?>) context.getPackageManager().queryIntentActivities(intent, 0));
        } catch (Exception e) {
            Ln.e(e);
            return false;
        }
    }
}
