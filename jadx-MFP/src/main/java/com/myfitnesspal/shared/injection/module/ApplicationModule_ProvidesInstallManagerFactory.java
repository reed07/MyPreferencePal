package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.registration.service.InstallManager;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesInstallManagerFactory implements Factory<InstallManager> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesInstallManagerFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<AnalyticsService> provider2, Provider<AppSettings> provider3) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.analyticsServiceProvider = provider2;
        this.appSettingsProvider = provider3;
    }

    public InstallManager get() {
        return provideInstance(this.module, this.contextProvider, this.analyticsServiceProvider, this.appSettingsProvider);
    }

    public static InstallManager provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<AnalyticsService> provider2, Provider<AppSettings> provider3) {
        return proxyProvidesInstallManager(applicationModule, (Context) provider.get(), (AnalyticsService) provider2.get(), (AppSettings) provider3.get());
    }

    public static ApplicationModule_ProvidesInstallManagerFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<AnalyticsService> provider2, Provider<AppSettings> provider3) {
        return new ApplicationModule_ProvidesInstallManagerFactory(applicationModule, provider, provider2, provider3);
    }

    public static InstallManager proxyProvidesInstallManager(ApplicationModule applicationModule, Context context, AnalyticsService analyticsService, AppSettings appSettings) {
        return (InstallManager) Preconditions.checkNotNull(applicationModule.providesInstallManager(context, analyticsService, appSettings), "Cannot return null from a non-@Nullable @Provides method");
    }
}
