package com.myfitnesspal.feature.goals.event;

import com.myfitnesspal.shared.event.MfpEventBase;
import com.uacf.core.util.Strings;

public class GoalUpdatedEvent extends MfpEventBase {
    private double doubleGoalValue;
    private String goalId;
    private int goalValue;
    private boolean isDouble;

    public GoalUpdatedEvent() {
    }

    public GoalUpdatedEvent(String str, int i) {
        this.goalId = str;
        this.goalValue = i;
        this.isDouble = false;
    }

    public GoalUpdatedEvent(String str, double d) {
        this.goalId = str;
        this.doubleGoalValue = d;
        this.isDouble = true;
    }

    public boolean containsValues() {
        return Strings.notEmpty(this.goalId);
    }

    public int getGoalValue() {
        return this.goalValue;
    }

    public double getDoubleGoalValue() {
        return this.doubleGoalValue;
    }

    public String getGoalId() {
        return this.goalId;
    }

    public boolean isDouble() {
        return this.isDouble;
    }
}
