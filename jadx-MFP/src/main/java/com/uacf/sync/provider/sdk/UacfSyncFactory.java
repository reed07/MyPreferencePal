package com.uacf.sync.provider.sdk;

import android.content.Context;
import com.uacf.core.logging.BaseLogConfig;
import com.uacf.core.util.ApplicationUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.sync.engine.R;
import com.uacf.sync.provider.internal.service.InternalSyncService;
import com.uacf.sync.provider.internal.service.InternalSyncServiceImpl;
import io.uacf.core.api.UacfApiEnvironmentProvider;
import io.uacf.core.api.UacfUserAgentProvider;
import io.uacf.core.api.UacfUserAgentProviderImpl;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.auth.UacfAuthProvider;
import io.uacf.core.util.ContextUtil;
import io.uacf.net.retrofit.tracers.UacfOkHttpNetworkTracer;
import okhttp3.OkHttpClient;

public final class UacfSyncFactory {
    private static UacfAppId appId;
    private static UacfAuthProvider authProvider;
    private static boolean configured;
    private static Context context;
    private static UacfApiEnvironmentProvider environmentProvider;
    private static OkHttpClient okHttpClient;
    private static UacfOkHttpNetworkTracer okHttpNetworkTracer;
    private static UacfUserAgentProvider userAgentProvider;

    public static void configure(Context context2, UacfAppId uacfAppId, String str, UacfAuthProvider uacfAuthProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider) {
        configure(context2, uacfAppId, str, uacfAuthProvider, uacfApiEnvironmentProvider, null);
    }

    public static void configure(Context context2, UacfAppId uacfAppId, String str, UacfAuthProvider uacfAuthProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, OkHttpClient okHttpClient2) {
        configure(context2, uacfAppId, str, uacfAuthProvider, uacfApiEnvironmentProvider, okHttpClient2, null);
    }

    public static void configure(Context context2, UacfAppId uacfAppId, String str, UacfAuthProvider uacfAuthProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, OkHttpClient okHttpClient2, UacfOkHttpNetworkTracer uacfOkHttpNetworkTracer) {
        Ln.setLoggingLevel(6);
        Ln.setConfig(new BaseLogConfig(ApplicationUtils.isDebuggable(context2), Strings.toString(context2.getPackageName()).toUpperCase()));
        if (uacfAppId == null) {
            throw new IllegalArgumentException("appId must not be null");
        } else if (!Strings.isEmpty(str)) {
            context = ContextUtil.getApplicationContextSafe(context2);
            authProvider = uacfAuthProvider;
            environmentProvider = uacfApiEnvironmentProvider;
            appId = UacfAppId.convertFromDeprecatedValue(uacfAppId);
            Ln.d("CONTENT: UacfSyncV2Factory#configure with app ID %s", uacfAppId);
            UacfUserAgentProviderImpl uacfUserAgentProviderImpl = new UacfUserAgentProviderImpl("SYNC", uacfAppId, str, context2.getString(R.string.sdk_version_name), context2.getString(R.string.sdk_version_code));
            userAgentProvider = uacfUserAgentProviderImpl;
            okHttpClient = okHttpClient2;
            okHttpNetworkTracer = uacfOkHttpNetworkTracer;
            configured = true;
        } else {
            throw new IllegalArgumentException("appVersion must not be null or empty");
        }
    }

    public InternalSyncService newSyncServiceInstance() {
        verifyConfigured();
        InternalSyncServiceImpl internalSyncServiceImpl = new InternalSyncServiceImpl(context, userAgentProvider, environmentProvider, authProvider, appId, okHttpClient, okHttpNetworkTracer);
        return internalSyncServiceImpl;
    }

    private void verifyConfigured() {
        if (!configured) {
            throw new IllegalStateException("Application must call UacfNotificationSdkFactory#configure() before calling any methods");
        }
    }
}
