package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.onboarding.navigation.Navigator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvideNavigatorFactory implements Factory<Navigator> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideNavigatorFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public Navigator get() {
        return provideInstance(this.module);
    }

    public static Navigator provideInstance(ApplicationModule applicationModule) {
        return proxyProvideNavigator(applicationModule);
    }

    public static ApplicationModule_ProvideNavigatorFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideNavigatorFactory(applicationModule);
    }

    public static Navigator proxyProvideNavigator(ApplicationModule applicationModule) {
        return (Navigator) Preconditions.checkNotNull(applicationModule.provideNavigator(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
