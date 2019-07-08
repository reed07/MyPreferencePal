package com.myfitnesspal.feature.meals.task;

import android.content.Context;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.feature.meals.service.MealService.CreateMode;
import com.myfitnesspal.feature.meals.service.MealService.ImageMode;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodPermission.Permission;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.service.foods.FoodPermissionsService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.List;

public class CrudMealFoodTask extends Unchecked<MealFood> {
    private final CreateMode createMode;
    private final String description;
    private final List<FoodEntry> foodEntries;
    private final Lazy<FoodPermissionsService> foodPermissionsService;
    private final Lazy<FoodService> foodService;
    private final ImageMode imageMode;
    private final String imagePath;
    private final MealFood mealFoodToDelete;
    private final Lazy<MealService> mealService;
    private final String notes;
    private final MealFood originalMealFood;
    private final Permission permission;
    private final long promotedFromMasterId;
    private final String promotedFromUid;

    public static class CompletedEvent extends TaskEventBase.Unchecked<MealFood> {
    }

    public static CrudMealFoodTask newInstanceForUpdatingExistingMeal(Lazy<MealService> lazy, Lazy<FoodService> lazy2, Lazy<FoodPermissionsService> lazy3, CreateMode createMode2, String str, List<FoodEntry> list, MealFood mealFood, Permission permission2, ImageMode imageMode2, String str2, String str3) {
        CrudMealFoodTask crudMealFoodTask = new CrudMealFoodTask(lazy, lazy2, lazy3, createMode2, str, list, mealFood, permission2, imageMode2, str2, null, 0, null, str3);
        return crudMealFoodTask;
    }

    public static CrudMealFoodTask newInstanceWithFoodToDelete(Lazy<MealService> lazy, Lazy<FoodService> lazy2, Lazy<FoodPermissionsService> lazy3, CreateMode createMode2, String str, List<FoodEntry> list, MealFood mealFood, Permission permission2, ImageMode imageMode2, String str2, MealFood mealFood2, String str3) {
        CrudMealFoodTask crudMealFoodTask = new CrudMealFoodTask(lazy, lazy2, lazy3, createMode2, str, list, mealFood, permission2, imageMode2, str2, mealFood2, 0, null, str3);
        return crudMealFoodTask;
    }

    public static CrudMealFoodTask newInstanceForCopyingSharedMeal(Lazy<MealService> lazy, Lazy<FoodService> lazy2, Lazy<FoodPermissionsService> lazy3, CreateMode createMode2, String str, List<FoodEntry> list, MealFood mealFood, Permission permission2, ImageMode imageMode2, String str2, long j, String str3, String str4) {
        CrudMealFoodTask crudMealFoodTask = new CrudMealFoodTask(lazy, lazy2, lazy3, createMode2, str, list, mealFood, permission2, imageMode2, str2, null, j, str3, str4);
        return crudMealFoodTask;
    }

    private CrudMealFoodTask(Lazy<MealService> lazy, Lazy<FoodService> lazy2, Lazy<FoodPermissionsService> lazy3, CreateMode createMode2, String str, List<FoodEntry> list, MealFood mealFood, Permission permission2, ImageMode imageMode2, String str2, MealFood mealFood2, long j, String str3, String str4) {
        super(CompletedEvent.class);
        this.mealService = lazy;
        this.foodService = lazy2;
        this.foodPermissionsService = lazy3;
        this.createMode = createMode2;
        this.description = str;
        this.foodEntries = list;
        this.originalMealFood = mealFood;
        this.permission = permission2;
        this.imageMode = imageMode2;
        this.imagePath = str2;
        this.mealFoodToDelete = mealFood2;
        this.promotedFromMasterId = j;
        this.promotedFromUid = str3;
        this.notes = str4;
    }

    /* access modifiers changed from: protected */
    public MealFood exec(Context context) {
        MealFood mealFood;
        if (this.mealFoodToDelete != null) {
            ((MealService) this.mealService.get()).deleteMealFood(this.mealFoodToDelete);
        }
        if (areMealFoodsIdentical(this.originalMealFood, this.description, this.foodEntries)) {
            mealFood = this.originalMealFood;
            if ((mealFood.getFoodPermission() == null && this.permission != null) || this.permission.getValue() != mealFood.getFoodPermission().getPermissionValue()) {
                ((FoodPermissionsService) this.foodPermissionsService.get()).deletePermission(mealFood.getLocalId(), mealFood.getOwnerUserId());
                mealFood.setFoodPermission(((FoodPermissionsService) this.foodPermissionsService.get()).createAndSaveFoodPermission(mealFood, this.permission.getValue()));
            }
            if (Strings.notEmpty(this.imagePath) && this.imageMode == ImageMode.Associate) {
                ((MealService) this.mealService.get()).associateMealImages(mealFood, null, this.imagePath);
            } else if (this.imageMode == ImageMode.Disassociate) {
                ((MealService) this.mealService.get()).disassociateMealImage(mealFood);
            }
        } else {
            mealFood = createMealFoodAndPermission(this.createMode, this.description, this.foodEntries, this.originalMealFood, this.permission, this.imageMode, this.imagePath, this.promotedFromMasterId, this.promotedFromUid);
        }
        if (mealFood != null) {
            ((FoodService) this.foodService.get()).saveFoodNotes(this.notes, mealFood);
        }
        return mealFood;
    }

    private MealFood createMealFoodAndPermission(CreateMode createMode2, String str, List<FoodEntry> list, MealFood mealFood, Permission permission2, ImageMode imageMode2, String str2, long j, String str3) {
        MealFood createCustomMeal = ((MealService) this.mealService.get()).createCustomMeal(createMode2, str, list, mealFood, permission2, imageMode2, str2, j, str3);
        if (createCustomMeal != null) {
            createCustomMeal.setFoodPermission(((FoodPermissionsService) this.foodPermissionsService.get()).createAndSaveFoodPermission(createCustomMeal, permission2.getValue()));
        }
        return createCustomMeal;
    }

    private boolean areMealFoodsIdentical(MealFood mealFood, String str, List<FoodEntry> list) {
        boolean z = false;
        if (mealFood == null) {
            return false;
        }
        List mealIngredientsFromFoodEntries = ((MealService) this.mealService.get()).getMealIngredientsFromFoodEntries(list, mealFood.getLocalId());
        if (Strings.equals(str, mealFood.getDescription()) && mealFood.ingredientsAreSameAs(mealIngredientsFromFoodEntries)) {
            z = true;
        }
        return z;
    }
}
