package com.myfitnesspal.shared.util;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;

public class UnitsUtils {
    private static final int CENTIMETERS_PER_METER = 100;

    public enum Energy {
        CALORIES(2),
        KILOJOULES(7);
        
        private static final String S_CAL = "cal";
        private static final String S_KJ = "kJ";
        private final int value;

        private Energy(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static Energy fromInt(int i) {
            if (i == 2) {
                return CALORIES;
            }
            if (i == 7) {
                return KILOJOULES;
            }
            throw new IllegalArgumentException("Wrong value for Energy enum");
        }

        public String toString() {
            int i = this.value;
            if (i == 2) {
                return S_CAL;
            }
            if (i == 7) {
                return "kJ";
            }
            throw new IllegalArgumentException("Wrong value for Energy enum");
        }

        public static Energy fromString(String str) {
            if (Strings.isEmpty(str)) {
                throw new IllegalArgumentException("Wrong value for Energy enum");
            } else if (Strings.equalsIgnoreCase(str, S_CAL) || Strings.equalsIgnoreCase(str, "calories")) {
                return CALORIES;
            } else {
                if (Strings.equalsIgnoreCase(str, "kJ") || Strings.equalsIgnoreCase(str, "kilojoules")) {
                    return KILOJOULES;
                }
                throw new IllegalArgumentException("Wrong value for Energy enum");
            }
        }
    }

    public enum Length {
        CENTIMETERS(8),
        KILOMETERS(10),
        MILES(9),
        INCHES(3),
        FEET(4),
        FEET_INCHES(11),
        METERS_CENTIMETERS(14);
        
        private static final String S_CM = "cm";
        private static final String S_FT = "ft";
        private static final String S_FT_IN = "ft_in";
        private static final String S_IN = "in";
        private static final String S_KM = "km";
        private static final String S_MI = "mi";
        private static final String S_M_CM = "m_cm";
        private final int value;

        private Length(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static Length fromInt(int i) {
            switch (i) {
                case 3:
                    return INCHES;
                case 4:
                    return FEET;
                case 8:
                    return CENTIMETERS;
                case 9:
                    return MILES;
                case 10:
                case 13:
                    return KILOMETERS;
                case 11:
                    return FEET_INCHES;
                case 14:
                    return METERS_CENTIMETERS;
                default:
                    throw new IllegalArgumentException("Wrong value for Length enum");
            }
        }

        public String toString() {
            switch (this.value) {
                case 3:
                    return S_IN;
                case 4:
                    return S_FT;
                case 8:
                    return S_CM;
                case 9:
                    return S_MI;
                case 10:
                case 13:
                    return S_KM;
                case 11:
                    return S_FT_IN;
                case 14:
                    return S_M_CM;
                default:
                    throw new IllegalArgumentException("Wrong value for Length enum");
            }
        }

        public static Length fromString(String str) {
            if (Strings.isEmpty(str)) {
                throw new IllegalArgumentException("Wrong value for Length enum");
            } else if (Strings.equalsIgnoreCase(str, S_CM)) {
                return CENTIMETERS;
            } else {
                if (Strings.equalsIgnoreCase(str, S_KM)) {
                    return KILOMETERS;
                }
                if (Strings.equalsIgnoreCase(str, S_MI)) {
                    return MILES;
                }
                if (Strings.equalsIgnoreCase(str, S_IN)) {
                    return INCHES;
                }
                if (Strings.equalsIgnoreCase(str, S_FT)) {
                    return FEET;
                }
                if (Strings.equalsIgnoreCase(str, S_FT_IN)) {
                    return FEET_INCHES;
                }
                if (Strings.equalsIgnoreCase(str, S_M_CM)) {
                    return METERS_CENTIMETERS;
                }
                throw new IllegalArgumentException("Wrong value for Length enum");
            }
        }
    }

    public enum Water {
        CUPS(14),
        MILLILITERS(15),
        FL_OZ(16);
        
