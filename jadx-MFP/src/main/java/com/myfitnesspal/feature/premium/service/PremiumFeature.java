package com.myfitnesspal.feature.premium.service;

import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.AdFree;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.AssignExerciseCalories;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.Content;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.CustomDailyGoals;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.FileExport;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.FoodList;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.FoodTimestamps;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.Graphs;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.MealGoals;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.MealMacros;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.NutrientDashboard;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.QuickAddMacros;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.TrackMacrosByGram;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.Upsell;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.VerifiedFoods;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.WeeklyDigestFeature;
import com.myfitnesspal.shared.constants.Constants.Premium;
import com.uacf.core.util.Strings;

public enum PremiumFeature {
    QuickAddMacros(Premium.QUICK_ADD_FEATURE_ID, QuickAddMacros.NAME),
    TrackMacrosByGram(Premium.TRACK_MACROS_FEATURE_ID, TrackMacrosByGram.NAME),
    AssignExerciseCalories(Premium.ASSIGN_EXERCISE_FEATURE_ID, AssignExerciseCalories.NAME),
    NutrientDashboard(Premium.NUTRIENT_DASHBOARD_FEATURE_ID, NutrientDashboard.NAME),
    CustomDailyGoals(Premium.CUSTOM_DAILY_GOALS_FEATURE_ID, CustomDailyGoals.NAME),
    Graphs(Premium.GRAPHS_FEATURE_ID, Graphs.NAME),
    Content(Premium.CONTENT_FEATURE_ID, Content.NAME),
    AdFree(Premium.AD_FREE_FEATURE_ID, AdFree.NAME),
    FoodLists(Premium.FOOD_LISTS_FEATURE_ID, FoodList.NAME),
    VerifiedFoods(Premium.VERIFIED_FOODS_FEATURE_ID, VerifiedFoods.NAME),
    PrioritySupport(Premium.PRIORITY_SUPPORT_FEATURE_ID, "premium-entry-points-android-v1a"),
    Upsell(Premium.UPSELL_FEATURE_ID, Upsell.NAME),
    MealMacros(Premium.MEAL_MACRO_FEATURE_ID, MealMacros.NAME),
    FileExport(Premium.FILE_EXPORT_FEATURE_ID, FileExport.NAME),
    MealGoals(Premium.MEAL_GOALS_ID, MealGoals.NAME),
    WeeklyDigest(Premium.WEEKLY_DIGEST_ID, WeeklyDigestFeature.NAME),
    FoodTimestamps(Premium.FOOD_TIMESTAMPS, FoodTimestamps.NAME);
    
    private String featureId;
    private String rolloutId;

    private PremiumFeature(String str, String str2) {
        this.featureId = str;
        this.rolloutId = str2;
    }

    public String getFeatureId() {
        return this.featureId;
    }

    public String getRolloutId() {
        return this.rolloutId;
    }

    public static PremiumFeature getFeature(String str) {
        PremiumFeature[] values;
        for (PremiumFeature premiumFeature : values()) {
            if (Strings.equals(premiumFeature.getFeatureId(), str)) {
                return premiumFeature;
            }
        }
        return null;
    }
}
