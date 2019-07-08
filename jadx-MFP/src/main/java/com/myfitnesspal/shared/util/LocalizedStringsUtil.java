package com.myfitnesspal.shared.util;

import com.myfitnesspal.shared.service.userdata.UserEnergyService;

public interface LocalizedStringsUtil {
    String getExerciseTypeNameString(String str);

    String getLocalizedMeasurementName(String str);

    String getLocalizedString(String str);

    String getLocalizedString(String str, int i, Object obj);

    String getLocalizedString(String str, Object obj);

    int getLocalizedStringId(String str, Object obj);

    String getMealNameString(String str, UserEnergyService userEnergyService);

    String getNutrientString(String str, int i, Object obj);
}
