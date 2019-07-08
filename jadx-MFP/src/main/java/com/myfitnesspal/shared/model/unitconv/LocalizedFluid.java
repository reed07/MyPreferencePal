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
import com.myfitnesspal.shared.constants.Constants.UserProperties.Units;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.UnitsUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Water;
import com.uacf.core.util.NumberUtils;
import java.lang.reflect.Type;

public class LocalizedFluid implements Parcelable {
    public static final Creator<LocalizedFluid> CREATOR = new Creator<LocalizedFluid>() {
        public LocalizedFluid createFromParcel(Parcel parcel) {
            return new LocalizedFluid(parcel);
        }

        public LocalizedFluid[] newArray(int i) {
            return new LocalizedFluid[i];
        }
    };
    private static final String CUPS_ABBR = "cups";
    private static final String FLUID_OUNCES_ABBR = "oz";
    private static final String MILLILITERS_ABBR = "ml";
    private double valueInMilliliters;

    public static class Deserializer implements JsonDeserializer<LocalizedFluid> {
        public LocalizedFluid deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalizedFluid.fromMilliliters(jsonElement.getAsFloat());
        }
    }

    public static class Serializer implements JsonSerializer<LocalizedFluid> {
        public JsonElement serialize(LocalizedFluid localizedFluid, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive((Number) Double.valueOf(localizedFluid.getValue(Water.MILLILITERS)));
        }
    }

    public int describeContents() {
        return 0;
    }

    private LocalizedFluid(double d) {
        this.valueInMilliliters = d;
    }

    private LocalizedFluid(Parcel parcel) {
        readFromParcel(parcel);
    }

    public static LocalizedFluid from(Water water, Float f) {
        if (water == Water.CUPS) {
            return fromCups(f.floatValue());
        }
        if (water == Water.FL_OZ) {
            return fromFluidOunces(f.floatValue());
        }
        return fromMilliliters(f.floatValue());
    }

    public static LocalizedFluid fromMilliliters(float f) {
        return new LocalizedFluid((double) f);
    }

    public static LocalizedFluid fromFluidOunces(float f) {
        return new LocalizedFluid(UnitsUtils.getMillilitersFromFluidOunces(f));
    }

    public static LocalizedFluid fromCups(float f) {
        return new LocalizedFluid(UnitsUtils.getMillilitersFromCups(f));
    }

    public double getValue(Water water) {
        switch (water) {
            case MILLILITERS:
                return this.valueInMilliliters;
            case FL_OZ:
                return UnitsUtils.getFluidOunces(this.valueInMilliliters);
            case CUPS:
                return UnitsUtils.getCups(this.valueInMilliliters);
            default:
                throw new IllegalArgumentException("specified conversion type is invalid");
        }
    }

    public static String getDisplayStringWithoutUnits(LocalizedFluid localizedFluid, Water water) {
        return NumberUtils.localeStringFromDoubleNoDecimal(localizedFluid.getValue(water));
    }

    public boolean isZero() {
        return this.valueInMilliliters == 0.0d;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.valueInMilliliters);
    }

    private void readFromParcel(Parcel parcel) {
        this.valueInMilliliters = parcel.readDouble();
    }

    public static int getDisplayDecimalPrecision(Water water) {
        if (water == Water.CUPS) {
            return 2;
        }
        return water == Water.FL_OZ ? 1 : 0;
    }

    public static String getDisplayString(Context context, LocalizedFluid localizedFluid, Water water) {
        int displayDecimalPrecision = getDisplayDecimalPrecision(water);
        if (water == Water.FL_OZ) {
            return String.format(context.getResources().getString(R.string.fl_oz_value), new Object[]{NumberUtils.localeStringFromDouble(localizedFluid.getValue(Water.FL_OZ), displayDecimalPrecision)});
        } else if (water == Water.CUPS) {
            double value = localizedFluid.getValue(Water.CUPS);
            return String.format(context.getResources().getString(value == 1.0d ? R.string.cup_value : R.string.cups_value), new Object[]{NumberUtils.localeStringFromDouble(value, displayDecimalPrecision)});
        } else {
            return String.format(context.getResources().getString(R.string.ml_value), new Object[]{NumberUtils.localeStringFromDouble(localizedFluid.getValue(Water.MILLILITERS), displayDecimalPrecision)});
        }
    }

    public static void setUseCurrentWaterUnit(Water water, Session session) {
        session.getUser().setProperty(Units.WATER_UNIT_PREFERENCE, String.valueOf(water.getValue()));
    }

    public static Water getUserCurrentWaterUnit(Session session) {
        return Water.fromInt(session.getUser().getWaterUnitPreference());
    }

    public static String getCurrentWaterUnitString(Context context, Session session) {
        switch (getUserCurrentWaterUnit(session)) {
            case MILLILITERS:
                return context.getString(R.string.milliliters_setting);
            case FL_OZ:
                return context.getString(R.string.fl_oz_setting);
            default:
                return context.getString(R.string.cups_setting);
        }
    }

    public static String getCurrentWaterUnitAbbrString(Context context, Session session) {
        switch (getUserCurrentWaterUnit(session)) {
            case MILLILITERS:
                return context.getString(R.string.milliliters);
            case FL_OZ:
                return context.getString(R.string.ounces);
            default:
                return context.getString(R.string.cups_lowercase);
        }
    }

    public static String getCurrentWaterUnitPluralAbbrString(Context context, Session session) {
        switch (getUserCurrentWaterUnit(session)) {
            case MILLILITERS:
                return context.getString(R.string.milliliters);
            case FL_OZ:
                return context.getString(R.string.ounces);
            default:
                return context.getString(R.string.cups_setting).toLowerCase();
        }
    }

    public static String getNonTranslatedUnitAbbrString(Session session) {
        switch (getUserCurrentWaterUnit(session)) {
            case MILLILITERS:
                return "ml";
            case FL_OZ:
                return "oz";
            default:
                return "cups";
        }
    }
}
