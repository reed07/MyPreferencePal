package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.v1.ApiBinaryConstructorArgs;
import com.myfitnesspal.shared.api.v1.MfpSearchApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesSearchApiFactory implements Factory<MfpSearchApi> {
    private final Provider<ApiBinaryConstructorArgs> argsProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesSearchApiFactory(ApplicationModule applicationModule, Provider<ApiBinaryConstructorArgs> provider) {
        this.module = applicationModule;
        this.argsProvider = provider;
    }

    public MfpSearchApi get() {
        return provideInstance(this.module, this.argsProvider);
    }

    public static MfpSearchApi provideInstance(ApplicationModule applicationModule, Provider<ApiBinaryConstructorArgs> provider) {
        return proxyProvidesSearchApi(applicationModule, (ApiBinaryConstructorArgs) provider.get());
    }

    public static ApplicationModule_ProvidesSearchApiFactory create(ApplicationModule applicationModule, Provider<ApiBinaryConstructorArgs> provider) {
        return new ApplicationModule_ProvidesSearchApiFactory(applicationModule, provider);
    }

    public static MfpSearchApi proxyProvidesSearchApi(ApplicationModule applicationModule, ApiBinaryConstructorArgs apiBinaryConstructorArgs) {
        return (MfpSearchApi) Preconditions.checkNotNull(applicationModule.providesSearchApi(apiBinaryConstructorArgs), "Cannot return null from a non-@Nullable @Provides method");
    }
}
