package com.myfitnesspal.shared.model.v1;

import android.graphics.drawable.Drawable;

public interface DiaryEntryCellModel {
    Drawable image();

    boolean isExercise();

    boolean isExerciseEntry();

    boolean isFood();

    boolean isFoodEntry();

    boolean isMealEntries();

    int itemType();

    String summaryLine1();
}
