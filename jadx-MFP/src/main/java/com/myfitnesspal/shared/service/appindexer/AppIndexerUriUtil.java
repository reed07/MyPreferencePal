package com.myfitnesspal.shared.service.appindexer;

import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import com.mopub.volley.BuildConfig;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v2.MfpExercise;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;

public class AppIndexerUriUtil {
    private static final String AUTHORITY = "com.myfitnesspal.android";
    private static final String SCHEME = "android-app";
    private static final String SOURCE_KEY = "source";

    public interface AddFoodUri {
        public static final String ID_KEY = "id";
        public static final String PATH = "mfp/myfitnesspal/add_food";
        public static final String WITHIN_APP_KEY = "withinApp";
    }

    public interface ExerciseUri {
        public static final String MASTER_ID_KEY = "master_id";
        public static final String PATH = "mfp/myfitnesspal/add_exercise";
    }

    public enum Source {
        Website("website"),
        AutoComplete("auto_complete"),
        Unspecified(BuildConfig.VERSION_NAME);
        
        String value;

        private Source(String str) {
            this.value = str;
        }

        /* access modifiers changed from: private */
        public static Source find(String str) {
            Source[] values;
            for (Source source : values()) {
                if (Strings.equals(source.value, str)) {
                    return source;
                }
            }
            return Unspecified;
        }
    }

    public static Source getSource(Intent intent) {
        return Source.find(BundleUtils.getString(intent.getExtras(), "source"));
    }

    public static String getFoodTitle(Food food, LocalizedStringsUtil localizedStringsUtil, UserEnergyService userEnergyService) {
        return getFoodTitle(food.brandAndDescription(), localizedStringsUtil, userEnergyService);
    }

    public static String getFoodTitle(MfpFood mfpFood, LocalizedStringsUtil localizedStringsUtil, UserEnergyService userEnergyService) {
        return getFoodTitle(mfpFood.brandAndDescription(), localizedStringsUtil, userEnergyService);
    }

    private static String getFoodTitle(String str, LocalizedStringsUtil localizedStringsUtil, UserEnergyService userEnergyService) {
        return String.format(localizedStringsUtil.getLocalizedString(LocalizedStrings.APP_INDEXING_FOOD_FORMAT, userEnergyService), new Object[]{str});
    }

    public static Uri foodToUri(Food food, Source source) {
        return foodToUri(food.getOriginalUid(), source);
    }

    public static Uri foodToUri(MfpFood mfpFood, Source source) {
        return foodToUri(mfpFood.getId(), source);
    }

    private static Uri foodToUri(String str, Source source) {
        if (Strings.isEmpty(str) || source == null) {
            return null;
        }
        return new Builder().scheme(SCHEME).authority("com.myfitnesspal.android").appendEncodedPath(AddFoodUri.PATH).appendQueryParameter("id", str).appendQueryParameter("withinApp", "true").appendQueryParameter("source", source.value).build();
    }

    public static Uri exerciseToUri(MfpExercise mfpExercise, Source source) {
        if (mfpExercise == null || source == null) {
            return null;
        }
        return new Builder().scheme(SCHEME).authority("com.myfitnesspal.android").appendEncodedPath(ExerciseUri.PATH).appendQueryParameter("master_id", String.valueOf(mfpExercise.getMasterId())).appendQueryParameter("source", source.value).build();
    }

    public static String getExerciseTitle(String str, LocalizedStringsUtil localizedStringsUtil, UserEnergyService userEnergyService) {
        return String.format(localizedStringsUtil.getLocalizedString(LocalizedStrings.APP_INDEXING_EXERCISE_FORMAT, userEnergyService), new Object[]{str});
    }
}
