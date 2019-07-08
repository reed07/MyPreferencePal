package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.premium.service.UpsellService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesUpsellServiceFactory implements Factory<UpsellService> {
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesUpsellServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<ConfigService> provider2, Provider<CountryService> provider3) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.configServiceProvider = provider2;
        this.countryServiceProvider = provider3;
    }

    public UpsellService get() {
        return provideInstance(this.module, this.contextProvider, this.configServiceProvider, this.countryServiceProvider);
    }

    public static UpsellService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<ConfigService> provider2, Provider<CountryService> provider3) {
        return proxyProvidesUpsellService(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static ApplicationModule_ProvidesUpsellServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<ConfigService> provider2, Provider<CountryService> provider3) {
        return new ApplicationModule_ProvidesUpsellServiceFactory(applicationModule, provider, provider2, provider3);
    }

    public static UpsellService proxyProvidesUpsellService(ApplicationModule applicationModule, Context context, Lazy<ConfigService> lazy, Lazy<CountryService> lazy2) {
        return (UpsellService) Preconditions.checkNotNull(applicationModule.providesUpsellService(context, lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
