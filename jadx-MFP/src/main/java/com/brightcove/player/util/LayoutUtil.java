package com.brightcove.player.util;

import android.content.Context;

public class LayoutUtil {
    public static String getSpecMode(int i) {
        if (i == Integer.MIN_VALUE) {
            return "MeasureSpec.AT_MOST";
        }
        if (i == 0) {
            return "MeasureSpec.UNSPECIFIED";
        }
        if (i != 1073741824) {
            return null;
        }
        return "MeasureSpec.EXACTLY";
    }

    public static boolean isLargeScreen(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }
}
