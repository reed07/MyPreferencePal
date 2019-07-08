package com.myfitnesspal.shared.model.mapper.impl;

import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v15.FoodPortionObject;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FoodPortionMapperImpl implements FoodPortionMapper {
    public FoodPortion reverseMap(FoodPortionObject foodPortionObject) {
        FoodPortion foodPortion = new FoodPortion();
        foodPortion.setAmount(foodPortionObject.getAmount());
        foodPortion.setDescription(foodPortionObject.getDescription());
        foodPortion.setGramWeight(foodPortionObject.getGramWeight());
        foodPortion.setIsFraction(foodPortionObject.isFraction());
        foodPortion.setNutritionMultiplier(foodPortionObject.getNutritionMultiplier());
        return foodPortion;
    }

    public FoodPortionObject mapFrom(FoodPortion foodPortion) throws IOException {
        FoodPortionObject foodPortionObject = new FoodPortionObject();
        foodPortionObject.setAmount(foodPortion.getAmount());
        foodPortionObject.setDescription(foodPortion.getDescription());
        foodPortionObject.setGramWeight(foodPortion.getGramWeight());
        foodPortionObject.setFraction(foodPortion.getIsFraction());
        foodPortionObject.setNutritionMultiplier(foodPortion.getNutritionMultiplier().doubleValue());
        return foodPortionObject;
    }

    public FoodPortionObject tryMapFrom(FoodPortion foodPortion) {
        try {
            return mapFrom(foodPortion);
        } catch (IOException e) {
            Ln.e(e);
            return null;
        }
    }

    public List<FoodPortionObject> mapList(List<FoodPortion> list) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (FoodPortion mapFrom : list) {
            arrayList.add(mapFrom(mapFrom));
        }
        return arrayList;
    }

    public List<FoodPortion> reverseMapList(List<FoodPortionObject> list) {
        return Enumerable.select((Collection<T>) list, (ReturningFunction2<U, T, Integer>) new ReturningFunction2<FoodPortion, FoodPortionObject, Integer>() {
            public FoodPortion execute(FoodPortionObject foodPortionObject, Integer num) {
                FoodPortion reverseMap = FoodPortionMapperImpl.this.reverseMap(foodPortionObject);
                reverseMap.setWeightIndex(num.intValue());
                return reverseMap;
            }
        });
    }
}
