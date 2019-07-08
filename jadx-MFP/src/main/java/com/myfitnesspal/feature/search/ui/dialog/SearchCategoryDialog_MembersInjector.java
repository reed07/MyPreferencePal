package com.myfitnesspal.feature.search.ui.dialog;

import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SearchCategoryDialog_MembersInjector implements MembersInjector<SearchCategoryDialog> {
    private final Provider<FoodSearchActivityFactory> foodSearchRouterProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public SearchCategoryDialog_MembersInjector(Provider<NavigationHelper> provider, Provider<Session> provider2, Provider<FoodSearchActivityFactory> provider3) {
        this.navigationHelperProvider = provider;
        this.sessionProvider = provider2;
        this.foodSearchRouterProvider = provider3;
    }

    public static MembersInjector<SearchCategoryDialog> create(Provider<NavigationHelper> provider, Provider<Session> provider2, Provider<FoodSearchActivityFactory> provider3) {
        return new SearchCategoryDialog_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(SearchCategoryDialog searchCategoryDialog) {
        injectNavigationHelper(searchCategoryDialog, (NavigationHelper) this.navigationHelperProvider.get());
        injectSession(searchCategoryDialog, (Session) this.sessionProvider.get());
        injectFoodSearchRouter(searchCategoryDialog, DoubleCheck.lazy(this.foodSearchRouterProvider));
    }

    public static void injectNavigationHelper(SearchCategoryDialog searchCategoryDialog, NavigationHelper navigationHelper) {
        searchCategoryDialog.navigationHelper = navigationHelper;
    }

    public static void injectSession(SearchCategoryDialog searchCategoryDialog, Session session) {
        searchCategoryDialog.session = session;
    }

    public static void injectFoodSearchRouter(SearchCategoryDialog searchCategoryDialog, Lazy<FoodSearchActivityFactory> lazy) {
        searchCategoryDialog.foodSearchRouter = lazy;
    }
}
