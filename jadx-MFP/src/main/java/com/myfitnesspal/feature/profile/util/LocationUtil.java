package com.myfitnesspal.feature.profile.util;

import com.myfitnesspal.shared.model.v1.Country;
import com.uacf.core.util.Strings;
import java.util.HashMap;
import java.util.Map;

public final class LocationUtil {
    private static Map<String, String> LONG_TO_SHORT_MAP = new HashMap();

    static {
        LONG_TO_SHORT_MAP.put("ALABAMA", "AL");
        LONG_TO_SHORT_MAP.put("ALASKA", "AK");
        LONG_TO_SHORT_MAP.put("ARIZONA", "AZ");
        LONG_TO_SHORT_MAP.put("ARKANSAS", "AR");
        LONG_TO_SHORT_MAP.put("CALIFORNIA", Country.CANADA_SHORT);
        LONG_TO_SHORT_MAP.put("COLORADO", "CO");
        LONG_TO_SHORT_MAP.put("CONNECTICUT", "CT");
        LONG_TO_SHORT_MAP.put("DELAWARE", "DE");
        LONG_TO_SHORT_MAP.put("FLORIDA", "FL");
        LONG_TO_SHORT_MAP.put("GEORGIA", "GA");
        LONG_TO_SHORT_MAP.put("HAWAII", "HI");
        LONG_TO_SHORT_MAP.put("IDAHO", "ID");
        LONG_TO_SHORT_MAP.put("ILLINOIS", "IL");
        LONG_TO_SHORT_MAP.put("INDIANA", "IN");
        LONG_TO_SHORT_MAP.put("IOWA", "IA");
        LONG_TO_SHORT_MAP.put("KANSAS", "KS");
        LONG_TO_SHORT_MAP.put("KENTUCKY", "KY");
        LONG_TO_SHORT_MAP.put("LOUISIANA", "LA");
        LONG_TO_SHORT_MAP.put("MAINE", "ME");
        LONG_TO_SHORT_MAP.put("MARYLAND", "MD");
        LONG_TO_SHORT_MAP.put("MASSACHUSETTS", "MA");
        LONG_TO_SHORT_MAP.put("MICHIGAN", "MI");
        LONG_TO_SHORT_MAP.put("MINNESOTA", "MN");
        LONG_TO_SHORT_MAP.put("MISSISSIPPI", "MS");
        LONG_TO_SHORT_MAP.put("MISSOURI", "MO");
        LONG_TO_SHORT_MAP.put("MONTANA", "MT");
        LONG_TO_SHORT_MAP.put("NEBRASKA", "NE");
        LONG_TO_SHORT_MAP.put("NEVADA", "NV");
        LONG_TO_SHORT_MAP.put("NEW HAMPSHIRE", "NH");
        LONG_TO_SHORT_MAP.put("NEW JERSEY", "NJ");
        LONG_TO_SHORT_MAP.put("NEW MEXICO", "NM");
        LONG_TO_SHORT_MAP.put("NEW YORK", "NY");
        LONG_TO_SHORT_MAP.put("NORTH CAROLINA", "NC");
        LONG_TO_SHORT_MAP.put("NORTH DAKOTA", "ND");
        LONG_TO_SHORT_MAP.put("OHIO", "OH");
        LONG_TO_SHORT_MAP.put("OKLAHOMA", "OK");
        LONG_TO_SHORT_MAP.put("OREGON", "OR");
        LONG_TO_SHORT_MAP.put("PENNSYLVANIA", "PA");
        LONG_TO_SHORT_MAP.put("RHODE ISLAND", "RI");
        LONG_TO_SHORT_MAP.put("SOUTH CAROLINA", "SC");
        LONG_TO_SHORT_MAP.put("SOUTH DAKOTA", "SD");
        LONG_TO_SHORT_MAP.put("TENNESSEE", "TN");
        LONG_TO_SHORT_MAP.put("TEXAS", "TX");
        LONG_TO_SHORT_MAP.put("UTAH", "UT");
        LONG_TO_SHORT_MAP.put("VERMONT", "VT");
        LONG_TO_SHORT_MAP.put("VIRGINIA", "VA");
        LONG_TO_SHORT_MAP.put("WASHINGTON", "WA");
        LONG_TO_SHORT_MAP.put("WEST VIRGINIA", "WV");
        LONG_TO_SHORT_MAP.put("WISCONSIN", "WI");
        LONG_TO_SHORT_MAP.put("WYOMING", "WY");
    }

    private static String getAbbreviatedState(String str) {
        String str2 = (String) LONG_TO_SHORT_MAP.get(str.toUpperCase());
        return Strings.notEmpty(str2) ? str2 : str;
    }

    public static String getFormattedLocation(String str, String str2, String str3) {
        String abbreviatedState = getAbbreviatedState(str2);
        if (Strings.notEmpty(str3) && Strings.notEmpty(abbreviatedState)) {
            return String.format("%s, %s", new Object[]{str3, abbreviatedState});
        } else if (Strings.notEmpty(str3)) {
            return str3;
        } else {
            return Strings.notEmpty(abbreviatedState) ? abbreviatedState : str;
        }
    }
}
