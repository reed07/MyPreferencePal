package com.samsung.android.sdk.healthdata;

public final class HealthConstants {
    public static final String USER_PROFILE_DATA_TYPE = "com.samsung.health.user_profile";

    public interface AmbientTemperature extends DiscreteMeasurement {
        public static final String ACCURACY = "accuracy";
        public static final String ALTITUDE = "altitude";
        public static final String COMMENT = "comment";
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.ambient_temperature";
        public static final String HUMIDITY = "humidity";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String TEMPERATURE = "temperature";
    }

    public interface BloodGlucose extends DiscreteMeasurement {
        public static final String COMMENT = "comment";
        public static final String GLUCOSE = "glucose";
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.blood_glucose";
        public static final String MEAL_TIME = "meal_time";
        public static final String MEAL_TYPE = "meal_type";
        @Deprecated
        public static final int MEAL_TYPE_AFTER_BEDTIME = 80009;
        public static final int MEAL_TYPE_AFTER_BREAKFAST = 80004;
        public static final int MEAL_TYPE_AFTER_DINNER = 80008;
        public static final int MEAL_TYPE_AFTER_LUNCH = 80006;
        public static final int MEAL_TYPE_AFTER_MEAL = 80002;
        public static final int MEAL_TYPE_AFTER_SNACK = 80010;
        public static final int MEAL_TYPE_BEFORE_BREAKFAST = 80003;
        public static final int MEAL_TYPE_BEFORE_DINNER = 80007;
        public static final int MEAL_TYPE_BEFORE_LUNCH = 80005;
        public static final int MEAL_TYPE_BEFORE_MEAL = 80011;
        public static final int MEAL_TYPE_BEFORE_SLEEP = 80013;
        public static final int MEAL_TYPE_FASTING = 80001;
        public static final int MEAL_TYPE_GENERAL = 80012;
        public static final String MEASUREMENT_TYPE = "measurement_type";
        public static final int MEASUREMENT_TYPE_NOT_DEFINED = -1;
        public static final int MEASUREMENT_TYPE_PLASMA = 90002;
        public static final int MEASUREMENT_TYPE_SERUM = 90003;
        public static final int MEASUREMENT_TYPE_WHOLE_BLOOD = 90001;
        public static final String SAMPLE_SOURCE_TYPE = "sample_source_type";
        public static final int SAMPLE_SOURCE_TYPE_CAPILLARY = 90002;
        public static final int SAMPLE_SOURCE_TYPE_NOT_DEFINED = -1;
        public static final int SAMPLE_SOURCE_TYPE_VENOUS = 90001;
    }

    public interface BloodPressure extends DiscreteMeasurement {
        public static final String COMMENT = "comment";
        public static final String DIASTOLIC = "diastolic";
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.blood_pressure";
        public static final String MEAN = "mean";
        public static final String PULSE = "pulse";
        public static final String SYSTOLIC = "systolic";
    }

    public interface BodyTemperature extends DiscreteMeasurement {
        public static final String COMMENT = "comment";
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.body_temperature";
        public static final String TEMPERATURE = "temperature";
    }

    public interface CaffeineIntake extends DiscreteMeasurement {
        public static final String AMOUNT = "amount";
        public static final String COMMENT = "comment";
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.caffeine_intake";
        public static final String UNIT_AMOUNT = "unit_amount";
    }

    public interface Common {
        public static final String CREATE_TIME = "create_time";
        public static final String DEVICE_UUID = "deviceuuid";
        public static final String PACKAGE_NAME = "pkg_name";
        public static final String UPDATE_TIME = "update_time";
        public static final String UUID = "datauuid";
    }

    public interface DiscreteMeasurement extends Common {
        public static final String START_TIME = "start_time";
        public static final String TIME_OFFSET = "time_offset";
    }

