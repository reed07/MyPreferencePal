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

public final class CalorieAddErrorDialogFragment_MembersInjector implements MembersInjector<CalorieAddErrorDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<Bus> messageBusAndBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public CalorieAddErrorDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<LocalizedStringsUtil> provider5, Provider<UserEnergyService> provider6) {
        this.messageBusAndBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.localizedStringsUtilProvider = provider5;
        this.userEnergyServiceProvider = provider6;
    }

    public static MembersInjector<CalorieAddErrorDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<LocalizedStringsUtil> provider5, Provider<UserEnergyService> provider6) {
        CalorieAddErrorDialogFragment_MembersInjector calorieAddErrorDialogFragment_MembersInjector = new CalorieAddErrorDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return calorieAddErrorDialogFragment_MembersInjector;
    }

    public void injectMembers(CalorieAddErrorDialogFragment calorieAddErrorDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(calorieAddErrorDialogFragment, (Bus) this.messageBusAndBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(calorieAddErrorDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(calorieAddErrorDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(calorieAddErrorDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectLocalizedStringsUtil(calorieAddErrorDialogFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectUserEnergyService(calorieAddErrorDialogFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectBus(calorieAddErrorDialogFragment, DoubleCheck.lazy(this.messageBusAndBusProvider));
    }

    public static void injectLocalizedStringsUtil(CalorieAddErrorDialogFragment calorieAddErrorDialogFragment, Lazy<LocalizedStringsUtil> lazy) {
        calorieAddErrorDialogFragment.localizedStringsUtil = lazy;
    }

    public static void injectUserEnergyService(CalorieAddErrorDialogFragment calorieAddErrorDialogFragment, Lazy<UserEnergyService> lazy) {
        calorieAddErrorDialogFragment.userEnergyService = lazy;
    }

    public static void injectBus(CalorieAddErrorDialogFragment calorieAddErrorDialogFragment, Lazy<Bus> lazy) {
        calorieAddErrorDialogFragment.bus = lazy;
    }
}
