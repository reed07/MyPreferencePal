package com.twitter.sdk.android.core;

import android.content.Context;
import android.content.Intent;

public class IntentUtils {
    public static boolean isActivityAvailable(Context context, Intent intent) {
        return !context.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
    }
}
