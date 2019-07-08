package com.uacf.identity.sdk;

import android.content.Context;
import com.uacf.core.caching.Cache;
import com.uacf.core.caching.MemoryCache;
import com.uacf.core.caching.SharedPreferenceCache;
import com.uacf.core.logging.BaseLogConfig;
import com.uacf.core.mapping.GsonObjectMapper;
import com.uacf.core.tracing.FSTracerProvider;
import com.uacf.core.util.ApplicationUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.identity.R;
import com.uacf.identity.internal.constants.Tracing;
import com.uacf.identity.internal.identity.IdentityService;
import com.uacf.identity.internal.identity.IdentityServiceImpl;
import com.uacf.identity.internal.model.AppSessionInfo;
import com.uacf.identity.internal.sdk.UacfIdentitySdkImpl;
import com.uacf.identity.internal.session.Session;
import com.uacf.identity.internal.session.SessionImpl;
import com.uacf.identity.sdk.model.UacfIdentityApiEnvironment;
import io.opentracing.Tracer;
import io.uacf.core.api.UacfApiEnvironmentProvider;
import io.uacf.core.api.UacfUserAgentProvider;
import io.uacf.core.api.UacfUserAgentProviderImpl;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.interfaces.UacfClientEventsCallback;
import io.uacf.core.util.ContextUtil;
import okhttp3.OkHttpClient;

public final class UacfIdentitySdkFactory {
    private static UacfApiEnvironmentProvider apiEnvironmentProvider;
    private static UacfAppId appId;
    private static String appVersion;
    private static UacfClientEventsCallback clientEventsCallback;
    private static boolean configured;
    private static Context context;
    static Session currentSession;
    private static OkHttpClient okHttpClient;
    private static Tracer tracer;
    private static UacfUserAgentProvider userAgentProvider;
    private static String versionCode;
    private static String versionName;

    public static void configure(Context context2, UacfAppId uacfAppId, String str, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfClientEventsCallback uacfClientEventsCallback) {
        configure(context2, uacfAppId, str, uacfApiEnvironmentProvider, uacfClientEventsCallback, null);
    }

    public static void configure(Context context2, UacfAppId uacfAppId, String str, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfClientEventsCallback uacfClientEventsCallback, OkHttpClient okHttpClient2) {
        configure(context2, uacfAppId, str, uacfApiEnvironmentProvider, uacfClientEventsCallback, okHttpClient2, null);
    }

    public static void configure(Context context2, UacfAppId uacfAppId, String str, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfClientEventsCallback uacfClientEventsCallback, OkHttpClient okHttpClient2, Tracer tracer2) {
        Ln.setConfig(new BaseLogConfig(ApplicationUtils.isDebuggable(context2), Strings.toString(context2.getPackageName()).toUpperCase()));
        if (uacfAppId == null) {
            throw new IllegalArgumentException("appId must not be null");
        } else if (!Strings.isEmpty(str)) {
            context = ContextUtil.getApplicationContextSafe(context2);
            appId = uacfAppId;
            appVersion = str;
            versionName = context2.getString(R.string.idm_version_name);
            versionCode = context2.getString(R.string.idm_version_name);
            clientEventsCallback = uacfClientEventsCallback;
            currentSession = new SessionImpl(context, uacfAppId, newSessionInfoCache());
            Ln.d("CONTENT: UacfIdentitySdkFactory#configure with app ID %s, CurrentSession = %s", uacfAppId, currentSession);
            UacfUserAgentProviderImpl uacfUserAgentProviderImpl = new UacfUserAgentProviderImpl("IDM", uacfAppId, str, versionName, versionCode);
            userAgentProvider = uacfUserAgentProviderImpl;
            apiEnvironmentProvider = uacfApiEnvironmentProvider;
            okHttpClient = okHttpClient2;
            tracer = FSTracerProvider.getTracerInstance(context2, tracer2, Tracing.TRACING_SETTING);
            configured = true;
        } else {
            throw new IllegalArgumentException("appVersion must not be null or empty");
        }
    }

    public Session getCurrentSession() {
        Ln.d("CONTENT: appId = %s, session = %s", appId, currentSession);
        return currentSession;
    }

    public UacfIdentitySdk newSdkInstance() {
        verifyConfigured();
        UacfIdentitySdkImpl uacfIdentitySdkImpl = new UacfIdentitySdkImpl(appId, currentSession, newIdentityService(), tracer, "fs-android-sso-sdk", versionName, versionCode, appVersion);
        return uacfIdentitySdkImpl;
    }

    private IdentityService newIdentityService() {
        String str;
        try {
            str = ((UacfIdentityApiEnvironment) apiEnvironmentProvider.get()).getRedirectUri();
        } catch (Exception e) {
            Ln.e(e, "Provided Api Environment is not of type UacfIdentityApiEnvironment", new Object[0]);
            str = null;
        }
        IdentityServiceImpl identityServiceImpl = new IdentityServiceImpl(context, currentSession, appId, userAgentProvider, apiEnvironmentProvider.get().getBaseUrl(), apiEnvironmentProvider.get().getClientId(), apiEnvironmentProvider.get().getClientSecret(), str, clientEventsCallback, okHttpClient, tracer);
        return identityServiceImpl;
    }

    private static Cache<AppSessionInfo> newSessionInfoCache() {
        return new MemoryCache(new SharedPreferenceCache(context.getSharedPreferences("session", 0)).withMapper(new GsonObjectMapper().withType(AppSessionInfo.class)));
    }

    private void verifyConfigured() {
        if (!configured) {
            throw new IllegalStateException("Application must call UacfIdentitySdkFactory#configure() before calling any methods");
        }
    }
}
