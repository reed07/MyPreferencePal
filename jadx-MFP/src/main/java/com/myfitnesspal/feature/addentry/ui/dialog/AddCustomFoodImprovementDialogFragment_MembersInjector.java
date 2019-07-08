package com.myfitnesspal.feature.addentry.ui.dialog;

import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class AddCustomFoodImprovementDialogFragment_MembersInjector implements MembersInjector<AddCustomFoodImprovementDialogFragment> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public AddCustomFoodImprovementDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<ActionTrackingService> provider5, Provider<ConfigService> provider6) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.actionTrackingServiceProvider = provider5;
        this.configServiceProvider = provider6;
    }

    public static MembersInjector<AddCustomFoodImprovementDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<ActionTrackingService> provider5, Provider<ConfigService> provider6) {
        AddCustomFoodImprovementDialogFragment_MembersInjector addCustomFoodImprovementDialogFragment_MembersInjector = new AddCustomFoodImprovementDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return addCustomFoodImprovementDialogFragment_MembersInjector;
    }

    public void injectMembers(AddCustomFoodImprovementDialogFragment addCustomFoodImprovementDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(addCustomFoodImprovementDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(addCustomFoodImprovementDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(addCustomFoodImprovementDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(addCustomFoodImprovementDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectActionTrackingService(addCustomFoodImprovementDialogFragment, (ActionTrackingService) this.actionTrackingServiceProvider.get());
        injectConfigService(addCustomFoodImprovementDialogFragment, DoubleCheck.lazy(this.configServiceProvider));
        injectAnalyticsService(addCustomFoodImprovementDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
    }

    public static void injectActionTrackingService(AddCustomFoodImprovementDialogFragment addCustomFoodImprovementDialogFragment, ActionTrackingService actionTrackingService) {
        addCustomFoodImprovementDialogFragment.actionTrackingService = actionTrackingService;
    }

    public static void injectConfigService(AddCustomFoodImprovementDialogFragment addCustomFoodImprovementDialogFragment, Lazy<ConfigService> lazy) {
        addCustomFoodImprovementDialogFragment.configService = lazy;
    }

    public static void injectAnalyticsService(AddCustomFoodImprovementDialogFragment addCustomFoodImprovementDialogFragment, Lazy<AnalyticsService> lazy) {
        addCustomFoodImprovementDialogFragment.analyticsService = lazy;
    }
}
