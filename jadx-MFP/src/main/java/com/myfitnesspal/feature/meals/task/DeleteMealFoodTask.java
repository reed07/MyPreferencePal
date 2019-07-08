package com.myfitnesspal.feature.meals.task;

import android.content.Context;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.model.v1.MealFood;
import dagger.Lazy;

public class DeleteMealFoodTask extends Unchecked<Boolean> {
    private final MealFood mealFood;
    private final Lazy<MealService> mealService;

    public static class CompletedEvent extends TaskEventBase.Unchecked<Boolean> {
    }

    public DeleteMealFoodTask(Lazy<MealService> lazy, MealFood mealFood2) {
        super(CompletedEvent.class);
        this.mealService = lazy;
        this.mealFood = mealFood2;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws RuntimeException {
        ((MealService) this.mealService.get()).deleteMealFood(this.mealFood);
        return Boolean.valueOf(true);
    }
}
