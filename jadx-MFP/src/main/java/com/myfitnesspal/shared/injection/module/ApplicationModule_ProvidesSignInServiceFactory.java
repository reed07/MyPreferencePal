package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.service.SignInService;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.notification.JobServiceFactory;
import com.myfitnesspal.shared.notification.PushNotificationManager;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.thirdparty.ThirdPartyService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesSignInServiceFactory implements Factory<SignInService> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<MfpApiSettings> apiSettingsProvider;
    private final Provider<AuthTokenProvider> authTokensProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<JobServiceFactory> jobServiceFactoryProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LoginModel> loginModelProvider;
    private final ApplicationModule module;
    private final Provider<PushNotificationManager> pushNotificationManagerProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SyncService> syncServiceProvider;
    private final Provider<ThirdPartyService> thirdPartyServiceProvider;

    public ApplicationModule_ProvidesSignInServiceFactory(ApplicationModule applicationModule, Provider<Session> provider, Provider<LoginModel> provider2, Provider<SyncService> provider3, Provider<MfpApiSettings> provider4, Provider<LocalSettingsService> provider5, Provider<AnalyticsService> provider6, Provider<AuthTokenProvider> provider7, Provider<ThirdPartyService> provider8, Provider<PushNotificationManager> provider9, Provider<DbConnectionManager> provider10, Provider<JobServiceFactory> provider11) {
        this.module = applicationModule;
        this.sessionProvider = provider;
        this.loginModelProvider = provider2;
        this.syncServiceProvider = provider3;
        this.apiSettingsProvider = provider4;
        this.localSettingsServiceProvider = provider5;
        this.analyticsServiceProvider = provider6;
        this.authTokensProvider = provider7;
        this.thirdPartyServiceProvider = provider8;
        this.pushNotificationManagerProvider = provider9;
        this.dbConnectionManagerProvider = provider10;
        this.jobServiceFactoryProvider = provider11;
    }

    public SignInService get() {
        return provideInstance(this.module, this.sessionProvider, this.loginModelProvider, this.syncServiceProvider, this.apiSettingsProvider, this.localSettingsServiceProvider, this.analyticsServiceProvider, this.authTokensProvider, this.thirdPartyServiceProvider, this.pushNotificationManagerProvider, this.dbConnectionManagerProvider, this.jobServiceFactoryProvider);
    }

    public static SignInService provideInstance(ApplicationModule applicationModule, Provider<Session> provider, Provider<LoginModel> provider2, Provider<SyncService> provider3, Provider<MfpApiSettings> provider4, Provider<LocalSettingsService> provider5, Provider<AnalyticsService> provider6, Provider<AuthTokenProvider> provider7, Provider<ThirdPartyService> provider8, Provider<PushNotificationManager> provider9, Provider<DbConnectionManager> provider10, Provider<JobServiceFactory> provider11) {
        return proxyProvidesSignInService(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10), DoubleCheck.lazy(provider11));
    }

    public static ApplicationModule_ProvidesSignInServiceFactory create(ApplicationModule applicationModule, Provider<Session> provider, Provider<LoginModel> provider2, Provider<SyncService> provider3, Provider<MfpApiSettings> provider4, Provider<LocalSettingsService> provider5, Provider<AnalyticsService> provider6, Provider<AuthTokenProvider> provider7, Provider<ThirdPartyService> provider8, Provider<PushNotificationManager> provider9, Provider<DbConnectionManager> provider10, Provider<JobServiceFactory> provider11) {
        ApplicationModule_ProvidesSignInServiceFactory applicationModule_ProvidesSignInServiceFactory = new ApplicationModule_ProvidesSignInServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
        return applicationModule_ProvidesSignInServiceFactory;
    }

    public static SignInService proxyProvidesSignInService(ApplicationModule applicationModule, Lazy<Session> lazy, Lazy<LoginModel> lazy2, Lazy<SyncService> lazy3, Lazy<MfpApiSettings> lazy4, Lazy<LocalSettingsService> lazy5, Lazy<AnalyticsService> lazy6, Lazy<AuthTokenProvider> lazy7, Lazy<ThirdPartyService> lazy8, Lazy<PushNotificationManager> lazy9, Lazy<DbConnectionManager> lazy10, Lazy<JobServiceFactory> lazy11) {
        return (SignInService) Preconditions.checkNotNull(applicationModule.providesSignInService(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11), "Cannot return null from a non-@Nullable @Provides method");
    }
}
