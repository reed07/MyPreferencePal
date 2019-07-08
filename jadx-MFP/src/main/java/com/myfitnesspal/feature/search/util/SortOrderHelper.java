package com.myfitnesspal.feature.search.util;

import android.content.SharedPreferences;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.feature.search.ui.constants.FoodSearchTab;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.ListType;
import com.myfitnesspal.shared.constants.Constants.SearchTabs;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;

public class SortOrderHelper {
    private static final String KEY_SORT_ALL = "all_foods_sort";
    private static final String KEY_SORT_ALL_EXERCISES = "all_exercises_sort";
    private static final String KEY_SORT_MOST_USED_EXERCISES = "most_used_exercises_sort";
    private static final String KEY_SORT_MOST_USED_FOODS = "most_used_foods_sort";
    private static final String KEY_SORT_MY_EXERCISES = "my_exercises_sort";
    private static final String KEY_SORT_MY_FOODS = "my_foods_sort";
    private static final String KEY_SORT_MY_MEALS = "my_meals_sort";
    private static final String KEY_SORT_MY_RECIPES = "my_recipes_sort";
    private static final String KEY_SORT_RECENT_FOODS = "recent_foods_sort";
    private static final Map<Integer, String> TAB_ANALYTIC_VALUE = new HashMap();
    private final SharedPreferences exerciseSortingPreferences;
    private final SharedPreferences foodSortingPreferences;
    private final Lazy<LocalSettingsService> localSettingsServiceLazy;

    private String getSortOrderKeyForTab(int i) {
        switch (i) {
            case SearchTabs.TAB_FREQUENT_FOODS /*6000*/:
                return KEY_SORT_MOST_USED_FOODS;
            case 6001:
                return KEY_SORT_RECENT_FOODS;
            case 6002:
                return KEY_SORT_MY_FOODS;
            case 6003:
                return KEY_SORT_MY_MEALS;
            case 6004:
                return KEY_SORT_MY_RECIPES;
            case 6005:
                return KEY_SORT_MOST_USED_EXERCISES;
            case SearchTabs.TAB_MY_EXERCISES /*6006*/:
                return KEY_SORT_MY_EXERCISES;
            case 6007:
                return KEY_SORT_ALL_EXERCISES;
            case SearchTabs.TAB_ALL /*6008*/:
                return KEY_SORT_ALL;
            default:
                return null;
        }
    }

    static {
        TAB_ANALYTIC_VALUE.put(Integer.valueOf(6005), "most_used");
        TAB_ANALYTIC_VALUE.put(Integer.valueOf(SearchTabs.TAB_MY_EXERCISES), ListType.MY_EXERCISES);
        TAB_ANALYTIC_VALUE.put(Integer.valueOf(6007), "browse_all");
        TAB_ANALYTIC_VALUE.put(Integer.valueOf(SearchTabs.TAB_FREQUENT_FOODS), "frequent");
        TAB_ANALYTIC_VALUE.put(Integer.valueOf(6001), "recent");
        TAB_ANALYTIC_VALUE.put(Integer.valueOf(6002), ListType.MY_FOODS);
        TAB_ANALYTIC_VALUE.put(Integer.valueOf(6003), "meals");
        TAB_ANALYTIC_VALUE.put(Integer.valueOf(6004), "recipes");
    }

    @Inject
    public SortOrderHelper(@Named("exerciseSortingPreferences") SharedPreferences sharedPreferences, @Named("foodSortingPreferences") SharedPreferences sharedPreferences2, Lazy<LocalSettingsService> lazy) {
        this.exerciseSortingPreferences = sharedPreferences;
        this.foodSortingPreferences = sharedPreferences2;
        this.localSettingsServiceLazy = lazy;
    }

    public SortOrder getCurrentSortOrderForTab(int i) {
        String sortOrderKeyForTab = getSortOrderKeyForTab(i);
        if (Strings.notEmpty(sortOrderKeyForTab)) {
            String string = getPreferencesForTab(i).getString(sortOrderKeyForTab, null);
            if (Strings.notEmpty(string)) {
                return SortOrder.fromShortName(string);
            }
            if (i == FoodSearchTab.ALL.getTabId()) {
                SortOrder convertTabSelectionToSortOrderForAllTab = convertTabSelectionToSortOrderForAllTab();
                if (convertTabSelectionToSortOrderForAllTab != null) {
                    return convertTabSelectionToSortOrderForAllTab;
                }
            }
        }
        switch (i) {
            case SearchTabs.TAB_FREQUENT_FOODS /*6000*/:
            case 6001:
            case 6005:
            case SearchTabs.TAB_ALL /*6008*/:
                return SortOrder.RECENTLY_USED;
            case 6002:
            case 6003:
            case 6004:
                return SortOrder.DATE_DESCENDING;
            case SearchTabs.TAB_MY_EXERCISES /*6006*/:
            case 6007:
                return SortOrder.ALPHABETICAL_ASCENDING;
            default:
                return SortOrder.ALPHABETICAL_ASCENDING;
        }
    }

    public void setCurrentSortOrderForSelectorButton(int i, SortOrder sortOrder) {
        String sortOrderKeyForTab = getSortOrderKeyForTab(i);
        if (Strings.notEmpty(sortOrderKeyForTab)) {
            SharedPreferences preferencesForTab = getPreferencesForTab(i);
            preferencesForTab.edit().putString(sortOrderKeyForTab, sortOrder.getShortName()).apply();
        }
    }

    public static void reportSortOrderChangedEvent(AnalyticsService analyticsService, int i, SortOrder sortOrder, String str) {
        String str2 = (String) TAB_ANALYTIC_VALUE.get(Integer.valueOf(i));
        String anltValue = sortOrder.getAnltValue();
        if (Strings.notEmpty(str2) && Strings.notEmpty(anltValue) && Strings.notEmpty(str)) {
            analyticsService.reportEvent(Events.SORT_ORDER_CHANGE, MapUtil.createMap(Attributes.PAGE_TYPE, str, Attributes.TAB, str2, Attributes.SORT_ORDER, anltValue.toLowerCase()));
        }
    }

    private SharedPreferences getPreferencesForTab(int i) {
        switch (i) {
            case 6005:
            case SearchTabs.TAB_MY_EXERCISES /*6006*/:
            case 6007:
                return this.exerciseSortingPreferences;
            default:
                return this.foodSortingPreferences;
        }
    }

    private SortOrder convertTabSelectionToSortOrderForAllTab() {
        FoodSearchTab fromTabId = FoodSearchTab.fromTabId(((LocalSettingsService) this.localSettingsServiceLazy.get()).getDefaultSearchTab());
        if (fromTabId == FoodSearchTab.RECENT) {
            return SortOrder.RECENTLY_USED;
        }
        if (fromTabId == FoodSearchTab.FREQUENT) {
            return SortOrder.FREQUENTLY_USED;
        }
        return null;
    }
}
