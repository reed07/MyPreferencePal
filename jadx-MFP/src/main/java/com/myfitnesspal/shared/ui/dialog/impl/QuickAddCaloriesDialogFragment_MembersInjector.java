package com.myfitnesspal.shared.ui.dialog.impl;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class QuickAddCaloriesDialogFragment_MembersInjector implements MembersInjector<QuickAddCaloriesDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<Bus> messageBusAndBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public QuickAddCaloriesDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserEnergyService> provider5, Provider<LocalizedStringsUtil> provider6) {
        this.messageBusAndBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.userEnergyServiceProvider = provider5;
        this.localizedStringsUtilProvider = provider6;
    }

    public static MembersInjector<QuickAddCaloriesDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserEnergyService> provider5, Provider<LocalizedStringsUtil> provider6) {
        QuickAddCaloriesDialogFragment_MembersInjector quickAddCaloriesDialogFragment_MembersInjector = new QuickAddCaloriesDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return quickAddCaloriesDialogFragment_MembersInjector;
    }

    public void injectMembers(QuickAddCaloriesDialogFragment quickAddCaloriesDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(quickAddCaloriesDialogFragment, (Bus) this.messageBusAndBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(quickAddCaloriesDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(quickAddCaloriesDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(quickAddCaloriesDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectUserEnergyService(quickAddCaloriesDialogFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectLocalizedStringsUtil(quickAddCaloriesDialogFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectBus(quickAddCaloriesDialogFragment, DoubleCheck.lazy(this.messageBusAndBusProvider));
    }

    public static void injectUserEnergyService(QuickAddCaloriesDialogFragment quickAddCaloriesDialogFragment, Lazy<UserEnergyService> lazy) {
        quickAddCaloriesDialogFragment.userEnergyService = lazy;
    }

    public static void injectLocalizedStringsUtil(QuickAddCaloriesDialogFragment quickAddCaloriesDialogFragment, Lazy<LocalizedStringsUtil> lazy) {
        quickAddCaloriesDialogFragment.localizedStringsUtil = lazy;
    }

    public static void injectBus(QuickAddCaloriesDialogFragment quickAddCaloriesDialogFragment, Lazy<Bus> lazy) {
        quickAddCaloriesDialogFragment.bus = lazy;
    }
}
