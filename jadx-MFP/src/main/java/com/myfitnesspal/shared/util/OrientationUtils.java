package com.myfitnesspal.shared.util;

import android.app.Activity;

public class OrientationUtils {
    private OrientationUtils() {
    }

    public static void lockOrientation(Activity activity) {
        activity.setRequestedOrientation(14);
    }

    public static void unlockOrientation(Activity activity) {
        activity.setRequestedOrientation(-1);
    }
}
