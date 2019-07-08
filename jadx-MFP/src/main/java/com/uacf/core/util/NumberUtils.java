package com.uacf.core.util;

import com.google.android.exoplayer2.C;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.constants.Constants.ABTest.SmartWaterPhase1;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

public final class NumberUtils {
    public static final double FLT_EPSILON = 1.192092896E-7d;
    private static final NavigableMap<Long, String> SUFFIXES = new TreeMap();

    public static float getValueFromPercentage(float f, float f2) {
        return (f * f2) / 100.0f;
    }

    public static float getFloatAtStartOf(String str) {
        if (Strings.isEmpty(str)) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        try {
            int indexOf = str.indexOf(".");
            if (indexOf >= 0) {
                int i = indexOf + 2;
                if (i > str.length()) {
                    i = str.length();
                }
                str = str.substring(0, i);
            }
            float parseFloat = Float.parseFloat(str);
            if (parseFloat < BitmapDescriptorFactory.HUE_RED) {
                parseFloat = BitmapDescriptorFactory.HUE_RED;
            }
            return parseFloat;
        } catch (Exception | NumberFormatException unused) {
            return BitmapDescriptorFactory.HUE_RED;
        }
    }

    public static float localeFloatFromString(String str) {
        return tryParseFloat(str);
    }

    public static String localeStringFromFloat(float f) {
        return localeStringFromFloat(f, true);
    }

