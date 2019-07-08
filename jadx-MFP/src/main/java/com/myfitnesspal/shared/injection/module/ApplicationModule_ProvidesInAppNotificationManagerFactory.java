package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.notification.InAppNotificationManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesInAppNotificationManagerFactory implements Factory<InAppNotificationManager> {
    private final Provider<AppSettings> appSettingsProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesInAppNotificationManagerFactory(ApplicationModule applicationModule, Provider<AppSettings> provider) {
        this.module = applicationModule;
        this.appSettingsProvider = provider;
    }

    public InAppNotificationManager get() {
        return provideInstance(this.module, this.appSettingsProvider);
    }

    public static InAppNotificationManager provideInstance(ApplicationModule applicationModule, Provider<AppSettings> provider) {
        return proxyProvidesInAppNotificationManager(applicationModule, (AppSettings) provider.get());
    }

    public static ApplicationModule_ProvidesInAppNotificationManagerFactory create(ApplicationModule applicationModule, Provider<AppSettings> provider) {
        return new ApplicationModule_ProvidesInAppNotificationManagerFactory(applicationModule, provider);
    }

    public static InAppNotificationManager proxyProvidesInAppNotificationManager(ApplicationModule applicationModule, AppSettings appSettings) {
        return (InAppNotificationManager) Preconditions.checkNotNull(applicationModule.providesInAppNotificationManager(appSettings), "Cannot return null from a non-@Nullable @Provides method");
    }
}
