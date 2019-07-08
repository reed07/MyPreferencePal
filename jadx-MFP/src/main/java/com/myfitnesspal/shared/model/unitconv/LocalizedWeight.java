package com.myfitnesspal.shared.model.unitconv;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.uacf.core.exception.UacfNotImplementedException;
import com.uacf.core.util.NumberUtils;
import java.lang.reflect.Type;

public class LocalizedWeight implements Parcelable {
    public static final Creator<LocalizedWeight> CREATOR = new Creator<LocalizedWeight>() {
        public LocalizedWeight createFromParcel(Parcel parcel) {
            return new LocalizedWeight(parcel);
        }

        public LocalizedWeight[] newArray(int i) {
            return new LocalizedWeight[i];
        }
    };
    private static FormatStringProvider DEFAULT_VALUE_FORMATTER;
    private static FormatStringProvider DEFAULT_VALUE_UNIT_FORMATTER;
    private static FormatStringProvider ROUNDED_VALUE_FORMATTER;
    private static FormatStringProvider ROUNDED_VALUE_UNIT_FORMATTER;
    private double valueInPounds;

    public static class Deserializer implements JsonDeserializer<LocalizedWeight> {
        public LocalizedWeight deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalizedWeight.fromPounds(jsonElement.getAsDouble());
        }
    }

    public static final class FormatStringProvider {
        private Context context;
        private FormatType formatType;
        private String kilograms;
        private String pounds;
        private String stonePounds;
        private String stones;
        private ValueDisplay valueDisplay;

        public enum FormatType {
            ValueOnly,
            ValueAndUnitSeprate
        }

        public enum ValueDisplay {
            OneDecimal,
            TwoDecimal,
            Rounded
        }

        public FormatStringProvider(Context context2, ValueDisplay valueDisplay2) {
            this(context2, FormatType.ValueOnly, valueDisplay2, (int) R.string.st_format, (int) R.string.weight_in_stone_pounds, (int) R.string.weight_in_pounds, (int) R.string.weight_in_kilograms);
        }

        public FormatStringProvider(Context context2, FormatType formatType2, ValueDisplay valueDisplay2, int i, int i2, int i3, int i4) {
            this.context = context2.getApplicationContext();
            this.formatType = formatType2;
            this.valueDisplay = valueDisplay2;
            this.stones = context2.getString(i);
            this.stonePounds = context2.getString(i2);
            this.pounds = context2.getString(i3);
            this.kilograms = context2.getString(i4);
        }

        public FormatStringProvider(Context context2, FormatType formatType2, ValueDisplay valueDisplay2, String str, String str2, String str3, String str4) {
            this.context = context2.getApplicationContext();
            this.formatType = formatType2;
            this.valueDisplay = valueDisplay2;
            this.stones = str;
            this.stonePounds = str2;
            this.pounds = str3;
            this.kilograms = str4;
        }

        public FormatType getFormatType() {
            return this.formatType;
        }

        public ValueDisplay getValueDisplay() {
            return this.valueDisplay;
        }

        public Context getContext() {
            return this.context;
        }

        public String getStones() {
            return this.stones;
        }

        public String getStonePounds() {
            return this.stonePounds;
        }

        public String getPounds() {
            return this.pounds;
        }

        public String getKilograms() {
            return this.kilograms;
        }

        static String getDisplayValue(double d, ValueDisplay valueDisplay2) {
            switch (valueDisplay2) {
                case OneDecimal:
                    return NumberUtils.localeStringFromDouble(d, 1);
                case TwoDecimal:
                    return NumberUtils.localeStringFromDouble(d, 2);
                case Rounded:
                    return NumberUtils.localeStringFromIntWithSeparators((int) Math.round(d));
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("type unexpected: ");
                    sb.append(valueDisplay2);
                    throw new IllegalArgumentException(sb.toString());
            }
        }
    }

    public static class Serializer implements JsonSerializer<LocalizedWeight> {
        public JsonElement serialize(LocalizedWeight localizedWeight, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive((Number) Double.valueOf(localizedWeight.getValue(Weight.POUNDS)));
        }
    }

    private static double getKilogramsFromPounds(double d) {
        return d * 0.4535923703803783d;
    }

    private static double getPoundsFromKilograms(double d) {
        return d * 2.20462262d;
    }

    private static double getPoundsFromStones(double d) {
        return d * 14.0d;
    }

    private static double getStonesFromPounds(double d) {
        return d * 0.07142857142857142d;
    }

    public int describeContents() {
        return 0;
    }

    private LocalizedWeight(double d) {
        this.valueInPounds = d;
    }

    private LocalizedWeight(Parcel parcel) {
        readFromParcel(parcel);
    }

    public static LocalizedWeight fromPounds(double d) {
        return new LocalizedWeight(d);
    }

    public static LocalizedWeight fromKilograms(double d) {
        return new LocalizedWeight(getPoundsFromKilograms(d));
    }

    public static LocalizedWeight fromStones(double d) {
        return new LocalizedWeight(getPoundsFromStones(d));
    }

    public static LocalizedWeight fromStonePounds(double d, double d2) {
        return fromStones(d + (d2 / 14.0d));
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.myfitnesspal.shared.model.unitconv.LocalizedWeight fromMeasuredValue(com.myfitnesspal.shared.model.v2.MfpMeasuredValue r3) {
        /*
            java.lang.String r0 = r3.getUnit()
            int r1 = r0.hashCode()
            r2 = -1470006725(0xffffffffa8617a3b, float:-1.25165135E-14)
            if (r1 == r2) goto L_0x001d
            r2 = -982397081(0xffffffffc571cf67, float:-3868.9626)
            if (r1 == r2) goto L_0x0013
            goto L_0x0027
        L_0x0013:
            java.lang.String r1 = "pounds"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0027
            r0 = 0
            goto L_0x0028
        L_0x001d:
            java.lang.String r1 = "kilograms"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0027
            r0 = 1
            goto L_0x0028
        L_0x0027:
            r0 = -1
        L_0x0028:
            switch(r0) {
                case 0: goto L_0x003f;
                case 1: goto L_0x0035;
                default: goto L_0x002b;
            }
        L_0x002b:
            float r3 = r3.getValue()
            double r0 = (double) r3
            com.myfitnesspal.shared.model.unitconv.LocalizedWeight r3 = fromStones(r0)
            return r3
        L_0x0035:
            float r3 = r3.getValue()
            double r0 = (double) r3
            com.myfitnesspal.shared.model.unitconv.LocalizedWeight r3 = fromKilograms(r0)
            return r3
        L_0x003f:
            float r3 = r3.getValue()
            double r0 = (double) r3
            com.myfitnesspal.shared.model.unitconv.LocalizedWeight r3 = fromPounds(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.model.unitconv.LocalizedWeight.fromMeasuredValue(com.myfitnesspal.shared.model.v2.MfpMeasuredValue):com.myfitnesspal.shared.model.unitconv.LocalizedWeight");
    }

    public static LocalizedWeight from(double d, Weight weight) {
        switch (weight) {
            case POUNDS:
                return fromPounds(d);
            case STONES:
            case STONES_POUNDS:
                return fromStones(d);
            case KILOGRAMS:
                return fromKilograms(d);
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("unit ");
                sb.append(weight);
                sb.append(" invalid");
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public void setValue(Weight weight, double d) {
        if (weight == Weight.POUNDS) {
            this.valueInPounds = d;
        } else if (weight == Weight.KILOGRAMS) {
            this.valueInPounds = getPoundsFromKilograms(d);
        } else if (weight == Weight.STONES || weight == Weight.STONES_POUNDS) {
            this.valueInPounds = getPoundsFromStones(d);
        } else {
            throw new IllegalArgumentException("invalid units!");
        }
    }

    public double getValue(Weight weight) {
        if (weight == Weight.POUNDS) {
            return this.valueInPounds;
        }
        if (weight == Weight.KILOGRAMS) {
            return getKilogramsFromPounds(this.valueInPounds);
        }
        if (weight == Weight.STONES || weight == Weight.STONES_POUNDS) {
            return getStonesFromPounds(this.valueInPounds);
        }
        throw new IllegalArgumentException("invalid units!");
    }

    public double toPounds() {
        return this.valueInPounds;
    }

    public double toKilograms() {
        return getKilogramsFromPounds(this.valueInPounds);
    }

    public double toStones() {
        return getStonesFromPounds(this.valueInPounds);
    }

    public static MajorMinorUnits<Weight> getMajorMinorUnits(LocalizedWeight localizedWeight, Weight weight) {
        switch (weight) {
            case POUNDS:
            case KILOGRAMS:
                throw new UacfNotImplementedException();
            case STONES:
            case STONES_POUNDS:
                String[] stonePoundsAsArrayString = getStonePoundsAsArrayString(localizedWeight.valueInPounds);
                int[] stonePoundsAsArrayInt = getStonePoundsAsArrayInt(localizedWeight.valueInPounds);
                MajorMinorUnits majorMinorUnits = new MajorMinorUnits(weight, stonePoundsAsArrayInt[0], stonePoundsAsArrayString[0], stonePoundsAsArrayInt[1], stonePoundsAsArrayString[1]);
                return majorMinorUnits;
            default:
                throw new IllegalArgumentException("toType");
        }
    }

    public static String getDisplayString(FormatStringProvider formatStringProvider, LocalizedWeight localizedWeight, Weight weight) {
        FormatType formatType = formatStringProvider.getFormatType();
        ValueDisplay valueDisplay = formatStringProvider.getValueDisplay();
        Context context = formatStringProvider.getContext();
        if (weight == Weight.STONES_POUNDS) {
            String[] stonePoundsAsArrayString = getStonePoundsAsArrayString(localizedWeight.valueInPounds);
            switch (formatType) {
                case ValueOnly:
                    return String.format(formatStringProvider.getStonePounds(), new Object[]{stonePoundsAsArrayString[0], stonePoundsAsArrayString[1]});
                case ValueAndUnitSeprate:
                    return String.format(formatStringProvider.getStones(), new Object[]{stonePoundsAsArrayString[0], context.getString(R.string.stone), stonePoundsAsArrayString[1], context.getString(R.string.lbs)});
            }
        } else if (weight == Weight.STONES) {
            String displayValue = FormatStringProvider.getDisplayValue(getStonesFromPounds(localizedWeight.valueInPounds), valueDisplay);
            switch (formatType) {
                case ValueOnly:
                    return String.format(formatStringProvider.getStones(), new Object[]{displayValue});
                case ValueAndUnitSeprate:
                    return String.format(formatStringProvider.getStones(), new Object[]{displayValue, context.getString(R.string.st)});
            }
        } else if (weight == Weight.POUNDS) {
            String displayValue2 = FormatStringProvider.getDisplayValue(localizedWeight.valueInPounds, valueDisplay);
            switch (formatType) {
                case ValueOnly:
                    return String.format(formatStringProvider.getPounds(), new Object[]{displayValue2});
                case ValueAndUnitSeprate:
                    return String.format(formatStringProvider.getPounds(), new Object[]{displayValue2, context.getString(R.string.lbs)});
            }
        } else if (weight == Weight.KILOGRAMS) {
            String displayValue3 = FormatStringProvider.getDisplayValue(localizedWeight.getValue(Weight.KILOGRAMS), valueDisplay);
            switch (formatType) {
                case ValueOnly:
                    return String.format(formatStringProvider.getKilograms(), new Object[]{displayValue3});
                case ValueAndUnitSeprate:
                    return String.format(formatStringProvider.getKilograms(), new Object[]{displayValue3, context.getString(R.string.kg)});
            }
        }
        throw new IllegalArgumentException("invalid units!");
    }

    public static String getDisplayString(Context context, LocalizedWeight localizedWeight, Weight weight) {
        FormatStringProvider formatStringProvider = new FormatStringProvider(context.getApplicationContext(), FormatType.ValueOnly, ValueDisplay.OneDecimal, (int) R.string.st_format, (int) R.string.weight_in_stone_pounds, (int) R.string.weight_in_pounds, (int) R.string.weight_in_kilograms);
        DEFAULT_VALUE_UNIT_FORMATTER = formatStringProvider;
        return getDisplayString(DEFAULT_VALUE_UNIT_FORMATTER, localizedWeight, weight);
    }

    public static String getLongDisplayString(Context context, LocalizedWeight localizedWeight, Weight weight) {
        FormatStringProvider formatStringProvider = new FormatStringProvider(context.getApplicationContext(), FormatType.ValueOnly, ValueDisplay.OneDecimal, (int) R.string.st_format_long, (int) R.string.weight_in_stone_pounds_long, (int) R.string.weight_in_pounds_long, (int) R.string.weight_in_kilograms_long);
        DEFAULT_VALUE_UNIT_FORMATTER = formatStringProvider;
        return getDisplayString(DEFAULT_VALUE_UNIT_FORMATTER, localizedWeight, weight);
    }

    public static String getRoundedDisplayString(Context context, LocalizedWeight localizedWeight, Weight weight) {
        if (ROUNDED_VALUE_UNIT_FORMATTER == null) {
            FormatStringProvider formatStringProvider = new FormatStringProvider(context.getApplicationContext(), FormatType.ValueOnly, ValueDisplay.Rounded, (int) R.string.st_format, (int) R.string.weight_in_stone_pounds, (int) R.string.weight_in_pounds, (int) R.string.weight_in_kilograms);
            ROUNDED_VALUE_UNIT_FORMATTER = formatStringProvider;
        }
        return getDisplayString(ROUNDED_VALUE_UNIT_FORMATTER, localizedWeight, weight);
    }

    public static String getDisplayStringWithoutUnit(Context context, LocalizedWeight localizedWeight, Weight weight) {
        if (DEFAULT_VALUE_FORMATTER == null) {
            FormatStringProvider formatStringProvider = new FormatStringProvider(context.getApplicationContext(), FormatType.ValueOnly, ValueDisplay.OneDecimal, "%s", "%s", "%s", "%s");
            DEFAULT_VALUE_FORMATTER = formatStringProvider;
        }
        return getDisplayString(DEFAULT_VALUE_FORMATTER, localizedWeight, weight);
    }

    public static String getRoundedDisplayStringWithoutUnit(Context context, LocalizedWeight localizedWeight, Weight weight) {
        if (ROUNDED_VALUE_FORMATTER == null) {
            FormatStringProvider formatStringProvider = new FormatStringProvider(context.getApplicationContext(), FormatType.ValueOnly, ValueDisplay.Rounded, "%s", "%s", "%s", "%s");
            ROUNDED_VALUE_FORMATTER = formatStringProvider;
        }
        return getDisplayString(ROUNDED_VALUE_FORMATTER, localizedWeight, weight);
    }

    public static String getAbbreviatedUnit(Context context, Weight weight) {
        switch (weight) {
            case POUNDS:
                return context.getString(R.string.lbs);
            case STONES:
            case STONES_POUNDS:
                return context.getString(R.string.stone);
            case KILOGRAMS:
                return context.getString(R.string.kg);
            default:
                throw new IllegalArgumentException("invalid unit!");
        }
    }

    public boolean isZero() {
        return this.valueInPounds == 0.0d;
    }

    public boolean isLessThan(LocalizedWeight localizedWeight) {
        return this.valueInPounds < localizedWeight.valueInPounds;
    }

    public boolean isLessThanOrEqualTo(LocalizedWeight localizedWeight) {
        return this.valueInPounds <= localizedWeight.valueInPounds;
    }

    public boolean isGreaterThan(LocalizedWeight localizedWeight) {
        return this.valueInPounds > localizedWeight.valueInPounds;
    }

    public boolean isGreaterThanOrEqualTo(LocalizedWeight localizedWeight) {
        return this.valueInPounds >= localizedWeight.valueInPounds;
    }

    public boolean isEqualTo(LocalizedWeight localizedWeight) {
        return NumberUtils.areEffectivelyEqual(this.valueInPounds, localizedWeight.valueInPounds);
    }

    private static int[] getStonePoundsAsArrayInt(double d) {
        if (d >= 0.0d) {
            int i = (int) (0.07142857142857142d * d);
            return new int[]{i, (int) Math.round(d - (((double) i) * 14.0d))};
        }
        throw new IllegalArgumentException("pounds cannot be negative");
    }

    private static String[] getStonePoundsAsArrayString(double d) {
        if (d >= 0.0d) {
            int i = (int) (0.07142857142857142d * d);
            double roundToNearestPlace = NumberUtils.roundToNearestPlace(d - (((double) i) * 14.0d), 0.1d);
            if (roundToNearestPlace >= 14.0d) {
                i++;
                roundToNearestPlace -= 14.0d;
            }
            return new String[]{NumberUtils.localeStringFromDoubleNoDecimal((double) i), NumberUtils.localeStringFromDouble(roundToNearestPlace, 1)};
        }
        throw new IllegalArgumentException("pounds cannot be negative");
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.valueInPounds);
    }

    private void readFromParcel(Parcel parcel) {
        this.valueInPounds = parcel.readDouble();
    }
}
