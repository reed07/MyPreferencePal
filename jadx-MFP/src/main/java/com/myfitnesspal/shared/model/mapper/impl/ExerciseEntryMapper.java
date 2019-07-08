package com.myfitnesspal.shared.model.mapper.impl;

import android.content.ContentValues;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.uacf.core.mapping.Mapper;
import java.util.Map;

public interface ExerciseEntryMapper extends Mapper<ExerciseEntry, MfpExerciseEntry> {
    ContentValues getV2ContentValuesFromV1ExtraProperties(Map<String, String> map);
}
