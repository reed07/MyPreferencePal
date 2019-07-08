package com.myfitnesspal.shared.notification;

import com.myfitnesspal.feature.settings.model.AppSettings;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class InAppNotificationManagerImpl_Factory implements Factory<InAppNotificationManagerImpl> {
    private final Provider<AppSettings> appSettingsProvider;

    public InAppNotificationManagerImpl_Factory(Provider<AppSettings> provider) {
        this.appSettingsProvider = provider;
    }

    public InAppNotificationManagerImpl get() {
        return provideInstance(this.appSettingsProvider);
    }

    public static InAppNotificationManagerImpl provideInstance(Provider<AppSettings> provider) {
        return new InAppNotificationManagerImpl((AppSettings) provider.get());
    }

    public static InAppNotificationManagerImpl_Factory create(Provider<AppSettings> provider) {
        return new InAppNotificationManagerImpl_Factory(provider);
    }

    public static InAppNotificationManagerImpl newInAppNotificationManagerImpl(AppSettings appSettings) {
        return new InAppNotificationManagerImpl(appSettings);
    }
}
