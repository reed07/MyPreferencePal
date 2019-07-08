package com.myfitnesspal.shared.injection.module;

import com.uacf.identity.sdk.UacfIdentitySdk;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvideUacfIdentitySdkFactory implements Factory<UacfIdentitySdk> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideUacfIdentitySdkFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public UacfIdentitySdk get() {
        return provideInstance(this.module);
    }

    public static UacfIdentitySdk provideInstance(ApplicationModule applicationModule) {
        return proxyProvideUacfIdentitySdk(applicationModule);
    }

    public static ApplicationModule_ProvideUacfIdentitySdkFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideUacfIdentitySdkFactory(applicationModule);
    }

    public static UacfIdentitySdk proxyProvideUacfIdentitySdk(ApplicationModule applicationModule) {
        return (UacfIdentitySdk) Preconditions.checkNotNull(applicationModule.provideUacfIdentitySdk(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
