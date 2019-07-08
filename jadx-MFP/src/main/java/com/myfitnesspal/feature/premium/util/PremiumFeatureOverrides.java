package com.myfitnesspal.feature.premium.util;

import android.content.SharedPreferences;
import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.uacf.core.util.Strings;
import java.util.HashMap;
import java.util.Map;

public class PremiumFeatureOverrides {
    private static final String AD_FREE_KEY = "ad-free";
    private static final String ASSIGN_EXERCISE_CALORIES_KEY = "assign-exercise-calories";
    private static final String CONTENT_KEY = "content";
    private static final String DISPLAY_MACROS_KEY = "display-macros";
    private static final Map<PremiumFeature, String> FEATURE_TO_STATE_KEY = new HashMap();
    private static final String FILE_EXPORT_KEY = "file-export";
    private static final String FOOD_LISTS_KEY = "food-lists";
    private static final String FOOD_TIMESTAMPS = "food-timestamps";
    private static final String GLOBAL_TOGGLE_KEY = "global-toggle";
    private static final String GRAPHS_KEY = "graphs";
    private static final String MACROS_BY_DAY_KEY = "macros-by-day";
    private static final String MEAL_GOALS_KEY = "meal-goals";
    private static final String MEAL_MACROS_KEY = "meal-macros";
    private static final String QUICK_ADD_MACROS_KEY = "quick-add";
    private static final String STATE_AVAILABLE = "-available";
    private static final String STATE_ENABLED = "-enabled";
    private static final String STATE_KEY = "saved_state";
    private static final String STATE_SUBSCRIBED = "-subscribed";
    private static final String TRACK_MACROS_BY_GRAMS_KEY = "track-by-grams";
    private static final String UPSELL_KEY = "upsell";
    private static final String VERIFIED_FOODS_KEY = "verified-foods";
    private ApiJsonMapper mapper = new ApiJsonMapper();
    private SharedPreferences prefs;
    private Map<String, Boolean> state;

    public static class OverrideState {
        private boolean isAvailable;
        private boolean isEnabled;
        private boolean isSubscribed;

        public OverrideState(boolean z, boolean z2, boolean z3) {
            this.isAvailable = z;
            this.isEnabled = z2;
            this.isSubscribed = z3;
        }

        public boolean isAvailable() {
            return this.isAvailable;
        }

        public boolean isEnabled() {
            return this.isEnabled;
        }

        public boolean isSubscribed() {
            return this.isSubscribed;
        }
    }

    private static final class STATE_MAPPER_TYPE extends HashMap<String, Boolean> {
        private STATE_MAPPER_TYPE() {
        }
    }

    static {
        FEATURE_TO_STATE_KEY.put(PremiumFeature.AdFree, AD_FREE_KEY);
        FEATURE_TO_STATE_KEY.put(PremiumFeature.QuickAddMacros, QUICK_ADD_MACROS_KEY);
        FEATURE_TO_STATE_KEY.put(PremiumFeature.TrackMacrosByGram, TRACK_MACROS_BY_GRAMS_KEY);
        FEATURE_TO_STATE_KEY.put(PremiumFeature.AssignExerciseCalories, ASSIGN_EXERCISE_CALORIES_KEY);
        FEATURE_TO_STATE_KEY.put(PremiumFeature.NutrientDashboard, DISPLAY_MACROS_KEY);
        FEATURE_TO_STATE_KEY.put(PremiumFeature.CustomDailyGoals, MACROS_BY_DAY_KEY);
        FEATURE_TO_STATE_KEY.put(PremiumFeature.Graphs, GRAPHS_KEY);
        FEATURE_TO_STATE_KEY.put(PremiumFeature.FoodLists, FOOD_LISTS_KEY);
        FEATURE_TO_STATE_KEY.put(PremiumFeature.Content, "content");
        FEATURE_TO_STATE_KEY.put(PremiumFeature.VerifiedFoods, VERIFIED_FOODS_KEY);
        FEATURE_TO_STATE_KEY.put(PremiumFeature.Upsell, UPSELL_KEY);
        FEATURE_TO_STATE_KEY.put(PremiumFeature.MealMacros, MEAL_MACROS_KEY);
        FEATURE_TO_STATE_KEY.put(PremiumFeature.FileExport, FILE_EXPORT_KEY);
        FEATURE_TO_STATE_KEY.put(PremiumFeature.MealGoals, MEAL_GOALS_KEY);
        FEATURE_TO_STATE_KEY.put(PremiumFeature.FoodTimestamps, FOOD_TIMESTAMPS);
    }

    public PremiumFeatureOverrides(SharedPreferences sharedPreferences) {
        this.prefs = sharedPreferences;
        load();
    }

    public void save(Map<String, Boolean> map) {
        if (enabledByRuntimeConfiguration()) {
            this.prefs.edit().putString(STATE_KEY, this.mapper.reverseMap((Object) map)).apply();
            this.state = map;
        }
    }

    private void load() {
        Map<String, Boolean> map = null;
        if (enabledByRuntimeConfiguration()) {
            String string = this.prefs.getString(STATE_KEY, null);
            if (Strings.notEmpty(string)) {
                map = (Map) this.mapper.tryMapFrom(string, STATE_MAPPER_TYPE.class);
            }
        }
        if (map == null) {
            map = new HashMap<>();
        }
        this.state = map;
    }

    public OverrideState getOverrideFor(PremiumFeature premiumFeature) {
        if (areOverridesEnabled()) {
            return getOverrideConfigFor(premiumFeature);
        }
        return null;
    }

    public OverrideState getOverrideFor_CONFIG_DEBUG_ONLY(PremiumFeature premiumFeature) {
        return getOverrideConfigFor(premiumFeature);
    }

    private OverrideState getOverrideConfigFor(PremiumFeature premiumFeature) {
        String str = (String) FEATURE_TO_STATE_KEY.get(premiumFeature);
        if (!Strings.notEmpty(str)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(STATE_AVAILABLE);
        boolean stateValueFor = getStateValueFor(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(STATE_ENABLED);
        boolean stateValueFor2 = getStateValueFor(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str);
        sb3.append(STATE_SUBSCRIBED);
        return new OverrideState(stateValueFor, stateValueFor2, getStateValueFor(sb3.toString()));
    }

    public void setOverridesEnabled(boolean z) {
        if (enabledByRuntimeConfiguration()) {
            this.state.put(GLOBAL_TOGGLE_KEY, Boolean.valueOf(z));
            save(this.state);
        }
    }

    public boolean areOverridesEnabled() {
        return enabledByRuntimeConfiguration() && getStateValueFor(GLOBAL_TOGGLE_KEY);
    }

    private boolean getStateValueFor(String str) {
        return this.state.containsKey(str) && ((Boolean) this.state.get(str)).booleanValue();
    }

    private boolean enabledByRuntimeConfiguration() {
        BuildConfiguration buildConfiguration = BuildConfiguration.getBuildConfiguration();
        return buildConfiguration.isDebug() || buildConfiguration.isQABuild();
    }
}
