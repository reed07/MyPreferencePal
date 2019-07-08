package io.uacf.clientevents.sdk;

import android.content.Context;
import com.uacf.core.logging.BaseLogConfig;
import com.uacf.core.util.ApplicationUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import io.uacf.clientevents.R;
import io.uacf.clientevents.internal.ClientEventsService;
import io.uacf.clientevents.internal.ClientEventsServiceImpl;
import io.uacf.clientevents.internal.build.RuntimeConfigurationImpl;
import io.uacf.clientevents.internal.database.ClientEventsTable;
import io.uacf.clientevents.internal.module.Statics;
import io.uacf.core.api.UacfApiEnvironmentProvider;
import io.uacf.core.api.UacfUserAgentProvider;
import io.uacf.core.api.UacfUserAgentProviderImpl;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.auth.UacfAuthProvider;
import io.uacf.core.interfaces.UacfDeviceIdProvider;
import io.uacf.core.util.ContextUtil;
import io.uacf.net.retrofit.tracers.UacfOkHttpNetworkTracer;
import okhttp3.OkHttpClient;

public final class UacfClientEventsSdkFactory {
    private static UacfAppId appId;
    private static UacfAuthProvider authProvider;
    private static boolean configured;
    private static Context context;
    private static UacfDeviceIdProvider deviceIdProvider;
    private static UacfApiEnvironmentProvider environmentProvider;
    private static UacfOkHttpNetworkTracer networkTracer;
    private static OkHttpClient okHttpClient;
    private static UacfUserAgentProvider userAgentProvider;

    public static void configure(Context context2, UacfAppId uacfAppId, String str, UacfDeviceIdProvider uacfDeviceIdProvider, UacfAuthProvider uacfAuthProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider) {
        configure(context2, uacfAppId, str, uacfDeviceIdProvider, uacfAuthProvider, uacfApiEnvironmentProvider, null);
    }

    public static void configure(Context context2, UacfAppId uacfAppId, String str, UacfDeviceIdProvider uacfDeviceIdProvider, UacfAuthProvider uacfAuthProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, OkHttpClient okHttpClient2) {
        configure(context2, uacfAppId, str, uacfDeviceIdProvider, uacfAuthProvider, uacfApiEnvironmentProvider, okHttpClient2, null);
    }

    public static void configure(Context context2, UacfAppId uacfAppId, String str, UacfDeviceIdProvider uacfDeviceIdProvider, UacfAuthProvider uacfAuthProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, OkHttpClient okHttpClient2, UacfOkHttpNetworkTracer uacfOkHttpNetworkTracer) {
        Statics.init(context2, uacfApiEnvironmentProvider);
        BaseLogConfig baseLogConfig = new BaseLogConfig(ApplicationUtils.isDebuggable(context2), Strings.toString(context2.getPackageName()).toUpperCase());
        baseLogConfig.setLoggingLevel(new RuntimeConfigurationImpl().isDebugBuild() ? 2 : 6);
        Ln.setConfig(baseLogConfig);
        if (uacfAppId == null) {
            throw new IllegalArgumentException("appId must not be null");
        } else if (Strings.isEmpty(str)) {
            throw new IllegalArgumentException("appVersion must not be null or empty");
        } else if (uacfDeviceIdProvider != null) {
            context = ContextUtil.getApplicationContextSafe(context2);
            authProvider = uacfAuthProvider;
            environmentProvider = uacfApiEnvironmentProvider;
            appId = UacfAppId.convertFromDeprecatedValue(uacfAppId);
            deviceIdProvider = uacfDeviceIdProvider;
            Ln.d("CONTENT: UacfClientEventsSdkFactory#configure with app ID %s", uacfAppId);
            UacfUserAgentProviderImpl uacfUserAgentProviderImpl = new UacfUserAgentProviderImpl("CLIENTEVENTS", uacfAppId, str, context2.getString(R.string.sdk_version_name), context2.getString(R.string.sdk_version_code));
            userAgentProvider = uacfUserAgentProviderImpl;
            okHttpClient = okHttpClient2;
            networkTracer = uacfOkHttpNetworkTracer;
            configured = true;
        } else {
            throw new IllegalArgumentException("deviceIdProvider must not be null or empty");
        }
    }

    public UacfClientEventsSdk newSdkInstance() {
        verifyConfigured();
        return new UacfClientEventsSdkImpl(newAnalyticsServiceInstance());
    }

    /* access modifiers changed from: 0000 */
    public ClientEventsService newAnalyticsServiceInstance() {
        verifyConfigured();
        ClientEventsServiceImpl clientEventsServiceImpl = new ClientEventsServiceImpl(context, deviceIdProvider.get(), userAgentProvider, environmentProvider, authProvider, appId, new ClientEventsTable(Statics.getMainDb()), okHttpClient, networkTracer);
        return clientEventsServiceImpl;
    }

    private void verifyConfigured() {
        if (!configured) {
            throw new IllegalStateException("Application must call UacfClientEventsSdkFactory#configure() before calling any methods");
        }
    }
}
