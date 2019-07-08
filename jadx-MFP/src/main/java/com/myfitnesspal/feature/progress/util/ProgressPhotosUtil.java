package com.myfitnesspal.feature.progress.util;

import com.myfitnesspal.shared.util.Measurements;

public final class ProgressPhotosUtil {
    public static boolean measurementTypeSupportsImageAssociations(String str) {
        return Measurements.isWeight(str);
    }
}