    public interface Electrocardiogram extends SessionMeasurement {
        public static final String COMMENT = "comment";
        public static final String DATA = "data";
        public static final String DATA_FORMAT = "data_format";
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.electrocardiogram";
        public static final String MAX_HEART_RATE = "max_heart_rate";
        public static final String MEAN_HEART_RATE = "mean_heart_rate";
        public static final String MIN_HEART_RATE = "min_heart_rate";
        public static final String SAMPLE_COUNT = "sample_count";
        public static final String SAMPLE_FREQUENCY = "sample_frequency";
    }

    public interface Exercise extends SessionMeasurement {
        public static final String ALTITUDE_GAIN = "altitude_gain";
        public static final String ALTITUDE_LOSS = "altitude_loss";
        public static final String CALORIE = "calorie";
        public static final String COMMENT = "comment";
        public static final String COUNT = "count";
        public static final String COUNT_TYPE = "count_type";
        public static final int COUNT_TYPE_STRIDE = 30001;
        public static final int COUNT_TYPE_STROKE = 30002;
        public static final int COUNT_TYPE_SWING = 30003;
        public static final String DECLINE_DISTANCE = "decline_distance";
        public static final String DISTANCE = "distance";
        public static final String DURATION = "duration";
        public static final String EXERCISE_CUSTOM_TYPE = "exercise_custom_type";
        public static final String EXERCISE_TYPE = "exercise_type";
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.exercise";
        public static final String INCLINE_DISTANCE = "incline_distance";
        public static final String LIVE_DATA = "live_data";
        public static final String LOCATION_DATA = "location_data";
        public static final String MAX_ALTITUDE = "max_altitude";
        public static final String MAX_CADENCE = "max_cadence";
        public static final String MAX_CALORICBURN_RATE = "max_caloricburn_rate";
        public static final String MAX_HEART_RATE = "max_heart_rate";
        public static final String MAX_POWER = "max_power";
        public static final String MAX_SPEED = "max_speed";
        public static final String MEAN_CADENCE = "mean_cadence";
        public static final String MEAN_CALORICBURN_RATE = "mean_caloricburn_rate";
        public static final String MEAN_HEART_RATE = "mean_heart_rate";
        public static final String MEAN_POWER = "mean_power";
        public static final String MEAN_RPM = "mean_rpm";
        public static final String MEAN_SPEED = "mean_speed";
        public static final String MIN_ALTITUDE = "min_altitude";
        public static final String MIN_HEART_RATE = "min_heart_rate";
    }

    public interface FoodInfo extends Common {
        public static final String CALCIUM = "calcium";
        public static final String CALORIE = "calorie";
        public static final String CARBOHYDRATE = "carbohydrate";
        public static final String CHOLESTEROL = "cholesterol";
        public static final String DEFAULT_NUMBER_OF_SERVING_UNIT = "default_number_of_serving_unit";
        public static final String DESCRIPTION = "description";
        public static final String DIETARY_FIBER = "dietary_fiber";
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.food_info";
        public static final String INFO_PROVIDER = "info_provider";
        public static final String IRON = "iron";
        public static final String METRIC_SERVING_AMOUNT = "metric_serving_amount";
        public static final String METRIC_SERVING_UNIT = "metric_serving_unit";
        public static final String MONOSATURATED_FAT = "monosaturated_fat";
        public static final String NAME = "name";
        public static final String POLYSATURATED_FAT = "polysaturated_fat";
        public static final String POTASSIUM = "potassium";
        public static final String PROTEIN = "protein";
        public static final String PROVIDER_FOOD_ID = "provider_food_id";
        public static final String SATURATED_FAT = "saturated_fat";
        public static final String SERVING_DESCRIPTION = "serving_description";
        public static final String SODIUM = "sodium";
        public static final String SUGAR = "sugar";
        public static final String TOTAL_FAT = "total_fat";
        public static final String TRANS_FAT = "trans_fat";
        public static final String UNIT_COUNT_PER_CALORIE = "unit_count_per_calorie";
        public static final String VITAMIN_A = "vitamin_a";
        public static final String VITAMIN_C = "vitamin_c";
    }

