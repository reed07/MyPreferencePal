package com.myfitnesspal.shared.model.mapper.impl;

import com.myfitnesspal.shared.model.v15.FoodObject;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.uacf.core.mapping.Mapper;
import java.io.IOException;
import java.util.List;

public interface MfpFoodMapper extends Mapper<FoodObject, MfpFood> {
    List<MfpFood> mapFromList(List<FoodObject> list) throws IOException;
}
