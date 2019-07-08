package com.myfitnesspal.shared.util;

import com.myfitnesspal.shared.service.session.Session;

public final class UserUtil {
    private static final int LOW_CALORIES_MEN_MIN = 1200;
    private static final int LOW_CALORIES_WOMEN_MIN = 1000;

    public static int getMinimuCalorieForUserBasedOnGender(Session session) {
        if (session.getUser().getProfile().isFemale().booleanValue()) {
            return 1000;
        }
        return LOW_CALORIES_MEN_MIN;
    }
}
