package com.myfitnesspal.shared.model.v2;

import android.graphics.drawable.Drawable;
import com.myfitnesspal.shared.model.v1.DatabaseObject;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;

public class MfpFoodViewModel extends DatabaseObject implements DiaryEntryCellModel {
    private MfpFood food;

    public Drawable image() {
        return null;
    }

    public boolean isExercise() {
        return false;
    }

    public boolean isExerciseEntry() {
        return false;
    }

    public boolean isFood() {
        return true;
    }

    public boolean isFoodEntry() {
        return false;
    }

    public boolean isMeal() {
        return false;
    }

    public boolean isMealEntries() {
        return false;
    }

    public int itemType() {
        return 1;
    }

    public MfpFoodViewModel(MfpFood mfpFood) {
        this.food = mfpFood;
    }

    public MfpFood getFood() {
        return this.food;
    }

    public String summaryLine1() {
        return this.food.getDescription();
    }
}
