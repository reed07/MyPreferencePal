package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.notification.MfpNotificationHandler;
import com.myfitnesspal.shared.notification.PushNotificationManager;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesPushNotificationManagerFactory implements Factory<PushNotificationManager> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ApiUrlProvider> apiUrlProvider;
    private final Provider<Context> contextProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<MfpNotificationHandler> mfpNotificationHandlerProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<SyncService> syncServiceProvider;

    public ApplicationModule_ProvidesPushNotificationManagerFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<ApiUrlProvider> provider2, Provider<MfpNotificationHandler> provider3, Provider<SyncService> provider4, Provider<AnalyticsService> provider5, Provider<GlobalSettingsService> provider6, Provider<Session> provider7) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.apiUrlProvider = provider2;
        this.mfpNotificationHandlerProvider = provider3;
        this.syncServiceProvider = provider4;
        this.analyticsServiceProvider = provider5;
        this.globalSettingsServiceProvider = provider6;
        this.sessionProvider = provider7;
    }

    public PushNotificationManager get() {
        return provideInstance(this.module, this.contextProvider, this.apiUrlProvider, this.mfpNotificationHandlerProvider, this.syncServiceProvider, this.analyticsServiceProvider, this.globalSettingsServiceProvider, this.sessionProvider);
    }

    public static PushNotificationManager provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<ApiUrlProvider> provider2, Provider<MfpNotificationHandler> provider3, Provider<SyncService> provider4, Provider<AnalyticsService> provider5, Provider<GlobalSettingsService> provider6, Provider<Session> provider7) {
        return proxyProvidesPushNotificationManager(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7));
    }

    public static ApplicationModule_ProvidesPushNotificationManagerFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<ApiUrlProvider> provider2, Provider<MfpNotificationHandler> provider3, Provider<SyncService> provider4, Provider<AnalyticsService> provider5, Provider<GlobalSettingsService> provider6, Provider<Session> provider7) {
        ApplicationModule_ProvidesPushNotificationManagerFactory applicationModule_ProvidesPushNotificationManagerFactory = new ApplicationModule_ProvidesPushNotificationManagerFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return applicationModule_ProvidesPushNotificationManagerFactory;
    }

    public static PushNotificationManager proxyProvidesPushNotificationManager(ApplicationModule applicationModule, Context context, Lazy<ApiUrlProvider> lazy, Lazy<MfpNotificationHandler> lazy2, Lazy<SyncService> lazy3, Lazy<AnalyticsService> lazy4, Lazy<GlobalSettingsService> lazy5, Lazy<Session> lazy6) {
        return (PushNotificationManager) Preconditions.checkNotNull(applicationModule.providesPushNotificationManager(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6), "Cannot return null from a non-@Nullable @Provides method");
    }
}
