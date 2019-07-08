package com.myfitnesspal.shared.injection.module;

import android.app.Application;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvideApplicationFactory implements Factory<Application> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideApplicationFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public Application get() {
        return provideInstance(this.module);
    }

    public static Application provideInstance(ApplicationModule applicationModule) {
        return proxyProvideApplication(applicationModule);
    }

    public static ApplicationModule_ProvideApplicationFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideApplicationFactory(applicationModule);
    }

    public static Application proxyProvideApplication(ApplicationModule applicationModule) {
        return (Application) Preconditions.checkNotNull(applicationModule.provideApplication(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
