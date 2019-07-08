package com.myfitnesspal.shared.util;

import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapperImpl;
import com.myfitnesspal.shared.model.mapper.impl.FoodPortionMapperImpl;
import com.myfitnesspal.shared.model.mapper.impl.MfpFoodMapper;
import com.myfitnesspal.shared.model.mapper.impl.MfpFoodMapperImpl;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v2.MfpFood;
import java.io.IOException;

public class FoodMapperUtil {
    private static FoodMapper mapperV15 = new FoodMapperImpl(new FoodPortionMapperImpl());
    private static MfpFoodMapper mapperV2 = new MfpFoodMapperImpl();

    public static MfpFood mapV1FoodToMfpFood(Food food) {
        try {
            return (MfpFood) mapperV2.mapFrom(mapperV15.mapFrom(food));
        } catch (IOException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Food Mapping failed, foodUId : ");
            sb.append(food.getUid());
            throw new RuntimeException(sb.toString());
        }
    }
}
