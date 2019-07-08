package com.myfitnesspal.shared.model.mapper.impl;

import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v15.FoodObject;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.uacf.core.mapping.Mapper;
import java.io.IOException;
import java.util.List;

public interface FoodMapper extends Mapper<Food, FoodObject> {
    List<FoodObject> mapFromList(List<Food> list) throws IOException;

    Food mapFromMfpFood(MfpFood mfpFood, User user);

    List<Food> reverseMapList(List<FoodObject> list);

    List<DiaryEntryCellModel> reverseMapListToDiaryEntryCellModel(List<FoodObject> list);
}
