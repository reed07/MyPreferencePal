package com.myfitnesspal.feature.foodeditor.ui.mixin.impl;

import com.myfitnesspal.feature.restaurantlogging.service.MenuService;
import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingAnalyticsHelper;
import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingSettingsService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MenuItemEditorMixin_MembersInjector implements MembersInjector<MenuItemEditorMixin> {
    private final Provider<MenuService> menuServiceProvider;
    private final Provider<RestaurantLoggingAnalyticsHelper> restaurantLoggingAnalyticsHelperProvider;
    private final Provider<RestaurantLoggingSettingsService> restaurantLoggingSettingsServiceProvider;

    public MenuItemEditorMixin_MembersInjector(Provider<MenuService> provider, Provider<RestaurantLoggingSettingsService> provider2, Provider<RestaurantLoggingAnalyticsHelper> provider3) {
        this.menuServiceProvider = provider;
        this.restaurantLoggingSettingsServiceProvider = provider2;
        this.restaurantLoggingAnalyticsHelperProvider = provider3;
    }

    public static MembersInjector<MenuItemEditorMixin> create(Provider<MenuService> provider, Provider<RestaurantLoggingSettingsService> provider2, Provider<RestaurantLoggingAnalyticsHelper> provider3) {
        return new MenuItemEditorMixin_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(MenuItemEditorMixin menuItemEditorMixin) {
        injectMenuService(menuItemEditorMixin, DoubleCheck.lazy(this.menuServiceProvider));
        injectRestaurantLoggingSettingsService(menuItemEditorMixin, DoubleCheck.lazy(this.restaurantLoggingSettingsServiceProvider));
        injectRestaurantLoggingAnalyticsHelper(menuItemEditorMixin, DoubleCheck.lazy(this.restaurantLoggingAnalyticsHelperProvider));
    }

    public static void injectMenuService(MenuItemEditorMixin menuItemEditorMixin, Lazy<MenuService> lazy) {
        menuItemEditorMixin.menuService = lazy;
    }

    public static void injectRestaurantLoggingSettingsService(MenuItemEditorMixin menuItemEditorMixin, Lazy<RestaurantLoggingSettingsService> lazy) {
        menuItemEditorMixin.restaurantLoggingSettingsService = lazy;
    }

    public static void injectRestaurantLoggingAnalyticsHelper(MenuItemEditorMixin menuItemEditorMixin, Lazy<RestaurantLoggingAnalyticsHelper> lazy) {
        menuItemEditorMixin.restaurantLoggingAnalyticsHelper = lazy;
    }
}
