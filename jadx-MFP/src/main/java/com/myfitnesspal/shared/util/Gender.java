package com.myfitnesspal.shared.util;

import com.facebook.internal.AnalyticsEvents;
import com.uacf.core.util.Strings;

public enum Gender {
    Unknown,
    Male,
    Female;

    public String toString() {
        switch (this) {
            case Male:
                return "Male";
            case Female:
                return "Female";
            case Unknown:
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            default:
                throw new IllegalStateException("Unknown value");
        }
    }

    public static Gender fromString(String str) {
        String lowerCase = Strings.toString(str, "").toLowerCase();
        if (lowerCase.equals("male") || lowerCase.equals("m")) {
            return Male;
        }
        if (lowerCase.equals("female") || lowerCase.equals("f")) {
            return Female;
        }
        return Unknown;
    }

    public static boolean isMale(String str) {
        return fromString(str) == Male;
    }

    public static boolean isFemale(String str) {
        return fromString(str) == Female;
    }
}
