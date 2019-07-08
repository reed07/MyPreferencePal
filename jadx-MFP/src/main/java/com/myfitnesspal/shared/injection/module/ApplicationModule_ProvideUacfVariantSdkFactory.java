package com.myfitnesspal.shared.injection.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.uacf.rollouts.sdk.UacfVariantSdk;

public final class ApplicationModule_ProvideUacfVariantSdkFactory implements Factory<UacfVariantSdk> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideUacfVariantSdkFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public UacfVariantSdk get() {
        return provideInstance(this.module);
    }

    public static UacfVariantSdk provideInstance(ApplicationModule applicationModule) {
        return proxyProvideUacfVariantSdk(applicationModule);
    }

    public static ApplicationModule_ProvideUacfVariantSdkFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideUacfVariantSdkFactory(applicationModule);
    }

    public static UacfVariantSdk proxyProvideUacfVariantSdk(ApplicationModule applicationModule) {
        return (UacfVariantSdk) Preconditions.checkNotNull(applicationModule.provideUacfVariantSdk(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
