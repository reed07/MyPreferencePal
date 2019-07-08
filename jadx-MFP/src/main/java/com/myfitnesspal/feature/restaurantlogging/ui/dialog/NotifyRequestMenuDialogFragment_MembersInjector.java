package com.myfitnesspal.feature.restaurantlogging.ui.dialog;

import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class NotifyRequestMenuDialogFragment_MembersInjector implements MembersInjector<NotifyRequestMenuDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<RestaurantLoggingAnalyticsHelper> restaurantLoggingAnalyticsHelperProvider;
    private final Provider<Session> sessionProvider;

    public NotifyRequestMenuDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<RestaurantLoggingAnalyticsHelper> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.restaurantLoggingAnalyticsHelperProvider = provider5;
    }

    public static MembersInjector<NotifyRequestMenuDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<RestaurantLoggingAnalyticsHelper> provider5) {
        NotifyRequestMenuDialogFragment_MembersInjector notifyRequestMenuDialogFragment_MembersInjector = new NotifyRequestMenuDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return notifyRequestMenuDialogFragment_MembersInjector;
    }

    public void injectMembers(NotifyRequestMenuDialogFragment notifyRequestMenuDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(notifyRequestMenuDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(notifyRequestMenuDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(notifyRequestMenuDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(notifyRequestMenuDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectRestaurantLoggingAnalyticsHelper(notifyRequestMenuDialogFragment, DoubleCheck.lazy(this.restaurantLoggingAnalyticsHelperProvider));
    }

    public static void injectRestaurantLoggingAnalyticsHelper(NotifyRequestMenuDialogFragment notifyRequestMenuDialogFragment, Lazy<RestaurantLoggingAnalyticsHelper> lazy) {
        notifyRequestMenuDialogFragment.restaurantLoggingAnalyticsHelper = lazy;
    }
}
