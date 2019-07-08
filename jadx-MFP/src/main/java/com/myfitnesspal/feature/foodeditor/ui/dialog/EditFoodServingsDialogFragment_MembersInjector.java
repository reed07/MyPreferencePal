package com.myfitnesspal.feature.foodeditor.ui.dialog;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class EditFoodServingsDialogFragment_MembersInjector implements MembersInjector<EditFoodServingsDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<FoodService> foodServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public EditFoodServingsDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<FoodService> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.foodServiceProvider = provider5;
    }

    public static MembersInjector<EditFoodServingsDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<FoodService> provider5) {
        EditFoodServingsDialogFragment_MembersInjector editFoodServingsDialogFragment_MembersInjector = new EditFoodServingsDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return editFoodServingsDialogFragment_MembersInjector;
    }

    public void injectMembers(EditFoodServingsDialogFragment editFoodServingsDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(editFoodServingsDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(editFoodServingsDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(editFoodServingsDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(editFoodServingsDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectFoodService(editFoodServingsDialogFragment, DoubleCheck.lazy(this.foodServiceProvider));
    }

    public static void injectFoodService(EditFoodServingsDialogFragment editFoodServingsDialogFragment, Lazy<FoodService> lazy) {
        editFoodServingsDialogFragment.foodService = lazy;
    }
}