        private static final String S_CUPS = "cups";
        private static final String S_FL_OZ = "fluid_ounces";
        private static final String S_MILLILITERS = "milliliters";
        private final int value;

        private Water(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static Water fromInt(int i) {
            switch (i) {
                case 14:
                    return CUPS;
                case 15:
                    return MILLILITERS;
                case 16:
                    return FL_OZ;
                default:
                    throw new IllegalArgumentException("Wrong value for Water enum");
            }
        }

        public String toString() {
            switch (this.value) {
                case 14:
                    return "cups";
                case 15:
                    return "milliliters";
                case 16:
                    return S_FL_OZ;
                default:
                    throw new IllegalArgumentException("Wrong value for Water enum");
            }
        }

        public static Water fromString(String str) {
            if (!Strings.isEmpty(str)) {
                char c = 65535;
                int hashCode = str.hashCode();
                if (hashCode != 3065333) {
                    if (hashCode != 1348024350) {
                        if (hashCode == 1746197884 && str.equals("milliliters")) {
                            c = 1;
                        }
                    } else if (str.equals(S_FL_OZ)) {
                        c = 2;
                    }
                } else if (str.equals("cups")) {
                    c = 0;
                }
                switch (c) {
                    case 0:
                        return CUPS;
                    case 1:
                        return MILLILITERS;
                    case 2:
                        return FL_OZ;
                    default:
                        throw new IllegalArgumentException("Wrong value for Weight enum");
                }
            } else {
                throw new IllegalArgumentException("Wrong value for Water enum");
            }
        }
    }

    public enum Weight {
        KILOGRAMS(6),
        POUNDS(1),
        STONES(5),
        STONES_POUNDS(12);
        
        private static final String S_KG = "kg";
        private static final String S_KILOGRAMS = "kilograms";
        private static final String S_LBS = "lbs";
        private static final String S_POUNDS = "pounds";
        private static final String S_ST = "st";
        private static final String S_ST_LBS = "st_lbs";
        private final int value;

        private Weight(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static Weight fromInt(int i) {
            if (i == 1) {
                return POUNDS;
            }
            if (i == 12) {
                return STONES_POUNDS;
            }
            switch (i) {
                case 5:
                    return STONES;
                case 6:
                    return KILOGRAMS;
                default:
                    throw new IllegalArgumentException("Wrong value for Weight enum");
            }
        }

        public String toString() {
            int i = this.value;
            if (i == 1) {
                return S_LBS;
            }
            if (i == 12) {
                return S_ST_LBS;
            }
            switch (i) {
                case 5:
                    return "st";
                case 6:
                    return S_KG;
                default:
                    throw new IllegalArgumentException("Wrong value for Weight enum");
            }
        }

        public static Weight fromString(String str) {
            if (!Strings.isEmpty(str)) {
                char c = 65535;
                int hashCode = str.hashCode();
                if (hashCode != -1470006725) {
                    if (hashCode != -982397081) {
                        if (hashCode != -892549409) {
                            if (hashCode != 3420) {
                                if (hashCode != 3681) {
                                    if (hashCode == 106941 && str.equals(S_LBS)) {
                                        c = 2;
                                    }
                                } else if (str.equals("st")) {
                                    c = 4;
                                }
                            } else if (str.equals(S_KG)) {
                                c = 0;
                            }
                        } else if (str.equals(S_ST_LBS)) {
                            c = 5;
                        }
                    } else if (str.equals("pounds")) {
                        c = 3;
                    }
                } else if (str.equals("kilograms")) {
                    c = 1;
                }
                switch (c) {
                    case 0:
                    case 1:
                        return KILOGRAMS;
                    case 2:
                    case 3:
                        return POUNDS;
                    case 4:
                        return STONES;
                    case 5:
                        return STONES_POUNDS;
                    default:
                        throw new IllegalArgumentException("Wrong value for Weight enum");
                }
            } else {
                throw new IllegalArgumentException("Wrong value for Weight enum");
            }
        }
    }

