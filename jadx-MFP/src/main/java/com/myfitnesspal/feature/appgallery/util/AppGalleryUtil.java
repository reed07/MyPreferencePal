package com.myfitnesspal.feature.appgallery.util;

import android.content.Context;
import com.myfitnesspal.feature.appgallery.event.NavigateToGoogleFitPermissionsEvent;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.shealth.constants.SHealthConstants;
import com.myfitnesspal.feature.externalsync.impl.shealth.mixin.SHealthMixin;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;

public final class AppGalleryUtil {
    public static final int ACTIVITY_TRACKER_INDEX = 1;
    public static final int NO_CATEGORY = 0;

    public static void launchAppConnect(MfpActivity mfpActivity, MfpPlatformApp mfpPlatformApp) {
        if (isGoogleFit(mfpPlatformApp)) {
            if (!isGoogleFitConnected(mfpActivity)) {
                mfpActivity.getMessageBus().post(new NavigateToGoogleFitPermissionsEvent());
            }
        } else if (isSHealth(mfpPlatformApp)) {
            SHealthConnection connection = ((SHealthMixin) mfpActivity.mixin(SHealthMixin.class)).getConnection();
            if (!connection.isPaired()) {
                connection.pair();
            }
        } else if (AppStateUtil.hasCompleteDeepLink(mfpPlatformApp)) {
            tryNavigateToUri(mfpPlatformApp.getDeepConnectUri(), mfpActivity);
        } else {
            tryNavigateToConnectUri(mfpPlatformApp, mfpActivity);
        }
    }

    public static void launchAppInstall(MfpActivity mfpActivity, MfpPlatformApp mfpPlatformApp) {
        String storeLink = mfpPlatformApp.getStoreLink();
        if (storeLink.contains(Uri.GOOGLE_PLAY) && packageInstalled(mfpActivity, "com.android.vending")) {
            mfpActivity.getNavigationHelper().setPackage("com.android.vending");
        }
        tryNavigateToUri(storeLink, mfpActivity);
    }

    public static boolean isGoogleFit(MfpPlatformApp mfpPlatformApp) {
        return Strings.equals(mfpPlatformApp.getClientId(), "google_fit");
    }

    public static boolean isSHealth(MfpPlatformApp mfpPlatformApp) {
        return Strings.equals(mfpPlatformApp.getAppId(), SHealthConstants.getAppId());
    }

    public static boolean isGoogleFitConnected(MfpActivity mfpActivity) {
        return isGoogleFitConnected(mfpActivity.getGoogleFitClient());
    }

    public static boolean isGoogleFitConnected(GoogleFitClient googleFitClient) {
        return googleFitClient != null && (googleFitClient.isConnected() || googleFitClient.isConnecting() || googleFitClient.isEnabled());
    }

    public static boolean isGoogleFitAndConnected(MfpPlatformApp mfpPlatformApp, GoogleFitClient googleFitClient) {
        return isGoogleFit(mfpPlatformApp) && isGoogleFitConnected(googleFitClient);
    }

    public static boolean isSHealthAndConnected(MfpPlatformApp mfpPlatformApp, SHealthConnection sHealthConnection) {
        return isSHealth(mfpPlatformApp) && sHealthConnection.isPaired();
    }

    private static String getConnectUri(MfpPlatformApp mfpPlatformApp) {
        return Strings.toStringOrDefaultIfEmpty(mfpPlatformApp.getDeepConnectUri(), mfpPlatformApp.getConnectUri());
    }

    private static String userifyUrlString(String str, Session session) {
        if (Strings.isEmpty(str)) {
            return "";
        }
        User user = session.getUser();
        return user.isLoggedIn() ? android.net.Uri.parse(str).buildUpon().appendQueryParameter("username", Strings.toString(user.getUsername())).toString() : str;
    }

    private static void tryNavigateToConnectUri(MfpPlatformApp mfpPlatformApp, MfpUiComponentInterface mfpUiComponentInterface) {
        tryNavigateToUri(userifyUrlString(getConnectUri(mfpPlatformApp), mfpUiComponentInterface.getSession()), mfpUiComponentInterface);
    }

    private static void tryNavigateToUri(String str, MfpUiComponentInterface mfpUiComponentInterface) {
        if (Strings.notEmpty(str)) {
            try {
                mfpUiComponentInterface.getNavigationHelper().withIntent(SharedIntents.newUriIntent(str)).startActivity();
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("failed to navigate to uri: ");
                sb.append(str);
                Ln.e(e, sb.toString(), new Object[0]);
            }
        }
    }

    public static boolean packageInstalled(Context context, String str) {
        try {
            context.getPackageManager().getApplicationInfo(str, 0);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
