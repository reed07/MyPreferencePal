package com.myfitnesspal.shared.model.mapper.impl;

import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.model.v15.FoodObject;
import com.myfitnesspal.shared.model.v15.FoodObjectFromClient;
import com.myfitnesspal.shared.model.v15.FoodPortionObject;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MfpFoodMapperImpl implements MfpFoodMapper {
    public FoodObject reverseMap(MfpFood mfpFood) {
        FoodObjectFromClient foodObjectFromClient = new FoodObjectFromClient();
        foodObjectFromClient.setBrand(mfpFood.getBrandName());
        foodObjectFromClient.setDescription(mfpFood.getDescription());
        foodObjectFromClient.setLocalId(mfpFood.getLocalId());
        foodObjectFromClient.setOriginalUid(mfpFood.getId());
        foodObjectFromClient.setPromotedFromMasterId(mfpFood.getPromotedFromMasterId());
        foodObjectFromClient.setPromotedFromUid(mfpFood.getPromotedFromUid());
        foodObjectFromClient.setUid(mfpFood.getVersion());
        foodObjectFromClient.setIsPublic(mfpFood.isPublicSafe());
        foodObjectFromClient.setGrams(1.0f);
        foodObjectFromClient.setFoodPortions(Enumerable.select((Collection<T>) mfpFood.getServingSizes(), (ReturningFunction1<U, T>) new ReturningFunction1<FoodPortionObject, MfpServingSize>() {
            public FoodPortionObject execute(MfpServingSize mfpServingSize) {
                FoodPortionObject foodPortionObject = new FoodPortionObject();
                foodPortionObject.setGramWeight(mfpServingSize.getNutritionMultiplier().floatValue());
                foodPortionObject.setDescription(mfpServingSize.getUnit());
                foodPortionObject.setAmount(mfpServingSize.getValue().floatValue());
                return foodPortionObject;
            }
        }));
        foodObjectFromClient.setNutritionalValues(NutritionalValues.valuesFromMfpNutritionalContents(mfpFood.getNutritionalContents(), 1.0f));
        foodObjectFromClient.setFoodPermission(mfpFood.getFoodPermission());
        return foodObjectFromClient;
    }

    public List<MfpFood> mapFromList(List<FoodObject> list) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (FoodObject mapFrom : list) {
            arrayList.add(mapFrom(mapFrom));
        }
        return arrayList;
    }

    public MfpFood mapFrom(final FoodObject foodObject) throws IOException {
        MfpFood mfpFood = new MfpFood();
        mfpFood.setBrandName(foodObject.getBrand());
        mfpFood.setDescription(foodObject.getDescription());
        mfpFood.setId(foodObject.getOriginalUid());
        mfpFood.setVersion(foodObject.getUid());
        mfpFood.setPublic(foodObject.isPublic());
        mfpFood.setLocalId(foodObject.getLocalId());
        mfpFood.setOriginalLocalId(foodObject.getOriginalLocalId());
        mfpFood.setMasterId(foodObject.getMasterId());
        mfpFood.setOwnerUserLocalId(foodObject.getOwnerLocalUserId());
        mfpFood.setOwnerUserMasterId(foodObject.getOwnerMasterUserId());
        mfpFood.setPromotedFromMasterId(foodObject.getPromotedFromMasterId());
        mfpFood.setPromotedFromUid(foodObject.getPromotedFromUid());
        mfpFood.setVerified(foodObject.isVerified());
        MfpNutritionalContents fromNutritionalValuesArray = MfpNutritionalContents.fromNutritionalValuesArray(foodObject.getNutritionalValues());
        List select = Enumerable.select((Collection<T>) foodObject.getFoodPortions(), (ReturningFunction1<U, T>) new ReturningFunction1<MfpServingSize, FoodPortionObject>() {
            public MfpServingSize execute(FoodPortionObject foodPortionObject) throws RuntimeException {
                MfpServingSize mfpServingSize = new MfpServingSize();
                mfpServingSize.setNutritionMultiplier(Double.valueOf((double) foodObject.nutrientMultiplierForFoodPortion(foodPortionObject)));
                mfpServingSize.setUnit(foodPortionObject.getDescription());
                mfpServingSize.setValue(Double.valueOf((double) foodPortionObject.getAmount()));
                return mfpServingSize;
            }
        });
        mfpFood.setNutritionalContents(fromNutritionalValuesArray);
        mfpFood.setServingSizes(select);
        mfpFood.setFoodPermission(foodObject.getFoodPermission());
        return mfpFood;
    }

    public MfpFood tryMapFrom(FoodObject foodObject) {
        try {
            return mapFrom(foodObject);
        } catch (IOException e) {
            Ln.e(e);
            return null;
        }
    }
}
