package com.myfitnesspal.feature.progress.constants;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.myfitnesspal.feature.explore.analytics.ExploreAnalytics.CommunityType;
import com.uacf.core.util.Strings;
import java.util.HashMap;
import java.util.Map;

public enum ShareTarget {
    Facebook("facebook"),
    Instagram(CommunityType.INSTAGRAM),
    SaveToGallery("camera_roll"),
    More("other");
    
    private static final String FACEBOOK_PACKAGE_NAME = "com.facebook.katana";
    private static final String INSTAGRAM_PACKAGE_NAME = "com.instagram.android";
    private String analyticsValue;

    private ShareTarget(String str) {
        this.analyticsValue = str;
    }

    public String getAnalyticsValue() {
        return this.analyticsValue;
    }

    public static Map<ShareTarget, Intent> getAvailableShareTargets(Context context, String str) {
        HashMap hashMap = new HashMap();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(str);
        hashMap.put(More, intent);
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
            ShareTarget fromPackage = fromPackage(resolveInfo.activityInfo.packageName);
            if (fromPackage != null) {
                Intent intent2 = new Intent();
                intent2.setAction("android.intent.action.SEND");
                intent2.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                intent2.setType(str);
                hashMap.put(fromPackage, intent2);
            }
        }
        return hashMap;
    }

    private static ShareTarget fromPackage(String str) {
        if (Strings.notEmpty(str)) {
            if (str.startsWith(FACEBOOK_PACKAGE_NAME)) {
                return Facebook;
            }
            if (str.startsWith(INSTAGRAM_PACKAGE_NAME)) {
                return Instagram;
            }
        }
        return null;
    }
}
