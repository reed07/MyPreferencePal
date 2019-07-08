package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.v1.ApiBinaryConstructorArgs;
import com.myfitnesspal.shared.api.v1.MfpSyncApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesSyncApiFactory implements Factory<MfpSyncApi> {
    private final Provider<ApiBinaryConstructorArgs> argsProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesSyncApiFactory(ApplicationModule applicationModule, Provider<ApiBinaryConstructorArgs> provider) {
        this.module = applicationModule;
        this.argsProvider = provider;
    }

    public MfpSyncApi get() {
        return provideInstance(this.module, this.argsProvider);
    }

    public static MfpSyncApi provideInstance(ApplicationModule applicationModule, Provider<ApiBinaryConstructorArgs> provider) {
        return proxyProvidesSyncApi(applicationModule, (ApiBinaryConstructorArgs) provider.get());
    }

    public static ApplicationModule_ProvidesSyncApiFactory create(ApplicationModule applicationModule, Provider<ApiBinaryConstructorArgs> provider) {
        return new ApplicationModule_ProvidesSyncApiFactory(applicationModule, provider);
    }

    public static MfpSyncApi proxyProvidesSyncApi(ApplicationModule applicationModule, ApiBinaryConstructorArgs apiBinaryConstructorArgs) {
        return (MfpSyncApi) Preconditions.checkNotNull(applicationModule.providesSyncApi(apiBinaryConstructorArgs), "Cannot return null from a non-@Nullable @Provides method");
    }
}
