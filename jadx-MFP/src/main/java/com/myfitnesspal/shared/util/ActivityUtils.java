package com.myfitnesspal.shared.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import com.myfitnesspal.shared.extension.BuildUtil;

public final class ActivityUtils {
    public static <T extends View> T findById(Activity activity, int i) {
        return activity.findViewById(i);
    }

    public static boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static boolean isInMultiWindow(Activity activity) {
        return BuildUtil.isNougatOrHigher() && activity.isInMultiWindowMode();
    }

    public static boolean isDefaultPortrait(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if ((rotation == 0 || rotation == 2) && i2 > i) {
            return true;
        }
        return (rotation == 1 || rotation == 3) && i > i2;
    }
}
