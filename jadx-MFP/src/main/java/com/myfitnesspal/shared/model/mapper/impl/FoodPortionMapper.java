package com.myfitnesspal.shared.model.mapper.impl;

import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v15.FoodPortionObject;
import com.uacf.core.mapping.Mapper;
import java.io.IOException;
import java.util.List;

public interface FoodPortionMapper extends Mapper<FoodPortion, FoodPortionObject> {
    List<FoodPortionObject> mapList(List<FoodPortion> list) throws IOException;

    List<FoodPortion> reverseMapList(List<FoodPortionObject> list);
}
