package io.uacf.inbox.sdk;

import android.content.Context;
import com.uacf.core.logging.BaseLogConfig;
import com.uacf.core.util.ApplicationUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import io.uacf.core.api.UacfApiEnvironmentProvider;
import io.uacf.core.api.UacfUserAgentProvider;
import io.uacf.core.api.UacfUserAgentProviderImpl;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.auth.UacfAuthProvider;
import io.uacf.core.interfaces.UacfClientEventsCallback;
import io.uacf.core.util.ContextUtil;
import io.uacf.inbox.R;
import io.uacf.inbox.internal.build.RuntimeConfigurationImpl;
import io.uacf.inbox.internal.module.Statics;
import io.uacf.inbox.internal.notification.NotificationService;
import io.uacf.inbox.internal.notification.NotificationServiceImpl;
import okhttp3.OkHttpClient;

public final class UacfNotificationSdkFactory {
    private static UacfAppId appId;
    private static UacfAuthProvider authProvider;
    private static UacfClientEventsCallback clientEventsCallback;
    private static boolean configured;
    private static Context context;
    private static UacfApiEnvironmentProvider environmentProvider;
    private static OkHttpClient okHttpClient;
    private static UacfUserAgentProvider userAgentProvider;

    public static void configure(Context context2, UacfAppId uacfAppId, String str, UacfAuthProvider uacfAuthProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfClientEventsCallback uacfClientEventsCallback) {
        configure(context2, uacfAppId, str, uacfAuthProvider, uacfApiEnvironmentProvider, uacfClientEventsCallback, null);
    }

    public static void configure(Context context2, UacfAppId uacfAppId, String str, UacfAuthProvider uacfAuthProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfClientEventsCallback uacfClientEventsCallback, OkHttpClient okHttpClient2) {
        Ln.setLoggingLevel(new RuntimeConfigurationImpl().isDebugBuild() ? 2 : 6);
        Statics.init(context2, uacfApiEnvironmentProvider);
        Ln.setConfig(new BaseLogConfig(ApplicationUtils.isDebuggable(context2), Strings.toString(context2.getPackageName()).toUpperCase()));
        if (uacfAppId == null) {
            throw new IllegalArgumentException("appId must not be null");
        } else if (!Strings.isEmpty(str)) {
            context = ContextUtil.getApplicationContextSafe(context2);
            authProvider = uacfAuthProvider;
            environmentProvider = uacfApiEnvironmentProvider;
            appId = UacfAppId.convertFromDeprecatedValue(uacfAppId);
            clientEventsCallback = uacfClientEventsCallback;
            Ln.d("CONTENT: UacfNotificationSdkFactory#configure with app ID %s", uacfAppId);
            UacfUserAgentProviderImpl uacfUserAgentProviderImpl = new UacfUserAgentProviderImpl("NIB", uacfAppId, str, context2.getString(R.string.sdk_version_name), context2.getString(R.string.sdk_version_code));
            userAgentProvider = uacfUserAgentProviderImpl;
            okHttpClient = okHttpClient2;
            configured = true;
        } else {
            throw new IllegalArgumentException("appVersion must not be null or empty");
        }
    }

    public UacfNotificationSdk newSdkInstance() {
        verifyConfigured();
        return new UacfNotificationSdkImpl(newNotificationServiceInstance());
    }

    @Deprecated
    public NotificationService newNotificationServiceInstance() {
        verifyConfigured();
        NotificationServiceImpl notificationServiceImpl = new NotificationServiceImpl(context, appId, userAgentProvider, environmentProvider, authProvider, clientEventsCallback, Statics.getMainDb(), okHttpClient);
        return notificationServiceImpl;
    }

    private void verifyConfigured() {
        if (!configured) {
            throw new IllegalStateException("Application must call UacfNotificationSdkFactory#configure() before calling any methods");
        }
    }
}
