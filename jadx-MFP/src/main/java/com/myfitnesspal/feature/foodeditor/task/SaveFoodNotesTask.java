package com.myfitnesspal.feature.foodeditor.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodNotes;
import com.myfitnesspal.shared.service.foods.FoodService;
import dagger.Lazy;

public class SaveFoodNotesTask extends EventedTaskBase<FoodNotes, Exception> {
    private final Food food;
    private final Lazy<FoodService> foodService;
    private final String notes;

    public static class CompletedEvent extends TaskEventBase<String, Exception> {
    }

    public SaveFoodNotesTask(Lazy<FoodService> lazy, String str, Food food2) {
        super((TaskEventBase) new CompletedEvent());
        this.foodService = lazy;
        this.notes = str;
        this.food = food2;
    }

    /* access modifiers changed from: protected */
    public FoodNotes exec(Context context) {
        return ((FoodService) this.foodService.get()).saveFoodNotes(this.notes, this.food);
    }
}
