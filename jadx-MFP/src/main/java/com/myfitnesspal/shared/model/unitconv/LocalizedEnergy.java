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
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.util.UnitsUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.uacf.core.util.NumberUtils;
import java.lang.reflect.Type;

public class LocalizedEnergy implements Parcelable {
    public static final Creator<LocalizedEnergy> CREATOR = new Creator<LocalizedEnergy>() {
        public LocalizedEnergy createFromParcel(Parcel parcel) {
            return new LocalizedEnergy(parcel);
        }

        public LocalizedEnergy[] newArray(int i) {
            return new LocalizedEnergy[i];
        }
    };
    private static FormatStringProvider DEFAULT_VALUE_FORMATTER_WITHOUT_UNIT;
    private static FormatStringProvider DEFAULT_VALUE_LONG_UNIT_FORMATTER;
    private static FormatStringProvider DEFAULT_VALUE_UNIT_FORMATTER;
    private static FormatStringProvider ROUNDED_VALUE_FORMATTER_WITHOUT_UNIT;
    private static FormatStringProvider ROUNDED_VALUE_UNIT_FORMATTER;
    private double valueInCalories;

    public static class Deserializer implements JsonDeserializer<LocalizedEnergy> {
        public LocalizedEnergy deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalizedEnergy.fromCalories(jsonElement.getAsDouble());
        }
    }

    public static final class FormatStringProvider {
        protected AbbreviationFormat abbreviationFormat;
        private String cal;
        private String calorie;
        private String calories;
        private Context context;
        private FormatType formatType;
        private String kilojoule;
        private String kilojoules;
        private String kj;
        private ValueDisplay valueDisplay;

        public enum AbbreviationFormat {
            Short,
            Long
        }

        public enum FormatType {
            ValueOnly,
            ValueAndUnitSeparate
        }

        public enum ValueDisplay {
            OneDecimal,
            TwoDecimal,
            Rounded
        }

        public FormatStringProvider(Context context2, FormatType formatType2, ValueDisplay valueDisplay2, AbbreviationFormat abbreviationFormat2) {
            this.context = context2.getApplicationContext();
            this.formatType = formatType2;
            this.valueDisplay = valueDisplay2;
            this.abbreviationFormat = abbreviationFormat2;
            this.cal = context2.getString(R.string.cal_format);
            this.kj = context2.getString(R.string.kj_format);
            this.calorie = context2.getString(R.string.calorie_format);
            this.kilojoule = context2.getString(R.string.kilojoule_format);
            this.calories = context2.getString(R.string.calories_format);
            this.kilojoules = context2.getString(R.string.kilojoules_format);
        }

        public FormatType getFormatType() {
            return this.formatType;
        }

        public ValueDisplay getValueDisplay() {
            return this.valueDisplay;
        }

        public AbbreviationFormat getAbbreviationFormat() {
            return this.abbreviationFormat;
        }

        public Context getContext() {
            return this.context;
        }

        public String getCal() {
            return this.cal;
        }

        public String getKj() {
            return this.kj;
        }

        public String getCalorie() {
            return this.calorie;
        }

        public String getKilojoule() {
            return this.kilojoule;
        }

        public String getCalories() {
            return this.calories;
        }

        public String getKilojoules() {
            return this.kilojoules;
        }

        static String getDisplayValue(double d, ValueDisplay valueDisplay2) {
            switch (valueDisplay2) {
                case OneDecimal:
                    return NumberUtils.localeStringFromDouble(d, 1);
                case TwoDecimal:
                    return NumberUtils.localeStringFromDouble(d, 2);
                case Rounded:
                    return NumberUtils.localeStringFromDoubleNoDecimal(d);
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("type unexpected: ");
                    sb.append(valueDisplay2);
                    throw new IllegalArgumentException(sb.toString());
            }
        }
    }

    public static class Serializer implements JsonSerializer<LocalizedEnergy> {
        public JsonElement serialize(LocalizedEnergy localizedEnergy, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive((Number) Double.valueOf(localizedEnergy.getValue(Energy.CALORIES)));
        }
    }

    public int describeContents() {
        return 0;
    }

    private LocalizedEnergy(double d) {
        this.valueInCalories = d;
    }

    private LocalizedEnergy(Parcel parcel) {
        readFromParcel(parcel);
    }

    public static LocalizedEnergy from(Energy energy, double d) {
        switch (energy) {
            case KILOJOULES:
                return fromKilojoules(d);
            case CALORIES:
                return fromCalories(d);
            default:
                throw new IllegalArgumentException("specified unit type is not recognized!");
        }
    }

    public static LocalizedEnergy fromCalories(double d) {
        return new LocalizedEnergy(d);
    }

    public static LocalizedEnergy fromKilojoules(double d) {
        return new LocalizedEnergy(UnitsUtils.getCalories(d));
    }

    public static LocalizedEnergy fromMeasuredValue(MfpMeasuredValue mfpMeasuredValue) {
        if (mfpMeasuredValue == null) {
            return fromCalories(0.0d);
        }
        if ("calories".equals(mfpMeasuredValue.getUnit())) {
            return fromCalories((double) mfpMeasuredValue.getValue());
        }
        if ("kilojoules".equals(mfpMeasuredValue.getUnit())) {
            return fromKilojoules((double) mfpMeasuredValue.getValue());
        }
        throw new IllegalArgumentException("specified measuredValue is not a valid energy value");
    }

    public double getValue(Energy energy) {
        switch (energy) {
            case KILOJOULES:
                return UnitsUtils.getKilojoules(this.valueInCalories);
            case CALORIES:
                return this.valueInCalories;
            default:
                throw new IllegalArgumentException("specified conversion type is invalid");
        }
    }

    public static String getDisplayStringWithoutUnits(LocalizedEnergy localizedEnergy, Energy energy) {
        return NumberUtils.localeStringFromDoubleNoDecimal(localizedEnergy.getValue(energy));
    }

    public boolean isZero() {
        return this.valueInCalories == 0.0d;
    }

    public static double getValueInUserCurrentUnitsFromMeasuredValue(MfpMeasuredValue mfpMeasuredValue, boolean z) {
        return fromMeasuredValue(mfpMeasuredValue).getValue(z ? Energy.CALORIES : Energy.KILOJOULES);
    }

    public static String getDisplayString(FormatStringProvider formatStringProvider, LocalizedEnergy localizedEnergy, Energy energy) {
        FormatType formatType = formatStringProvider.getFormatType();
        ValueDisplay valueDisplay = formatStringProvider.getValueDisplay();
        AbbreviationFormat abbreviationFormat = formatStringProvider.getAbbreviationFormat();
        if (energy == Energy.CALORIES) {
            String displayValue = FormatStringProvider.getDisplayValue(localizedEnergy.valueInCalories, valueDisplay);
            switch (formatType) {
                case ValueOnly:
                    return displayValue;
                case ValueAndUnitSeparate:
                    if (abbreviationFormat == AbbreviationFormat.Short) {
                        return String.format(formatStringProvider.getCal(), new Object[]{displayValue});
                    }
                    return String.format(localizedEnergy.valueInCalories == 1.0d ? formatStringProvider.getCalorie() : formatStringProvider.getCalories(), new Object[]{displayValue});
            }
        } else if (energy == Energy.KILOJOULES) {
            double kilojoules = UnitsUtils.getKilojoules(localizedEnergy.valueInCalories);
            String displayValue2 = FormatStringProvider.getDisplayValue(kilojoules, valueDisplay);
            switch (formatType) {
                case ValueOnly:
                    return displayValue2;
                case ValueAndUnitSeparate:
                    if (abbreviationFormat == AbbreviationFormat.Short) {
                        return String.format(formatStringProvider.getKj(), new Object[]{displayValue2});
                    }
                    return String.format(kilojoules == 1.0d ? formatStringProvider.getKilojoule() : formatStringProvider.getKilojoules(), new Object[]{displayValue2});
            }
        }
        throw new IllegalArgumentException("invalid units!");
    }

    public static String getDisplayString(Context context, LocalizedEnergy localizedEnergy, Energy energy) {
        if (DEFAULT_VALUE_UNIT_FORMATTER == null) {
            DEFAULT_VALUE_UNIT_FORMATTER = new FormatStringProvider(context.getApplicationContext(), FormatType.ValueAndUnitSeparate, ValueDisplay.OneDecimal, AbbreviationFormat.Short);
        }
        return getDisplayString(DEFAULT_VALUE_UNIT_FORMATTER, localizedEnergy, energy);
    }

    public static String getLongDisplayString(Context context, LocalizedEnergy localizedEnergy, Energy energy) {
        if (DEFAULT_VALUE_LONG_UNIT_FORMATTER == null) {
            DEFAULT_VALUE_LONG_UNIT_FORMATTER = new FormatStringProvider(context.getApplicationContext(), FormatType.ValueAndUnitSeparate, ValueDisplay.OneDecimal, AbbreviationFormat.Long);
        }
        return getDisplayString(DEFAULT_VALUE_LONG_UNIT_FORMATTER, localizedEnergy, energy);
    }

    public static String getRoundedDisplayString(Context context, LocalizedEnergy localizedEnergy, Energy energy) {
        if (ROUNDED_VALUE_UNIT_FORMATTER == null) {
            ROUNDED_VALUE_UNIT_FORMATTER = new FormatStringProvider(context.getApplicationContext(), FormatType.ValueAndUnitSeparate, ValueDisplay.Rounded, AbbreviationFormat.Short);
        }
        return getDisplayString(ROUNDED_VALUE_UNIT_FORMATTER, localizedEnergy, energy);
    }

    public static String getDisplayStringWithoutUnit(Context context, LocalizedEnergy localizedEnergy, Energy energy) {
        if (DEFAULT_VALUE_FORMATTER_WITHOUT_UNIT == null) {
            DEFAULT_VALUE_FORMATTER_WITHOUT_UNIT = new FormatStringProvider(context.getApplicationContext(), FormatType.ValueOnly, ValueDisplay.OneDecimal, AbbreviationFormat.Short);
        }
        return getDisplayString(DEFAULT_VALUE_FORMATTER_WITHOUT_UNIT, localizedEnergy, energy);
    }

    public static String getRoundedDisplayStringWithoutUnit(Context context, LocalizedEnergy localizedEnergy, Energy energy) {
        if (ROUNDED_VALUE_FORMATTER_WITHOUT_UNIT == null) {
            ROUNDED_VALUE_FORMATTER_WITHOUT_UNIT = new FormatStringProvider(context.getApplicationContext(), FormatType.ValueOnly, ValueDisplay.Rounded, AbbreviationFormat.Short);
        }
        return getDisplayString(ROUNDED_VALUE_FORMATTER_WITHOUT_UNIT, localizedEnergy, energy);
    }

    public void add(LocalizedEnergy localizedEnergy) {
        this.valueInCalories += localizedEnergy.valueInCalories;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.valueInCalories);
    }

    private void readFromParcel(Parcel parcel) {
        this.valueInCalories = parcel.readDouble();
    }
}
