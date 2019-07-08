package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.v1.ApiBinaryConstructorArgs;
import com.myfitnesspal.shared.api.v1.MfpActionApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesActionApiFactory implements Factory<MfpActionApi> {
    private final Provider<ApiBinaryConstructorArgs> argsProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesActionApiFactory(ApplicationModule applicationModule, Provider<ApiBinaryConstructorArgs> provider) {
        this.module = applicationModule;
        this.argsProvider = provider;
    }

    public MfpActionApi get() {
        return provideInstance(this.module, this.argsProvider);
    }

    public static MfpActionApi provideInstance(ApplicationModule applicationModule, Provider<ApiBinaryConstructorArgs> provider) {
        return proxyProvidesActionApi(applicationModule, (ApiBinaryConstructorArgs) provider.get());
    }

    public static ApplicationModule_ProvidesActionApiFactory create(ApplicationModule applicationModule, Provider<ApiBinaryConstructorArgs> provider) {
        return new ApplicationModule_ProvidesActionApiFactory(applicationModule, provider);
    }

    public static MfpActionApi proxyProvidesActionApi(ApplicationModule applicationModule, ApiBinaryConstructorArgs apiBinaryConstructorArgs) {
        return (MfpActionApi) Preconditions.checkNotNull(applicationModule.providesActionApi(apiBinaryConstructorArgs), "Cannot return null from a non-@Nullable @Provides method");
    }
}
