package com.myfitnesspal.shared.model.v2;

import com.myfitnesspal.shared.db.table.ExerciseEntryPropertiesTable.Keys;
import com.myfitnesspal.shared.util.Database;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MfpExerciseEntryHelper {
    public static Map<String, String> mapV2FieldsToV1ExtraProperties(MfpExerciseEntry mfpExerciseEntry) {
        HashMap hashMap = new HashMap();
        String source = mfpExerciseEntry.getSource();
        if (Strings.notEmpty(source)) {
            hashMap.put("source", source);
        }
        Date startTime = mfpExerciseEntry.getStartTime();
        if (startTime != null) {
            hashMap.put("start_time", Database.encodeDateAndTime(startTime));
        }
        List<MfpExerciseContents> contents = mfpExerciseEntry.getContents();
        if (CollectionUtils.notEmpty((Collection<?>) contents)) {
            for (MfpExerciseContents metadata : contents) {
                MfpExerciseMetadata metadata2 = metadata.getMetadata();
                if (metadata2 instanceof MfpExerciseMetadataForCalorieAdjustment) {
                    MfpExerciseMetadataForCalorieAdjustment mfpExerciseMetadataForCalorieAdjustment = (MfpExerciseMetadataForCalorieAdjustment) metadata2;
                    hashMap.put(Keys.CLIENT_APP_CALORIE_BURNED_PROJECTION_AMOUNT, Strings.toString(Double.valueOf(mfpExerciseMetadataForCalorieAdjustment.getCalorieBurnedProjectionAmount())));
                    hashMap.put(Keys.CLIENT_APP_MFP_CALORIE_PROJECTION, Strings.toString(Double.valueOf(mfpExerciseMetadataForCalorieAdjustment.getMfpCalorieProjection())));
                    hashMap.put(Keys.CLIENT_APP_NAME, Strings.toString(mfpExerciseMetadataForCalorieAdjustment.getPartnerName()));
                    hashMap.put(Keys.CALORIE_ADJUSTMENT_REDUCED, Strings.toString(Boolean.valueOf(mfpExerciseMetadataForCalorieAdjustment.getCalorieAdjustmentReduced())));
                    hashMap.put("allow_negative_calorie_adjustment", Strings.toString(Boolean.valueOf(mfpExerciseMetadataForCalorieAdjustment.getAllowNegativeCalorieAdjustment())));
                    hashMap.put(Keys.CLIENT_APP_PROJECTION_TIMESTAMP, Database.encodeDateAndTime(mfpExerciseMetadataForCalorieAdjustment.getProjectionTimestamp()));
                    hashMap.put(Keys.CLIENT_APP_CALORIE_BURNED_AMOUNT, Strings.toString(Double.valueOf(mfpExerciseMetadataForCalorieAdjustment.getCalorieBurnedAmount())));
                    hashMap.put(Keys.CLIENT_APP_EXERCISE_CALORIES, Strings.toString(Double.valueOf(mfpExerciseMetadataForCalorieAdjustment.getPartnerExerciseCalories())));
                }
            }
        }
        return hashMap;
    }
}
