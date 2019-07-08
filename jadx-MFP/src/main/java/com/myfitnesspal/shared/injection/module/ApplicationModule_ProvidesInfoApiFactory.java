package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.v1.ApiBinaryConstructorArgs;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesInfoApiFactory implements Factory<MfpInformationApi> {
    private final Provider<ApiBinaryConstructorArgs> argsProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesInfoApiFactory(ApplicationModule applicationModule, Provider<ApiBinaryConstructorArgs> provider) {
        this.module = applicationModule;
        this.argsProvider = provider;
    }

    public MfpInformationApi get() {
        return provideInstance(this.module, this.argsProvider);
    }

    public static MfpInformationApi provideInstance(ApplicationModule applicationModule, Provider<ApiBinaryConstructorArgs> provider) {
        return proxyProvidesInfoApi(applicationModule, (ApiBinaryConstructorArgs) provider.get());
    }

    public static ApplicationModule_ProvidesInfoApiFactory create(ApplicationModule applicationModule, Provider<ApiBinaryConstructorArgs> provider) {
        return new ApplicationModule_ProvidesInfoApiFactory(applicationModule, provider);
    }

    public static MfpInformationApi proxyProvidesInfoApi(ApplicationModule applicationModule, ApiBinaryConstructorArgs apiBinaryConstructorArgs) {
        return (MfpInformationApi) Preconditions.checkNotNull(applicationModule.providesInfoApi(apiBinaryConstructorArgs), "Cannot return null from a non-@Nullable @Provides method");
    }
}
