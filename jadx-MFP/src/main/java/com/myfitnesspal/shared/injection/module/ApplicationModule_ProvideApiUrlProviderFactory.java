package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.service.install.CountryService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideApiUrlProviderFactory implements Factory<ApiUrlProvider> {
    private final Provider<AuthTokenProvider> authTokenProvider;
    private final Provider<String> clientIdProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<String> guestAccessTokenProvider;
    private final Provider<MfpApiSettings> mfpApiSettingsProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideApiUrlProviderFactory(ApplicationModule applicationModule, Provider<MfpApiSettings> provider, Provider<CountryService> provider2, Provider<AuthTokenProvider> provider3, Provider<String> provider4, Provider<String> provider5) {
        this.module = applicationModule;
        this.mfpApiSettingsProvider = provider;
        this.countryServiceProvider = provider2;
        this.authTokenProvider = provider3;
        this.clientIdProvider = provider4;
        this.guestAccessTokenProvider = provider5;
    }

    public ApiUrlProvider get() {
        return provideInstance(this.module, this.mfpApiSettingsProvider, this.countryServiceProvider, this.authTokenProvider, this.clientIdProvider, this.guestAccessTokenProvider);
    }

    public static ApiUrlProvider provideInstance(ApplicationModule applicationModule, Provider<MfpApiSettings> provider, Provider<CountryService> provider2, Provider<AuthTokenProvider> provider3, Provider<String> provider4, Provider<String> provider5) {
        return proxyProvideApiUrlProvider(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), (String) provider4.get(), (String) provider5.get());
    }

    public static ApplicationModule_ProvideApiUrlProviderFactory create(ApplicationModule applicationModule, Provider<MfpApiSettings> provider, Provider<CountryService> provider2, Provider<AuthTokenProvider> provider3, Provider<String> provider4, Provider<String> provider5) {
        ApplicationModule_ProvideApiUrlProviderFactory applicationModule_ProvideApiUrlProviderFactory = new ApplicationModule_ProvideApiUrlProviderFactory(applicationModule, provider, provider2, provider3, provider4, provider5);
        return applicationModule_ProvideApiUrlProviderFactory;
    }

    public static ApiUrlProvider proxyProvideApiUrlProvider(ApplicationModule applicationModule, Lazy<MfpApiSettings> lazy, Lazy<CountryService> lazy2, Lazy<AuthTokenProvider> lazy3, String str, String str2) {
        return (ApiUrlProvider) Preconditions.checkNotNull(applicationModule.provideApiUrlProvider(lazy, lazy2, lazy3, str, str2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
