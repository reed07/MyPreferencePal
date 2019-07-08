package com.myfitnesspal.feature.foodeditor.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.db.table.FoodNotesTable;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodNotes;

public class FetchFoodNotesTask extends EventedTaskBase<String, Exception> {
    private final Food food;
    private final FoodNotesTable foodNotesTable;

    public static class CompletedEvent extends TaskEventBase<String, Exception> {
    }

    public FetchFoodNotesTask(FoodNotesTable foodNotesTable2, Food food2) {
        super((TaskEventBase) new CompletedEvent());
        this.foodNotesTable = foodNotesTable2;
        this.food = food2;
    }

    /* access modifiers changed from: protected */
    public String exec(Context context) {
        Food food2 = this.food;
        if (food2 != null) {
            FoodNotes findByFood = this.foodNotesTable.findByFood(food2);
            if (findByFood != null) {
                return findByFood.getNotes();
            }
        }
        return null;
    }
}
