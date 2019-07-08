package com.myfitnesspal.shared.injection.module;

import android.os.Handler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvideMainHandlerFactory implements Factory<Handler> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideMainHandlerFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public Handler get() {
        return provideInstance(this.module);
    }

    public static Handler provideInstance(ApplicationModule applicationModule) {
        return proxyProvideMainHandler(applicationModule);
    }

    public static ApplicationModule_ProvideMainHandlerFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideMainHandlerFactory(applicationModule);
    }

    public static Handler proxyProvideMainHandler(ApplicationModule applicationModule) {
        return (Handler) Preconditions.checkNotNull(applicationModule.provideMainHandler(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