    public static double getCalories(double d) {
        return d * 0.2390057361376673d;
    }

    public static double getCentimetersFromFeet(double d) {
        if (d >= 0.0d) {
            return 30.48d * d;
        }
        return 0.0d;
    }

    public static double getCentimetersFromInches(double d) {
        if (d >= 0.0d) {
            return 2.54d * d;
        }
        return 0.0d;
    }

    public static double getCups(double d) {
        return d * 0.004166666666666667d;
    }

    public static double getFeetFromCentimeters(double d) {
        if (d >= 0.0d) {
            return 0.03280839895013123d * d;
        }
        return 0.0d;
    }

    public static double getFeetFromInches(double d) {
        if (d >= 0.0d) {
            return d / 12.0d;
        }
        return 0.0d;
    }

    public static double getFluidOunces(double d) {
        return d * 0.03381402220161069d;
    }

    public static double getInchesFromCentimeters(double d) {
        if (d >= 0.0d) {
            return 0.39370078740157477d * d;
        }
        return 0.0d;
    }

    public static double getInchesFromFeet(double d) {
        if (d >= 0.0d) {
            return 12.0d * d;
        }
        return 0.0d;
    }

    public static double getKilogramsFromPounds(double d) {
        if (d >= 0.0d) {
            return 0.4535923703803783d * d;
        }
        return 0.0d;
    }

    public static double getKilogramsFromStones(double d) {
        if (d >= 0.0d) {
            return 6.350293185325296d * d;
        }
        return 0.0d;
    }

    public static double getKilojoules(double d) {
        return d * 4.184d;
    }

    public static double getKilometers(double d) {
        if (d >= 0.0d) {
            return 1.6093444978925633d * d;
        }
        return 0.0d;
    }

    public static double getMiles(double d) {
        if (d >= 0.0d) {
            return 0.621371d * d;
        }
        return 0.0d;
    }

    public static double getMillilitersFromCups(float f) {
        return ((double) f) * 240.0d;
    }

    public static double getMillilitersFromFluidOunces(float f) {
        return ((double) f) * 29.57353d;
    }

    public static double getPoundsFromKilograms(double d) {
        if (d >= 0.0d) {
            return 2.20462262d * d;
        }
        return 0.0d;
    }

    public static double getPoundsFromStones(double d) {
        if (d >= 0.0d) {
            return 14.0d * d;
        }
        return 0.0d;
    }

    public static double getStonesFromKilograms(double d) {
        if (d >= 0.0d) {
            return 0.1574730442857143d * d;
        }
        return 0.0d;
    }

    public static double getStonesFromPounds(double d) {
        if (d >= 0.0d) {
            return 0.07142857142857142d * d;
        }
        return 0.0d;
    }

    private UnitsUtils() {
        throw new AssertionError();
    }

    public static double getWeight(Weight weight, Weight weight2, double d) {
        if (weight == weight2) {
            return d;
        }
        switch (weight2) {
            case STONES:
            case STONES_POUNDS:
                d = getPoundsFromStones(d);
                break;
            case KILOGRAMS:
                d = getPoundsFromKilograms(d);
                break;
        }
        switch (weight) {
            case STONES:
            case STONES_POUNDS:
                d = getStonesFromPounds(d);
                break;
            case KILOGRAMS:
                d = getKilogramsFromPounds(d);
                break;
        }
        return d;
    }

    public static String[] getStonesPoundsFromKilograms(double d) {
        return getStonesPoundsFromPounds(getPoundsFromKilograms(d));
    }

