package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.app.UiModeManager;
import android.content.Context;
import android.os.Build.VERSION;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;

/* compiled from: IMASDK */
public final class aes {
    public static boolean a = false;

    @TargetApi(13)
    public static boolean a(Context context, TestingConfiguration testingConfiguration) {
        if (testingConfiguration != null && testingConfiguration.forceTvMode()) {
            return true;
        }
        if (VERSION.SDK_INT <= 12) {
            return false;
        }
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        if (uiModeManager == null || uiModeManager.getCurrentModeType() != 4) {
            return false;
        }
        return true;
    }
}
