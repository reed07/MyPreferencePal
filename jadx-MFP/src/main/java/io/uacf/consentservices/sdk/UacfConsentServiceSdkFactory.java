package io.uacf.consentservices.sdk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.uacf.core.logging.BaseLogConfig;
import com.uacf.core.util.ApplicationUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import io.uacf.consentservices.R;
import io.uacf.consentservices.internal.service.ConsentServiceImpl;
import io.uacf.core.api.UacfApiEnvironmentProvider;
import io.uacf.core.api.UacfUserAgentProvider;
import io.uacf.core.api.UacfUserAgentProviderImpl;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.app.UacfUserAccountDomain;
import io.uacf.core.auth.UacfAuthProvider;
import io.uacf.core.interfaces.UacfDeviceIdProvider;
import io.uacf.net.retrofit.tracers.UacfOkHttpNetworkTracer;
import java.util.Locale;
import okhttp3.OkHttpClient;

public class UacfConsentServiceSdkFactory {
    /* access modifiers changed from: private */
    public static UacfConsentsAppDomain appDomain;
    /* access modifiers changed from: private */
    public static UacfAppId appId;
    /* access modifiers changed from: private */
    public static String appVersion;
    /* access modifiers changed from: private */
    public static UacfAuthProvider authProvider;
    /* access modifiers changed from: private */
    public static Locale consentLocale;
    /* access modifiers changed from: private */
    public static UacfDeviceIdProvider deviceIdProvider;
    /* access modifiers changed from: private */
    public static UacfUserAccountDomain domain;
    /* access modifiers changed from: private */
    public static UacfApiEnvironmentProvider environmentProvider;
    /* access modifiers changed from: private */
    public static boolean isConfigured;
    /* access modifiers changed from: private */
    public static OkHttpClient okHttpClient;
    /* access modifiers changed from: private */
    public static UacfOkHttpNetworkTracer okHttpNetworkTracer;
    /* access modifiers changed from: private */
    public static UacfUserAgentProvider userAgentProvider;

    public static class Builder {
        private UacfConsentsAppDomain appDomain;
        private UacfAppId appId;
        private String appVersion;
        private UacfAuthProvider authProvider;
        private Locale consentLocale;
        private Context context;
        private UacfDeviceIdProvider deviceIdProvider;
        private UacfUserAccountDomain domain;
        private UacfApiEnvironmentProvider environmentProvider;
        private OkHttpClient okHttpClient;
        private UacfOkHttpNetworkTracer okHttpNetworkTracer;
        private UacfUserAgentProvider userAgentProvider;

        public Builder(@NonNull Context context2) {
            if (context2 != null) {
                this.context = context2;
                return;
            }
            throw new IllegalArgumentException("context must not be null");
        }

        public Builder withDomain(@NonNull UacfUserAccountDomain uacfUserAccountDomain) {
            this.domain = uacfUserAccountDomain;
            return this;
        }

        public Builder withConsentsAppDomain(@NonNull UacfConsentsAppDomain uacfConsentsAppDomain) {
            this.appDomain = uacfConsentsAppDomain;
            return this;
        }

        public Builder withDeviceIdProvider(@NonNull UacfDeviceIdProvider uacfDeviceIdProvider) {
            this.deviceIdProvider = uacfDeviceIdProvider;
            return this;
        }

        public Builder withAppId(@NonNull UacfAppId uacfAppId) {
            this.appId = uacfAppId;
            return this;
        }

        public Builder withAppVersion(@NonNull String str) {
            this.appVersion = str;
            return this;
        }

        public Builder withAuthProvider(@NonNull UacfAuthProvider uacfAuthProvider) {
            this.authProvider = uacfAuthProvider;
            return this;
        }

        public Builder withEnvironmentProvider(@NonNull UacfApiEnvironmentProvider uacfApiEnvironmentProvider) {
            this.environmentProvider = uacfApiEnvironmentProvider;
            return this;
        }

