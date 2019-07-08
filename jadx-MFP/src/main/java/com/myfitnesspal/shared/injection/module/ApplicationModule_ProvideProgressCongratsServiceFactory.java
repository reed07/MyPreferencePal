package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.progress.service.ProgressCongratsService;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.uacf.core.preferences.KeyedSharedPreferences;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideProgressCongratsServiceFactory implements Factory<ProgressCongratsService> {
    private final ApplicationModule module;
    private final Provider<KeyedSharedPreferences> prefsProvider;
    private final Provider<UserHeightService> userHeightServiceProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public ApplicationModule_ProvideProgressCongratsServiceFactory(ApplicationModule applicationModule, Provider<UserWeightService> provider, Provider<UserHeightService> provider2, Provider<KeyedSharedPreferences> provider3) {
        this.module = applicationModule;
        this.userWeightServiceProvider = provider;
        this.userHeightServiceProvider = provider2;
        this.prefsProvider = provider3;
    }

    public ProgressCongratsService get() {
        return provideInstance(this.module, this.userWeightServiceProvider, this.userHeightServiceProvider, this.prefsProvider);
    }

    public static ProgressCongratsService provideInstance(ApplicationModule applicationModule, Provider<UserWeightService> provider, Provider<UserHeightService> provider2, Provider<KeyedSharedPreferences> provider3) {
        return proxyProvideProgressCongratsService(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), (KeyedSharedPreferences) provider3.get());
    }

    public static ApplicationModule_ProvideProgressCongratsServiceFactory create(ApplicationModule applicationModule, Provider<UserWeightService> provider, Provider<UserHeightService> provider2, Provider<KeyedSharedPreferences> provider3) {
        return new ApplicationModule_ProvideProgressCongratsServiceFactory(applicationModule, provider, provider2, provider3);
    }

    public static ProgressCongratsService proxyProvideProgressCongratsService(ApplicationModule applicationModule, Lazy<UserWeightService> lazy, Lazy<UserHeightService> lazy2, KeyedSharedPreferences keyedSharedPreferences) {
        return (ProgressCongratsService) Preconditions.checkNotNull(applicationModule.provideProgressCongratsService(lazy, lazy2, keyedSharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}
