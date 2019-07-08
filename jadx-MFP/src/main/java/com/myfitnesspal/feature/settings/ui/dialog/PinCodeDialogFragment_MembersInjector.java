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

public final class PinCodeDialogFragment_MembersInjector implements MembersInjector<PinCodeDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<Bus> messageBusAndBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public PinCodeDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<CountryService> provider5) {
        this.messageBusAndBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.countryServiceProvider = provider5;
    }

    public static MembersInjector<PinCodeDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<CountryService> provider5) {
        PinCodeDialogFragment_MembersInjector pinCodeDialogFragment_MembersInjector = new PinCodeDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return pinCodeDialogFragment_MembersInjector;
    }

    public void injectMembers(PinCodeDialogFragment pinCodeDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(pinCodeDialogFragment, (Bus) this.messageBusAndBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(pinCodeDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(pinCodeDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(pinCodeDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectCountryService(pinCodeDialogFragment, DoubleCheck.lazy(this.countryServiceProvider));
        injectBus(pinCodeDialogFragment, DoubleCheck.lazy(this.messageBusAndBusProvider));
    }

    public static void injectCountryService(PinCodeDialogFragment pinCodeDialogFragment, Lazy<CountryService> lazy) {
        pinCodeDialogFragment.countryService = lazy;
    }

    public static void injectBus(PinCodeDialogFragment pinCodeDialogFragment, Lazy<Bus> lazy) {
        pinCodeDialogFragment.bus = lazy;
    }
}
