package com.myfitnesspal.shared.model.mapper.impl;

import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v15.FoodEntryFromServer;
import com.myfitnesspal.shared.model.v15.FoodObject;
import com.uacf.core.util.Ln;
import java.io.IOException;

public class FoodEntryFromServerMapperImpl implements FoodEntryFromServerMapper {
    private final FoodMapper foodMapper;

    public FoodEntryFromServerMapperImpl(FoodMapper foodMapper2) {
        this.foodMapper = foodMapper2;
    }

    public FoodEntry reverseMap(FoodEntryFromServer foodEntryFromServer) {
        FoodEntry foodEntry = new FoodEntry();
        Food food = (Food) this.foodMapper.reverseMap(foodEntryFromServer.getFood());
        foodEntry.setLocalId(foodEntryFromServer.getLocalId());
        foodEntry.setMasterDatabaseId(foodEntryFromServer.getMasterId());
        foodEntry.setUid(foodEntryFromServer.getUid());
        foodEntry.setDate(foodEntryFromServer.getDate());
        foodEntry.setFood(food);
        foodEntry.setMealName(foodEntryFromServer.getMealName());
        foodEntry.setQuantity(foodEntryFromServer.getQuantity());
        foodEntry.setWeightIndex((int) foodEntryFromServer.getMasterIdOfWeight());
        foodEntry.setDescription(foodEntryFromServer.getDescription());
        foodEntry.setEntryTime(foodEntryFromServer.getEntryTime());
        foodEntry.setLoggedAt(foodEntryFromServer.getLoggedAt());
        foodEntry.setFoodPortion(food.foodPortionWithIndex(foodEntry.getWeightIndex()));
        return foodEntry;
    }

    public FoodEntryFromServer mapFrom(FoodEntry foodEntry) throws IOException {
        FoodEntryFromServer foodEntryFromServer = new FoodEntryFromServer();
        FoodObject foodObject = (FoodObject) this.foodMapper.mapFrom(foodEntry.getFood());
        foodEntryFromServer.setLocalId(foodEntry.getLocalId());
        foodEntryFromServer.setMasterId(foodEntry.getMasterDatabaseId());
        foodEntryFromServer.setUid(foodEntry.getUid());
        foodEntryFromServer.setDate(foodEntry.getDate());
        foodEntryFromServer.setFood(foodObject);
        foodEntryFromServer.setMealName(foodEntry.getMealName());
        foodEntryFromServer.setQuantity(foodEntry.getQuantity());
        foodEntryFromServer.setMasterIdOfWeight((long) foodEntry.getWeightIndex());
        foodEntryFromServer.setDescription(foodEntry.getDescription());
        foodEntryFromServer.setEntryTime(foodEntry.getEntryTime());
        foodEntryFromServer.setLoggedAt(foodEntry.getLoggedAt());
        return foodEntryFromServer;
    }

    public FoodEntryFromServer tryMapFrom(FoodEntry foodEntry) {
        try {
            return mapFrom(foodEntry);
        } catch (IOException e) {
            Ln.e(e);
            return null;
        }
    }
}
