package com.myfitnesspal.feature.premium.util;

import android.content.Context;
import android.graphics.Color;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.uacf.core.util.Strings;
import java.util.HashMap;
import java.util.Map;

public class UpsellParseUtils {
    private static final String PRIORITY_SUPPORT_SPECIAL_CASE_STRING_KEY = "upsell-premium-priority-support-description-text";
    private static final Map<String, Integer> PROPERTY_KEY_TO_STRING_ID_V1 = new HashMap();
    private static final Map<String, Integer> PROPERTY_KEY_TO_STRING_ID_V2 = new HashMap();

    static {
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-grouping-1-headline-text", Integer.valueOf(R.string.premium_upsell_grouping_1_headline_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-grouping-2-headline-text", Integer.valueOf(R.string.premium_upsell_grouping_2_headline_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-grouping-3-headline-text", Integer.valueOf(R.string.premium_upsell_grouping_3_headline_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-hero-cta-text", Integer.valueOf(R.string.premium_upsell_hero_cta_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-ad-free-description-text", Integer.valueOf(R.string.premium_upsell_premium_ad_free_description_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-ad-free-title-text", Integer.valueOf(R.string.premium_upsell_premium_ad_free_title_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-assign-exercise-description-text", Integer.valueOf(R.string.premium_upsell_premium_assign_exercise_description_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-assign-exercise-title-text", Integer.valueOf(R.string.premium_upsell_premium_assign_exercise_title_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-nutrient-dashboard-description-text", Integer.valueOf(R.string.premium_upsell_premium_nutrient_dashboard_description_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-nutrient-dashboard-title-text", Integer.valueOf(R.string.premium_upsell_premium_nutrient_dashboard_title_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-food-list-description-text", Integer.valueOf(R.string.premium_upsell_premium_food_list_description_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-food-list-title-text", Integer.valueOf(R.string.premium_upsell_premium_food_list_title_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-content-description-text", Integer.valueOf(R.string.premium_upsell_premium_content_description_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-content-title-text", Integer.valueOf(R.string.premium_upsell_premium_content_title_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-custom-daily-goals-description-text", Integer.valueOf(R.string.premium_upsell_premium_custom_daily_goals_description_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-custom-daily-goals-title-text", Integer.valueOf(R.string.premium_upsell_premium_custom_daily_goals_title_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put(PRIORITY_SUPPORT_SPECIAL_CASE_STRING_KEY, Integer.valueOf(R.string.premium_upsell_premium_priority_support_description_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-priority-support-title-text", Integer.valueOf(R.string.premium_upsell_premium_priority_support_title_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-quick-add-description-text", Integer.valueOf(R.string.premium_upsell_premium_quick_add_description_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-quick-add-title-text", Integer.valueOf(R.string.premium_upsell_premium_quick_add_title_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-track-macros-description-text", Integer.valueOf(R.string.premium_upsell_premium_track_macros_description_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-track-macros-title-text", Integer.valueOf(R.string.premium_upsell_premium_track_macros_title_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-custom-meal-goals-description-text", Integer.valueOf(R.string.premium_upsell_custom_meal_goals_description_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-custom-meal-goals-title-text", Integer.valueOf(R.string.premium_upsell_custom_meal_goals_title_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-file-export-description-text", Integer.valueOf(R.string.premium_upsell_premium_file_export_description_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-file-export-title-text", Integer.valueOf(R.string.premium_upsell_premium_file_export_title_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-diary-meal-macros-description-text", Integer.valueOf(R.string.premium_upsell_premium_diary_meal_macros_description_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-premium-diary-meal-macros-title-text", Integer.valueOf(R.string.premium_upsell_premium_diary_meal_macros_title_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-title-text", Integer.valueOf(R.string.premium_upsell_title_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-hero-cta2-text", Integer.valueOf(R.string.premium_upsell_hero_cta2_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-registration-cta-text", Integer.valueOf(R.string.premium_upsell_registration_cta_text));
        PROPERTY_KEY_TO_STRING_ID_V1.put("upsell-registration-cta2-text", Integer.valueOf(R.string.premium_upsell_registration_cta2_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-grouping-1-headline-text", Integer.valueOf(R.string.premium_upsell_grouping_set_yourself_up_for_success));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-grouping-2-headline-text", Integer.valueOf(R.string.premium_upsell_grouping_track_nutrients_your_way));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-grouping-3-headline-text", Integer.valueOf(R.string.premium_upsell_grouping_focus_on_what_matters));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-hero-cta-text", Integer.valueOf(R.string.premium_users_lose_6x));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-ad-free-description-text", Integer.valueOf(R.string.premium_upsell_premium_ad_free_description_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-ad-free-title-text", Integer.valueOf(R.string.premium_upsell_premium_ad_free_title_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-assign-exercise-description-text", Integer.valueOf(R.string.premium_upsell_premium_assign_exercise_description_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-assign-exercise-title-text", Integer.valueOf(R.string.premium_upsell_premium_assign_exercise_title_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-nutrient-dashboard-description-text", Integer.valueOf(R.string.premium_upsell_premium_nutrient_dashboard_description_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-nutrient-dashboard-title-text", Integer.valueOf(R.string.premium_upsell_premium_nutrient_dashboard_title_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-food-list-description-text", Integer.valueOf(R.string.premium_upsell_premium_food_list_description_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-food-list-title-text", Integer.valueOf(R.string.premium_upsell_premium_food_list_title_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-content-description-text", Integer.valueOf(R.string.premium_upsell_premium_content_description_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-content-title-text", Integer.valueOf(R.string.premium_upsell_premium_content_title_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-custom-daily-goals-description-text", Integer.valueOf(R.string.premium_upsell_premium_custom_daily_goals_description_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-custom-daily-goals-title-text", Integer.valueOf(R.string.premium_upsell_premium_custom_daily_goals_title_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put(PRIORITY_SUPPORT_SPECIAL_CASE_STRING_KEY, Integer.valueOf(R.string.premium_upsell_premium_priority_support_description_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-priority-support-title-text", Integer.valueOf(R.string.premium_upsell_premium_priority_support_title_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-quick-add-description-text", Integer.valueOf(R.string.premium_upsell_track_grams_of_carbs));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-quick-add-title-text", Integer.valueOf(R.string.premium_upsell_quick_add_carbs));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-track-macros-description-text", Integer.valueOf(R.string.premium_upsell_set_carbs_by_gram));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-track-macros-title-text", Integer.valueOf(R.string.premium_upsell_carbs_by_gram));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-custom-meal-goals-description-text", Integer.valueOf(R.string.premium_upsell_custom_meal_goals_description_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-custom-meal-goals-title-text", Integer.valueOf(R.string.premium_upsell_custom_meal_goals_title_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-file-export-description-text", Integer.valueOf(R.string.premium_upsell_premium_file_export_description_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-file-export-title-text", Integer.valueOf(R.string.premium_upsell_premium_file_export_title_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-diary-meal-macros-description-text", Integer.valueOf(R.string.premium_upsell_view_carbs_in_diary));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-diary-meal-macros-title-text", Integer.valueOf(R.string.premium_upsell_carbs_by_meal));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-food-entry-timestamps-title-text", Integer.valueOf(R.string.food_timestamps));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-food-entry-timestamps-description-text", Integer.valueOf(R.string.diary_settings_description_show_food_timestamps));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-weekly-digest-title-text", Integer.valueOf(R.string.premium_upsell_premium_weekly_digest_title_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-premium-weekly-digest-description-text", Integer.valueOf(R.string.premium_upsell_premium_weekly_digest_description_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-title-text", Integer.valueOf(R.string.premium_upsell_title_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-hero-cta2-text", Integer.valueOf(R.string.premium_upsell_hero_cta2_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-registration-cta-text", Integer.valueOf(R.string.premium_upsell_registration_cta_text));
        PROPERTY_KEY_TO_STRING_ID_V2.put("upsell-registration-cta2-text", Integer.valueOf(R.string.premium_upsell_registration_cta2_text));
    }

    public static int parseUpsellColor(String str) {
        if (str != null && str.startsWith("#") && str.length() == 7) {
            return Color.parseColor(str);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("malformed color string: ");
        sb.append(str);
        throw new IllegalArgumentException(sb.toString());
    }

    public static String parseUpsellString(Context context, CountryService countryService, String str, Map<String, String> map, ConfigService configService) {
        String localizedString = getLocalizedString(context, countryService, str, (String) map.get(str), configService);
        if (!Strings.isEmpty(localizedString)) {
            return localizedString;
        }
        throw new IllegalArgumentException("null/missing upsell string!");
    }

    public static int parseUpsellInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("invalid integer value: ");
            sb.append(str);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static int parseUpsellInteger(String str, int i) {
        int parseUpsellInteger = parseUpsellInteger(str);
        if (parseUpsellInteger >= i) {
            return parseUpsellInteger;
        }
        throw new IllegalArgumentException(String.format("specified integer should be at least %d but is %d", new Object[]{Integer.valueOf(i), Integer.valueOf(parseUpsellInteger)}));
    }

    public static int[] parseUpsellIntegerList(String str) {
        if (!Strings.isEmpty(str)) {
            String[] split = str.split(",");
            int[] iArr = new int[split.length];
            int i = 0;
            while (i < split.length) {
                try {
                    iArr[i] = Integer.parseInt(split[i].trim());
                    i++;
                } catch (NumberFormatException unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("invalid integer value: ");
                    sb.append(split[i]);
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            return iArr;
        }
        throw new IllegalArgumentException("input cannot be null or empty");
    }

    private static String getLocalizedString(Context context, CountryService countryService, String str, String str2, ConfigService configService) {
        if (PRIORITY_SUPPORT_SPECIAL_CASE_STRING_KEY.equals(str) && !countryService.isEnglishCurrentDialect()) {
            return context.getString(R.string.premium_upsell_premium_priority_support_description_non_english_text);
        }
        Map<String, Integer> map = PROPERTY_KEY_TO_STRING_ID_V2;
        return map.containsKey(str) ? context.getString(((Integer) map.get(str)).intValue()) : str2;
    }
}
