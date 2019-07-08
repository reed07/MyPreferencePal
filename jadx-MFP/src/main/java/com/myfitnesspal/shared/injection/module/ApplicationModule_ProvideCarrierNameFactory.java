package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideCarrierNameFactory implements Factory<String> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideCarrierNameFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public String get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static String provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvideCarrierName(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_ProvideCarrierNameFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvideCarrierNameFactory(applicationModule, provider);
    }

    public static String proxyProvideCarrierName(ApplicationModule applicationModule, Context context) {
        return (String) Preconditions.checkNotNull(applicationModule.provideCarrierName(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}
