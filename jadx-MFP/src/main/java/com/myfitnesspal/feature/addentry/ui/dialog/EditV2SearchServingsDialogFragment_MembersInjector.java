package com.myfitnesspal.feature.addentry.ui.dialog;

import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class EditV2SearchServingsDialogFragment_MembersInjector implements MembersInjector<EditV2SearchServingsDialogFragment> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<FoodService> foodServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public EditV2SearchServingsDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<FoodService> provider5, Provider<ActionTrackingService> provider6, Provider<CountryService> provider7) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.foodServiceProvider = provider5;
        this.actionTrackingServiceProvider = provider6;
        this.countryServiceProvider = provider7;
    }

    public static MembersInjector<EditV2SearchServingsDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<FoodService> provider5, Provider<ActionTrackingService> provider6, Provider<CountryService> provider7) {
        EditV2SearchServingsDialogFragment_MembersInjector editV2SearchServingsDialogFragment_MembersInjector = new EditV2SearchServingsDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return editV2SearchServingsDialogFragment_MembersInjector;
    }

    public void injectMembers(EditV2SearchServingsDialogFragment editV2SearchServingsDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(editV2SearchServingsDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(editV2SearchServingsDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(editV2SearchServingsDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(editV2SearchServingsDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectFoodService(editV2SearchServingsDialogFragment, DoubleCheck.lazy(this.foodServiceProvider));
        injectActionTrackingService(editV2SearchServingsDialogFragment, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        injectCountryService(editV2SearchServingsDialogFragment, DoubleCheck.lazy(this.countryServiceProvider));
    }

    public static void injectFoodService(EditV2SearchServingsDialogFragment editV2SearchServingsDialogFragment, Lazy<FoodService> lazy) {
        editV2SearchServingsDialogFragment.foodService = lazy;
    }

    public static void injectActionTrackingService(EditV2SearchServingsDialogFragment editV2SearchServingsDialogFragment, Lazy<ActionTrackingService> lazy) {
        editV2SearchServingsDialogFragment.actionTrackingService = lazy;
    }

    public static void injectCountryService(EditV2SearchServingsDialogFragment editV2SearchServingsDialogFragment, Lazy<CountryService> lazy) {
        editV2SearchServingsDialogFragment.countryService = lazy;
    }
}
