package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.myfitnesspal.feature.payments.db.SubscriptionServiceDbAdapter;
import com.myfitnesspal.feature.payments.service.PaymentService;
import com.myfitnesspal.feature.payments.service.PaymentServiceImpl;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.PremiumServiceImpl;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.premium.util.PremiumFeatureOverrides;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.settings.model.ABTestSettings;
import com.myfitnesspal.shared.api.ApiConstructorArgs;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.api.v2.MfpV2ApiImpl;
import com.myfitnesspal.shared.api.v2.MfpV2ConfigApi;
import com.myfitnesspal.shared.bus.PostFromAnyThreadBus;
import com.myfitnesspal.shared.logging.CrashlyticsPrinter;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.config.ConfigServiceImpl;
import com.myfitnesspal.shared.service.config.Configuration;
import com.myfitnesspal.shared.service.device.UserAgentProvider;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.squareup.otto.Bus;
import com.uacf.core.caching.Cache;
import com.uacf.core.logging.BaseLogConfig;
import com.uacf.core.logging.LogConfig;
import com.uacf.core.logging.LogcatPrinter;
import com.uacf.core.logging.MultiPrinter;
import com.uacf.core.logging.Printer;
import com.uacf.core.logging.PrivateFilePrinter;
import com.uacf.core.mapping.Mapper2;
import com.uacf.core.mock.interceptor.RequestInterceptor;
import com.uacf.core.mock.interceptor.legacy.FileRequestInterceptor;
import com.uacf.core.util.ApplicationUtils;
import com.uacf.core.util.DeviceInfo;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import java.util.UUID;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

@Module
public class ServiceModule {
    /* access modifiers changed from: 0000 */
    @Singleton
    @NonNull
    @Provides
    public Bus provideOttoBus() {
        return new PostFromAnyThreadBus();
    }

    @NonNull
    @Provides
    public MfpV2Api providesMfpJsonV2Api(Lazy<ApiUrlProvider> lazy, UserAgentProvider userAgentProvider, @Named("deviceUUID") UUID uuid, @Named("appVersionCode") long j, Lazy<RequestInterceptor> lazy2, ApiJsonMapper apiJsonMapper, Lazy<Bus> lazy3, Lazy<AuthTokenProvider> lazy4, Lazy<CountryService> lazy5, Lazy<DeviceInfo> lazy6) {
        ApiConstructorArgs apiConstructorArgs = new ApiConstructorArgs(lazy, userAgentProvider, uuid, j, lazy2, lazy3, lazy4, lazy5, lazy6);
        return new MfpV2ApiImpl(apiConstructorArgs).withMapper((Mapper2) apiJsonMapper);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public Printer providesPrinter(PrivateFilePrinter privateFilePrinter) {
        return new MultiPrinter(new LogcatPrinter(), new CrashlyticsPrinter(), privateFilePrinter);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public LogConfig providesLnConfig(Context context) {
        return new BaseLogConfig(ApplicationUtils.isDebuggable(context), Strings.toString(context.getPackageName()).toUpperCase());
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public LoginModel providesLoginModel(@Named("login-shared-preferences") SharedPreferences sharedPreferences, Lazy<Session> lazy, Lazy<GlobalSettingsService> lazy2) {
        return new LoginModel(lazy, sharedPreferences, lazy2);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public ConfigService providesConfigService(Provider<MfpV2ConfigApi> provider, Cache<Configuration> cache, Lazy<Session> lazy, ABTestSettings aBTestSettings, @Named("deviceUUID") UUID uuid, @Named("appVersionName") String str, @Named("appVersionCode") long j, Lazy<AnalyticsService> lazy2, Lazy<CountryService> lazy3) {
        ConfigServiceImpl configServiceImpl = new ConfigServiceImpl(provider, cache, lazy, aBTestSettings, uuid, str, j, lazy2, lazy3);
        return configServiceImpl;
    }

    /* access modifiers changed from: protected */
    @Singleton
    @NonNull
    @Provides
    public PaymentService providesPaymentService(Context context) {
        return new PaymentServiceImpl(context);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public PremiumService providesPremiumService(Lazy<ConfigService> lazy, Lazy<SubscriptionService> lazy2, Lazy<ProductService> lazy3, Lazy<PremiumFeatureOverrides> lazy4, Lazy<GlobalSettingsService> lazy5, Lazy<Bus> lazy6) {
        PremiumServiceImpl premiumServiceImpl = new PremiumServiceImpl(lazy, lazy2, lazy3, lazy4, lazy5, lazy6);
        return premiumServiceImpl;
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @NonNull
    @Provides
    public RequestInterceptor providesInterceptor(MfpApiSettings mfpApiSettings) {
        return new FileRequestInterceptor(mfpApiSettings);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public SubscriptionServiceDbAdapter providesSubscriptionServiceDbAdapter(Context context, Lazy<Session> lazy) {
        return new SubscriptionServiceDbAdapter(context, lazy);
    }
}
