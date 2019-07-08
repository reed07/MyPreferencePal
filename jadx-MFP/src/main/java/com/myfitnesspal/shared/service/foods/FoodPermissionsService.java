package com.myfitnesspal.shared.service.foods;

import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodPermission;
import com.myfitnesspal.shared.model.v2.MfpFood;

public interface FoodPermissionsService {
    FoodPermission createAndSaveFoodPermission(Food food, long j);

    void deletePermission(long j, long j2);

    FoodPermission getFromFoodLocalId(long j);

    FoodPermission getFromFoodMasterId(long j);

    FoodPermission getFromFoodUid(String str);

    FoodPermission getFromLocalId(long j);

    FoodPermission getFromMasterId(long j);

    void updateFoodPermissionForFood(MfpFood mfpFood, int i);

    void updatePermissionFromServer(FoodPermission foodPermission);
}
