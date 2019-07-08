package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.framework.mvvm.ViewModelCache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidesViewModelCacheFactory implements Factory<ViewModelCache> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesViewModelCacheFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public ViewModelCache get() {
        return provideInstance(this.module);
    }

    public static ViewModelCache provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesViewModelCache(applicationModule);
    }

    public static ApplicationModule_ProvidesViewModelCacheFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesViewModelCacheFactory(applicationModule);
    }

    public static ViewModelCache proxyProvidesViewModelCache(ApplicationModule applicationModule) {
        return (ViewModelCache) Preconditions.checkNotNull(applicationModule.providesViewModelCache(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
