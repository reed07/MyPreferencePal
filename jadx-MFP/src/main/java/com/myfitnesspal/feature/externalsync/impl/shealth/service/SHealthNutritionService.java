package com.myfitnesspal.feature.externalsync.impl.shealth.service;

import android.content.Context;
import android.database.Cursor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.externalsync.impl.shealth.model.SHealthDailyNutrition;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection.Permission;
import com.myfitnesspal.feature.externalsync.service.ExternalNutritionService;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.FoodEntriesDBAdapter;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.Database;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.samsung.android.sdk.healthdata.HealthConstants.FoodInfo;
import com.samsung.android.sdk.healthdata.HealthConstants.FoodIntake;
import com.samsung.android.sdk.healthdata.HealthData;
import com.samsung.android.sdk.healthdata.HealthDataResolver;
import com.samsung.android.sdk.healthdata.HealthDataResolver.DeleteRequest.Builder;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter;
import com.samsung.android.sdk.healthdata.HealthDataResolver.InsertRequest;
import com.samsung.android.sdk.healthdata.HealthDataResolver.ReadRequest;
import com.samsung.android.sdk.healthdata.HealthDataResolver.ReadResult;
import com.samsung.android.sdk.healthdata.HealthDataStore;
import com.samsung.android.sdk.healthdata.HealthResultHolder.BaseResult;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

public class SHealthNutritionService extends SHealthServiceBase implements ExternalNutritionService {
    private static final Set<Permission> READ_PERMISSIONS = new HashSet();
    private static final int[] SAMSUNG_MEAL_NAMES = {FoodIntake.MEAL_TYPE_BREAKFAST, FoodIntake.MEAL_TYPE_MORNING_SNACK, FoodIntake.MEAL_TYPE_LUNCH, FoodIntake.MEAL_TYPE_AFTERNOON_SNACK, FoodIntake.MEAL_TYPE_DINNER, FoodIntake.MEAL_TYPE_EVENING_SNACK};
    private static final Set<Permission> WRITE_PERMISSIONS = new HashSet();
    private final Context context = MyFitnessPalApp.getInstance();
    private final HashMap<String, Boolean> daysThatNeedSync = new HashMap<>();
    private final FoodEntriesDBAdapter foodEntriesDbAdapter;
    private final Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private final Lazy<UserEnergyService> userEnergyService;

    private static float calculateValue(float f, float f2) {
        float f3 = f * f2;
        return f3 < BitmapDescriptorFactory.HUE_RED ? BitmapDescriptorFactory.HUE_RED : f3;
    }

    /* access modifiers changed from: protected */
    public void syncRead() {
    }

    static {
        READ_PERMISSIONS.add(Permission.ReadFood);
        READ_PERMISSIONS.add(Permission.ReadFoodEntry);
        WRITE_PERMISSIONS.add(Permission.WriteFood);
        WRITE_PERMISSIONS.add(Permission.WriteFoodEntry);
    }

    public SHealthNutritionService(SHealthConnection sHealthConnection, Lazy<ConfigService> lazy, Lazy<LocalizedStringsUtil> lazy2, Lazy<UserEnergyService> lazy3, Lazy<AppGalleryService> lazy4, DbConnectionManager dbConnectionManager) {
        super(sHealthConnection, lazy, lazy4);
        this.localizedStringsUtil = lazy2;
        this.userEnergyService = lazy3;
        this.foodEntriesDbAdapter = dbConnectionManager.foodEntriesDbAdapter();
    }

    public void onFoodEntryDeleted(FoodEntry foodEntry, String str) {
        markPending(foodEntry.getDate());
    }

    public void onFoodEntryInserted(FoodEntry foodEntry, String str) {
        markPending(foodEntry.getDate());
    }

    private void markPending(Date date) {
        if (isWithinSyncDateRange(date)) {
            this.daysThatNeedSync.put(Database.encodeDate(date), Boolean.valueOf(true));
        }
    }

