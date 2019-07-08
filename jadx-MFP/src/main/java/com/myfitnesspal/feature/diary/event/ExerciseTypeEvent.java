package com.myfitnesspal.feature.diary.event;

public class ExerciseTypeEvent {
    public static final int CARDIO = 0;
    public static final int STRENGTH = 1;
    private int exerciseType;

    public ExerciseTypeEvent(int i) {
        this.exerciseType = i;
    }

    public int getExerciseType() {
        return this.exerciseType;
    }
}
