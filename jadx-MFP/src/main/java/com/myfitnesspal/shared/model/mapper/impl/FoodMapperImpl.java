package com.myfitnesspal.shared.model.mapper.impl;

import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.model.v1.RecipeFood;
import com.myfitnesspal.shared.model.v15.FoodObject;
import com.myfitnesspal.shared.model.v15.FoodObjectFromClient;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FoodMapperImpl implements FoodMapper {
    private final FoodPortionMapper foodPortionMapper;

    public FoodMapperImpl(FoodPortionMapper foodPortionMapper2) {
        this.foodPortionMapper = foodPortionMapper2;
    }

    public Food reverseMap(FoodObject foodObject) {
        Food food;
        int itemTypeFromServerFoodType = Food.itemTypeFromServerFoodType(foodObject.getType());
        if (itemTypeFromServerFoodType == 1) {
            food = new Food();
        } else if (itemTypeFromServerFoodType == 3) {
            food = new MealFood();
        } else if (itemTypeFromServerFoodType != 11) {
            food = new Food();
        } else {
            food = new RecipeFood();
        }
        food.setLocalId(foodObject.getLocalId());
        food.setMasterDatabaseId(foodObject.getMasterId());
        food.setUid(foodObject.getUid());
        food.setOriginalUid(foodObject.getOriginalUid());
        food.setPromotedFromMasterId(foodObject.getPromotedFromMasterId());
        food.setPromotedFromUid(foodObject.getPromotedFromUid());
        food.setBrand(foodObject.getBrand());
        food.setDescription(foodObject.getDescription());
        food.setGrams(foodObject.getGrams());
        NutritionalValues nutritionalValues = new NutritionalValues();
        nutritionalValues.setValues(foodObject.getNutritionalValues());
        food.setNutritionalValues(nutritionalValues);
        List reverseMapList = this.foodPortionMapper.reverseMapList(foodObject.getFoodPortions());
        food.setFoodPortions((FoodPortion[]) reverseMapList.toArray(new FoodPortion[reverseMapList.size()]));
        food.setFoodType(foodObject.getType());
        food.setIsDeleted(foodObject.isDeleted());
        food.setIsPublic(foodObject.isPublic());
        food.setOriginalId(foodObject.getOriginalLocalId());
        food.setOriginalMasterId(foodObject.getOriginalMasterId());
        food.setOwnerUserId(foodObject.getOwnerLocalUserId());
        food.setOwnerUserMasterId(foodObject.getOwnerMasterUserId());
        food.setFoodPermission(foodObject.getFoodPermission());
        return food;
    }

    public FoodObject mapFrom(Food food) throws IOException {
        FoodObjectFromClient foodObjectFromClient = new FoodObjectFromClient();
        foodObjectFromClient.setLocalId(food.getLocalId());
        foodObjectFromClient.setMasterId(food.getMasterDatabaseId());
        foodObjectFromClient.setUid(food.getUid());
        foodObjectFromClient.setOriginalUid(food.getOriginalUid());
        foodObjectFromClient.setPromotedFromMasterId(food.getPromotedFromMasterId());
        foodObjectFromClient.setPromotedFromUid(food.getPromotedFromUid());
        foodObjectFromClient.setBrand(food.getBrand());
        foodObjectFromClient.setBarcode(food.getBarcode());
        foodObjectFromClient.setDescription(food.getDescription());
        foodObjectFromClient.setGrams(food.getGrams());
        foodObjectFromClient.setNutritionalValues(food.getNutritionalValues().getValues());
        foodObjectFromClient.setFoodPortions(this.foodPortionMapper.mapList(Arrays.asList(food.getFoodPortions())));
        foodObjectFromClient.setType(food.getFoodType());
        foodObjectFromClient.setIsDeleted(food.isDeleted());
        foodObjectFromClient.setIsPublic(food.isPublic());
        foodObjectFromClient.setOriginalLocalId(food.getOriginalId());
        foodObjectFromClient.setOriginalMasterId(food.getOriginalMasterId());
        foodObjectFromClient.setOwnerLocalUserId(food.getOwnerUserId());
        foodObjectFromClient.setOwnerMasterUserId(food.getOwnerUserMasterId());
        foodObjectFromClient.setFoodPermission(food.getFoodPermission());
        foodObjectFromClient.setVerified(food.isVerified());
        return foodObjectFromClient;
    }

    public FoodObject tryMapFrom(Food food) {
        try {
            return mapFrom(food);
        } catch (IOException e) {
            Ln.e(e);
            return null;
        }
    }

    public List<FoodObject> mapFromList(List<Food> list) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (Food mapFrom : list) {
            arrayList.add(mapFrom(mapFrom));
        }
        return arrayList;
    }

    public List<Food> reverseMapList(List<FoodObject> list) {
        return Enumerable.select((Collection<T>) list, (ReturningFunction1<U, T>) new ReturningFunction1<Food, FoodObject>() {
            public Food execute(FoodObject foodObject) {
                return FoodMapperImpl.this.reverseMap(foodObject);
            }
        });
    }

    public List<DiaryEntryCellModel> reverseMapListToDiaryEntryCellModel(List<FoodObject> list) {
        return Enumerable.select((Collection<T>) list, (ReturningFunction1<U, T>) new ReturningFunction1<DiaryEntryCellModel, FoodObject>() {
            public DiaryEntryCellModel execute(FoodObject foodObject) {
                return FoodMapperImpl.this.reverseMap(foodObject);
            }
        });
    }

    public Food mapFromMfpFood(MfpFood mfpFood, User user) {
        Food food = new Food();
        food.setOriginalId(mfpFood.getOriginalLocalId());
        food.setIsPublic(mfpFood.isPublicSafe());
        if (!mfpFood.isPublicSafe()) {
            food.setOwnerUserId(user.getLocalId());
            food.setOwnerUserMasterId(user.getMasterDatabaseId());
        }
        food.setPromotedFromMasterId(mfpFood.getPromotedFromMasterId());
        food.setPromotedFromUid(mfpFood.getPromotedFromUid());
        food.setDescription(mfpFood.getDescription());
        food.setBrand(mfpFood.getBrandName());
        food.setGrams(1.0f);
        food.setUid(mfpFood.getVersion());
        food.setOriginalUid(mfpFood.getId());
        food.setVerified(mfpFood.getVerified());
        food.setLocalId(mfpFood.getLocalId());
        food.setMasterDatabaseId(mfpFood.getMasterId());
        ArrayList arrayList = new ArrayList();
        for (MfpServingSize mfpServingSize : mfpFood.getServingSizes()) {
            FoodPortion foodPortion = new FoodPortion();
            foodPortion.setGramWeight(1.0f);
            foodPortion.setAmount(mfpServingSize.getValue().floatValue());
            foodPortion.setDescription(mfpServingSize.getUnit());
            foodPortion.setWeightIndex(mfpFood.getServingSizes().indexOf(mfpServingSize));
            foodPortion.setNutritionMultiplier(mfpServingSize.getNutritionMultiplier());
            arrayList.add(foodPortion);
        }
        food.setFoodPortions((FoodPortion[]) arrayList.toArray(new FoodPortion[arrayList.size()]));
        food.setNutritionalValues(new NutritionalValues(NutritionalValues.valuesFromMfpNutritionalContents(mfpFood.getNutritionalContents(), 1.0f)));
        food.setFoodPermission(mfpFood.getFoodPermission());
        return food;
    }
}
