package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.uacf.UacfConfigurationUtil;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.uacf.configuration.sdk.UacfConfigurationSdk;
import javax.inject.Provider;

public final class ApplicationModule_ProvideUacfConfigurationUtilFactory implements Factory<UacfConfigurationUtil> {
    private final Provider<UacfConfigurationSdk> configurationSdkProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideUacfConfigurationUtilFactory(ApplicationModule applicationModule, Provider<UacfConfigurationSdk> provider) {
        this.module = applicationModule;
        this.configurationSdkProvider = provider;
    }

    public UacfConfigurationUtil get() {
        return provideInstance(this.module, this.configurationSdkProvider);
    }

    public static UacfConfigurationUtil provideInstance(ApplicationModule applicationModule, Provider<UacfConfigurationSdk> provider) {
        return proxyProvideUacfConfigurationUtil(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvideUacfConfigurationUtilFactory create(ApplicationModule applicationModule, Provider<UacfConfigurationSdk> provider) {
        return new ApplicationModule_ProvideUacfConfigurationUtilFactory(applicationModule, provider);
    }

    public static UacfConfigurationUtil proxyProvideUacfConfigurationUtil(ApplicationModule applicationModule, Lazy<UacfConfigurationSdk> lazy) {
        return (UacfConfigurationUtil) Preconditions.checkNotNull(applicationModule.provideUacfConfigurationUtil(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
