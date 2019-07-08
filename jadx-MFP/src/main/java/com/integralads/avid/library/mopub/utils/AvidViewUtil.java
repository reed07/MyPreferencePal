package com.integralads.avid.library.mopub.utils;

import android.os.Build.VERSION;
import android.view.View;

public class AvidViewUtil {
    public static boolean isViewVisible(View view) {
        boolean z = false;
        if (view.getVisibility() != 0) {
            return false;
        }
        if (VERSION.SDK_INT < 11) {
            return true;
        }
        if (((double) view.getAlpha()) > 0.0d) {
            z = true;
        }
        return z;
    }
}
