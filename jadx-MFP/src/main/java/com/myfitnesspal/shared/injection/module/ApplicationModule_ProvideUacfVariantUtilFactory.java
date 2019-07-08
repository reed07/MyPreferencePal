package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.uacf.UacfRolloutUtil;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.uacf.rollouts.sdk.UacfVariantSdk;
import javax.inject.Provider;

public final class ApplicationModule_ProvideUacfVariantUtilFactory implements Factory<UacfRolloutUtil> {
    private final ApplicationModule module;
    private final Provider<UacfVariantSdk> variantSdkProvider;

    public ApplicationModule_ProvideUacfVariantUtilFactory(ApplicationModule applicationModule, Provider<UacfVariantSdk> provider) {
        this.module = applicationModule;
        this.variantSdkProvider = provider;
    }

    public UacfRolloutUtil get() {
        return provideInstance(this.module, this.variantSdkProvider);
    }

    public static UacfRolloutUtil provideInstance(ApplicationModule applicationModule, Provider<UacfVariantSdk> provider) {
        return proxyProvideUacfVariantUtil(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvideUacfVariantUtilFactory create(ApplicationModule applicationModule, Provider<UacfVariantSdk> provider) {
        return new ApplicationModule_ProvideUacfVariantUtilFactory(applicationModule, provider);
    }

    public static UacfRolloutUtil proxyProvideUacfVariantUtil(ApplicationModule applicationModule, Lazy<UacfVariantSdk> lazy) {
        return (UacfRolloutUtil) Preconditions.checkNotNull(applicationModule.provideUacfVariantUtil(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
