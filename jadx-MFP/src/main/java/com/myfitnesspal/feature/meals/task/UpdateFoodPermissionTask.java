package com.myfitnesspal.feature.meals.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.model.v1.FoodPermission;
import com.myfitnesspal.shared.model.v1.FoodPermission.Permission;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.service.foods.FoodPermissionsService;
import dagger.Lazy;

public class UpdateFoodPermissionTask extends Unchecked<FoodPermission> {
    private final Lazy<FoodPermissionsService> foodPermissionsService;
    private final MealFood mealFood;
    private final Permission updatedPermission;

    public static class CompletedEvent extends TaskEventBase.Unchecked<FoodPermission> {
    }

    public UpdateFoodPermissionTask(Lazy<FoodPermissionsService> lazy, Permission permission, MealFood mealFood2) {
        super(CompletedEvent.class);
        this.foodPermissionsService = lazy;
        this.updatedPermission = permission;
        this.mealFood = mealFood2;
    }

    /* access modifiers changed from: protected */
    public FoodPermission exec(Context context) throws RuntimeException {
        ((FoodPermissionsService) this.foodPermissionsService.get()).deletePermission(this.mealFood.getLocalId(), this.mealFood.getOwnerUserId());
        return ((FoodPermissionsService) this.foodPermissionsService.get()).createAndSaveFoodPermission(this.mealFood, this.updatedPermission.getValue());
    }
}