        public UacfConsentServiceSdk build() {
            if (this.appId == null && UacfConsentServiceSdkFactory.appId == null) {
                throw new IllegalStateException("appId must not be null");
            } else if (TextUtils.isEmpty(this.appVersion) && TextUtils.isEmpty(UacfConsentServiceSdkFactory.appVersion)) {
                throw new IllegalStateException("appVersion must not be null");
            } else if (this.authProvider == null && UacfConsentServiceSdkFactory.authProvider == null) {
                throw new IllegalStateException("authProvider must not be null");
            } else if (this.environmentProvider == null && UacfConsentServiceSdkFactory.environmentProvider == null) {
                throw new IllegalStateException("environmentProvider must not be null");
            } else if (this.deviceIdProvider == null && UacfConsentServiceSdkFactory.deviceIdProvider == null) {
                throw new IllegalStateException("deviceIdProvider must not be null");
            } else if (this.domain == null && UacfConsentServiceSdkFactory.domain == null) {
                throw new IllegalStateException("domain must not be null");
            } else if (this.appDomain == null && UacfConsentServiceSdkFactory.appDomain == null) {
                throw new IllegalStateException("consentsAppDomain must not be null");
            } else {
                configure();
                return UacfConsentServiceSdkFactory.getUacfConsentServiceSdk(this.context);
            }
        }

        private void configure() {
            Ln.setConfig(new BaseLogConfig(ApplicationUtils.isDebuggable(this.context), Strings.toString(this.context.getPackageName()).toUpperCase()));
            Ln.d("CONTENT: UacfConsentsSdkFactory#configure with app ID %s", this.appId);
            UacfUserAccountDomain uacfUserAccountDomain = this.domain;
            if (uacfUserAccountDomain != null) {
                UacfConsentServiceSdkFactory.domain = uacfUserAccountDomain;
            }
            UacfConsentsAppDomain uacfConsentsAppDomain = this.appDomain;
            if (uacfConsentsAppDomain != null) {
                UacfConsentServiceSdkFactory.appDomain = uacfConsentsAppDomain;
            }
            UacfDeviceIdProvider uacfDeviceIdProvider = this.deviceIdProvider;
            if (uacfDeviceIdProvider != null) {
                UacfConsentServiceSdkFactory.deviceIdProvider = uacfDeviceIdProvider;
            }
            UacfAppId uacfAppId = this.appId;
            if (uacfAppId != null) {
                UacfConsentServiceSdkFactory.appId = uacfAppId;
            }
            String str = this.appVersion;
            if (str != null) {
                UacfConsentServiceSdkFactory.appVersion = str;
            }
            UacfApiEnvironmentProvider uacfApiEnvironmentProvider = this.environmentProvider;
            if (uacfApiEnvironmentProvider != null) {
                UacfConsentServiceSdkFactory.environmentProvider = uacfApiEnvironmentProvider;
            }
            UacfAuthProvider uacfAuthProvider = this.authProvider;
            if (uacfAuthProvider != null) {
                UacfConsentServiceSdkFactory.authProvider = uacfAuthProvider;
            }
            OkHttpClient okHttpClient2 = this.okHttpClient;
            if (okHttpClient2 != null) {
                UacfConsentServiceSdkFactory.okHttpClient = okHttpClient2;
            }
            UacfOkHttpNetworkTracer uacfOkHttpNetworkTracer = this.okHttpNetworkTracer;
            if (uacfOkHttpNetworkTracer != null) {
                UacfConsentServiceSdkFactory.okHttpNetworkTracer = uacfOkHttpNetworkTracer;
            }
            Locale locale = this.consentLocale;
            if (locale != null) {
                UacfConsentServiceSdkFactory.consentLocale = locale;
            }
            UacfUserAgentProviderImpl uacfUserAgentProviderImpl = new UacfUserAgentProviderImpl(Screens.CONSENTS_SCREEN, UacfConsentServiceSdkFactory.appId, UacfConsentServiceSdkFactory.appVersion, this.context.getString(R.string.sdk_version_name), this.context.getString(R.string.sdk_version_code));
            this.userAgentProvider = uacfUserAgentProviderImpl;
            UacfConsentServiceSdkFactory.userAgentProvider = this.userAgentProvider;
            UacfConsentServiceSdkFactory.isConfigured = true;
        }
    }

    /* access modifiers changed from: private */
    @NonNull
    public static UacfConsentServiceSdk getUacfConsentServiceSdk(Context context) {
        ConsentServiceImpl consentServiceImpl = new ConsentServiceImpl(context, domain, appDomain, deviceIdProvider.get(), appId.getBaseAppId(), userAgentProvider, environmentProvider, authProvider, okHttpClient, okHttpNetworkTracer, consentLocale);
        return new UacfConsentServiceSdkImpl(consentServiceImpl);
    }

    public UacfConsentServiceSdk newSdkInstance(@NonNull Context context) {
        if (isConfigured) {
            return getUacfConsentServiceSdk(context);
        }
        throw new IllegalStateException("Application must call UacfConsentServiceSdkFactory#build() before calling any methods");
    }
}
