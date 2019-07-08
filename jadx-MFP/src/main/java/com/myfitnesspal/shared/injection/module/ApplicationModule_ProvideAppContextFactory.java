package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvideAppContextFactory implements Factory<Context> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideAppContextFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public Context get() {
        return provideInstance(this.module);
    }

    public static Context provideInstance(ApplicationModule applicationModule) {
        return proxyProvideAppContext(applicationModule);
    }

    public static ApplicationModule_ProvideAppContextFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideAppContextFactory(applicationModule);
    }

    public static Context proxyProvideAppContext(ApplicationModule applicationModule) {
        return (Context) Preconditions.checkNotNull(applicationModule.provideAppContext(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
