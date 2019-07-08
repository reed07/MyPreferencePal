package com.myfitnesspal.shared.ui.dialog;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class CustomLayoutBaseDialogFragment_MembersInjector implements MembersInjector<CustomLayoutBaseDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public CustomLayoutBaseDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
    }

    public static MembersInjector<CustomLayoutBaseDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        return new CustomLayoutBaseDialogFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(CustomLayoutBaseDialogFragment customLayoutBaseDialogFragment) {
        injectMessageBus(customLayoutBaseDialogFragment, (Bus) this.messageBusProvider.get());
        injectNavigationHelper(customLayoutBaseDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        injectSession(customLayoutBaseDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        injectAnalyticsService(customLayoutBaseDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
    }

    public static void injectMessageBus(CustomLayoutBaseDialogFragment customLayoutBaseDialogFragment, Bus bus) {
        customLayoutBaseDialogFragment.messageBus = bus;
    }

    public static void injectNavigationHelper(CustomLayoutBaseDialogFragment customLayoutBaseDialogFragment, NavigationHelper navigationHelper) {
        customLayoutBaseDialogFragment.navigationHelper = navigationHelper;
    }

    public static void injectSession(CustomLayoutBaseDialogFragment customLayoutBaseDialogFragment, Lazy<Session> lazy) {
        customLayoutBaseDialogFragment.session = lazy;
    }

    public static void injectAnalyticsService(CustomLayoutBaseDialogFragment customLayoutBaseDialogFragment, Lazy<AnalyticsService> lazy) {
        customLayoutBaseDialogFragment.analyticsService = lazy;
    }
}
