package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesSignUpModelFactory implements Factory<SignUpModel> {
    private final Provider<CountryService> countryServiceProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<SharedPreferences> signUpPrefsProvider;

    public ApplicationModule_ProvidesSignUpModelFactory(ApplicationModule applicationModule, Provider<SharedPreferences> provider, Provider<CountryService> provider2, Provider<Session> provider3) {
        this.module = applicationModule;
        this.signUpPrefsProvider = provider;
        this.countryServiceProvider = provider2;
        this.sessionProvider = provider3;
    }

    public SignUpModel get() {
        return provideInstance(this.module, this.signUpPrefsProvider, this.countryServiceProvider, this.sessionProvider);
    }

    public static SignUpModel provideInstance(ApplicationModule applicationModule, Provider<SharedPreferences> provider, Provider<CountryService> provider2, Provider<Session> provider3) {
        return proxyProvidesSignUpModel(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static ApplicationModule_ProvidesSignUpModelFactory create(ApplicationModule applicationModule, Provider<SharedPreferences> provider, Provider<CountryService> provider2, Provider<Session> provider3) {
        return new ApplicationModule_ProvidesSignUpModelFactory(applicationModule, provider, provider2, provider3);
    }

    public static SignUpModel proxyProvidesSignUpModel(ApplicationModule applicationModule, Lazy<SharedPreferences> lazy, Lazy<CountryService> lazy2, Lazy<Session> lazy3) {
        return (SignUpModel) Preconditions.checkNotNull(applicationModule.providesSignUpModel(lazy, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }
}
