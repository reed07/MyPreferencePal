package io.uacf.rollouts.sdk;

import android.content.Context;
import com.uacf.core.logging.BaseLogConfig;
import com.uacf.core.util.ApplicationUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import io.uacf.core.api.UacfApiEnvironmentProvider;
import io.uacf.core.api.UacfUserAgentProvider;
import io.uacf.core.api.UacfUserAgentProviderImpl;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.app.UacfUserAccountDomain;
import io.uacf.core.auth.UacfAuthProvider;
import io.uacf.core.interfaces.UacfClientEventsCallback;
import io.uacf.core.interfaces.UacfDeviceIdProvider;
import io.uacf.core.util.ContextUtil;
import io.uacf.net.retrofit.tracers.UacfOkHttpNetworkTracer;
import io.uacf.rollouts.R;
import io.uacf.rollouts.internal.RuntimeConfigurationImpl;
import io.uacf.rollouts.internal.module.Statics;
import io.uacf.rollouts.internal.service.VariantServiceImpl;
import okhttp3.OkHttpClient;

public final class UacfVariantSdkFactory {
    private static UacfAppId appId;
    private static String appVersion;
    private static UacfAuthProvider authProvider;
    private static UacfClientEventsCallback clientEventsCallback;
    private static boolean configured;
    private static Context context;
    private static String defaultVariantValuesAsJsonString;
    private static UacfDeviceIdProvider deviceIdProvider;
    private static UacfUserAccountDomain domain;
    private static UacfApiEnvironmentProvider environmentProvider;
    private static OkHttpClient okHttpClient;
    private static UacfOkHttpNetworkTracer okHttpNetworkTracer;
    private static UacfUserAgentProvider userAgentProvider;

    public static void configure(Context context2, UacfAppId uacfAppId, String str, UacfDeviceIdProvider uacfDeviceIdProvider, UacfAuthProvider uacfAuthProvider, UacfUserAccountDomain uacfUserAccountDomain, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfClientEventsCallback uacfClientEventsCallback, String str2) {
        configure(context2, uacfAppId, str, uacfDeviceIdProvider, uacfAuthProvider, uacfUserAccountDomain, uacfApiEnvironmentProvider, uacfClientEventsCallback, str2, null);
    }

    public static void configure(Context context2, UacfAppId uacfAppId, String str, UacfDeviceIdProvider uacfDeviceIdProvider, UacfAuthProvider uacfAuthProvider, UacfUserAccountDomain uacfUserAccountDomain, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfClientEventsCallback uacfClientEventsCallback, String str2, OkHttpClient okHttpClient2) {
        configure(context2, uacfAppId, str, uacfDeviceIdProvider, uacfAuthProvider, uacfUserAccountDomain, uacfApiEnvironmentProvider, uacfClientEventsCallback, str2, okHttpClient2, null);
    }

    public static void configure(Context context2, UacfAppId uacfAppId, String str, UacfDeviceIdProvider uacfDeviceIdProvider, UacfAuthProvider uacfAuthProvider, UacfUserAccountDomain uacfUserAccountDomain, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfClientEventsCallback uacfClientEventsCallback, String str2, OkHttpClient okHttpClient2, UacfOkHttpNetworkTracer uacfOkHttpNetworkTracer) {
        Context context3 = context2;
        UacfApiEnvironmentProvider uacfApiEnvironmentProvider2 = uacfApiEnvironmentProvider;
        Ln.setLoggingLevel(new RuntimeConfigurationImpl().isDebugBuild() ? 2 : 6);
        if (context3 == null) {
            throw new IllegalArgumentException("context must not be null");
        } else if (uacfClientEventsCallback == null) {
            throw new IllegalArgumentException("clientEventsCallback must not be null");
        } else if (uacfApiEnvironmentProvider2 != null) {
            Statics.init(context2, uacfApiEnvironmentProvider);
            Ln.setConfig(new BaseLogConfig(ApplicationUtils.isDebuggable(context2), Strings.toString(context2.getPackageName()).toUpperCase()));
            if (uacfAppId == null) {
                throw new IllegalArgumentException("appId must not be null");
            } else if (Strings.isEmpty(str)) {
                throw new IllegalArgumentException("appVersion must not be null or empty");
            } else if (uacfDeviceIdProvider == null) {
                throw new IllegalArgumentException("deviceId must not be null or empty");
            } else if (uacfAuthProvider == null) {
                throw new IllegalArgumentException("authProvider must not be null");
            } else if (uacfUserAccountDomain != null) {
                context = ContextUtil.getApplicationContextSafe(context2);
                authProvider = uacfAuthProvider;
                domain = uacfUserAccountDomain;
                appId = UacfAppId.convertFromDeprecatedValue(uacfAppId);
                deviceIdProvider = uacfDeviceIdProvider;
                appVersion = str;
                environmentProvider = uacfApiEnvironmentProvider2;
                clientEventsCallback = uacfClientEventsCallback;
                defaultVariantValuesAsJsonString = str2;
                okHttpNetworkTracer = uacfOkHttpNetworkTracer;
                Ln.d("CONTENT: UacfVariantSdkFactory#configure with app ID %s", uacfAppId);
                UacfUserAgentProviderImpl uacfUserAgentProviderImpl = new UacfUserAgentProviderImpl("ROLLOUT", uacfAppId, str, context2.getString(R.string.sdk_version_name), context2.getString(R.string.sdk_version_code));
                userAgentProvider = uacfUserAgentProviderImpl;
                okHttpClient = okHttpClient2;
                configured = true;
            } else {
                throw new IllegalArgumentException("domain must not be null");
            }
        } else {
            throw new IllegalArgumentException("environmentProvider must not be null");
        }
    }

    public UacfVariantSdk newSdkInstance() {
        verifyConfigured();
        VariantServiceImpl variantServiceImpl = new VariantServiceImpl(context, deviceIdProvider.get(), userAgentProvider, environmentProvider, authProvider, appId, domain, appVersion, Statics.getMainDb(), clientEventsCallback, defaultVariantValuesAsJsonString, okHttpClient, okHttpNetworkTracer);
        return new UacfVariantSdkImpl(variantServiceImpl);
    }

    private void verifyConfigured() {
        if (!configured) {
            throw new IllegalStateException("Application must call UacfVariantSdkFactory#configure() before calling any methods");
        }
    }
}
