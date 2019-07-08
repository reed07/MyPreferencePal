package com.myfitnesspal.feature.meals.task;

import android.content.Context;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.model.v1.MealFood;
import dagger.Lazy;

public class MealFoodNameConflictCheckTask extends Unchecked<MealFood> {
    private final String mealName;
    private final Lazy<MealService> mealService;

    public static class CompletedEvent extends TaskEventBase.Unchecked<MealFood> {
        private final String mealName;

        public CompletedEvent(String str) {
            this.mealName = str;
        }

        public String getMealName() {
            return this.mealName;
        }
    }

    public MealFoodNameConflictCheckTask(Lazy<MealService> lazy, String str) {
        super((TaskEventBase) new CompletedEvent(str));
        this.mealService = lazy;
        this.mealName = str;
    }

    /* access modifiers changed from: protected */
    public MealFood exec(Context context) {
        return ((MealService) this.mealService.get()).getExistingMealFoodWithDescription(this.mealName);
    }
}
