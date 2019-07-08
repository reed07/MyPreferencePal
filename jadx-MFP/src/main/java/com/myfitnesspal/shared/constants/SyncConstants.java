package com.myfitnesspal.shared.constants;

public final class SyncConstants {
    public static final int DEFAULT_API_VERSION = 42;
    public static final String LOCAL_USERNAME = "_local_";
    public static final int MAGIC_NUMBER = 1235;

    public static final class Flags {
        public static final int APPTYPE_ANDROID = 4;
        public static final int DEBUG_FLAG = 32768;
        public static final int PLATFORM_TYPE_ANDROID = 2;
    }

    public static final class ItemType {
        public static final int DELETED_MOST_USED_FOOD = 25;
        public static final int DIARY_NOTE = 10;
        public static final int EXERCISE = 2;
        public static final int EXERCISE_ENTRY = 5;
        public static final int EXERCISE_GOAL = 24;
        public static final int FOOD = 1;
        public static final int FOOD_ENTRY = 4;
        public static final int FOOD_NOTES = 27;
        public static final int FOOD_PERMISSIONS = 26;
        public static final int FOOD_PORTION = 6;
        public static final int FRIEND_REQUEST = 16;
        public static final int INBOX_MESSAGE = 14;
        public static final int MEAL = 3;
        public static final int MEAL_INGREDIENT = 9;
        public static final int MEASUREMENT = 8;
        public static final int MINI_USER_INFO = 19;
        public static final int NONE = 0;
        public static final int NUTRIENT_GOAL = 23;
        public static final int RECIPE = 11;
        public static final int RECIPE_BOX_ITEM = 12;
        public static final int RECIPE_INGREDIENT = 13;
        public static final int REMINDER = 20;
        public static final int STATUS_COMMENT = 18;
        public static final int STATUS_UPDATE = 17;
        public static final int THIRD_PARTY_ASSOCIATION = 22;
        public static final int TRACKED_NUTRIENT = 21;
        public static final int USER_IMAGE = 15;
        public static final int WATER_ENTRY = 7;
    }

    public static final class Mode {
        public static final int IMPORTING = 32770;
        public static final int NORMAL = 2;
        public static final int SEARCH = 4;
        public static final int USER_REGISTRATION = 3;
    }

    public static final class RequestTypes {
        public static final int ACTION = 1;
        public static final int INFORMATION = 0;
    }

    public static final class Services {
        public static final int FACEBOOK = 1;
        public static final int MFP = 0;
    }
}
