package com.myfitnesspal.feature.meals.ui.dialog;

import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class DeleteMealConfirmationDialogFragment_MembersInjector implements MembersInjector<DeleteMealConfirmationDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<MealService> mealServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public DeleteMealConfirmationDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<MealService> provider5, Provider<LocalSettingsService> provider6) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.mealServiceProvider = provider5;
        this.localSettingsServiceProvider = provider6;
    }

    public static MembersInjector<DeleteMealConfirmationDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<MealService> provider5, Provider<LocalSettingsService> provider6) {
        DeleteMealConfirmationDialogFragment_MembersInjector deleteMealConfirmationDialogFragment_MembersInjector = new DeleteMealConfirmationDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return deleteMealConfirmationDialogFragment_MembersInjector;
    }

    public void injectMembers(DeleteMealConfirmationDialogFragment deleteMealConfirmationDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(deleteMealConfirmationDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(deleteMealConfirmationDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(deleteMealConfirmationDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(deleteMealConfirmationDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectMealService(deleteMealConfirmationDialogFragment, DoubleCheck.lazy(this.mealServiceProvider));
        injectLocalSettingsService(deleteMealConfirmationDialogFragment, DoubleCheck.lazy(this.localSettingsServiceProvider));
    }

    public static void injectMealService(DeleteMealConfirmationDialogFragment deleteMealConfirmationDialogFragment, Lazy<MealService> lazy) {
        deleteMealConfirmationDialogFragment.mealService = lazy;
    }

    public static void injectLocalSettingsService(DeleteMealConfirmationDialogFragment deleteMealConfirmationDialogFragment, Lazy<LocalSettingsService> lazy) {
        deleteMealConfirmationDialogFragment.localSettingsService = lazy;
    }
}
