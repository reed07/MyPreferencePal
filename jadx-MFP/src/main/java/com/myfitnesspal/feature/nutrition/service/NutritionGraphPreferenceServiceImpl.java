package com.myfitnesspal.feature.nutrition.service;

import com.myfitnesspal.shared.constants.Constants.Graphs.Types;
import com.uacf.core.preferences.KeyedSharedPreferences;

public class NutritionGraphPreferenceServiceImpl implements NutritionGraphPreferenceService {
    private static final String GRAPH_SUB_TYPE = "graphSubType";
    private static final String GRAPH_TYPE = "graphType";
    private static final String IS_WEEKLY = "isWeekly";
    KeyedSharedPreferences prefs;

    public NutritionGraphPreferenceServiceImpl(KeyedSharedPreferences keyedSharedPreferences) {
        this.prefs = keyedSharedPreferences;
    }

    public void setGraphType(String str) {
        this.prefs.edit().putString(GRAPH_TYPE, str).apply();
    }

    public void setGraphSubType(int i) {
        this.prefs.edit().putInt(GRAPH_SUB_TYPE, i).apply();
    }

    public void setIsWeekly(boolean z) {
        this.prefs.edit().putBoolean(IS_WEEKLY, z).apply();
    }

    public String getGraphType() {
        return this.prefs.getString(GRAPH_TYPE, Types.CALORIES);
    }

    public int getGraphSubType() {
        return this.prefs.getInt(GRAPH_SUB_TYPE, 1);
    }

    public boolean isWeekly() {
        return this.prefs.getBoolean(IS_WEEKLY, false);
    }
}
