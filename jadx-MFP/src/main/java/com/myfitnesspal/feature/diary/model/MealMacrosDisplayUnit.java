package com.myfitnesspal.feature.diary.model;

import com.myfitnesspal.android.R;

public enum MealMacrosDisplayUnit {
    Percent(R.string.goals_percent, R.string.percent_value),
    Gram(R.string.gram_abbreviation, R.string.gram_value);
    
    private final int unitStringResId;
    private final int unitWithValuePlaceholderStringResId;

    private MealMacrosDisplayUnit(int i, int i2) {
        this.unitStringResId = i;
        this.unitWithValuePlaceholderStringResId = i2;
    }

    public int getUnitStringResId() {
        return this.unitStringResId;
    }

    public int getUnitWithValuePlaceholderStringResId() {
        return this.unitWithValuePlaceholderStringResId;
    }
}
