package com.myfitnesspal.shared.util;

import android.support.annotation.NonNull;
import com.myfitnesspal.feature.settings.model.AppSettings;

public final class ApiUtil {
    private static int apiVersion;

    public static void resetApiVersion(int i) {
        apiVersion = i;
    }

    public static int getBinaryApiVersion(@NonNull AppSettings appSettings) {
        if (apiVersion == 0) {
            apiVersion = appSettings.getApiVersion();
        }
        return apiVersion;
    }
}
