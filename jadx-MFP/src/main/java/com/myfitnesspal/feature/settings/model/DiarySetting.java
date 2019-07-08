package com.myfitnesspal.feature.settings.model;

import com.myfitnesspal.android.R;

public enum DiarySetting {
    MealMacros(R.id.settings_macros, R.string.show_meal_macros),
    FoodInsights(R.id.settings_insights, R.string.show_diary_food_insights),
    WaterCard(R.id.settings_water, R.string.show_water_card),
    CustomMealNames(R.id.settings_meal_name, R.string.custom_meal_names_settings),
    CustomNutrientDashboard(R.id.settings_dashboard, R.string.customize_nutrient_dashboard),
    DiarySharing(R.id.settings_diary_sharing, R.string.diary_sharing_settings),
    DefaultSearchTab(R.id.settings_search_tab, R.string.default_search_tab_settings),
    MultiAdd(R.id.settings_multiadd, R.string.always_use_multi_add),
    ShowAllFoods(R.id.settings_all_foods, R.string.diary_settings_show_all_foods_title),
    EditGoals(R.id.settings_goals, R.string.diary_settings_edit_goals),
    FoodTimestamps(R.id.settings_food_timestamps, R.string.diary_settings_show_food_timestamps);
    
    private final int layoutResId;
    private final int stringResId;

    private DiarySetting(int i, int i2) {
        this.layoutResId = i;
        this.stringResId = i2;
    }

    public int getStringResId() {
        return this.stringResId;
    }

    public int getLayoutResId() {
        return this.layoutResId;
    }
}
