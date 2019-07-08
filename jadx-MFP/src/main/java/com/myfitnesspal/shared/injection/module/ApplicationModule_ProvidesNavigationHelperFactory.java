package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidesNavigationHelperFactory implements Factory<NavigationHelper> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesNavigationHelperFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public NavigationHelper get() {
        return provideInstance(this.module);
    }

    public static NavigationHelper provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesNavigationHelper(applicationModule);
    }

    public static ApplicationModule_ProvidesNavigationHelperFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesNavigationHelperFactory(applicationModule);
    }

    public static NavigationHelper proxyProvidesNavigationHelper(ApplicationModule applicationModule) {
        return (NavigationHelper) Preconditions.checkNotNull(applicationModule.providesNavigationHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
