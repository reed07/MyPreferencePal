package com.myfitnesspal.shared.util;

import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Tuple2;
import dagger.Lazy;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class FoodMigrationAndCorrectionHelper {
    private final Lazy<DbConnectionManager> dbConnectionManager;
    private final Lazy<LocalSettingsService> localSettingsService;

    public FoodMigrationAndCorrectionHelper(Lazy<LocalSettingsService> lazy, Lazy<DbConnectionManager> lazy2) {
        this.localSettingsService = lazy;
        this.dbConnectionManager = lazy2;
    }

    public int fixFoodNutritionInfoInDB() {
        return ((DbConnectionManager) this.dbConnectionManager.get()).nutritionalValuesDBAdapter().fixInvalidNutritionValues();
    }

    public Set<String> migrateAndReturnFoodIdsWithMissingInfo() {
        Set performFoodsDbMigrationIfNeeded = performFoodsDbMigrationIfNeeded();
        Set fetchAndSaveFoodsUidAndVersionWithNoFoodPortionsOrNutritionInfo = fetchAndSaveFoodsUidAndVersionWithNoFoodPortionsOrNutritionInfo();
        HashSet hashSet = new HashSet();
        if (performFoodsDbMigrationIfNeeded != null) {
            hashSet.addAll(performFoodsDbMigrationIfNeeded);
        }
        if (fetchAndSaveFoodsUidAndVersionWithNoFoodPortionsOrNutritionInfo != null) {
            hashSet.addAll(fetchAndSaveFoodsUidAndVersionWithNoFoodPortionsOrNutritionInfo);
        }
        return hashSet;
    }

    public void correctFoodOriginalIdInDB() {
        if (!((LocalSettingsService) this.localSettingsService.get()).wereFoodOriginalIdsCorrected()) {
            ((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter().correctOriginalIdOfFoods();
            ((LocalSettingsService) this.localSettingsService.get()).setWereFoodOriginalIdsCorrected(true);
        }
    }

    private Set<String> performFoodsDbMigrationIfNeeded() {
        LocalSettingsService localSettingsService2 = (LocalSettingsService) this.localSettingsService.get();
        if (localSettingsService2.wasFoodDBMigrated()) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Ln.d("Initiate Food DB migration", new Object[0]);
        Tuple2 migrateFoodInfoBlobData = ((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter().migrateFoodInfoBlobData();
        boolean booleanValue = ((Boolean) migrateFoodInfoBlobData.getItem1()).booleanValue();
        Set<String> set = (Set) migrateFoodInfoBlobData.getItem2();
        localSettingsService2.setFoodDBMigrated(booleanValue);
        StringBuilder sb = new StringBuilder();
        sb.append("Food migration completed successfully? ");
        sb.append(booleanValue);
        sb.append(" Time taken: ");
        sb.append(System.currentTimeMillis() - currentTimeMillis);
        Ln.d(sb.toString(), new Object[0]);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Number of foods with incorrect info: ");
        sb2.append(CollectionUtils.size((Collection<?>) set));
        Ln.d(sb2.toString(), new Object[0]);
        return set;
    }

    private Set<String> fetchAndSaveFoodsUidAndVersionWithNoFoodPortionsOrNutritionInfo() {
        return ((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter().getUidAndVersionOfFoodsWithoutFoodPortionsOrNutritionInfo();
    }
}
