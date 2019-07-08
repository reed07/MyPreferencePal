package com.myfitnesspal.feature.settings.ui.dialog;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class CountryDialogFragment_MembersInjector implements MembersInjector<CountryDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public CountryDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<CountryService> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.countryServiceProvider = provider5;
    }

    public static MembersInjector<CountryDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<CountryService> provider5) {
        CountryDialogFragment_MembersInjector countryDialogFragment_MembersInjector = new CountryDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return countryDialogFragment_MembersInjector;
    }

    public void injectMembers(CountryDialogFragment countryDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(countryDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(countryDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(countryDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(countryDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectCountryService(countryDialogFragment, DoubleCheck.lazy(this.countryServiceProvider));
    }

    public static void injectCountryService(CountryDialogFragment countryDialogFragment, Lazy<CountryService> lazy) {
        countryDialogFragment.countryService = lazy;
    }
}
