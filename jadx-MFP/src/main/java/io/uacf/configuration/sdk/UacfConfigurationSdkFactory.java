package io.uacf.configuration.sdk;

import android.content.Context;
import com.uacf.core.caching.Cache;
import com.uacf.core.caching.MemoryCache;
import com.uacf.core.caching.SharedPreferenceCache;
import com.uacf.core.logging.BaseLogConfig;
import com.uacf.core.mapping.GsonObjectMapper;
import com.uacf.core.util.ApplicationUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import io.uacf.configuration.R;
import io.uacf.configuration.internal.model.Configuration;
import io.uacf.configuration.internal.service.ConfigurationServiceImpl;
import io.uacf.core.api.UacfApiEnvironmentProvider;
import io.uacf.core.api.UacfUserAgentProvider;
import io.uacf.core.api.UacfUserAgentProviderImpl;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.auth.UacfAuthProvider;
import io.uacf.core.interfaces.UacfClientEventsCallback;
import io.uacf.core.util.ContextUtil;
import io.uacf.net.retrofit.tracers.UacfOkHttpNetworkTracer;
import okhttp3.OkHttpClient;

public class UacfConfigurationSdkFactory {
    private static UacfAppId appId;
    private static String appVersion;
    private static UacfAuthProvider authProvider;
    private static UacfClientEventsCallback clientEventsCallback;
    private static Cache<Configuration> configurationCache;
    private static boolean configured;
    private static Context context;
    private static UacfApiEnvironmentProvider environmentProvider;
    private static OkHttpClient okHttpClient;
    private static UacfOkHttpNetworkTracer okHttpNetworkTracer;
    private static UacfUserAgentProvider userAgentProvider;

    public static void configure(Context context2, UacfAppId uacfAppId, String str, UacfAuthProvider uacfAuthProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfClientEventsCallback uacfClientEventsCallback) {
        configure(context2, uacfAppId, str, uacfAuthProvider, uacfApiEnvironmentProvider, uacfClientEventsCallback, null);
    }

    public static void configure(Context context2, UacfAppId uacfAppId, String str, UacfAuthProvider uacfAuthProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfClientEventsCallback uacfClientEventsCallback, OkHttpClient okHttpClient2) {
        configure(context2, uacfAppId, str, uacfAuthProvider, uacfApiEnvironmentProvider, uacfClientEventsCallback, okHttpClient2, null);
    }

    public static void configure(Context context2, UacfAppId uacfAppId, String str, UacfAuthProvider uacfAuthProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfClientEventsCallback uacfClientEventsCallback, OkHttpClient okHttpClient2, UacfOkHttpNetworkTracer uacfOkHttpNetworkTracer) {
        Ln.setConfig(new BaseLogConfig(ApplicationUtils.isDebuggable(context2), Strings.toString(context2.getPackageName()).toUpperCase()));
        if (context2 == null) {
            throw new IllegalArgumentException("context must not be null");
        } else if (uacfApiEnvironmentProvider == null) {
            throw new IllegalArgumentException("environmentProvider must not be null");
        } else if (uacfAppId == null) {
            throw new IllegalArgumentException("appId must not be null");
        } else if (Strings.isEmpty(str)) {
            throw new IllegalArgumentException("appVersion must not be null or empty");
        } else if (uacfAuthProvider != null) {
            context = ContextUtil.getApplicationContextSafe(context2);
            authProvider = uacfAuthProvider;
            appId = UacfAppId.convertFromDeprecatedValue(uacfAppId);
            appVersion = str;
            environmentProvider = uacfApiEnvironmentProvider;
            clientEventsCallback = uacfClientEventsCallback;
            okHttpClient = okHttpClient2;
            configurationCache = newConfigurationCache();
            okHttpNetworkTracer = uacfOkHttpNetworkTracer;
            Ln.d("CONTENT: UacfConfigurationSdkFactory#configure with app ID %s", uacfAppId);
            UacfUserAgentProviderImpl uacfUserAgentProviderImpl = new UacfUserAgentProviderImpl("CONFIGURATION", appId, appVersion, context2.getString(R.string.sdk_version_name), context2.getString(R.string.sdk_version_code));
            userAgentProvider = uacfUserAgentProviderImpl;
            configured = true;
        } else {
            throw new IllegalArgumentException("authProvider must not be null");
        }
    }

    public UacfConfigurationSdk newSdkInstance() {
        verifyConfigured();
        ConfigurationServiceImpl configurationServiceImpl = new ConfigurationServiceImpl(context, appId, userAgentProvider, environmentProvider, authProvider, clientEventsCallback, configurationCache, okHttpClient, okHttpNetworkTracer);
        return new UacfConfigurationSdkImpl(configurationServiceImpl);
    }

    private static Cache<Configuration> newConfigurationCache() {
        return new MemoryCache(new SharedPreferenceCache(context.getSharedPreferences("ConfigurationSharedPrefs", 0)).withMapper(new GsonObjectMapper().withType(Configuration.class)));
    }

    private void verifyConfigured() {
        if (!configured) {
            throw new IllegalStateException("Application must call UacfConfigurationSdkFactory#configure() before calling any methods");
        }
    }
}