    public static String[] getStonesPoundsFromPounds(double d) {
        if (d < 0.0d) {
            return new String[]{"0", "0"};
        }
        int i = (int) (0.07142857142857142d * d);
        double roundToNearestPlace = NumberUtils.roundToNearestPlace(d - (((double) i) * 14.0d), 0.1d);
        if (roundToNearestPlace >= 14.0d) {
            i++;
            roundToNearestPlace -= 14.0d;
        }
        return new String[]{NumberUtils.localeStringFromDoubleNoDecimal((double) i), NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(roundToNearestPlace)};
    }

    public static String getKilogramsFromStonesPounds(double d, double d2) {
        return NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(getKilogramsFromStones(d) + getKilogramsFromPounds(d2));
    }

    public static String getPoundsFromStonesPounds(double d, double d2) {
        double poundsFromStones = getPoundsFromStones(d);
        if (d2 < 0.0d) {
            d2 = 0.0d;
        }
        return NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(poundsFromStones + d2);
    }

    public static String getFeetAndInches(double d) {
        String[] feetAndInchesAsStringArray = getFeetAndInchesAsStringArray(d);
        StringBuilder sb = new StringBuilder();
        sb.append(feetAndInchesAsStringArray[0]);
        sb.append("'");
        sb.append(feetAndInchesAsStringArray[1]);
        sb.append("\"");
        return sb.toString();
    }

    public static int[] getFeetAndInchesAsIntArray(double d) {
        if (d < 0.0d) {
            return new int[]{0, 0};
        }
        int feetFromCentimeters = (int) getFeetFromCentimeters(d);
        int round = (int) Math.round(getInchesFromCentimeters(d - getCentimetersFromFeet((double) feetFromCentimeters)));
        double d2 = (double) round;
        if (d2 >= 12.0d) {
            feetFromCentimeters++;
            round = (int) (d2 - 12.0d);
        }
        return new int[]{feetFromCentimeters, round};
    }

    public static String[] getFeetAndInchesAsStringArray(double d) {
        if (d < 0.0d) {
            return new String[]{"0", "0"};
        }
        int feetFromCentimeters = (int) getFeetFromCentimeters(d);
        long round = Math.round(getInchesFromCentimeters(d - getCentimetersFromFeet((double) feetFromCentimeters)));
        double d2 = (double) round;
        if (d2 >= 12.0d) {
            feetFromCentimeters++;
            round = (long) (d2 - 12.0d);
        }
        return new String[]{NumberUtils.localeStringFromDoubleNoDecimal((double) feetFromCentimeters), NumberUtils.localeStringFromDoubleNoDecimal((double) round)};
    }

    public static int[] getMeterAndCentimetersIntAsArray(double d) {
        if (d < 0.0d) {
            return new int[]{0, 0};
        }
        int i = (int) (d / 100.0d);
        return new int[]{i, (int) Math.round(d - ((double) (i * 100)))};
    }

    public static String[] getMeterAndCentimetersAsStringArray(double d) {
        if (d < 0.0d) {
            return new String[]{"0", "0"};
        }
        int i = (int) (d / 100.0d);
        return new String[]{NumberUtils.localeStringFromDoubleNoDecimal((double) i), NumberUtils.localeStringFromDoubleNoDecimal(d - ((double) (i * 100)))};
    }

    public static String getCentimetersFromFeetInches(double d, double d2) {
        return NumberUtils.localeStringFromDoubleNoDecimal(getCentimetersFromFeet(d) + getCentimetersFromInches(d2));
    }

    public static int getInchesFromFeetInches(double d, double d2) {
        double inchesFromFeet = getInchesFromFeet(d);
        if (d2 < 0.0d) {
            d2 = 0.0d;
        }
        return (int) (inchesFromFeet + d2);
    }

