package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ApplicationModule_ProvideAppVersionCodeFactory implements Factory<Long> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideAppVersionCodeFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public Long get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static Long provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return Long.valueOf(proxyProvideAppVersionCode(applicationModule, (Context) provider.get()));
    }

    public static ApplicationModule_ProvideAppVersionCodeFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvideAppVersionCodeFactory(applicationModule, provider);
    }

    public static long proxyProvideAppVersionCode(ApplicationModule applicationModule, Context context) {
        return applicationModule.provideAppVersionCode(context);
    }
}
