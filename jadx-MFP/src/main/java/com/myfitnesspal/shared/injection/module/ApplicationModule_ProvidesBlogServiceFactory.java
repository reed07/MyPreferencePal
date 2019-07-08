package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.blog.service.BlogService;
import com.myfitnesspal.shared.service.install.CountryService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesBlogServiceFactory implements Factory<BlogService> {
    private final Provider<CountryService> countryServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesBlogServiceFactory(ApplicationModule applicationModule, Provider<CountryService> provider) {
        this.module = applicationModule;
        this.countryServiceProvider = provider;
    }

    public BlogService get() {
        return provideInstance(this.module, this.countryServiceProvider);
    }

    public static BlogService provideInstance(ApplicationModule applicationModule, Provider<CountryService> provider) {
        return proxyProvidesBlogService(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvidesBlogServiceFactory create(ApplicationModule applicationModule, Provider<CountryService> provider) {
        return new ApplicationModule_ProvidesBlogServiceFactory(applicationModule, provider);
    }

    public static BlogService proxyProvidesBlogService(ApplicationModule applicationModule, Lazy<CountryService> lazy) {
        return (BlogService) Preconditions.checkNotNull(applicationModule.providesBlogService(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
