package com.myfitnesspal.feature.goals.event;

import com.myfitnesspal.shared.event.MfpEventBase;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/myfitnesspal/feature/goals/event/GoalPreferencesUpdatedEvent;", "Lcom/myfitnesspal/shared/event/MfpEventBase;", "workoutsPerWeek", "", "minutesPerWorkout", "(II)V", "getMinutesPerWorkout", "()I", "getWorkoutsPerWeek", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: GoalPreferencesUpdatedEvent.kt */
public final class GoalPreferencesUpdatedEvent extends MfpEventBase {
    private final int minutesPerWorkout;
    private final int workoutsPerWeek;

    public GoalPreferencesUpdatedEvent(int i, int i2) {
        this.workoutsPerWeek = i;
        this.minutesPerWorkout = i2;
    }

    public final int getMinutesPerWorkout() {
        return this.minutesPerWorkout;
    }

    public final int getWorkoutsPerWeek() {
        return this.workoutsPerWeek;
    }
}
