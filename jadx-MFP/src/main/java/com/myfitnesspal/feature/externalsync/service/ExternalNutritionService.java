package com.myfitnesspal.feature.externalsync.service;

import com.myfitnesspal.shared.model.v1.FoodEntry;

public interface ExternalNutritionService extends ExternalService {
    void onFoodEntryDeleted(FoodEntry foodEntry, String str);

    void onFoodEntryInserted(FoodEntry foodEntry, String str);
}
