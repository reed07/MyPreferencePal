package com.myfitnesspal.feature.meals.util;

import android.content.Context;
import android.support.annotation.StringRes;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.mapper.impl.MealIngredientMapper;
import com.myfitnesspal.shared.model.mapper.impl.MfpFoodMapper;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v1.MealEntries;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v1.MealIngredient;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.model.v15.FoodObject;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpIngredient;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MealUtilImpl implements MealUtil {
    private static final String MEAL = "meal";
    private final Lazy<DbConnectionManager> dbConnectionManager;
    private final Lazy<FoodMapper> foodMapper;
    private final Lazy<MealIngredientMapper> mealIngredientMapper;
    private final Lazy<MfpFoodMapper> mfpFoodMapper;
    private final Lazy<Session> session;

    public MealUtilImpl(Lazy<Session> lazy, Lazy<MfpFoodMapper> lazy2, Lazy<FoodMapper> lazy3, Lazy<MealIngredientMapper> lazy4, Lazy<DbConnectionManager> lazy5) {
        this.session = lazy;
        this.mfpFoodMapper = lazy2;
        this.foodMapper = lazy3;
        this.mealIngredientMapper = lazy4;
        this.dbConnectionManager = lazy5;
    }

    public MfpNutritionalContents getNutritionalContents(MealFood mealFood) {
        ArrayList arrayList = new ArrayList();
        if (mealFood != null) {
            for (MealIngredient mealIngredient : mealFood.getIngredients()) {
                MfpFood mfpFood = (MfpFood) ((MfpFoodMapper) this.mfpFoodMapper.get()).tryMapFrom(((FoodMapper) this.foodMapper.get()).tryMapFrom(mealIngredient.getIngredientFood()));
                arrayList.add(MfpIngredient.fromFood(mfpFood, getServingSizeForWeightIndex(mfpFood, mealIngredient.getWeightIndex()), (double) mealIngredient.getQuantity()));
            }
        }
        return MfpNutritionalContents.fromIngredientList(arrayList);
    }

    public MfpNutritionalContents getNutritionalContents(List<FoodEntry> list) {
        ArrayList arrayList = new ArrayList();
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            for (FoodEntry foodEntry : list) {
                MealIngredient mealIngredient = (MealIngredient) ((MealIngredientMapper) this.mealIngredientMapper.get()).reverseMap(foodEntry);
                MfpFood mfpFoodFromFoodEntry = getMfpFoodFromFoodEntry(foodEntry);
                arrayList.add(MfpIngredient.fromFood(mfpFoodFromFoodEntry, getServingSizeForWeightIndex(mfpFoodFromFoodEntry, mealIngredient.getWeightIndex()), (double) mealIngredient.getQuantity()));
            }
        }
        return MfpNutritionalContents.fromIngredientList(arrayList);
    }

    private MfpServingSize getServingSizeForWeightIndex(MfpFood mfpFood, int i) {
        List servingSizes = mfpFood.getServingSizes();
        if (CollectionUtils.isEmpty((Collection<?>) servingSizes)) {
            return null;
        }
        if (i >= CollectionUtils.size((Collection<?>) servingSizes)) {
            i = 0;
        }
        return (MfpServingSize) servingSizes.get(i);
    }

    public MealFood getMealFoodFromMealEntries(MealEntries mealEntries) {
        if (mealEntries == null) {
            return null;
        }
        User user = ((Session) this.session.get()).getUser();
        MealFood mealFood = new MealFood();
        mealFood.setOwnerUserId(user.getLocalId());
        mealFood.setOwnerUserMasterId(user.getMasterDatabaseId());
        mealFood.setDescription(mealEntries.summaryLine1());
        mealFood.setBrand("");
        FoodPortion foodPortion = new FoodPortion();
        foodPortion.setGramWeight(1.0f);
        foodPortion.setWeightIndex(0);
        foodPortion.setAmount(1.0f);
        foodPortion.setDescription("meal");
        mealFood.setFoodPortions(new FoodPortion[]{foodPortion});
        ArrayList arrayList = new ArrayList();
        NutritionalValues nutritionalValues = new NutritionalValues();
        nutritionalValues.initAsBlank();
        if (CollectionUtils.notEmpty((Collection<?>) mealEntries.getEntries())) {
            Iterator it = mealEntries.getEntries().iterator();
            while (it.hasNext()) {
                DiaryEntryCellModel diaryEntryCellModel = (DiaryEntryCellModel) it.next();
                if (diaryEntryCellModel instanceof FoodEntry) {
                    FoodEntry foodEntry = (FoodEntry) diaryEntryCellModel;
                    nutritionalValues.addNutritionalValues(foodEntry.getFood().getNutritionalValues(), foodEntry.getFood().nutrientMultiplierForFoodPortion(foodEntry.getFoodPortion()) * foodEntry.getQuantity());
                    arrayList.add(((MealIngredientMapper) this.mealIngredientMapper.get()).reverseMap(foodEntry));
                }
            }
        }
        mealFood.setNutritionalValues(nutritionalValues);
        mealFood.setIngredients(arrayList);
        return mealFood;
    }

    public FoodEntry createFoodEntryForDiaryToast(Food food, String str) {
        FoodEntry foodEntry = new FoodEntry();
        foodEntry.setDate(((Session) this.session.get()).getUser().getActiveDate());
        foodEntry.setFood(food);
        foodEntry.setMealName(str);
        foodEntry.setQuantity(1.0f);
        foodEntry.setFoodPortion(food.defaultPortion());
        foodEntry.setIsFraction(false);
        foodEntry.setDescription(food.getDescription());
        return foodEntry;
    }

    public FoodEntry foodEntryFromMfpFood(MfpFood mfpFood) {
        if (mfpFood == null) {
            return null;
        }
        Food food = (Food) ((FoodMapper) this.foodMapper.get()).reverseMap((FoodObject) ((MfpFoodMapper) this.mfpFoodMapper.get()).reverseMap(mfpFood));
        MfpServingSize selectedServingSize = mfpFood.getSelectedServingSize();
        FoodPortion foodPortion = new FoodPortion();
        foodPortion.setGramWeight(1.0f);
        foodPortion.setAmount(selectedServingSize.getValue().floatValue());
        foodPortion.setDescription(selectedServingSize.getUnit());
        foodPortion.setWeightIndex(mfpFood.getSelectedServingSizeIndex());
        foodPortion.setNutritionMultiplier(selectedServingSize.getNutritionMultiplier());
        FoodEntry foodEntry = new FoodEntry();
        foodEntry.setFood(food);
        foodEntry.setQuantity(mfpFood.getSelectedServingAmount());
        foodEntry.setFoodPortion(foodPortion);
        foodEntry.setDate(((Session) this.session.get()).getUser().getActiveDate());
        foodEntry.setWeightIndex(mfpFood.getSelectedServingSizeIndex());
        return foodEntry;
    }

    public MealFood loadMealFoodLazy(MealFood mealFood) {
        mealFood.loadIngredientsIfNeeded((DbConnectionManager) this.dbConnectionManager.get());
        return mealFood;
    }

    private String getFormattedMacro(Context context, @StringRes int i, double d) {
        String string = context.getString(R.string.gram_abbreviation);
        String str = "";
        if (d < 0.0d) {
            return str;
        }
        return String.format("%s %s%s", new Object[]{context.getString(i), Strings.initStringWithFormattedFloat((float) d, 0), string});
    }

    public MfpFood getMfpFoodFromFoodEntry(FoodEntry foodEntry) {
        MfpFood mfpFood = (MfpFood) ((MfpFoodMapper) this.mfpFoodMapper.get()).tryMapFrom((FoodObject) ((FoodMapper) this.foodMapper.get()).tryMapFrom(foodEntry.getFood()));
        mfpFood.setSelectedServingAmount(foodEntry.getQuantity());
        mfpFood.setSelectedServingSizeIndex(foodEntry.getWeightIndex());
        return mfpFood;
    }

    public List<MealIngredient> getMealIngredientsFromFoodEntries(List<FoodEntry> list) {
        ArrayList arrayList = new ArrayList();
        for (FoodEntry foodEntry : list) {
            if (foodEntry != null) {
                arrayList.add(((MealIngredientMapper) this.mealIngredientMapper.get()).reverseMap(foodEntry));
            }
        }
        return arrayList;
    }

    public List<FoodEntry> getFoodEntriesFromIngredients(MealFood mealFood, float f) {
        ArrayList arrayList = new ArrayList();
        List<MealIngredient> ingredients = mealFood.getIngredients();
        if (CollectionUtils.notEmpty((Collection<?>) ingredients)) {
            for (MealIngredient tryMapFrom : ingredients) {
                FoodEntry foodEntry = (FoodEntry) ((MealIngredientMapper) this.mealIngredientMapper.get()).tryMapFrom(tryMapFrom);
                if (foodEntry != null) {
                    foodEntry.multiplyWithAndSetToQuantity(f);
                    arrayList.add(foodEntry);
                }
            }
        }
        return arrayList;
    }
}
