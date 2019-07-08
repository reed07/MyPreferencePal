package com.mopub.common.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.privacy.ConsentDialogActivity;
import com.mopub.mobileads.MraidVideoPlayerActivity;
import java.util.ArrayList;
import java.util.List;

public class ManifestUtils {
    private static final List<Class<? extends Activity>> REQUIRED_GDPR_ACTIVITIES = new ArrayList(1);
    private static final List<Class<? extends Activity>> REQUIRED_NATIVE_SDK_ACTIVITIES = new ArrayList(1);
    private static final List<Class<? extends Activity>> REQUIRED_WEB_VIEW_SDK_ACTIVITIES = new ArrayList(4);
    private static FlagCheckUtil sFlagCheckUtil = new FlagCheckUtil();

    private static class ActivityConfigChanges {
        public boolean hasKeyboardHidden;
        public boolean hasOrientation;
        public boolean hasScreenSize;

        private ActivityConfigChanges() {
        }
    }

    static class FlagCheckUtil {
        FlagCheckUtil() {
        }

        public boolean hasFlag(Class cls, int i, int i2) {
            return Utils.bitMaskContainsFlag(i, i2);
        }
    }

    private ManifestUtils() {
    }

    static {
        try {
            Class cls = Class.forName("com.mopub.mobileads.MoPubActivity");
            Class cls2 = Class.forName("com.mopub.mobileads.MraidActivity");
            Class cls3 = Class.forName("com.mopub.mobileads.RewardedMraidActivity");
            REQUIRED_WEB_VIEW_SDK_ACTIVITIES.add(cls);
            REQUIRED_WEB_VIEW_SDK_ACTIVITIES.add(cls2);
            REQUIRED_WEB_VIEW_SDK_ACTIVITIES.add(cls3);
        } catch (ClassNotFoundException unused) {
            MoPubLog.i("ManifestUtils running without interstitial module");
        }
        REQUIRED_WEB_VIEW_SDK_ACTIVITIES.add(MraidVideoPlayerActivity.class);
        REQUIRED_WEB_VIEW_SDK_ACTIVITIES.add(MoPubBrowser.class);
        REQUIRED_NATIVE_SDK_ACTIVITIES.add(MoPubBrowser.class);
        REQUIRED_GDPR_ACTIVITIES.add(ConsentDialogActivity.class);
    }

    public static void checkGdprActivitiesDeclared(@NonNull Context context) {
        if (NoThrow.checkNotNull(context, "context is not allowed to be null")) {
            displayWarningForMissingActivities(context, REQUIRED_GDPR_ACTIVITIES);
            displayWarningForMisconfiguredActivities(context, REQUIRED_GDPR_ACTIVITIES);
        }
    }

    public static void checkWebViewActivitiesDeclared(@NonNull Context context) {
        if (NoThrow.checkNotNull(context, "context is not allowed to be null")) {
            displayWarningForMissingActivities(context, REQUIRED_WEB_VIEW_SDK_ACTIVITIES);
            displayWarningForMisconfiguredActivities(context, REQUIRED_WEB_VIEW_SDK_ACTIVITIES);
        }
    }

    public static void checkNativeActivitiesDeclared(@NonNull Context context) {
        if (NoThrow.checkNotNull(context, "context is not allowed to be null")) {
            displayWarningForMissingActivities(context, REQUIRED_NATIVE_SDK_ACTIVITIES);
            displayWarningForMisconfiguredActivities(context, REQUIRED_NATIVE_SDK_ACTIVITIES);
        }
    }

    @VisibleForTesting
    static void displayWarningForMissingActivities(@NonNull Context context, @NonNull List<Class<? extends Activity>> list) {
        List filterDeclaredActivities = filterDeclaredActivities(context, list, false);
        if (!filterDeclaredActivities.isEmpty()) {
            logWarningToast(context);
            logMissingActivities(filterDeclaredActivities);
        }
    }

    @VisibleForTesting
    static void displayWarningForMisconfiguredActivities(@NonNull Context context, @NonNull List<Class<? extends Activity>> list) {
        List misconfiguredActivities = getMisconfiguredActivities(context, filterDeclaredActivities(context, list, true));
        if (!misconfiguredActivities.isEmpty()) {
            logWarningToast(context);
            logMisconfiguredActivities(context, misconfiguredActivities);
        }
    }

