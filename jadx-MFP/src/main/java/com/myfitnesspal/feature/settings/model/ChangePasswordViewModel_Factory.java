package com.myfitnesspal.feature.settings.model;

import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ChangePasswordViewModel_Factory implements Factory<ChangePasswordViewModel> {
    private final Provider<MfpInformationApi> apiProvider;
    private final Provider<AuthTokenProvider> authTokensProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<Session> sessionProvider;

    public ChangePasswordViewModel_Factory(Provider<CountryService> provider, Provider<MfpInformationApi> provider2, Provider<AuthTokenProvider> provider3, Provider<Session> provider4, Provider<LocalSettingsService> provider5) {
        this.countryServiceProvider = provider;
        this.apiProvider = provider2;
        this.authTokensProvider = provider3;
        this.sessionProvider = provider4;
        this.localSettingsServiceProvider = provider5;
    }

    public ChangePasswordViewModel get() {
        return provideInstance(this.countryServiceProvider, this.apiProvider, this.authTokensProvider, this.sessionProvider, this.localSettingsServiceProvider);
    }

    public static ChangePasswordViewModel provideInstance(Provider<CountryService> provider, Provider<MfpInformationApi> provider2, Provider<AuthTokenProvider> provider3, Provider<Session> provider4, Provider<LocalSettingsService> provider5) {
        ChangePasswordViewModel changePasswordViewModel = new ChangePasswordViewModel(DoubleCheck.lazy(provider), provider2, DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5));
        return changePasswordViewModel;
    }

    public static ChangePasswordViewModel_Factory create(Provider<CountryService> provider, Provider<MfpInformationApi> provider2, Provider<AuthTokenProvider> provider3, Provider<Session> provider4, Provider<LocalSettingsService> provider5) {
        ChangePasswordViewModel_Factory changePasswordViewModel_Factory = new ChangePasswordViewModel_Factory(provider, provider2, provider3, provider4, provider5);
        return changePasswordViewModel_Factory;
    }

    public static ChangePasswordViewModel newChangePasswordViewModel(Lazy<CountryService> lazy, Provider<MfpInformationApi> provider, Lazy<AuthTokenProvider> lazy2, Lazy<Session> lazy3, Lazy<LocalSettingsService> lazy4) {
        ChangePasswordViewModel changePasswordViewModel = new ChangePasswordViewModel(lazy, provider, lazy2, lazy3, lazy4);
        return changePasswordViewModel;
    }
}
