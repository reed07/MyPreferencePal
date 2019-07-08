package com.myfitnesspal.feature.goals.ui.dialog;

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

public final class WeightGoalDialogFragment_MembersInjector implements MembersInjector<WeightGoalDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public WeightGoalDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserWeightService> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.userWeightServiceProvider = provider5;
    }

    public static MembersInjector<WeightGoalDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserWeightService> provider5) {
        WeightGoalDialogFragment_MembersInjector weightGoalDialogFragment_MembersInjector = new WeightGoalDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return weightGoalDialogFragment_MembersInjector;
    }

    public void injectMembers(WeightGoalDialogFragment weightGoalDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(weightGoalDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(weightGoalDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(weightGoalDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(weightGoalDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectUserWeightService(weightGoalDialogFragment, (UserWeightService) this.userWeightServiceProvider.get());
        injectSession(weightGoalDialogFragment, DoubleCheck.lazy(this.sessionProvider));
    }

    public static void injectUserWeightService(WeightGoalDialogFragment weightGoalDialogFragment, UserWeightService userWeightService) {
        weightGoalDialogFragment.userWeightService = userWeightService;
    }

    public static void injectSession(WeightGoalDialogFragment weightGoalDialogFragment, Lazy<Session> lazy) {
        weightGoalDialogFragment.session = lazy;
    }
}
