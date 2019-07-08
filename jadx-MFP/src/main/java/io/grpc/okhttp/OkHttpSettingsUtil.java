package io.grpc.okhttp;

import io.grpc.okhttp.internal.framed.Settings;

class OkHttpSettingsUtil {
    OkHttpSettingsUtil() {
    }

    public static boolean isSet(Settings settings, int i) {
        return settings.isSet(i);
    }

    public static int get(Settings settings, int i) {
        return settings.get(i);
    }
}