    public interface FoodIntake extends DiscreteMeasurement {
        public static final String AMOUNT = "amount";
        public static final String CALORIE = "calorie";
        public static final String COMMENT = "comment";
        public static final String FOOD_INFO_ID = "food_info_id";
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.food_intake";
        public static final String MEAL_TYPE = "meal_type";
        public static final int MEAL_TYPE_AFTERNOON_SNACK = 100005;
        public static final int MEAL_TYPE_BREAKFAST = 100001;
        public static final int MEAL_TYPE_DINNER = 100003;
        public static final int MEAL_TYPE_EVENING_SNACK = 100006;
        public static final int MEAL_TYPE_LUNCH = 100002;
        public static final int MEAL_TYPE_MORNING_SNACK = 100004;
        public static final String NAME = "name";
        public static final String UNIT = "unit";
        public static final String UNIT_TYPE_DEFAULT = "120001";
        public static final String UNIT_TYPE_GRAM = "120002";
        public static final String UNIT_TYPE_KILOCALORIE = "120004";
        public static final String UNIT_TYPE_NOT_DEFINED = "-1";
        public static final String UNIT_TYPE_OUNCE = "120003";
    }

    public interface HbA1c extends DiscreteMeasurement {
        public static final String COMMENT = "comment";
        public static final String HBA1C = "hba1c";
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.hba1c";
    }

    public interface HeartRate extends SessionMeasurement {
        public static final String COMMENT = "comment";
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.heart_rate";
        public static final String HEART_BEAT_COUNT = "heart_beat_count";
        public static final String HEART_RATE = "heart_rate";
    }

    public interface OxygenSaturation extends SessionMeasurement {
        public static final String COMMENT = "comment";
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.oxygen_saturation";
        public static final String HEART_RATE = "heart_rate";
        public static final String SPO2 = "spo2";
    }

    public interface SessionMeasurement extends Common {
        public static final String END_TIME = "end_time";
        public static final String START_TIME = "start_time";
        public static final String TIME_OFFSET = "time_offset";
    }

    public interface Sleep extends SessionMeasurement {
        public static final String COMMENT = "comment";
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.sleep";
    }

    public interface SleepStage extends SessionMeasurement {
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.sleep_stage";
        public static final String SLEEP_ID = "sleep_id";
        public static final String STAGE = "stage";
        public static final int STAGE_AWAKE = 40001;
        public static final int STAGE_DEEP = 40003;
        public static final int STAGE_LIGHT = 40002;
        public static final int STAGE_REM = 40004;
    }

    public interface StepCount extends SessionMeasurement {
        public static final String CALORIE = "calorie";
        public static final String COUNT = "count";
        public static final String DISTANCE = "distance";
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.step_count";
        public static final String SAMPLE_POSITION_TYPE = "sample_position_type";
        public static final int SAMPLE_POSITION_TYPE_ANKLE = 230003;
        public static final int SAMPLE_POSITION_TYPE_ARM = 230004;
        public static final int SAMPLE_POSITION_TYPE_UNKNOWN = 230001;
        public static final int SAMPLE_POSITION_TYPE_WRIST = 230002;
        public static final String SPEED = "speed";
    }

    public interface UvExposure extends DiscreteMeasurement {
        public static final String ACCURACY = "accuracy";
        public static final String ALTITUDE = "altitude";
        public static final String COMMENT = "comment";
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.uv_exposure";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String UV_INDEX = "uv_index";
    }

    public interface WaterIntake extends DiscreteMeasurement {
        public static final String AMOUNT = "amount";
        public static final String COMMENT = "comment";
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.water_intake";
        public static final String UNIT_AMOUNT = "unit_amount";
    }

    public interface Weight extends DiscreteMeasurement {
        public static final String BASAL_METABOLIC_RATE = "basal_metabolic_rate";
        public static final String BODY_FAT = "body_fat";
        public static final String COMMENT = "comment";
        public static final String HEALTH_DATA_TYPE = "com.samsung.health.weight";
        public static final String HEIGHT = "height";
        public static final String MUSCLE_MASS = "muscle_mass";
        public static final String SKELETAL_MUSCLE = "skeletal_muscle";
        public static final String WEIGHT = "weight";
    }
}
