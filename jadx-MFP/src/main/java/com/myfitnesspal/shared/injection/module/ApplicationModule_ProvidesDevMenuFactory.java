package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.debug.ui.activity.PremiumDebugActivity;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidesDevMenuFactory implements Factory<PremiumDebugActivity> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesDevMenuFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public PremiumDebugActivity get() {
        return provideInstance(this.module);
    }

    public static PremiumDebugActivity provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesDevMenu(applicationModule);
    }

    public static ApplicationModule_ProvidesDevMenuFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesDevMenuFactory(applicationModule);
    }

    public static PremiumDebugActivity proxyProvidesDevMenu(ApplicationModule applicationModule) {
        return (PremiumDebugActivity) Preconditions.checkNotNull(applicationModule.providesDevMenu(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
