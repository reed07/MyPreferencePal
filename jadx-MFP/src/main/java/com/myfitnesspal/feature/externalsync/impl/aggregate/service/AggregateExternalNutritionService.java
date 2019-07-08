package com.myfitnesspal.feature.externalsync.impl.aggregate.service;

import com.myfitnesspal.feature.externalsync.service.ExternalNutritionService;
import com.myfitnesspal.feature.externalsync.service.ExternalSyncCooldown;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.util.Arrays;
import java.util.List;

public class AggregateExternalNutritionService implements ExternalNutritionService {
    private List<Lazy<? extends ExternalNutritionService>> services;

    public AggregateExternalNutritionService(Lazy<? extends ExternalNutritionService>... lazyArr) {
        this.services = Arrays.asList(lazyArr);
    }

    public void sync() {
        for (Lazy lazy : this.services) {
            try {
                if (((ExternalNutritionService) lazy.get()).enabled()) {
                    ((ExternalNutritionService) lazy.get()).sync();
                }
            } catch (Exception unused) {
                Ln.e("failed to sync nutrition data for %s", ((ExternalNutritionService) lazy.get()).getClass());
            }
        }
    }

    public boolean enabled() {
        for (Lazy lazy : this.services) {
            if (((ExternalNutritionService) lazy.get()).enabled()) {
                return true;
            }
        }
        return false;
    }

    public void onFoodEntryDeleted(FoodEntry foodEntry, String str) {
        ExternalSyncCooldown.clear();
        for (Lazy lazy : this.services) {
            try {
                ((ExternalNutritionService) lazy.get()).onFoodEntryDeleted(foodEntry, str);
            } catch (Exception unused) {
                Ln.e("failed to delete nutrition entry for %s", ((ExternalNutritionService) lazy.get()).getClass());
            }
        }
    }

    public void onFoodEntryInserted(FoodEntry foodEntry, String str) {
        ExternalSyncCooldown.clear();
        for (Lazy lazy : this.services) {
            try {
                ((ExternalNutritionService) lazy.get()).onFoodEntryInserted(foodEntry, str);
            } catch (Exception unused) {
                Ln.e("failed to insert nutrition entry for %s", ((ExternalNutritionService) lazy.get()).getClass());
            }
        }
    }
}
