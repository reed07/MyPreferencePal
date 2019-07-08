package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsTaskQueue;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.UUID;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesMfpAnalyticsServiceFactory implements Factory<MfpAnalyticsService> {
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<AuthTokenProvider> authTokensProvider;
    private final Provider<String> carrierNameProvider;
    private final Provider<String> clientIdProvider;
    private final Provider<Context> contextProvider;
    private final Provider<UUID> deviceIdProvider;
    private final ApplicationModule module;
    private final Provider<MfpAnalyticsTaskQueue> queueProvider;
    private final Provider<String> sessionIdProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SubscriptionService> subscriptionServiceProvider;

    public ApplicationModule_ProvidesMfpAnalyticsServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<AppSettings> provider2, Provider<String> provider3, Provider<String> provider4, Provider<MfpAnalyticsTaskQueue> provider5, Provider<UUID> provider6, Provider<String> provider7, Provider<AuthTokenProvider> provider8, Provider<Session> provider9, Provider<SubscriptionService> provider10) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.appSettingsProvider = provider2;
        this.sessionIdProvider = provider3;
        this.carrierNameProvider = provider4;
        this.queueProvider = provider5;
        this.deviceIdProvider = provider6;
        this.clientIdProvider = provider7;
        this.authTokensProvider = provider8;
        this.sessionProvider = provider9;
        this.subscriptionServiceProvider = provider10;
    }

    public MfpAnalyticsService get() {
        return provideInstance(this.module, this.contextProvider, this.appSettingsProvider, this.sessionIdProvider, this.carrierNameProvider, this.queueProvider, this.deviceIdProvider, this.clientIdProvider, this.authTokensProvider, this.sessionProvider, this.subscriptionServiceProvider);
    }

    public static MfpAnalyticsService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<AppSettings> provider2, Provider<String> provider3, Provider<String> provider4, Provider<MfpAnalyticsTaskQueue> provider5, Provider<UUID> provider6, Provider<String> provider7, Provider<AuthTokenProvider> provider8, Provider<Session> provider9, Provider<SubscriptionService> provider10) {
        return proxyProvidesMfpAnalyticsService(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2), (String) provider3.get(), (String) provider4.get(), DoubleCheck.lazy(provider5), (UUID) provider6.get(), (String) provider7.get(), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10));
    }

    public static ApplicationModule_ProvidesMfpAnalyticsServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<AppSettings> provider2, Provider<String> provider3, Provider<String> provider4, Provider<MfpAnalyticsTaskQueue> provider5, Provider<UUID> provider6, Provider<String> provider7, Provider<AuthTokenProvider> provider8, Provider<Session> provider9, Provider<SubscriptionService> provider10) {
        ApplicationModule_ProvidesMfpAnalyticsServiceFactory applicationModule_ProvidesMfpAnalyticsServiceFactory = new ApplicationModule_ProvidesMfpAnalyticsServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
        return applicationModule_ProvidesMfpAnalyticsServiceFactory;
    }

    public static MfpAnalyticsService proxyProvidesMfpAnalyticsService(ApplicationModule applicationModule, Context context, Lazy<AppSettings> lazy, String str, String str2, Lazy<MfpAnalyticsTaskQueue> lazy2, UUID uuid, String str3, Lazy<AuthTokenProvider> lazy3, Lazy<Session> lazy4, Lazy<SubscriptionService> lazy5) {
        return (MfpAnalyticsService) Preconditions.checkNotNull(applicationModule.providesMfpAnalyticsService(context, lazy, str, str2, lazy2, uuid, str3, lazy3, lazy4, lazy5), "Cannot return null from a non-@Nullable @Provides method");
    }
}
