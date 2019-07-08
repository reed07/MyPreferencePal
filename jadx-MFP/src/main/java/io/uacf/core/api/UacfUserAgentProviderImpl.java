package io.uacf.core.api;

import android.os.Build.VERSION;
import com.uacf.core.util.Strings;
import io.uacf.core.app.UacfAppId;

public class UacfUserAgentProviderImpl implements UacfUserAgentProvider {
    private static String sdkVersion;
    private static String sdkVersionCode;
    private final UacfAppId appId;
    private final String appVersion;
    private final String id;

    public UacfUserAgentProviderImpl(String str, UacfAppId uacfAppId, String str2, String str3, String str4) {
        this.id = str;
        this.appId = uacfAppId;
        this.appVersion = str2;
        sdkVersion = str3;
        sdkVersionCode = str4;
    }

    public String get() {
        return String.format("%s/%s (build %s) %s/%s Android/%s (API %s)", new Object[]{this.id, sdkVersion, sdkVersionCode, Strings.toString(this.appId), Strings.toString(this.appVersion), VERSION.RELEASE, Integer.valueOf(VERSION.SDK_INT)});
    }
}
