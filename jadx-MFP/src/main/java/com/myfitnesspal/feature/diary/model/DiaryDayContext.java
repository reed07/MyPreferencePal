package com.myfitnesspal.feature.diary.model;

import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;

public class DiaryDayContext {
    private final DiaryDay diaryDay;
    private final MfpNutrientGoal nutrientGoal;

    public DiaryDayContext(DiaryDay diaryDay2, MfpNutrientGoal mfpNutrientGoal) {
        this.diaryDay = diaryDay2;
        this.nutrientGoal = mfpNutrientGoal;
    }

    public DiaryDay getDiaryDay() {
        return this.diaryDay;
    }

    public MfpNutrientGoal getNutrientGoal() {
        return this.nutrientGoal;
    }
}
