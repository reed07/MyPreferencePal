package com.myfitnesspal.feature.goals.event;

import com.myfitnesspal.feature.goals.ui.adapter.GoalItem;

public class WeightLossGoalDialogEvent {
    private final GoalItem item;

    public WeightLossGoalDialogEvent(GoalItem goalItem) {
        this.item = goalItem;
    }

    public GoalItem getItem() {
        return this.item;
    }
}
