package com.myfitnesspal.feature.consents.model;

import android.app.Application;
import com.myfitnesspal.feature.consents.service.ConsentsService;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelper;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ConsentsViewModel_Factory implements Factory<ConsentsViewModel> {
    private final Provider<Application> applicationContextProvider;
    private final Provider<ConsentsAnalyticsHelper> consentsAnalyticsHelperProvider;
    private final Provider<ConsentsService> consentsServiceProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<Session> sessionsProvider;

    public ConsentsViewModel_Factory(Provider<Application> provider, Provider<Session> provider2, Provider<ConsentsService> provider3, Provider<CountryService> provider4, Provider<ConsentsAnalyticsHelper> provider5) {
        this.applicationContextProvider = provider;
        this.sessionsProvider = provider2;
        this.consentsServiceProvider = provider3;
        this.countryServiceProvider = provider4;
        this.consentsAnalyticsHelperProvider = provider5;
    }

    public ConsentsViewModel get() {
        return provideInstance(this.applicationContextProvider, this.sessionsProvider, this.consentsServiceProvider, this.countryServiceProvider, this.consentsAnalyticsHelperProvider);
    }

    public static ConsentsViewModel provideInstance(Provider<Application> provider, Provider<Session> provider2, Provider<ConsentsService> provider3, Provider<CountryService> provider4, Provider<ConsentsAnalyticsHelper> provider5) {
        ConsentsViewModel consentsViewModel = new ConsentsViewModel((Application) provider.get(), (Session) provider2.get(), (ConsentsService) provider3.get(), (CountryService) provider4.get(), (ConsentsAnalyticsHelper) provider5.get());
        return consentsViewModel;
    }

    public static ConsentsViewModel_Factory create(Provider<Application> provider, Provider<Session> provider2, Provider<ConsentsService> provider3, Provider<CountryService> provider4, Provider<ConsentsAnalyticsHelper> provider5) {
        ConsentsViewModel_Factory consentsViewModel_Factory = new ConsentsViewModel_Factory(provider, provider2, provider3, provider4, provider5);
        return consentsViewModel_Factory;
    }

    public static ConsentsViewModel newConsentsViewModel(Application application, Session session, ConsentsService consentsService, CountryService countryService, ConsentsAnalyticsHelper consentsAnalyticsHelper) {
        ConsentsViewModel consentsViewModel = new ConsentsViewModel(application, session, consentsService, countryService, consentsAnalyticsHelper);
        return consentsViewModel;
    }
}
