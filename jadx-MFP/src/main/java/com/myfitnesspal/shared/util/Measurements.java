package com.myfitnesspal.shared.util;

import com.myfitnesspal.shared.constants.Constants.Measurement;
import com.uacf.core.util.Strings;

public final class Measurements {
    private static final String FITBIT_MEASUREMENT_SUBSTRING = "fitbit";
    private static final float METERS_TO_KILOMETERS_FACTOR = 1000.0f;
    private static final float METERS_TO_MILE_FACTOR = 1609.344f;

    public static float convertMetersToKilometers(float f) {
        return f / 1000.0f;
    }

    public static float convertMetersToMiles(float f) {
        return f / METERS_TO_MILE_FACTOR;
    }

    public static boolean validateType(String str) {
        return isWeight(str) || isLength(str);
    }

    public static boolean isWeight(String str) {
        return Strings.equalsIgnoreCase(str, Measurement.WEIGHT);
    }

    public static boolean isLength(String str) {
        return Strings.equalsIgnoreCase(str, Measurement.NECK) || Strings.equalsIgnoreCase(str, Measurement.WAIST) || Strings.equalsIgnoreCase(str, Measurement.HIPS);
    }

    public static boolean isSteps(String str) {
        return Strings.equalsIgnoreCase(str, Measurement.STEPS);
    }

    public static boolean isFitbit(String str) {
        return str.toLowerCase().contains(FITBIT_MEASUREMENT_SUBSTRING);
    }
}