    /* access modifiers changed from: protected */
    public void syncWrite() {
        if (enabled()) {
            for (int i = -2; i <= 0; i++) {
                Date dateForDayWithOffset = getDateForDayWithOffset(i);
                String encodeDate = Database.encodeDate(dateForDayWithOffset);
                Boolean bool = (Boolean) this.daysThatNeedSync.get(encodeDate);
                if (bool == null || bool.booleanValue()) {
                    writeMealNutritionForDate(dateForDayWithOffset);
                    this.daysThatNeedSync.put(encodeDate, Boolean.valueOf(false));
                }
            }
        }
    }

    private void removeFoodInfo(int i, Date date) {
        HealthDataStore dataStore = getDataStore();
        if (dataStore != null) {
            String packageName = getPackageName();
            new HealthDataResolver(dataStore, getHandler()).delete(new Builder().setDataType(FoodInfo.HEALTH_DATA_TYPE).setFilter(Filter.and(Filter.eq(FoodInfo.PROVIDER_FOOD_ID, getMfpFoodIdForMeal(i, date)), Filter.eq("pkg_name", packageName))).build()).await();
        }
    }

    private void writeMealNutritionForDate(Date date) {
        removeMfpResourcesFromSHealthOnDate(FoodIntake.HEALTH_DATA_TYPE, date);
        SHealthDailyNutrition sHealthDailyNutrition = new SHealthDailyNutrition(getContext(), this.foodEntriesDbAdapter.fetchFoodEntriesOnDate(date), this.localizedStringsUtil, this.userEnergyService);
        int i = 0;
        while (true) {
            int[] iArr = SAMSUNG_MEAL_NAMES;
            if (i < iArr.length) {
                int i2 = iArr[i];
                List foodEntriesForMealId = sHealthDailyNutrition.getFoodEntriesForMealId(i2);
                if (foodEntriesForMealId.size() > 0) {
                    float[] aggregateNutritionalValues = aggregateNutritionalValues(foodEntriesForMealId);
                    if (aggregateNutritionalValues[0] > BitmapDescriptorFactory.HUE_RED) {
                        removeFoodInfo(i2, date);
                        writeFoodForDate(aggregateNutritionalValues, i2, date);
                        writeFoodEntryForDate(aggregateNutritionalValues, i2, date);
                    }
                }
                i++;
            } else {
                return;
            }
        }
    }

    private static String getMfpFoodIdForMeal(int i, Date date) {
        return String.format("%s-%s", new Object[]{Integer.valueOf(i), date});
    }

    private String getSHealthFoodId(String str) {
        HealthDataStore dataStore = getDataStore();
        if (dataStore != null) {
            ReadResult readResult = (ReadResult) new HealthDataResolver(dataStore, getHandler()).read(new ReadRequest.Builder().setDataType(FoodInfo.HEALTH_DATA_TYPE).setFilter(Filter.eq(FoodInfo.PROVIDER_FOOD_ID, str)).build()).await();
            if (!(readResult == null || readResult.getResultCursor() == null)) {
                Cursor resultCursor = readResult.getResultCursor();
                try {
                    if (resultCursor.moveToNext()) {
                        return resultCursor.getString(resultCursor.getColumnIndexOrThrow("datauuid"));
                    }
                    resultCursor.close();
                } finally {
                    resultCursor.close();
                }
            }
        }
        return null;
    }

    private String getUserFriendlyFoodName(int i) {
        int stringIdForSamsungMeal = SHealthDailyNutrition.getStringIdForSamsungMeal(i);
        Context context2 = this.context;
        return context2.getString(R.string.shealth_branded_mealname, new Object[]{context2.getString(stringIdForSamsungMeal)});
    }

