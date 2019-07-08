package com.nimbusds.jwt.util;

import java.util.Date;

public class DateUtils {
    public static long toSecondsSinceEpoch(Date date) {
        return date.getTime() / 1000;
    }

    private DateUtils() {
    }
}
