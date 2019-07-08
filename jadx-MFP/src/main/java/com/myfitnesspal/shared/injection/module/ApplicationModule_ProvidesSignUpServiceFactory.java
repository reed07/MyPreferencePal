package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.notification.PushNotificationManager;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.thirdparty.ThirdPartyService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesSignUpServiceFactory implements Factory<SignUpService> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<MfpInformationApi> apiProvider;
    private final Provider<AuthTokenProvider> authTokenProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<MeasurementsService> measurementsServiceProvider;
    private final ApplicationModule module;
    private final Provider<PushNotificationManager> pushNotificationManagerProvider;
    private final Provider<MfpV2Api> regionServiceApiProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SyncService> syncServiceProvider;
    private final Provider<ThirdPartyService> thirdPartyServiceProvider;

    public ApplicationModule_ProvidesSignUpServiceFactory(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<MeasurementsService> provider2, Provider<AnalyticsService> provider3, Provider<AuthTokenProvider> provider4, Provider<ThirdPartyService> provider5, Provider<SyncService> provider6, Provider<MfpInformationApi> provider7, Provider<MfpV2Api> provider8, Provider<Session> provider9, Provider<PushNotificationManager> provider10, Provider<DbConnectionManager> provider11) {
        this.module = applicationModule;
        this.localSettingsServiceProvider = provider;
        this.measurementsServiceProvider = provider2;
        this.analyticsServiceProvider = provider3;
        this.authTokenProvider = provider4;
        this.thirdPartyServiceProvider = provider5;
        this.syncServiceProvider = provider6;
        this.apiProvider = provider7;
        this.regionServiceApiProvider = provider8;
        this.sessionProvider = provider9;
        this.pushNotificationManagerProvider = provider10;
        this.dbConnectionManagerProvider = provider11;
    }

    public SignUpService get() {
        return provideInstance(this.module, this.localSettingsServiceProvider, this.measurementsServiceProvider, this.analyticsServiceProvider, this.authTokenProvider, this.thirdPartyServiceProvider, this.syncServiceProvider, this.apiProvider, this.regionServiceApiProvider, this.sessionProvider, this.pushNotificationManagerProvider, this.dbConnectionManagerProvider);
    }

    public static SignUpService provideInstance(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<MeasurementsService> provider2, Provider<AnalyticsService> provider3, Provider<AuthTokenProvider> provider4, Provider<ThirdPartyService> provider5, Provider<SyncService> provider6, Provider<MfpInformationApi> provider7, Provider<MfpV2Api> provider8, Provider<Session> provider9, Provider<PushNotificationManager> provider10, Provider<DbConnectionManager> provider11) {
        return proxyProvidesSignUpService(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), provider7, provider8, DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10), DoubleCheck.lazy(provider11));
    }

    public static ApplicationModule_ProvidesSignUpServiceFactory create(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<MeasurementsService> provider2, Provider<AnalyticsService> provider3, Provider<AuthTokenProvider> provider4, Provider<ThirdPartyService> provider5, Provider<SyncService> provider6, Provider<MfpInformationApi> provider7, Provider<MfpV2Api> provider8, Provider<Session> provider9, Provider<PushNotificationManager> provider10, Provider<DbConnectionManager> provider11) {
        ApplicationModule_ProvidesSignUpServiceFactory applicationModule_ProvidesSignUpServiceFactory = new ApplicationModule_ProvidesSignUpServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
        return applicationModule_ProvidesSignUpServiceFactory;
    }

    public static SignUpService proxyProvidesSignUpService(ApplicationModule applicationModule, Lazy<LocalSettingsService> lazy, Lazy<MeasurementsService> lazy2, Lazy<AnalyticsService> lazy3, Lazy<AuthTokenProvider> lazy4, Lazy<ThirdPartyService> lazy5, Lazy<SyncService> lazy6, Provider<MfpInformationApi> provider, Provider<MfpV2Api> provider2, Lazy<Session> lazy7, Lazy<PushNotificationManager> lazy8, Lazy<DbConnectionManager> lazy9) {
        return (SignUpService) Preconditions.checkNotNull(applicationModule.providesSignUpService(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, provider, provider2, lazy7, lazy8, lazy9), "Cannot return null from a non-@Nullable @Provides method");
    }
}
