package com.myfitnesspal.feature.settings.ui.dialog;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class HeightDialogFragment_MembersInjector implements MembersInjector<HeightDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserHeightService> userHeightServiceProvider;

    public HeightDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserHeightService> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.userHeightServiceProvider = provider5;
    }

    public static MembersInjector<HeightDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserHeightService> provider5) {
        HeightDialogFragment_MembersInjector heightDialogFragment_MembersInjector = new HeightDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return heightDialogFragment_MembersInjector;
    }

    public void injectMembers(HeightDialogFragment heightDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(heightDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(heightDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(heightDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(heightDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectUserHeightService(heightDialogFragment, (UserHeightService) this.userHeightServiceProvider.get());
        injectSession(heightDialogFragment, DoubleCheck.lazy(this.sessionProvider));
    }

    public static void injectUserHeightService(HeightDialogFragment heightDialogFragment, UserHeightService userHeightService) {
        heightDialogFragment.userHeightService = userHeightService;
    }

    public static void injectSession(HeightDialogFragment heightDialogFragment, Lazy<Session> lazy) {
        heightDialogFragment.session = lazy;
    }
}
