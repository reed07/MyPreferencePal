package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.util.ResourceUtils;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesCountryServiceFactory implements Factory<CountryService> {
    private final Provider<Context> contextProvider;
    private final Provider<GlobalSettingsService> globalSettingsProvider;
    private final ApplicationModule module;
    private final Provider<ResourceUtils> resourceUtilsProvider;

    public ApplicationModule_ProvidesCountryServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<ResourceUtils> provider2, Provider<GlobalSettingsService> provider3) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.resourceUtilsProvider = provider2;
        this.globalSettingsProvider = provider3;
    }

    public CountryService get() {
        return provideInstance(this.module, this.contextProvider, this.resourceUtilsProvider, this.globalSettingsProvider);
    }

    public static CountryService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<ResourceUtils> provider2, Provider<GlobalSettingsService> provider3) {
        return proxyProvidesCountryService(applicationModule, (Context) provider.get(), (ResourceUtils) provider2.get(), DoubleCheck.lazy(provider3));
    }

    public static ApplicationModule_ProvidesCountryServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<ResourceUtils> provider2, Provider<GlobalSettingsService> provider3) {
        return new ApplicationModule_ProvidesCountryServiceFactory(applicationModule, provider, provider2, provider3);
    }

    public static CountryService proxyProvidesCountryService(ApplicationModule applicationModule, Context context, ResourceUtils resourceUtils, Lazy<GlobalSettingsService> lazy) {
        return (CountryService) Preconditions.checkNotNull(applicationModule.providesCountryService(context, resourceUtils, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
