package com.myfitnesspal.feature.goals.service;

import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;

import java.util.Date;
import java.util.List;

import lanchon.dexpatcher.annotation.DexAction;
import lanchon.dexpatcher.annotation.DexEdit;

@DexEdit(defaultAction = DexAction.IGNORE, contentOnly = true)
public interface NutrientGoalsUtil {

    float getAdjustedNutritionalGoal(DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal, int i);

    float getAdjustedNutritionalGoal(DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal, int i, boolean z);
}
