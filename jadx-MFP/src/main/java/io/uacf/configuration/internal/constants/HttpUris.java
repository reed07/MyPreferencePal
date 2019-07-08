package io.uacf.configuration.internal.constants;

import android.support.annotation.NonNull;
import io.uacf.core.app.UacfAppId;
import java.util.Locale;

public class HttpUris {
    public static String getConfigurationUri(@NonNull UacfAppId uacfAppId) {
        StringBuilder sb = new StringBuilder();
        sb.append(uacfAppId.toString().toLowerCase(Locale.ENGLISH));
        sb.append("/");
        sb.append("android/config.json");
        return sb.toString();
    }
}
