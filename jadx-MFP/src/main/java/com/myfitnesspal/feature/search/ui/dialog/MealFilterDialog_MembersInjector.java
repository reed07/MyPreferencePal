package com.myfitnesspal.feature.search.ui.dialog;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
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

public final class MealFilterDialog_MembersInjector implements MembersInjector<MealFilterDialog> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public MealFilterDialog_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<LocalizedStringsUtil> provider5, Provider<UserEnergyService> provider6, Provider<LocalSettingsService> provider7) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.localizedStringsUtilProvider = provider5;
        this.userEnergyServiceProvider = provider6;
        this.localSettingsServiceProvider = provider7;
    }

    public static MembersInjector<MealFilterDialog> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<LocalizedStringsUtil> provider5, Provider<UserEnergyService> provider6, Provider<LocalSettingsService> provider7) {
        MealFilterDialog_MembersInjector mealFilterDialog_MembersInjector = new MealFilterDialog_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return mealFilterDialog_MembersInjector;
    }

    public void injectMembers(MealFilterDialog mealFilterDialog) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(mealFilterDialog, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(mealFilterDialog, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(mealFilterDialog, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(mealFilterDialog, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectLocalizedStringsUtil(mealFilterDialog, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectUserEnergyService(mealFilterDialog, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectLocalSettingsService(mealFilterDialog, DoubleCheck.lazy(this.localSettingsServiceProvider));
    }

    public static void injectLocalizedStringsUtil(MealFilterDialog mealFilterDialog, Lazy<LocalizedStringsUtil> lazy) {
        mealFilterDialog.localizedStringsUtil = lazy;
    }

    public static void injectUserEnergyService(MealFilterDialog mealFilterDialog, Lazy<UserEnergyService> lazy) {
        mealFilterDialog.userEnergyService = lazy;
    }

    public static void injectLocalSettingsService(MealFilterDialog mealFilterDialog, Lazy<LocalSettingsService> lazy) {
        mealFilterDialog.localSettingsService = lazy;
    }
}
