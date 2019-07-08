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

public final class MealNamesDialogFragment_MembersInjector implements MembersInjector<MealNamesDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public MealNamesDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserEnergyService> provider5, Provider<LocalizedStringsUtil> provider6) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.userEnergyServiceProvider = provider5;
        this.localizedStringsUtilProvider = provider6;
    }

    public static MembersInjector<MealNamesDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserEnergyService> provider5, Provider<LocalizedStringsUtil> provider6) {
        MealNamesDialogFragment_MembersInjector mealNamesDialogFragment_MembersInjector = new MealNamesDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return mealNamesDialogFragment_MembersInjector;
    }

    public void injectMembers(MealNamesDialogFragment mealNamesDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(mealNamesDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(mealNamesDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(mealNamesDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(mealNamesDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectUserEnergyService(mealNamesDialogFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectLocalizedStringsUtil(mealNamesDialogFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectSession(mealNamesDialogFragment, DoubleCheck.lazy(this.sessionProvider));
    }

    public static void injectUserEnergyService(MealNamesDialogFragment mealNamesDialogFragment, Lazy<UserEnergyService> lazy) {
        mealNamesDialogFragment.userEnergyService = lazy;
    }

    public static void injectLocalizedStringsUtil(MealNamesDialogFragment mealNamesDialogFragment, Lazy<LocalizedStringsUtil> lazy) {
        mealNamesDialogFragment.localizedStringsUtil = lazy;
    }

    public static void injectSession(MealNamesDialogFragment mealNamesDialogFragment, Lazy<Session> lazy) {
        mealNamesDialogFragment.session = lazy;
    }
}
