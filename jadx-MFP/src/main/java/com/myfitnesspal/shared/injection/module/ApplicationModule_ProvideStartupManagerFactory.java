package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.consents.service.ConsentsService;
import com.myfitnesspal.feature.home.service.AppRatingService;
import com.myfitnesspal.feature.registration.service.InstallManager;
import com.myfitnesspal.feature.registration.util.StartupManager;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.notification.PushNotificationManager;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.syncv2.SyncUtil;
import com.myfitnesspal.shared.uacf.UacfEmailVerificationManager;
import com.myfitnesspal.shared.util.GlobalAppPreferenceMigrationUtil;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideStartupManagerFactory implements Factory<StartupManager> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<AppRatingService> appRatingServiceProvider;
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<ConsentsService> consentsServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<UacfEmailVerificationManager> emailVerificationManagerProvider;
    private final Provider<GlobalAppPreferenceMigrationUtil> globalAppPreferenceMigrationUtilProvider;
    private final Provider<InstallManager> installManagerProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LocationService> locationServiceProvider;
    private final ApplicationModule module;
    private final Provider<PushNotificationManager> pushNotificationManagerProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<StepService> stepServiceProvider;
    private final Provider<UacfScheduler<SyncType>> syncSchedulerProvider;
    private final Provider<SyncUtil> syncUtilProvider;
    private final Provider<MfpAnalyticsService> v2AnalyticsProvider;

    public ApplicationModule_ProvideStartupManagerFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<ConfigService> provider2, Provider<AnalyticsService> provider3, Provider<MfpAnalyticsService> provider4, Provider<InstallManager> provider5, Provider<Session> provider6, Provider<LocalSettingsService> provider7, Provider<SyncUtil> provider8, Provider<UacfScheduler<SyncType>> provider9, Provider<StepService> provider10, Provider<LocationService> provider11, Provider<PushNotificationManager> provider12, Provider<GlobalAppPreferenceMigrationUtil> provider13, Provider<AppSettings> provider14, Provider<AppRatingService> provider15, Provider<DbConnectionManager> provider16, Provider<CountryService> provider17, Provider<ConsentsService> provider18, Provider<UacfEmailVerificationManager> provider19) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.configServiceProvider = provider2;
        this.analyticsServiceProvider = provider3;
        this.v2AnalyticsProvider = provider4;
        this.installManagerProvider = provider5;
        this.sessionProvider = provider6;
        this.localSettingsServiceProvider = provider7;
        this.syncUtilProvider = provider8;
        this.syncSchedulerProvider = provider9;
        this.stepServiceProvider = provider10;
        this.locationServiceProvider = provider11;
        this.pushNotificationManagerProvider = provider12;
        this.globalAppPreferenceMigrationUtilProvider = provider13;
        this.appSettingsProvider = provider14;
        this.appRatingServiceProvider = provider15;
        this.dbConnectionManagerProvider = provider16;
        this.countryServiceProvider = provider17;
        this.consentsServiceProvider = provider18;
        this.emailVerificationManagerProvider = provider19;
    }

    public StartupManager get() {
        ApplicationModule applicationModule = this.module;
        return provideInstance(applicationModule, this.contextProvider, this.configServiceProvider, this.analyticsServiceProvider, this.v2AnalyticsProvider, this.installManagerProvider, this.sessionProvider, this.localSettingsServiceProvider, this.syncUtilProvider, this.syncSchedulerProvider, this.stepServiceProvider, this.locationServiceProvider, this.pushNotificationManagerProvider, this.globalAppPreferenceMigrationUtilProvider, this.appSettingsProvider, this.appRatingServiceProvider, this.dbConnectionManagerProvider, this.countryServiceProvider, this.consentsServiceProvider, this.emailVerificationManagerProvider);
    }

    public static StartupManager provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<ConfigService> provider2, Provider<AnalyticsService> provider3, Provider<MfpAnalyticsService> provider4, Provider<InstallManager> provider5, Provider<Session> provider6, Provider<LocalSettingsService> provider7, Provider<SyncUtil> provider8, Provider<UacfScheduler<SyncType>> provider9, Provider<StepService> provider10, Provider<LocationService> provider11, Provider<PushNotificationManager> provider12, Provider<GlobalAppPreferenceMigrationUtil> provider13, Provider<AppSettings> provider14, Provider<AppRatingService> provider15, Provider<DbConnectionManager> provider16, Provider<CountryService> provider17, Provider<ConsentsService> provider18, Provider<UacfEmailVerificationManager> provider19) {
        return proxyProvideStartupManager(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10), DoubleCheck.lazy(provider11), DoubleCheck.lazy(provider12), DoubleCheck.lazy(provider13), DoubleCheck.lazy(provider14), DoubleCheck.lazy(provider15), DoubleCheck.lazy(provider16), DoubleCheck.lazy(provider17), DoubleCheck.lazy(provider18), DoubleCheck.lazy(provider19));
    }

    public static ApplicationModule_ProvideStartupManagerFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<ConfigService> provider2, Provider<AnalyticsService> provider3, Provider<MfpAnalyticsService> provider4, Provider<InstallManager> provider5, Provider<Session> provider6, Provider<LocalSettingsService> provider7, Provider<SyncUtil> provider8, Provider<UacfScheduler<SyncType>> provider9, Provider<StepService> provider10, Provider<LocationService> provider11, Provider<PushNotificationManager> provider12, Provider<GlobalAppPreferenceMigrationUtil> provider13, Provider<AppSettings> provider14, Provider<AppRatingService> provider15, Provider<DbConnectionManager> provider16, Provider<CountryService> provider17, Provider<ConsentsService> provider18, Provider<UacfEmailVerificationManager> provider19) {
        ApplicationModule_ProvideStartupManagerFactory applicationModule_ProvideStartupManagerFactory = new ApplicationModule_ProvideStartupManagerFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19);
        return applicationModule_ProvideStartupManagerFactory;
    }

    public static StartupManager proxyProvideStartupManager(ApplicationModule applicationModule, Context context, Lazy<ConfigService> lazy, Lazy<AnalyticsService> lazy2, Lazy<MfpAnalyticsService> lazy3, Lazy<InstallManager> lazy4, Lazy<Session> lazy5, Lazy<LocalSettingsService> lazy6, Lazy<SyncUtil> lazy7, Lazy<UacfScheduler<SyncType>> lazy8, Lazy<StepService> lazy9, Lazy<LocationService> lazy10, Lazy<PushNotificationManager> lazy11, Lazy<GlobalAppPreferenceMigrationUtil> lazy12, Lazy<AppSettings> lazy13, Lazy<AppRatingService> lazy14, Lazy<DbConnectionManager> lazy15, Lazy<CountryService> lazy16, Lazy<ConsentsService> lazy17, Lazy<UacfEmailVerificationManager> lazy18) {
        return (StartupManager) Preconditions.checkNotNull(applicationModule.provideStartupManager(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13, lazy14, lazy15, lazy16, lazy17, lazy18), "Cannot return null from a non-@Nullable @Provides method");
    }
}
