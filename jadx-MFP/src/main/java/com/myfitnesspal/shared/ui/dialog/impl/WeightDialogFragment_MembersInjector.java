package com.myfitnesspal.shared.ui.dialog.impl;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class WeightDialogFragment_MembersInjector implements MembersInjector<WeightDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public WeightDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserWeightService> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.userWeightServiceProvider = provider5;
    }

    public static MembersInjector<WeightDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserWeightService> provider5) {
        WeightDialogFragment_MembersInjector weightDialogFragment_MembersInjector = new WeightDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return weightDialogFragment_MembersInjector;
    }

    public void injectMembers(WeightDialogFragment weightDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(weightDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(weightDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(weightDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(weightDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectUserWeightService(weightDialogFragment, DoubleCheck.lazy(this.userWeightServiceProvider));
        injectAnalyticsService(weightDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
    }

    public static void injectUserWeightService(WeightDialogFragment weightDialogFragment, Lazy<UserWeightService> lazy) {
        weightDialogFragment.userWeightService = lazy;
    }

    public static void injectAnalyticsService(WeightDialogFragment weightDialogFragment, Lazy<AnalyticsService> lazy) {
        weightDialogFragment.analyticsService = lazy;
    }
}
