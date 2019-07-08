package com.myfitnesspal.shared.service.foods;

import android.support.annotation.NonNull;
import com.myfitnesspal.shared.db.table.FoodPermissionsTable;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodPermission;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;

public class FoodPermissionsServiceImpl implements FoodPermissionsService {
    private final Lazy<FoodPermissionsTable> foodPermissionsTable;
    private final Lazy<Session> session;

    public FoodPermissionsServiceImpl(Lazy<Session> lazy, Lazy<FoodPermissionsTable> lazy2) {
        this.session = lazy;
        this.foodPermissionsTable = lazy2;
    }

    public FoodPermission createAndSaveFoodPermission(@NonNull Food food, long j) {
        FoodPermission foodPermission = new FoodPermission();
        foodPermission.setFoodLocalId(food.getLocalId());
        foodPermission.setFoodMasterId(food.getMasterDatabaseId());
        foodPermission.setFoodUid(food.getUid());
        foodPermission.setOriginalFoodMasterId(food.getOriginalMasterId());
        foodPermission.setOriginalFoodUid(food.getOriginalUid());
        foodPermission.setUserId(((Session) this.session.get()).getUser().getLocalId());
        foodPermission.setPermissionValue(j);
        foodPermission.setId(((FoodPermissionsTable) this.foodPermissionsTable.get()).save(foodPermission));
        return foodPermission;
    }

    public void updatePermissionFromServer(FoodPermission foodPermission) {
        foodPermission.setUserId(((Session) this.session.get()).getUser().getLocalId());
        ((FoodPermissionsTable) this.foodPermissionsTable.get()).updatePermissionFromServer(foodPermission);
    }

    public FoodPermission getFromLocalId(long j) {
        return ((FoodPermissionsTable) this.foodPermissionsTable.get()).fetchByLocalId(j);
    }

    public FoodPermission getFromMasterId(long j) {
        return ((FoodPermissionsTable) this.foodPermissionsTable.get()).fetchByMasterId(j);
    }

    public FoodPermission getFromFoodLocalId(long j) {
        return ((FoodPermissionsTable) this.foodPermissionsTable.get()).fetchByFoodLocalId(j);
    }

    public FoodPermission getFromFoodMasterId(long j) {
        return ((FoodPermissionsTable) this.foodPermissionsTable.get()).fetchByFoodMasterId(j);
    }

    public FoodPermission getFromFoodUid(String str) {
        return ((FoodPermissionsTable) this.foodPermissionsTable.get()).fetchByFoodUid(str);
    }

    public void updateFoodPermissionForFood(MfpFood mfpFood, int i) {
        ((FoodPermissionsTable) this.foodPermissionsTable.get()).updateFoodPermissionForFood(mfpFood, i, ((Session) this.session.get()).getUser().getLocalId());
    }

    public void deletePermission(long j, long j2) {
        ((FoodPermissionsTable) this.foodPermissionsTable.get()).deletePermission(j, j2);
    }
}
