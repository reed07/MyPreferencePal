package com.myfitnesspal.feature.externalsync.impl.googlefit.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.feature.externalsync.impl.googlefit.model.DataFitResult.Type;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.uacf.core.util.Ln;
import java.util.HashMap;
import java.util.Map;

public class GoogleFitNutritionEntry implements Parcelable, DataFitResult {
    public static final Creator<GoogleFitNutritionEntry> CREATOR = new Creator<GoogleFitNutritionEntry>() {
        public GoogleFitNutritionEntry createFromParcel(Parcel parcel) {
            return new GoogleFitNutritionEntry(parcel);
        }

        public GoogleFitNutritionEntry[] newArray(int i) {
            return new GoogleFitNutritionEntry[i];
        }
    };
    public static final int DELETE_FLAG = 1;
    public static final int INSERT_FLAG = 0;
    public static final int MEAL_TYPE_BREAKFAST = 10101010;
    public static final int MEAL_TYPE_DINNER = 11100101;
    public static final int MEAL_TYPE_LUNCH = 11110101;
    public static final int MEAL_TYPE_SNACK = 11111010;
    public static final int MEAL_TYPE_UNKNOWN = 10101011;
    public static final String NUTRIENT_CALCIUM = "calcium";
    public static final String NUTRIENT_CALORIES = "calories";
    public static final String NUTRIENT_CHOLESTEROL = "cholesterol";
    public static final String NUTRIENT_DIETARY_FIBER = "dietary_fiber";
    public static final String NUTRIENT_IRON = "iron";
    public static final String NUTRIENT_MONOUNSATURATED_FAT = "fat.monounsaturated";
    public static final String NUTRIENT_POLYUNSATURATED_FAT = "fat.polyunsaturated";
    public static final String NUTRIENT_POTASSIUM = "potassium";
    public static final String NUTRIENT_PROTEIN = "protein";
    public static final String NUTRIENT_SATURATED_FAT = "fat.saturated";
    public static final String NUTRIENT_SODIUM = "sodium";
    public static final String NUTRIENT_SUGAR = "sugar";
    public static final String NUTRIENT_TOTAL_CARBS = "carbs.total";
    public static final String NUTRIENT_TOTAL_FAT = "fat.total";
    public static final String NUTRIENT_TRANS_FAT = "fat.trans";
    public static final String NUTRIENT_VITAMIN_C = "vitamin_c";
    public static final int READ_FLAG = 2;
    private static final int TOTAL_DAILY_CALCIUM_MG = 1000;
    private static final int TOTAL_DAILY_IRON_MG = 18;
    private static final int TOTAL_DAILY_VITAMIN_C_MG = 60;
    private static final int UNIT_GRAMS = 0;
    private static final int UNIT_MILLIGRAMS = 1;
    private static final int UNIT_PERCENTAGE = 2;
    private long endTime;
    private long entryTime;
    private int meal;
    private Map<String, Float> nutrients;
    private long startTime;
    private int statusFlag;

    public int describeContents() {
        return 0;
    }

    public static GoogleFitNutritionEntry fromFoodEntry(FoodEntry foodEntry, int i) {
        long time = foodEntry.getDate().getTime();
        GoogleFitNutritionEntry googleFitNutritionEntry = new GoogleFitNutritionEntry();
        googleFitNutritionEntry.setEntryTime(time);
        googleFitNutritionEntry.setStartTime(time);
        googleFitNutritionEntry.setEndTime(time);
        googleFitNutritionEntry.setMeal(MEAL_TYPE_UNKNOWN);
        googleFitNutritionEntry.setNutrients(getNutrientsForEntry(foodEntry));
        googleFitNutritionEntry.setStatusFlag(i);
        return googleFitNutritionEntry;
    }

    public GoogleFitNutritionEntry() {
    }