    private void writeFoodForDate(float[] fArr, int i, Date date) {
        HealthDataStore dataStore = getDataStore();
        if (dataStore != null) {
            String userFriendlyFoodName = getUserFriendlyFoodName(i);
            String mfpFoodIdForMeal = getMfpFoodIdForMeal(i, date);
            InsertRequest build = new InsertRequest.Builder().setDataType(FoodInfo.HEALTH_DATA_TYPE).build();
            HealthData healthData = new HealthData();
            healthData.setSourceDevice(getDeviceUuid());
            healthData.putString("deviceuuid", getDeviceUuid());
            healthData.putString(FoodInfo.PROVIDER_FOOD_ID, mfpFoodIdForMeal);
            healthData.putString("name", userFriendlyFoodName);
            healthData.putString("description", userFriendlyFoodName);
            healthData.putFloat("calorie", fArr[0]);
            healthData.putFloat(FoodInfo.TOTAL_FAT, fArr[1]);
            healthData.putFloat("saturated_fat", fArr[2]);
            healthData.putFloat(FoodInfo.POLYSATURATED_FAT, fArr[3]);
            healthData.putFloat(FoodInfo.MONOSATURATED_FAT, fArr[4]);
            healthData.putFloat("trans_fat", fArr[5]);
            healthData.putFloat("cholesterol", fArr[6]);
            healthData.putFloat("sodium", fArr[7]);
            healthData.putFloat("potassium", fArr[8]);
            healthData.putFloat(FoodInfo.CARBOHYDRATE, fArr[9]);
            healthData.putFloat("dietary_fiber", fArr[10]);
            healthData.putFloat("sugar", fArr[11]);
            healthData.putFloat("protein", fArr[12]);
            healthData.putFloat("vitamin_a", fArr[13]);
            healthData.putFloat("vitamin_c", fArr[14]);
            healthData.putFloat("calcium", fArr[15]);
            healthData.putFloat("iron", fArr[16]);
            build.addHealthData(healthData);
            if (new HealthDataResolver(dataStore, getHandler()).insert(build).await().getCount() == 0) {
                Ln.e("failed to insert food!", new Object[0]);
            }
        }
    }

    private void writeFoodEntryForDate(float[] fArr, int i, Date date) {
        HealthDataStore dataStore = getDataStore();
        if (dataStore != null) {
            InsertRequest build = new InsertRequest.Builder().setDataType(FoodIntake.HEALTH_DATA_TYPE).build();
            try {
                String sHealthFoodId = getSHealthFoodId(getMfpFoodIdForMeal(i, date));
                long time = date.getTime();
                long offset = (long) TimeZone.getDefault().getOffset(time);
                HealthData healthData = new HealthData();
                healthData.setSourceDevice(getDeviceUuid());
                healthData.putInt("meal_type", i);
                healthData.putDouble("calorie", (double) fArr[0]);
                healthData.putString("name", getUserFriendlyFoodName(i));
                healthData.putDouble("amount", 1.0d);
                healthData.putString("unit", "meal");
                healthData.putLong("start_time", time);
                healthData.putLong("time_offset", offset);
                healthData.putString(FoodIntake.FOOD_INFO_ID, sHealthFoodId);
                build.addHealthData(healthData);
                BaseResult await = new HealthDataResolver(dataStore, getHandler()).insert(build).await();
                if (await == null || await.getCount() == 0) {
                    Ln.e("failed to insert samsung food entries", new Object[0]);
                }
            } catch (Exception e) {
                Ln.e(e, "failed to build FoodEntry for SHealth", new Object[0]);
            }
        }
    }

    private static float[] aggregateNutritionalValues(List<FoodEntry> list) {
        float[] fArr = new float[20];
        for (int i = 0; i < list.size(); i++) {
            FoodEntry foodEntry = (FoodEntry) list.get(i);
            Food food = foodEntry.getFood();
            float nutrientMultiplierForFoodPortion = food.nutrientMultiplierForFoodPortion(foodEntry.getFoodPortion());
            float quantity = foodEntry.getQuantity();
            float[] values = food.getNutritionalValues().getValues();
            fArr[0] = fArr[0] + calculateValue(food.getNutritionalValues().getCaloriesValue(1.0f), quantity * nutrientMultiplierForFoodPortion);
            for (int i2 = 1; i2 < 20; i2++) {
                fArr[i2] = fArr[i2] + calculateValue(values[i2], nutrientMultiplierForFoodPortion);
            }
        }
        return fArr;
    }

    /* access modifiers changed from: protected */
    public Set<Permission> getWritePermissions() {
        return WRITE_PERMISSIONS;
    }

    /* access modifiers changed from: protected */
    public Set<Permission> getReadPermissions() {
        return READ_PERMISSIONS;
    }
}
