package com.myfitnesspal.shared.util;

import android.content.Context;
import android.support.annotation.NonNull;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.Crashlytics.Builder;
import com.crashlytics.android.core.CrashlyticsCore;
import com.myfitnesspal.build.BuildConfiguration;
import io.fabric.sdk.android.Fabric;

public class CrashlyticsUtil {
    public static boolean isEnabledByRuntimeConfig() {
        return !BuildConfiguration.getBuildConfiguration().isDebug();
    }

    public static boolean startIfEnabled(Context context) {
        Fabric.with(context, new Builder().core(new CrashlyticsCore.Builder().disabled(!isEnabledByRuntimeConfig()).build()).build());
        return true;
    }

    public static boolean logIfEnabled(Throwable th) {
        if (!isEnabledByRuntimeConfig()) {
            return false;
        }
        Crashlytics.logException(th);
        return true;
    }

    public static boolean logIfEnabled(@NonNull String str) {
        if (!isEnabledByRuntimeConfig()) {
            return false;
        }
        Crashlytics.log(str);
        return true;
    }
}
