package com.myfitnesspal.feature.restaurantlogging.ui.view;

import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingSettingsService;
import com.myfitnesspal.feature.restaurantlogging.util.MultiAddMenuItemHelper;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MenusListView_MembersInjector implements MembersInjector<MenusListView> {
    private final Provider<MultiAddMenuItemHelper> multiAddMenuItemHelperProvider;
    private final Provider<RestaurantLoggingSettingsService> restaurantLoggingSettingsServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public MenusListView_MembersInjector(Provider<UserEnergyService> provider, Provider<RestaurantLoggingSettingsService> provider2, Provider<MultiAddMenuItemHelper> provider3) {
        this.userEnergyServiceProvider = provider;
        this.restaurantLoggingSettingsServiceProvider = provider2;
        this.multiAddMenuItemHelperProvider = provider3;
    }

    public static MembersInjector<MenusListView> create(Provider<UserEnergyService> provider, Provider<RestaurantLoggingSettingsService> provider2, Provider<MultiAddMenuItemHelper> provider3) {
        return new MenusListView_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(MenusListView menusListView) {
        injectUserEnergyService(menusListView, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectRestaurantLoggingSettingsService(menusListView, DoubleCheck.lazy(this.restaurantLoggingSettingsServiceProvider));
        injectMultiAddMenuItemHelper(menusListView, DoubleCheck.lazy(this.multiAddMenuItemHelperProvider));
    }

    public static void injectUserEnergyService(MenusListView menusListView, Lazy<UserEnergyService> lazy) {
        menusListView.userEnergyService = lazy;
    }

    public static void injectRestaurantLoggingSettingsService(MenusListView menusListView, Lazy<RestaurantLoggingSettingsService> lazy) {
        menusListView.restaurantLoggingSettingsService = lazy;
    }

    public static void injectMultiAddMenuItemHelper(MenusListView menusListView, Lazy<MultiAddMenuItemHelper> lazy) {
        menusListView.multiAddMenuItemHelper = lazy;
    }
}
