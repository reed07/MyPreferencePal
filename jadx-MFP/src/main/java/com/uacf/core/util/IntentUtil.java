package com.uacf.core.util;

import android.content.Intent;
import android.net.Uri;

public class IntentUtil {
    public static boolean hasData(Intent intent) {
        return (intent == null || intent.getData() == null) ? false : true;
    }

    public static Uri getData(Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.getData();
    }
}
