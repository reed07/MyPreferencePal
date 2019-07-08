package com.myfitnesspal.feature.addentry.ui.dialog;

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

public final class EditServingsDialogFragmentV2_MembersInjector implements MembersInjector<EditServingsDialogFragmentV2> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<FoodService> foodServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public EditServingsDialogFragmentV2_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<FoodService> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.foodServiceProvider = provider5;
    }

    public static MembersInjector<EditServingsDialogFragmentV2> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<FoodService> provider5) {
        EditServingsDialogFragmentV2_MembersInjector editServingsDialogFragmentV2_MembersInjector = new EditServingsDialogFragmentV2_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return editServingsDialogFragmentV2_MembersInjector;
    }

    public void injectMembers(EditServingsDialogFragmentV2 editServingsDialogFragmentV2) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(editServingsDialogFragmentV2, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(editServingsDialogFragmentV2, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(editServingsDialogFragmentV2, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(editServingsDialogFragmentV2, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectFoodService(editServingsDialogFragmentV2, DoubleCheck.lazy(this.foodServiceProvider));
    }

    public static void injectFoodService(EditServingsDialogFragmentV2 editServingsDialogFragmentV2, Lazy<FoodService> lazy) {
        editServingsDialogFragmentV2.foodService = lazy;
    }
}
