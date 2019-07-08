package com.myfitnesspal.feature.meals.task;

import android.content.Context;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.db.table.FoodNotesTable;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodNotes;
import com.myfitnesspal.shared.model.v1.FoodPermission;
import com.myfitnesspal.shared.model.v1.FoodPermission.Permission;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;

public class CheckMealModifiedTask extends Unchecked<Boolean> {
    private final FoodNotesTable foodNotesTable;
    private final Lazy<MealService> mealService;
    private final String newDescription;
    private final String newImage;
    private final List<FoodEntry> newIngredients;
    private final String newNotes;
    private final long newPermission;
    private final MealFood originalMealFood;

    public static class CompletedEvent extends TaskEventBase.Unchecked<Boolean> {
    }

    public CheckMealModifiedTask(Lazy<MealService> lazy, FoodNotesTable foodNotesTable2, MealFood mealFood, String str, List<FoodEntry> list, Permission permission, String str2, String str3) {
        super(CompletedEvent.class);
        this.mealService = lazy;
        this.foodNotesTable = foodNotesTable2;
        this.originalMealFood = mealFood;
        this.newDescription = str;
        this.newIngredients = list;
        this.newPermission = permission.getValue();
        this.newImage = str2;
        this.newNotes = Strings.toString(str3);
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws RuntimeException {
        long j;
        String str;
        boolean z = false;
        if (this.originalMealFood == null) {
            if (Strings.notEmpty(this.newImage) || Strings.notEmpty(this.newDescription) || Strings.notEmpty(this.newNotes) || CollectionUtils.notEmpty((Collection<?>) this.newIngredients)) {
                z = true;
            }
            return Boolean.valueOf(z);
        } else if (Strings.notEmpty(this.newImage)) {
            return Boolean.valueOf(true);
        } else {
            if (!Strings.equals(this.originalMealFood.getDescription(), this.newDescription)) {
                return Boolean.valueOf(true);
            }
            FoodPermission foodPermission = this.originalMealFood.getFoodPermission();
            if (foodPermission == null) {
                j = -1;
            } else {
                j = foodPermission.getPermissionValue();
            }
            if (j != this.newPermission) {
                return Boolean.valueOf(true);
            }
            if (!this.originalMealFood.ingredientsAreSameAs(((MealService) this.mealService.get()).getMealIngredientsFromFoodEntries(this.newIngredients, this.originalMealFood.getLocalId()))) {
                return Boolean.valueOf(true);
            }
            FoodNotes findByFood = this.foodNotesTable.findByFood(this.originalMealFood);
            if (findByFood == null) {
                str = "";
            } else {
                str = Strings.toString(findByFood.getNotes());
            }
            if (!Strings.equals(str, this.newNotes)) {
                return Boolean.valueOf(true);
            }
            return Boolean.valueOf(false);
        }
    }
}
