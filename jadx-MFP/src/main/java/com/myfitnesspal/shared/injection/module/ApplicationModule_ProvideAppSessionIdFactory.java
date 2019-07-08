package com.myfitnesspal.shared.injection.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvideAppSessionIdFactory implements Factory<String> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideAppSessionIdFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public String get() {
        return provideInstance(this.module);
    }

    public static String provideInstance(ApplicationModule applicationModule) {
        return proxyProvideAppSessionId(applicationModule);
    }

    public static ApplicationModule_ProvideAppSessionIdFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideAppSessionIdFactory(applicationModule);
    }

    public static String proxyProvideAppSessionId(ApplicationModule applicationModule) {
        return (String) Preconditions.checkNotNull(applicationModule.provideAppSessionId(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
