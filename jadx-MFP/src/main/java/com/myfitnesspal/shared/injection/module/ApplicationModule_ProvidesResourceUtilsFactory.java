package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.util.ResourceUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidesResourceUtilsFactory implements Factory<ResourceUtils> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesResourceUtilsFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public ResourceUtils get() {
        return provideInstance(this.module);
    }

    public static ResourceUtils provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesResourceUtils(applicationModule);
    }

    public static ApplicationModule_ProvidesResourceUtilsFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesResourceUtilsFactory(applicationModule);
    }

    public static ResourceUtils proxyProvidesResourceUtils(ApplicationModule applicationModule) {
        return (ResourceUtils) Preconditions.checkNotNull(applicationModule.providesResourceUtils(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
