package com.myfitnesspal.feature.addentry.event;

import com.myfitnesspal.shared.constants.Constants.Extras;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/addentry/event/MealSelectedEvent;", "", "mealName", "", "(Ljava/lang/String;)V", "getMealName", "()Ljava/lang/String;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: MealSelectedEvent.kt */
public final class MealSelectedEvent {
    @NotNull
    private final String mealName;

    public MealSelectedEvent(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, Extras.MEAL_NAME);
        this.mealName = str;
    }

    @NotNull
    public final String getMealName() {
        return this.mealName;
    }
}