    public static String getLocalizedWeightString(Weight weight, Weight weight2, double d) {
        int i = AnonymousClass1.$SwitchMap$com$myfitnesspal$shared$util$UnitsUtils$Weight[weight2.ordinal()];
        if (i != 1) {
            switch (i) {
                case 3:
                    if (weight == Weight.POUNDS) {
                        return NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(getKilogramsFromPounds(d));
                    }
                    if (weight == Weight.STONES) {
                        return NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(getKilogramsFromStones(d));
                    }
                    if (weight == Weight.KILOGRAMS) {
                        return NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(d);
                    }
                    return "0";
                case 4:
                    if (weight == Weight.KILOGRAMS) {
                        return NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(getPoundsFromKilograms(d));
                    }
                    if (weight == Weight.STONES) {
                        return NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(getPoundsFromStones(d));
                    }
                    if (weight == Weight.POUNDS) {
                        return NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(d);
                    }
                    return "0";
                default:
                    return "0";
            }
        } else if (weight == Weight.KILOGRAMS) {
            return NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(getStonesFromKilograms(d));
        } else {
            if (weight == Weight.POUNDS) {
                return NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(getStonesFromPounds(d));
            }
            return weight == Weight.STONES ? NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(d) : "0";
        }
    }

    public static double getWeightInPounds(Weight weight, String[] strArr) {
        switch (weight) {
            case STONES:
                return getPoundsFromStones((double) NumberUtils.localeFloatFromString(strArr[0]));
            case STONES_POUNDS:
                return (double) NumberUtils.localeFloatFromString(getPoundsFromStonesPounds((double) NumberUtils.localeFloatFromString(strArr[0]), (double) NumberUtils.localeFloatFromString(strArr[1])));
            case KILOGRAMS:
                return getPoundsFromKilograms((double) NumberUtils.localeFloatFromString(strArr[0]));
            case POUNDS:
                return (double) NumberUtils.localeFloatFromString(strArr[0]);
            default:
                return 0.0d;
        }
    }

    public static String getLocalizedLengthString(Length length, Length length2, double d, boolean z) {
        if (length == length2) {
            return NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(d);
        }
        switch (length2) {
            case CENTIMETERS:
                if (length == Length.INCHES) {
                    return z ? NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(getCentimetersFromInches(d)) : NumberUtils.localeStringFromDoubleNoDecimal(getCentimetersFromInches(d));
                } else if (length != Length.FEET) {
                    return "0";
                } else {
                    return z ? NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(getCentimetersFromFeet(d)) : NumberUtils.localeStringFromDoubleNoDecimal(getCentimetersFromFeet(d));
                }
            case KILOMETERS:
                return length == Length.MILES ? NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(getKilometers(d)) : "0";
            case MILES:
                return length == Length.KILOMETERS ? NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(getMiles(d)) : "0";
            case INCHES:
                return length == Length.CENTIMETERS ? NumberUtils.localeStringFromDoubleNoDecimal(getInchesFromCentimeters(d)) : "0";
            case FEET:
                if (length == Length.INCHES) {
                    return NumberUtils.localeStringFromDoubleNoDecimal(getFeetFromInches(d));
                }
                return length == Length.CENTIMETERS ? NumberUtils.localeStringFromDoubleNoDecimal(getFeetFromCentimeters(d)) : "0";
            case FEET_INCHES:
                if (length == Length.CENTIMETERS) {
                    return getFeetAndInches(d);
                }
                return "0";
            default:
                return "0";
        }
    }

    public static String getLocalizedEnergyString(Energy energy, double d) {
        switch (energy) {
            case CALORIES:
                return NumberUtils.localeStringFromDoubleNoDecimal(Math.abs(getCalories(d)));
            case KILOJOULES:
                return NumberUtils.localeStringFromDoubleNoDecimal(Math.abs(getKilojoules(d)));
            default:
                return "0";
        }
    }

    public static float getKilojoules(float f) {
        return Double.valueOf(((double) f) * 4.184d).floatValue();
    }

    public static float getEnergyFloat(Energy energy, float f) {
        switch (energy) {
            case CALORIES:
                return f;
            case KILOJOULES:
                return getKilojoules(f);
            default:
                return BitmapDescriptorFactory.HUE_RED;
        }
    }
}
