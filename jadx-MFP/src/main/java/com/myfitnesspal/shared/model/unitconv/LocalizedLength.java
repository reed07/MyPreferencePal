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
import com.myfitnesspal.shared.util.UnitsUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Length;
import com.uacf.core.exception.UacfNotImplementedException;
import com.uacf.core.util.NumberUtils;
import java.lang.reflect.Type;

public class LocalizedLength implements Parcelable {
    private static final long CENTIMETERS_PER_KILOMETER = 100000;
    public static final Creator<LocalizedLength> CREATOR = new Creator<LocalizedLength>() {
        public LocalizedLength createFromParcel(Parcel parcel) {
            return new LocalizedLength(parcel);
        }

        public LocalizedLength[] newArray(int i) {
            return new LocalizedLength[i];
        }
    };
    private double valueInInches;

    public static class Deserializer implements JsonDeserializer<LocalizedLength> {
        public LocalizedLength deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalizedLength.fromInches(jsonElement.getAsDouble());
        }
    }

    public static class Serializer implements JsonSerializer<LocalizedLength> {
        public JsonElement serialize(LocalizedLength localizedLength, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive((Number) Double.valueOf(localizedLength.getValue(Length.INCHES)));
        }
    }

    public int describeContents() {
        return 0;
    }

    public static LocalizedLength fromInches(double d) {
        return new LocalizedLength(d);
    }

    public static LocalizedLength fromFeetInches(double d, double d2) {
        return fromFeet(d + (d2 / 12.0d));
    }

    public static LocalizedLength fromCentimeters(double d) {
        return new LocalizedLength(UnitsUtils.getInchesFromCentimeters(d));
    }

    public static LocalizedLength fromKilometers(double d) {
        return new LocalizedLength(UnitsUtils.getInchesFromCentimeters(d * 100000.0d));
    }

    public static LocalizedLength fromFeet(double d) {
        return new LocalizedLength(UnitsUtils.getInchesFromFeet(d));
    }

    public static LocalizedLength fromMiles(double d) {
        return new LocalizedLength(UnitsUtils.getInchesFromCentimeters(UnitsUtils.getKilometers(d) * 100000.0d));
    }

    public static LocalizedLength from(double d, Length length) {
        switch (length) {
            case METERS_CENTIMETERS:
            case CENTIMETERS:
                return fromCentimeters(d);
            case KILOMETERS:
                return fromKilometers(d);
            case MILES:
                return fromMiles(d);
            case FEET_INCHES:
            case FEET:
                return fromFeet(d);
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("invalid units specified! ");
                sb.append(length.toString());
                throw new IllegalArgumentException(sb.toString());
        }
    }

    private LocalizedLength(double d) {
        this.valueInInches = 0.0d;
        this.valueInInches = d;
    }

    private LocalizedLength(Parcel parcel) {
        this.valueInInches = 0.0d;
        readFromParcel(parcel);
    }

    public double getValue(Length length) {
        switch (length) {
            case METERS_CENTIMETERS:
            case CENTIMETERS:
                return UnitsUtils.getCentimetersFromInches(this.valueInInches);
            case KILOMETERS:
                return UnitsUtils.getCentimetersFromInches(this.valueInInches) / 100000.0d;
            case MILES:
                return UnitsUtils.getMiles(getValue(Length.KILOMETERS));
            case FEET_INCHES:
            case INCHES:
                return this.valueInInches;
            case FEET:
                return UnitsUtils.getFeetFromInches(this.valueInInches);
            default:
                throw new IllegalArgumentException(String.format("invalid unit type %s specified. see getDisplayString() instead!", new Object[]{length}));
        }
    }

    public static MajorMinorUnits<Length> getMajorMinorUnits(LocalizedLength localizedLength, Length length) {
        int[] iArr;
        String[] strArr;
        int i = AnonymousClass2.$SwitchMap$com$myfitnesspal$shared$util$UnitsUtils$Length[length.ordinal()];
        if (i != 1) {
            switch (i) {
                case 5:
                case 6:
                    strArr = UnitsUtils.getFeetAndInchesAsStringArray(localizedLength.getValue(Length.CENTIMETERS));
                    iArr = UnitsUtils.getFeetAndInchesAsIntArray(localizedLength.getValue(Length.CENTIMETERS));
                    break;
                default:
                    throw new UacfNotImplementedException();
            }
        } else {
            strArr = UnitsUtils.getMeterAndCentimetersAsStringArray(localizedLength.getValue(Length.CENTIMETERS));
            iArr = UnitsUtils.getMeterAndCentimetersIntAsArray(localizedLength.getValue(Length.CENTIMETERS));
        }
        MajorMinorUnits majorMinorUnits = new MajorMinorUnits(length, iArr[0], strArr[0], iArr[1], strArr[1]);
        return majorMinorUnits;
    }

    public void setValue(Length length, double d) {
        switch (length) {
            case METERS_CENTIMETERS:
            case FEET_INCHES:
                throw new IllegalArgumentException(String.format("invalid unit type %s specified. see getDisplayString() instead!", new Object[]{length}));
            case CENTIMETERS:
                this.valueInInches = UnitsUtils.getInchesFromCentimeters(d);
                return;
            case KILOMETERS:
                this.valueInInches = UnitsUtils.getInchesFromCentimeters(d * 100000.0d);
                return;
            case MILES:
                this.valueInInches = UnitsUtils.getInchesFromCentimeters(d * 100000.0d);
                return;
            case FEET:
                this.valueInInches = UnitsUtils.getInchesFromFeet(d);
                return;
            case INCHES:
                this.valueInInches = d;
                return;
            default:
                return;
        }
    }

    public boolean isZero() {
        return this.valueInInches == 0.0d;
    }

    public static String getDisplayString(Context context, LocalizedLength localizedLength, Length length) {
        switch (length) {
            case METERS_CENTIMETERS:
                String[] meterAndCentimetersAsStringArray = UnitsUtils.getMeterAndCentimetersAsStringArray(localizedLength.getValue(Length.CENTIMETERS));
                return context.getString(R.string.m_cm_format, new Object[]{meterAndCentimetersAsStringArray[0], meterAndCentimetersAsStringArray[1]});
            case CENTIMETERS:
                return context.getString(R.string.cm_format, new Object[]{formatDecimal(localizedLength.getValue(Length.CENTIMETERS))});
            case KILOMETERS:
                return context.getString(R.string.km_format, new Object[]{formatDecimal(localizedLength.getValue(Length.KILOMETERS))});
            case MILES:
                return context.getString(R.string.mi_format, new Object[]{formatDecimal(localizedLength.getValue(Length.MILES))});
            case FEET_INCHES:
                String[] feetAndInchesAsStringArray = UnitsUtils.getFeetAndInchesAsStringArray(localizedLength.getValue(Length.CENTIMETERS));
                return context.getString(R.string.height_in_feet_inches, new Object[]{feetAndInchesAsStringArray[0], feetAndInchesAsStringArray[1]});
            case FEET:
                return context.getString(R.string.ft_format, new Object[]{formatDecimal(localizedLength.getValue(Length.FEET))});
            case INCHES:
                return context.getString(R.string.in_format, new Object[]{formatDecimal(localizedLength.getValue(Length.INCHES))});
            default:
                throw new UacfNotImplementedException();
        }
    }

    public static String getDisplayStringWithoutUnits(LocalizedLength localizedLength, Length length) {
        switch (length) {
            case METERS_CENTIMETERS:
            case CENTIMETERS:
                return formatDecimal(localizedLength.getValue(Length.CENTIMETERS));
            case KILOMETERS:
                return formatDecimal(localizedLength.getValue(Length.KILOMETERS));
            case MILES:
                return formatDecimal(localizedLength.getValue(Length.MILES));
            case FEET_INCHES:
            case INCHES:
                return formatDecimal(localizedLength.getValue(Length.INCHES));
            case FEET:
                return formatDecimal(localizedLength.getValue(Length.FEET));
            default:
                throw new UacfNotImplementedException();
        }
    }

    private static String formatDecimal(double d) {
        return NumberUtils.localeStringFromDouble(d, 1);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.valueInInches);
    }

    private void readFromParcel(Parcel parcel) {
        this.valueInInches = parcel.readDouble();
    }
}
