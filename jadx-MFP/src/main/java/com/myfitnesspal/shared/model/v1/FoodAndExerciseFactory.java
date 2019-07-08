package com.myfitnesspal.shared.model.v1;

import android.database.sqlite.SQLiteException;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.uacf.core.util.Ln;
import java.util.ArrayList;
import javax.annotation.Nonnull;

public final class FoodAndExerciseFactory {
    public static Food createFoodFrom(BinaryDecoder binaryDecoder, @Nonnull DbConnectionManager dbConnectionManager) {
        Food food;
        try {
            long decode4ByteInt = binaryDecoder.decode4ByteInt();
            String decodeString = binaryDecoder.decodeString();
            long decode4ByteInt2 = binaryDecoder.decode4ByteInt();
            long decode4ByteInt3 = binaryDecoder.decode4ByteInt();
            String decodeString2 = binaryDecoder.decodeString();
            long decode8ByteInt = binaryDecoder.decode8ByteInt();
            String decodeString3 = binaryDecoder.decodeString();
            String decodeString4 = binaryDecoder.decodeString();
            String decodeString5 = binaryDecoder.decodeString();
            binaryDecoder.decodeString();
            int decode4ByteInt4 = (int) binaryDecoder.decode4ByteInt();
            NutritionalValues createNutritionalValuesFrom = createNutritionalValuesFrom(binaryDecoder);
            float decodeFloat = binaryDecoder.decodeFloat();
            int itemTypeFromServerFoodType = Food.itemTypeFromServerFoodType(binaryDecoder.decode2ByteInt());
            NutritionalValues nutritionalValues = createNutritionalValuesFrom;
            if (itemTypeFromServerFoodType == 1) {
                food = new Food();
            } else if (itemTypeFromServerFoodType == 3) {
                food = new MealFood();
            } else if (itemTypeFromServerFoodType != 11) {
                food = new Food();
            } else {
                food = new RecipeFood();
            }
            food.setMasterDatabaseId(decode4ByteInt);
            food.setUid(decodeString);
            food.setOwnerUserMasterId(decode4ByteInt2);
            food.setOwnerUserId(dbConnectionManager.usersDbAdapter().lookupUserLocalIdFromMasterId(decode4ByteInt2));
            food.setOriginalMasterId(decode4ByteInt3);
            food.setOriginalUid(decodeString2);
            food.setPromotedFromMasterId(decode8ByteInt);
            food.setPromotedFromUid(decodeString3);
            food.setDescription(decodeString4);
            food.setBrand(decodeString5);
            food.setIsPublic((decode4ByteInt4 & 1) > 0);
            food.setIsDeleted((decode4ByteInt4 & 2) > 0);
            food.setNutritionalValues(nutritionalValues);
            food.setGrams(decodeFloat);
            int decode2ByteInt = binaryDecoder.decode2ByteInt();
            ArrayList arrayList = new ArrayList(decode2ByteInt);
            for (int i = 0; i < decode2ByteInt; i++) {
                FoodPortion createFoodPortionFrom = createFoodPortionFrom(binaryDecoder);
                if (createFoodPortionFrom != null) {
                    createFoodPortionFrom.setWeightIndex(i);
                    arrayList.add(createFoodPortionFrom);
                }
            }
            food.setFoodPortions((FoodPortion[]) arrayList.toArray(new FoodPortion[arrayList.size()]));
            return food;
        } catch (SQLiteException e) {
            Ln.e(e);
            return null;
        }
    }

    public static final NutritionalValues createNutritionalValuesFrom(BinaryDecoder binaryDecoder) {
        try {
            NutritionalValues nutritionalValues = new NutritionalValues();
            nutritionalValues.initAsBlank();
            for (int i = 0; i < 20; i++) {
                nutritionalValues.setNutrientIndex(i, binaryDecoder.decodeFloat());
            }
            return nutritionalValues;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("SynchronizationResponse, An Exception occured while attempting to decodeNextNutritionalValues(): ");
            sb.append(e.getMessage());
            Ln.v(sb.toString(), new Object[0]);
            return null;
        }
    }

    public static FoodPortion createFoodPortionFrom(BinaryDecoder binaryDecoder) {
        FoodPortion foodPortion = new FoodPortion();
        foodPortion.setAmount(binaryDecoder.decodeFloat());
        foodPortion.setGramWeight(binaryDecoder.decodeFloat());
        foodPortion.setDescription(binaryDecoder.decodeString());
        foodPortion.setIsFraction(binaryDecoder.decode2ByteInt() != 0);
        return foodPortion;
    }

    public static Exercise createExerciseFrom(BinaryDecoder binaryDecoder, @Nonnull DbConnectionManager dbConnectionManager) {
        Exercise exercise = new Exercise();
        long decode4ByteInt = binaryDecoder.decode4ByteInt();
        exercise.setOriginalUid(binaryDecoder.decodeString());
        exercise.setMasterDatabaseId(decode4ByteInt);
        long decode4ByteInt2 = binaryDecoder.decode4ByteInt();
        exercise.setOwnerUserMasterId(decode4ByteInt2);
        exercise.setOwnerUserId(dbConnectionManager.usersDbAdapter().lookupUserLocalIdFromMasterId(decode4ByteInt2));
        binaryDecoder.decode4ByteInt();
        exercise.setOriginalUid(binaryDecoder.decodeString());
        exercise.setExerciseType(binaryDecoder.decode2ByteInt());
        exercise.setDescription(binaryDecoder.decodeString());
        int decode4ByteInt3 = (int) binaryDecoder.decode4ByteInt();
        boolean z = false;
        exercise.setIsPublic((decode4ByteInt3 & 1) > 0);
        exercise.setIsDeleted((decode4ByteInt3 & 2) > 0);
        if ((decode4ByteInt3 & 8) > 0) {
            z = true;
        }
        exercise.setCalorieAdjustmentExercise(z);
        exercise.setMets(binaryDecoder.decodeFloat());
        return exercise;
    }
}
