package com.myfitnesspal.feature.meals.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v1.FoodNotes;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.uacf.core.util.Tuple2;
import dagger.Lazy;

public class RetrieveMealFoodTask extends EventedTaskBase<Tuple2<MealFood, FoodNotes>, ApiException> {
    private final String foodId;
    private final Lazy<FoodService> foodService;
    private final boolean isCurrentUsersMeal;

    public static class CompletedEvent extends TaskEventBase<Tuple2<MealFood, FoodNotes>, ApiException> {
    }

    public RetrieveMealFoodTask(Lazy<FoodService> lazy, String str, boolean z) {
        super(CompletedEvent.class);
        this.foodId = str;
        this.foodService = lazy;
        this.isCurrentUsersMeal = z;
    }

    /* access modifiers changed from: protected */
    public Tuple2<MealFood, FoodNotes> exec(Context context) throws ApiException {
        return ((FoodService) this.foodService.get()).getMealFoodForId(this.foodId, this.isCurrentUsersMeal);
    }
}
