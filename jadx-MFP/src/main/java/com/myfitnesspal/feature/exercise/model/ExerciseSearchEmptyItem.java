package com.myfitnesspal.feature.exercise.model;

public class ExerciseSearchEmptyItem implements ExerciseSearchAdapterItem {
    private final EmptyItemType emptyItemType;

    public enum EmptyItemType {
        NoLocalItems,
        NoFilteredItems,
        NoOnlineItems
    }

    public ExerciseSearchEmptyItem(EmptyItemType emptyItemType2) {
        this.emptyItemType = emptyItemType2;
    }

    public EmptyItemType getEmptyItemType() {
        return this.emptyItemType;
    }
}