    public static boolean isDebuggable(@NonNull Context context) {
        return Utils.bitMaskContainsFlag(context.getApplicationInfo().flags, 2);
    }

    private static List<Class<? extends Activity>> filterDeclaredActivities(@NonNull Context context, @NonNull List<Class<? extends Activity>> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (Class cls : list) {
            if (Intents.deviceCanHandleIntent(context, new Intent(context, cls)) == z) {
                arrayList.add(cls);
            }
        }
        return arrayList;
    }

    @TargetApi(13)
    private static List<Class<? extends Activity>> getMisconfiguredActivities(@NonNull Context context, @NonNull List<Class<? extends Activity>> list) {
        ArrayList arrayList = new ArrayList();
        for (Class cls : list) {
            try {
                ActivityConfigChanges activityConfigChanges = getActivityConfigChanges(context, cls);
                if (!activityConfigChanges.hasKeyboardHidden || !activityConfigChanges.hasOrientation || !activityConfigChanges.hasScreenSize) {
                    arrayList.add(cls);
                }
            } catch (NameNotFoundException unused) {
            }
        }
        return arrayList;
    }

    private static void logMissingActivities(@NonNull List<Class<? extends Activity>> list) {
        StringBuilder sb = new StringBuilder("AndroidManifest permissions for the following required MoPub activities are missing:\n");
        for (Class cls : list) {
            sb.append("\n\t");
            sb.append(cls.getName());
        }
        sb.append("\n\nPlease update your manifest to include them.");
        MoPubLog.w(sb.toString());
    }

    private static void logMisconfiguredActivities(@NonNull Context context, @NonNull List<Class<? extends Activity>> list) {
        StringBuilder sb = new StringBuilder("In AndroidManifest, the android:configChanges param is missing values for the following MoPub activities:\n");
        for (Class cls : list) {
            try {
                ActivityConfigChanges activityConfigChanges = getActivityConfigChanges(context, cls);
                if (!activityConfigChanges.hasKeyboardHidden) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("\n\tThe android:configChanges param for activity ");
                    sb2.append(cls.getName());
                    sb2.append(" must include keyboardHidden.");
                    sb.append(sb2.toString());
                }
                if (!activityConfigChanges.hasOrientation) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("\n\tThe android:configChanges param for activity ");
                    sb3.append(cls.getName());
                    sb3.append(" must include orientation.");
                    sb.append(sb3.toString());
                }
                if (!activityConfigChanges.hasScreenSize) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("\n\tThe android:configChanges param for activity ");
                    sb4.append(cls.getName());
                    sb4.append(" must include screenSize.");
                    sb.append(sb4.toString());
                }
            } catch (NameNotFoundException unused) {
            }
        }
        sb.append("\n\nPlease update your manifest to include them.");
        MoPubLog.w(sb.toString());
    }

    private static ActivityConfigChanges getActivityConfigChanges(@NonNull Context context, @NonNull Class<? extends Activity> cls) throws NameNotFoundException {
        ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(new ComponentName(context, cls.getName()), 0);
        ActivityConfigChanges activityConfigChanges = new ActivityConfigChanges();
        activityConfigChanges.hasKeyboardHidden = sFlagCheckUtil.hasFlag(cls, activityInfo.configChanges, 32);
        activityConfigChanges.hasOrientation = sFlagCheckUtil.hasFlag(cls, activityInfo.configChanges, 128);
        activityConfigChanges.hasScreenSize = true;
        activityConfigChanges.hasScreenSize = sFlagCheckUtil.hasFlag(cls, activityInfo.configChanges, 1024);
        return activityConfigChanges;
    }

    private static void logWarningToast(@NonNull Context context) {
        if (isDebuggable(context)) {
            Toast makeText = Toast.makeText(context, "ERROR: YOUR MOPUB INTEGRATION IS INCOMPLETE.\nCheck logcat and update your AndroidManifest.xml with the correct activities and configuration.", 1);
            makeText.setGravity(7, 0, 0);
            makeText.show();
        }
    }
}
