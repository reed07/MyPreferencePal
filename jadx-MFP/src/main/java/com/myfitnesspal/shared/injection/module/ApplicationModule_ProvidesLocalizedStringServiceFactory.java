package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.strings.GrammarService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesLocalizedStringServiceFactory implements Factory<GrammarService> {
    private final Provider<CountryService> countryServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesLocalizedStringServiceFactory(ApplicationModule applicationModule, Provider<CountryService> provider) {
        this.module = applicationModule;
        this.countryServiceProvider = provider;
    }

    public GrammarService get() {
        return provideInstance(this.module, this.countryServiceProvider);
    }

    public static GrammarService provideInstance(ApplicationModule applicationModule, Provider<CountryService> provider) {
        return proxyProvidesLocalizedStringService(applicationModule, (CountryService) provider.get());
    }

    public static ApplicationModule_ProvidesLocalizedStringServiceFactory create(ApplicationModule applicationModule, Provider<CountryService> provider) {
        return new ApplicationModule_ProvidesLocalizedStringServiceFactory(applicationModule, provider);
    }

    public static GrammarService proxyProvidesLocalizedStringService(ApplicationModule applicationModule, CountryService countryService) {
        return (GrammarService) Preconditions.checkNotNull(applicationModule.providesLocalizedStringService(countryService), "Cannot return null from a non-@Nullable @Provides method");
    }
}