    public GoogleFitNutritionEntry(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setEntryTime(long j) {
        this.entryTime = j;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public void setMeal(int i) {
        this.meal = i;
    }

    public void setNutrients(Map<String, Float> map) {
        if (map == null) {
            this.nutrients = new HashMap();
        } else {
            this.nutrients = map;
        }
    }

    public void setStatusFlag(int i) {
        this.statusFlag = i;
    }

    public long getEntryTime() {
        return this.entryTime;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public int getMeal() {
        return this.meal;
    }

    public Map<String, Float> getNutrients() {
        return this.nutrients;
    }

    public int getStatusFlag() {
        return this.statusFlag;
    }

    public Type getType() {
        return Type.FitNutrition;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.entryTime);
        parcel.writeLong(this.startTime);
        parcel.writeLong(this.endTime);
        parcel.writeInt(this.meal);
        parcel.writeMap(this.nutrients);
        parcel.writeInt(this.statusFlag);
    }

    private void readFromParcel(Parcel parcel) {
        this.entryTime = parcel.readLong();
        this.startTime = parcel.readLong();
        this.endTime = parcel.readLong();
        this.meal = parcel.readInt();
        this.nutrients.clear();
        parcel.readMap(this.nutrients, Float.class.getClassLoader());
        this.statusFlag = parcel.readInt();
    }

    private static Map<String, Float> getNutrientsForEntry(FoodEntry foodEntry) {
        Food food = foodEntry.getFood();
        HashMap hashMap = new HashMap();
        if (food == null) {
            return hashMap;
        }
        try {
            MfpNutritionalContents fromFood = MfpNutritionalContents.fromFood(food);
            float nutrientScale = nutrientScale(food, foodEntry.getFoodPortion(), foodEntry.getQuantity());
            hashMap.put("calories", Float.valueOf(foodEntry.getCaloriesValue()));
            hashMap.put("fat.total", Float.valueOf(calculateValue("fat.total", fromFood.getFat().doubleValue(), nutrientScale, 0)));
            hashMap.put("fat.saturated", Float.valueOf(calculateValue("fat.saturated", fromFood.getSaturatedFat().doubleValue(), nutrientScale, 0)));
            hashMap.put("fat.polyunsaturated", Float.valueOf(calculateValue("fat.polyunsaturated", fromFood.getPolyunsaturatedFat().doubleValue(), nutrientScale, 0)));
            hashMap.put("fat.monounsaturated", Float.valueOf(calculateValue("fat.monounsaturated", fromFood.getMonounsaturatedFat().doubleValue(), nutrientScale, 0)));
            hashMap.put("fat.trans", Float.valueOf(calculateValue("fat.trans", fromFood.getTransFat().doubleValue(), nutrientScale, 0)));
            hashMap.put("cholesterol", Float.valueOf(calculateValue("cholesterol", fromFood.getCholesterol().doubleValue(), nutrientScale, 1)));
            hashMap.put("sodium", Float.valueOf(calculateValue("sodium", fromFood.getSodium().doubleValue(), nutrientScale, 1)));
            hashMap.put("potassium", Float.valueOf(calculateValue("potassium", fromFood.getPotassium().doubleValue(), nutrientScale, 1)));
            hashMap.put("carbs.total", Float.valueOf(calculateValue("carbs.total", fromFood.getCarbohydrates().doubleValue(), nutrientScale, 0)));
            hashMap.put("dietary_fiber", Float.valueOf(calculateValue("dietary_fiber", fromFood.getFiber().doubleValue(), nutrientScale, 0)));
            hashMap.put("sugar", Float.valueOf(calculateValue("sugar", fromFood.getSugar().doubleValue(), nutrientScale, 0)));
            hashMap.put("protein", Float.valueOf(calculateValue("protein", fromFood.getProtein().doubleValue(), nutrientScale, 0)));
            hashMap.put("vitamin_c", Float.valueOf(calculateValue("vitamin_c", fromFood.getVitaminC().doubleValue(), nutrientScale, 2)));
            hashMap.put("calcium", Float.valueOf(calculateValue("calcium", fromFood.getCalcium().doubleValue(), nutrientScale, 2)));
            hashMap.put("iron", Float.valueOf(calculateValue("iron", fromFood.getIron().doubleValue(), nutrientScale, 2)));
            return hashMap;
        } catch (Exception e) {
            Ln.e(e);
            return hashMap;
        }
    }

    private static float calculateValue(String str, double d, float f, int i) {
        if (d < 0.0d) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        float f2 = (float) (d * ((double) f));
        if (i != 2) {
            if (i != 0 && i == 1) {
                f2 /= 1000.0f;
            }
            return f2;
        } else if ("vitamin_c".equals(str)) {
            return ((f2 / 100.0f) * 60.0f) / 1000.0f;
        } else {
            if ("calcium".equals(str)) {
                return ((f2 / 100.0f) * 1000.0f) / 1000.0f;
            }
            return "iron".equals(str) ? ((f2 / 100.0f) * 18.0f) / 1000.0f : f2;
        }
    }

    private static float nutrientScale(Food food, FoodPortion foodPortion, float f) {
        if (foodPortion != null) {
            return food.nutrientMultiplierForFoodPortion(foodPortion) * f;
        }
        return 1.0f;
    }
}
