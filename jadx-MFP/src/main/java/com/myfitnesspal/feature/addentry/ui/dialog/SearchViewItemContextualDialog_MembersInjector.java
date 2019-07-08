package com.myfitnesspal.feature.addentry.ui.dialog;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SearchViewItemContextualDialog_MembersInjector implements MembersInjector<SearchViewItemContextualDialog> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<FoodService> foodServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public SearchViewItemContextualDialog_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<FoodService> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.foodServiceProvider = provider5;
    }

    public static MembersInjector<SearchViewItemContextualDialog> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<FoodService> provider5) {
        SearchViewItemContextualDialog_MembersInjector searchViewItemContextualDialog_MembersInjector = new SearchViewItemContextualDialog_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return searchViewItemContextualDialog_MembersInjector;
    }

    public void injectMembers(SearchViewItemContextualDialog searchViewItemContextualDialog) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(searchViewItemContextualDialog, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(searchViewItemContextualDialog, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(searchViewItemContextualDialog, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(searchViewItemContextualDialog, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectFoodService(searchViewItemContextualDialog, (FoodService) this.foodServiceProvider.get());
    }

    public static void injectFoodService(SearchViewItemContextualDialog searchViewItemContextualDialog, FoodService foodService) {
        searchViewItemContextualDialog.foodService = foodService;
    }
}
