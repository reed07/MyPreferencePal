package com.myfitnesspal.shared.injection.module;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvideGsonFactory implements Factory<Gson> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideGsonFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public Gson get() {
        return provideInstance(this.module);
    }

    public static Gson provideInstance(ApplicationModule applicationModule) {
        return proxyProvideGson(applicationModule);
    }

    public static ApplicationModule_ProvideGsonFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideGsonFactory(applicationModule);
    }

    public static Gson proxyProvideGson(ApplicationModule applicationModule) {
        return (Gson) Preconditions.checkNotNull(applicationModule.provideGson(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
