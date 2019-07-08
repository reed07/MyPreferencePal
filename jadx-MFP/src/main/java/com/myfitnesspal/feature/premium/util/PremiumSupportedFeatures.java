package com.myfitnesspal.feature.premium.util;

import android.net.Uri.Builder;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.UpsellOptimizations;
import com.myfitnesspal.shared.constants.Constants.Premium;
import java.util.HashSet;
import java.util.Set;

public class PremiumSupportedFeatures {
    public static final Set<String> SUPPORTED_FEATURES_SET = new HashSet();

    static {
        SUPPORTED_FEATURES_SET.add(Premium.ASSIGN_EXERCISE_FEATURE_ID);
        SUPPORTED_FEATURES_SET.add(Premium.CUSTOM_DAILY_GOALS_FEATURE_ID);
        SUPPORTED_FEATURES_SET.add(Premium.MEAL_MACRO_FEATURE_ID);
        SUPPORTED_FEATURES_SET.add(Premium.AD_FREE_FEATURE_ID);
        SUPPORTED_FEATURES_SET.add(Premium.FOOD_LISTS_FEATURE_ID);
        SUPPORTED_FEATURES_SET.add(Premium.QUICK_ADD_FEATURE_ID);
        SUPPORTED_FEATURES_SET.add(Premium.NUTRIENT_DASHBOARD_FEATURE_ID);
        SUPPORTED_FEATURES_SET.add(Premium.CONTENT_FEATURE_ID);
        SUPPORTED_FEATURES_SET.add(Premium.FILE_EXPORT_FEATURE_ID);
        SUPPORTED_FEATURES_SET.add(Premium.TRACK_MACROS_FEATURE_ID);
        SUPPORTED_FEATURES_SET.add(Premium.MEAL_GOALS_ID);
        SUPPORTED_FEATURES_SET.add(Premium.PRIORITY_SUPPORT_FEATURE_ID);
        SUPPORTED_FEATURES_SET.add(Premium.WEEKLY_DIGEST_ID);
        SUPPORTED_FEATURES_SET.add(Premium.FOOD_TIMESTAMPS);
    }

    public static void appendSupportedProductsToUrl(Builder builder) {
        StringBuilder sb = new StringBuilder();
        for (String append : SUPPORTED_FEATURES_SET) {
            sb.append(append);
            sb.append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        builder.appendQueryParameter(UpsellOptimizations.VARIANT_FEATURES, sb.toString());
    }
}
