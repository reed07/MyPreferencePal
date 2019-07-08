package com.myfitnesspal.feature.recipes.util;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import dagger.Lazy;

public class RecipesMealsFoodsAnalyticsHelper {
    private static final String ATTRIBUTE_TAB_NAME = "tab_name";
    private static final String EVENT_RECIPES_AND_FOODS_SCREEN_FOOD_SEARCH_TAP = "recipes_and_foods_screen_food_search_tap";
    private static final String EVENT_RECIPES_AND_FOODS_SCREEN_FOOD_SEARCH_TYPE = "recipes_and_foods_screen_food_search_type";
    private static final String EVENT_RECIPES_MEALS_FOODS_TAB = "recipes_and_foods_screen_foods_tab";
    private final Lazy<AnalyticsService> analyticsService;
    private boolean wasTypeEventReported = false;

    public RecipesMealsFoodsAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        this.analyticsService = lazy;
    }

    public void reportRecipesMealsFoodsTabEvent(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_RECIPES_MEALS_FOODS_TAB, MapUtil.createMap("tab_name", str));
    }

    public void reportRecipesAndFoodsSearchTap(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_RECIPES_AND_FOODS_SCREEN_FOOD_SEARCH_TAP, MapUtil.createMap("tab_name", str));
    }

    public void reportRecipesAndFoodsSearchType(String str) {
        if (!this.wasTypeEventReported) {
            this.wasTypeEventReported = true;
            ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_RECIPES_AND_FOODS_SCREEN_FOOD_SEARCH_TYPE, MapUtil.createMap("tab_name", str));
        }
    }
}
