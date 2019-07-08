package com.myfitnesspal.feature.consents.model;

import android.app.Application;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ConfirmLocationViewModel_Factory implements Factory<ConfirmLocationViewModel> {
    private final Provider<Application> applicationContextProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<Session> sessionsProvider;

    public ConfirmLocationViewModel_Factory(Provider<Application> provider, Provider<Session> provider2, Provider<CountryService> provider3) {
        this.applicationContextProvider = provider;
        this.sessionsProvider = provider2;
        this.countryServiceProvider = provider3;
    }

    public ConfirmLocationViewModel get() {
        return provideInstance(this.applicationContextProvider, this.sessionsProvider, this.countryServiceProvider);
    }

    public static ConfirmLocationViewModel provideInstance(Provider<Application> provider, Provider<Session> provider2, Provider<CountryService> provider3) {
        return new ConfirmLocationViewModel((Application) provider.get(), (Session) provider2.get(), (CountryService) provider3.get());
    }

    public static ConfirmLocationViewModel_Factory create(Provider<Application> provider, Provider<Session> provider2, Provider<CountryService> provider3) {
        return new ConfirmLocationViewModel_Factory(provider, provider2, provider3);
    }

    public static ConfirmLocationViewModel newConfirmLocationViewModel(Application application, Session session, CountryService countryService) {
        return new ConfirmLocationViewModel(application, session, countryService);
    }
}
