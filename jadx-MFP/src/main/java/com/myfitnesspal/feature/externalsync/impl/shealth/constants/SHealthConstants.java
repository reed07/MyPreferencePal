package com.myfitnesspal.feature.externalsync.impl.shealth.constants;

public class SHealthConstants {
    private static final String APP_ID = "7965e9aeef6d";
    private static final String CLIENT_ID = "samsung";

    public interface StepDailyTrend {
        public static final String BINNING_DATA = "binning_data";
        public static final String CALORIE = "calorie";
        public static final String COUNT = "count";
        public static final String CREATE_TIME = "create_time";
        public static final String DATA_UUID = "datauuid";
        public static final String DAY_TIME = "day_time";
        public static final String DEVICE_UUID = "deviceuuid";
        public static final String DISTANCE = "distance";
        public static final String HEALTH_DATA_TYPE = "com.samsung.shealth.step_daily_trend";
        public static final String PKG_NAME = "pkg_name";
        public static final String SOURCE_PKG_NAME = "source_pkg_name";
        public static final String SOURCE_TYPE = "source_type";
        public static final int SOURCE_TYPE_ACCESSORIES = 1;
        public static final int SOURCE_TYPE_ACTIVITY_TRACKERS = 2;
        public static final int SOURCE_TYPE_ALL = -2;
        public static final int SOURCE_TYPE_PARTNER_APPS = -1;
        public static final int SOURCE_TYPE_S_HEALTH_ONLY = 0;
        public static final String SPEED = "speed";
        public static final String UPDATE_TIME = "update_time";
    }

    public static String getAppId() {
        return APP_ID;
    }

    public static String getClientId() {
        return CLIENT_ID;
    }
}
