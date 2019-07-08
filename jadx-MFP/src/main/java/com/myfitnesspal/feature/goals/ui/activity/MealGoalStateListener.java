package com.myfitnesspal.feature.goals.ui.activity;

import com.myfitnesspal.shared.model.v2.MfpDailyGoal;

public interface MealGoalStateListener {
    void onMealGoalFocusChanged();

    void onMealGoalStateChanged(boolean z);

    void persistDailyGoal(MfpDailyGoal mfpDailyGoal);
}
