package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.settings.api.TroubleShootingApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;

public final class ApplicationModule_ProvideTroubleshootingApiFactory implements Factory<TroubleShootingApi> {
    private final ApplicationModule module;
    private final Provider<Retrofit> retrofitProvider;

    public ApplicationModule_ProvideTroubleshootingApiFactory(ApplicationModule applicationModule, Provider<Retrofit> provider) {
        this.module = applicationModule;
        this.retrofitProvider = provider;
    }

    public TroubleShootingApi get() {
        return provideInstance(this.module, this.retrofitProvider);
    }

    public static TroubleShootingApi provideInstance(ApplicationModule applicationModule, Provider<Retrofit> provider) {
        return proxyProvideTroubleshootingApi(applicationModule, (Retrofit) provider.get());
    }

    public static ApplicationModule_ProvideTroubleshootingApiFactory create(ApplicationModule applicationModule, Provider<Retrofit> provider) {
        return new ApplicationModule_ProvideTroubleshootingApiFactory(applicationModule, provider);
    }

    public static TroubleShootingApi proxyProvideTroubleshootingApi(ApplicationModule applicationModule, Retrofit retrofit) {
        return (TroubleShootingApi) Preconditions.checkNotNull(applicationModule.provideTroubleshootingApi(retrofit), "Cannot return null from a non-@Nullable @Provides method");
    }
}