    public static String localeStringFromFloat(float f, boolean z) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setGroupingUsed(z);
        return numberInstance.format((double) f);
    }

    public static String localeStringFromFloatWithMaxFractionDigits(float f, int i) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setGroupingUsed(true);
        numberInstance.setMaximumFractionDigits(i);
        return numberInstance.format((double) f);
    }

    public static String localeStringFromFloatWithExactFractionDigits(float f, int i) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setGroupingUsed(true);
        numberInstance.setMaximumFractionDigits(i);
        numberInstance.setMinimumFractionDigits(i);
        return numberInstance.format((double) f);
    }

    public static String localeStringFromIntWithSeparators(int i) {
        return ((DecimalFormat) NumberFormat.getInstance(Locale.getDefault())).format((long) i);
    }

    public static String localeStringFromLongWithSeparators(long j) {
        return ((DecimalFormat) NumberFormat.getInstance(Locale.getDefault())).format(j);
    }

    public static String normalizeInputString(String str) throws ParseException {
        if (str == null || str.length() == 0) {
            throw new NullPointerException("The argument val cannot be NULL");
        }
        char decimalSeparator = new DecimalFormatSymbols(Locale.getDefault()).getDecimalSeparator();
        char c = decimalSeparator == '.' ? ',' : '.';
        int i = 0;
        while (i < str.length()) {
            if (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.' || str.charAt(i) == ',') {
                i++;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid character found in string at position ");
                sb.append(i);
                throw new ParseException(sb.toString(), i);
            }
        }
        if (str.contains(",") && str.contains(".") && str.lastIndexOf(c) > str.lastIndexOf(decimalSeparator)) {
            int lastIndexOf = str.lastIndexOf(c);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Invalid character found in string at position ");
            sb2.append(lastIndexOf);
            throw new ParseException(sb2.toString(), lastIndexOf);
        } else if (str.indexOf(c) != 0) {
            String replaceAll = str.replace(c, ' ').trim().replaceAll("\\s", "");
            int instancesOfCharIn = Strings.instancesOfCharIn(replaceAll, decimalSeparator);
            if (instancesOfCharIn > 1) {
                throw new ParseException("String can have only one decimal separator", 0);
            } else if (instancesOfCharIn != 1 || replaceAll.length() != 1) {
                return replaceAll;
            } else {
                throw new ParseException("Input is not a valid number", 0);
            }
        } else {
            throw new ParseException("Invalid character found in string at position 0", 0);
        }
    }

    public static String localeStringFromDouble(double d) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setGroupingUsed(true);
        return numberInstance.format(d);
    }

    public static String localeStringFromDoubleNoDecimal(double d) {
        if (Math.abs(d) < 1.0d) {
            d = 0.0d;
        }
        return localeStringFromDouble(d, 0);
    }

    public static String localeStringFromAbsDoubleNoDecimal(double d) {
        return localeStringFromDouble(Math.abs(d), 0);
    }

    public static String localeStringFromInt(int i) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setGroupingUsed(true);
        return numberInstance.format((long) i);
    }

    public static String localeStringFromDoubleOneDecimalIfNeeded(double d) {
        return localeStringFromDouble(d, 1);
    }

    public static String localeStringFromDouble(double d, int i) {
        return localeStringFromDouble(d, i, true);
    }

    public static String localeStringFromDouble(double d, int i, boolean z) {
        if (i <= 0 && Math.abs(d) < 1.0d) {
            d = 0.0d;
        }
        String str = "#,###,##0.";
        for (int i2 = 0; i2 < i; i2++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("#");
            str = sb.toString();
        }
        DecimalFormat decimalFormat = new DecimalFormat(str);
        decimalFormat.setGroupingUsed(z);
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        decimalFormat.setDecimalSeparatorAlwaysShown(false);
        return decimalFormat.format(d);
    }

    public static final int clamp(int i, int i2, int i3) {
        return Math.min(i3, Math.max(i, i2));
    }

    public static final float clamp(float f, float f2, float f3) {
        return Math.min(f3, Math.max(f, f2));
    }

    public static boolean isEffectivelyZero(double d) {
        return Math.abs(d) <= 1.192092896E-7d;
    }

    public static boolean areEffectivelyEqual(double d, double d2) {
        return isEffectivelyZero(d - d2);
    }

    public static boolean areEffectivelyEqual(float f, float f2) {
        return isEffectivelyZero((double) (f - f2));
    }

    public static String getDecimalSeparatorForRegex() {
        char decimalSeparator = new DecimalFormatSymbols(Locale.getDefault()).getDecimalSeparator();
        boolean z = decimalSeparator == '.' || decimalSeparator == ',';
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "\\" : "");
        sb.append(decimalSeparator);
        return Strings.toString(sb.toString());
    }

    public static double roundToNearestPlace(double d, double d2) {
        if (d2 < 0.0d || isEffectivelyZero(d2)) {
            return d;
        }
        double log10 = Math.log10(d2);
        double pow = (double) ((float) Math.pow(10.0d, (double) ((int) (log10 + ((log10 < 0.0d ? -1.0d : 1.0d) * 1.0E-5d)))));
        return pow * ((double) Math.round(d / pow));
    }

    public static float roundToNearestPlace(float f, float f2) {
        return (float) roundToNearestPlace((double) f, (double) f2);
    }

    public static int getBase5Value(float f) {
        return Math.round(f / 5.0f) * 5;
    }

    public static float tryParseFloat(String str) {
        return tryParseFloat(str, BitmapDescriptorFactory.HUE_RED);
    }

    public static float tryParseFloat(String str, float f) {
        return tryParseFloat(str, f, Locale.getDefault());
    }

    public static float tryParseFloat(String str, float f, Locale locale) {
        Number tryParseNumber = tryParseNumber(str, locale);
        return tryParseNumber != null ? tryParseNumber.floatValue() : f;
    }

    public static double tryParseDouble(String str) {
        return tryParseDouble(str, 0.0d);
    }

    public static double tryParseDouble(String str, double d) {
        return tryParseDouble(str, d, Locale.getDefault());
    }

    public static double tryParseDouble(String str, double d, Locale locale) {
        Number tryParseNumber = tryParseNumber(str, locale);
        return tryParseNumber != null ? tryParseNumber.doubleValue() : d;
    }

    public static int tryParseInt(String str) {
        return tryParseInt(str, 0);
    }

    public static int tryParseInt(String str, int i) {
        return tryParseInt(str, i, Locale.getDefault());
    }

    public static int tryParseInt(String str, int i, Locale locale) {
        Number tryParseNumber = tryParseNumber(str, locale);
        return tryParseNumber != null ? tryParseNumber.intValue() : i;
    }

    public static long tryParseLong(String str) {
        return tryParseLong(str, 0);
    }

    public static long tryParseLong(String str, long j) {
        return tryParseLong(str, j, Locale.getDefault());
    }

    public static long tryParseLong(String str, long j, Locale locale) {
        Number tryParseNumber = tryParseNumber(str, locale);
        return tryParseNumber != null ? tryParseNumber.longValue() : j;
    }

    private static Number tryParseNumber(String str) {
        return tryParseNumber(str, Locale.getDefault());
    }

    private static Number tryParseNumber(String str, Locale locale) {
        if (Strings.notEmpty(str)) {
            try {
                return NumberFormat.getNumberInstance(locale).parse(str);
            } catch (ParseException e) {
                Ln.e(e);
            }
        }
        return null;
    }

    public static double getDoubleValue(Number number) {
        if (number != null) {
            return number.doubleValue();
        }
        return 0.0d;
    }

    public static int getIntValue(Number number) {
        if (number != null) {
            return number.intValue();
        }
        return 0;
    }

    public static boolean getBooleanValue(Boolean bool) {
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public static float getFloatValue(Number number) {
        return number != null ? number.floatValue() : BitmapDescriptorFactory.HUE_RED;
    }

    public static float getPercentage(float f, float f2) {
        return isEffectivelyZero((double) f2) ? BitmapDescriptorFactory.HUE_RED : (f * 100.0f) / f2;
    }

    public static int getRoundedPercentage(float f, float f2) {
        return Math.round(getPercentage(f, f2));
    }

    public static int intValue(Integer num) {
        return intValue(num, 0);
    }

    public static int intValue(Integer num, int i) {
        return num == null ? i : num.intValue();
    }

    static {
        SUFFIXES.put(Long.valueOf(1000), "k");
        SUFFIXES.put(Long.valueOf(1000000), Attributes.MONDAY);
        SUFFIXES.put(Long.valueOf(C.NANOS_PER_SECOND), SmartWaterPhase1.SPONSORED_WATER_ON_VARIANT);
        SUFFIXES.put(Long.valueOf(1000000000000L), "T");
        SUFFIXES.put(Long.valueOf(1000000000000000L), "P");
        SUFFIXES.put(Long.valueOf(1000000000000000000L), "E");
    }

    public static String formatWithSuffix(long j) {
        StringBuilder sb;
        if (j == Long.MIN_VALUE) {
            return formatWithSuffix(-9223372036854775807L);
        }
        if (j < 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("-");
            sb2.append(formatWithSuffix(-j));
            return sb2.toString();
        } else if (j < 1000) {
            return Long.toString(j);
        } else {
            Entry floorEntry = SUFFIXES.floorEntry(Long.valueOf(j));
            Long l = (Long) floorEntry.getKey();
            String str = (String) floorEntry.getValue();
            long longValue = j / (l.longValue() / 10);
            if (longValue < 100 && ((double) longValue) / 10.0d != ((double) (longValue / 10))) {
                sb = new StringBuilder();
                sb.append(((double) longValue) / 10.0d);
            } else {
                sb = new StringBuilder();
                sb.append(longValue / 10);
            }
            sb.append(str);
            return sb.toString();
        }
    }
}
