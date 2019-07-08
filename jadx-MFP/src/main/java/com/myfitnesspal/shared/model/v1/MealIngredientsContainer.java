package com.myfitnesspal.shared.model.v1;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable;
import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.exception.UacfNotImplementedException;
import java.util.HashMap;
import java.util.Map;

public class MealIngredientsContainer implements BinaryApiSerializable {
    public static final BinaryCreator<MealIngredientsContainer> BINARY_CREATOR = new BinaryCreator<MealIngredientsContainer>() {
        public MealIngredientsContainer create(BinaryDecoder binaryDecoder) {
            MealIngredientsContainer mealIngredientsContainer = new MealIngredientsContainer();
            mealIngredientsContainer.readData(binaryDecoder);
            return mealIngredientsContainer;
        }
    };
    private Map<Long, MealIngredient> foodMasterIdToIngredientMap;

    public void writeData(BinaryEncoder binaryEncoder) {
        throw new RuntimeException(new UacfNotImplementedException());
    }

    public void readData(BinaryDecoder binaryDecoder) {
        binaryDecoder.decode4ByteInt();
        binaryDecoder.decode4ByteInt();
        binaryDecoder.decodeString();
        binaryDecoder.decodeString();
        int decode4ByteInt = (int) binaryDecoder.decode4ByteInt();
        this.foodMasterIdToIngredientMap = new HashMap(decode4ByteInt);
        while (true) {
            int i = decode4ByteInt - 1;
            if (decode4ByteInt > 0) {
                MealIngredient mealIngredient = new MealIngredient();
                mealIngredient.setMasterDatabaseId(binaryDecoder.decode4ByteInt());
                mealIngredient.setUid(binaryDecoder.decodeString());
                binaryDecoder.decode4ByteInt();
                long decode4ByteInt2 = binaryDecoder.decode4ByteInt();
                binaryDecoder.decodeString();
                binaryDecoder.decodeString();
                int decode4ByteInt3 = (int) binaryDecoder.decode4ByteInt();
                mealIngredient.setQuantity(binaryDecoder.decodeFloat());
                mealIngredient.setIsFraction(binaryDecoder.decode2ByteInt() > 0);
                mealIngredient.setWeightIndex(decode4ByteInt3);
                this.foodMasterIdToIngredientMap.put(Long.valueOf(decode4ByteInt2), mealIngredient);
                decode4ByteInt = i;
            } else {
                return;
            }
        }
    }

    public Map<Long, MealIngredient> getFoodMasterIdToIngredientMap() {
        return this.foodMasterIdToIngredientMap;
    }
}
