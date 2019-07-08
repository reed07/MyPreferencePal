package com.myfitnesspal.shared.injection.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.uacf.configuration.sdk.UacfConfigurationSdk;

public final class ApplicationModule_ProvideUacfConfigurationSdkFactory implements Factory<UacfConfigurationSdk> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideUacfConfigurationSdkFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public UacfConfigurationSdk get() {
        return provideInstance(this.module);
    }

    public static UacfConfigurationSdk provideInstance(ApplicationModule applicationModule) {
        return proxyProvideUacfConfigurationSdk(applicationModule);
    }

    public static ApplicationModule_ProvideUacfConfigurationSdkFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideUacfConfigurationSdkFactory(applicationModule);
    }

    public static UacfConfigurationSdk proxyProvideUacfConfigurationSdk(ApplicationModule applicationModule) {
        return (UacfConfigurationSdk) Preconditions.checkNotNull(applicationModule.provideUacfConfigurationSdk(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
