package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsTaskQueue;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesAnalyticsTaskQueueFactory implements Factory<MfpAnalyticsTaskQueue> {
    private final ApplicationModule module;
    private final Provider<SharedPreferences> prefsProvider;

    public ApplicationModule_ProvidesAnalyticsTaskQueueFactory(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        this.module = applicationModule;
        this.prefsProvider = provider;
    }

    public MfpAnalyticsTaskQueue get() {
        return provideInstance(this.module, this.prefsProvider);
    }

    public static MfpAnalyticsTaskQueue provideInstance(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return proxyProvidesAnalyticsTaskQueue(applicationModule, (SharedPreferences) provider.get());
    }

    public static ApplicationModule_ProvidesAnalyticsTaskQueueFactory create(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return new ApplicationModule_ProvidesAnalyticsTaskQueueFactory(applicationModule, provider);
    }

    public static MfpAnalyticsTaskQueue proxyProvidesAnalyticsTaskQueue(ApplicationModule applicationModule, SharedPreferences sharedPreferences) {
        return (MfpAnalyticsTaskQueue) Preconditions.checkNotNull(applicationModule.providesAnalyticsTaskQueue(sharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}
