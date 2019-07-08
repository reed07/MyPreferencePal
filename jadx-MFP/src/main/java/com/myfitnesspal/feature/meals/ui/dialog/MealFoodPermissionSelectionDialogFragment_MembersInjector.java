package com.myfitnesspal.feature.meals.ui.dialog;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MealFoodPermissionSelectionDialogFragment_MembersInjector implements MembersInjector<MealFoodPermissionSelectionDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public MealFoodPermissionSelectionDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
    }

    public static MembersInjector<MealFoodPermissionSelectionDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        return new MealFoodPermissionSelectionDialogFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(MealFoodPermissionSelectionDialogFragment mealFoodPermissionSelectionDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(mealFoodPermissionSelectionDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(mealFoodPermissionSelectionDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(mealFoodPermissionSelectionDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(mealFoodPermissionSelectionDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
    }
}
