package com.myfitnesspal.feature.externalsync.impl.googlefit.constants;

import java.text.SimpleDateFormat;
import java.util.Locale;

public final class GoogleFitConstants {

    public static final class DateTime {
        public static final String DATE_FORMAT = "yyyy.MM.dd HH:mm:ss";

        public static final class Format {
            public static final SimpleDateFormat newIso8601DateFormat() {
                return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            }

            public static final SimpleDateFormat newIso8601DateTimeFormat() {
                return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
            }
        }
    }

    public static final class General {
        public static final String GOOGLE_FIT_CLIENT_ID = "google_fit";
        public static final String GOOGLE_FIT_STEPS_CLIENT_ID = "mfp-mobile-android-google";
    }

    public static final class GoogleFit {
        public static final String DATA_SOURCE_NAME = "MyFitnessPal";
    }

    public static final class SharedPreferences {
        public static final String IS_GOOGLE_FIT_ENABLED = "is_google_fit_enabled";
        public static final String LAST_SYNC_STEP_COUNT = "fit_last_sync_step_count";
        public static final String LAST_SYNC_TIME_ACTIVITY = "fit_last_sync_activity";
        public static final String LAST_SYNC_TIME_NUTRIENT = "fit_last_sync_nutrient";
        public static final String LAST_SYNC_TIME_STEPS = "fit_last_sync_steps";
        public static final String LAST_SYNC_TIME_WEIGHT = "fit_last_sync_weight";
        public static final String LAST_SYNC_TODAYS_STEP_COUNT = "fit_last_sync_todays_step_count";
        public static final String SCOPES = "fit_scopes";
    }

    public final class SyncScopes {
        public static final String FITNESS_ACTIVITY_READ_WRITE = "mfp_fitness_activity_read_write";
        public static final String FITNESS_BODY_READ_WRITE = "mfp_fitness_body_read_write";
        public static final String FITNESS_NUTRITION_READ_WRITE = "mfp_fitness_nutrition_read_write";
        public static final String FIT_ACTIVITY_READ = "mfp_fit_activity_read";
        public static final String FIT_NUTRIENT_WRITE = "mfp_fit_nutrient_write";
        public static final String FIT_STEPS_READ = "mfp_fit_steps_read";
        public static final String FIT_USER_WEIGHT_READ = "mfp_fit_user_weight_read";
        public static final String FIT_USER_WEIGHT_READ_WRITE = "mfp_fit_user_weight_read_write";
        public static final String FIT_USER_WEIGHT_WRITE = "mfp_fit_user_weight_write";

        public SyncScopes() {
        }
    }
}
